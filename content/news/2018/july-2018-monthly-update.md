---
title: July 2018 Monthly Update
date: 2018-08-15T05:59:31+12:00
draft: false
type: post
---

## Clojurists Together news

This was the final month for CIDER and ClojureScript. Our next two projects that we are funding are cljdoc and Shadow CLJS. Thanks to all of our members who support Clojurists Together. It's thanks to your generous support that we can do this.

## CIDER updates

This months updates were all around getting nREPL 0.4 into the hands of everyone.

- I've also opened PRs for [lein](https://github.com/technomancy/leiningen/pull/2444) and [boot](https://github.com/boot-clj/boot/pull/703) for the new nREPL.
- Released [nREPL 0.4.2](https://github.com/nrepl/nREPL/releases/tag/0.4.2)
- Released [nREPL 0.4.3](https://github.com/nrepl/nREPL/releases/tag/0.4.3)
- Released [nREPL 0.4.4](https://github.com/nrepl/nREPL/releases/tag/0.4.4)
- Created a [nice manual](http://nrepl.readthedocs.io/) for nREPL
- Released [cider-nrepl 0.18.0](https://github.com/clojure-emacs/cider-nrepl/blob/master/CHANGELOG.md#0180-2018-08-06) (with support for nREPL 0.4)
- Removed the lockstep between CIDER and CIDER nREPL versions, now cider-nrepl releases are going to happen independently from CIDER's (mostly because several editors now rely on cider-nrepl)
- Updated refactor-nrepl to support nREPL 0.4
- Updated piggieback to support nREPL 0.4 (released with 0.3.8)
- Created a [lein plugin](https://github.com/nrepl/lein-nrepl) to start an nREPL 0.4 server easily
- Send a PR to sayid [adding nREPL 0.4 support](https://github.com/bpiel/sayid/pull/40)
- Released [drawbridge](https://github.com/nrepl/drawbridge) 0.1.3 which added back support for tools.nrepl

## ClojureScript updates

Several ClojureScript patches were worked on in first half of July.

- Added a new feature to produce compiler warnings on private var use via [CLJS-1702](https://dev.clojure.org/jira/browse/CLJS-1702) and [CLJS-2817](https://dev.clojure.org/jira/browse/CLJS-2817).
- Added function return type inference via [CLJS-1997](https://dev.clojure.org/jira/browse/CLJS-1997).
- Added support for `IPrintWithWriter` with native types via [CLJS-2812](https://dev.clojure.org/jira/browse/CLJS-2812).
- Added warnings for non-dynamic earmuffed vars via [CLJS-2819](https://dev.clojure.org/jira/browse/CLJS-2819).
- Updated to latest `test.check` via [CLJS-2806](https://dev.clojure.org/jira/browse/CLJS-2806).
- Updated docstring for `doto` to use `js/Map` via [CLJS-2821](https://dev.clojure.org/jira/browse/CLJS-2821).
- Submitted a patch in [CLJS-2693](https://dev.clojure.org/jira/browse/CLJS-2693), which adds chunked seq support to ranges.
- With [CLJS-2796](https://dev.clojure.org/jira/browse/CLJS-2796), caught a Closure Compiler regression, filed an upstream issue, which was fixed.
- Added a patch in [CLJS-2802](https://dev.clojure.org/jira/browse/CLJS-2802), which lets `empty?` work on transient collections.
- Landed a fix for a bad test related to Windows in [CLJS-2811](https://dev.clojure.org/jira/browse/CLJS-2811).
- Submitted a patch in [CLJS-2813](https://dev.clojure.org/jira/browse/CLJS-2813), adding Java serializability of `JSValue`.
- Looked into [CLJS-2793](https://dev.clojure.org/jira/browse/CLJS-2793), an issue with specing variadic fns.

The second half of July involved these tickets:

- Added a new Graal.JS REPL environment via [CLJS-2831](https://dev.clojure.org/jira/browse/CLJS-2831).
- Updated to Closure Compiler v20180716 via [CLJS-2833](https://dev.clojure.org/jira/browse/CLJS-2833).
- Updated ClojureScript's implementation of spec, to bring it to parity with Clojure's via: [CLJS-2725](https://dev.clojure.org/jira/browse/CLJS-2725), [CLJS-2822](https://dev.clojure.org/jira/browse/CLJS-2822), [CLJS-2665](https://dev.clojure.org/jira/browse/CLJS-2665), [CLJS-2848](https://dev.clojure.org/jira/browse/CLJS-2848), [CLJS-2846](https://dev.clojure.org/jira/browse/CLJS-2846), [CLJS-2841](https://dev.clojure.org/jira/browse/CLJS-2841), [CLJS-2847](https://dev.clojure.org/jira/browse/CLJS-2847), [CLJS-2842](https://dev.clojure.org/jira/browse/CLJS-2842), [CLJS-2845](https://dev.clojure.org/jira/browse/CLJS-2845), [CLJS-2844](https://dev.clojure.org/jira/browse/CLJS-2844), [CLJS-2840](https://dev.clojure.org/jira/browse/CLJS-2840), [CLJS-2839](https://dev.clojure.org/jira/browse/CLJS-2839), [CLJS-2838](https://dev.clojure.org/jira/browse/CLJS-2838), and [CLJS-2837](https://dev.clojure.org/jira/browse/CLJS-2837).
- Landed a fix for an issue where `binding` was not being done in parallel via [CLJS-2541](https://dev.clojure.org/jira/browse/CLJS-2541).
- Landed a fix for bad code gen surrounding application of `not` in operator position via [CLJS-2382](https://dev.clojure.org/jira/browse/CLJS-2832).
- Submitted a patch to eliminate unnecessary `^boolean` annotations in [CLJS-2825](https://dev.clojure.org/jira/browse/CLJS-2825) (possible because of the new function return type inference).
- Landed a fix for an issue with arglists and macros via [CLJS-2852](https://dev.clojure.org/jira/browse/CLJS-2852).

This work was a lot of fun—I'm looking forward to the changes appearing in an official ClojureScript release in the future!
