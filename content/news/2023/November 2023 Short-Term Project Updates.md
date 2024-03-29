---
title: "November 2023 Short-Term Project Updates"
date: 2023-12-06T08:30:00+08:00
summary: "The latest from Biff, Bosquet, Clj-kondo, Deps-try,GDL,Quil, Uncomplicate "
author: Kathy Davis


---  

Check out the latest updates from Q2 and Q3 2023 Short Term Project developers. Thanks to members for your support - making this work possible! 

[Biff: Jacob O’Bryant](#biff-jacob-obryant)  
[Bosquet: Žygis Medelis](#bosquet-žygis-medelis)  
[Clj-kondo, babashka, cherry, SCI: Michiel Borkent](#clj-kondo-babashka-cherry-sci-michiel-borkent)   
[Deps-try: Gert Goet](#deps-try-gert-goet)  
[GDL: Michael Sappler](#gdl-michael-sappler)  
[Quil: Jack Rusher and Charles Comstock](#quil-jack-rusher-and-charles-comstock)  
[Uncomplicate: Dragan Duric](#uncomplicate-dragan-duric)  

## Biff: Jacob O’Bryant  
**2023 Q3 Report 2. Published 30 November 2023.**  

Since the [first update](https://www.clojuriststogether.org/news/october-2023-project-updates/#biff-jacob-o'bryant), I have completed:  
- wrote the second half of [XTDB compared to other databases](https://biffweb.com/p/xtdb-compared-to-other-databases).  
- published another essay, [Philosophy of Biff](https://biffweb.com/p/philosophy-of-biff/).
- added a [Should you use Biff?](https://biffweb.com/docs/get-started/should-you-use-biff/) page to the Get Started docs.  
- published two how-to guides (including fully working example code): [How to use Postgres with
  Biff](https://biffweb.com/p/how-to-use-postgres-with-biff/) and [How to use re-frame with
  Biff](https://biffweb.com/p/how-to-use-re-frame-with-biff).  
- finished [the example code](https://github.com/jacobobryant/biff-docker2) for a future "how to deploy Biff with Docker" guide. That repo includes code for building an uberjar + Docker container, and I've successfully deployed it to Fly.io and DigitalOcean App Platform.    

On the last point: I was going to write up a quick accompanying how-to post to go along with that Biff-Docker example repo. However, I don't really feel comfortable recommending Fly.io or DO App Platform at this stage. Fly.io had some weird, show-stopping networking errors when I tried to use it just recently. DO App Platform worked, but their UI around deployment/logs was clunky and didn't inspire confidence (I did also encounter at least one small bug). I am extremely wary of unreliable deployments which have been a pain point for me in the past. If you're small enough to consider using either of those platforms, I think you're far better off going with plain VMs (e.g. DigitalOcean droplets), which
is why that's the default in Biff.  

IMO Docker-based deployment is a much better fit for organizations that are to the point where using
AWS/Azure/GCP/Kubernetes makes sense. As such, my plan is to get that example repo deployed on Kubernetes (DigitalOcean's managed offering, specifically) and then write a how-to guide to go along with that.  

I will say that I am excited about Fly.io. Of all the "next-gen Heroku" platforms I'm aware of (Fly, Render, Railway, DO App Platform), Fly is IMO the most promising. Making it easy to run any Docker-based app at the edge (instead of e.g. just Javascript apps) is a particularly good fit for apps that use htmx (like Biff apps, by default). I'm guessing that the issues I've experienced with Fly are due to them [still having lots of growth and having trouble keeping up with it](https://community.fly.io/t/reliability-its-not-great/11253), which seems like not the worst problem you could
have! After they've become as reliable as DO droplets, I think there's a good chance I'll have Biff use them as the default deployment option.  

Anyway: thank you for the grant! I'm really happy with where Biff's documentation is now--it solidly covers the [four main areas](https://documentation.divio.com/) of tutorials, reference, how-to guides and explanation (essays). I've also been receiving some really kind feedback from the community as I've been publishing these articles ([for example](https://www.reddit.com/r/Clojure/comments/17qqnav/philosophy_of_biff).<br>

---


## Bosquet: Žygis Medelis  
**2023 Q3 Report 2. Published 30 October 2023.**   
1. Settle on Clerk-generated static content, published on GH Pages as the home for Bosquet documentation.
2. [Documentation](https://zmedelis.github.io/bosquet/):
   1. Getting Started guide
   2. Configuration. Bosquet relies heavily on declaring how AI generation is executed. The parameter setting is getting quite rich.
   3. Use cases. Generating interpretable code for math calculations
   4. Papers. Implementation of various academic papers discussing LLM techniques.
   5. Guide on how to use Short-term memory
3. Substantial time was spent on a dead end trying to work out local embedding vector generation. The rationale was to have a self-contained system offering embeddings. **Deeplearning4j** and **StandfordNLP** Java libs proved to be too opaque, with no good implementations for this task.
4. Support for *OpenAI* **embeddings** added to Bosquet. Now library users can create and use OAI embeddings as part of memory-driven workflows.
5. To store and use Embeddings we need **Vector DB**. I have settled on using [Qdrant](https://qdrant.tech/) as the database of choice. The implementation is still to be tested and changed with more rigorous testing and possibly various LLM papers implementations (see 'Papers' in documentation)
6. With that done, two types of memory are now introduced
   1. **Short-term memory** acts as a simple illustration of memory and retrieval concepts
   2. **Long-term memory** using Qdrant and OAI embeddings (as noted above likely to evolve and change). Follow `:memory/long-term-embeddings` in `system.edn` to see how it is built.
7. To aid in embeddingless (simple memory) scenarios, I have added **similarity metrics** support to compare different texts. It is based on [Apache Commons](https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/package-summary.html) text package.
8. The **caching** of generation results is reintroduced. Now LLM requests can be fulfilled from the cache if prompt and parameters do not change. Great to boost development speed development and save on some costs.
9. **Output coercion**. To achieve more complex chained prompt executions (or in agent use cases) where outputs the output from one LLM step needs to feed into the other we need well-defined in and out formats. Bosquet introduced the coercing of LLM outputs into *JSON* and *EDN* formats. See [Chain of Density](https://zmedelis.github.io/bosquet/notebook/papers/chain_of_density) paper implementation for details.  



#### 2023 Q3 Report 3. Published 30 November 2023    
* [Hugging Face data set loader](https://github.com/zmedelis/hfds-clj). For the work related to memory and embedding handling, I needed easy access to HF datasets. An implementation used to download and cache them was initially done in Bosquet but then moved to an independent small lib.  
* [Text splitters](https://zmedelis.github.io/bosquet/notebook/text_splitting). Building RAGs or dealing with long texts requires splitting them into chunks. Bosquet text splitting API supports choosing splitting units: sentence, token, and character. API allows specifying overlap between chunks counted in selected text units.  
* [Document loaders](https://zmedelis.github.io/bosquet/notebook/document_loading/index.html). PDF, DOC any other sane format loading into LLM processing is now supported via the Apache Tika wrapping component.  
* [Experimental evaluation](https://github.com/zmedelis/bosquet/blob/main/notebook/papers/llms_as_optimizers.clj). More complex or agent-based LLM processing needs automatic evaluation as a function guiding generation quality and direction. This is a very basic Q&A evaluation implementation. Example use is documented in the linked 'LLMs as Optimizers' paper implementation.  
* **Long-term memory**. Qdrant and OAI embeddings-based memory implementation. Long-term memory is configured as a Bosquet component and LLM apps or agents can use it to create embeddings, store, and query them. See Evaluation and Scicloj recording for example use.  
* [Local LLM support](https://zmedelis.github.io/bosquet/notebook/using_llms/index.html). Bosquet supports calling models hosted locally via [LM Studio](https://lmstudio.ai).  
* [Scicloj Meetup 6](https://www.youtube.com/watch?v=2JGQt9iW6dk): 'LLMs as Optimizers' (OPRO) implementation with Bosquet  
* Bug fixing component initialization and error handling  <br>

---



## Clj-kondo, babashka, cherry, SCI: Michiel Borkent  
**2023 Q2 Report 3. Published 31 October and 30 November 2023.**    
To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work possible! Open the details section for more info.  

**Top sponsors:**    
* [Clojurists Together](https://clojuriststogether.org)  
* [Roam Research](https://roamresearch.com)  
* [Nextjournal](https://nextjournal.com)  
* [Toyokumo](https://toyokumo.co.jp)  
* [Cognitect](https://www.cognitect.com)  
* [Kepler16](https://kepler16.com)  
* [Pitch](https://github.com/pitch-io)  

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  

* [Github Sponsors](https://github.com/sponsors/borkdude)  
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective  
* [Ko-fi](https://ko-fi.com/borkdude)  
* [Patreon](https://www.patreon.com/borkdude)  
* [Clojurists Together](https://www.clojuriststogether.org)  

If you're used to sponsoring through some other means which isn't listed above, please get in touch.  

#### [October 2023 Update](https://blog.michielborkent.nl/oss-updates-oct-2023.html)  
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.  
    * [Support self-contained binaries as uberjars!](https://github.com/babashka/babashka/wiki/Self-contained-executable#uberjar)  
    * Add `java.security.KeyFactory`, `java.security.spec.PKCS8EncodedKeySpec`, `java.net.URISyntaxException`  
    * Fix babashka.process/exec wrt `babashka.process/*defaults*`  
    * [#1632](https://github.com/babashka/babashka/issues/1632): Partial fix for `(.readPassword (System/console))`  
    * Enable producing self-contained binaries using [uberjars](https://github.com/babashka/babashka/wiki/Self-contained-executable#uberjar)  
    * Bump httpkit to `2.8.0-beta3` (fixes GraalVM issue with virtual threads)  
    * Bump `deps.clj` and `fs`  
    * Expose `taoensso.timbre.appenders.core`   
    * nREPL: implement `ns-list` op  
    * SCI: optimize `swap!`, `deref` and `reset!` for normal atoms (rather than user-created `IAtom`s)  
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.  
    * A configuration for [hoplon](https://github.com/hoplon/hoplon) and [javelin](https://github.com/hoplon/javelin) was added. You can play around with hoplon in a SCI-enabled environment  [here](https://babashka.org/sci.configs/?gist=e83da19df3d2739861334171779f79d5)
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.  
    * [#2207](https://github.com/clj-kondo/clj-kondo/issues/2207): New `:condition-always-true` linter, see [docs]( https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md)  
    * [#2013](https://github.com/clj-kondo/clj-kondo/issues/2013): Fix NPE and similar errors when linting an import with an illegal token \  
Published a new version (2023.10.20) with these changes:  
    * [#1804](https://github.com/clj-kondo/clj-kondo/issues/1804): new linter `:self-requiring-namespace`
    * [#2065](https://github.com/clj-kondo/clj-kondo/issues/2065): new linter `:equals-false`, counterpart of `:equals-true` ([@svdo](https://github.com/svdo))
    * [#2199](https://github.com/clj-kondo/clj-kondo/issues/2199): add `:syntax` check for var names starting or ending with dot (reserved by Clojure)
    * [#2179](https://github.com/clj-kondo/clj-kondo/issues/2179): consider alias-as-object usage in CLJS for :unused-alias linter  
    * [#2183](https://github.com/clj-kondo/clj-kondo/issues/2183): respect `:level` in `:discouraged-var` config  
    * [#2184](https://github.com/clj-kondo/clj-kondo/issues/2184): Add missing documentation for `:single-logical-operand` linter  [@wtfleming](https://github.com/wtfleming)
    * [#2187](https://github.com/clj-kondo/clj-kondo/issues/2187): Fix type annotation of argument of `clojure.core/parse-uuid` from `nilable/string` to string ([@dbunin](https://github.com/dbunin) 
    * [#2192](https://github.com/clj-kondo/clj-kondo/issues/2192): Support `:end-row` and `:end-col` in `:pattern` output format ([@joshgelbard](https://github.com/joshgelbard)  
    * [#2182](https://github.com/clj-kondo/clj-kondo/issues/2182): Namespace local configuration does not silence `:missing-else-branch`  
    * [#2186](https://github.com/clj-kondo/clj-kondo/issues/2186): Improve warning when `--copy-configs` is enabled but no config dir exists  
    * [#2190](https://github.com/clj-kondo/clj-kondo/issues/2190): false positive with `:unused-alias` and namespaced map  
    * [#2200](https://github.com/clj-kondo/clj-kondo/issues/2200): include optional `:callstack` in analysis  
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler \
Lots of stuff happened in October with squint!  
    * [#350](https://github.com/squint-cljs/squint/issues/350): `js*` should default to `:context :expr`  
    * [#352](https://github.com/squint-cljs/squint/issues/352): fix `zero?` in return position  
    * Add `NaN?` ([@sher](https://github.com/sher)  
    * [#347](https://github.com/squint-cljs/squint/issues/347): Add `:pre` and `:post` support in `fn`  
    * Add `number?`  
    * Support `docstring` in `def`  
    * Handle multipe source `:paths` in a more robust fashion  
    * [#344](https://github.com/squint-cljs/squint/issues/344): macros can't be used via aliases  
    * Add `squint.edn` support, see [docs](https://github.com/squint-cljs/squint/tree/main/README.md#squintedn)  
    * Add `watch` subcommand to watch `:paths` from `squint.edn`  
    * Make generated `let` variable names in JS more deterministic, which helps hot reloading in React  
    * Added a [vite + react example project](https://github.com/squint-cljs/squint/blob/main/examples/vite-react).  
    * Resolve symbolic namespaces `(:require [foo.bar])` from `:paths`
    * Add `bit-and` and `bit-or`  
    * Include `lib/squint.core.umd.js` which defines a global `squint.core`   object (easy to use in browsers, see [docs](https://github.com/squint-cljs/squint/blob/main/README.md#compile-on-a-server-use-in-a-browser))  
    * Add `subs`, `fn?`, `re-seq`  
    * Add `squint.edn` with `:paths` to resolve macros from (via `:require-macros`)  
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects Version 0.2.62 released  
    * Fix NPE during `neil dep upgrade`  
* [clojure-mode](https://github.com/nextjournal/clojure-mode)  
    * Porting this CLJS project such that it can run with [squint](https://github.com/squint-cljs/squint) also. You can now use this library directly from NPM as a JS library. See [this page](https://squint-cljs.github.io/squint/) for a demo on how to use it directly from a CDN! This work is funded by [Nextjournal](https://nextjournal.com/).  
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * Released version 0.1.9  
* [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
    * Fix self-requiring namespace (which clj-kondo now also catches via optional linter!)  
* [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo): a leiningen plugin for clj-kondo  
    * Bumped clj-kondo version  
* [http-kit](https://github.com/http-kit/http-kit): Simple, high-performance event-driven HTTP client+server for Clojure.  
    * [#543](https://github.com/http-kit/http-kit/issues/543) Migrate away from `SimpleDateFormat` to `java.time`, fixes native-image issue and virtual threads  
* [http-client](https://github.com/babashka/http-client): babashka's http-client
    * A number of small bugfixes and additions  
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs  
    * Expose `destructure` to scripts
    * Macroexpand `(.foo bar)` form in `macroexpand-1`
    * Optimize `deref`, `swap!`, `reset!` for host values
    * Add `time` macro
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * `sci.core` itself was exposed to nbb users  
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure  
    * Minor fixes in `glob` by [@eval](https://github.com/eval), thanks!  
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure  
    * Get home directory via environment variable rather than system property by [@DerGuteMoritz](https://github.com/DerGuteMoritz), thanks!  
* [babashka.nrepl](https://github.com/babashka/babashka.nrepl): The nREPL server from babashka as a library, so it can be used from other SCI-based CLIs  
    * Fix `classpath` op  
    * Implement `ns-list` op  


#### [November 2023 Update](https://blog.michielborkent.nl/oss-updates-nov-2023.html)  
#### Advent of Code  
It is Advent of Code time of year again. You can solve puzzles in an online [squint](https://github.com/squint-cljs/squint) or [cherry](https://github.com/squint-cljs/cherry) playground [here](https://squint-cljs.github.io/squint/examples/aoc/index.html).  

Change the `/squint/` part of the url to `/cherry/` to switch ClojureScript dialect versions.  

You can read more about the playground [here](https://blog.michielborkent.nl/squint-advent-of-code.html).  

#### November Updates  
* [blog](https://blog.michielborkent.nl/archive.html) I've written two blog posts this month:  
    * [Writing a Cloudflare worker with squint and bun](https://blog.michielborkent.nl/squint-cloudflare-bun.html)  
    * [Playing Advent of Code with Squint](https://blog.michielborkent.nl/squint-advent-of-code.html).  
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler \
Lots of stuff happened in November with squint! You could say that I've grown a little addicted to improving this project currently, driven by how users use it and also while developing the [playground](https://squint-cljs.github.io/squint/examples/aoc/index.html), a lot of potential improvements emerged.  

<details> 
<summary>Open for Squint Details</summary>  

* Restore backward compatibility with code that is compiled with older versions of squint  
* Optimize various outputs for smaller size  
* Add js-in  
* Support into + xform  
* Support sort on strings  
* #386: allow expression in value position in map literal  
* Improvements with respect to laziness in mapcat and concat  
* Do not array mutate argument in reverse  
* Escape JSX attribute vector value (and more)  
* map + transduce support  
* Fix for in REPL mode  
* Throw when object is not iterable in for  
* Make next lazy when input is lazy  
* Fix playground shim (fixes issue in older versions of Safari)  
* Add js-mod and quot  
* #380: Don't emit space in between #jsx tags  
* Add re-find  
* Add condp macro  
* Use compare as default compare function in sort (which fixes numerical sorting)  
* Allow assoc! to be called on arbitrary classes (regression)  
* Improve get to call get method when present.  
* Allow keywords and collections to be used as functions in HOFs  
* Make filter, etc aware of truthiness  
* Reduce code size for truthiness checks  
* Add str/split-lines  
* Add partition-by  
* Add parse-long  
* Add sort-by  
* Fix top level await  
* Support multiple dimensions in aset  
* Add coercive-= as alias for ==  
* Add js-delete  
* Fix min-key and max-key and improve tests  
* Add min-key and max-key  
* Fix defonce in REPL-mode  
* Fix doseq and for when binding name clashes with core var  
* Several REPL improvements  
* Improve https://squint-cljs.github.io/squint/  
* Allow alias name to be used as object in REPL mode    
* Copy resources when using squint compile or squint watch    
* Return map when select-keys is called with nil  
* nREPL server: print values through cljs.pprint (@PEZ)  
* Initial (incomplete!) nREPL server on Node.js: npx squint nrepl-server :port 1888  
* Update/refactor threejs example  
* #360: assoc-in! should not mutate objects in the middle if they already exist  
* Evaluate lazy-seq body just once  
* Avoid stackoverflow with min and max  
* #360: fix assoc-in! with immutable objects in the middle  
* Add mod, object?  
* Optimize get  
* Add threejs example  
* #357: fix version in help text  
* Fix iterating over objects  
* Add clojure.string's triml, trimr, replace  
* Fix examples/vite-react by adding public/index.html  
* Add find, bounded-count, boolean?, merge-with, meta, with-meta, int?, ex-message, ex-cause, ex-info  
* Fix munging of reserved symbols in function arguments  
</details>  

* [scittle-hoplon](https://jsfiddle.net/xbgj6v1q/1/): a custom scittle distribution with Hoplon. I helped developing the SCI configuration for Hoplon.  
* [gespensterfelder](https://squint-cljs.github.io/squint/examples/threejs/playground.html): a demo that Jack Rusher wrote using Three.js ported to squint.  
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects Version 0.2.63 released which adds mvn search and some bugfixes  
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Small bugfix around priority of `:exec-args` and `default`  
* [aoc-proxy](https://github.com/borkdude/aoc-proxy): a Cloudflare worker that can be used to fetch Advent of Code puzzle input from the browser (see [Advent of Code playground](https://squint-cljs.github.io/squint/examples/aoc/index.html)  
* [squint-macros](https://github.com/squint-cljs/squint-macros): a couple of macros that stand-in for [applied-science/js-interop](https://github.com/applied-science/js-interop) and [promesa](https://github.com/funcool/promesa) to make CLJS projects compatible with squint and/or cherry.  
* [clojure-mode](https://github.com/nextjournal/clojure-mode): Clojure/Script mode for CodeMirror 6.  
    * Ported the eval-region extension to squint so you can use it straight from JS. This is used in the [squint playground](https://squint-cljs.github.io/squint/?repl=true) when you press Cmd-Enter after an expression.  
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.  
    * A helper macro was improved such that you can define macros that are usable in SCI  
    * The re-frame configuration now has support for `re-frame.alpha`. See [playground](https://babashka.org/sci.configs/).  
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting. A new release: 1.3.186!  
<details> 
<summary> Open for Babashka Details</summary>  

* Support self-contained binaries as uberjars!  
* Add java.security.KeyFactory, java.security.spec.PKCS8EncodedKeySpec, java.net.URISyntaxException, javax.crypto.spec.  
* IvParameterSpec  
* Fix babashka.process/exec wrt babashka.process/*defaults*  
* #1632: Partial fix for (.readPassword (System/console))  
* Enable producing self-contained binaries using uberjars  
* Bump httpkit to 2.8.0-beta3 (fixes GraalVM issue with virtual threads)  
* Bump deps.clj and fs  
* Expose taoensso.timbre.appenders.core  
* nREPL: implement ns-list op  
* SCI: optimize swap!, deref and reset! for normal atoms (rather than user-created IAtoms)  
* Add test for #1639               
* Upgrade to GraalVM 21.0.1  
* **Still unreleased:**  
* Add java.util.ScheduledFuture   
* Support Runnable to be used without import   
* Allow catch to be used as var name    
</details>  


* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs \
Released version 0.8.41  
<details>  
<summary> Open for SCI Details</summary>  

* Bump edamame to 1.3.23  
* #889: allow (def foo/foo 1) when inside namespace foo  
* #891: reset file metadata on var when it's re-evaluated from other file  
* #893: expose sci.async/eval-form and sci.async/eval-form+  
* Improve sci.async/eval-string, respect top-level do forms  
* Add experimental new :static-methods option to override how static methods get evaluated.  
* Expose destructure  
* Macroexpand (.foo bar) form  
* Optimize deref, swap!, reset! for host values  
* Add time macro to core namespace  
* #896: allow catch to be used as var name  
</details>  

* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler  
    * Released version 0.1.10 which catches up with the latest compiler improvements in squint  
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.  
    * New `:condition-always-true` and `:underscore-in-namespace` linters + couple of bugfixes. Release expected in December.  

### Other projects  

These are (some of the) other projects I'm involved with but little to no activity happened in the past month.
Click for more [details]( https://github.com/borkdude/blog/discussions/categories/posts). Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).  
_Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) <br>

---


## Deps-try: Gert Goet  
**2023 Q3 Report 2. Published 4 December 2023.**    


I've been working on recipe functionality for deps-try (a tool to quickly try out Clojure libraries on rebel-readline), and I am happy to announce this functionality got released as deps-try v0.10.0.  

Recipes practically function as 'walkthroughs for the REPL' as steps get front-loaded in the REPL-history and a user can work their way through by submitting steps.  

As recipe namespaces list required dependencies and requires, they can also simply be used as a way to jumpstart a REPL-session to work on a particular domain using the --recipe-ns flag (e.g. connect to a PostgreSQL database having all necessary libraries loaded).  

I added some built-in recipes to get started (see below) and it was fun to explore writing tutorials in this format.  

There's more recipes in draft and a lot of ways I can see this functionality being extended. I'm also curious how others will use it and what recipes they come up with: if you have ideas for a recipe that others can benefit from, don't hesistate to open a PR.  

A big thanks to (everyone supporting) Clojurists Together 🙏!

Built-in recipes:
| NAME                         | TITLE                                                                                    |
| :------------------------------------------ | :-------------------------------------------------------------------------------------------------- |
| deps-try/recipes             | Introducing recipes                                                                            |
| malli/malli-select           | Introduction to malli-select, a library for spec2-inspired selection of Malli-schemas          |
| next-jdbc/intro-sqlite          | A next-jdbc introduction using SQLite                                                  |
| portal/intro                 | Introduction to portal, a Clojure tool to navigate data           
 |
 |_________________________|________________________________________________________________|
<br>
## GDL: Michael Sappler  
**2023 Q3 Report 2. Published 30 November 2023**     

I am taking an experimental approach to developing this game&engine by working bottom-up and fixing/evolving the code before focusing on new/more features.  

Also I want to create a simple GUI for editing the sounds/animations/properties of the game entities. This could lead to an action RPG-maker which can easily be modded or even a general 2D game maker tool.  

The main problems right now in the codebase (because it is basically a legacy codebase from a game I wrote more than 10 years ago during my studies) are lots of global state and hardcoded properties (sounds, images, animations, creature, projectile properties are all over the place and hard to change).  

I have focused on simplifying the lower level functions and moving side effects and dependencies to higher level code, thus making them easier to understand and read and not hiding side-effects like 'swap! entity ...' in them.  

### The engine [GDL](https://github.com/damn/gdl)  

In gdl I have investigated what changes would be required to remove the global states and dependencies between namespaces. For example in the gdl.audio/play function which receives a sound-file parameter string depends on asset-manager to get the sound asset.  

If I want to remove this dependency I found out I have to fix all the hard coded sound values in CDQ and move them to the properties.edn file, which I can then edit and view with the property-editor GUI.  

So it is quite interesting that refactoring the play-sound function would lead to such dramatic and interesting changes, also it lead me to find out that animations are in many cases played together with a sound and will be moved together into an audiovisual 'effect' property.  

### The game [Cyber Dungeon Quest](https://github.com/damn/Cyber-Dungeon-Quest)  

* Cleanup: Remove a lot of unused code/files
* Remove data-based hitpoints/mana effects and just directly change those entity values
* In data.val-max (functions for operating on health/mana/damage values) removed the complicated 'affect-val-max-stat!' function  
* Simplify deal-damage! swaps! (Use 1 instead of 3)  
* Simplify game.utils.counter (only work with maps, atom swaps! move to higher level code)  
* Add skill usable-state function for player info messages -
    - return exact keywords for skill usage so can display info messages/sounds later
    - also simplifies skills component by moving player handling more 'up'  
    - skill is  either on :cooldown, :not-enough-mana, :invalid-params, or :usable  
    - skill effect params are for example the skill requires a target chosen or a free space  

* game.entities.animation => game.entities.audiovisual  

* Move audiovisual effects into properties.edn<br>  

---


## Quil: Jack Rusher and Charles Comstock  
**2023 Q3 Report 2. Published 20 November 2023**    

In this cycle, we've made an official release of version 4.3.1323!
Here are the release highlights:  
* New automated test harness for assertions of Processing and P5js behaviors
* Clojar release automation using Github Actions to generate snapshot and tagged jar files
* Github Actions test harness for existing lein test (Linux)
* Fix for #cljs.core/abs compile warning
* Implement fract calculation
* Fix canvas resize and fullscreen behavior for p5js sketches
* Fix exception on exit when the applet has no window
* Added :mouse-buttons option to navigation-2d
* New calculation tests that don't require image generation
* Modernized quil-examples and tested them on Linux amd64/MacOS M1
* Added initial Clojure CLI documentation (was `lein`-only)
* Added the SCM block to pom + add a tag version if the release is not a SNAPSHOT build
* Pushed an updated version of the `lein` templates for new projects
  that uses this release. Currently, this is only for JVM projects
  because we don't yet have access to the right Clojars group for the
  CLJS template 🙈
* Triaged many old issues  

**Upcoming work:** 

* Continue to improve the test harness, try to get CI working for
  MacOS/M1
* Complete Clojure CLI docs for quil (both in the repo and wiki)
* Update interactive website and quil.info/api
* Quil deps-new templates for cljs and clj<br>  

---

## Uncomplicate: Dragan Duric  
**2023 Q3 Report 2. Published 31 October 2023**    

My goal with this round is to polish Uncomplicate libraries (mainly Neanderthal, Deep Diamond, ClojureCUDA, ClojureCL, ClojureCPP),
rather than develop new functionality.  

In this month, I've continued with Deep Diamond coding related to the port to JavaCPP. I've updated DNNL
to version 3.3, ported dense and RNN layers in the DNNL engine to (Clojure CPP) JavaCPP, updated tests
and made them pass. Then I ported the cuDNN GPU engine to Clojure CPP, and made all tensor,
directed and RNN tests pass. Along the way, I fixed many bugs caused by differences between how JavaCPP
and JCuda work. I updated Fluokitten support.

While working on Deep Diamond's port, I've discovered many opportunities to improve and polish Clojure CPP,
so my major efforts went toward understanding and fixing everything in Clojure CPP, so it's ready for
the first release. This includes complete documentation and test suite.

I've also cleaned up and polished new version of ClojureCUDA that is based on Clojure CPP. I completed
documentation and polished the test suite. I've cleaned up Neanderthal's port to Clojure CPP, worked
on some bugs/issues, and managed to polish the transition to Clojure CPP, and fixed the test suite..

Assorted bugfixes and improvements in all libraries. I've also identified places for further improvements and cleanups.

Although there's places for countless improvements in all libraries, the current progress in the short
term is:
- Commons: fairly ready for release and documented
- Clojure CPP: fairly ready for release and documented
- ClojureCUDA: fairly ready for release and documented
- Neanderthal: almost ready but needs more polishing, and documentation update
- Deep Diamond: needs more testing and bugfixing, and new documentation

In the third month, the majority of my efforts will go to Deep Diamond and Neanderthal.

The non-snapshot release will have to wait for the next release of JavaCPP, as now most Uncomplicate
projects use its dependencies, which I expect in the following
few months. Hopefully by the end of the year.



**2023 Q3 Report 3. Published 30 November 2023**     
My goal with this round was to polish Uncomplicate libraries (mainly Neanderthal, Deep Diamond, ClojureCUDA, ClojureCL, ClojureCPP), rather than develop new functionality.  

In the third month, the majority of my efforts went to Deep Diamond. I wrote documentation, fixed lots of bugs, updated deps to new versions, and wrote fluokitten implementation. I've resolved some outstanding issuses.
However, it still needs more polishing, especially the RNN part. In Neanderthal I discovered a bug in JavaCPP implementation (didn't find a culprit). I tidied up metadata and licenses.  

Although there's places for countless improvements in all libraries, the current progress in the short term is:  
- Commons: ready for release and documented  
- Clojure CPP:  ready for release and documented  
- ClojureCUDA: ready for release and documented  
- Fluokitten: ready for release  
- Neanderthal: ready for release  
- Deep Diamond: ready for release, but RNN regressed during the JavaCPP port, and I didn't manage to find the culprit.  

Since now ClojureCUDA, Neanderthal, and Deep Diamond depend on JavaCPP, and I used new JavaCPP features that are present only in snapshots, I have to wait for the next official release of JavaCPP 1.5.10 to be able to publish official jars to Clojars. Of course, everything is on GitHub, so whoever needs these new versions, can build them from the source. JavaCPP is usually released twice a year, so I expect it in December, or at least before the next spring.  

In short, here's the final state of what I've proposed to do:  
- DONE Do a thorough re-visiting of existing code in major Uncomplicate libraries.  
- DONE (to a fair extent) Read the code without rushing to implement new features.  
- DONE (not as much as I hoped) Write more tests for edge cases.  
- DONE (not all, of course) Discover bugs and fix them.  
- DONE (but not as much as I hoped) Discover places where code could be improved (without rushing the new functionality).  
- DONE Re-visit documentation and improve it to better match the current state of Uncomplicate libraries.  
- DONE If opportunities arise, implement some new functionality based of all the aforementioned items.  

In even shorter terms, I proposed to do some hammock time, combined with some un-exciting chores that enhance the stability and user-friendliness of Uncomplicate libraries. I completed a lot of chores, and lots of development of the loose ends from the previous round. I am satisfied how I managed to fulfill this for Clojure CPP, ClojureCUDA, Neanderthal, and Fluokitten, fairly satisfied with ClojureCL,
and almost satisfied with Deep Diamond (where I had to deal with changes in new DNNL and cuDNN versions while porting to JavaCPP, so I did not manage to iron out RNN).  
















