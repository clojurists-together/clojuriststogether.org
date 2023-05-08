---
title: "2023 Long Term Projects: March & April Updates"
date: 2023-05-10T08:30:00+08:00
summary: Reports from our 10 developers working on 2023 long term projects
author: Kathy Davis
draft: True
---
There is a lot to catch up on! Check out the March and April 2023 updates from Bozhidar Batsov, Michiel Borkent, Sean Corfield, Eric Dallo, Christophe Grand, Thomas Heller, Nikita Prokopov, Tommi Reiman, Peter Stromberg,
and Peter Taoussanis.  
  

## Bozhidar Batsov  

### CIDER  

- Released CIDER 1.7. 
- Deprecate `cider-*-global-options` configuration options.
- Document how to load REPL utils in REPL buffers. 
- Disable the problematic `paredit-RET` keybinding automatically.
- Some work-in-progress work towards adding "debug-on-exception" functionality.
- Discussed how to support `add-lib` in CIDER it's bundled with Clojure.  

### inf-clojure  
- Updated CI pipeline to match that of CIDER. 
- Added the ability to start socket REPLs straight from Emacs (see `inf-clojure-socket-repl`).
- That's similar to `cider-jack-in`  

### clojure-ts-mode  
- Improve the CI (made it similar to that of `clojure-mode`).
- Various documentation and code consistency updates.  

### clojure-mode  
- Font-lock `defproject` as a keyword.  

### nREPL  
- Make the test suite compatible with Windows.  
- Various small Windows-related fixes (e.g. now Unix sockets should work on recent versions of Windows).
- Replace `Makefile` with `bb` for portability reasons.<br>

---

## Michiel Borkent  

### Sponsors  
But first off, I'd like to thank all the sponsors and contributors that make this work possible! Top sponsors:  
* [Clojurists Together](https://clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Toyokumo](https://toyokumo.co.jp/)
* [Cognitect](https://www.cognitect.com/)
* [Kepler16](https://kepler16.com/)
* [Adgoji](https://www.adgoji.com/)  

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [[Babaska](https://opencollective.com/babashka)](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)  

If you're used to sponsoring through some other means which isn't listed above, please get in touch.  On to the projects that I've been working on!  

### March Report  
 (Published 07 April 2023)  

### [cherry](https://github.com/squint-cljs/cherry)  
Experimental ClojureScript to ES6 module compiler  
This month I've been preparing cherry as a compiler that you can embed in your existing CLJS / shadow-cljs applications. This makes cherry an additional alternative to [SCI](https://github.com/babashka/sci) and self-hosted CLJS.  

Read about embedding cherry into your application [here](https://github.com/squint-cljs/cherry/blob/main/doc/embed.md).  
I've been working on several PRs to include cherry as an additional evaluator in:  
* [clerk](https://github.com/nextjournal/clerk/pull/446)
* [malli](https://github.com/metosin/malli/pull/888)
* One project that is still unpublished but will be coming soon!  

### [scimacs](https://github.com/jackrusher/scimacs)   
The Small Clojure Interpreter (SCI) integrated with emacs as a loadable module.  
This is a new project by Jack Rusher and I've helped him with the SCI integration.  

### [clj2el](https://borkdude.github.io/clj2el/) 
Transpile clojure to elisp. A brand new project to transpile Clojure to Elisp. It might be of value for those who know Clojure better than Elisp and want to have something to get started. It's far from complete. Try it in the playground [here](https://borkdude.github.io/clj2el/).  

### [deflet](https://github.com/borkdude/deflet)  
Make let-expressions REPL-friendly!  
The `deflet` macro lets your write inline-def expressions, while expanding those to regular let expressions, giving you the benefits of REPL-driven development without polluting production code with top level vars.

### [babashka](https://github.com/babashka/babashka)   
Native, fast starting Clojure interpreter for scripting  
New release: 1.3.175 (2023-03-18)), 1.3.176 (2023-03-18)  
**Highlights:**  
* The `clojure.core.async/go` macro now uses virtual threads.
* Many small fixes and upgrades.
* See the complete [CHANGELOG](https://github.com/babashka/babashka/blob/master/CHANGELOG.md).

### [babashka.json](https://github.com/babashka/json)    
This is a JSON abstraction library that you can include in babashka and JVM projects while also including your own favorite JSON implementation. The idea is that babashka libraries can include this, while JVM projects don't have to switch their JSON implementation to cheshire.core.  

### Babashka compatibility in external libs  
I worked together with the maintainers of the following libraries to make them compatible with babashka:  
* [martian](https://github.com/oliyh/martian/commit/f0f7679364f58eb4ef558e9ad2340274b9742542): HTTP abstraction library for Clojure/script, supporting OpenAPI, Swagger, Schema, re-frame and more  

### [clj-kondo](https://github.com/clj-kondo/clj-kondo)   
Static analyzer and linter for Clojure code that sparks joy

New release: 2023.03.17      
**Some highlights:** 
* [#2010](https://github.com/clj-kondo/clj-kondo/issues/2010): Support inline macro configuration. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md#inline-macro-configuration)
* [#2010](https://github.com/clj-kondo/clj-kondo/issues/2010): Short syntax to disable linters: `{:ignore [:unresolved-symbol]}` or `{:ignore true}`, valid in ns-metadata, `:config-in-ns`, `:config-in-call`
* [#2009](https://github.com/clj-kondo/clj-kondo/issues/2009): new `:var-same-name-except-case` linter: warn when vars have names that differ only in case (important for AOT compilation and case-insensitive filesystems)
* [#1269](https://github.com/clj-kondo/clj-kondo/issues/1269): warn on `:jvm-opts` in top level of `deps.edn`
* [#2003](https://github.com/clj-kondo/clj-kondo/issues/2003): detect invalid arity call for function passed to `update`, `update-in`, `swap!`, `swap-vals!`, `send`, `send-off`, and `send-via`

[Check the changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details.  

### [SCI](https://github.com/babashka/sci)  
Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs  
New release: 0.7.39 (2023-03-07)  
* [#874](https://github.com/babashka/sci/issues/874): Keyword arguments as map support for CLJS  
See [changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md) for more.  

### Contributions to other projects  
* [clojurescript](https://github.com/clojure/clojurescript):
    * [PR 202](https://github.com/clojure/clojurescript/pull/202): a `macroexpand` fix
    * [PR 203](https://github.com/clojure/clojurescript/pull/203): a symbol optimization fix
* [clojure-lsp](https://github.com/clojure-lsp/clojure-lsp/commit/b7111ef6b5f9c1d93b2272683ff4b6eb58b240c9): fix reflection issue on JDK 19
* [http-kit](https://github.com/http-kit/http-kit/commit/e2d71039ea2617e789a08606a0c404c41367dca8): add native-image tests
* [promesa](https://github.com/funcool/promesa/commit/18fea52fd99b24a65927907eff6879b970c71dfd): fix GraalVM native-image compilation
* [etaoin](https://github.com/clj-commons/etaoin/commit/706f342216af69d23de671803ac67c3e1f515941): JDK 19 + babashka issue  

### Brief mentions    
The following projects also got updates, mostly in the form of maintenance and performance improvements. This post would get too long if I had to go into detail about them, so I'll briefly mention them in random order:  
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
* [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
* [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
* [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
* [Nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
* [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
* [http-client](https://github.com/babashka/http-client): Babashka's http-client  

### Other projects   
These are (some of the) other projects I'm involved with but little to no activity happened in the past month.   
* [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
* [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
* [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
* [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs
* [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
* [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
* [instaparse-bb](https://github.com/babashka/instaparse-bb)
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
* [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
* [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))    
Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).  _Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)_

### April 2023 Report  
(Published: 30 April, 2023)  

In this post I'll give updates about open source I worked on during April 2023. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### [Babashka-conf](https://babashka.org/conf/)   
Babashka-conf is happening June 10th in Berlin. Save the date and/or submit your babashka/clojure-related talk or workshop in the CfP!    
The following projects had updates in the last month. Note that only highlights are mentioned and not a full overview of all changes. See the project's changelogs for all changes.  
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * [#1196](https://github.com/clj-kondo/clj-kondo/issues/1196): show language context in `.cljc` files with `:output {:langs true}`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md#show-language-context-in-cljc-files).
    * [#2030](https://github.com/clj-kondo/clj-kondo/issues/2030): Add a new `:discouraged-tag` linter for discouraged tag literals. See the [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#discouraged-tag).
    * [#2058](https://github.com/clj-kondo/clj-kondo/issues/2058): warn about `#()` and `#""` in `.edn files
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting
    * Released 1.3.177 - 1.3.179
    * [#1541](https://github.com/babashka/babashka/issues/1541): respect `bb.edn` adjacent to invoked file. This eases writing system-global scripts from projects without using bbin. See [docs](https://book.babashka.org/#_script_adjacent_bb_edn).
    * See the complete [CHANGELOG](https://github.com/babashka/babashka/blob/master/CHANGELOG.md).
* [cherry](https://github.com/squint-cljs/cherry) Experimental ClojureScript to ES6 module compiler
    * Improve `cherry.embed`
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Better error message when trying to `recur` across `try`
    * Improvement for error locations in multiple threads
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
    * Improvements for reading namespaced maps
* [babashka.book](https://github.com/babashka/book): Babashka manual
    * Several corrections
    * Dynamic `:exec-args`
    * Script-adjacent `bb.edn` docs
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Support `--no-option` and parse as `{:option false}`
    * Support grouped aliase like `-ome` as `{:o true, :m true, :e true}`
* [http-client](https://github.com/babashka/http-client): Babashka's http-client
    * Support `java.net.URI` directly in `:uri` option
    * Better `:ssl-config` option support
    * Better `:proxy` option support
* [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
    * Better error message when connection is not a string
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Fix `:local/root` deps in `nbb.edn` when not invoking from current working directory
    * Fix regression, `cljs.core/PersistentQueue.EMPTY` no longer working
* [instaparse-bb](https://github.com/babashka/instaparse-bb)
    * Add transform function
* [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
    * Add option to elide commas
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Catch up with clojure CLI changes  

### Contributions to other projects   
* [clojurescript](https://github.com/clojure/clojurescript):
    * [PR 202](https://github.com/clojure/clojurescript/pull/202): a `macroexpand` fix
    * [PR 203](https://github.com/clojure/clojurescript/pull/203): a symbol optimization fix
* [malli](https://github.com/metosin/malli/commit/cf918db28ff71a2f735f465f30f0bc1028ecd7d9): cherry integration
* [clerk](https://github.com/nextjournal/clerk/commit/cb079b14213185d27c5a2d1cc1e80943521a4fb5): cherry integration
* [clojure-lsp](https://github.com/clojure-lsp/clojure-lsp/commit/60d67cca59f0747e8b68802157afbe7f61440c7f): integrated a new clj-kondo feature: showing the languages in a CLJC context  

### Other projects    
These are (some of the) other projects I'm involved with but little to no activity happened in the past month.  
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
* [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
* [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
* [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
* [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
* [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
* [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
* [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs
* [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
* [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
* [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
* [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
* [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
* [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
* [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
* [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter  

Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).  _Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)<br>

---

### Sean Corfield  
(Published 30 April, 2023)
tags {["clojure" "clojure-doc.org" "open source" "tools.build" "community"]}  

In my [previous Long-Term Funding update]([https://corfield.org/blog/2023/02/28/long-term-funding-1/](https://corfield.org/blog/2023/02/28/long-term-funding-1/)) I said that I planned "to review and/or overhaul the Getting Started, Introduction, and Web Development sections, with a focus on the latter." (of the [clojure-doc.org](https://clojure-doc.org) website).  
I mostly achieved that goal but didn't get to the additional goal I set of writing a `tools.build` cookbook. I have sketched out the topics I hope to cover in that cookbook, however.  

How did the past two months go?  

### `clojure-doc.org`  
I updated [Getting Started with Clojure](https://clojure-doc.org/articles/tutorials/getting_started/) to talk about both Leiningen **and** the Clojure CLI and wrote a new [Getting Started with the Clojure CLI](https://clojure-doc.org/articles/tutorials/getting_started_cli/) page that also covers `tools.build` / `build.clj` and building uberjars. Both pages now have a new section highlighting **Interactive Development** (as opposed to "just" using a REPL).  

I reviewed the [Introduction to Clojure](https://clojure-doc.org/articles/tutorials/introduction/) and decided it was mostly sound, but added more examples, tweaked the formatting, and added references to the Clojure CLI.  

I reworked John Gabriele's excellent [Basic Web Development guide](https://clojure-doc.org/articles/tutorials/basic_web_development/) to use the Clojure CLI, updated all the library references, and reordered the sections to work bottom-up so that code could always be evaluated in the REPL. I didn't get as far as I wanted with the `tools.build` cookbook due to a combination of writer's block (and stress over my mother being taken to hospital unexpectedly for a fractured hip -- she's nearly 90, has osteoporosis, and her oxygen levels are too low/precarious for surgery at this point; she's home now but it's been a rough few weeks).  

### `deps-new`  
As the [Practical.li project templates](https://github.com/practicalli/project-templates) were being developed, John found a bug in the `template` project which I fixed and in turn I reviewed drafts of his [articles about creating project templates using `deps.new`](https://practical.li/blog/posts/create-deps-new-template-for-clojure-cli-projects/), both of which are now linked from the `deps-new` README and documentation.  

### `honeysql`  

During March/April, HoneySQL saw four new releases, which were mostly an even
split between improving documentation and expanding ANSI SQL support.  
Many of the questions I see about HoneySQL on Slack (and other places) suggest
deficiencies in the documentation so, while I try to answer those questions
directly on Slack, I also tend to create GitHub issues for them to see if I
can improve the documentation in those areas.  
Some of the ANSI SQL improvements including support for `INTERVAL`,
keyword arguments in function calls, nested `JOIN`, standardizing `TRUNCATE` syntax,
and `WITH ORDINALITY`. There were also bug fixes and documentation
improvements around `DO UPDATE SET` and a number of other constructs.  

I'd also like to give a special shout-out to [Eugene Pakhomov](https://github.com/p-himik)
who contributed three pull requests to the release that went out in early March.   

### `next.jdbc`  
`next.jdbc` also saw four releases, which provided a mix of bug fixes,
compatibility improvements (with `clojure.java.jdbc`, to ease migration),
documentation improvements, and a few enhancements.  

### `next.jdbc.xt`  
The [Juxt](https://www.juxt.pro/) team were present in force at
[Clojure/conj](https://2023.clojure-conj.org/) this year and announced
early access to [XTDB 2.0]([https://www.xtdb.com/blog/2x-early-access](https://www.xtdb.com/blog/2x-early-access)).  
I think this will be a very exciting release, with improved bitemporality
(including temporal joins and range scans), a new columnar architecture,
and -- the part that interests me the most -- a dynamic relation engine
that provides both a Datalog API **and** a SQL API.  
That inspired me to create a new project, offering experimental support
for XTDB 2.0 in `next.jdbc`: [`next.jdbc.xt`](https://github.com/seancorfield/next.jdbc.xt).
This allows you to treat an XTDB client "node" as a "connectable" so
you can call `execute!`, `execute-one!`, and `plan` on it (as well as the
full range of "friendly SQL functions").  

As XTDB 2.0 evolves, I plan to continue to enhance this new project to
hopefully support batch operations and perhaps full transaction support, if possible.  

### What's Next?  
In May/June, I hope to get the `tools.build` cookbook written and
to review/overhaul the Libraries pages (both authoring and the directory).<BR>

---

## Eric Dallo  

These 2 months I spent a considerable time improving java interop support in both clj-kondo and clojure-lsp, so LSP can understand it better and provide completion, definition, hover and other features in a close future as mentioned below.  

### [clojure-lsp](https://clojure-lsp.io/)  
The main highlights for this release are:  
- clojure-lsp feedback for Editor UI is faster (Emacs users you may notice that reducing `lsp-idle-delay` to something lower like `0.05`)
- We now have a new edn tree visualization from `documentSymbolfeature`, showing all keyords of a edn for faster navigation/visualization!
- Adds support for completion of Static java classes (for now only for classes from .class, not .java, check print), I'm working on improvements on clj-kondo and clojure-lsp to improve that even further being able to see docs of java methods and more, stay tuned! 

![](edn-tree.png)  

### 2023.04.19-12.43.29  
**General**
  - Fix reflection issue in JDK19 with `Thread/sleep` #1520  
  - Bump clj-kondo to `2023.04.15-20230418.173453-3`, fixing analysis inconsistencies with `schema.core`  
  - Ignore vars defined wrongly via config. #1510  
  - Add support for `:output {:langs true}` in clj-kondo config to show `.cljc` language contexts  

**Editor**  
  - Fix classpath issue message to properly ignore or retry after user input. #1500  
  - Decreate debounce time for calculating changes and diagnostics, improving UX in cost of performance, it doesn't seem to highly affect performance though.  
  - Add new setting to change diagnostics range type. #1530  
  - Spec compliance: avoid registering client capabilities if dynamic registration is not set. #1535  
  - Improve java support: using new `java-member-definitions` analysis, first feature is the allow completion of static members (fields and methods) for java classes.  
  - Show edn tree when in edn files via `documentSymbol` feature.  
  - Improve java db cache consistency + analysis performance.  

### [clj-kondo]([https://github.com/clj-kondo/clj-kondo/](https://github.com/clj-kondo/clj-kondo/))  
[#2503](https://github.com/clj-kondo/clj-kondo/pull/2053): parse java files via javaparser to produce java-member-definitions analysis
[#1983](https://github.com/clj-kondo/clj-kondo/issues/1983): add support for java member analysis, via new java-member-definitions bucket (@ericdallo).  

### ClojureConj 2023  
I gave a talk at ClojureConj about how to use clojure-lsp as a linter for multiple projects and how to manage everything in a single place, should be available soon in https://www.youtube.com/@ClojureTV/videos <br>  

---

## Christophe Grand  
(Published 4 May, 2023)  

### ClojureDart  
The period ended with the Conj and our ClojureDart talk was well received 🎉.
Still it's worth repeating the core message: ClojureDart is useable *righ now*!
You can use it to write mobile or desktop apps, CLIs and lambdas. Web even.
It doesn't mean our work is done but language-wise the only missing feature are multimethods.
Macros do work but sometimes need some annotations -- this will be fixed once we are self-hosted.
And there are so many places where we can still make interop nicer.  

Speaking of self-hosted, the reader rewrite (the previous one was accidentally quadratic 🙄) is coming to fruition now that the design is settled. Design took time because I wanted to have a chunked reader, so as to be able to process big files and REPL input.  

A lot of time was spent fixing bugs or making usability improvements (I'm looking forward to porting gen tests from CLJ/CLJS) and helping users on Slack. More often than not fixes/improvements were an answer to users experiences.  

### Fixes  
Chunked seqs on vectors (twice!), inconsistent hash values across platforms (web vs others), fixed cross-nses protocols which AOT compiled fine but failed on hot-reload, fixed bug with collisions on transient maps, fixed issue with comparator fns.  

### Improvements: 
* better error message on macro expansion failure, 
* update README Flutter example to use `cljd.flutter` (plain interop before) as we are confident with our design now and that the default experience (especially towards hot reloading works as expected)
* removed small inefficiencies in maps
* tear-off support: unlike in JS, in Dart `obj.meth` returns a function closing over `obj` as `this` --   like `obj.meth.bind(obj)` would do in JS. That's what they call "tear-offs" in Dart. We have added (or rather re-enabled) support for tear-offs in Dart. More work needs to be done for better type inference of the returned function.
* removed `nullWriter` usage which required Java 11 and caused bad first experiences for some users. So now Java 8 is enough.  

### New features  
* sorted colls are at parity with Clojure's: rseq, subseq, rsubseq support have been added.
* rseq on subvecs
* in `cljd.flutter`, `:watch` accepts an `:as` option for the common case of local state, e.g. `:watch [the-value (atom nil) :as the-atom]` . Previously the more involved `:managed [the-atom (atom nil) :dispose nil] :watch [the-value the-atom]` was required and non-obvious to many users. While we are on it `:dispose nil` (or `false`) is new too: previously to have the same effect (no disposal) you had to write hacks such as `:dispose comment`, `:dispose do`, `:dispose ->`...  

###Future work  

### ClojureDart  
* Finish the new chunked reader  
* We have found a path allowing a Flutter-only REPL could be hacked in a couple of days, is it worth the hack?  
* Look into porting Datascript and SCI to ClojureDart  
* New APIs to leverage our persistent data structures:  
** maps (hash and sorted) in ClojureDart are original implementations (not the same as CLJ/CLJS) -- hash maps could be seen as another refinement of the original, sorted maps constitute a novel implementation.  
** Sorted colls should be good enough for direct use by Datascript.  
** Both hash and sorted maps can support accelerated merge/diff/join/etc. operations.  
* Multimethods  
* Dart 3 support  
* `cljd` CLI written in `cljd` for easier project creation etc.  
* gen tests<br>  

---

## Thomas Heller  
(Published x, xxx, 2023)  

### shadow-cljs  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).  
Current shadow-cljs version: 2.23.3 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  

**Notable Updates:**  
- Tweaked [:js-provider :external](https://shadow-cljs.github.io/docs/UsersGuide.html#js-provider-external) to optionally output ESM code (JS `import`), which enables some tools such as webpack to tree shake npm dependencies properly.<BR>

---

## Nikita Prokopov    
Three big things happened in the last two months:  
- Clojure Sublimed 3.0 release with new pluggable REPL infrastructure and Socket Server REPL support  
- Proper accented input support in JWM on macOS  
- Majority of research for new state implementation and component DSL for Humble UI  

### [Humble UI]([https://github.com/HumbleUI/HumbleUI](https://github.com/HumbleUI/HumbleUI)):  
- Text fields now support accented input with ApplePressAndHoldEnabled on macOS  
- Basic paragraph impl (!)  
- Fixed linux dependencies #72  
- Setting/Debug toggle works across tools.namespace reloads  
- fixed label to apply all features instead of just last one  
- Draw inside vscroll uses child height instead of Integer/MAX_VALUE  
- Preliminary signals impl  
- Treemap example that scans a directory on your disk and displays what occupies the most space  
- Switched to Socket REPL  
- hash-p for easier debugging  

### [JWM]([https://github.com/HumbleUI/JWM](https://github.com/HumbleUI/JWM)):   
- macOS: Fixed missed trackpad clicks when tap to click is enabled. This has been annoying me and few other folks for quite a while, turned out you can’t trust AppKit API, but luckily can work around it.  
- macOS: Reworked text input system to support accented input with ApplePressAndHoldEnabled. Also quite a ride, at some point I had to dig into OpenJDK sources and use same proprietary APIs Apple built into macOS just for AWT (accented input was added much later than JDK).  
- Windows: Merged Window::bringToFront & ::isFront #266  

### [Clojure Sublimed]([https://github.com/tonsky/Clojure-Sublimed](https://github.com/tonsky/Clojure-Sublimed)):   
- Released 3.0.0 and some quick fixes (3.1.0) after that.  
- Documented my journey at [Adventures in REPL implementation](https://tonsky.me/blog/clojure-sublimed-3/)  

### [Sublime Executor]([https://github.com/tonsky/Sublime-Executor](https://github.com/tonsky/Sublime-Executor)):   
- Convenience: executor_repeat_last will now automatically stop current process if present. Stop + start again now is a single command! 
- Execute any shell command #4  [Uberdeps](https://github.com/tonsky/uberdeps):  
- Fixed merging data_readers.cljc with reader conditionals #51

### Blogging:  
- [Adventures in REPL implementation](https://tonsky.me/blog/clojure-sublimed-3/). It even inspired some people to implement their own REPLs!  
- [Humble Chronicles: State Management](https://tonsky.me/blog/humble-state/). Great feedback on that one, with lots more to learn!  <br>  

---

## Tommi Reiman  
(Published 03-04/2023)  

### Malli  
* pushed out 3 patch and 1 minor releases (0.10.2, 0.10.3, 0.10.4 and 0.11.0) with lot's of stuff in

### Reitit  
* mostly reviewing and helping with upcoming [OpenAPI3](https://github.com/metosin/reitit/blob/master/doc/ring/openapi.md) release, releasing it next month

### New stuff  
* worked on `metosin/viesti` and the attached state machine abstraction, something have used in project, planning to push as open source.  
* created `metosin/ctrl-merge`, a fork of `weavejester/meta-merge` with breaking changes needed to simplify reitit interanls. Rationale on fork [here](https://github.com/weavejester/meta-merge/issues/11).  

### Malli CHANGELOG  

### 0.11.0 (2023-04-12)  
* BREAKING: remove map syntax: `mu/from-map-syntax`, `mu/to-map-syntax`. Note that AST syntax and lite syntax remain unchanged.  
* BREAKING: walking a `:schema` with an `id` no longer passes `[id]` instead of `children` to the walker function [#884 (https://github.com/metosin/malli/issues/884)  
* Support converting recursive malli schemas to json-schema [#464](https://github.com/metosin/malli/issues/464) [#868](https://github.com/metosin/malli/issues/868)  
* Add cherry as alternative CLJS evaluator [#888](https://github.com/metosin/malli/pull/888)  
* Replace `goog/mixin` with `Object.assign` [#890](https://github.com/metosin/malli/pull/890)  
* Simplify uuid regex for accept non-standard and zero uuids [#889](https://github.com/metosin/malli/pull/889)  
* Fix clj-doc API import [#887](https://github.com/metosin/malli/pull/887)  

### 0.10.4 (2023-03-19)  
* FIX `malli.swagger` ns, broken test on reitit.  

### 0.10.3 (2023-03-18)  
* Add support for default branch `::m/default` for `:map` schema
  [#871](https://github.com/metosin/malli/pull/871), see [docs](README.md#map-with-default-schemas).

```clojure

(m/validate

 [:map

  [:x :int]

  [:y :int]

  [::m/default [:map-of :string :string]]]

 {:x 1, :y 2, "kikka" "kukka"})

; => true

```

* `mt/strip-extra-keys-transformer` works with `:map-of`.

```clojure

(m/decode

 [:map-of :int :int]

 {1 1, 2 "2", "3" 3, "4" "4"}

 (mt/strip-extra-keys-transformer))

; => {1 1}

```

* `m/default-schema` to pull the `::m/default` schema from entry schemas  
* `m/explicit-keys` to get a vector of explicit keys from entry schemas (no `::m/default`)  
* Simplify content-dependent schema creation with `m/-simple-schema` and `m/-collection-schema` via new 3-arity `:compile` function of type `children properties options -> props`. Old 2-arity top-level callback function is `m/deprecated!` and support for it will be removed in future versions. [#866](https://github.com/metosin/malli/pull/866)  
* FIX Repeated calls to `malli.util/assoc-in` referencing non-existing maps fail [#874](https://github.com/metosin/malli/issues/874)  
* Updated dependencies:  
```clojure

borkdude/edamame 1.1.17 -> 1.3.20

```

### 0.10.2 (2023-03-05)  
* Implement `malli.experimental.time` schemas for clojurescript using js-joda [#853](https://github.com/metosin/malli/pull/853)  
* Allow instrumenting external functions [#841](https://github.com/metosin/malli/pull/841)  
* Add clj-kondo support for cljs function schemas [#833](https://github.com/metosin/malli/pull/833)  
* Turn on instrumentation for `mx/defn` with `:malli/always` meta [#825](https://github.com/metosin/malli/pull/825)  
* Support type-properties in `m/-map-schema`, `m/-map-of-schema` and `m/-tuple-schema` [#856](https://github.com/metosin/malli/pull/856)  
* FIX: properly compose interceptors in :map-of json-transformer [#849](https://github.com/metosin/malli/pull/849)  
* FIX: error paths for `:multi` schemas when value is not a map [#845](https://github.com/metosin/malli/pull/845)  
* FIX: Malli generates `:nilable/any` which is not a valid type in clj-kondo [#821](https://github.com/metosin/malli/issues/821)  
* FIX: `mi/collect!` without args doesn't work properly [#834](https://github.com/metosin/malli/issues/834)  
* Updated dependencies:

```clojure

mvxcvi/mvxcvi 2.0.0 -> 2.1.0

borkdude/edamame 1.0.0 -> 1.1.17

```

### Something else  
Retuning from my first Conj, really enjoyed the trip!

![conj]([https://user-images.githubusercontent.com/567532/235355537-22aa71c6-1d3f-4660-9458-0686bba0156b.jpg](https://user-images.githubusercontent.com/567532/235355537-22aa71c6-1d3f-4660-9458-0686bba0156b.jpg))  <br>

---

## Peter Stromberg  
(Published xx xx 2023)  

It's fantastic being sponsored to work on something I love to work with, for people I love to work for. These two month I have been even more user feedback driven than usual. Letting the conversations with users inform me where Calva could help them better. I have then quite immediately addressed the things I think I understand how to address.  

With other things it can take quite some time to understand what should be done. The idea for a solution needs to present itself, but some times the idea is very shy! This has been the case with REPL connection automation. It's an area where Calva is strong, yet it hasn't been possible to bring it to zero interaction, and some users really want that. Now, finally, Calva can quite easily be configured for this. While at it, Calva REPL start and connection (a.k.a. Jack-in) is now available for many more projects, even with quite special setup requirements. The solution was to let the user provide their own scripts and Calva will call the script providing environment and command line arguments to control that the REPL is started in a shape that Calva needs it to be. It's quite obvious, but it took several years to figure it out. 😄  

A common mistake in monorepos was to use the Calva command for copying the Jack-in command line for a particular project and then run this command line in the wrong monorepo directory. Note the past tense there. Now the copied command can be run from any directory on the computer. Further, the problems on some OS:s with Calva Jack-in zombies walking around after a REPL session are now much less pronounced.  

Another thing that talking to users make obvious is that a lot of Calva's functionality is something of a secret. It is satisfying to be able to answer: ”How do I do X?” with: ”There's a settings Y”, or ”Use the command Z”. Though, this often means that Calva is making Y and Z hard to find. We've now been able to focus a bit on this and to use feedback from users to surface important REPL functionality better.  

Calva Notebooks got some nice attention from [Kira McLean at The Conj](https://www.youtube.com/watch?v=xEvkT9YeBQU). As great as the Notebooks are for some users, a bug in VS Code LiveShare has made the Notebook feature a blocker for using Calva in LiveShare sessions. Stefan van den Oord have been maintaining a build of Calva with Notebooks disabled, but many users probably have had no idea about this. We finally found a more convenient workaround, where we can ship the Notebook configuration in a separate extension (named Calva Spritz) that is bundled with Calva. This extension can easily be disabled during LiveShare sessions.   

As a side effect to the LiveShare and Notebooks problem workaround, we also realized that the same mechanism can be used to make Joyride reach the parts of the VS Code API we thought was denied to Joyride scripts. Users can [provide Joyride with a Sidecar](https://blog.agical.se/en/posts/extend-vs-code-in-user-space--without-invented-restrictions/) extension manifest, and this way control all of VS Code with ClojureScript, in user space.  

** Some more things we got done:  
- Add commands for copying HTML code as Hiccup, and also for pasting HTML code as Hiccup.  
- Provide access to clojure-lsp commands from VS Code keyboard shortcuts (and from Joyride).  
- Add some more Rich Comment Form evaluation convenience.  
- Fix many issues with Calva's Clojure indenter, and also a few regarding the formatter.   

During the past months both Brandon Ringe and I have gotten increasingly fed up with a family of problems stemming from the limitations posed by using a regular file for REPL output, and other output, and also for REPL input. This is a bit ironic because with the [Clojurists Together Summer of Bugs 2020 sponsoring](https://www.clojuriststogether.org/news/summer-of-bugs-update-2020/#calva), I used a lot of the time to get rid of the problematic Webview solution for REPL Window that Calva had back then. Misunderstand this correctly (as we say in Sweden), the regular file solution has served Calva very well, and saved us from the impossible task of fixing the problems with the old Webview. But the limitations are getting too obvious, and now replacing the regular file REPL Window with one based on a Webview is a hot candidate for our time spent the coming two months... <br>

---

## Peter Taoussanis  

**Mar/Apr 2023 updates** for [Peter Taoussanis](https://www.taoensso.com/)
More details now also at [taoensso.com/clojure/2023](https://www.taoensso.com/clojure/2023) 👍  

### http-kit  
[http-kit v2.7.0-beta2](https://github.com/http-kit/http-kit/releases/tag/v2.7.0-beta2) has been released 🎉
This is the first major http-kit release since June 2022, and includes work from 15 contributors.  

Improvements include:  
- Several important **stability fixes**  
- Client+server support for **Unix Domain Sockets**  
- **SNI client** is now enabled by default on Java 8+  
- A new [wiki](https://github.com/http-kit/http-kit/wiki) for community documentation  

See the [release notes](https://github.com/http-kit/http-kit/releases/tag/v2.7.0-beta2) for more info.  

### Tempel  
One of my major open-source goals for this year is the release of a new **data security framework**, currently [planned](https://www.taoensso.com/clojure/2023) for August. This will be the first major all-new Clojure work I've put out in over 7 years, and has been my main focus so far this year.  
Will share more details closer to release, but as a quick teaser: the high-level goal is to make it easy for non-experts to quickly integrate solid data encryption into a variety of types of apps. The work is being grown from an expansion of techniques I've used in my own apps, and heavily optimised for ease-of-use.  

### More beginner-focused documentation   
Another of my goals for this year (and moving forward) is to put more **emphasis on documentation quality**, and on providing a **smoother experience for beginners**.  
As part of that work, I've started rolling out **GitHub wikis** for community documentation on my various [projects](https://www.taoensso.com/clojure).  

I'll be seeding these myself over time, but ultimately my hope is to make it easier and more sustainable for each project's own community to contribute documentation and tips. Tempel (mentioned above) will be my first release to be built from the start with this wiki-focused documentation concept in mind.  

### What's next  
The current [plan](https://www.taoensso.com/clojure/2023):  
- A major [Sente](https://github.com/ptaoussanis/sente) release in May.  
- A major [Carmine](https://github.com/ptaoussanis/carmine) release in June.  
- The first Tempel release in August.  
- Additional Carmine work through EoY.  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](https://github.com/sponsors/ptaoussanis#sponsors) of my open-source work 🙏 

