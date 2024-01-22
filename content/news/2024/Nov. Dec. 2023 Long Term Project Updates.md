---
title: "November and December 2023 Project Updates"
date: 2024-01-22T08:30:00+08:00
summary: "Cider/REPL, clj-kondo, basbashka, clojars, clojure-lsp, shadow, calva, malli, carmine V4, Humble UI and more ."
author: Kathy Davis
draft: True


---  
<br>  
There is a lot of work to report on folks! We're closing 2023 with two groups of updates:  

*  the 6th and final reports from our 2023 long-term developers 
* final updates from Q3 2023 projects Jank and Quil 

Thanks to all our 2023 developers for their incredible work throughout the year. We're looking forward to an awesome 2024! 




**2023 Long-Term Developers:**  
[Bozhidar Batsov:](#bozhidar-batsov) CIDER/REPL    
[Eric Dallo:](#eric-dallo) Clojure-lsp, clojure-repl-intellij, clojure-lsp-intellij   
[Michiel Borkent:](#michiel-borkent) clj-kondo,babashka, squint, neil, CLI,clojure-mode, and more..  
[Nikita Prokopov:](#nikita-prokopov) new projects, Clojure Sublimed, DataScript, Sublime Executor  
[Peter Stromberg:](#peter-stromberg) Calva, JavaScript REPL, Polylith    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, http-kit, Tempel, Telemere   
[Sean Corfield:](#sean-corfield) clojure-doc.org, deps-new, honeySQL, expectations, next.jdbc, clj-Watson, org.clojure/java.data        
[Thomas Heller:](#thomas-heller) Shadow-cljs     
[Toby Crawley:](#toby-crawley) Clojars   
[Tommi Reiman:](#tommi-reiman) Malli, Reitit, Jsonista   

**Q3 2023 Updates:**  
[Jeaye Wilkerson:Jank](#jeaye-wilkerson-jank)  
[Charles Comstock and Jack Rusher: Quil](#charles-comstock-and-jack-rusher-quil )  <br>

---


### Final Reports 2023 Long-Term Developers  

### Bozhidar Batsov  

Happy New Year, everyone!  

November and December were a bit slower than the previous couple of months, but we still made some nice progress. During this period we've continued the trend of refining the big changes introduced in CIDER 1.8.
We've shipped 2 new releases - [CIDER 1.11](https://github.com/clojure-emacs/cider/releases/tag/v1.11.0) and [CIDER 1.12](https://github.com/clojure-emacs/cider/releases/tag/v1.12.0) - both of them feature mostly refinements to the Inspector functionality.  

There's currently a bit of work-in-progress to refine `cider-log-mode` that will likely land in the next feature release. The work to add `clojure-ts-mode` support in CIDER in in progress as well.   

I also wrote [an article on using clojure-lsp alongside CIDER](https://metaredux.com/posts/2023/12/23/cider-clojure-lsp-sitting-on-a-tree.html) and I plan to do a bit more work in that direction (e.g. expand CIDER's documentation). And finally write some of the backlogged CIDER articles I have in my personal TODO list. :-)  <br>

---

### Eric Dallo

During these 2 months I spent a considerable time doing mostly 2 things:  

- Working with [@afucher](https://github.com/afucher) in a new IntelliJ Clojure repl plugin, so people can really use clojure-lsp-intellij plugin with an option to repl support  
- Moving to a new apartment in another city :)  

Even so, I can say I'm proud of the ongoing work on IntelliJ side related to clojure-lsp support, and I hope in a few weeks we should announce the new clojure-repl-intellij plugin and how to use it with clojure-lsp.  

### [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)  

It's still pretty alpha and missing lots of REPL features, but this is how it's looking for now:  
![clojure-repl-intellij-demo](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/59fab2a4-d828-4b3d-b888-954e3361f673)


### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

clojure-lsp-intellij is way more stable, compatible with multiple intellij versions and other plugins like vim-intellij, also the main feature highlight is the new wizard to create new Clojure projects from Intellij itself:  
![clojure-lsp-intellij-wizard](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/3cc61bc3-b469-48c5-833c-09e338228692)


#### 0.13.1 - 0.14.1  
- Bump clojure-lsp to `2023.12.29-12.09.27`.
- Add wizzard to create multiple Clojure types of projects directly via Intellij.
- Fix format for non clojure files. #28
- Bump clojure-lsp to 2023.10.30-16.25.41-hotfix2 to fix settings merge during startup.
- Fix exception during hover element.
- Fix exception during find definition specific cases.
- Fix support for older intellij.
- Fix classpath lookup injecting user env on default classpath lookup commands.

### [clojure-lsp](https://clojure-lsp.io/)

The main highlights are performance improvements and fixes, there is a huge improvements in clojure-lsp API for the format task.

#### 2023.12.29-12.09.27
**General**  
  - Fix deep-merge of client settings with project settings.
  - Fix `max-line-length` on clean-ns feature not respecting some lines when contains a lot of refers. #1729
  - Bump cljfmt to 0.12.0.
  - Bump clj-kondo to `2023.12.15`.  

**Editor**  
  - Fix edn tree visualization for keys with same name in the same level. #1558
  - Make clear when `hover` feature is in a calling and not in a specific symbol. #1281
  - Exclude keys that are already included in the function call from completion suggestions. #1720

**API/CLI**  
  - Drastically improves performance of `format` task matching `cljfmt`, avoiding analyzing the project. #1723

Happy new year, and thank you all for this amazing journey!  <br>

---

### Michiel Borkent  
### Happy new year!  
First all, as this is the last day of 2023, I wish you all a happy new year. Hopefully many goods things may happen in the Clojure ecosystem. I'm grateful many of you have sponsored my work in 2023!  

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work possible. Without you, the below projects would not be as mature or wouldn't exist or be maintained at all. Top sponsors:  
* [Clojurists Together](https://www.clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Toyokumo](https://toyokumo.co.jp/)
* [Cognitect](https://www.cognitect.com/)
* [Kepler16](https://kepler16.com/)
* [Pitch](https://github.com/pitch-io)

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways.  Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)  

If you're used to sponsoring through some other means which isn't listed above, please get in touch.  Thank you! On to the projects that I've been working on!  
 
### Advent of Code  

It is Advent of Code time of year again. You can solve puzzles in an online [squint](https://github.com/squint-cljs/squint) or [cherry](https://github.com/squint-cljs/cherry) playground [here](https://squint-cljs.github.io/squint/examples/aoc/index.html).

Change the `/squint/` part of the url to `/cherry/` to switch ClojureScript dialect versions. You can read more about the playground [here](https://blog.michielborkent.nl/squint-advent-of-code.html).

### November 2023 Updates   

* [blog](https://blog.michielborkent.nl/archive.html) I've written two blog posts this month:
    * [Writing a Cloudflare worker with squint and bun](https://blog.michielborkent.nl/squint-cloudflare-bun.html)
    * [Playing Advent of Code with Squint](https://blog.michielborkent.nl/squint-advent-of-code.html).
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler \
 Lots of stuff happened in November with squint! You could say that I've grown a little addicted to improving this project currently, driven by how users use it and also while developing the [playground](https://squint-cljs.github.io/squint/examples/aoc/index.html), a lot of potential improvements emerged. [See blog for more detail]( https://blog.michielborkent.nl/oss-updates-nov-2023.html)
* [scittle-hoplon](https://jsfiddle.net/xbgj6v1q/1/): a custom scittle distribution with Hoplon. I helped developing the SCI configuration for Hoplon.
* [gespensterfelder](https://squint-cljs.github.io/squint/examples/threejs/playground.html): a demo that Jack Rusher wrote using Three.js ported to squint.
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects Version 0.2.63 released which adds mvn search and some bugfixes
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Small bugfix around priority of `:exec-args` and `default`
* [aoc-proxy](https://github.com/borkdude/aoc-proxy): a Cloudflare worker that can be used to fetch Advent of Code puzzle input from the browser (see [Advent of Code playground](https://squint-cljs.github.io/squint/examples/aoc/index.html))
* [squint-macros](https://github.com/squint-cljs/squint-macros): a couple of macros that stand-in for [applied-science/js-interop](https://github.com/applied-science/js-interop) and [promesa](https://github.com/funcool/promesa) to make CLJS projects compatible with squint and/or cherry.
* [clojure-mode](https://github.com/nextjournal/clojure-mode): Clojure/Script mode for CodeMirror 6.
    * Ported the eval-region extension to squint so you can use it straight from JS. This is used in the [squint playground](https://squint-cljs.github.io/squint/?repl=true) when you press Cmd-Enter after an expression.
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
    * A helper macro was improved such that you can define macros that are usable in SCI
    * The re-frame configuration now has support for `re-frame.alpha`. See [playground](https://babashka.org/sci.configs/).
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting. A new release: 1.3.186! [See blog for more detail]( https://blog.michielborkent.nl/oss-updates-nov-2023.html)  
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs \ Released version 0.8.41  [See blog for more detail]( https://blog.michielborkent.nl/oss-updates-nov-2023.html)
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * Released version 0.1.10 which catches up with the latest compiler improvements in squint
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * New `:condition-always-true` and `:underscore-in-namespace` linters + couple of bugfixes. Release expected in December.

### December 2023 Updates  
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy. Released 2023.12.15
    * [#1990](https://github.com/clj-kondo/clj-kondo/issues/1990): Specify `:min-clj-kondo-version` in config.edn and warn when current version is too low ([@snasphysicist](https://github.com/snasphysicist))
    * [#1753](https://github.com/clj-kondo/clj-kondo/issues/1753): New linter `:underscore-in-namespace` ([@cosineblast](https://github.com/cosineblast))
    * [#2207](https://github.com/clj-kondo/clj-kondo/issues/2207): New `:condition-always-true` linter, see [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md)
    * [#2235](https://github.com/clj-kondo/clj-kondo/issues/2235): New `:multiple-async-in-deftest` linter: warn on multiple async blocks in `cljs.test/deftest`, since only the first will run.
    * [#2013](https://github.com/clj-kondo/clj-kondo/issues/2013): Fix NPE and similar errors when linting an import with an illegal token ([@cosineblast](https://github.com/cosineblast))
    * [#2215](https://github.com/clj-kondo/clj-kondo/issues/2215): Passthrough hook should not affect linting
    * [#2232](https://github.com/clj-kondo/clj-kondo/issues/2232): Bump analysis for clojure 1.12 (partitionv, etc)
    * [#2223](https://github.com/clj-kondo/clj-kondo/issues/2223): Do not consider classes created with `deftype` a var that is referred with `:refer :all`
    * [#2236](https://github.com/clj-kondo/clj-kondo/issues/2236): `:line-length` warnings cannot be `:clj-kondo/ignore`d
    * [#2224](https://github.com/clj-kondo/clj-kondo/issues/2224): Give `#'foo/foo` and `(var foo/foo)` the same treatment with respect to private calls
    * [#2239](https://github.com/clj-kondo/clj-kondo/issues/2239): Fix printing of unresolved var when going through `:macroexpand` hook
* [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka v0.3.3 - v0.3.6 released
    * Fix caching in watch mode
    * [#86](https://github.com/borkdude/quickblog/issues/86): group archive page by year
    * [#85](https://github.com/borkdude/quickblog/issues/85): don't render discuss links when `:discuss-link` isn't set
    * [#84](https://github.com/borkdude/quickblog/issues/84): sort tags by post count
    * [#80](https://github.com/borkdude/quickblog/issues/80): Generate an `about.html` when a template exists ([@elken](https://github.com/elken))
    * [#78](https://github.com/borkdude/quickblog/issues/78): Allow configurable :page-suffix to omit `.html` from page links ([@anderseknert](https://github.com/anderseknert))
    * [#76](https://github.com/borkdude/quickblog/pull/76): Remove livejs script tag on render. ([@jmglov](https://github.com/jmglov))
    * [#75](https://github.com/borkdude/quickblog/pull/75): Omit preview posts from index. ([@jmglov](https://github.com/jmglov))
    * Support capitalization of tags
    * [#66](https://github.com/borkdude/quickblog/issues/66): Unambigous ordering of posts, sorting by date (descending), post title, and then file name. ([@UnwarySage](https://github.com/UnwarySage))
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler \
 Lots of stuff happened in December with squint! Too many to mention here, just check the [CHANGELOG.md](https://github.com/squint-cljs/squint/blob/main/CHANGELOG.md)
* [clojure-mode](https://github.com/nextjournal/clojure-mode): Clojure/Script mode for CodeMirror 6.
    * Improved the eval-region extension: when you evaluate `#_(+ 1 2 3)|` the expression `(+ 1 2 3)` is evaluated Test it in the [squint playground](https://squint-cljs.github.io/squint/?repl=true&src=I18oKyAxIDIgMyk%3D).
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure Released 0.5.20:
    * [#119](https://github.com/babashka/fs/issues/119): `fs/delete-tree`: add `:force` flag to delete read-only directories/files. Set the flag to true in `fs/with-temp-dir` ([@jlesquembre](https://github.com/jlesquembre))
    * [#102](https://github.com/babashka/fs/issues/102): add `gzip` and `gunzip` functions
    * [#113](https://github.com/babashka/fs/issues/113): `fs/glob`: enable `:hidden` (when not already set) when `pattern` starts with dot ([@eval](https://github.com/eval)).
    * [#117](https://github.com/babashka/fs/issues/117): fix `fs/match` and `fs/glob` not finding files in root-folder ([@eval](https://github.com/eval)).
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * Released version 0.1.16 which catches up with the latest compiler improvements in squint and also adds the `clojure.set` namespace
* [http-server](https://github.com/babashka/http-server): serve static assets
    * Released 0.1.12 with several new features
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * Working towards a new release, planned for next month.

### Other projects  
These are (some of the) other projects I’m involved with but little to no activity happened in November and December. See final section of each report (“Other Projects”) for details. \
[https://blog.michielborkent.nl/oss-updates-nov-2023.html]  
[https://blog.michielborkent.nl/oss-updates-dec-2023.html]

Discuss this post [here \
](https://github.com/borkdude/blog/discussions/categories/posts) Published: 1 and 31 December, 2023 \ Tagged: clojure oss updates 

<br>

---

### Nikita Prokopov  

Last update of the passing year, so pour yourself a warm drink, cover yourself with a cozy wool blanket and let’s see what are we ending the year with.

[Humble UI](https://github.com/HumbleUI/HumbleUI):

- VDOM prototype implementation
- Blog: [Humble Chronicles: Managing State with VDOM](https://tonsky.me/blog/humble-vdom/)

[DataScript SQL Storage](https://github.com/tonsky/datascript-storage-sql):

- A bit embarassing to miss at first, but we now support connection pooling for SQL connections. To be frank, I became too lazy and soft from using Datomic and forgot how hard “real programmers” have it with SQL databases.

[DataScript](https://github.com/tonsky/datascript):

- Migrated Conn to extend-clj atom (should be invisible for end users though).
- Prevent explosion of redundant tuples during rule solving #456 #457, thx @RutledgePaulV
- Fixed JS version bug caused by Conn rewrite

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed):

- We now support a connection per widnow, so you can have multiple REPLs at the same time as long as they are in separate windows.
- We also now support (non-standard) `.repl-port` files for Socket REPL. Turned out, with multiple REPLs, you can’t just bind them all to port 5555 anymore.
- Fixed status eval not clearing on disconnect
- Fixed error when reporting warnings

[Sublime Executor](https://github.com/tonsky/Sublime-Executor):

- One execution per window, same as with Clojure Sublimed.
- Added executor_clear_output command, with pair nicely e.g. right before reload namespaces or running test suite.
- Ability to redirect output to a view

To sum the whole year up, these are the most notable things that happened:

- [DataScript](https://github.com/tonsky/datascript) got [persistence](https://github.com/tonsky/datascript-storage-sql)! You can now save it on disk, like a real database. Immediately did that with Grumpy Website.
- Made [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed) a world-class tool for Clojure development.
- Did tons of research on [Humble UI](https://github.com/HumbleUI/HumbleUI) with intermediate results all published. It’s now on direct train for getting component model in early 2024.
- Published widely popular [Unicode article](https://tonsky.me/blog/unicode/)
- Released a few Clojure libraries:
  - [datascript-storage-sql](https://github.com/tonsky/datascript-storage-sql),
  - [extend-clj](https://github.com/tonsky/extend-clj),
  - [toml-clj](https://github.com/tonsky/toml-clj),
  - [clj-simple-router](https://github.com/tonsky/clj-simple-router).
- Made a macOS app: [Dark Mode Toggle](https://github.com/tonsky/DarkModeToggle). I use it almost every day myself, perfectly happy with effort/RoI ratio.
- Left my day job and completely switched to open source/donation model.

Thanks Clojurists Together and its sponsors for funding this work. This is a dream job for me and I’m very thankful for a chance to be doing it. 2024 is going to be a open-source-as-a-full-time-job year, so even crazier!

Happy New Year!  <br>  

---

### Peter Stromberg  

This summarizes my last two month of long term funding 2023. I can't find words for how great this has been for me. Clojurists Together ROCKS! ❤️

### Calva

The changes to Calva were mostly about fixing bugs. User support revealed quite a few places where Calva quality could be improved and was improved:

* A strange situation where [another VS Code extension](https://github.com/AnalyticalGraphicsInc/gltf-vscode/issues/264) stopped Calva's nREPL client from working was fixed. Or, worked around rather, it is still unclear what the root cause is.
* Instrumenting the REPL for utilities such as `doc`, and `pprint`, was failing.
  * This also caused plain ClojureScript (i.e. not shadow-cljs) projects to sometimes fail to connect to our nREPL client.
* Calva uses cljfmt for formatting. Mostly for performance reasons we have a separate, cljfmt.edn compliant, indenter for placing the cursor correctly when new lines are created. The indent configuration supports regular expressions, and we had bugs in this support.
* We have been fighting lots of `npm audit` complaints. It's a never ending struggle, but now Calva has a lot fewer dependencies, which improves the situation. (And, right now the report says **0 vulnerabilities detected**.)
* Word selection outside CLojure structural code (i.e. in line comments) was misbehaving.
* It was discovered that Calva's nREPL client was sending non-compliant `eval` messages. Most nREPL servers are resilient to this, but there showed up one that wasn't (**Squint**). Fixing this we could also clean up a lot of code in Calva that compensated for the lacking compliance.
  * This also prevented Calva from working with the [Squint](https://github.com/squint-cljs/squint) nREPL server.
* The Calva API was upgraded with namespace utilities.
* A glitch made the error message return late for failed evaluations in some situations.
* A bug prevented the **Project Type** REPL Jack-in from populating on the first jack-in attempt (for some projects).
* Connecting to a remote nREPL server sometimes didn't work at all.
* Manually breaking up line comments caused suprising, and sometimes rather messy reformatting of the code.
* Connecting the shadow-cljs REPL failed for some projects
* Glitches in including the doc string in hovers.
* Duplicate definitions in hovers (both clojure-lsp and nREPL provides this)
* Namespace alises shadowed local bindings in completions
* Static definition lookup not working from project dependency files


### Joyride

**Clojure language support updates:**

* Add Clojure functions `tap>`, `add-tap`, and `remove-tap`
* Conditional readers

**Developer tooling:**  

* Auto-create configuration and instructions for better clojure-lsp support
  for Joyride scripts
* Fix nREPL server bug causing compliant `eval` op messages to fail (bug
  revealed when Calva started to send compliant `eval` messages)

### Squint

I wanted to help test Squint and used it for solving some Advent of Code problems. Then I started instead to help fix some of the issues I reported as a result. Because Michiel Borkent is the way he is, helping with Squint development was more fun than solving AOC problems so I dropped out of AOC. My most significant contribution to Squint during this “sprint” was adding almost complete support for `clojure.set`.  <br>

---

## Peter Taoussanis  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](/sponsors) of my open source work!


### Recent work

In November I released the first public alpha of [Tempel](https://www.taoensso.com/tempel), a new **data security framework** for Clojure - and my first all-new Clojure library in 7 (!) years.

Its main objective - to make it practical for more Clojure applications to use encryption and cryptographic best practices to protect user data and other sensitive info.

It offers a *particularly* high-level API focused on common tasks including logins, symmetric & asymmetric encryption, end-to-end encryption, signing, and key management.

For lots more info (incl. beginner-oriented documentation), see the [Tempel GitHub page](https://github.com/taoensso/tempel).

Since then, I've been mostly concentrating on [Telemere](https://www.taoensso.com/telemere) - another all-new library focused on providing an idiomatic and flexible Clojure/Script API for structured telemetry. Support is planned for [OpenTelemetry](https://opentelemetry.io/) and others.  Will share more info on this at release.

### Upcoming work

My current [roadmap](https://www.taoensso.com/clojure/roadmap) includes:

- Next [http-kit](https://www.taoensso.com/http-kit) beta mid January
- First [Tempel](https://www.taoensso.com/tempel) beta mid January
- First [Telemere](https://www.taoensso.com/telemere) alpha mid February

### 2023 recap

I'm happy to say that 2023 was a really productive year for my open source work. It was the first time I've had support from [Clojurists Together](https://www.clojuriststogether.org/) - and their backing along with my usual contributors meant that I could put more time + effort into open source than I've been able to do in many years.

In particular, I took the opportunity to target some larger/hairier tasks that would have otherwise been infeasible. Some of that work has already borne fruit, some of it will bear fruit in the coming year.

**Some notable results this year:**

- 37 public library [releases](https://www.taoensso.com/news#open-source), incl. 1 all-new library and another to come soon.
- Major improvements to my workflow for writing and maintaining documentation.
- Updated and expanded documentation for most of my libraries (a few still TBD).
- Several other cross-project improvements (unit tests, Graal support, cljdoc support, improved Cljs documentation, etc.).
- Laid significant foundations for future upcoming work :-)

\- [Peter Taoussanis](https://www.taoensso.com)  <br>

---

### Sean Corfield  

In my [previous Long-Term Funding update](https://corfield.org/blog/2023/10/31/long-term-funding-5/)
I said I would review and update of the
"cookbooks" section and make another pass of "TBD" items in the "language"
section.  

### `clojure-doc.org`  
I reviewed and updated the cookbooks for
[Files and Directories](https://clojure-doc.org/articles/cookbooks/files_and_directories/),
[Mathematics](https://clojure-doc.org/articles/cookbooks/math/),
[Middleware](https://clojure-doc.org/articles/cookbooks/middleware/),
[Parsing XML in Clojure](https://clojure-doc.org/articles/cookbooks/parsing_xml_with_zippers/), and
[Strings](https://clojure-doc.org/articles/cookbooks/strings/),
bringing them all up to Clojure 1.11 (and testing the examples -- and
fixing the broken ones).

For the **Mathematics** cookbook, that meant rewriting the content that previously used Java interop and/or [math.numeric-tower](https://github.com/clojure/math.numeric-tower/) to use the new-in-1.11 `clojure.math` namespace.  

**Several cookbooks got minor updates** to take advantage of functions in `clojure.core` and `clojure.string` that have been added since Clojure 1.4, when most of the original material on `clojure-doc` was written.  

**I also went through all the Java documentation links** and updated those to
point to the Java 17 versions (they were mostly pointing at Java 7 previously!).
These will get updated again once use of JDK 21 has become more widespread.  

I've been slowly working my way through the "TBD" items in the various
**Language** guides, including the **Glossary**, although some of them really need input from community members who have specialist knowledge in those areas. In particular, the
[**Concurrency and Parallelism**](https://clojure-doc.org/articles/language/concurrency_and_parallelism/) and
[**Polymorphism**](https://clojure-doc.org/articles/language/polymorphism/)
guides still have a number of "TBD" items that I don't feel qualified to write!
**Volunteers welcome!**  

Thank you to [@adham-omran](https://github.com/adham-omran) for a PR that
added the [Date and Time cookbook](https://clojure-doc.org/articles/cookbooks/date_and_time/)
and to [@samhedin](https://github.com/samhedin) for a PR that added
a section about [adding Java code to Clojure projects](https://clojure-doc.org/articles/cookbooks/cli_build_projects/#including-java-code-in-a-clojure-project) to the `tools.build` cookbook.  

Finally, I made a logo and a favicon for the site with my very limited
artistic "talents"!  

To wrap up the year of work on `clojure-doc.org`, I consider the Clojurists
Together funding to have been a massive success. The site has been completely
overhauled at this point, bringing it up to date with Clojure 1.11 and
removing all the outdated (and now-duplicated) material that was originally
missing from the official Clojure documentation. In addition, by raising the
profile of `clojure-doc.org` in the community, contributions have increased
with two new cookbooks added via Pull Requests and several other sections of
the site either getting PRs or being updated by me in response to extensive
feedback from the community (mostly on Slack).  

Keeping the site updated now feels like a tractable problem and I'm hoping
to find time in 2024 and beyond to add more content to the site, especially
when Clojure 1.12 is released and there are a lot of enhancements to Java
interop!

### `deps-new`  

[`deps-new`](https://github.com/seancorfield/deps-new/) 0.6.0 was released
with several
documentation updates, a new `:src-dirs` option to make it
easier to use `deps-new` as a library and use templates from the local
file system, and a new `:post-process-fn` to make it possible to modify
the generated project programmatically.

### Expectations  
No new release yet but several documentation updates for
the `clojure.test`-compatible version of
[Expectations](https://github.com/clojure-expectations/clojure-test).

### HoneySQL  
[HoneySQL](https://github.com/seancorfield/honeysql/) 2.5.1103
was released with smarter quoting of entities, smarter
handling of metadata in formatting, and new options to provide more control
over both of those features.

### `next.jdbc`  
[`next.jdbc`](https://github.com/seancorfield/next-jdbc/) 1.3.909
brings improved compatibility with `clojure.java.jdbc` for
`insert-multi!` and adds a `:schema-opts` option to provide more control over
schema conventions for `datafy`/`nav`. There have also been several
documentation updates, in particular around how to use `next.jdbc/plan` and
`next.jdbc.sql/find-by-keys`. The `build.clj` has been updated to use the
`:pom-data` option introduced in `tools.build` 0.9.6, as a better example
for the community.

### `clj-watson`  
[`clj-watson`](https://github.com/clj-holmes/clj-watson/) is a great tool
for checking your dependencies for known security vulnerabilities. It's a
wrapper around
[OWASP Dependency Check](https://owasp.org/www-project-dependency-check/)
and NIST is requiring users of its NVD (National Vulnerability Database)
to switch from using data feed downloads to a new API that requires a free key for
access. The DependencyCheck library that `clj-watson` uses has been updated
to use the new API, but it isn't backward compatible so `clj-watson` needed
changes to use the new version of the library -- and to provide an easier
way for users to specify their own NVD API key.

Although the `clj-watson` maintainer has moved on from Clojure,
they've been receptive to my Pull Requests to update the documentation,
update the library dependencies, add a new, optional properties file that
users can provide to override defaults, as well as a new command line option
to specify that file, if you don't want it on the classpath, and to update
the DependencyCheck library and provide documentation on how to obtain an
NVD API key and how to use it with `clj-watson`.

A v5.0.0 release of `clj-watson` has been made, with all these changes, and
is available as a git dependency. A Pull Request is pending with the README
updates.

### `org.clojure/java.data`  
Finally, the [`java.data`](https://github.com/clojure/java.data/) Contrib
library has a new release, 1.1.103, which removes the dependency on
`org.clojure/tools.logging` -- which in turn means that `next.jdbc` no longer
depends on `tools.logging`, reducing the chance of conflicts for users of
either library.
<br>

---

### Thomas Heller  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (e.g. Clojurians Slack).

Current shadow-cljs version: 2.26.2 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)
<br>  

---

### Toby Crawley  
### November 2023  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/d92cf1eb2f78e13894d37eb8e4b5fc070d9e00e0...efde442180ed43d323a3fff1061a2bd6f7dfd157), [`infrastructure`](https://github.com/clojars/infrastructure/compare/ad8335b312a81567a4c78ef4fe1587741794534c...30bab1d3eefea9963117496c9aa420680ee3efc2)  

I [rewrote the permissions system](https://github.com/clojars/clojars-web/pull/877) this month to support [project-level scoping](https://github.com/clojars/clojars-web/issues/709),
allowing delegation of deploy rights to a user for a subset of projects under a group. This was released in December.  

I also made [improvements to the AMI release process](https://github.com/clojars/infrastructure/commit/30bab1d3eefea9963117496c9aa420680ee3efc2).  

### December 2023  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/efde442180ed43d323a3fff1061a2bd6f7dfd157...a24421654d39e01f8fadeaa7b7b878a3217ff581), [`infrastructure`](https://github.com/clojars/infrastructure/compare/30bab1d3eefea9963117496c9aa420680ee3efc2...f529f091d3b003d8a7d251c6afc6569e4fb607b4)

This month I released the [permissions system rewrite](https://github.com/clojars/clojars-web/pull/877), updated the release logic
to [require a license in the POM for all releases](https://github.com/clojars/clojars-web/pull/875), and made a few other minor improvements:  

-   [Use long polling with SQS](https://github.com/clojars/clojars-web/commit/8d5b8e2c4b93771a34ecb1b14e5d5cbf62d2491e)  
-   [Throw on SQS receive-loop exception to force prcess to exit](https://github.com/clojars/clojars-web/commit/b60afa3cf8c181fe511b71a1ade27bd04c46110f)  
-   [Use mock mailer in development](https://github.com/clojars/clojars-web/commit/785266dbcbc0ea3fea4ccf55a8992f2ed62183f4)  
-   [Update logback to address CVE-2023-6378](https://github.com/clojars/clojars-web/commit/d25bdccbfe32ad23b6b60906b596ed1b5d63cbad)  

On the infrastructure front, I added [disk space and SQS queue delay alarms](https://github.com/clojars/infrastructure/compare/30bab1d3eefea9963117496c9aa420680ee3efc2...57142fd9726c1f6ba42abedfe696632893c58621) so we
can be better informed of when things go wrong.  <br>  

---



### Tommi Reiman  
Many improvements are in the works, but no releases on libraries. My work spread over the following:  

**Working with Malli:**
  - enchanced development mode, new extension apis & pretty printing everything possible (screenshots below)
  - little progress on the [new effective type system](https://github.com/metosin/malli/issues/264)
  - thinking about schemas and global identity, entities and values. Goal is to allow decomplecting maps/keys/values with using local data / schema properties
  - learning how to program with TypeScript Types  

**Drafting a Clojure specification for a 1:1 [x-state](https://xstate.js.org/) compatible FSM implementation([xstate v5 released in december](https://stately.ai/blog/2023-12-01-xstate-v5))**
  - Why? have used FSMs for 10+ years with Clojure with great results, mostly copy-pasting the project-spesific engine from one project to another
  - We have already many [great FSM libraries](https://clojurians.slack.com/archives/C01C7RJA81M/p1649760505661159) for Clojure, but all differ with design objectives. I'll reach out to the library maintainers, e.g. could we have one new/great tool instead of many small ones? we have already depreceated [tilakone](https://github.com/metosin/tilakone).
* organizing open source & stable libraries in general - getting new maintainters for some of the projects
* reviewing and merging pull requests

### Malli Development Mode

#### Coercion

![coercion](https://gist.githubusercontent.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/7e038854006f274cddcea212da566090dc370dc2/malli-coercion.png)

#### Schema creation

![schema-creation](https://gist.githubusercontent.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/7e038854006f274cddcea212da566090dc370dc2/malli-schema-creation.png)

### Something else

Looking forward to 2024! Lot's of interesting things in the OS pipeline and thanks to new long-term funding, will continue to work on them. Also, New year starts with crispy -20 (in Celcius).

![tampere](https://gist.githubusercontent.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/7e038854006f274cddcea212da566090dc370dc2/winter.jpg)
<br>

---

## Q3 2023 Project Reports

### Charles Comstock and Jack Rusher: Quil  
We had hoped to have a new release by the time we submitted this
update, but we -- by which I mean Charles -- are down in the weeds
trying to get all of the test infrastructure to work for every major
platform in Github CI. The good news:

* we have managed to migrate all of the testing away `lein` to `deps.edn`
* tests all work properly from cider now
* upgraded the Clojure test runner to Kaocha (thanks, plexus!)
* moved browser testing for CLJS to Figwheel
* Fixed some bugs around color processing (thanks, plexus!)
* Foreign deps are better handled
* we should have a new release either by the end of December or early
  in January 2024

Our plan is to continue the work a bit more in the new year to get
everything clean and maintainable, then train some other devs in the
community on the codebase.

Thanks for much for your help, Clojurists Together!  
Published 27 December 2023 <br>  

---

### Jeaye Wilkerson: Jank  
I've been quiet for the past couple of months, finishing up this work on jank's
module loading, class path handling, aliasing, and var referring. Along the way,
I ran into some very interesting bugs and we're in for a treat of technical
detail in this holiday edition of jank development updates! A warm shout out to
my [Github sponsors](https://github.com/sponsors/jeaye)
and [Clojurists Together](https://www.clojuriststogether.org/) for sponsoring this work.

### Module loading progress
Ok, first and foremost, where is jank now with regard to module loading? I'm
very pleased to say that everything I wanted to tackle this quarter has been
finished and even more on top of that. There's a PR up for the full changes
[here](https://github.com/jank-lang/jank/pull/49).

Let's break this down by section.

### Class paths
jank traverses a user-defined class path, which supports directories and JAR
files, and can use that to find modules when you use `require` and friends. This
is specifically designed to be compatible with the JVM, so once we hook in
Leiningen or Clojure CLI, your existing dependency management should work just
fine.

### Necessary core functions
The following functions have all been implemented, which were required for
module loading:

* `require`
* `alias`
* `use`
* `refer`
* `load`

These take into account modules that are already loaded, flags for things like
reloading, excluding, etc. For most use cases, they're at functional parity with
Clojure on the happy path. Error handling will improve once I have some better
mechanisms for it.

Still, that's not a very big list of functions, I know. How about this one?

* `compile`
* `create-ns`
* `find-ns`
* `remove-ns`
* `the-ns`
* `ns-name`
* `ns-map`
* `ns-publics`
* `var?`
* `var-get`
* `keys` (note - not using a custom seq yet)
* `vals` (note - not using a custom seq yet)
* `name`
* `namespace`
* `subs`
* `gensym`
* `concat`
* `contains?`
* `find`
* `select-keys`
* `map` (note - not lazy yet, no transducers)
* `mapv` (note - not lazy yet, no transducers)
* `mapcat` (note - not lazy yet, no transducers)
* `filter` (note - not lazy yet, no transducers)
* `complement`
* `remove`
* `set?`
* `set`
* `vector`
* `doseq` (note - not supporting fancy `for` features yet)
* `list*`
* `apply`
* `some`
* `not-any?`
* `not=`
* `symbol`
* `var?`
* `cond`
* `and`
* `or`
* `ns`

All of these were needed by some of the above necessary functions, so I
implemented them as much as possible. Most of them have complete functional
parity with Clojure, but a few have interim implementations, especially since
jank doesn't yet have have an equivalent object type to Clojure JVM's `LazySeq`.
Still, jank feels, and looks, more and more like a proper Clojure every day.

### (Bonus) Initial AOT compilation
You may have noticed, in that list, that `compile` has been implemented. This is
an initial step toward AOT compilation and it compiles jank files into C++ files
on the class path. Those can then be loaded in lieu of the jank files for a
performance win. I also added a CMake job to jank's build system to build the
jank Clojure libs along with the compiler, so we can always have those
pre-compiled and also always know they actually compile.

I'm currently working with the Cling developers to get support added to Cling
for jank to pre-compile these C++ files into a closer equivalent to JVM class files.
In my local testing, the startup time improvements by doing this were 10x. I'll
have more info on this once the work picks up.

### (Bonus) CLI argument parsing
In order to support things like user-defined class paths, I've added a proper
CLI arg parser to jank. You can see the current options in the help output here:

```bash
❯ ./build/jank -h
jank compiler
Usage: ./build/jank [OPTIONS] SUBCOMMAND

Options:
  -h,--help                   Print this help message and exit
  --class-path TEXT           A : separated list of directories, JAR files, and ZIP files to search for modules
  --output-dir TEXT           The base directory where compiled modules are written
  --profile                   Enable compiler and runtime profiling
  --profile-output TEXT       The file to write profile entries (will be overwritten)
  --gc-incremental            Enable incremental GC collection
  -O,--optimization INT:INT in [0 - 3]
                              The optimization level to use

Subcommands:
  run                         Load and run a file
  compile                     Compile a file and its dependencies
  repl                        Start up a terminal REPL and optional server
```

Each subcommand has its own help output, too. Speaking of subcommands, however,
jank now has a `repl` subcommand which spins up a terminal REPL client with
readline enabled for (single session) history and improved editing. This has
been very handy for me as I'm testing out new things and was something that just
came naturally after implementing the CLI argument parsing.

```clojure
❯ ./build/jank repl
> (ns foo.bar)
nil
> *ns*
foo.bar
> (def wow "WOW!")
#'foo.bar/wow
> (def nice "NICE!")
#'foo.bar/nice
> (ns main)
nil
> *ns*
main
> (refer 'foo.bar :only '[wow])
nil
> wow
WOW!
> (alias 'fb 'foo.bar)
nil
> fb/nice
NICE!
> (ns omg.wow (:use [foo.bar :exclude [wow]]))
nil
> *ns*
omg.wow
> nice
NICE!
> (native/raw "*((char*)0) = 0;")
Segmentation fault (core dumped)
```

### (Bonus) Maps, sets, keywords as functions
As part of implementing all of the new core functions this quarter, I also
tackled these particular objects which behave as functions. Fortunately, because
of the new object model design, these objects can have this behavior without the
need for dynamic dispatch!

### There will be bugs
jank is still pre-alpha software. I have an ever growing test suite, but no
battle testing yet. As I develop more functionality, I find more issues and
introduce more yet. That will remain the case until development can settle down
and stable APIs can be decided. jank still isn't ready to compile most Clojure
programs, since it lacks support for some basic features like destructuring,
lazy sequences, and even doc strings. While we're talking about bugs, though,
and since I've shown everything else I've built this quarter, let me tell you
about such an interesting bug I found and how I fixed it.

## Variadic argument matching bug
I fixed a few interesting bugs in the past couple of months, but this one was
the most intriguing by far. So, the problem showed up in this case:

```clojure
(defn ambiguous
  ([a]
   :fixed)
  ([a & args]
   :variadic))

(ambiguous :a) ; => should be :fixed
```

What jank was trying to do was call the variadic arity, with an empty seq for
`args`, rather than to call the fixed arity. This is because both of them
require one fixed argument first and the information I was storing for each
function object was the required fixed args prior to variadic arg packing. 

The equivalent function in Clojure JVM is `RestFn.getRequiredArity`, which
returns the required fixed position arguments prior to the packed args. However,
where Clojure JVM differs from jank is that Clojure uses dynamic dispatch to
solve this ambiguity whereas jank does its own fixed vs variadic overload
matching, for performance reasons.

To actually solve this problem, we need to know three things:

1. Is the function variadic?
2. Is there an ambiguous fixed overload?
3. How many fixed arguments are required before the packed args?


We cannot perform the correct call without *all* of this information.
Also, function calls in a functional programming language like Clojure are on
the hottest of hot code paths, so I can't exactly add two more virtual functions
to jank's `callable` interface to get this data. In truth, even keeping one
function but putting all of this data in a struct proved too much of an impact
on the performance. Thus, we need to encode the data more compactly.

jank now packs all of this into a single byte. Questions 1 and 2 each get a high bit
and question 3 gets the 6 remaining bits (of which it uses 4) to store the fixed
arg count. So, this byte for our `ambiguous` function above would look like
this:

```cpp
1  1  0  0  0  0  0  1
^  ^  ^---------------
|  |  |
|  |  /* How many fixed arguments are required before the packed args? */
|  /* Is there an ambiguous overload? */
/* Is the function variadic? */
```

From there, when we use it, we disable the bit for question 2 and we
`switch` on the rest. This allows us to do a `O(1)` jump on the combination of
whether it's variadic and the required fixed args. Finally, we only need the
question 2 bit to disambiguate one branch of each switch, which is the branch
equal to however many arguments we received.

```cpp
object_ptr dynamic_call(object_ptr const source, object_ptr const a1)
{
  return visit_object
  (
    [=](auto const typed_source) -> object_ptr
    {
      using T = typename decltype(typed_source)::value_type;

      if constexpr(function_like<T> || std::is_base_of_v<callable, T>)
      {
        /* This is the whole byte, answering all three questions. */
        auto const arity_flags(typed_source->get_arity_flags());
        /* We strip out the bit for ambiguous checking and switch on it. */
        auto const mask(callable::extract_variadic_arity_mask(arity_flags));

        /* We're matching on variadic + required arg position. */
        switch(mask)
        {
          case callable::mask_variadic_arity(0):
            return typed_source->call(make_box<obj::native_array_sequence>(a1));
          case callable::mask_variadic_arity(1):
            /* Only in the case where the arg count == the required arity do we
               check the extra bit in the flags. */
            if(!callable::is_variadic_ambiguous(arity_flags))
            { return typed_source->call(a1, obj::nil::nil_const()); }
            /* We're falling through! */
          default:
            /* The default case is not variadic. */
            return typed_source->call(a1);
        }
      }
      else
      { /* ... redacted error handling ... */ }
    },
    source
  );
}
```

The special case, which needs to check the ambiguous flag, incurs a performance
cost, due to the if. Every other case is unaffected. This was a challenge to
wrap my head around at first, but after I wrote out all the things I need to
know, as well as a test suite for each of the cases, I could work toward a
solution which addressed everything.

## What's next?
Firstly, dynamic vars. Once those are implemented, I'll need to go through all
of the different parts of the compiler and runtime to start filling in vars.
This will allow everything from improved error messages by tracking
file/line/function to cyclical dependency checks on module loading.

Also, in order for jank to operate alongside other Clojure dialects, we'll need
to support reader conditionals on the `:jank` key. Currently, jank doesn't
support any reader macros, so getting that system going will open up the door to
things like `#()` and `#{}` being supported.

Finally, I'll be improving the interop interpolation syntax to be consistent with
ClojureScript, adding meta hint support, and more. Stay tuned!  
Published 27 December 2023 <br> 












