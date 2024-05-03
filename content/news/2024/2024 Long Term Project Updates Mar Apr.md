---
title: "March & April 2024 Long-Term Project Updates"
date: 2024-05-03T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Batsov, Borkent, Crawley, Heller, McLean, Nikitonsky, Reiman, Taoussanis"
draft: True

---

There is a lot of work to report on for March and April by our 2024 long-term developers. 

[Bozhidar Batsov:](#bozhidar-batsov) CIDER, Clojure-mode, clojure ts-mode     
[Michiel Borkent:](#michiel-borkent) clj-kondo, babashka, SCI, squint, nbb, CLI, and more   
[Toby Crawley:](#toby-crawley) clojars    
[Thomas Heller:](#thomas-heller) shadow-cljs  
[Kira McLean:](#kira-mclean) Scicloj Libraries  
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Clj-reload, Datascript, and more  
[Tommi Reiman:](#tommi-reiman) Malli   
[Peter Taoussanis:](#peter-taoussanis) Telemere, Tempel, interviews, and more  

## Bozhidar Batsov  
Copy to come after vacation   


## Michiel Borkent  


## Toby Crawley:  

**April 2024**

**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/commit/4c63223f47bd4d94e879acfbfdee8ea6ecd869e3)

Mostly maintenance work this month related to error handling and tighening error boundaries.

-   [Tighten search page error handling](https://github.com/clojars/clojars-web/commit/50c6cc28261a6ee8fda4d476f8c004004841d961)
-   [Address CVEs with bouncycastle](https://github.com/clojars/clojars-web/commit/32867445a39a6a744fbadd60e9ce1d4f44110964)
-   [Remove usage of clj-time in favor of java.time](https://github.com/clojars/clojars-web/commit/c061d2bd6df6314a200a30c82bb04733a863019c)
-   [Remove /error route](https://github.com/clojars/clojars-web/commit/d3a7cff8c40c4bc8f462e155548027f8d4dac2f5)
-   [Fix logback to actually roll logs](https://github.com/clojars/clojars-web/commit/6824d96c945d697022b67990f809364128930b29)
-   [Reject non-flat http params in an attempt to reduce errors from fuzzing](https://github.com/clojars/clojars-web/commit/a26e1ee9eb5cee9e4ef6ac6f9ef6164c37c0e40e)
-   [Return invalid params response as json](https://github.com/clojars/clojars-web/commit/89e33a5a60f10ccb33e59d3a418a224bcb4af0dd)

**March 2024**  

**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/0b131ebcaf21b33cb7106026726d571e4fc47d1c...4a109fd875b0101164c2349b31a1fa624a7f28be), [`infrastructure`](https://github.com/clojars/infrastructure/compare/4d5993b0860857276a13874ec42e89f238c30188...190777d4270533d7d4316bb7f2e911cb80ee0dc1)

-   [Upgrade from Amazon Linux 2 to Amazon Linux 2023](https://github.com/clojars/infrastructure/commit/a50476c3073a7b5269a27cac8ce3b5085433fe22)
-   [Updates to error handling and reporting to allow more to be reported](https://github.com/clojars/clojars-web/commit/8160f6320156ac890b72aec5d3f97263a45bcd60)
-   Upgrades to address CVEs  <br>

---


## Thomas Heller: shadow-cljs  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (e.g. Clojurians Slack).

Current shadow-cljs version: 2.28.4 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

**Notable Updates**

- Introduced the [shadow-cljs Browser Extension](https://github.com/thheller/shadow-cljs-ext)
- Worked on some additional devtools for [shadow-grove](https://github.com/thheller/shadow-grove), which will also end up as a browser extension at some point. Similar in spirit as the react/vue developer tools.  <be>

---


## Kira McClean  
This is a summary of the open source work I've spent my time on throughout March and April, 2024. Overall it was a really insightful couple of months for me, with lots of productive discussions and meetings happening among key contributors to Clojure's data science ecosystem and great progress toward some of our most ambitious goals.  

### Sponsors  
This work is made possible by the generous ongoing support of my sponsors. I appreciate all of the support the community has given to my work and would like to give a special thanks to Clojurists Together and Nubank for providing me with lucrative enough grants that I can reduce my client work significantly and afford to spend more time on these projects.  

If you find my work valuable, please share it with others and consider supporting it financially. There are details about how to do that on my [GitHub sponsors page](https://github.com/sponsors/kiramclean/). On to the updates!  

### Grammar of graphics in Clojure  
With help from Daniel Slutsky and others in the community, I started some concrete work on implementing a grammar of graphics in Clojure. I'm convinced this is the correct long-term solution for dataviz in Clojure, but it is a big project that will take time, including a lot of [hammock time](https://www.youtube.com/watch?v=f84n5oFoZBc). It's still useful to play around with proofs of concept whilst thinking through problems, though, and in the interest of transparency I'm making all of [those experiments public](https://github.com/kiramclean/ggclj).  

The discussions around this development are all also happening in public. There were two visual tools meetups focused on this over the last two months ([link 1](https://www.youtube.com/watch?v=MxjzaOtcdcY), [link 2](https://www.youtube.com/watch?v=d3iRGmbJmes)). And at the London Clojurians talk I just gave today I demonstrated an example of one proposed implementation of a [grammar-of-graphics-like API](https://github.com/kiramclean/workshops/blob/main/london_clojurians_april_2024/src/utils/hana.clj) on top of hanami implemented by Daniel.  

There are more meetups planned for the coming months and work in this area for the foreseeable future will look like researching and understanding the fundamentals of the grammar of graphics in order to design a simple implementation in Clojure.  

### Clojure's ML and statistics tools   
I spent a lot of time these last couple of months documenting and testing out Clojure's current ML tools, leading to many great conversations and one [blog post](https://codewithkira.com/2024-04-04-state-of-clojure-ml.html) that generated many more interesting discussions. The takeaway is that the tools themselves in this area are all quite mature and stable, but there are still ongoing discussions around how to best accommodate the different ways that people want to work with them. The overall goal in this area of my work is to stabilize the solutions so we can start advocating for specific ways of using them.  

Below are some key takeaways from my research into all this stuff. Note none of these are my decisions to make alone, but represent my current opinions and what I will be advocating for within the community:
- Smile will be slowly sunsetted from the ecosystem. The switch to GPL licensing was made in bad faith and many of the common models don't work on Apple chips. Given the abundance of suitable alternatives, the easiest option is to move away from depending on it.  
- A greater distinction between statistical modelling and machine learning workflows will be helpful. Right now there are many uses of the various models that are available in Clojure, and the wrappers and tools surrounding them are usually designed with a specific type of user in mind. For example machine learning people almost always have separate training and testing datasets, whereas statisticians "train" their models on an entire dataset. The highest-level APIs for these different usages (among others) look quite different, and we would benefit from having APIs that are ergonomic and familiar to our target users of various backgrounds.  
- We should agree on standards for accomplishing certain very common and basic tasks and propose a recommended usage for users. For example, there are almost a dozen ways to do linear regression in Clojure and it's not obvious which is "the best" way to someone not deeply familiar with the ecosystem.
- Everything should work with tablecloth datasets and expect them as inputs. This is mostly the case already, but there is still some progress to be made.  

### Foundations of Clojure's data science stack  
I continue to work on guides and tutorials for the parts of Clojure's data science stack that I feel are ready for prime time, mainly tablecloth and all of the amazing underlying libraries it leverages. Every once in a while this turns up surprises, for example this month I was surprised at how column header processing is handled for nippy files specifically. I also [fixed one bug](https://github.com/scicloj/tablecloth/pull/143) in tablecloth itself, which I discovered in the process of writing a tutorial earlier in March. I have a pile of in-progress guides focusing on some more in-depth topics from developing the London Clojurians talk that I'm going to tidy up and publish in the coming months.  

The overarching goal in this area is to create a unified data science stack with libraries for processing, modelling, and visualization that all interoperate seamlessly and work with tablecloth datasets, like the tidyverse in R. Part of achieving that is making sure that tablecloth is rock solid, which just takes a lot of poking and prodding.  

### London Clojurians talk  
This talk was a big inspiration for diving deep into Clojure's data science ecosystem. I experimented with a ton of different datasets for the workshop and discovered tons of potential areas for future development. Trying to put together a polished data workflow really exposed many of the key areas I think we should be focusing on and gave me a lot of inspiration for future work. I spent a ton of time exploring all of the possible ways to demonstrate a broad sample of data science tools and learned a lot along the way.  

The resources from the talk are all available [in this repo](https://github.com/kiramclean/workshops/tree/main/london_clojurians_april_2024) and the video will be posted soon.  

### Summary of future work  
I mentioned a few areas of focus above, below is a summary of the ongoing work as I see it. A framework for organizing this work is starting to emerge, and I've been thinking about in terms of four key areas:  

#### Visualisation  
- Priority here is to release a stable dataviz API using the tools and wrappers we currently have so that we can start releasing guides and tutorials that follow a consistent style.  
- The long-term goal is to develop a robust, flexible, and stable data visualization library in Clojure itself based on the grammar of graphics.  

#### Machine learning  
- Priority is to decide which APIs we will commit to supporting in the long term and stabilize the "glue" libraries that provide the high-level APIs for data-first users.  
- Long term goal is to support the full spectrum of libraries and models that are in everyday use by data science professionals.  

#### Statistics  
- Priority is to document the current options for accomplishing basic statistical modelling tasks, including Clojure libraries we do have, Java libs, and Python interop.  
- Long term goal is to have tablecloth-compatible stats libraries implemented in pure Clojure.  

#### Foundations  
- Priority is to build a tidyverse for Clojure. This includes battle-testing tablecloth, fully documenting its capabilities, and fixing remaining, small, sharp edges.  

### Going forward  

My overarching goal (personally) is still to write a canonical resource for working with Clojure's data science stack (the Clojure Data Cookbook), and I'm still working on finding the right balance of documenting "work-in-progress" tools and libraries vs. delaying progress until I feel they are more "ready". Until now I've let the absence of stable or ideal APIs in certain areas hinder development of this book, but I'm starting to feel very confident in my understanding of the current direction of the ecosystem, enough so that I would feel good about releasing something a little bit more formal than a tutorial or guide and recommending usages with the caveat that development is ongoing in some areas. And while it will take a while to get where we want to go, I feel like I can finally see the path to getting there. It just takes a lot of work and lot of collaboration, but with your support we'll make it happen! Thanks for reading.  <br>

---


## Nikita Prokopov   

Hello hello hello, I hope you like some open-source, because I have some for ya. This past two months have been busy!  

[DataScript](https://github.com/tonsky/datascript):  

- Implement “constant substitution” optimization for queries [#462](https://github.com/tonsky/datascript/issues/462)  
- Fixed :max-eid for dangling entities during reader-based serialization [#463](https://github.com/tonsky/datascript/issues/463)  
- Fixed tempid in unique refs [#464](https://github.com/tonsky/datascript/issues/464)  
- Pass through BigInteger/BigDeciman to freeze-fn in serializable [#465](https://github.com/tonsky/datascript/pull/465), [#466](https://github.com/tonsky/datascript/issues/466)  

[Humble UI](https://github.com/HumbleUI/Skija):  

- Migration to VDOM continues. Ported `stack`, redid `button`  
- Redid state sharing approach between behavior components (e.g. `clickable`) and visual ones (e.g. `button-look`)
- New frame & event pace graphs
- New canvas example (drawing, mouse lag graph)

[Skija](https://github.com/HumbleUI/Skija):

- Surface and PictureRecorder cache returned Canvas object and invalidate it when owner is closed [#66](https://github.com/HumbleUI/Skija/issues/66)

[clj-reload](https://github.com/tonsky/clj-reload), a smarter way to reload Clojure code:

- Added `unload`
- Fixed issues when adding/removing keeps
- Speed up topo-sort [#5](https://github.com/tonsky/clj-reload/pull/5)
- Do not report self-reference as a cycle [#6](https://github.com/tonsky/clj-reload/issues/6)
- Parse record ctor syntax [#7](https://github.com/tonsky/clj-reload/issues/7)
- Added `:files` option [#8](https://github.com/tonsky/clj-reload/pull/8)

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed), Clojure development environment for Sublime Text:

- Socket REPL: Watches (just like in original LightTable!)
- Added `transform` argument to `clojure_sublimed_eval`. It lets you implement stuff like print to buffer, eval test under cursor, macroexpand etc [#101](https://github.com/tonsky/Clojure-Sublimed/issues/101) [#102](https://github.com/tonsky/Clojure-Sublimed/pull/102)
- Added `expand` argument to `clojure_sublimed_eval`
- Added `output.repl` panel and `Toggle Output Panel` command for raw nREPL [#104](https://github.com/tonsky/Clojure-Sublimed/issues/104)
- Raw nREPL: Support colored output in output console [#99](https://github.com/tonsky/Clojure-Sublimed/issues/99)
- Display failed tests report as red
- Fixed `Reconnect` command
- Add `on_finish_callback` to `eval` [#105](https://github.com/tonsky/Clojure-Sublimed/pull/105)
- Added `print_quota` as a setting and as an argument to `cs_conn.eval`

New library: [sane-math](https://github.com/tonsky/sane-math)

- Write mathematical expression in Clojure in infix notation
- Part April Fools’ joke, part serious
- Supports `+`, `−`, `*`, `/`, unary minus, `**` and parentheses

Blogging:

- [Hardest Problem in Computer Science: Centering Things](https://tonsky.me/blog/centering/)
- [Humble Chronicles: The Inescapable Objects](https://tonsky.me/blog/humble-objects/)
- [Humble Chronicles: Shape of the Component](https://tonsky.me/blog/humble-defcomp/)
- [Daylight Saving Time is a perfect test for UI designer](https://tonsky.me/blog/dst/)

Best,
Niki  <br>

---

## Tommi Reiman  

Finally! Reitit 0.7.0 is out. It has been over a year in the making, spanning multiple libraries: 
[reitit](https://github.com/metosin/reitit), [malli](https://github.com/metosin/malli), 
[schema-tools](https://github.com/metosin/schema-tools), [spec-tools](https://github.com/metosin/spec-tools and
[ring-swagger-ui](https://github.com/metosin/ring-swagger-ui). Big thanks to everyone involved.
There is a lot of draft work that has been queued and can now be worked on. New releases should come more
frequently in the future.

### Reitit 0.7.0 (all 8 alphas flattened)

### 0.7.0 (2024-04-30)

The OpenAPI3 release, Year in the making - the changes span over multiple repositories!

* Openapi3 support, see the [docs](https://github.com/metosin/reitit/blob/master/doc/ring/openapi.md)
  * Fetch OpenAPI content types from Muuntaja [#636](https://github.com/metosin/reitit/issues/636)
  * OpenAPI 3 parameter descriptions get populated from malli/spec/schema descriptions. [#612](https://github.com/metosin/reitit/issues/612)
  * Generate correct OpenAPI $ref schemas for malli var and ref schemas [#673](https://github.com/metosin/reitit/pull/673)
  * new syntax for `:request` and `:response` per-content-type coercions. See [coercion.md](https://github.com/metosin/reitit/blob/master/doc/ring/coercion.md). [#627](https://github.com/metosin/reitit/issues/627)
  * [#84](https://github.com/metosin/reitit/issues/84)
* Handlers can be vars [#585](https://github.com/metosin/reitit/pull/585)
* Fix swagger generation when unsupported coercions are present [#671](https://github.com/metosin/reitit/pull/671)
* **BREAKING**: require Clojure 1.11, drop support for Clojure 1.10
* **BREAKING**: `compile-request-coercers` returns a map with `:data` and `:coerce` instead of plain `:coerce` function
* **BREAKING**: Parameter and Response schemas are merged into the route data vector - so they can be properly merged into the compiled result, fixes [#422](https://github.com/metosin/reitit/issues/422) - merging multiple schemas together works with `Malli` and `Schema`, partially with `data-spec` but not with `spec`.
* Fixed some module dependencies so Cljdoc can properly analyze all the modules
* Fix reading fragment string on `Html5History` initialization
* Add fragment string parameter to reitit-frontend functions ([#604](https://github.com/metosin/reitit/pull/604))
* Frontend: provide easy way to update current query params. [#600](https://github.com/metosin/reitit/issues/600)

* Updated dependencies:

```clojure
[metosin/malli "0.16.1"] is available but we use "0.10.1"
[metosin/muuntaja "0.6.10"] is available but we use "0.6.8"
[metosin/spec-tools "0.10.6"] is available but we use "0.10.5"
[metosin/schema-tools "0.13.1"] is available but we use "0.13.0"
[metosin/jsonista "0.3.8"] is available but we use "0.3.7"
[com.fasterxml.jackson.core/jackson-core "2.17.0"] is available but we use "2.14.2"
[com.fasterxml.jackson.core/jackson-databind "2.17.0"] is available but we use "2.14.2"
[ring/ring-core "1.12.1"] is available but we use "1.9.6"
[metosin/ring-swagger-ui "5.9.0"] is available but we use "4.15.5"
```

### Malli

#### 0.16.1 (2024-04-30)

* Enabled Java8 tests back, no need to limit the version.

#### 0.16.0 (2024-04-20)

* **BREAKING**: minimum Java-version is now Java11
* allow changing prefix of json-schema $refs via option `:malli.json-schema/definitions-path` [#1045](https://github.com/metosin/malli/pull/1045)
* Inline refs in non-`:body` swagger parameters [#1044](https://github.com/metosin/malli/pull/1044)
* Fix flaky test [#1040](https://github.com/metosin/malli/pull/1040)
* Utility to update entry properties: `mu/update-entry-properties` [#1037](https://github.com/metosin/malli/pull/1037)
* Fix actions cache [#1036](https://github.com/metosin/malli/pull/1036)
* Only humanize one of `:min` / `:max` when different [#1032](https://github.com/metosin/malli/pull/1032)
* Distinguish between symbols and strings in humanize [#1031](https://github.com/metosin/malli/pull/1031)
* Fix `:map-of` `:min` and unreachable generator, explain such-that failures [#1029](https://github.com/metosin/malli/pull/1029)

#### 0.15.0 (2024-03-23)

* `:=>` takes optional 3rd child, the guard schema validating vector of arguments and return value `[args ret]`. See [Function Guards](https://github.com/metosin/malli/blob/master/docs/function-schemas.md#function-guards) for more details. Fixes [#764](https://github.com/metosin/malli/issues/764) and [#764](https://github.com/metosin/malli/issues/764).

```clojure
;; function of arg:int -> ret:int, where arg < ret
[:=> 
 [:cat :int] 
 :int 
 [:fn (fn [[[arg] ret]] (< arg ret))]]
```

* **BREAKING**: `malli.generator/function-checker` returns explanations under new keys:
  * `::mg/explain-input` -> `::m/explain-input`
  * `::mg/explain-output` -> `::m/explain-output`
  * new `::m/explain-guard` to return guard explanation, if any
* `m/explain` for `:=>` returns also errors for args, return and guard if they exist
* FIX `m/deref-recursive` doesn't play nice with `:merge` schema [#997](https://github.com/metosin/malli/issues/997) via [#999](https://github.com/metosin/malli/pull/999)
* FIX nested `:repeat` sequence schema's doesn't seem to work [#761](https://github.com/metosin/malli/issues/761) via [#1024](https://github.com/metosin/malli/pull/1024)
* FIX Invalid Swagger JSON with `[:or :nil]` alternatives [#1006](https://github.com/metosin/malli/issues/1006) via [#1023](https://github.com/metosin/malli/pull/1023)
* FIX `(explain :tuple [])` [#1022](https://github.com/metosin/malli/pull/1022)
* Enforce entry specs in open map destructurings [#1021](https://github.com/metosin/malli/pull/1021)
* FIX `goog/mixin` was deprecated and is now removed [#1016](https://github.com/metosin/malli/pull/1016)

* Updated dependencies:

```clojure
borkdude/edamame 1.3.23 -> 1.4.25
```

### Something else

Back at Greece, to relax and to Open Source.

<img width="1728" alt="os-greece" src="https://gist.github.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/382b1e5afb832088157a989ae2d5ab316683f3de/os-greece.jpg">
<br>

---


## Peter Taoussanis  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work!

Hi folks! 🦎

It's been a very productive couple months! Have been able to continue full-time on open source. Output included:  

### http-kit v2.8.0  

[http-kit](https://www.taoensso.com/http-kit) [v2.8.0 final](https://github.com/http-kit/http-kit/releases/tag/v2.8.0) is now [on Clojars](https://clojars.org/http-kit/versions/2.8.0) 👍  

> http-kit is a simple, high-performance event-driven **HTTP client+server** for Clojure.  

This is the first major stable http-kit release since 2023-06-30, and includes work from 10 contributors. Big thanks to everyone involved! 🙏  

Some highlights include:  

- Performance improvements, incl. **auto JVM 21+ virtual threads** when available.
- Support for the **latest Ring async and WebSocket APIs**.
- A comprehensive new [benchmark suite](https://github.com/http-kit/http-kit/wiki/4-Benchmarking) for http-kit server and client.
- **>15 fixes** and numerous other improvements  

### Nippy v3.4.0  

[Nippy](https://www.taoensso.com/nippy) [v3.4.0 final](https://github.com/taoensso/nippy/releases/tag/v3.4.0) is now [on Clojars](https://clojars.org/com.taoensso/nippy/versions/3.4.0) 👍

> Nippy is a [fast](https://github.com/taoensso/nippy?tab=readme-ov-file#performance) and mature **binary serialization** library for Clojure.

This is the first major stable Nippy release since 2023-10-11, and includes:

- Faster, more accurate [freezable?](https://cljdoc.org/d/com.taoensso/nippy/CURRENT/api/taoensso.nippy#freezable?) util (checks if arg is serializable)
- [Zstandard](https://facebook.github.io/zstd/) compression support
- Support for serializing [next.JDBC](https://github.com/seancorfield/next-jdbc) results (this was previously broken)

### Telemere v1.0.0 first public pre-releases  

My main focus during this period has been has been [Telemere](https://www.taoensso.com/telemere).

Telemere is a major new library (and along with [Tempel](https://www.taoensso.com/tempel), my first all-new library in 7+ years). It's a **structured telemetry library** for Clojure/Script, and a highly-polished modern rewrite of [Timbre](https://www.taoensso.com/timbre) without any of the historical constraints.

It offers a superset of the functionality found in **traditional and structured logging**, and out-the-box  support for **SLF4J**, **OpenTelemetry**, clojure.tools.logging, etc.

The latest release can be found [here](https://github.com/taoensso/telemere/releases) (currently [beta5](https://github.com/taoensso/telemere/releases/tag/v1.0.0-beta5)).

Folks happy with Timbre have zero pressure to update, I’ll continue to support Timbre as usual.  But Telemere offers _a lot_ of new features and improvements (see [README](https://github.com/taoensso/telemere?tab=readme-ov-file#why-telemere)), and [migration](https://github.com/taoensso/telemere/wiki/5-Migrating) is often pretty easy.

Will note that based on Clojure survey feedback, I’ve been putting a lot more emphasis lately on **beginner-oriented support**.  For Telemere this includes the most comprehensive [wiki](https://www.taoensso.com/telemere/wiki) and [API docs](https://www.taoensso.com/telemere/wiki) I’ve yet included with a library.

Please do let me know if this stuff is helpful, since it adds a lot to the development effort! 🙏  

There’s also a new Telemere [Slack channel](https://www.taoensso.com/telemere/slack) and short [demo video](https://youtu.be/-L9irDG8ysM?si=NR9tKG6IygaPDx8i).  

Telemere's a small library, but it's been a lot of work getting the details just right. I'm happy with the results, and excited for folks to try it out.  

Telemere is in many ways represents the refinement and culmination of ideas brewing over 12+ years in [Timbre](https://www.taoensso.com/timbre), [Tufte](https://www.taoensso.com/tufte), [Truss](https://www.taoensso.com/truss), etc.

Ultimately the hope is for it to help enable Clojure/Script systems that are **observable**, **robust**, and **debuggable**. The [wiki intro](https://github.com/taoensso/telemere/wiki/1-Getting-started) is probably a good place to start if you're interested in hearing more.

### London Clojurians talk  

This was actually recorded back in February, but I have folks still occasionally mentioning that they'd missed it earlier - so I'll share a reminder here.

The talk is available [on YouTube](https://www.youtube.com/watch?v=Jz9NcnQbH5I), and is titled **Some Controversial Truths**: _challenging some commonly-held ideas about Clojure, software engineering, and startups; and sharing the 1 secret that will solve all your problems.

Big thanks to [Bruno Bonacci](https://twitter.com/BrunoBonacci) for hosting!

### Interview with Daniel Compton  

Likewise for folks that missed it earlier - [Daniel Compton](https://www.therepl.net/episodes/50/)  recently posted a [chat](https://www.therepl.net/episodes/50/) we had about my open source work. Big thanks to Daniel for hosting!  

### Upcoming work  

My current roadmap can always be found [here](https://www.taoensso.com/roadmap).

Current objectives for May-June include:

- Release the final stable version of [Tempel](https://www.taoensso.com/tempel) - my new **data security framework** for Clojure. Before the final release I'm planning to add support for [MFA](https://en.wikipedia.org/wiki/Multi-factor_authentication), extend the docs re: use with [OpenID](https://en.wikipedia.org/wiki/OpenID), [OWASP](https://en.wikipedia.org/wiki/OWASP), and make a few other last improvements.
  
- Continued efforts on [Telemere](https://www.taoensso.com/telemere). I'm currently working on porting handlers over from Timbre, improving the documentation, and helping out folks on the [Slack channel](https://www.taoensso.com/telemere/slack).
  
- (If time allows) I'd also like to update [Tufte](https://www.taoensso.com/tufte) to use the new engine that was written for [Telemere](https://www.taoensso.com/telemere). The two already [work well together](https://github.com/taoensso/telemere/wiki/3-Config#tufte), but this'll be especially true after they share the same engine (and so filtering and handler API).

\- [Peter Taoussanis](https://www.taoensso.com)

