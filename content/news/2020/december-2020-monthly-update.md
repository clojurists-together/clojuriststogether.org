---
title: "December 2020 Monthly Update"
date: 2021-02-28T08:30:00+08:00
author: Alyssa Parado
summary: Read more updates from Clj-kondo/babashka/sci, ClojisR, O’Doyle Rules, and Calva
---

Happy (belated) New Year everyone! Here are the updates from our [new projects](https://www.clojuriststogether.org/news/q4-2020-funding-announcement).


# Clj-kondo/babashka/sci

### **December 1-15**

### Babashka

- Babashka [news](https://github.com/borkdude/babashka/blob/master/doc/news.md) page
- Released [babashka/process](https://github.com/babashka/process) to clojars: https://clojars.org/babashka/process
- Released [babashka/babashka.curl](https://github.com/borkdude/babashka.curl) to clojars https://clojars.org/babashka/babashka.curl
- Held talk on [babashka and sci internals](https://youtu.be/pgNp4Lk3gf0)
- Made 3 week survey and made data [available](https://nl.surveymonkey.com/results/SM-8W8V36DZ7/)
- Moved most of README docs to [book.babashka.org](https://book.babashka.org/)
- Expose `get-classpath` and `split-classpath` fns in `babashka.classpath` [#670](https://github.com/borkdude/babashka/issues/670)
- Expose `add-deps` in `babashka.deps` [#677](https://github.com/borkdude/babashka/issues/677). See [docs](https://book.babashka.org/master.html#_add_deps).
- Expose `clojure` in `babashka.deps` [#678](https://github.com/borkdude/babashka/issues/678). See [docs](https://book.babashka.org/master.html#_clojure).
- Implement `--clojure` option to invoke a JVM clojure process similar to the official Clojure CLI.
- Upgrade to GraalVM 20.3.0 [#653](https://github.com/borkdude/babashka/issues/653)

### Sci

- Add syntax checks to sci binding macro [#458](https://github.com/borkdude/sci/issues/458)
- Add option to disable arity checks [#460](https://github.com/borkdude/sci/issues/460)

### Clj-kondo

- Documentation: a list of all available [linters](https://github.com/borkdude/clj-kondo/blob/master/doc/linters.md) [#936](https://github.com/borkdude/clj-kondo/issues/936)
- Lint protocol and interface implementations in `deftype` and `defrecord` [#140](https://github.com/borkdude/clj-kondo/issues/140)s
- Upgrade to GraalVM 20.3.0 [#1085](https://github.com/borkdude/clj-kondo/issues/1085)
- Fix analysis for cljs.core simple-benchmark [#1079](https://github.com/borkdude/clj-kondo/issues/1079)
- Support babashka.process $ macro syntax [#1089](https://github.com/borkdude/clj-kondo/issues/1089)
- Fix recur arity in doysync [#1081](https://github.com/borkdude/clj-kondo/issues/1081)
- Alias linter doesn't recognize (quote alias) form [#1074](https://github.com/borkdude/clj-kondo/issues/1074)
- Fix retries for refer :all when linting in parallel [#1068](https://github.com/borkdude/clj-kondo/issues/1068)
- Improve analyzing syntax of `amap` [#1069](https://github.com/borkdude/clj-kondo/issues/1069)
- Namespaced map in deps.edn causes false positive [#1093](https://github.com/borkdude/clj-kondo/issues/1093)
- Support ignore hints in `deps.edn` [#1094](https://github.com/borkdude/clj-kondo/issues/1094)
- Fix unsorted namespaces linter for nested libspecs [#1097](https://github.com/borkdude/clj-kondo/issues/1097)
- Fix recur arity in doysync [#1081](https://github.com/borkdude/clj-kondo/issues/1081)
- Released v2020.12.12

### Misc

- deps.clj 0.0.11 [released](https://github.com/borkdude/deps.clj/releases/tag/v0.0.11): catching up with clojure 1.10.1.763




### **December 16-31**

### Babashka

- Buddy pod https://github.com/babashka/pod-babashka-buddy
- Etaoin pod 0.0.1 release https://github.com/babashka/pod-babashka-etaoin
- Pod registry https://github.com/babashka/pod-registry
- Fswatcher pod https://github.com/babashka/pod-babashka-fswatcher
- Fix issue with unzipping nested directory [babashka/pod-registry#4](https://github.com/babashka/pod-registry/issues/4)
- Sqlite3 pod https://github.com/babashka/pod-babashka-sqlite3

### Sci

- Detect macro var as val at analysis time [#467](https://github.com/borkdude/sci/issues/467)
- Restructure namespace for #417-related refactoring [#468](https://github.com/borkdude/sci/issues/468)
- Optimization for `let` [#470](https://github.com/borkdude/sci/issues/470), [#478](https://github.com/borkdude/sci/issues/478)
- Optimization for `if` [#472](https://github.com/borkdude/sci/issues/472)
- Excluded clojure var in macro still gets resolved to in syntax quote [#466](https://github.com/borkdude/sci/issues/466)
- Optimization for ctx [#473](https://github.com/borkdude/sci/pull/473)
- Optimization for fns [#475](https://github.com/borkdude/sci/issues/475)
- Add `with-local-vars` [#383](https://github.com/borkdude/sci/issues/383)
- Release 0.2.0: https://github.com/borkdude/sci/blob/master/CHANGELOG.md#v020
- Optimization for vars that are inlined in clojure [#483](https://github.com/borkdude/sci/issues/483)
- Only add location metadata to seqs and symbols [#488](https://github.com/borkdude/sci/issues/488)
- Inline edamame opts / get rid of end-line and end-column [#490](https://github.com/borkdude/sci/issues/490)

### Clj-kondo

- Fix NPE in VSCode plugin [#12](https://github.com/borkdude/clj-kondo.lsp/issues/12)

### Misc

- Edamame (parser lib for sci): handle whitespace after read-cond splice [#71](https://github.com/borkdude/edamame/issues/71)
- Edamame: add `location?` predicate [#72](https://github.com/borkdude/edamame/issues/72)
- Edamame: fix for auto-resolved map and current namespace [#74](https://github.com/borkdude/edamame/issues/74)
- Report number of arguments used in `assoc` over a large body of source code https://gist.github.com/borkdude/e6f0b12f9352f3375e5f3277d2aba6c9 as investigation for https://clojure.atlassian.net/browse/CLJ-1656.
- Edamame: make end locations optional [#75](https://github.com/borkdude/edamame/issues/75)
- Depstar: fix for Windows [#57](https://github.com/seancorfield/depstar/issues/57)




# O’Doyle Rules

### **December 1-15**

I released version 0.5.0 of O'Doyle Rules with two new features that make it rule even more:

* An easy way to request all facts in a session via `(query-all session)`, mainly intended for serialization. You can parse them later and re-insert them into your session.
* A new block type called `:then-finally`, which lets you react to insertions *and* retractions that affect a rule's matches. This is vital for creating "derived facts" that accumulate other facts -- which is my equivalent to Clara's [accumulators](https://www.clara-rules.org/docs/accumulators/).

You can read more in the [release notes](https://github.com/oakes/odoyle-rules/releases/tag/0.5.0).

I also added several benchmarks to the repo. One of them is code extracted from my [dungeon crawler](https://github.com/oakes/play-cljc-examples/tree/master/dungeon-crawler) test game. Some of the benchmarks are also implemented with Clara and DataScript to compare performance. I will soon be putting a lot of focus on performance so these will help. GOTTA GO FAST, as Ricky Bobby once said.




### **December 16-31**

I released version 0.6.0 with a new feature that makes it possible for rules to build recursive data structures similar to what you'd get from "pull" queries in DataScript or Datomic. See the [release notes](https://github.com/oakes/odoyle-rules/releases/tag/0.6.0) for more. Using O'Doyle in this way is somewhat advanced and hard to explain briefly, so to go along with the release I wrote a more in-depth explanation: [Using O'Doyle Rules as a poor man's DataScript](https://github.com/oakes/odoyle-rules/blob/master/bench-src/todos/README.md).

Next month will be focused on performance. There's a lot of low-hanging fruit from the RETE literature that I still need to steal, such as "node sharing". When they make a movie about this, there'll be a scene where my character is deep in the stacks of an old library, dusting off CS papers and scrolling through microfilm to find the lost gems of early rules engine research.

It won't be true, but it'll be a cool scene.




# Calva

### **December 1-15**

This first couple of weeks involved information gathering on the Language Server Protocol in general and clojure-lsp, specifically, and how to integrate it with VS Code and Calva. Fortunately, the initial integration had been started on a branch by Kevin Ahlbrecht and Peter Strömberg.

Below is a list of work that has been done in the last couple of weeks. Some features in clojure-lsp have been disabled in favor of Calva's functionality, at least for now, because Calva's similar functionality is either more mature at this time, or there is work required to make the two feature sets work well together. The initial goal is to publish a release with clojure-lsp early, disabling anything that might cause issues for now, while providing features Calva was lacking. The disabled features may receive work later in order to integrate them.

* Figured out how to trace LSP messages and added a Calva setting for enabling this.
* Submitted a [PR to Microsoft's language-server-protocol-inspector](https://github.com/microsoft/language-server-protocol-inspector/pull/59) to fix an issue with running this project locally. The inspector helps more easily sort through LSP messages.
* Made a change to clojure-lsp to [make references code lens work](https://github.com/snoe/clojure-lsp/pull/197)
* Added a command to Calva to make references code lens work. When references code lenses are clicked, a peek definitions window opens.
* Added a Calva setting to enable references code lens
* Disabled Calva's Clojure definition provider in favor of clojure-lsp's
* Disabled clojure-lsp's diagnostics in favor of clj-kondo's, which we bundle with Calva
* Disabled clojure-lsp's hover provider in favor of Calva's
* Disabled clojure-lsp's completion provider in favor of Calva's
* Fixed an issue in clojure-lsp to [prevent negative numbers in ranges](https://github.com/snoe/clojure-lsp/pull/200)

A development version (vsix) was sent to the #calva channel of the Clojurians slack for testing of the above changes, and I plan to release the changes soon and then continue work on some things that have been disabled, and/or on [extra capabilities](https://github.com/snoe/clojure-lsp#extra-capabilities) clojure-lsp can provide.

### Version 2.0.134 Was Released

This includes a couple of small fixes for things I noticed while developing.

- Fix: [Live share jackout error](https://github.com/BetterThanTomorrow/calva/issues/856)
- Fix: [Cannot read property 'document' of undefined](https://github.com/BetterThanTomorrow/calva/issues/846)




### **December 16-31**

Calva's clojure-lsp integration [has been released](https://clojureverse.org/t/calva-gets-static-analysis-features-via-clojure-lsp/6939) as of version 2.0.137. See the ClojureVerse post for details.

### Clojure-lsp Work

Contributing to clojure-lsp is important to improving Calva, and I expect to be doing more of this in the future. Below are changes I made or helped with.

- [Fix jar file URIs to be compatible with both Windows and Unix](https://github.com/clojure-lsp/clojure-lsp/pull/226)
- [Add initialization options that allow disabling formatting providers](https://github.com/clojure-lsp/clojure-lsp/pull/207)
- [Fix go id for documentHighlight](https://github.com/clojure-lsp/clojure-lsp/pull/212)
- [Fix duplicated usages/references for cljc files](https://github.com/clojure-lsp/clojure-lsp/pull/211)

### Calva Work

Peter Strömberg and myself have been quite active lately, and Eric Dallo has been a tremendous help with clojure-lsp fixes, PR reviews, ideas, etc. Peter added refactoring commands that utilize clojure-lsp's refactorings as well as updated the docs to [explain clojure-lsp in Calva](https://calva.io/clojure-lsp/) and the [refactoring commands](https://calva.io/refactoring/). Below is a list of recent Calva changes.

#### 2.0.135

- [Binding keys to REPL functions, passing the namespace and cursor line (Notespace integration)](https://github.com/BetterThanTomorrow/calva/issues/863)
- [Make REPL prompt submit if the cursor is after the top level form](https://github.com/BetterThanTomorrow/calva/issues/875)
- [Only print stacktrace on demand](https://github.com/BetterThanTomorrow/calva/issues/878)

#### 2.0.136

- Fix: [Jack-in/Connect prompts sometimes not showing on Windows](https://github.com/BetterThanTomorrow/calva/issues/885)

#### 2.0.137
- [Bring in clojure-lsp](https://github.com/BetterThanTomorrow/calva/pull/572)

#### 2.0.138

- [Bring in refactorings we get access to via clojure-lsp](https://github.com/BetterThanTomorrow/calva/issues/890)
- [Add ”clojure-lsp starting” progress indicator](https://github.com/BetterThanTomorrow/calva/issues/892)
- [Fix step into local dep with debugger](https://github.com/BetterThanTomorrow/calva/issues/893)

#### 2.0.139

- [Use Pseudo Terminal instead of Task for Jack-in](https://github.com/BetterThanTomorrow/calva/pull/654)
- [Prefer cider-nrepl symbol definitions over clojure-lsp](https://github.com/BetterThanTomorrow/calva/issues/897)
- [Enable clojure-lsp completion items when no nrepl connection](https://github.com/BetterThanTomorrow/calva/pull/898)

#### 2.0.140

- [Make Jack-in dependency versions configurable (and bump 'em all with default settings)](https://github.com/BetterThanTomorrow/calva/pull/899)

#### 2.0.141

- Update clojure-lsp to include [jar dependency navigation fix for Windows](https://github.com/clojure-lsp/clojure-lsp/issues/223)
- Fix: [clojure-lsp refactorings not working on Windows](https://github.com/BetterThanTomorrow/calva/issues/911)
- [Remove default key binding for toggling Calva key bindings](https://github.com/BetterThanTomorrow/calva/issues/815)
- [Make load-file available in the output window](https://github.com/BetterThanTomorrow/calva/issues/904)
- [Make the ns in the repl prompt a peekable symbol](https://github.com/BetterThanTomorrow/calva/issues/904)
