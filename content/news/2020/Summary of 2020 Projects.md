---
title: "Summary of 2020 Projects"
date: 2021-01-24T08:30:00+08:00
author: Alyssa Parado
summary: Read more about all the projects we funded in 2020
draft: true
---

# 2020 Summary of Projects

## Calva

* Version 2.0.75 released
  - [Support cljs-suitable JavaScript completion](https://github.com/BetterThanTomorrow/calva/issues/552)
  - [Fix Printing to Calva REPL prints <repl#7> before each print out](https://github.com/BetterThanTomorrow/calva/issues/548)


* Version 2.0.76 released
  - [Fix Calva locking up when opening files with very long lines](https://github.com/BetterThanTomorrow/calva/issues/556)


#### Configuration of Formatting

Calva's formatter can now be configured to support different formatting policies - a big thanks to [nbardiuk](https://github.com/nbardiuk). This is something users have been asking for, and we're excited to offer it now! See [this post](https://clojureverse.org/t/calva-gets-configurable-formatting/5596) for more information.


#### Docs

The [Calva docs](https://calva.io/) have a new look and a new home! We switched to using mkdocs for building our docs and are using the material theme.

#### Debugger

The debugger has been released! [Documentation for the debugger](https://calva.io/debugger/) was written and published that covers the existing functionality and lists current and upcoming features.

I've added a command to instrument functions for debugging as an alternative and more convenient method than adding `#dbg` reader tags to the code file. I've also added editor decorations to visually indicate which functions are instrumented. The instrumented function definitions as well as the usages gain a top and bottom border and a background. clj-kondo is used [as a JVM library](https://github.com/borkdude/clj-kondo/blob/master/doc/jvm.md#api) to get the var definitions and usages, and [this data](https://github.com/borkdude/clj-kondo/tree/master/analysis) is matched up with cider-nrepl's [`debug-instrumented-defs`](https://docs.cider.mx/cider-nrepl/nrepl-api/ops.html#_debug_instrumented_defs) response data to create the decorations on document change.

The instrument command and decorations features were released and I've started working on displaying structured values, for locals, in the VS Code debugger side pane (currently non-primitives are shown as strings). This should allow a user to "drill down" into a structured value like a map or collection.





## Fireplace

* Worked on issues related to Fireplace
* Retool `:Eval` to support streaming response.
* Implemented sending text to stdin.
* Implemented eval backgrounding.  Press CTRL-D during any interactive eval to
  detach from it and continue using Vim.  The results will open in a preview
  window when finished.
* Phased out internal use of old APIs.





## Reagent

Some of the fixed tests were related to how Reagent added component stack
information into the error essages, which was retrieved from React class component internal properties.

React now provides a proper way to get the component stack information for errors
using Error Boundaries.


I created Reagent 0.10.0 release. This version marks the functions deprecated,
so library authors have some time to fix calls before functions are removed.



Related to the functional render feature, I implemented first version
of providing options to Reagent. This way existing applications can keep
using the old implementation and Class components, and allow users to choose
if they want to try functional components. Additionally a custom component
can be added, which can be used to control per form if Class or functional
component is created. 

Improved the documentation and examples, and added code coverage reporting to CI and Github.


Reagent 1.0.0-alpha1 is out and contains implementation of both
creating React function components from Reagent components
and options.

I added some new documentation pages
and a new example showing making Function components default
and using Hooks with Reagent, and created the first release for testing.

Based on the comments from users, I've already added
two more "shortcuts" to Reagent: `:f>` to create
Function Reagent component (using the default
options), and `:r>` to use React
components without properties conversion done by
`:>` and `adapt-react-class`.

added new test environment for Reagent, using the
new `:bundle` compilation target. This target generates
JS tooling compatible output. This output can be passed to
Webpack, or for testing, Karma.


#### General bug fixes

During implementation of functional components, I've also merged some
previously implemented changes to master, and refactored test checks
to provide better failure messages.





## Ring


The [ADR](https://github.com/ring-clojure/ring/blob/2.0/doc/adr-spec-2.md) for Ring 2.0 has been completed


The servlet interop functions and Jetty adapter have been updated to
support the Ring 2.0 request and response formats. 

Fixed various smaller issues with Ring 1.8.0
 
Ring 2.0.0-alpha1 has been released, with the adapter and servlet
utility functions supporting both Ring 1 and Ring 2 keys by default,
though there is also an option for forcing a particular version.





## Cider

#### nREPL:

* Implemented an initial version of the built-in code completion middleware
* Reviewed and merged dynamic middleware loading functionality
* Provided default print functions via `nrepl.util.print`.
* Implemented an initial version of the build-in lookup middleware and fixed handling of aliased symbols in lookup middleware
* added var type to completion candidates
* cut nREPL 0.8.0-alpha1 
* Issued several alphas including small fixes to the new lookup and completion logic.
* Released nREPL 0.8 (https://metaredux.com/posts/2020/06/15/nrepl-0-8-evolving-the-protocol.html)
* Extended the nREPL docs on building new middleware (https://nrepl.org/nrepl/building_middleware.html)


#### cider-nrepl:

* Normalized the interface of several ops by making the params they accept consistent
* Cut a new release (0.25)
* Issued to a bug-fix release that workarounds an incompatibility between clj-suitable and shadow-cljs 2.10.
* Released cider-nrepl 0.25.3 (mostly to address the complaints about downloading the ClojureDocs export automatically)


#### Piggieback:

* Cut Piggieback 0.5 with pprint support


####  CIDER:

* Added support for the new nREPL completions op
* Fixed a couple of long-standing bugs related to streamed printing in the REPL (https://github.com/clojure-emacs/cider/issues/2628 and https://github.com/clojure-emacs/cider/issues/1971)
* Make it possible to configure the print buffer size that nREPL uses via CIDER
* Reviewed and merged some inspector improvements
* Added a couple of small command (e.g. `cider-repl-toggle-clojure-font-lock`) and made many documentation improvements including some new page navigation
* Improved the behavior of commands based on `cider-jump` (https://github.com/clojure-emacs/cider/issues/2850)
* Fixed eldoc on Emacs 28.1. 
* Improved support for Babashka.
* Fixed a couple of small bugs with REPL warning messages.
* Did some work towards making CIDER a bit Clojure-agnostic, so it's easier to use it with flavours of Clojure and other programming languages.
* Fixed eldoc for `.` and `..`.
* Merged and polished REPL auto-trimming functionality (https://github.com/clojure-emacs/cider/pull/2857).
* Implemented initial support for sideloading.
* Released CIDER 0.25 and 0.26 ("Nesebar") (https://github.com/clojure-emacs/cider/releases/tag/v0.26.0)
* Polished the sideloading functionality (have user and system sideloading, add better docs)


#### inf-clojure:

* Released inf-clojure 3.0 (no new functionality, but massive improvements to the internals)



Orchard:

* Merged a fix for #86 that fixes the resolution order in the info function
* Cut new releases (0.5.9), (0.5.10), and (0.5.11)


Misc:

* Fixed a deployment issue with the clojuredocs-edn-export tool (it generates data that orchard uses)
* Helped with adding print middleware support to shadow-cljs
* Helped with adding eldoc support to babashka.nrepl
* Specified docs license (https://github.com/clojure-emacs/cider/issues/2740)
* Fixed a dependency bug in Orchard and cider-nrepl (https://github.com/clojure-emacs/orchard/issues/91)
* Bundle clojuredocs export with Orchard and cut version 0.6 (this eliminates the need to delete the data on demand when starting CIDER)
* Fixed cider-mode not being auto-activated for babashka (https://github.com/clojure-emacs/cider/issues/2882)
* Merged Krell REPL support (https://github.com/clojure-emacs/cider/pull/2861)
* Fixed an init bug when using babashka (https://github.com/clojure-emacs/cider/issues/2882)
* Made connection-info babashka/generic nREPL server aware (https://github.com/clojure-emacs/cider/pull/2883)
* Added cider-eval-list-at-point for evaluating code between paired delimiters (lists, vectors, etc)





## Figwheel

* Created some integration [testing](https://github.com/bhauman/figwheel-main-testing) for figwheel-main to track if new releases of ClojureScript break any of the main features.

	These tests launch figwheel and then touch various files to ensure
correct loading in terms of load order and files included in the
reload.

	These tests append various errors to files to ensure the correct error
messages show up as well.

* Got both lein-figwheel and figwheel-main working with the latest CLJS :bundle target

	The new :bundle target is an important improvement in the CLJS
compiler that provides better support for NPM dependencies in web
projects.

	* Updated the Leiningen template for lein-figwheel.

 		- The template now generates a project that supports the new `:bundle` target. One can generate the standard figwheel project by adding a `+no-bundle` option on the command line.
		- Improved the templates CLI option parsing and removed `om` as a generated framework.
* Added the new Figwheel logo and updated the website style of the [Figwheel website](https://figwheel.org)
* Improved the `:bundle` target support in [figwheel-main](https://figwheel.org).
	- This was reflected in the `com.bhaumsn/figwheel-main 0.2.7` release and the newly updated [documentation for working with NPM](https://figwheel.org/docs/npm.html).
* Refactored RNFB and added support for `expo`
* Removed [Devcards](https://github.com/bhauman/devcards) dependence on `js/React` global
* Implemented a `--clean` command which makes it easier to clean your compiled artifacts out of your project.
* Created the [`certifiable`](https://github.com/bhauman/certifiable) library to ease the creation of a local dev certificate and Java Keystore. This should enable `figwheel-main` to have turn-key HTTPS support.
* Added cleaning to `figwheel-main`.  You can either add the `--clean` to your CLI options or or `:clean-ouputs true` to your Figwheel options. 
	- This will clean all of your builds compilation output including generated bundles AND all of the compiled output from and extra-mains that you have configured as well.
* Added a new [`:ssl-valid-hosts`](https://figwheel.org/config-options#ssl-valid-hosts) option will let you configure which hostnames and ips the generated certificate considers valid.
* Completed React Native support. This supports React Native Expo CLI as well.
	- The minimal configuration for a React Native project in `figwheel.main` is now:


      ^{:react-native :cli}
      {:main example.main}

* Released `figwheel.main 0.2.10`
* Fixed bug introduced by a refactor that broke `:connect-url` this
  has been deployed to 0.2.11
* Added a tutorial/doc on how to use Nodejs with Figwheel





## Practicalli

#### Practicalli Study Group

* Provided the weekly live broadcasts, data science series and a new series on `clojure.spec`
* Developed content for Practicalli Clojure and Practicalli Clojure Webapps books.


#### Visualising data science

Concluded the [series of 7 live broadcasts on Visualising data science](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiDUXIR2z8Y8wvhpoPyl0t_D).


#### Introduction to clojure.spec.alpha

* Started a new video series covering how to use spec in the REPL and with Clojure projects

* [Practicalli/leveraging-spec](https://github.com/practicalli/leveraging-spec) project was created, covering Clojure predicates, spec/conform, spec/valid?, literal values (Clojure sets), the spec registry, fully qualified namespaces, map literal syntax, spec/def and spec/explain.


#### Practicalli Clojure

Migrated the book to Clojure CLI tools and deps.edn projects.

Updated install guide to use Java 11 and added more editor options to the install guides, including NeoVim Conjure and Atom.io Chlorine.

Updated the [practicalli/clojure-deps-edn](https://github.com/practicalli/clojure-deps-edn) repository with also contains a collection of commonly used aliases.  This repository greatly simplifies the installation of Clojure CLI tooling.

Added content and videos to the [Introducing Spec section](http://practicalli.github.io/clojure/clojure-spec/) of the book

Added details on configuring tools.deps and how to define and use multiple aliases. Provided a collection of aliases for community tools, jcenter clojars mirror and how to use a local Artifactory instance.

[Configuring a REPL on starutup for deps.edn projects](https://practicalli.github.io/clojure/repl-driven-development/configure-repl-startup.html), examples of using `dev/user.clj` to require namespaces, call functions and manage component lifecycle services (mount, component, integrant, etc.).

Extended the Unit Testing section to cover useful practices with clojure.test library, refactor is assertions with are to work over data sets.

Configured git template to use live branch as the default branch name.

Created [an introduction to CircleCI as a continuous integration service](http://practicalli.github.io/clojure/testing/integration-testing/circle-ci/random-clojure-function.html) and identified and documented recommended docker images to use for Clojure deps.edn and Leinigen projects. 

Configure REPL startup using `dev/user.clj` file and `:dev` alias in practicalli/clojure-deps-edn configuration.

Add section on [data browser tools](https://practicalli.github.io/clojure/clojure-tools/data-browsers/), extending REBL and Clojure Inspector with new projects Reveal and Portal.



**Random Clojure Function project**.
Created a guide to develop a project that [generates a random function](http://practicalli.github.io/clojure/simple-projects/random-clojure-function.html) from the namepaces available in the REPL or the functions from specified namespaces.

Using the [random Clojure function project](http://practicalli.github.io/clojure/simple-projects/random-clojure-function.html), created a [guide to develop a project with the help of CircleCI](http://practicalli.github.io/clojure/testing/integration-testing/circle-ci/random-clojure-function.html) as the continuous integration service.


**Banking on Clojure**
Updated the [banking-on-clojure project](http://practicalli.github.io/clojure/clojure-spec/projects/bank-account/) using a TDD approach with Clojure spec.



#### Practicalli ClojureScript

Clarified the introduction to the ClojureScript book status and surfaced the work that remains current and functional. This content includes and several reagent based projects, building and deploying a ClojureScript website for ClojureBridge and creating a TicTacToe game with Scalable Vector Graphics.


#### Practicalli Spacemacs

Supported the community with issues on Spacemacs gitter and #spacemacs channel of Clojurians Slack.

Several updates on using the Magit client including guide on using Magit Forge, updated and tested the Magit Forge configuration page and more. 

[Practicalli Spacemacs playlist](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiCHMl2_dn1Fovcd34Oz45su) updated with related Spacemacs videos from jr0cket channel.

Created a reference sheet for CIDER configuration variables, as there is no overall reference.

Add Emacs profiler use to the [Spacemacs troubleshooting guide](https://practicalli.github.io/spacemacs/install-spacemacs/troubleshooting.html)



#### Hacking on Spacemacs

Added key bindings to refactor namespace forms in clojure-mode

Updated [practicalli/.spacemacs.d](https://github.com/practicalli/spacemacs.d/) repository with doom modeline configuration.


#### Spacemacs Pull Requests

Refactor applications menu key bindings to create more room for key bindings and improve mnemonic keybinding use.  


#### Practicalli Clojure WebApps

Refactored overall book content design for Practicalli Clojure WebApps

Created [a guide to deploy a Clojure application via CirceCI onto Heroku](https://practicalli.github.io/clojure-webapps/projects/status-monitor-deps/). 

Updated the status monitor project to deps.edn to use as the basis for a guide to deploy Clojure applications via CircleCI to Heroku (a cloud platform as a service).

Updated details of using postgresql with Clojure (documentation will be extended soon) and recommended next.java as a library to use for SQL.

Started a section on [Application servers](https://practicalli.github.io/clojure-webapps/app-servers/), covering approaches to server configuration and server start/stop/reload.

Started a section on Databases that will initially cover H2 and Postgresql relational databases, using Sql with next.jdbc



#### Practicalli Website

Added [Shields](https://github.com/badges/shields) for each book with links to content ideas and pull requests on the respective repositories. 

Added Practicalli website and YouTube channel to the [Clojure.org community resources](https://clojure.org/community/resources).

Updated the Creative commons license notice on the front pages of all books and GitHub README files, ensuring compliance with the [Software Freedom Conservancy](https://sfconservancy.org/).

Add favicon to each book website


#### Clojure deps.edn updates

* Using REBL from Emacs CIDER using nREBL middleware, alias and configuration
* Update of dependency versions in the `deps.edn` file with depot
* Update unit testing aliases, add separate expectation aliases.
* Added more tools to practicalli/clojure-deps-edn
Any experimental or alpha state tools are clearly marked as 'experimental - used at own risk' to set clear expectations.

Added Google Storage mirrors for Maven Central for Americas, Asia and Europe to library repository configuration.  Also added a community mirror in Asia (China) for Clojars.





## Re-frame

* Reviewed and triaged all existing open issues and Prs.
* Released a stable v1.0.0 version of re-frame
* Many improvements to the docs have been made. Too many to mention but some are notable:

  - added a new FAQ on [laggy input](https://day8.github.io/re-frame/FAQs/laggy-input/)
  - added a new FAQ on [field focus](https://day8.github.io/re-frame/FAQs/FocusOnElement/)
  - added to existing FAQ on [global interceptors](https://day8.github.io/re-frame/FAQs/GlobalInterceptors/#answer-v100-onwards)
  - Additions made to [External-Resources](https://day8.github.io/re-frame/External-Resources/)
  - completely reworked [Infographics for Dominoes 1,2 3](https://day8.github.io/re-frame/event-handling-infographic/)
  - completely reworked [Infographics for Interceptors](https://day8.github.io/re-frame/Interceptors/#infographics). See also other explanations added to that tutorial. 





## Clj-kondo_babashka_sci

Fixed issues related to Clj-kondo, Babashka and Sci.

#### Clj-kondo

- Enhanced `rum/defcs` [hook code example](https://github.com/borkdude/clj-kondo/commit/9f004622d0c69a6baf93bb63243306a7098f0941)
- New `:config-paths` option in `<project>/.clj-kondo/config.edn`. This allows
  extra configuration directories to be merged in. See
  [config.md](doc/config.md) for details.

- [Config tool](https://github.com/clj-kondo/config) that can spit out library
  specific configurations that can be added via `:config-paths`.

- [Clj-kondo inspector](https://github.com/clj-kondo/inspector), a POC of
  inspecting Clojure specs and turning them


#### Babashka

* Released babashka [v0.2.0](https://github.com/borkdude/babashka/blob/master/CHANGELOG.md#v020-2020-08-28) and [v0.2.1](https://github.com/borkdude/babashka/releases/tag/v0.2.1)
- Made differences with Clojure on the JVM clearer in README
- Add `java.io.FileNotFoundException`, `java.security.DigestInputStream`, `java.nio.file.FileVisitOption` classes
- Add classes `java.util.zip.ZipInputStream` and `java.util.zip.ZipEntry`. This makes babashka compatible with [glam](https://github.com/borkdude/glam), a work in progress package manager.
- Documentation updates for `--uberscript`:
  - https://github.com/borkdude/babashka#uberscript
  - https://github.com/borkdude/babashka#carve
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): a pod for creating TUI apps
- [babashka/process](https://github.com/babashka/process): a Clojure library for working with `java.lang.Process`
- Review PR [feature flag](https://github.com/borkdude/babashka/commit/13f65f05aeff891678e88965d9fbd146bfa87f4e)
- Add [portal](https://github.com/borkdude/babashka/tree/master/examples#portal) example
- Add [image-viewer](https://twitter.com/borkdude/status/1320134220107419648) example


#### Sci

- Fixed libsci, an example of how to use sci as a shared native library (https://github.com/borkdude/sci/commit/d0b10f25bbbd6b09fb5c4ac99cf9887d33115845)
- Added REPL docs [here](https://github.com/borkdude/sci#repl) and examples [here](https://github.com/borkdude/sci/tree/688a4addda7cb9e630a49042743ae254571ac5f0/examples/sci/examples)


#### Misc

- Released [dynaload](https://github.com/borkdude/dynaload) 0.1.0 to clojars
- Released [clj-reflector-graal-java11-fix](https://github.com/borkdude/clj-reflector-graal-java11-fix) 20.2.0
- Released [jet](https://github.com/borkdude/jet) v0.0.13
- dynaload 0.2.0, 0.2.1 and 0.2.2: https://github.com/borkdude/dynaload#graalvm
- https://clojure.atlassian.net/browse/CLJ-2582
- deps.clj [v0.0.10](https://github.com/borkdude/deps.clj/releases/tag/v0.0.10)
  Parity with Clojure Tools 1.10.1.697
- Carve:
  - [:silent option](https://github.com/borkdude/carve/commit/b53b85af41eadafe9d9a76d4e9bf5f5ace0ea19e)
  - updated deps
  - deprecate question mark in options
- [Rewrite-edn](https://github.com/borkdude/rewrite-edn): a tool to rewrite EDN files while preserving whitespace and comments
- [puget-cli](https://github.com/borkdude/puget-cli): a CLI to pprint and colorize EDN output
- [clojure-lantera](https://github.com/babashka/clojure-lanterna): an updated
  version of the original repository.
- [Grasp](https://github.com/borkdude/grasp): a new tool to grep Clojure code
  using clojure.spec! It uses sci under the hood.






## Datahike


#### Datomic API

API documentation were adjusted in order to provide better examples on how to use them. 

The api namespace of Datahike is updated to match the signatures of Datomic, among them the ability to pass queries as string and to transact lazy sequences.

Got the cljdoc api-docs working, which can now be seen on
[cljdoc](https://cljdoc.org/d/io.replikativ/datahike).

Datomic API compatibility was finished up with more tests and the `listen` functionality that allows for callbacks on each transaction. Finally these features were merged with the [PR #240](https://github.com/replikativ/datahike/pull/240).


#### Entity Specs

Users can now create new specs with `:db.entity/attrs` and `:db.entity/preds` and assert entities in transactions with `:db/ensure`.

 Older stale branches and PRs were also cleaned out. 


#### Tuple Support

Added 'tuples' to Datahike. It is now possible to declare the three types of tuples
(homogeneous, heterogeneous and composite) in a Datahike schema. As a result Datahike will not only check the conformity of the declaration but also the validity of a tuple related transaction at transaction time. 

Finished transacting and querying homogeneous and heterogeneous.

Implemented composite tuples. 


####  Released 0.3.2

Released version [0.3.2](https://github.com/replikativ/datahike/releases/tag/v0.3.2)

- fixed hash computation
- improved printer
- fixed history upsert
- added database name to environ
- added circle ci orbs for ci/cd across all libraries
- fixed reverse schema update
- added automatic releases
- added benchmark utility
- extended time variance test
- updated dependencies
- adjusted documentation


#### Released 0.3.3

Released version [0.3.3](https://github.com/replikativ/datahike/releases/tag/v0.3.3)

- Support for tuples (#104)
- Switch to Clojure CLI tools (#253)
- Adapt API namespace for Datomic compatibility (#196)
- Implement query with string (#196)
- Implement transact with lazy sequence (#196, #78, #151)
- Change upsert implementation to - Improve transaction performance (#62)
- Improve cljdoc (#88)
- Format source code according to - Clojure Style Guide (#198)
- Improve benchmark tooling
- Improve documentation on the pull-api namespace


#### Beyond Clojurists Together Tasks

Unified the CI/CD for circleci, moving from leiningen to Clojure CLI tools.

Implemented a garbage collector for hitchhiker-tree based backends.

Updated the
[benchmark suite](https://github.com/replikativ/datahike-benchmark).

Tested and reviewed the
[attribute references](https://github.com/replikativ/datahike/pull/236) feature

Worked on a [remote client](https://github.com/replikativ/datahike-client) for [datahike-server](https://github.com/replikativ/datahike-server). 

The ClojureScript port is moving forward focussing on the transactor and query engine.

The [upsert feature](https://github.com/replikativ/datahike/pull/201) had to be adjusted and is now benchmarked in order to see which functions take most of the perfomance.





## Malli

* Fixed issues, closed PRs, polished the code, updated dependencies, updated libraries using malli, curved out bundle size on js, added `type-properties`, rewrote a lot of core code to use it, and wrote alpha release blog post.

* Add utilities for users to easily create simple schemas with property-based validation, including `:min` and `:max` ranges for numbers, collections and dates.
* More built-in schemas: `:int`, `:double`, `:boolean`, `:keyword`, `:symbol`, `:qualified-keyword`, `:qualified-symbol` and `:uuid`

* Handling of Map Schemas is rewritten lifting MapEntry properties as first class concept for schema applications. This includes new entry value schema type (`m/-val-schema`), conditional entry walking and a new reusable syntax parser.

#### Alpha is out!
https://twitter.com/ikitommi/status/1314254607846191104


### Misc

* Studied [Specter Source](https://github.com/redplanetlabs/specter/blob/1.0.4/src/clj/com/rpl/specter/navs.cljc#L243-L367) for finding more performant ways for doing value transformations. Good stuff.






## Practicallli (Part 2)


#### Practicalli Study Group

- [082 - Banking On Clojure - Part3 - next.jdbc for SQL in Clojure](https://youtu.be/sBdmwDUp1Ho)
- [083 - Banking On Clojure - Part4 - Updating data in the database](https://youtu.be/DmYlNTe7Gds)
- [084 - Banking On Clojure - Part5 - Generated database records from clojure.spec](https://youtu.be/Cn5QX9nL7jU)
- [085 - Banking On Clojure - Part6 - Refactor database schema, specs and namespaces](https://youtu.be/e4QInyWa1bM)
* [086 - Banking On Clojure - Part7 - Refactor data-access namespaces and fix the build on CircleCI](https://www.youtube.com/watch?v=Ro_h5jt02Q0&list=PLpr9V-R8ZxiDjyU7cQYWOEFBDR1t7t0wv&index=89)

Getting back to some important basics of Clojure, walking through several Exercism.io coding challenges (to support mentoring efforts there).

* [089 - Code challenges - Regular Expressions to solve Bob challenge on Exercism.io](https://youtu.be/QKBZYSITkRc)
* [088 - Code challenges - Encoding and decoding](https://youtu.be/91wrchRjdtg)
* [087 - Clojure CLI tools - Practicalli Clojure deps.edn configuration and a tour of community tools](https://youtu.be/u5VoFpsntXc)
* [090 - Code challenges - Exercism.io Space-Age challenge](https://youtu.be/7-LCVAtkP9o)
* [091 - Code challenges - Exercism.io Spiral Matrix challenge](https://youtu.be/Z5C7X1UN8yo)


#### Practicalli Clojure
Added [core principles for writing effective unit tests](https://practicalli.github.io/clojure/testing/unit-testing/) in Clojure. 

Added Code Challenges section, covering the Clojure challenges available in 4Clojure.com, Exercism.io, CodwWars.com, ClojureScript Koans and Advent of Code.  A quick guide to using each of the Code challenge websites was provided and tips to using them effectively.  GitHub code repositories for the Practicalli [4Clojure guides](https://github.com/practicalli/four-clojure/), [codewars-guides](https://github.com/practicalli/codewars-guides) and [exercism-guides](https://github.com/practicalli/exercism-clojure-guides) were included, along with the [4Clojure guides video playlist](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiDB_KGrbliCsCUrmcBvdW16) which walks through the solution to over 60 challenges and different approaches to solving them. Several 4Clojure and Exercism challenges have been added as solution walk-through.

Released  high-quality REPL driven development videos.

Updated [Clojure CLI tools version used with CircleCI](http://practicalli.github.io/clojure/testing/integration-testing/circle-ci/) and updating scripts to use -M flag with aliases.

Launch Portal data navigator with any REPL using `:env/dev` and `:inspect/portal-cli` aliases. The REPL automatically evaluates the `dev/user.clj` source code file to require portal, open the portal window and add portal as a `tap>` source


#### Clojure WebApps

Continued the [Banking on Clojure project](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/database-tables.html)

The application server system, UI, routing and initial handler have been defined and CircleCI used for system integration and generative testing, deploying to a staging environment on Heroku on successful builds.

PostgreSQL provisioned using Heroku for staging and production database.

Added code to create and delete the development database which is called from fixture functions within the handler-helper-test namepace.  The tests now run successfully via the CirceCI service.

Using kaocha profiles to configure different behavior in the development environment and when running on the CI server, specifically file change watcher and test output.


#### Clojure deps.edn

Updated existing aliases.

[Additional aliases added for lambdaisland/kaocha](https://github.com/practicalli/clojure-deps-edn/blob/live/deps.edn#L318-L350) to support ClojureScript test runner, BDD style tests, code coverage and junit-xml reports for CI tools and wall-boards.

Updated and fixed to the practicalli/clojure-deps-edn user level configuration for Clojure CLI tools.

Added clj-kondo as a GitHub action to lint all pull requests and commits, ensuring aliases are in a good state.


#### Practicalli Clojure
Added [several small projects](https://practicalli.github.io/clojure/simple-projects/) to help people learn the basics of Clojure.

Added docs to [use CIDER and Calva with REBL](https://practicalli.github.io/clojure/clojure-tools/data-browsers/rebl-data-visualization.html#configure-rebl-with-cider-for-spacemacs--emacs)


#### Conjure - vim tooling for Clojure development
Expanded the Clojure aware tools recommendations with Conjure, an excellent development environment for Neovim.

Created a [install walk through guide for Conjure](https://gist.github.com/jr0cket/6c475137ee57fbb14f9289bd76889512) that supports those new to Neovim. Added  [example init.vim](https://gist.github.com/jr0cket/fc7c6ec08d584675105667a0e483a643) configuration that is documented and explains the purpose of the plugins included, supporting the adoption of the Conjure tool.


#### Screencasts

Updated [Cognitect REBL install procedure](https://practicalli.github.io/clojure/alternative-tools/clojure-tools/cognitect-rebl.html), local maven install no longer required.

Updated [Conjure configuration](https://gist.github.com/jr0cket/6c475137ee57fbb14f9289bd76889512) and documentation


#### Practicalli Spacemacs

Now Emacs 27 is the default, trying out Ligature support in Emacs.  Added the unicode layer with variables to include ligatures.  Initially switching to the Fira code font which contains a wide range of ligatures in the font already.

Changed to the [practicalli/spacemacs.d](https://github.com/practicalli/spacemacs.d/) configuration for Spacemacs.

Several custom snippets added to the [practicalli/spacemacs.d](https://github.com/practicalli/spacemacs.d/): deprecated and design journal banners, rich code block with clj-kondo ignore duplicate


#### Practicalli Data Science
Created a new (alpha state) book to provide practical guides to using Clojure tools and libraries to build applications in a data science context.  

* [Notespace section](https://practicalli.github.io/data-science/notebooks/notespace/) - created [a simple demo of the Notespace data science journal tool](https://github.com/practicalli/scicloj-notespace-simple-demo) for Clojure, combined with Portal to help browse large data sets.



##### Hacking CIDER

[Hacking on CIDER live broadcast](https://youtu.be/XuquYgOSOnc) to evolve the way cider-jack-in manages aliases. Covers basic elisp print line style debugging and how to hack on the live Cider code running in Emacs.

* [PR #2926](https://github.com/clojure-emacs/cider/pull/2926) - Tip to evaluate namespace before documentation lookup - added to Practicalli Spacemacs instead.






# Calva

* Made a change to clojure-lsp to [make references code lens work](https://github.com/snoe/clojure-lsp/pull/197)
* Added a command to Calva to make references code lens work. When references code lenses are clicked, a peek definitions window opens.
* Added a Calva setting to enable references code lens
* Fixed an issue in clojure-lsp to [prevent negative numbers in ranges](https://github.com/snoe/clojure-lsp/pull/200)


## Clojure-lsp Work
- [Fix jar file URIs to be compatible with both Windows and Unix](https://github.com/clojure-lsp/clojure-lsp/pull/226)
- [Add initialization options that allow disabling formatting providers](https://github.com/clojure-lsp/clojure-lsp/pull/207)
- [Fix go id for documentHighlight](https://github.com/clojure-lsp/clojure-lsp/pull/212)
- [Fix duplicated usages/references for cljc files](https://github.com/clojure-lsp/clojure-lsp/pull/211)
* Added refactoring commands that utilize clojure-lsp's refactorings as well as updated the docs to [explain clojure-lsp in Calva](https://calva.io/clojure-lsp/) and the [refactoring commands](https://calva.io/refactoring/).
* Added a change to Clojure-lsp to prevent the process from being orphaned. If VS Code was killed in a certain time window while Clojure-lsp was initializing, the process would be left running. Now Clojure-lsp periodically checks if the parent process is alive and will exit if not. This is a suggested feature of servers by the language server protocol. 
* Fixed an issue with the default classpath lookup for Windows.
* Replaced the usage of clj-kondo with clojure-lsp. This allowed us to remove clj-kondo as an injected jack-in dependency.


## Calva Changes


Calva now uses clj-kondo via clojure-lsp, and no longer bundles the clj-kondo extension. This reduces Calva's indirect memory footprint as opposed to the previous setup of using clojure-lsp and the clj-kondo exntension, since the clj-kondo extension runs its own LSP server, which is no longer necessary.

* 2.0.134
	- Fix: [Live share jackout error](https://github.com/BetterThanTomorrow/calva/issues/856)
	- Fix: [Cannot read property 'document' of undefined](https://github.com/BetterThanTomorrow/calva/issues/846

* 2.0.135
	- [Binding keys to REPL functions, passing the namespace and cursor line (Notespace integration)](https://github.com/BetterThanTomorrow/calva/issues/863)
	- [Make REPL prompt submit if the cursor is after the top level form](https://github.com/BetterThanTomorrow/calva/issues/875)
	- [Only print stacktrace on demand](https://github.com/BetterThanTomorrow/calva/issues/878)

* 2.0.136
	- Fix: [Jack-in/Connect prompts sometimes not showing on Windows](https://github.com/BetterThanTomorrow/calva/issues/885)

* 2.0.137
	- [Bring in clojure-lsp](https://github.com/BetterThanTomorrow/calva/pull/572)

* 2.0.138
	- [Bring in refactorings we get access to via clojure-lsp](https://github.com/BetterThanTomorrow/calva/issues/890)
	- [Add ”clojure-lsp starting” progress indicator](https://github.com/BetterThanTomorrow/calva/issues/892)
	- [Fix step into local dep with debugger](https://github.com/BetterThanTomorrow/calva/issues/893)

* 2.0.139
  - [Use Pseudo Terminal instead of Task for Jack-in](https://github.com/BetterThanTomorrow/calva/pull/654)
  - [Prefer cider-nrepl symbol definitions over clojure-lsp](https://github.com/BetterThanTomorrow/calva/issues/897)
  - [Enable clojure-lsp completion items when no nrepl connection](https://github.com/BetterThanTomorrow/calva/pull/898)

* 2.0.140
	- [Make Jack-in dependency versions configurable (and bump 'em all with default settings)](https://github.com/BetterThanTomorrow/calva/pull/899)

* 2.0.141
  - Update clojure-lsp to include [jar dependency navigation fix for Windows](https://github.com/clojure-lsp/clojure-lsp/issues/223)
  - Fix: [clojure-lsp refactorings not working on Windows](https://github.com/BetterThanTomorrow/calva/issues/911)
  - [Remove default key binding for toggling Calva key bindings](https://github.com/BetterThanTomorrow/calva/issues/815)
  - [Make load-file available in the output window](https://github.com/BetterThanTomorrow/calva/issues/904)
  - [Make the ns in the repl prompt a peekable symbol](https://github.com/BetterThanTomorrow/calva/issues/904)


* 2.0.144
  - [Reactivate definitions/navigation in core and library files](https://github.com/BetterThanTomorrow/calva/issues/915)
  - [Make load-file available in the output window](https://github.com/BetterThanTomorrow/calva/issues/910)
  - [Make the ns in the repl prompt a peekable symbol](https://github.com/BetterThanTomorrow/calva/issues/904)

* 2.0.145
  - [Add command for opening the file for the output/repl window namespace](https://github.com/BetterThanTomorrow/calva/issues/920)
  - [Add setting for auto opening the repl window on Jack-in/Connect](https://github.com/BetterThanTomorrow/calva/issues/922)
  - [Add setting for auto opening the Jack-in Terminal](https://github.com/BetterThanTomorrow/calva/issues/923)
  - [Replace opening Calva says on start w/ info message box](https://github.com/BetterThanTomorrow/calva/issues/923)
  - [Add command for opening Calva documentation](https://github.com/BetterThanTomorrow/calva/issues/923)
  - [Change default keyboard shortcut for syncing the repl window ns to `ctrl+alt+c n`](https://github.com/BetterThanTomorrow/calva/issues/923)

* 2.0.146
  - Fix: [Slurp forward sometimes joins forms to one](https://github.com/BetterThanTomorrow/calva/issues/883)
  - Fix: [clojure-lsp processes left running/orphaned if VS Code is closed while the lsp server is starting](https://github.com/BetterThanTomorrow/calva/issues/906)
  - Fix: [go to definition jumps to inc instead of inc'](https://github.com/BetterThanTomorrow/calva/issues/884)
  - Fix: [Error when start a REPL with jdk15](https://github.com/BetterThanTomorrow/calva/issues/888)

* 2.0.147
  - Fix: [Dimming ignored forms does not work correctly with metadata](https://github.com/BetterThanTomorrow/calva/issues/908)
  - [Improve clojure-lsp jar integration](https://github.com/BetterThanTomorrow/calva/issues/913)
  - Update clojure-lsp to version 2021.01.07-12.28.44

* 2.0.148
	- Update clojure-lsp to version 2021.01.07-20.02.02

* 2.0.149
  - Fix: [calva.jackInEnv does not resolve `${env:...}`](https://github.com/BetterThanTomorrow/calva/issues/933)
  - Update clojure-lsp to version 2021.01.12-02.18.26. Fix: [clojure-lsp processes left running/orphaned if VS Code is closed while the lsp server is starting](https://github.com/BetterThanTomorrow/calva/issues/906)

* 2.0.150
	- [Stop bundling clj-kondo in favor of using it through clojure-lsp](https://github.com/BetterThanTomorrow/calva/issues/868)

* 2.0.151
	- Fix: [Debugger is broken on Windows](https://github.com/BetterThanTomorrow/calva/issues/947)

* 2.0.152
  - Fix: [Jack-In env with non-string variables fails](https://github.com/BetterThanTomorrow/calva/issues/959)
  - [Use clojure-lsp for usages for debug instrumentation decorations, and stop injecting clj-kondo at jack-in](https://github.com/BetterThanTomorrow/calva/issues/931)

* 2.0.153 
  - [Use status bar message instead of withProgress message for clojure-lsp initialization](https://github.com/BetterThanTomorrow/calva/issues/974)
  - [Update cider-nrepl: 0.25.6 -> 0.25.7](https://github.com/BetterThanTomorrow/calva/issues/973)
  - Fix: ["Extract function" refactoring doesn't work as expected with selections](https://github.com/BetterThanTomorrow/calva/issues/958)

* 2.0.154
  - Fix: [Calva uses ; for comments instead of ;;](https://github.com/BetterThanTomorrow/calva/issues/971)
  - Update cider-nrepl to 0.25.8
  - Update clojure-lsp to 2021.01.26-22.35.27

* 2.0.155
  - [Make command palette show alt+enter shortcut variant instead of enter for evaluating top level form](https://github.com/BetterThanTomorrow/calva/issues/989)
  - Update clojure-lsp to 2021.01.28-03.03.16
  - Fix: [nrepl port detection race condition](https://github.com/BetterThanTomorrow/calva/issues/901)

* 2.0.156
  - Fix: [Debug instrumentation decoration not working correctly anymore on Windows](https://github.com/BetterThanTomorrow/calva/issues/969)
  - Fix: [Debugger decorations issues](https://github.com/BetterThanTomorrow/calva/issues/976)






# Clj-kondo/babashka/sci


### Babashka

- Babashka [news](https://github.com/borkdude/babashka/blob/master/doc/news.md) page
- Released [babashka/process](https://github.com/babashka/process) to clojars: https://clojars.org/babashka/process
- Released [babashka/babashka.curl](https://github.com/borkdude/babashka.curl) to clojars https://clojars.org/babashka/babashka.curl
- Moved most of README docs to [book.babashka.org](https://book.babashka.org/)
- Implement `--clojure` option to invoke a JVM clojure process similar to the official Clojure CLI.
- Upgrade to GraalVM 20.3.0 [#653](https://github.com/borkdude/babashka/issues/653)
- Buddy pod https://github.com/babashka/pod-babashka-buddy
- Etaoin pod 0.0.1 release https://github.com/babashka/pod-babashka-etaoin
- Pod registry https://github.com/babashka/pod-registry
- Fswatcher pod https://github.com/babashka/pod-babashka-fswatcher
- Sqlite3 pod https://github.com/babashka/pod-babashka-sqlite3
- Only build static binary on non-SNAPSHOT release [#695](https://github.com/babashka/babashka/issues/695)
- Migration from borkdude/babashka to babashka/babashka
- [pod-babashka aws](https://github.com/babashka/pod-babashka-aws): a new pod to interact with AWS
- spartan.spec: fix compatibility with expound and cli-matic
- Release 0.2.7
- babashka-sql-pods: upgrade to newest next.jdbc version [#18](https://github.com/babashka/babashka-sql-pods/issues/18)
- spartan.spec: fix s/and + s/cat [#15](https://github.com/borkdude/spartan.spec/issues/15)
- Include [clojure.core.match](https://github.com/clojure/core.match) [#594](https://github.com/babashka/babashka/issues/594) and [hiccup](https://github.com/weavejester/hiccup) [#646](https://github.com/babashka/babashka/issues/646)




## Sci

- Add syntax checks to sci binding macro [#458](https://github.com/borkdude/sci/issues/458)
- Add option to disable arity checks [#460](https://github.com/borkdude/sci/issues/460)
- Optimization for `let`, `if`, ctx and fns
- Add `with-local-vars` [#383](https://github.com/borkdude/sci/issues/383)
- Release [0.2.0](https://github.com/borkdude/sci/blob/master/CHANGELOG.md#v020)
- defrecord `type` improvements [#492](https://github.com/borkdude/sci/issues/492)
- Several performance improvements)
- Release [0.2.1](https://github.com/borkdude/sci/blob/master/CHANGELOG.md#v021)
- Faster processing of colls
- Fix destructuring of destructuring in protocol method of record


## Clj-kondo

- Documentation: a list of all available [linters](https://github.com/borkdude/clj-kondo/blob/master/doc/linters.md) [#936](https://github.com/borkdude/clj-kondo/issues/936)
- Lint protocol and interface implementations in `deftype` and `defrecord` [#140](https://github.com/borkdude/clj-kondo/issues/140)s
- Upgrade to GraalVM 20.3.0 [#1085](https://github.com/borkdude/clj-kondo/issues/1085)
- Fix analysis for cljs.core simple-benchmark [#1079](https://github.com/borkdude/clj-kondo/issues/1079)
- Support babashka.process $ macro syntax [#1089](https://github.com/borkdude/clj-kondo/issues/1089)
- Improve analyzing syntax of `amap` [#1069](https://github.com/borkdude/clj-kondo/issues/1069)
- Fix recur arity in doysync [#1081](https://github.com/borkdude/clj-kondo/issues/1081)
- Released v2020.12.12
- Fix NPE in VSCode plugin [#12](https://github.com/borkdude/clj-kondo.lsp/issues/12)
- Move to clj-kondo/clj-kondo organisation
- Fix finding without location info
- Review several PRs
- New linter: `:unresolved-var`. This detects unresolved vars in other namespaces, like `set/onion`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#unresolved-var). [#635](https://github.com/clj-kondo/clj-kondo/issues/635)
- Avoid crash when using `:refer-clojure` + `:only` [#957](https://github.com/clj-kondo/clj-kondo/issues/957)
- Release v2021.01.20




## Misc

- deps.clj 0.0.11 [released](https://github.com/borkdude/deps.clj/releases/tag/v0.0.11): catching up with clojure 1.10.1.763
- Edamame (parser lib for sci): handle whitespace after read-cond splice [#71](https://github.com/borkdude/edamame/issues/71)
- Edamame: add `location?` predicate [#72](https://github.com/borkdude/edamame/issues/72)
- Edamame: fix for auto-resolved map and current namespace [#74](https://github.com/borkdude/edamame/issues/74)
- Edamame: make end locations optional [#75](https://github.com/borkdude/edamame/issues/75)
- Depstar: fix for Windows [#57](https://github.com/seancorfield/depstar/issues/57)
- First release (0.0.1-0.0.3) of the [puget](https://github.com/borkdude/puget-cli) CLI, a binary to pipe EDN to and get pprinted and colorize output.
- First release of [Carve](https://github.com/borkdude/carve/issues/35) as a binary.






# O’Doyle Rules

* Released version [0.5.0](https://github.com/oakes/odoyle-rules/releases/tag/0.5.0)
	* An easy way to request all facts in a session via `(query-all session)`, mainly intended for serialization. You can parse them later and re-insert them into your session.
	* A new block type called `:then-finally`, which lets you react to insertions *and* retractions that affect a rule's matches. This is vital for creating "derived facts" that accumulate other facts -- which is my equivalent to Clara's [accumulators](https://www.clara-rules.org/docs/accumulators/).
	* Added several benchmarks to the repo.

* Released version [0.6.0](https://github.com/oakes/odoyle-rules/releases/tag/0.6.0)
	* Made it possible for rules to build recursive data structures similar to what you'd get from "pull" queries in DataScript or Datomic. Here's an in-depth explanation: [Using O'Doyle Rules as a poor man's DataScript](https://github.com/oakes/odoyle-rules/blob/master/bench-src/todos/README.md).

* Implemented a common optimization called "node sharing". Rules that pulled in similar data now could share nodes internally, avoiding duplicated effort. 
* Wrote a [section in the README](https://github.com/oakes/odoyle-rules#performance) about how to use derived facts in this way. When I applied this technique to the "dungeon" benchmark, it went from ~1700 ms to ~1100 ms -- an even better improvement than from node sharing. The [dungeon crawler game](https://github.com/oakes/play-cljc-examples/tree/master/dungeon-crawler) improved by 5 FPS with the same technique.





