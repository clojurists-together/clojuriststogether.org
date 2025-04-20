---
title: "Q2 2025 Funding Announcement"
date: 2025-04-20T08:30:00+08:00
summary: "We are funding 6 projects for a total of $33K USD in Q2 2025."
author: Kathy Davis
draft: True

---

Clojurists Together is excited to announce that  we will be funding 6 projects in Q2 2025 for a total of $33K USD (3 for $9K and 3 shorter or more experimental projects for $2K).  Thanks to all our members for making this happen! Congratulations to the 6 developers below: 

**$9K Projects**  
[Bozhidar Batsov: CIDER](#bozhidar-batsov-cider)  
[Brandon Ringe: CALVA](#brandon-ringe-calva)   
[Jeaye Wilkerson: Jank](#jeaye-wilkerson-jank)   

**$2K Projects**   
[Jeremiah Coyle: Bling](#jeremiah-coyle-bling)  
[Karl Pietrzak: CodeCombat](#karl-pietrzak-code-combat)  
[Siyoung Buyn: Scicloj - Building Bridges to New Clojure Users](#siyoung-buyn-scicloj---building-bridges-to-new-clojure-users)  

## Bozhidar Batsov: CIDER  
Provide continued support for CIDER, nREPL and the related libraries (e.g. Orchard, cidernrepl, etc) and improve them in various ways.  

Some ideas that I have in my mind:   
- Improve support for alternative Clojure runtimes   
- Simplify some of CIDER's internals (e.g. jack-in, session management)   
- Improve CIDER's documentation (potentially record a few up-to-date video tutorials as well)   
- Improve clojure-ts-mode and continue the work towards it replacing clojure-mode   
- Add support for clojure-ts-mode in inf-clojure    
- Continue to move logic outside of cider-nrepl   
- Improvement to the nREPL specification and documentation; potentially built some test suite for nREPL specification compatibility   
- Various improvements to the nREPL protocol   
- Stabilize Orchard and cider-nrepl enough to do a 1.0 release for both projects   
- Build a roadmap for CIDER 2.0   
- Write up an analysis of the State of Clojure 2024 survey results (connected to the roadmap item)   


## Brandon Ringe: CALVA  
I'll be working on a new REPL output view for Calva, which is a webview in VS Code. The current default REPL output view utilizes an editor and somewhat emulates a terminal prompt. The performance of the editor view degrades when there's a high volume of output and/or when there are large data structures printed in it. The webview will allow us to add more rich features to the output webview, while also providing better performance.  

I've started this work, the and I'll use the funding of Clojurists Together to get the work over the finish line and release an initial, opt-in version of the REPL output webview. I'll also be adding tests, responding to user feedback about the feature, fixing bugs, and adding features to it.  

This is the first feature of Calva that integrates with VS Code's API directly from ClojureScript. This is partly an experiment to see if writing more of Calva in ClojureScript is a good idea; I suspect that it is.  

## Jeaye Wilkerson: Jank  
In Q1 2025, I built out jank's error reporting to stand completely in a category of its own, within the lisp world. We have macro expansion stack tracing, source info preserved across expansions so we can point at specific forms in a syntax quote, and even clever solutions for deducing source info for non-meta objects like numbers and keywords. All of this is coupled with gorgeous terminal reporting with syntax highlighting, underlining, and box formatting.  

In Q2, I plan to aim even higher. I'm going to build jank's seamless C++ interop system. We had native/raw, previously, for embedding C++ strings right inside of jank code. This worked alright, but it was tied to jank having C++ codegen. Now that we have LLVM IR codegen, embedding C++ is less practical. Beyond that, though, we want to do better. Here's a snippet of what I have designed for jank this quarter.  
; Feed some C++ into Clang so we can start working on it.  
; Including files can also be done in a similar way.  
; This is very similar to native/raw, but is only used for declarations.  
; It cannot *run* code.  
(c++/declare "struct person{ std::string name; };")  
; `let` is a Clojure construct, but `c++/person.` creates a value  
; of the `person` struct we just defined above, in automatic memory (i.e. no heap allocation).
(let [s (c++/person. "sally siu")  
    ; We can then access structs using Clojure's normal interop syntax. n (.-name s)  
    ; We can call member functions on native values, too.  
    ; Here we call std::string::size on the name member.  
    l (.size n)]  
; When we try to gives these native values to `println`, jank will  
; detect that they need boxing and will automatically find a  
; conversion function from their native type to jank's boxed  
; `object_ptr` type. If such a function doesn't exist, the  
; jank compiler fails with a type error.  
(println n l))  

![image](https://github.com/user-attachments/assets/e7b554c8-fb8d-40c8-a75b-af9cf7e60055)


In truth, this is basically the same exact syntax that Clojure has for Java interop, except for the c++ namespace to disambiguate. Since I want jank to work with other langs in the future, I think it makes sense to spell out the lang. Later, we may have a swift or rust namespace which works similarly. But let's talk about this code.   

This interop would be unprecedented. Sure, Clojure JVM does it, but we're talking about the native world. We're talking about C++. Ruby, Python, Lua, etc. can all reach into C. The C ABI is the lingua franca of the native world. But here, we're reaching into C++ from a dynamic lang. We'll call constructors, pull out members, call member functions, and jank will automatically ensure that destructors are called for any locals. Furthermore, jank already has full JIT compilation abilities for C++ code, so that means we can use our seamless interop to instantiate templates, define new structs which never existed before, etc.   

## Jeremiah Coyle: Bling  
Bling is a library for rich text formatting in the console. https://github.com/paintparty/bling Work on Bling in Q2 of 2025 will focus on the following 3 goals:  
- Add support for using hiccup to style and format messages   
- Add support a template string syntax to style and format messages   
- Create 1-3 additional formatting templates for callouts, headers, and points-of-interest.  

The following 4 features are stretch goals for Q2. They will be pursued in the following order when the initial 3 goals are completed.   
- Add support automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to https://github.com/chalk/supports-color   
- Add documentation about how to leverage Bling to create great-looking warnings and errors in your own projects. Example of using bling's templates to create nice warnings can be found {here:](https://github.com/paintparty/fireworks?tab=readme-ov-file#helpful-warnings-forbad-option-values)   
- Add documentation about using Bling in conjunction with existing libraries which format Spec and Malli messages into human readable form.   
- Support arbitrary hex colors, and their conversion, if necessary, to x256   


## Karl Pietrzak: Code Combat    
My project will focus on adding Clojure(Script) to CodeCombat   
See Wiki page at https://github.com/codecombat/codecombat/wiki/Aether  


## Siyoung Buyn: Scicloj - Building Bridges to New Clojure Users  
In 2025, Scicloj aims to improve the accessibility of Clojure for individuals working with data,
regardless of their programming backgrounds. The project will initially focus on reviewing
existing Scicloj libraries, analyzing their codebases, and actively using them to better
understand their documentation structure. Specifically, the initial effort will concentrate on
clearly organizing and distinguishing between tutorials and API documentation. From these
insights, the project aims to develop standardized templates to encourage greater consistency across the documentation of existing Scicloj ecosystem libraries, making those libraries more robust and user-friendly.  


