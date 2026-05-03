---
title: "Q2 2026 Funding Announcement"
date: 2026-05-02T14:00:00+12:00
author: Kathy Davis
summary: "5 projects are awarded a total of $31K"




---

Clojurists Together is excited to announce that we will be funding 5 projects in Q2 2026 for a total of $31K USD (3 for $9K and 2 shorter or more experimental projects for $2K).  

Thanks to all our members for making this happen! We also want to give a special mention to [Metabase](https://www.metabase.com), our newest Transduce member for signing up to support Ambrose Bonnaire-Sergeant's work on [Malli](https://github.com/metosin/malli). Malli is used heavily at Metabase, as well as by many of our other members.

Congratulations to the 5 developers below - we’re looking forward to your project updates.  

### $9K USD Projects   
[Ambrose Bonnaire-Sergeant: Malli](#ambrose-bonnaire-sergeant-malli)    
[Dragan Djuric: Uncomplicate AI: Clojure LLM](#dragan-djuric-uncomplicate-ai-clojure-llm)   
[Cvetomir Dimov: SciCloj Documentation and Plotting Libraries](#cvetomir-dimov-scicloj-documentation-and-plotting-libraries)    

### $2K USD Projects  
[Ingy döt Net: Gloat](#ingy-döt-net-gloat)  
[Shantanu Kumar: PluMCP](#shantanu-kumar-plumcp)    

Here’s what each developer has planned for the next several months:   

### Ambrose Bonnaire-Sergeant: Malli  
In my [previous Clojurists Together work for malli](https://www.clojuriststogether.org/news/december-q3-2025-project-updates/#ambrose-bonnaire-seargent-malli), I improved the performance of validating recursive refs, bounding the amount of memory required for validation regardless of the depth of input values. This is implemented by eagerly expanding recursive schemas until recursive points are discovered, instead of lazily realizing and caching (an unbounded number of) new levels of recursion as inputs require.   

While this increases the reliability of long-running systems by preventing memory leaks caused by validating large inputs, it came with the drawback that more memory was required upfront during validator compilation.This is an obstacle Metabase has been navigating when testing this optimization. While they are excited to see validation of nested structures now uses constant memory, the amount of upfront memory required was uncomfortably high. There are two main ways to tackle this problem:   

**The first is to discover recursion points lazily.** This will reduce the initial memory use of recursive validators, however, while the maximum memory use is bounded, it will still grow over time as large inputs are validated. This makes it difficult to predict how much memory to reserve for the JVM, negatively impacting reliability of long-running systems. 

**The second is to find ways to reduce the maximum memory usage.** In fact, the optimization interfered with a custom optimization Metabase introduced to share schemas across their usages. This points to the problem and solution: the reason for the increased memory was that identical schemas were using distinct validators each time it was used, and the solution is to ensure that references to the same schema point to the same Schema object and validator. This problem can be quite severe in the general case (probably why Metabase needed to patch it). 

In this project, I intend to investigate ways to improve this situation so that systems like Metabase can both take advantage of the constant memory usage of recursive ref validators without incurring prohibitive amounts of upfront memory, hopefully in such a way that custom work-arounds addressing Malli's high memory use can be removed. <br>

---


### Cvetomir Dimov:  SciCloj Documentation and Plotting Libraries  
SciCloj is a Clojure group whose goal is to help Clojure grow into new and uncommon domains, use cases, and professions. On the technical side, SciCloj develops tools and libraries geared towards data analysis, AI, documentation workflows, scientific computing, interop, and beginner-friendly experience. On the community building side, it runs through multiple work groups and weekly meetings (recently mostly around tooling and AI), mentors open-source contributors, and organizes conferences on specialized topics. These steady efforts have culminated in the first online Clojure conference for data analysis stories, [SciNoj](https://scicloj.github.io/docs/community/groups/scinoj-light/). 

SciNoj demonstrated the wide range of applications that Clojure is already suitable for. 
This project aims to extend and improve the libraries in Noj, the entry point for data analysis in Clojure, and their documentation in three ways. First, we aim to extend the plotting capabilities of Noj's plotting library, including expanding the number of plotting backends ittargets and the types of plots it can generate. Second, we aim to create a new library for generating interactive dashboards. Third, we aim to expand the Noj book such that it has a more complete coverage of all libraries in Noj and a consistent structure between chapters.  <br>  

---

### Dragan Djuric: Uncomplicate AI: Clojure LLM   
My goal is to provide a high performance local LLM (large language model) AI solution, that supports mainstream open models, freely available at Hugging Face and elsewhere. Something like [llama.cpp](https://llama-cpp.com/), but (hopefully!) simpler *and faster*,with both GPU and CPU support baked-in from the start. 

Clojure LLM is going to provide a high performance local LLM library with a simple and straightforward high-level API, and the "batteries included" implementation based on [Clojure ONNX Runtime](https://github.com/uncomplicate/diamond-onnxrt).   

The first version, which I hope to develop during this funding cycle, will support Google's [Gemma 3 model family](https://ai.google.dev/gemma/docs/core) and Google's sentencepiece tokenizer, since they are multimodal, offer great overall performance and support 140 languages out of the box. This will tightly integrate with Clojure and its way of doing things, and with ClojureCUDA, Neanderthal, Deep Diamond, etc. Gemma 3 comes in several sizes from 270M (text only), via 1B (text only), 4B, 12B, to 27B parameters, that punch well for their weights. That will provide the turnkey solution for Clojurists to use mainstream open and free LLM models directly in their JVM, with a Clojure-friendly API, that uses Clojure idioms. I even have a catchy name for the library: iLLaManati :)   

Based on my previous experience, I am fairly sure that iLLaManati is going to:
- work :)
- be very fast
- have a very simple API (possibly even a NO-API if you use the default configuration)
- have a fairly elegant implementation with not many lines of code, which will be a great showcase for Clojure as an enabling technology, and a good learning source for Clojurians.
- integrate into the Clojure ecosystem naturally and seamlessly
- NOT require Clojurists to know anything about CUDA, ONNX, tensors, or linear algebra, to be able to use it (will require some of that if you want to extend it, though!)
- run on your laptop, server, or cloud; wherever Clojure runs. It's your choice.
- be a great low-effort gateway for Clojurists to peek, as users, into high-performance and GPU computing
- be a very attractive topic to tell the world about!  <br>  

---  

### Ingy döt Net: Gloat   
Gloat already compiles Clojure to Go code (with related Go build files), native binaries (cross-compiles to ~25 targets), Wasm modules (both browser and server) and shared libraries (with FFI binding examples in 20+ programming languages). Gloat promises to be a complete replacement/alternative to GraalVM's native-image for Clojure, with much faster build times, much farther reach to platforms and a 100% Open Source license. It already works but needs to produce smaller and faster binaries and be tested against more real world Clojure code to be viable.  

Glojure is new and still has rough edges, so the Gloat project is using the gloathub/glojure fork to advance both Gloat and Glojure in parallel, with strong upstream communication and plans get all improvements back upstream.   

This funding will be used to:
- Make Gloat/Glojure binaries smaller and faster. Much progress has already been made including a mode for clojure.core "tree shaking" (not compiling parts of clojure.core that a program doesn't need).
- Pass more of the Clojure Compatibility Test Suite.
- Create tutorial docs on: 
    - How to use Gloat to integrate Clojure into Go projects   
    - How to use Gloat instead of GraalVM to (cross-)compile Clojure programs to native binaries, shared libraries and Wasm modules. <br>  

---

### Shantanu Kumar: PluMCP  
PluMCP Model Context Protocol (MCP) is a pivotal component of the Agentic AI ecosystem. PluMCP is the only Clojure+ClojureScript project that implements both MCP client and server features, supporting STDIO and Streamable HTTP transports with OAuth2.1 integration. Currently, PluMCP is being used by the Open Source https://eca.dev project.  PluMCP currently has client/server features of the MCP spec version 2025-06-18.  

With this funding I want to implement features in the next spec [version 2025-11-25](https://modelcontextprotocol.io/specification/2025-11-25) and work on project documentation. A summary of major changes planned:   
1. Add support for OpenID Connect Discovery 1.0 to authorization server discovery  
2. Allow servers to expose icons as additional metadata for tools, resources, resource templates, and prompts  
3. Enhance authorization flows with incremental scope consent via `WWW-Authenticate`  
4. Validate tool names as per the new spec  
5. Update `ElicitResult` and `EnumSchema` to use a more standardsbased approach and support titled, untitled, single-select, and multiselect enums  
6. Add support for URL mode elicitation  





