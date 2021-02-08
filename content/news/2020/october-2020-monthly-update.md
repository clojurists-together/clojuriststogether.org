---
title: "October 2020 Monthly Update"
date: 2020-11-04T12:00:00+08:00
author: Alyssa Parado
summary: Read more updates from Malli, Practicalli, Clj-kondo/babashka/sci, and Datahike
---

Here are the updates from our [Q3 projects](/news/q3-2020-funding-announcement/). They are finishing in the middle of November, so there will be one more short update next month. Don't forget that applications are open for our [next funding round](/news/q3-2020-survey-results/), apply today!.

# Malli

### **October 1-15**

Alpha is out, FINALLY: https://twitter.com/ikitommi/status/1314254607846191104

### Done stuff

* [#265](https://github.com/metosin/malli/pull/266) - fix sequential explain
* [#272](https://github.com/metosin/malli/pull/272) - big bowl of stuff in, shipping the alpha out
* [#276](https://github.com/metosin/malli/pull/277) - explicitly disabling sci & sci options
* [#278](https://github.com/metosin/malli/pull/278) - better configuration for default-value-transformer
* Wrote a blog post: https://www.metosin.fi/blog/malli/

### Ongoing

* working on [declarative utils for schemas](https://github.com/metosin/malli/issues/217), fixes also [#281](https://github.com/metosin/malli/issues/281)

#### Misc

4 weeks to go. Will focus on solving real-world issues that people face when adopting the library, update the libs using Malli and try to get some of the larger issues solved.



### **October 16-31**

Fixing things of the alpha, helping people adopting, small stuff.

### Done stuff

* [#282](https://github.com/metosin/malli/pull/282) - revisited how references work and how `malli.util` work with them.
* [#283](https://github.com/metosin/malli/pull/283) - declarative schema transformations
* [#289](https://github.com/metosin/malli/pull/289) - fix :sequential decoding with empty sequence
* Adopted [reitit](https://github.com/metosin/reitit) to use malli, with 3 patch releases `0.5.8`, `0.5.9` & `0.5.10`

### Ongoing

* helping people adopt malli, small fixes and examples
* design and prototyping on the Functional Schema Bundle (much bigger than expected):
  * full clj-kondo integration: https://github.com/metosin/malli/issues/268 
  * support schema defn syntax: https://github.com/metosin/malli/issues/125
  * sequence/regex schemas: https://github.com/metosin/malli/issues/180

#### Misc

no misc now.





# Practicalli

### **October 1-15**

Creating more [recorded video content around REPL driven development](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiB3u90ga_SdxYsF2k2JTag1) of high quality, improving speed of editing with Blender.org as well as better script writing and delivery practice.

Represented Clojure at the JVMWars 2020 online meetup, giving [a short presentation](https://docs.google.com/presentation/d/e/2PACX-1vR3jDNOaYoAQRUQcCm9NccXY-dNxr5JT5LfURqujAVp23dohoYKLH9BHGlFgdQjKrLKnNKkraeRbKzA/pub?start=false&loop=false&delayms=3000) which included [a brief demo of REPL driven development](https://youtu.be/rQ802kSaip4).


### Practicalli Study Group
Refactored the data-access code into `practicalli.data.*` namespaces to make each namespace have a specific aim. Identified generic functions to reduce repetition in the code.

Added unit test fixture (setup/teardown) to create and delete the H2 database on the fly, using functions in the `handler-helper` namespace.

* [086 - Banking On Clojure - Part7 - Refactor data-access namespaces and fix the build on CircleCI](https://www.youtube.com/watch?v=Ro_h5jt02Q0&list=PLpr9V-R8ZxiDjyU7cQYWOEFBDR1t7t0wv&index=89)


### Practicalli Clojure
New sections and Pages:
* [Unit testing selectors](https://practicalli.github.io/clojure/testing/unit-testing/test-selectors.html)
* [Unit testing fixtures](https://practicalli.github.io/clojure/testing/unit-testing/fixtures.html)

New page on the use of test selectors to organise tests and run test suites more effectively.  Included examples for LambdaIsland kaocha, Spacemacs, Cider and Cognitect labs test runners.

New page on fixtures for unit tests, with example fixture functions and configuration on when to run fixtures for testing.

### Screencasts
* [Clojure REPL driven development with Rebel Readline](https://www.youtube.com/watch?v=U19TWMsg0s0&list=PLpr9V-R8ZxiB3u90ga_SdxYsF2k2JTag1&index=1)
* [Clojure projects with the REPL](https://www.youtube.com/watch?v=7muHVkxzZcE&list=PLpr9V-R8ZxiB3u90ga_SdxYsF2k2JTag1&index=2)
* [Clojure REPL driven development - a simple example](https://youtu.be/rQ802kSaip4)

Updated [Cognitect REBL install procedure](https://practicalli.github.io/clojure/alternative-tools/clojure-tools/cognitect-rebl.html), local maven install no longer required.

Updated [Conjure configuration](https://gist.github.com/jr0cket/6c475137ee57fbb14f9289bd76889512) and documentation


### Screencast Video Editing
Learned how to [use Blender.org for Video editing](https://www.youtube.com/playlist?list=PLxdnSsBqCrrG9vYaQMDE0A16c4M52b7vJ) which proved to be much more efficient that previous tools used.  This will help produce a higher quality of videos for the new series on Clojure CLI tools and REPL driven development.


### Practicalli Clojure deps.edn configuration
Minor updates to the practicalli/clojure-deps-edn user level configuration for Clojure CLI tools.  This configuration is also recommended by [seancorfield/dot-clojure](https://github.com/seancorfield/dot-clojure) personal configuration

* inspect/rebl - link added to updated install requirements
* updated library versions for: clj-new and depstar update
* title and alias sub-section in mini table of contents
* merge project related aliases into one section


### Practicalli Spacemacs
Changes to the [practicalli/spacemacs.d](https://github.com/practicalli/spacemacs.d/) configuration for Spacemacs.
* renamed Git branch to [live](https://github.com/practicalli/spacemacs.d/) (update your remote urls)
* recommending Emacs 27.1 as its the current stable version and it supports ligatures (stylized character pairs).
* added unicode ligature support (no need for ligature configuration in dotspacemacs/user-config as it built into the unicode layer)
* Switched to using Fira code font as this has ligature support
* Updated the doom modeline theme for a  nice minimal look without loosing any important information
* Added font rendering optimizations

Several custom snippets added to the [practicalli/spacemacs.d](https://github.com/practicalli/spacemacs.d/): deprecated and design journal banners, rich code block with clj-kondo ignore duplicate



### **October 16-31**

A range of updates, new content and tool reviews and testing.  [Helping lots of students on Exercism](https://exercism.io/profiles/Practicalli) in the last week.

### Practicalli Study Group
Getting back to some important basics of Clojure, walking through several Exercism.io coding challenges (to support mentoring efforts there).

* [089 - Code challenges - Regular Expressions to solve Bob challenge on Exercism.io](https://youtu.be/QKBZYSITkRc)
* [088 - Code challenges - Encoding and decoding](https://youtu.be/91wrchRjdtg)
* [087 - Clojure CLI tools - Practicalli Clojure deps.edn configuration and a tour of community tools](https://youtu.be/u5VoFpsntXc)

### Practicalli Clojure
Reviewed the Introduction and Getting Started sections, improving the flow and providing a solid introduction to Clojure.

New sections
* [Using Clojure CLI tools - common tasks](http://practicalli.github.io/clojure/clojure-tools/using-clojure-tools.html) - with accompanying broadcast
* [Community Tools](https://practicalli.github.io/clojure/clojure-tools/install/community-tools.html) providing a detailed guide to tools built on Clojure CLI.
* [Code Analysis](https://practicalli.github.io/clojure/clojure-tools/install/code-analysis.html) - install and configuring clj-kondo
* [Neovim and Conjure install guide](https://practicalli.github.io/clojure/clojure-editors/editor-install-guides/neovim-conjure.html)
* [Neovim and Conjure user guide](https://practicalli.github.io/clojure/clojure-editors/editor-user-guides/neovim-conjure.html)

Updated [Clojure CLI tools version used with CircleCI](http://practicalli.github.io/clojure/testing/integration-testing/circle-ci/) and updating scripts to use -M flag with aliases.

Evaluated [Spacevim](https://spacevim.org/) to see if it should be added to the [recommended Clojure aware editors](https://practicalli.github.io/clojure/clojure-editors/).  An issue was found running the Clojure CLI tools REPL on Linux, along with a quick fix.  Although the configuration of vim packages is excellent and a very impressive overall experience. the Clojure environment is very basic and would welcome modernizing.  Ideally adding [Conjure](https://github.com/Olical/conjure) to SpaceVim would produce an excellent development experience.


### Practicalli clojure-deps-edn
Updates and fixes to the user level configuration for Clojure CLI projects

* Add `:middleware/cider-clj` and `:middleware/cider-clj-refactor` aliases to allow connections to a running REPL from Cider (and should also work for Calva).
* practicalli/live middleware/clojure-clj-refactor: add documentation
* Clojure CLI config precedence - added graphic
* Added Common development tasks
* kaocha test runner: fix for 1.0.700 release
* Update group-id of `deps-deploy` (#3)


### Practicalli Spacemacs
New content:
* [Running specific test groups with test selectors](https://practicalli.github.io/spacemacs/testing/unit-testing/running-tests.html#using-test-selectors-to-run-specific-tests)

Updated content:
* [Line numbers - visual, relative and absolute styles](https://practicalli.github.io/spacemacs/install-spacemacs/line-numbers.html)
* [Repeating searches and scrolling through search pattern history](https://practicalli.github.io/spacemacs/spacemacs-basics/working-with-projects/searching-projects.html)

#### Cider issue
Investigated bug with Emacs Cider and the new Clojure CLI tools `-M` alias flag.  Cider modeled Clojure CLI tools support on the approach for Leiningen and this resulted in the -A alias flag occurring out of position.  The clojure command does work this way, only since the `-M` flag has been introduced that the issue became visible.  The fix organises the arguments in the correct position
* [Issue: Clojure CLI command parameters order incorrect](https://github.com/clojure-emacs/cider/issues/2916)
* [PR: jack-in: move Clojure-cli parameter global-opts after -Sdeps](https://github.com/clojure-emacs/cider/issues/2917)

[Hacking on CIDER live broadcast](https://youtu.be/XuquYgOSOnc) to evolve the way cider-jack-in manages aliases. Covers basic elisp print line style debugging and how to hack on the live Cider code running in Emacs.

### Clojure CLI support for other tools
Reviewed Clojure CLI support for other [Clojure aware editors](https://practicalli.github.io/clojure/clojure-editors/) recommended and raised issues and fixes.

#### Calva issue
Calva only seems to allow the use of `-A` flag when using its jack-in feature, so [issue #826 raised](https://github.com/BetterThanTomorrow/calva/issues/826) for awareness of the change in Clojure CLI tools.

#### Spacevim issue
Spacevim [fails to run the REPL using Command Line tools on Unix systems](https://github.com/SpaceVim/SpaceVim/issues/3931), picking up the wrong executable name.  Suggested [a quick hack to fix the issue](https://github.com/SpaceVim/SpaceVim/issues/3931#issuecomment-720140933) to help the community create a suitable fix in vimscript.





# Clj-kondo/babashka/sci

### **October 1-15**

Here is an overview of the work I did per project.

### Babashka

- Ensure ns map exists for namespaces used only "code" vars [babashka/babashka.pods#20](https://github.com/babashka/babashka.pods/issues/20). This fixes compatibility with [bootleg](https://github.com/retrogradeorbit/bootleg).
- Support `java.io.file.FileVisitor` and `java.io.FilenameFilter` with `reify` [#600](https://github.com/borkdude/babashka/issues/600). Nice side effect: this makes babashka compatible with the [fs](https://github.com/clj-commons/fs) library.
- Add classes `java.util.zip.ZipInputStream` and `java.util.zip.ZipEntry`. This makes babashka compatible with [glam](https://github.com/borkdude/glam), a work in progress package manager.
- Documentation updates for `--uberscript`:
  - https://github.com/borkdude/babashka#uberscript
  - https://github.com/borkdude/babashka#carve
- Sending mail, experimented with drewr/postal [#599](https://github.com/borkdude/babashka/issues/599)
- Fix `System/exit` in REPL [#606](https://github.com/borkdude/babashka/issues/606)
- Review PR for [babashka/babashka.nrepl#28](https://github.com/babashka/babashka.nrepl/issues/28)

### Sci

- Add `lazy-cat` [#427](https://github.com/borkdude/sci/issues/427)

### Clj-kondo

- Review PR and making additional changes for
  [clj-kondo#1016](https://github.com/borkdude/clj-kondo/issues/1016): config
  for ignoring unused `:as` binding
- Support ignore hint on unused binding [#1017](https://github.com/borkdude/clj-kondo/issues/1017)
- Support ignore hint in ns form [#1031](https://github.com/borkdude/clj-kondo/issues/1031)
- Support require in top-level do [#1018](https://github.com/borkdude/clj-kondo/issues/1018)
- Support quote in require [#1019](https://github.com/borkdude/clj-kondo/issues/1019)
- Type warning for `contains?` [#1021](https://github.com/borkdude/clj-kondo/issues/1021)
- Predicate functions for hooks api [#1006](https://github.com/borkdude/clj-kondo/issues/1006)
- Fix memory leak in long running process [#1036](https://github.com/borkdude/clj-kondo/issues/1036)
- Shadowed var linter [#646](https://github.com/borkdude/clj-kondo/issues/646)
- Review PR and making additional changes for [clj-kondo/config#7](https://github.com/clj-kondo/config/pull/7)
- Support reader conditionals in ignore hint [#1022](https://github.com/borkdude/clj-kondo/issues/1022)
- Don't warn about redundant let and do in hook-generated code [#1038](https://github.com/borkdude/clj-kondo/issues/1038)
- Fix format string false positive [#1007](https://github.com/borkdude/clj-kondo/issues/1007)
- Parse failure in `(or)` [#1023](https://github.com/borkdude/clj-kondo/issues/1023)
- Base Docker image on Ubuntu latest [#1026](https://github.com/borkdude/clj-kondo/issues/1026)

### Misc

- deps.clj [v0.0.10](https://github.com/borkdude/deps.clj/releases/tag/v0.0.10)
  Parity with Clojure Tools 1.10.1.697
- Glam, a work in progress package manager https://github.com/borkdude/glam
- Carve:
  - [:silent option](https://github.com/borkdude/carve/commit/b53b85af41eadafe9d9a76d4e9bf5f5ace0ea19e)
  - updated deps
  - deprecate question mark in options
- Jet: support `take-while` and `drop-while` [#69](https://github.com/borkdude/jet/issues/69)



### **October 16-31**

Here is an overview of the work I did per project. These two weeks mostly were
spent on babashka and sci.

### Babashka

- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): a pod for creating TUI apps
- [babashka/process](https://github.com/babashka/process): a Clojure library for working with `java.lang.Process`
- `pprint/print-table` should write to `sci/out` [#611](https://github.com/borkdude/babashka/issues/611)
- add `lazy-cat` [#605](https://github.com/borkdude/babashka/issues/605)
- Fix `System/exit` in REPL [#605](https://github.com/borkdude/babashka/issues/606)
- Review PR [babashka.nrepl#28](https://github.com/babashka/babashka.nrepl/issues/28)
- Review PR [feature flag](https://github.com/borkdude/babashka/commit/13f65f05aeff891678e88965d9fbd146bfa87f4e)
- Add `transit+json` format support to pods [babashka/pods#21](https://github.com/babashka/pods/issues/21)
- Fix pod destroy function [#615](https://github.com/borkdude/babashka/issues/615)
- Bind `*file*` in nREPL server [babashka/babashka.nrepl#31](https://github.com/babashka/babashka.nrepl/issues/31)
- Add [portal](https://github.com/borkdude/babashka/tree/master/examples#portal) example
- Import should return class [#610](https://github.com/borkdude/babashka/issues/610)
- Support `clojure.java.io/Coercions` protocol [#601](https://github.com/borkdude/babashka/issues/601)
- Add `clojure.pprint/write` [#607](https://github.com/borkdude/babashka/issues/607)
- Add socket support to pods [babashka/pods#2](https://github.com/babashka/pods/issues/2)
- Partial support for multiple classes in `reify` [#603](https://github.com/borkdude/babashka/issues/603)
- Add [image-viewer](https://twitter.com/borkdude/status/1320134220107419648) example

### Sci

- Add `bound?` [borkdude/sci#430](https://github.com/borkdude/sci/issues/430)
- Support `map->` constructor on defrecords [borkdude/sci#431](https://github.com/borkdude/sci/issues/431)
- Add `*print-namespace-maps*` [borkdude/sci#428](https://github.com/borkdude/sci/issues/428)

### Misc

- [Rewrite-edn](https://github.com/borkdude/rewrite-edn): a tool to rewrite EDN files while preserving whitespace and comments
- [puget-cli](https://github.com/borkdude/puget-cli): a CLI to pprint and colorize EDN output
- [clojure-lantera](https://github.com/babashka/clojure-lanterna): an updated
  version of the original repository.





# Datahike

### **October 1-15**

In this iteration [Konrad](https://github.com/kordano) was on his yearly
vacation, so the focus was primarily on API docs, Datomic compatibility, tuple
support, and upsert optimizations.

### API and Datomic Compatibility

The api namespace of Datahike is updated to match the signatures of Datomic,
among them the ability to pass queries as string and to transact lazy sequences.
Besides that the namespace has now more examples and is ready to be released
onto cljdoc for a complete documentation of the Datahike API. The next step is
to implement missing functionality from the Datomic client API including
asynchronous behavior and further harmonize the returning values of Datahike
with the ones of Datomic.

### Tuple Support

During the last period, further progress has been made towards adding 'tuples'
to Datahike. It is now possible to declare the three types of tuples
(homogeneous, heterogeneous and composite) in a Datahike schema. As a result
Datahike will not only check the conformity of the declaration but also the
validity of a tuple related transaction at transaction time. In addition,
transacting and querying homogeneous and heterogeneous is also done. Finally,
what remains to be done is composite tuples transaction and querying. Work into
this direction has started. Currently, as a first step, the design of an
algorithm for supporting composite tuples transaction has been undertaken.

### Beyond Clojurists Together Tasks

We have continued and finished the work on improving upsert operations support
in Datahike. This is expected to have a significant impact on transaction
speed.
Further work was done on the implementation of a garbage collector for
hitchhiker-tree based backends.
Design and implementation on attribute references and test refactoring
was continued.



### **October 16-31**

This iteration included extensive work on the API docs and Datomic
compatibility, as well as progress on the tuple support.

### API and Datomic Compatibility

We continued work on the Datomic API compatibility and finished most of the
adjustments and documentation with the pull request #240. Part of that PR was
to get the cljdoc api-docs working, which can now be seen on
[cljdoc](https://cljdoc.org/d/io.replikativ/datahike).
Other parts of the Datomic API were the compatibility of the pull-api,
unit-testing the examples in the api-docs and comparing the return values
between Datahike and Datomic. It was also investigated if we can offer a
query that is returning a lazy sequence but this is only possible with
breaking changes and will possibly be part of future iterations when async
support is on the verge of completion. The support of returning vectors -
like Datomic does - is basically the same issue and needs a rewrite of the
query engine and therefore a breaking change that needs further investigation
and should be aligned with coming changes. Besides that we made further
progress moving to Clojure CLI tools. In the coming weeks work will be spent
on bug fixing seek-datoms, preparing remote communication with empty protocols
and move the listen function to the api namespace.

### Tuple Support

The work on tuple is gearing towards its end. During the last period, the
focus was on the implementation of composite tuples. This stage has just been
reached and the first few tuples have been transacted into a Datahike
instance. What remains to be done is adding more tests, bug fixing and making
the artifact more bullet-proof.

### Beyond Clojurists Together Tasks

We continued on our work on the ClojureScript port with now a first simple
round trip with only core db functions. Additionally we started updating our
[benchmark suite](https://github.com/replikativ/datahike-benchmark).
The work on the [upsert functionality](https://github.com/replikativ/datahike/pull/201)
was also continued, and testing and review for the
[attribute references](https://github.com/replikativ/datahike/pull/236) feature was extended.
