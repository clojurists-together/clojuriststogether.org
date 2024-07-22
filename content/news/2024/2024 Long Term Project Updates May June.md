title: "May & June 2024 Long-Term Project Updates"
date: 2024-07-22T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Peter Taoussanis"  
draft: True


---
 

A huge thank you to our 2024 long-term developers for their amazing work in May and June. Check out their latest project updates!


[Bozhidar Batsov:](#bozhidar-batsov) CIDER       
[Michiel Borkent:](#michiel-borkent) squint, neil, clj-kondo,nbb, CLI, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs  
[Kira McLean:](#kira-mclean) Scicloj Libraries and more   
[Nikita Prokopov:](#nikita-prokopov) Datascript, Clj-reload, Clojure Sublimed, and more  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli    
[Peter Taoussanis:](#peter-taoussanis) http-kit, Nippy, Telemere, and more  


## Bozhidar Batsov   
This period was quite busy and productive for CIDER and friends. The highlights are 3 (!!!) CIDER releases and a couple of nREPL releases:  

- [CIDER 1.14 ("Verona")](https://github.com/clojure-emacs/cider/releases/tag/v1.14.0)  
- [CIDER 1.15 ("Cogne")](https://github.com/clojure-emacs/cider/releases/tag/v1.15.0)  
- [CIDER 1.15.1 ](https://github.com/clojure-emacs/cider/releases/tag/v1.15.1) (small bug-fix release)  
- [nREPL 1.1.2](https://github.com/nrepl/nrepl/releases/tag/v1.1.2) (small bug-fix release)  
- [nREPL 1.2.0](https://github.com/nrepl/nrepl/releases/tag/v1.2.0)  

CIDER 1.14 is our most ambitious release since CIDER 1.8 ("Geneva"), that got released last autumn.  

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

If you're used to sponsoring through some other means which isn't listed above, please get in touch.

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





---

## Toby Crawley  






---

## 
