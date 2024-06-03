---
title: "April & May 2024 Short-Term Project Updates"
date: 2024-06-03T08:30:00+08:00
summary: Updates from clj-lmerge, Compojure=api, Instaparse, Jank, Plexus, Lost in Lambduhhs Podcast, Scicloj
author: Kathy Davis
draft: True  


---

We've got several updates to share from our Q1 and Q2 project developers. Check out the latest in their following April and May reports.  


clj-merge tool: Kurt Harriger  
Compojure-api: Ambrose Bonnaire-Sergeant  
Instaparse: Mark Engelberg  
Jank: Jeaye Wilkerson  
Plexus: John Collins  
Lost in Lambduhhs Podast: L. Jordan Miller  
Scicloj: Daniel Slutsky 

## Clj-merge: Kurt Harriger  
Q2 2024 Report No. 1, Published May 15, 2023

### Introduction  

This tool aims to reduce unnecessary conflicts due to whitespace and syntax peculiarities by using a more semantic approach to diffing and merging. I'm grateful for the support from ClojuristsTogether and the invaluable feedback and support from the Clojure community.  

### Milestones Overview  

The project was structured around several key milestones:  

1. Development of the MVP.  
2. Enhancement of diff handling and presentation.  
3. Community engagement and feedback integration.  
4. Performance optimization and cross-platform compatibility.  

### Milestone Progress  

1. **Development of the MVP**  
    
    - **Goal**: To create a minimal viable product using `editscript` and `rewrite-clj`.  
    - **Progress**: The MVP was successfully developed and demonstrated its capability in resolving basic merge conflicts. Initially, extensive diffs suggested a complete rewrite of `editscript` might be necessary. However, implementing isomorphic translations between `rewrite-clj` node representations proved to be a sufficient workaround for now.  
    - **Next Steps**: The focus will now shift towards refining this MVP, enhancing its error handling, and improving the user feedback system to make the tool more robust.  
2. **Enhancement of Diff Handling and Presentation**  
    
    - **Goal**: To improve the readability and utility of diffs for developers.  
    - **Progress**: The generated diffs are more machine-readable than human-readable, early use of the tool has underscored the importance of easily interpretable diffs emphasizing the need for better visualization.  
    - **Next Steps**: I plan to continue working on improving the presentation of diffs, making them easier to understand and act upon.  
3. **Community Engagement and Feedback Integration**  
    
    - **Goal**: To actively engage with the community to gather detailed feedback and real-world merge conflict examples.  
    - **Progress**: Some feedback and bug reports have been received, such as an "index out of range" error; however, collecting comprehensive examples has proved challenging.  
    - **Next Steps**: I aim to increase efforts to engage the community and hope to present at one of the clojure meetups in the near future.  
4. **Performance optimization and cross-platform compatibility**  
    
    - **Goal**: Simplify the installation process  
    - **Progress**: The project has been successfully compiled with GraalVM for fast startup. However, these are built during the install process and require GraalVM to be available at install.    
    - **Next Steps**: CICD process to publish the binaries for download.  

### Future Vision and Calls to Action  

Moving forward, enhancing diff visualization will be my primary focus. A more intuitive representation of changes will not only improve the tool's usability but also its adoption. I encourage everyone in the Clojure community to try clj-mergetool, especially in challenging merge scenarios, and share any issues or feedback. Your contributions are crucial for refining the tool and expanding its capabilities.  

Thank you for your continued support and contributions to the clj-mergetool project.   <br>

---

## Compojure-api: Ambrose Bonnaire-Sergeant  
Q2 2024 Report No. 1, Published April 30, 2024  

### ring-swagger  
I have released ring-swagger 1.0.0, compojure-api 1.1.14 and 2.0.0-alpha33
which all include a critical fix to prevent [this memory leak](https://github.com/metosin/compojure-api/issues/454).  

Rajkumar Natarajan proposed [OpenAPI3 support](https://github.com/metosin/ring-swagger/pull/150) and I have been reviewing it.  

#### 1.0.0 (30.4.2024)  
* Fix memory leaks via multimethods caching default dispatch values: https://github.com/metosin/compojure-api/issues/454  



### compojure-api  
My two main focuses with compojure-api have been to make 2.x backwards compatible with 1.x
and implement performance improvements.  

I have drafted some further performance ideas as [issues](https://github.com/metosin/compojure-api/issues)
and the remaining tasks for 1.x compatilibity are [here](https://github.com/metosin/compojure-api/issues/462).  

#### 2.0.0-alpha33 (2024-04-30)  
* Throw an error on malformed `:{body,query,headers}`, in particular if anything other than 2 elements was provided
* Disable check with `-Dcompojure.api.meta.allow-bad-{body,query,headers}=true`
* 50% reduction in the number of times `:{return,body,query,responses,headers,coercion,{body,form,header,query,path}-params}` schemas/arguments are evaluated/expanded
* saves 1 evaluation per schema for static contexts
  * saves 1 evaluation per schema, per request, for dynamic contexts
* Fix: Merge `:{form,multipart}-params` `:info :public :parameters :formData` field at runtime
* Add `:outer-lets` field to `restructure-param` result which wraps entire resulting form
* Remove `static-context` macro and replace with equivalent expansion without relying on compojure internals.
* Upgrade to ring-swagger 1.0.0 to fix memory leaks  

#### 2.0.0-alpha32 (2024-04-20)  

* Fix empty spec response coercion. [#413](https://github.com/metosin/compojure-api/issues/413)
* Add back `defapi` (and deprecate it)
* Remove potemkin [#445](https://github.com/metosin/compojure-api/issues/445)
* Add back `compojure.api.routes/create`
* Add back `middleware` (and deprecate it)
* Make `context` :dynamic by default
* Add `:static true` option to `context`
* Add static context optimization coach
  * `-Dcompojure.api.meta.static-context-coach=print` to print hints
  * `-Dcompojure.api.meta.static-context-coach=assert` to assert hints
* port unit tests from midje to clojure.test  <br>

---

## Instaparse: Mark Engelberg   
Q1 2024 Report 2   Published May 31, 2024  

Thanks to funding from Clojurists Together, I have continued to review instaparse pull requests that have been submitted over the past couple of years.  

The most interesting and useful issue, which had languished among the pull requests for over two years, was a suggestion to incorporate namespaced non-terminals, so I am pleased to report that this feature has now been implemented, tested, documented, and deployed in instaparse version 1.5.0. The pull request wasn't quite usable out of the box, as it relied on a feature unique to Clojure 1.11, and I always strive for instaparse to be backwards compatible to Clojure 1.5. But it provided a great starting point for implementation. I think the community will find this feature to be useful.  

For the final third of my Clojurists Together time, I have my eye on a bug with negative lookahead that was discovered shortly after instaparse's initial release. It's something I have always wanted to fix, but have never had the time to solve it as the problem is quite subtle, and any fix will require in-depth testing since it could have wider ramifications.  <br>

---

## Jank: Jeaye Wilkerson  
Q2 2024 Reports 1&2, Published April 30 & May 31, 2024  

### April 30, 2024 Report 1   
This quarter, I'm being funded by [Clojurists Together](https://www.clojuriststogether.org/)
to build out jank's lazy sequences, special `loop*` form, destructuring, and
support for the `for` and `doseq` macros. Going into this quarter, I had only a
rough idea of how Clojure's lazy sequences were implemented. Now, a month in,
I'm ready to report some impressive progress!   

### Lazy sequences  
There are three primary types of lazy sequences in Clojure. I was planning on
explaining all of this, but, even better, I can shine the spotlight on
[Bruno Bonacci's blog](https://blog.brunobonacci.com/2023/09/08/buffered-sequences/),
since he's covered all three of them very clearly. In short, we have:  

1. Per-element lazy sequences
2. Chunked lazy sequences
3. Buffered lazy sequences  

This month, I have implemented per-element lazy sequences, along with partial
support for chunked lazy sequences. Chunked lazy sequences will be finished next
month. By implementing even per-element lazy sequences, so many new
opportunities open up. I'll show what I mean by that later in this post, so
don't go anywhere!  

### Loop  
Prior to this month, jank supported function-level `recur`. As part of this
month's work, I also implemented `loop*` and its related `recur`. When we look at
how Clojure JVM implements `loop*`, it has two different scenarios:  

1. Expression loops
2. Statement loops  

If a loop is in a statement position, Clojure JVM will code-generate labels with
goto jumps and local mutations. If the loop is an expression, Clojure JVM
generates a function around the loop and then immediately calls that. There is
*potentially* a performance win of not generating the function wrapper and
calling it right away, but note that this particular idiom is commonly
identified and elided by optimizing compilers. It even has its own acronym:
[IIFE](https://en.wikipedia.org/wiki/Immediately_invoked_function_expression).
(see [this](https://rigtorp.se/iife/) also)  

jank, for now anyway, simplifies this by always using the IIFE. It does it in a
more janky way, though, which is interesting enough that I'll share it with you
all. Let's take an example `loop*` (note that the special form of `loop` is
actually `loop*`, same as in Clojure; `loop` is a macro which provides
destructuring on top of `loop*` -- now you know):  

```clojure
(loop* [x 0]
  (when (< x 10)
    (println x)
    (recur (inc x))))
```

Given this, jank will replace the `loop*` with a `fn*` and just use function
recursion. Initial loop values just get lifted into parameters. The jank compiler
will transform the above code into the following:  

```clojure
((fn* [x]
   (when (< x 10)
     (println x)
     (recur (inc x)))) 0)
```

jank code-generates function recursion into a `while(true)` loop with mutation on some
locals for each iteration, similar to Clojure.  

However, `loop*` is tricky, since it can also do anything `let*` can do. For
example (also note: no recursion):  

```clojure
(loop* [a 1
        b (* 2 a)]
  (println a b))
```

Since we're using `a` in the binding for `b`, we can't know `b` until we've
calculated `a`, and doing so can involve any arbitrary expression. Agh! This
can't work if we just dump those into the positional parameters of the IIFE. So
jank gets around this by actually just wrapping it in a `let*`. 🙃  

```clojure
(let* [a 1
       b (* 2 a)]
  ((fn* [a b]
    (println a b)) a b))
```

This could be done in a macro, but since it's a language-level feature, the
compiler does it for us. This means you can still use `loop*` even if you're
running without `clojure.core`. As mentioned, this is potentially slower, in the
scenario of the loop being in statement position. We can return to this when the
performance of loops is the most important thing to tackle. Right now, parity
with Clojure and getting jank onto your machine are most important.  

### Destructuring  
Clojure supports all kinds of fancy destructuring of sequences, maps, and
keyword arguments. We use destructuring in `let`, `defn`, and `loop`, primarily. One
interesting thing about this destructuring is that there's no compiler support
for it at all; it's not a language-level feature. It's a library feature, done
entirely in macros. The amazing thing about it is that, as long as we support
all of the core functions required, we can support destructuring. The actual
`destructure` function is huge, but you can see it in Clojure's source
[here](https://github.com/clojure/clojure/blob/06d450895e2d4028afaa4face17f8e597c772a24/src/clj/clojure/core.clj#L4417-L4511).  

This month, I implemented all of the missing functions required for
the `destructure` function to be ported over to jank. Largely, once all those
functions were implemented, the port just meant updating Java interop in a few
places to be C++ interop. Now jank supports all of the fancy destructuring
Clojure does, in all the same places. This helps demonstrate how much closer
jank is to being a complete Clojure dialect, since complex functions like this
can *almost* just work.  

### New clojure.core functions  
So, to support lazy sequences and destructuring, I needed to add several new
core functions. While adding those, I tended toward implementing any similar or
surrounding functions as well. I got a little carried away, to be honest. Let's
take a look at the new functions jank now supports.  

| | |
|---|---|
| `take`  (no transducer) |       `cycle` |
| `take-while` (no transducer) | `repeat` |
| `drop` (no transducer) |       `seq?` |
| `filter` (no transducer) |     `concat` |
| `identity` |                   `->` |
| `constantly` |                 `->>` |
| `into` (no transducer) |       `cond->` |
| `mapv` |                       `zipmap` |
| `filterv` |                    `last` |
| `reduce` |                     `butlast` |
| `nthrest` |                    `map?` |
| `nthnext` |                    `key` |
| `partition` |                  `val` |
| `partition-all` |              `dissoc` |
| `partition-by` |               `ident?` |
| `dorun` |                      `simple-ident?` |
| `doall` |                      `qualified-ident?` |
| `when-let` |                   `boolean` |
| `when-some` |                  `nth` |
| `when-first` |                 `loop` |
| `split-at` |                   `peek` |
| `split-with` |                 `pop` |
| `drop-last` |                  `for` |
| `take-last` |                  `chunk-buffer` |
| `chunk-append` |             `destructure`     |  

That's 52 new functions/macros! That alone amounts to around 10% of all the
functions in `clojure.core` jank will be implementing. A few of these will need
some updates once jank fully supports chunked lazy sequences and transducers,
but they're all very usable today. You may also note that `for` is in there,
which was one of the goals this quarter.  

### Migration from Cling to Clang  
jank is much closer to running on Clang's JIT compiler than it was a month ago.
Some recent patches have landed which partially address a blocking bug with
pre-compiled header handling in Clang's internal C++ JIT compiler. I have
identified another small reproduction case for what I hope to be the rest of the
issues. Part of my work this month involved getting jank running on LLVM
19 and updating filling out the related CMake system to be able to flexibly
bring in LLVM on any system.  

Once jank moves away from Cling in favor of Clang, building and distributing
jank will be significantly easier. Developers won't need to compile a custom
Cling/Clang/LLVM stack. On top of that, Clang's JIT compiler has recently landed
support for loading C++20 modules, which can serve as an less-portable
equivalent to JVM's class files, allowing jank to load pre-compiled modules very
quickly. This will drastically optimize jank's startup time, but will require
some work to get going. I'll keep you updated!  

### What's next?  
I'm well ahead of schedule, for the quarter, but I need to finish up chunked
sequences and `doseq`. I'll have time after that and I'd like to get atoms
working, since most Clojure programs have some form of state. From there, I can
look into strengthening native interop and making jank more easily
distributable, but let's not get ahead of ourselves.  

### May 31, 2024 Report 2  

Hey folks! I've been building on last month's addition of lazy sequences,
`loop*`, destructuring, and more. This month, I've worked on rounding out
lazy sequences, adding more mutability, better meta support, and some big
project updates.  

### Chunked sequences  
I've expanded the lazy sequence support added last month to include chunked
sequences, which pre-load elements in chunks to aid in throughput. At this
point, only `clojure.core/range` returns a chunked sequence, but all of the
existing `clojure.core` functions which should have support for them do.  

If you recall from last month, there is a third lazy sequence type: buffered
sequences. I won't be implementing those until they're needed, as I'd never even
heard of them before researching more into the lazy sequences in Clojure.  

### Initial quarter goals accomplished  
Wrapping up the lazy sequence work, minus buffered sequences, actually checked
off all the boxes for my original goals this quarter. There's a bottomless
well of new tasks, though, so I've moved onto some others. So, how do I decide what
to work on next?  

My goal is for you all to be writing jank programs. The most important tasks are
the ones which bring me closer to that goal. Let's take a look at what those
have been so far.  

### Volatiles, atoms, and reduced  
Most programs have some of mutation and we generally handle that with volatiles
and atoms in Clojure. jank already supported transients for most data structures,
but we didn't have a way to hold mutable boxes to immutable values. Volatiles are
also essential for many transducers, which I'll mention a bit later. This month,
both volatiles and atoms have been implemented.  

Implementing atoms involved a fair amount of research, since lockless
programming with atomics is
[not nearly as straightforward](https://www.youtube.com/watch?v=c1gO9aB9nbs)
as one might expect.  

As part of implementing atoms, I also added support for the `@` reader macro and
the overall `derefable` behavior. This same behavior will be used for delays,
futures, and others going forward.  

### Meta handling for defs  
Last quarter, I added support for meta hints, but I didn't actually use that
metadata in many places. Now, with defs, I've added support for the optional
meta map and doc string and I also read the meta from the defined symbol. This
isn't a huge win, but it does mean that jank can start using doc strings
normally, and that we can do things like associate more function meta to the var
in a `defn`, which can improve error reporting.  

### Monorepo  
There will be many jank projects and I've known for a while that I want them
all to be in one git [monorepo](https://en.wikipedia.org/wiki/Monorepo).
This makes code sharing, searching, refactoring, and browsing simpler. It gives
contributors one place to go in order to get started and one place for all of
the issues and discussions. It's not my intention to convince you of anything, if
you're not a fan of monorepos, but jank is now using one.   

This started by bringing in [lein-jank](https://github.com/Samy-33/lein-jank),
which was initially created by Saket Patel. From there, I've added a couple of
more projects, which I'll cover later in this update.  

### New clojure.core functions  
Following last month's theme, which saw 52 new Clojure functions, I have
excellent news. We actually beat that this month, adding 56 new Clojure functions!
However, I only added 23 of those and the other 33 were added
by [madstap](https://github.com/madstap) (Aleksander Madland Stapnes). He did
this while also adding the transducer arity into pretty much every existing
sequence function. I added volatiles to support him in writing those transducers.  

| | |
|---|---|
| `dotimes` | `chunk` |
| `chunk-first` | `chunk-next` |
| `chunk-rest` | `chunk-cons` |
| `chunked-seq?` | `volatile!` |
| `vswap!` | `vreset!` |
| `volatile?` | `deref` |
| `reduced` | `reduced?` |
| `ensure-reduced` | `unreduced` |
| `identical?` | `atom` |
| `swap!` | `reset!` |
| `swap-vals!` | `reset-vals!` |
| `compare-and-set!` | `keep` |
| `completing` | `transduce` |
| `run!` | `comp` |
| `repeatedly` | `tree-seq` |
| `flatten` | `cat` |
| `interpose` | `juxt` |
| `partial` | `doto` |
| `map-indexed` | `keep-indexed` |
| `frequencies` | `reductions` |
| `distinct` | `distinct?` |
| `dedupe` | `fnil` |
| `every-pred` | `some-fn` |
| `group-by` | `not-empty` |
| `get-in` | `assoc-in` |
| `update-in` | `update` |
| `cond->>` | `as->` |
| `some->` | `some->>` |  

### New projects  
At this point, I was thinking that jank actually has pretty darn good Clojure
parity, both in terms of syntax and essential core functions. So how can I 
take the best steps toward getting jank onto your computer?  

Well, I think the most important thing is for me to start writing some actual
projects in jank. Doing this will require improving the tooling and will help
identify issues with the existing functionality. The project I've chosen is
jank's nREPL server. By the end of the project, we'll not only have more
confidence in jank, we'll all be able to connect our editors to running jank
programs!  

### nREPL server  
nREPL has [some docs](https://nrepl.org/nrepl/building_servers.html) on building
new servers, so I've taken those as a starting point. However, let's be clear,
there are going to be a *lot* of steps along the way. jank is *not* currently
ready for me to just build this server today and have it all work. I need a goal
to work toward, though, and every quest I go on is bringing me one step closer
to completing this nREPL server in jank. Let's take a look at some of the things
I know I'll need for this.  

#### Module system  
jank's module system was implemented two quarters ago, but since there are no
real jank projects, it hasn't seen much battle testing. To start with, I will
need to work through some issues with this. Already I've found (and fixed) a
couple of bugs related to module writing and reading while getting started on
the nREPL server. Further improvements will be needed around how modules are
cached and timestamped for iterative compilation.

#### Native interop  
Next, jank's native interop support will need to be expanded. I've started on that
this month by making it possible to now write C++ sources alongside your jank
sources and actually `require` them from jank! As you may know, jank allows
for inline C++ code within the special `native/raw` form, but by compiling
entire C++ files alongside your jank code, it's now much easier to offload
certain aspects of your jank programs to C++ without worrying about writing too
much C++ as inline jank strings.  

jank's native interop support can be further improved by declaratively noting
include paths, implicit includes, link paths, and linked libraries as part of
the project. This will likely end up necessary for the nREPL server.  

#### AOT compilation  
Also required for the nREPL server, I'll need to design and implement jank's AOT
compilation system. This will involve compiling all jank sources and C++ sources
together and can allow for direct linking, whole-program link time optimizations
(LTO), and even static runtimes (no interactivity, but smaller binaries).  

#### Distribution  
Finally, both jank and the nREPL server will need distribution mechanisms for
Linux and macOS. For jank, that may mean AppImages or perhaps more integrated
binaries. Either way, I want this to be easy for you all to use and I'm
following Rust/Cargo as my overall inspiration.  

I hope I've succeeded in showing how much work still remains for this nREPL
server to be built and shipped out. This will take me several months, I'd
estimate. However, I think having this sort of goal in mind is very powerful and
I'm excited that jank is far enough along to where I can actually be doing this.  

### nREPL server progress  
Since I have C++ sources working alongside jank source now, I can use
`boost::asio` to spin up an async TCP server. The data sent over the wire for
nREPL servers is encoded with bencode, so I started on a `jank.data.bencode`
project and I have the decoding portion of that working. From there, I wanted to
write my tests in jank using `clojure.test`, but I haven't implemented
`clojure.test` yet, so I looked into doing that. It looks like `clojure.test`
will require me to implement multimethods in jank, which don't yet exist. On top
of that, I'll need to implement `clojure.template`, which requires
`clojure.walk`, none of which have been started.  

I'll continue on with this depth-first search, implementing as needed, and then
unwind all the way back up to making more progress on the nREPL server. Getting
`clojure.test` working will be a huge step toward being able to
[dogfood](https://en.wikipedia.org/wiki/Eating_your_own_dog_food) more, so I
don't want to cut any corners there. Once I can test my decode
implementation for bencode, I'll write the encoding (which is easier) and then
I'll be back onto implementing the nREPL server functionality.  

Hang tight, folks! We've come a long way, and there is still so much work to do,
but the wheels are rolling and jank is actually becoming a usable Clojure
dialect. Your interest, support, questions, and encouragement are all the
inspiration which keeps me going.  <br>

---

## Plexus: John Collins   
Q2 2024 Report 1. Published May 15, 2024  

### 1. Much better Loft Algorithm.   

The previous loft algorithm simply mapped vertices between one-to-one between cross sections. This meant that lofted cross-sections had to have the same number of vertices. The new loft algorithm is much more general and now supports many-to-one and one-to-many vertex mappings. Loft is a very important operation that is not available in many programmatic CAD tools, so I'm excited to have support for this.  

I go over this a bit more in a blog post:
http://www.cartesiantheatrics.com/2024/04/09/perfect-loft.html  

### 2. Text rendering Support  

I wrote a first-cut at support for text rendering in companion library to `plexus` called `clj-manifold3d`. There is not now a simple function called `text` that takes a .ttf font file, a string, and a few other parameters and renders the string to a 2D cross section that can be extruded like any other cross section. This is done in the c++ by interpolating freetype2 glyphs.  

Aside from built-in support for textures, this is the last major feature that clj-manifold lacked compared to OpenSCAD.  

### 3. N Slices  

There is now a highly efficient algorithm to slice a manifold into N evenly spaced 2D cross sections. This is a primitive that can be used to implement custom slicers. In the future, I will likely be writing a slicer that gives a high level of control over the G-code generated.  

### 4. Three Point Arcs  

You can now create arcs given three points. This lets you draw circles or arcs in a similar way that is often done in graphical CAD systems. It is useful for many complex polygon constructions.  


### 5. Progress on Navigation on Manifolds  

This is totally unfinished and unproven, but I have been experimenting heavily with a somewhat unique way doing custom texturing of manifolds. It works by creating a set of primitives that make it easy to "draw on" arbitrary 3D manifolds as if you're drawing on a 2D plane. I'm hoping achieve something more fine-tunnable than common texturing methods (like UV mapping) and that could integrate seamlessly with Plexus's abstractions. It has proven to be challenging unclear how well it's going to work out.  

While continuing to tinker with this approach, I'll likely add support for more standard texturing.  

### 6. Example projects  

I improved and updated example projects that demonstrate using plexus for real-world problems. These are:  

  - A Simple rapidly printable hydroponic tower: https://github.com/SovereignShop/spiralized-hydroponic-tower  

  - Kossel delta printer: https://github.com/SovereignShop/kossel-printer/  

I will try more examples in the future.  

### 7. Better support for 2D affine transforms  

2D affine transforms (3x2 matrices) are now fully supported. They support the same API as 2D cross sections. Having affine transforms as independent objects is a big advantage over OpenSCAD.  

### 8. Tests, Docs, Code Cleanup  

Documentation is still sorely lacking, but README's have been improved. There are more tests now and somewhat better API documentation in the code. There are also now github action pipelines in an effort to bring a bit more transparency and professionalism to the dev process.  <br>

---

## Lost in Lambduhhs Podast: L. Jordan Miller  
Q2 2024 Report 1. Published May 15, 2024.  

### Progress Overview  

I have made significant progress on my new podcast series, thanks to the support from Clojurists Together. Here are the key milestones I've achieved so far and my plans moving forward:  

#### Platform Subscription  
- **Subscribed to Riverside.fm** as my podcasting platform to ensure high-quality audio recordings and ease of use for my guests.  

#### Guest Coordination  
- **First Four Guests Identified and Scheduled:**  
  - David Nolan - Recorded on Monday, May 13.
  - Arne Brasseur - Scheduled to record on Wednesday, May 15.
  - Recia Roopnarine - Scheduled to record on Wednesday, May 21.
  - Raf Dittwald - Scheduled to record on Wednesday, May 21.  

#### Recordings and Editing  
- **Completed Recording:**  
  - My first session with David Nolan was successfully recorded.
- **Current Task:**  
  - I am currently editing and post-processing the interview with David Nolan.
- **Upcoming Recordings:**  
  - Recording with Arne Brasseur on May 15.
  - Recording with Recia Roopnarine and Raf Dittwald on May 21.  

#### Release Schedule  
- **Planned Release:**  
  - I plan to release the episode featuring David Nolan within the next week.

### Next Steps  

- Complete the editing phase for the first episode.
- Prepare and test all technical setups for the upcoming recordings.
- Begin outreach for additional guests to feature in future episodes.  

### Conclusion  

I am on track with my project timeline and excited about the content I am creating. I will continue to provide updates as I progress further.  <br>

---

## Scicloj: Daniel Slutsky  
Q1 2024 Report 3. Published April 30, 2024  

April 2024 was the last of three months on the Clojurists Together project titled "Scicloj Community Building and Infrastructure".  

Scicloj is an open-source group developing Clojure tools and libraries for data and science. As a community organizer at Scicloj, my current role is to help make the emerging Scicloj stack easier and more accessible for broad groups of Clojurians. I collaborate with a few Scicloj members on this.   

While this is the last update under the Clojurists Together 2024 Q1 support, the project will, of course, continue.   

Below are the sub-projects that were addressed during April 2024. They are listed by their proposed priorities for the coming month.   

The new real-world-data group is ranked highest for its impact on community growth. This means the following. Assuming this group will (hopefully) grow well and demand attention, the goals of other projects will receive less attention and will be delayed. However, some of them (e.g., required extensions or bugfixes to libraries) will receive more attention if the real-world-data group requires them.  


### [The real-world-data group](https://scicloj.github.io/docs/community/groups/real-world-data/)  

The real-world-data group is a space for Clojure data and science practitioners to bring their data projects, share experiences, and evolve common practices.  

#### April summary  
- had a few one-on-one meetings with group members, discussing their goals, and helping out with the technical path
- had the second and third group meetings, which included new presentations, follow-ups on personal projects, hands-on parts, and discussions
- kept working on introductory materials to support the group  

#### May goals  
- have more one-on-one meetings, three more group meetings, and ad-hoc small topical meetings
- help the participants take on active paths that connect their interests with community goals  


### [Noj](https://scicloj.github.io/noj/)  
The Noj project bundles a few recommended libraries for data and science and adds convenience layers and documentation for using them together.  

#### April summary  
- collaborated with Kira McLean on a draft for a new data-visualization API, combining Tablecloth, Hanami, and statistical functions
- updated documentation: added a tutorial for visualizing correlation matrices (WIP); started working on an additional machine-learning tutorial
- updated the implementation to reuse existing functions of other libraries  

#### May goals  
- implement the new data-visualization API (still in experimental stage)
- improve documentation  


### [translating books](https://clojureverse.org/t/learn-data-and-science-in-clojure-by-translating-a-book-together/)  
In this project, we are renewing previous efforts to systematically review data science books in other programming languages and convert them to Clojure.  
The goal is twofold: figuring out what common data science needs are still missing in the Clojure stack and creating well-polished documentation of this stack. It is also an opportunity for Clojurians to get involved in the data science community and learn from books they are curious about.  

#### April summary  
- created a list of books (to be announced soon in a tidy repo) in a discussion with community members, exploring content and licenses
- explored a Clay+Quarto workflow for a couple of the books, and created draft repos for them
- started exploring certain books with community members who may take them on as their long-term projects  

#### May goals  
- focus on [Modern Statistics with R](https://github.com/mthulin/mswr-book) by Måns Thulin
- keep exploring other books with community members  


### [visual-tools group](https://scicloj.github.io/docs/community/groups/visual-tools/)  

This group's goal is to create collaborations in learning and building Clojure tools for data visualization, literate programming, and UI design.  

#### April summary   
- had the third ggplot study session
- had a meetup about badspreadsheet and HTMX with Adam James
- coordinated collaborations with a few group members who are working on HTMX-based dashboards (TBA)
- kept exploring options for grammar-of-graphics implementations (documented in the Scrapbook)  

#### May goals  
- keep the collaborations around HTMX-based layers
- continue the grammar-of-graphics study sessions
- clarify a proposal and a proof-of-concept for the long-term grammar-of-graphics project  


### [Clojure Data Scrapbook](https://scicloj.github.io/clojure-data-scrapbook/)  
The Clojure Data Scrapbook is intended to be a community-driven collection of tutorials around data and science in Clojure.  

#### April summary  
- continued the "exploring ggplot" tutorials
- started tutorials (WIP): processing JSON files, analyzing transportation networks
- adapted old tutorials to ecosystem updates  
 
#### May goals  
- encourage and help community contributions to the scrapbook
- keep adding content to support other projects  


### [Clay](https://scicloj.github.io/clay/)  
Clay is a minimalistic namespace-as-a-notebook tool for literate programming and data visualization.  

#### April summary  
- added an experimental version of test generation for the purpose of testable docs / literate testing
- minor bugixes and extensions
- 5 minor releases of Clay, 2 minor releases of the clay.el Emacs package  

### May goals  
- start working on additional visualizations, mostly Emmy.viewers integration
- explore the extraction of the HTML and Markdown generation layer as a separate library
- keep evolving by user needs  


### [Kindly](https://scicloj.github.io/kindly-noted/)  
Kindly is a proposed standard for requesting data visualizations in Clojure.  

#### April summary  
- added the meta-kind `kind/fn` for user-defined display
- added the meta-kind `kind/test-last` (with `kindly/check` syntactic sugar) for test generation
- updated documentation (the Kindly-noted project)
- updated kind-clerk (Clerk adapter): plotly support  

#### May goals  
- start working on Kindly support with the creators of new HTMX-based visual-tools
- explore the option of a standalone Kindly implementation that is reusable in different tools (an alternative to the current approach of tool-specific implementations)  


### [cmdstan-clj](https://github.com/scicloj/cmdstan-clj)  

Cmdstan-clj is a draft library for interop with [Stan](https://mc-stan.org/) (probabilistic modeling through Bayesian statistics).  

#### April summary  
- gave a presentation of the library and the topic of Bayesian Statistics at the real-world-data group
- maintenance: adapted the library to related ecosystem changes  

#### May goals  
- practice usage with community members and keep developing by need  


### [ClojisR](https://github.com/scicloj/clojisr)  

ClojisR is a bridge between Clojure and the R language for statistical computing.
During this Month, @generateme released the first non-beta version of the library and announced it as stable after 4.5 years of usage.  

#### April summary  
- My role in the release was mostly migrating the old documentation to use our current literate programming workflow with Clay, test-generation, and Quarto.  

#### May goals  
- Migrate the [clojisr-examples](https://github.com/scicloj/clojisr-examples) project to the current workflow.  


### [The Scicloj website](https://scicloj.github.io/)  

#### April summary  
- Maintenance and updates  

#### May goals  
- minor updates reflecting current projects and events  


### Your feedback would help  

Scicloj is in transition. On the one hand, quite a few of the core members have been very active recently, developing the emerging stack of libraries. At the same time, new friends are joining, and soon, more people will enjoy the Clojure for common data and science needs.  

If you have any thoughts about the current directions, or if you wish to discuss how the evolving platform may fit your needs, please [reach out](https://scicloj.github.io/docs/community/contact/).  











