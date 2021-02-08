---
title: June 2018 Monthly Update
date: 2018-07-18T20:59:31+12:00
author: Daniel Compton
type: post
---

## Clojurists Together news

This has been another productive month for CIDER and ClojureScript. There is one more month left in this funding cycle and then we're starting our next round.

We recently [surveyed](/news/q3-2018-survey-results/) our members to see what they wanted us to fund. Error messages, documentation, developer experience, and IDE support featured highly. If you maintain a Clojure project that is important to some or all of the Clojure community, especially in one of these areas please [apply](/open-source/) for funding. Applications close July 20.

## CIDER updates

- A new [release](https://github.com/clojure-emacs/clojure-mode/commits/master) of clojure-mode.el (performance improvements and integration with project.el, needed for the new connection management in CIDER)
- The new connection management for CIDER is now in master (another person did most of the work, as after I announced I planned to rewrite it in the scope of the funding he expressed the desire to pick up an old PR he had created about this). Afterwards I did a few rounds of testing and bug-fixing together with him.
- I completed the removal of cider-interaction.el and restructured the code quite a lot in the process.
- I did many improvements to the manual (+ a new domain for CIDER - http://docs.cider.mx).
- Some other small improvements are [handling cider-ancillary-buffers more consistently](https://github.com/clojure-emacs/cider/commit/13c76efcfc94f3c97183962f91146a851bd7a9d3) and [improving nREPL connection handling](https://github.com/clojure-emacs/cider/commit/c658d8a759adc187da70efaacf800fdc0f852021)
- I've also opened PRs for lein and boot for the new nREPL - [boot-clj/boot#703](https://github.com/boot-clj/boot/pull/703) and [technomancy/leiningen#2444](https://github.com/technomancy/leiningen/pull/2444)

## ClojureScript updates

### June 1-15

- Several ClojureScript patches were worked on in first half of June.
- A self-host regression ([CLJS-2766](https://dev.clojure.org/jira/browse/CLJS-2766)) surrounding revisions to the `exists?` implementation was fixed.
- A patch was landed ([CLJS-2769](https://dev.clojure.org/jira/browse/CLJS-2769)) which it possible to run the self-hosts tests with the very latest Google Closure Library code.
- A corner case surrounding `:install-deps` and `cljs.main`, when using `-e` to evaluate code ([CLJS-2775](https://dev.clojure.org/jira/browse/CLJS-2775)) was fixed.
- Work was started on a patch to enable `*warn-on-reflection*` in the compiler's codebase ([CLJS-2748](https://dev.clojure.org/jira/browse/CLJS-2748)).
- An issue that prevented async test result reporting from properly occurring with the Node REPL ([CLJS-2780](https://dev.clojure.org/jira/browse/CLJS-2780)) was fixed.

All of the work to date, apart from the last two items, was shipped in the ClojureScript 1.10.312 release.

### June 16-30

Several ClojureScript patches were worked on in second half of June.

- Landed a fix for an issue that prevented proper async test result reporting when using the Node REPL ([CLJS-2780](https://dev.clojure.org/jira/browse/CLJS-2780)).
- Landed a patch ([CLJS-2442](https://dev.clojure.org/jira/browse/CLJS-2442)), which speeds things up when applying `set` to a set and `vec` to a vector, thus aligning with Clojure for these cases. This allows for simple and efficient code when you need to coerce from an arbitrary collection to a set or vector.
- Landed a patch ([CLJS-2790](https://dev.clojure.org/jira/browse/CLJS-2790)) which fixes spurious [inference warnings](https://clojurescript.org/guides/externs) when using `defrecord` with fields.
- Re-baselined and re-benchmarked patch in ([CLJS-2341](https://dev.clojure.org/jira/browse/CLJS-2341)) given that [CLJS-844](https://dev.clojure.org/jira/browse/CLJS-844) has been applied. (Whatever the end result is, you can count on speedups in the range of 1.5 to 2 for `js->clj`.)
- Landed patches in [CLJS-2798](https://dev.clojure.org/jira/browse/CLJS-2798) and [CLJS-2799](https://dev.clojure.org/jira/browse/CLJS-2799), which were some minor quirks in need of fixing in preparation for [CLJS-2693](https://dev.clojure.org/jira/browse/CLJS-2693), which aims to adds support for chuncked sequence ranges. (Check out the potential perf improvements detailed in that ticket!)
