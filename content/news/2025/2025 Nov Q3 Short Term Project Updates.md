---
title: "November 2025 Short-Term Q3 Project Updates"
date: 2025-11-14T14:00:00+12:00
author: Kathy Davis
summary: "News from Ambrose Bonnaire-Sergeant, Dragan Duric, Jeaye Wilkerson" 
draft: True  


---
This is the second project update for three of our Q3 2025 Funded Projects. (Reports for the others are on a different schedule). A brief summary of each project is included to provide overall context. Thanks everyone for your awesome work!  

 
[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)  
This quarter, I'll be building packages for Ubuntu, Arch, Homebrew, and Nix. I'll be minimizing jank's dependencies, automating builds, filling in test suites for module loading, AOT building, and the Clojure runtime. I'll be working to get the final Clang and LLVM changes I have upstreamed into LLVM 22, adding a health check to jank to diagnose installation issues, and filling in some C++ interop functionality I couldn't get to last quarter.  Altogether, this quarter is going to be a hodgepodge of all of the various tasks needed to get jank shipped.  


[Malli: Ambrose Bonnaire-Sergeant](#malli-ambrose-bonnaire-sergeant)  
Malli's core algorithms for transforming schemas into things like validators and generators are quite sensitive to the the input schema. Even seemingly equivalent schemas can have different performance characteristics and different generators.  
I would like to create a schema analyzer that can simplify complex schemas,
such that two different but equivalent schemas could have the same representation. Using this analyzer, we can build more efficient validators, more reliable generators, more helpful error messages, more succinct translations to other specification tools, and beyond.  


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


### AND NOW FOR THE REPORTS!

### Jank: Jeaye Wilkerson  
Q3 2025 $9K, Report No. 2, Published October 31, 2025  

Thank you so much for the sponsorship this quarter! This past month of jank development has been focused on stability of all existing features in preparation for the alpha release in December. Of note, I done the following:  
* Stabilized binary builds for macOS, Ubuntu, Arch, and Nix  
* Improved `loop` IR to support native values without boxing  
* Merged all pending LLVM changes upstream, so jank can build with LLVM 22 (releasing in January 2026)  
* The lein-jank plugin and template have been improved so that starting a new jank project is now just a couple of commands  
* libzip has been removed as a dependency, which enables Ubuntu 25.04 support  
* AOT building on all supported platforms has been stabilized  
* Added a daily scheduled CI binary package check for macOS, Ubuntu, and Arch  
* Added runtime type validation for `cpp/unbox` usages  
* Added C preprocessor value support, so `cpp/FOO` can refer to any `#define`  
* Improved IR gen and static type checking for `if` so that both branches must provide the same type  

Normally I have an associated blog post, but as I'll be leaving for Clojure Conj 2025 next week, I'm taking the time to polish my talk.  <br>

---

### Malli: Ambrose Bonnaire-Sergeant  
Q3 2025 $0K, Report No. 2, Published November 11, 2025  

This month I [fixed a bug](https://github.com/metosin/malli/pull/1234) in the work I merged last month: [Robust :and parser, add :andn](https://github.com/metosin/malli/pull/1182)  

The bugfix got me thinking about recursive refs again (following on from my work on recursive generators in malli.generator from a few years ago), which is a major part of the performance work
I proposed for this Clojurists Together project (compiling efficient validators for recursive schemas).  

I realized that we can use the cycle detection logic from malli.generator to detect cycles
in validators! A major performance difference between Plumatic Schema/Spec and Malli is in
recursive validators: Malli never "ties the knot" and will lazily compile and cache
_every_ layer of recursion. This means Malli uses linear space for recursive validators, while
Schema/Spec use constant space, _and_ Malli can never fully compile a recursive validator and
must pay a time cost for compiling schemas at "runtime" (e.g., when your webserver is accepting requests).  

I submitted a Malli "discussion" PR [Sketch: tying the knot for ref validator](https://github.com/metosin/malli/pull/1235) proposing an approach to fixing this performance issue for recursive validators. See the PR if you're interested in the exact details, as it contains a full explanation of the problem and (mostly) the solution, as well as a reference implementation that passes all the tests.  

A neat part of this approach is that it is a completely transparent optimization from a user's perspective, with no extra configuration or changes needed. Similar optimizations may be possible for other operations like `m/explain` and `m/parse`, but that's future work.  

For now, the maintainers showed interest in accepting this optimization and my next step will be to further test and validate the approach, and propose a final PR.  <br>

---

### Uncomplicate Clojure ML: Dragan Duric  
Q3 2025 $9K, Report No. 2, Published October 31, 2025  

My goal with this funding in Q3 2005 is to develop a new Uncomplicate library, ClojureML (see above).  

### Progress during the second month:

In the first month, I have already implemented the first version, which I released to Clojars
as `org.uncomplicate/diamond-onnxrt` version 0.5.0.  

So, what has been done since then, in the second month?  

We are at version 0.17.0 now!  

As I already established solid foundations in the first month, in the second month, I focused
on expanding the coverage of ONNX Runtime's C api in Clojure, writing tests to get a feel how
it works, working on hiding the difficult parts under a nicer Clojure API, fixing bugs.
It wasn't always a smooth sail, but with every storm I got a better understanding
of the ways things work in ONNX Runtime, and I also covered most of the API available
by ONNX Runtime version 1.22.2, at least the relevant part that can be used by Deep Diamond right
now (which excludes sparse tensors and string tensors). The majority is covered and well tested.  

The second area was using the newly added core API in higher-level integration with Deep Diamond.
The result is that the public onnx function can now be integrated with the rest of DD Tensor machinery!
And the public API that the user has to see is only one function, while everything related to
ONNX is automatically wired under the hood! I am quite pleased with what I achieved on that
front. Of course, I also implemented custom configurations that the user can provide as a
Clojure map, and the underlying machinery will call the right onnx runtime function to set everything up.  

The third big area is CUDA support. Since the whole setup up to this point was quite elegant
(I'm sorry for having to praise my own work :), especially having lots of supporting functionality
in Deep Diamond and Neanderthal, the Clojure code for this in diamond-onnx was not too demanding.
However, the upstream library for this, namely ONNX Runtime's backend for CUDA, and the build
provided by javacpp-presets, was quite difficult to tame. I had to run many multi-hour C++ compilations,
thaw through C++ compilation errors, dig to unearth causes, finding ways to fix, helping upstream,
and this took lots and lots of time. But, that's what it had to be done. That's why I'll have to wait
for upgrade to the newest ONNX Runtime 1.23.2, but I managed to tame version 1.22.2.  

During all this, I did numerous refinements to the codebase, so future library improvements
will be easier. This is not a one-shot, I hope that this library will be a staple in the Clojure AI/ML
community for the years to come, and that I'll be able to further expand it and improve it
as the requirements expand.  

I probably forgot to mentioned some stuff that I worked on, too, but I hope I've mentioned the most important things.  



