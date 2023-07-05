---
title: "2023 Long Term Projects: May and June Updates"
date: 2023-07-05T08:30:00+08:00
summary: Hear from the 10 developers working on long term projects and a few shorter term project updates
author: Kathy Davis
draft: true
---  
<br>
There is a lot of work to catch up on! Our 10 developers working on long-term projects check in with their May and June project updates. We also have two updates from shorter Q1 2023 projects (Donut and Adelph/Manifold).  
<br>

### 2023 Long-Term Projects  
Bozhidar Batsov  
Michiel Borkent  
Thomas Heller  
Nikita Prokopov  
Peter Stromberg  
Tommi Reiman  
Sean Corfield  
Christophe Grand (waiting for report)  
Peter Taoussanis  
Eric Dallo  
<br>

### Q1 2023  
Project Donut: Daniel Higginbotham  
Aleph/Manifold: Matthew Davidson  
<br>

## Bozhidar Batsov  

### CIDER  
For the past few months the primary focus of my Clojure-related work was CIDER. We've made some important steps towards the next release:

- We're working on a "debug on error" functionality (see https://github.com/clojure-emacs/cider/pull/3337). This also resulted in some internal improvements to the existing debugger codebase. 
- We're working on a new way to interact with application logs from CIDER, which we currently call "cider-log-mode" (see https://github.com/clojure-emacs/cider/pull/3352)
	- This also resulted in the creation of the standalone library [logjam](https://github.com/clojure-emacs/logjam/), which CIDER uses internally.
- We're now using the latest version of `compliment`, which comes which a bunch of code completion improvements (see https://github.com/alexander-yakushev/compliment/blob/master/CHANGELOG.md#0315-2023-06-22)

As usual the next release will also feature a few smaller improvements and bugfixes that you can peruse [here](https://github.com/clojure-emacs/cider/blob/master/CHANGELOG.md).  Just as importantly we've started to discuss the plans for [CIDER 2.0](https://github.com/clojure-emacs/cider/issues/3356). Everyone's more than welcome to participate in the conversation there!  

In other news:
- There's a small new bugfix release of clojure-mode (see https://github.com/clojure-emacs/clojure-mode/blob/master/CHANGELOG.md#5161-2023-06-26)
- There are also new releases of orchard and cider-nrepl
- We're working on some improvements for refactor-nrepl and enrich-classpath  

---

## Michiel Borkent  
In this post I'll give updates about open source I worked on during May and June 2023. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work possible! Open the details section for more info.

**Top sponsors:**  
* [Clojurists Together](https://clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Toyokumo](https://toyokumo.co.jp/)
* [Cognitect](https://www.cognitect.com/)
* [Kepler16](https://kepler16.com/)
* [Adgoji](https://www.adgoji.com/)

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!

**[Github Sponsors](https://github.com/sponsors/borkdude):**
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)

If you're used to sponsoring through some other means which isn't listed above, please get in touch. On to the projects that I've been working on!


### May 2023 Updates  
The following projects had updates in May 2023.  Note that only highlights are mentioned and not a full overview of all changes. See the project's changelogs for all changes.

* Preparations for [babashka conf](https://github.com/babashka/conf) are in full swing and I'm preparing a talk with the title 'Growing an ecosystem'.
* This month I've had the honor to visit the JUXT 10 year anniversary in London and met a lot of fellow Clojurians over there.
* Babashka and SCI will be featured at the last iteration of [Strange Loop](https://www.thestrangeloop.com/)!
* My OSS work is funded by Clojurists Together in [Q2](https://www.clojuriststogether.org/news/q2-2023-funding-announcement/)
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * Version 2023.05.18 - 2023.05.26 were released. Full changelogs [here](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md). Highlights:
    * Linter `:uninitialized-var` moved from default `:level :off` to `:warning`
    * [#2065](https://github.com/clj-kondo/clj-kondo/issues/2065): new linter `:equals-true`: suggest using `(true? x)` over `(= true x)` (defaults to `:level :off`).
    * [#2066](https://github.com/clj-kondo/clj-kondo/issues/2066): new linters `:plus-one` and `:minus-one`: suggest using `(inc x)` over `(+ x 1)` (and similarly for `dec` and `-`, defaults to `:level :off`)
    * [#2058](https://github.com/clj-kondo/clj-kondo/issues/2058): warn about `#()` and `#""` in `.edn` files
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * Released 1.3.180, mostly a maintenance release
    * See the complete [CHANGELOG](https://github.com/babashka/babashka/blob/master/CHANGELOG.md)
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
    * [#58](https://github.com/babashka/scittle/issues/58): build system for creating scittle distribution with custom libraries. See [plugins/demo](https://github.com/babashka/scittle/tree/main/plugins/demo).
    * Use `window.location.hostname` for WebSocket connection instead of hardcoding `"localhost"` ([@pyrmont](https://github.com/pyrmont))
    * Upgrade `sci.configs` to `"33bd51e53700b224b4cb5bda59eb21b62f962745"`
    * Update nREPL implementation: implement `eldoc` (`info`, `lookup`) ([@benjamin-asdf](https://github.com/benjamin-asdf))
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Introduce all new programmatic [API](https://github.com/borkdude/deps.clj/blob/master/API.md)
    * Automatically use file when exceeding Windows argument length
* [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI
    * First clojars release
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Nbb is now compatible with [bun](https://bun.sh/). To run nbb in a bun project, use `bunx --bun nbb`.
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
    * Fix import with `$default`
* [cherry](https://github.com/squint-cljs/cherry) Experimental ClojureScript to ES6 module compiler
    * Support `with-out-str`
* [http-client](https://github.com/babashka/http-client): Babashka's http-client
    * Add `:authenticator` option
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
    * This project now has a configuration for datascript, for anyone who wants to use SCI together with datascript. See [this](https://github.com/babashka/sci.configs/commit/33bd51e53700b224b4cb5bda59eb21b62f962745) commit.
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Support `:require-macros`
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
    * Support `:pre-start-fn` in `exec`
    * Allow passing `:cmd` in map argument
    * Better testing for `exec` by [@lread](https://github.com/lread)
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
    * `:paths` argument for `fs/which` by [@lread](https://github.com/lread)
    * Support inputstream in `fs/copy`
    * Add `fs/owner` to return owner of file  

### Other projects  
These are (some of the) other projects I'm involved with but little to no activity happened in the past month.  
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
    * Improvements for reading namespaced maps
* [babashka.book](https://github.com/babashka/book): Babashka manual
    * Several corrections
    * Dynamic `:exec-args`
    * Script-adjacent `bb.edn` docs
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Support `--no-option` and parse as `{:option false}`
    * Support grouped aliase like `-ome` as `{:o true, :m true, :e true}`
* [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
    * Better error message when connection is not a string
* [instaparse-bb](https://github.com/babashka/instaparse-bb)
    * Add transform function
* [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
    * Add option to elide commas
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
* [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
* [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
* [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
* [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
* [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
* [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
* [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka
* [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
* [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
* [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
* [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
* [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
* [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
* [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
* [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter


### June 2023 Updates  
First a few updates of what I've been up to outside of sitting behind a screen. June was packed with two trips and a vacation so there aren't as many updates as usual.  
* [Babashka-conf](https://babashka.org/conf/) happened June 10th in Berlin. It was more than I could have hoped for: a professionally organized event with the creative, positive, welcoming spirit we're used to from other Clojure conferences. Thanks everybody for coming out, especially to the speakers, organization and Malcolm + staff for recording the videos. You can view all the talks [here](https://www.youtube.com/playlist?list=PLaN-rC-CjQqDu1AVhGdGOoEqsSAhd2W6t). To see photos, comments, etc you can look for the #babashka_conf hash-tag on social media.
* I've had the pleasure to visit the JUXT 10th year anniversary this month in London. Check out [this wonderful talk](https://www.youtube.com/watch?v=fT28NeZtaAg) by Alexander Davis on the state of frontend, with some nice comments about squint at the end.
* End of June and beginning of July I'm away on a [vacation](https://twitter.com/borkdude/status/1674401271372042240) to Switzerland to recover a bit from all of this fun. I hope to take it easy on the coding and just relax a bit and recharge for what is to come next.
* Babashka and SCI will be featured at the last iteration of [Strange Loop](https://www.thestrangeloop.com/)!

The following projects had updates in June. Note that only highlights are mentioned and not a full overview of all changes. See the project's changelogs for all changes.  
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler and [cherry](https://github.com/squint-cljs/cherry) Experimental ClojureScript to ES6 module compiler
    * Both projects can now be used simultaneously in one build. The use case for this is when you have projects like [clerk](https://github.com/nextjournal/clerk) that ship with multiple options for evaluating CLJS at runtime and you want to offer both squint and cherry as options.
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
    * Version 0.1.2 was released which contains upgrades of database drivers and next.jdbc library. Also a bug was fixed around mssql.
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Implemented an alternative to `shutdown-agents` which does not kill threads when using an exec function, e.g. when spinning up a web server. Also see [TDEPS-198](https://clojure.atlassian.net/browse/TDEPS-198).
* [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
    * Namespace state is now preserved over multiple blocks
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * Actively working towards a [new release](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#unreleased), probably next month.
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
    * Add `gzip` and `gunzip` functions (thanks to Lauri Oherd)
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Tried to improve the situation where the downloaded tools jar may be corrupt and causes trouble when calculating the classpath, using a crc32 check. See babashka [issue](https://github.com/babashka/babashka/issues/1576).
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Support `:require-macros`
    * Introduce `eval-string+` which received an optional initial `:ns` key and also returns the last active `:ns` so you can preserve the namespace state over multiple evaluations.
    * Released v0.8.40
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
    * Implement `:out :bytes` to receive output as bytes (thanks Hans Bugge Grathwohl)
    * Make `:dir` option accept `java.nio.file.Path`
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * 1575: fix command line parsing problem with -e + `*command-line-args*`
    * 1576: make downloading/unzipping of deps.clj tools .zip file more robust (see deps.clj)
    * released version 1.3.181
    * 1581: bb `print-deps`: sort dependencies (thanks to Teodor Heggelund)
    * 1579: add `clojure.tools.reader/resolve-symbol` built-in

### Other projects  
These are (some of the) other projects I'm involved with but little to no activity happened in the past month.  
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
* [http-client](https://github.com/babashka/http-client): Babashka's http-client
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
* [babashka.book](https://github.com/babashka/book): Babashka manual
* [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
* [instaparse-bb](https://github.com/babashka/instaparse-bb)
* [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
* [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
* [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
* [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
* [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
* [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
* [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka
* [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
* [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
* [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
* [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
* [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
* [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
* [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
* [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter
* [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI

Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts). _Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)_<br>

---

## Thomas Heller  

### shadow-cljs 
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

**Current shadow-cljs version: 2.24.1**  [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

**Notable Updates**  
- Added full support for `package.json` `"exports"` since its use has become more widespread.<br>

---

## Nikita Prokopov  
Less releases these last two months, more research and reports.  

### [DataScript](https://github.com/tonsky/datascript):  
- Lots of small optimizations for ClojureScript version (who would’ve thought that declare or case could be slow?), with roughly up to 7% perf improvements across the board. 
- Prototyping faster “index sub-slicing” for cases when you e.g. need to find values of the same attribute accross many entities. Seeing up to 30% improvements for this particular case on Roam 3M datoms graph.  

### [Humble UI](https://github.com/HumbleUI/HumbleUI):  
- Prototyping state management solution based on signals  

### [Clojure Sublimed](https://github.com/tonsky/clojure-sublimed):  
- Better indenting of reader conditionals (turned out they use parentheses, but their content is more like a map).  


### Blogging:  
- [Humble Chronicles: Managing State with Signals](https://tonsky.me/blog/humble-signals/). Some research about what to do with components in Humble UI.
- [A Case for ClojureScript 2.0](https://tonsky.me/blog/clojurescript-2/). Some reflection on why ClojureScript feels so different from Clojure and where I think it should be going.  

---

## Peter Stromberg  

As usual the bulk of my open source energy is poured into [Calva](https://calva.io/), a [Clojure](https://clojure.org/)/[ClojureScript](https://clojurescript.org/)/[ClojureCLR](https://github.com/clojure/clojure-clr)/[ClojureDart](https://github.com/Tensegritics/ClojureDart)/[Babashka](https://babashka.org/)/[nbb](https://github.com/babashka/nbb)/[Joyride](https://marketplace.visualstudio.com/items?itemName%253Dbetterthantomorrow.joyride)/etcetera development environment that [takes Interactive Programming seriously](https://www.youtube.com/watch?v%253DL0-yDtVUWMQ).  

I also got to contribute to Clojure-related starter projects and was interviewed for Flexiana's new **Clojure Corner** series: [Clojure Corner with Peter Strömberg](https://flexiana.com/2023/06/clojure-corner-with-pez)  

## Calva  
Over at Twitter someone asked me if I was up to something exciting. Implicitly wondering if Calva was about to get some cool new big thing (I think). My answer was that it is mostly about maintenance currently. However,
**I find Calva maintenance exciting**!  

Maintenance has many aspects. Is the documentation helpful? Are there bugs that create that extra friction making them priority? Is there some feature that could be made more powerful with some change? What about some new feature I feel curious about and that I can finish with one or two late night hacking sessions? The best part: **I get to talk to Calva users** and consider their feedback, questions and suggestions.  
Here's a summary of Calva maintenance May + June 2023. I'm including some of the rationales, to give you a feel for how we reason when maintaining Calva. Wall of text:

* Calva now makes an effort to try **format code with incomplete structure** (missing opening or closing brackets). This is common when formatting a selection, or formatting pasted code. It gets extra important for beginners who do not ”see” the structure as clearly as us with experience from Clojure or some other LISP(s). Not being familiar with the structure also makes you less likely to use Calva's structural editing power. Having the broken code formatted helps in highlighting the structure and what is missing from it. While at it we also got some glitches with format-on-paste feature fixed. It's a bit more complicated than you might think, as [this issue](https://github.com/BetterThanTomorrow/calva/issues/2236) might illustrate. Thanks [Alexander Kouznetsov](https://github.com/mrkam2) for the help in figuring this out!

* Closing/fixing incomplete structure is not new in Calva. It has been used for Calva's commands for evaluating *to the cursor* from arbitrary deep *before the cursor*. Which is very handy for investigating the steps in threads with the REPL, and for including `let` bindings in what is evaluated. It has so far not been available for your [custom REPL evaluation commands](https://calva.io/custom-commands/), which is now fixed with the `$selection-closing-brackets` interpolation variable.

* The [Custom REPL Commands](https://calva.io/custom-commands/) have been available only from Clojure files. This is now changed so that you can fire these commands from any file, as long as the REPL is connected. Good for [Clerk](https://clerk.vision/) Markdown Notebooks, which was the driver for this change.

* The command **Expand Selection** now considers binding pairs, making it easy to select key/value paris in maps or bindings in `let` boxes, etcetera.

* [Stefan van den Oord](https://github.com/svdo) fixed a long standing and mysterious issue we have had with the **deps.edn + shadow-cljs** [REPL connect sequence](https://calva.io/connect-sequences/). It turned out we didn't handle the case of selecting multiple [shadow-cljs](https://github.com/thheller/shadow-cljs) launch builds. (This is correctly handled for the **shadow-cljs** sequence, making our suggestion to use this sequence instead work as a ”solution”.)

* Selecting a `deps.edn` alias that provides `:main-opts` can break Calva's connection of the REPL, because calva provides its own main-opts to start the nREPL server. The warning message Calva used to put up worked to help users navigate around this, but it was also terribly confusing. Thanks to [Dustin Getz](https://github.com/dustingetz) and [Sean Corfield](https://github.com/seancorfield) for helping with finding a better Ux!

* Connecting a [shadow-cljs](https://github.com/thheller/shadow-cljs) REPL is now a more managed process than it used to be. I'm already noticing a drop in user support time for this. Freeing up you users and us Calva maintainers for higher level conversations. 😀

* Traditionally [REPL connect sequences](https://calva.io/connect-sequences/) have been intended per workspace. [Babashka](https://babashka.org/) changes this. To fire up a Babashka REPL often the built in sequence works fine, but if you want to start it in WSL... Now you can define these sequences both globally and locally. Starting Babashka in WSL and connecting VS Code is simplified with a sequence like this one defined in your User VS Code settings file:

    ```json

      "calva.replConnectSequences": [

        {

          "name": "Bashbabka (WSL)",

          "projectType": "custom",

          "customJackInCommandLine": "bash -c 'bb --nrepl-server JACK-IN-NREPL-PORT'",

        },

      ],

  ``````

* Actually, in order to support ^that^ sequence we also needed to add support for fully custom connect sequences. The `"custom"` project type is new since a few weeks.

* [Calva Notebooks](https://calva.io/notebooks/) are a bit of a special take on notebooks in that we insist that any Clojure file can be a notebook. This has been more than a tiny bit hampered by that evaluating a notebook has also evaluated the Rich Comments in there... This is now fixed. You dare evaluate a Calva Notebook if you dare evaluate the file in its regular form.

* There were also numerous small fixes and things happening that I either skip mentioning here, or that I have forgotten about. 😀

* For a few days of this period I was [fire fighting](https://hbr.org/2000/07/stop-fighting-fires). My changes introduced regressions, and my fixes for the regressions introduced regressions. Nothing super major, but there is a promise we want to keep with Calva: **We prioritize regressions that we introduce.** As can be picked up in [The Tao of Calva](https://calva.io/tao/): Moving fast is great. Breaking things is fine, **as long as you fix them fast**.

## Starter projects  

I am a big fan of small starter projects. They can load a lot of experimentation and failure experience into a simple and approachable package that is easy for beginners and non-beginners alike to pick up and use. Hopefully for a quicker and smoother experience than the starter project creator had. Hereby I am encouraging you all to consider this when you have experimented with some tech stack, or tech stack component. It is my experience that I learn better from creating them as well. Both by the activity of structuring the project and writing the README in a way that I can hope is understandable, and from the feedback and contributions I get from others trying to use my project.

Anyway. This period around I created one new starter project and gave some love to an old and still popular one.

* [Dockerized ClojureCLR Starter Project](https://github.com/PEZ/clojure-clr-starter). Celebrating that [ClojureCLR](https://github.com/clojure/clojure-clr) got [a new nREPL server](https://github.com/clojure/clr.tools.nrepl), and confirming that it works well with Calva, the result was a starter project. As I am on a Mac and don't have reasons for having .Net installed, I choose to use Docker Compose to set the project up. This also makes it super quick to go from Zero to a ClojureCLR REPL running and connected to your editor. Special Calva convenience added. Please consider contributing configuration for your favorite Clojure IDE.

* [rn-rf-shadow](https://github.com/PEZ/rn-rf-shadow) is a [ClojureScript](https://clojurescript.org/) + [Reagent](https://reagent-project.github.io/) + [re-frame](https://day8.github.io/re-frame/re-frame/) + [React Native](https://reactnative.dev/) starter project, powered by [shadow-cljs](https://github.com/thheller/shadow-cljs) and [Expo](https://expo.dev/). It has helped a quite a few ClojureScript React Native projects to get started.

  * It is now up-to-date with latest React, latest Reagent, latest everything.

  * The dependency bumps were the result of me adding [React Navigation](https://reactnavigation.org/) to the project. This makes sense for a lot of projects and is easy enough to rip out if your project does not need it. There is now also [a branch](https://github.com/PEZ/rn-rf-shadow/blob/pez/try-countdown-circle-timer/src/main/example/app.cljs#L37) demonstrating how to use React Native libraries, in this case the [react-native-countdown-timer](https://www.npmjs.com/package/react-native-countdown-circle-timer) (no README update for this yet).

## Please follow me  

* I'm [@PEZ](https://github.com/PEZ) on Github.

* I'm [@pappapez](https://twitter.com/pappapez) on Twitter.

## Thanks for sponsoring me! ❤️

* [Clojurists Together](https://www.clojuriststogether.org/): This long term sponsoring is fantastic. It makes wonders for how I can fit open source contributions into my family life.

* [Github Sponsors](https://github.com/sponsors/PEZ#sponsors): Same here, it's long term and it makes my family respect Clojure, Calva, Open Source and that I want to spend time with it.<br>

---


## Tommi Reiman  
Had a busy 2 months and did not have enough focus time to deliver the things I planned on OS. Just started my 9 week summer vacation, will be few weeks off the grid, but then will jump back into OS.

### Malli  
* Continued with the [new effective type system](https://github.com/metosin/malli/issues/264) - WIP
* Working with Schema inheritance (optional, solving real-world problems), WIP  

## Reitit   
* Work on with the upcoming 0.7.0 Version, with 5 new alpha releases. Thanks for all the alpha-testers!
* Managed to fix [a foundational issue](https://github.com/metosin/reitit/issues/422) by [Introducing two-phase Schema compilation](https://github.com/metosin/reitit/pull/626)
* Goal wast to ship the 0.7.0 out on June, but ended up not being happy with the new syntax and started to do [a big refactor](https://github.com/metosin/reitit/pull/628) on it. Almost done.  

# Something else  
Summer.

![summer](https://user-images.githubusercontent.com/567532/250184909-55f636ae-5e3a-4a10-b6fc-38c4749ce2fb.png)

<br>

---

## Sean Corfield  
In my [previous Long-Term Funding update](https://corfield.org/blog/2023/04/30/long-term-funding-2/) I said I would review/overhaul the Libraries pages (both authoring and the directory) and write the `tools.build` cookbook.

The [library authoring guide](https://clojure-doc.org/articles/ecosystem/libraries_authoring/) has been rewritten to use the Clojure CLI, `deps-new`, and `deps-deploy` and was well-received by the community, who provided some useful feedback that I have also incorporated into the guide. The information from the library directory has been integrated into [The Clojure Toolbox](https://www.clojure-toolbox.com/) via a couple of Pull Requests that [added optional tool-tip descriptions](https://github.com/weavejester/clojure-toolbox.com/pull/470) and [libraries that were on `clojure-doc`](https://github.com/weavejester/clojure-toolbox.com/pull/472) but missing from the Toolbox. Thanks to James Reeves for accepting those PRs!

What else did I get done?&lt;!--more-->

### ClojureCLR, HoneySQL  

There's been quite a bit of activity around [ClojureCLR](https://github.com/clojure/clojure-clr) recently, so I've been testing .NET-related things on Windows and on Ubuntu. David Miller submitted a patch to `tools.cli` to add CLR support which I released as [`tools.cli` v1.0.219](https://github.com/clojure/tools.cli/releases/tag/v1.0.219) and I updated HoneySQL to add CLR support:
[`honeysql` v2.4.1033](https://github.com/seancorfield/honeysql/releases/tag/v2.4.1033).

`tools.nrepl` has been ported to ClojureCLR and Peter Strömberg (maintainer of Calva) has created a [ClojureCLR starter project for VS Code/Calva](https://github.com/PEZ/clojure-clr-starter)

which I've also been helping to test on Windows and Ubuntu. HoneySQL saw another release, mostly improving documentation and docstrings, near the end of the this period: [v2.4.1045](https://github.com/seancorfield/honeysql/releases/tag/v2.4.1045). Both releases improved the experience with `:on-conflict` clauses.

## `next.jdbc`  
[`next.jdbc` v1.3.883](https://github.com/seancorfield/next-jdbc/releases/tag/v1.3.883) was also released in this period, also mostly improving documentation and docstrings, and adding an `active-tx?` predicate to expose whether `next.jdbc` thinks you are currently in a `with-transaction` context.

## `clojure-doc.org`  
All of the content from the library directory has been incorporated into The Clojure Toolbox at this point. Every library that was previously listed on `clojure-doc.org` is now listed on the Toolbox and all of the one-line descriptions have been added to the Toolbox as well (which now show up as tooltips when you hover over the library name/link). The Toolbox still has a lot of libraries listed without descriptions so, hopefully, that's something the community can add over time (or help James with automating, using project descriptions from GitHub, perhaps?).

The library authoring guide has been substantially rewritten to use the Clojure CLI, `deps.edn`, and `build.clj`. The old Leiningen-based library authoring guide has been lightly updated and is still available, linked from the new guide.

In addition to the library work mentioned above, I've been working on the [`tools.build` cookbook](https://clojure-doc.org/articles/cookbooks/cli_build_projects/). I shared an early draft to get community feedback and then shared the completed version this week. The whole thing is over 3,000 words now, with a lot of code examples. I've tried to distill everything I've learned about `tools.build` into a single document that covers various scenarios that go beyond what is in the [official `tools.build` guide](https://clojure.org/guides/tools_build).

Some additional community feedback has already been incorporated and more will be incorporated over the next few weeks, I expect.

### What's Next?  
In July/August, I'm hoping to complete a review and update of both the "ecosystem" and "tutorials" section of clojure-doc.org, and then in the two remaining periods, I'll tackle the "cookbooks" and "language" sections.

### On a personal note…  
I mentioned in the previous update that my mother was in hospital and I want to thank everyone who reached out to me with kind words and support. She came home and was doing well for a while but then she had another fall and she's back in hospital as I write this, this time with severe anemia on top of her other issues. She's had a blood transfusion and seems to be doing better but we don't know when she'll be home. It's times like these when I really do feel the five and a half thousand miles between us...
 :tags ["clojure" "clojure-doc.org" "honeysql" "clojure-clr" "jdbc" "open source" "community" "clojurists together"]}<br>

---

## Peter Taoussanis  
**May/Jun 2023 updates** for [Peter Taoussanis](https://www.taoensso.com/)  

### Recent releases include:  
- [Sente](https://github.com/ptaoussanis/sente): `v1.18.0` - see [release notes](https://github.com/ptaoussanis/sente/releases) for details
- [Timbre](https://github.com/ptaoussanis/timbre): `v6.2.1` - see [release notes](https://github.com/ptaoussanis/timbre/releases) for details
- [http-kit](https://github.com/http-kit/http-kit): `v2.7.0` - see [release notes](https://github.com/http-kit/http-kit/releases) for details  

### Upcoming work:  
- [Carmine](https://github.com/ptaoussanis/carmine) `v3.3.0-RC1` will be out in the first week of July. This includes a rewrite of Carmine's message queue system, with major improvements in mq performance, observability, and documentation.
- [Tempel](https://github.com/ptaoussanis/tempel) `v1.0.0-beta1` is still on track for end of August, more info on that closer the time.
- Moving forward, all my libraries will include testing for GraalVM when relevant. Thanks to Michiel Borkent for Babashka, and for his assistance with testing.
As usual, my current open-source plan is available [here](https://www.taoensso.com/clojure/2023).  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](https://github.com/sponsors/ptaoussanis#sponsors) of my open-source work 🙏 <br>

---

## Eric Dallo  
During these 2 months, I mainly focused on improving clojure-lsp, fixing bugs and adding some features mentioned above, especially improving the java support.
Also, I've been working for some months now in a new Intellij plugin called __clojure-lsp-intellij__, which I should finish the alpha and announce soon!   

### [clojure-lsp]([https://clojure-lsp.io/](https://clojure-lsp.io/))   
**Main highlights:**  
- Recently I focused on java interop improvements in both clojure-lsp and clj-kondo, so now we have more java analysis to provide features, we have now hover doc (pic1) and completion for java static members! We still have to enhance clj-kondo and lsp to be able to provide for all java usages besides static ones only, but it's a huge start!
- Keywords completions are now smarter, checking other namespaces and sorting better.
- We have more visual feedbacks when LSP is doing post start tasks in the editor.
- clojure-lsp now understand ClojureDart projects!

![](hover-java-signature.png)

### 2023.05.04-19.38.01
- General
  - Bump clj-kondo to `2023.04.15-20230503.152749-8`  

- Editor  
  - Fix edn tree to consider symbols. #1556

  - Fix edn tree to consider vector root nodes. #1557

  - Fix edn tree to handle invalid edn code and not throw exceptions while typing code.  

- API/CLI  
  - Avoid enabling unecessary analysis features for API/CLI lint, improving memory usage.

  
### 2023.07.01-22.35.41  
- General
  - Update flake.nix to build with babashka. #1373
  - Bump clj-kondo to `2023.06.02-20230630.144012-16`.
  - Improve support for ClojureDart `.cljd` files. #1589
  - Slightly reduce OutOfMemory exceptions that may happen with the JVM version.
  - Support ignore unused-public-vars via `:linters :clojure-lsp/unused-public-var :exclude-when-contains-meta` clj-kondo setting.
  - Fix restructure-keys when map has an `:or`. #1583
  - Bump lsp4clj to `1.8.0`.
  - Add post startup tasks progress feedbacks, like "Generating stubs", "Analyzing JDK source" and "Fetching Clojars".
  - Bump cljfmt to `0.10.6`. #1605  

- Editor
  - New code actions: `Replace ':refer :all' with ':refer [my-refer]'` and `Replace ':refer :all' with alias`. #1575
  - Enhance java support for hover and completion of static class members.
  - Improve `:paths-ignore-regex` to ignore features avoiding impact in huge files that are intented to be excluded.
  - Fixed semantic-tokens for full qualified namespace
  - Add `score` to completion items for a better completion client sorting. #1595
  - Revamp keyword completion. #1599
  - Avoid LSP errors when cleaning a file without namespaces. #1603  

- API/CLI
  - New feature: Find all references via API and CLI. #1572

### [clj-kondo](https://github.com/clj-kondo/clj-kondo/)

[#2106](https://github.com/clj-kondo/clj-kondo/pull/2106): Support `.cljd` files as source files.  

### [jet.el](https://github.com/ericdallo/jet.el)

[#7](https://github.com/ericdallo/jet.el/issues/7): json with ' (single quote) fails to convert to edn.<br>

---


# Q2 2023 Project Updates  

## Donut: Daniel Higganbotham   

(Report 2 Published 25 May, 2023)  
Over the last couple months I've made a lot of changes across the Donut ecosystem to get the framework closer to realizing the vision of providing a foundation for building single-page apps. I've focused on creating a cookie-based auth plugin that provides both backend API endpoints and frontend components.  
Per-repo changes include:  

### [donut-party/endpoint]([https://github.com/donut-party/endpoint](https://github.com/donut-party/endpoint))  
This repo provides components you can add to your system to configure it for serving HTTP API requests.  
* **Added `ServerComponent` donut.system components**  
  This encapsulates a default configuration for running a jetty server

* **Added `HTTPComponentGroup` donut.system component group and `http-plugin` plugin**  
  These make it possible for a developer to add a collection of donut.system components to their system with just a lien of code

* **Improved docstrings**  

### [donut-party/endpoint-test]([https://github.com/donut-party/endpoint-test](https://github.com/donut-party/endpoint-test))  
This repo provides a test harness for applications that use donut.system and donut.endpoint to define API handlers. It handles system setup/teardown and provides helpers to:
* Construct endpoint paths
* Send requests to your app's request handler
* Parse responses back into Clojure data structures

Updates:  
* **Updated plugin definition to use latest syntax from donut.system**
  I'd updated donut.system's plugin syntax to be more readable and more data-oriented, and this required an update to `donut.endpoint.test.harness/test-harness-plugin`. The plugin provides some default configuration for the library's helpers.  

* **Revised interface for request helpers**  
  I significantly rewrote the interface for request handlers to take a single argument, a map, rather than relying on variadic positional arguments. I found the positional arguments too difficult to remember and not as readable.  

* **Added a README**
* **Added support for edn content-type**

### [donut-party/frontend]([https://github.com/donut-party/frontend](https://github.com/donut-party/frontend))  
* **Added support for auth responses**
  The frontend framework can handle API responses structured to convey an auth token to the frontend, and store that token in the re-frame app db.

* **Update XHR handling to add auth header when token present**
  The frontend library has a lot of tools for performing XHR requests. I updated them to include an `Authorization` HTTP header when an auth token is present in the re-frame app db.

  

### [donut-party/donut-cli](https://github.com/donut-party/donut-cli)

* **Updated project generator**
  Updated the template used for new projects, and updated the CLI to use the updated templated.

  

### [donut-party/system]([https://github.com/donut-party/system](https://github.com/donut-party/system))  

* **Added `dependency-pairs` function**  
  This makes it easier to grok depedencies and put them in a graph

* **Added ability to specify repl system**  
  Previously, the tools in `donut.system.repl` required you to use a named-system named `:donut.system/repl`. This change makes it possible to pass in the name of any named system and use that.

  
* **Added namespace for visualizing the dep graph with visjs**  
  Calling `(donut.view.visjs/show your-system)` will open a browser window that uses visjs to show a dependency graph of your system.

* **Added component caching**  
  Sometimes you don't want to constantly stop and restart components. For example, you probably don't want to stop and start a threadpool for every test. This addition lets you cache a component, preserving a component instance across stops and starts. Clearing the cache will make the component respond to the next stop signal.<br>

---

  
## Aleph/Manifold: Matthew Davidson  
(Report 2 Published 30 June 2023)  

### HTTP/2 support  
- Client-side HTTP/2 is now generally available, as of 0.7.0-alpha1
- H2C cleartext is now supported (use with caution)  

### Other Aleph changes  
- *Massive* refactoring of Aleph internals
- Much better documented
- Split up pipelines for multiplex codec
- Added helper Java classes for channel initialization and ALPN negotiation
- Added pr support for Netty classes
