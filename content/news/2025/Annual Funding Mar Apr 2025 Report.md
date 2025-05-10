---
title: "Annually-Funded Developers' Update: Mar./April 2025"
date: 2025-05-10T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Peter Taoussanis, Oleksandr Yakushev"  
Draft: True

---

Hello Fellow Clojurists!
This is the second report from the 5 developers receiving Annual Funding in 2025. 


[Dragan Duric](#dragan-duric): Apple M Engine Neanderthal  
[Eric Dallo](#eric-dallo): metrepl, lsp-intellij, repl-intellij. lsp, lsp4clj   
[Michiel Borkent](#michiel-borkent): clj-kondo, squint, babashka, fs, SCI, and more...  
[Peter Taoussanis](#peter-taoussanis): Truss v2, Telemere v1 RC3  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER, clj-async-profiler and Flamebin, clj-memory-meter    


## Dragan Duric  
2025 Annual Funding Report 2. Published May 5, 2025.

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

In March and April, I implemented the JNI bindings for 5 Apple Accelerate libraries (blas_new, lapack_new, Sparse, BNNS, BNNS Graph, vDSP, vForce and vImage) and implemented almost all functionality of Neaanderthal's Apple M engine based on Accelerate.  
It will soon be ready for proper release (currently waiting some bugfixes, polishing, and a javacpp-presets release to be available in Maven Central).  

In more detail:  

Here's what I've proposed when applying for the CT grant.  

I proposed to *Implement an Apple M engine for Neanderthal.* This involves:  
- buying an Apple M2/3 Mac (the cheapest M3 in Serbia is almost 3000 USD (with VAT).  
- learning enough macOS tools (Xcode was terrible back in the days) to be able to do anything.  
- exploring JavaCPP support for ARM and macOS.  
- exploring relevant libraries (OpenBLAS may even work through JavaCPP).  
- exploring Apple Accelerate.  
- learning enough JavaCPP tooling to be able to see whether it is realistic that I build Accelerate wrapper (and if I can't, at least to know how much I don't know).  
- I forgot even little C/C++ that I did know back in the day. This may also give me some headaches, as I'll have to quickly pick up whatever is needed.  
- writing articles about relevant topics so Clojurians can pick this functionality as it arrives.  

Projects directly involved:  
https://github.com/uncomplicate/neanderthal  
https://github.com/uncomplicate/deep-diamond  
https://github.com/uncomplicate/clojure-cpp  

As I implemented and OpenBLAS-based engine in January-February, in March-April I tackled the main objective: Apple Accelerate bindings. As Apple's documentation is not stellar, and there are multiple tools and languages involved, it was a slow and tedious work consisting of lots of experimentation and discovery. Even boring, I would say. But, slowly and steadily I discovered how relevant JavaCPP generators work, I unearthed Accelerate C++ intricacies, fought with them one by one, and slowly I managed to create the proper Java bindings! Along the way I even contributed some fixes and updates to JavaCPP itself! YAY! This is available as a new project under Uncomplicate umbrella at https://github.com/uncomplicate/apple-presets  

Next, I returned to the pleasant part of work - programming in Clojure - and almost completed the dedicated Neanderthal engine that utilizes Apple Accelerate for BLAS, LAPACK, as well as Math functions and random generators on M Macs. This covers the core and linalg namespace, that was already supported by the alternative OpenBLAS engine (implemented in Jan-Feb) AND Math and RNG engines. I didn't manage to iron out all the bugs so it could be ready for release, but this will certainly be ready in May-June. I also didn't manage to tackle Sparse matrices, but as I managed to create Accelerate bindings for all types, including Sparse, I expect this to not be a problem and be completed during this funding round.  

Looking at the funding proposal, I can say that I'm very satisfied that all the features that I promised to build are progressing even better than expected, so that will leave some time to try to do some of the features
that I said I hope to be able to support, namely to explore Deep Diamond Tensor support on Apple M (via BNNS and/or BNNS Graph) and GPU support via Metal.  

I even got some ideas for additional projects based on native Apple functionality related to machine learning and audio/video, but lets not getting too much ahead.  

All in all, I feel optimistic about how this project progresses!  <br>  

---


## Eric Dallo  
2025 Annual Funding Report 2. Published April 30, 2025.  

In these 2 last months I could work on multiple projects and even focus on a new exciting project called [metrepl](https://github.com/ericdallo/metrepl), a nREPL middleware to help extract metrics about your REPL, really helpful when you have multiple coworkers working in multiple projects and you want to collect information about performance, time spent in REPL features and others!
Besides that, I worked hard on keep improving the IntelliJ experience using the 2 OSS plugins of LSP + REPL, and of cource improving clojure-lsp the base of all major editors now.

## [metrepl](https://github.com/ericdallo/metrepl)  

## - 0.3.1  

- Improve export exception handler  
- Remove jvm started flaky metric  
- Fix `event/op-completed` metric to measure time correctly  
- Add `event/test-executed` event.  
- Add `event/test-passed`, `event/test-errored` and `event/test-failed` events.  
- Add `session-time-ms` to `close` op.  
- Add `:project-path` to metrics.  
- Add compatibility with older Clojure versions  

## [clojure-lsp](https://clojure-lsp.io/)  

### 2025.03.07-17.42.36 - 2025.04.23-18.16.46  

- General  
  - Bump clj-kondo to `2025.02.20`.  
  - Add support for OpenTelemetry(otlp) log, enabled if configured. #1963  
  - Bump rewrite-clj to `0bb572a03c0025c01f9c36bfca2815254683fbde`. #1984  
  - Bump clj-kondo to `2025.02.21-20250314.135629-7`.  
  - Add support for ignoring tests references for the `clojure-lsp/unused-public-var` linter. #1878  
  - Add `:test-locations-regex` configuration to allow customizing test file detection for the `unused-public-var` linter's `:ignore-test-references?` and test code lenses. #1878  
  - Improve and standardize all logs for better troubleshooting and metrics collection.  
  - Fix `unused-public-var` false positives when `:ignore-test-references? true`.  
  - Bump clj-kondo to `2025.04.07`.  
- Editor  
  - Improve paredit slurp and barf corner cases. #1973 #1976  
  - Add Semantic Tokens support for the Clojure Reader Dispatch macro `#_` (ignore next form). #1965  
  - Fix regression on previous version on snippets completion. #1978  
  - Add `forward`, `forward-select`, `backward` and `backward-select` paredit actions.  
  - Show add require code action for invalid syntax codes like `my-alias/`. #1957  
  - Improve startup performance for huge projects avoiding publish empty diagnostics for every file of the project unnecessarily.  
  - Improve timbre context log.  
  - Fix suggestion for add require code action. #2017  
  - Improve find definition so it works on `declare` forms too. #1986   

## [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

### 3.1.1 - 3.4.0  

- Remove `:` lexer check since this is delegated to clojure-lsp/clj-kondo already.  
- Fix comment form complain about missing paren.  
- Improve server installation fixing concurrency bugs + using lsp4ij install API.  
- Bump clj4intellij to 0.7.1  
- Support Namespaces on search everywhere (Shift + shift). #64  
- Add support for `forward`, `backward`, `forward-select`, `backward-select` paredit actions. #72  
- Fix go to declaration or usages. #70  

## [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)  

Together with the help of [@afucher](https://github.com/afucher), we improved so much the IntelliJ REPL experience, fixing multiple issues and adding multiple features, the experience now is pretty close to other REPL experiences in other editors!  

### 2.3.0 - 2.5.2  

- Update repl window ns after switching ns.  
- Fix exception on settings page.  
- Fix special form evaluations. #135  
- Add support for JVM args on local REPL configuration. #124  
- Send to REPL eval results. #92  
- Fix repl input when evaluated the same input of last eval.  
- Fix history navigation via shortcut not working after 2.0.0.  
- Enhance REPL evaluations. #108  
  - Isolate ns from REPL windows and file editors  
  - Evaluate ns form from file automatically to avoid namespace-not-found errors.   
- Fix REPL window horizontal scrollbar not working.  
- Fix REPL window broken after making any change to its layout. #144  
- Disable "clear REPL" action when REPL is not connected. #126  
- Bump clj4intellij to 0.8.0  
- Configure project with IntelliJ integration tests (headless)  

## [lsp4clj](https://github.com/clojure-lsp/lsp4clj)  

### 1.12.0 - 1.13.0  

- Add `textDocument/selectionRange` LSP feature coercers.  
- Add inlay-hint LSP feature coercers.  

## [clj4intellij](https://github.com/ericdallo/clj4intellij)  

### 0.7.0 - 0.8.0  

- Create `def-extension` to create plugin.xml extension points easily and more idiomatic.  
- Fix clojure-lsp hook  
- Drop support of older IntelliJ versions (2021/2022). Now requires minimum IntelliJ 2023.3 (Build 233)  
- Bump JAVA min version to 17  
- Add support for tests. <br>    

---


## Michiel Borkent  
2025 Annual Funding Report 2. Published May xx, 2025.  

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work possible. Without you, the below projects would not be as mature or wouldn't exist or be maintained at all. Top sponsors:  
* [Clojurists Together](https://www.clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Toyokumo](https://toyokumo.co.jp/)
* [Cognitect](https://www.cognitect.com/)
* [Kepler16](https://kepler16.com/)
* [Pitch](https://github.com/pitch-io)

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways.  Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)  
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo)  OpenCollective  
* [Ko-fi](https://ko-fi.com/borkdude)  
* [Patreon](https://www.patreon.com/borkdude)  
* [Clojurists Together](https://www.clojuriststogether.org/)    

If you're used to sponsoring through some other means which isn't listed above, please get in touch.  Thank you! On to the projects that I've been working on!  

## Peter Taoussanis  
2025 Annual Funding Report 2. Published May 27, 2025.  


## Oleksandr Yakushev  
2025 Annual Funding Report 2. Published May xx, 2025.  