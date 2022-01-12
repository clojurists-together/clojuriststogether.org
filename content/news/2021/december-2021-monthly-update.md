---
title: "December 2021 Monthly Update"
date: 2022-01-08T05:40:00+08:00
author: Alyssa Parado
summary: Read updates from Dependabot Core, Clojure LSP, Typed Clojure, Polylith, Holy Lambda, Clojurians-log-v2, Malli, PCP, and our long term projects with Bozhidar Batsov, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov
draft: true
---

Happy New Year everyone! I hope all of you had a wonderful holiday season with your loved ones. Below are the recent project updates to wrap up the end of the year 2021. 



## Dependabot Core

The dependabot core project has successfully been quick-started by having a technical conversation with Daniel.

We rapidly figured out that the people at github are currently considering a major rewrite/upgrade of the current apis, one that wouldn't
"require us to write a whole bunch of ruby" we realized later on.

Shortly after, Github's stakeholders were contacted involved in the process of figuring out the best way to build the system.
No meeting has been set yet but daniel's went ahead and set up a doodle for all us.
I also made sure to create a slack channel on the clojurians slack for communications on this.

For the next weeks, we're hoping to understand where the dependabot team is heading towards in terms of designs, and hopefully be able to tag along and start writing code that would benefit from the new apis.
I'm also hoping to be able to involve as many people as possible in the work.





## Clojure LSP

#### release [2021.11.16-16.52.14](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2021.11.16-16.52.14)

This release was focused mostly on housekeeping, minor fixes and improvements to keep features more mature and stable for most cases. We had huge improvements on  keywords completion, completion snippets improvements adding documentation and other details to common clojure.core snippets, code actions tune and more.
One of the new features was the ability to suggest require an already refer required before in other namespace, a very requested feature.

Here is the changelog of this release:

- General
  - Improve rename feature to not heavily rely on valid source-paths for most cases.
  - Fix setTrace exception logs for graalvm native images.
  - Huge improvements on namespaces renames and namespaces references find. #573
  - Fix/Remove warnings during datalevin access.
  - Improve freezing for some MacOS cases. #631
  - Bump clj-kondo to `2021.10.20-20211116.110002-7` improving code parsing and other fixes.
  
- Editor
  - Fix "Add require" code actions adding multiple requires instead of the selected.
  - Improve "Add require" wording, making it easier to understand what each different action will do.
  - Smart check all available refers to require, adding refer options to `Add require` code actions. #627
  - Big improvements on keyword completions. #630
  - Add setting `keep-parens-when-threading?` to keep parens for single arity functions when threading. #636
  - Avoid adding duplicate requires when adding a new require via code action. #640
  - Improve common known snippets to replace completion items, improving completion UX. #638
 

#### release [2021.12.01-12.28.16](https://github.com/clojure-lsp/clojure-lsp/releases/edit/2021.12.01-12.28.16)

This was a important release for community, a lot of new features we added, the main highlights are: It's possible to generate stubs for specific namespaces, this is a huge step for clojure-lsp as now it's possible to get most clojure-lsp features working even for closed source libraries like `datomic.api`.
There are new code actions like `Sort map` to sort a clojure map keys alphabetically and `Create function` which creates a unknown public function on the specified namespace or even create the namespace and require it automatically if doesn't exist yet.
We improved the `deps.edn` source-path discovery, improving support mostly for mono-repo projects like `polylith`! 

Here is the changelog of this release:

- General
  - Add support for LSP method `textDocument/prepareRename` which it's the proper way to check if the rename will work correctly. #642 
  - Expose new custom method `clojure/cursorInfo/raw` for custom hack on current cursor information code. #645
  - Support stub generation using `clj-easy/stub`, adding analysis and linting support for closed sources codes like Datomic. Check `:stubs` settings for more details. #637
  - Handle config deep merge differently for collections, concating instead of overwriting. 
  - Fix unnecessary exception thrown on graal images during startup.
  - Support `deps.edn` `:local/root` source-paths discovery, improving support for monorepo projects like `polylith`. #652
  - New setting value for `:clean :sort :require`: `:lexicographic`. #654
  - Bump clj-kondo to `2021.10.20-20211126.151305-16`.

- Editor
  - Support completion on aliased keywords. #649
  - Add new `Sort map keys` refactoring code action. #651
  - Add new `Create function` code action, allowing to create a function on a existing namespace or creating a new namespace + the function. #646
  - Improve `Extract function` refactoring to consider comments above current function. 
  - Experimental: new `:linters :clj-kondo :async-custom-lint?` setting, when true, scan unused-public-vars async improving lint/analysis UI feedback for huge buffers (> ~1000 lines). Default `false`.





## Typed Clojure

The goal of [this project funded by Clojurists Together](https://www.clojuriststogether.org/news/q3-2021-funding-announcement/) is to
improve static type error messages in [Typed Clojure](https://github.com/typedclojure/typedclojure),
specifically to replace expanded code in error messages with surface-level syntax.

In the first half of the project (last update), I concentrated on three main areas:
1. Increase direct support for problematic clojure.core macros
2. Improve error messages for inlining functions
3. Identify classes of implementation quirks in core Clojure macros to prepare for potential typing rules

In the second half (this update), I:
- Improved the experience of mixing clojure.spec and Typed Clojure
  - Typed Clojure no longer complains about the expansion of `s/def` `s/fdef`
  - Approach: implemented custom typing rules for both macros that ignores the body
  - [Commit](https://github.com/typedclojure/typedclojure/commit/2c423fb3daacaeb120b54a390c315588bba531a5)
- Trimmed error messages
  - Some hints in error messages have become irrelevant noise.
  - [Commit](https://github.com/typedclojure/typedclojure/commit/613691ff176d05dd886a5d387979868a5f5ab2bb)
- Created a proof-of-concept cljs.analyzer variant that can partially macroexpand
  - inspired by the inlining work from the first half of this project
  - Problem: CLJS uses lots of macros. Typed Clojure errors are even worse in CLJS.
    - eg., (+ 1 2) => (js* "(~{})" 1)
    - we want to pass (+ 1 2) to the type checker, not (js* "(~{})" 1)
      - but + is a macro call... not representable by cljs.analyzer
  - Solution: make partially expanded forms representable to the type checker
    - Type checker uses tools.analyzer-style AST's to analyze code
      - so does cljs.analyzer
    - I created typed.clj.analyzer to add an :unanalyzed form for partially expanded forms
      - but I didn't know how to port to cljs
  - Promising early results
    - unit tests for pausing the expansion of the children of each AST :op
  - Next: integrate into cljs checker (which was on pause for several years and needs a lot of other updates)
  - [Commit](https://github.com/typedclojure/typedclojure/commit/c8c2f870d7f58ff8a8abfaa83f939b5a824bd5cb)





## Polylith

I haven't been able to work near as much this quarter as the previous one (where I worked too much to finish everything) and I couldn't manage to finish the big task "Custom commands" (issue 113) but is working on it. 

These tasks are finished but I will wait with a release till issue 133 is finished:

These tasks are finished but I will wait with a release till issue 133 is finished:
- [Issue 161](https://github.com/polyfy/polylith/issues/161): If test setup fails, tests should not run
- [PR 148](https://github.com/polyfy/polylith/pull/148): restore description/url to generated pom.xml
- [Issue 154](https://github.com/polyfy/polylith/issues/154): Allow entities to be symbols in "clojure -T" commands
- [Issue 144](https://github.com/polyfy/polylith/issues/144): poly flags :dev alias if it has no :extra-paths in it
- [Issue 145](https://github.com/polyfy/polylith/issues/145): Calculate the size of the library even if using short shas





## Holy Lambda

#### Version 0.6.0

Finally, I've removed the mandatory download of dependencies to the local `.holy-lambda` directory. Previously both `deps.edn` and `bb.edn` required a special `:mvn/local-repo` keyword pointing to `.holy-lambda` directory to compile the project in Docker context or to pack `babashka` dependencies into deployable zip. Now uberjaring Clojure sources happen without Docker. Additionally, `bb hl:babashka-sync` packs `babashka` dependencies via a combination of `bb uberjar` and `unzip`. Thanks to packaging changes, it will be easier to cache dependencies in CI by pointing to standard `$HOME/.m2` directory. It's also possible to use `polylith` now since HL stopped requiring mounting local dependencies in the Docker context.
Moreover, packaging changes allowed me to reduce the deployment artifact size for `babashka`.

I'm also pleased to announce a new extension for Holy Lambda request/response model that is [holy-lambda-ring-adapter](https://github.com/FieryCod/holy-lambda-ring-adapter).
The adapter supports the Ring request/response specification. Users may now utilize an adapter to run full-featured Ring applications on AWS Lambda. To boost productivity for API Lambda development, one can plug in an adapter with Jetty instead of relying on slow AWS Lambda API emulators.

AWS recently announced an AWS Lambda support for ARM-based processors. Running code on Graviton2 is beneficial for the users since both the cost per 1M invocations and the cold start time are lower. I've introduced a new [holy-lambda-builder](https://github.com/FieryCod/holy-lambda/pkgs/container/holy-lambda-builder) images hosted in Github Registry with the support to both ARM64 (aarch64) and AMD64 architectures. Furthermore, HL works now great with M1-powered Macs and aarch64 based builder images.

Babashka backend layers for each architecture are available in `AWS Serverless Repository`:

- [AMD64 Babashka backend Layer](https://serverlessrepo.aws.amazon.com/applications/eu-central-1/443526418261/holy-lambda-babashka-runtime-amd64)
- [ARM64 Babashka backend Layer](https://serverlessrepo.aws.amazon.com/applications/eu-central-1/443526418261/holy-lambda-babashka-runtime-arm64)


#### Version 0.6.1

Holy Lambda now automatically parses the inner body of the event according to `content-type` to `:body-parsed` field.
I've bumped the inner `babashka` dependency in the backend layer to version `0.6.3`.

#### Version 0.6.2

The new version brings some improvements in tasks and `babashka` backend. Users can now update tasks to the latest stable revision by invoking the `bb hl:update-bb-tasks` command. I was also able to speed up the `babashka` backend by removing the costly classpath calculation in the init step by taking into account the recent changes to packaging.

Babashka's backend layer now also doesn't depend directly on `holy-lambda`.

Why it's important?

-   users can now deploy the backend layer and provide their implementation of the custom runtime,
-   users can now update the `holy-lambda` independently by packaging a new version in the dependencies layer.





## Clojurians-log-v2
Hello fellow clojurians!

The beta release of Clojurians log v2 is live at: https://clojurians-log-v2.oxal.org/ 🦄 😊

Source code: github.com/oxalorg/clojurians-log-v2

The initial goal we had with clojurians=log-v2 was mostly around improved full
text searching. But as we all know the clojurians slack was sponsored for a paid
version for the next 1 year (thanks to all who got this done!) which means our
direction has to change a little.

Although full text search will be available and working on the v2, we would like
to focus more on other parts of the app now and find ways to make the logs more 
usefull because paid slack already does search very well.

With that said the beta release now had the following work put into it:

* The importing of data for threaded messages / replies / reactions has been
  added. This was huge as this was the last key pending in the idempotent
  imports 🎉.
* UI now shows all thread messages directly under the parent. This is effecient
  enough to not cause any slowdowns.
* The UI is now fully mobile responsive 📱. That means easy reading of logs from
  your bed, and also better SEO points by Google!
* That brings us to SEO. Not only are we fully compatible with the existing
  clojurians-log, but we have also added lots of open-graph tags, html structure
  and other small changes for optimal SEO brownie points. The improved seo tags
  should also now help "Clojure" to rank higher in the TIOBE index.
* Lots of behind the scene work to improve deployment strategy. Although not
  fully automated, I have some local scripts which gets me to deploy new
  versions quite easily. A future task would be to convert it into GH actions.
* Added support for db migrations using migratus.
* Analytics for the website are now PUBLIC. You can view them at
  https://clojurians-log-v2.goatcounter.com/
  
I would like to extend immense gratitude to the clojurists-together team and the
clojure community for helping us get here.

So what's next? We do have a near-future roadmap of things which should land in very soon:

* Right now all the data imports are manual. Implementing of real-time socket
  API and making it compatible / interchangable with the idempotent incremental
  imports (w/ option to backfill data) is posing a bit of a hassle which needs
  some more work
* Although we've got the reactions into the database, rendering of reactions as
  emojis on the UI is pending
* Contacting a clojurians slack admin to get the data up-to-date

After that, there are tons more ideas listed on the repo which should keep us
busy throughout the next year! Super humbled and excited to continue working on
this 🙏😊

Cheers and Happy New Years everyone 🌟🎄

Mitesh (@oxalorg)





## Malli

Part 2/3 by [Tommi Reiman](https://twitter.com/ikitommi).

<img src="https://raw.githubusercontent.com/metosin/malli/master/docs/img/malli.png" width=150 align="right"/>

#### Done Stuff

* Shipped one of the biggest releases of Malli, the 0.7.0 on Dec 7th! contained all the stuff done earlier + small fixes and more performance improvements & tests
* Wrote [High-Performance Schemas in Clojure/Script with Malli 1/2](https://www.metosin.fi/blog/high-performance-schemas-in-clojurescript-with-malli-1-2/) to celebrate the release
* Implemented a most wanted feature, [a swappable default registry](https://github.com/metosin/malli/blob/master/CHANGELOG.md#swappable-default-registry)
* Helping users and contributors (should do much more of this)
* 27 Closed PRs
* Pushed five small patch releases (0.7.1 - 0.7.5) with bug fixes, updated dependencies and some nice schema provider improvements:
  * support for `:map-of`, `:tuple` and `:enum` types
  * support for type-hints
  * schema inferring via value decoding (e.g. `:uuid`, `inst?`)
  * performance improvements
* Improvements to [clj-kondo](https://github.com/clj-kondo/clj-kondo) interop

#### TODO

Zillion things to do and 2 months to go. Currently working on [destructuring parser](https://github.com/metosin/malli/pull/606) to enable first-class support inline typehints and to support inferring of function schemas from vanilla Clojure/Script. Seems like the easy features are all done, just the hard ones left :)

Cheers.





## PCP

TLDR
* [x]  Create Project using CLI from [template project](https://github.com/alekcz/pcp-template)
* [x] Introduce dev daemon simplified local development
* [x] Reworked CLI for easier maintenance
* [x] Automated docker image creation 
* [x] Add convenience script for docker
* ~~Trigger Deploy from CLI~~
* ~~Deploy from git~~
* [ ] Create 1-click deploy to Heroku
* [ ] Document the new and improved PCP. 

Hello friends. I hope you had a pleasant holiday season. It's been an interesting 45 days for PCP. I'm in two minds about the progress to date. Hammock time overtook hand on keyboard time. The change was quite uncomfortable but I think overall better for the project. 

Initially my thinking was to add functionality like the firebase-cli with something along the lines of `pcp deploy` as well as something like heroku where `git push pcp` would trigger a deploy. These two approaches are critical in the absence of container based deploys. They are however (in my estimation) inferior if containerized deploys are possible. I spent a fair bit investigating both. 

- `pcp deploy` introduces the question whether latest in git is equivalent to the latest deployed version. 
- `git push pcp` requires either a standalone git server (not possible with a $5 VPS) or two a fro between the VPS and the git repository. 

Both of these issues are eliminated by connecting the pcp docker images and the project repository directly to DigitalOcean or Heroku (or any other app platform) as you get the the benefits of the $5 VPS (low price) without the downside (managing the infra). 

After coming this  conclusion improving the CLI became a lot more straight forward. The CLI is now easier to maintain and the end user can create project from the CLI directly in a manner that allows the project to be deployed to DigitalOcean directly. 

There are two task I need to complete as part of Clojurists Together funding specifically the new documentation and the deployment to Heroku so our end user isn't locked in to DigitalOcean. I expect to complete these in the next week or so.

So what's next? 
This update is the last one as part of the Clojurists Together funding. It's not the end of PCP though. 
I used PCP to build a simple In Memoriam during the last quarter. I now want to use PCP in anger, perhaps in e-commerce to really stress test it. I imagine I'll pick up some rough edges and bugs in the process. 

It's been real. Stay frosty.

Alex





## Bozhidar Batsov

In the past couple of months I've achieved the following major objectives:

* Released nREPL 0.9 (https://metaredux.com/posts/2021/12/12/nrepl-0-9.html)
* Released CIDER 1.2 (https://metaredux.com/posts/2021/12/22/cider-1-2-nice.html)

Some smaller achievements from this period:

* Lots of documentation improvements for CIDER (https://docs.cider.mx)
* CIDER is now available for installation from NonGNU ELPA (https://elpa.nongnu.org/nongnu/cider.html)
* cider-nrepl and orchard had new releases. Orchard finally achieved its goal to
get rid of its dynapath dependency and is now a dep-free library (which is a very desirable trait in development tooling).

Going forward I'll need to update Leiningen for nREPL (mostly to support starting a server listening to an Unix socket), solidify the API specs for nREPL and cider-nrepl and continue to polish the new CIDER functionality. If I have enough time and inspiration I might revisit sayid for a bit as well. Thanks to everyone for your support!





## Dragan Djuric

The main objective during November and December was to create a cuDNN-based GPU implementation of Deep Diamond's
support for advanced neural networks developed on the CPU in the previous two months.
Previously, Deep Diamond's cuDNN engines supported fully connected layers, convolutional layers, and a bunch of supporting
infrastructure for effective learning, including access at different abstraction tiers, from lowest, tensor routines level,
to automatic network construction from pure, simple, Clojure functions. Tensor operations were quite well developed, too.
This high level API only supported sequential architecture of layers (which is quite useful) but
not branching that would enable advanced direct acyclic graphs, which is used in many modern networks.
This required matching implementation of relevant lower level concepts that I've developed on DNNL, but backed by cuDNN.
While working on this, I fixed bugs that slipped previously, improved general code, and tests.

The following features were developed for cuDNN engine:

    - Implementation of Bach Normalization (API, functions, layer, and the cuDNN backend)
    - Concatenation (API, functions, layer, cuDNN backend)
    - Branching (API, functions, layer, cuDNN backend)
    - Sum (API, functions, layer, cuDNN)
    - Split (API, functions, layer, cuDNN)
    - Parallel network branches support for cuDNN
    - Tested cuDNN support for directed acyclic graphs in high-level API
    - Improved some internal code.
    - Several important bugfixes
    - Tests for all this
    - Research (literature, theory) in the background

Although that work finely summed up by the end of the month, I'm still not satisfied with the polish,
so I decided not to rush the release before doing further high-level tests. I expect to uncover some
more subtle bugs (of course, whoever wants to experiment, can easily clone the repository from GitHub and build their own snapshot).
I plan to tackle this in January, since I wouldn't be able to do more serious deep work during that time
due to numerous classes and exams.

I hope that in February I'll be able to have more uninterupted periods for the next major theme (still
undecided what will come first: Neanderthal sparse matrices, Neanderthal FFT, or some work on Deep Diamond support for recurrent networks).
Anyway, the next big chunk of work in February, March, and/or April should be Recurrent Networks support,
as the last major feature that was missing from DD.

In addition to this, I devoted lots of time to learning skills in preparation for a new Clojure project.
There's a long path ahead, so it might be many months until I'm ready to actually build and release
something, but I hope that this long shot will be interesting to many Clojurists,
and hopefully many not(yet)-Clojurists. (I realize that this sentence is verbatim copy/paste from the
last report, but it's the same long-term work that the sentence describes accurately. ;)





## Thomas Heller

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

Current shadow-cljs version: 2.16.10 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

Notable Updates

- Updated everything to support the latest ClojureScript and Closure Compiler/Library releases. This included some breaking changes originating from the Closure Library. More info is available in the [ClojureScript news post](https://clojurescript.org/news/2021-11-04-release).
- Added a new `^:dev/asset-load` metadata hook as requested by [#951](https://github.com/thheller/shadow-cljs/issues/951). This allows calling a CLJS function after a CSS file was hot-reloaded. This may be useful if you are using `getComputedStyle` and use the results somewhere in your code.

Example: 
```clojure
(defn ^:dev/asset-load css-updated! [path node]
  (js/console.log "css-updated!" path node))

;; path is the path used in <link href="...">
;; node is the actual link DOM node
```






## David Nolen

#### November
- ClojureScript core.async: #js literal support
- ClojureScript Test GitHub CI for Windows
- General ClojureScript JIRA issues review

#### December
- Support Node.js "exports" in package.json (Fixes Firebase v9 usage)
- ClojureScript Test GitHub CI for Windows
- General ClojureScript JIRA issues review





## Nikita Prokopov

Hi, this is Niki Tonsky and I have been writing lots of Clojure for the past two months!

#### [DataScript](https://github.com/tonsky/datascript/)

- 6 new releases.
- A full rewrite of `pull`/`pull-many`, up to 7× faster than before. `pull` is finally a better choice than `entity` performance-wise, as it was always supposed to be.
- Patches and PRs.

#### [HumbleUI](https://github.com/HumbleUI)

- [Unified build system](https://github.com/HumbleUI/Skija/blob/master/script/build_utils.py) between all projects.
- Skija upgdated to Skia m98.
- Common types between JWM and Skija extracted into [github.com/HumbleUI/Types](https://github.com/HumbleUI/Types).
- Simplified JWM usage: layers, event listeners, event propagation.
- Ship Skija layers with JWM (Skija linked optionally).
- All you need to get started with JWM now is create a window and provide `onPaint` handler. Everything else is optional.
- [Basic Clojure APIs](https://github.com/HumbleUI/HumbleUI/blob/214352b2296ad89091c7891d7407a8b19d172d14/dev/user.clj#L23-L49) already allow one to build simple (but dynamic!) UIs. A screenshot:

![](https://github.com/HumbleUI/HumbleUI/raw/main/extras/screenshot.png)

This first UI demostrates:

- JWM and Skija integration.
- Text labels (including Unicode).
- Correct text measurement (notice how perfectly centered text inside a button is).
- Nested components.
- Basic dynamic layout (center, gap, padding, column) relative to window size.
- Dynamic components (hoverable/clickable button, click counter).
- Composable and reusable components (see [the code](https://github.com/HumbleUI/HumbleUI/blob/214352b2296ad89091c7891d7407a8b19d172d14/dev/user.clj#L23-L49)).

#### [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed)

- 10 new releases.
- [Publicly announced](https://tonsky.me/blog/sublime-clojure/).
- Renamed from Sublime Clojure to pass Package Control naming guidelines.
- Still not merged to Package Control, but perfectly avaliable from sources.
- Many patches by me and [Jaihindh Reddy](https://github.com/jaihindhreddy).

#### [Uberdeps](https://github.com/tonsky/uberdeps/)

- Issue: [Munge Main-Class name](https://github.com/tonsky/uberdeps/pull/45) submitted by [Maks](https://github.com/mvproton).

#### [Tongue](https://github.com/tonsky/tongue/)

- Feature: [Override missing key message](https://github.com/tonsky/tongue/issues/32).

#### [net.async](https://github.com/tonsky/net.async)

- [Unbind server port when calling shutdown!](https://github.com/tonsky/net.async/pull/5) via [dpetran](https://github.com/dpetran).

#### YouTube:

- New [Talk on QML](https://www.youtube.com/watch?v=HU_fvRfY2VA) (in Russian)
- Advent of Code, [all 25 days](https://www.youtube.com/playlist?list=PLdSfLyn35ej-UL9AuxUvoFXerHac4RYnH) solved live in Clojure, Sublime Text and Clojure Sublimed in front of a camera.

#### Design:

- [New logo for clj-commons](https://tonsky.me/design/#clj-commons)
- [New logo for EQL](https://tonsky.me/design/#eql)

To wrap up 2021, I build this simple interactive demo in Humble UI [watch it here](https://youtu.be/qqfiWlr2YHw)

Happy New Year!


