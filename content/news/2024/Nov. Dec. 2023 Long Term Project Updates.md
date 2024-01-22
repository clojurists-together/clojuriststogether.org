---
title: "November & December 2023 Long-Term Project Updates"
date: 2024-01-20T08:30:00+08:00
summary: "Cider/REPL, clj-kondo, basbashka, clojars, clojure-lsp, shadow, calva, malli, carmine V4, Humble UI and more ."
author: Kathy Davis
draft: True


---  
<br>  

Thanks to our 2023 long-term developers for their incredible work throughout the year. This is their 6th and final report. 

[Bozhidar Batsov:](#bozhidar-batsov) CIDER/REPL  
[Christophe Grand:](#christophe-grand) ClojureDart et.al.   
[Eric Dallo:](#eric-dallo) Clojure-lsp, intellij   
[Michiel Borkent:](#michiel-borkent) clj-kondo,babashka, squint, neil, CLI,clojure-mode, and more..  
[Nikita Prokopov:](#nikita-prokopov) new projects, Clojure Sublimed, DataScript, Sublime Executor  
[Peter Stromberg:](#peter-stromberg) Calva, JavaScript REPL, Polylith    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, http-kit, Tempel, Telemere   
[Sean Corfield:](#sean-corfield) clojure-doc.org, deps-new, honeySQL, expectations, next.jdbc, clj-Watson, org.clojure/java.data        
[Thomas Heller:](#thomas-heller) Shadow-cljs     
[Toby Crawley:](#toby-crawley) Clojars   
[Tommi Reiman:](#tommi-reiman) Malli, Reitit, Jsonista   



## Bozhidar Batsov  

Happy New Year, everyone!  

November and December were a bit slower than the previous couple of months, but we still made some nice progress. During this period we've continued the trend of refining the big changes introduced in CIDER 1.8.
We've shipped 2 new releases - [CIDER 1.11](https://github.com/clojure-emacs/cider/releases/tag/v1.11.0) and [CIDER 1.12](https://github.com/clojure-emacs/cider/releases/tag/v1.12.0) - both of them feature mostly refinements to the Inspector functionality.  

There's currently a bit of work-in-progress to refine `cider-log-mode` that will likely land in the next feature release. The work to add `clojure-ts-mode` support in CIDER in in progress as well.   

I also wrote [an article on using clojure-lsp alongside CIDER](https://metaredux.com/posts/2023/12/23/cider-clojure-lsp-sitting-on-a-tree.html) and I plan to do a bit more work in that direction (e.g. expand CIDER's documentation). And finally write some of the backlogged CIDER articles I have in my personal TODO list. :-)  <br>

---

## Eric Dallo

During these 2 months I spent a considerable time doing mostly 2 things:  

- Working with [@afucher](https://github.com/afucher) in a new IntelliJ Clojure repl plugin, so people can really use clojure-lsp-intellij plugin with an option to repl support  
- Moving to a new apartment in another city :)  

Even so, I can say I'm proud of the ongoing work on IntelliJ side related to clojure-lsp support, and I hope in a few weeks we should announce the new clojure-repl-intellij plugin and how to use it with clojure-lsp.  

### [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)  

It's still pretty alpha and missing lots of REPL features, but this is how it's looking for now:  

![](./clojure-repl-intellij-demo.png)

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

clojure-lsp-intellij is way more stable, compatible with multiple intellij versions and other plugins like vim-intellij, also the main feature highlight is the new wizard to create new Clojure projects from Intellij itself:  

![](./clojure-lsp-intellij-wizard.png)

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

- General
  - Fix deep-merge of client settings with project settings.
  - Fix `max-line-length` on clean-ns feature not respecting some lines when contains a lot of refers. #1729
  - Bump cljfmt to 0.12.0.
  - Bump clj-kondo to `2023.12.15`.
  
- Editor
  - Fix edn tree visualization for keys with same name in the same level. #1558
  - Make clear when `hover` feature is in a calling and not in a specific symbol. #1281
  - Exclude keys that are already included in the function call from completion suggestions. #1720

- API/CLI
  - Drastically improves performance of `format` task matching `cljfmt`, avoiding analyzing the project. #1723

Happy new year, and thank you all for this amazing journey!  <br>

---

## Michiel Borkent  
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

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)  

If you're used to sponsoring through some other means which isn't listed above, please get in touch. Thank you! On to the projects that I've been working on!  
 
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
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs \
 Released version 0.8.41
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
[ https://blog.michielborkent.nl/oss-updates-nov-2023.html \
 https://blog.michielborkent.nl/oss-updates-dec-2023.html](https://blog.michielborkent.nl/oss-updates-nov-2023.html)

Discuss this post [here \
](https://github.com/borkdude/blog/discussions/categories/posts) Published: 1 and 31 December, 2023 \
 Tagged: clojure oss updates 


<br>

---

## Nikita Prokopov  

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

## Peter Stromberg  

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

Clojure language support updates

* Add Clojure functions `tap>`, `add-tap`, and `remove-tap`
* Conditional readers

Developer tooling

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

Since then, I've been mostly concentrating on [Telemere](https://www.taoensso.com/telemere) - another all-new library focused on providing an idiomatic and flexible Clojure/Script API for structured telemetry. Support is planned for [OpenTelemetry](https://opentelemetry.io/) and others.

Will share more info on this at release.

### Upcoming work

My current [roadmap](https://www.taoensso.com/clojure/roadmap) includes:

- Next [http-kit](https://www.taoensso.com/http-kit) beta mid January
- First [Tempel](https://www.taoensso.com/tempel) beta mid January
- First [Telemere](https://www.taoensso.com/telemere) alpha mid February

### 2023 recap

I'm happy to say that 2023 was a really productive year for my open source work. It was the first time I've had support from [Clojurists Together](https://www.clojuriststogether.org/) - and their backing along with my usual contributors meant that I could put more time + effort into open source than I've been able to do in many years.

In particular, I took the opportunity to target some larger/hairier tasks that would have otherwise been infeasible. Some of that work has already borne fruit, some of it will bear fruit in the coming year.

Some notable results this year:

- 37 public library [releases](https://www.taoensso.com/news#open-source), incl. 1 all-new library and another to come soon.
- Major improvements to my workflow for writing and maintaining documentation.
- Updated and expanded documentation for most of my libraries (a few still TBD).
- Several other cross-project improvements (unit tests, Graal support, cljdoc support, improved Cljs documentation, etc.).
- Laid significant foundations for future upcoming work :-)

\- [Peter Taoussanis](https://www.taoensso.com)  <br>

---

## Sean Corfield  

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

Several cookbooks got minor updates to take advantage of functions in `clojure.core` and `clojure.string` that have been added since Clojure 1.4, when most of the original material on `clojure-doc` was written.  

I also went through all the Java documentation links and updated those to
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

## Tommi Reiman  
Many improvements are in the works, but no releases on libraries. My work spread over the following:  

* working with Malli
  - enchanced development mode, new extension apis & pretty printing everything possible (screenshots below)
  - little progress on the [new effective type system](https://github.com/metosin/malli/issues/264)
  - thinking about schemas and global identity, entities and values. Goal is to allow decomplecting maps/keys/values with using local data / schema properties
  - learning how to program with TypeScript Types
* drafting a Clojure spesification for a 1:1 [x-state](https://xstate.js.org/) compatible FSM implementation([xstate v5 released in december](https://stately.ai/blog/2023-12-01-xstate-v5))
  - why? have used FSMs for 10+ years with Clojure with great results, mostly copy-pasting the project-spesific engine from one project to another
  - we have already many [great FSM libraries](https://clojurians.slack.com/archives/C01C7RJA81M/p1649760505661159) for Clojure, but all differ with design objectives. I'll reach out to the library maintainers, e.g. could we have one new/great tool instead of many small ones? we have already depreceated [tilakone](https://github.com/metosin/tilakone).
* organizing open source & stable libraries in general - getting new maintainters for some of the projects
* reviewing and merging pull requests

## Malli Development Mode

### Coercion

![coercion](https://gist.githubusercontent.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/7e038854006f274cddcea212da566090dc370dc2/malli-coercion.png)

### Schema creation

![schema-creation](https://gist.githubusercontent.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/7e038854006f274cddcea212da566090dc370dc2/malli-schema-creation.png)

# Something else

Looking forward to 2024! Lot's of interesting things in the OS pipeline and thanks to new long-term funding, will continue to work on them. Also, New year starts with crispy -20 (in Celcius).

![tampere](https://gist.githubusercontent.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/7e038854006f274cddcea212da566090dc370dc2/winter.jpg)











