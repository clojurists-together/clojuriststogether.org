---
title: "Annually-Funded Developers' Update: Jan./Feb. 2025"
date: 2025-03-08T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Peter Taoussanis, Oleksandr Yakushev"  
draft: True


---

Hello Fellow Clojurists!
This is the first report from the 5 developers receivng Annual Funding on 2025. (Highlights listed on the list below - but the reports include much more).  

[Dragan Duric](#dragan-duric): Apple M Engine Neanderthal  
[Eric Dallo](#eric-dallo):lsp-intellij, repl-intellij. lsp, lsp4clj   
[Michiel Borkent](#michiel-borkent)  
[Peter Taoussanis](#peter-taoussanis): Truss v2, Telemere v1 RC3  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER, clj-async-profiler and Flamebin, clj-memory-meter    


## Dragan Duric  
2025 Annual Funding Report 1. Published February 27, 2025.

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

In January and February, I released the first version of Neanderhal that can run on Mac/Apple M!  

In more detail:  

Here's what I've proposed when applying for the CT grant.  

I propose to * Implement an Apple M engine for Neanderthal.* This involves:  
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

First, I set to the task of tidying up the existing versions of Uncomplicate libraries (Neanderthal, Deep Diamond, etc.) to bring them up with the latest versions of native libraries, cuda, etc., and to fix some outstanding issues/bugs that might complicate work on Apple M support. After that, it was time for the main task, the beginnings of Apple M support.  

The plan was to buy an Apple M2/M3, but in the meantime the nice Clojurians from Prague donated a used (but fantastically beefed up) MacBook Pro Max M1, so this was covered quickly!  

I explored OpenBLAS as the first choice (the other is Apple Accelerate), as it can also work on Linux and Windows, and could be immediately beneficial to all users and easier to start with (I didn't need to switch to Apple yet).  
I implemented the OpenBLAS engine for the part of functionality that was supported by JavaCPP's openblas preset.  
A lot of critical functionality was not present there (although some of it was there in the openblas itself),
so I jumped at the opportunity to learn some JavaCPP preset building, and improved JavaCPP's OpenBLAS.
After a bit of experimentation and lot of waiting on the compiler and github tools, this is now contributed upstream.  

Next, I returned to the pleasant part of work - programming in Clojure - and completed the first Neanderthal
engine that runs in Apple M Macs. This covers the core and linalg namespace, because this is what OpenBLAS covers. The rest of Neanderthal functionality is waiting for me to explore Apple Accelerate, and to create engine based on that.  

I managed to release Neanderthal 0.53.2, which enables Clojurians to use this on their Macs, just in time for this report.  

I hope they'll have immediate benefits, and have fun doing some high performance hacking on their Macs.  

Many thanks for CT for sponsoring this work!  <br>

---

## Eric Dallo  
2025 Annual Funding Report 1. Published February 27, 2025.  

In the first two months of sponsorship I could work on so many things related to IDE development which made me really glad of this sponsoship! :heart:  
I spend most of the time improving the Clojure development on IntelliJ, improving both [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij) and [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij) plugins releasing 2 major extremally important versions.
The IntelliJ Clojure development using those plugins are way better and mature, please test and give feedback!  

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

The 3.0.0 major version was a refactor on most of the plugin to use [lsp4ij](https://github.com/redhat-developer/lsp4ij), an OSS plugin for IntelliJ which makes easier to code and use LSP features, so this integration removed tons of code from this plugin that I needed to implement manually (and some had some bugs) and added support for lots of missing features that I didn't even plan to add. The lsp4ij plugin is used by multiple languages already which makes this plugin more resilient and stable. Check it out the call hieararchy feature on the image.  
![call-hierarchy dallo feb 2025](https://github.com/user-attachments/assets/10fec440-1eb0-4fbd-a668-be36f4061d73)

_Kudos to [@angelozerr](https://github.com/angelozerr) for the help during lsp4ij integration on their side._  

#### 3.0.0 - 3.1.0

- Fix brace matcher to insert closing brace for some missing cases.  
- Add imcompatible tag with Cursive and Clojure-Kit plugins.  
- Integrate with lsp4ij, a LSP client plugin, removing lots of logics from this plugin and fixing multiple bugs and issues. Fixes #63, #61, #59, #57, #53, #36, #21, #9, #5  
- Drop support for older intellijs, supporting 2023.3 onwards.  
- Bump clj4intellij to 0.6.3.  
- Fix code lens references not working when more than a project is opened. #67  
- Fix Settings page exception when more than a project was opened and closed.  
- Fix comment form complain about missing paren.  
- Improve server installation fixing concurrency bugs + using lsp4ij install API.  

### [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)  

This 2.0.0 major release included multiple fixes and new features like the new __Inlay hint eval result_ + __REPL syntax coloring_.  

![clojure-repl-intellij dallo feb 2025](https://github.com/user-attachments/assets/f441d4cc-8a14-4939-baaa-268b7886fbae)



_Kudos to [@afucher](https://github.com/afucher) for the help on some of those features._  

#### 2.0.0 - 2.2.0  

 - Add icons of REPL commands to REPL window (clear and entry history navigation). #99  
 - Drop support of older IntelliJ versions (2021/2022). Now requires minimum IntelliJ 2023.3 (Build 233)  
 - Fix namespace-not-found error handling. Now shows a message to the user. #107  
 - Add eval inlay hint support. #106  
 - Add action to interrupt evals on the REPL session (`shift alt R` + `shift alt S`).  #104  
 - Add color settings page for customization of some tokens.  
 - Add default name for RunConfigurations instead of save as Unnamed. #123  
 - Add REPL syntax highlight. #18  
 - Fix `eval defun at cursor` action error. #121  
 - Create view error on test error. #128  
 - Block backspace on repl input.  

### [clojure-lsp](https://clojure-lsp.io/)  

[clojure-lsp](https://clojure-lsp.io/) is the base for all Clojure language handle logic and analysis, so lots of fixes and improvements are made all the time.  

#### 2025.01.22-23.28.23 - 2025.02.07-16.11.24  

- General  
  - Bump clj-kondo to `2025.01.16`  
  - Bump lsp4clj to `1.11.0`.  
  - Add semantic version sorting in completion lib versions. #1913  
  - Fix internal error in range formatting. #1931  
  - Drop support for jdk 8. #1942  

- Editor  
  - Change simple keyword completion to return all known keywords. #1920  
  - Return textEdit to CompletionItems to fix completion in Zed #1933  
  - Restrict linked edits to namespace aliases only, and fix a few related issues #1947  
  - Add `:hover :hide-signature-call?` settings option to disable showing the surrounding call. #1954, @NoahTheDuke  
  - Revert #1933, which caused a regression on completion adding extra text.  
  - Fix fetching libs exception causing progress notification to be stuck. #1958  

- API/CLI  
  - Add `:project-and-shallow-analysis` type to `dump` command  
  - Add `:diagnostics` to `dump` command output (successor of `:findings`)  

### [lsp4clj](https://github.com/clojure-lsp/lsp4clj)  

lsp4lj is the base of clojure-lsp, it's the layer that has all the LSP communication layer, making easy to build LSP clients/servers of any language in Clojure.  

#### v1.11.0  

- Add a `:response-executor` option to control on which thread responses to
  server-initiated requests are run, defaulting to Promesa's `:default`
  executor, i.e. `ForkJoinPool/commonPool`.  
- Fix work done progress notification to allow nullable `message`.  

### [clj4intellij](https://github.com/ericdallo/clj4intellij)  

clj4intellij is a lib that makes possible to code IntelliJ plugins in Clojure, it's used by both clojure-lsp-intellij and clojure-repl-intellij plugins.  

#### 0.6.0 - 0.6.3  

- Add unregister-action! and improve register-action!  
- Add clj-kondo hook for proxy+.  <br>  

---

## Michiel Borkent  
2025 Annual Funding Report 1. Published February 28, 2025.  

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

As I'm writing this I'm still recovering from a flu that has kept me bedridden for a good few days, but I'm starting to feel better now. Here are updates about the projects/libraries I've worked on in the last two months.  
As I'm writing this I'm still recovering from a flu that has kept me bedridden for a good few days, but I'm starting to feel better now.

Here are updates about the projects/libraries I've worked on in the last two months.



* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * Unreleased:
    * [#2493](https://github.com/clj-kondo/clj-kondo/issues/2493): reduce image size of native image
    * 2025.02.20:
    * [#2473](https://github.com/clj-kondo/clj-kondo/issues/2473): New linter: :unknown-ns-options will warn on malformed (ns) calls. The linter is {:level :warning} by default. ([@Noahtheduke](https://github.com/Noahtheduke))
    * [#2475](https://github.com/clj-kondo/clj-kondo/issues/2475): add :do-template linter to check args & values counts ([@imrekoszo](https://github.com/imrekoszo))
    * [#2465](https://github.com/clj-kondo/clj-kondo/issues/2465): fix :discouraged-var linter for fixed arities
    * [#2277](https://github.com/clj-kondo/clj-kondo/issues/2277): prefer an array class symbol over (Class/forName ...) in defprotocol and extend-type
    * [#2466](https://github.com/clj-kondo/clj-kondo/issues/2466): fix false positive with tagged literal in macroexpand hook
    * [#2463](https://github.com/clj-kondo/clj-kondo/issues/2463): using :min-clj-kondo-version results in incorrect warning ([@imrekoszo](https://github.com/imrekoszo))
    * [#2464](https://github.com/clj-kondo/clj-kondo/issues/2464): :min-clj-kondo-version warning/error should have a location in config.edn ([@imrekoszo](https://github.com/imrekoszo))
    * [#2472](https://github.com/clj-kondo/clj-kondo/issues/2472) hooks api/resolve should return nil for unresolved symbols and locals
    * [#2472](https://github.com/clj-kondo/clj-kondo/issues/2472): add api/env to determine if symbol is local
    * [#2482](https://github.com/clj-kondo/clj-kondo/issues/2482): Upgrade to Oracle GraalVM 23
    * [#2483](https://github.com/clj-kondo/clj-kondo/issues/2483): add api/quote-node and api/quote-node? to hooks API ([@camsaul](https://github.com/camsaul))
    * [#2490](https://github.com/clj-kondo/clj-kondo/issues/2490): restore unofficial support for ignore hints via metadata
* [squint](https://github.com/squint-cljs/squint): CLJS *syntax* to JS compiler
    * Fix [#609](https://github.com/squint-cljs/squint/issues/609): make remove return a transducer when no collection is provided
    * Fix [#611](https://github.com/squint-cljs/squint/issues/611): Implement the set? function ([@jonasseglare](https://github.com/jonasseglare))
    * Fix [#613](https://github.com/squint-cljs/squint/issues/613): Optimize aset
    * Fix [#626](https://github.com/squint-cljs/squint/issues/XXX): Implement take-last
    * Fix [#615](https://github.com/squint-cljs/squint/issues/615): (zero? "0") should return false
    * Fix [#617](https://github.com/squint-cljs/squint/issues/617): deftype field name munging problem
    * Fix [#618](https://github.com/squint-cljs/squint/issues/618): Named multi-arity fn args don't get munged ([@grayrest](https://github.com/grayrest))
    * Fix [#622](https://github.com/squint-cljs/squint/issues/622): operator precendence issue with | and if
    * Add clojure.string functions lower-case, upper-case, capitalize ([@plexus](https://github.com/plexus))
    * Fix [#605](https://github.com/squint-cljs/squint/issues/605): merge command line --paths with squint.edn config properly
    * Fix [#607](https://github.com/squint-cljs/squint/issues/607): make mapcat return a transducer if no collections are provided ([@jonasseglare](https://github.com/jonasseglare))
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * Experimenting upgrading to new beta core.async, work in is a branch ready to be merged
    * [#1785](https://github.com/babashka/babashka/issues/1785): Allow subclasses of Throwable to have instance methods invoked ([@bobisageek](https://github.com/bobisageek))
    * [#1791](https://github.com/babashka/babashka/issues/1791): interop problem on Jsoup form element
    * [#1793](https://github.com/babashka/babashka/issues/1793): Bump rewrite-clj to 1.1.49 (fixes parsing of foo// among other things)
    * Bump deps.clj
    * Bump fs
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
    * v0.5.24 (2025-01-09)
    * [#135](https://github.com/babashka/fs/issues/135): additional fix for preserving protocol when calling fs/path on multiple arguments ([@Sohalt](https://github.com/Sohalt))
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
    * Records should have keys present and set to nil
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Catch up with several new versions of clojure CLI
    * Fix [#132](https://github.com/borkdude/deps.clj/issues/132): copy install tools.edn to config dir when install version is newer, similar to clojure CLI bash script
    * Adds support for XDG_DATA_HOME environment variable according to [XDG Base Directory Specification](https://specifications.freedesktop.org/basedir-spec/latest/)
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
    * Add support for DuckDB ([@avelino](https://github.com/paintparty))
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
    * Fix #115: add location to exception for invalid keyword
* [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of rewrite-clj with common operations to update EDN while preserving whitespace and comments
    * [#40](https://github.com/borkdude/rewrite-edn/issues/40): assoc/update now handles map keys that have no indent at all ([@lread](https://github.com/lread))
    * [#43](https://github.com/borkdude/rewrite-edn/issues/43): bump rewrite-clj to 1.1.49 ([@lread](https://github.com/lread))
    * [#40](https://github.com/borkdude/rewrite-edn/issues/40): assoc/update now handles map keys that have no indent at all ([@lread](https://github.com/lread))
    * [#40](https://github.com/borkdude/rewrite-edn/issues/40): assoc/update now aligns indent to comment if that's all that is in the map ([@lread](https://github.com/lread))
    * [#40](https://github.com/borkdude/rewrite-edn/issues/40): update now indents new entries in same way as assoc ([@lread](https://github.com/lread)) <br>  

---

## Peter Taoussanis  
2025 Annual Funding Report 1. Published February 27, 2025.  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

\- [Peter Taoussanis](https://www.taoensso.com)  


Hi everyone! 👋  

I've been focused recently on getting [Telemere](https://www.taoensso.com/telemere) v1 over the finish line. That's been a lot of ongoing work, in part because I'm trying to establish patterns that can be easily shared between a suite of complementary libs (Telemere, [Tufte](https://www.taoensso.com/tufte), and [Truss](https://www.taoensso.com/truss)).  

I've also been considering long-term plans for how to better modularize [Encore](https://www.taoensso.com/encore) to help reduce dependency and build sizes where relevant. More on that later in the year.  

### Recent work  

#### Truss v2 - an opinionated micro toolkit for Clj/s errors  

I've recently released [Truss v2](https://github.com/taoensso/truss/releases/tag/v2.0.0), which has enlarged the scope of the library from just assertion utils to a **general error toolkit** for Clojure and ClojureScript.  

v2 includes:  

- A ground-up rewrite of the existing assertion utils to improve performance, reduce build size, and improve integration with Telemere.  
- A new set of small but high-value error utils moved from Encore as part of the ongoing modularization effort.  
- A new [contextual exception](https://cljdoc.org/d/com.taoensso/truss/CURRENT/api/taoensso.truss#ex-info) type that's already used by Encore and Telemere, and will be used by all of my other libraries in future.  
- Updated docs and a new [Slack channel](https://www.taoensso.com/truss/slack).  

Truss v2 basically packages together a minimal set of mature tools and patterns that I've used over many years to help tame Clojure's often unruly errors. It's simple stuff, but practical - and it helps.  

For more info see the [README](https://github.com/taoensso/truss) and [docstrings](https://cljdoc.org/d/com.taoensso/truss/CURRENT/api/taoensso.truss).

#### Telemere v1 RC3 - structured logging and telemetry for Clj/s  

I was hoping to release Telemere v1 final this month, but decided that a [third release candidate](https://github.com/taoensso/telemere/releases/tag/v1.0.0-RC3) was warranted.  

The latest improvements are focused on harmonizing relevant terminology, concepts, and API between Telemere, [Truss](https://www.taoensso.com/truss), and the forthcoming v3 of [Tufte](https://www.taoensso.com/tufte).  

Big thanks to everyone that's been helping test and give feedback! v1 final really should (finally) be available next month 🙏  

### Upcoming work  

The next major release planned is [Tufte](https://www.taoensso.com/tufte) v3. I've already got an early draft prepared, but there's polish needed - and new docs.  

v3 improves performance and significantly improves interop with Telemere, offering what I believe to be a pretty compelling combination for [Clojure/Script observability and monitoring](https://github.com/taoensso/telemere#next-gen-observability).  

For plans after that, you can see my [2025 roadmap](https://taoensso.com/roadmap/2025)), which as usual I'll keep updated along the way 👍  

Cheers everyone! :-)  <br>  

---

## Oleksandr Yakushev  
2025 Annual Funding Report 1. Published February 28, 2025.  

Hello friends! Here's a short summary of my **January-February Clojurists Together 2025 sponsorship** work.  

### CIDER  

- I spearheaded the [**1.17**](https://github.com/clojure-emacs/cider/releases/tag/v1.17.0) and **1.17.1** releases of CIDER.  
- The biggest feature of the release is the support for automatic downloading of Java sources (details: https://clojurians.slack.com/archives/C06MAR553/p1739810631203369).  
- Auxiliary releases: nrepl [1.3.1](https://github.com/nrepl/nrepl/blob/master/CHANGELOG.md#131-2025-01-01), cider-nrepl [0.52.1](https://github.com/clojure-emacs/cider-nrepl/blob/master/CHANGELOG.md#0521-2025-02-24), Orchard [0.30.1](https://github.com/clojure-emacs/orchard/blob/master/CHANGELOG.md#0301-2025-02-24).  

### clj-async-profiler and Flamebin  

- New releases: [1.6.0](https://github.com/clojure-goes-fast/clj-async-profiler/blob/master/CHANGELOG.md#160-2025-01-09) and [1.6.1](https://github.com/clojure-goes-fast/clj-async-profiler/blob/master/CHANGELOG.md#161-2025-02-10).  
- New feature: switchable viewing modes for diffgraphs (details: https://clojurians.slack.com/archives/C06MAR553/p1736447669594659)
- New feature: total percentage is automatically calculated for highlighted frames.
- Both features are also supported at Flamebin (https://flamebin.dev/).  

### clj-memory-meter  

- New release: [0.4.0](https://github.com/clojure-goes-fast/clj-memory-meter/blob/master/CHANGELOG.md#040-2025-02-18).  
- New feature: memory usage tracing.  
- New blog post: I have written about this new clj-memory-meter feature on Clojure Goes Fast blog: [Tracking memory usage with clj-memory-meter.trace](https://clojure-goes-fast.com/blog/tracking-memory-usage/).  

### Misc projects  

- New release: virgil [0.3.2](https://github.com/clj-commons/virgil/blob/master/CHANGELOG.md#032-2025-01-28) â€” bugfixes.  
- New release: clj-java-decompiler [0.3.7](https://github.com/clojure-goes-fast/clj-java-decompiler/blob/master/CHANGELOG.md#037-2025-01-28) â€” bugfixes.  <br>  


