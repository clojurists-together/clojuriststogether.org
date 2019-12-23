title: September 2019 Monthly Update
date: 10/28/2019
draft: false
type: post

September was our second month with this round of projects, check out their monthly update!

* [Shadow CLJS](http://shadow-cljs.org/) with Thomas Heller
* [Meander](https://github.com/noprompt/meander) with Joel Holdbrooks
* [Calva](https://github.com/BetterThanTomorrow/calva) with Peter Strömberg
* [CIDER](https://cider.mx/) with Bozhidar Batsov

## Shadow CLJS
### September 1-15
* Released shadow-cljs versions 2.8.53 - 2.8.59
* Rewrote most of the nREPL implementation to improve compatibility with cider-nrepl.
* Added shadow-cljs classpath to print the classpath from the CLI
* Fixed an issue causing "Too many open files" on some systems
* Added basic CLJS prepl support

### Sept 16-30
* Added [:warnings-as-errors](https://shadow-cljs.github.io/docs/UsersGuide.html#warnigs-as-errors) to treat certain warnings as errors instead. Mostly intended for CI builds that should fail instead of warn.
* Changed the behavior of [:build-hooks](https://shadow-cljs.github.io/docs/UsersGuide.html#build-hooks) so thrown exceptions fail the build, instead of just being logged.
* Added :devtools {:log-style "color: red;"} to allow customizing the styles used for shadow-cljs log messages in the browser during development. Suggestions welcome for a better default color schema. The default blue doesn't play so nice with dark-mode.

## Meander
### September 1-15

The work for the first half of September saw several improvements to ergonomics, documentation, code generation, and the official release of the epsilon branch of the project.
* Improving map pattern ergonomics and performance
* Previously, the match macro would never allow maps patterns to contain logic variable keys. The reason for this was due to the semantics of match pattern matching which requires that each pattern have one, and only, one possible match. A map pattern with a logic variable key — in isolation — could yield multiple solutions for that logic variable. However, if the map pattern is not in isolation, and the logic variables has been previously bound, then the restriction to disallow variable keys can be relaxed. Not only does this allow the match macro do more than it could before, it also greatly improved performance for these cases as it eliminated needless searches.
* Improving code generation
* Compilation of sequential pattern matches such as lists and vectors saw a reduction in code size. By doing some simple manipulations of the pattern AST prior to handing it to the match compiler, eliminated extra runtime work. Some literal pattern matching has also been improved.
* Improving documentation
* With an eye towards the upcoming Strange Loop talk about Meander, numerous documentation changes were made. The previously large README has now been updated to be more of an attention getter, and the formal documentation has been moved to the projects doc folder with the content separated into different files. Virtually all of the core API was documented and formatted such that we could take advantage of the wonderful formatting capabilities of cljdoc.

### September 16-30
The weeks between the 16 and the 30th saw some pretty amazing improvements, particularly to the performance of the pattern matcher.

* Map and set patterns with variable keys/elements saw massive performance boosts by, once again, rewriting the pattern AST and the IR (intermediate representation). While specific details can be found in the commits 51f24ba0, f78296, and ee8845a, I think it is safe to say that map and set patterns containing variable keys should be compiled to code which runs as fast as most hand written code with the same semantics. Coupled with the improvements earlier in the month of September, matching against maps and set patterns are now very fast.
Several big improvements also came by various AST manipulations to with patterns. By inlining non or, acyclic pattern references, the compiler will only generate function code for pattern matches which need recursion or have several branches by way of or patterns. This results in both less code and better runtime performance.
Some minor improvements also include applying beta reduction when safe, leveraging type information when possible, and preventing reflection when using method calls.
* A very exciting addition to pattern matcher was a new operator called "cata" which is short for catamorphism and should not be confused with the Category Theory concept by the same name. The cata operator was borrowed from the paper "A Nanopass Framework for Commercial Compiler Development" by Andrew Keep where it appears as the square braces. This operator allows for pattern matching to occur recursively over the entire set of match clauses and apply further pattern matching to the result. It is an incredibly powerful tool for performing nested transformations or recursive searches.

## Calva

### September 1 - 15  Theme: Project Maintainability - through quality

Documentation
* Added instructions for how to [setup your VS Code Workspace layouts to work with Calva](https://calva.readthedocs.io/en/latest/workspace-layouts.html).
* Updated documentation on how to [Connect Calva to the REPL](https://calva.readthedocs.io/en/latest/connect.html).
* Including a page on the subject of [Customizing the Connect Sequence](https://calva.readthedocs.io/en/latest/connect-sequences.html#settings-for-adding-custom-sequences).
* More information for [VIM Extension](https://calva.readthedocs.io/en/latest/vim.html) users on what to (not) expect when using Calva.
* There's a description of the process for integrating changes to Calva (This was the documentation first driver for the CI automation).
* Updated documentation on [How to Contribute code to Calva](https://github.com/BetterThanTomorrow/calva/wiki/How-to-Contribute).

Continuous Integration
* We have reworked our integration and release process a bit. Or, rather, just trying to get it less ad hoc. And we are now taking help from Circle CI to automate much of it. Circle CI now does the following:
* Unit tests are run on all updates of the Calva repository.
* PR's automatically renders a build of a Calva extension, and the VSIX package is made publicly available for contributors and maintainers to download and test.
* Pre-release are built and pushed to GitHub Releases at will.
* New Calva versions are built, pushed to GitHub Releases, and published to the VS Code Extension Marketplace.

This is a total bliss, I tell ya.

Jack-in and Connect
* The Customizable Connect Sequences are released, including the No-prompting Jack-in mentioned in the last report. It has been iterated on and some bugs are also squashed.
* Fix Figwheel Main deps added to non-cljs projects
* Support connecting to Leiningen and CLI project using shadow-cljs watcher
*S upport for launching with user aliases/profiles

Other
* More accurate code completion lookups (by giving the nREPL completion middleware more context for the lookup).
* By PR: Keep focus in editor when evaluating to the REPL Window
* By PR: Close java processes when clsoing or reloading VS Code. (On Windows, where VS Code fails to clean up Jack-in processes for us.)
* Fix REPL Window namespace being reset to user.
* Provide JavaScript completion in ClojureScript code. (by upgrading to `cider-nrepl 0.22.1` and thus gaining the power of suitable).
* Fix Printing of preformatted results in REPL window (By a small change to the Calva Clojure lexer.)

### September 16 - 30 Theme: Project Maintainability - through reinforcements

A Growing Team

I have the pleasure to announce that Christian Fehse has accepted to join the Calva Team. Christian showed with his many PRs, that he understands the Calva project and that he cares about that it is a maintainable project.

More PRs

My work has shifted focus a bit the latest period. I have committed less code myself, as the PRs poured in and I instead try to help with getting that work integrated. It's pretty good, Calva improves much faster this way.

Things done From the CHANGELOG

New features in **bold**:
* [Revert disconnecting and jacking out on closing of REPL window](https://github.com/BetterThanTomorrow/calva/issues/326)
* [Add command for connecting to a non-project REPL](https://github.com/BetterThanTomorrow/calva/issues/328)
* **[Add hover to inline result display, containing the full results](https://github.com/BetterThanTomorrow/calva/pull/336)**
* **[Better inline evaluation error reports with file context](https://github.com/BetterThanTomorrow/calva/issues/329)**
* **[Enhancement REPL window handling / nREPL menu button](https://github.com/BetterThanTomorrow/calva/issues/337)**
* **[Print async output, and a setting for where it should go](https://github.com/BetterThanTomorrow/calva/issues/218)**
* [Fix REPL window prompt does not always reflect current ns](https://github.com/BetterThanTomorrow/calva/issues/280)
* [Escape HTML in stdout and stderr in REPL window](https://github.com/BetterThanTomorrow/calva/issues/321)
* [Add content security policy to webview and remove image load error](https://github.com/BetterThanTomorrow/calva/issues/341)
* [Add pretty print mode](https://github.com/BetterThanTomorrow/calva/issues/327)
* [Add command for evaluating top level form as comment](https://github.com/BetterThanTomorrow/calva/issues/349)
* [Stop writing results from **Evaluate to Comment** to output pane](https://github.com/BetterThanTomorrow/calva/issues/347)
* [Adding selected calva commands to the editors context menu](https://github.com/BetterThanTomorrow/calva/issues/33)
* [Fix bug with painting all existing result decoration with the same status](https://github.com/BetterThanTomorrow/calva/issues/353)
* [Fix bug with reporting errors using off-by-one line and column numbers](https://github.com/BetterThanTomorrow/calva/issues/354)

Not showing in the CHANGELOG
* The development process has been vastly simplified. Showing here: [How to Contribute](https://github.com/BetterThanTomorrow/calva/wiki/How-to-Contribute)
* We have added the tests for Calva's Clojure grammar to the CI pipeline

## CIDER
### September 1-15
* I've started work on CIDER 0.23 which is going to be a relative small update. All details are in the changelog, there are just a couple of outstanding PRs that need to be wrapped before I can cut the release.
* I've added DocSearch integration to nrepl.org
* I've started work on the new documentation site for cider-nrepl ()
* I've been working with Shen Tian on a Clojure spec for nREPL (merged) and sideloading of missing classes/resources automatically (PR in progress)
* On the piggieback front we've got a PR that needs some cosmetic tweaks to handle gracefully the lack of ClojureScript
* I've been discussing with Thomas Heller how to improve the nREPL integration for shadow-cljs and the potential creation of a piggieback alternative. I'm quite excited about the possible outcome here.
* I've started working a couple of blog posts for "Hard CIDER", and I should start publishing something soon
* I've also started working on a "State of CIDER" survey, but it got blocked by the poor state of free survey tools (now I'm torn between sending the survey in chunks or limiting the number of people who can respond). Or just going with Google Forms. :D

### September 16-30
* Shipped CIDER 0.23
* Shipped cider-nrepl 0.22.4 (fixes several bugs)
* Shipped orchard 0.5.3 (fixes a small bug with ClojureDocs)
* Shipped piggieback 0.4.2 (it doesn't blow up in the absence of ClojureScript)
* Shipped nREPL 0.7.0-alpha2 with support for [sideloading](https://nrepl.org/nrepl/design/middleware.html#_sideloading)
* Participated in "The REPL" :-)
* Published a couple of CIDER-related blog posts [hard-cider-project-specific-configuration](https://metaredux.com/posts/2019/10/05/hard-cider-project-specific-configuration.html)
and [hard-cider-navigating-cider-buffers-ninja-style](https://metaredux.com/posts/2019/10/07/hard-cider-navigating-cider-buffers-ninja-style.html))
* [More details on CIDER 0.23](https://metaredux.com/posts/2019/10/08/cider-0-23-lima.html)
