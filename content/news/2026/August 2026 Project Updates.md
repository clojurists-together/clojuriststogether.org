---
title: "August 2026 Short Term Project Updates"
date: 2026-08-21T14:00:00+12:00
author: Kathy Davis
summary: News from Ambrose Bonnaire-Sergeant, Dragan Djuric, and Shantanu Kumar
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






## PluMCP: Shantanu Kumar  
Q2 2026 Report 2. Published Aug. 15, 2026   






