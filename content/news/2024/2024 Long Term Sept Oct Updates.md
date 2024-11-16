---
title: "Sept. and Oct. 2024  Long-Term Project Updates"
date: 2024-11-16T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Peter Taoussanis"  
draft: True


---
 


Check out their latest project updates from our 2024 long-term developers! These reports just in for September and October. Thanks to all!


## Long Term Project Updates  

[Bozhidar Batsov:](#bozhidar-batsov) CIDER       
[Michiel Borkent:](#michiel-borkent) squint, babashka, neil, cherry, clj-kondo, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs, shadow-grove    
[Kira McLean:](#kira-mclean) Scicloj Libraries. tcutils, Clojure Data Cookbook, and more   
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Datascript, AlleKinos, Clj-reload, and more  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli, jsonista, and more    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, Telemere, and more  <br>



## Bozhidar Batsov   
The big news for the past couple of months was the release of CIDER 1.16 ("Kherson").  
It was a fairly small release, mainly focused on adding support for nREPL 1.3 (which brought a lot of internal improvements). The other interesting bit about CIDER 1.16 was switching to an "in-house" tracing back-end that resulted in slightly nicer tracing output. Check the [release notes](https://github.com/clojure-emacs/cider/releases/tag/v1.16.0) for more details.  

Other interesting things that happened in the realm of CIDER & friends were:  
- Orchard [0.28](https://github.com/clojure-emacs/orchard/blob/master/CHANGELOG.md#0280-2024-10-31) brought improvements to the inspector and simplified the Java parser codebase  
- We've fixed the font-locking of CIDER results in the minibuffer  
- We've revived the `cider` completion style (details [here](https://github.com/clojure-emacs/cider/pull/3746))
- You can now display the available log frameworks with `cider-log-show-frameworks`  
- A few bugs were fixed in `clojure-ts-mode` and we've started to port `clojure-mode`'s tests over there  

In other news - I turned 40 in October, which means I've spent 30% of my life working on CIDER & co! :D As usual - huge thanks for supporting my work!  <br>

---

## Michiel Borkent  
**Updates**
In this post I'll give updates about open source I worked on during September and October 2024. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

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

If you're used to sponsoring through some other means which aren't listed above, please get in touch. On to the projects that I've been working on!

### Updates  
In September I visited [Heart of Clojure](https://2024.heartofclojure.eu/) where Christian, Teodor and I did a workshop on babashka. The first workshop was soon fully booked so we even did a second one and had a lot of fun doing so. It was so good to see familiar Clojure faces in real life again. Thanks Arne and Gaiwan team for organizing this amazing conference.

Although I didn't make it to the USA for the Clojure conj in October, Alex Miller did invite me to appear towards the end of his closing talk when he mentioned that 90% of survey respondents used babashka.

![image](https://github.com/user-attachments/assets/cba703f9-820e-4b22-a33f-eb4f3ff56957)

![image](https://github.com/user-attachments/assets/5e0d873d-2211-478c-8ae8-c53dd5b456b8)

If you are interested in a full stack web framework with babashka and squint, check out [borkweb](https://github.com/m3tti/borkweb).

Here are updates about the projects/libraries I've worked on in the last two months.
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy. \

    * Unreleased
    * [#1784](https://github.com/clj-kondo/clj-kondo/issues/1784): detect `:redundant-do` in `catch`
    * [#2410](https://github.com/clj-kondo/clj-kondo/issues/2410): add `--report-level` flag
    * 2024.09.27
    * [#2404](https://github.com/clj-kondo/clj-kondo/issues/2404): fix regression with metadata on node in hook caused by `:redundant-ignore` linter
    * 2024.09.26
    * [#2366](https://github.com/clj-kondo/clj-kondo/issues/2366): new linter: `:redundant-ignore`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md)
    * [#2386](https://github.com/clj-kondo/clj-kondo/issues/2386): fix regression introduced in [#2364](https://github.com/clj-kondo/clj-kondo/issues/2364) in `letfn`
    * [#2389](https://github.com/clj-kondo/clj-kondo/issues/2389): add new `hooks-api/callstack` function
    * [#2392](https://github.com/clj-kondo/clj-kondo/issues/2392): don't skip jars that were analyzed with `--skip-lint`
    * [#2395](https://github.com/clj-kondo/clj-kondo/issues/2395): enum constant call warnings
    * [#2400](https://github.com/clj-kondo/clj-kondo/issues/2400): `deftype` and `defrecord` constructors can be used with `Type/new`
    * [#2394](https://github.com/clj-kondo/clj-kondo/issues/2394): add `:sort` option to `:unsorted-required-namespaces` linter to enable case-sensitive sort to match other tools
    * [#2384](https://github.com/clj-kondo/clj-kondo/issues/2384): recognize `gen/fmap` var in `cljs.spec.gen.alpha`
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * [#1752](https://github.com/babashka/babashka/issues/1752): include `java.lang.SecurityException` for `java.net.http.HttpClient` support
    * [#1748](https://github.com/babashka/babashka/issues/1748): add `clojure.core/ensure`
    * Upgrade to `taoensso/timbre` `v6.6.0`
    * Upgrade to GraalVM 23
    * [#1743](https://github.com/babashka/babashka/issues/1743): fix new fully qualified instance method in call position with GraalVM 23
    * Clojure 1.12 interop: method thunks, FI coercion, array notation (see below)
    * Upgrade SCI reflector based on clojure 1.12 and remove specific workaround for `Thread/sleep` interop
    * Add `tools.reader.edn/read`
    * Fix [#1741](https://github.com/babashka/babashka/issues/1741): `(taoensso.timbre/spy)` now relies on macros from `taoensso.encore` previously not available in bb
    * Upgrade Clojure to `1.12.0`
    * [#1722](https://github.com/babashka/babashka/issues/1722): add new clojure 1.12 vars
    * [#1720](https://github.com/babashka/babashka/issues/1720): include new clojure 1.12's `clojure.java.process`
    * [#1719](https://github.com/babashka/babashka/issues/1719): add new clojure 1.12 `clojure.repl.deps` namespace. Only calls with explicit versions are supported.
    * [#1598](https://github.com/babashka/babashka/issues/1598): use Rosetta on CircleCI to build x64 images
    * [#1716](https://github.com/babashka/babashka/issues/1716): expose `babashka.http-client.interceptors` namespace
    * [#1707](https://github.com/babashka/babashka/issues/1707): support `aset` on primitive array
    * [#1676](https://github.com/babashka/babashka/issues/1676): restore compatibility with newest [at-at](https://github.com/overtone/at-at/) version (1.3.58)
    * Bump SCI
    * Bump `fs`
    * Bump `process`
    * Bump `deps.clj`
    * Bump `http-client`
    * Bump `clj-yaml`
    * Bump `edamame`
    * Bump `rewrite-clj`
    * Add `java.io.LineNumberReader`
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Fix [#942](https://github.com/babashka/sci/issues/942): improve error location of invalid destructuring
    * Fix [#917](https://github.com/babashka/sci/issues/917): support new Clojure 1.12 Java interop: `String/new`, `String/.length` and `Integer/parseInt` as fns
    * Fix [#925](https://github.com/babashka/sci/issues/925): support new Clojure 1.12 array notation: `String/1`, `byte/2`
    * Fix [#926](https://github.com/babashka/sci/issues/926): Support `add-watch` on vars in CLJS
    * Support `aset` on primitive array using reflection
    * Fix [#928](https://github.com/babashka/sci/issues/928): record constructor supports optional meta + ext map
    * Fix [#934](https://github.com/babashka/sci/issues/934): `:allow` may contain namespaced symbols
    * Fix [#937](https://github.com/babashka/sci/issues/937): throw when copying non-existent namespace
    * Update `sci.impl.Reflector` (used for implementing JVM interop) to match Clojure 1.12
* [squint](https://github.com/squint-cljs/squint): CLJS *syntax* to JS compiler
    * Fix watcher and compiler not overriding `squint.edn` configurations with command line options.
    * Allow passing `--extension` and `--paths` via CLI
    * Fix [#563](https://github.com/squint-cljs/squint/issues/563): prioritize refer over core built-in
    * Update `chokidar` to v4 which reduces the number of dependencies
    * BREAKING: Dynamic CSS in `#html` must now be explicitly passed as map literal: `(let [m {:color :green}] #html [:div {:style {:& m}}])`. Fixes issue when using `lit-html` in combination with `classMap`. See [demo](https://squint-cljs.github.io/squint/?src=KG5zIG15bGl0CiAgKDpyZXF1aXJlIFtzcXVpbnQuY29yZSA6cmVmZXIgW2RlZmNsYXNzIGpzLXRlbXBsYXRlXV0KICAgWyJodHRwczovL2VzbS5zaC9saXRAMy4wLjAiIDphcyBsaXRdCiAgIFsiaHR0cHM6Ly9lc20uc2gvbGl0QDMuMC4wL2RpcmVjdGl2ZXMvY2xhc3MtbWFwLmpzIiA6cmVmZXIgW2NsYXNzTWFwXV0pKQoKKGRlZmNsYXNzIE15RWxlbWVudAogIChleHRlbmRzIGxpdC9MaXRFbGVtZW50KQogICheOnN0YXRpYyBmaWVsZCBwcm9wZXJ0aWVzIHs6Y291bnQge319KQoKICAoY29uc3RydWN0b3IgW3RoaXNdCiAgICAoc3VwZXIpCiAgICAoc2V0ISB0aGlzLmNvdW50IDApCiAgICAoc2V0ISB0aGlzLm5hbWUgIkhlbGxvIikpCgogIE9iamVjdAogIChyZW5kZXIgW3RoaXNdCiAgICAjaHRtbCBebGl0L2h0bWwKICAgIFs6ZGl2CiAgICAgWzpoMSB7OmNsYXNzIChjbGFzc01hcCB7OmVuYWJsZWQgdHJ1ZX0pfQogICAgICB0aGlzLm5hbWVdCiAgICAgWzpidXR0b24geyJAY2xpY2siIHRoaXMub25DbGljawogICAgICAgICAgICAgICA6cGFydCAiYnV0dG9uIn0KICAgICAgIkNsaWNrIGNvdW50ICIgdGhpcy5jb3VudF1dKQoKICAob25DbGljayBbdGhpc10KICAgIChzZXQhIHRoaXMuY291bnQgKGluYyB0aGlzLmNvdW50KSkpKQoKKGRlZm9uY2UgZm9vCiAgKGRvCiAgICAoanMvd2luZG93LmN1c3RvbUVsZW1lbnRzLmRlZmluZSAibXktZWxlbWVudCIgTXlFbGVtZW50KQogICAgdHJ1ZSkpCgooZGVmIGFwcCAob3IgKGpzL2RvY3VtZW50LnF1ZXJ5U2VsZWN0b3IgIiNhcHAiKQogICAgICAgICAgIChkb3RvIChqcy9kb2N1bWVudC5jcmVhdGVFbGVtZW50ICJkaXYiKQogICAgICAgICAgICAgKHNldCEgLWlkICJhcHAiKQogICAgICAgICAgICAgKGpzL2RvY3VtZW50LmJvZHkucHJlcGVuZCkpKSkKCihzZXQhICguLWlubmVySFRNTCBhcHApICNodG1sIFs6ZGl2CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBbOm15LWVsZW1lbnRdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAjX1s6bXktZWxlbWVudF1dKQ%3D%3D)
    * [#556](https://github.com/squint-cljs/squint/issues/556): fix referring to var in other namespace via global object in REPL mode
    * Pass `--repl` opts to `watch` subcommand in CLI
    * [#552](https://github.com/squint-cljs/squint/issues/552): fix REPL output with hyphen in ns name
    * Ongoing work on browser REPL. Stay tuned.
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * Fix referring to vars in other namespaces globally
    * Allow `defclass` to be referenced through other macros, e.g. as `cherry.core/defclass`
    * Fix emitting keyword in HTML
    * [#138](https://github.com/squint-cljs/cherry/issues/138): Support `#html` literals, ported from squint
* [http-client](https://github.com/babashka/http-client): babashka's http-client \

    * [#68](https://github.com/babashka/http-client/issues/68) Fix accidental URI path decoding in uri-with-query ([@hxtmdev](https://github.com/hxtmdev))
    * [#71](https://github.com/babashka/http-client/issues/71): Link back to sources in release artifact ([@lread](https://github.com/lread))
    * [#73](https://github.com/babashka/http-client/issues/71): Allow implicit ports when specifying the URL as a map ([@lvh](https://github.com/lvh))
* [http-server](https://github.com/babashka/http-server): serve static assets
    * [#16](https://github.com/babashka/http-server/issues/16): support range requests ([jmglov](https://github.com/jmglov))
    * [#13](https://github.com/babashka/http-server/issues/13): add an ending slash to the dir link, and don't encode the slashes ([@KDr2](https://github.com/KDr2))
    * [#12](https://github.com/babashka/http-server/issues/12): Add headers to index page (rather than just file responses)
* [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one command \

    * Fix #88: bbin ls with 0-length files doesn't crash
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
    * Add `cljs.pprint/code-dispatch` and `cljs.pprint/with-pprint-dispatch`
* [clojurescript](https://github.com/clojure/clojurescript)
    * [Throw when calling ana-api/ns-publics on non-existing ns](https://github.com/clojure/clojurescript/commit/f1fc819da94147411e353ecb17c751af6a18ce2e)
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects. \

    * [#241](https://github.com/babashka/neil/issues/241): ignore missing deps file (instead of throwing) in `neil new` ([@bobisageek](https://github.com/bobisageek))
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
    * Added a configuration for `cljs.spec.alpha` and related namespaces
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Include `cljs.spec.alpha`, `cljs.spec.gen.alpha`, `cljs.spec.test.alpha`
* [qualify-methods](https://github.com/borkdude/qualify-methods)
    * Initial release of experimental tool to rewrite instance calls to use fully qualified methods (Clojure 1.12 only0
* [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
    * Add support for `:require-cljs` which allows you to use `.cljs` files for render functions
    * Add support for nREPL for developing render functions
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Upgrade/sync with clojure CLI v1.12.0.1479
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
    * Work has started to support prepending output (in support for babashka parallel tasks). Stay tuned. <br>

---


## Toby Crawley  
### October 2024  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/4dc64513a676198f9971f1609246e92d83d6a110...7414339136d6ac3455525fd949ee8372ff15ea03), [`infrastructure`](https://github.com/clojars/infrastructure/compare/28a7362e772902241162ebcfdb6e8bc228c3b35c...e801cb297560b911c9b1ad01cd46c805d16eeed3)  

-   [Increased max upload size from 20MB to 30MB](https://github.com/clojars/infrastructure/commit/93724045d5afcebdce9819c9b7f0fbdd46221538) This is to allow larger artifacts
    to be uploaded, now that we see native builds more often.  
-   [Addressed CVEs](https://github.com/clojars/clojars-web/commit/4c461c4b546350536b19988f9391d3fe9d27e822)  


### September 2024   
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/2202a352e84c9eb63671d2163ae94bc96e4a331e...0537bc843a8ace13f7ff716410975949587ec7ae), [`infrastructure`](https://github.com/clojars/infrastructure/compare/6cf9c100e38408016cd979f1611602523766200e...28a7362e772902241162ebcfdb6e8bc228c3b35c)  

-   [Upgraded to clojure 1.12.0](https://github.com/clojars/clojars-web/commit/7760c34369f0d107bfa26a3703e1334f32f531e3)  
-   [Upgraded to java 21](https://github.com/clojars/infrastructure/commit/28a7362e772902241162ebcfdb6e8bc228c3b35c)  
-   [Added hcaptcha to signup form](https://github.com/clojars/clojars-web/pull/886)  
-   [Fixed status-message sending on validation failure](https://github.com/clojars/clojars-web/commit/e644fcc9a414b628e8b5de8772f8ec2ccf95d7f5)  <br>

---

## Thomas Heller  
### shadow-cljs Update  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).  

Current shadow-cljs version: 2.28.18 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  


### Notable Updates  
- Added support for package.json `"imports"`  

### Blog  
Wrote 2 new blog posts describing my personal REPL-based Workflow using shadow-cljs  
- [Fullstack Workflow with shadow-cljs](https://code.thheller.com/blog/shadow-cljs/2024/10/18/fullstack-cljs-workflow-with-shadow-cljs.html)  
- [Supercharging the REPL Workflow](https://code.thheller.com/blog/shadow-cljs/2024/10/30/supercharging-the-repl-workflow.html)  <br>

---

## Kira McLean  
This is a summary of the open source work I spent my time on throughout September and October 2024. This was a very busy period in my personal life and I didn't make much progress on my projects, but I did have more time than usual to think about things, which prompted many further thoughts. Keep reading for details :)

### Sponsors

I always start these posts with a sincere thank you to the generous ongoing support of my sponsors that make this work possible. I can't say how much I appreciate all of the support the community has given to my work and would like to give a special thanks to Clojurists Together and Nubank for providing incredibly generous grants that allowed me reduce my client work significantly and afford to spend more time on projects for the Clojure ecosystem for nearly a year.

If you find my work valuable, please share it with others and consider supporting it financially. There are details about how to do that on my [GitHub sponsors page](https://github.com/sponsors/kirahowe). On to the updates!

### Personal update

I'll save the long version for the end but there is one important personal update that's worth mentioning up front: I go by Kira Howe now. I used be known as Kira McLean, and all of my talks, writing, and commits up to this point use Kira McLean, but I'm still the same person! Just with a new name. I even updated my [GitHub handle](https://github.com/kirahowe), which went remarkably smoothly.

### Conj 2024

The main Clojure-related thing I did during this period was attend the Conj. It's always cool to meet people in person who you've only ever worked with online, and I finally got to meet so many of the wonderful people from Clojure Camp and Scicloj who I've had the pleasure of working with virtually. I also had the chance to meet some of my new co-workers, which was great. There were tons of amazing talks and as always insightful and inspiring conversations. I always leave conferences with tons of energy and ideas. Then get back to reality and realize there's no time to implement them all :) But still, below are some of the main ideas I'm working through after a wonderful conference.

### SVGs for visualizing graphics

Tim Pratley and Chris Houser gave a fun talk about SVGs, among other things, that made me realize using SVGs might be the perfect way to implement the "graphics" side of a grammar of graphics.

Some of you may be following the development of [tableplot](https://github.com/scicloj/tableplot) (formerly hanamicloth), in which Daniel Slutsky has been implementing an elegant, layered, grammar-of-graphics-inspired way to describe graphics in Clojure. This library takes this description of a graphic and translates it into a specification for one of the supported underlying Javascript visualization libraries (currently vega-lite or plotly, via hanami). Another way to think about it is as the "grammar" part of a grammar of graphics; a way to declaratively transform an arbitrary dataset into a standardized set of instructions that a generic visualization library can turn into a graphic. This is the first half of what we need for a pure Clojure implementation of a grammar of graphics.

The second key piece we need is a Clojure implementation of the actual graphics rendering. Whether we adopt a similar underlying representation for the data as vega-lite, plotly, or whatever else is less consequential at this stage. Currently we just "translate" our Clojure code into vega-lite or plotly specs and call it a day. What I want to implement is a Clojure library that can take some data and turn it into a visualization. There are many ways to implement such a thing, all with different trade-offs, but Tim and Chouser's talk made me realize SVGs might be a great tool for the job. They're fast, efficient, simple to style and edit, plus they offer potentially the most promising avenues toward making graphics accessible and interactive since they're really just XML, which is semantic, supports ARIA labels, and is easy to work with in JS.

Humble UI also came up in a few conversations, which is a totally tangential concern, but it was interesting to start thinking about how all of this could come together into a really elegant, fully Clojure-based data visualization tool for people who don't write code.

### A Clojurey way of working with data 

I also had a super interesting conversation on my last night in Alexandria about Clojure's position in the broader data science ecosystem. It's fair to say that we have more or less achieved feature parity now for all the essential things a person working with data would need to do. Work is ongoing organizing these tools into a coherent and accessible stack (see [noj](https://scicloj.github.io/noj/)), but the pieces are all there.

The main insight I left with, though, was that we shouldn't be aiming for mere feature parity. It's important, but if you're a working data scientist doing everything you already do just with Clojure is only a very marginal improvement and presents a very high switching cost for potentially not enough payoff. In short, it's a tough sell to someone who's doesn't already have some prior reason to prefer Clojure.

What we should do is leverage Clojure's strengths to build tools that could leapfrog the existing solutions, rather than just providing better implementations of them. I.e. think about new ways to solve the fundamental problems in data science, rather than just offering better tools to work within the current dominant paradigm.

For example, a fundamental problem in science is reproducibility. The current ways data is prepared and managed in most data (and regular) science workflows is madness, and versioning is virtually non-existent. If you pick up any given scientific paper that does some sort of data analysis, the chances that you will be able to reproduce the results are near zero, let alone using the same tools the author used. If you do manage to, you will have had to use a different implementation than the authors, re-inventing wheels and reverse-engineering their thought process. The problem isn't that scientists are bad at working with data, it's the fundamental chaos of the underlying ecosystem that's impossible to fight.

If you've ever worked with Python code, you know that dependency management is a nightmare, never mind state management within a single program. Stateful objects are just a bad mental model for computing because they require us to hold more information in our heads in order to reason about a system than our brains can handle. And when your mental model for a small amount of local data is a stateful, mutable thing, the natural inclination is to scale that mental model to your entire system. Tracking data provenance, versions, and lineage at scale is impossible when you're thinking about your problem as one giant, mutable, interdependent pile of unorganized information.

Clojure allows for some really interesting ways of thinking about data that could offer novel solutions to problems like these, because we think of data as immutable and have the tools to make working with such data efficient. None of this is new. Somehow at this Conj between some really interesting talks focused on ways of working with immutable data and subsequent conversations it clicked for me, though. If we apply the same ways we think about data in the small, like in a given program, more broadly to an entire system or workflow, I think the benefits could be huge. It's basically implementing the ideas from [Rich Hickey's "Value of values"](https://www.youtube.com/watch?v=-6BsiVyC1kM) talk over 10 years ago to a modern data science workflow. 

Other problems that Clojure is well-placed to support are:
- Scalability -- Current dominant data science tools are slow and inefficient. People try to work around it by implementing libraries in C, Rust, Java, etc. and using them from e.g. Python, but this can only get you so far and adds even more brittleness and dependency management problems to the mix.
- Tracking data and model drift -- This problem has a very similar underlying cause as the reproducibility issue, also fundamentally caused by a faulty mental model of data models as mutation machines.
- Testing and validation -- Software engineering practices have not really permeated the data science community and as such most pipelines are fragile. Bringing a values-first and data-driven paradigm to pipeline development could make them much more robust and reliable.

Anyway I'm not exactly sure what any of this will look like as software yet, but I know it will be written in Clojure and I know it will be super cool. It's what I'm thinking about and experimenting with now. And I think the key point that thinking about higher-level problems and how Clojure can be applied to them is the right path toward introducing Clojure into the broader data science ecosystem.

### Software engineers as designers

Alex Miller's keynote was all about designing software and how they applied a process similar to the one described in [Rich Hickey's keynote from last year's conj](https://www.youtube.com/watch?v=c5QF2HjHLSE) to Clojure 1.12 (among other things). The main thing I took away from it was that the best use of an experienced software engineer's time is not programming. I've had the good fortune of working with a lot of really productive teams over the years, and this talk made me realize that one thing the best ones all had in common is that at least a couple of people with a lot of experience were not in the weeds writing code all the time. Conversely a common thread between all of the worst teams I've been a part of is that team leads and managers were way too in the weeds, worrying too much about implementation details and not enough about what was being implemented.

I've come to believe that it's not possible to reason about systems at both levels simultaneously. My brain at least just can't handle both the intense attention to detail and very concrete, specific steps required to write software that actually works and the abstract, general conceptual type of thinking that's required to build systems that work. The same person can do both things at different times, but not at the same time, and the cost of switching between both contexts is high.

Following the process described by Rich and then Alex is a really great way to add structure and coherence to what can otherwise come across as just "thinking", but it requires that we admit that writing code is not always the best use of our time, which is a hard sell. I think if we let experienced software engineers spend more time thinking and less time coding we'd end up with much better software, but this requires the industry to find better ways to measure productivity.


### Long version of personal updates

As most of you know or will have inferred by now, I got married in September! It was the best day ever and the subsequent vacation was wonderful, but it did more or less cancel my productivity for over a month. If you're into weddings or just want a glimpse into my personal life, we had a reel made of our wedding day that's available [here on instagram via our wedding coordinator](https://www.instagram.com/reel/C__5_r9pAea/).

Immediately after I got back from my honeymoon I also started a new job at BroadPeak, which is going great so far, but also means I have far less time than I used for open source and community work. I'm back to strictly evening and weekend availability, and sadly (or happily, depending how you see it) I'm at a stage of my life where not all of that is free time I can spend programming anymore.

I appreciate everyone's patience and understanding as I took these last couple of months to focus on life priorities outside of this work. I'm working on figuring out what my involvement in the community will look like going forward, but there are definitely tons of interesting things I want to work on. I'm looking forward to rounding out this year with some progress on at least some of them, but no doubt the end of December will come before I know it and there will be an infinite list of things left to do.

Thanks for reading all of this. As always, feel free to reach out anytime, and hope to see you around the Clojureverse :)  <br>  

---

## Nikita Prokopov  
Hi, this is Niki Tonsky and past two month we made a huge progress on Humble UI, culminating in Heart of Clojure workshop. Clojure Sublimed also saw some love. Enjoy!  

[Humble UI](https://github.com/HumbleUI/HumbleUI), Clojure Desktop UI framework:  
- Documentation is now part of Humble UI jar, can be opened from any app as a second window
- Lots of components were documented
- Declarative paints
- Support oklch color model
- More options for containers (column/row/grid)
- `hsplit` component
- Button styles
- Added user-facing `ui/measure` that only needs a component
- Tuned measurement/sizing caching, still a bit buggy
- Add bring-to-front to ui/window, cmd/ctrl+w to close docs, signal to ui namespace
- Convenience: default focusable on top level
- Convenience: allow ^{:stretch true} as a most common use case
- Fixed: with-bounds, draggable, vscrollable after resize,
- Fixed: crash on window close
- Demo: [humble-file-picker](https://github.com/HumbleUI/humble-file-picker/) demo as a separate application, with step-by-step progression
- Demo: new `Sand` sandbox
- Demo: new `Mirrors` demo, mirrored from Philippa Markovics  

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed), Clojure support for Sublime Text 4:  
- Support Clojure 1.12 array type annotations
- Pretty print selection #123
- Toggle Comment command that uses `#_` instead of `;;`
- Execute code from inside top-level `; ...` and `#_...` #124
- Handle eval of `#_` forms in nREPL JVM
- Better handle socket close
- Highlight namespace name as entity.name, same as defs
- Simplified formatting rules: if list's first form is a symbol, indent 2, in other cases, indent to opening paren
- Better handle selection after formatting with cljfmt
- Tests for indentation, cljfmt default config
- cljfmt correctly indents forms with custom rules
- Tuned color scheme a bit  

[Sublime Executor](https://github.com/tonsky/Sublime-Executor), executable runner for Sublime Text:  

- Fixed result_file_regex/result_line_regex, clear them on clear_output  

[Clerk](https://github.com/nextjournal/clerk/), Moldable Live Programming for Clojure:  

- [Better drop-in implementation of `clojure.walk`](https://github.com/nextjournal/clerk/blob/main/src/nextjournal/clerk/walk.cljc) (should make it into a library) <br>  


---

## Tommi Reiman  
My planned Open Source Time got postponed due to small family crisis, so no big releases.  

Had some time to help people, do PR reviews and small updates to [viesti](https://github.com/metosin/viesti), to be released later this year or early next.  

Reserved OS time for December, to `(╯°□°)╯︵ LIBS` out!  

### Something Else  

<img width="1728" alt="woods" src="https://gist.githubusercontent.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/1c2b8c5b41c858a3cf39676c804ba82c9b51b71e/woods.jpg">  

<br>

---

## Peter Taoussanis  
A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

\- [Peter Taoussanis](https://www.taoensso.com)  

### Recent work  
Hi folks! 👋 I'm a bit crunched for time atm, so will keep this update short for a change :-) Hope everyone's well!  

#### Telemere  

> [Telemere](https://www.taoensso.com/telemere) is a modern rewrite of [Timbre](https://www.taoensso.com/timbre) that offers an improved API to cover traditional logging, structured logging, tracing, basic performance measurement, and more.  

[v1.0.0-RC1](https://github.com/taoensso/telemere/releases/tag/v1.0.0-RC1) is now available! 🎉  

It's been a lot of work getting here, but I'm happy with the results. Big thanks to everyone that's been testing the (many) betas and giving valuable feedback! 🙏  

Have also recorded a new [lightning intro video](https://youtu.be/lOaZ0SgPVu4) that gives a 7-min tour of what Telemere is and roughly what it can do. If you've been curious but short on time, this might be a good way to get started.  

#### Timbre  

> [Timbre](https://www.taoensso.com/timbre) is a pure Clojure/Script logging library.  

[v6.6.0](https://github.com/taoensso/timbre/releases/tag/v6.6.0) was released, and [v6.6.1](https://github.com/taoensso/timbre/releases/tag/v6.6.1) shortly after.  

These mark the stable final release of the previous release candidate. Main highlight is Timbre's new out-the-box SLF4Jv2 support.  

#### Nippy  

> [Nippy](https://www.taoensso.com/nippy) is fast serialization library for Clojure.  

[v3.5.0-RC1](https://github.com/taoensso/nippy/releases/tag/v3.5.0-RC1) is out now, which updates dependencies and adds read support for some new primitive array types that'll follow shortly in a v3.6.  

#### Sente  

> [Sente](https://www.taoensso.com/sente) is a realtime web comms library for Clojure/Script  

[v1.20.0-RC1](https://github.com/taoensso/sente/releases/tag/v1.20.0-RC1) is out now, and includes an experimental new Jetty adapter and a number of internal improvements.  

### Upcoming work  

Can't believe it's almost the end of the year! It's been a productive one, and I'd like to focus on wrapping up a few dangling ends before the year's out.  

Plans include:

- [Telemere](https://www.taoensso.com/telemere) v1 final before year's end.
- [Tempel](https://www.taoensso.com/tempel) v1 final before year's end.
- [Tufte](https://www.taoensso.com/tufte) v3 pre-release (*hopefully*) before year's end. Have actually already invested quite a bit of time in this - but it's turned out to be a bigger job than expected, and I want to get it right.  

Also hoping to find some time for some [http-kit](https://www.taoensso.com/http-kit) maintenance, and I have a couple talks I'd like to record at some point - though those'll probably need to wait until next year.  

On a background thread, I'm continuing to make progress on [Carmine](https://www.taoensso.com/carmine) v4. Just recently deployed my first production code using v4's alpha branch, so that'll be a good opportunity for testing.  

Thanks everyone! Cheers 👋  


