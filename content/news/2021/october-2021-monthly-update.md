---
title: "October 2021 Monthly Update"
date: 2021-11-18T06:30:00+08:00
author: Alyssa Parado
summary: Read updates from Clojure LSP, Clojurians-log-v2, Malli, PCP and our long term projects with Bozhidar Batsov, Michiel Borkent, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov.
draft: true
---

#### This post includes also our long-term projects updates. Here are the project updates from the second half of October to November. 


## Clojure LSP

This month we had 2 important releases with a lot of fixes and improvements!

#### release [2021.10.20-13.04.11](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2021.10.20-13.04.11)

This release had some big core changes like replacing the cache db implementation from `sqlite` to `datalevin`, this increase a lot the performance of saving and reading the cache improving clojure-lsp startup time.
Besides that, a lot of bugs and improvements were made like auto refresh settings if any changed, performance improvements, new code actions to create tests from current function, and last but not least, the `clojuredocs` integration, showing examples, documentation, notes about the current symbol!

Here is the changelog of this release:

- General
  - Improve initialization feedback report messages, this is important for user knows what server is doing during it's initialization and how much time left.
  - Consider `dev` and `test` alias for deps.edn projects as project-specs during classpath lookup. #586
  - Avoid scanning source-paths twice, as it was being considered as part of external classpath as well.
  - Change cache db from sqlite to datalevin for faster startup + better graalvm compatibility.
  - Make the cache analysis save async to make startup faster.
  - Support Auto refresh settings memoizing with a ttl of 1 second avoiding the need of restarting server when changing configs. #502
  - Bump clj-kondo adding new `gen-class` linter and other fixes/improvements. Fixes #589
  - Remove unused duplicate require if any. #527 
  - Fix crash on clean-ns when ns contains comments.
  - Improve project analysis filter to check source-paths. #597
  
- Editor
  - Add reference code lens for ns forms. #578
  - Fix expand-let bug that occurs when a list form precedes let. #590
  - Add new command to create test for function at point. #582
  - Add new code action to create test for current function/var
  - Add `private` to documentSymbol to make clear that a var or function is  `private`.
  - Add new code action `Suppress xxx diagnostic`, adding clj-kondo comment code to ignore the diagnostic. #591
  - Add more semantic tokens: aliases for macros, variable and function definitions.
  - Add [clojuredocs](https://clojuredocs.org/) information during symbol hover. #571
  

#### release [2021.11.02-15.24.47](https://github.com/clojure-lsp/clojure-lsp/releases/edit/2021.11.02-15.24.47)

This release added some new features like new live rename locals on files with the new LSP `LinkedEditingRange` feature, new `Unwind thread` code actions, huge improvement on `Add missing require` code action making it smarter on finding which namespace is related to the unresolved alias, and the change of `:notify-references-on-file-change` from false to true after important performance improvements on clojure-lsp, which this clojure-lsp will have more consistent diagnostics about user's projects becoming a more reliable tool.

Here is the changelog of this release:

- General
  - Bump Graalvm from 21.2.0 to 21.3.0 improving binary performance/size
  - Fix wrong parse of code when code contains namespaced maps like `#:foo{:bar 1}`. This issue was affecting a lot of features for example code actions.
  - Bump datalevin from 0.5.26 to 0.5.27.
  - Improve semantic tokens for dynamic vars, function definitions, namespaced and aliased keywords.
  - Fix bug where `:source-paths` settings could be hot-reloaded with wrong-value.
  
- Editor
  - Deprecates setting `:show-docs-arity-on-same-line?` in favor of `:hover` `:arity-on-same-line?`.
  - Add support to new LSP `LinkedEditingRange` feature. #341
  - Improve suggested `Add require ...` code actions, this should make clojure-lsp smarter when user wants to add a missing require. #614
  - Change `:notify-references-on-file-change` default from `false` to `true`, we had some performance improvements and I've been testing this for some time now and didn't see any new issues with that. This should improve a lot the UX when user change any code that is references on other files, updating the diagnostics for those files as well.
  - Improve rename feature UX to output errors when it's not possible rename.
  - Add support for `window/showDocument` LSP method, used on `create-test` command/code action after creating the test to show the test file.
  - Add new `Unwind thread once` and `Unwind whole thread` code actions to undo a thread call.
  - Improve code actions performance requesting async all actions.
  - Add new LSP custom method `clojure/clojuredocs/raw` which takes a symbol and a namespace (both strings) and returns any Clojuredocs entry found, otherwise `null`.
  - Fix missing keywords rename/references for destructured keywords.
  
- CLI
  - Show error/warning message when a classpath scan fail during analysis. Fixes #626
  - Add coloring to `diagnostics` output matching diagnostic severity.



## Clojurians-log-v2

Hello fellow clojurians!

The alpha release of Clojurians log v2 is now live at: [https://clojurians-log-v2.oxal.org/](https://clojurians-log-v2.oxal.org/) 🦄 😊

Source code: [github.com/oxalorg/clojurians-log-v2](https://github.com/oxalorg/clojurians-log-v2)

PS: it's not 100% usable on mobile yet, please use a desktop for the time being!

This release brings in the following features:

* Search across the entire slack logs
    - try it here: https://clojurians-log-v2.oxal.org/search?q=hello+clojurians
    - lots of work is still pending to get this into an actual usable state, but hey it works! 💃
    - searches over 2 million messages in ~50-500ms!
* ansible scripts to setup the server
    - setup user, fail2ban, firewall, postgres, docker, java, clojure, git, caddy, SSL, etc.
* 🐿 Web app deployed live on a domain 🌏 on a Hetzner VPS (2cpu, 4gb ram)
* setup docker deployment and recreating process
* setup the production postgres db
* idempotent import data for channels, members, and messages
    - it works (atleast for the current schema, most likely to change soon)
    - fixed mysterious null character issues in slack dumps, postgres really doesn't like to eat `\u0000` chars for breakfast
    - only partial data is imported (~2 million messages) as I only have slack dumps till June 2021
    - expect things to break because I'm still messing around with indexes, rankings, and migrations to optimise search and other queries 🤪
    - imports for reactions, threads, etc are still pending
* added `mrkdwn` message rendering with emojis
* more advanced configuration, dynamic based on `:dev` vs `:prod`
* proper support for secrets via a `secrets.edn` file
* lots of small bug fixes, UI/UX changes, optimized db queries, refactoring, etc.

I wanted to ship the minimal possible version first and then iterate over it. And I'm quite happy with this. Looking forward to adding lots more features and polish over the next few months, especially related to text searching!

Please feel free to hop over in #clojureverse-ops channel in clojurians slack to discuss anything related to this project! Thanks.


## Malli

Have spent a lot of time working with Malli internals: on hammock, paper protos,
reading about stuff done elsewhere, perf tuning and testing, better designs, in the mushroom forest,
refactoring and finally, an initial new internal design I'm quite happy with.

#### The Performance gains

* Malli has now an 1-2 orders of magnitude faster Schema inferrer
* Malli's [runtime validation and transformation performance](https://github.com/metosin/malli#performance) is on most cases better than using idiomatic hand-written Clojure, while being fully declarative.
* Schema creation and transformations are 10-200 times faster than before
* Schema workers can be effectively cached yielding much better dev experience (and perf)
* Relevant resolved issues and PRs:
  * malli.providers/provide takes "forever" for large samples, [#191](https://github.com/metosin/malli/issues/191)
  * Performance of Schema and Worker Creation, [#513](https://github.com/metosin/malli/issues/513)
  * Schema AST - Fast Schema Creation and Transformations, [#543](https://github.com/metosin/malli/issues/543)
  * Perf part4, [#539](https://github.com/metosin/malli/pull/539)
  * faster mu/merge, [#540](https://github.com/metosin/malli/pull/540)
  * Schema AST, Cached and other performance improvement, [#544](https://github.com/metosin/malli/pull/544)
  * Cleanup, Entry parser protocol, worker caches and fast inferring, [#550](https://github.com/metosin/malli/pull/550)

#### The Release

All the current improvements are shipped in `[metosin/malli "0.7.0-20211031.202317-3"]`, will test it properly againt real projects before releasing 0.7.0. See the full [CHANGELOG](https://github.com/metosin/malli/blob/master/CHANGELOG.md#070-20211031202317-3-2021-10-31).



## PCP

#### TLDR
[x] Created docker image for PCP
[x] Created [template project](https://github.com/alekcz/pcp-template) with  1-click deploy to DigitalOcean 
[x] Improved security with strict mode by only allowing access to `/var/pcp/`
[x] Automatically route domains to similarly name folders

Hello friends. Hope you're all well. It's been a pretty productive month and half. I'm pretty happy with the progress so far and I hope you'll be too.

These last 45 days I focused on achieving a 1-click deploy to DigitalOcean (DO). Unfortunately DOs app platform doesn't natively support Clojure (or Java for that matter), it does however support docker images. And so I went down the long winding path of creating a docker image for PCP and deciphering the DO spec for 1-click deploy apps. 

Out of this herculean effort (aka skimming the docs and paying the price)  a number of default options are now configurable via environment variables; when PCP was only service this wasn't necessary. We also got 1-click deploys to DO from this effort. That "Deploy to DigitalOcean" really is a thing of beauty. 

Once deploying was working it dawned on my that further changes to the core of PCP were required. You see hosting multiple sites per instance was pretty simple when PCP was running as a service on a VPS, but now with docker some changes were necessary. After some tinkering, PCP now routes requests based on the host name of a request so your various domains will be routed to the right place with minimum admin on your part. e.g. navigating to `www.clojuriststogether.org` will send PCP looking for a project in `/var/pcp/www.clojuriststogether.org/`. If no folder matches the host name PCP targets `/var/pcp/default`. Pretty neat, right? 

#### So what's next? 
There are a few more tweaks I'd like to make so that copying the template project easier. From there my focus will then shift to the CLI and deploying from git. Depending how things go I might also create a 1-click deploy to Heroku. My only hesitation with Heroku is their pricing for upgrades, it's tad too steep. 

That's all from me. Stay frosty.

Alex



#### Here are the updates from our long-term project grantees.

## Bozhidar Batsov

My focus for the past couple of months were:

- nREPL 0.9 (we're more or less feature-complete there)
- clj-refactor/refactor-nrepl 3.0 (released)
- CIDER 1.2 (ETA Dec)

The main improvements to CIDER in this period were:
- proper support for sideloading
- integration with enrich-classpath (a Lein plugin that automatically fetches Java sources and Javadoc)
- babashka support in cider-jack-in

As usual there was also some bug-fixing, documentation updates and lots of support work going on.



## Michiel Borkent

I'll be going over the releases of existing and new projects and will mention highlights in some of them. Often, the devil is in the details and there aren't any new user-facing features, but still a lot of work has gone into refining existing features.

Note that the projects listed below are not exclusively my effort and are worked on by a number or regular contributors. My thanks also goes out to them.

#### [Nbb](https://github.com/babashka/nbb)
Ad-hoc CLJS scripting on Node.js using SCI.

Many releases on [npm](https://www.npmjs.com/package/nbb).

Highlights:

- Console REPL. If you have Node.js simply type npx nbb and you will be dropped into a REPL.
- Socket server REPL
- nREPL server for development with Calva
- Add **clojure.test** so you can now use nbb to develop e.g. browser tests using puppeteer or playwright
- Support for reader conditionals using **:org.babashka/nbb**
- Print nicer stacktrace when error happens (similar to bb)
- Misc. fixes and enhancements.
- Add **js-interop** module
- Add Javascript API

Many exciting things are happening around this project in the community. It's now possible to run nbb on [lambda](https://github.com/vharmain/nbb-lambda-adapter). [Exercism](https://github.com/babashka/nbb/discussions/91#discussioncomment-1510273) is using babashka and nbb to run Clojure exercise submissions. [Sitefox](https://github.com/chr15m/sitefox) is a CLJS + Node.js web framework that works well with nbb.


#### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)
A linter for Clojure (code) that sparks joy.

Releases: [2021.10.19](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#20211019), [2021.09.25](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#20210925), [2021.09.15](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#20210915), [2021.09.14](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#20210914)

Highlights:

- New linter: warn on missing **gen-class** when writing **-main** function. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#main-without-gen-class).
- New **loop** without **recur** linter. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#loop-without-recur).
- Several inference improvements, e.g. **(def f (fn [])) (f 1 2)** will now give an arity warning.
- Return arbitrary metadata in analysis data. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/analysis/README.md).


#### [Babashka](https://github.com/babashka/babashka)
Native, fast starting Clojure interpreter for scripting.

Releases: [0.6.4](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#064), [0.6.3](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#063), [0.6.2](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#062), [0.6.1](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#061), [0.6.0](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#060)

Highlight:

- Support for java.net HTTP Client (via raw Java interop). This enables running [java-http-clj](https://github.com/schmee/java-http-clj) from source.



#### [Api-diff](https://github.com/borkdude/api-diff)
Print API diffs between library versions.

This is a new project.


#### [SCI](https://github.com/babashka/sci)
Configurable Clojure interpreter suitable for scripting and Clojure DSLs.

Releases: [0.2.7](https://github.com/babashka/sci/blob/master/CHANGELOG.md#v027)

Highlights:

- Loads of JS improvements coming from usage in [nbb](https://github.com/borkdude/nbb). Printing in JS targets can now be controlled via *print-fn* like in ClojureScript.



#### [Neil](https://github.com/babashka/neil)
A CLI to add common aliases and features to deps.edn-based projects.

This is a new project.


#### [Edamame](https://github.com/borkdude/edamame)
Configurable EDN/Clojure parser with location metadata

Releases: [0.0.12](https://github.com/borkdude/edamame/blob/master/CHANGELOG.md#0012)


#### [Scittle](https://github.com/babashka/scittle)
The Small Clojure Interpreter exposed for usage in browser script tags.

Releases: [0.0.4](https://github.com/babashka/scittle/blob/main/CHANGELOG.md#v004)


#### [Babashka.fs](https://github.com/babashka/fs)
File system utility library.

Releases: [0.1.0](https://github.com/babashka/fs/releases/tag/v0.1.0)

Highlight: **add fs/zip** function.


#### [Deps.clj](https://github.com/borkdude/deps.clj)
A faithful port of the clojure CLI bash script to Clojure. Used as native CLI, deps resolver in babashka and getting started REPL in Calva.

Various [releases](https://github.com/borkdude/deps.clj/releases)


#### [Graal-build-time](https://github.com/clj-easy/graal-build-time)
Library to initialize Clojure packages at build time with GraalVM native-image.

This is a new project.


#### [Graal-config](https://github.com/clj-easy/graal-config)
GraalVM native-image configurations distribution for Clojure libraries.

This is a new project.


#### [Jet](https://github.com/borkdude/jet)
Releases: 0.0.16

Highlight: allow keywordize fn to access all available conversion functions from camel-snake-kebab lib. e.g. **csk/->PascalCase**.


#### [Digest](https://github.com/clj-commons/digest)
Digest algorithms (md5, sha1 ...) for Clojure.

I took over this library from Miki Tebeka and I'm maintaining it under clj-commons.


#### [Carve](https://github.com/borkdude/carve)
Carve out the essentials of your Clojure app.

Fixed a regression.



## Dragan Djuric

My main objective in September and October was to improve Deep Diamond's support for advanced neural networks.
Previously, Deep Diamond supported fully connected layers, convolutional layers, and a bunch of supporting
infrastructure for effective learning, including access at different abstraction tiers, from lowest, tensor routines level,
to automatic network construction from pure, simple, Clojure functions. Tensor operations were quite well developed, too.
However, the high level API only supported sequential architecture of layers (which is quite useful) but
not branching that would enable advanced direct acyclic graphs, which is used in many modern networks.
This required implementation of relevant lower level concepts and the integration with appropriate Intel DNNL routines.
While I was at DNNL, I decided to integrate a few other layer types, which will be used all-around in network training.

This includes:

    - Implementation of Bach Normalization (API, functions, layer, and the DNNL backend)
    - Concatenation (API, functions, layer, DNNL backend)
    - Branching (API, functions, layer, DNNL)
    - Sum (API, functions, layer, DNNL)
    - Split (API, functions, layer, DNNL)
    - Parallel network branches support
    - Support for directed acyclic graphs in high-level API
    - Implementation of Reduction operation in DNNL backend
    - Removing a few protocols and concepts that were rendered obsolete by the new code
    - Improving support for simpler resource requirements for inference-only networks
    - Several important bugfixes (that took lots of time to even identify bugs, so it was subtle but important for future robustness)
    - Tests for all this
    - Research (literature, theory) in the background

All this work solidly, but needs more polishing AND functional tests with real networks, so I was not able
to cut a release (of course, whoever wants to experiment, can easily clone the repository from GitHub and build their own snapshot).
I plan to tackle this in the next period.

In particular, I need to implement the same stuff on the GPU with cuDNN, and properly test and polish this new functionality.

I hope that in the next two months I'll be able to do that, and release this major improvement. After that,
the next big chunk of work will be Recurrent Networks support, as the last major feature that was missing from DD.

In addition to this, I devoted lots of time to learning skills and early preparations for a new Clojure project
that I plan to start with a new Clojure programmer. There's a long path ahead, so it might be many months until
    we're ready to actually build and release something, but I hope that this long shot will be interesting to many Clojurists, and hopefully many not(yet)-Clojurists.



## Thomas Heller

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

I did some work on the shadow-cljs UI as well, most notably:

- Making the Inspect browser slightly smarter when using maps with mixed keys (eg. vectors and keywords). When displaying maps Inspect tries to sort keys before displaying. This previously failed when mixed keys where used but now works fine. Sorting is important since otherwise hash-maps may appear is a somewhat random order making it difficult to find certain keys you may be looking for.
- First draft of "Explore Runtime", you can find it in the shadow-cljs UI Runtimes tab (defaults to http://localhost:9630/runtimes). The idea was to display all loaded namespaces and their vars in a somewhat structured manner and letting you click around and work with it. Turns out that this requires much more work to be of any actual use (eg. namespace/var filtering+search) but it works for CLJ and all CLJS build targets that support the REPL. Even without any builds you can always test this using the default Clojure Runtime (ie. the runtime shadow-cljs itself is running in) and exploring the shadow-cljs internal runtime state. Find the `shadow.cljs.devtools.server.runtime` namespace and the `instance-ref` in there. Hope to make this all a little more usuable over time. Ideas are welcome.



## David Nolen

* ClojureScript JIRA tickets
* Update ClojureScript to latest Google Closure Compiler /
  Lbrary - handle breaking changes, future-proofing work
  


## Nikita Prokopov

Hi, this is Niki Tonsky and we are building a Desktop UI Framework in Clojure!

The work on UI framework started in September with a blog post that collected everything I knew about UI frameworks. This is that post: https://tonsky.me/blog/clojure-ui/

The post caused a fantastic feedback and filled my read/watch list for weeks to come. In reaction to that feedback, I did three public interviews with developers involved in UI programming:

- [Raph Levien on Rust Druid](https://www.youtube.com/watch?v=PwuwG2-0n3I)
- [Adrian Smith on Clojure Membrane](https://www.youtube.com/watch?v=Nk6mcyv7Sz4)
- [Yakov Zhmurov on Epam Unified UI](https://www.youtube.com/watch?v=qn6M2WO_TDE) (in Russian) 

Both JWM and Skija were migrated from JetBrains Github into the neutral HumbleUI organization and deployed to Maven Central repository.

JWM got a few quality-of-life fixes regarding mouse scroll, themes, z-order and macOS crashes. We also got native-image example contributed.

Finally, as the culmination of all that preparation, the first commit to the new Clojure UI framework [has been made](https://github.com/HumbleUI/HumbleUI/commit/d8eca60ef77f22a26d472fb38440109928b72f47)!

Another big thing that happened in October is Sublime Clojure: a new Clojure REPL client for Sublime Text.

Github repo: https://github.com/tonsky/sublime-clojure
Blog post: https://tonsky.me/blog/sublime-clojure/
Youtube lecture on Clojure REPLs in general: https://www.youtube.com/watch?v=Rdk5r1bjBoc

DataScript, Uberdeps, Tongue are all continued to be maintained and got few PRs merged.

Finally, one more post was written in September https://tonsky.me/blog/python-build/

My hope is that majority of November and December will be focused on Humble UI from now on.

If any of that sounds exciting to you and you want to participate, both [JWM](https://github.com/HumbleUI/JWM) (Java/C++) and [Sublime Clojure](https://github.com/tonsky/sublime-clojure) (Python) need a few helping hands and both have a huge scope for contributions. Let me know!
