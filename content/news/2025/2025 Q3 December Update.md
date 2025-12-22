---
title: "December Q3 2025 Project Updates"
date: 2025-12-22T14:00:00+12:00
author: Kathy Davis
summary: "News from Ambrose Bonnaire-Seargent, Thomas Clark, Jeremiah Coyle, Dragan Djuric, and Jeaye Wilkerson" 




---

As 2025 winds down, we have several Q3 project updates and  a few more  coming in January 2026 as several are on staggered schedules. A brief summary of each project is included to provide overall context. Thanks to everyone for your incredible work on these projects!   

[Ambrose Bonnaire-Seargent: Malli](#ambrose-bonnaire-seargent-malli)  
Looking back on this project, it started with a proposal for an external analysis pass that could be used to optimize Malli validators. Now at the completion of the funding
period, we're solving the same problem, but instead of an additional tool, we're applying the analysis directly within Malli's validation algorithm.   


[Thomas Clark: Fastmath](#thomas-clark-fastmath)   
Inasmuch as Lewis Carrol may have creatively objected, complex numbers are now an essential part of modern life: from quantum computing upwards and whether we are aware of it or not. Clojure's support for these numbers however, remains sporadic while it's biggest competitor, the well-known comedy snake - and scripting language https://www.geeksforgeeks.org/python/history-of-python/ treats complex numbers as first-class citizens.   

With this funding, I would like to address the issue somewhat, particularly with regard to the implementation of complex matrices, but concerning a consistent complex API more generally.  


[Jeremiah Coyle: Fireworks](#jeremiah-coyle-fireworks)   
- Publish Fireworks editor plugins/extensions/integrations for Emacs, VS Code, and IntelliJ. These are fairly simple extensions that involve some basic form rewriting for wrapping/unwrapping forms.  
- Add support for automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to [Chalk](https://github.com/chalk/supports-color). [#42](https://github.com/paintparty/fireworks/issues/42)  
- Documentation of interactive workflow. 
- Enhanced documentation for theme creation.
- Call-site options for quick formatting changes. For hifi printing, support call-site option to disable all truncation and ellipsis [#14](https://github.com/paintparty/fireworks/issues/14)   


[Dragan Djuric: Uncomplicate Clojure ML](#dragan-djuric-uncomplicate-clojure-ml)  
My goal with this funding in Q3 2005 is to develop a new Uncomplicate library, ClojureML.  
- a Clojure developer-friendly API for AI/DL/ML models (in the first iteration based on ONNX Runtime, but later refined to be even more general).  
- Implement its first backend engine (based on ONNX Runtime).  
- support relevant operations as Clojure functions.  
- an extension infrastructure for various future backend implementations.  
- a clean low-level integration with Uncomplicate, Neanderthal, Deep Diamond and Clojure abstractions.  
- assorted improvements to Uncomplicate, Neanderthal, Deep Diamond, to support these additions.  
- develop examples for helping people getting started.  
- related bugfixes.  
- TESTS (of course!).  
  

[Jeaye Wilkerson: Jank](#jeaye-wilkerson-jank)    
This quarter, I'll be building packages for Ubuntu, Arch, Homebrew, and Nix. I'll be minimizing jank's dependencies, automating builds, filling in test suites for module loading, AOT building, and the Clojure runtime. I'll be working to get the final Clang and LLVM changes I have upstreamed into LLVM 22, adding a health check to jank to diagnose installation issues, and filling in some C++ interop functionality I couldn't get to last quarter.  Altogether, this quarter is going to be a hodgepodge of all of the various tasks needed to get jank shipped.    

### AND NOW FOR THE REPORTS!  <br>



## Ambrose Bonnaire-Seargent: Malli  
Q3 2025 $9K, Report No. 3, Published December 12, 2025   

Looking back on this project, it started with a proposal for an external analysis pass that could be used to optimize Malli validators. Now at the completion of the funding
period, we're solving the same problem, but instead of an additional tool, we're applying the analysis directly within Malli's validation algorithm. This is an excellent improvement, but the stakes are now _much_ higher as we're changing some of the oldest, most foundational code in Malli.  

The big news is that recursive validators now [compile to recursive functions](https://github.com/metosin/malli/pull/1245)! This is a major optimization, integrated directly into the heart of Malli.  

The road to this point started in 2021, where I [prototyped](https://github.com/metosin/malli/pull/507) and ultimately---a year later---[contributed](https://github.com/metosin/malli/pull/677) a subtle enhancement to `malli.generator` to map recursive schemas onto recursive generators. The main insight: recursive schemas can be detected by finding cycles of Malli refs.  

Over the last few months, I knew I needed to really nail _why_ this works before
exploiting it to optimize Malli's validation.  

This culminated in a [simple but clarifying documentation improvement](https://github.com/metosin/malli/pull/1244) in which we learn something
new about Malli itself.  

I'll include it in full here:

----

#### Mutable registries are a dev-time abstraction

For performance reasons, Malli heavily caches registry
lookups once a schema has been created via `m/schema`.

Don't rely on registry mutations to be recognized consistently
unless all schemas are reparsed. Here's a simple example:

```clojure
(def registry*
  (atom {:int (m/-int-schema)
         :string (m/-string-schema)
         ::node :int}))

(def eagerly-cached
  (m/schema ::node {:registry (mr/mutable-registry registry*)}))

(swap! registry* assoc ::node :string)

(-> eagerly-cached m/deref m/form)
;; => :int
```

Even atomic transactions mutating multiple schemas simultaneously in a mutable registry
is not reliable, as a parsed schema may have cached one eagerly, and another lazily, leading to inconsistent results. See `malli.core-test/caching-of-mutable-registries-test` for demonstration of this phenomenon.  

In practice, this is analogous to Clojure's treatment of vars. If a var
is mutated, the most reliable general strategy to recognize the update
is to refresh all namespaces that use that var. Similarly, if a registry is
mutated, the best strategy for recognizing the update in all schemas is to
recreate all schemas.  

----

Back to ref cycles, this doc is relevant to recursive schemas because the cycle detection algorithm _also_ breaks in the presence of unprincipled mutating registries.
If we assume registries are immutable for the duration of cycle detection, then the algorithm seems air-tight to me, and so we
went forward with using ref cycles to [compile to recursive schemas to recursive validators](https://github.com/metosin/malli/pull/1245).  

Recursive validators are superior to the previous approach because:
1. they can be fully compiled ahead of time  
2. they take constant space relative to their inputs, before it was linear in the maximum depth of the validated input  
3. they only need to compile one layer of recursion, before it was equal to the maximum depth of the validated input  

While this is a great improvement, there is even more we can do, with even higher impact. Not everyone uses recursive schemas, but it's much more common to use refs more than once such as `[:tuple ::expr ::expr]`.
Malli's handling of validators for such schemas could be greatly improved. Here's how I explained it to Malli's maintainers:  

----

Plumatic Schema [solves this](https://github.com/plumatic/schema/blob/74a1ecdb645d3f43b405a27e2b70f72db861b7ca/src/cljc/schema/spec/core.cljc#L74-L86) by caching validators for _all_ schemas during compilation. In addition to handling recursive schemas, it also prevents a nasty exponential blowup of compilation size for even non-recursive schemas, that Malli also suffers from.

Malli's validator compilation is exponential. This registry demonstrates how:  

```clojure
(def registry {::creates-1-validator [:tuple]
               ::creates-2-validators [:tuple ::creates-1-validator ::creates-1-validator ::creates-1-validator ::creates-1-validator]
               ::creates-16-validators [:tuple ::creates-2-validators ::creates-2-validators ::creates-2-validators ::creates-2-validators]
               ::creates-64-validators [:tuple ::creates-16-validators ::creates-16-validators ::creates-16-validators ::creates-16-validators]
               ::creates-256-validators [:tuple ::creates-64-validators ::creates-64-validators ::creates-64-validators ::creates-64-validators]
               ::creates-1024-validators [:tuple ::creates-256-validators ::creates-256-validators ::creates-256-validators ::creates-256-validators]
               ::creates-4096-validators [:tuple ::creates-1024-validators ::creates-1024-validators ::creates-1024-validators ::creates-1024-validators]
               ::creates-16384-validators [:tuple ::creates-4096-validators ::creates-4096-validators ::creates-4096-validators ::creates-4096-validators]
               ::creates-65536-validators [:tuple ::creates-16384-validators ::creates-16384-validators ::creates-16384-validators ::creates-16384-validators]
               ::creates-262144-validators [:tuple ::creates-65536-validators ::creates-65536-validators ::creates-65536-validators ::creates-65536-validators]
               ::creates-1048576-validators [:tuple ::creates-262144-validators ::creates-262144-validators ::creates-262144-validators ::creates-262144-validators]
               ::creates-4194304-validators [:tuple ::creates-1048576-validators ::creates-1048576-validators ::creates-1048576-validators ::creates-1048576-validators]})
```

With this registry, each level of depth N compiles `(m/validator ::creates-1-validator)` 4^N times.  

e.g., `(m/validator ::creates-4194304-validators)` compiles `(m/validator ::creates-1-validator)` 4,194,304 (4^11) times.  

Plumatic Schema would only compile it once. It's not so trivial to achieve with dynamically scoped refs, but it's the same idea as detecting ref cycles, which we can now do reliably. Here's a reproduction of the issue https://github.com/frenchy64/malli/pull/36/files which I have been pondering since discussing https://github.com/metosin/malli/pull/1180  

---

I may propose work on this for a future Clojurists Together project, please stay tuned.  

Thank you Clojurists Together and the Clojure community for funding this project, it was highly enjoyable and I learnt a lot.  
I hope you find the results useful. <br>    

---


## Thomas Clark: Fastmath  
Q3 2025 $2K, Report No. 1, Published December 8, 2025   

### Table of Contents  
1.  [Overview](#org1c5ce13)  
2.  [Surveying the scene](#org5787ad0)  
3.  [Revised goals and towards future-proofed user-experience](#org245f5e3)  
4.  [Outlook](#orgf49cd9c)  


<a id="org1c5ce13"></a>

### Overview

Due to various circumstances - life, the universe and everything etc. - this project started significantly later than it should have and I apologise for that. It did however gain momentum quickly. Below, I consider the progress, as measured both chronologically and according to milestone. In a nutshell though, I have considered different possible avenues of implementation, played with various API strategies and made a couple of draft implementations.  


<a id="org5787ad0"></a>

### Surveying the scene

The first task of the project was to more concretely situate the goals with respect to what already exists: that is, with respect to \`fastmath\` itself, the wider Clojure ecosystem and the available java libraries.  

It was clear from the outset that \`fastmath\` hadn&rsquo;t yet implemented complex matrices, but how real matrices and complex numbers themselves were implemented, was still a question. Thanks to a valuable discussion with generateme though, I had my first look at the depths and came to understand the mixture of Apache Commons and hard-coded Clojure that made up the library as it was.  

It was concluded that \`core.matrix\`, rightly or wrongly, was currently dead to the community and not the right avenue for this project. The next big Clojure allies were Neanderthal and dtype-next. Both of these are very powerful and mature, but neanderthal was considered too low-level for fastmath and dtype-next, although potentially promising, would realistically require a lot of original code writing, with most operations and decomposition etc. needing to be implemented manually. The maths section of the \`qclojure\` library was also considered with interest, but it wasn&rsquo;t implemented with efficiency as a goal. Instead, it will be used as something of a model consumer for this complex matrix project.  

The conclusion therefore, was, at least initially, to wrap a java library. But which one? And how? The natural choice, would have beeen to extend the coverage of [Apache Commons](https://commons.apache.org/proper/commons-math/), but, as a working physicist, performance is a strong goal for me and it seemed that there were faster options available. Surveying the existing documentation online,     \`jblas\` looked powerful, but has complicated dependencies and so the best all round options (as demonstrated by the  [Java Matrix Benchmark](https://github.com/lessthanoptimal/Java-Matrix-Benchmark), and filtered by available &rsquo;complex&rsquo; API) seemed to be [EJML](https://github.com/lessthanoptimal/ejml) and [ojAlgo](https://github.com/optimatika/ojAlgo). I experimented with both of these libraries somewhat and my feeling is that ojAlgo has the greater performance, but that it&rsquo;s API is much less intuitive. It took me significant time to work out how to do basic constructions and operations - and I also found a bug that required reflection (in the Java sense!) to overcome. Using EJML however, was very straightforward and I could quickly draft a solution. Going forward therefore, in the knowledge that this project has a finite deadline, I decided to continue with EJML, but with a caveat. I wanted to keep the implementation details abstracted, so that we can swap alternative backends in and out more easily in the future.  


<a id="org245f5e3"></a>

### Revised goals and towards future-proofed user-experience  
Realising that I might have to change how complex &rsquo;numbers&rsquo; are implemented opened up a considerable can of worms, as did the temptation of unifying the complex implementation with the real implementation. In fact, (over)thinking and implementing a separation of user experience from the Java backend took up much of the remaining time so far. And the decision of how much to change of the existing \`fastmath\` API is an ongoing question, that will have to be finalized with generateme in the second half.  

Fundamentally, the tension in this project is to make a library that is easy to use for maths/physics-minded people, is flexible enough to be extended into the future and yet still still fast enough to live up to the library&rsquo;s name. The current solution therefore is a sophisticated hierarchy of protocols: to represent mathematical identities, and for fast implementation; as well as a new, versioned, multimethod-based API. This second layer features overloaded mathematical operators such that the full linear algebra stack can be used intuitively, with operator ordering and type coercion happening &rsquo;under the hood&rsquo;. If this proves too much of a performance cost though, then you can simply revert to the explicit protocol methods.   

As an example, rather than \`fastmath.protocols.matrix\` beeing one of only two protocols, we now have a full mathematical structure available, with implementations like \`fastmath.algebra.object.number.complex.ejml\` and \`fastmath.algebra.object.matrix.rectangular.complex.ejml\`. This provides more obvious places for future specializations or alternative backends.  

With mathematical properties, we can use Clojure protocols as they were intended, for flexibility and without reinventing the wheel, when it comes to how to partition functions. For example, a complex number, can now be easily implemented according to its definition: as &rsquo;a field on a normed space that has complex and polar coordinates&rsquo;. What is a field you might ask? Well, it&rsquo;s an algebraic structure that forms a ring and a multiplicative group. As an example, that last sentence can now be represented programmatically (below). And by structuring the protocols this way, we get polymorphism across different types and domains for free.  

    (ns fastmath.protocol.algebra.structure.field
      (:require [fastmath.protocol.algebra.structure.ring :as ring]
                [fastmath.protocol.algebra.structure.multiplicative.group :as mgroup]))
    
    (defprotocol  Field)
    
    (def add ring/add)
    (def multiply ring/multiply)
    (def negate ring/negate)
    (def one ring/one)
    (def zero ring/zero)
    
    (def inverse mgroup/inverse)
    
    (defn ? [x]
      (and
       (ring/?  x)
       (mgroup/?  x)
       (satisfies? Field x)))


<a id="orgf49cd9c"></a>

### Outlook  
Having considered the architecture, API creation and first implementation tests, the project, as a whole, is on a firm footing for development. With the expansion of API goals however, the proposal metrics will have to be reprioritised. In this light, the next steps will be to widen the matrix implementations beyond basic operations and to integrate them with other \`fastmath\` functions, like decomposition and fourier functions. After this, I will focus on integration with the \`clay\` system, so that some sort of report and documentation can be published on time. On the current trajectory, it&rsquo;s not clear that the performance comparisons, for example, will be completed during the official funding period, but, if not, they will surely follow promptly.  
<br>

---


## Jeremiah Coyle: Fireworks  
Q3 2025 $2K, Report No. 2, Published November 30, 2025  

I'm happy to report that 5 of the primary goals and 6 of the secondary goals were achieved in Q3. Many thanks to Clojurists Together for supporting this work!  

### Primary goals
  - Add support for automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to [Chalk](https://github.com/chalk/supports-color).<br>[#42](https://github.com/paintparty/fireworks/issues/42)<br>
[Completed](https://github.com/paintparty/fireworks/pull/76)
<br>

  - Support call-site option to disable all truncation and ellipsis<br>
[#14](https://github.com/paintparty/fireworks/issues/14)<br>
[Completed](https://github.com/paintparty/fireworks/commit/d1232b7fe3d522f751009c2cccc8aeca87966d34)
<br>

  - Documentation of interactive workflow:  
    - [`deps.edn` sample project](https://github.com/paintparty/fireworks?tab=readme-ov-file#jvm-clojure-deps-setup) 
    - [`Leiningen` sample project](https://github.com/paintparty/fireworks?tab=readme-ov-file#jvm-clojure-leiningen-setup)
<br>

  - VS Code Integration <br>
[Completed](https://github.com/paintparty/fireworks/blob/main/docs/editors/vscode/vscode.md)
<br>

  - Cursive / IntelliJ Integration <br>
[Completed](https://github.com/paintparty/fireworks/blob/main/docs/editors/cursive/cursive.md)
<br>

  - Emacs Integration<br>
In progress. Work on this will commence and once a sufficient amount of data from the use of the Joyride and Cursive implementations is gathered. This will inform any unforeseen details about ergonomics and/or implementation details.
<br>

### Secondary goals  
  - Allow for call-site changes to the label color.<br>[#53](https://github.com/paintparty/fireworks/issues/53)<br>
[Completed](https://github.com/paintparty/fireworks/pull/76)<br>
```Clojure
(? {:label-color :red} (+ 1 1))
```
<br>

  - Flag for eliding truncation and ellipsis at callsite <br>
[#77](https://github.com/paintparty/fireworks/issues/77)<br>
[Completed](https://github.com/paintparty/fireworks/pull/77)<br>

```Clojure
(? :+ my-coll)
;; as shorthand for:
(? {:truncation? false} my-coll)
```

<br>

  - Add option to produce bold output. <br>
[#70](https://github.com/paintparty/fireworks/issues/70)<br>
[Completed](https://github.com/paintparty/fireworks/pull/76)<br>

```Clojure
(? {:bold? true} (+ 1 1))
```
<br>

  - Add option to format the label as code. <br>
[#82](https://github.com/paintparty/fireworks/issues/82)<br>
[Completed](https://github.com/paintparty/fireworks/pull/82)<br>

```Clojure
(? {:format-label-as-code? true}
   (mapv (fn [i] (str "id-" i))
         (range 20)))
```
<br>

  - Add function to set options globally for project, at runtime, with a `config!` macro. <br>
[#81](https://github.com/paintparty/fireworks/issues/81)<br>
[Completed](https://github.com/paintparty/fireworks/pull/81)

```Clojure
(fireworks.core/config!
 {:format-label-as-code? true
  :template              [:file-info :form-or-label :result]
  :label-length-limit    100})
```

<br>

  - Properly display contents and badges of native js data structures, when they are within a native cljs data structure.<br>
  [#46](https://github.com/paintparty/fireworks/issues/46)<br>
[Completed](https://github.com/paintparty/fireworks/pull/86)

```Clojure
(? [#js {:a 1 :b 2}
    (new js/Set #js["foo" "bar"])
    (into-array [1 2 3])
    (new js/Map #js[#js[3 1] #js[4 2]])
    (new js/Int8Array #js[1 2 3])])
```

<br>
<br>
<br>

The latest release of Fireworks is [`v0.16.1`](https://clojars.org/io.github.paintparty/fireworks/versions/0.13.0), which features the enhancements listed above.<br>


---


## Dragan Djuric: Uncomplicate Clojure ML  
Q3 2025 $9K, Report No. 3, Published December 1, 2025   

### Final progress after the third month:
In the first two months, I have already released more than 10 versions to Clojars
as `org.uncomplicate/diamond-onnxrt`, with the progressively better coverage
of the onnxruntime's C API, established a nicer Clojure API for onnxruntime internals,
higher level Deep Diamond and Neanderthal model integrations, and make inroads
into GPU support with CUDA.  

In the third month, i tried several typical open models, and did the development
and refinements to support the necessary features required by these real-world examples.  

I refined the low-level and high-level model support incrementally, to support
as much automatic wiring of the models with Clojure as possible, and I am quite pleased
with the result. One of the goals of all Uncomplicate libraries
is to offer access to all abstraction levels to Clojure programmers: from the lowest fine grained just above the C API,
to the highest "magic" that configures itself, and we supported this here to the fullest.
Everyone can use the internal core namespace to program every single detail by themselves in Clojure - if they wish so -
while they also can leverage mid-level abstract functions that build standalone models,
OR, let Deep Diamond do everything for themselves. There's something even handier:
even the most abstract level supports detailed configuration of the models, with sane defaults.
This is what I spent most of the time in the third month of the project: the overall usability
and polishing!  

The final result: now we can load a model, and almost everything
related to loading and setting that model up with Deep Diamond's Clojure tensors is automatic.
If we want to configure most of the stuff that can be configured, it's an options map away,
with nice Clojure keywords, while we don't have to worry how that configuration
produces its results. At the same time, if the users have more specific requirements,
or they don't like how I implemented it, or they simply just want to learn, they
still have a ladder down the rabbit hole, and they can use any of the lower layers
as they please, as independent nuts and bolts for their own creations.  

I released a few more versions to Clojars (the most recent one is 0.20.0).  

An example of what we can do now is that we can now clone a Hugging Face repository of
Google's open model Gemma 3 in ONNX format, we can load it without much hassle as DD blueprint,
instantiate it as a standalone layer function, get the access to input and output parameters
as nice DD Clojure tensors, easily load them with data, and run the model as a straightforward
Clojure function evaluation! Now, a casual onlooker would think: "Great. Now I just load Gemma 3,
and I have a home-sized mini Gemini!" Well, no. This model accepts tensor full of numbers, and
it can infer tensors full of numbers that indicate next token(s). But, LLM is more than that. It accepts
strings (or images, etc) re-computes these strings into appropriate numbers (tensors),
runs the inference in a loop, bookkeep various context tensors, makes sense of the output numbers,
etc. That's a whole new level of application of these moving parts, and requires
another level of applicative automation.
Some models do not need that applicative automation, but LLMs are more complex beasts.  

So, looking back at what I proposed to implement, I would declare that I'm quite
happy that I achieved everything planned, and even achieved a simpler API than envisioned at the start.  
No we can bite even at the LLMs at the next step, which is a focused effort on its own.  
<br>

---

## Jeaye Wilkerson: Jank
Q3 2025 $9K, Report No. 3, Published November 30, 2025   

Howdy folks! Thank you so much for the sponsorship this quarter. This month was
Conj 2025 month, so leading up to the Conj I was very focused on preparing for
my talk and two different Conj working groups. Outside of that, though, I have
been tackling overall jank stability. The number one concern there was GC
stability, since our GC usage of BDWGC (Boehm) was leading to either leaks or
intermittent crashes. In total, this has involved three weeks of spelunking in
both the GC code and jank's code.  

The crashes were nearly always caused by the GC prematurely collecting an object
because it could no longer find a usage of it. In some cases, this happened
because that object was only accessible from the system heap (i.e. normal `new`
or `malloc`), rather than the GC heap. These cases could be fixed by correct
usage of the GC allocator for C++ collections, which we sometimes missed. In
another case, jank was using a thread-local collection to store Clojure
bindings. However, on Linux, thread-local memory is stored separately from
normal memory, so the GC is unable to find references to the objects within that
collection. Finally, I have found that there are some aspects of our LLVM IR
generation which lead the GC to prematurely collect. I have solved some of these
issues, but more investigation and development remains. Curiously, though, I
found that using jank's C++ code generation, rather than LLVM IR, skirts these
issues.  

To prioritize stability for the upcoming alpha release in December, I decided to
focus on C++ generation as the default. This has required some catching up,
since we've been running both for a while but we've been focusing on LLVM IR.
Now, at the end of the month, all tests are passing with C++ code generation and
there are no known GC issues on macOS and Linux. I'll continue developing the
LLVM IR generation, to resolve these issues, but I'll take the time needed to do
it right. Until then, C++ generation as a default provides many benefits and no
behavioral change.  

The biggest remaining task for the alpha release is now documentation, which I
will be tackling in the next few weeks. Then it's alpha time!  
<br>





