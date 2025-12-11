---
title: "December 2025 Short-Term Q3 Project Updates"
date: 2025-12-11T14:00:00+12:00
author: Kathy Davis
summary: "News from Thomas Clark, Dragan Djuric, Jeaye Wilkerson, and Jeremiah Coyle" 
draft: True



---

As 2025 winds down, we have several Q3 project updates. We have a few more reports coming in January 2026 as several are on staggered schedules. A brief summary of each project is included to provide overall context. Thanks to everyone for incredible work on these projects!   


[Thomas Clark: Fastmath]()  
Inasmuch as Lewis Carrol may have creatively objected, complex numbers are now an essential part of modern life: from quantum computing upwards and whether we are aware of it or not. Clojure's support for these numbers however, remains sporadic while it's biggest competitor, the well-known comedy snake - and scripting language https://www.geeksforgeeks.org/python/history-of-python/ treats complex numbers as first-class citizens.   

With this funding, I would like to address the issue somewhat, particularly with regard to the implementation of complex matrices, but concerning a consistent complex API more generally.  


[Dragan Djuric: Uncomplicate Clojure ML]()
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

[Jeaye Wilkerson: Jank]()  
This quarter, I'll be building packages for Ubuntu, Arch, Homebrew, and Nix. I'll be minimizing jank's dependencies, automating builds, filling in test suites for module loading, AOT building, and the Clojure runtime. I'll be working to get the final Clang and LLVM changes I have upstreamed into LLVM 22, adding a health check to jank to diagnose installation issues, and filling in some C++ interop functionality I couldn't get to last quarter.  Altogether, this quarter is going to be a hodgepodge of all of the various tasks needed to get jank shipped.   

[Jeremiah Coyle: Fireworks]()  
- Publish Fireworks editor plugins/extensions/integrations for Emacs, VS Code, and IntelliJ. These are fairly simple extensions that involve some basic form rewriting for wrapping/unwrapping forms.  
- Add support for automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to [Chalk](https://github.com/chalk/supports-color). [#42](https://github.com/paintparty/fireworks/issues/42)  
- Documentation of interactive workflow. 
- Enhanced documentation for theme creation.
- Call-site options for quick formatting changes. For hifi printing, support call-site option to disable all truncation and ellipsis [#14](https://github.com/paintparty/fireworks/issues/14)   

**AND NOW FOR THE REPORTS!**

## Thomas Clark: Fastmath  
Q3 2025 $2K, Report No. 1, Published December 9, 2025   



<br>

---

## Dragan Djuric: Uncomplicate Clojure ML  
Q3 2025 $9K, Report No. 3, Published November 30, 2025   



<br>

--

## Jeaye Wilkerson: Jank
Q3 2025 $9K, Report No. 3, Published November 30, 2025   




<br>

---


## Jeremiah Coyle
Q3 2025 $2K, Report No. 2, Published November 30, 2025





