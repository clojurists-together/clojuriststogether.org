---
title: "November 2020 Monthly Update"
date: 2020-12-03T09:58:00+08:00
author: Alyssa Parado
summary: Read more updates from Malli, Practicallli, Clj-kondo/babashka/sci, and Datahike
---

Welcome to the final (half) month of updates from our Q3 projects. Our [Q4 projects](/news/q4-2020-funding-announcement/) have started this month and will continue through to February.

# Malli

### **November 1-15**

### Done stuff

* [#295](https://github.com/metosin/malli/pull/295) - fix set explain
* [#298](https://github.com/metosin/malli/pull/298) - fast schema creation
* [#303](https://github.com/metosin/malli/pull/303) - Schemas know th IntoSchema that created it

### Ongoing

* working with Functional Schema Bundle (much bigger than expected):
  * full clj-kondo integration: https://github.com/metosin/malli/issues/268 
  * support schema defn syntax: https://github.com/metosin/malli/issues/125
  * sequence/regex schemas: https://github.com/metosin/malli/issues/180
  
<img src="https://user-images.githubusercontent.com/567532/99222308-f5869300-27ea-11eb-864c-a70c4e4b017a.png">  

#### Misc

* the three months is over! started bit late, so will continue to work to get the Ongoing (more) done. 
Already happy with the results, the main goal was to ship the lib. Coudn't have done that without Clojurists Together funding.




# Practicallli

### **November 1-15**

A range of updates, new content and tool reviews and testing.  [Reviewed 75 solutions for 44 students on Exercism.io](https://twitter.com/practical_li/status/1327671242388893697) over the last two week, adding that advice and code walk-throughs to the Practicalli Clojure book.

Several high-quality REPL driven development videos will be released soon, awaiting some final editing and post processing.

### Practicalli Study Group
Continued solving Exercism.io challenges for the Live broadcasts, with a request to specifically do the Spiral Matrix challenge
* [090 - Code challenges - Exercism.io Space-Age challenge](https://youtu.be/7-LCVAtkP9o)
* [091 - Code challenges - Exercism.io Spiral Matrix challenge](https://youtu.be/Z5C7X1UN8yo)


### Practicalli Clojure
Add [SpaceVim](https://spacevim.org/) to Clojure Editors in book now the Clojure command line bug is fixed.  Continued adding REPL driven development approaches to solving Exercism.io challenges.

Launch Portal data navigator with any REPL using `:env/dev` and `:inspect/portal-cli` aliases. The REPL automatically evaluates the `dev/user.clj` source code file to require portal, open the portal window and add portal as a `tap>` source

+**New sections**
* [Exercism.io bob challenge](http://practicalli.github.io/clojure/coding-challenges/exercism/bob.html) - walking through two alternative solutions
* [SpaceVim and vim-fireplace install guide](http://practicalli.github.io/clojure/clojure-editors/editor-install-guides/spacevim-fireplace.html) - including clj-kondo
* [SpaceVim and vim-fireplace user guide](http://practicalli.github.io/clojure/clojure-editors/editor-user-guides/spacevim-fireplace.html) - a quick guide to get started.

**Updated**
* [Portal - auto-start portal on REPL startup](http://practicalli.github.io/clojure/clojure-tools/data-browsers/portal.html#starting-portal-on-repl-startup)
* [Clojure CLI tools version used with CircleCI](http://practicalli.github.io/clojure/continuous-integration/circle-ci/) and updating scripts to use latest Clojure CLI tools release and -M flag with aliases.
* [nrepl and cider aliase](https://practicalli.github.io/clojure/clojure-tools/data-browsers/reveal.html) for the Reveal data visualization tool


### Practicalli clojure-deps-edn
Updates and fixes to the user level configuration for Clojure CLI projects.  Added clj-kondo as a GitHub action to lint all pull requests and commits, ensuring aliases are in a good state.

* [Alternative REPLs](https://github.com/practicalli/clojure-deps-edn#alternative-repls): socket server and prepl aliases
* `:inspect/reveal-nrepl` to run an nREPL server with Reveal data visualization
* `:inspect/reveal-light-nrepl` a light theme version with a 32 point Ubuntu Mono font useful for demos and HiDPI screens
* `:inspect/reveal-nrepl-cider` CIDER specific libraries and middleware configuration with `:inspect/reveal-light-nrepl-cider` as a light version
* `:test/cognitect-precompile` alias to compile tests before running Cognitect Labs test runner
* [Monthly update of library versions](https://github.com/practicalli/clojure-deps-edn/blob/live/CHANGELOG.org#2020-11-08) for all aliases using `:project/outdated` alias
* `community/zulip-event` to [add community event to zulip chat](https://github.com/practicalli/clojure-deps-edn#community-activities)
* `test/cloverage` add clojure exec options for cloverage (#8)
* `:inspect/reveal-nrepl` and `:inspect/reveal-light-nrepl` Cider specific aliases for Reveal data browser


### Practicalli Data Science
Created a new (alpha state) book to provide practical guides to using Clojure tools and libraries to build applications in a data science context.  Working with the [SciCloj community](https://scicloj.github.io/) to raise awareness of what is possible in this space and as I grow my understanding it will be captured in the Practicalli Data Science book.

* [Notespace section](https://practicalli.github.io/data-science/notebooks/notespace/) - created [a simple demo of the Notespace data science journal tool](https://github.com/practicalli/scicloj-notespace-simple-demo) for Clojure, combined with Portal to help browse large data sets.

### Practicalli ClojureScript
Updated to new Practicalli theme and put all project content first, moving overview and design content to the reference section.  The book will focus on figwheel-main, reagent and ClojureScript for the next quarter.

### Practicalli Spacemacs
Update [Clojure documentation section](https://practicalli.github.io/spacemacs/documentation/cider-doc.html) to show Clojure and [Java documentation](https://practicalli.github.io/spacemacs/documentation/javadoc.html) functions, including how to navigate to source code and specifications from help.

#### Hacking CIDER
* [PR #2926](https://github.com/clojure-emacs/cider/pull/2926) - Tip to evaluate namespace before documentation lookup - added to Practicalli Spacemacs instead.





# Clj-kondo/babashka/sci

### **November 1-15**

# Clojurists Together Project Update Q3 2020 November 1-15

Here is an overview of the work I did per project.

### Babashka

- Can't call symbol literal [#622](https://github.com/borkdude/babashka/issues/622)
- Expose `clojure.pprint/simple-dispatch` [#627](https://github.com/borkdude/babashka/issues/627)
- Random number stream doesn't work [#630](https://github.com/borkdude/babashka/issues/630)
- :or in destructuring broken for false case [#632](https://github.com/borkdude/babashka/issues/632)

### Sci

- Support nested libspecs [#399](https://github.com/borkdude/sci/issues/399)
- Allow names of vars when def is allowed [#434](https://github.com/borkdude/sci/issues/434)
- Allow aliases in protocol fn impls [#440](https://github.com/borkdude/sci/issues/440)
- Allow overriding :line metadata [#443](https://github.com/borkdude/sci/issues/443)

### Clj-kondo

- Lint deps.edn [#945](https://github.com/borkdude/clj-kondo/issues/945)
- Export and import config via classpath [#559](https://github.com/borkdude/clj-kondo/issues/559), [clj-kondo/confi
g#1](https://github.com/clj-kondo/config/issues/1)
- Cache jar analysis [#705](https://github.com/borkdude/clj-kondo/issues/705)
- Fix index out of bounds exception [clj-kondo.lsp#11](https://github.com/borkdude/clj-kondo.lsp/issues/11)
- Better resolving of vars referred with `:all` [#1010](https://github.com/borkdude/clj-kondo/issues/1010)
- Implement `:include` option for shadowed-var linter [#1040](https://github.com/borkdude/clj-kondo/issues/1040)
- Mark generated nodes to avoid redundant dos and lets despite location metadata
  [#1059](https://github.com/borkdude/clj-kondo/issues/1059)
- Fix number of retries when linting in parallel [#1068](https://github.com/borkdude/clj-kondo/issues/1068)

#### Misc

- [Grasp](https://github.com/borkdude/grasp): a new tool to grep Clojure code
  using clojure.spec! It uses sci under the hood.
- https://github.com/borkdude/edamame/issues/5
- https://github.com/borkdude/edamame/issues/66
- https://clojure.atlassian.net/browse/TRDR-63





# Datahike

### **November 1-15**

The last iteration was focused on reviews and testing of the tuple feature as well as the last adjustments for the Datomic compatibility.

### Datomic Compatibility

Datomic API compatibility was finished up with more tests and the `listen` functionality that allows for callbacks on each transaction. Finally these features were merged with the [PR #240](https://github.com/replikativ/datahike/pull/240).

### Tuple Support

Tests were added for the different tuple types with the open [PR #251](https://github.com/replikativ/datahike/pull/251) ready to review.
We planned to finish this up in the upcoming days for the release of 0.3.3 of Datahike.

### Release 0.3.3 Candidate

Since Datahike was extended with multiple new features a new release candidate was defined. It will include the following features:

- Support for tuples (#104)
- Switch to Clojure CLI tools (#253)
- Adapt API namespace for Datomic compatibility (#196)
- Implement query with string (#196)
- Implement transact with lazy sequence (#151, #78, #196)

After the review of the last PRs a release is planned within the next week.

### Beyond Clojurists Together Tasks

We started working on a [remote client](https://github.com/replikativ/datahike-client) for [datahike-server](https://github.com/replikativ/datahike-server). For the server we are now looking into a JSON API that would allow other programming languages to use Datahike.

The ClojureScript port is moving forward focussing on the transactor and query engine.

The [upsert feature](https://github.com/replikativ/datahike/pull/201) had to be adjusted and is now benchmarked in order to see which functions take most of the perfomance.
