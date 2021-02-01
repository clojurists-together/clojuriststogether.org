---
title: "January 2021 Monthly Update"
date: 2021-02-01T08:30:00+08:00
author: Alyssa Parado
summary: Read more updates from Clj-kondo/babashka/sci, ClojisR, O’Doyle Rules, and Calva
draft: true
---


# Clj-kondo/babashka/sci

### **January 1-15**

Here is an overview of the work I did per project.

## Babashka

- Only build static binary on non-SNAPSHOT release [#695](https://github.com/babashka/babashka/issues/695)
- Migration from borkdude/babashka to babashka/babashka
- Babashka pods: write to output before delivering result [commit](https://github.com/babashka/pods/commit/0bffce3573d0361cf2592d65cefc8a4048454ff9)
- [pod-babashka aws](https://github.com/babashka/pod-babashka-aws): a new pod to interact with AWS
- Add `pp` macro from `clojure.pprint` [#707](https://github.com/babashka/babashka/issues/707)
- Add `--download-dir` option to install script [#688](https://github.com/babashka/babashka/issues/688)

## Sci

- defrecord `type` improvements [#492](https://github.com/borkdude/sci/issues/492)
- conditionally defined vars should not have metadata [#496](https://github.com/borkdude/sci/issues/496)
- experiment with using sci and pgx to create postgres extension [#373](https://github.com/borkdude/sci/issues/373). Also see the [repo](https://github.com/borkdude/plsci).
- Fix shadow warnings #499
- Several performance improvements

## Clj-kondo

- Review PR [#1108](https://github.com/borkdude/clj-kondo/pull/1108#issuecomment-753454914): add local analysis to clj-kondo
- Move to clj-kondo/clj-kondo organisation
- Fix local analysis name [#1112](https://github.com/clj-kondo/clj-kondo/issues/1112)
- Research for clj-kondo resolve from other namespace, work in progress.
- Fix finding without location info [#1101](https://github.com/clj-kondo/clj-kondo/issues/1101)
- Alpine Docker build [#1111](https://github.com/clj-kondo/clj-kondo/issues/1111)
- Review several PRs

## Misc

- First release (0.0.1-0.0.3) of the [puget](https://github.com/borkdude/puget-cli) CLI, a binary to pipe EDN to and get pprinted and colorize output.
- First release of [Carve](https://github.com/borkdude/carve/issues/35) as a binary.





### **January 16-31**

## Babashka

- spartan.spec: fix compatibility with expound. See [example](https://github.com/borkdude/spartan.spec#example).
- spartan.spec: fix compatibility with cli-matic. See [issue](https://github.com/borkdude/spartan.spec/issues/13).
- interop on map returns nil. [#711](https://github.com/babashka/babashka/issues/711)
- `(instance? clojure.lang.Fn x)` now works
- Release 0.2.7
- pod-babashka-aws: fix problem with `nil` in response [#27](https://github.com/babashka/pod-babashka-aws/issues/27)
- babashka-sql-pods: upgrade to newest next.jdbc version [#18](https://github.com/babashka/babashka-sql-pods/issues/18)
- spartan.spec: fix s/and + s/cat [#15](https://github.com/borkdude/spartan.spec/issues/15)
- Include [clojure.core.match](https://github.com/clojure/core.match) [#594](https://github.com/babashka/babashka/issues/594)
- Include [hiccup](https://github.com/weavejester/hiccup) [#646](https://github.com/babashka/babashka/issues/646)
- Include [clojure.test.check](https://github.com/clojure/test.check) [#487](https://github.com/babashka/babashka/issues/487). Included namespaces:
  - clojure.test.check
  - clojure.test.check.generators
  - clojure.test.check.properties

## Sci

- Unroll local binding calls [#502](https://github.com/borkdude/sci/issues/502)
- Unroll local binding ref [#504](https://github.com/borkdude/sci/issues/504)
- `alength` doesn't work with GraalVM [#507](https://github.com/borkdude/sci/issues/507)
- Release [0.2.1](https://github.com/borkdude/sci/blob/master/CHANGELOG.md#v021)
- Prioritize current namespace in syntax quote [#509](https://github.com/borkdude/sci/issues/509)
- Faster processing of colls [#482](https://github.com/borkdude/sci/issues/482)
- Fix destructuring of destructuring in protocol method of record [#512](https://github.com/borkdude/sci/issues/512)
- Fix shadowing of record field in protocol method [#513](https://github.com/borkdude/sci/issues/513)

## Clj-kondo

- New linter: `:unresolved-var`. This detects unresolved vars in other namespaces, like `set/onion`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#unresolved-var). [#635](https://github.com/clj-kondo/clj-kondo/issues/635)
- Derive file path from only file linted [#1135](https://github.com/clj-kondo/clj-kondo/issues/1135)
- Fix finding without location info [#1101](https://github.com/clj-kondo/clj-kondo/issues/1101)
- Derive config dir from only file path linted [#1135](https://github.com/clj-kondo/clj-kondo/issues/1135)
- Support name in defmethod fn-tail [#1115](https://github.com/clj-kondo/clj-kondo/issues/1115)
- Avoid crash when using `:refer-clojure` + `:only` [#957](https://github.com/clj-kondo/clj-kondo/issues/957)
- Release v2021.01.20






# O’Doyle Rules

### **January 1-15**

In the past few weeks I tried finding the low-hanging fruit to improve performance. I did this with the help of the excellent [clj-async-profiler](https://github.com/clojure-goes-fast/clj-async-profiler). I also tried a few ideas that ultimately didn't pan out:

1. Running `:when` conditions earlier in the network. In theory, this could reduce unnecessary work by discarding invalid facts sooner. In practice, it didn't affect the benchmarks at all, and the code was really convoluted. So much for that.
2. Storing matches as records instead of hash maps. In theory, this could boost performance because records have faster field access than hash maps. In practice, the benchmarks actually got a bit slower! I still haven't figured out exactly why, but it at least indicates that field access isn't a bottleneck.

With the micro-optimizations out of the way, the next few weeks will be all about improving perf with the RETE algorithm itself. GOTTA GO FAST.





### **January 16-31**

I shed a lot of blood, sweat, and parenthesis to implement a common optimization called "node sharing". Rules that pulled in similar data now could share nodes internally, avoiding duplicated effort. Then, just as I was about to cut a release, I had an epiphany: "derived facts" give us the same benefits as node sharing!

I wrote a [section in the README](https://github.com/oakes/odoyle-rules#performance) about how to use derived facts in this way. When I applied this technique to the "dungeon" benchmark, it went from ~1700 ms to ~1100 ms -- an even better improvement than from node sharing. The [dungeon crawler game](https://github.com/oakes/play-cljc-examples/tree/master/dungeon-crawler) improved by 5 FPS with the same technique.

I ended up deleting a few hundred lines of fresh code -- no need to complicate the codebase if we can just leverage an existing feature instead. I'm now going to focus on debugging and inspecting sessions. Can we do better than the almighty `println`? Probably not, but no harm in trying...





# Calva

### **January 1-15**

I've done some work in both Calva and Clojure-lsp. 

## Calva Work

Calva now uses clj-kondo via clojure-lsp, and no longer bundles the clj-kondo extension. This reduces Calva's indirect memory footprint as opposed to the previous setup of using clojure-lsp and the clj-kondo exntension, since the clj-kondo extension runs its own LSP server, which is no longer necessary.

My co-maintainer Peter also did quite a bit of work recently. Below is a list of Calva changes in the last couple weeks.

### [2.0.151] - 2021-01-15
- Fix: [Debugger is broken on Windows](https://github.com/BetterThanTomorrow/calva/issues/947)

### [2.0.150] - 2021-01-13
- [Stop bundling clj-kondo in favor of using it through clojure-lsp](https://github.com/BetterThanTomorrow/calva/issues/868)

### [2.0.149] - 2021-01-12
- Fix: [calva.jackInEnv does not resolve `${env:...}`](https://github.com/BetterThanTomorrow/calva/issues/933)
- Update clojure-lsp to version 2021.01.12-02.18.26. Fix: [clojure-lsp processes left running/orphaned if VS Code is closed while the lsp server is starting](https://github.com/BetterThanTomorrow/calva/issues/906)

### [2.0.148] - 2021-01-07
- Update clojure-lsp to version 2021.01.07-20.02.02

### [2.0.147] - 2021-01-07
- Fix: [Dimming ignored forms does not work correctly with metadata](https://github.com/BetterThanTomorrow/calva/issues/908)
- [Improve clojure-lsp jar integration](https://github.com/BetterThanTomorrow/calva/issues/913)
- Update clojure-lsp to version 2021.01.07-12.28.44

### [2.0.146] - 2021-01-04
- Fix: [Slurp forward sometimes joins forms to one](https://github.com/BetterThanTomorrow/calva/issues/883)
- Fix: [clojure-lsp processes left running/orphaned if VS Code is closed while the lsp server is starting](https://github.com/BetterThanTomorrow/calva/issues/906)
- Fix: [go to definition jumps to inc instead of inc'](https://github.com/BetterThanTomorrow/calva/issues/884)
- Fix: [Error when start a REPL with jdk15](https://github.com/BetterThanTomorrow/calva/issues/888)

### [2.0.145] - 2021-01-03
- [Add command for opening the file for the output/repl window namespace](https://github.com/BetterThanTomorrow/calva/issues/920)
- [Add setting for auto opening the repl window on Jack-in/Connect](https://github.com/BetterThanTomorrow/calva/issues/922)
- [Add setting for auto opening the Jack-in Terminal](https://github.com/BetterThanTomorrow/calva/issues/923)
- [Replace opening Calva says on start w/ info message box](https://github.com/BetterThanTomorrow/calva/issues/923)
- [Add command for opening Calva documentation](https://github.com/BetterThanTomorrow/calva/issues/923)
- [Change default keyboard shortcut for syncing the repl window ns to `ctrl+alt+c n`](https://github.com/BetterThanTomorrow/calva/issues/923)

### [2.0.144] - 2021-01-01
- [Reactivate definitions/navigation in core and library files](https://github.com/BetterThanTomorrow/calva/issues/915)
- [Make load-file available in the output window](https://github.com/BetterThanTomorrow/calva/issues/910)
- [Make the ns in the repl prompt a peekable symbol](https://github.com/BetterThanTomorrow/calva/issues/904)

## Clojure-lsp Work

I added a change to Clojure-lsp to prevent the process from being orphaned. If VS Code was killed in a certain time window while Clojure-lsp was initializing, the process would be left running. Now Clojure-lsp periodically checks if the parent process is alive and will exit if not. This is a suggested feature of servers by the language server protocol.

I also fixed an issue with the default classpath lookup for Windows.

- [Fix classpath-cmd modification for Windows](https://github.com/clojure-lsp/clojure-lsp/pull/241)
- [Prevent orphaned server processes](https://github.com/clojure-lsp/clojure-lsp/pull/251)





### **January 16-31**

For decorating functions instrumented for debugging, I've replaced the usage of clj-kondo with clojure-lsp, as well as restructured the code to make the feature work a bit better. This allowed us to remove clj-kondo as an injected jack-in dependency.

I've also changed the way clojure-lsp initialization progress is shown. Instead of a popup progress indicator, progress is now shown in the bottom status bar, and disappears when initialization is complete.

I've also helped with finding and debugging issues and testing fixes in Clojure-lsp after some significant changes were released. Much thanks to Eric Dallo for being very active, responding to and fixing issues that arise.

## Calva Changes

### [2.0.156] - 2021-01-28
- Fix: [Debug instrumentation decoration not working correctly anymore on Windows](https://github.com/BetterThanTomorrow/calva/issues/969)
- Fix: [Debugger decorations issues](https://github.com/BetterThanTomorrow/calva/issues/976)

### [2.0.155] - 2021-01-27
- [Make command palette show alt+enter shortcut variant instead of enter for evaluating top level form](https://github.com/BetterThanTomorrow/calva/issues/989)
- Update clojure-lsp to 2021.01.28-03.03.16
- Fix: [nrepl port detection race condition](https://github.com/BetterThanTomorrow/calva/issues/901)

### [2.0.154] - 2021-01-27
- Fix: [Calva uses ; for comments instead of ;;](https://github.com/BetterThanTomorrow/calva/issues/971)
- Update cider-nrepl to 0.25.8
- Update clojure-lsp to 2021.01.26-22.35.27

### [2.0.153] - 2021-01-19
- [Use status bar message instead of withProgress message for clojure-lsp initialization](https://github.com/BetterThanTomorrow/calva/issues/974)
- [Update cider-nrepl: 0.25.6 -> 0.25.7](https://github.com/BetterThanTomorrow/calva/issues/973)
- Fix: ["Extract function" refactoring doesn't work as expected with selections](https://github.com/BetterThanTomorrow/calva/issues/958)

### [2.0.152] - 2021-01-19
- Fix: [Jack-In env with non-string variables fails](https://github.com/BetterThanTomorrow/calva/issues/959)
- [Use clojure-lsp for usages for debug instrumentation decorations, and stop injecting clj-kondo at jack-in](https://github.com/BetterThanTomorrow/calva/issues/931)





# ClojisR

### **January 1-15**

## Project Goals:
* Creating good documentation and beginner-friendly tutorials for [ClojisR](https://github.com/scicloj/clojisr) (a wrapper library exposing APIs for calling R functions on R objects in Clojure), thereby allowing us to expose the Clojure data science ecosystem to a diverse group of users, especially data scientists studying R as their main language.
* Comparing R and Clojure ecosystems to push the Clojure data science stack forward.
  - figuring out the differences and helping bring the missing functionalities to Clojure
  - creating documentation that explains how R functionality and idioms can be achieved in Clojure
* Bringing the ClojisR library to a more complete and stable state.

## Completed tasks:
* [#1](https://github.com/ashimapanjwani/r-for-data-science-in-clojure/pull/1) Used a wide range of Clojure data science libraries (mainly Tablecloth, tech.ml.dataset, dtype-next, ClojisR, Vega+Hanami, Fastmath, Notespace) to translate code samples under the Data Wrangling section of [R4DS](https://r4ds.had.co.nz/wrangle-intro.html) covering the following topics:
  - Tibbles (Dataframes)
  - Data import
  - Tidy data
  - Relational data
  - Strings
  - Dates and times
* The above translation allowed us to compare the current Clojure data science ecosystem with the R ecosystem and helped us figure out the functionality which was missing and the features which could be made more user-friendly. These points were discussed with the relevant library authors (mainly Tablecloth, ClojisR, Notespace, dtype-next) and various GitHub issues were opened for feature requests as well as bug fixes.
  - [dtype-next : human readable datetime](https://github.com/cnuernber/dtype-next/issues/9)
  - [tablecloth: view each column's datatype below the column name](https://github.com/scicloj/tablecloth/issues/12)
  - [tablecloth: Simplify access to row values in adjacent columns](https://github.com/scicloj/tablecloth/issues/13)
  - [tablecloth: Update documentation](https://github.com/scicloj/tablecloth/issues/14)
  - [tablecloth: (repeatedly rand) is running indefinitely despite other columns being finite in a dataset](https://github.com/scicloj/tablecloth/issues/15)
  - [notespace: Ability to view the entire dataset in a scrollable format](https://github.com/scicloj/notespace/issues/54)

## Upcoming tasks:
* Translate the Data Visualization, Data Transformation, and Exploratory Data Analysis sections of R4DS to Clojure using libraries such as Tablecloth, Vega+Hanami, Fastmath, and dtype-next.
* Implement the relevant sections of R4DS using ClojisR in order to compare Clojure-R interop vs native Clojure functionality.
* Maybe translate the Predictive Machine Learning Models section of R4DS. (Note: Throughout my work, I’m maintaining a continuous dialogue with some of the relevant library authors. The order in which I approach these tasks may depend upon related developments in other libraries such as [tech.ml]() (for machine learning))





### **January 16-31**





