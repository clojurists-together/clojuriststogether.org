---
title: "July 2026 Short Term Project Updates"
date: 2026-07-15T14:00:00+12:00
author: Kathy Davis
summary: News from Ambrose Bonnaire-Sergeant, Dragan Djuric, and 
draft: True  



---
Here are July's updates for short term projects funded in Q2 2026. You can find overviews of these projects and the two others which will be reporting on a slightly different schedule in the [original funding announcement](https://www.clojuriststogether.org/news/q2-2026-funding-announcement/). Thanks everyone!  

 
[Clojure LLM: Dragan Djuric](#clojure-llm-dragan-djuric)   
[Malli: Ambrose Bonnaire-Sergeant](#malli-ambrose-bonnaire-sergeant)   
[PluMCP: Shantanu Kumar](#plumcp-shantanu-kumar) <br>
<br>  
 


## Clojure LLM: Dragan Djuric  
Q2 2026 Report 2. Published June 30, 2026  

### The proposal was (in short):  
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

### Progress so far:  

In the second month, main focus was on the hammock, but I also accomplished plenty of implementation.
The initial prototype is almost there. When it start working correctly, I will be able to polish
it a bit and try to squeeze as much performance is available in ONNX Runtime (not that much!)
However, these challenges help me forge a better overall framework for more serious engines in the future.

### The hammock   
Lots of reading and thinking. And again.  

### Tokenizer  
I polished the tokenizer a bit and integrated it with LLM. It works, and works well!  

### The original superfast token sampler  
I polished this sampler and integrated it into the LLM loop. I fixed some correctness bugs
and also supported float16 data (without losing perofmance).
I didn't have time to write up a scientific article, so that's pushed into July (I hope!)
so I didn't publish the source yet.  

### The heart: LLM runner  
This is still WIP, but i made lots of progress still. I implemented universal Clojure types
that can cover both CPU and GPU implementations of prefill and decode. I connected that with
the tokenizer and sampler, and got a consistent and meaningful stream of tokens out of it.
So the first milestone for the functional part is there.  

The KV manager also works well, having in mind that it has to cater to the ONNX Runtime constraint.  

However, lots of challenges, especially with the CUDA EP. The ONNX Runtime has its own quirks,
and of course the documentation is scarce and examples non-existing when it comes to
integrating this with other CUDA code. I spent many hours debugging heisenbugs and
trying to make it fits nicely. I made huge progress, but still have some quirks to
solve before I get it to the same level of correctness that the CPU has. ONNX Runtime
seems to have mind of its own with CUDA, cuBLAS, and cuDNN contexts and streams, and, of course it can't
be controlled fully from the outside, and of course it sometimes work this way, and sometimes that way...  

So, the correctness part is not fully there, but I expect to solve it soon.  

I'm less optimistic about the performance part. Of course it's not expected of the default
execution provider, but plenty of powerful providers are there: CUDA, TensorRT, OpenVINO, DNNL.
Alas, none of these providers supports ALL Gemma3 operations, and this seriously sabotages
the performance. Some less advanced models might be better supported (and even Gemma3 is last years news though)
but overall it seems that ONNX Runtime struggles with up-to-date support for diverse LLM model architectures.

But does that make me pessimistic? On the contrary! This struggle gave me great insights into the
challenges of running diverse LLMs (not only for text generation), and I have some concrete ideas
about a solution with many backends, not unlike how Neanderthal and Deep Diamond solved this for
matrices and tensors. The backend based on ONNX Runtime will be a good multiplatform baseline
and the initial prototype, and then I can create backends based on industry heavyweights
such as TensorRT-LLM for Nvidia GPUs, OpenVINO for Intel CPUs, MLX for MacOS, and, why not,
even integrate Llama.cpp as an all-rounder.

### Miscellaneous  
To accommodate the requirements of iLLaManati, I worked on assorted improvements and upgrades
in Uncomplicate libraries. I also spent a lot of time compiling upstream C++ code and dealing
with cryptic C++ compiler shenanigans, that I am constantly reminded why Clojure is so great to work with :)  

Of great importance is that I added support for Float16 to both Neanderthal and Deep Diamond!  

I haven't had time to make official releases, nor I committed all code to GitHub. I'm still in the middle of the battle.  <br>  

---
## Malli: Ambrose Bonnaire-Sergeant  
Q2 2026 Report 2. Published July xx, 2026  





<br>

---



## PluMCP: Shantanu Kumar  
Q2 2026 Report 1. Published July 1, 2026  

I am grateful to Clojurists Together for sponsoring PluMCP during the
2026 Q2 cycle. The planned scope of work for this sponsorship is:  
- MCP spec 2025-11-25 implementation  
- PluMCP Usage documentation enhancement  


Early in the sponsorship period I had to deal with unavoidable
commitments outside the project, which delayed my progress. Kathy kindly
granted me an extension, so this report is being submitted about two
weeks later than originally planned.  


### MCP 2025-11-25 implementation  
At the beginning of the sponsorship cycle, PluMCP v0.2.x supported the
following MCP specification versions:  


| MCP spec version  | PluMCP implementation status                   |
| ----------------- | ---------------------------------------------- |
| 2025-11-25 (TODO) | To be implemented during the sponsorship cycle |
| 2025-06-18 (Done) | Supported as the main spec version             |
| 2025-03-26 (Done) | Supported in compatibility mode                |
| 2024-11-05        | Not supported, no plan to support              |


At the start of the cycle, PluMCP advertised support for the 2025-11-25
specification during the MCP handshake but the implemented feature set
corresponded entirely to the 2025-06-18 specification. This was
functionally correct because the differences between the two
specification versions were limited to optional features.  


This sponsorship cycle closes that gap. PluMCP is implementing features
listed in the MCP 2025-11-25 spec. The changes between 2025-06-18 and
2025-11-25 are
[captured here](https://modelcontextprotocol.io/specification/2025-11-25/changelog).  


### Progress so far  
The larger part of the work is the MCP 2025-11-25 implementation, which
can be tracked in PluMCP
[pull request #6](https://github.com/plumce/plumcp/pull/6).  


This list does not include the schema updates required by the new MCP
specification or the corresponding entity generator functions. Although
implementing these required considerable groundwork, they provide a
reliable foundation for the remaining implementation.  


At this point, 5 of the 9 major feature changes and 5 of the 10 minor
feature changes are complete. The completed work includes:  


### Major Changes completed  
- Allow servers to expose icons as additional metadata for tools,
  resources, resource templates, and prompts
- Validate tool names as per the new spec
- Update `ElicitResult` and `EnumSchema` to use a more standards-based
  approach and support titled, untitled, single-select, and multi-select
  enums
- Add support for URL mode elicitation
- Add tool calling support to sampling via `tools` and `toolChoice`
  parameters  


### Minor changes completed  
- Add utility function(s) to let servers using STDIO transport use
  STDERR for all types of logging, not just error messages
- Add optional description field to `Implementation` (schema) interface
  to align with MCP registry `server.json` format and provide
  human-readable context during initialization
- Have the servers respond with HTTP 403 Forbidden for invalid Origin
  headers in Streamable HTTP transport
- Add support for default values in all primitive types (string, number,
  enum) for elicitation schemas
- Establish JSON Schema 2020-12 as the default dialect for MCP schema
  definitions (2020-12 is the ONLY supported dialect for now)   

### Current status  
10 of the 19 planned specification changes are complete, with
**Task orchestration** currently under active development.  


### Remaining work  


MCP 2025-11-25 introduced an experimental feature for task orchestration,
which is also one of the largest additions in this release. This is the
feature I am currently working on, and I hope to release a 0.3.0 _alpha_
in about a week.  


More than half of the specification's feature set has now been implemented.
What remains is to implement the rest of the specification along with
the planned documentation improvements and example code.  


I look forward to completing the remaining implementation and
documentation work during the second half of the sponsorship period.  
