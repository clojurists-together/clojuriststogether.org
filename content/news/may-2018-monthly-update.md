---
title: May 2018 Monthly Update
date: 2018-05-12T20:36:31+12:00
draft: false
type: post
---

## Clojurists Together news

Not much to report from Clojurists Together this month, things have been pretty quiet with lots of other things going on. This is the first month of work from the second round of funding. Bozhidar Batsov is working on CIDER + related projects and Mike Fikes is working on ClojureScript. If you like the work that they're doing and would like to support it further, consider joining yourself or your company as a member.

## CIDER updates

### May 1 - 15

* Finished the nREPL migration out of clojure-contrib (the 0.4.0 marks the end of the breaking changes)
* Provided a [PR](https://github.com/trptcolin/reply/pull/182) for reply to switch to the new nREPL
* Migrated [drawbridge](https://github.com/nrepl/drawbridge) to the new org and updated it for nREPL 0.4
* [Extracted](https://github.com/clojure-emacs/orchard/commit/b7ceb98b3d0b1f1035f9586a127cd23b3d18ff5c) the var-info functionality from cider-nrepl to orchard
* Added basic cljs deps injection to CIDER on cider-jack-in-cljs

### May 16 - 31

* Extracted the bencoding from nREPL into a [library](https://github.com/nrepl/bencode) we hope to make portable
* Improved Clojure's dependency checking a bit to be aware of group ids and versions
* Fixed a couple of small nREPL issues
* Started work on lein/boot plugins to boot the new nREPL from cider-jack-in

## ClojureScript updates

### May 1 - 15

The last couple of weeks saw this activity in the ClojureScript compiler:

* Patch landed for [CLJS-2702](https://dev.clojure.org/jira/browse/CLJS-2702), allowing the ClojureScript compiler
  (or end users) to upgrade to the latest Closure Library. The patch was written in such a way that end users can
  continue to use the current Closure Library, or upgrade to the latest, with the compiler codebase being compatible
  with both. You can read more details on this work at this [gist](https://gist.github.com/mfikes/baf71581b759dc6a844b860db5889bad)
* Patch landed for highly-voted [CLJS-2724](https://dev.clojure.org/jira/browse/CLJS-2724), which will allow native Node modules (like "fs") to be used in scripts executed via `cljs.main`
* Towards improving the use of `:npm-deps`, patch landed for [CLJS-2739](https://dev.clojure.org/jira/browse/CLJS-2739), which improves the performance of the compiler when indexing the `node_modules` directory. A test exhibited a 6-fold speedup, cutting the indexing time down from 30 seconds to 5 seconds.

Please continue to [vote](https://dev.clojure.org/jira/secure/IssueNavigator.jspa?mode=hide&requestId=10706) for, or file issues in the ClojureScript JIRA. I'm especially interested in those related to NPM dependency management.

### May 16-31

The last couple of weeks saw this activity in the ClojureScript compiler:

* Submitted a patch in [CLJS-2298](https://dev.clojure.org/jira/browse/CLJS-2298), which causes REPLs to load any
user-defined `user.cljs` / `user.cljc` file.
* Worked on [CLJS-2651](https://dev.clojure.org/jira/browse/CLJS-2651), which allows Git Deps to be supported
in the AOT cache feature. Follow up work will be needed to sort out a corner
case: [CLJS-2765](https://dev.clojure.org/jira/browse/CLJS-2765).
* Landed a patch for [CLJS-1677](https://dev.clojure.org/jira/browse/CLJS-1677) which was causing an
issue if you explicitly require `goog` in `ns` forms in an `:advanced` build.
* Landed a patch in [CLJS-2755](https://dev.clojure.org/jira/browse/CLJS-2755), that makes it possible to
generate values satisfying `uri?` when generatively testing with Spec.
* Landed a patch in [CLJS-1871](https://dev.clojure.org/jira/browse/CLJS-1871), which allows static dispatch
to declared functions when hinted with `:arglists` meta. A bonus side effect is that it helps catch arity errors for calls to declared functions.
* Began investigation into [CLJS-2339](https://dev.clojure.org/jira/browse/CLJS-2369) and discovered they are
no longer reproducible with latest compiler.
