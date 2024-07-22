---
title: "May & June 2024 Long-Term Project Updates"
date: 2024-07-22T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Peter Taoussanis"  



---
 

A huge thank you to our 2024 long-term developers for their amazing work in May and June. Check out their latest project updates!


[Bozhidar Batsov:](#bozhidar-batsov) CIDER       
[Michiel Borkent:](#michiel-borkent) squint, babashka, neil, cherry, clj-kondo, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs, shadow-grove    
[Kira McLean:](#kira-mclean) Scicloj Libraries. tcutils, Clojure Data Cookbook, and more   
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Datascript, AlleKinos, Clj-reload, and more  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli, jsonista, and more    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, Telemere, and more  


## Bozhidar Batsov   
This period was quite busy and productive for CIDER and friends. The highlights are 3 (!!!) CIDER releases and a couple of nREPL releases:  

- [CIDER 1.14 ("Verona")](https://github.com/clojure-emacs/cider/releases/tag/v1.14.0)  
- [CIDER 1.15 ("Cogne")](https://github.com/clojure-emacs/cider/releases/tag/v1.15.0)  
- [CIDER 1.15.1 ](https://github.com/clojure-emacs/cider/releases/tag/v1.15.1) (small bug-fix release)  
- [nREPL 1.1.2](https://github.com/nrepl/nrepl/releases/tag/v1.1.2) (small bug-fix release)  
- [nREPL 1.2.0](https://github.com/nrepl/nrepl/releases/tag/v1.2.0)  

CIDER 1.14 is our most ambitious release since CIDER 1.8 ("Geneva"), which was released last autumn.  

The single most notable user-visible change of this release is that CIDER is now more robust when evaluating and displaying large values. CIDER will no longer hang when `C-x C-e`ing a big value in a source buffer or stepping over such a value with CIDER debugger.  

I'm guessing that many people will also appreciate the improvements we've made to flex completion (which is finally fully compliant with the Emacs completion API), the inspector and to the cider-cheatsheet functionality which was mostly redesigned.  

nREPL 1.2 restores the ability to interrupt evaluation on JDK 20+ (see https://github.com/nrepl/nrepl/pull/318 for details) and CIDER 1.15 implements support for nREPL 1.2.  

More interesting work is in progress, so I hope I'll have another exciting report for all of you in a couple of months!  <br>

---


## Michiel Borkent  

**Updates**
In this post I'll give updates about open source I worked on during May and June 2024. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors

I'd like to thank all the sponsors and contributors that make this work possible. Without _you_, the below projects would not be as mature or wouldn't exist or be maintained at all.

Current top tier sponsors:  
* [Clojurists Together](https://clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Nubank](https://nubank.com.br/)

### Sponsor info  
If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)

If you're used to sponsoring through some other means which aren't listed above, please get in touch.

On to the projects that I've been working on!

### Updates  

Here are updates about the projects/libraries I've worked on in May and June.  

* [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag
    * A **NEW** library for html generation that is both safe, performant, generates easy to understand code and works the same across CLJ and CLJS.
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting. Released v1.3.191 with the following changes: \

    * Fix [#1688](https://github.com/babashka/babashka/issues/1688): `use-fixtures` should add metadata to `*ns*`
    * Fix [#1692](https://github.com/babashka/babashka/issues/1692): Add support for `ITransientSet` and `org.flatland/ordered-set`
    * Bump org.flatland/ordered to `1.15.12`.
    * Partially Fix [#1695](https://github.com/babashka/babashka/issues/1695): `--repl` arg handling should consume only one arg (itself) ([@bobisageek](https://github.com/bobisageek))
    * Partially Fix [#1695](https://github.com/babashka/babashka/issues/1695): make `*command-line-args*` value available in the REPL ([@bobisageek](https://github.com/bobisageek))
    * Fix [#1686](https://github.com/babashka/babashka/issues/1686): do not fetch dependencies/invoke java for `version`, `help`, and `describe` options ([@bobisageek](https://github.com/bobisageek))
    * [#1696](https://github.com/babashka/babashka/issues/1696): add `clojure.lang.DynamicClassLoader` constructors ([@bobisageek](https://github.com/bobisageek))
    * [#1696](https://github.com/babashka/babashka/issues/1696): add `clojure.core/*source-path*` (points to the same sci var as `*file*`) ([@bobisageek](https://github.com/bobisageek))
    * [#1696](https://github.com/babashka/babashka/issues/1696): add `clojure.main/with-read-known` ([@bobisageek](https://github.com/bobisageek))
    * [#1696](https://github.com/babashka/babashka/issues/1696): add `clojure.core.server/repl-read` ([@bobisageek](https://github.com/bobisageek))
    * [#1696](https://github.com/babashka/babashka/issues/1696): make the `cognitect-labs/transcriptor` library work ([@bobisageek](https://github.com/bobisageek))
    * [#1700](https://github.com/babashka/babashka/issues/1700): catch exceptions from resolving symbolic links during `bb.edn` lookup ([@bobisageek](https://github.com/bobisageek))
    * Support `java.nio.channels.ByteChannel` + several other related interop
    * Bump `nrepl/bencode` to `1.2.0`
    * Bump `babashka/fs`
    * Bump `org.babashka/http-client` to `0.4.18`
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy. \

    * Fix [#2335](https://github.com/clj-kondo/clj-kondo/issues/2335): read causes side effect, thus not an unused value
    * Fix [#2336](https://github.com/clj-kondo/clj-kondo/issues/2336): `do` and `doto` type checking ([@yuhan0](https://github.com/yuhan0))
    * Fix [#2322](https://github.com/clj-kondo/clj-kondo/issues/2322): report locations for more reader errors ([@yuhan0](https://github.com/yuhan0))
    * Imports were copied to `.clj-kondo/imports` but weren't pick up correctly. Thanks [@frenchy64](https://github.com/frenchy64) for reporting the bug.
    * [#2333](https://github.com/clj-kondo/clj-kondo/issues/2333): Add location to invalid literal syntax errors
    * [#2323](https://github.com/clj-kondo/clj-kondo/issues/2323): New linter `:redundant-str-call` which detects unnecessary `str` calls. Off by default.
    * [#2302](https://github.com/clj-kondo/clj-kondo/issues/2302): New linter: `:equals-expected-position` to enforce expected value to be in first (or last) position. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md)
    * [#1035](https://github.com/clj-kondo/clj-kondo/issues/1035): Support SARIF output with `--config {:output {:format :sarif}}`
    * [#2307](https://github.com/clj-kondo/clj-kondo/issues/2307): import configs to intermediate dir
    * [#2309](https://github.com/clj-kondo/clj-kondo/issues/2309): Report unused `for` expression
    * [#2315](https://github.com/clj-kondo/clj-kondo/issues/2315): Fix regression with unused JavaScript namespace
    * [#2304](https://github.com/clj-kondo/clj-kondo/issues/2304): Report unused value in `defn` body
    * [#2227](https://github.com/clj-kondo/clj-kondo/issues/2227): Allow `:flds` to be used in keys destructuring for ClojureDart
    * [#2316](https://github.com/clj-kondo/clj-kondo/issues/2316): Handle ignore hint on protocol method
    * [#2322](https://github.com/clj-kondo/clj-kondo/issues/2322): Add location to warning about invalid unicode character
    * [#2319](https://github.com/clj-kondo/clj-kondo/issues/2319): Support `:discouraged-var` on global JS values, like `js/fetch`
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
    * [#536](https://github.com/squint-cljs/squint/issues/536): HTML is not escaped in dynamic expression
    * [#537](https://github.com/squint-cljs/squint/issues/537): Fix `not`: wrap argument in parens
    * Return interop expression in function body
    * Prefer value from props map over explicit value
    * `#html` improvements, support `:&` for spreading props
    * [#492](https://github.com/squint-cljs/squint/issues/492): defclass static methods and fields
    * [#526](https://github.com/squint-cljs/squint/issues/526): Fix export of class name with dashes
    * [#517](https://github.com/squint-cljs/squint/issues/517): Preserve state over REPL evals
    * [#513](https://github.com/squint-cljs/squint/issues/513): Fix `shuffle` core function random distribution and performances
    * [#517](https://github.com/squint-cljs/squint/issues/517): Fix re-definition of class with `defclass` in REPL
    * [#522](https://github.com/squint-cljs/squint/issues/522): fix `nil` `#html` rendering issue
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects. \
Released version 0.3.65 with the following changes:
    * [#209](https://github.com/babashka/neil/issues/209): add newlines between dependencies
    * [#185](https://github.com/babashka/neil/issues/185): throw on non-existing library
    * Bump `babashka.cli`
    * Fetch latest stable `slipset/deps-deploy`, instead of hard-coding ([@vedang](https://github.com/vedang))
    * Several emacs package improvements ([@agzam](https://github.com/agzam))
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * Fix [#130](https://github.com/squint-cljs/cherry/issues/130): fix predefined `:aliases` for cherry.embed
    * Support `IDeref`, `ISwap`, `IReset` in `deftype`
* [clojure-mode](https://github.com/nextjournal/clojure-mode): Clojure/Script mode for CodeMirror 6.
    * Fix [#54](https://github.com/nextjournal/clojure-mode/issues/54): support slurping from within string literal
* [pottery](https://github.com/brightin/pottery): A clojure library to interact with gettext and PO/POT files
    * Contributed a few improvements to dealing with reader conditionals
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Fix `cljs.pprint/print-table` + `with-out-str`
    * Fixed `cljs.test/testing` macro to display strings correctly on test failure ([@jaidetree](https://github.com/jaidetree))
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs! \

    * Fix [#98](https://github.com/babashka/cli/issues/98): internal options should not interfere with :restrict
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Upgrade/sync with clojure CLI v1.11.3.1463

### Other projects  
There are many other projects I’m involved with but that had little to no activity in the past month. Check out the **Other Projects** section (more details) of [my blog here](https://blog.michielborkent.nl/oss-updates-may-jun-2024.html) to see a full list.

Published 30-06-2024. <br>  

---

## Toby Crawley  

**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/89e33a5a60f10ccb33e59d3a418a224bcb4af0dd...a59efb6128f2ae355df359a57bf06d6b8b0289cc), [`infrastructure`](https://github.com/clojars/infrastructure/compare/190777d4270533d7d4316bb7f2e911cb80ee0dc1...5cb87635b43bf3febe3187940e53ce0ecbf918fd)  

-   [Add wrappers for hashicorp tools to ensure consistent version usage](https://github.com/clojars/infrastructure/commit/e84ca04ec2bb304212751d709c168d781cd101ae)  
-   [Ensure emails are downcased; don&rsquo;t allow future duplicate email usage](https://github.com/clojars/clojars-web/pull/882)
-   [Upgrade to Jetty 11](https://github.com/clojars/clojars-web/pull/883)  

### June 2024  

**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/a59efb6128f2ae355df359a57bf06d6b8b0289cc...02a10234c5b2e78a3b07ab20f9062fb6f24b833b), [`infrastructure`](https://github.com/clojars/infrastructure/compare/5cb87635b43bf3febe3187940e53ce0ecbf918fd...59cec6c2924811f07d5e231923e642f6306fee05)  

-   [Upgrade nippy to address CVE-2024-36124](https://github.com/clojars/clojars-web/commit/4acf752f11adf2a32041f8788852771c5eb909fa)
-   [Upgrade LoadBalancer SSL policy](https://github.com/clojars/infrastructure/commit/02f51fd94057680ddb4e205e73ba2d8d82547b57)
-   [Clean up many typos](https://github.com/clojars/clojars-web/commit/053cb838677011f7cc39ad3a577b01edc279ccf8) (also in [infrastructure](https://github.com/clojars/infrastructure/commit/59cec6c2924811f07d5e231923e642f6306fee05))  <br>


---

## Thomas Heller  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (e.g. Clojurians Slack).  

Current shadow-cljs version: 2.28.10 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  

### Notable Updates  

- Reworked some of the shadow-grove internals and adjusted the shadow-cljs UI accordingly. Fixing some old bugs in the process.
- Updated some Grove Architecture docs: [KV](https://github.com/thheller/shadow-grove/blob/master/doc/kv.md), [Big Picture](https://github.com/thheller/shadow-grove/blob/master/doc/arch/big-picture.md), [defc deep dive](https://github.com/thheller/shadow-grove/blob/master/doc/arch/defc-deep-dive.md) and [impl notes](https://github.com/thheller/shadow-grove/blob/master/doc/arch/defc-impl-notes.md) <br>

---

## Kira McLean  
This is a summary of the open source work I've spent my time on throughout May and June, 2024. There were lots of small bug fixes and reports, driven by work on the Clojure Data Cookbook. This work was also the impetus for my initial release of [`tcutils`](https://github.com/scicloj/tcutils), a library of utility functions for working with tablecloth datasets. I also had the wonderful opportunity to attend PyData London in June and found it really insightful and inspiring. Read on for more details.  

### Sponsors  
This work is made possible by the generous ongoing support of my sponsors. I appreciate all of the support the community has given to my work and would like to give a special thanks to Clojurists Together and Nubank for providing me with lucrative enough grants that I can reduce my client work significantly and afford to spend more time on these projects.  

If you find my work valuable, please share it with others and consider supporting it financially. There are details about how to do that on my [GitHub sponsors page](https://github.com/sponsors/kiramclean). On to the updates!  

### Ecosystem issue reports and bug fixes  
Working on the cookbook these last couple of months turned up a few small issues in ecosystem libraries. The other developers of Clojure's data science tools are such a pleasure to work with, it's so rare and nice to have a distributed team of people capable of getting cool things built asynchronously. Here are some details of a few particular issues that came up:  
- Small problem loading .xls/.xlsx files as datasets if they had a number as a column name: [discussed here](https://clojurians.zulipchat.com/#narrow/stream/236259-tech.2Eml.2Edataset.2Edev/topic/xlsx.20column.20parsing/near/437313810), [reported here](https://github.com/techascent/tech.ml.dataset/issues/408), and graciously [fixed by Chris Nuernberger](https://github.com/techascent/tech.ml.dataset/commit/24c0e646f289210aa95c1ac9998cb2ddd5c9f836).  
- Unexpected behaviour when comparing certain numeric types in `dtype-next`: [discussed here](https://clojurians.zulipchat.com/#narrow/stream/236259-tech.2Eml.2Edataset.2Edev/topic/numeric.20datatypes/near/438617694%5D(https://clojurians.zulipchat.com/%23narrow/stream/236259-tech.2Eml.2Edataset.2Edev/topic/numeric.20datatypes/near/438617694), [reported here](https://github.com/cnuernber/dtype-next/issues/99), and again [fixed by Chris](https://github.com/cnuernber/dtype-next/commit/563fe9c13797feb206391cd951655942e3e6cf0f). This one sadly had some unintended consequences that [generateme found and reported here](https://github.com/cnuernber/dtype-next/issues/103).  
- [Many improvements to Clay](https://github.com/scicloj/clay/blob/b299d060c3edbce789a55fee3efedce42fbd2ab4/CHANGELOG.md) by Daniel Slutsky, especially a couple of ones that make the quarto publications it produces much nicer: [fixing too-wide tables in quarto pages](https://github.com/scicloj/clay/pull/102) and [supporting limiting the number of table rows that get displayed](https://clojurians.zulipchat.com/#narrow/stream/321125-noj-dev/topic/kindly.20options/near/440663980).  
- Some good discussions about how best to incorporate the myriad of dependencies required to use Java machine learning libraries in Clojure libs, including sorting out what to do about [transitive dependencies in our tribuo wrapper](https://github.com/scicloj/scicloj.ml.tribuo/issues/1), led by Carsten Behring.  

### Initial release of tcutils  
In my explorations of other languages' tools for working data I often come across nice utility functions that are super simple but have a big impact on the ergonomics of using the tools. I wanted to start bringing some of these convenience utilities to Clojure, so for now I'm putting them in [`tcutils`](https://github.com/scicloj/tcutils). So far only a handful of helpers are implemented (`lag`, `lead`, `cumsum`, and `clean-column-names`). The goal is to eventually fill out more utilities that save people from having to dig into the documentation of half a dozen different libraries to figure out how to implement things like these. The goal is not to achieve feature parity or to exactly copy similar libraries, like pandas or dplyr, but rather to take inspiration from them and make our tools easier to use for people who are used to these conveniences.  

### Progress on Clojure Data Cookbook  
I spent a lot of time on the Clojure Data Cookbook over these last two months. Notable progress includes:  
- The introductory chapters bear some resemblance now to the final form they'll take.  
- The overall structure of the book is much more clear now.  
- I started the example analysis that will serve as the high-level introductory section of the book.  
- The publishing and deployment process is finally working.  

It's still very much in progress, but in the interest of transparency the work-in-progress version is [available online now](https://github.com/scicloj/clojure-data-cookbook). It will continue to evolve and change as I fill out more and more of the chapters, but there's enough of it available now to hopefully give a sense of the style and tone I'm going for. I also finally have the publishing workflow set up and it's generating a nice-looking Quarto book, thanks to all of Daniel Slutsky's amazing work on Clay and Quarto integration recently.  

### Progress on high-level goals  
The high-level goal of my work in general remains to steward Clojure's data science ecosystem to a state of maturity and flourishing so that data practitioners can use it to get real work done. Toward this end, I set up a [project board](https://github.com/users/kiramclean/projects/4) to track progress toward what I see as the main components of this project.   

Over the last couple of months, beginning with a prototype demoed at my [London Clojurians talk in April](https://www.youtube.com/watch?v=eUFf3-og_-Y), Daniel Slutsky has made tremendous progress on our goal of implementing a grammar of graphics in Clojure in the new [hanamicloth library](https://github.com/scicloj/hanamicloth). The near-term goal is to stabilize the API of this library enough that it can be used to provide a user-friendly way to accomplish all of the simple data visualization tasks that are currently possible with our other tools. The long term goal is to take the lessons we learn from this library and build a JVM-only grammar of graphics library for doing data visualization "right" in Clojure.  

The development and surrounding discussions of hanamicloth have also made me realize it would be useful to write an overview of the current state of dataviz options for Clojure and why we're working on building something new. That's on my list for the coming months, but lower priority than actual development work.  

### Impressions from PyData London  
I got to attend PyData London this year thanks to a client of mine who was sponsoring the conference. I learned a lot and found the talks very interesting. My overall impression is that data science is maturing as a discipline, with more polished methods and robust theory backing up different approaches to data-related problems. With this maturation, though, comes higher expectations for production-ready, professional quality results. Most of the talks focused on high-level concerns like observability, scalability, and long-term stewardship of large open-source projects.  

There are a lot of reasons why Python is just not ideal for building highly available, high-performance systems, and I really believe this is a good time to be building alternative tools for data science. Python is obviously entrenched as the current default language for working with data, but it is difficult and slow to write code that can take full advantage of modern hardware (because of the infamous global interpreter lock, reference counting, slow I/O, among other reasons). And to be fair, the Python community knows this. It's why virtually all of the libraries that do the heavy lifting for data science in Python are actually implemented in C (numpy,  pandas) or Rust (Polars, Pydantic), or are wrappers around C++ (PyTorch, TensorFlow, matplotlib) or Java (PySpark, Pydoop, confluent-kafka) libraries.   

I think this provides a lot of insights into what data practitioners want. It's clear that users _want_ approachable, simple, human-readable interfaces for all of these tools, and that any new tool needs to interoperate with the rest of the ones currently in use. People are also [tired of churn](https://news.ycombinator.com/item?id=40815097) and are craving stability. I think Clojure has a lot to offer in all of these areas and is well placed to become more widely adopted for data science.  

### Ongoing work  
My focus over the next two months will remain on the cookbook. My main goal is to finish the introductory chapter with the housing price analysis and to continue putting together the data import section with instructions and examples for all file formats that can reasonably be supported easily at this time.  

I'll continue to support and contribute to all of the ecosystem libraries I come across in my writings and analysis work in hopes of smoothing out all the rough edges I find.  

Thanks for reading. I always love hearing from people who are interested in any of the things I'm working on. If that's you, don't hesitate to be in touch :)  <br>

---

## Nikita Prokopov  
Hi, I’m Nikitonsky and this is my open-source update for the past two months. Some good work was done on Humble UI (finally!), DataScript and new project — AlleKinos.de.  

New project: [AlleKinos.de](https://allekinos.de/), a no-nonsense movie showtimes site for the entire Germany:  
- A simple view for all movie screenings in Germany, inspired by Bret Victor’s Magic Ink  
- Developed in Clojure, data stored in DataScript, hosted on [application.garden](https://application.garden/)  
- Includes many many small cities (up to 671 now!),  
- And all the cinemas that were reported missing before.  

[HumbleUI](https://github.com/HumbleUI/HumbleUI), Clojure Desktop UI framework:  

- A long-standing `vdom` branch that was dragging from January [is finally merged](https://github.com/HumbleUI/HumbleUI/commit/6be0b49f27994d17d5197bdb2ac5d6e31b976625)!  
- All examples have been ported to the new style  
- Still lots of issues and design ideas to look at, but now I’ll be going through them in the main branch  

[DataScript](https://github.com/tonsky/datascript), immutable database and Datalog query engine for Clojure, ClojureScript, and JS:  

- fixed some OR queries broken in 1.6.4 [468](https://github.com/tonsky/datascript/issues/468), closes [469](https://github.com/tonsky/datascript/issues/469))  
- Remove duplicate code [471](https://github.com/tonsky/datascript/issues/471)  
- Document “partial db” during transaction [366](https://github.com/tonsky/datascript/issues/366)  
- Stable sorting of sequences of various types [470](https://github.com/tonsky/datascript/issues/470)  
- Correctly restore `:max-tx` from storage  
- Fixed tempid/upsert resolution when multiple tempids are added first (closes [472](https://github.com/tonsky/datascript/issues/472)  
- Allow upsert by implicit tuple when only tuple components are specified [473](https://github.com/tonsky/datascript/issues/473)  
- Allow lookup-refs inside tuples used for lookup-refs [452](https://github.com/tonsky/datascript/issues/452)  
- Code cleanup and formatting for the entire codebase  

[clj-reload](https://github.com/tonsky/clj-reload), a smarter way to reload Clojure code:  

- Disabled parallel init/reload via lock [9](https://github.com/tonsky/clj-reload/issues/9)  
- `:only` with regexp only reloads changed + new matching  
- added find-namespaces  
- clj-reload support has been [merged into CIDER 1.14](https://github.com/clojure-emacs/cider/releases/tag/v1.14.0)  
- Now possible to initialize without passing `:dirs` option, will use system classpath in that case  

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed), Clojure support for Sublime Text 4:  

- Fixed Socket REPL not working on Windows [95](https://github.com/tonsky/Clojure-Sublimed/issues/95)  
- Fixed a bug when Clojure Sublimed would not work right after first install [109](https://github.com/tonsky/Clojure-Sublimed/issues/109)  

[Sublime Executor](https://github.com/tonsky/Sublime-Executor), Sublime Text plugin to run any executable from your working dir:  

- Added `executor_show_panel_on_output` option  

### Blogging:  

- [Going to the cinema is a data visualization problem](https://tonsky.me/blog/allekinos/)  
- [Local, First, Forever](https://tonsky.me/blog/crdt-filesync/): what if your CRDT’s syncing engine was Dropbox?  

### Talks:  
- [Clj-reload at Clojure Berlin](https://www.youtube.com/watch?v=PEDTXF6SQeE) (a bit rushed).  
- [Same talk, but re-recorded offline](https://www.youtube.com/watch?v=srvFRG5tdg8), a little more deep and less rushed. I recommend this version.  

Love,  
Niki  <br> 

---

## Tommi Reiman  
Started my 3 month sabbatical in June with a road-trip with the kids, a welcome reset! Now back to home, learning and doing.  
Refreshed my knowledge of the latest [TypeScript](https://typescriptlang.org), [Zod](https://zod.dev/) and [XState](https://xstate.js.org/)
with a goal to pull some of the good things to Clojure (into Malli + a fully Xstate-compatible FSM-library). Also working on a
template-project with monorepo + malli + reitit, using Java21 and Virtual Threads.  

### Library Releases  

### reitit 0.7.1 (active)  

Fixing regression bugs from 0.7.0 + latest features via dependent libraries.
Changelog [here](https://github.com/metosin/reitit/blob/master/CHANGELOG.md#071-2024-06-30).  

### malli 0.16.2 (active)  

Welcome Experimental Simplified Function Schemas!  

```clojure
[:-> :any] ; [:=> :cat :any]
[:-> :int :any] ; [:=> [:cat :int] :any]
[:-> [:cat :int] :any]  ; [:=> [:cat [:cat :int]] :any]
[:-> a b c d :any] ; [:=> [:cat a b c d] :any]

;; guard property
[:-> {:guard (fn [[[arg] ret]] ...)} :string :boolean]
; [:=> [:cat :string] :boolean [:fn (fn [[[arg] ret]] ...)]]
```

Also, small fixes and additions. Changelog [here](https://github.com/metosin/malli/blob/master/CHANGELOG.md#0162-2024-06-30).  

There is a big bunch of WIP work from myself and contributors waiting to be finished.  

### jsonista 0.3.9 (stable)  

`:do-not-fail-on-empty-beans` option + updated dependencies, Changelog [here](https://github.com/metosin/jsonista/blob/master/CHANGELOG.md#039-2023-06-29)  

### ring-http-response 0.9.4 (stable)  

Teapots welcome! Changelog [here](https://github.com/metosin/ring-http-response/blob/master/CHANGELOG.md#094-2962024).  

### spec-tools 0.10.7 (inactive)  

Small fixes and improvements, Changelog [here](https://github.com/metosin/spec-tools/blob/master/CHANGELOG.md#0107-2024-06-29).
If you are a user of spec-tools and want to help, feel free to ping me on Clojurians Slack, happy to take a new contributor here.  

### Something Else  

Old abandoned Soviet-era sanatorium in Latvia.

<img width="1728" alt="baltics" src="https://gist.githubusercontent.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/16c45e9af4d423c758f399db2f4d94c4cadf02d6/baltics.jpg"> <br>

---

## Peter Taoussanis  
A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

### 2024 May - Jun  
Hi folks! 👋  

The last couple months have been light on big-ticket releases. Have been focused on maintenance, support, and groundwork for future releases. Output included:  

### Nippy and Carmine security releases  

If you haven't yet, please do try update to the latest versions of Nippy and/or Carmine when possible:  

- [Nippy v3.4.2](https://github.com/taoensso/nippy/releases/tag/v3.4.2) released 2024-05-26  
- [Carmine v3.4.1](https://github.com/taoensso/carmine/releases/tag/v3.4.1) released 2024-05-30  

These include a fix to address a [security vulnerability](https://github.com/taoensso/nippy/security/advisories/GHSA-vw78-267v-588h) described in more detail in [Nippy's release notes](https://github.com/taoensso/nippy/releases/tag/v3.4.2).  

In short: Carmine uses Nippy for its serialization, and Nippy uses a [Java compression library](https://github.com/airlift/aircompressor) for its compression. Earlier releases of that Java library may be vulnerable when decompressing **malicious data directly crafted by an attacker**. The attack is believed to require arbitrary control of the data provided to Nippy for thawing.  

Relevant posts were made to the Clojure [subreddit](https://www.reddit.com/r/Clojure/comments/1d4q9k0/security_advisory_moderate_please_update_to_nippy/), Clojurians [Slack](https://clojurians.slack.com/archives/C06MAR553/p1717141613851239), and my X account.  

### Telemere  

Work has continued on [Telemere](https://www.taoensso.com/telemere), my new **structured logging and telemetry library** for Clojure/Script.  

There were numerous minor beta releases to address various issues that came up, and to polish sharp edges and documentation, etc.  

Instead of detailing all that here, I'll just point to the current release - [v1.0.0-beta14](https://github.com/taoensso/telemere/releases/tag/v1.0.0-beta14). The latest beta release will always include a summary of all major recent changes.  

I'm aiming to try out RC1 around the end of August, but won't needlessly rush. I'd like the API to be completely stable after v1 final is out, so I'd rather go a bit slower now to get things right.  

Big thanks to early adopters and testers for all the valuable feedback so far! 🙏  

### Carmine  

Work has continued on Carmine v4. It's quite an undertaking, but I've recently updated and merged the first parts of the new v4 core into mainline.  

The current plan is for all the new stuff to live in a parallel `taoensso.carmine-v4` namespace. This'll make it easier for me to roll out the new work in stages, and get feedback from early adopters without negatively impacting existing users.  

There'll be a lot to say on Carmine v4, but that'll come later.  

### Upcoming work  

My current roadmap can always be found [here](https://www.taoensso.com/roadmap), and it's now also possible to [vote](https://www.taoensso.com/roadmap/vote) to help guide my priorities.  

Current objectives for July-August include:  

- Continued efforts on [Telemere](https://www.taoensso.com/telemere).  
- Hopefully release the final stable version of [Tempel](https://www.taoensso.com/tempel) - my new **data security framework** for Clojure. Before the final release I'm planning to investigate support for [MFA](https://en.wikipedia.org/wiki/Multi-factor_authentication), extend the docs re: use with [OpenID](https://en.wikipedia.org/wiki/OpenID), [OWASP](https://en.wikipedia.org/wiki/OWASP), and make a few other last improvements. Originally had this planned for earlier, but rescheduled so that I could prioritise the Nippy security topic.  

Cheers!  
\- [Peter Taoussanis](https://www.taoensso.com)

