---
title: "Jan. and Feb. Project Updates for 2023 Long Term Projects"
date: 2023-03-24 T08:30:00+08:00
summary: Updates from Bozhidar Batsov, Michiel Borkent, Sean Corfield, Eric Dallo, Christophe Grand, Thomas Heller, Nikita Prokopov, Tommi Reiman, Peter Stromberg, and Peter Taoussanis
author: Kathy Davis
draft: true
---
Here are the first reports (Jan. and Feb. 2023) from our developers who are receiving annual funding for their 2023 projects. They have been busy! Thanks all for the great work!<br>
  
 


## 2023 Annual Funding: Bozhidar Batsov 
January/February 2023 Report

### CIDER
I've been mostly working on the next CIDER release, which I hope to release in March. Here's how things are shaping up so far:
### New features
- [#3314](https://github.com/clojure-emacs/cider/issues/3314): Detect `nrepl+unix` sockets (say via `lein nrepl :headless :socket nrepl.sock`).
- [#3262](https://github.com/clojure-emacs/cider/issues/3262): Add navigation functionality to `npfb` keys inside the data inspector's buffer.
- [#3310](https://github.com/clojure-emacs/cider/issues/3310): Add ability to use custom coordinates in jack-in-dependencies.
- [#766](https://github.com/clojure-emacs/cider-nrepl/issues/766): Complete local bindings for ClojureScript files.
- [#3179](https://github.com/clojure-emacs/cider/issues/3179): Introduce `cider-jack-in-universal` to support jacking-in without a project from a set of pre-configured Clojure project tools.
### Changes
- Allow using `npx nbb` as `cider-nbb-command`.
- [#3281](https://github.com/clojure-emacs/cider/pull/3281): Replace newline chars with actual newlines in `*cider-test-report*` buffer, for prettier error messages.
- Bump the injected `cider-nrepl` to 0.30.
- [#3219](https://github.com/clojure-emacs/cider/issues/3219): Disable by default forcing the display of output when the REPL prompt is at the first line of the of the REPL window. This behavior is desirable, but very slow and rarely needed. It can be re-enabled by setting `cider-repl-display-output-before-window-boundaries` to `t`.
I've also did a bunch of documentation improvements here and there. There were also a bunch of improvements to CIDER's test suite during this period. 


### inf-clojure
There has been one small improvement here recently:
* [#208](https://github.com/clojure-emacs/inf-clojure/pull/208) Display message after setting the current REPL.  
  
  
  
### nREPL
Things have been relatively quiet on this front. There's some ongoing work to improve the test suit and have it running on Windows.
I've also been in communication with David Miller to discuss the state of nREPL for ClojureCLR and the outstanding work there. The project is very close to being usable and it's current status is documented [here](https://github.com/clojure/clr.tools.nrepl#status). David could definitely use some help from people knowledgeable in CLR to drive it through the finish line!<br>
     
---
   
## 2023 Annual Funding: Michiel Borkent (January 2023)

In this post I'll give updates about open source I worked on during January 2023. 

**Sponsors:** But first off, I'd like to thank all the sponsors and contributors that make this work possible! Top sponsors:
* [Clojurists Together](https://clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Toyokumo](https://toyokumo.co.jp/)
* [Cognitect](https://www.cognitect.com/)
* [Kepler16](https://kepler16.com/)
* [Adgoji](https://www.adgoji.com/)

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Clojurists Togethe](https://www.clojuriststogether.org/)r
* [Patreon](https://www.patreon.com/borkdude)

If you're used to sponsoring through some other means which isn't listed above, please get in touch.

**Attention:**  If you are using Github Sponsors and are making payments via Paypal, please update to a credit card since Github Sponsors won't support Paypal from February 23rd 2023. Read their statement [here](https://github.blog/changelog/2023-01-23-github-sponsors-will-stop-supporting-paypal/). If you are not able to pay via a credit card, you can still sponsor me via one of the ways mentioned above.

### [Babashka](https://github.com/babashka/babashka) 

Native, fast starting Clojure interpreter for scripting. New releases in the past month: 1.0.170 - 1.1.173. Highlights: 
* Support for data_readers.clj(c)
* Include [http-client](https://github.com/babashka/http-client) as built-in library
* Compatibility with [clojure.tools.namespace.repl/refresh](https://github.com/clojure/tools.namespace)
* Compatibility with [clojure.java.classpath](https://github.com/clojure/java.classpath) (and other libraries which rely on java.class.path and RT/baseLoader)
* Compatibility with [eftest](https://github.com/weavejester/eftest) test runner (see demo)
* Compatibility with [cljfmt](https://github.com/weavejester/cljfmt)
* Support for *loaded-libs* and (loaded-libs)
* Support add-watch on vars (which adds compatibility with potemkin.namespaces)
* BREAKING: make printing of script results explicit with --prn

### Babashka compatibility in external libs

I contributed changes to the following libraries to make them compatible with babashka: 
* [cljfmt](https://github.com/weavejester/cljfmt) - A tool for formatting Clojure code
* [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
* [debux](https://github.com/philoskim/debux) - A trace-based debugging library for Clojure and ClojureScript

Check the [changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md) for all the changes!

### [Http-client](https://github.com/babashka/http-client)

The new babashka http-client project mostly replaces [babashka.curl](https://github.com/babashka/babashka.curl). This month the default client was improved to accept gzip and deflate as encodings by default, reflecting what babashka.curl did. Also babashka.http-client is now available as a built-in namespace in babashka v1.1.171 and higher.

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo) 
Static analyzer and linter for Clojure code that sparks joy
Three new releases with many fixes and improvements in the last month. [Check the changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details.
Some highlights:  
* [#1742](https://github.com/clj-kondo/clj-kondo/issues/1742): new linter :aliased-namespace-var-usage: warn on var usage from namespaces that were used with :as-alias. See [demo](https://twitter.com/borkdude/status/1613524896625340417/photo/1).
* [#1926](https://github.com/clj-kondo/clj-kondo/issues/1926): Add keyword analysis for EDN files. This means you can find references for keywords throughout your project with clojure-lsp, now including in EDN files.
* [#1902](https://github.com/clj-kondo/clj-kondo/issues/1902): provide :symbols analysis for navigation to symbols in quoted forms or EDN files. See [demo](https://twitter.com/borkdude/status/1612773780589355008).
The symbol analysis is used from clojure-lsp for which I provided a patch [here](https://github.com/borkdude/clojure-lsp/commit/f662adab1b17d5dbc3648d6d8208334dc920aa0e).

A new project around clj-kondo is [clj-kondo-bb](https://github.com/clj-kondo/clj-kondo-bb) which enables you to use clj-kondo from babashka scripts.<br>
Also [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo) got an update.

### [Instaparse-bb](https://github.com/babashka/instaparse-bb)  

This is a new project and gives you access to a subset of [instaparse](https://github.com/Engelberg/instaparse) via a [pod](https://github.com/babashka/pod-babashka-instaparse). Instaparse was request a few times to have as a library in babashka and instaparse-bb is a good first step, without making a decision on that yet. See the relevant discussion [here](https://github.com/babashka/babashka/discussions/1335).

### [Carve](https://github.com/borkdude/carve)

Remove unused Clojure vars. In the [0.3.5](https://github.com/borkdude/carve/blob/master/CHANGELOG.md#035) version, Carve got the following updates:
* Upgrade clj-kondo version
* Make babashka compatible by using the [clj-kondo-bb](https://github.com/clj-kondo/clj-kondo-bb) library
* Discontinue the carve binary in favor of invocation with babashka. Instead you can now install carve with [bbin](https://github.com/babashka/bbin):

```
 bbin install io.github.borkdude/carve
```

* Implement [babashka.cli](https://github.com/babashka/cli) integration
* Implement --help

### [Jet](https://github.com/borkdude/jet)  
CLI to transform between JSON, EDN, YAML and Transit using Clojure Version 0.4.23:  
* [#123](https://github.com/borkdude/jet/issues/123): Add base64/encode and base64/decode
* Add jet/paths and jet/when-pred
* Deprecate interactive mode
* Deprecate --query in favor of --thread-last, --thread-first or --func

### [Fs](https://github.com/babashka/fs)
File system utility library for Clojure. Fs has gotten a few new functions:
* unifixy, to turn a Windows path into a path with Unix-style pathseparators. Note that that style is supported by the JVM and this offers a morereliable way to e.g. match filenames via regexes.
* several xdg-*-home helper functions, contributed by [@eval](https://github.com/eval)<br>
See [changelog](https://github.com/babashka/fs/blob/master/CHANGELOG.md#changelog) for more details.

### [Neil](https://github.com/babashka/neil)
A CLI to add common aliases and features to deps.edn-based projects. his month there were several small fixes, one of them being to always pick stable versions when adding or upgrading libraries. See full [changelog](https://github.com/babashka/neil/blob/main/CHANGELOG.md) for details.

### [Quickblog](https://github.com/borkdude/quickblog)
Light-weight static blog engine for Clojure and babashka. The blog you're currently reading is made with quickblog. Version [0.2.3](https://github.com/borkdude/quickblog/blob/main/CHANGELOG.md#023-2023-01-30) was released with contributions from several people, mostly enabling you to tweak your own blog even more, while having good defaults. Instances of quickblog can be seen here:
* [Michiel Borkent's blog](https://blog.michielborkent.nl/)
* [Josh Glover's blog](https://jmglov.net/blog)
* [Jeremy Taylor's blog](https://jdt.me/strange-reflections.html)
* [Luc Engelen's blog](https://blog.cofx.nl/) ([source](https://github.com/cofx22/blog))
* [Rattlin.blog](https://rattlin.blog/)

If you are also using quickblog, please let me know!

A collection of ready to be used SCI configs for e.g. Reagent, Promesa, Re-frame and other projects that are used in nbb, joyride, scittle, etc. See recent [commits](https://github.com/babashka/sci.configs/commits/main) for what's been improved.

### [Edamame](https://github.com/borkdude/edamame)
Edamame got a new function: parse-next+string which returns the original string along with the parsed s-expression.

### [lein2deps](https://github.com/borkdude/lein2deps)
Lein to deps.edn converter. This tool can convert a project.edn file to a deps.edn file. It even supports Java compilation and evaluation of code within project.clj. There is now a lein plugin which enables you to sync your project.clj with your deps.edn every time you start lein. Several other minor enhancements were made. See [changelog](https://github.com/borkdude/lein2deps/blob/main/CHANGELOG.md).

### [4ever-clojure](https://github.com/oxalorg/4ever-clojure)
I added the ability to build and deploy 4ever-clojure to Github Actions. Every time a commit is merged, the site is automatically updated.

### Brief mentions  
The following projects also got updates, mostly in the form of maintenance and performance improvements. This post would get too long if I had to go into detail about them, so I'll briefly mention them in random order:
* [jna-native-image-sci](https://github.com/borkdude/jna-native-image-sci): Compile a program that uses JNA to native-image and allow dynamic evaluation using [SCI](https://github.com/babashka/sci)!
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
* [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
* [tools-deps-native](https://github.com/babashka/tools-deps-native): Run tools.deps as a native binary
* [tools.bbuild](https://github.com/babashka/tools.bbuild): Library of functions for building Clojure projects
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
* [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs

Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).. Published: 2023-02-05  Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)<br>
    
---
   
## 2023 Annual Funding: Michiel Borkent (February 2023)  

In this post I'll give updates about open source I worked on during February 2023.

### [Babashka](https://github.com/babashka/babashka)

Native, fast starting Clojure interpreter for scripting. New release: 1.2.174. Highlights:
* Use GraalVM 22.3.1 on JDK 19.0.2. This adds virtual thread support. See [demo](https://twitter.com/borkdude/status/1572222344684531717).
* Add more java.time and related classes with the goal of supporting [juxt.tick](https://github.com/juxt/tick) ([issue](https://github.com/juxt/tick/issues/86))<br>
See the complete [CHANGELOG](https://github.com/babashka/babashka/blob/master/CHANGELOG.md).

### Babashka compatibility in external libs

I worked together with the maintainers of the following libraries to make them compatible with babashka:
* [kaocha](https://github.com/lambdaisland/kaocha): test runner
* [multiformats](https://github.com/greglook/clj-multiformats): Clojure(Script) implementations of the self-describing multiformat specs

### [Http-client](https://github.com/babashka/http-client): Babashka's http-client
The babashka.http-client namespace mostly replaces [babashka.curl](https://github.com/babashka/babashka.curl).
This month support for :multipart uploads was added, mostly based on and inspired by [hato](https://github.com/gnarroway/hato)'s implementation.

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)<br>
Static analyzer and linter for Clojure code that sparks joy. New release: 2023.02.17. Some highlights:
* [#1976](https://github.com/clj-kondo/clj-kondo/issues/1976): warn about using multiple bindings after varargs (&) symbol in fn syntax
* Add arity checks for core def
* [#1954](https://github.com/clj-kondo/clj-kondo/issues/1954): new :uninitialized-var linter. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#uninitialized-var).
* [#1996](https://github.com/clj-kondo/clj-kondo/issues/1996): expose hooks-api/resolve. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/hooks.md#api).<br>
[Check the changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details.

### [SCI](https://github.com/babashka/sci)
Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs. This month:
* Adding JS libraries to a SCI context. See [docs](https://github.com/babashka/sci#javascript-libraries)
* Keyword arguments as map support for CLJS
* Making loading of libraries thread-safe in JVM
* Several fixes with respect to deftype and toString + equals

### [Fs](https://github.com/babashka/fs)  
File system utility library for Clojure. Highlights:
* several xdg-*-home helper functions, contributed by [@eval](https://github.com/eval)
* babashka.fs/zip now takes a :root option to elide a parent folder or folders.E.g. (fs/zip "src" {:root "src"}) will zip src/foo.clj into the zip file under foo.clj.
See [changelog](https://github.com/babashka/fs/blob/master/CHANGELOG.md#changelog) for more details.

### [Process](https://github.com/babashka/process)
Clojure library for shelling out / spawning sub-processes. This month I looked into wrapping output of processes with a prefix so when ran in parallel, you can easily distuingish them. A preliminary solution is in [this thread](https://github.com/babashka/process/discussions/102#discussioncomment-4903758).

### [Pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna)
Interact with clojure-lanterna from babashka. A very experimental 0.0.1 release was published. You can try it out by playing tetris in the console with babashka:
```
bb -Sdeps '{:deps {io.github.borkdude/console-tetris {:git/sha "2d3bee34ea93c84608c7cc5994ae70480b2df54c"}}}' -m tetris.core
```
### [Nbb](https://github.com/babashka/nbb)</strong></code>

Scripting in Clojure on Node.js using SCI. Finally nbb has gotten support for passing maps to keyword argument functions:
```
(defn foo [& {:keys [a b c]}])
(foo :a 1 :b 2 :c 3)
(foo {:a 1 :b 2 :c 3})
```  
  
Several other improvements have been made in the area of macros and resolving JS library references and resolving dependencies in an nbb.edn file, relative to an invoked script which is not in the current directory. See changelogs [here](https://github.com/babashka/nbb/blob/main/CHANGELOG.md).

### [Joyride](https://github.com/BetterThanTomorrow/joyride)
VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci)). This month I contributed a built-in version of [rewrite-clj](https://github.com/clj-commons/rewrite-clj) to joyride, so joyriders can rewrite their code from within VSCode.

### [Cljs-showcase](https://github.com/borkdude/cljs-showcase)*
Showcase CLJS libs using SCI. A little project to show how you can use SCI to showcare your CLJS library in an interactive way.

### Brief mentions
The following projects also got updates, mostly in the form of maintenance and performance improvements. This post would get too long if I had to go into detail about them, so I'll briefly mention them in random order:
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
* [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
* [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
* [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler

### Other projects
These are some of the other projects I'm involved with but little to no activity happened in the past month.
* [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
* [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
* [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
* [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs
* [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
* [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
* [instaparse-bb](https://github.com/babashka/instaparse-bb)
* [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).

Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).  Published: 2023-03-01   Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)<br>
  
---
    
## 2023 Annual Funding: Sean Corfield 
January/February 2023 Update<br>
:tags ["clojure" "clojure-doc.org" "open source" "tools.build" "community"]}

As part of [Clojurists Together's Long-Term Funding for 2023](https://www.clojuriststogether.org/news/clojurists-together-2023-long-term-funding-announcement/) I talked about working on [clojure-doc.org](https://clojure-doc.org) which I had resurrected a few years ago, as a GitHub Pages project, powered by [Cryogen](https://cryogenweb.org/).<!--more-->

It was originally created over a decade ago, intended as a community hub for general documentation related to Clojure that couldn't be found on
[clojure.org](https://clojure.org) and which wasn't bound by the [Clojure Contributor Agreement](https://clojure.org/dev/contributor_agreement).

A lot has changed since then. The Contributor Agreement has gone from a purely paper and "snail mail" process to an online e-form. The `clojure.org` website is now [on GitHub](https://github.com/clojure/clojure-site) and accepts Pull Requests (if you've e-signed the CA) -- and it has expanded massively,compared to the material it covered back then.<br>

`clojure-doc.org` itself grew a lot of ambitious content, including extensive guides to `clojure.java.jdbc` and `clojure.core.typed` that are now very dated.<br>  

In addition to cleaning up and modernizing `clojure-doc.org`, I also said that I wanted to help streamline the beginner experience around Clojure tooling, and I've started to talk with Alex Miller about possible avenues for that.  

My first couple of months have focused on reviewing the content on`clojure-doc.org` to establish what is still relevant and pruning outdated content, as well as streamlining my own open source projects around tooling.

### `build-clj` & `tools.build`

I was very pleased when [`tools.build`](https://github.com/clojure/tools.build) landed and I aggressively switched all my open source projects and my work
projects over to it.

I initially felt that there was a lot of boilerplate and duplication in the `build.clj` files I was creating and my initial reaction was to create a simple wrapper that provided "sane" defaults to make build files smaller and simpler. As people started using this wrapper, it began to develop more "knobs & dials" to make it more configurable -- and then I created [`deps-new`](https://github.com/seancorfield/deps-new) as a modern "replacement" for [`clj-new` (https://github.com/seancorfield/clj-new)(which in turn derived from `boot-new`, which derived from `lein new`), with the idea of supporting more declarative templates for projects. That led to more "knobs & dials" on my `tools.build` wrapper as the projects created by `deps-new` depended heavily on the wrapper.

In January, I stripped the wrapper out of all my open source projects
`build.clj` files as a way to make them a better example for beginners. I went on to strip the wrapper out of the usermanager example application](https://github.com/seancorfield/usermanager-example) that I regularly link beginners to, as a basic web application, and out of the project templates in [`deps-new`](https://github.com/seancorfield/deps-new) so that people creating projects via that will no longer use my wrapper.

The net result should be many more examples of how to use `tools.build`
directly and a normalization of how `build.clj` files should be written.
I think the [HoneySQL `build.clj`](https://github.com/seancorfield/honeysql/blob/develop/build.clj)
file in particular is a good example of how to run various tasks,
such as [`test-doc-blocks`](https://github.com/lread/test-doc-blocks)
and [Eastwood](https://github.com/jonase/eastwood), as well as running
multi-version testing, both Clojure and ClojureScript tests, and building
and deploying a library JAR to Clojars.

I plan to write a cookbook for `clojure-doc.org` that expands on the
[official `tools.build` guide](https://clojure.org/guides/tools_build),
covering the sorts of things I've found useful in both my open source
and work projects.

### `clojure-doc.org`

As noted above, `clojure.org` has grown dramatically since `clojure-doc.org`
was created and provides more up-to-date content in many areas, so I've
focused on pruning the duplicated and outdated content and linking to the
official site where up-to-date content now exists.

In particular, the following pages have been unlinked and removed from
the navigation, in favor of other sources:

* Books -- deferring to clojure.org
* Emacs & vim-fireplace -- the former was very outdated, and the latter is only one of several good vim/neovim plugins so deferring to other docs makes more sense
* Typed Clojure -- or `core.typed` as the old section was called, which was a decade old
* User Groups, including how to start/run a user group -- deferring to clojure.org

I've temporarily removed all the ecosystem and library pages from the navigation
to make the main sections easier to find, while I review and update them over
the next few months.

The following pages have been overhauled/updated:

* About -- which is also the README now
* Community -- several sections defer to new material on clojure.org
* Content -- a work in progress as the site evolves
* Editors -- a new overview page has been added with key links to other material

Nearly 60 pages have been updated to fix broken links.
[Changes so far this year!](https://github.com/clojure-doc/clojure-doc.github.io/compare/03d64232651eb6ca77630edca6059c0c70fa72be..source)

I welcome feedback on both the updated content and the site itself,
either via the [#clojure-doc channel on Slack](https://app.slack.com/client/T03RZGPFR/C02M6N5C137)
_(self-signup at [clojurians.net](http://clojurians.net))_ or via
[clojure-doc discussions on GitHub](https://github.com/clojure-doc/clojure-doc.github.io/discussions).
Feel free to open issues directly for errors on the site and of course
pull requests on the `source` branch are also welcome!

### What's Next?

In March/April, I plan to review and/or overhaul the Getting Started,
Introduction, and Web Development sections, with a focus on the latter.

### Additional Project Releases

In addition to changing all my projects to use plain `tools.build` instead
of my wrapper for it,
[HoneySQL](https://github.com/seancorfield/honeysql)
2.x has had four releases in January/February,
that have mostly focused on improving documentation and also compatibility
with 1.x to make it easier for folks to migrate.<br>  
  
---
    
## 2023 Annual Funding: Eric Dallo  
January/February 2023 Report
### [jet.el](https://github.com/ericdallo/jet.el)

New opensource project!

jet.el is a Emacs package that uses [jet](https://github.com/borkdude/jet), offering, via a smooth magit-like UI, multiple ways to quickly convert between edn/json/transit/yaml.

Parse edn/json/transit/yaml directly from Emacs using a smooth magit-like interface.
Besides the main entrypoint, the package has lots of commands to parse and:
- copy to clipboard
- to a new buffer
- print to messages
- just paste at cursor

Available at MELPA!
![](jet.gif)

### [clojure-lsp](https://clojure-lsp.io/)
Clojure-lsp is a tool that can be used both in your editor like Calva, Emacs, Vim and others, and as a command line tool, mostly as a linter for formatting, cleaning and finding diagnostics for a whole project.

There were some improvements on the documentation regarding what and how clojure-lsp can be used in outside your editor [here](https://clojure-lsp.io/api/what-is-it/).

### Release 2023.01.26-11.08.16
The most important features of this release were the find definition of symbols and the edn file support for both editor and lint via CLI, improving the IDE experience, being able to find the definition and references of keywords and symbols, besides that, there was a lot of fixes, performance improvements, and new settings for more projects support.

![](clojure-lsp-edn-keyword.gif)

#### Changelog 
**General**
  - Bump clj-kondo to `2023.01.20`
  - Avoid false-positives of unused-public-var for functions used in :gen-class that starts with `-` as convention. #1443
  - New jar available on releases: `clojure-lsp-server.jar`, an Uberjar not aot-ed containing both clojure-lsp core + server code, improving startup time for JVM clients, for example for `lein-clojure-lsp`. Any lib using `clojure-lsp-standalone.jar` should consider switch to this new jar.
  - Change `:source-paths-ignore-regex` default value to `["target.*"]`, removing resource folders. #1452
  - Bump cljfmt to 0.9.2
  - Bump lsp4clj to 1.7.0
  - Support `:style/indent` metadata for indentation with cljfmt #1420

**Editor**
  - Fix add missing import code action when there are multiple options. #1422
  - Only show completion items if no changed code is being processed, avoiding wrong completion items. #1425
  - Improve semantic tokens for defprotocol, definterface coloring as `interface` tokens.
  - Include reffered vars in completion. #1440
  - Allow rename alias of function calls, renaming properly both function usages and alias definition.
  - Add support for keyword analysis in edn files, allowing find-references, document highlight and other lsp features to keywords.
  - find-definition and references for quoted symbols like `'clojure.core/inc` and symbols mentioned in `.edn` files. #1446
  - Show error messages when can't apply edits after commands. #1448

**API/CLI**
  - Make diagnostics command print as json or edn. #1419
  - Make possible starting a chan server with clojure-lsp components, useful to other clients extend.
  - Consider edn files when linting project, allowing to format edn files. #1452
  - Report diagnostics lines and columns as 1 based, not 0 like LSP, following clj-kondo standard. #1459


### Release 2023.02.27-13.12.12 
Mostly improvement on completion performance, some improvements on the __hover__ feature, and fixes.

#### Changelog
**General**
  - Add new setting `:paths-ignore-regex` to allow ignore analyzing/linting specific paths, useful if you have folders or files that are not relevant to be linted and are huge which may affect performance.
  - Bump clj-kondo. Fixes missing lint class constructors, making clojure-lsp show add import code action always.
  - Fix wrong ns rename for cljc files. #1508

**Editor**
  - Fix cache issue: wrong external dependencies analysis when running clojure-lsp in editor after running on CLI, affecting navigation. #1473
  - Bump lsp4clj fixing progress notifications during initialization for Calva.
  - Allow go to definition of namespace even when the var is not known. Ex: `clojure.string/foo` will go to the definition of `clojure.string`. This is useful for cases where the var was not created yet but user wants to go to the ns to check the available functions or check the correct name of the function.
  - Avoid basing results on old analysis.
  - Add new setting `:completion :analysis-type` to choose between `:fast-but-stale`(default) or `:slow-but-accurate`, this should define whether completion should wait for changes that may still happening, this by default reverts the behavior introduced after #1425. #1487
  - Fix `textDocument/hover` issue when doc metadata isn't a string literal.
  - Follow references to other vars in doc metadata for use in `textDocument/hover`.
  - Support `:arglists` meta when finding docs of functions via hover feature. #1504
  - Enhance hover to search clojuredocs on CLJS. #1506

### [clj-kondo](https://github.com/clj-kondo/clj-kondo/)
Worked on analysis stuff regarding edn support for clojure-lsp.<br>
  
  ---
  
## 2023 Annual Funding: Christophe Grand 
January 2023 Report

This month we merged our changes for a better hot reload (see Update #2) and then went on bugfixing and doing small improvements for a while.<br>  

A problem kept bothering us: in our `cljd.flutter` (well in January it was still `cljd.flutter.alpha2`) we have `:watch` to react to updates to all kind of stateful objects and `sub` to narrow the scope of changes we are interested in for a given "watchable". This is very useful as it allows to change the frequency at which a piece of UI is updated: one can have a big atom holding a lot of state (and this changing frequently) and watch a slower changing subset of it.<br>  
  
However `sub` only allowed to narrow *one* watchable. This led to awkward code were every other watchables (IO or framework related) updates had to be shoe-horned in a single canonical atom with the help of listeners (whose lifecycles have to be tied to specific parts of the widget tree) and hair-pulling naming decisions on paths inside this atom.<br>  
  
We first extended `sub` to allow to apply a function across several watchables latest values, somehow behaving like a join.<br>  
    
It worked but felt quite stiff and not very pleasing to use. `sub` relied on the `f & args` update convention (as used in `swap!`, `update-in` etc.) but there's no agreed upon convention for when one has several input and thus it doesn't mesh as well as the single-input version with the core lib.<br>  
  
This dissatisfaction led us to develop "cells". Cells as in spreadsheets. So cells are expressions which recompute their value when their dependencies values change. Obviously we made cells watchable. A cell is defined by using the `$` macro (because it caches its latest value) and inside (dynamically, not lexically) a cell one can take (`&lt;!`) from other cell *or any other watchable*.<br>  
  
Example: assuming `now` is an atom (a plain old one) updated at 60fps, then `($ (.-day (&lt;! now)))` is a cell recomputed at 60fps but yielding a new value once a day. So any widget (or other cell) depending on this cell will only be updated once a day. <br>  
  
This generalizes nicely to multiple dependencies and even to dynamic dependency graphs. Another interesting fact to know about cells is that a cell is recomputed only when it's watched (and this cascades transitively: an unwatched cell doesn't watch its dependencies...).<br>  
  
Another consequence of cells is that the push for a single big canonical atom goes away.  
  
This changed positively the way we write apps. So much that in our February workshop we talked only about cells, not subs.

[New boost](https://3.basecamp.com/4323235/buckets/29639833/boosts/new?boost%5Bboostable_gid%5D=Z2lkOi8vYmMzL1JlY29yZGluZy81OTIxMTQ0MDY0) <br>
  
---
  
## 2023 Annual Funding: Thomas Heller 
January/February 2023 Report

### shadow-cljs 
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).
Current shadow-cljs version: 2.21.0 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  


**Notable Updates**
* Fixed an issue related to `reify`, that could happen when `:advanced` optimizing multiple modules. Running into this had rather confusing and hard to debug errors, so I'm glad this is gone. [CLJS-3207](https://clojure.atlassian.net/browse/CLJS-3207) is the official issue for this and I hope to make a patch for that at some point
* Fixed build reports not properly grouping some npm packages.
* Fixed a react-native bad default, since RN apparently only supports ES5 via the now default Hermes engine. `:compiler-options {:output-feature-set :es5}` is now the default for `:target :react-native` builds.
* Fixed an externs inference issue, which greatly improved externs inference warning coverage. Previously many places would not warn about possible externs issues, and potentially producing bad code in `:advanced` due to missing externs.<br>
  
---
  
## 2023 Annual Funding: Nikita Prokopov

January and February have been two months dedicated to Clojure Sublimed, my Sublime Text plugin for Clojure development.

I wasn't happy how REPL implementation was coupled with syntax highlighting, so I decided to decouple them. That lead to a lot of yak shaving, but also to a much better code base (not in a single file anymore!) and to an ability to add new REPLs much easier. I also added Socket REPL support (no need to load nREPL anymore!) and gave Shadow-CLJS some long deserved love.

Unfortunately, while everything is _almost_ ready, it just didn't make it to end-of-February deadline, so expect the proper release somewhere in the beginning of March. Brave people could already use it from master (I do). Also expect a huge blog posts with a lot of details on this adventure — it was very fun!

More detailed changelog:
### [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed)
- Decoupled REPLs from syntax highlighting
- Wrote Clojure parser in Python from scratch
- Formatting based on parser (10x speedup)
- Namespace and top-level form detection based on parser
- Client-side pretty-printer
- Split plugin code into modules
- Refactored code so that new REPLs could be added
- New REPL: Socket REPL
- New REPL: Raw nREPL
- Improved: Shadow-CLJS REPL

### [Sublime Executor](https://github.com/tonsky/Sublime-Executor):
- Redid process management based on Default/exec.py

### [Humble UI](https://github.com/HumbleUI/HumbleUI/):
- Played with button and card effects, see video

### [Skija](https://github.com/HumbleUI/Skija):
- Merged Linux-arm64 support

### [Sublime Color Schemes](https://github.com/tonsky/sublime-color-schemes)
- New repo!
- Just some fun themes I make for Sublime from time to time. I spent my last two months in Berkeley scheme from that repo.<br>
  
---
   
## 2023 Annual Funding:Tommi Reiman
January/February 2023 Report

Spend a lot of time with [Malli](https://github.com/metosin/malli) and [Reitit](https://github.com/metosin/reitit) for revisiting, priorising and designing the essential missing core features. 

For Malli, my top priority is to resolve [the derivation and declaration of effective types](https://github.com/metosin/malli/issues/264), which will simplify library internals, writing schema extensions and makes CLJS bundle size smaller. Might have to break the protocol-api for this. 

For Reitit, drafting a proper plugin system, have studied how this is solved in other ecosystems (e.g. [fastify](https://www.fastify.io/), [phoenix](https://www.phoenixframework.org/)). Reitit is powerful, proper plugin system makes it much more approachable.

Have also reviews lot's of PRs, helped people online and pushed out some smaller features and release the following:

### Malli
#### 0.10.1 (2023-01-21)
* Strip-extra-keys should not break on non-map values [#818](https://github.com/metosin/malli/pull/818)

#### 0.10.0 (2023-01-12)
* New optional time-schemas for the JVM on top of `java.time`: 
  * `:time/duration`, `:time/instant`, `:time/local-date`, `:time/local-date-time`, `:time/local-time`, `:time/offset-date-time`, `:time/offset-time`, `:time/zone-id`, `:time/zone-offset`, `:time/zoned-date-time`, see [README](README.md#malliexperimentaltime)
* automatic type inferring with `:enum` and `:=` with `malli.transform` and `malli.json-schema` - detects homogenous `:string`, `:keyword`, `:symbol`, `:int` and `:double`), [#782](https://github.com/metosin/malli/pull/782) & [#784](https://github.com/metosin/malli/pull/784)
* New `malli.core/coercer` and `malli.core/coerce` to both decode and validate a value, see [Docs](README.md#coercion)
* New `malli.core/-no-op-transformer`
* **BREAKING**: new implemenation for `:map-of` inferring via `malli.provider/provide`:
  * Option `:malli.provider/map-of-threshold` default dropped (was 3)
  * New and configurable (`malli.provider/map-of-accept`) function of `stats -> boolean` for identifying `:map-of`
* **BREAKING**: Prefer to real Schemas instead of predicates in inferring (e.g. `:int` over `'int?`)
* Adds `:pred` option to `m/-map-schema` [#767](https://github.com/metosin/malli/pull/767)
* New `:some` schema (like `some?`)
* New `malli.experimental.describe` to describe Schemas in english:

```clojure
* (require '[malli.experimental.describe :as med])

(med/describe [:map {:closed true} [:x int?]])
; => "map where {:x -> <integer>} with no other keys"
```

### Reitit

#### 0.6.0 (2023-02-21)
* Add reitit-frontend support for fragment string [#581](https://github.com/metosin/reitit/pull/581)
* reloading-ring-handler [#584](https://github.com/metosin/reitit/pull/584)
* Remove redundant s/and [#552](https://github.com/metosin/reitit/pull/552)
* FIX: redirect-trailing-slash-handler strips query-params [#565](https://github.com/metosin/reitit/issues/565)
* **BREAKING**: Drop tests for Clojure 1.9, run tests with 1.10 & 1.11
* NEW option `:meta-merge` on a router for custom merge strategy on route data
* Swagger: support operationId in generated swagger json [#452](https://github.com/metosin/reitit/pull/452) & [#569](https://github.com/metosin/reitit/pull/569)
* Update documentation and link to the startrek project [#578](https://github.com/metosin/reitit/pull/578)
* Upgrade jackson for CVE-2022-42003 and CVE-2022-42004 [#577](https://github.com/metosin/reitit/pull/577)
* Improved coercion errors perf [#576](https://github.com/metosin/reitit/pull/576)
* Add example for Reitit + Pedestal + Malli coercion [#572](https://github.com/metosin/reitit/pull/572)
* Handle empty seq as empty string in query-string [#566](https://github.com/metosin/reitit/pull/566)
* Polish pedestal chains when printing context diffs [#557](https://github.com/metosin/reitit/pull/557)
* Updated dependencies:

```clojure
[metosin/ring-swagger-ui "4.15.5"] is available but we use "4.3.0"
[metosin/jsonista "0.3.7"] is available but we use "0.3.5"
[metosin/malli "0.10.1"] is available but we use "0.8.2"
[fipp "0.6.26"] is available but we use "0.6.25"
[ring/ring-core "1.9.6"] is available but we use "1.9.5"
[com.fasterxml.jackson.core/jackson-core "2.14.2"] is available but we use "2.14.1"
[com.fasterxml.jackson.core/jackson-databind "2.14.2"] is available but we use "2.14.1"
```

# Something else
Spring is coming!

<img width="1728" alt="spring-fi" src="https://user-images.githubusercontent.com/567532/221986452-83d562ab-c3f0-4362-9041-475df7486af9.png"
  
    
---
  
## 2023 Annual Funding: Peter Stromberg
My first two months of 2023 long term funding was spent in a typical mostly-Calva-someJoyride-and-some-other-stuff manner. 😀 Even though it was an unusual two months in ways I hope will stick, as well as in ways I hope we will see less of. 

### [Calva](https://github.com/BetterThanTomorrow/calva)
Calva is growing. This is wonderful and twofold: 1. More users: We are in a period of picking up many new users. 
🎉 It means we spend much more time on user support than we are used to. And that's coming from a situation where user support is the bulk of the time we spend on Calva. We gain many insights. What the beginner experience is like. Bugs we were not aware of are discovered. Use cases we didn't know existed look quite common. And so on. 
2. More contributions: With user growth, and the various new needs and problems that reveals, bring more contributors. * Many more issues are contributed. The Calva issues list has always had a slow growth. We never close issues as "won't fix", unless it is super clear that it is out of the project scope. The current issue growth is unusually high, though. * Moar PRs! Especially [Julien Vincent](https://github.com/julienvincent) and [Aleksei Ivanov](https://github.com/SillyCoon) have been busy. Julien has been fixing some big use cases he think were lacking. Aleksei has been focused on quality and fixing existing issues. Thanks! And also thanks to all contributors I am not even mentioning here. The growth has changed the characteristics of [Brandon Ringe](https://github.com/bpringe)'s and my work with Calva a bit. More time to support and learn from users and contributors, less time on adding features or fixing issues ourselves. Brandon has nonetheless spent considerable time on trying to figure out a tool-chain where we can write more of Calva in ClojureScript. This is a theme throughout Calva's short history. 😀 He has had some successes and some of the usual boring "this is really hard and strange" moments. 

There's a [separate repository for figuring this out and learning](https://github.com/BetterThanTomorrow/calva-cljs-testbed) before we start to try implement in the Calva project. We'll see if that ever happens. There are advantages with the current tool-chain. Brandon mentions these: 1. The official tooling and docs are geared toward TS, so there's no translating stuff or figuring out how to make stuff work with cljs, aside from what we've already put in place. 1. It's really important to have input and contributions from beginners to Clojure to make Calva better for them. Some/most might be familiar with TS/JS or it might be easier for them to pick up than cljs. 1. Some contributors might be pretty familiar with VS Code extension dev, but not using cljs, and they might be more likely to contribute if it's written somewhat or mostly in TS. February was also a bit different in a not so pleasant way. In releasing some quite big new things, we by mistake changed things we did not want to change, and even broke some things for Windows (non-WSL) users. We have had to make two rollbacks of releases. The content of both these releases have been reworked and released again, save for one new feature which we hope to get in there soon. By this I am reminded about how special a project like Calva is, with so many use cases and different operating system environments, projects and expectations. We hope that our rollbacks were quick enough to not impact work too much for too many people. We try to learn as much as possible from the incidents. (I am pretty sure it will happen many times again. It comes with [how the Calva project works](https://calva.io/tao/).) All in all, we have had 13 new Calva releases, including rollbacks: * We now have a model for supporting several clojure-lsp instances per VS Code Workspace. See https://calva.io/clojure-lsp/. We think we can carry over some of general concept to when we start supporting more than two (CLJ + CLJS) nREPL sessions per workspace. * More scalable Project selection behaviour (mainly for monorepos) for both the REPL connection and for clojure-lsp. * The Calva indenter now behaves much, much better * [Project auto-selection] (https://calva.io/connect/#auto-select-project-type). We're going for supporting fully automated REPL Jack-in and connect, including API that Joyriders can use. This is a step in that direction. * End-to-end testing (using the Joyride test-runner) added. It can test both the extension under development and a release packaged VSIX. Both these are tested in the CI build-test pipeline. There is very, very little coverage yet. The E2E testing compliments Calva's current unit and integration testing. * Many quality issues fixed. * Lessons given to us, some hopefully learnt. I've started looking at adding Windows executors to our build and test steps in CI. This is WIP, because I got distracted. And I also was a bit defeated by YAML. 

## Joyride with Borkdude is so fun! 
I have spent most of my [Joyride](https://github.com/BetterThanTomorrow/joyride) time, with a lot of assistance from [Michiel Borkent](https://github.com/borkdude), adding automatic testing. We've written a test-runner which is using `clojure.test` and Joyride itself. It's for e2e testing so far (and possibly will stay like that). Like with Calva, the test-runner can support testing both the extension under development and a packaged VSIX. Both these are tested in the CI build-test pipeline. There have been five new Joyride releases. * Basic e2e tests coverage (not tied to releases, but anyway) * Improvement in support for writing Joyride scripts with JavaScript * npm require fixes * Enable js/require * Improve JavaScript introspection from the REPL * Add rewrite-clj as a built-in library for Joyride scripts We have also made it possible to build Joyride using ESM, to make lazy requires possible. We got as far as we now need to make Joyride's execution async, line [nbb](https://github.com/babashka/nbb)'s is. This needs to be spearheaded by Michiel, because he's the one who knows what we are doing. 

## GitHub ReadME project attention 
The GitHub ReadME Podcast geve [the Calva model for comaintainership some focus](https://github.com/readme/podcast/comaintaining-openness). Brandon and I were interviewed. Some super high level words on what is Clojure survived the editing as well. 

## Various experiments conducted 
As always I have been trying to figure out how we can support people new to Clojure better. This is all still quite chaotic for me, with my ideas and thinking being all over the place. Very much still in the hammock, even if some things sometimes find their way to Calva. And this time I've also created a [Getting Started with Clojure mini-repo](https://github.com/PEZ/clojure-get-started-mini) as a result of this thinking process. I'm hoping this project can help people find their footing quicker and give them help in starting their own first projects. I think example/starter projects in combination with Calva (and other IDE's) support for them, as well as carefully crafted support for [neil] (https://github.com/babashka/neil) might take us far. I have been experimenting a bit with [Electric Clojure](https://github.com/hyperfiddle/electric) to try figure out how Calva supports it as is, and get an idea what Calva (and/or other extensions) could do to leverage this new development model. Not much to show for it, just some instructions contributed to the main and example app repositories. Electric Clojure is under rapid development, so this will have to be a revisited often this year. Thanks to the Electric Clojure team for assisting me so generously and kindly in my explorations! I arranged my first Meetup, a [Clojure Meetup at that] (https://www.meetup.com/sthlm-clj/events/291204199/), featuring talks from [Josh Glover] (https://github.com/jmglov) and myself. It was hosted at the [Agical](https://agical.se) (my employer) office, and food was provided by [Pitch](https://pitch.com/). The event was live streamed. Which was also a first for me, and not without problems. Our production broke with Josh's presentation, leaving us only with a presenters camera which could only barely be convinced to film the slides as they where projected in the room. People tell us that it was still very enjoyable to follow the stream. * The full stream: [Meetup Feb 23: Blambda! The sound of Babashka and Lambda colliding](https://www.youtube.com/watch?v=NfgYon96dsE) (talks start at 30 minutes) * My talk: [Give me Interactive Programming, or give me death] (https://www.youtube.com/watch?v=L0-yDtVUWMQ) (please share around) I would summarize the meetup with the words of [Verner von Heidenstam] (https://en.wikipedia.org/wiki/Verner_von_Heidenstam): > It is fairer to listen to the string that broke than to never strain a bow. Thanks for reading! /[Peter Strömberg (aka PEZ)] (https://github.com/PEZ) <br>
  
---
  
## 2023 Annual Funding: Peter Taoussanis  
Hi everyone, [Peter Taoussanis](https://github.com/ptaoussanis) here :-)
A brief update on my [open-source work](https://www.taoensso.com/clojure) from Jan+Feb 2023 below.

Have been taking the unusual opportunity to concentrate on some larger/thornier items. Major things I've been working on (in ETA order):

1. [**Sente**](https://github.com/ptaoussanis/sente) v1.18,  
 Beta expected: **~early March**.  
 Current status: in closed testing.
 This'll include a significant number of improvements and overdue fixes, focused especially on reliability.

2. [**Carmine**](https://github.com/ptaoussanis/carmine) v3.3,  
 Beta expected: **~mid March**.  
 Current status: in closed testing.
 This'll include a significant rewrite of the Carmine **message queue** - and provide greatly improved performance and inspection/monitoring capabilities.

3. A new **Clojure data encryption library**,  
 Alpha expected: **~late March**.  
 Current status: working-prototype ready.
 Will share more details when published, but the gist is that this'll offer a set of tools heavily optimized for ease-of-use in some particularly common cases.
 Will support a future upgrade to [Nippy](https://github.com/ptaoussanis/nippy)'s encryption facilities.
 As part of this work, am planning to document some patterns that I've used in several Clojure web apps to secure user data.


With all upcoming releases, I'm also planning to start exploring some new ideas re: **documentation**. The intention will be to help better ease migration during major updates, and to help better accomodate beginners. Will have more to share on this later.

## What then?
- **http-kit v2.7**, expected **~April**.
- TBD (have several options I want to weigh then, depending on feedback).

A big thanks again to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](https://github.com/sponsors/ptaoussanis#sponsors) of my open-source work.


