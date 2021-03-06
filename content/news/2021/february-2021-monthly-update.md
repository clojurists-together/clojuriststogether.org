---
title: "February 2021 Monthly Update"
date: 2021-03-05T09:30:00+08:00
author: Alyssa Parado
summary: Read more updates from Clj-kondo/babashka/sci, ClojisR, O’Doyle Rules, and Calva
draft: true
---

Here's the final updates from the projects in February. This is the 2nd month of ClojisR. They will have their final update next month.

# Clj-kondo/babashka/sci

### **February 1-14**

I spent some time improving Calva's publishing process to automate the steps we were doing manually before, like updating the changelog and some git tasks. This allows us to spend a bit less time publishing new versions, and makes it less error-prone.

I also added a command for copying the jack-in command to the clipboard, so users that want to start a repl themselves but still have Calva's required repl dependencies can do so more easily.

I've started experimenting with using ClojureScript for more of Calva's code base by porting the clojure-lsp integration over to ClojureScript. In doing so I've come across some issues, which Thomas Heller and others are assisting with. I think using ClojureScript more going forward, incrementally translating existing features from Typescript and adding new features in ClojureScript, will improve Calva by way of all known benefits of Clojure (smaller code base, fewer bugs, repl-driven development, etc.) as well as by potentially increasing the number of contributors to Calva.

Some other changes:

* Fixed the load file command for Windows, on which it was not loading changes since the last file save
* Updated the clojure-lsp version used by Calva to fix an issue with completions and an issue with when the clj-kondo config is respected. These updates also brought in other new features of clojure-lsp.

## Recent Calva Changes

### [2.0.171] - 2021-02-10
- Update clojure-lsp to version 2021.02.09-18.28.06 (Fix: [Auto completion does not work in clojure-lsp only mode (no repl connection)](https://github.com/BetterThanTomorrow/calva/issues/996#issuecomment-776148282))
- Update clojure-lsp to version 2021.02.10-03.01.19 (Fix: [Project clj-kondo config file not being considered](https://github.com/BetterThanTomorrow/calva/issues/1026))

### [2.0.170] - 2021-02-09
- [Paredit drag backward/forward should drag bindings as pairs](https://github.com/BetterThanTomorrow/calva/issues/529)

### [2.0.169] - 2021-02-09
- Update clojure-lsp to version 2021.02.07-22.51.26 (fix previous attempt)

### [2.0.168] - 2021-02-08
- Update clojure-lsp to version 2021.02.07-22.51.26

### [2.0.164] - 2021-02-06
- Really fix: [Demo gifs 404 on VisualStudio Marketplace](https://github.com/BetterThanTomorrow/calva/issues/1018)

### [2.0.163] - 2021-02-06
- Fix: [Demo gifs 404 on VisualStudio Marketplace](https://github.com/BetterThanTomorrow/calva/issues/1018)

### [2.0.162] - 2021-02-06
- Fix for fix of: [Fix Paredit raise sexpr doesn't work when cursor is behind the current form](https://github.com/BetterThanTomorrow/calva/issues/1016)

### [2.0.161] - 2021-02-05
- [Automate more of the release process and document it (including rationale)](https://github.com/BetterThanTomorrow/calva/issues/860)

### [2.0.160] - 2021-02-05
- [Upgrade clojure-lsp to 2021.02.05-03.05.34](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2021.02.05-03.05.34)
- [Fix Paredit raise sexpr doesn't work when cursor is behind the current form](https://github.com/BetterThanTomorrow/calva/issues/1016)

### [2.0.159] - 2021-02-05
- [Enable keyboard shortcuts for custom REPL commands](https://github.com/BetterThanTomorrow/calva/issues/1011)
- [Add commands for tapping current and top-level forms](https://github.com/BetterThanTomorrow/calva/issues/1008)

### [2.0.158] - 2021-02-03
- [Add setting to use only static parts of Calva](https://github.com/BetterThanTomorrow/calva/issues/1005)
- Fix: [Load file command not loading changes since last file save on Windows](https://github.com/BetterThanTomorrow/calva/issues/975)
- Update clojure-lsp to 2021.02.02-14.02.23

### [2.0.157] - 2021-02-01
- [Add command for copying jack-in command to clipboard](https://github.com/BetterThanTomorrow/calva/pull/995)
- [Change default shortcuts for Paredit forward/backward sexp, expand/shrink selection, and for slurping and barfing](https://github.com/BetterThanTomorrow/calva/issues/950)
- [Add Custom Commands variables for current form and more](https://github.com/BetterThanTomorrow/calva/issues/986)
- Fix: [Jack-in fails to launch deps.edn projects for some Windows users](https://github.com/BetterThanTomorrow/calva/issues/1000)





### **February 15-28**

I've continued exploring our options for using more ClojureScript in Calva, that interops with the Typescript we already have. I translated the clojure-lsp integration code from Typescript to ClojureScript. While what I was doing worked in dev, I realized there was an issue caused by the way we bundle code for releases. There's a bit more for me to try, but I think we can reach a solution that works in a way that's satisfactory.

Once I get past the above, I plan to make Calva download the clojure-lsp native binary and use that, instead of packaging the clojure-lsp jar with Calva. The native binaries come with improved memory usage and startup time of clojure-lsp.

Also, thanks to a contributor, Calva now has an option to disable diagnostics from clojure-lsp.

## Recent Calva Changes

### [2.0.176] - 2021-02-24
- Revert switch to cljs for lsp, until [the issue with released cljs/js interop](https://github.com/BetterThanTomorrow/calva/issues/1044) has been fixed

### [2.0.174] - 2021-02-24
- [Translate clojure-lsp integration to cljs](https://github.com/BetterThanTomorrow/calva/issues/1025)

### [2.0.173] - 2021-02-21
- Fix [Connect ”not in project” glitches](https://github.com/BetterThanTomorrow/calva/issues/814)
- [Add a ”Start Standalone REPL” commands](https://github.com/BetterThanTomorrow/calva/issues/1003)
- [Add a configuration option to disable diagnostics](https://github.com/BetterThanTomorrow/calva/pull/981)





# O’Doyle Rules

### **February 1-14**

I released version 0.7.0 which smooths down something that I think is the sharpest edge of O'Doyle: infinite loops. They are easy to create, and before this release, all you were greeted with was a long `StackOverflowException` with no useful info whatsoever.

In this release, O'Doyle detects these infinite loops and tells you exactly what rule(s) are being called in that loop. See [the release notes](https://github.com/oakes/odoyle-rules/releases/tag/0.7.0) for examples of what these error messages look like now. They're so beautiful you might start making infinite loops on purpose just to see them.

I also recorded a talk for the Bay Area Clojure meetup on O'Doyle, which will now serve as a nice intro to the library for people. You can see it here: https://www.youtube.com/watch?v=XONRaJJAhpA





### **February 15-28**

I released 0.8.0 which includes:

* [PR #5](https://github.com/oakes/odoyle-rules/pull/5) - Improve cljs require support. Add cljs tests
* [Issue #6](https://github.com/oakes/odoyle-rules/issues/6) - Nondeterministic behavior?

The last three months added far more polish to O'Doyle than I ever intended. The addition of `:then-finally` made it far better for creating derived facts, and the `{:then not=}` feature made it possible to derive all kinds of hierarchical data without any query language. With the recursion limit and cycle detector, O'Doyle is now far easier to debug when infinite loops happen in your rules. Detecting the opposite (why a rule *didn't* fire) is still an interesting design question that I plan on working on. O'Doyle rules :D





# Calva

### **February 1-14**

Here is an overview of the work I did per project.

## Babashka

- [babashka.fs](https://github.com/babashka/fs): utility library for dealing with files (based on java.nio)
- babashka 0.2.9 released
- upgrade to graalvm 21.0.0
- https://github.com/babashka/babashka/issues/728
- https://github.com/borkdude/spartan.spec/issues/16
- babashka.nrepl: fix printing in lazy value [#36](https://github.com/babashka/babashka.nrepl/issues/36)

## Sci

- Optimize if [#514](https://github.com/borkdude/sci/issues/514)
- Include namepace in invalid arity msg and fix fn name [#518](https://github.com/borkdude/sci/issues/518)
- Fix ns-publics to not include refers [#520, #523](https://github.com/borkdude/sci/issues/520)
- Add `refer-clojure` macro [#519](https://github.com/borkdude/sci/issues/519)
- Syntax quote resolves referred var incorrectly [#526](https://github.com/borkdude/sci/issues/526)

- Priorize referred vars over vars in current ns [#527](https://github.com/borkdude/sci/issues/527)

- if with falsy literal returns nil [#529](https://github.com/borkdude/sci/issues/529)

## Clj-kondo

- Fix crash when linting kitchen-async [#1148](https://github.com/clj-kondo/clj-kondo/issues/1148)
- Core.match support [#496](https://github.com/clj-kondo/clj-kondo/issues/496)
- memory optimizations for clojure-lsp [commit](https://github.com/clj-kondo/clj-kondo/commit/175c48839299c445f6684fa15e5692b03c9bcb5a0)
- Upgrade to GraalVM 21.0.0 [#1163](https://github.com/clj-kondo/clj-kondo/issues/1163)
- Fix analysis of case dispatch vals [#1169](https://github.com/clj-kondo/clj-kondo/issues/1169)
- Don't use lint-as for hooks [#1170](https://github.com/clj-kondo/clj-kondo/issues/1170)
- Exported config fix for git deps [#1171](https://github.com/clj-kondo/clj-kondo/issues/1171)
- Potemkin improvement with regards to unresolved var [#1167](https://github.com/clj-kondo/clj-kondo/issues/1167)
- Fix unresolved var `clojure.spec.gen.alpha/fmap` [#1157](https://github.com/clj-kondo/clj-kondo/issues/1157)

## Edamame

- Return array-based map when c <= 16 [#78](https://github.com/borkdude/edamame/issues/78)

## Misc

- Carve [0.0.2](https://github.com/borkdude/carve/releases/tag/v0.0.2)





### **February 15-28**

Here is my "thank you" video message to Clojurists Together which gives an
overview of the most important new features implemented in this funding round:

https://youtu.be/P09GZVqiDdM

Here is an overview of the work I did per project in the last two weeks.

## Babashka

- move sqlite pod to -go name
- Update link in nREPL server message [#37](https://github.com/babashka/babashka.nrepl/issues/37)
- Upgrade httpkit to 2.5.3 [#738](https://github.com/babashka/babashka/issues/738)
- Add several classes to be used with `defprotocol` (`PersistentVector`, `PersistentHashSet`, ...)
- Release v0.2.11

Spartan.spec:

- Fix `&` with predicate [#19](https://github.com/borkdude/spartan.spec/issues/19)

## Sci

- Detect cyclic load dependencies [#531](https://github.com/babashka/babashka/issues/531)
- `(instance? clojure.lang.IAtom 1)` returns `true` [#537](https://github.com/borkdude/sci/issues/537)
- Pick fn arity independent of written order [#532](https://github.com/babashka/babashka/issues/532)
- Fix `ns-unmap` on referred var [#539](https://github.com/borkdude/sci/issues/539)
- Release v0.2.4

## Clj-kondo

- Lint nested function literal [#636](https://github.com/clj-kondo/clj-kondo/issues/636)
- Continue analyzing on invalid symbol [#1146](https://github.com/clj-kondo/clj-kondo/issues/1146)
- Redundant expression linter [#298](https://github.com/clj-kondo/clj-kondo/issues/298)
- Add `:exclude` config to :refer linter [#1172](https://github.com/clj-kondo/clj-kondo/issues/1172)
- Warn on non-existent var in `:refer` [#546](https://github.com/clj-kondo/clj-kondo/issues/546)
- Support `clojure.data.xml/alias-uri`[#1180](https://github.com/clj-kondo/clj-kondo/issues/1180)
- Standalone require should be emitted to analysis [#1177](https://github.com/clj-kondo/clj-kondo/issues/1177)
- Release v2021.02.28

## Misc

- Run tools-deps as native binary: https://github.com/borkdude/tools-deps-native-experiment
- Infer dependencies from sources: https://github.com/borkdude/deps-infer





# ClojisR

### **February 1-14**

Currently, our main focus is to help different Clojure data science libraries to bring R like functionality by filling in the missing gaps.

## Completed tasks:
* [ClojisR Docker template + documentation](https://github.com/ashimapanjwani/clojisr-template): Created a template containing a Dockerfile with all the ClojisR dependencies + user-guide. This will enable users to quickly get started with using the library without worrying about the environment setup. (Inspired by Carsten Behring's [Template for creating Clojure DS projects with  R + Python](https://cljdoc.org/d/clj-py-r-template/clj-template/1.1.0/doc/readme))
* Performed testing on the [ClojisR](https://github.com/scicloj/clojisr) library and conveyed the issues discovered to the main authors. These discussions helped fix a few problems in the library.
  
## Ongoing tasks:
* [Tablecloth: Cloning functionality](https://github.com/scicloj/tablecloth/pull/22): Implementing cloning by default for all instances of reader in Tablecloth. This is a part of our effort to create better ergonomics for array programming in Clojure.
* Adapting the Tablecloth API so that more functions will have options map for future configurability, while respecting backward compatibility.

## Upcoming tasks:
* Complete the ongoing tasks in Tablecloth.
* Implement the relevant sections of [R4DS](https://r4ds.had.co.nz/explore-intro.html) using ClojisR.





### **February 15-28**

Currently, our main focus is to help different Clojure data science libraries to bring R like functionality by filling in the missing gaps.

## Completed tasks:
* [Tablecloth](https://github.com/scicloj/tablecloth/pull/22):
  * Cloning functionality - Implemented cloning by default for computation-intensive functions involving readers in Tablecloth. This is a part of our effort to create better ergonomics for array programming in Clojure.
  * Adapted the Tablecloth API so that more functions will have options map for future configurability, while respecting backward compatibility.
  * Added Midje unit tests for the functions which were updated while adding the above functionalities.
* [ClojisR tutorials](https://github.com/ashimapanjwani/r-for-data-science-in-clojure/pull/3):
  Translated the code samples from the following chapters of [R4DS](https://r4ds.had.co.nz/index.html) using [ClojisR](https://github.com/scicloj/clojisr).
  * Data visualisation
  * Data transformation
  * Exploratory Data Analysis
* Tested the usage of [Notespace](https://github.com/scicloj/notespace) from Docker and discussed the issues encountered with the library authors.
* Added the functionality to [specify the datatype while adding column](https://github.com/scicloj/tablecloth/pull/26) in Tablecloth.

## Upcoming tasks:
* Translate the Predictive Machine Learning Models section of R4DS using libraries such as [tech.ml](https://github.com/techascent/tech.ml) and [metamorph.ml](https://github.com/scicloj/metamorph.ml).
* Implement the [data wrangling](https://r4ds.had.co.nz/wrangle-intro.html) section of R4DS using ClojisR.
* Open github issues for any problems encountered during the above translation, discuss with the library maintainers, and try to come up with fixes.
* Continue our effort to create better ergonomics for array programming in Clojure.






