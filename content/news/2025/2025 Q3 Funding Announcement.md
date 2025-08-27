---
title: "Q3 2025 Funding Announcement"
date: 2025-08-23T08:30:00+08:00
summary: "We are funding 6 projects for a total of $33K USD in Q3 2025."
author: Kathy Davis

---

Clojurists Together is excited to announce that we will be funding 6 projects in Q3 2025 for a total of $33K USD (3 for $9K and 3 shorter or more experimental projects for $2K).  Thanks to all our members for making this happen! Congratulations to the 6 developers below - we're looking forward to your project updates.  

**$9K USD Projects**  
[Ambrose Bonnaire-Seargent: Malli](#ambrose-bonnaire-sergeant-malli)  
[Dragan Duric: Uncomplicate Clojure ML](#dragan-duric-uncomplicate-clojure-ml)   
[Jeaye Wilkerson: Jank](#jeaye-wilkerson-jank)  

**$2K USD Projects**  
[Jeremiah Coyle: Fireworks](#jeremiah-coyle-fireworks)  
[Roman Liutikov: Pitch Uix](#roman-liutikov-uix)  
[Thomas Clark: FastMath](#thomas-clark-fastmath)  

And here's a project description from each developer:



## Ambrose Bonnaire-Sergeant: Malli  

### Automatic Schema Optimizer and Simplifier    
There are many different ways to organize schemas in malli based on your needs.
You might write them manually, deciding between registries or top-level defs,
and :multi vs :or. You can even generate them automatically from external data sources,
with much less control over the final nested structure of your schemas.  

Unfortunately, malli's core algorithms for transforming schemas into things like validators and generators are quite sensitive to the the input schema. Even seemingly equivalent schemas can have different performance characteristics and different generators.  

I would like to create a schema analyzer that can simplify complex schemas,
such that two different but equivalent schemas could have the same representation. Using this analyzer, we can build more efficient validators, more reliable generators, more helpful error messages, more succinct translations to other specification tools, and beyond.

This work directly follows my previous Clojurists Together project on
map constraints: https://www.clojuriststogether.org/news/nov.-2024-short-term-project-updates/  <br>

---


## Dragan Duric: Uncomplicate Clojure ML  
I propose to implement:
- a Clojure developer-friendly API for AI/DL/ML models (in the first iteration based on ONNX Runtime, but later refined to be even more general).  
- Implement its first backend engine (based on ONNX Runtime).  
- support relevant operations as Clojure functions.  
- an extension infrastructure for various future backend implementations.  
- a clean low-level integration with Uncomplicate, Neanderthal, Deep Diamond and Clojure abstractions.  
- assorted improvements to Uncomplicate, Neanderthal, Deep Diamond, to support these additions.  
- develop examples for helping people getting started.  
- related bugfixes.  
- TESTS (of course!).  <br>  

---


## Jeaye Wilkerson: Jank  
This next quarter of jank development is what ties everything together. We have a Clojure compiler and runtime. We have gorgeous error reporting. We have seamless C++ interop. Now we need jank to be easy to build and install on both macOS and Linux. We need stable and robust tooling, including leiningen and deps.edn support. We need to fill in all of the cracks, squash the bugs, document features, and get jank into a state which our adventurous alpha testers can use.  

This quarter, I'll be building packages for Ubuntu, Arch, Homebrew, and Nix. I'll be minimizing jank's dependencies, automating builds, filling in test suites for module loading, AOT building, and the Clojure runtime. I'll be working to get the final Clang and LLVM changes I have upstreamed into LLVM 22, adding a health check to jank to diagnose installation issues, and filling in some C++ interop functionality I couldn't get to last quarter.  

Altogether, this quarter is going to be a hodgepodge of all of the various tasks needed to get jank shipped.   <br>  

---


## Jeremiah Coyle: Fireworks  
### Primary goals for Fireworks, Q3 2025  
**Editor integration**  
Publish Fireworks editor plugins/extensions/integrations for Emacs, VS Code, and IntelliJ. These are fairly simple extensions that involve some basic form rewriting for wrapping/unwrapping forms.  

**Automatic color support detection**  
Add support for automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to [Chalk](https://github.com/chalk/supports-color). [#42](https://github.com/paintparty/fireworks/issues/42)  

**Documentation of interactive workflow.**  
Produce written and/or video documentation a live hot-reloading dev environment for JVM Clojure, powered by Fireworks and Bling.  

**Enhanced documentation for theme creation.**  
Produce written and/or video documentation that makes it easier for people to create their own themes to suite their tastes.  

**Call-site options for quick formatting changes.**  
For hifi printing, support call-site option to disable all truncation and ellipsis  
[#14](https://github.com/paintparty/fireworks/issues/14)  

### Secondary goals for Fireworks, Q3 2025  
**Pipeline for cljs browser console printing**  
Hifi output with Fireworks works just fine for printing to a browser dev console. Post-processing ansi-tagged output for browser console printing, would, however, be a big improvement vs the current implementation.  

**Option for changing label color at callsite**   
Allow for quick call-site changes to the label color for Fireworks output. [#53](https://github.com/paintparty/fireworks/issues/53)  

**Support bold output**    
Allow for quick call-site changes to the produce bold Fireworks output. [#70](https://github.com/paintparty/fireworks/issues/70)  

**Support for native JS sets and maps in CLJS**    
When hifi printing, properly display contents of JS Sets and Maps, when they are within a native cljs data structure. [#46](https://github.com/paintparty/fireworks/issues/46)  

**Map layout flexibility**  
For hifi printing, support call-site option to force single-column map layout a la carte. This is sometimes preferable when map contains keys or values that are long strings. [#45](https://github.com/paintparty/fireworks/issues/45)   <br>  

 ---


## Roman Liutikov: Uix    
I'm currently experimenting with auto memoizing compiler for UIx, an optimization similar to React's Compiler https://react.dev/learn/react-compiler. This blog post describes the feature in detail https://romanliutikov.com/blog/bringing-granular-updates-to-react-the-clojure-way. As a result of this work UIx apps should get better baseline performance out of the box, with fewer work needed from developers.  <br>

---


## Thomas Clark: FastMath  
Inasmuch as Lewis Carrol may have creatively objected, complex numbers are now an essential part of modern life: from quantum computing upwards and whether we are aware of it or not. Clojure's support for these numbers however, remains sporadic while it's biggest competitor, the well-known comedy snake - and scripting language https://www.geeksforgeeks.org/python/history-of-python/ treats complex numbers as first-class citizens.  

With this funding, I would like to address the issue somewhat, particularly with regard to the implementation of complex matrices, but concerning a consistent complex API more generally.  

Surveying the current field, the 'emmy' library seems to be the most developed option in this area, but it is focussed on symbolic manipulation. When dealing with large memory simulations, it is unlikely to be competitive. 'core.matrix.complex' seems to be the numerical choice, but its last commit was 5 years ago, it is a thin wrapper over apache commons and not all matrix protocols seem to have been extended to complex numbers. More confusingly, when dealing with numerical matrices more generally, the go-to choices are fastmath and neanderthal - neither of which have implemented complex matrices.   

As the ecosystem is clearly fragmented, this project would seek to extend fastmath, which is the current high-level go-to for numerical matrices, to deal with complex numbers in an API-consistent way.  

I would proceed as follows:  
* extended survey of the status and limitations of the present options (including performance tests for the current implementations)  
* decision on the best architecture for a competitive solution  
* Implementation/API creation for:
** complex vectors, arrays and fundamental matrix ops (including scaling/trace determinant etc.)  
** LU decomposition and solver  

* Integration with:  
**existing fastmath functions, e.g. including fourier analysis and polynomials  
** Wolframite  
** Clay / kind 

* Performance comparisons with (at least):  
** Python  
** Emmy  
** Wolframite  

* Publication of a full blog report, outlining an introduction to working with complex numbers in Clojure, complete with the results and analysis of the above.








