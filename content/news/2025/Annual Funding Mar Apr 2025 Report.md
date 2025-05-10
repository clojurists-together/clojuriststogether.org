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

In March and April, I implemented the JNI bindings for 5 Apple Accelerate libraries (blas_new, lapack_new, Sparse, BNNS, BNNS Graph, vDSP, vForce and vImage) and implemented almost all functionality of Neaanderthal's Apple M engine based on Accelerate. It will soon be ready for proper release (currently waiting some bugfixes, polishing, and a javacpp-presets release to be available in Maven Central).  

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

### [metrepl](https://github.com/ericdallo/metrepl)  

#### - 0.3.1  

- Improve export exception handler  
- Remove jvm started flaky metric  
- Fix `event/op-completed` metric to measure time correctly  
- Add `event/test-executed` event.  
- Add `event/test-passed`, `event/test-errored` and `event/test-failed` events.  
- Add `session-time-ms` to `close` op.  
- Add `:project-path` to metrics.  
- Add compatibility with older Clojure versions  

### [clojure-lsp](https://clojure-lsp.io/)  

#### 2025.03.07-17.42.36 - 2025.04.23-18.16.46  

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

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

#### 3.1.1 - 3.4.0  

- Remove `:` lexer check since this is delegated to clojure-lsp/clj-kondo already.  
- Fix comment form complain about missing paren.  
- Improve server installation fixing concurrency bugs + using lsp4ij install API.  
- Bump clj4intellij to 0.7.1  
- Support Namespaces on search everywhere (Shift + shift). #64  
- Add support for `forward`, `backward`, `forward-select`, `backward-select` paredit actions. #72  
- Fix go to declaration or usages. #70  

### [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)  

Together with the help of [@afucher](https://github.com/afucher), we improved so much the IntelliJ REPL experience, fixing multiple issues and adding multiple features, the experience now is pretty close to other REPL experiences in other editors!  

#### 2.3.0 - 2.5.2  

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

### [lsp4clj](https://github.com/clojure-lsp/lsp4clj)  

#### 1.12.0 - 1.13.0  

- Add `textDocument/selectionRange` LSP feature coercers.  
- Add inlay-hint LSP feature coercers.  

### [clj4intellij](https://github.com/ericdallo/clj4intellij)  

#### 0.7.0 - 0.8.0  

- Create `def-extension` to create plugin.xml extension points easily and more idiomatic.  
- Fix clojure-lsp hook  
- Drop support of older IntelliJ versions (2021/2022). Now requires minimum IntelliJ 2023.3 (Build 233)  
- Bump JAVA min version to 17  
- Add support for tests. <br>    

---


## Michiel Borkent  
2025 Annual Funding Report 2. Published May 2, 2025.  

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
<br>

**Blog posts**  

I blogged about an important improvement in babashka regarding type hints [here](https://blog.michielborkent.nl/babashka-java-reflection-type-hints.html).  

**Interviews**  

Also I did an interview with Jiri from Clojure Corner by Flexiana, viewable [here](https://www.youtube.com/watch?v=H7ZlwEDxzRs).  

**Updates**  

Here are updates about the projects/libraries I've worked on in the last two months.  

* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.  
    * Improve Java reflection based on provided type hints (read blog post [here](https://blog.michielborkent.nl/babashka-java-reflection-type-hints.html))  
    * Add compatibility with the [fusebox](https://github.com/potetm/fusebox) library  
    * Fix virtual `ThreadBuilder` interop  
    * Add `java.util.concurrent.ThreadLocalRandom`  
    * Add `java.util.concurrent.locks.ReentrantLock`  
    * Add classes:
        * `java.time.chrono.ChronoLocalDate`
        * `java.time.temporal.TemporalUnit`
        * `java.time.chrono.ChronoLocalDateTime`
        * `java.time.chrono.ChronoZonedDateTime`
        * `java.time.chrono.Chronology`
    * [#1806](https://github.com/babashka/babashka/issues/1806): Add `cheshire.factory` namespace ([@lread](https://github.com/lread))
    * Bump GraalVM to `24`
    * Bump SCI to `0.9.45`
    * Bump edamame to `1.4.28`
    * [#1801](https://github.com/babashka/babashka/issues/1801): Add `java.util.regex.PatternSyntaxException`
    * Bump core.async to `1.8.735`
    * Bump cheshire to `6.0.0`
    * Bump babashka.cli to `0.8.65`
* [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
    * Replace tools.analyzer with a more light-weight analyzer which also adds support for Clojure 1.12
* [squint](https://github.com/squint-cljs/squint): CLJS *syntax* to JS compiler
    * [#653](https://github.com/squint-cljs/squint/issues/653): respect `:context expr` in `compile-string`
    * [#657](https://github.com/squint-cljs/squint/issues/657): respect `:context expr` in `set!` expression
    * [#659](https://github.com/squint-cljs/squint/issues/659): fix invalid code produced for REPL mode with respect to `return`
    * [#651](https://github.com/squint-cljs/squint/issues/651) Support `:require` + `:rename` + allow renamed value to be used in other :require clause
    * Fix [#649](https://github.com/squint-cljs/squint/issues/649): reset ns when compiling file and fix initial global object
    * Fix [#647](https://github.com/squint-cljs/squint/issues/647): emit explicit `null` when written in else branch of `if`
    * Fix [#640](https://github.com/squint-cljs/squint/issues/640): don't emit anonymous function if it is a statement ([@jonasseglare](https://github.com/jonasseglare))
    * Fix [#643](https://github.com/squint-cljs/squint/issues/643): Support lexicographic compare of arrays ([@jonasseglare](https://github.com/jonasseglare))
    * Fix [#602](https://github.com/squint-cljs/squint/issues/602): support hiccup-style shorthand for id and class attributes in `#jsx` and `#html`
    * Fix [#635](https://github.com/squint-cljs/squint/issues/635): `range` fixes
    * Fix [#636](https://github.com/squint-cljs/squint/issues/636): add `run!`
    * `defclass`: elide constructor when not provided
    * Fix [#603](https://github.com/squint-cljs/squint/issues/603): don't emit multiple returns
    * Drop constructor requirement for `defclass`
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * [#2522](https://github.com/clj-kondo/clj-kondo/issues/2522): support `:config-in-ns` on `:missing-protocol-method`
    * [#2524](https://github.com/clj-kondo/clj-kondo/issues/2524): support `:redundant-ignore` on `:missing-protocol-method`
    * [#1292](https://github.com/clj-kondo/clj-kondo/issues/1292): NEW linter: `:missing-protocol-method`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md)
    * [#2512](https://github.com/clj-kondo/clj-kondo/issues/2512): support vars ending with `.`, e.g. `py.` according to clojure analyzer
    * [#2516](https://github.com/clj-kondo/clj-kondo/issues/2516): add new `--repro` flag to ignore home configuration
    * [#2493](https://github.com/clj-kondo/clj-kondo/issues/2493): reduce image size of native image
    * [#2496](https://github.com/clj-kondo/clj-kondo/issues/2496): Malformed `deftype` form results in `NPE`
    * [#2499](https://github.com/clj-kondo/clj-kondo/issues/2499): Fix `(alias)` bug ([@Noahtheduke](https://github.com/Noahtheduke))
    * [#2492](https://github.com/clj-kondo/clj-kondo/issues/2492): Report unsupported escape characters in strings
    * [#2502](https://github.com/clj-kondo/clj-kondo/issues/2502): add end locations to invalid symbol
    * [#2511](https://github.com/clj-kondo/clj-kondo/issues/2511): fix multiple parse errors caused by incomplete forms
    * document var-usages location info edge cases ([@sheluchin](https://github.com/sheluchin))
    * Upgrade to GraalVM 24
    * Bump datalog parser
    * Bump built-in cache
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
    * Fix [#957](https://github.com/babashka/sci/issues/957): `sci.async/eval-string+` should return promise with `:val nil` for ns form rather than `:val &lt;Promise>`
    * Fix [#959](https://github.com/babashka/sci/issues/959): Java interop improvement: instance method invocation now leverages type hints
    * Fix [#942](https://github.com/babashka/sci/issues/942): improve error location of invalid destructuring
    * Add `volatile?` to core vars
    * Fix [#950](https://github.com/babashka/sci/issues/950): interop on local in CLJS
    * Bump edamame to `1.4.28`
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
    * Fix [#32](https://github.com/borkdude/quickdoc/issues/32): fix anchor links to take into account var names that differ only by case
    * Revert source link in var title and move back to `&lt;sub>`
    * Specify clojure 1.11 as the minimal Clojure version in `deps.edn`
    * Fix macro information
    * Fix [#39](https://github.com/borkdude/quickdoc/issues/39): fix link when var is named multiple times in docstring
    * Upgrade clj-kondo to `2025.04.07`
    * Add explicit `org.babashka/cli` dependency
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * [#119](https://github.com/babashka/cli/issues/119): `format-table` now formats multiline cells appropriately ([@lread](https://github.com/lread))
    * Remove `pom.xml` and `project.clj` for cljdoc
    * [#116](https://github.com/babashka/cli/issues/116): Un-deprecate `:collect` option to support custom transformation of arguments to collections ([@lread](https://github.com/lread))
    * Support `:collect` in `:spec`
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
    * [#163](https://github.com/babashka/process/issues/163), [#164](https://github.com/babashka/process/issues/164): Program resolution strategy for `exec` and Windows now matches macOS/Linux/PowerShell ([@lread](https://github.com/lread))
    * Fix memory leak by executing shutdown hook when process finishes earlier than VM exit ([@maxweber](https://github.com/maxweber))
* [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag
    * Fix [#3](https://github.com/borkdude/html/issues/3): allow dynamic attribute value: `(html [:a {:a (+ 1 2 3)}])`
    * Fix [#9](https://github.com/borkdude/html/issues/9): shortcuts for id and classes
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * Add `cljs.pprint/pprint`
    * Add `add-tap`
    * Bump squint compiler common which brings in new `#html` id and class shortcuts + additional features and optimizations, such as an optimization for `aset`
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Add better Deno + `jsr:` dependency support, stay tuned.
* [instaparse-bb](https://github.com/babashka/instaparse-bb): Use instaparse from babashka
    * Several improvements which makes babashka compatible with [test.chuck](https://github.com/gfredericks/test.chuck). See [this screenshot](https://files.mastodon.social/media_attachments/files/114/437/768/756/996/338/original/b8ebcb333f287e5c.png)!
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
    * [#117](https://github.com/borkdude/edamame/issues/117): throw on triple colon keyword
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
    * [#141](https://github.com/babashka/fs/issues/141): `fs/match` doesn't match when root dir contains glob or regex characters in path
    * [#138](https://github.com/babashka/fs/issues/138): Fix `fs/update-file` to support paths ([@rfhayashi](https://github.com/rfhayashi))
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
    * Upgrade to GraalVM 23, fixes encoding issue with Korean characters

**Other projects**
[Other projects I've been working on this month. See Click for More Details.](https://blog.michielborkent.nl/oss-updates-mar-apr-2025.html) <br>

*Published: 2025-05-02*
*Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)*

 <br>

---


## Oleksandr Yakushev  
2025 Annual Funding Report 2. Published May 5, 2025.  

Hello friends! Here's an update on my **March-April 2025 Clojurists Together** work.  

### CIDER  
- We just published a huge CIDER [**1.18**](https://clojurians.slack.com/archives/C015AL9QYH1/p1746007257709719) release that I spent two months working on. It is packed with features to the brim! See the full list of changes in the announcement.  
- 150 commits and 64 PRs accross 6 repositories.  
- Auxiliary releases: cider-nrepl 0.52.1 -> [0.55.7](https://github.com/clojure-emacs/cider-nrepl/blob/master/CHANGELOG.md#0557-2025-04-29), Orchard 0.30.1 -> [0.34.3](https://github.com/clojure-emacs/orchard/blob/master/CHANGELOG.md#0343-2025-04-28).  

### Compliment  

- New release: [0.7.0](https://github.com/alexander-yakushev/compliment/blob/master/CHANGELOG.md#070-2025-03-25).  
- New feature: priority-based candidate sorting.  

### Maintenance  

- Started testing all of the projects I maintain again JDK24.  
- Added support for JDK24 in Virgil ([0.4.0](https://github.com/clj-commons/virgil/blob/master/CHANGELOG.md#040-2025-03-27))  <br>


---

## Peter Taoussanis  
2025 Annual Funding Report 2. Published April 30, 2025.  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

\- [Peter Taoussanis](https://www.taoensso.com)  

Hi folks! 👋👋  

Hope everyone's well, and those in Europe enjoying the first glimpses of actual ☀️ in a while :-)  

### Recent work  

#### Telemere: structured logs and telemetry for Clj/s  

[Telemere](https://www.taoensso.com/telemere) **v1 stable** is now officially and finally [available](https://github.com/taoensso/telemere/releases/tag/v1.0.0)! 🍾🥳🎉  

It was a lot of work to get here, but I'm happy with the results - and I'm very grateful for all the folks that have been patiently testing early releases and giving feedback 🙏  

If you haven't yet had an opportunity to check out Telemere, now's a pretty good time.  

It's basically a modern rewrite of [Timbre](https://www.taoensso.com/timbre) that handles both **structured and unstructured logging** for Clojure and ClojureScript applications. It's small, fast, and *very* flexible.  

I'll of course continue to support Timbre, but Telemere offers a lot of advantages, and [migration](https://github.com/taoensso/telemere/wiki/5-Migrating#from-timbre) is often pretty straight-forward.

There's a couple video intros:

- [Lightning tour](https://www.youtube.com/watch?v=lOaZ0SgPVu4) (7 mins)  
- [Full intro](https://youtu.be/-L9irDG8ysM?si=nu9qTubt6BE_7h7e)  (24 mins)  

Telemere also has the most extensive [docs](https://github.com/taoensso/telemere/wiki/1-Getting-started) I've written for a library, including both:  

- Lots of beginner-oriented stuff, and  
- Advanced info for folks that might like to [write custom handlers](https://github.com/taoensso/telemere/wiki/4-Handlers#writing-handlers), [transforms](https://cljdoc.org/d/com.taoensso/telemere/CURRENT/api/taoensso.telemere#*xfn*), or [other tools](https://github.com/taoensso/telemere/wiki/8-Community#handlers-and-tools).  

#### Tufte: performance monitoring for Clj/s  

[Tufte](https://www.taoensso.com/tufte) **v3 RC1** is now also [available](https://github.com/taoensso/tufte/releases/tag/v3.0.0-RC1).  

Tufte's been around for ages but recently underwent a **major overhaul** focused on improving usability, and interop with Telemere.  

The two now share a **common core** for filtering and handling. This means that they get to share relevant concepts, terminology, capabilities, and config APIs.  

The shared core also means wider testing, easier ongoing maintenance, and the opportunity for improvements to further cross-pollinate in future.  

Performance has also been significantly improved, and the documentation greatly expanded. There's too much new stuff to mention here, but as usual please see the [release notes](https://github.com/taoensso/tufte/releases/tag/v3.0.0-RC1) for details.  

#### Other stuff  

Several other releases worth mentioning:  

- [Truss](https://www.taoensso.com/truss) [v2.1.0](https://github.com/taoensso/truss/releases/tag/v2.1.0) - micro toolkit for Clojure/Script errors  
- [http-kit](https://www.taoensso.com/http-kit) [v2.9.0-beta1](https://github.com/http-kit/http-kit/releases/tag/v2.9.0-beta1) - high performance HTTP client+server for Clojure  
- [Timbre](https://www.taoensso.com/timbre) [v6.7.0](https://github.com/taoensso/timbre/releases/tag/v6.7.0) - pure Clojure/Script logging library  
- [Nippy](https://www.taoensso.com/nippy) [v3.5.0](https://github.com/taoensso/nippy/releases/tag/v3.5.0) - fast serialization for Clojure  

I'll note that Telemere, Tufte, and Truss are now intended to form a sort of **suite of complementary observability tools** for modern Clojure and ClojureScript systems:  

- [Telemere](https://www.taoensso.com/telemere) for logging, tracing, and general telemetry  
- [Tufte](https://www.taoensso.com/tufte) for performance monitoring  
- [Truss](https://www.taoensso.com/truss) for assertions and error handling  

Together the 3x offer what I hope is quite a pleasant (and unique) observability story for Clojure/Script developers.  

### Upcoming work  

Next couple months I expect to focus on:  

- Getting [Tempel](https://www.taoensso.com/tempel) v1 stable out (data security framework for Clojure)  
- Significant work on [Sente](https://www.taoensso.com/sente) (realtime web comms for Clojure/Script)  

After that, still need to decide. Might be additional stuff for Telemere, or gearing up for the first public release of [Carmine](https://www.taoensso.com/carmine) v4 (Redis client + message queue for Clojure).  

Cheers everyone! :-)  <br>


