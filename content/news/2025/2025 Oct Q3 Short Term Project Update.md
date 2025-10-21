---
title: "Oct 2025 Short-Term Q3 Project Updates"
date: 2025-010-21T14:00:00+12:00
author: Kathy Davis
summary: "News from Ambrose Bonnaire-Sergeant, Dragan Duric, Jeremiah Coyle, Jeaye Wilkerson, Roman Liutikov"
draft: True


---
This is the first project update for fice of our Q3 2025 Funded Projects. (Reports for the other one is on a different schedule). A brief summary of each project is included to provide overall context. Thanks everyone for your awesome work!  


[Fireworks: Jeremiah Coyle](#fireworks-jeremiah-coyle)  
**Editor integration**  
Publish Fireworks editor plugins/extensions/integrations for Emacs, VS Code, and IntelliJ. These are fairly simple extensions that involve some basic form rewriting for wrapping/unwrapping forms.  
**Automatic color support detection**  
Add support for automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to [Chalk](https://github.com/chalk/supports-color). [#42](https://github.com/paintparty/fireworks/issues/42)  
**Documentation of interactive workflow.**  
**Enhanced documentation for theme creation.**  
**Call-site options for quick formatting changes.**  
For hifi printing, support call-site option to disable all truncation and ellipsis  
[#14](https://github.com/paintparty/fireworks/issues/14)   


[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)  
This quarter, I'll be building packages for Ubuntu, Arch, Homebrew, and Nix. I'll be minimizing jank's dependencies, automating builds, filling in test suites for module loading, AOT building, and the Clojure runtime. I'll be working to get the final Clang and LLVM changes I have upstreamed into LLVM 22, adding a health check to jank to diagnose installation issues, and filling in some C++ interop functionality I couldn't get to last quarter.  Altogether, this quarter is going to be a hodgepodge of all of the various tasks needed to get jank shipped.   

[Malli: Ambrose Bonnaire-Sergeant](#malli-ambrose-bonnaire-sergeant)  
Malli's core algorithms for transforming schemas into things like validators and generators are quite sensitive to the the input schema. Even seemingly equivalent schemas can have different performance characteristics and different generators.  

I would like to create a schema analyzer that can simplify complex schemas,
such that two different but equivalent schemas could have the same representation. Using this analyzer, we can build more efficient validators, more reliable generators, more helpful error messages, more succinct translations to other specification tools, and beyond.  

[UIx: Roman Liutikov](#uix-roman-liutikov)  
I'm currently experimenting with auto memoizing compiler for UIx, an optimization similar to React's Compiler https://react.dev/learn/react-compiler. This blog post describes the feature in detail https://romanliutikov.com/blog/bringing-granular-updates-to-react-the-clojure-way. As a result of this work UIx apps should get better baseline performance out of the box, with fewer work needed from developers.  


[Uncomplicate Clojure ML: Dragan Duric](#uncomplicate-clojure-ml-dragan-duric)  
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

## Fireworks: Jeremiah Coyle  
Q3 2025 $2K, Report No. 1, Published October 15, 2025  

I'm happy to report that 2 of the primary goals and 2 of the secondary goals were achieved in the first half of Q3. The remainder of the primary goals will be prioritized for the second half of Q3, and hopefully there will be time to knockout the rest of the secondary goals. Many thanks to Clojurists Together for supporting this work!  

Summary of goals achieved in the first half of Q3:  

<br>

**Primary goals**  

-   Add support for automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to [Chalk](https://github.com/chalk/supports-color).<br>[#42](https://github.com/paintparty/fireworks/issues/42)<br>
[Completed](https://github.com/paintparty/fireworks/pull/76)  
  
  - Support call-site option to disable all truncation and ellipsis<br>
[#14](https://github.com/paintparty/fireworks/issues/14)<br>
[Completed](https://github.com/paintparty/fireworks/commit/d1232b7fe3d522f751009c2cccc8aeca87966d34)  

<br>

**Secondary goals**  
  - Allow for call-site changes to the label color for Fireworks output.<br>[#53](https://github.com/paintparty/fireworks/issues/53)<br>
[Completed](https://github.com/paintparty/fireworks/pull/76)  

  - Add option to the produce bold Fireworks output.<br>[#70](https://github.com/paintparty/fireworks/issues/70)<br>
[Completed](https://github.com/paintparty/fireworks/pull/76)  

<br>

The latest release is [`v0.13.0`](https://clojars.org/io.github.paintparty/fireworks/versions/0.13.0), which features the enhancements listed above.  <br>

---

## Jank: Jeaye Wilkerson  
Q3 2025 $9K,  Report No. 1, Published October 1, 2025  

Hi folks! Thanks so much for the sponsorship this quarter. To kick it off, I have been focusing on stabilizing jank's seamless C++ interop, which includes more robust handling for C++ enums, pointers to arrays, pointers to functions, implicit conversions, non-type template parameters, lambda captures, initial array support via `cpp/aget`, and better syntax error detection in the special `cpp/value` and `cpp/type` forms. Furthermore, jank's interop support is now robust enough to handle all of jank's `clojure.core` functions which need interop. This has allowed us to rip out some of the old code which set up those functions from C++ instead.  

Aside from robust C++ interop, my focus these past several weeks has been on making jank easy to build and install on major Linux distros and macOS. The main announcement here is that jank now has an Ubuntu PPA which is hosting jank binaries compatible with Ubuntu 24.04 and 24.10. These binaries are built continuously. If you'd like to try out the latest version of jank on Ubuntu, you can look at the installation docs [here](https://github.com/jank-lang/jank/blob/main/compiler+runtime/doc/install.md#ubuntu-linux-2404-2410).  

Additionally, we now continuously build jank using macOS, as well as the previous Linux setup. While jank already has a build-from-source Homebrew formula, in the coming weeks, we'll also have a binary Homebrew formula so folks on macOS (aarch64) can easily and quickly try out jank.  

I have much more detail, if you're interested, in my latest blog post [here](https://jank-lang.org/blog/2025-10-03-community).  <br>  

---

## Malli: Ambrose Bonnaire-Sergeant  
Q3 2025 $9K, Report No. 1, Published October 16, 2025  

This month I finished some lingering work from earlier in the year, and it is
now merged into Malli's main branch:  

[Robust :and parser, add :andn](https://github.com/metosin/malli/pull/1182)  

This work solves a tricky problem inherited from spec's original design.
spec2-alpha provided a solution with its "non-flowing and" spec, but
in [prototying a port to Malli](https://github.com/metosin/malli/pull/1167)
we realized a "flowing and" schema was difficult to justify, where the parsing
results are passed to the next conjunct's parser. On the other hand, parsing
`:and` has many use cases.  I proposed instead we:  

1. improve `:and` parsing to be more predictable and  
2. introduce a new schema `:andn` which parses all its branches, but returns them all independently.  

The solution to the first problem relies on a new extension point for schemas `m/-parser-info`
which describes the parsing behavior of the schema. Simple schemas like `:int` and `:string`
have _simple_ or _non-transforming_ parsers, and should return `{:simple-parser true}`. Compound schemas like `:orn`
return tagged values from parsing that are different from their input, which we designate
as _transforming_ parsers (and return `nil` or don't implement `m/-parser-info`).
Other parsers are not as clear cut: `:or` is sometimes one or the other
depending on its children, and most compound schemas inherit parsers from their children.
This is why `m/-parser-info` is a function, not a static configuration.  

We use `m/-parser-info` to improve `:and`'s parsing behavior by identifying up
to one transforming parser from its children, and designating that as the parser for the
entire schema. The other conjuncts are used only to validate the input value. This
works for unparsing too, but in reverse: the transforming schema is used to unparse the
value, and then the other conjuncts validate the unparsed output value.  

An error is thrown if more than one transforming parser is detected when creating
`:and` [un]parsers. There is a property to override which conjunct to use
for transforming, if any.  

Our hope is that the parser analysis is precise enough that it only throws an error
in genuinely problematic cases. So far there have been no reports of real-world problems,
most notably against Metosin's codebase, though it is pre-release at the moment.  

Follow-up work on [improving the parser analysis for regex schemas](https://github.com/metosin/malli/issues/1230)
was identified after a fruitful discussion on the #malli Slack channel.  
I also successfully used the new abstraction to build a [new schema classifier/linter](https://github.com/frenchy64/malli/pull/38)
for identifying schemas that "roundtrip" with respect to `(comp (m/unparser s) (m/parser s))`.  

[Malli's new maintainer Matti](https://clojurians.slack.com/archives/CLDK6MFMK/p1760417952597889)
reached out to me to coordinate my next steps. We hope to merge some of my open PR's
and then I will coordinate with community members on the details of the proposed project:
an optimizer for Malli schemas.  <br>

---


## UIx: Roman Liutikov
Q3 2025 $2K, Report No. 1, Published October 15, 2025  

Last month I focused on benchmarking and testing auto-memoizing compiler to measure potential gains in real world projects and ensure correctness of the output.  

While [synthetic testing](https://romanliutikov.com/blog/bringing-granular-updates-to-react-the-clojure-way) showed ~60% improvement in re-render times, in real projects the gain is more conservative, up to 30% in most cases. The numbers will obviously differ between codebases. As the compiler will be evolving, it will be able to optimize more constructs, as long as your UIx/React code is clean and idiomatic ofcourse :)  

In the meantime, while the compiler is able to optimize only basic constructs, I added a few compiler warnings in UIx to guide users and let them know what code locations should be fixed in their code. A positive side effect of those small refactorings is, well, more idiomatic React code.  

However, all optimizations have their downsides. While your UIx apps will automatically reduce amount of updates during runtime, at startup time (when your app renders for the first time) UIx will need slightly more time than the version of UIx without auto-memoizing compiler. Fear not, this delta is negligible.  

### Next steps  

The plan is to keep working on edge cases and improve how deep the compiler can reach into user's code and optimize it.  <br>

---

## Uncomplicate Clojure ML: Dragan Duric  
Q3 2025 $9K, Report No. 1, Published Sept. 30, 2025  

# Clojure ML report 1

## The proposal was:
My goal with this funding in Q3 2005 is to develop a new Uncomplicate library, ClojureML.  

I propose to implement:  
- a Clojure developer-friendly API for AI/DL/ML models (in the first iteration based on ONNX Runtime, but later refined to be even more general).  
- Implement its first backend engine (based on ONNX Runtime).  
- support relevant operations as Clojure functions.  
- an extension infrastructure for various future backend implementations.  
- a clean low-level integration with Uncomplicate, Neanderthal, Deep Diamond and Clojure abstractions.  
- assorted improvements to Uncomplicate, Neanderthal, Deep Diamond, to support these additions.  
- develop examples for helping people getting started.  
- related bugfixes.  
- TESTS (of course!).  

## Progress so far:  

In the first month, I have already implemented the first version, which I released to Clojars
as `org.uncomplicate/diamond-onnxrt`.  

So, what has been done in this first version?  

I have investigated various available ways to run ONNX runtime (ORT). There's a Java binding that Oracle donated to the (Microsoft-led) ONNX Runtime project. It's decent, but does not support all ORT functionality, and, more importantly, is not the best choice for Clojure, since it comes with their own Java-ness.
Also, the public distribution only comes with vanilla execution engine, and does not include the real stuff, namely CUDA and DNNL engines, which require each user to build their own jars. So, this one was to avoid if possible.  

That left me with the trusty JavaCPP, which was my first choice anyway, since Clojure CPP is based on it,
and I use it for native bindings throughout Uncomplicate libraries. JavaCPP comes with two bindings
for ORT: C++ based, and C-based API. I started with C++ based API, but it turns out the native generator
is at odds with the way ORT does resource cleaning, so the default destructors are irreparably crashing the JVM.  

I then ported whatever I implemented with the C++ API to the C API, and it turned out to be the right match
for Clojure and Uncomplicate way of doing stuff. I then proceeded to discover the ORT API, write Clojure
bindings, and create tests that help me shape things to fit into Clojure ergonomics.  

Along the way, I discovered a few things that need to improved in JavaCPP's ORT bingings themselves,
and helped the upstream developers fixing/improving these.  

I created a few higher-level example tests with common modeling problems and used these
to guide improvements of the internals. The goal is to have an implementation that is,
at the same time fast, easy to use, low on resources (as possible), fits into Clojure
ergonomics, and capable of meeting the real world usage needs. It's not perfect yet,
but it looks to me that I'm getting there even more than I expected.  

The thing that (pleasantly!) surprised me that I didn't even need to introduce new API:
the Deep Diamond API already conceptually supports what we want here! A few small adaptations were needed,
but no breaking changes. I even managed to create example where an ONNX model is a part of Deep Diamond
network! (in the ubiquitous MNIST example).  

That, I think, gave a huge justification for Deep Diamond's Tensor machinery. Normally,
each new framework that we want to integrate comes with its own abstraction for data structures,
needing ways to get data in and out, convert formats and data types, etc. Deep Diamond just
handled these without sweat! It is already so featureful and flexible that I managed to
blend ONNX runtime structures without much hitches.  

So, I guess the remainder of the project will focus on just doing more of the same to
broaden the ONNX feature coverage, making whatever can be configured automatically invisible,
and making whatever must be configured by the user simple and approachable, making more examples,
and generally polishing everything in several cycles of improvements.  

The library is now renamed from Clojure ML to Diamond ML (and umbrella for diamond-onnxrt and a few possible
future libraries such as diamond-tensorrt, diamond-torch, etc.)
and can be found here: https://github.com/uncomplicate/diamond-ml.
The idea now is that we don't actually need a separate library and api for this,
and that these integrations, as Diamond ML, are add-ons that seamlessly blend into Deep Diamond, the core library.  

I'm quite pleased with the results in the first quarter, I hope the users would be, too!  


