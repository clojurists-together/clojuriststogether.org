---
title: "January & February 2024 Long-Term Project Updates"
date: 2024-03-11T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Batsov, Borkent, Crawley, Heller, McLean, Nikitonsky, Reiman, Taoussanis"


---

We're excited to post the first reports of the year from our 2024 long-term developers. There is a lot of great work to catch-up on, so dive in!  

[Bozhidar Batsov:](#bozhidar-batsov) CIDER, Clojure-mode, clojure ts-mode     
[Michiel Borkent:](#michiel-borkent) clj-kondo, babashka, SCI, squint, nbb, CLI, and more   
[Toby Crawley:](#toby-crawley) clojars    
[Thomas Heller:](#thomas-heller) shadow-cljs  
[Kira McLean:](#kira-mclean) Scicloj Libraries  
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Clj-reload, Datascript, and more  
[Tommi Reiman:](#tommi-reiman) Malli   
[Peter Taoussanis:](#peter-taoussanis) Telemere, Tempel, and more  

## Bozhidar Batsov  
Another busy couple of months with quite a few achievements, both big and small. This time around I even managed to write a few articles about the bigger achievements! Here are the highlights for this period:

- CIDER now features preliminary support for `clojure-ts-mode` (see <https://metaredux.com/posts/2024/02/19/cider-preliminary-support-for-clojure-ts-mode.html> for more details)  
- `clojure-mode` has full support for "tonsky"/fixed indentation (see <https://metaredux.com/posts/2024/02/19/configuring-fixed-tonsky-indentation-in-clojure-mode.html>)  
- nREPL 1.1.1 is out with improved code completion functionality (see <https://metaredux.com/posts/2024/02/20/nrepl-1-1-1-improved-completion-with-compliment-lite.html>)  
- `clojure-ts-mode` 0.2.1 and 0.2.2 were released (see <https://github.com/clojure-emacs/clojure-ts-mode/releases/tag/v0.2.1>)  
- More improvements are in the pipeline on `clojure-ts-mode`'s front  
- A new stable CIDER release is likely around the corner  

On top of this I wrote [an article](https://metaredux.com/posts/2024/02/15/cider-community-impact.html) about the positive impact that community-contributed funding
has had on CIDER and friends. Thanks to everyone for their continued support!  

P.S. Some of you might be interested to hear that in this period [I've also become of Flycheck's maintainers](https://metaredux.com/posts/2024/02/10/the-state-of-flycheck-alive-and-kicking.html). I've spent quite a bit of time working on Flycheck, and I see it very connected to the rest of my projects (e.g. there's `flycheck-elgot` and `flycheck-clj-kondo` which many Clojure programmers user)  <br>

---

## Michiel Borkent  

In this post I'll give updates about open source I worked on during January and February 2024.
To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).


### Sponsors

I'd like to thank all the sponsors and contributors that make this work possible. Like you can read on [Bozhidar Batsov](https://metaredux.com/posts/2024/02/15/cider-community-impact.html)'s blog these aren't the easiest times for Open Source sponsored software. I have no reason to complain, but I did see a similar drop in sponsoring in the last year. I'm thankful for those who sponsored my projects in the past and even more for those who keep doing so! Without _you_, the below projects would not be as mature or wouldn't exist or be maintained at all.  

Current top tier sponsors:  
* [Clojurists Together](https://clojuriststogether.org/)  
* [Roam Research](https://roamresearch.com/)  
* [Nextjournal](https://nextjournal.com/)  
* [Cognitect](https://www.cognitect.com/)  

Additional sponsor info: If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
[Clojurists Together](https://clojuriststogether.org/)  
[Github Sponsors](https://github.com/sponsors/borkdude)  
The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective  
[Ko-fi](https://ko-fi.com/borkdude)  
[Patreon](https://www.patreon.com/borkdude)  
If you're used to sponsoring through some other means which isn't listed above, please get in touch.  On to the projects that I've been working on!  


### Updates

Here are updates about the projects/libraries I've worked on last two months.  

* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy. Released 2024.02.12
    * [#2276](https://github.com/clj-kondo/clj-kondo/issues/2276): New Clojure 1.12 array notation (`String*`) may occur outside of metadata
    * [#2278](https://github.com/clj-kondo/clj-kondo/issues/2278): `bigint` in CLJS is a known symbol in `extend-type`
    * [#2288](https://github.com/clj-kondo/clj-kondo/issues/2288): fix static method analysis and suppressing `:java-static-field-call` locally
    * [#2293](https://github.com/clj-kondo/clj-kondo/issues/2293): fix false positive static field call for `(Thread/interrupted)`
    * [#2093](https://github.com/clj-kondo/clj-kondo/issues/2093): publish multi-arch Docker images (including linux aarch64)
    * [#2274](https://github.com/clj-kondo/clj-kondo/issues/2274): Support clojure 1.12 new type hint notations
    * [#2260](https://github.com/clj-kondo/clj-kondo/issues/2260): calling static _field_ as function should warn, e.g. `(System/err)`
    * [#1917](https://github.com/clj-kondo/clj-kondo/issues/1917): detect string being called as function
    * [#1923](https://github.com/clj-kondo/clj-kondo/issues/1923): Lint invalid fn name
    * [#2256](https://github.com/clj-kondo/clj-kondo/issues/2256): enable `assert` in hooks
    * [#2253](https://github.com/clj-kondo/clj-kondo/issues/2253): add support for `datomic-type-extensions` to datalog syntax checking
    * [#2255](https://github.com/clj-kondo/clj-kondo/issues/2255): support `:exclude-files` in combination with linting from stdin + provided `--filename` argument
    * [#2246](https://github.com/clj-kondo/clj-kondo/issues/2246): preserve metadata on symbol when going through `:macroexpand` hook
    * [#2254](https://github.com/clj-kondo/clj-kondo/issues/2254): lint files in absence of config dir
    * [#2251](https://github.com/clj-kondo/clj-kondo/issues/2251): support suppressing `:unused-value` using `:config-in-call`
    * [#2266](https://github.com/clj-kondo/clj-kondo/issues/2266): suppress `:not-a-function` linter in reader tag
    * [#2259](https://github.com/clj-kondo/clj-kondo/issues/2259): `ns-map` unmaps var defined prior in namespace
    * [#2272](https://github.com/clj-kondo/clj-kondo/issues/2272): Report var usage in `if`/`when` condition as always truthy, e.g. `(when #'some-var 1)`
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
    * [#472](https://github.com/squint-cljs/squint/issues/472): Use consistent alias
    * [#474](https://github.com/squint-cljs/squint/issues/474): fix JSX fragment
    * [#475](https://github.com/squint-cljs/squint/issues/475): don't crash watcher on deleting file
    * Add `simple-benchmark`
    * [#468](https://github.com/squint-cljs/squint/issues/468): Keywords in JSX should render with hyphens
    * [#466](https://github.com/squint-cljs/squint/issues/466): Fix `doseq` expression with `set!` in function return position
    * [#462](https://github.com/squint-cljs/squint/issues/462): Add `"exports"` field to `package.json`
    * [#460](https://github.com/squint-cljs/squint/issues/460): escape `&lt;` and `>` in JSX strings
    * [#458](https://github.com/squint-cljs/squint/issues/458): don't emit `null` in statement position
    * [#455](https://github.com/squint-cljs/squint/issues/455): don't export non-public vars
    * Fix infix operator in return position
    * Allow playground to use JSX in non-REPL mode
    * Add transducer arity to all existing core functions
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting. Two releases in the past two months with the following changes:
    * [#1660](https://github.com/babashka/babashka/issues/1660): add `:deps-root` as part of hash to avoid caching issue with `deps.clj`
    * [#1632](https://github.com/babashka/babashka/issues/1632): fix `(.readPassword (System/console))` by upgrading GraalVM to `21.0.2`
    * [#1661](https://github.com/babashka/babashka/issues/1661): follow symlink when reading adjacent bb.edn
    * [#1665](https://github.com/babashka/babashka/issues/1665): `read-string` should use non-indexing reader for compatibilty with Clojure
    * Bump edamame to 1.4.24
    * Bump http-client to 0.4.16
    * Bump babashka.cli to 0.8.57
    * Uberjar task: support reader conditional in .cljc file
    * Support reader conditional in .cljc file when creating uberjar
    * Add more `javax.net.ssl` classes
    * [#1675](https://github.com/babashka/babashka/issues/1675): add `hash-unordered-coll`
    * [#1658](https://github.com/babashka/babashka/issues/1658): fix command line parsing for scripts that parse `--version` or `version` etc
    * Add `clojure.reflect/reflect`
    * Add `java.util.ScheduledFuture`, `java.time.temporal.WeekFields`
    * Support `Runnable` to be used without import
    * Allow `catch` to be used as var name
    * [#1646](https://github.com/babashka/babashka/issues/1646): command-line-args are dropped when file exists with same name
    * [#1645](https://github.com/babashka/babashka/issues/1645): Support for `clojure.lang.LongRange`
    * [#1652](https://github.com/babashka/babashka/issues/1652): allow `bb.edn` to be empty
    * [#1586](https://github.com/babashka/babashka/issues/1586): warn when config file doesn't exist and `--debug` is enabled
    * [#1410](https://github.com/babashka/babashka/issues/1410): better error message when exec fn doesn't exist
    * Bump `babashka.cli` to `0.8.55` which contains subcommand improvements
    * Bump `deps.clj` to `1.11.1.1435`
    * Bump `babashka.fs` to `0.5.20`
    * Compatibility with `plumbing.core`
    * Compatibility with `shadow.css` by improving `tools.reader` compatibility
    * [#1647](https://github.com/babashka/babashka/issues/1647): Allow capturing env vars at build time (only relevant for building bb)
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
    * [#123](https://github.com/babashka/process/issues/123): `exec` now converts `:env` and `:extra-env` keywords ([@lread](https://github.com/lread))
    * [#140](https://github.com/babashka/process/issues/140): accept `java.nio.file.Path` as `:dir` argument
    * [#148](https://github.com/babashka/process/issues/148): accept `Path` in `:out`, `:err` and `:in`
    * Support `:out :bytes` ([@hansbugge](https://github.com/hansbugge))
* [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter
    * Released version 0.1.6 which fixes `:key-fn` + `read` behavior for cheshire
* [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
    * Upgraded the underlying tools.build version to the latest version used in tools.build (the very latest wasn't compatible with tools.build!)
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
    * Support new `^[String]` metadata notation which desugars into `^{:param-tags [String]}`
    * Add `:map` and `:set` options to coerce map/set literals into customizable data structures, for example, an ordered collections to preserve key order.
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Add `cljs.test/run-test` macro
    * Add cljs.core/Atom
    * Add promesa `promesify`
* [http-client](https://github.com/babashka/http-client): babashka's http-client
    * [#45](https://github.com/babashka/http-client/issues/45): query param values are double encoded
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Fix [#82](https://github.com/babashka/cli/issues/82): prefer alias over composite option
    * Add `:opts` to `:error-fn` input
    * Fix command line args for test runner `--dirs`, `--only`, etc
    * Fix `--no-option` (`--no` prefix) in combination with subcommands
    * Prioritize `:exec-args` over spec `:default`s
    * `dispatch` improvements ([@Sohalt](https://github.com/Sohalt), [@borkdude](https://github.com/borkdude)):
        * The `:cmds` order of entries in the table doesn't matter
        * Support parsing intermediate options: `foo --opt1=never bar --opt2=always`
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Bump edamame
    * Add `hash-ordered-coll`
    * `read-string` should use non-indexing reader


### Other projects  
These are many other projects I'm involved with but that had little to no activity in the past month. Check out the Other Projects section (more details) of my [blog here](https://blog.michielborkent.nl/oss-updates-jan-feb-2024.html) to see a full list.  
_Published: 2024-02-29_  
_Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)_  <br>  

---


## Toby Crawley  

### January 2024  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/commit/4c63223f47bd4d94e879acfbfdee8ea6ecd869e3), [`infrastructure`](https://github.com/clojars/infrastructure/commit/4d5993b0860857276a13874ec42e89f238c30188)

I upgraded the PostgreSQL database from v12.1 to v15.5.  

### February 2024  

**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/4c63223f47bd4d94e879acfbfdee8ea6ecd869e3...0b131ebcaf21b33cb7106026726d571e4fc47d1c)

This month was a smattering of small changes/fixes:

-   [Display username on all pref/admin pages](https://github.com/clojars/clojars-web/commit/bb01ae647468e8591d2de642d1d11ad7e8be18f2)
-   [Use a Thread for SQS receive loop to allow UCE to work](https://github.com/clojars/clojars-web/commit/dbe8769339c6470f9a094e1017e695bc85e27a3b)
-   [Report validity to GitHub on secret scanning requests](https://github.com/clojars/clojars-web/commit/60c064f9612f98336aebf6bda2845d112ff2827c)
-   [Add admin tooling to soft-delete users](https://github.com/clojars/clojars-web/commit/1655377c40927316c15cfd225dda5470cb801efb)
-   [Upgrade postgresql driver to address CVE-2024-1597](https://github.com/clojars/clojars-web/commit/d3153018dda214f00569bc8276c9b9dbf28a46c8)
<br>

---

## Thomas Heller  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (e.g. Clojurians Slack).  

Current shadow-cljs version: 2.27.5 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  

Notable Updates:
- Tweaked `watch` recompile logic to also account for indirect var usages via macros. The usual recompile logic compiles a changed namespace, and then everything which directly required it. This is sometimes insufficient since macros can insert references to other namespaces, without the namespace using the macro having direct knowledge (or require) of that ns. Now all direct accessed vars (after analysis) are collected and used for this logic, making it much more accurate.  
- Added limited support for npm modules using `import()` in their code. This is still fairly limited in support, and does not perform actual code splitting as `webpack` might, but it made some npm modules usable again that would previously just crash.
- Worked a lot on [shadow-grove](https://github.com/thheller/shadow-grove). Spent way too much time [benchmarking](https://github.com/thheller/js-framework-shadow-grove), but I feel confident now that performance is quite good and potentially the fastest CLJS `react`-equivalent available. Also updated the [playground](https://code.thheller.com/shadow-grove-playground/0.6.0/), which I plan on using as the basis for documentation. Still a couple of things to work out, but overall in good shape.  <br>

---

## Kira McLean  

This is a summary of the work I did on open source projects in January and February 2024. It was published as a blog post [on my website here](https://codewithkira.com/2024-02-29-clojurists-together-update-jan-feb-2024.html).  

### Clojure Tidy Tuesdays  

The main thing I spent my time working on over the past couple of months was a collection of tutorials and guides for working with data in Clojure. The R for Data Science online learning community publishes toy datasets every week for "Tidy Tuesdays" with a question to answer or example article to reproduce. I've been going through them in Clojure, and it's proven a great tool for uncovering areas for future development in the Clojure data science ecosystem.  

### Other Work  

The explorations with the Tidy Tuesday data have been revealing areas where I think we could benefit from more ergonomic ways to work with tablecloth datasets. I started two little projects each with a couple of little wrappers around existing functions to make them easier to use with tablecloth datasets. So far I'm calling them `tcstats` (for statistical operations on datasets) and `tcutils` (with miscellaneous dataset manipulation tools that aren't built-in to tablecloth directly).  

I am also still working on the Clojure Data Cookbook. I nudged it forward ever so slightly these last couple of months, and I plan to finish it despite the remaining holes in Clojure's data science stack. I would love to also fill these in eventually, but the Cookbook will be a living document that can easily evolve and be updated as new tools and libraries are developed.  

Lastly, one of the main missing pieces I'm discovering we really need to work on in Clojure's data science ecosystem is a robust yet flexible graphics library. There are a few great solutions that already exist, but they take different approaches to graphing that can make them a bit clumsy to work with when it comes time to build more complex visualisations. My dream is to implement a proper [grammar of graphics](https://ggplot2-book.org) in Clojure, giving the Clojure data ecosystem a "profressional quality" graphics library, so to speak. Anyway.. there is still tons of work to do here so I'm grateful for the ongoing funding that will allow me to continue to focus a large amount of time on it for the foreseeable future.  <br>

---

## Nikita Prokopov   
Hello guys and gals, Nikitonsky here with some Winter 2024 updates.  

New library! [Clj-reload](https://github.com/tonsky/clj-reload) is a smarter way to reload Clojure code during development:  
Main goal was to replace and improve over `tools.namespace`:  
- Do not reload everything on first repl/refresh  
- Allow users to register hooks on namespace unload  
- Support in-ns and standalone require and use  
- Not reload namespaces that were never loaded before  
- Keep defonce values/protocols/types between reload  

And it worked! Humble UI and my website are already migrated to it, and it feels fantastic to being able to use `defonce` again, without creating a separate namespace just to hold that one value.  

Filipe Silva [is working on CIDER integration](https://github.com/clojure-emacs/cider-nrepl/issues/849), so I hope we’ll see more adoption soon.  

If you’ve been using `tools.namespace`, give `clj-reload` a try. You might like it better. If you haven’t, it’s time to reconsider your REPL workflow. We have some convincing points in the README.  

Oh, and DO check out [our new logo](https://mastodon.online/@nikitonsky/112021659534638868). I’m pretty happy with how it turned out.  

[Humble UI](https://github.com/HumbleUI/HumbleUI):  

- Implemented tricky lazy-loading system  
- Improved deftype+ stacktraces  
- Migrated to VDom:  
  - gap
  - label
  - image
  - svg
  - padding
  - rect, rounded-rect
  - clip, clip-rrect
  - halign, valign, center
  - vscroll, vscrollbar
  - column, row
  - hoverable, clickable
  - button, toggle-button (new!)
  - slider
- Migrated to clj-reload  
- Jakub Dundalek managed to [run HumbleUI app from GraalVM native image](https://clojurians.slack.com/archives/C033FTUBWH5/p1709151287356759?thread_ts=1709110584.101919&cid=C033FTUBWH5
)  

[JWM](https://github.com/HumbleUI/JWM):  

- macOS: fix fullscreen. Yes, I had to fix something that I didn’t touch for a year and it broke anyways.  

[Humble Deck](https://github.com/HumbleUI/humble-deck):  
- Now supports multiple decks  

[humble-starter](https://github.com/HumbleUI/humble-starter):  
- Simplest possible project to draw a window on a screen  

[DataScript](https://github.com/tonsky/datascript/):  
- Query: shortcircuit clause resolution when result is guaranteed to be empty #459 via @galdre  

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed):  
- Detect namespace from in-ns forms  

[Sublime Executor](https://github.com/tonsky/Sublime-Executor):  
- Support colored output (I can finally see colored diffs in failing tests)  

Blogging:  
- [JavaScript Bloat in 2024](https://tonsky.me/blog/js-bloat/) (funny side note: other people now translate my articles to Russian because I write in English. How the table have turned!)  
- [New Library: clj-reload](https://tonsky.me/blog/clj-reload/)  
- [In Loving Memory of Square Checkbox](https://tonsky.me/blog/checkbox/)  

Talks:  
- [Desktop GUI Made Easy](https://www.youtube.com/watch?v=HZTrfz-2yW4)  

Onward and upward!  

<br>

---

## Tommi Reiman  
Worked on a big new release of [Malli](https://github.com/metosin/malli) and wrote a [blog post](https://www.metosin.fi/blog/2024-01-16-malli-data-modelling-for-clojure-developers) about it.  

[Reitit](https://github.com/metosin/reitit) should be now feature-complete for OpenAPI, will cut out release soon.  

Helped users to use and adopt the libs.  

### 0.14.0 (2024-01-16)  

* Better development-time tooling  
  * `malli.dev/start!` captures all malli-thrown exceptions, see [README]([README.md#development-mode](https://github.com/metosin/malli/blob/master/README.md#development-mode) for details  
  * does not log individual re-instrumentation of function vars  
  * **BREAKING**: changes in `malli.dev.virhe` and `malli.pretty` extension apis, wee [#980](https://github.com/metosin/malli/pull/980) for details  
* New `m/deref-recursive` to recursive deref all schemas (not `:ref`s)  
* FIX: Malli generates incorrect clj-kondo spec for :fn schemas [#836](https://github.com/metosin/malli/issues/836) via [#987](https://github.com/metosin/malli/pull/987)  
* Support for Var references [#985](https://github.com/metosin/malli/pull/985), see [guide]([README.md#var-registry](https://github.com/metosin/malli/blob/master/README.md#var-registry) for details.  
* **BREAKING**: `m/coerce` and `m/coercer` throw `::m/coercion` instead of `::m/invalid-input`  
* New Guide for [Reusable Schemas](https://github.com/metosin/malli/blob/master/docs/reusable-schemas.md)
* Less printing of Var instumentation   
* **BREAKING**: qualified symbols are valid reference types [#984](https://github.com/metosin/malli/pull/984)   
* Fixing `mt/strip-extra-keys-transformer` for recursive map encoding [#963](https://github.com/metosin/malli/pull/963)  
* Support passing custom `:type` in into-schema opt for `:map` and `:map-of` [#968](https://github.com/metosin/malli/pull/968)  
* `mu/path->in` works with `:orn`, `:catn` and `:altn`.  

### Something else  
Teppo the Dog enjoying the Sun at [Näsijärvi](https://fi.wikipedia.org/wiki/N%C3%A4sij%C3%A4rvi).  

<img width="1728" alt="spring-fi" src="https://gist.githubusercontent.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/1a23548f4d1cc94f6ec836cd4d7e5a039c4a35ad/teppo-nassy.jpg">
<br>

---
## Peter Taoussanis  

### Open source update

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and other sponsors of my open source work!  

It's been a productive start to the year! Have been focused almost entirely on open source. Output has included:  

### 7x library releases  

This includes [http-kit v2.8.0-RC1](https://github.com/http-kit/http-kit/releases/tag/v2.8.0-RC1) (tons of new stuff in here!), [Tempel v1.0.0-RC1](https://github.com/taoensso/tempel/releases/tag/v1.0.0-RC1), [Nippy v3.4.0-RC2](https://github.com/taoensso/nippy/releases/tag/v3.4.0-RC2), and [Timbre v6.5.0](https://github.com/taoensso/timbre/releases/tag/v6.5.0).  

See [here](https://www.taoensso.com/news#open-source) for the full list, and also [here](https://www.taoensso.com/roadmap) for a new GitHub-hosted **roadmap of my major upcoming open source work**.  

### New Tempel explainer video  

I recorded a short [video](https://www.youtube.com/watch?v=sULZVFhR848) to explain the new/upcoming [Tempel](https://www.taoensso.com/tempel) **data security framework**.  

This hopefully helps makes it clearer where Tempel can be useful.  

The ultimate goal is to try make it feasible for more Clojure devs to incorporate **data security in their apps**, and/or at least get more Clojure devs thinking about ways to protect their users’ data.  

It can be easier than you expect, and I detail one example pattern in the video.  

BTW please let me know if there's interest in me doing more of these kinds of videos in future.  

### London Clojurians Talk  

On 20 Feb I gave an online talk at the London Clojurians Group. The talk's now [on YouTube](https://www.youtube.com/watch?v=Jz9NcnQbH5I).  

Had a good time, was fun talking with folks there. A big thanks to [Bruno Bonacci](https://x.com/BrunoBonacci) for organizing and hosting!  

The talk was non-technical, titled:  

> **Some controversial truths**: challenging some commonly-held ideas about Clojure, software engineering, and startups; and sharing the 1 secret that will solve all your problems.  

Part of the talk's intention was to discuss some of the trade-offs that Clojure users/businesses should be aware of, but it looks like the Q&A after was unfortunately not recorded.  

Please ping if there's interest in me posting a write-up to summarize some of the key points discussed.  

### Interview with Daniel Compton  

Had a really nice [chat](https://t.co/VmwvbxAzC5) with [Daniel Compton](https://x.com/danielwithmusic) about my open source work and other projects. (Thanks Daniel!)  

To avoid possible confusion re: discussed dates/timelines, please note that this was recorded at the end of last year (2023).  

### Lots of work On Telemere  

Have been putting in a lot of work on the upcoming [Telemere](https://www.taoensso.com/telemere) **structured telemetry** library for Clojure/Script.  

Along with Tempel, this'll be my first all-new Clojure library in 7+ years.  

Very happy with how it's coming along so far, and looking forward to sharing more closer to release (hopefully this [April](https://www.taoensso.com/roadmap)).  

In some ways Telemere represents the culmination and refinement of many years of ideas from some of my other libraries - notably [Timbre](https://www.taoensso.com/timbre) and [Tufte](https://www.taoensso.com/tufte).  

I think the result is going to be really nice - and something possible only in Clojure.  

### Upcoming work

All major upcoming work is now documented live [here](https://taoensso.com/roadmap). In addition to all the usual maintenance and support, my biggest objectives for this year are definitely [Telemere](https://www.taoensso.com/telemere) and a major [Carmine](https:/www.taoensso.com/carmine) update.  

\- [Peter Taoussanis](https://www.taoensso.com)  









