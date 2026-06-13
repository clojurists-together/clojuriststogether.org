---
title: "June 2026 Short Term Project Updates"
date: 2026-06-13T14:00:00+12:00
author: Kathy Davis
summary: News from Ambrose Bonnaire-Sergeant and Dragan Djuric
draft: True



---
Here are the first updates for short term projects funded in Q2 2026. Thanks everyone!  

Malli: Ambrose Bonnaire-Sergeant   
Clojure LLM: Dragan Djuric   


### Malli: Ambrose Bonnaire-Sergeant  
Q2 2026 Report 1. Published June 9, 2026  

In this project, I am tackling exponential growth related to Malli refs.

Thanks to Metabase and Clojurists Together for funding this project.

My work in progress so far is in [#1284](https://github.com/metosin/malli/pull/1284/files).
The rest of this report documents some surprising discoveries about Malli's current behavior
that should give some context on the work so far.

As usual for optimizations around refs, there's
surprisingly few lines of code involved and it's quite difficult to explain (like the
[essay in `malli.generator`](https://github.com/metosin/malli/blob/a74e3b45efa30b3bcdb2e997f337c71614eba3c5/src/malli/generator.cljc#L201) explaining about 10 lines of ref optimization code).
I'm reminded of Dan Friedman's C311 course at Indiana University Bloomington, which taught how to switch an interpreter
from lexical to dynamic scoping with similarly few mind-bending changes.  

The first challenge was understanding why Malli schemas themselves exhibit exponential growth in certain cases.  

In my pull request, the `is-counting-times` function checks how many times the `::counting` schema is initialized. This helps validate Malli's current behavior.  

The first interesting discovery is that the simplest "pointer" ref parses the pointed child twice.
For example, `::counting` is created twice here:

```clojure
(is-counting-times [:schema {:registry {::first ::counting}} ::first] 2)
```

Furthermore, adding unreferenced entries to the registry increases the initialization count:

```clojure
(is-counting-times [:schema {:registry {::s0 ::counting}} ::s0] 2)
(is-counting-times [:schema {:registry {::s0 ::counting ::s1 ::s0}} ::s0] 3)
(is-counting-times [:schema {:registry {::s0 ::counting ::s1 ::s0 ::s2 ::s1}} ::s0] 4)
```

However, changing which registry entry we refer to in the schema body in the does not change
the initialization count:

```clojure
(is-counting-times [:schema {:registry {::s0 ::counting ::s1 ::s0 ::s2 ::s1}} ::s0] 4)
(is-counting-times [:schema {:registry {::s0 ::counting ::s1 ::s0 ::s2 ::s1}} ::s1] 4)
(is-counting-times [:schema {:registry {::s0 ::counting ::s1 ::s0 ::s2 ::s1}} ::s2] 4)
```

This led me to look into the initialization of property registries, a very subtle part
of Malli that uses an obscure option called `::allow-invalid-refs`. As best I can tell,
this option is a symptom of this implementation approach causing the unexpected copies.

For example, let's step through why the following schema creates 3 copies of `::counting`:
```clojure
[:schema {:registry {::s0 ::counting ::s1 ::s0}} ::s0]
```
The property registry parses `::s0` and `::s1`
independently, effectively creating two disjoint "pointer" schemas. Then the body
triggers a third copy. Using `def`s this looks like:

```clojure
(def s0 (m/-pointer ::s0 ::counting nil))
(def s1 (m/-pointer ::s1 (m/-pointer ::s0 ::counting nil) nil))
(def body (m/-pointer ::s0 ::counting nil))
```

Ideally, instead they would share an instance of `::counting`:

```clojure
(def s0 (m/-pointer ::s0 ::counting nil))
(def s1 (m/-pointer ::s1 s0 nil))
(def body s0)
```

The subtlety preventing this optimization is Malli's dynamic scoping for refs.
The current implementation intentionally treats each
pointer to a property registry independently because the scope at that point is determined dynamically.
This matters only in very rare, obscure, and (in my experience) often buggy circumstances,
an insight that we can take advantage to optimize most schemas.

Here's the simplest example I'm aware of that requires the most general implementation,
one where `::counting` is never actually used, yet created twice:

```clojure
(is-counting-times [:schema {:registry {::s0 ::counting ::s1 ::s0}}
                    [:schema {:registry {::s0 :int}}
                     ::s1]]
                   2)
```

The inner registry overrides `::s1`'s value for `::s0`.
This is relevant because we have two _different_ schemas called `::s1`,
so naively reusing the same schema for both positions that mention `::s1`
would be incorrect.

Hopefully these observations help understand some of the approaches in the PR.  

In an earlier iteration of the PR, I managed to remove all exponential growth
in schemas with non-overlapping registries, including the massive schema
in the [PR description](https://github.com/metosin/malli/pull/1284) which optimizes the parsed representation from
including millions of copies of a single schema to just one canonical representation.  

Next, I would like to tackle the exponential growth of generating a validator for this
schema. I initially attempted to solve the problems of exponential growth of schemas themselves and their
validators separately, however I suspect there is a unifying solution for both.
That will hopefully be the next milestone.  <br>

---

### Clojure LLM: Dragan Djuric  
Q2 2026 Report 1. Published May 31, 2026  

#### The proposal was (in short):  
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


#### Progress so far:

In the first month, main focus was on the hammock, but I also accomplished plenty of implementation.
I already written some elegant code for some parts and some code with very promising performance
for the others. The main sauce, the LLM prefill/decode loop, and the KV-cache management,
has not yet been written, and I am still running experiments and trying to conquer ONNX Runtime
performance pitfalls.  

#### The hammock  

I have researched how to build LLMs from scratch and identified relative performance-sensitive
areas. This enabled me to better understand the current playing field, and be prepared to deal with various
implementation libraries for running LLMs. It also enables me to understand where ONNX Runtime falls short
when it comes to top of the line LLM performance. Whether it can be wrangled to be neck to neck with
the fastest implementations is unlikely (although I hope it's not impossible, so we'll see), but it's still a good
choice as the first backend since it's relatively approachable, and it runs on diverse hardware
(+ we already have Diamond ONNX Runtime in Clojure). Anyway, I already see how it is going to at
least direct the UX/API/architecture base for a polymorphic backend design of the future.  

#### Tokenizer

I have implemented an universal token encoder by integrating HuggingFace's tokenizer via its Java wrapper.  
It supports all major models (not explicitly tested yet).
For token decoding, I tried using HuggingFace's tokenizer, but it's API and implementation does
not fit with the planned UX, so I implemented my own streaming tokenizer which is  
1) much more elegant (IMHO)  
2) faster (at least for our planned use case, if not universally)  
3) fits perfectly into the planed Clojure async workflow   

The initial version is available here: https://github.com/uncomplicate/iLLaManati/tree/main/illamanati-tokenizer  

Dropped plans for sentencepiece integration, as our tokenizer is compatible with it.  

#### The original superfast token sampler  

This is the current highlight! During the hammock phase I got an idea of a novel
(publication worthy!) fused sampler for the token decoding phase. It's super fast
- that's why it's worthy of a scientific publication - and also elegant!
Since I have to write up this "discovery" for publication, I did not put it on GitHub yet,
as we don't want someone look it up and publish it before I do that, but I plan to write
the article in June (and if I don't manage it in June, definitely by the end of this
funding period), send it to a scientific journal, put the pre-print on arXiv,
and publish the implementation at https://github.com/uncomplicate/iLLaManati/
Yes, it has already been implemented and tested for the GPU in ClojureCUDA,
it's sitting on my machine ready to shine! In the worst case, I'll publish this
source by the end of this funding period, and in the best, ASAP.  

#### The heart: LLM runner  

As I've already written, this part is an (early) WIP. I experimented a lot, wrote some code,
that code gave me some information, and I'm grinding it bit by bit. So, I can be pretty sure
we'll have a pretty good implementation of LLM prefill and decode, and some nice
KV-management for local models, but whether it will be fast enough with its ONNX Runtime engine
to be put against the big boys depends more on the capabilities of ONNX Runtime itself,
than my ability to code around it.  

Even if I don't manage to work around it's deficiencies, we should at leastt get an okay engine during
this funding cycle, but a great general base that could  replace ORT directly with vendor
libraries such as TensorRT and OpenVINO for achieving top speed in the future.If that happens to not
be enough for us (we Clojurists want the best of the best :) I'll try to make this base flexible
enough to be able to integrate with the battleship solutions, such as TensorRT-LLM, directly,
but that's the story for another time :)  

#### Miscellaneous  

To accommodate the requirements of iLLaManati, I worked on assorted improvements and upgrades
in Uncomplicate libraries. I also spent a lot of time compiling upstream C++ code and dealing
with cryptic C++ compiler shenanigans, that I am constantly reminded why Clojure is so great to work with :)  




