---
title: "Sept. & Oct. 2023 Long-Term Project Updates"
date: 2023-11-18T08:30:00+08:00
summary: "Cider/REPL, clj-kondo, basbashka, clojars, clojure-lsp, shadow, calva, malli, carmine V4, Humble UI and more ."
author: Kathy Davis
draft: True


---  
<br>  

[Bozhidar Batsov:](#bozhidar-batsov) CIDER/REPL     
[Michiel Borkent:](#michiel-borkent) clj-kondo,babashka, squint, neil, CLI,clojure-mode, and more..  
[Sean Corfield:](#sean-corfield)clojure-doc.org, toolsbuild, deps-new, honey-SQL, expectations      
[Toby Crawley:](#toby-crawley) Clojars  
[Eric Dallo:](#eric-dallo) Clojure-lsp, intellij   
[Cristophe Grand:](#christophe-grand) ClojureDart et.al.  
[Thomas Heller:](#thomas-heller) Shadow-cljs     
[Nikita Prokopov:](#nikita-prokopov) new projects, Sublimed, DataScript  
[Tommi Reiman:](#tommi-reiman) Malli, Reitit, Jsonista   
[Peter Stromberg:](#peter-stromberg) Calva, JavaScript REPL, Polylith    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, http-kit, Tempel, Telemere   
<br>
## Bozhidar Batsov  

I'm happy to report that after numerous delays the long-awaited CIDER 1.8 ("Geneva") was shipped on October 13th (which also happened to be my 39th birthday)! This was a truly colossal release that addressed many long-standing issues and introduced some impressive new features (e.g. much improved enrich-classpath, cider-log-mode, a new way to display javadoc and many improvements to the tests runner).
See the [release notes](https://github.com/clojure-emacs/cider/releases/tag/v1.8.0) for more details.  

This release was quickly followed by [CIDER 1.9](https://github.com/clojure-emacs/cider/releases/tag/v1.9.0) and [CIDER 1.10](https://github.com/clojure-emacs/cider/releases/tag/v1.10.0) - much smaller releases that mostly addressed some small issues and polished a bit some of the new functionality in CIDER 1.8.
All this work also resulted in the list of open issues on CIDER finally dipping under 100 (currently they stand at 85). That feels nice!  

Now that this is done, my focus will switch to completing the support for `clojure-ts-mode` in CIDER and finally cutting an nREPL 1.1 (which will bring TLS support). <br> 

---



## Michiel Borkent  

In this post I'll give updates about open source I worked on during September and October 2023.  
To see previous OSS updates, go here.  


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

If you're used to sponsoring through some other means which isn't listed above, please get in touch. On to the projects that I've been working on!

### September Updates  

Last week I delivered my babashka talk at [Strange loop](https://www.thestrangeloop.com/2023/babashka-a-meta-circular-clojure-interpreter-for-the-command-line.html) talk and much of my attention went to the preparation of that (slides [here](https://speakerdeck.com/borkdude/babashka-a-meta-circular-clojure-interpreter-for-the-command-line-at-strange-loop-2023)). The talk is now available [here](https://www.youtube.com/watch?v=DHtRfO3Bp90).

I did manage to get some coding done as well, despite testing positive for COVID when I arrived back home... Here are updates about the projects/libraries I've worked in September.  


* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy. Published a new version (2023.09.07) with these changes:
    * [#1332](https://github.com/clj-kondo/clj-kondo/issues/1332): New linter `:unused-alias`. See [docs](https://github.com/clj-kondo/clj-kondo/tree/master/doc/linters.md).
    * [#2143](https://github.com/clj-kondo/clj-kondo/issues/2143): false positive type warning for `clojure.set/project`
    * [#2145](https://github.com/clj-kondo/clj-kondo/issues/2145): support ignore hint on multi-arity branch of function definition
    * [#2147](https://github.com/clj-kondo/clj-kondo/issues/2147): use alternative solution as workaround for https://github.com/cognitect/transit-clj/issues/43
    * [#2152](https://github.com/clj-kondo/clj-kondo/issues/2152): Fix false positive with used-underscored-binding with core.match
    * [#2150](https://github.com/clj-kondo/clj-kondo/issues/2150): allow command line options = as in –fail-level=error
    * [#2149](https://github.com/clj-kondo/clj-kondo/issues/2149): `:lint-as clojure.core/defmacro` should suppress `&env` as unresolved symbol
    * [#2161](https://github.com/clj-kondo/clj-kondo/issues/2161): Fix type annotation for `clojure.core/zero?` to number -> boolean
    * [#2165](https://github.com/clj-kondo/clj-kondo/issues/2165): Fix error when serializing type data to cache
    * [#2167](https://github.com/clj-kondo/clj-kondo/issues/2167): Don't crash when `:unresolved-symbol` linter config contains unqualified symbol
    * [#2170](https://github.com/clj-kondo/clj-kondo/issues/2170): `:keyword-binding` linter should ignore auto-resolved keywords
    * [#2172](https://github.com/clj-kondo/clj-kondo/issues/2172): detect invalid amount of args and invalid argument type for `throw`
    * [#2164](https://github.com/clj-kondo/clj-kondo/issues/2164): deftest inside let triggers :unused-value
    * [#2154](https://github.com/clj-kondo/clj-kondo/issues/2154): add `:exclude` option to `:deprecated-namespace` linter
    * [#2134](https://github.com/clj-kondo/clj-kondo/issues/2134): don't warn on usage of private var in `data_readers.clj(c)`
    * [#2148](https://github.com/clj-kondo/clj-kondo/issues/2148): warn on configuration error in `:unused-refeferred-var` linter
    * Expose more vars in `clj-kondo.hooks-api` interpreter namespace  

* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting. Version 1.3.185 released!
    * [#1624](https://github.com/babashka/babashka/pull/1624): Use Oracle GraalVM 21 ([@lispyclouds](https://github.com/lispyclouds))
    * Use PGO to speed up loops (now 2-3x faster for `(time (loop [val 0 cnt 10000000] (if (pos? cnt) (recur (inc val) (dec cnt)) val)))`!)
    * Bump babashka.http-client to v0.4.15
    * Bump rewrite-clj to v0.1.1.47
    * [#1619](https://github.com/babashka/babashka/issues/1619): Fix reflection issue with `Thread/sleep` in `core.async/timeout`
    * Support interop on `java.util.stream.IntStream`
    * [#1513](https://github.com/babashka/babashka/issues/1513): Fix interop on `Thread/sleep` with numbers that aren't already longs
    * Bump babashka.cli to 0.7.53
    * Fix [#babashka.nrepl/66](https://github.com/babashka/babashka.nrepl/issues/66)
    * Various nREPL server improvements (classpath op, file lookup information for `cider-find-var`)
    * Bump cheshire to 5.12.0  

* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler A lot of happened in squint this month:
    * Many core var implementations got added: `reduce-kv`, `max`, `min`, `every-pred`, `into-array`, `some-fn`, `keep-indexed`, `iterate`, `juxt`, `compare`, `to-array`, `fn?`
    * Bun compatibility
    * Lots of bug fixes
    * REPL improvements
    * Adopt CLJS truth semantics: `0` and `""` are no longer considered falsey
    * Lots more coming next month!  

* [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects Version 0.2.61 released
    * [#181](https://github.com/babashka/neil/issues/181): fix `neil --version`
    * fix tests by referring to latest hiccup ([@teodorlu](https://github.com/teodorlu))
    * [#180](https://github.com/babashka/neil/issues/180): `neil dep upgrade`: allow upgrading from an unstable version to the latest unstable version ([@teodorlu](https://github.com/teodorlu))
    * [#180](https://github.com/babashka/neil/issues/180): `neil dep upgrade`: with `--unstable`, opt-into unstable library updates ([@teodorlu](https://github.com/teodorlu))
    * [#183](https://github.com/babashka/neil/issues/183): Don't drop `:exclusions` when running `neil dep add` or `neil dep upgrade` ([@borkdude] and [@teodorlu])
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Small release with the option to add your own header on top of `format-opts`, thanks to [@Sohalt](https://github.com/Sohalt)
* [http-client](https://github.com/babashka/http-client): babashka's http-client  
    * A number of small bugfixes and additions

* A number of experiments around [squint](https://github.com/squint-cljs/squint):  
    * [bun-squint-loader](https://github.com/borkdude/bun-squint-loader): a demo of how to implement a loader for [bun](https://github.com/oven-sh/bun) which lets you directly load `.cljs` files which are then compiled using
    * squint [cloudflare worker](https://github.com/borkdude/squint-bun-cloudflare)  

* [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of rewrite-clj with common operations to update EDN while preserving whitespace and comments
    * Fixed a round-tripping issue by bumping rewrite-clj  

* [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
    * aarch64 binary (thanks @TimoKramer for contributing)
    * update upstream projects  

* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler  

    * Bump shared compiler code with squint and publish new version
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Bumped tools jar and fixed a bug concerning SHA comparison
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Add experimental `:static-methods` override to control how a static method is invoked. This allowed a fix in babashka for `Thread/sleep` on non-longs and for `Class/forName` which works arond a bug in Oracle GraalVM 21.
* [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
    * [@holyjak](https://github.com/holyjak) made a configuration for [Fulcro](https://github.com/fulcrologic/fulcro) which can be seen live in action [here](https://blog.jakubholy.net/2023/interactive-code-snippets-fulcro/)

### October Updates  


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
    * A configuration for [hoplon](https://github.com/hoplon/hoplon) and [javelin](https://github.com/hoplon/javelin) was added. You can play around with hoplon in a SCI-enabled environment [here](https://babashka.org/sci.configs/?gist=e83da19df3d2739861334171779f79d5)  

* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * [#2207](https://github.com/clj-kondo/clj-kondo/issues/2207): New `:condition-always-true` linter, see [docs](https://github.com/clj-kondo/clj-kondo/tree/master/doc/linters.md))
    * [#2013](https://github.com/clj-kondo/clj-kondo/issues/2013): Fix NPE and similar errors when linting an import with an illegal token \
Published a new version (2023.10.20) with these changes:
    * [#1804](https://github.com/clj-kondo/clj-kondo/issues/1804): new linter `:self-requiring-namespace`
    * [#2065](https://github.com/clj-kondo/clj-kondo/issues/2065): new linter `:equals-false`, counterpart of `:equals-true` ([@svdo](https://github.com/svdo))
    * [#2199](https://github.com/clj-kondo/clj-kondo/issues/2199): add `:syntax` check for var names starting or ending with dot (reserved by Clojure)
    * [#2179](https://github.com/clj-kondo/clj-kondo/issues/2179): consider alias-as-object usage in CLJS for :unused-alias linter
    * [#2183](https://github.com/clj-kondo/clj-kondo/issues/2183): respect `:level` in `:discouraged-var` config
    * [#2184](https://github.com/clj-kondo/clj-kondo/issues/2184): Add missing documentation for `:single-logical-operand` linter ([@wtfleming](https://github.com/wtfleming))
    * [#2187](https://github.com/clj-kondo/clj-kondo/issues/2187): Fix type annotation of argument of `clojure.core/parse-uuid` from `nilable/string` to string ([@dbunin](https://github.com/dbunin))
    * [#2192](https://github.com/clj-kondo/clj-kondo/issues/2192): Support `:end-row` and `:end-col` in `:pattern` output format ([@joshgelbard](https://github.com/joshgelbard))
    * [#2182](https://github.com/clj-kondo/clj-kondo/issues/2182): Namespace local configuration does not silence `:missing-else-branch`
    * [#2186](https://github.com/clj-kondo/clj-kondo/issues/2186): Improve warning when `--copy-configs` is enabled but no config dir exists
    * [#2190](https://github.com/clj-kondo/clj-kondo/issues/2190): false positive with `:unused-alias` and namespaced map
    * [#2200](https://github.com/clj-kondo/clj-kondo/issues/2200): include optional `:callstack` in analysis
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler \
Lots of stuff happened in October with squint!
    * [#350](https://github.com/squint-cljs/squint/issues/350): `js*` should default to `:context :expr`
    * [#352](https://github.com/squint-cljs/squint/issues/352): fix `zero?` in return position
    * Add `NaN?` ([@sher](https://github.com/sher))
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
    * Include `lib/squint.core.umd.js` which defines a global `squint.core` object (easy to use in browsers, see [docs](https://github.com/squint-cljs/squint/blob/main/README.md#compile-on-a-server-use-in-a-browser))
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


### Other projects  
These are (some of the) other projects I'm involved with but little to no activity happened in September and October.  See detail in the final section of each report names “Other Projects” for details.  
https://blog.michielborkent.nl/oss-updates-sep-2023.html    
https://blog.michielborkent.nl/oss-updates-oct-2023.html  

Discuss this post here.  
Published: 31 October and 30 September, 2023  
Tagged: clojure oss updates  <br>  

---






## Sean Corfield  
 :tags ["clojure" "clojure-doc.org" "expectations" "honeysql" "jdbc" "open source" "community" "clojurists together"]}  

In my [previous Long-Term Funding update](https://corfield.org/blog/2023/08/31/long-term-funding-4/)
I said I would review/overhaul the "ecosystem" and "tutorials" sections
(once I'd finished the "language" section).

### `clojure-doc.org`  

I finished reviewing and updating the last three language sections to Clojure  **1.11**  
([Concurrency and Parallelism](https://clojure-doc.org/articles/language/concurrency_and_parallelism/),
[Macros](https://clojure-doc.org/articles/language/macros/),
and [Laziness](https://clojure-doc.org/articles/language/laziness/)), and I
added a new section about transducers to the
[Collections and Sequences](https://clojure-doc.org/articles/language/collections_and_sequences/)
section. In the next period, I'll revisit the "TBD" items in the language
section to see what I can do to address them.  

I rewrote the
[Generating Documentation](https://clojure-doc.org/articles/ecosystem/generating_documentation/)
section (ecosystem) to focus on [cljdoc.org](https://cljdoc.org/)
and removed the outdated content.  

The [Web Development](https://clojure-doc.org/articles/ecosystem/web_development/)
section (ecosystem) also got a major rewrite, adding a lot of new content,
removing outdated content, and incorporating a lot of community feedback
on a draft version (thank you!).  

Finally, I added a new section to the
[`tools.build` Cookbook](https://clojure-doc.org/articles/cookbooks/cli_build_projects/)
about template `pom.xml` file and the `:pom-data` approach (new in `tools.build` 0.9.6).  

### `tools.build` and `deps-new`  

Following on from the `tools.build` Cookbook update, I updated
[`deps-new`](https://github.com/seancorfield/deps-new) to use that new
release of `tools.build` and updated all the project templates to use
`:pom-data` for generating `pom.xml` files.
See the [v0.5.3](https://github.com/seancorfield/deps-new/releases/tag/v0.5.3)
Release  

I need to do the same for `clj-new` at some point -- perhaps in the next period!  

### HoneySQL  

HoneySQL also saw a lot of work with two releases that mostly focused on
improving BigQuery support (array subquery, select as struct, create or replace,
ignore/respect nulls, and new `:distinct` and `:expr` clauses to facilitate
certain non-standard queries), and introducing basic support for NRQL (New Relic Query Language) as a new dialect.  

NRQL has non-standard quoting rules, non-standard entity names, and inlines
all expression values, since it is mostly used directly within the New Relic
web UI or via their CLI, neither of which support parameterized queries. New
clauses and helpers have been added for `:compare-with`, `:since`, `:timeseries`,`until`, and `:facet`.  

In addition, an important bug in the helper merge functions was fixed that
affected anyone using the quoted-symbol style of DSL (instead of the keyword
style).  

See [HoneySQL releases](https://github.com/seancorfield/honeysql/releases)
for more details of these two new versions.  

### Expectations  

[Expectations](https://github.com/clojure-expectations/clojure-test) also saw
two new releases this period. The first release focused on improving the
way test failures are reported to be more consistent and informative. The
second release was a minor one to improve `clj-kondo` support for `more-of`.  

See [Expectations releases](https://github.com/clojure-expectations/clojure-test/releases)
for more details on these two new versions.

### `next.jdbc`

Finally, `next.jdbc` [1.3.894](https://github.com/seancorfield/next-jdbc/releases/tag/v1.3.894)
provides variants of `with-transaction` and `on-connection` that will rewrap an
options-wrapped connectable. This was a long-requested feature that I had
struggled with finding an elegant solution for. In addition, I updated most of
the JDBC drivers that `next.jdbc` is tested against, to flush out any issues
with those newer versions. Notably, SQLite no longer supports `:return-generated-keys true` but you can specify `RETURNING *` in your SQL instead.  

### What's Next?  

In November/December, I'm hoping to complete a review and update of the
"cookbooks" section and make another pass of "TBD" items in the "language"
section.  <br> 

---


## Toby Crawley  
### Clojars Worklog  

### September 2023  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/0a5eb2175e7b417fc9e64bcb6fb87f6d15cbddbc...3dfeb61890e1f61c54e334dabe5c5e5bcccaeb67)  

Most of September was vacation, but I did make a few improvements towards the
end of the month:  
-   [Prevent memory leak from in-memory session store](https://github.com/clojars/clojars-web/commit/29b5d25cdc8ede87a35967691110f84a4a8c23d1) This was the source of intermittent OOMs for years, and I finally tracked it down.  
-   [Require a license in the POM for new projects](https://github.com/clojars/clojars-web/pull/874)  

### October 2023  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/3dfeb61890e1f61c54e334dabe5c5e5bcccaeb67...d92cf1eb2f78e13894d37eb8e4b5fc070d9e00e0)  

The work this month was modernization: we moved away from   [yesql](https://github.com/krisajenkins/yesql) in favor of  
[honeysql](https://github.com/seancorfield/honeysql), and to  [next.jdbc](https://github.com/seancorfield/next-jdbc) from  [clojure.java.jdbc](https://github.com/clojure/java.jdbc):  

-   [Convert from yesql to honeysql for SQL queries](https://github.com/clojars/clojars-web/commit/2d09602722df78ef40e30f5d9bcf01df011af27e)  
-   [Convert from java.jdbc to next.jdbc](https://github.com/clojars/clojars-web/commit/1116f6c6b3a5cc7b484fdb24175127f43f232d83)  
-   [Don&rsquo;t send password change email when just profile email address changed](https://github.com/clojars/clojars-web/commit/07603ad74ea21f722ef7184f200b04ff6e75b30e)  
-   [Adjust dependencies to address CVEs](https://github.com/clojars/clojars-web/commit/f1b251f4e788798a61cdb0d72023a00f7525945f)  <br>

---



## Eric Dallo  

During these months I worked a lot on clojure-lsp-intellij to make it stable and allow IntelliJ users have the same experience of using clojure-lsp in other editors, the plugin is way more stable now, and I've been working with @afucher in a clojure-repl-intellij OSS plugin which should work pretty well with this plugin so users will be able to have both REPL access and LSP features in Intellij, we should announce it soon!  

For that to work and scale, I extracted all the clojure integration part from clojure-lsp-intellij to a separated library called `clj4intellij`, this new library allows the creation of Intellij plugins in Clojure without too much effort, this was the result of months of research and headaches about how IntelliJ's API could work with Clojure, we are already successfully using it in clojure-repl-intellij!  

### [clj4intellij](https://github.com/ericdallo/clj4intellij)  

#### 0.3.1 
- Support custom nrepl ports  
- Add NREPL and logger support.  
- Include clojure source to jar.  
- Add `com.github.ericdallo.clj4intellij.app-manager` namespace with functions to access `ApplicationManager`.  
- First release  

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

#### 0.9.0 - 0.13.0  
- Add support for paredit actions: slurp, barf, raise and kill.  
- Bump clojure-lsp to `2023.10.30-16.25.41`.  
- Allow specifying a server log-path to better troubleshooting.  
- Fix find definition to work for external deps as well.  
- Improve troubleshooting section  
- Use nrepl and logger from clj4intellij.  
- Improve line indent to recognize some macros.  
- Fix exception on startup related to status bar.  
- Implement lineIdentProvider to handle enters and move cursor to correct position.  
- Start LSP server only when opening clojure files, avoiding starting on non Clojure projects. #20  
- Fix LSP not being disconnected on project close/switch.  
- Bump clj4intellij to `0.2.1`.  
- Fix language attribute in intentionAction from plugin.xml. #18  
- Fix documentationProvider plugin.xml. #19  
- Extract Clojure intellij integration to separated lib clj4intellij.  
- Fix Find definition to work with Ctrl+B + Ctrl+click.  
- Add support for `textDocument/didSave` notification.  
- Fix rename refactor when file is not opened.  

### [clojure-lsp](https://clojure-lsp.io/)  

The main highlights are performance and memory improvement along with paredit features for `clojure-lsp-intellij` and other editors.  

#### 2023.10.30-16.25.41  

- General  
  - Bump clj-kondo to `2023.10.21-20231022.204832-4`.   
  - Fix move-to-let/expand-let bug for multi-arity functions #1283  
  - Fix `:dependency-scheme` setting for .java files from jars #1653  
  - Bump graalvm to 21.  
  - Improve overall performance using GraalVM 21 PGO (Profile-Guided Optimizations).  
  - Extract the responsibility for merging clj-depend config #1265  
  - Support passing configurations to clj-depend via CLI #1694  
  - Bump clj-depend to `0.9.2`  
  - Performance: remove keyword-usages elements from external analysis as they are not used for any feature and for big projects may affect memory. #1700  

- Editor  
  - New paredit refactorings:  
    - `forward-slurp`  
    - `forward-barf`  
    - `backward-slurp`  
    - `backward-barf`  
    - `raise`  
    - `kill`  
  - Improve rename to avoid internal errors and show proper error messages. #1691  
  - Exclude unused-public-vars when inside a comment block.  
  - Add `:analysis-summary` field to `serverInfo` command to get information about project for performance analysis.  
  - Add `:analysis` settings, with options to disable specific analysis for better performance on huge projects. #1700
<br>  
---




## Christophe Grand  

### ClojureDart  
- ClojureDart channel on Clojurians' Slack is getting close to 500 members.  
- We demoed ClojureDart at the London Clojurians meetup ([video](https://www.youtube.com/watch?v=pkw_0I6iTYE&t=2263s)).  
- We didn't had time to work on the REPL or multimethods (still planning to).  

### Improvements  
* In `cljd.flutter`, `:watch` now always deduplicate values. Previously deduplication only happened when destructuring was used; the rationale for the change is that if `:watch` returns the same values the generated UI will be the same. The only exception is when mutable objects are repeatedly returned. That's why there's now the `:dedup false` option to turn off deduplication of a given watch binding.  
* We reimplemented dynamic bindings on top of Dart Zones, this has two benefits:  
  * making bindings conveyance automatic across all async mechanisms,  
  * allowing to catch async errors! There's a new `*async-error-handler*` dynamic var which can be bound to a function taking two arguments (exception and stacktrace), when bound it will catch uncaught errors happening in async mechanisms (futures, streams, async/await) started directly or indirectly by code under this binding!  
* In `cljd.flutter` we added helper directives for common widgets and cut on the boilerplate: `:color`, `:height`, `:width` and `:padding`.  
* This one is big for the ecosystem: we made it possible for cljd deps (managed via `deps.edn`) to have dependencies on Dart packages. This is big because it means that cljc or cljd libraries can use Dart dependencies without their own consumers having to redeclare them. The only requirement is for library authors when packaging to jars: copy pubspec.yaml` in the jar (or put a copy of it on the classpath before creating the jar).  
* We added a new special form: `dart/async-barrier`. Basically it's a `do` which prevents `await` from leaking outside of the `do` (so the `do` form will evaluate to a `Future`). This is important when writing macros to prevent user code (which may contain `await`) from contamining macro-generated code.  
* In `cljd.flutter` we added a `get-of` helper to retrieve widget-scope values bound with `:bind` from a `BuildContext` object.  
* We ported `clojure.data` to `cljd.data`.  
* We added `instance?` to ClojureDart. This is something I have been reluctant to do because in Dart you can't test an instance against a type value only against a type literal. That's why we have `dart/is?` which is a special form and not a function. However when porting code to ClojureDart having to rewrite all `instance?` calls to `dart/is?` is tedious -- and most of the time the type is passed as a literal to `instance?`. So to make porting easier we added an `instance?` function which throws when called as a function but is otherwise inline as a `dart/is?`. We do have [plans for a true `instance?`](https://github.com/tensegritics/ClojureDart/issues/11).  
* We added `into-array` which suffer from a similar problem to `instance?`: the type of an array has to be statically known in Dart, it can't be specified as a value. Here we followed CLJS lead and ignored the type for compatibility. Most certainly a behaviour which will have to be revisited when more libs are ported.
* Last we added the possibility to have conflicting method definitions in a `deftype`/`defrecord`/`reify`. However one of the conflicting methods must have an `^:override` meta. This was done to port some libraries with hacks around `defrecord`.  

### Fixes  
Several fixes to the compiler, cljd.core.  Amongst them:  
* type inference on `reify`  
* `apply` with some native Dart functions  
* `not-empty` inline was causing double evaluations  
* bug on `dissoc!` on transient hash maps  
* fix field lookup on defrecords fields whose names are not valid Dart names  
* `##NaN`, `##Infinite` and `##-Infinite` were not properly compiled  
* fix propagation of return-type hint in `defn` argvecs.  

### Future work  
### ClojureDart  
**New items:**  
* Migrate from Clojure's own reader to `tools.reader`. Currently ClojureDart uses `LispReader` from Clojure but this reader doesn't allow to ignore the `:clj` feature when reading `cljc` so it requires to modify a lot of reader conditionals, even some which shouldn't concern ClojureDart, and to order them precisely.  
* Value semantics on closures (not by default, as an option), this has the potential of simplifying caching in Flutter code.  

**Existing items:**  
* Publish the Flutter-only REPL  
* Multimethods to allow more libs to be ported  
* Look into porting SCI to ClojureDart  
* New APIs to leverage our persistent data structures:  
** maps (hash and sorted) in ClojureDart are original implementations (not the same as CLJ/CLJS) -- hash maps could be seen as another refinement of the original, sorted maps constitute a novel implementation.  
** Sorted colls should be good enough for direct use by Datascript.  
** Both hash and sorted maps can support accelerated merge/diff/join/etc. operations.  
* `cljd` CLI written in `cljd` for easier project creation etc.
* gen tests  
* ... <br>

---




## Thomas Heller  

### shadow-cljs  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (e.g. Clojurians Slack).  

Current shadow-cljs version: 2.52.10 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  

### Notable Updates  
- Fixed some [:dev-http](https://shadow-cljs.github.io/docs/UsersGuide.html#dev-http) issues that prevented proxying websockets or self-signed SSL certificates.  
- Fixed some node REPL issues, hopefully making it more reliable overall  <br>

---


## Nikita Prokopov  
(aka Niki Tonsky)  

Must be something in the air that makes you start new projects in the Fall. Three new ones has popped up:  

#### New project - [clj-simple-router](https://github.com/tonsky/clj-simple-router):  

- A minimal, order-independent Ring router
- [An introductory blog post](https://tonsky.me/blog/simple-router/)  

#### New project - [toml-clj](https://github.com/tonsky/toml-clj):  

- Fast TOML parser for Clojure  
- 2000x faster than existing Instaparse-based ones   
- 30x faster than tomlj (Java)  

#### New project - [extend-clj](https://github.com/tonsky/extend-clj):  

- Easily extend clojure.core built-in protocols  
- Both Clojure and ClojureScript  
- First version let you implement your own Atoms  
- More to come  

Old projects also have seen some love:  

#### [DataScript](https://github.com/tonsky/datascript/):  

- :xform is not called on ref attributes #455  
- Work started on automatic client-server synchronization. Don’t tell anyone, but you can [follow progess in the branch](https://github.com/tonsky/datascript/commits/sync)  

#### [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed):  

- Socket REPL: handle exceptions in lookup     
- Do not silence exception during lazy seq printing     
- Report number of reflection warnings in status bar  
- Eval inside already evaled region re-evals same region instead of going to top form  
- Printer can display newlines  

#### [Sublime Executor](https://github.com/tonsky/Sublime-Executor):  

- Don’t die if project has non-existing folders  

And, of course, blogging. Unicode article has seen its five minutes of fame:  

- [The Absolute Minimum Every Software Developer Must Know About Unicode in 2023 (Still No Excuses!)](https://tonsky.me/blog/unicode/)  <br> 

---

 

## Tommi Reiman  

Working mostly with Malli and Reitit. Will do an open source retreat to get planned things out in 2023.  

### Malli  

* Still WIP with the [new effective type system](https://github.com/metosin/malli/issues/264).  
* Lot's of small improvements released and waiting for a release.  

#### 0.13.0 (2023-09-24)  
* **BREAKING** Fallback to use result of first branch when decoding `:or` and `:orn`, [#946](https://github.com/metosin/malli/pull/946)  
* **BREAKING**: `decode` for `:double` and `double?` in cljs doesn't allow trailing garbage any more [#942](https://github.com/metosin/malli/pull/942)  
* Faster generators for `:map`,  [#948](https://github.com/metosin/malli/pull/948) & [#949](https://github.com/metosin/malli/pull/949)  
* FIX: `:altn` can't handle just one child entry when nested in sequence schema [#945](https://github.com/metosin/malli/pull/945)  
* Officially drop Clojure 1.10 support. Tests haven't passed for some time with Clojure 1.10, but this was not noticed due to a faulty CI setup.  
* Use type inferrer when encoding enums  [#951](https://github.com/metosin/malli/pull/951)  
* Use `bound-fn` in `malli.dev/start!` to preserve `*out*`  [#954](https://github.com/metosin/malli/pull/954)  
* FIX: Malli generates invalid clj-kondo type spec for [:map [:keys [:+ :keyword]]] [#952](https://github.com/metosin/malli/pull/952)  
* FIX: `malli.experimental.describe` descriptions of `:min` and `:max` are backwards [#959](https://github.com/metosin/malli/pull/959)  
* FIX: Malli tuple should generate clj-kondo seqable [#962](https://github.com/metosin/malli/pull/962)  

### Reitit  
#### 0.7.0-alpha7 (2023-10-03)  
* Revert the group id change from alpha6  
* New release to bring alpha6 changes to the old group id
* Updated dependencies:  

```clojure
[metosin/ring-swagger-ui "4.19.1"] is available but we use "4.18.1"  
```

#### 0.7.0-alpha6 (2023-09-11)  
* **BREAKING**: require Clojure 1.11, drop support for Clojure 1.10  
* **BREAKING**: new syntax for `:request` and `:response` per-content-type coercions. See [coercion.md](doc/ring/coercion.md).  [#627](https://github.com/metosin/reitit/issues/627)
* **BREAKING**: replace the openapi `:content-types` keyword with separate `:openapi/request-content-types` and `:openapi/response-content-types`. See [openapi.md](doc/ring/openapi.md)  
* **NOTE!**: all reitit libraries are now under the `fi.metosin` group on clojars instead of `metosin`. Use `fi.metosin/reitit` in your dependencies instead of `metosin/reitit` to get new versions.  
    - **Reverted in alpha7 due to problems with renaming artifacts**

### Jsonista  
* A maintanance release  

### Something else  

We had a family vacation in the Rhodes Island, which had massive wildfires earlier this year  

![image](https://gist.github.com/ikitommi/a1e62b3a35359259bfef94433ec2730b/raw/cb2161b4b8a75b38190940731cc02c01b407396a/image.png)

<br>

---


## Peter Stromberg  

### Calva  

I had a bit of vacation from hacking on Calva during September. Which does not mean I had (or need ??) a vacation from Calva, I kept pretty busy with user support. And it was not all pause on the hacking either, more that I couldn't really get a hold on some bugs and after some fruitless hunts I decided to wait for more information. Which I got, both from users and from my own investigations. There was also an issue with VS Code Insider builds, [stopping our CI pipeline completely](https://github.com/BetterThanTomorrow/calva/issues/2317). Calva uses the Insider branch of VS Code for integration testing. I was pretty sure the VS Code team would fix it, so I waited that out instead of installing workarounds. But not without spending considerable time on the issue before figuring out that it was upstream, and where upstream.  

The major issue hitting users was quite awful. [A hang in Calva's structural editor](https://github.com/BetterThanTomorrow/calva/issues/2299), which mostly manifested itself in moments of total Calva unresponsiveness. With the help of [Ingy döt Net](https://github.com/ingydotnet)'s ever more minimal reproductions we finally understood the problem and could fix it. ?? There is also some other hang hitting users of WSL, that we haven't figured out yet...  

Calva user [Albert Snow](https://github.com/AlbertSnows) discovered that [Calva's ClojureScript Quick Start REPLs were broken on Windows](https://github.com/BetterThanTomorrow/calva/issues/2325). He set the Calva development environment up and we could pair on finding the issue, which he then fixed! ??  

Also fixed:  
* An annoying glitch with [some Calva REPL commands not working when the cursor was inside the ns form](https://github.com/BetterThanTomorrow/calva/issues/2309).
* Another annoying glitch where[disconnecting and then reconnecting a REPL didn't work](https://github.com/BetterThanTomorrow/calva/issues/2301)  

### Giving myself a JavaScript REPl with Joyride  

As a not-too-frequent user of JavaScript, I often find myself lacking an easy way to experiment with JS code, especially in the way I am used to being able to experiment with Clojure code. I decided to use [Joyride](https://github.com/BetterThanTomorrow/joyride/tree/master) for it, and created a script that gives me a JavaScript REPL, right there in VS Code. It's still very simple, and I plan to return to it, making it a bit more sophisticated, but it is already pretty useful.  

* Joyride Examples entry: [Give yourself a JavaScript REPL](https://github.com/BetterThanTomorrow/joyride/tree/master/examples#give-yourself-a-javascript-repl)  

### Polylith Real World example in the browser  

When helping Joakim Tengstrand with feedback on an article about Polylith (pending), we realized that it would be very good with a way to test Polylith without having to set your machine up for Clojure development. So I created an experience for that, leveraging Gitpod and [Furkan Bayraktar](furkan3ayraktar)'s Real World example app.  
* [Hack on Real World Polylith in your Browser](https://github.com/furkan3ayraktar/clojure-polylith-realworld-example-app/blob/master/gitpod.md)  
* CalvaTV: [The Polylith Real World Example, with an IDE Running in The Browser](https://www.youtube.com/watch?v=FjdXjwcXaZg)  

Related: I also wrote about observing when [ChatGPT taught itself about Polylith from only a single diagram](https://blog.agical.se/en/posts/to-chatgpt-a-picture-says-more-than-a-thousand-words/) from that coming article.  

### Image composition using Babashka & Membrane  

But not together. Though enabling using [Membrane](https://github.com/phronmophobic/membrane) to [Babashka](https://babashka.org) does present itself as something I might try to do one of these days.  

Some venturing into image and text composition lead me to two different solutions. One invloving ImageMagick and Babashka, plus it had me create a missing Docker image. And one involving the super exciting Clojure UI library, Membrane:  

* Imagemagick + Pango + Babashka:  
  * Write up: [ImageMagick + Pango + Babashka = ??](https://blog.agical.se/en/posts/imagemagick--x--pango--x--babashka--x---x--x-/)  
  * CalvaTV: [Interactive Shell Scripting with Babashka](https://youtu.be/fa5ig2cIWnU)  
  * Docker image [cospaia/magick-pango-babashka](https://hub.docker.com/repository/docker/cospaia/magick-pango-babashka/)  
* Membrane  
  * Example project: [REPL Playground for adding back article info to links shared on X/Twitter](https://github.com/PEZ/x-meta-image)  
  * Write up: Pending...  
  * CalvaTV: [Interactive Programming with Clojure & Membrane](https://www.youtube.com/watch?v=ImBji-1bKkc)  
<br> 

---



## Peter Taoussanis  
### Open source update  
A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](/sponsors) of my open source work!  

### Recent work  

This was another productive period with 100% of my time going to open source. Highlights:  
- The usual support, hotfixes, etc.  
- [14 library releases](https://www.taoensso.com/clojure/news) including:  
    - [Carmine v3.3.0](https://github.com/taoensso/carmine/releases/tag/v3.3.0) with the first stable release of a significantly improved message queue [architecture](https://github.com/taoensso/carmine/wiki/0-Breaking-changes#architectural-improvements) and [API](https://github.com/taoensso/carmine/wiki/0-Breaking-changes#api-improvements).  

    - [Nippy v3.3.0](https://github.com/taoensso/nippy/releases/tag/v3.3.0) with updated [benchmarks](https://github.com/taoensso/nippy#performance), efficiency improvements, and experimental transducer support.  
    - [Nippy v3.4.0-beta1](https://github.com/taoensso/nippy/releases/tag/v3.4.0-beta1) with significant compression performance improvements, support for [Zstandard](http://facebook.github.io/zstd/), and a much improved `freezable?` util.  
    - [http-kit v2.8.0-beta2](https://github.com/http-kit/http-kit/releases/tag/v2.8.0-beta2) with auto Java 21+ virtual threads for both client+server, extensive new [benchmark suite and results](https://github.com/http-kit/http-kit/wiki/4-Benchmarking), early support for Ring-style async handlers, and more.  
- Continued work on [Tempel](https://www.taoensso.com/tempel), an upcoming **data security framework** for Clojure.  
- Continued work on [Telemere](https://www.taoensso.com/telemere), an upcoming **structured telemetry library** for Clojure/Script.  
- Did an interview with [Daniel Compton](https://twitter.com/danielwithmusic) (still to be published).  

### Upcoming work  

- [Tempel](https://www.taoensso.com/tempel) is nearing completion, I'm down to the final polishing and documentation. Aiming for the first public pre-release **early November**.  
- [Telemere](https://www.taoensso.com/telemere)'s first pre-release is then planned for **~mid December**.  

\- [Peter Taoussanis](https://www.taoensso.com)  

































