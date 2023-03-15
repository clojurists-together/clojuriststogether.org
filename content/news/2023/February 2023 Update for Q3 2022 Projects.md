---
title: "February 2023 Project Updates for Q3 2022 Projects"
date: 2023-03-14T08:30:00+08:00
summary: February 2023 Updates for Q3 2022 Projects
author: Kathy Davis
draft: true
---
Greetings Clojurists Together community! This report includes final updates for the following  Q3 2022  projects:
Project Koacha
Project Clj-kondo (Jan. & Feb., 2023)
Project ClojureDart


**2022 Q3 Projects**
## Project Koacha: Arne Brasseur
Report #2: After a refreshing break, we've hit the ground running in 2023, merging approximately 20 PRs from both Gaiwan and community contributors, fixing many bugs and some CI/cljdocs issues. Several new libraries support Babashka, including Kaocha. We hope this and [other efforts](https://blog.michielborkent.nl/babashka-test-runner.html) will expand the pool of Clojure libraries and tools available for use in Babashka.
Most of the focus has been on Kaocha, which saw eight separate releases over the past two months. We've also released minor updates for Launchpad, embedkit, and Deja-fu.

### [Kaocha](https://github.com/lambdaisland/kaocha) 1.79.1270 (2023-02-28 / 47a7b61)

- Kaocha is now compatible with Babashka. Running under Babashka is most useful for validating your Clojure code runs correctly on Babashka, but it may also be faster for some test suites because of reduced start-up time.
- Fix issue with `--watch` and Neovim by bumping Beholder to `1.0.2`
- Fix bug causing namepaces to not be loaded if an alias was already created for them using `:as-alias`
- `kaocha.repl/config` accepts a `:profile` key when specifying extra options.
- Configuration errors and circular dependencies are reported as warnings, rather than causing the entire watcher to crash. (thanks[@frenchy64](https://github.com/frenchy64))
- Fix bug added in [#384](https://github.com/lambdaisland/kaocha/issues/384): assertions in the tail position of an each fixture would return the result of the assertion instead of the testable object with the merged report data (thanks [@NoahTheDuke](https://github.com/NoahTheDuke))
- Dependency version bumps
- `kaocha.plugin.capture-output/bypass` macro, for temporarily bypassing output capturing. 
- Circular dependencies in watch mode no longer kills the process. (thanks  [@frenchy64](https://github.com/frenchy64))
- Ensure reloading errors are printed in watch mode when the first test suite is disabled.
- Using a try-catch (without rethrowing) in an `:each` fixture could swallow thrown exceptions, resulting in a test being treated as passing when it should have been reported as an error. Fixed by changing how `:each` fixtures wrap the test function in execution. (thanks [@NoahTheDuke](https://github.com/NoahTheDuke))
- Fix crash on Windows when using `--watch` with the default Beholder watcher.
- Documentation fixes and improvements


### [Launchpad](https://github.com/lambdaisland/launchpad) 0.15.79-alpha (2023-01-20 / 2b06d8e)

-  Allow setting nrepl port/bind from CLI
-  Provide a warning when connecting to emacs fails, rather than exiting
-  Dependency version bumps

### [Embedkit](https://github.com/lambdaisland/embedkit) 0.0.50 (2023-01-19 / 8e058ff)
-  Let (setup/init-metabase! config) support first-name, last-name, site-name.

### [Deja-fu](https://github.com/lambdaisland/deja-fu) 1.4.58 (2023-01-16 / 1446eef)
-  `distance-in-words` now renders approximate weeks; month ranges were adjusted


## Project Clj-kondo: Michiel Borkent
In this post, I'll give updates about open source I worked on during January 2023. 

**Sponsors** 

But first off, I'd like to thank all the sponsors and contributors that make this work possible! Top sponsors: 
- [Clojurists Together](https://clojuriststogether.org/)
- [Roam Research](https://roamresearch.com/)
- [Nextjournal](https://nextjournal.com/) 
- [Toyokumo](https://toyokumo.co.jp/) 
- [Cognitect](https://www.cognitect.com/) 
- [Kepler16](https://kepler16.com/) 
- [Adgoji](https://www.adgoji.com/) 

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you! 

- [Github Sponsors](https://github.com/sponsors/borkdude) 
- The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective 
- [Ko-fi](https://ko-fi.com/borkdude) 
- [Clojurists Together](https://www.clojuriststogether.org/) 
- [Patreon](https://www.patreon.com/borkdude) 

If you're used to sponsoring through some other means which isn't listed above, please get in touch. 

**Attention** 

If you are using Github Sponsors and are making payments via Paypal, please update to a creditcard since Github Sponsors won't support Paypal after February 23rd 2023. Read their statement [here](https://github.blog/changelog/2023-01-23-github-sponsors-will-stop-supporting-paypal/). If you are not able to pay via a creditcard, you can still sponsor me via one of the ways mentioned above. 


### [Babashka](https://github.com/babashka/babashka)
Native, fast-starting Clojure interpreter for scripting. New releases in the past month: 1.0.170 - 1.1.173 Highlights:
- Support for data_readers.clj(c)
- Include [http-client](https://github.com/babashka/http-client) as built-in library
- Compatibility with [clojure.tools.namespace.repl/refresh](https://github.com/clojure/tools.namespace)
- Compatibility with [clojure.java.classpath](https://github.com/clojure/java.classpath) (and other libraries which rely on java.class.path and RT/baseLoader)
- Compatibility with [eftest](https://github.com/weavejester/eftest) test runner (see demo)
- Compatibility with [cljfmt](https://github.com/weavejester/cljfmt)
- Support for *loaded-libs* and (loaded-libs)
- Support add-watch on vars (which adds compatibility with potemkin.namespaces)
- BREAKING: make printing of script results explicit with --prn
- Babashka compatibility in external libs

I contributed changes to the following libraries to make them compatible with babashka:
- [cljfmt](https://github.com/weavejester/cljfmt) - A tool for formatting Clojure code
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
- [debux](https://github.com/philoskim/debux) - A trace-based debugging library for Clojure and ClojureScript
Check the [changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md) for all the changes!

### [Http-client](https://github.com/babashka/http-client)
The new babashka http-client project mostly replaces [babashka.curl](https://github.com/babashka/babashka.curl).
This month the default client was improved to accept gzip and deflate as encodings by default, reflecting what babashka.curl did. Also babashka.http-client is now available as a built-in namespace in babashka v1.1.171 and higher.

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)
Static analyzer and linter for Clojure code that sparks joy. Three new releases with many fixes and improvements in the last month. [Check the changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details. Some highlights:
- [#1742](https://github.com/clj-kondo/clj-kondo/issues/1742): new linter :aliased-namespace-var-usage: warn on var usage from namespaces that were used with :as-alias. See [demo](https://twitter.com/borkdude/status/1613524896625340417/photo/1).
- [#1926](https://github.com/clj-kondo/clj-kondo/issues/1926): Add keyword analysis for EDN files. This means you can find references for keywords throughout your project with clojure-lsp, now including in EDN files.
- [#1902](https://github.com/clj-kondo/clj-kondo/issues/1902): provide :symbols analysis for navigation to symbols in quoted forms or EDN files. See [demo](https://twitter.com/borkdude/status/1612773780589355008).
- The symbol analysis is used from clojure-lsp for which I provided a patch [here](https://github.com/borkdude/clojure-lsp/commit/f662adab1b17d5dbc3648d6d8208334dc920aa0e).A new project around clj-kondo is [clj-kondo-bb](https://github.com/clj-kondo/clj-kondo-bb) which enables you to use clj-kondo from babashka scripts. Also [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo) got an update.

### [Instaparse-bb](https://github.com/babashka/instaparse-bb)
This is a new project and gives you access to a subset of [instaparse](https://github.com/Engelberg/instaparse) via a [pod](https://github.com/babashka/pod-babashka-instaparse). Instaparse was request a few times to have as a library in babashka and instaparse-bb is a good first step, without making a decision on that yet. See the relevant discussion [here](https://github.com/babashka/babashka/discussions/1335).

### [Carve](https://github.com/borkdude/carve)
Remove unused Clojure vars. In the [0.3.5](https://github.com/borkdude/carve/blob/master/CHANGELOG.md#035) version, Carve got the following updates:
- Upgrade clj-kondo version
- Make babashka compatible by using the [clj-kondo-bb](https://github.com/clj-kondo/clj-kondo-bb) library
- Discontinue the carve binary in favor of invocation with babashka. Instead you can now install carve with [bbin](https://github.com/babashka/bbin): bbin install io.github.borkdude/carve
-  Implement [babashka.cli](https://github.com/babashka/cli) integration
- Implement --help

### [Jet](https://github.com/borkdude/jet)
CLI to transform between JSON, EDN, YAML and Transit using Clojure Version 0.4.23: 
- [#123](https://github.com/borkdude/jet/issues/123): Add base64/encode and base64/decode
- Add jet/paths and jet/when-pred
- Deprecate interactive mode
- Deprecate --query in favor of --thread-last, --thread-first or --func

### [Fs](https://github.com/babashka/fs)
File system utility library for Clojure. Fs has gotten a few new functions:
- unifixy, to turn a Windows path into a path with Unix-style pathseparators. Note that that style is supported by the JVM and this offers a morereliable way to e.g. match filenames via regexes.
- several xdg-*-home helper functions, contributed by [@eval](https://github.com/eval) 


See [changelog](https://github.com/babashka/fs/blob/master/CHANGELOG.md#changelog) for more details.

### [Neil](https://github.com/babashka/neil)
A CLI to add common aliases and features to deps.edn-based projects.
This month there were several small fixes, one of them being to always pick stable versions when adding or upgrading libraries. See full [changelog](https://github.com/babashka/neil/blob/main/CHANGELOG.md) for details.

### [Quickblog](https://github.com/borkdude/quickblog)
Light-weight static blog engine for Clojure and babashka. The blog you're currently reading is made with quickblog. Version [0.2.3](https://github.com/borkdude/quickblog/blob/main/CHANGELOG.md#023-2023-01-30) was released with contributions from several people, mostly enabling you to tweak your own blog even more, while having good defaults. Instances of quickblog can be seen here:
- [Michiel Borkent's blog](https://blog.michielborkent.nl/)
- [Josh Glover's blog](https://jmglov.net/blog)
- [Jeremy Taylor's blog](https://jdt.me/strange-reflections.html)
- Luc Engelen's blog](https://blog.cofx.nl/) ([source](https://github.com/cofx22/blog))
- [Rattlin.blog](https://rattlin.blog/)
If you are also using quickblog, please let me know!
A collection of ready to be used SCI configs for e.g. Reagent, Promesa, Re-frame and other projects that are used in nbb, joyride, scittle, etc. See recent [commits](https://github.com/babashka/sci.configs/commits/main) for what's been improved.

### [Edamame](https://github.com/borkdude/edamame)
Edamame got a new function: parse-next+string which returns the original string along with the parsed s-expression.

### [lein2deps](https://github.com/borkdude/lein2deps)
- Lein to deps.edn converter. This tool can convert a project.edn file to a deps.edn file. It even supports Java compilation and evaluation of code within project.clj. There is now a lein plugin which enables you to sync your project.clj with your deps.edn every time you start lein. Several other minor enhancements were made. See [changelog](https://github.com/borkdude/lein2deps/blob/main/CHANGELOG.md).

### [4ever-clojure](https://github.com/oxalorg/4ever-clojure)
I added the ability to build and deploy 4ever-clojure to Github Actions. Every time a commit is merged, the site is automatically updated.

### Brief mentions
The following projects also got updates, mostly in the form of maintenance and performance improvements. This post would get too long if I had to go into detail about them, so I'll briefly mention them in random order:
[jna-native-image-sci](https://github.com/borkdude/jna-native-image-sci): Compile a program that uses JNA to native-image and allow dynamic evaluation using [SCI](https://github.com/babashka/sci)!
[deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
[joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
[squint](https://github.com/squint-cljs/squint): CLJS syntax to JS compiler
[tools-deps-native](https://github.com/babashka/tools-deps-native): Run tools.deps as a native binary
[tools.bbuild](https://github.com/babashka/tools.bbuild): Library of functions for building Clojure projects
[scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
[pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
[nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
[CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
[process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
[SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
[scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
[sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs
Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).
Published: 2023-02-05
Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)

## Project Clj-kondo: Michiel Borkent
In this post, I'll give updates about open source I worked on during February 2023.

### [Babashka](https://github.com/babashka/babashka)
Native, fast-starting Clojure interpreter for scripting. New release: 1.2.174. Highlights:
- Use GraalVM 22.3.1 on JDK 19.0.2. This adds virtual thread support. See [demo](https://twitter.com/borkdude/status/1572222344684531717).
- Add more java.time and related classes with the goal of supporting [juxt.tick](https://github.com/juxt/tick) ([issue](https://github.com/juxt/tick/issues/86))
- See the complete [CHANGELOG](https://github.com/babashka/babashka/blob/master/CHANGELOG.md).

Babashka compatibility in external libs
- I worked together with the maintainers of the following libraries to make them compatible with babashka:
- [kaocha](https://github.com/lambdaisland/kaocha): test runner[multiformats](https://github.com/greglook/clj-multiformats): Clojure(Script) implementations of the self-describing multiformat specs

### [Http-client](https://github.com/babashka/http-client): Babashka's http-client
The babashka.http-client namespace mostly replaces [babashka.curl](https://github.com/babashka/babashka.curl).
This month support for :multipart uploads was added, mostly based on and inspired by [hato](https://github.com/gnarroway/hato)'s implementation.

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)
Static analyzer and linter for Clojure code that sparks joy. New release: 2023.02.17..Some highlights:
- [#1976](https://github.com/clj-kondo/clj-kondo/issues/1976): warn about using multiple bindings after varargs (&) symbol in fn syntax
- Add arity checks for core def
- [#1954](https://github.com/clj-kondo/clj-kondo/issues/1954): new :uninitialized-var linter. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#uninitialized-var).
- [#1996](https://github.com/clj-kondo/clj-kondo/issues/1996): expose hooks-api/resolve. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/hooks.md#api).
- [Check the changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details.

### [SCI](https://github.com/babashka/sci)
Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
This month:
- Adding JS libraries to a SCI context. See [docs](https://github.com/babashka/sci#javascript-libraries)
- Keyword arguments as map support for CLJS
- Making loading of libraries thread-safe in JVM
- Several fixes with respect to deftype and toString + equals

### [Fs](https://github.com/babashka/fs)
File system utility library for Clojure. Highlights:
- several xdg-*-home helper functions, contributed by [@eval](https://github.com/eval)
babashka.fs/zip now takes a :root option to elide a parent folder or folders.E.g. (fs/zip "src" {:root "src"}) will zip src/foo.clj into the zip file under foo.clj.
- See [changelog](https://github.com/babashka/fs/blob/master/CHANGELOG.md#changelog) for more details.

### [Process](https://github.com/babashka/process)
Clojure library for shelling out / spawning sub-processes
This month I looked into wrapping output of processes with a prefix so when ran in parallel, you can easily distuingish them. A preliminary solution is in [this thread](https://github.com/babashka/process/discussions/102#discussioncomment-4903758).

### [Pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna)
Interact with clojure-lanterna from babashka. A very experimental 0.0.1 release was published. You can try it out by playing tetris in the console with babashka:
bb -Sdeps '{:deps {io.github.borkdude/console-tetris {:git/sha "2d3bee34ea93c84608c7cc5994ae70480b2df54c"}}}' -m tetris.core

### [Nbb](https://github.com/babashka/nbb)
Scripting in Clojure on Node.js using SCI
Finally nbb has gotten support for passing maps to keyword argument functions:
- (defn foo [& {:keys [a b c]}])
- (foo :a 1 :b 2 :c 3)
- (foo {:a 1 :b 2 :c 3})
Several other improvements have been made in the area of macros and resolving JS library references and resolving dependencies in an nbb.edn file, relative to an invoked script which is not in the current directory.
See changelogs [here](https://github.com/babashka/nbb/blob/main/CHANGELOG.md).

### [Joyride](https://github.com/BetterThanTomorrow/joyride)
VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
This month I contributed a built-in version of [rewrite-clj](https://github.com/clj-commons/rewrite-clj) to joyride, so joyriders can rewrite their code from within VSCode.

### [Cljs-showcase](https://github.com/borkdude/cljs-showcase)
Showcase CLJS libs using SCI
A little project to show how you can use SCI to showcare your CLJS library in an interactive way.

### Brief mentions
The following projects also got updates, mostly in the form of maintenance and performance improvements. This post would get too long if I had to go into detail about them, so I'll briefly mention them in random order:
[CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
[quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
[process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
[rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
[sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
[squint](https://github.com/squint-cljs/squint): CLJS syntax to JS compiler
###Other projects
These are some of the other projects I'm involved with but little to no activity happened in the past month.
[carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
[deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
[edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
[cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
[grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
[jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
[scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
[neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
[quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka
[sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs
[lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
[4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
[instaparse-bb](https://github.com/babashka/instaparse-bb)
[pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
Discuss this post [here](https://github.com/borkdude/blog/discussions/categories/posts).
Published: 2023-03-01
Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)


## Project ClojureDart: Christophe Grand
This final update covers January 2023. This month we merged our changes for a better hot reload (see Update #2) and then went on bug fixing and doing small improvements for a while.
A problem kept bothering us: in our `cljd.flutter` (well in January it was still`cljd.flutter.alpha2`) we have `:watch` to react to updates to all kind of stateful objects and `sub` to narrow the scope of changes we are interested in for a given "watchable". This is very useful as it allows to change the frequency at which a piece of UI is updated: one can have a big atom holding a lot of state (and this changing frequently) and watch a slower changing subset of it.

However `sub` only allowed to narrow *one* watchable. This led to awkward code were every other watchables (IO or framework related) updates had to be shoe-horned in a single canonical atom with the help of listeners (whose lifecycles have to be tied to specific parts of the widget tree) and hair-pulling naming decisions on paths inside this atom. We first extended `sub` to allow to apply a function across several watchables latest values, somehow behaving like a join. It worked but felt quite stiff and not very pleasing to use. `sub` relied on the `f & args` update convention (as used in `swap!`, `update-in` etc.) but there's no agreed upon convention for when one has several input and thus it doesn't mesh as well as the single-input version with the core lib.

This dissatisfaction led us to develop "cells". Cells as in spreadsheets. So cells are expressions which recompute their value when their dependencies values change. Obviously we made cells watchable. A cell is defined by using the `$` macro (because it caches its latest value) and inside (dynamically, not lexically) a cell one can take (`<!`) from other cell *or any other watchable*.
Example: assuming `now` is an atom (a plain old one) updated at 60fps, then `($ (.-day (<! now)))` is a cell recomputed at 60fps but yielding a new value once a day. So any widget (or other cell) depending on this cell will only be updated once a day.
This generalizes nicely to multiple dependencies and even to dynamic dependency graphs. Another interesting fact to know about cells is that a cell is recomputed only when it's watched (and this cascades transitively: an unwatched cell doesn't watch its dependencies...). Another consequence of cells is that the push for a single big canonical atom goes away. This changed positively the way we write apps. So much that in our February workshop we talked only about cells, not subs.
