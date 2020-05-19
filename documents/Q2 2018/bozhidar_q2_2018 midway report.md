## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve?

    • Internal improvements
        ◦ break down `cider-interaction.el` and remove this file completely
        ◦ improve the connection management (https://github.com/clojure-emacs/cider/pull/2069)
        ◦ improve nREPL callback handling (https://github.com/clojure-emacs/cider/issues/1099) (stretch goal)
        ◦ better handling for huge output/results (we can warn users about it, truncate it in the REPL and store the whole result internally, etc)
    • Better ClojureScript support
        ◦ Make it easier to start ClojureScript REPLs
            ▪ Implement some deps injection for ClojureScript REPLs (stretch goal)
            ▪ Providing meaningful errors when starting ClojureScript REPLs
            ▪ Make it possible to have a project with only a ClojureScript REPL
            ▪ Merge cljs-tooling into orchard and evolve it a bit (under consideration, might be better to keep it a separate library)
            ▪ Add ability to restart a ClojureScript REPL (https://github.com/clojure-emacs/cider/issues/1874)
        ◦ Always show meaningful errors if a command is not supported under ClojureScript

### Why is this project important to the Clojure community?

A lot of people are using it directly (or indirectly) - CIDER is popular by itself and many other editors use its additional middleware. CIDER is the most widely adopted Clojure development environment. In the most recent [Clojure survey](http://blog.cognitect.com/blog/2017/1/31/clojure-2018-results) CIDER was the primary development environment for 50% of survey respondents. Additionally, in our most recent member survey, it featured highly as a project important to our members workflows.

## Work log

### Improving nREPL

* finished the nREPL migration out of clojure-contrib (the 0.4.0 marks the end of the breaking changes)
* Provided a PR for reply to switch to the new nREPL https://github.com/trptcolin/reply/pull/182
* migrated to the new org and updated it for nREPL 0.4 https://github.com/nrepl/drawbridge
* fixed a couple of small nREPL issues
* started work on lein/boot plugins to boot the new nREPL from cider-jack-in
* I've also opened PRs for lein and boot for the new nREPL - https://github.com/boot-clj/boot/pull/703 and https://github.com/technomancy/leiningen/pull/2444

### Improving related projects/libraries

These are projects and libraries that CIDER makes use of, but are written in such a way that they could be useful to other IDE's and Clojure tooling.

* extracted the var-info functionality from cider-nrepl to orchard (https://github.com/clojure-emacs/orchard/commit/b7ceb98b3d0b1f1035f9586a127cd23b3d18ff5c)
* Extracted the bencoding from nREPL into a library we hope to make portable https://github.com/nrepl/bencode

### ClojureScript support

* added basic cljs deps injection to CIDER on cider-jack-in-cljs
* Improved Clojure's dependency checking a bit to be aware of group ids and versions

* A new release of clojure-mode.el (performance improvements and integration with project.el, needed for the new connection management in CIDER) https://github.com/clojure-emacs/clojure-mode/commits/master

### Improving connection management

* The new connection management for CIDER is now in master (another person did most of the work, as after I announced I planned to rewrite it in the scope of the funding he expressed the desire to pick up an old PR he had created about this). Afterwards I did a few rounds of testing and bug-fixing together with him. 

### Internal improvements

* I completed the removal of cider-interaction.el and restructured the code quite a lot in the process.
* I did many improvements to the manual (+ a new domain for CIDER - http://docs.cider.mx).
* Some other small improvements would be https://github.com/clojure-emacs/cider/commit/13c76efcfc94f3c97183962f91146a851bd7a9d3 and https://github.com/clojure-emacs/cider/commit/c658d8a759adc187da70efaacf800fdc0f852021

## Commits

git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="MAY 1 2018" --until "JUNE 30 2018"

### clojure-emacs/cider

* 5992565 - Fix classpath libs functionality on Windows (#2356) (2018-06-30 00:11:03 +0200) <Kevin Jiang>
* 0ca47cb - [Fix #2317] Allow cancelling stdin prompt (2018-06-30 00:09:31 +0200) <Timo Freiberg>
* 571036e - Fix the test selector filtering functionality (2018-06-28 15:28:51 +0200) <Bozhidar Batsov>
* 24db03d - [#2342] Add a cider-current-connection alias for cider-current-repl (2018-06-28 08:48:41 +0200) <Bozhidar Batsov>
* f504aa1 - [#2342] Add an obsolete alias for cider-current-repl-buffer (2018-06-28 08:40:08 +0200) <Bozhidar Batsov>
* 4da871b - Group a couple of related tips (2018-06-26 08:23:18 +0300) <Bozhidar Batsov>
* 89e77f3 - Add a few extra tips (2018-06-26 08:22:46 +0300) <Bozhidar Batsov>
* 02dc4ac - Move the cider-find-var functionality to cider-find.el (2018-06-26 08:12:19 +0300) <Bozhidar Batsov>
* d7d6821 - Use more specific requires in the tests (2018-06-25 12:25:26 +0300) <Bozhidar Batsov>
* 0758f72 - Use a more specific require in a test (2018-06-25 12:15:39 +0300) <Bozhidar Batsov>
* cb581f3 - Update the roadmap document (2018-06-25 09:57:54 +0300) <Bozhidar Batsov>
* ac9cb61 - [Fix #2203] Rename cider-interaction.el to cider-eval.el (2018-06-25 09:44:51 +0300) <Bozhidar Batsov>
* 02dc9db - Restructure a bit cider-interaction.el (2018-06-25 09:37:08 +0300) <Bozhidar Batsov>
* 858ebe0 - Clean up the requires in cider-interaction.el (2018-06-25 09:21:41 +0300) <Bozhidar Batsov>
* ad68a56 - Fix the build (2018-06-25 08:38:40 +0300) <Bozhidar Batsov>
* 2df0e7a - Add a small comment on auto-loading (2018-06-25 08:35:45 +0300) <Bozhidar Batsov>
* 8717df7 - Don't require cider-profile in cider-mode (2018-06-25 08:25:13 +0300) <Bozhidar Batsov>
* f7f3fc7 - Fix formatting error (2018-06-24 23:51:14 +0300) <Henry Blevins>
* 13c76ef - [Fix #2347] Remove direct manipulations of cider-ancillary-buffers (2018-06-24 22:52:23 +0300) <Bozhidar Batsov>
* 1fa530a - Fix one problem compilation problem (2018-06-24 17:20:21 +0300) <Bozhidar Batsov>
* a7d1969 - Try to fix again the compilation issues in cider-connection.el (2018-06-24 16:51:26 +0300) <Bozhidar Batsov>
* c015706 - Fix more compilation problems (2018-06-24 16:29:14 +0300) <Bozhidar Batsov>
* ce712f2 - Fix one more compilation warning (2018-06-24 16:10:53 +0300) <Bozhidar Batsov>
* a1f9994 - Gut the cider-stdin-handler (2018-06-24 15:41:40 +0300) <Bozhidar Batsov>
* d6a835e - Fix a few more compilation warnings (2018-06-24 15:41:00 +0300) <Bozhidar Batsov>
* e1f9208 - Address a couple of compilation warnings (2018-06-24 15:21:37 +0300) <Bozhidar Batsov>
* 77423c2 - Restructure some requires (2018-06-24 15:01:52 +0300) <Bozhidar Batsov>
* 1366f4e - Fix a broken test (2018-06-24 15:01:39 +0300) <Bozhidar Batsov>
* adb648a - Avoid a compilation issue (2018-06-24 12:25:45 +0300) <Bozhidar Batsov>
* 4fb50b9 - Move the definition of cider-ancillary-buffers to cider-common.el (2018-06-24 12:22:55 +0300) <Bozhidar Batsov>
* 73d4d88 - Move cider-nrepl-session-buffer where it belongs (2018-06-24 11:41:48 +0300) <Bozhidar Batsov>
* 4f3f6b0 - Fix a couple of references to cider-jump-to (2018-06-24 11:37:42 +0300) <Bozhidar Batsov>
* 3098f42 - [#2203] Extract the find functionality in its own source file (2018-06-24 11:31:30 +0300) <Bozhidar Batsov>
* 5a75a3e - Improve a docstring (2018-06-24 10:58:19 +0300) <Bozhidar Batsov>
* 6e23946 - Fix a bunch of docstrings (2018-06-24 10:55:33 +0300) <Bozhidar Batsov>
* 3c1f8a6 - Kill obsolete defvar (2018-06-24 10:45:12 +0300) <Bozhidar Batsov>
* d985efb - Bump the minimum and the latest Clojure versions (2018-06-24 10:27:10 +0300) <Bozhidar Batsov>
* ac5221e - Fix the build (2018-06-24 10:21:57 +0300) <Bozhidar Batsov>
* 86f6a3f - Mark a function as private (2018-06-24 10:19:52 +0300) <Bozhidar Batsov>
* c97fe1a - [#2203] Move the cider-auto-mode stuff to cider-connection.el (2018-06-24 10:16:54 +0300) <Bozhidar Batsov>
* fc63c29 - [#2203] Move cider-undef and cider-run to cider-mode.el (2018-06-24 10:07:36 +0300) <Bozhidar Batsov>
* c9153e2 - [#2203] Move cider-read-from-minibuffer to cider-common.el (2018-06-24 09:08:52 +0300) <Bozhidar Batsov>
* fb27fd6 - Remove obsolete alias (2018-06-24 09:04:39 +0300) <Bozhidar Batsov>
* 7ebe957 - [#2203] Move stdin handling to cider-client.el (2018-06-24 09:01:41 +0300) <Bozhidar Batsov>
* fd6e7ea - [#2203] Move cider-interrupt-handler to cider-client.el (2018-06-24 08:51:30 +0300) <Bozhidar Batsov>
* 527466c - Add :safe clause to cider-repl-auto-detect-type (2018-06-23 10:37:21 +0300) <Andrea Richiardi>
* 4c00b08 - [#2203] Extract the code completion functionality in its own source file (2018-06-23 10:13:50 +0300) <Bozhidar Batsov>
* f9d0ba4 - Remove cider-ping command (2018-06-23 09:38:43 +0300) <Bozhidar Batsov>
* 8092831 - Kill misplaced section heading (2018-06-23 09:38:01 +0300) <Bozhidar Batsov>
* 9fcce7d - Fix broken build (2018-06-23 08:57:08 +0300) <Bozhidar Batsov>
* 2b7a88e - [#2203] Extract the formatting functionality in its own source file (2018-06-23 08:50:32 +0300) <Bozhidar Batsov>
* c5157be - Mark a private function as such (2018-06-23 08:33:54 +0300) <Bozhidar Batsov>
* f8a9f5e - [#2203] Extract the refresh functionality in its own source file (2018-06-23 08:29:45 +0300) <Bozhidar Batsov>
* 7f5c1df - Fix a reference to cider-toggle-trace (2018-06-23 08:03:28 +0300) <Bozhidar Batsov>
* 37365f1 - [#2203] Extract the tracing functionality in its own source file (2018-06-22 22:08:46 +0300) <Bozhidar Batsov>
* 6d4a5a8 - [#2203] Move the insert and eval functionality out of cider-interaction.el (2018-06-22 20:35:19 +0300) <Bozhidar Batsov>
* bb47916 - Add keybindings for cider-eval-defun-at-point and cider-eval-last-sexp in the eval keymap (2018-06-22 20:17:23 +0300) <Bozhidar Batsov>
* 48468d7 - Rename `cider-eval-defun-to-point` and `cider-eval-sexp-to-point` (2018-06-22 20:07:18 +0300) <Bozhidar Batsov>
* eecc51b - Add cider-jack-in-clj&cljs to cider-connection-init-commands (2018-06-22 17:31:49 +0300) <Bozhidar Batsov>
* 05aa034 - Reflow a few long docstring lines (2018-06-22 09:25:26 +0300) <Bozhidar Batsov>
* 939428d - Reorder a bit the eval menu (2018-06-22 09:16:16 +0300) <Bozhidar Batsov>
* cb19861 - Add a tip on building locally the MELPA package (2018-06-22 08:59:58 +0300) <Bozhidar Batsov>
* 0bca1d1 - [Fix #2328] Add new interactive command cider-eval-sexp-to-point (#2339) (2018-06-22 08:04:06 +0300) <Chris Zheng>
* 37bf753 - Fix unbalanced parentheses (2018-06-22 07:58:15 +0300) <Dirceu Pereira Tiegs>
* 28bdc1f - Add support for printing to the current buffer to `cider-eval-defun-to-point` (2018-06-21 23:44:56 +0300) <Bozhidar Batsov>
* 4fd637f - Make cider-set-repl-type interactive (2018-06-21 20:44:16 +0300) <Bozhidar Batsov>
* 5bc9326 - [#2305] Make it possible to disable the REPL type auto-detection (2018-06-21 20:33:18 +0300) <Bozhidar Batsov>
* bd606e3 - Replace the mentions of the now defunct CCW with calva (2018-06-21 18:59:10 +0300) <Bozhidar Batsov>
* c9d3654 - Update the piggieback url (2018-06-21 18:16:10 +0300) <Bozhidar Batsov>
* 3446eca - Fix a typo (2018-06-21 18:08:59 +0300) <Bozhidar Batsov>
* 9e9de01 - Add a few tips and notes to the FAQ (2018-06-21 18:04:09 +0300) <Bozhidar Batsov>
* e414f7d - Fix a broken link (2018-06-21 15:51:04 +0300) <Bozhidar Batsov>
* d53ae1f - Add a few more tips and notes to the manual (2018-06-21 13:12:07 +0300) <Bozhidar Batsov>
* 8c57020 - Add a note on the history of CIDER (2018-06-21 12:36:13 +0300) <Bozhidar Batsov>
* 499be10 - Eval top level defuns inside comment forms (#2323) (2018-06-19 23:46:22 +0300) <dpsutton>
* eea7e6e - Introduce a new command named `cider` (`C-c M-x`) (2018-06-19 21:29:05 +0300) <Bozhidar Batsov>
* d07081d - Fix the link to the manual (2018-06-19 10:35:29 +0300) <Bozhidar Batsov>
* d135ce3 - Move some code where it belongs (2018-06-19 10:02:16 +0300) <Bozhidar Batsov>
* 0965939 - Remove duplicated elisp section in .dir-locals.el (2018-06-19 09:58:17 +0300) <Bozhidar Batsov>
* f05dbc4 - Pass cider-default-cljs-repl value to on-port-callback (2018-06-19 08:14:03 +0300) <Vitalie Spinu>
* 3a792fe - Check in `cider-connected-p` that the process is alive (2018-06-18 18:35:41 +0300) <Vitalie Spinu>
* b2147d5 - [sesman] Expand paths before link registration (2018-06-18 18:35:41 +0300) <Vitalie Spinu>
* e7e7bfe - Trim cider-compat.el down to just if-let* and when-let* (2018-06-18 09:38:34 +0300) <Bozhidar Batsov>
* a59fa09 - Revert "Remove cider-compat.el" (2018-06-18 09:29:42 +0300) <Bozhidar Batsov>
* 6981c31 - Remove some dead code (2018-06-18 08:14:43 +0300) <Bozhidar Batsov>
* 674f34e - Group a few requires a bit better (2018-06-18 08:13:27 +0300) <Bozhidar Batsov>
* b28fbac - Remove cider-compat.el (2018-06-18 08:01:51 +0300) <Bozhidar Batsov>
* e7f075f - Update the changelog (2018-06-18 07:55:57 +0300) <Bozhidar Batsov>
* d277e8f - Update the requirements (2018-06-18 07:54:20 +0300) <Bozhidar Batsov>
* cac4916 - Rename -cljcljs -> -clj&cljs postfix (2018-06-18 07:37:17 +0300) <Vitalie Spinu>
* 026ac3d - Check for missing repls in cider-switch-to-repl-buffer (2018-06-18 07:37:17 +0300) <Vitalie Spinu>
* 36e5110 - Remove lingering condition-case-unless-debug (2018-06-18 07:37:17 +0300) <Vitalie Spinu>
* 4a6a250 - Fix cider-restart for single-repl sessions (2018-06-18 07:37:17 +0300) <Vitalie Spinu>
* ecc0a55 - On session-restart don't unregister links (2018-06-18 07:37:17 +0300) <Vitalie Spinu>
* 99ca228 - [Sesman] Improve link retrieval API (2018-06-18 07:37:17 +0300) <Vitalie Spinu>
* 6bdd935 - Update an nREPL url (2018-06-17 23:30:12 +0300) <Bozhidar Batsov>
* 5cb98a7 - New connection API and jack-in rewrite (#2324) (2018-06-17 22:11:00 +0200) <Vitalie Spinu>
* 3e45c4c - Improve the formatting of the code completion section of the manual (2018-06-17 12:51:46 +0300) <Bozhidar Batsov>
* ecb7ff7 - Update the manual links to use our new domain cider.mx (2018-06-17 12:38:56 +0300) <Bozhidar Batsov>
* ac1d792 - Improve the formatting of the debugging section of the manual (2018-06-17 11:29:35 +0300) <Bozhidar Batsov>
* 31fd350 - Improve the formatting of a code example (2018-06-17 11:20:32 +0300) <Bozhidar Batsov>
* 3979451 - Improve a bit the "Up and Running" section of the manual (2018-06-17 11:16:46 +0300) <Bozhidar Batsov>
* 547e52f - Use a fancy copyright symbol (2018-06-17 11:03:52 +0300) <Bozhidar Batsov>
* 5c3723e - Add the admonitions markdown extension (2018-06-17 10:59:33 +0300) <Bozhidar Batsov>
* 587f6ac - Check if those docs annotations are not case sensitive (2018-06-17 10:48:16 +0300) <Bozhidar Batsov>
* 2fafba3 - Improve the markup of the installation section of the manual (2018-06-17 10:35:32 +0300) <Bozhidar Batsov>
* 35cf253 - Group insert-into-repl commands in a common keymap and make them more flexible  (#2319) (2018-06-12 09:35:47 +0300) <dpsutton>
* d71342f - [Fix #2310] Add cider-format-edn-last-sexp (#2311) (2018-06-08 13:52:22 +0300) <Chris Zheng>
* 4c593d0 - Add figwheel-main REPL type (#2313) (2018-06-08 13:55:45 +0900) <Andrea Richiardi>
* aec1fcd - Bump piggieback to 0.3.6 (2018-06-08 07:24:27 +0300) <Bozhidar Batsov>
* 49034dc - Use a better error when a cljs repl form cannot be found (2018-06-07 08:08:27 +0900) <Andrea Richiardi>
* 82b8165 - Use Emacs 26.1 in the Travis build matrix (2018-06-04 09:54:45 +0900) <Bozhidar Batsov>
* 4ff5d3a - [Fix #2294] simplify stacktrace filter mechanism (2018-06-04 09:53:22 +0900) <dan sutton>
* 89cb4a4 - Fix a typo in cider-connections function (#2304) (2018-06-01 00:23:11 +0900) <fourtytoo>
* c40a020 - Don't use (deprecated) internal tramp var (2018-05-27 16:45:35 +0300) <Juergen Hoetzel>
* 209fb0a - Add a FIXME to cider-classpath-libs (2018-05-27 08:29:05 +0300) <Bozhidar Batsov>
* e63c115 - Quick and dirty fix for not building properly the group-id (2018-05-26 14:47:49 +0300) <Bozhidar Batsov>
* 29af513 - Fix a roadmap entry (2018-05-26 11:39:00 +0300) <Bozhidar Batsov>
* 717222c - Improve library checking (2018-05-26 10:08:32 +0300) <Bozhidar Batsov>
* e8ff30f - Indicate support for Clojure CLI tools in docs (2018-05-26 08:46:09 +0300) <Chris Bowdon>
* 5f2495e - Bump the clojure-mode dep (2018-05-25 01:27:29 +0300) <Bozhidar Batsov>
* b9832c5 - Kills some extra spaces (2018-05-21 22:10:03 +0300) <Bozhidar Batsov>
* 796bae2 - Update @tapeinosyne's GitHub handle (2018-05-21 10:11:33 +0300) <Bozhidar Batsov>
* 0004e63 - Update the piggieback dep (2018-05-21 09:18:19 +0300) <Bozhidar Batsov>
* 31d4f0f - Make the ClojureScript deps injection configurable and document it (2018-05-21 09:17:12 +0300) <Bozhidar Batsov>
* ab25ea6 - Inject piggieback automatically on cider-jack-in-clojurescript (2018-05-20 23:33:08 +0300) <Bozhidar Batsov>
* c08efcf - Update the changelog (2018-05-19 23:58:42 +0300) <Bozhidar Batsov>
* dfa45ae - [Fix #2286] Fix eldoc on repl image (#2293) (2018-05-19 21:30:52 +0300) <stardiviner>
* 45bd3bc - Update the piggieback version mentioned in the manual (2018-05-15 15:32:38 +0300) <Bozhidar Batsov>
* eb42764 - Update the requirements (2018-05-15 12:21:43 +0300) <Bozhidar Batsov>
* 5cc147e - Remove a few references to SLIME from the documentation (2018-05-15 10:47:32 +0300) <Bozhidar Batsov>
* ed51096 - Move a defcustom (2018-05-14 14:27:10 +0300) <Bozhidar Batsov>
* feeed85 - Add :safe properties to a few defcustoms (2018-05-14 14:20:43 +0300) <Bozhidar Batsov>
* c64b3e5 - Require the nashorn REPL before trying to use it (2018-05-14 09:37:57 +0300) <Bozhidar Batsov>
* ff3bfa2 - Use line-move instead of previous-line for emacs24.5 tests (2018-05-13 19:28:48 +0300) <Vitalie Spinu>
* 7392893 - Expand locref file when file is relative (2018-05-13 19:28:48 +0300) <Vitalie Spinu>
* bfa84e0 - Add tests for locref detection (2018-05-13 19:28:48 +0300) <Vitalie Spinu>
* b573180 - Add locref reg-expressions for cljs-messages and reflection warnings (2018-05-13 19:28:48 +0300) <Vitalie Spinu>
* 466c660 - Update some wording for 0.18 (2018-05-10 19:32:08 +0300) <Bozhidar Batsov>
* e304a84 - Stop posting notifications to our defunct gitter channel (2018-05-09 13:48:54 +0300) <Bozhidar Batsov>
* dcba258 - Bump the development version (2018-05-09 11:09:15 +0300) <Bozhidar Batsov>
* 0a652cc - Add to the manual some tips for tweaking cider-cljs-repl-types (2018-05-08 20:11:26 +0300) <Bozhidar Batsov>
* fed67b8 - Extend the usage section :-) (2018-05-08 20:01:56 +0300) <Bozhidar Batsov>
* 3795864 - Update the suggested piggieback version (2018-05-08 11:22:32 +0300) <Bozhidar Batsov>
* 5b1c148 - (tag: v0.17.0) Release 0.17 (Andalucía)! (2018-05-07 22:27:41 +0300) <Bozhidar Batsov>
* 581aaef - Optimize a bit of code (2018-05-07 22:07:11 +0300) <Bozhidar Batsov>
* 35d7261 - Update the piggieback instructions (2018-05-07 07:52:05 +0300) <Bozhidar Batsov>
* d01366a - Mention the new content-type functionality in the manual (2018-05-07 00:10:32 +0300) <Bozhidar Batsov>
* ae22b86 - Fix a couple of small mistakes in the cider-repl-use-pretty-printing defcustom (2018-05-07 00:09:53 +0300) <Bozhidar Batsov>
* aeccd83 - Improve a comment (2018-05-06 20:41:02 +0300) <Bozhidar Batsov>
* 8b4b899 - Update the roadmap (2018-05-05 23:15:26 +0300) <Bozhidar Batsov>
* fdf97bc - Update a bit the roadmap document (2018-05-05 22:31:43 +0300) <Bozhidar Batsov>
* 95defaa - Autoload command aliases (2018-05-05 12:10:19 +0300) <Bozhidar Batsov>
* f615669 - Improve a docstring (2018-05-05 12:07:06 +0300) <Bozhidar Batsov>
* 8c8e197 - [Fix #2283] Update all ClojureScript REPL forms to use piggieback 0.3+ (2018-05-05 01:10:05 +0300) <Bozhidar Batsov>

### clojure-emacs/cider-nrepl

* 2e7be20 - Document the use of Lein project middleware (2018-06-26 18:32:49 +0300) <Bozhidar Batsov>
* 4a6be9f - Add a simple nrepl-server task in cider.tasks (#532) (2018-06-16 10:27:31 +0300) <Andrea Richiardi>
* eb2b5a2 - Fix the version parsing (2018-05-23 09:41:58 +0300) <Bozhidar Batsov>
* 4219f5f - Add a todo to the lein plugin test (2018-05-21 20:15:34 +0300) <Bozhidar Batsov>
* 916394c - Delete see-also.edn (2018-05-21 09:21:47 +0300) <Bozhidar Batsov>
* 19729ad - Update a docstring (2018-05-20 19:47:27 +0300) <Bozhidar Batsov>
* 85a3333 - Refactor a bit the version parsing (2018-05-20 19:43:34 +0300) <Bozhidar Batsov>
* 6dd044f - Revert "Leverage versioneer to remove the version info duplication" (2018-05-20 19:35:47 +0300) <Bozhidar Batsov>
* 65fc896 - Leverage versioneer to remove the version info duplication (2018-05-20 09:48:00 +0300) <Bozhidar Batsov>
* 7d0c22c - Add a ns docstring (2018-05-20 09:15:31 +0300) <Bozhidar Batsov>
* 3ea8b29 - Fix a regression with see-also (2018-05-20 00:20:50 +0300) <Bozhidar Batsov>
* 531d980 - Extract the bulk of the info functionality to orchard (2018-05-19 10:53:23 +0300) <Bozhidar Batsov>
* c839623 - Remove some obsolete code (2018-05-15 15:36:31 +0300) <Bozhidar Batsov>
* cbe964a - Update the nREPL GitHub url (2018-05-14 18:40:58 +0300) <Bozhidar Batsov>
* 6c2422b - Stop posting notifications to our defunct gitter channel (2018-05-09 13:45:52 +0300) <Bozhidar Batsov>
* 9c5bd4f - Stop testing on Clojure 1.7 (2018-05-09 13:42:20 +0300) <Bozhidar Batsov>
* 6618a0d - Stop testing on Java 7 (2018-05-09 13:35:25 +0300) <Bozhidar Batsov>
* 066b603 - Add a todo note (2018-05-09 12:23:30 +0300) <Bozhidar Batsov>
* 51141dc - Mention the ops provided by wrap-out (2018-05-09 11:20:35 +0300) <Bozhidar Batsov>
* 9ba8525 - Fix a description (2018-05-09 11:19:15 +0300) <Bozhidar Batsov>
* 48b18b7 - Bump the development version (2018-05-09 11:10:39 +0300) <Bozhidar Batsov>
* 7ea2655 - Fix a broken link (2018-05-09 11:00:46 +0300) <Bozhidar Batsov>
* d18a6e8 - (tag: v0.17.0) Release 0.17.0 (2018-05-07 22:23:59 +0300) <Bozhidar Batsov>
* 9d8bcaa - Bump the orchard dep (2018-05-07 22:23:59 +0300) <Bozhidar Batsov>
* d29ba46 - Add tests around test op (2018-05-07 13:19:39 -0500) <Dominic Monroe>
* 766e730 - Accept non-namespaced tests in backwards compat (2018-05-07 13:19:39 -0500) <Dominic Monroe>
* 815df35 - Revert "Bump the piggieback dep to 0.3.2" (2018-05-07 15:19:07 +0300) <Bozhidar Batsov>
* 9639449 - Revert "Try to fix the broken cljs build" (2018-05-07 15:18:00 +0300) <Bozhidar Batsov>
* 79b37e0 - Extend coercion to match cider's send format (2018-05-07 07:14:10 -0500) <Dominic Monroe>
* cdd9450 - Try to fix the broken cljs build (2018-05-07 12:17:48 +0300) <Bozhidar Batsov>
* 886a410 - [Fix clojure-emacs/cider-nrepl#524] Remove tools.nrepl from the dev profile (2018-05-07 09:16:27 +0300) <Bozhidar Batsov>
* 08e0287 - Bump the piggieback dep to 0.3.2 (2018-05-07 09:16:27 +0300) <Bozhidar Batsov>
* 9bd73a3 - Update documentation regarding var-query support (2018-05-06 15:17:37 -0500) <Dominic Monroe>
* cf9f3a0 - Use orchard.query for apropos and tests (2018-05-06 15:17:37 -0500) <Dominic Monroe>
* 201bced - Bump orchard dependency (2018-05-06 15:17:37 -0500) <Dominic Monroe>
* 59db6d9 - Bump the orchard dep to 0.1.0 (2018-05-05 14:33:59 +0300) <Bozhidar Batsov>

### clojure-emacs/orchard

* b86c498 - Bump the development version (2018-06-16 13:59:56 +0300) <Bozhidar Batsov>
* c883c0e - (tag: v0.3.0) Release 0.3.0 (2018-06-16 13:58:46 +0300) <Bozhidar Batsov>
* 8e77b88 - Add a couple of extra eldoc unit tests (2018-06-16 13:47:14 +0300) <Bozhidar Batsov>
* e8db8ff - Denote the public api for orchard.eldoc clearly (2018-06-16 13:36:51 +0300) <Bozhidar Batsov>
* 1ad8330 - Update the changelog (2018-06-16 12:57:09 +0300) <Bozhidar Batsov>
* ce6dbd8 - Fix the datomic-query unit tests (2018-06-16 11:40:42 +0300) <Bozhidar Batsov>
* 9cc9a07 - Move a test where it belongs (2018-05-19 10:45:45 +0300) <Bozhidar Batsov>
* 7705b8b - Add a todo (2018-05-19 10:45:38 +0300) <Bozhidar Batsov>
* 501787f - Fix a broken test (2018-05-19 10:31:41 +0300) <Bozhidar Batsov>
* b7ceb98 - Move more functionality from cider-nrepl to orchard's info namespace (2018-05-19 09:47:29 +0300) <Bozhidar Batsov>
* 5f2c497 - Fix the formatting (2018-05-17 13:21:48 +0300) <Bozhidar Batsov>
* 7f1adbb - Fix a duplicated test definition (2018-05-16 12:07:25 +0300) <Bozhidar Batsov>
* c6470bf - Extract some of the var info and eldoc functionality from cider-nrepl (2018-05-16 11:27:58 +0300) <Bozhidar Batsov>
* 473c641 - Remove the redundant Clojure 1.7 profile (2018-05-10 17:01:53 +0300) <Bozhidar Batsov>
* 84ccea5 - Update the README (2018-05-09 13:58:31 +0300) <Bozhidar Batsov>
* 0351762 - Kill some commented out legacy (2018-05-09 13:57:43 +0300) <Bozhidar Batsov>
* aabf916 - Drop support for Java 7 and Clojure 1.7 (2018-05-09 13:57:03 +0300) <Bozhidar Batsov>
* a33426b - Fix the classpath under Java 9 for real (2018-05-08 14:30:50 +0300) <Bozhidar Batsov>
* 83f1fc6 - Add a todo note (2018-05-08 14:26:54 +0300) <Bozhidar Batsov>
* 199e49b - Fix the build (2018-05-08 14:24:48 +0300) <Bozhidar Batsov>
* b77b4ab - Bump the java.classpath dep (2018-05-08 13:23:28 +0300) <Bozhidar Batsov>
* a7db9f0 - Bump the development version (2018-05-08 13:13:35 +0300) <Bozhidar Batsov>
* 8896c3e - (tag: v0.2.0) Release 0.2.0 (2018-05-07 22:15:50 +0300) <Bozhidar Batsov>
* 4587ccf - Update the changelog (2018-05-06 19:03:39 +0300) <Bozhidar Batsov>
* 38f6af5 - Add environment query functions (2018-05-06 06:57:41 -0500) <Dominic Monroe>
* 999409d - Import eastwood's clojure.clj config (2018-05-06 06:57:41 -0500) <Dominic Monroe>
* 0e4fde5 - Update the README (2018-05-05 14:57:45 +0300) <Bozhidar Batsov>
* fa06a39 - Update the changelog (2018-05-05 14:33:19 +0300) <Bozhidar Batsov>
* c7bb4dc - Bump the development version (2018-05-05 14:30:58 +0300) <Bozhidar Batsov>
* 1911c4f - (tag: v0.1.0) Release 0.1.0 (2018-05-05 14:29:21 +0300) <Bozhidar Batsov>

### clojure-emacs/clojure-mode

* 9a2c3e7 - Fix the project.el integration (2018-06-30 00:00:43 +0200) <Bozhidar Batsov>
* d335f9a - Fix a link name (2018-06-27 18:25:10 +0200) <Bozhidar Batsov>
* 6ee34b9 - Bump the development version (2018-06-27 18:24:08 +0200) <Bozhidar Batsov>
* ed1929b - Add Emacs 26.1 to the Travis CI build (2018-06-26 15:39:47 +0300) <Bozhidar Batsov>
* f6b0be3 - Fix a couple of docstrings (2018-06-26 15:37:15 +0300) <Bozhidar Batsov>
* c433916 - Don't cache ns in the test for clojure-find-ns (2018-06-26 14:41:56 +0300) <Bozhidar Batsov>
* 8fab875 - (tag: 5.8.0) Release 5.8.0 (2018-06-26 14:35:38 +0300) <Bozhidar Batsov>
* 6b35097 - Tweak the changelog (2018-06-26 14:34:24 +0300) <Bozhidar Batsov>
* 9e28721 - Add some basic integration with project.el (2018-06-26 14:33:02 +0300) <Bozhidar Batsov>
* 610d1f6 - Bump the development version (2018-06-08 15:07:18 +0300) <Bozhidar Batsov>
* 5478ab7 - Expand a bit on clojure-build-tool-files (2018-06-08 15:06:22 +0300) <Bozhidar Batsov>
* 04cf70a - Update the ns cache automatically in clojure-update-ns (2018-06-08 08:39:55 +0300) <Bozhidar Batsov>
* 34998fb - [Fix #478] Cache the result of clojure-find-ns to optimize its performance (2018-06-08 08:33:12 +0300) <Bozhidar Batsov>
* 9b9bd5f - Add shadow-cljs.edn to the default build tool files list (2018-06-05 12:56:41 +0900) <cnd>
* 615ad75 - [clojure-emacs/cider#2281] Cache the results of clojure-project-dir (2018-05-26 12:25:48 +0300) <Bozhidar Batsov>
* 2fddd43 - Indent clojure.spec/fdef differently (#476) (2018-05-16 16:40:55 +0300) <Ghadi Shayban>

### clojure-emacs/cljs-tooling

(No commits in this period)

### nrepl/bencode

* 37ec1fc - (HEAD -> master, origin/master, origin/HEAD) Improve the release management (2018-06-17 21:01:23 +0300) <Bozhidar Batsov>
* 6537f92 - Bump the version to 1.0.0 (2018-06-17 20:58:04 +0300) <Bozhidar Batsov>
* 298f867 - Add some basic usage section (2018-06-17 20:56:47 +0300) <Bozhidar Batsov>
* b43c232 - Give credit to @kotarak for creating this beautiful piece of code (2018-06-08 20:24:45 +0300) <Bozhidar Batsov>
* 1a98f6a - Extend the README a bit (2018-06-08 20:21:45 +0300) <Bozhidar Batsov>
* e2a9b3a - Add a couple of badges to the README (2018-06-08 20:14:39 +0300) <Bozhidar Batsov>
* cf8027a - Add Travis config (2018-06-08 20:14:16 +0300) <Bozhidar Batsov>
* 8049e1a - Initial commit (2018-06-06 18:01:24 +0900) <Bozhidar Batsov>

### nrepl/piggieback

* 52a92e5 - (tag: 0.3.6) Release 0.3.6 (2018-06-07 13:00:34 +0900) <Bozhidar Batsov>
* 6f29fe1 - allow repl-options to flow through (2018-06-05 20:16:24 -0700) <Bruce Hauman>
* 507baa9 - Remove unused refer (2018-05-21 22:53:01 +0300) <Bozhidar Batsov>
* 0a91b8e - Update the nREPL repo url (2018-05-21 22:52:06 +0300) <Bozhidar Batsov>
* 0fde017 - Leverage a couple more functions from clojure.string (2018-05-19 08:44:35 +0300) <Bozhidar Batsov>
* 286176b - Update the project description (2018-05-19 08:43:59 +0300) <Bozhidar Batsov>
* e850d21 - Bump the development version (2018-05-19 08:42:52 +0300) <Bozhidar Batsov>
* aa3e203 - Update the README (2018-05-19 00:17:49 +0300) <Bozhidar Batsov>
* d635c69 - (tag: 0.3.5) Cut 0.3.5 (2018-05-19 00:15:49 +0300) <Bozhidar Batsov>
* cca4b3f - Update the repo URL (again) :-) (2018-05-17 19:20:39 +0300) <Bozhidar Batsov>
* 391c290 - fix loss of compiler-env (2018-05-15 14:08:55 -0700) <Bruce Hauman>
* faab3b0 - bump to 0.3.5-SNAPSHOT (2018-05-15 14:08:18 -0700) <Bruce Hauman>
* 4a5574b - (tag: 0.3.4) Release 0.3.4 (2018-05-15 12:11:23 +0300) <Bozhidar Batsov>
* 5f89860 - Tweak a couple of TODOs (2018-05-15 00:12:39 +0300) <Bozhidar Batsov>
* 323482d - Fix the formatting (2018-05-15 00:04:52 +0300) <Bozhidar Batsov>
* 26b7c06 - add comment (2018-05-14 14:41:35 -0400) <Bruce Hauman>
* 65f1b3b - prevent tear-down from being called (2018-05-14 14:36:24 -0400) <Bruce Hauman>
* a25f11c - add development helper in user.clj (2018-05-14 14:36:00 -0400) <Bruce Hauman>
* d312b79 - bump to 0.3.4-SNAPSHOT (2018-05-14 14:35:21 -0400) <Bruce Hauman>
* 6b6a7ba - Update the copyright (2018-05-08 11:08:18 +0300) <Bozhidar Batsov>
* a983d50 - (tag: 0.3.3) Release 0.3.3 (2018-05-08 11:05:59 +0300) <Bozhidar Batsov>
* cc48fe2 - fix REPL teardown problem and bind *out* and *err* for initialization (2018-05-07 17:36:14 -0400) <Bruce Hauman>
* b4e788d - bump to 0.3.2-SNAPSHOT (2018-05-07 17:34:37 -0400) <Bruce Hauman>
* ef08e83 - (tag: 0.3.2) Release 0.3.2 (2018-05-07 07:41:07 +0300) <Bozhidar Batsov>

### nrepl/drawbridge 

* 6a9a854 - (HEAD -> master, origin/master, origin/HEAD) Fix indentation with cljfmt fix (2018-05-22 23:32:34 +0300) <Bozhidar Batsov>
* c18a75d - Add cljfmt (2018-05-22 23:32:20 +0300) <Bozhidar Batsov>
* 9feabbe - Update ns declaration (2018-05-22 23:30:42 +0300) <Bozhidar Batsov>
* 85e2fa8 - Fix comment (2018-05-22 23:28:29 +0300) <Bozhidar Batsov>
* 1c840db - (tag: 0.1.1) Release 0.1.1 (2018-05-22 22:22:47 +0300) <Bozhidar Batsov>
* abae176 - Fix some nREPL namespace references (2018-05-22 22:20:25 +0300) <Bozhidar Batsov>
* d09d991 - Add a clojars badge (2018-05-22 22:17:00 +0300) <Bozhidar Batsov>
* 2847af8 - (tag: 0.1.0) Release 0.1.0 (2018-05-22 16:15:36 +0300) <Bozhidar Batsov>
* 0598e25 - Update the README (2018-05-22 16:14:06 +0300) <Bozhidar Batsov>
* 24d0989 - Update nREPL dep to 0.4 (2018-05-22 16:11:17 +0300) <Bozhidar Batsov>
* 4ed6fa0 - Remove :main from project.clj (2018-05-22 16:08:28 +0300) <Bozhidar Batsov>
* 49db31f - Update the clojars config (2018-05-22 16:07:02 +0300) <Bozhidar Batsov>
* 613ce11 - Change the namespace and the deployment coordinates (2018-05-22 16:04:22 +0300) <Bozhidar Batsov>

### nrepl/nREPL

* 407a789 - Add a changelog entry for #34 (2018-06-27 09:05:49 +0300) <Bozhidar Batsov>
* 3597ac1 - Make sure that we assign zero when port is nil (2018-06-26 19:19:38 +0300) <Andrea Richiardi>
* ea65049 - Use TODO annotations consistently (2018-06-10 16:53:26 +0300) <Bozhidar Batsov>
* 094f30c - Update the README (2018-05-23 09:35:22 +0300) <Bozhidar Batsov>
* 768a5b2 - (tag: 0.4.1) Release 0.4.1 (2018-05-23 09:33:08 +0300) <Bozhidar Batsov>
* 7319ec9 - Fix the version parsing (2018-05-23 09:32:02 +0300) <Bozhidar Batsov>
* e536bd5 - [Fix #11] Don't read the version string from a resource file (2018-05-22 09:12:17 +0300) <Bozhidar Batsov>
* 031586c - Bump the development version (2018-05-22 09:02:41 +0300) <Bozhidar Batsov>
* 34fb75a - Kill a dead test (2018-05-22 09:01:01 +0300) <Bozhidar Batsov>
* 44fd9b3 - Cleanup a couple of examples (2018-05-22 08:16:41 +0300) <Bozhidar Batsov>
* 1f4d9d4 - (tag: 0.4.0) Release 0.4.0 (2018-05-21 23:39:36 +0300) <Bozhidar Batsov>
* 61ab0d9 - [Fix #4] Change the project's namespaces (2018-05-21 23:35:27 +0300) <Bozhidar Batsov>
* 6542d4d - Fix a few line comments (2018-05-20 22:45:28 +0300) <Bozhidar Batsov>
* 8428621 - Add better license information (2018-05-20 08:58:00 +0300) <Bozhidar Batsov>
* cbeecf6 - Improve the code style a bit (2018-05-19 22:51:34 +0300) <Bozhidar Batsov>
* e43c4de - Fix a bunch of line comments (2018-05-19 22:45:11 +0300) <Bozhidar Batsov>
* 40ecf02 - Update BEncode URL (2018-05-19 20:38:40 +0300) <Reid D McKenzie>
* 9107676 - Update the README (2018-05-19 17:20:38 +0300) <Bozhidar Batsov>
* d5337c1 - Add url and license to the project.clj (2018-05-19 17:17:17 +0300) <Bozhidar Batsov>
* 44521a1 - (tag: 0.3.1) Release 0.3.1 (2018-05-19 17:14:48 +0300) <Bozhidar Batsov>
* 1440933 - Get rid of some legacy code for compatibility with Java 5 (2018-05-19 17:08:20 +0300) <Bozhidar Batsov>
* 90db7f9 - [Fix #26] Specify Java 8 as the compiler target (2018-05-19 16:52:54 +0300) <Bozhidar Batsov>
* a62347f - Adopt a more structured changelog format (a la CIDER) (2018-05-18 18:15:25 +0300) <Bozhidar Batsov>
* 193b5d9 - cljfmt compliance (2018-05-18 18:09:29 +0300) <Александар Симић>
* a1bdb6e - Updated the CHANGELOG (2018-05-18 18:09:29 +0300) <Александар Симић>
* e68d773 - Added a test for no-code operation (2018-05-18 18:09:29 +0300) <Александар Симић>
* 61b012d - Fixed some messages not resulting in status :done (2018-05-18 18:09:29 +0300) <Dirk Geurs>
* ca48975 - Update the copyright (2018-05-18 10:18:58 +0300) <Bozhidar Batsov>
* 063e6ab - Add a Clojars badge (2018-05-18 10:17:42 +0300) <Bozhidar Batsov>
* 20134ce - Simplify project.clj a tiny bit (2018-05-18 10:11:42 +0300) <Bozhidar Batsov>
* 9dd71a4 - Bump the development version (2018-05-18 09:46:10 +0300) <Bozhidar Batsov>
* e10e7fd - (tag: 0.3.0) ns clean up (2018-05-18 07:39:57 +0300) <Александар Симић>
* b63debd - Add a proper description to the project (2018-05-17 19:57:56 +0300) <Bozhidar Batsov>
* f9209f0 - Fix a typo (2018-05-17 19:53:53 +0300) <Bozhidar Batsov>
* f10a9a9 - Update the Clojar coordinates (2018-05-17 19:31:59 +0300) <Bozhidar Batsov>
* 0bc6c66 - Update the repo URL (again) :-) (2018-05-17 19:29:08 +0300) <Bozhidar Batsov>
* 521aa47 - Update the build instructions (2018-05-16 14:27:11 +0300) <Bozhidar Batsov>
* db9b7c5 - Update .gitignore (2018-05-16 12:29:22 +0300) <Bozhidar Batsov>
* 9509815 - Fix a broken link (2018-05-16 12:26:47 +0300) <Bozhidar Batsov>
* 46380a9 - [Fix #1] Release nREPL 0.3.0!!! (2018-05-16 12:12:03 +0300) <Bozhidar Batsov>
* 3ab6c1f - Tweak some metadata for consistency (2018-05-16 11:58:01 +0300) <Bozhidar Batsov>
* 2c9e7c8 - Use a proper docstring (2018-05-16 11:54:25 +0300) <Bozhidar Batsov>
* ac8f6bb - Fix the version string (2018-05-16 11:53:24 +0300) <Bozhidar Batsov>
* 459ed52 - Update the changelog (2018-05-16 11:53:17 +0300) <Bozhidar Batsov>
* 40a4a3e - Change file encoding to utf-8-unix (2018-05-16 11:50:11 +0300) <Bozhidar Batsov>
* e72434f - Change the maven-specific dir structure to something more lein friendly (2018-05-16 11:46:35 +0300) <Bozhidar Batsov>
* 1558f70 - Get rid of our maven legacy (2018-05-16 11:39:04 +0300) <Bozhidar Batsov>
* c812c3f - Namespace formatting fix (2018-05-16 11:18:12 +0300) <Александар Симић>
* f80e763 - lein cljfmt fix (2018-05-16 11:18:12 +0300) <Александар Симић>
* 5136c58 - Use eastwood-ignore-unused-ret macro (2018-05-16 11:18:12 +0300) <Александар Симић>
* 5987435 - Don't reinvent the logging (2018-05-16 11:18:12 +0300) <Александар Симић>
* 54cabc2 - Ignore the macroexpansion of while (2018-05-16 11:18:12 +0300) <Александар Симић>
* 0de3d63 - Not sure if this should be ignored (2018-05-16 11:18:12 +0300) <Александар Симић>
* b477ead - This warning is not being used anywhere (2018-05-16 11:18:12 +0300) <Александар Симић>
* bb13e37 - Two Eastwood warnings less (2018-05-16 11:18:12 +0300) <Александар Симић>
* 74a8a3a - Fix for Eastwood's unused-ret-vals-in-try warning (2018-05-16 11:18:12 +0300) <Александар Симић>
* 78c2c91 - Eastwood warning fix (2018-05-16 11:18:12 +0300) <Александар Симић>
* 3ed3cfa - Prevention of read shadowing (2018-05-16 11:18:12 +0300) <Александар Симић>
* 0edb25c - Making Eastwood happy (2018-05-16 11:18:12 +0300) <Александар Симић>
* f431f67 - Further Eastwood compliance (2018-05-16 11:18:12 +0300) <Александар Симић>
* 32aff2d - One less complaint from Eastwood (2018-05-16 11:18:12 +0300) <Александар Симић>
* 3620ede - Corrected require in clojure.tools.nrepl-test (2018-05-16 11:18:12 +0300) <Александар Симић>
* f5fa259 - Renamed symbol to keep Eastwood happy (2018-05-16 11:18:12 +0300) <Александар Симић>
* 16fbdab - Whitespace & blanklines (2018-05-16 11:18:12 +0300) <Александар Симић>
* 2fd2824 - Switch the encoding of the history file to utf-8-unix (2018-05-14 14:47:46 +0300) <Bozhidar Batsov>
* c68d495 - Update the history a bit (2018-05-14 14:45:22 +0300) <Bozhidar Batsov>
* 5ad3a0e - Fix the formatting issue for real (2018-05-14 14:37:09 +0300) <Bozhidar Batsov>
* e2249a3 - Fix a formatting problem in the history (2018-05-13 22:18:13 +0300) <Bozhidar Batsov>
* eda17a5 - Update the CIDER entry (2018-05-11 23:36:16 +0300) <Bozhidar Batsov>
* 9115ed5 - Update a couple of links (2018-05-11 23:33:43 +0300) <Bozhidar Batsov>
* c8f956b - Fix the Travis badge (2018-05-11 23:29:11 +0300) <Bozhidar Batsov>
* e625bd8 - Tweak the project.clj and the Travis CI setup (2018-05-11 20:27:37 +0300) <Bozhidar Batsov>
* 67bba07 - Update the deployment coordinates (2018-05-11 19:38:52 +0300) <Bozhidar Batsov>
* 9e8faf7 - Update a couple of links (2018-05-11 12:07:35 +0300) <Bozhidar Batsov>
* c497d10 - Fix a readme heading (2018-05-10 23:57:44 +0300) <Bozhidar Batsov>
