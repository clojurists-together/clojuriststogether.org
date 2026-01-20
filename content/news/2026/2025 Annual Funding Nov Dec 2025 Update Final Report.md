---
title: "Annually-Funded Developers' Update: November and December 2025"
date: 2026-01-20T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Djuric, Eric Dallo, Michiel Borkent, Oleksandr Yakushev, Peter Taoussanis"  
draft: True


---

Hello Fellow Clojurists!

This is the sixth and final report from the 5 developers who received Annual Funding in 2025. You can review their reports from throughout the year here:

Jan/Feb 2025  
March/April 2025  
May/June 2025  
July/Aug 2025  
Sept/Oct 2025  


Thanks everyone for the fantastic work! 



[Dragan Djuric](#dragan-djuric): Neanderthal, Deep Diamond, Diamond ONNX Runtime   
[Eric Dallo](#eric-dallo): ECA, clojure-lsp  
[Michiel Borkent](#michiel-borkent): Eucalypt, Reagent, Squint, babashka, and more...  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER nREPL, Orchard, clj-async-profiler, Virgil   
[Peter Taoussanis](#peter-taoussanis): Sente, Tempel, Carmine  



## Dragan Djuric    
2025 Annual Funding Report 6. Published January 4, 2026.  

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

Having achieved a lot of the goals for this project in 2025 in the previous 10 months,
in this funding period, my main effort was concentrated on the icing on the cake.  

- I improved support for various data types in Deep Diamond (including :double)  
- improved the implementation of the info method in DD  
- released Deep Diamond 0.41.0  
- improved cuda handling of :long descriptors  
- extended Neanderthal Vector with the support for Tensor protocols,
- extended Neanderthal Matrix with the support for Tensor protocols,  
- simplified the code in various places,  
- wrote new tests,  
- GE matrices are now also tensors  
- fixed the transfer support for integer matrices  
- implementated integer kernels for vectors and GE matrices in CUDA,  
- updated Apple BNNS tensor to be compatible with the changes in the last 3-4 months,  
- fixed leftover reflections in the old BNNS-based engine in DD  
- released Neanderthal 0.60.0,  
- released Deep Diamond 0.42.0,  
- Update upstream onnxruntime to 1.23.2, also build it locally with CUDA to update diamond-onnxrt,  
- released diamond-onnxrt 0.21.0  

I released several new versions of Uncomplicate libraries with these user-facing improvements including:  
- Deep Diamond  
- Neanderthal  
- Diamond ONNX Runtime  

Hammock time. Did some research on LLMs and the future Clojure implementation of high-level LLMs based on Diamond ONNX Runtime  

I also wrote a tutorial on dragan.rocks. I had plans to write more, but couldn't find time and energy, since December was especially full with lectures at the university, and I did not want to risk burnout :)  <br>

---



## Eric Dallo  
2025 Annual Funding Report 6. Published January 6, 2026.  

What a year! This was probably the year that I most commited and worked in some many things for Clojure community, all of that thanks to ClojuristsTogether, thank you very much!
In November I met so many people at ClojureConj that I work for 5 years and never met personally, it was a very good feeling to talk about so many subjects, projects, and hear about the best people in Clojure community. Check it out the picture with the biggest names in developer tooling for Clojure! 

![conj](./conj.jpg)
( From left to right: From left to right: Eric Dallo, Rich Hickey, Peter Stromberg, Michiel Borkent, Arthur Fucher)  

I spent some time preparing my talk that I gave there about ECA which should be available soon for anyone interested.  

### [eca](https://github.com/editor-code-assistant/eca)  
**ECA is growing even faster**, with more people using, testing, finding bugs, asking for features, and **I'm confident to say that after 6 months, ECA is a tool as good comparing with big players in the market, being free, OSS, written in Clojure and so much extensible, I'm really happy with the result so far and there are so many ideas and improvements I wanna do next year thanks to community sponsor and support!  

#### 0.78.2 - 0.87.2  
- Add workspaces to `/doctor`  
- Improve LLM request logs to include headers.  
- Add `openai/gpt-5.1` to default models.  
- Fix regression exceptions on specific corner cases with log obfuscation.  
- Fix absolute paths being interpreted as commands. #199  
- Remove non used sync models code during initialize. #100  
- Fix system prompt to mention the user workspace roots.  
- Improve system prompt to add project env context.  
- Add support to rollback messages via `chat/rollback` and `chat/clear` messages. #42  
- Add new models to GitHub config (Gpt 5.1 and Opus 4.5).  
- Update anthropic default models to include opus-4.5  
- Update anthropic default models to use alias names.  
- Fix binary for macos amd64. #217  
- Support rollback file changes done by `write_file`, `edit_file` and `move_file`. #218  
- Improve rollback to keep consistent UI before the rollback, fixing tool names and user messages.  
- Support nested folder for rules and commands. #220  
- Fix custom tools output to return stderr when tool error. #219  
- Support dynamic string parse (`${file:/path/to/something}` and `${env:MY_ENV}`) in all configs with string values. #200  
- Improve `/compact` UI in chat after running, cleaning chat and showing the new summary.  
- Better config values dynamic string parse:  
    - Support `${classapath:path/to/eca/classpath/file}` in dynamic string parse.  
    - Support `${netrc:api.foo.com}` in dynamic string parse to parse keys. #200  
    - Support default env values in `${env:MY_ENV:default-value}`.  
    - Support for ECA_CONFIG and custom config file.  
- Deprecate configs:  
  - `systemPromptFile` in favor of `systemPrompt` using `${file:...}` or `${classpath:...}`  
  - `urlEnv` in favor of `url` using `${env:...}`  
  - `keyEnv` in favor of `key` using `${env:...}`  
  - `keyRc` in favor of `key` using `${netrc:...}`  
  - `compactPromptFile` in favor of `compactPrompt` using `${classpath:...}`  
- Fix `${netrc:...} ` to consider `:netrcFile` config.  
- Fix `${netrc:...} ` to consider `:netrcFile` config properly.  
- Enhanced hooks documentation with new types (sessionStart, sessionEnd, chatStart, chatEnd), JSON input/output schemas, execution options (timeout)  
- Fix custom tools to support argument numbers.  
- Improve read_file summary to mention offset being read.  
- Enhanced hooks documentation with new types (sessionStart, sessionEnd, chatStart, chatEnd), JSON input/output schemas, execution options (timeout)  
- Support rollback only messages, tool call changes or both in `chat/rollback`.  
- Fix backwards compatibility for chat rollback.  
- Support `providers <provider> httpClient version` config, allowing to use http-1.1 for some providers like lmstudio. #229  
- Support `openai/gpt-5.2` and `github-copilot/gpt-5.2` by default.  
- Improve agent behavior prompt to mention usage of editor_diagnostics tool. #230  
- Use selmer syntax for prompt templates.  
- Support Google Gemini thought signatures.  
- Support `gemini-3-pro-preview` and `gemini-3-flash-preview` models in Google and Copilot providers.  
- Fix deepseek reasoning with openai-chat API #228  
- Support `~` in dynamic string parser.  
- Support removing nullable values from LLM request body if the value in extraPayload is null. #232  
- Improve read-file summary to show final range properly.  
- Improve model capabilities for providers which model name has slash: `my-provider/anthropic/my-model`  
- Fix openai-chat tool call + support for Mistral API #233  
- Skip missing/unreadable @file references when building context  
- Fix regression: /compact not working for some models. Related to #240  

### [clojure-lsp](https://clojure-lsp.io/)  
We finally support vertical alignment in clojure-lsp format via cljfmt, one of the most requested features!   
I started a sequence of lots of outdated bumps which fixed some issues and intend to finish on the next release as some break tests and some existing features.
Also, we had some new features like squint projects support!  

#### 2025.11.28-12.47.43  
- New keywords completion inside namespaced maps. #2113  
- Pass current namespace aliases to cljfmt when range-formatting. #2129  
- bump clj-kondo to `2025.10.24-20251120.193408-8` improving performance, fixing false-positives and supporting java inner classes.  
- Bump cljfmt to `0.15.5` - adding support for vertical alignment.  
- Support `squint` projects when having `squint.edn`. #2158  
- Support find definition of java inner classes (Foo$Bar). #2157  
- Bump babashka/fs to `0.5.28`.  
- Bump opentelemetry to `1.51.0`.  <br>

---


## Michiel Borkent  
2025 Annual Funding Report 6. Published January 6, 2026.  

In this post I'll give updates about open source I worked on during November and December 2025.  

To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors  

I'd like to thank all the sponsors and contributors that make this work
possible. Without you, the below projects would not be as mature or wouldn't
exist or be maintained at all! So a sincere thank you to everyone who
contributes to the sustainability of these projects.  

<img alt="gratitude" src="https://emoji.slack-edge.com/T03RZGPFR/gratitude/f8716bb6fb7e5249.png" width="50px" text-align="center">  

Current top tier sponsors:  

- [Clojurists Together](https://clojuriststogether.org/)
- [Roam Research](https://roamresearch.com/)
- [Nextjournal](https://nextjournal.com/)
- [Nubank](https://nubank.com.br)

Open the details section for more info about sponsoring.

<details>
<summary>Sponsor info</summary>

If you want to ensure that the projects I work on are sustainably maintained,
you can sponsor this work in the following ways. Thank you!

- [Github Sponsors](https://github.com/sponsors/borkdude)
- The [Babashka](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
- [Ko-fi](https://ko-fi.com/borkdude)
- [Patreon](https://www.patreon.com/borkdude)
- [Clojurists Together](https://www.clojuriststogether.org/)

</details>

<!--

- ls -lat ~/dev
- babashka sub dir checken

-->  

### Updates  

### Clojure Conj 2025  
Last November I had the honor and pleasure to visit the Clojure Conj 2025. I met
a host of wonderful and interesting long-time and new Clojurians, many that I've
known online for a long time and now met for the first time. It was especially exciting to finally meet Rich Hickey and talk to him during a meeting about Clojure dialects and Clojure tooling. The talk that I gave there: "Making tools developers actually use" will come online soon.  

<img src="https://photos.clojure-conj.org/uploads/medium2x/21/bb/eee18492f42518506f78b0f48587.jpg" width="100%" align="center" alt="presentation at Dutch Clojure meetup">

### Babashka conf and Dutch Clojure Days 2026  
In 2026 I'm organizing Babashka Conf 2026. It will be an afternoon event (13:00-17:00) hosted in the Forum hall of the beautiful public library of Amsterdam.
More information [here](https://babashka.org/conf/). Get your ticket via [Meetup.com](https://www.meetup.com/the-dutch-clojure-meetup/events/312079164/) (currently there's a waiting list, but more places will come available once speakers are confirmed). CfP will open mid January.
The day after babashka conf, Dutch Clojure Days 2026 will be happening. It's not too late to get your talk proposal in. More info [here](https://clojuredays.org/).  

### Clojurists Together: long term funding  
I'm happy to announce that I'm among the 5 developers that were granted Long term support for 2026. Thanks to all who voted! Read the announcement [here](https://www.clojuriststogether.org/news/clojurists-together-2026-annual-funding-announcement/).  

### Projects  
Here are updates about the projects/libraries I've worked on in the last two months in detail.  

- [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.  
  - Bump `process` to `0.6.25`
  - Bump `deps.clj`
  - Fix #1901: add `java.security.DigestOutputStream`
  - Redefining namespace with `ns` should override metadata
  - Bump `nextjournal.markdown` to `0.7.222`
  - Bump `edamame` to `1.5.37`
  - Fix [#1899](https://github.com/babashka/babashka/issues/1899): `with-meta` followed by `dissoc` on records no longer works
  - Bump `fs` to `0.5.30`
  - Bump `nextjournal.markdown` to `0.7.213`
  - Fix [#1882](https://github.com/babashka/babashka/issues/1882): support for reifying `java.time.temporal.TemporalField` ([@EvenMoreIrrelevance](https://github.com/EvenMoreIrrelevance))
  - Bump Selmer to `1.12.65`
  - SCI: `sci.impl.Reflector` was rewritten into Clojure
  - `dissoc` on record with non-record field should return map instead of record
  - Bump edamame to `1.5.35`
  - Bump `core.rrb-vector` to `0.2.0`
  - Migrate detecting of executable name for self-executing uberjar executable from `ProcessHandle` to to native image `ProcessInfo` to avoid sandbox errors
  - Bump `cli` to `0.8.67`
  - Bump `fs` to `0.5.29`
  - Bump `nextjournal.markdown` to `0.7.201`

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting  
  - Add support for `:refer-global` and `:require-global`
  - Add `println-str`
  - Fix [#997](https://github.com/babashka/sci/issues/997): Var is mistaken for local when used under the same name in a `let` body
  - Fix [#1001](https://github.com/babashka/sci/issues/1001): JS interop with reserved js keyword fails (regression of [#987](https://github.com/babashka/sci/issues/987))
  - `sci.impl.Reflector` was rewritten into Clojure
  - Fix [babashka/babashka#1886](https://github.com/babashka/babashka/issues/1886): Return a map when dissociating a
    record basis field.
  - Fix [#1011](https://github.com/babashka/sci/issues/1011): reset ns metadata when evaluating ns form multiple times
  - Fix for https://github.com/babashka/babashka/issues/1899
  - Fix [#1010](https://github.com/babashka/sci/issues/1010): add `js-in` in CLJS
  - Add `array-seq`  

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.<br>  
  - [#2600](https://github.com/clj-kondo/clj-kondo/issues/2600): NEW linter: `unused-excluded-var` to warn on unused vars in `:refer-clojure :exclude` ([@jramosg](https://github.com/jramosg))
  - [#2459](https://github.com/clj-kondo/clj-kondo/issues/2459): NEW linter: `:destructured-or-always-evaluates` to warn on s-expressions in `:or` defaults in map destructuring ([@jramosg](https://github.com/jramosg))
  - Add type checking support for `sorted-map-by`, `sorted-set`, and `sorted-set-by` functions
  ([@jramosg](https://github.com/jramosg))
  - Add new type `array` and type checking support for the next functions: `to-array`, `alength`,
  `aget`, `aset` and `aclone` ([@jramosg](https://github.com/jramosg))
  - Fix [#2695](https://github.com/clj-kondo/clj-kondo/issues/2696): false positive `:unquote-not-syntax-quoted` in leiningen's `defproject`
  - Leiningen's `defproject` behavior can now be configured using `leiningen.core.project/defproject`
  - Fix [#2699](https://github.com/clj-kondo/clj-kondo/issues/2699): fix false positive unresolved string var with extend-type on CLJS
  - Rename `:refer-clojure-exclude-unresolved-var` linter to `unresolved-excluded-var` for consistency
  - v2025.12.23
  - [#2654](https://github.com/clj-kondo/clj-kondo/issues/2654): NEW linter: `redundant-let-binding`, defaults to `:off` ([@tomdl89](https://github.com/tomdl89))
  - [#2653](https://github.com/clj-kondo/clj-kondo/issues/2653): NEW linter: `:unquote-not-syntax-quoted` to warn on `~` and `~@` usage outside syntax-quote (`` ` ``) ([@jramosg](https://github.com/jramosg))
  - [#2613](https://github.com/clj-kondo/clj-kondo/issues/2613): NEW linter: `:refer-clojure-exclude-unresolved-var` to warn on non-existing vars in `:refer-clojure :exclude` ([@jramosg](https://github.com/jramosg))
  - [#2668](https://github.com/clj-kondo/clj-kondo/issues/2668): Lint `&` syntax errors in let bindings and lint for trailing `&` ([@tomdl89](https://github.com/tomdl89))
  - [#2590](https://github.com/clj-kondo/clj-kondo/issues/2590): `duplicate-key-in-assoc` changed to `duplicate-key-args`, and now lints `dissoc`, `assoc!` and `dissoc!` too ([@tomdl89](https://github.com/tomdl89))
  - [#2651](https://github.com/clj-kondo/clj-kondo/issues/2651): resume linting after paren mismatches
  - [clojure-lsp#2651](https://github.com/clojure-lsp/clojure-lsp/issues/2157): Fix inner class name for java-class-definitions.
  - [clojure-lsp#2651](https://github.com/clojure-lsp/clojure-lsp/issues/2157): Include inner class java-class-definition analysis.
  - Bump `babashka/fs`
  - [#2532](https://github.com/clj-kondo/clj-kondo/issues/2532): Disable `:duplicate-require` in `require` + `:reload` / `:reload-all`
  - [#2432](https://github.com/clj-kondo/clj-kondo/issues/2432): Don't warn for `:redundant-fn-wrapper` in case of inlined function
  - [#2599](https://github.com/clj-kondo/clj-kondo/issues/2599): detect invalid arity for invoking collection as higher order function
  - [#2661](https://github.com/clj-kondo/clj-kondo/issues/2661): Fix false positive `:unexpected-recur` when `recur` is used inside `clojure.core.match/match` ([@jramosg](https://github.com/jramosg))
  - [#2617](https://github.com/clj-kondo/clj-kondo/issues/2617): Add types for `repeatedly` ([@jramosg](https://github.com/jramosg))
  - Add `:ratio` type support for `numerator` and `denominator` functions ([@jramosg](https://github.com/jramosg))
  - [#2676](https://github.com/clj-kondo/clj-kondo/issues/2676): Report unresolved namespace for namespaced maps with unknown aliases ([@jramosg](https://github.com/jramosg))
  - [#2683](https://github.com/clj-kondo/clj-kondo/issues/2683): data argument of `ex-info` may be nil since clojure 1.12
  - Bump built-in ClojureScript analysis info
  - Fix [#2687](https://github.com/clj-kondo/clj-kondo/issues/2687): support new `:refer-global` and `:require-global` ns options in CLJS
  - Fix [#2554](https://github.com/clj-kondo/clj-kondo/issues/2544): support inline configs in `.cljc` files  

- [edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more  
Edamame: configurable EDN and Clojure parser with location metadata and more
  - Minor: leave out `:edamame/read-cond-splicing` when not splicing
  - Allow `:read-cond` function to override `:edamame/read-cond-splicing` value
  - The result from `:read-cond` with a function should be spliced. This behavior differs from `:read-cond` + `:preserve` which always returns a reader conditional object which cannot be spliced.
  - Support function for `:features` option to just select the first feature that occurs

- [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler  
  - Allow macro namespaces to load `"node:fs", etc.` to read config files for conditional compilation
  - Don't emit IIFE for top-level let so you can write `let` over `defn` to capture values.
  - Fix `js-yield` and `js-yield*` in expression position
  - Implement `some?` as macro
  - Fix [#758](https://github.com/squint-cljs/squint/issues/758): `volatile!`, `vswap!`, `vreset!`
  - `pr-str`, `prn` etc now print EDN (with the idea that you can paste it back into your program)
  - new `#js/Map` reader that reads a JavaScript `Map` from a Clojure map (maps are printed like this with `pr-str` too)
  - Support passing keyword to `mapv`
  - [#759](https://github.com/squint-cljs/squint/issues/759): `doseq` can't be used in expression context
  - Fix [#753](https://github.com/squint-cljs/squint/issues/753): optimize output of dotimes
  - `alength` as macro  

- [reagami](https://github.com/borkdude/reagami): A minimal zero-deps Reagent-like for Squint and CLJS  
  - Performance enhancements
  - treat `innerHTML` as a property rather than an attribute
  - Drop support for camelCased properties / (css) attributes
  - Fix `:default-value` in input range
  - Support data param in `:on-render`
  - Support default values for uncontrolled components
  - Fix child count mismatch
  - Fix re-rendering/patching of subroots
  - Add `:on-render` hook for mounting/updating/unmounting third part JS components

- NEW: [parmezan](https://github.com/borkdude/parmezan): fixes unbalanced or unexpected parens or other delimiters in Clojure files  
- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
  - [#126](https://github.com/babashka/cli/issues/126): `-` value accidentally parsed as option, e.g. `--file -`
  - [#124](https://github.com/babashka/cli/issues/124): Specifying exec fn that starts with hyphen is treated as option
  - Drop Clojure 1.9 support. Minimum Clojure version is now 1.10.3.  

- [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
  - always analyze doc (but not deps) when no-cache is set (#786)
  - add option to disable inline formulas in markdown (#780)  

- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
  - [#114](https://github.com/babashka/scittle/issues/114): Enable source maps ([@jeroenvandijk](https://github.com/jeroenvandijk))
  - [#140](https://github.com/babashka/scittle/issues/140): Enable customizing the nrepl websocket port ([@PEZ](https://github.com/PEZ))
  - Bump shadow-cljs and SCI  

- [Nextjournal Markdown](https://github.com/nextjournal/markdown)  
  - Add config option to avoid TeX formulas
  - API improvements for passing options

- [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler  
  - Fix `cherry compile` CLI command not receiving file arguments
  - Bump shadow-cljs to `3.3.4`
  - Fix [#163](https://github.com/squint-cljs/cherry/issues/163): Add assert to macros ([@willcohen](https://github.com/willcohen))
  - Fix [#165](https://github.com/squint-cljs/cherry/issues/165): Fix ClojureScript protocol dispatch functions ([@willcohen](https://github.com/willcohen))
  - Fix [#167](https://github.com/squint-cljs/cherry/issues/167): Protocol dispatch functions inside IIFEs; bump squint [accordingly](https://github.com/squint-cljs/squint/commit/db0e3c7b831568a3766e9ade0e2a05490446bfe5)
  - Fix [#169](https://github.com/squint-cljs/cherry/issues/169): fix `extend-type` on `Object`
  - Fix [#171](https://github.com/squint-cljs/cherry/issues/171): Add `satisfies?` macro ([@willcohen](https://github.com/willcohen))  

- [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure  
  - Released several versions catching up with the clojure CLI  

- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure  
  - Fix extra newline in codeblock  

- [quickblog](https://github.com/borkdude/quickblog): light-weight static blog engine for Clojure and babashka  
  - Add support for a blog contained within another website; see [Serving an alternate content root](https://github.com/borkdude/quickblog/README.md#serving-an-alternate-content-root) in README.  ([@jmglov](https://github.com/jmglov))
  - Upgrade babashka/http-server to 0.1.14
  - Fix `:blog-image-alt` option being ignored when using CLI (`bb quickblog render`)  

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI  
  - [#395](https://github.com/babashka/nbb/issues/395): fix `vim-fireplace` infinite loop on nREPL session close.
  - Add `ILookup` and `Cons`
  - Add `abs`
  - nREPL: support `"completions"` op  

- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects.<br>
  - neil.el - a hook that runs after finding a package ([@agzam](https://github.com/agzam))
  - neil.el - adds a function for injecting a found package into current CIDER session ([@agzam](https://github.com/agzam))
  - [#245](https://github.com/babashka/neil/issues/245): neil.el - neil-executable-path now can be set to `clj -M:neil`
  - [#251](https://github.com/babashka/neil/issues/251): Upgrade library deps-new to 0.10.3
  - [#255](https://github.com/babashka/neil/issues/255): update maven search URL

- [fs](https://github.com/babashka/fs) - File system utility library for Clojure
  - [#154](https://github.com/babashka/fs/issues/154) reflect in directory check and docs that `move` never follows symbolic links ([@lread](https://github.com/lread))
  - [#181](https://github.com/babashka/fs/issues/181) `delete-tree` now deletes broken symbolic link `root` ([@lread](https://github.com/lread))
  - [#193](https://github.com/babashka/fs/issues/193) `create-dirs` now recognizes sym-linked dirs on JDK 11 ([@lread](https://github.com/lread)) 
  - [#184](https://github.com/babashka/fs/issues/184): new check in `copy-tree` for copying to self too rigid
  - [#165](https://github.com/babashka/fs/issues/165): `zip` now excludes `zip-file` from `zip-file` ([@lread](https://github.com/lread))
  - [#167](https://github.com/babashka/fs/issues/167): add `root` fn which exposes `Path` `getRoot` ([@lread](https://github.com/lread))
  - [#166](https://github.com/babashka/fs/issues/166): `copy-tree` now fails fast on attempt to copy parent to child ([@lread](https://github.com/lread))
  - [#152](https://github.com/babashka/fs/issues/152): an empty-string path `""` is now (typically) understood to be the current working directory (as per underlying JDK file APIs) ([@lread](https://github.com/lread))
  - [#155](https://github.com/babashka/fs/issues/155): `fs/with-temp-dir` clj-kondo linting refinements ([@lread](https://github.com/lread))
  - [#162](https://github.com/babashka/fs/issues/162): `unixify` no longer expands into absolute path on Windows (potentially BREAKING)
  - Add return type hint to `read-all-bytes`

- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes  
  - [#181](https://github.com/babashka/process/issues/181): support `:discard` or `ProcessBuilder$Redirect` as `:out` and `:err` options  

Contributions to third party projects:  

- [ClojureScript](https://github.com/clojure/clojurescript)
  - CLJS-3466: support qualified method in return position
  - CLJS-3468: :refer-global should not make unrenamed object available

### Other projects

These are (some of the) other projects I'm involved with but little to no activity
happened in the past month.  

<details>
<summary>Click for more details</summary>
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
- [http-server](https://github.com/babashka/http-server): serve static assets
- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
- [http-client](https://github.com/babashka/http-client): babashka's http-client
- [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag
- [instaparse-bb](https://github.com/babashka/instaparse-bb): Use instaparse from babashka
- [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
- [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one command
- [qualify-methods](https://github.com/borkdude/qualify-methods)
  - Initial release of experimental tool to rewrite instance calls to use fully
    qualified methods (Clojure 1.12 only)
- [tools](https://github.com/borkdude/tools): a set of [bbin](https://github.com/babashka/bbin/) installable scripts
- [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter
- [speculative](https://github.com/borkdude/speculative)
- [squint-macros](https://github.com/squint-cljs/squint-macros): a couple of
  macros that stand-in for
  [applied-science/js-interop](https://github.com/applied-science/js-interop)
  and [promesa](https://github.com/funcool/promesa) to make CLJS projects
  compatible with squint and/or cherry.
- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo): a leiningen plugin for clj-kondo
- [http-kit](https://github.com/http-kit/http-kit): Simple, high-performance event-driven HTTP client+server for Clojure.
- [babashka.nrepl](https://github.com/babashka/babashka.nrepl): The nREPL server from babashka as a library, so it can be used from other SCI-based CLIs
- [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
- [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
- [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
- [babashka.book](https://github.com/babashka/book): Babashka manual
- [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
- [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
- [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
- [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
- [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
- [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
- [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI

</details>

<br>

---  


## Oleksandr Yakushev  
2025 Annual Funding Report 6. Published January 8, 2026.  

Hello friends! Here's my update on November-December 2025 Clojurists Together work. I've dedicated time in equal parts to improving clj-async-profiler and to working on nREPL/CIDER stack.  

### clj-async-profiler  
I've released the first beta version of the quite disruptive 2.0.0 release of the profiler. The new version contains a whole new type of graph - a heatgraph - which incorporates time as a separate dimension, and allows constructing on-demand flamegraphs over arbitrary time slices. This new version also migrates from profiles as collapsed TXT files to the industry-standard JFR (Java Flight Recorder) profiles which incorporate more data and are more compact and efficient.  

- Released `2.0.0-beta` with support for JFR profiler data and heatgraphs.  
- [45](https://github.com/clojure-goes-fast/clj-async-profiler/issues/45): Released version 1.7.0 for the older pre-2.0.0 branch of the profiler to restore compatibility with Linux kernerl 6.17.  

### nREPL  
nREPL [1.6.0-alpha1](https://github.com/nrepl/nrepl/releases/tag/v1.6.0-alpha1) has been prepared which contains many updates that improve the stability of nREPL and simplify its codebase. nREPL is continually moving towards its original goal of being a simple reliable foundation for other tools to build upon.  

- [#403](https://github.com/nrepl/nrepl/pull/403): Fix off by 1 error in CallbackBufferedOutputStream.  
- [#408](https://github.com/nrepl/nrepl/pull/408): Refactor `stdin` middleware.  
- [#409](https://github.com/nrepl/nrepl/pull/409): Refactor handler construction and middleware application.  

### Orchard  
Plenty of work has gone into Orchard, mostly on the inspector and doc/Javadoc side. These changes will go into the next named CIDER release.  

- [#362](https://github.com/clojure-emacs/orchard/pull/362): Info: don't crash if symbol contains $ or /.  
- [#363](https://github.com/clojure-emacs/orchard/pull/363): Inspector: show duplicates in analytics.  
- [#365](https://github.com/clojure-emacs/orchard/pull/365): Inspector: don't datafy vars.
- [#367](https://github.com/clojure-emacs/orchard/pull/367): Inspector: pretty-print arrays distinctively from vectors.  
- [#369](https://github.com/clojure-emacs/orchard/pull/369): Inspector: group methods by declaring class.  
- [#370](https://github.com/clojure-emacs/orchard/pull/370): Inspector: dedicated view for methods.  
- [#371](https://github.com/clojure-emacs/orchard/pull/371): Inspector: analytics improvements.  
- [#368](https://github.com/clojure-emacs/orchard/pull/368): Print: correctly render empty records.  

### cider-nrepl  
Here I worked on the dependency footprint of the library. Cider-nrepl has many dependencies and a complicated solution to managing and shading them.  

* [#956](https://github.com/clojure-emacs/cider-nrepl/pull/956): [ci] Include nrepl dependency into releases again.  
* [#959](https://github.com/clojure-emacs/cider-nrepl/pull/959): Stop shading Compliment and clj-reload.  

### Virgil  
Released Virgil 0.5.1.  

- [#45](https://github.com/clj-commons/virgil/pull/45): Address reflection warnings.  
- [#46](https://github.com/clj-commons/virgil/pull/46): Enable linting on CI.  <br>

---

## Peter Taoussanis  
2025 Annual Funding Report 6. Published January 9, 2026.   

Hi everyone 👋  

Another year behind us! I hope everyone had a peaceful break, and managed to get some quality time with family/friends/pets ^^  

2025 was a productive year for my Clojure projects with >36 non-trivial [library releases](https://www.taoensso.com/news#2025) and one [talk](https://youtu.be/IlV8R6k8XvY) ("Effective Open Source Maintenance Maintenance").  

**Some highlights included:**  
- [Telemere v1](https://github.com/taoensso/telemere/releases/tag/v1.0.0) (now at [v1.2.1](https://github.com/taoensso/telemere/releases/tag/v1.2.1)) - Structured logs and telemetry for Clj+s  
- [Tempel v1](https://github.com/taoensso/tempel/releases/tag/v1.0.0) (now at [v1.1](https://github.com/taoensso/tempel/releases/tag/v1.1.0)) - Data security framework for Clj  
- [Trove v1](https://github.com/taoensso/trove/releases/tag/v1.0.0) (now at [v1.1](https://github.com/taoensso/trove/releases/tag/v1.1.0)) - Modern logging facade for Clj+s  
- [Tufte v3](https://github.com/taoensso/tufte/releases/tag/v3.0.0) - Simple performance monitoring library for Clj+s  
- [Sente v1.21](https://github.com/taoensso/sente/releases/tag/v1.21.0) - Realtime web comms library for Clj+s  
- [Truss v2](https://github.com/taoensso/truss/releases/tag/v2.0.0) (now at [v2.3](https://github.com/taoensso/truss/releases/tag/v2.3.0)) - Micro toolkit for Clj+s errors  
- [http-kit v2.9b1](https://github.com/http-kit/http-kit/releases/tag/v2.9.0-beta1) (now at [beta3](https://github.com/http-kit/http-kit/releases/tag/v2.9.0-beta3)) - Fast HTTP client+server for Clj  
- [Carmine v3.5](https://github.com/taoensso/carmine/releases/tag/v3.5.0) - Redis client + message queue for Clj  

My main unifying themes for the year were **observability** and **documentation**. I'm pretty happy now with the combo of Telemere, Trove, Tufte, and Truss. These work well together as a sort of opinionated **observability suite** for serious Clojure applications. And I think that together they offer a pretty compelling demonstration of some of the kinds of real-world advantages Clojure can offer people trying to get things done at scale.  

As always, a very big and warm *thank you* to the many users and contributors that helped patiently test, give feedback, report bugs, and offer improvements over the year. The Clojure community has always been and continues to be a special one ❤️  

Likewise a big thank you to the companies and folks (incl. [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors)) that have helped support my OSS work financially! It's been great being able to dedicate so much time to open source, and it's something I feel very grateful to have been able to benefit from 🙏  

Those closely following my releases may have noted that I was unusually quiet over Nov/Dec. tl;dr ended up with some unexpected Life stuff coming up that has required the bulk of my attention and energy. So a few releases I had planned for that period will need to wait until later (likely Q2 2026).  

Relatedly, I should warn that I might need to deload a little in 2026. Will still be providing maintenance and support as usual, just not sure yet to what extent I'll be able to contribute the usual amount of attention to substantial greenfield work.  

I'm in Clojure for the long haul, and will continue to be present and as active as I'm able- things may just be a little bit more unpredictable on my side for the next few months as I see how next developments play out.  

Best wishes to everyone for the new year, and much love to you all! 🫶  

\- [Peter Taoussanis](https://www.taoensso.com)