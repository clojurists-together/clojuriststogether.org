---
title: "January 2023 Project Updates"
date: 2023-02-06T08:30:00+08:00
summary: January 2023 Project Updates"
author: Kathy Davis
draft: true
---
Our first project update for 2023 includes reports for Clj-Kondo, Clojure Data Cookbook, ClojureDart, Mathbox, and Tablecloth. Thanks to all. We're off to a great start!   


## Project Clj-Kondo: Michiel Borkent

In this post, I'll give updates about my open source during November and December 2022. But first off, I'd like to thank all the sponsors and contributors that make this work possible! Top sponsors:  
[Clojurists Together](https://clojuriststogether.org/)  
[Roam Research](https://roamresearch.com/)  
[Nextjournal](https://nextjournal.com/)  
[Toyokumo](https://toyokumo.co.jp/)  
[Cognitect](https://www.cognitect.com/)  
[Kepler16](https://kepler16.com/)  
[Adgoji](https://www.adgoji.com/)  

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work via the following organizations. Thank you!  
[Clojurists Together](https://www.clojuriststogether.org/)  
[Github Sponsors](https://github.com/sponsors/borkdude)  
[OpenCollective](https://opencollective.com/babashka)  
(also see the [clj-kondo](https://opencollective.com/clj-kondo) one)  


Here are my updates for the Clj-Kondo projects:

### [http-client](https://github.com/babashka/http-client)

The new babashka http-client aims to become default HTTP client solution in babashka. Babashka has several built-in options for making HTTP requests, including:
[babashka.curl](https://github.com/babashka/babashka.curl); 
[http-kit](https://github.com/http-kit/http-kit); 
[java.net.http](https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/package-summary.html)

In addition, it allows to use several libraries to be used as a dependency:
[java-http-clj](https://github.com/schmee/java-http-clj)
[hato](https://github.com/gnarroway/hato)
[clj-http-lite](https://github.com/clj-commons/clj-http-lite)

The built-in clients come with their own trade-offs. E.g. babashka.curl shells out to curl which on Windows requires your local curl to be updated. Http-kit buffers the entire response in memory. Using java.net.http directly can be a bit verbose. Babashka's http-client aims to be a good default for most scripting use cases and is built on top of java.net.http and can be used as a dependency-free JVM library as well. The API is mostly compatible with babashka.curl so it can be used as a drop-in replacement. The other built-in solutions will not be removed any time soon.


### [Babashka](https://github.com/babashka/babashka)

Native, fast starting Clojure interpreter for scripting.

I had the honor to write a guest blog post for the GraalVM blog about babashka. You can read it [here](https://medium.com/graalvm/babashka-how-graalvm-helped-create-a-fast-starting-scripting-environment-for-clojure-b0fcc38b0746). Daniel Higginbotham from Brave Clojure wrote [Babashka Babooka](https://www.braveclojure.com/quests/babooka/) which I helped reviewing. I also wrote a blog on how to [test babashka scripts](https://blog.michielborkent.nl/babashka-test-runner.html). Versions 1.0.165 - 1.0.169 were released. Visit the [changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md) for details. Highlights:

- Compatibility with Cognitest [test-runner](https://github.com/cognitect-labs/test-runner) and [tools.namespace](https://github.com/clojure/tools.namespace)
- [#1433](https://github.com/babashka/babashka/issues/1433): spec source as built-in fallback. When not including the [clojure.spec.alpha](https://github.com/babashka/spec.alpha) fork as a library, babashka loads a bundled version, when clojure.spec.alpha is required.
- Many performance improvements (via SCI)
- Several non-special forms are now regular macros rather than treated special (via SCI)
- Update to babashka process to v0.4.13: support (process opts? & args) syntax everywhere


### [Squint](https://github.com/squint-cljs/squint) and [Cherry](https://github.com/squint-cljs/cherry)

Squint and cherry are two flavors of the same CLJS compiler.  Squint is a CLJS _syntax_ to JS compiler for use case where you want to write JS, but do it using CLJS syntax and tooling instead. Cherry comes with the CLJS standard library and is as such much closer to the normal ClojureScript, but the minimal amount of JS is a little bigger.

In the past two months, I've been restructuring code between squint and cherry: a bit boring but necessary to keep going forward. Along with some minor bugfixes and features, one new JSX feature landed: you can pass a props map using a new notation inspired by [helix](https://github.com/lilactown/helix).  You can read details in the [changelog](https://github.com/squint-cljs/squint/blob/main/CHANGELOG.md).

The [video](https://www.youtube.com/watch?v=oCd74TQ-gf4) of the talk I did on ClojureDays 2022 came online!

### [SCI](https://github.com/babashka/sci)

Configurable Clojure interpreter suitable for scripting and Clojure DSLs.
This is the workhorse that powers babashka, nbb, Joyride, and many other projects. Many improvements have happened over the last two months, both in Clojure compatibility and performance. JS and JVM interop has become up to 5x faster. All of these changes benefit babashka, nbb, joyride, etc. See [changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md) for more details.


### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)

Static analyzer and linter for Clojure code that sparks joy... Two new releases with many fixes and improvements. [Check the changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details. Some highlights:

- [#609](https://github.com/clj-kondo/clj-kondo/issues/609): typecheck var usage, e.g. (def x :foo) (inc x) will now give a warning
- [#1846](https://github.com/clj-kondo/clj-kondo/issues/1846): new linters: :earmuffed-var-not-dymamic and :dynamic-var-not-earmuffed. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#dynamic-vars).
- [#1875](https://github.com/clj-kondo/clj-kondo/issues/1875): add :duplicate-field-name linter for deftype and defrecord definitions.


### [Scittle](https://github.com/babashka/scittle)
Execute Clojure(Script) directly from browser script tags via SCI. See it in [action](https://babashka.org/scittle/). Version 0.4.11 introduced the [re-frame](https://github.com/day8/re-frame) plugin. You can play around with it in the playground [here](https://babashka.org/scittle/codemirror.html). Several other releases were made. Details in the [changelog](https://github.com/babashka/scittle/blob/main/CHANGELOG.md).


### [Process](https://github.com/babashka/process)
Clojure library for shelling out / spawning subprocesses. This library traditionally had the syntax: (process [ & cmd-args ] ?opts) but in practice, it turned out that having the syntax (process ?opts & cmd-args) is more convenient, since you can use it with apply and *command-line-args*. All functions in babashka.process have been rewritten to support this syntax. See [changelog](https://github.com/babashka/process/blob/master/CHANGELOG.md) for details.


### [Quickdoc](https://github.com/borkdude/quickdoc)
Quickdoc is a tool to generate documentation from namespace/var analysis done by clj-kondo. It's fast and spits out an API.md file in the root of your project, so you can immediately view it on Github. Minor fixes and improvements were made.


### [Fs](https://github.com/babashka/fs)

File system utility library for Clojure. Fs has gotten one new function: update-file, that alters the contents of a (text) file using a function. The function is reminiscent of swap!. See [changelog](https://github.com/babashka/fs/blob/master/CHANGELOG.md#changelog) for more details.


### [Neil](https://github.com/babashka/neil)

A CLI to add common aliases and features to deps.edn-based projects. A NEIL_GITHUB_TOKEN can now be configured to avoid hitting the rate limit of the Github API, thanks to Russ Matney.


### [Quickblog](https://github.com/borkdude/quickblog)

Light-weight static blog engine for Clojure and babashka. The blog you're currently reading is made with quickblog. Version [0.1.0](https://github.com/borkdude/quickblog/blob/main/CHANGELOG.md#010-2022-12-11) was finally released with much thanks to Josh Glover. See [changelog](https://github.com/borkdude/quickblog/blob/main/CHANGELOG.md#changelog) for more details.


### [Rewrite-edn](https://github.com/borkdude/rewrite-edn)

Utility lib on top of rewrite-clj with common operations to update EDN while preserving whitespace and comments. Minor fixes and enhancements. Several functions have been added like fnil and conj. See [changelog](https://github.com/borkdude/rewrite-edn/blob/master/CHANGELOG.md).


### [Sci.configs](https://github.com/babashka/sci.configs)

A collection of ready to be used SCI configs for e.g. Reagent, Promesa, Re-frame and other projects that are used in nbb, joyride, scittle, etc. See recent [commits](https://github.com/babashka/sci.configs/commits/main) for what's been improved.


### [Nbb](https://github.com/babashka/nbb)

Scripting in Clojure on Node.js using SCI. Because it's so easy to deploy to npm, I usually publish a new version for each issue that is resolved. No big changes, but many small bugfixes and improvements in the last two months. See [changelog](https://github.com/babashka/nbb/blob/main/CHANGELOG.md).


### [Edamame](https://github.com/borkdude/edamame)

Configurable EDN/Clojure parser with location metadata. It has been stable for a while and reached version 1.0.0. The API is exposed now in [babashka](https://github.com/babashka/babashka) and [nbb](https://github.com/babashka/nbb) as well. See [Changelog](https://github.com/borkdude/edamame/blob/master/CHANGELOG.md)


### [lein2deps](https://github.com/borkdude/lein2deps)

Lein to deps.edn converter. This tool can convert a project.edn file to a deps.edn file. It even supports Java compilation and evaluation of code within project.clj. Several minor enhancements were made. See [changelog](https://github.com/borkdude/lein2deps/blob/main/CHANGELOG.md).


### [Joyride](https://github.com/BetterThanTomorrow/joyride)

Modify VSCode by executing ClojureScript (SCI) code in your REPL and/or run scripts via keyboard shortcuts. I'm working on this project together with Peter Strömberg (known for his work on Calva) and I'm mostly reviewing Peter's PR instead of writing code. Read the changelog [here](https://github.com/BetterThanTomorrow/joyride/blob/master/CHANGELOG.md).


### [Deps.clj](https://github.com/borkdude/deps.clj)

Regular maintainance, keeping up with the official Clojure CLI and tools jar!


### [Clj-kondo configs](https://github.com/clj-kondo/configs)

Library configurations as dependencies for clj-kondo. The claypoole configuration was improved.


### [Babashka CLI](https://github.com/babashka/cli)

Turn Clojure functions into CLIs! Minor fixes. See [changelog](https://github.com/babashka/cli/blob/main/CHANGELOG.md).

### Babashka pods

The [pods](https://github.com/babashka/pods) library contains the code that supports using pods in babashka and the JVM. A critical error was fixed that would hang babashka and a new JVM release was pushed to Clojars (v0.1.0).

### Babashka compatibility in external libs

I contributed to [RCF](https://github.com/hyperfiddle/rcf), [deep-diff2](https://github.com/lambdaisland/deep-diff2) and [clj-diff](https://github.com/lambdaisland/clj-diff) to make these libraries babashka compatible. Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).

_Published: 2023-01-06. Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)_    




## Project Clojure Data Cookbook: Kira McCLean

Last updated December 30, 2022

The work that's been done in this period for the Clojure Data Cookbook includes:

- Set up programming environment for the project. I experimented with a few different options and in the end decided to use a Clerk notebook. I set up the repo to include instructions on how to run the notebook locally for now, but the long term plan is to publish a static HTML artifact somewhere online that can be viewed in a browser.
- Completed code examples for sub-chapters 2.1 and 2.2, as well as some prose for the introductory chapter. Commits including this output are here: [https://github.com/scicloj/clojure-data-cookbook/commits/main](https://github.com/scicloj/clojure-data-cookbook/commits/main)
- Discovered and resolved issues in the underlying libraries, e.g. problems loading excel files with tablecloth, problems loading headers for different data input types, as a result of writing the examples.
- Researched the Clojure data science landscape and explored available solutions to different problems. Decisions about which libraries to use are mostly not finalized yet, but the survey of the ecosystem has given me a much better understanding of the existing tools that are available. This work was summed up in the talk I gave at this year’s re:Clojure conference: [https://www.youtube.com/watch?v=BxVtQM6FPHU](https://www.youtube.com/watch?v=BxVtQM6FPHU) 

The next phase will be spent filling out the code examples for the rest of chapters 2 & 3, as well as finalizing the introductory text for the first chapter.  


 

##  Project ClojureDart: Christophe Grand

(Disclaimer: because of various holidays, our months have been longer than calendar months...)

**First reporting period:** The biggest effort the during first period was the rework of our ill-named analyzer.

Dart doesn't provide the level of introspection the JVM provides — there are "mirrors" but they provide incomplete information and are only available to the Dart VM (not to natively compiled code or code compiled to javascript).  Being VM-only isn't a too big requirement since dev workflow is centered on the VM but not having precise enough information is a deal-breaker.  All the information we need is available through generated dart docs or through LSP. Both leverages [a lib of the Dart SDK which is called the analyzer](https://pub.dev/packages/analyzer).

Based on the same lib we had our own "analyzer" which was dumping in a single EDN file every piece of information about classes, fields and functions found in all libs of all deps (pubspec.yaml). Effective but slow as the dump may take minutes with large dependencies.  

So this period we reworked our analyzer to be a subprocess of the compiler and not a batch. This leads to faster startups without adverse consequence on compilation time (the compiler caches analyzer answers). Overall making the developer experience more pleasing.

**Second reporting Period:** During the second period we focused on making hot-reload less brittle.  This is significant for two reasons:
- It makes the dev experience more pleasing (the application needs to be restarted less often)
- It will serve as the foundation for the REPL (as hot-reload is the only way to get new code loaded).

The Dart VM can hot reload code. In practical terms it means functions and methods bodies can be updated while preserving existing state (at some point you'll end up with a mismatch with the new code and the old state and you'll have to "hot restart" which is a plain restart of the application -- but not of the VM). The goal of this game is to minimize how often the application has to be restarted. 

One crucial point are Dart top-levels (to which Clojure vars are mapped) which are initialized on demand. Once initialized you can't update the initialization code — or rather you can but it won't be run again. Which proves to be a problem for all vars which don't compile to a simple Dart function.  That's why it was frequently required to hot restart (and thus lose state) to see the effect of redefs.

With the changes we made, when a var is redefined it's compiled to a new top-level whose name contains a reload count. And all dependents are updated to point to the new name. Thus we get a fresh init and everything points to the new version (closing over a stale instance is still possible though but just like in Clojure). This hasn't been merged yet into `main`.  

While we are on unmerged branches: we have also sorted collections in the works, maps are done, sets are almost done, some helper functions are missing.  Sorted collections are not a hot topic and are rarely used, however we put a lot of work in them: they use a novel implementation influenced by B+trees, treaps and zip-trees.  An important feature of all ClojureDart maps (sorted or not) is that they are history-independent: their layout is determined by their content only, not by the insertion order nor the removal of some keys etc.  

This is going to allow us to offer accelerated operations on maps: merge, merge-with, join, diff, merge3. Hopefully this will enable some interesting in-memory databases.  




## Project Mathbox-cljs: Sam Ritchie

Final Update (January 25, 2023)

### Overview

First of all, apologies that this last phase stretched out over an extra month. I'm really happy with the amount of code I was able to ship; I'm especially pleased that I was able to keep my standards for writing and documentation high, though I did have to relax a touch to get these last two very complex libraries out the door.

### Libraries Shipped

I've shipped final versions of the following libraries, along with interactive docs notebooks for each:

- https://github.com/mentat-collective/mafs.cljs (https://mafs.mentat.org)
- https://github.com/mentat-collective/mathbox.cljs (https://mathbox.mentat.org)
- https://github.com/mentat-collective/leva.cljs (https://leva.mentat.org)

and was able to get started with a set of Clerk templates that will make it easy for folks to use all of these libraries:
https://github.com/nextjournal/clerk-cljs-demo/pull/2
Notice that MathBox.cljs, the original goal of all of this work, is out!

### Mafs
I noticed https://mafs.dev/ while trawling around for good 2d interactive visualization systems. JSXGraph is more complete, but Mafs is simple and beautiful; when I found it it wasn't in active development, but the maintainer has since done a ton of work and the project is moving quickly. I've been working on the API with Steven Petryk in his Discord, and submitted a [PR to speed up all linear algebra](https://github.com/stevenpetryk/mafs/pull/92) in the library. The API, specifically the "movable points", relied on React hooks; the biggest change required by this wrapper involved making these points work well with Reagent's atoms with the ease that ClojureScript developers should expect.

### MathBox

I updated the MathBox README with instructions on how to use all of our new work from JS land: https://github.com/unconed/mathbox

The interactive docs page contains a full port of the "getting started" intro from MathBox: https://mathbox.mentat.org/

There are a bunch of things that this wrapper can do much better than the original library, thanks to React. I've continued to work with Chris Chudzicki to push improvements back to his mathbox-react library.
I was able to port a bunch of the examples over to MathBox.cljs, but my index page is currently broken due to a Clerk bug... I'm working on a [fix here](https://github.com/mentat-collective/MathBox.cljs/pull/13) that should be out and announced soon.

### Leva.cljs

This worked out REALLY WELL... I spent a lot of time making every input element in Leva compatible with my "sync via atom" approach, and I think it feels even better than the original. The docs at https://leva.mentat.org/ are certainly better than anything in JS land.

### Last Month's Stretch Goals

I wasn't able to complete the Clerk project templates, but those are the next logical thing to do, so I'll move on to that next. I've also changed the name of my SICMUtils project to "Emmy", to prepare for integration with all of these libraries. I've started work on my "ultra-fast ODE solvers" at https://github.com/sicmutils/sicmutils/pull/533 (yet-to-be-migrated to Emmy).

Thanks to Clojurists Together, I now have a stable foundation of every 2d and 3d visualization that I need to represent all of Emmy's mathematical objects and algorithms... and the community has all of these tools as well, along with the guarantee that they all work with Clerk and Reagent, and coming-soon guides on how to integrate all of this easily. (Of course each docs notebook is sort of a guide on how to integrate all three.)  



## Project Tablecloth: Ethan Miller

 Final Update (December 31, 2022)

-   During this last quarter, I dug into the problem of lifting the incredible set of array operations present in dtype-next's \`tech.v3.datatype.functional\` namespace into tablecloth.
-   Currently, the practice is to import these operations externally and use them with tablecloth. But as part of the tablecloth columns project, there was a feeling that a big part of this project is to "lift" these operations into tablecloth and make them work with the columns and then eventually with the dataset.

-   The decision to do this was a result of conversations [here](https://clojurians.zulipchat.com/#narrow/stream/151924-data-science/topic/use.20cases.20for.20a.20tablecloth.20column.3F>) that I summarized in the [last project update](https://humanscode.com/columns-for-tablecloth-ii) Ultimately, the goal is that these functions will take a columns and return either a scalar or a column where appropriate: \`(column) => column. In the future, the same operations can be used on the dataset. 
-   I started this effort with some discussion of the right strategy for lifting these functions - see [here](https://clojurians.zulipchat.com/#narrow/stream/236259-tech.2Eml.2Edataset.2Edev/topic/.E2.9C.94.20.22lifting.22.20dtype-next.20functions.20into.20Tablecloth.20column). We decided not to use macros but take a code generation strategy where we write the functions namespace ahead of time. There's an existing "lifting" operation in tablecloth that adapts tablecloth functions so they will work with the pipeline's of scicloj.ml. This code uses macros which generate the functions at compile time, but this approach didn't work well with the dev tooling. By writing the namespace ahead of time, we can get slightly better integration with tooling. 
-   The work on this lifting operation is ongoing, but has gone through one round of review. The pull request is [here](https://github.com/scicloj/tablecloth/pull/90). It will be merged soon once the set of lifted functions is more complete.

-   Looking forward to the next quarter, my plan is to wrap this PR up, covering most of the functions in the\`tech.v3.datatype.functional\` namespace. After that, given that ther's only one more quarter in this project, I am hoping to spend sometime thinking with Tomasz Sulez (author of Tablecloth) about the minimum viable version of this column project that could be merged into the main branch. Hopefully, by the end of the following quarter we can merge that MVP.
