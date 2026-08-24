---
title: "August 2026 Short Term Project Updates"
date: 2026-08-21T14:00:00+12:00
author: Kathy Davis
summary: News from Dragan Djuric, Ingy dot Net and Shantanu Kumar
draft: True


---
Here are August's updates for short term projects funded in Q2 2026. You can find overviews of these projects and the two others which will be reporting on a slightly different schedule in the [original funding announcement](https://www.clojuriststogether.org/news/q2-2026-funding-announcement/). Thanks everyone for the awesome work!  


[Clojure LLM: Dragan Djuric](#clojure-llm-dragan-djuric)   
[Gloat and Glojure: Ingy dot Net](#gloat-and-glojure-ingy-dot-net)  
[PluMCP: Shantanu Kumar](#plumcp-shantanu-kumar)  


## Clojure LLM: Dragan Djuric  
Q2 2026 Final Report 3. Published August 1, 2026  

## The proposal was (in short):  
The goal is to provide a high performance local LLM (large language model) AI solution,
that supports mainstream open models, freely available at Hugging Face and elsewhere.
Something like llama.cpp (https://llama-cpp.com/), but (hopefully!) simpler *and faster*,
with both GPU and CPU support baked-in from the start.  

I even have a catchy name for the library: iLLaManati :)  

iLLaManati should:  
- work :)
- be very fast,
- have a very simple API (possibly even a NO-API if you use the default configuration),
- have a fairly elegant implementation with not many lines of code, which will be a great showcase for Clojure as an enabling technology, and a good learning source for Clojurians.
- integrate into the Clojure ecosystem naturally and seamlessly,
- NOT require Clojurists to know anything about CUDA, ONNX, tensors, or linear algebra, to be able to use it (will require some of that if you want to extend it, though!),
- run on your laptop, server, or cloud; wherever Clojure runs. It's your choice.
- be a great low-effort gateway for Clojurists to peek, as users, into high-performance and GPU computing,
- be a very attractive topic to tell the world about!  

### TLDR results after 2006 Q2 funding  

At the very end, i am quite pleased what I achieved for this first version. The project delivered on all
proposed bullets (with caveats, of course, but still :)  

iLLaManati:  
- does work! I implemented model-agnostic code, and tested it with both official-ish Gemma 3 variants available on Hugging Face (onnx-community and onnxruntime)  
- is very fast. On my 7 year old computer I get 16 tokens/second on the CPU (Intel i7-9900X), and on a very old GPU Nvidia RTX 2080Ti, 80 + tokens/second. I had not had time to do detailed benchmarks and apples to apples comparisons with established frameworks, but this is similar to the speed that llamma.cpp achieves on my machine. So, surprisingly, not bad at all, given that it runs on ONNX Runtime, which is not very well adapted to running LLM models!  
- has a very simple API. Practically, no API. There's just a function for loading the model config, and a couple extra functions that automatically plug the LLM generator and the tokenizer into the core.async or core.async Flow!  
- has a fairly elegant implementation. If you look at https://github.com/uncomplicate/iLLaManati, you'll see it's 100% Clojure (yes!). The whole LLM implementation for several different model architectures, and both CPU and GPU engines is less than 1000 lines of Clojure, and 3 supported tokenizers take up 800. And since there's lots of nitty-gritty low-level code, I can even have ideas how to further squeeze some LOC-s, I just didn't have time to do it during this funding round.  
- integrates into Clojure ecosystem naturally and seamlessly. What can I tell you, other than the only functions in the public API are the a couple functions that talk through core.async channels. Everything is automatized under the hood!  
- NOT require Clojurists to know anything about CUDA, ONNX, tensors, or linear algebra, to be able to use it. Please see the examples here: https://github.com/uncomplicate/iLLaManati/blob/main/illamanati-onnxrt/test/uncomplicate/illamanati/internal/onnxrt/generator_test.clj That's haiku-level code!  
- runs on your laptop, server, or cloud; wherever JVM Clojure runs. It's your choice.  
- be a great low-effort gateway for Clojurists to peek, as users, into high-performance and GPU computing. Although looking at the final result it may seem to you it was walk in the park, I had to conquer many subtle issues with diverse complex technologies. There's lot of real-world tensor/GPU/CUDA/native interop primers here. Of course, the only reason it's so elegant is because it leverages the power of Clojure, and many Uncomplicate libraries. Compare that to any other equivalent on the internet.  
- will be a very attractive topic to tell the world about. Unfortunately, it is not yet production ready since ONNX Runtime does not support many of the real world optimizations needed for server use, but I can solve that in the future by implementing backends in whatever LLM runner that offers C++ API! So, I'm optimistic that we'll have a production-ready systems much earlier than an average Clojurian would be able to afford the proper hardware with enough memory for this kind of software :)  

### Now, some details about the work during these 3 months, especially the obstacles.

### The heart: LLM runner  

In the first and second month, I was focused a lot on the hammock, to learn the details of LLM
implementations, and also several side quests to related topics. I also accomplished plenty of
implementation, especially on the tokenizers and the logits sampler. I also created a LLM runner
that worked, sort of, but needed lots of polishing and bugfixing.  

In the third month, I concentrated on the implementation. In the first two months, I have already
hit a few serious deficiences in ONNX Runtime when it comes to LLM support, so my hopes for a
super-great solution were low. Specifically, ONNX Runtime lags when it comes to supporting
high-performance implementations of more recent operations used in LLMs (such as those that Gemma 3 uses),
and it generally does not expose the internals of such operations even when it supports them,
so creating a server production-worthy general LLM with it is practically impossible now.  

Too bad, you'd think, but hold your breath: even the state of the art runners, such as Nvidia's TensorRT
is like that, and that's why Nvidia has a special runner, optimized just for LLM models: TensorRT-LLM.
So, it's not that ONNX Runtime is a bad model runner, it's that LLM models are so specific that
they require a more specialized runners. Bummer, ha? Not at all, since most of the code that I wrote
during this project (and the lessons learned) fits perfectly into how LLM model works, and provide
the parts of the solution orthogonal to specialized runners such as TensorRT-LLM, or llama.cpp.  

So, in the third month I hoped to assemble what could be assembled and get at least a decent implementation.
There were MANY obstacles. I hoped to leverage ONNX Runtime vendor execution providers, but it turned
out both OpenVINO and TensorRT EPs were riddled with unsupported LLM operations, and heavy builds.
I kept helping with upstream builds and bugfixes, but 5 hour builds are the norm there. At the end,
I managed to make even these work, but their performance was much worse that stock CPU and CUDA EP
so I stayed with stock ONNX Runtime. Also, information was scarce. Sometimes I felt I was the only
one actually trying many of these code paths in practice, and I probably was :) Nevermind, these
were long hours, but I learned lots of things, and, most importantly, REPL enabled me to
debug my way out of most of that technical obstacles, so in the last seconds of the funding
period, I managed to put everything into a very nice package. Maybe not suitable for production,
but definitely something anyone can try on their machine and learn (it works on the CPU, too!).  

### The hammock  

Lots of reading and thinking. And again.  

### Tokenizer  

It turned out that the tokenizer I was using in the first two months (from Hugging Face, wrapped in Java by DJL)
messes up with the CUDA context. It probably took me a week or two of hair-pulling chasing that
weird bug, but REPL helped me again. I literally discovered this by eliminating every other possibility.  

So, in the third month, I had to integrate another tokenizer, just to be able to work with CUDA.
I integrated Sentencepiece, which is Google's tokenizer, which has been created for Gemma.
It's a lot faster than Hugging Face, but it doesn't support nearly as many models. But it works with CUDA...  

So, now, we have 2 tokenizer integrations (HF and Sentencepiece), and we have my own streaming detokenizer implementation i pure Clojure (it's fast while still being elegant).  

### The original superfast token sampler  

I further polished this sampler and integrated it into the LLM loop. Although I hoped
to, I didn't have time to write up a scientific article, so I didn't publish the source
of that part yet. The LLM implementation woes took all of my time, so this source will be
published as soon as I write that damn article. The CPU model doesn't have this sampler
algorithm implemented yet, so I provided a default logmax for now (it's in the public part of the Snapdragan project)
so people can try the code and the examples.  


### Miscellaneous  

During all this time, whenever LLM models required something that's not supported in mainstream
matrix/tensor backends that I use (cuDNN, cuBLAS, BLAS...), such as long integers in tensors,
or float16 in matrices, I created a workaround and implemented that under the hood of Neanderthal
and Deep Diamond. Several Uncopmlicate libraries got new features under the hood along the way!  

I'll spare you the details of the C++ compilations and bug hunts. Too many bad memories :)
I also don't have any more strength (just a joke, I'm fine :) to itemize many detailed paths and activities
that I had to do to make this work.  

But I hope that I mentioned the most important thing: iLLaManati is here, and it's not all that bad! :)  

(Also worth noting: the code is human-written!)  <br>

---


## Gloat and Glojure: Ingy dot Net  
Q2 2026 Report 2. Published July 31, 2026   

Today is the final day of my Q2 2026 [Clojurists Together](https://www.clojuriststogether.org/) funding cycle for [Gloat](https://gloathub.org/) and [Glojure](https://github.com/glojurelang/glojure).  

This was my original commitment for the grant:  

> Make Gloat/Glojure binaries smaller and faster. Pass more of the Clojure Compatibility Test Suite. Create tutorial docs for using Gloat in the real world.
>

The short version is that Glojure now passes every enabled test in the current Clojure Compatibility Test Suite, Gloat's default AOT binaries are leaner and the Glojure compiler has made major performance gains, a new tutorial series takes you from installation through compiling a binary and using Clojure in a Go project.  

**Gloat can now be seriously considered as a full replacement for GraalVM native-image when compiling Clojure programs to native binaries!**  

The second half also took Gloat somewhere I hadn't imagined at the [halfway point](https://gloathub.org/blog/2026/06/16/gloat-q2-grant-halfway-report/): it has become a front end for multiple Clojure compilation engines, including Glojure, let-go, and GraalVM native-image.  

### Since the Halfway Report    
At the halfway point, Gloat was at `v0.1.50` and the first upstream Glojure release of this grant had just landed.  

Since then Gloat has had 16 more releases and is now at [`v0.1.67`](https://github.com/gloathub/gloat/releases/tag/v0.1.67). Glojure has had another 8 releases and is now at [`v0.7.3`](https://github.com/glojurelang/glojure/releases/tag/v0.7.3).  

The most important changes were:   

- A Glojure AOT runtime that Gloat that performs as well as GraalVM  
- Significantly smaller native binaries (as small as 7.5MB so far)  
- A large wave of compiler and runtime performance work  
- Addition of let-go as a second compilation engine  
- Added shared-library support for let-go (which doesn't support it itself)  
- A GraalVM native-image engine for direct comparison and use  
- Better installation, formatting, coloring, paging, classpath, and REPL UX  
- A new Gloat tutorial series  


### The Performance Story   
My long-term goal for Gloat was to make a common YAML framework for all programming languages which I call [YAMLStar](https://yalmstar.org/).  

**TL;DR** YAMLStar now builds its shared library `libyamlstar.so` with Gloat instead of GraalVM, and the resulting binary is about half the size and has similar runtime performance. Being back by Gloat means I can release prebuilt binaries for 15 platforms instead of the 4 platforms it was confined to with GraalVM. YAMLStar now delivers identical YAML capabilities (via a Clojure engine) to 32 (and counting...) programming languages!  


Happy ending, but until this week I was not confident that Gloat would get there by today.  

About 4 or 5 weeks ago Norman Nunley from the let-go project offered to help with YAMLStar by getting let-go's native lowering to match GraalVM's performance. The Glojure and let-go projects have been in friendly competition for a while, challenging each other and contributing to each other's projects. Soon after Norman's work started, I decided that Gloat could and should do all its automations over let-go as well as Glojure.   

Then 2 or 3 weeks ago James Hamlin, Glojure's original author, came out of hiding and decided to do a major optimization push on Glojure to have it reach this goal the go faster than the Speed of GraalVM!   

We all worked together and the result was a major success.  

Soon there will be very few reasons to use GraalVM to compile Clojure programs to binaries instead of Gloat.  


### Smaller Binaries  
A happy result of the performance work is that the AOT Go code (and thus the native binaries) became much smaller. Glojure used to produce a 50MB hello world that was about 40x slower than GraalVM's 28MB hello world.   

Now the Glojure hello world is 19MB and both Glojure and GraalVM binaries take 0.007s to run.   

The existing Gloat `-Xprune` extension was also updated to work with the optimized runtime. The prune extension tree-shakes out the unused parts of `clojure.core` and all the transitive Go dependencies they would normally pull in.   

On hello world, `-Xprune` produces a 7.6MB binary that runs in 0.004s here.  

Pretty nice!  


### Gloat Became a Multi-Engine Tool
Gloat now has an `-E` / `--engine` option and a `gloat --engines` command. The current engine list is:

```
glj       Glojure (default)

lgvm      let-go bytecode VM
lglvm     let-go native lowering with VM fallback
lgl       let-go native lowering (not yet implemented)

graalvm   GraalVM Native Image (binaries only)
```

The goal is to have let-go be a replacement engine for all of the things that Gloat does with Glojure. Compiling to binary was easy, but let-go does not yet support shared libraries and I really needed that to try it out with YAMLStar. So I made Gloat add the things let-go was missing, and now it can produce shared libraries from let-go as well. Hopefully soon we'll get this working in let-go itself.  

Gloat+Glojure can convert Clojure to Go source code, but that isn't yet supported for let-go. It should happen eventually.  

I also added a GraalVM engine to Gloat. I was doing a lot of time comparisons between engines and wanted gloat users to be able to do the same. But most people likely aren't familiar with using GraalVM, and even if they are, they have to install it and set up their environment to use it.  

If you have Gloat installed, GraalVM is ready to go with a single command:  
`gloat -E graalvm hello.clj  # produces ./hello binary`  

Doesn't get easier than that.  


### New Tutorials
The final grant commitment was tutorial documentation.   

I am finishing a new three-part [Gloat tutorial series](https://gloathub.org/tutorial/) today:  
1. **Introduction and Installation**  
2. **Compiling your first Glojure binary**  
3. **Using Clojure in a Go project**    


I really want to create at least a dozen tutorials because there's so much cool stuff you can do with Gloat.   

If you would like to see something covered as a tutorial, please find me on the Clojurians Slack or open an issue on the Gloat repository and let me know what you let me know what you would like to see.   

### Looking Back  
I am very happy with the results of this grant. I have a working YAMLStar for myself, a Gloat that does away with all the shortcomings of GraalVM and a new community of dialect making friend that Gloat can help to do awesome things with Clojure.  

That feels like a very good three months.  

### Thanks  
Thank you to Clojurists Together, and to every person and company that funds it. The grant created the sustained time needed to work through compiler internals, runtime behavior, test suites, release engineering, examples, and documentation as one connected project.  

Thank you to James Hamlin for the enormous Glojure optimization effort and for making upstream Glojure such an exciting place to work.  

Thank you to [Marcin Gasperowicz](https://github.com/nooga), [Norman Nunley](https://github.com/nnunley), and the let-go contributors for the ideas, benchmarks, collaboration, and friendly competition.  

A special shout-out to [Dmitri Sotnikov](https://github.com/yogthos) the author of the Jolt Clojure dialect, for his friendship and daily collaboration. [Jolt](https://github.com/jolt-lang/jolt) is Gloat's next engine target!  

And thank you to everyone who tried Gloat, reported a problem, asked a sharp question, or followed these reports.  

The grant ends today. The work most definitely does not.  

Time to Gloat!  <br>  

---

## PluMCP: Shantanu Kumar  
Q2 2026 Final Report 2. Published Aug. 15, 2026   

I am happy to report my final progress on the scope of work for this
sponsorship:  
- MCP spec 2025-11-25 implementation  
- PluMCP Usage documentation enhancement  

When I shared my first update of this sponsorship cycle, about half of
the work was already complete, although no release had been made yet.
The second half focused on completing the remaining pieces, including
Task orchestration and the OAuth overhaul, and resulted in several
releases covering the overall scope.   


## MCP 2025-11-25 implementation  
The major feature item that received most of its implementation work in
the first part was Task orchestration. It was fully completed and
released in the second part, alongside a substantial expansion and
overhaul of OAuth support. Thanks to these OAuth changes, PluMCP is now
ready to move beyond _Preview_ and is available for beta testing.  


While the entire work has been tracked under a single pull request at
[Pull Request #6](https://github.com/plumce/plumcp/pull/6), with much of
the Task orchestration work already reflected in its WIP changelog, there
have now been several releases:  

- [0.3.0-alpha1](https://github.com/plumce/plumcp/blob/v0.3.0-alpha1/CHANGELOG.md#030-alpha1---2026-jul-07)  
- [0.3.0-alpha2](https://github.com/plumce/plumcp/blob/v0.3.0-alpha2/CHANGELOG.md#030-alpha2---2026-aug-06)  
- [0.3.0-beta1](https://github.com/plumce/plumcp/blob/v0.3.0-beta1/CHANGELOG.md#030-beta1---2026-aug-14)  


### Progress in Second part  
In the second part, I completed the remaining 4 of 9 major feature
changes and 5 of 10 minor feature changes. The completed work includes:  

#### Major Changes completed  
- Add support for OpenID Connect Discovery 1.0 to authorization server
  discovery  
- Enhance authorization flows with incremental scope consent via
  `WWW-Authenticate`  
- Add support for OAuth Client ID Metadata Documents as a recommended
  client registration mechanism  
- Add support for (potentially long running) tasks to enable tracking
  durable requests with polling and deferred result retrieval  


#### Minor Changes completed  
- Review _Security Best Practices Guidance_ - add required utility
  functions  
  - Mostly inapplicable as the majority is meant for MCP proxy  
  - MCP server `Origin` header check already implemented  
- Return Input validation errors as Tool Execution Errors rather than
  Protocol Errors to enable model self-correction  
  - Validating "Required tool params" is implemented  
  - JSON-Schema validation is pending; Malli-based validation is already
    available  
- Support polling SSE streams by allowing servers to disconnect at will  
  - Already supported by PluMCP through session-level detachment of the
    SSE stream and server  
- Support polling in GET streams, resumption always via GET regardless
  of stream origin  
  - Polling streams and resumption via GET is already supported  
- Align OAuth 2.0 Protected Resource Metadata discovery with RFC 9728,
  making `WWW-Authenticate` header optional with fallback to
  `.well-known` endpoint  


#### Current status  
Since all planned feature changes are now complete and included in the
latest `0.3.0-beta1` release, PluMCP is now open for testing by users.
A GA release is planned following beta testing.  

### Remaining work  
Apart from squashing any bugs that arise during the beta testing period,
the subsequent 0.3.x releases would focus on internal refactoring and
code organization. It is also planned to deprecate some API in sync with
changes introduced in the MCP 2026-07-27 spec version.  


### PluMCP Usage Documentation Enhancement  
The [PluMCP documentation website](https://plumce.github.io/plumcp-docs/)
has been updated with the following new entries:  
- MCP Server  
  - Completion  
  - Logging  
- MCP Client  
  - Roots  
  - Sampling  
  - Elicitation  

The existing MCP Server → Tools entry has also been updated with a manual
tool definition mechanism.  

With these changes, the sponsored scope has been completed, and PluMCP
`0.3.0` is now entering beta testing ahead of the planned GA release.  






