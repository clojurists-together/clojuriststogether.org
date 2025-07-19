---
title: "Annually-Funded Developers' Update: May/June 2025"
date: 2025-07-19T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Oleksandr Yakushev, Peter Taoussanis"  
draft: True

---

Hello Fellow Clojurists!
This is the third report from the 5 developers receiving Annual Funding in 2025. 


[Dragan Duric](#dragan-duric): Apple M Engine Neanderthal, MKL, OpenBlas   
[Eric Dallo](#eric-dallo): ECA, metrepl, clojure-lsp, clojure-lsp-intellij  
[Michiel Borkent](#michiel-borkent): clj-kondo, babashka, SCI, quickblog, edamame, and more...  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER  
[Peter Taoussanis](#peter-taoussanis): Trove, Sente, Tufte

## Dragan Duric  
2025 Annual Funding Report 3. Published July 4, 2025.  

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

In May and June, I completed Neanderthal's Apple Silicon engine, updated MKL and OpenBLAS engines to be compatible with the introduced improvements to the overall code, fixed and polished many incompatibilities between the engines, and bugs that were introduced or discovered during the process, and updated all technologies newer versions. One huge improvement that the users will notice is AOT version of Neanderthal that is loading engines quickly.  

I also reorganized Deep Diamond as separate technology-specific projects (so the users can configure their own combination appropriate to their systems) and an AOT project. I started working on the CPU engine for tensors and neural networks. It's an uphill battle, since
Apple's API are:  
- not very nice compared to Intel and Nvidia (which are atrocities themselves compared to Clojurians' standards :))  
- poorly documented  
- very inconsistent -  
- contain several generations of deprecations and new ways of doing things  
- buggy  
- (most annoyingly) there are literally no code examples anywhere on the internet, so I have to discover everything by poking inside the REPL (I can't imagine how people do that without Clojure/REPL :) So, I made lots of progress, but not nearly enough for a stable engine. It will take time.  

As I also made some contributions to JavaCPP, I had to wait for it to release new version for the proper release of Neanderthal. They finally did it on 1st July, so I released Neanderthal as soon as I could (not in June, but day or two later :)  

I also made assorted improvements and bugfixes to related Uncomplicate projects, all of them under the hood, but some of them very useful!  

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
https://github.com/uncomplicate/clojurecuda  

Looking at the funding proposal, I can say that I'm very satisfied that all the features that I promised to build are nicely progressing.  

As the Deep Diamond Tensor stuff is brewing, I might start looking into Apple Metal and low-level GPU computing, so I can hope to also provide GPU computing on Apple silicon at least for Neanderthal, if not for Deep Diamond.  

All in all, I feel optimistic about how this project progresses!  <br>

---


## Eric Dallo 
2025 Annual Funding Report 3. Published June 30, 2025. 

In these last 2 months I could work on 2 main things, multiple improvements for clojure-lsp with fixes and new features and a exciting new project called [ECA](https://github.com/editor-code-assistant/eca).  

### [eca](https://github.com/editor-code-assistant/eca)  
ECA (Editor Code Assistant) is a OpenSource, free, standardized server written in Clojure to make any editor have AI features like Cursor, Continue, Claude and others.
The idea is simple, it has a protocol heavily inspired by LSP, and editors can communicate with the eca server which will have the logic to communicate with multiple LLMs and provide AI features like chat, edit, MCPs, completion etc.  

The project is in alpha state and there are so many things to do, but I hope community will help make this project a success!  

[Give a star to the project](https://github.com/editor-code-assistant/eca) 🌟 and for more details check the [roadmap](https://github.com/orgs/editor-code-assistant/projects/1)  

![eca-emacs](https://github.com/user-attachments/assets/66e61f06-733e-4208-80a2-f7c75a74552e)


### [clojure-lsp](https://clojure-lsp.io/)  
There were so many improvements for clojure-lsp regarding performance, fixes, and new features. 
The most notable new feature is the [support for custom linters](https://clojure-lsp.io/features/#custom-linters) defined by the user, it follows a similar logic of clj-kondo hooks, but with improved support for clojure-lsp in-memory data access, giving power for linters know about the project and not only a single file, with test support for linters.
Check the missing-unit-test custom linter:  
<img width="1023" height="140" alt="custom-linters" src="https://github.com/user-attachments/assets/8ac55c51-6ce0-4959-9ddc-e89d8f304ed4" />


#### 2025.05.27-13.56.57 - 2025.06.13-20.45.44  
- General
  - Consider `.lsp/config.edn` as part of project code, removing false positives of unused-public-var linter.
  - Consider full qualified symbols in edn files when checking for var references.
  - Improve clojure-lsp linter capabilities, migrating `unused-public-var` and `different-aliases` linters to be built-in linters. #2050
    - Migrate from clj-kondo `custom-lint-fn` but considering kondo settings to avoid breaking changes.
    - Considerably improve performance of `unused-public-var` and `different-aliases` linters.
  - Bump rewrite-clj to 1.2.50.
  - New feature: Add support for custom project linters. #2043 #2058
  - Publish to clojars `com.github.clojure-lsp/clojure-lsp-test-helper` to be able to test created custom linters.
  - Bump clj-kondo to `2025.04.08-20250526.195207-12`.
  - Small performance improvements across clojure-lsp, especially on places with comparassions inside big loops.
  - Bump clj-depend to `0.11.1`.
  - Provide analysis of unresolved namespaces, making features like definition, hover, references work.  

  - Fix .lsp/config.edn file not found diagnostic when it doesn't exist on project.
  - Fix custom linters source-paths analysis to consider all files in a source-path.
  - Fix crash on files with empty ignore comments.
  - Bump clj-kondo to `2025.06.05`.
  - Fix analysis consistency for internal files.
  - Fix custom-linters not working for cases using `clojure-lsp.custom-linters-api/find-nodes`.
  - Improve `clojure-lsp.custom-linters-api/dir-uris->file-uris` to consider file-uris as well on input.
  
- Editor
  - Add support for LSP feature `textDocument/selectionRange`. #1961
  - Fix outdated analysis for watched created/deleted files (git branch switchs for example). #2046

- API/CLI
  - Replace `tools.cli` with `babashka.cli`. #2036

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)

#### 3.5.0
- Add support for creating Clojure namespaces. #78

### [metrepl](https://github.com/ericdallo/metrepl)

#### 0.3.1 - 0.4.1
- Add compatibility with older opentelemetry versions.
- Fix read config from classpath
- Fix OpenTelemetry integration race condition corner case.
- Replace `event/first-op-requested` with `info/repl-ready` adding more info about the project.
- Add `:first-time` to `:event/op-completed` and `:event/op-requested` when op is `load-file` and first time processing it.
- Bump opentelemetry to 1.51.0.  <br>  

---

## Michiel Borkent  
2025 Annual Funding Report 3. Published July 1, 2025.  

In this post I'll give updates about open source I worked on during May and June 2025.
To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work
possible. Without you the below projects would not be as mature or wouldn't
exist or be maintained at all! So a sincere thank you to everyone who
contributes to the sustainability of these projects.

<img alt="gratitude" src="https://emoji.slack-edge.com/T03RZGPFR/gratitude/f8716bb6fb7e5249.png" width="50px" text-align="center">

Current top tier sponsors:

- [Clojurists Together](https://clojuriststogether.org/)
- [Roam Research](https://roamresearch.com/)
- [Nextjournal](https://nextjournal.com/)
- [Nubank](https://nubank.com.br)

Open the details section for more info about sponsoring.

<details>
<summary>Sponsor info</summary>

If you want to ensure that the projects I work on are sustainably maintained,
you can sponsor this work in the following ways. Thank you!

- [Github Sponsors](https://github.com/sponsors/borkdude)
- The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
- [Ko-fi](https://ko-fi.com/borkdude)
- [Patreon](https://www.patreon.com/borkdude)
- [Clojurists Together](https://www.clojuriststogether.org/)

</details>

<!--

- ls -lat ~/dev
- babashka sub dir checken

-->

### Updates

Here are updates about the projects/libraries I've worked on in the last two months, 19 in total!

- [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
  - Bump edamame (support old-style `#^` metadata)
  - Bump SCI: fix `satisfies?` for protocol extended to `nil`
  - Bump rewrite-clj to `1.2.50`
  - **1.12.204 (2025-06-24)**
  - Compatibility with [clerk](https://github.com/nextjournal/clerk)'s main branch
  - [#1834](https://github.com/babashka/babashka/issues/1834): make `taoensso/trove` work in bb by exposing another `timbre` var
  - Bump `timbre` to `6.7.1`
  - Protocol method should have `:protocol` meta
  - Add `print-simple`
  - Make bash install script work on Windows for GHA
  - Upgrade Jsoup to `1.21.1`
  - **1.12.203 (2025-06-18)**
  - Support `with-redefs` + `intern` (see SCI issue [#973](https://github.com/babashka/sci/issues/973)
  - [#1832](https://github.com/babashka/babashka/issues/1832): support `clojure.lang.Var/intern`
  - Re-allow `init` as task name
  - **1.12.202 (2025-06-15)**
  - Support `clojure.lang.Var/{get,clone,reset}ThreadBindingFrame` for JVM Clojure compatibility
  - [#1741](https://github.com/babashka/babashka/issues/1741): fix `taoensso.timbre/spy` and include test
  - Add `taoensso.timbre/set-ns-min-level!` and `taoensso.timbre/set-ns-min-level`
  - **1.12.201 (2025-06-12)**
  - [#1825](https://github.com/babashka/babashka/issues/1825): Add [Nextjournal Markdown](https://github.com/nextjournal/markdown) as built-in Markdown library
  - Promesa compatibility (pending PR [here](https://github.com/funcool/promesa/pull/160))
  - Upgrade clojure to `1.12.1`
  - [#1818](https://github.com/babashka/babashka/issues/1818): wrong argument order in `clojure.java.io/resource` implementation
  - Add `java.text.BreakIterator`
  - Add classes for compatibility with [promesa](https://github.com/funcool/promesa):
    - `java.lang.Thread$Builder$OfPlatform`
    - `java.util.concurrent.ForkJoinPool`
    - `java.util.concurrent.ForkJoinPool$ForkJoinWorkerThreadFactory`
    - `java.util.concurrent.ForkJoinWorkerThread`
    - `java.util.concurrent.SynchronousQueue`
  - Add `taoensso.timbre/set-min-level!`
  - Add `taoensso.timbre/set-config!`
  - Bump `fs` to `0.5.26`
  - Bump `jsoup` to `1.20.1`
  - Bump `edamame` to `1.4.30`
  - Bump `taoensso.timbre` to `6.7.0`
  - Bump `pods`: more graceful error handling when pod quits unexpectedly
  - [#1815](https://github.com/babashka/babashka/issues/1815): Make install-script wget-compatible ([@eval](https://github.com/eval))
  - [#1822](https://github.com/babashka/babashka/issues/1822): `type` should prioritize `:type` metadata
  - `ns-name` should work on symbols
  - `:clojure.core/eval-file` should affect `*file*` during eval
  - [#1179](https://github.com/babashka/babashka/issues/1179): run `:init` in tasks only once
  - [#1823](https://github.com/babashka/babashka/issues/1823): run `:init` in tasks before task specific requires
  - Fix `resolve` when `*ns*` is bound to symbol
  - Bump `deps.clj` to `1.12.1.1550`
  - Bump `http-client` to `0.4.23`

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - **0.10.47 (2025-06-27)**
  - Security issue: function recursion can be forced by returning internal keyword as return value
  - Fix [#975](https://github.com/babashka/sci/issues/975): Protocol method should have :protocol var on metadata
  - Fix [#971](https://github.com/babashka/sci/issues/971): fix `satisfies?` for protocol that is extended to `nil`
  - Fix [#977](https://github.com/babashka/sci/issues/977): Can't analyze sci.impl.analyzer with splint
  - **0.10.46 (2025-06-18)**
  - Fix [#957](https://github.com/babashka/sci/issues/957): `sci.async/eval-string+` should return promise with `:val nil` for ns form rather than `:val <Promise>`
  - Fix [#959](https://github.com/babashka/sci/issues/959): Java interop improvement: instance method invocation now leverages type hints
  - Bump edamame to `1.4.30`
  - Give metadata `:type` key priority in `type` implementation
  - Fix [#967](https://github.com/babashka/sci/issues/967): `ns-name` should work on symbols
  - Fix [#969](https://github.com/babashka/sci/issues/969): `^:clojure.core/eval-file` metadata should affect binding of `*file*` during evaluation
  - Sync `sci.impl.Reflector` with changes in `clojure.lang.Reflector` in clojure 1.12.1
  - Fix `:static-methods` option for class with different name in host
  - Fix [#973](https://github.com/babashka/sci/issues/973): support `with-redefs` on core vars, e.g. `intern`. The fix for this
    issue entailed quite a big refactor of internals which removes "magic"
    injection of ctx in core vars that need it.
  - Add `unchecked-set` and `unchecked-get` for CLJS compatibility

- [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
  - Make clerk compatible with babashka

- [quickblog](https://github.com/borkdude/quickblog): light-weight static blog engine for Clojure and babashka
  - **0.4.7 (2025-06-12)**
  - Switch to [Nextjournal Markdown](https://github.com/nextjournal/markdown) for markdown rendering
    The minimum babashka version to be used with quickblog is now v1.12.201 since it comes with Nextjournal Markdown built-in.
  - Link to previous and next posts; see "Linking to previous and next posts" in
    the README ([@jmglov](https://github.com/jmglov))
  - Fix flaky caching tests ([@jmglov](https://github.com/jmglov))
  - Fix argument passing in test runner ([@jmglov](https://github.com/jmglov))
  - Add `--date` to api/new. ([@jmglov](https://github.com/jmglov))
  - Support Selmer template for new posts in api/new; see [Templates > New
    posts](README.md#new-posts) in README. ([@jmglov](https://github.com/jmglov))
  - Add 'language-xxx' to pre/code blocks
  - Fix README.md with working version in quickstart example
  - Fix [#104](https://github.com/borkdude/quickblog/issues/104): fix caching with respect to previews
  - Fix [#104](https://github.com/borkdude/quickblog/issues/104): document `:preview` option

- [edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
  - **1.4.31 (2025-06-25)**
  - Fix [#124](https://github.com/borkdude/edamame/issues/124): add `:imports` to `parse-ns-form`
  - Fix [#125](https://github.com/borkdude/edamame/issues/125): Support `#^:foo` deprecated metadata reader macro ([@NoahTheDuke](https://github.com/NoahTheDuke))
  - Fix [#127](https://github.com/borkdude/edamame/issues/127): expose `continue` value that indicates continue-ing parsing ([@NoahTheDuke](https://github.com/NoahTheDuke))
  - Fix [#122](https://github.com/borkdude/edamame/issues/122): let `:auto-resolve-ns` affect syntax-quote
  - **1.4.30**
  - [#120](https://github.com/borkdude/edamame/issues/120): fix `:auto-resolve-ns` failing case

- [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  - [#678](https://github.com/squint-cljs/squint/issues/678): Implement `random-uuid` ([@rafaeldelboni](https://github.com/rafaeldelboni))
  - **v0.8.149 (2025-06-19)**
  - [#671](https://github.com/squint-cljs/squint/issues/671): Implement `trampoline` ([@rafaeldelboni](https://github.com/rafaeldelboni))
  - Fix [#673](https://github.com/squint-cljs/squint/issues/673): remove experimental atom as promise option since it causes unexpected behavior
  - Fix [#672](https://github.com/squint-cljs/squint/issues/672): alias may contain dots
  - **v0.8.148 (2025-05-25)**
  - Fix [#669](https://github.com/squint-cljs/squint/issues/669): munge refer-ed + renamed var
  - **v0.8.147 (2025-05-09)**
  - Fix [#661](https://github.com/squint-cljs/squint/issues/661): support `throw` in expression position
  - Fix [#662](https://github.com/squint-cljs/squint/issues/662): Fix extending protocol from other namespace to `nil`
  - Better solution for multiple expressions in return context in combination with pragmas
  - Add an [ajv](examples/ajv) example

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.<br>
  - [#2560](https://github.com/clj-kondo/clj-kondo/issues/2560): NEW linter: `:locking-suspicious-lock`: report when locking is used on a single arg, interned value or local object
  - [#2555](https://github.com/clj-kondo/clj-kondo/issues/2555): false positive with `clojure.string/replace` and `partial` as replacement fn
  - **2025.06.05**
  - [#2541](https://github.com/clj-kondo/clj-kondo/issues/2541): NEW linter: `:discouraged-java-method`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md)
  - [#2522](https://github.com/clj-kondo/clj-kondo/issues/2522): support `:config-in-ns` on `:missing-protocol-method`
  - [#2524](https://github.com/clj-kondo/clj-kondo/issues/2524): support `:redundant-ignore` on `:missing-protocol-method`
  - [#2536](https://github.com/clj-kondo/clj-kondo/issues/2536): false positive with `format` and whitespace flag after percent
  - [#2535](https://github.com/clj-kondo/clj-kondo/issues/2535): false positive `:missing-protocol-method` when using alias in method
  - [#2534](https://github.com/clj-kondo/clj-kondo/issues/2534): make `:redundant-ignore` aware of `.cljc`
  - [#2527](https://github.com/clj-kondo/clj-kondo/issues/2527): add test for using ns-group + config-in-ns for `:missing-protocol-method` linter
  - [#2218](https://github.com/clj-kondo/clj-kondo/issues/2218): use `ReentrantLock` to coordinate writes to cache directory within same process
  - [#2533](https://github.com/clj-kondo/clj-kondo/issues/2533): report inline def under fn and defmethod
  - [#2521](https://github.com/clj-kondo/clj-kondo/issues/2521): support `:langs` option in `:discouraged-var` to narrow to specific language
  - [#2529](https://github.com/clj-kondo/clj-kondo/issues/2529): add `:ns` to `&env` in `:macroexpand-hook` macros when executing in CLJS
  - [#2547](https://github.com/clj-kondo/clj-kondo/issues/2547): make redundant-fn-wrapper report only for all cljc branches
  - [#2531](https://github.com/clj-kondo/clj-kondo/issues/2531): add `:name` data to `:unresolved-namespace` finding for clojure-lsp

- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
  - A configuration for [replicant](https://github.com/cjohansen/replicant/) was added

- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
  - **v0.7.23 (2025-06-18)**
  - [#107](https://github.com/babashka/scittle/issues/107): add `replicant` plugin ([@jeroenvandijk](https://github.com/jeroenvandijk))
  - [#102](https://github.com/babashka/scittle/issues/102): add `applied-science/js-interop` plugin ([@chr15m](https://github.com/chr15m))
  - [#105](https://github.com/babashka/scittle/issues/105): add `goog.string/htmlEscape` ([@ikappaki](https://github.com/ikappaki) )
  - [#113](https://github.com/babashka/scittle/issues/113): add `unchecked-set` and `unchecked-get`

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
  - **1.3.204 (2025-05-15)**
  - [#389](https://github.com/babashka/nbb/issues/389): fix regression caused by [#387](https://github.com/babashka/nbb/issues/387)
  - **1.3.203 (2025-05-13)**
  - [#387](https://github.com/babashka/nbb/issues/387): bump `import-meta-resolve` to fix deprecation warnings on Node 22+
  - **1.3.202 (2025-05-12)**
  - Fix nbb nrepl server for Deno
  - **1.3.201 (2025-05-08)**
  - Deno improvements for loading `jsr:` and `npm:` deps, including react in combination with reagent
  - [#382](https://github.com/babashka/nbb/issues/382): prefix all node imports with `node:`

- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
  - **v0.2.5 (2025-05-01)**
  - Fix [#32](https://github.com/borkdude/quickdoc/issues/32): fix anchor links to take into account var names that differ only by case
  - **v0.2.4 (2025-05-01)**
  - Revert source link in var title and move back to `<sub>`
  - Specify clojure 1.11 as the minimal Clojure version in `deps.edn`
  - Fix macro information
  - Fix [#39](https://github.com/borkdude/quickdoc/issues/39): fix link when var is named multiple times in docstring
  - Upgrade clj-kondo to `2025.04.07`
  - Add explicit `org.babashka/cli` dependency

- [Nextjournal Markdown](https://github.com/nextjournal/markdown)
  - **0.7.186**
  - Make library more GraalVM `native-image` friendly
  - **0.7.184**
  - Consolidate utils in `nextjournal.markdown.utils`
  - **0.7.181**
  - Hiccup JVM compatibility for fragments (see [#34](https://github.com/nextjournal/markdown/issues/34))
  - Support HTML blocks (`:html-block`) and inline HTML (`:html-inline`) (see [#7](https://github.com/nextjournal/markdown/issues/7))
  - Bump commonmark to 0.24.0
  - Bump markdown-it to 14.1.0
  - Render `:code` according to spec into `<pre>` and `<code>` block with language class (see [#39](https://github.com/nextjournal/markdown/issues/39))
  - No longer depend on `applied-science/js-interop`
  - Accept parsed result in `->hiccup` function
  - Expose `nextjournal.markdown.transform` through main `nextjournal.markdown` namespace
  - Stabilize API and no longer mark library alpha

- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
  - Add `:responses` key with raw responses

- [speculative](https://github.com/borkdude/speculative)
  - Add spec for `even?`

- [http-client](https://github.com/babashka/http-client): babashka's http-client
  -  **0.4.23 (2025-06-06)**
  - [#75](https://github.com/babashka/http-client/issues/75): override existing content type header in multipart request
  - Accept `:request-method` in addition to `:request` to align more with other clients
  - Accept `:url` in addition to `:uri` to align more with other clients

- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
  - This is a brand new project!

- [fs](https://github.com/babashka/fs) - File system utility library for Clojure
  - [#147](https://github.com/babashka/fs/issues/147): `fs/unzip` should allow selective extraction of files ([@sogaiu](https://github.com/sogaiu))
  - [#145](https://github.com/babashka/fs/issues/145): `fs/modified-since` works only with ms precision but should support the precision of the filesystem

- [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - Fix `cherry.embed` which is used by malli

- [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
  - Released several versions catching up with the clojure CLI

### Other projects

These are (some of the) other projects I'm involved with but little to no activity
happened in the past month.

<details>
<summary>Click for more details</summary>  
- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!  <br>  
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes  <br>  
- [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag  <br> 
- [instaparse-bb](https://github.com/babashka/instaparse-bb): Use instaparse from babashka  <br> 
- [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases  <br> 
- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of  <br> 
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn  <br> 
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3  <br> 
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka  <br> 
- [http-server](https://github.com/babashka/http-server): serve static assets  <br> 
- [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one comman  <br> 
- [qualify-methods](https://github.com/borkdude/qualify-methods)  <br> 
  - Initial release of experimental tool to rewrite instance calls to use fully
    qualified methods (Clojure 1.12 only0  <br> 
- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects.  <br> 
- [tools](https://github.com/borkdude/tools): a set of [bbin](https://github.com/babashka/bbin/) installable scripts  <br> 
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser  <br> 
- [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter  <br> 
- [squint-macros](https://github.com/squint-cljs/squint-macros): a couple of
  macros that stand-in for 
  [applied-science/js-interop](https://github.com/applied-science/js-interop)
  and [promesa](https://github.com/funcool/promesa) to make CLJS projects
  compatible with squint and/or cherry.  <br> 
- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes  <br> 
- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo): a leiningen plugin for clj-kondo  <br> 
- [http-kit](https://github.com/http-kit/http-kit): Simple, high-performance event-driven HTTP client+server for Clojure.  <br> 
- [babashka.nrepl](https://github.com/babashka/babashka.nrepl): The nREPL server from babashka as a library, so it can be used from other SCI-based CLIs  <br> 
- [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure  <br> 
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod  <br> 
- [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter  <br> 
- [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI  <br> 
- [babashka.book](https://github.com/babashka/book): Babashka manual  <br> 
- [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).  <br> 
- [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently  <br> 
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars  <br> 
- [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!  <br> 
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka  <br> 
- [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))  <br> 
- [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp  <br> 
- [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly! <br>  
- [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI  <br> 

</details>  <br>  

---

## Oleksandr Yakushev  
2025 Annual Funding Report 3. Published July 5, 2025.   

Hello friends! Here's the May-June 2025 for my Clojurists Together work!. I've been focusing on CIDER during these two months, so all updates are related to it:  

- New [hexdump inspector view](https://github.com/clojure-emacs/orchard/pull/342) for displaying byte arrays.  
- New [diff inspector view](https://github.com/clojure-emacs/orchard/pull/350) for inspecting differences in nested data structures.  
- Small inspector improvements ([map sorting](https://github.com/clojure-emacs/orchard/pull/349), [display class flags](https://github.com/clojure-emacs/orchard/pull/348))
- [Reduced the instrumentation footprint](https://github.com/clojure-emacs/cider-nrepl/pull/943) of CIDER debugger, which allows debugging larger functions.  
- Reworked approach to enabling [3rd-party pretty-printers](https://github.com/clojure-emacs/cider-nrepl/pull/941).  
- Completed the [transition](https://github.com/clojure-emacs/cider/pull/3816) from `enrich-classpath` to `cider-download-java-sources` for accessing 3rd-party Java sources.  
- All changes are already in the rolling CIDER version, the stable version will be released shortly.  
- Auxiliary releases: cider-nrepl 0.55.7 -> [0.57.0](https://github.com/clojure-emacs/cider-nrepl/blob/master/CHANGELOG.md#0570-2025-06-29), Orchard 0.34.3 -> [0.36.0](https://github.com/clojure-emacs/orchard/blob/master/CHANGELOG.md#0360-2025-06-29).  <br>

---

## Peter Taoussanis  
2025 Annual Funding Report 3. Published July 1, 2025.   

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

\- [Peter Taoussanis](https://www.taoensso.com)  


Hello from sunny Berlin! 👋 🐝 🫶  

### Recent work  

### Introducing Trove   

First up, a new micro library! It's called [Trove](https://www.taoensso.com/trove), and it's basically a modern minimal (~100 loc) [tools.logging](https://github.com/clojure/tools.logging) that adds support for ClojureScript, structured logging and filtering.  

#### Who's this for?  

Mostly **library authors** that want to do library-level logging without forcing a particular logging library on their users.  

Authors _could_ use tools.logging, but that doesn't support ClojureScript, structured logging, or the kind of data-oriented filtering possible with structured logging.  

#### Motivation  

With [Telemere](https://www.taoensso.com/telemere) now stable, I wanted to start updating libraries like [Sente](https://www.taoensso.com/sente) and [Carmine](https://www.taoensso.com/carmine) to support structured logging.  

Trove basically allows me to do this without forcing anyone to update if they'd rather stick with [Timbre](https://www.taoensso.com/timbre) or use an alternative like [μ/log](https://github.com/BrunoBonacci/mulog).  

#### Why structured logging?  

Traditional logging is **string oriented**. Program state is captured at the logging callsite, then generally **formatted into a message string** or serialized into something like EDN/JSON before hitting the rest of the logging pipeline (filters → middleware → handlers).  

Structured logging is **data oriented**. Program state is captured at the logging callsite, then retained _as-is_ through the rest of the logging pipeline (filters → middleware → handlers).  

The data-oriented approach means that:  

- No expensive formatting or serialization are needed.
- Exact data types and (nested) data structures are losslessly retained throughout the logging pipeline.  

The latter matters! It means that it's easy and cheap to build a rich pipeline that can do data-oriented filtering, analytics, aggregations, transformations, and final handling (e.g. your DB or log aggregator might itself support rich types).  

**Clojure loves plain data** and offers a rich set of data types, structures, and tools. Structured logging takes advantage of this. It's essentially the extension of the data-first principle to the way we instrument our programs.  

### A big Sente update  

[Sente](https://www.taoensso.com/sente) has a major new pre-release [out](https://github.com/taoensso/sente/releases/tag/v1.21.0-beta1).  

There's a lot of significant improvements in here. Some highlights include:  

- Added support for upcoming **high-speed binary serialization**  
- Added support for **compression**  
- A **smaller dependency**  
- Improved **reliability** under heavy load  
- Pluggable **logging** (via [Trove](https://www.taoensso.com/trove))  
- Many other small fixes and improvements  

### Tufte v3 final  

[Tufte](https://www.taoensso.com/tufte)'s official v3 release is [out](https://github.com/taoensso/tufte/releases/tag/v3.0.0).  

There's many big improvements, including many new features shared with [Telemere](https://www.taoensso.com/telemere), and new handlers for Telemere and Trove.  

### Other stuff  

Other misc releases include:  

- [Nippy v3.6.0](https://github.com/taoensso/nippy/releases/tag/v3.6.0)  
- [Telemere v1.0.1](https://github.com/taoensso/telemere/releases/tag/v1.0.1)  
- [Timbre v6.7.1](https://github.com/taoensso/timbre/releases/tag/v6.7.1)  
  
### Upcoming work  

Next couple months I expect to focus on:  

- Getting [Sente](https://www.taoensso.com/sente) v1.21 final released, ideally with an official binary serialization mode  
- Getting [Tempel](https://www.taoensso.com/tempel) v1 final released (sorry about the delays!)  
- Getting [http-kit](https://www.taoensso.com/http-kit) updated (lots of cool stuff pending there)  

Cheers everyone! :-)  





