## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve with this funding?

    • Come up with a way to accommodate a recent change in the Closure Library that currently prevents ClojureScript from upgrading to newer versions of this library. ClojureScript currently depends on a private implementation detail that has changed, with the specifics captured in https://dev.clojure.org/jira/browse/CLJS-2702
    • Work on some of the most voted ClojureScript feature requests and issues in (https://dev.clojure.org/jira/browse/CLJS#selectedTab=com.atlassian.jira.plugin.system.project%3Apopularissues-panel). 
    • Work on issues related to NPM dependency management
    • Continue my general work on providing patches for ClojureScript issues and general improvements

### Why is this project important to the Clojure community?

ClojureScript itself is important to the community, and the Closure Library is essentially the "runtime library" used by ClojureScript, as well as many users of ClojureScript. So, being able to upgrade to the latest version of this library would be of value to the community (thus being able to bring in bug fixes made to this library as well as new features that Google may add).

## Work log

### Closure Library Upgrade

* Patch landed for [CLJS-2702](https://dev.clojure.org/jira/browse/CLJS-2702), allowing the ClojureScript compiler
  (or end users) to upgrade to the latest Closure Library. The patch was written in such a way that end users can 
  continue to use the current Closure Library, or upgrade to the latest, with the compiler codebase being compatible 
  with both.

### Work on most voted feature requests

* Patch landed for highly-voted [CLJS-2724](https://dev.clojure.org/jira/browse/CLJS-2724), which will allow native Node modules (like "fs") to be used in scripts executed via `cljs.main`
* Landed a patch in [CLJS-1871](https://dev.clojure.org/jira/browse/CLJS-1871), which allows static dispatch 
to declared functions when hinted with `:arglists` meta. A bonus side effect is that it helps catch arity errors for calls to declared functions.
* Re-baselined and re-benchmarked patch in ([CLJS-2341](https://dev.clojure.org/jira/browse/CLJS-2341)) given that [CLJS-844](https://dev.clojure.org/jira/browse/CLJS-844) has been applied. (Whatever the end result is, you can count on speedups in the range of 1.5 to 2 for `js->clj`.)
* Landed a patch ([CLJS-2442](https://dev.clojure.org/jira/browse/CLJS-2442)), which speeds things up when applying `set` to a set and `vec` to a vector, thus aligning with Clojure for these cases. This allows for simple and efficient code when you need to coerce from an arbitrary collection to a set or vector. 
* Submitted a patch in [CLJS-2693](https://dev.clojure.org/jira/browse/CLJS-2693), which adds chunked seq support to ranges.

### Work on NPM dependency management

* Towards improving the use of `:npm-deps`, patch landed for [CLJS-2739](https://dev.clojure.org/jira/browse/CLJS-2739), which improves the performance of the compiler when indexing the `node_modules` directory. A test exhibited a 6-fold speedup, cutting the indexing time down from 30 seconds to 5 seconds.


### Other patches and general improvements

* Submitted a patch in [CLJS-2298](https://dev.clojure.org/jira/browse/CLJS-2298), which causes REPLs to load any 
user-defined `user.cljs` / `user.cljc` file.
* Worked on [CLJS-2651](https://dev.clojure.org/jira/browse/CLJS-2651), which allows Git Deps to be supported 
in the AOT cache feature. Follow up work will be needed to sort out a corner 
case: [CLJS-2765](https://dev.clojure.org/jira/browse/CLJS-2765).
* Landed a patch for [CLJS-1677](https://dev.clojure.org/jira/browse/CLJS-1677) which was causing an 
issue if you explicitly require `goog` in `ns` forms in an `:advanced` build.
* Landed a patch in [CLJS-2755](https://dev.clojure.org/jira/browse/CLJS-2755), that makes it possible to 
generate values satisfying `uri?` when generatively testing with Spec.
* Began investigation into [CLJS-2339](https://dev.clojure.org/jira/browse/CLJS-2369) and discovered they are 
no longer reproducible with latest compiler.
* A self-host regression ([CLJS-2766](https://dev.clojure.org/jira/browse/CLJS-2766)) surrounding revisions to the `exists?` implementation was fixed.
* A patch was landed ([CLJS-2769](https://dev.clojure.org/jira/browse/CLJS-2769)) which it possible to run the self-hosts tests with the very latest Google Closure Library code.
* A corner case surrounding `:install-deps` and `cljs.main`, when using `-e` to evaluate code ([CLJS-2775](https://dev.clojure.org/jira/browse/CLJS-2775)) was fixed.
* Work was started on a patch to enable `*warn-on-reflection*` in the compiler's codebase ([CLJS-2748](https://dev.clojure.org/jira/browse/CLJS-2748)).
* An issue that prevented async test result reporting from properly occurring with the Node REPL ([CLJS-2780](https://dev.clojure.org/jira/browse/CLJS-2780)) was fixed.
* Landed a fix for an issue that prevented proper async test result reporting when using the Node REPL ([CLJS-2780](https://dev.clojure.org/jira/browse/CLJS-2780)).
* Landed a patch ([CLJS-2790](https://dev.clojure.org/jira/browse/CLJS-2790)) which fixes spurious [inference warnings](https://clojurescript.org/guides/externs) when using `defrecord` with fields.
* Landed patches in [CLJS-2798](https://dev.clojure.org/jira/browse/CLJS-2798) and [CLJS-2799](https://dev.clojure.org/jira/browse/CLJS-2799), which were some minor quirks in need of fixing in preparation for [CLJS-2693](https://dev.clojure.org/jira/browse/CLJS-2693), which aims to adds support for chunked sequence ranges. (Check out the potential perf improvements detailed in that ticket!)
- Updated ClojureScript's implementation of spec, to bring it to parity with Clojure's via: [CLJS-2725](https://dev.clojure.org/jira/browse/CLJS-2725), [CLJS-2822](https://dev.clojure.org/jira/browse/CLJS-2822), [CLJS-2665](https://dev.clojure.org/jira/browse/CLJS-2665), [CLJS-2848](https://dev.clojure.org/jira/browse/CLJS-2848), [CLJS-2846](https://dev.clojure.org/jira/browse/CLJS-2846), [CLJS-2841](https://dev.clojure.org/jira/browse/CLJS-2841), [CLJS-2847](https://dev.clojure.org/jira/browse/CLJS-2847), [CLJS-2842](https://dev.clojure.org/jira/browse/CLJS-2842), [CLJS-2845](https://dev.clojure.org/jira/browse/CLJS-2845), [CLJS-2844](https://dev.clojure.org/jira/browse/CLJS-2844), [CLJS-2840](https://dev.clojure.org/jira/browse/CLJS-2840), [CLJS-2839](https://dev.clojure.org/jira/browse/CLJS-2839), [CLJS-2838](https://dev.clojure.org/jira/browse/CLJS-2838), and [CLJS-2837](https://dev.clojure.org/jira/browse/CLJS-2837). 
- Added a new feature to produce compiler warnings on private var use via [CLJS-1702](https://dev.clojure.org/jira/browse/CLJS-1702) and [CLJS-2817](https://dev.clojure.org/jira/browse/CLJS-2817).
- Added function return type inference via [CLJS-1997](https://dev.clojure.org/jira/browse/CLJS-1997).
- Added support for `IPrintWithWriter` with native types via [CLJS-2812](https://dev.clojure.org/jira/browse/CLJS-2812).
- Added warnings for non-dynamic earmuffed vars via [CLJS-2819](https://dev.clojure.org/jira/browse/CLJS-2819). 
- Updated to latest `test.check` via [CLJS-2806](https://dev.clojure.org/jira/browse/CLJS-2806).
- Updated docstring for `doto` to use `js/Map` via [CLJS-2821](https://dev.clojure.org/jira/browse/CLJS-2821).
- With [CLJS-2796](https://dev.clojure.org/jira/browse/CLJS-2796), caught a Closure Compiler regression, filed an upstream issue, which was fixed.
- Added a patch in [CLJS-2802](https://dev.clojure.org/jira/browse/CLJS-2802), which lets `empty?` work on transient collections.
- Landed a fix for a bad test related to Windows in [CLJS-2811](https://dev.clojure.org/jira/browse/CLJS-2811).
- Submitted a patch in [CLJS-2813](https://dev.clojure.org/jira/browse/CLJS-2813), adding Java serializability of `JSValue`.
- Looked into [CLJS-2793](https://dev.clojure.org/jira/browse/CLJS-2793), an issue with specing variadic fns.
- Added a new Graal.JS REPL environment via [CLJS-2831](https://dev.clojure.org/jira/browse/CLJS-2831).
- Updated to Closure Compiler v20180716 via [CLJS-2833](https://dev.clojure.org/jira/browse/CLJS-2833). 
- Landed a fix for an issue where `binding` was not being done in parallel via [CLJS-2541](https://dev.clojure.org/jira/browse/CLJS-2541).
- Landed a fix for bad code gen surrounding application of `not` in operator position via [CLJS-2382](https://dev.clojure.org/jira/browse/CLJS-2832).
- Submitted a patch to eliminate unnecessary `^boolean` annotations in [CLJS-2825](https://dev.clojure.org/jira/browse/CLJS-2825) (possible because of the new function return type inference).
- Landed a fix for an issue with arglists and macros via [CLJS-2852](https://dev.clojure.org/jira/browse/CLJS-2852).

## Commits

$ git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="MAY 1 2018" --until "JULY 31 2018"
* 3123aa32 - CLJS-2827: Avoid var special in core macros for private var access (2018-07-17 15:51:25 -0400) <Mike Fikes>
* 99efac4a - CLJS-2821: Update doto docstring to not use Java example (2018-07-17 14:36:38 -0400) <Mike Fikes>
* a0cadee3 - CLJS-2819: Warn on non-dynamic earmuffed vars (2018-07-17 13:54:10 -0400) <Mike Fikes>
* 9ffa2f7b - CLJS-2817: Suppress private var warnings for specs on private vars (2018-07-17 12:57:55 -0400) <Mike Fikes>
* c8a5876b - CLJS-2806: Bump test.check to 0.10.0-alpha3 (2018-07-17 12:18:28 -0400) <Mike Fikes>
* 63fb5d5f - CLJS-2822: cljs.core.specs.alpha: Map bindings should be `:kind map?` (2018-07-17 11:42:08 -0400) <Mike Fikes>
* 9099373f - CLJS-2829: Fix deep object property access for :global-exports (2018-07-16 20:19:16 -0400) <Juho Teperi>
* 17b71461 - CLJS-2816: Skip non-string package.json browser entry values (2018-07-14 19:17:19 -0400) <Juho Teperi>
* 6ae39305 - CLJS-2815: Support string keys in :global-exports (2018-07-14 19:15:53 -0400) <Juho Teperi>
* 6e78d138 - CLJS-2814: Fix munge-node-lib/global-export on self-host (2018-07-14 19:14:33 -0400) <Juho Teperi>
* 0773689e - CLJS-2812: Support for overriding object printing (2018-07-09 14:02:05 -0400) <Mike Fikes>
* 2e62351f - CLJS-2811: cljs-1537-circular-deps fail on Windows (2018-07-08 16:51:38 -0400) <Mike Fikes>
* 6cbd40f8 - CLJS-2257: Split :var op, desugar dotted symbols and add tests+doc for analyzer (2018-07-08 11:59:56 -0400) <Ambrose Bonnaire-Sergeant>
*   b5e8dbd3 - Merge branch 'master' of github.com:clojure/clojurescript (2018-07-05 20:57:55 -0400) <David Nolen>
|\  
| * 2072f454 - CLJS-2805: Bump tools.reader to 1.3.0 (2018-07-05 17:59:11 -0400) <Mike Fikes>
* | 1cd76502 - CLJS-2803: Remove :record-value op and add record literal tests (2018-07-05 17:59:11 -0400) <Ambrose Bonnaire-Sergeant>
|/  
* 5aaf6db4 - CLJS-2807: Macroexpand failure with set literal (2018-07-05 13:11:43 -0400) <Ambrose Bonnaire-Sergeant>
*   88390da6 - Merge branch 'master' of github.com:clojure/clojurescript (2018-07-04 13:17:29 -0400) <David Nolen>
|\  
| * f289ffee - CLJS-1702: Warning when using private vars (2018-07-04 11:59:54 -0400) <Mike Fikes>
| * 4ac13fc1 - CLJS-1997: Outward function type hint propagation (2018-07-04 09:16:18 -0400) <Mike Fikes>
* | 7c99d37f - CLJS-2801: Add :quote AST op and remove :list op (2018-07-04 12:01:55 -0400) <Ambrose Bonnaire-Sergeant>
|/  
* ea53fd0c - CLJS-2800: Use tools.analyzer style :children (2018-07-02 18:34:40 -0400) <Ambrose Bonnaire-Sergeant>
* 6c8e69cd - CLJS-2797: Prepare :method, :dot, :js-value, and :case* for tools.analyzer AST conversion (2018-07-01 17:13:00 -0400) <Ambrose Bonnaire-Sergeant>
* e43c6f7c - CLJS-2799: Handle nth on seqables with negative indexes (2018-07-01 17:07:15 -0400) <Mike Fikes>
* 892ed202 - CLJS-2798: ChunkCons -next doesn't handle nil more (2018-07-01 09:14:40 -0400) <Mike Fikes>
* e173e54d - CLJS-844: Optimize js->clj by switching to transients (2018-06-29 14:58:58 -0400) <Thomas Spellman>
* c2b864d5 - rename :f to :fn in :invoke op (2018-06-29 13:50:21 -0400) <Ambrose Bonnaire-Sergeant>
* 9cae8d21 - rename :expr entry to :body in :let/:loop op (2018-06-29 13:50:20 -0400) <Ambrose Bonnaire-Sergeant>
* c89f5e2b - rename :expr entry to :body in :letfn op (2018-06-29 13:50:20 -0400) <Ambrose Bonnaire-Sergeant>
* 3b4e8721 - rename :try entry to :body in :try op (2018-06-29 13:50:20 -0400) <Ambrose Bonnaire-Sergeant>
* 0765fc73 - rename :throw entry to :exception in :throw op (2018-06-29 13:50:20 -0400) <Ambrose Bonnaire-Sergeant>
* c9876637 - add :ns entry to :def op (2018-06-29 13:50:20 -0400) <Ambrose Bonnaire-Sergeant>
* 19e5727e - CLJS-2589: allow / as a protocol method name in cljs (2018-06-29 13:46:54 -0400) <henryw374>
* 9a8196eb - CLJS-2442: `set` and `vec` performance enhancements (2018-06-25 16:10:56 -0400) <Mike Fikes>
* b1ade48e - (tag: untagged-9fd5e90b7e1d1b96ed0e, tag: r1.10.339) 1.10.339 (2018-06-25 10:47:49 -0400) <David Nolen>
* b2b13e37 - add transit-clj to deps.edn (2018-06-25 10:28:07 -0400) <David Nolen>
* d91207cb - CLJS-2790: Externs inference warnings for defrecord fields (2018-06-25 10:23:27 -0400) <Mike Fikes>
* 44379a6e - CLJS-2791: transit-cli should be bumped in pom.template.xml (2018-06-25 10:20:08 -0400) <Mike Fikes>
* ef3a22d1 - (tag: r1.10.335) 1.10.335 (2018-06-24 20:48:30 -0400) <David Nolen>
* 4f477f7b - bump transit in bootstrap and project.clj (2018-06-24 20:46:12 -0400) <David Nolen>
* a7c0899e - rename :meta op to :with-meta (2018-06-23 08:40:47 -0400) <Ambrose Bonnaire-Sergeant>
* 70910c21 - rename :defrecord* op to :defrecord (2018-06-23 08:40:47 -0400) <Ambrose Bonnaire-Sergeant>
* 62f967b6 - rename :deftype* op to :deftype (2018-06-23 08:40:46 -0400) <Ambrose Bonnaire-Sergeant>
* 93496676 - rename :var-special op to :the-var (2018-06-23 08:40:46 -0400) <Ambrose Bonnaire-Sergeant>
* 359d34ef - (tag: untagged-486000476a5174cad3b5, tag: r1.10.329) 1.10.329 (2018-06-22 17:00:59 -0400) <David Nolen>
* e58b4352 - 1.10.328 (2018-06-22 16:54:07 -0400) <David Nolen>
* 5e426036 - CLJS-2787: Record comparison is broken when instance is constructed from another record instance via map factory (2018-06-22 16:48:44 -0400) <David Nolen>
* 3620434c - add :val to :const node (2018-06-22 16:10:15 -0400) <Ambrose Bonnaire-Sergeant>
* c7c7449b - rename ast op :constant -> :const (2018-06-22 16:10:15 -0400) <Ambrose Bonnaire-Sergeant>
*   68ff2499 - Merge remote-tracking branch 'origin/master' (2018-06-17 10:06:10 -0400) <David Nolen>
|\  
| * 5dc641ed - CLJS-2780: Async tests prematurely terminate in Node (2018-06-16 22:25:45 -0400) <Mike Fikes>
* | abf4e784 - CLJS-2784: Special handling of com breaks requires (2018-06-17 10:06:01 -0400) <David Nolen>
|/  
* 6048bc95 - CLJS-2783: with-out-str conflicts with :infer-externs (2018-06-16 22:06:24 -0400) <David Nolen>
* 7528dc79 - CLJS-2730 Fix docstrings in filter, filtev, remove, and take-while (2018-06-16 10:31:27 -0400) <Erik Assum>
* 6d23b1f4 - CLJS-2703: module name substitution test fails if hypen in directory path (2018-06-15 17:29:56 -0400) <Mike Fikes>
* b8101e63 - CLJS-2731: Failure comparing sorted sets (2018-06-15 17:23:53 -0400) <Jeremy R. Sellars>
* 6950aff4 - CLJS-2746 Missing provides for JS modules (2018-06-15 16:57:09 -0400) <Corin Lawson>
* ec1e416b - CLJS-2770: Problem with namespaces starting with com in Rhino (2018-06-15 16:48:21 -0400) <David Nolen>
* 343e6aef - CLJS-2772: Trying to run `cljs.main` repl with `:modules` results in `brepl_deps.js` being `clojure.lang.LazySeq` (2018-06-15 16:22:29 -0400) <David Nolen>
* 6b7e9402 - CLJS-2736: Elements returned from sets as functions are not the actual elements in the set (2018-06-15 15:28:06 -0400) <David Nolen>
* 9100030b - CLJS-2298: REPLs should automatically load user.(cljs|cljc) files at root of Java classpath (2018-06-15 14:16:14 -0400) <Mike Fikes>
* 6512df83 - (tag: r1.10.312) 1.10.312 (2018-06-15 08:39:47 -0400) <David Nolen>
* 18b97ab8 - CLJS-2278 & CLJS-2279 (2018-06-15 08:14:02 -0400) <David Nolen>
* 3b0ce12d - goog.global lookup must be a string (2018-06-14 18:52:44 -0400) <dnolen>
* 23d97da8 - CLJS-2777: Bump Closure-compiler (2018-06-14 13:47:28 -0400) <Juho Teperi>
* e75706a1 - CLJS-2775: cljs.main: Node modules not installed if -re node (2018-06-13 17:40:19 -0400) <Mike Fikes>
*   fed0807f - Merge branch 'cljs-2767' (2018-06-13 17:38:44 -0400) <dnolen>
|\  
| * 693cd0da - (origin/cljs-2767) ignore exists? (2018-06-13 17:33:07 -0400) <dnolen>
| * 1c4eefac - CLJS-2767: Externs inference warnings for defrecord and deftype (2018-06-13 11:57:33 -0400) <David Nolen>
* | 43bc1482 - continue to allow :main to be a string (2018-06-13 15:12:07 -0400) <David Nolen>
* | 3d44a6f5 - validate :main (2018-06-13 14:34:05 -0400) <David Nolen>
|/  
* 00b8dea4 - CLJS-2754: Broken cli tests (2018-06-12 10:52:54 -0400) <Mike Fikes>
* 0ffe3d8f - CLJS-2771: Elide "use strict" from final output (2018-06-11 09:35:41 -0400) <Pieter du Toit>
* 215cd3c9 - CLJS-2769: Eliminate goog.structs.AvlTree.Node in self-parity test (2018-06-10 09:53:17 -0400) <Mike Fikes>
* aab9710a - elide "use strict" after we've prepended foreign deps (2018-06-08 10:48:37 +0200) <David Nolen>
* 03455b4b - elide "use strict" and 'use strict' by default from all production builds. Add flag to disable the behavior. (2018-06-07 13:28:11 +0200) <David Nolen>
* 3d5553d9 - test -> test-clj alias (2018-06-07 12:52:52 +0200) <David Nolen>
* 571dc6ff - add Clojure test runner to deps.edn (2018-06-07 12:41:04 +0200) <David Nolen>
* 9a4e89ef - CLJS-2766: Revisions to exists? fails in self-host (2018-06-04 10:14:27 -0400) <Mike Fikes>
* 72e99c5d - exists? should continue to support some? style usage (2018-06-01 15:39:10 -0400) <dnolen>
* d5e8da2a - assert that argument to exists? is a symbol, clarify docstring (2018-06-01 15:33:18 -0400) <dnolen>
* 80c77a2f - exists? should ignore js/ (2018-06-01 15:21:55 -0400) <dnolen>
* cae47920 - return false for gitlibs-src? for now (2018-06-01 14:25:17 -0400) <dnolen>
* 9f8ad161 - CLJS-2764: exists? is not nil safe (2018-06-01 14:23:02 -0400) <dnolen>
* ab00c862 - CLJS-2760 Make browser repl web-severs mime-type case-insensitive (2018-06-01 12:45:52 -0400) <Erik Assum>
* 2ad14709 - CLJS-1871: A declare with :arglists should generate static function calls (2018-05-25 13:32:02 -0400) <Mike Fikes>
* 56ea8ee0 - CLJS-2755: Can't generate uri instances (2018-05-21 17:01:20 -0400) <Mike Fikes>
* 27c27e0b - CLJS-1677: Requiring [goog] breaks an :advanced build, but the compiler exits successfully (2018-05-21 17:00:19 -0400) <Mike Fikes>
* 2e15a5cc - CLJS-2688 cljs.main: Accumulate all meaningful repeated inits modules using global-exports (2018-05-18 16:52:00 -0400) <dnolen>
* 92c9d60c - CLJS-2681: Accepting multiple paths to the --watch option for cljs.main (2018-05-18 16:44:17 -0400) <Petter Eriksson>
* e2310802 - CLJS-2651: Shared AOT cache: Support git deps (2018-05-18 16:38:07 -0400) <Mike Fikes>
* adeaa9be - Recompile cljs.loader in REPL (2018-05-18 14:11:43 -0400) <r0man>
* fb312cde - CLJS-2733: Throw error message if too few or too many args to throw (2018-05-18 14:01:22 -0400) <David Nolen>
* 0cdbb23a - CLJS-2751: script/bootstrap --closure-library-head misses goog/text (2018-05-18 11:37:20 -0400) <Mike Fikes>
* 8e723a6f - CLJS-2480: Periods at end of analyzer warnings (2018-05-18 11:35:57 -0400) <Mike Fikes>
* 1d784517 - CLJS-2618 Fix docstring for `remove-tap` (2018-05-18 11:34:55 -0400) <Erik Assum>
* 0ef73e0d - CLJS-2743 Fix docstring misspelling (2018-05-18 11:33:12 -0400) <Erik Assum>
* 78753d37 - CLJS-2724: Native Node modules Node (like "fs") cannot be required (2018-05-07 16:47:38 -0400) <Mike Fikes>
* b31a9a9b - CLJS-2702: Accomodate new Closure Library dependency loading strategy (2018-05-07 16:43:14 -0400) <Mike Fikes>
* e4e4a295 - CLJS-2741: Function invoke errors report arity off by 1 (2018-05-07 16:38:56 -0400) <Mike Fikes>
* a3039bd3 - CLJS-2745: Add GraalVM to the set of JavaScript engines we can test against (2018-05-07 16:31:03 -0400) <Mike Fikes>
* e577d46c - CLJS-2739: Optimize node_modules indexing (2018-05-07 15:53:14 -0400) <Mike Fikes>
