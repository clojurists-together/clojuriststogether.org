## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve with this funding?
- Continue  work  on  improving  stability
- Continue  working  on  usability
- Improve  the  documentation  for  the  project
- I  want  to  sort  out  the  "template"  situation  since  generating  a  basic  project  is  a  very  common  request.

### Why is this project important to the Clojure community?

To  work  on  developing  the  shadow-cljs  build  tool.  It  covers  the  entire  spectrum  from  development  and  testing,  to production  builds.  It  assumes  no  prior  knowledge  of  the  JVM  or  Clojure  to  get  started,  which  makes  it  more  accessible  to  a  wider  audience.

## Work log

### Improving Stability

- I reverted the launcher changes I did recently due to some compatibility issues where `tools.deps` was handling dependencies differently than `pomegranate` and thus breaking builds that were working previously. Need to investigate this a bit more before making the switch again. There were also a few classloader issues that need to be sorted out.
- Fixed an issue with CSS reloading in [Electron](https://github.com/thheller/shadow-cljs/commit/17309326ab440b86618294b31f468f6c40438800)
- Fixed an `ns` parsing [issue](https://github.com/thheller/shadow-cljs/issues/377) where an invalid `ns` form would pass without warnings
- Switched HTTP handling to use mostly Undertow built-in functionality for serving files to support Range requests with `206 Partial Content` responses and `Transfer-Encoding: chunked`.

### Increasing Usability 

- Started reworking the shadow-cljs [standalone launcher](https://clojureverse.org/t/poll-reworking-the-launcher/2633) which is responsible for downloading dependencies and launching the actual tool. Intent here is to gain access to git-deps and dynamically loading dependencies without requiring a hard restart.
- Started publishing AOT compiled shadow-cljs versions which can significantly [improve start-up times](https://clojureverse.org/t/faster-startup-via-aot/2603). Used by default when using the standalone launcher.
- Massively increased performance of Source Maps handling. The raw data was way too costly to serialize to disk and would often take longer than actual compilation. Instead now only the compacted VLQ encoded data is stored. Cache reads/writes are significantly faster, eg. `cljs.core` used to take 2.5s to write the cache and now `~250ms`.
- Increased parallel compilation speeds by splitting analysis and "compilation" (ie. converting AST to JS code) into two different phases. Parallel compilation used to wait for both but now only waits for analysis since that's all that's required.

#### UI Work

- The UI is still a Work-in-Progress but it is now possible to control builds via the Web UI. Builds can be started/stopped and inspected (although the UI doesn't visualize this well yet).
- Created a [preview video](https://clojureverse.org/t/shadow-cljs-ui-preview/2826) of the shadow-cljs UI. Tweaked the UI to display warnings better.
- [WIP] Implemented basic REPL support in the UI
- [WIP] Implemented basic standalone Launcher using Electron

#### Bugfixes

-  Fixed an issue with [npm link](https://github.com/thheller/shadow-cljs/commit/d59b91d5dbc83a1b2b9147a3f55d3fd38077f099)
- fixed a [REPL issue](https://github.com/thheller/shadow-cljs/commit/f6694aaa5459591556a5e83f939885b70924d3b0) related to require and browser reload
- added support for [overriding npm resolves](https://github.com/thheller/shadow-cljs/commit/a61eea6d7f74fb7d0d806bc030442cf554ab5a24) to exclude packages from a build
- fixed a [race condition](https://github.com/thheller/shadow-cljs/commit/f321b390d52b69bf89e4568cf096a8d51e04575c) related to async loading the web-related namespaces
- Stricter checks for `:modules` when `:entries` get moved out of their specified module with incorrect `:require` dependency graphs.
- Fixed all test targets to ensure that `:runner-ns` is always compiled last to ensure side-effecting macros return the expected results.
- Fixed a bug in classpath Closure JS processing (ie. none `node_modules/**.js` files) where cache would increase exponentially in size on each compile.


#### New features

- Added support for private `s3` maven repos via [s3-wagon-private](https://github.com/s3-wagon-private/s3-wagon-private)

#### Minor changes

- Added support for [:parallel-build false](https://github.com/thheller/shadow-cljs/commit/99741e3edd07ef8ba8a20e5fc3e2e0cad14051ad), `true` by default but wasn't configurable before.
- Copied [clojure.core.specs.alpha](https://github.com/thheller/shadow-cljs/commit/0416ea27e9a031c4a39c49df820855aa4b72575c) and adjusted them for CLJS. Clojure 1.10 alpha renamed a few specs and broke the direct use that was previously used.

## Commits

$ git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="AUGUST 1 2018" --until "OCTOBER 31 2018"

* 64c74fc - (tag: 2.6.21) bump 2.6.21 (2018-10-30 09:53:52 +0100) <Thomas Heller>
* 673325e - change dev proxy server setup and add more options (2018-10-30 09:49:56 +0100) <Thomas Heller>
* 4f3f9ff - (tag: 2.6.20) bump 2.6.20 (2018-10-30 08:58:03 +0100) <Thomas Heller>
* f10dc37 - add support for https :proxy-url in dev server (2018-10-30 08:57:14 +0100) <Thomas Heller>
* aea5896 - (tag: 2.6.19) bump 2.6.19 (2018-10-26 22:19:53 +0200) <Thomas Heller>
* f5ea6c2 - run compile/release/check sequentially when called from the CLI (2018-10-26 22:16:04 +0200) <Thomas Heller>
*   f2022ee - Merge branch 'master' of github.com:thheller/shadow-cljs (2018-10-26 22:12:40 +0200) <Thomas Heller>
|\  
| * db42782 - [ Summary ] Handle resources for both junction and symlink on windows. (#400) (2018-10-26 22:11:49 +0200) <Huabin Zhang>
* | db7d19b - change exception handling in log printer (2018-10-26 12:08:04 +0200) <Thomas Heller>
|/  
* d4960a1 - (tag: 2.6.18) bump 2.6.18 (2018-10-25 13:08:57 +0200) <Thomas Heller>
* 6f39dcb - allow undertow file handler to follow links (2018-10-25 13:07:36 +0200) <Thomas Heller>
* fc4739f - add application/wasm mime type (2018-10-24 23:31:37 +0200) <Thomas Heller>
* 1272b3b - (tag: 2.6.17) bump 2.6.17 (2018-10-24 17:11:51 +0200) <Thomas Heller>
* 7cef4ef - fix closure js conversion cache bug (2018-10-24 17:08:38 +0200) <Thomas Heller>
* e12d59b - initialize REPL after build config (2018-10-24 11:41:34 +0200) <Thomas Heller>
* 3caec04 - (tag: 2.6.16) bump 2.6.16 (2018-10-24 01:00:28 +0200) <Thomas Heller>
* 9c87128 - fix broken live reload due to bad OPTIONS request handling (2018-10-24 00:14:42 +0200) <Thomas Heller>
* 1d21049 - (tag: 2.6.15) bump 2.6.15 (2018-10-23 17:00:38 +0200) <Thomas Heller>
* 427defa - ensure that all test namespaces are compiled before runner-ns (2018-10-23 13:26:32 +0200) <Thomas Heller>
* 1361d7a - fix build log update for UI (2018-10-22 20:58:29 +0200) <Thomas Heller>
* a0bc3f2 - start using undertow handlers instead of ring (2018-10-22 20:54:04 +0200) <Thomas Heller>
* c43ed25 - bring raw log back into UI for now (2018-10-22 14:27:02 +0200) <Thomas Heller>
* 899bbcc - check if specified :entries get moved out of their module (2018-10-21 16:37:29 +0200) <Thomas Heller>
* 1894ffb - (tag: 2.6.14) bump 2.6.14 (2018-10-18 21:07:37 +0200) <Thomas Heller>
* 14a8621 - no need to recompile files with warnings with every cycle (2018-10-18 20:48:28 +0200) <Thomas Heller>
* c1a71d2 - tweak parallel compilation (2018-10-18 14:30:41 +0200) <Thomas Heller>
* 2e08f24 - optimize source map handling (2018-10-18 12:03:00 +0200) <Thomas Heller>
* c9c7a15 - (tag: 2.6.13) bump 2.6.13 (2018-10-17 20:07:19 +0200) <Thomas Heller>
* f32e40f - add back nashorn flag with version check (2018-10-17 20:03:26 +0200) <Thomas Heller>
* fca9d35 - fix build-failure print for the console (2018-10-17 15:06:52 +0200) <Thomas Heller>
* d5dbd0d - use a more robust way to launch external processes (hopefully) (2018-10-17 12:02:48 +0200) <Thomas Heller>
* 8f817ab - add sentry/electron for exception reports (2018-10-17 09:43:11 +0200) <Thomas Heller>
* 0a368e2 - (tag: 2.6.12) bump 2.6.12 (2018-10-16 09:38:22 +0200) <Thomas Heller>
* 97b4a2b - remove nashorn deprecation warning flag (2018-10-16 09:37:34 +0200) <Thomas Heller>
* f0cb5e8 - (tag: 0.2.11) bump 2.6.11 (2018-10-15 23:37:00 +0200) <Thomas Heller>
* 11b97bb - make sure only cljs+goog names affect munging (2018-10-15 23:26:16 +0200) <Thomas Heller>
* 86dd1ff - make sure the CLI check thread doesn't end up keeping the JVM alive (2018-10-15 23:04:07 +0200) <Thomas Heller>
* 33fd629 - ensure java process exits when shadow-cljs node process is killed (2018-10-15 22:23:21 +0200) <Thomas Heller>
* ed5590e - fix race condition in cljs.compiler/munge (2018-10-15 22:22:44 +0200) <Thomas Heller>
* 3fc6c19 - change REPL impl to allow option :ns attribute (via nREPL) (2018-10-15 19:44:12 +0200) <Thomas Heller>
* d77c885 - get rid of nashorn deprecation warning on jdk11 (2018-10-15 19:42:47 +0200) <Thomas Heller>
* 4f20d59 - remove unused nrepl refers (2018-10-13 11:53:09 +0200) <Thomas Heller>
* 781e49e - fix emacs temp file filtering on windows (2018-10-13 11:52:29 +0200) <Thomas Heller>
* f0b3695 - add missing launcher css (2018-10-10 09:59:22 +0200) <Thomas Heller>
* e53cb11 - separate build script for win/mac (2018-10-10 09:58:06 +0200) <Thomas Heller>
* be3e86c - fix file perms, resize icon for osx (2018-10-10 09:57:35 +0200) <Thomas Heller>
* 8aca2e7 - build scripts (2018-10-10 09:33:16 +0200) <Thomas Heller>
* 8e0246c - [WIP] launcher packaging, ui tweaks (2018-10-10 09:26:44 +0200) <Thomas Heller>
* 70af5be - [WIP] launcher stuff (2018-10-06 14:17:54 +0200) <Thomas Heller>
* de065f2 - update Closure Java API changes (2018-10-06 09:55:27 +0200) <Thomas Heller>
* 1b88a93 - bump deps (2018-10-06 09:47:56 +0200) <Thomas Heller>
* 7ec8453 - fix npm-deps install failing on windows (2018-10-02 09:42:23 +0200) <Thomas Heller>
* 741a8fa - fix proc.kill on windows (2018-10-02 08:55:16 +0200) <Thomas Heller>
* cb04d78 - [WIP] launcher basic process management, kill doesn't work (2018-10-01 21:43:20 +0200) <Thomas Heller>
* 522929d - make module :preloads dev-only too (2018-10-01 08:28:20 +0200) <Thomas Heller>
* 48c55dc - prevent name collision on PrintWriter-on with clj 1.10 alphas (2018-09-30 18:35:15 +0200) <Thomas Heller>
* d9f7468 - allow specifying :preloads per :modules entry (2018-09-30 18:32:43 +0200) <Thomas Heller>
* ad4a3c4 - [WIP] first few bits of the electron launcher UI (2018-09-30 18:21:40 +0200) <Thomas Heller>
* 5f1c6cf - (tag: 0.2.10) bump 2.6.10 (2018-09-30 18:21:33 +0200) <Thomas Heller>
* f321b39 - fix loading race condition (2018-09-24 22:46:09 +0200) <Thomas Heller>
* 1f44c60 - bump fulcro (2018-09-22 18:26:16 +0200) <Thomas Heller>
* a56205f - prep for upcoming analyzer :var changes (2018-09-22 18:24:29 +0200) <Thomas Heller>
* 5c29b22 - (tag: 2.6.9) bump 2.6.9 (2018-09-20 17:51:39 +0200) <Thomas Heller>
* 2ce6fcc - [WIP] UI REPL (2018-09-20 17:48:16 +0200) <Thomas Heller>
* a61eea6 - fix :js-options :resolve package to false (2018-09-20 17:47:52 +0200) <Thomas Heller>
* f6694aa - REPL should make no assumptions about the namespaces loaded by the runtime (2018-09-17 15:13:27 +0200) <Thomas Heller>
* fbac46a - [WIP] minimal chrome extension for UI (2018-09-16 12:51:51 +0200) <Thomas Heller>
* 105d377 - ensure that babel-worker.js is updated properly (2018-09-16 09:28:33 +0200) <Thomas Heller>
* dfa1515 - (tag: 2.6.8) bump 2.6.8 (2018-09-15 20:10:01 +0200) <Thomas Heller>
* f09237f - update resolve-var hacks in prep for next cljs release (2018-09-15 10:07:08 +0200) <Thomas Heller>
* 0e7ac52 - format warnings a little bit more, add proper favicon (2018-09-11 16:18:19 +0200) <Thomas Heller>
* 6e8ea57 - (tag: 2.6.7) bump 2.6.7 (2018-09-11 10:15:50 +0200) <Thomas Heller>
* 2234802 - remove redundant wrapping component (2018-09-11 10:07:56 +0200) <Thomas Heller>
* 99741e3 - add support for :parallel-build false (no threads) (2018-09-11 10:06:34 +0200) <Thomas Heller>
* 532fb53 - [WIP] UI hooking up more features (2018-09-10 20:59:05 +0200) <Thomas Heller>
* 30e1844 - minor style tweaks (2018-09-07 10:04:19 +0200) <Thomas Heller>
* 8c9b391 - add local roboto font (2018-09-07 09:43:19 +0200) <Thomas Heller>
* 88d533b - [WIP] UI starting to get somewhat usable (2018-09-06 20:01:34 +0200) <Thomas Heller>
* 0416ea2 - port latest core.specs.alpha (2018-09-05 20:08:45 +0200) <Thomas Heller>
* f0dda3e - [WIP] big UI restructure (2018-09-05 16:46:36 +0200) <Thomas Heller>
* 1e8e187 - (tag: 2.6.6) bump 2.6.6 (2018-08-31 18:20:20 +0200) <Thomas Heller>
* 748e1ec - bump 2.6.5 (2018-08-31 13:25:28 +0200) <Thomas Heller>
* 3d9d81a - [WIP] UI work (2018-08-31 13:22:32 +0200) <Thomas Heller>
* 8a89341 - use a volatile to track par-compile progress (2018-08-31 10:26:54 +0200) <Thomas Heller>
* 4129249 - fix watch with autobuild disabled issue (2018-08-30 18:48:10 +0200) <Thomas Heller>
* 873924f - don't filter clojure-future-spec because of the clojure.future ns (2018-08-30 18:28:42 +0200) <Thomas Heller>
* fb9e0c3 - can't figure this out (2018-08-28 23:48:55 +0200) <Thomas Heller>
* 6980541 - try cleaning up the keyword alias madness (2018-08-28 23:25:43 +0200) <Thomas Heller>
* d59b91d - only use absolute paths in npm, not replacing links (2018-08-28 23:25:29 +0200) <Thomas Heller>
* 5bcb83f - (tag: 2.6.4) 2.6.4 (2018-08-28 13:09:11 +0200) <Thomas Heller>
* daf9e3b - first basic UI setup (2018-08-28 12:42:05 +0200) <Thomas Heller>
* 3d52c4f - (tag: 2.6.3) bump 2.6.3 (2018-08-24 14:43:08 +0200) <Thomas Heller>
* 1730932 - fix electron css reloading (2018-08-24 14:40:38 +0200) <Thomas Heller>
* bcf1f3a - missing files (2018-08-24 12:48:15 +0200) <Thomas Heller>
* 179f591 - add built-in browser-test/workspaces build pages (2018-08-24 12:31:57 +0200) <Thomas Heller>
* c1f8b0d - removed unused dep (2018-08-24 10:56:32 +0200) <Thomas Heller>
* bb8507d - fix last minute var rename (2018-08-24 10:51:10 +0200) <Thomas Heller>
* 8208109 - first draft of pathom driven graph API endpoint (2018-08-24 10:47:52 +0200) <Thomas Heller>
* 51f5cda - temp fix for exists? code gen issue (2018-08-22 22:39:41 +0200) <Thomas Heller>
* f64aff5 - stricter ns parsing for :require-macros (2018-08-22 08:37:35 +0200) <Thomas Heller>
* 1e9c3ef - new command-aware CLI parsing (2018-08-21 19:42:13 +0200) <Thomas Heller>
* b56e293 - more tweaks (2018-08-21 19:20:00 +0200) <Thomas Heller>
* 98e5cf3 - tweak deps tree output (2018-08-21 18:54:46 +0200) <Thomas Heller>
* ff8987c - add dependency graph printer (2018-08-21 17:58:09 +0200) <Thomas Heller>
* bfac7e1 - ignore more deps that are known to cause problems (2018-08-21 16:39:42 +0200) <Thomas Heller>
* dc00dfd - add additional s3p fix, print stacktrace on errors (2018-08-21 10:20:45 +0200) <Thomas Heller>
* 26cbb11 - try adding support for s3-wagon-private (2018-08-21 08:58:42 +0200) <Thomas Heller>
* e936d72 - (tag: 2.6.2) bump 2.6.2 (2018-08-20 23:11:27 +0200) <Thomas Heller>
* 016c0c8 - best effort nrepl cleanup on worker exit (2018-08-20 23:03:37 +0200) <Thomas Heller>
* da96615 - also enable :preloads in :karma and :browser-test (2018-08-20 11:15:47 +0200) <Thomas Heller>
* 3c52152 - fix :preloads injection in :node-test (2018-08-20 11:13:43 +0200) <Thomas Heller>
* fc3e745 - fix js-invalid-requires log missing resource-name (2018-08-20 11:05:50 +0200) <Thomas Heller>
* 8cca2cb - fix create-cljs-project windows issue (2018-08-19 00:48:53 +0200) <Thomas Heller>
* 7173529 - (tag: 2.6.1) bump 2.6.1 (2018-08-19 00:09:51 +0200) <Thomas Heller>
* 6a6a95d - delay installing babel until its actually required (2018-08-19 00:02:48 +0200) <Thomas Heller>
* 97d25b8 - add initial project template script (2018-08-18 22:49:22 +0200) <Thomas Heller>
* 4601153 - fix worker using wrong fs-watch namespace (2018-08-18 22:49:12 +0200) <Thomas Heller>
* bfb29ad - support :preloads in :node-test target (2018-08-18 18:55:20 +0200) <Thomas Heller>
* aa94acf - (tag: 2.6.0) bump 2.6.0 (2018-08-17 18:20:24 +0200) <Thomas Heller>
* ed0fae7 - fix CI build (2018-08-17 18:16:04 +0200) <Thomas Heller>
* c0edec8 - revert launcher change to 2.4.33 (2018-08-17 18:08:59 +0200) <Thomas Heller>
* 53bfd3f - fix closure variable naming issue (2018-08-17 14:12:59 +0200) <Thomas Heller>
* c257ff6 - reworks cljs-hacks to be more AOT friendly (2018-08-16 21:42:32 +0200) <Thomas Heller>
* 586e08a - fix node-repl getting unusable after node process exit (2018-08-16 18:37:25 +0200) <Thomas Heller>
* 779cb62 - fix node-repl issue related to recent relative path change (2018-08-16 18:06:46 +0200) <Thomas Heller>
* 2f4a0ea - drop CLJS slim classifier due to issues in lein (2018-08-16 10:01:21 +0200) <Thomas Heller>
* 19cc640 - fix bad merge related to reload-flags in require and ns (2018-08-15 23:50:11 +0200) <Thomas Heller>
* c3838e1 - (tag: launcher-2.1.0, tag: 2.5.1) bump 2.5.1, launcher 2.1.0 (2018-08-14 22:45:27 +0200) <Thomas Heller>
* 7c0ef10 - basic support for reloading deps, adding deps without restart. (2018-08-14 22:40:21 +0200) <Thomas Heller>
* fee6a20 - (tag: 2.5.0) bump 2.5.0 (2018-08-12 11:13:59 +0200) <Thomas Heller>
* a55c653 - fix launcher location, file encoding, typo. (2018-08-12 11:06:47 +0200) <Thomas Heller>
* f310f5f - (tag: launcher-2.0.0) launcher rework (2018-08-11 22:22:20 +0200) <Thomas Heller>
* 86dfd91 - fix CI config? (2018-08-08 23:10:57 +0200) <Thomas Heller>
* c01f428 - remove shadow-cljs-jar package dependency (2018-08-08 23:01:33 +0200) <Thomas Heller>
* 71403a6 - use relative paths in :node-script dev builds (2018-08-08 18:54:00 +0200) <Thomas Heller>
* f536575 - (tag: 2.4.33) bump 2.4.33 (2018-08-06 23:46:55 +0200) <Thomas Heller>
* e9bd399 - revert java.classpath removal, required for boot to work (2018-08-06 23:45:42 +0200) <Thomas Heller>
* ba67074 - cleanup some warnings getting printed to the wrong places (2018-08-06 18:09:05 +0200) <Thomas Heller>
* 901d153 - (tag: 2.4.32) bump 2.4.32 (2018-08-06 17:36:46 +0200) <Thomas Heller>
* 6282b06 - oops didn't mean to disable this (2018-08-06 17:13:07 +0200) <Thomas Heller>
* 1fb0b08 - replace clojure.tools.logging with simplified logger (2018-08-06 16:55:16 +0200) <Thomas Heller>
* 64631b8 - (tag: 2.4.31) bump 2.4.31 (2018-08-06 09:49:57 +0200) <Thomas Heller>
* 95221a9 - fix nrepl port getting lost (2018-08-06 09:49:17 +0200) <Thomas Heller>
* 29be35e - (tag: 2.4.30) bump 2.4.30 (2018-08-05 23:19:49 +0200) <Thomas Heller>
* cb5eda7 - fix bad ns reference and file encoding (2018-08-05 23:19:20 +0200) <Thomas Heller>
* b3fcdad - (tag: 2.4.29) bump 2.4.29 (2018-08-05 22:58:22 +0200) <Thomas Heller>
* 6435fc7 - revert 2.4.25 nrepl changes and try different appraoch (2018-08-05 22:57:26 +0200) <Thomas Heller>
* cb833fd - (tag: 2.4.28) bump 2.4.28 (2018-08-04 17:36:07 +0200) <Thomas Heller>
* cf008dc - bump 2.4.27 (2018-08-04 17:13:29 +0200) <Thomas Heller>
* 190c800 - create an AOT compiled artifact for the standalone version (2018-08-04 17:03:58 +0200) <Thomas Heller>
* b49a803 - Use newer Buffer API instead of deprecated one (#359) (2018-08-03 21:35:52 +0200) <Samuel Wagen>
* 0a3f6f7 - bump closure, remove deprecated AMD transform option (2018-08-03 00:09:16 +0200) <Thomas Heller>
* 5b91df9 - fix load-file issue on windows, can't just (str ... "/") there (2018-08-02 20:28:41 +0200) <Thomas Heller>
* 6231331 - fix outdated cursive-repl helper (2018-08-02 20:28:19 +0200) <Thomas Heller>