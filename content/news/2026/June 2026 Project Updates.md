---
title: "June 2026 Short Term Project Updates"
date: 2026-06-13T14:00:00+12:00
author: Kathy Davis
summary: News from Ambrose Bonnaire-Sergeant, Dragan Djuric, and Ingy döt Net
draft: True



---
Here are the first updates for short term projects funded in Q2 2026. You can find overviews of these projects and two others which will be reporting on a slightly different schedule in the [original funding announcement](https://www.clojuriststogether.org/news/q2-2026-funding-announcement/). Thanks everyone!  

 
[Clojure LLM: Dragan Djuric](#clojure-llm-dragan-djuric)   
[Gloat and Glojure: Ingy döt Net](#gloat-and-glojure-ingy-dot-net)   
[Malli: Ambrose Bonnaire-Sergeant](#malli-ambrose-bonnaire-sergeant)   <br>  

<br>  
 


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
with cryptic C++ compiler shenanigans, that I am constantly reminded why Clojure is so great to work with :)  <br>

---

### Gloat and Glojure: Ingy döt Net  
Q2 2026 Report 1. Published June 16, 2026  


We are halfway through the Q2 2026 [Clojurists Together](
https://www.clojuriststogether.org) funding cycle, so this is a good time to
report what has been done for [Gloat](https://gloathub.org) and
[Glojure](https://github.com/glojurelang/glojure).  

The grant officially started on May 1, but since I had learned that it was
accepted a couple weeks earlier, I got too excited and decided to start working
on it immediately.  
I was able to quickly get [Glojure to pass](https://gloathub.org/test-report/)
the entire [Clojure Compatibility Test Suite](
https://github.com/jank-lang/clojure-test-suite);
except for 9 tests that are skipped as being not feasible.
That gave Gloat a much stronger base to build on.  

Since the start of the grant period, Gloat and Glojure have had over 20
releases, with Gloat moving from `v0.1.26` to
[`v0.1.50`](https://github.com/gloathub/gloat/releases/tag/v0.1.50).
The Glojure work was all being done on the long running fork
[`gloathub/glojure`](https://github.com/gloathub/glojure), but I'm thrilled to
announce that as of today, the work has been fully moved back to the upstream
[`glojurelang/glojure`](https://github.com/glojurelang/glojure) and will
continue to be maintained and released from there.  

My overall ambition for Gloat is to have Clojure be as full featured and
prominent to Go programming as it is to Java.
The industry is crazy about Go.
Let's get it crazy about Clojure.  

Furthermore, given that Go cross-compiles to almost everywhere, I am bullish on
Gloat being a serious alternative to GraalVM's native-image compiler.
An alternative with fast compilation, expansive compilation targets (including
WebAssembly) and completely open source licensing.  


<!-- more -->  

#### The Goals

In the [funding announcement post](2026-05-04.md) I listed three main goals:

- **Smaller, faster binaries.**
- **Pass the Clojure Compatibility Test Suite (CCTS).**
- **Tutorial docs.**

The CCTS goal landed early, just before the official grant start.
That did not mean compatibility was finished, but it meant the core language
was good enough to shift attention toward real-world use: interop, REPLs,
dependencies, docs, examples, and the missing pieces that appear when you stop
testing toy programs and start trying to use the system.

The short version: the first half of the grant has been mostly about making
Gloat usable and explainable.


#### Glojure Passed the Compatibility Test Suite  

The most important foundation work happened in April: the Gloat/Glojure stack
got to the point where Glojure passed Jank's Clojure compatibility test suite.  

For Gloat, that matters because Gloat is not trying to invent a new language.
The point is to take normal Clojure source, route it through Glojure and Go,
and end up with Go source, native binaries, Wasm modules, or shared libraries.
Every compatibility test that passes is one less place where user code can
surprise us later.  

After that milestone, I added a [report page](https://gloathub.org/test-report/)
to the Gloat site so the compatibility state is easier to see for yourself.  


#### The REPL Became a Real Tool  

The most visible work this period was the REPL.  

At the beginning of the cycle, Gloat was mostly an ahead-of-time compiler.
You pointed it at source code and got output.
That is still the center of the project, but a serious language tool needs an
interactive story too.  

The Glojure `glj` binary already had a working CLI REPL, but I really wanted
to push Gloat's CLI REPL beyond anything I'd seen to date (in any language).  


[`gloat --repl`](../../doc/gloat-repl.md) now starts a REPL CLI with:

- Syntax highlighting w/ rainbow parens
- Multiline editing and history
- Auto-indent
- Namespace-aware tab completion
- Toggle vi/emacs readline behavior
- Persistent history with multiline selection
- Inline help
- Better startup information
- nREPL support
- Socket REPL support
- Editor integration

I also added client modes so Gloat can connect to other Clojure-family REPLs.
For example, `gloat --repl=+bb`, `gloat --repl=+clj`, and
`gloat --repl=+let-go` all use the same general idea: install the requested
tool if needed, start it in repl server mode and connect with Gloat's fancy UX.  

You can also connect to any existing Clojure REPL with `gloat --repl=12345` or
`gloat --repl=.port-file`.  

This work was partly for Gloat itself and partly for comparison.
When you are building a Clojure implementation, it is extremely useful to be
able to jump between [Clojure](https://clojure.org),
[Babashka](https://babashka.org), Glojure,
[let-go](https://github.com/nooga/let-go), and other dialects quickly.  

I'm super proud of how far this CLI REPL has come so far.
I hope you take some time to kick the tires and take it for a test drive.
Let me know what you think!  


#### Browser REPL

The REPL work also produced a browser REPL.  

The Gloat website now has a [Glojure REPL page](https://gloathub.org/repl/)
backed by a WebAssembly build of Glojure.
It has syntax highlighting, multiline input handling, toolbar controls, rainbow
brackets, and shareable sessions.  

That matters for two reasons.  

First, it makes the project easier to try.
People can evaluate Glojure in the browser without installing Go, Glojure,
Gloat, Java, or anything else.  

Second, it proves out one of the promises of the stack: Clojure code can travel
through Glojure and Go into Wasm.
The demo is not just a toy on the side; it exercises the same direction the
compiler is trying to make practical.  

My favorite part is sharable stateful URLs.
[Try this one](https://gloathub.org/repl/#s:WyIoZGVmbiBmaXp6YnV6eiBbc3RhcnQgZmluaXNoXSBcbiAgKG1hcCAoZm4gW25dXG5cdChjb25kXG5cdFx0KHplcm8/IChtb2QgbiAxNSkpIFwiRml6ekJ1enpcIlxuXHRcdCh6ZXJvPyAobW9kIG4gMykpIFwiRml6elwiXG5cdFx0KHplcm8/IChtb2QgbiA1KSkgXCJCdXp6XCJcblx0XHQ6ZWxzZSBuKSlcblx0KHJhbmdlIHN0YXJ0IGZpbmlzaCkpKVxuIiwiKGZpenpidXp6IDEgMTAwKSJd)!


#### JVM Interop Compatibility  

The biggest compatibility expansion was JVM-style interop.  

Glojure is hosted on Go, not Java, but most real Clojure code assumes at least
some `java.lang` behavior exists.  
Even simple programs use things like `Math/sqrt`, `System/getenv`, strings,
regexes, UUIDs, numeric wrappers, and time classes.  

During this period I added docs, demos, and tests around a growing Go-native
Java compatibility layer, [gojava](https://github.com/gloathub/gojava).
The current Java class set is:  

- `java.lang.Math`
- `java.lang.System`
- `java.lang.Integer`
- `java.lang.Long`
- `java.lang.String`
- `java.lang.Double`
- `java.lang.Boolean`
- `java.lang.Character`
- `java.util.regex.Pattern`
- `java.util.UUID`
- `java.lang.Thread/sleep`
- `java.time.Instant`

The goal is not to make Glojure secretly become a JVM.
The goal is to make normal Clojure source behave correctly when it uses common
JVM interop forms.  

For example, `(Math/floorDiv -7 2)` should return the JVM result, not the Go
integer-division result.
`(System/getenv "PATH")` should feel like Clojure.
Fully-qualified `java.lang.Math/abs` and bare `Math/abs` should both resolve in
the expected way.
Fresh namespaces should have host-class imports populated in a way that matches
what Clojure programmers expect.  

This is the kind of work that makes unmodified Clojure code more likely to run.  


#### Go Interop and `gljdeps.edn`  

The other side of the interop story is Go.  

Glojure already has Go interop, but Gloat needed clearer
[Go interop docs](../../doc/gloat-go-interop.md), runnable demos, and an
explicit way for Gloat programs to declare third-party Go packages.  

I added a full Go interop guide covering:  

- Calling Go package functions
- Calling methods
- Reading and setting struct fields
- Constructors
- Passing Glojure functions into Go higher-order functions
- Wrapping handlers
- Handling multiple return values
- Byte slices
- Reflection escape hatches

I also added runnable demo files for each of those cases.  

The more important practical change is [`gljdeps.edn`](
../../doc/gloat-go-interop.md#packages-are-linked-not-imported)
support in AOT compilation.
Gloat now resolves deps from `--deps=`, `GLOAT_GLJDEPS`, or a local
`./gljdeps.edn`, passes that file through to the Glojure compile workspace, and
injects the declared modules into the generated `go.mod`.  

For example, a Glojure program can declare a dependency on a Go module, compile
through Gloat, and end up with a generated Go module or native binary linked
against that Go package.
That moves Gloat closer to the original promise: Clojure syntax and semantics,
with access to the Go ecosystem and Go's build targets.  

There is still more work needed here.  
For public modules, AOT builds can sometimes succeed without `gljdeps.edn`
because `go mod tidy` can discover missing imports through the Go module proxy.
The deps file is still the reproducible path: it pins versions, supports
private/offline builds, and is required for REPL use where there is no final
`go build` discovery step.  
Some dependency declaration and interop edges are still rough.
But the basic path exists now, and it has docs and demos behind it.  
 

#### Upstream Glojure  

Another important milestone: Gloat has moved back to upstream Glojure.  

At the end of May I met with [James Hamlin](https://github.com/jfhamlin), the
original author and owner of Glojure.
He gave me full permission to maintain the project.  

That is a big deal for Gloat.
Since the beginning of the Gloat project I had been working on a fork of
Glojure, because I needed to make a lot of changes quickly: compiler changes,
runtime changes, REPL work, Java compatibility, AOT support, packaging, and
release mechanics.
A fork was the practical way to move fast, but it was never the ideal long-term
shape.  

As of today, Glojure is unforked.
Gloat is using and releasing from the original
[upstream Glojure repository](https://github.com/glojurelang/glojure) again.  

One of the important things I had added on the fork was prebuilt binaries for
Linux and macOS.
That made Gloat and Glojure (`glj`) installs much faster, because users did not
need to build the Glojure toolchain from source on first run.
That prebuilt-binary release flow now works upstream too with the just released
Glojure [`v0.6.5`](https://github.com/glojurelang/glojure/releases/tag/v0.6.5)
version.   

That is the first upstream Glojure release since
[`v0.6.4`](https://github.com/glojurelang/glojure/releases/tag/v0.6.4), which
came out last October.
It means the work is no longer living off to the side in a Gloat-specific fork.
It is going back into the main Glojure project where it belongs.  

The surrounding Gloat work also tightened release/development workflow around
Glojure versions, local overrides, Go module replacement behavior, and local
worktrees.
Those details matter because Gloat, Glojure, gojava,
[go-readline](https://github.com/gloathub/go-readline), and the
[YAMLScript](https://yamlscript.org) stdlib package all move together during
development.  


#### Documentation and Examples  

I added a lot of documentation this period.  

New or heavily expanded docs include:

- [`gloat-repl`](../../doc/gloat-repl.md)
- [`gloat-go-interop`](../../doc/gloat-go-interop.md)
- [`gloat-java-interop`](../../doc/gloat-java-interop.md)
- [`gloat-install`](../../doc/gloat-install.md)
- [`gloat-tutorial`](../../doc/gloat-tutorial.md)

The generated man pages and website docs were refreshed too.  

The demos matter as much as the prose.
There are now runnable examples for Go interop, Java interop, and third-party
Go dependencies.
Docs that only explain an idea are easy to get wrong.
Docs that point to runnable files are much harder to fake.  


#### Clojure.cc and the Wider Dialect Story  

One unexpected side project was [Clojure.cc](https://clojure.cc).  

I built it because Gloat and Glojure make more sense when people can see the
larger Clojure dialect landscape.
The site catalogs Clojure-family languages and lets people compare hosts,
status, release activity, tags, and project links.  

It includes Gloat, Glojure, let-go, Babashka,
[ClojureScript](https://clojurescript.org), [Jank](https://jank-lang.org),
[Basilisp](https://github.com/basilisp-lang/basilisp),
[Phel](https://phel-lang.org), [Fennel](https://fennel-lang.org),
[Janet](https://janet-lang.org), and many others.
It also has instant command launchers:  

```bash
$(make -f <(curl -sL clojure.cc/cmd.mk) glj)
$(make -f <(curl -sL clojure.cc/cmd.mk) gloat) --repl
$(make -f <(curl -sL clojure.cc/cmd.mk) lg) -e '(apply + (range 3 10))'
```

This is not the core Gloat compiler work, but it supports the same goal:
make these dialects easier to discover, try, and compare.
It also gave me another place to exercise the Glojure browser REPL and the
multi-dialect REPL launcher work.  


#### let-go Collaboration  

The REPL comparison work also pulled in [let-go](https://github.com/nooga/let-go),
another Go-hosted Clojure-like language.  

Gloat can now launch/connect to let-go as one of the supported external REPL
targets, and Clojure.cc includes it in both the dialect catalog and the instant
launcher.  

The more interesting part is that the two projects are starting to challenge
and inspire each other.
Gloat/Glojure has been pushing hard on AOT compilation, Go interop, and
Clojure compatibility.
let-go, meanwhile, has a very promising performance story.
It compiles to bytecode today, and the let-go team is now exploring AOT
compilation by turning that bytecode into Go code.  

I have been talking with let-go's author, [Marcin Gasperowicz (@nooga)](
https://github.com/nooga),
and with [Norman Nunley](https://github.com/nnunley), one of the main
contributors working on performance.
Norman and I knew each other about twenty years ago in the Perl world, so this
is also a fun reconnection across language communities.  

There is a real chance for collaboration here.
One direction I have been thinking about is making Gloat an abstraction over
both Glojure and let-go: same user-facing tool, potentially different Clojure-
on-Go engines underneath.
It is too early to promise that shape, but the overlap is obvious enough that
it is worth exploring seriously.  

#### What Still Needs Work  

The original grant goal of smaller and faster binaries still needs more direct
attention over the final 6 weeks of this grant.  

Some groundwork is already there, especially the earlier `-Xprune` work for
dropping unused `clojure.core` code, but I have not spent enough of this cycle
yet on output size and runtime speed.
The first half pulled me toward compatibility, REPLs, dependencies, and docs,
because those were the things blocking real use and feedback.

For the second half, the main areas are:  

- Continue reducing binary size
- Improve runtime performance
- Run more real Clojure programs through Gloat
- Keep expanding JVM compatibility where real code needs it
- Smooth out `gljdeps.edn` and Go interop edges
- Keep the docs tied to runnable examples


#### Thanks  

Thanks to Clojurists Together and everyone who funds it.
This kind of work needs long stretches of focused time, and the funding makes
that possible. 


Thanks also to the people building and maintaining the surrounding Clojure
dialect ecosystem.
Gloat sits in that larger context, and the project is better when it can learn
from Clojure, ClojureScript, Babashka, Glojure, let-go, Jank, Basilisp, and the
rest of the family.

Halfway through the grant, Gloat is much easier to try, much better documented,
more compatible with ordinary Clojure code, and more connected to the Go
ecosystem than it was on May 1.

Now back to making the binaries smaller and faster. <br>   

---

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






