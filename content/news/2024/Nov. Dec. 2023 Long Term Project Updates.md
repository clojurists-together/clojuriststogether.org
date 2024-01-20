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
[Sean Corfield:](#sean-corfield) clojure-doc.org, toolsbuild, deps-new, honey-SQL, expectations       
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











