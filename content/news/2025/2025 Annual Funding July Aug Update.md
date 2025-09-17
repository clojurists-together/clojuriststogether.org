---
title: "Annually-Funded Developers' Update: July/August 2025"
date: 2025-09-17T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Oleksandr Yakushev, Peter Taoussanis"  


---

Hello Fellow Clojurists!
This is the fourth report from the 5 developers receiving Annual Funding in 2025. 


[Dragan Duric](#dragan-duric): Apple M Engine Neanderthal, Deep Diamond CPU   
[Eric Dallo](#eric-dallo): ECA, clojure-lsp, clojure-lsp-intellij  
[Michiel Borkent](#michiel-borkent): clj-kondo, babashka, SCI, clj-kondo, scittle, and more...  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER, clj-async-profiler, nREPL   
[Peter Taoussanis](#peter-taoussanis): Sente, Truss, Trove, Telemere  


## Dragan Duric  
2025 Annual Funding Report 4. Published September 4, 2025.  

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

Having a decent Apple CPU engine for Neanderthal completed in the May-June period, I could continue building on the work on the Deep Diamond CPU engine for Apple hardware that I've started.  

I had already found out that Apple's low-level APIs (BNNS) are not very well thought out (as I wrote in the last report), so I expected it to be a not so pleasant march. And it wasn't. No wonder why large parts of it are already deprecated, in favor of Graph API (which does not replace it's functionality, but is a completely alternative way of dealing with tensors and Deep Learning).  

However, there were no room for quitting, since we need at least a basic tensor functionality, regardless of DNN operations, so we can later potentially integrate the Graph API and other tensor-based libraries. Besides that, the NDArray and Tensor parts of BNNS is not deprecated, so that's the stuff we have to work with.  

I will spare you the gory details, including lots of segfaults and WTFs, but in the end I managed to tame it, and even fit it into the existing deep-diamond/dnnl API, with backward compatibility (not including the convolution and rnn ops which I found not worth trying to tame at this stage).  

Then, just at the end of the month, I even managed to tidy up a Apple silicon enabled Deep Diamond release (0.35.2, 0.35.3).  

#### It's in the Clojars!  

I didn't have time and resources to advertise it wildly right away, and the next day I already started working on the ONNX Runtime integration, so it's still an Easter egg for the dedicated folks who actually read these reports. :)  

I've already worked on Neanderthal, and made a release with assorted bugfixes and improvements.  

It's not a glamurous work, working at these lower levels, but I see bright future for Clojure tensors. Cheers!  <br>

---

## Eric Dallo  
2025 Annual Funding Report 4. Published September 8, 2025. 

In these last 2 months I mainly focused on my recently created project, [ECA](https://eca.dev) and its related projects, there were so many improvements and new features, the project grown a lot with lots of people using!  

### [eca](https://github.com/editor-code-assistant/eca)  
ECA (Editor Code Assistant) is a OpenSource, free, standardized server written in Clojure to make any editor have AI features like Cursor, Continue, Claude and others.  

#### 0.2.0 - 0.43.1  
There were so many releases I can't just put the whole changelog here hehe, but the main highlights were:  
- __Web page___: A whole new site detailing the project, docs and features: [https://eca.dev](https://eca.dev)  
- __New models___: Anthropic (with subscription too), OpenAI, Github Copilot, Z.AI, OpenRouter, Azure and many others  
- __Custom providers___: It's possible to configure custom providers for your models.  
- __Command support___: built-in commands + mcp commands + eca commands + custom prompts. Ex: `/login`, `/my-prompt`  
- __Plan behavior___: Plan your changes before tellin LLM to act.  
- __AGENTS.md___: `/init` your project to update/create a AGENTS.md file so ECA will have more context.  
- __Editor diagnostics__: New tool `eca_editor_diagnostics` which gets LSP diagnostics about your project.  

And many more! come to #eca Clojurians channel.  

#### [eca-emacs](https://github.com/editor-code-assistant/eca-emacs)  
After testing other tools, improving a lot and receiving positive feedbacks, I believe ECA emacs offers the best Emacs tool for AI development right now, which is great, there are still so many features to add!  

![eca-emacs (1)](https://github.com/user-attachments/assets/81cdc51a-0be4-409c-81e7-db6af75b377a)


#### [eca-intellij](https://github.com/editor-code-assistant/eca-intellij)  
New editor support! :tada:  
Using the same UX from eca-vscode.  

![eca-intellij](https://github.com/user-attachments/assets/d85d0a97-b918-4093-8380-44525922c2ca)


#### [eca-vscode](https://github.com/editor-code-assistant/eca-vscode)  
Multiple improvements, especially extracted the webview to `eca-webview` so it can be used by eca-intellij as well.  

![eca-vscode](https://github.com/user-attachments/assets/bd835c29-9668-4f14-9bfd-70bf2d1c0f26)



### [clojure-lsp](https://clojure-lsp.io/)  
There were mainly improvements in performance regarding clj-kondo bumps and some small fixes.
Also now we have a new custom linter `clojure-lsp/cyclic-dependencies`!  

#### 2025.08.15-15.37.37 - 2025.08.25-14.21.46  
- Docs  
  - update neovim editor configuration for clojure lsp  

- General  
  - New feature: Add `clojure-lsp/cyclic-dependencies` linter to detect cyclic dependencies between namespaces in the project.  
    - Change `clojure-lsp/cyclic-dependencies` custom linter default level to be `off` until corner cases are fixed.  
  - New optional `:kondo-config-dir` setting to configure clj-kondo execution.  
  - Parallelize and log the time spent on built-in linters execution.  
  - Fix #1851: Error when source files have non-ASCII characters in their path or name
  - Fix caching issue when :source-aliases changes. #2081  
  - Fix emitting invalid messages if there's an internal error.  
  - Bump clj-kondo to `2025.07.28` considerably improving memory usage.  
  
- Editor  
  - Avoid lint .lsp/stubs folder when starting.  <br>

  ---

## Michiel Borkent  
2025 Annual Funding Report 4. Published September 5, 2025. 

In this post I'll give updates about open source I worked on during July and August 2025.  

To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work
possible. Without you the below projects would not be as mature or wouldn't
exist or be maintained at all! So a sincere thank you to everyone who
contributes to the sustainability of these projects.  

c:\Users\kathl\Documents\Documents\Clojurists Together\2025 Reports\mayJune lng term 2025\mb-switzerland-2025.jpeg

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
- The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
- [Ko-fi](https://ko-fi.com/borkdude)
- [Patreon](https://www.patreon.com/borkdude)
- [Clojurists Together](https://www.clojuriststogether.org/)

</details>

<!--

- TODO: mention upcoming talk at Clojure Conj 2025!

- ls -lat ~/dev
- babashka sub dir checken

-->

### Updates

Although summer hit Europe and I made a train trip to Switzerland for some hiking with my wife, OSS activity continued in the borkiverse. 20 projects saw updates. As usual, babashka, SCI and clj-kondo saw the most activity. 
<img width="600" height="500" alt="image" src="https://github.com/user-attachments/assets/7c6e5937-7e0d-4285-a036-26fdbf1a84ff" />

One of the big things I’m looking forward to is speaking at [Clojure Conj 2025](https://www.2025.clojure-conj.org/schedule). At the risk of sounding a bit pretentious, the title of my talk is "Making Tools Developers Actually Use". Babashka started as a quirky interpreter "nobody had asked for" but now many Clojure developers don't want to live without it. Clj-kondo started out as a minimal proof-of-concept linter and now is widely used tool in Clojurian's every day toolset and available even in Cursive today. In the talk I want to reflect on what makes a tool something developers (like myself) actually want to use. I'm excited about this opportunity and about my first time visiting the Conj (don't ask me how I got the Clojure Conj cap on the photo above). Given the rest of the schedule, it's something I wouldn't want to miss.  

For babashka, my main focus has been making it feel even more like regular Clojure. One example is the change in how non-daemon threads are handled. Previously, people had to sometimes add sometimes `@(promise)` to keep an httpkit server alive. Now babashka behaves like `clojure -X` in this regard: if you spawn non-daemon threads, the process waits for them. It’s looks like a small change, but it brings consistency with JVM Clojure, something I'm always aiming for more with babashka. If you want the old behavior, you can still use `--force-exit`. While implementing this I hit an interesting bug with [GraalVM](https://github.com/oracle/graal/issues/12116) and also found out that `clojure -X` sometimes stalls when using agents. Maybe more on this next time.  

Another change that was introduced is that when code is evaluated through `load-string` or `Compiler/load` (which is the same thing in bb), vars like `*warn-on-reflection*` are bound. This fixes a problem with loading code in non-main threads. E.g. `@(future (load-string "(set! *warn-on-reflection* true)"))` would fail in previous versions of babashka. You might wonder why you would ever want to do this. Well, a similar thing happens when you execute babashka tasks in parallel and that's where I ran into this problem.  

SCI, the interpreter under the hood of babashka and several other projects, got some critical fixes as well. I detected one somewhat embarrasing bug when loading `clojure+.hashp` in babashka. It had code that looked like:  


``` clojure
(def config {})
(let [config {}
      _ (alter-var-root #'config (constantly config))
     ]
  ...)
```

In the expression `(alter-var-root #'config (constantly config))` the var `#'config` was mistaken for the local `config` since SCI's analyzer used a `resolve`-like function that also resolves locals. This fails horribly. In 6 years of SCI it's the first time I encountered this bug though. After fixing this problem, I noticed that babashka's CI acted up. On every commit, babashka CI tests dozens of Clojure libraries by running their test suites. I noticed that specter's tests were failing. It turned out that one test actually worked prior to fixing the above bug exactly because the SCI analyzer's `resolve` returned a node that evaluated to a local value. But there is no way I could just leave that bug in, so I had to make a pull request to specter as well to set this straight. A new specter version was released that works both with older version of babashka and the new version.  

One other headscratcher in SCI was on the ClojureScript side of things and had to do with munging. In interop like `(.-foo-bar #js {:foo-bar 1})` ClojureScript munges the field name in the interop form to `foo_bar` but in the object it stays `"foo-bar"`. The munging of this name wasn't applied in SCI as an oversight. So in SCI (and thus in nbb, joyride, scittle, etc.) the above expression would return `1` whereas in ClojureScript it would return `nil`. In contrast, `(.-foo-bar #js {:foo_bar 1})` would return `nil` in SCI but `1` in CLJS. Although fixing this could mean a breaking change in SCI-based scripting environments I decided to align it with CLJS anyway, as switching between SCI and CLJS should not introduce these kinds of surprises.  

Other improvements in SCI were made in the area of better using type hints on instance method interop.  


And then there’s clj-kondo, the linter that is supposed to spark joy ✨, as far as a linter is able to do that in a developer's life. Two new linters were added, including one that catches suspicious uses of locking. This linter was inspired by a similar rule in splint. Lots of smaller improvements were made like sorting findings and imported files such that they are consistent across multiple runs that use the `--parallel` option and across operating systems. And as usual bugfixes and preventing false positives.  

One happy improvement to [scittle]() is that referencing a library that was introduced by a `<script>` tag now was made a lot easier. You can find the docs about that [here](https://github.com/babashka/scittle/blob/main/doc/js-libraries.md). The tl;dr of this is that when a library registers itself as a global, you can just use that global in `:require` now: `(require '["JSConfetti" :as confetti])`.  

Of course, none of this happens in isolation. I’m deeply grateful to the community and the sponsors who make this work sustainable: Clojurists Together, Roam Research, Nextjournal, Nubank, and many other companies and individuals. Every bit of support means I can keep refining these tools, fixing edge cases, and thinking about the long-term direction.  

Here are updates about the projects/libraries I've worked on in the last two months in detail.  

- [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
  - Bump clojure to `1.12.2`
  - [#1843](https://github.com/babashka/babashka/issues/1843): BREAKING (potententially): non-daemon thread handling change. Similar
    to JVM clojure, babashka now waits for non-daemon threads to finish. This
    means you don't have to append `@(promise)` anymore when you spawn an
    httpkit server, for example. For futures and agents, bb uses a thread pool
    that spawns daemon threads, so that pool isn't preventing an exit. This
    behavior is similar to `clojure -X`. You can get back the old behavior where
    bb always forced an exit and ignored running non-daemon threads with
    `--force-exit`.
  - [#1690](https://github.com/babashka/babashka/issues/1690): bind `clojure.test/*test-out*` to same print-writer as `*out*` in nREPL server
  - Add `Compiler/demunge`
  - Add `clojure.lang.TaggedLiteral/create`
  - Add `java.util.TimeZone/setDefault`
  - Add `println-str`
  - SCI: Var literal or special form gets confused with local of same name
  - [#1852](https://github.com/babashka/babashka/issues/1852): `(.getContextClassLoader (Thread/currentThread))` should be able to return results from babashka classpath
  - Bump `deps.clj` to `1.12.2.1565`
  - Bind more vars like `*warn-on-reflection*` during `load{string,reader}` (same as JVM Clojure) so can load code in other than than the main thread
  - [#1845](https://github.com/babashka/babashka/issues/1845): expose `cheshire.generate/{add-encoder,encode-str}`
  - Bump timbre to `6.8.0`
  - Bump clojure.tools.logging to `1.3.0`
  - Improve interop using type hints on qualified instance methods
  - Bump Jsoup to `1.21.2`
  - Bump `fs` to `0.5.7`
  - Bump `cheshire` to `6.1.0`
  - Pods: no exception on destroy when there's still calls in progress

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - Add `println-str`
  - Fix [#997](https://github.com/babashka/sci/issues/997): Var is mistaken for local when used under the same name in a `let` body
  - Fix regression introduced in [#987](https://github.com/babashka/sci/issues/987)
  - Fix [#963](https://github.com/babashka/sci/issues/963): respect `:param-tags` on qualified instance method
  - Add `*suppress-read*`
  - Add `load-reader`
  - Fix [#872](https://github.com/babashka/sci/issues/872): `*loaded-libs*` is now the single source of truth about loaded libs
  - Fix [#981](https://github.com/babashka/sci/issues/981): respect type hint on instance method callee
  - Add core dynamic vars like `*warn-on-reflection*` and bind them during
    `load-string` etc. such that `set!`-ing then in a `future` works.
  - Fix [#984](https://github.com/babashka/sci/issues/984): support alternative `set!` syntax in CLJS
  - Fix [#987](https://github.com/babashka/sci/issues/987): method or property name in interop should be munged
  - Fix [#986](https://github.com/babashka/sci/issues/986): preserve error location for js static method
  - Fix [#990](https://github.com/babashka/sci/issues/990): fix `merge-opts` with `:bindings` + deprecate `:bindings` (replaced by `:namespaces {'user ...}`)

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.<br>
  - Unreleased
  - [#2588](https://github.com/clj-kondo/clj-kondo/issues/2588): false positive type mismatch about `symbol` accepting var
  - Require clojure `1.10.3` is the minimum clojure version
  - [#2564](https://github.com/clj-kondo/clj-kondo/issues/2564): detect calling set with wrong number of arguments
  - [#2257](https://github.com/clj-kondo/clj-kondo/issues/2257): support ignore hint on invalid symbol
  - Sort findings on filename, row, column and now additionally on message too
  - [#2602](https://github.com/clj-kondo/clj-kondo/issues/2602): Sort auto-imported configs to avoid differences based on OS or file system
  - [#2603](https://github.com/clj-kondo/clj-kondo/issues/2603): warn on `:inline-def` with nested `deftest`
  - [#2606](https://github.com/clj-kondo/clj-kondo/issues/2606): make it easy for users to know how inline-config files should be version controlled ([@lread](https://github.com/lread))
  - [#2610](https://github.com/clj-kondo/clj-kondo/issues/2610): ignores may show up unordered due to macros
  - [#2615](https://github.com/clj-kondo/clj-kondo/issues/2615): emit `inline-configs` `config.edn` in a git-diff-friendly way ([@lread](https://github.com/lread))
  - 2025.07.28
  - [#2580](https://github.com/clj-kondo/clj-kondo/issues/2580): false positive type mismatch with quoted value
  - Fix some `:locking-suspicious-lock` false positives
  - [#2582](https://github.com/clj-kondo/clj-kondo/issues/2582): `:condition-always-true` false positives
  - 2025.07.26
  - [#2560](https://github.com/clj-kondo/clj-kondo/issues/2560): NEW linter: `:locking-suspicious-lock`: report when locking is used on a single arg, interned value or local object
  - [#2519](https://github.com/clj-kondo/clj-kondo/issues/2519): NEW linter: `:unresolved-protocol-method`. See [docs](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md) ([@emerson-matos](https://github.com/emerson-matos))
  - [#2555](https://github.com/clj-kondo/clj-kondo/issues/2555): false positive with `clojure.string/replace` and `partial` as replacement fn
  - [#2566](https://github.com/clj-kondo/clj-kondo/issues/2566): Expand `:condition-always-true` check. ([@NoahTheDuke](https://github.com/NoahTheDuke))
  - [#2350](https://github.com/clj-kondo/clj-kondo/issues/2350): support `schema.core/defprotocol` ([@emerson-matos](https://github.com/emerson-matos))
  - [#2571](https://github.com/clj-kondo/clj-kondo/issues/2571): false positive unresolved symbol when ignoring expression that goes through macroexpansion hook
  - [#2575](https://github.com/clj-kondo/clj-kondo/issues/2575): false positive type mismatch with nested keyword call and `str`
  - Bump SCI to `0.10.47`
  - Drop memoization for hook fns and configuration, solves memory issue with Cursive + big projects like metabase
  - Optimizations to compensate for dropping caching, performance should be similar (or better depending on the size of your project)
  - [#2568](https://github.com/clj-kondo/clj-kondo/issues/2568): support `:deprecated-namespace` for `.cljc` namespaces

- [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
  - Upgrade to Reagent and fix unsafe HTML rendering
  - Add viewers for HTML markdown nodes
  - Support file watching in babashka
  - Support server side rendering of formulas using KaTeX

- [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  - v0.8.153 (2025-08-31)
  - Fix [#704](https://github.com/squint-cljs/squint/issues/704): `while` didn't compile correctly
  - Add `clojure.string/includes?`
  - Emit less code for varargs functions
  - Fix solidJS example
  - Documentation improvements ([@lread](https://github.com/lread))
  - Fix [#697](https://github.com/squint-cljs/squint/issues/697): `ClassCastException` in statement function when passed Code records
  - v0.8.152 (2025-07-18)
  - Fix [#680](https://github.com/squint-cljs/squint/issues/680): support import attributes using `:with` option in require, e.g. `:with {:type :json}`
  - v0.8.151 (2025-07-15)
  - Implement `not=` as function
  - Fix [#684](https://github.com/squint-cljs/squint/issues/684): JSX output
  - v0.8.150 (2025-07-09)
  - [#678](https://github.com/squint-cljs/squint/issues/678): Implement `random-uuid` ([@rafaeldelboni](https://github.com/rafaeldelboni))
  - Fix [#681](https://github.com/squint-cljs/squint/issues/681): support unsafe HTML via `[:$ ...]` tag

- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
  - v0.7.27 (2025-08-21)
  - [#95](https://github.com/babashka/scittle/issues/121): support string requires
    of `globalThis` js deps ([@chr15m](https://github.com/chr15m)). See
    [docs](https://github.com/babashka/scittle/blob/main/doc/js-libraries.md).
  - Potentially breaking: `(.-foo-bar {})` now behaves as `{}.foo_bar`, i.e. the property or method name is munged.
  - v0.7.26 (2025-08-20)
  - [#121](https://github.com/babashka/scittle/issues/121): add `cjohansen/dataspex` plugin ([@jeroenvandijk](https://github.com/jeroenvandijk))
  - [#118](https://github.com/babashka/scittle/issues/118): add `goog.string/format` ([@jeroenvandijk](https://github.com/jeroenvandijk))
  - Support alternative `(set! #js {} -a 1)` CLJS syntax (by bumping SCI)
  - Add source maps to distribution
  - Add dev versions of all modules in the `dev` folder of the distribution + a `dev/scitte.cljs-devtools.js` module

- [edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
  - Fix [#132](https://github.com/borkdude/edamame/issues/132): Add counterpart to Clojure's `*suppress-read*`: `:suppress-read`

- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
  - Add config for dataspex

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
  - nREPL improvement for vim-fireplace

- [Nextjournal Markdown](https://github.com/nextjournal/markdown)
  - Drop KaTeX dependency by inlining TeXMath lib

- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
  - Add `:responses` key with raw responses

- [fs](https://github.com/babashka/fs) - File system utility library for Clojure
  - Documentation improvements
  - Fix wrong typehint

- [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - `not=` is now a function

- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
  - [#122](https://github.com/babashka/cli/issues/122): introduce new
    `:repeated-opts` option to enforce repeating the option for accepting multiple
    values (e.g. `--foo 1 --foo 2` rather than `--foo 1 2`)

- [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
  - Fixed Java download program that respects `CLJ_JVM_OPTS` for downloading tools jar.
  - Released several versions catching up with the clojure CLI

- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
  - Pod protocol fix: don't send done with async messages
  - Robustness improvements
  - Bump fsnotify

- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
  - Send current working directory in describe message (for tools like clojure-mcp)
  - Add `"session-closed"` to close op reply

- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
  - JSON1 support

- [http-server](https://github.com/babashka/http-server): serve static assets
  - 0.1.15
  - [#22](https://github.com/babashka/http-server/issues/22): fix off-by-one error in range requests ([@jyn514](https://github.com/jyn514))
  - 0.1.14
  - [#21](https://github.com/babashka/http-server/issues/21): Add `:not-found` option for handling unfound files. The option is a function of the request and should return a map with `:status` and `:body`.
  - [#19](https://github.com/babashka/http-server/issues/19): Add text/html MIME types for asp and aspx file extensions ([@respatialized](https://github.com/respatialized))
  - 0.1.13
  - [#16](https://github.com/babashka/http-server/issues/16): support range requests ([jmglov](https://github.com/jmglov))
  - [#13](https://github.com/babashka/http-server/issues/13): add an ending slash to the dir link, and don't encode the slashes ([@KDr2](https://github.com/KDr2))
  - [#12](https://github.com/babashka/http-server/issues/12): Add headers to index page (rather than just file responses)

Contributions to third party projects:

- [specter](https://github.com/redplanetlabs/specter): Clojure(Script)'s missing piece
  - Fix babashka support by removing optimizations that only worked due to SCI bug

- [clojure-test-suite](https://github.com/jank-lang/clojure-test-suite): Dialect-independent tests for clojure.core, and others, focused on characterizing how Clojure JVM behaves so that other dialects to reach parity.
  - Added babashka to the test suite

## Other projects

These are (some of the) other projects I'm involved with but little to no activity
happened in the past month.

<details>
<summary>Click for more details</summary>

- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
- [http-client](https://github.com/babashka/http-client): babashka's http-client
- [quickblog](https://github.com/borkdude/quickblog): light-weight static blog engine for Clojure and babashka
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
- [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag
- [instaparse-bb](https://github.com/babashka/instaparse-bb): Use instaparse from babashka
- [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
- [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one comman
- [qualify-methods](https://github.com/borkdude/qualify-methods)
  - Initial release of experimental tool to rewrite instance calls to use fully
    qualified methods (Clojure 1.12 only0
- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects.<br>
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

</details> <br>  

---

## Oleksandr Yakushev
2025 Annual Funding Report 4. Published September 6, 2025.  

Hello friends! Here's my update on July-August 2025 Clojurists Together work. It has been a slow summer for me, but I still advanced in several projects under my wing.  

### clj-async-profiler  
I have been progressing towards a major 2.0.0 release for quite some time, and it will be released soon. The release will contain prominent changes, including the complete transition to JFR file format for collected profiles (compatible with Java Flight Recorder), support for continuous profiling, and a new exciting flamegraph type (secret for now!). I'm also making sure that Flamebin will receive the same new features shortly after that.  

### nREPL  
We have just released [nREPL 1.4](https://clojurians.slack.com/archives/C06MAR553/p1756827753587169) which supports configuring dynamic var values for the REPL environment and improvements to `load-file` middleware. There are already futher improvements in the pipeline, so expect a 1.5 coming soon.  

### CIDER  
Together with Bozhidar, we have released [CIDER 1.9](https://clojurians.slack.com/archives/C06MAR553/p1752150481484109) back in July, bringing the
accumulated features and fixes to stable-version users. In the unstable version, I've fixed a couple annoying bugs, namely:  

- ClojureScript node repl switches back to cljs.user namespace (fixed in [piggieback 0.6.1](https://github.com/nrepl/piggieback/blob/master/CHANGES.md#061-2025-12-31)).
- Broken stacktrace response when C-c C-p throws an exception ([#3827](https://github.com/clojure-emacs/cider/issues/3827)).  

CIDER users will also benefit from the recent and upcoming nREPL improvements.  <br>

---

## Peter Taoussanis        
2025 Annual Funding Report 4. Published September 2, 2025.   

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

\- [Peter Taoussanis](https://www.taoensso.com)  

Hi folks! 👋 Will try keep today's update for July and August brief.  

### Recent work  

### Sente  

> ([Sente](https://www.taoensso.com/sente) is a realtime web comms library for Clojure/Script)  

Sente [v1.21.0-RC1](https://github.com/taoensso/sente/releases/tag/v1.21.0-RC1) is out now. v1.21 is a big release with improved performance, improved reliability, improved logging (via [Trove](https://www.taoensso.com/trove)) and new [high-speed binary serialization](https://github.com/taoensso/sente/blob/a8de08f26cf739512d249dc88bc98c33a9992bcf/src/taoensso/sente/packers/msgpack.cljc#L8) that supports Clojure's rich data types.  

The binary serialization is still marked as experimental to be safe, though I have it running in production myself. This is a nice improvement, especially for folks with larger payloads and/or with mobile users that might be sensitive to network speed/limits.  

To try the new serialization: just give `(taoensso.sente.packers.msgpack/get-packer)` to your [client](https://cljdoc.org/d/com.taoensso/sente/1.21.0-RC1/api/taoensso.sente#make-channel-socket-client!) and [server](https://cljdoc.org/d/com.taoensso/sente/1.21.0-RC1/api/taoensso.sente#make-channel-socket-server!) constructor calls. No extra deps needed.  

### Truss  

> ([Truss](https://www.taoensso.com/truss) is a micro toolkit for Clojure/Script errors)  

Truss [v2.2.0](https://github.com/taoensso/truss/releases/tag/v2.2.0) is out now with some usability improvements, and a new [demo video](https://www.youtube.com/watch?v=vGewwWuzk9o&feature=youtu.be) to show what the library can do. In short: the goal is to **help improve the Clojure/Script error experience**. Almost all of my Clojure/Script code uses Truss in some way.  

The video should be a decent starting point if you're not familiar with Truss.  

### Trove and Telemere  

> [Trove](https://www.taoensso.com/trove) is a modern logging facade for Clojure/Script.  
> [Telemere](https://www.taoensso.com/telemere) is the successor to [Timbre](https://www.taoensso.com/timbre).  

Trove [v1.0 final](https://github.com/taoensso/trove/releases/tag/v1.0.0) and Telemere [v1.1.0](https://github.com/taoensso/telemere/releases/tag/v1.1.0) are out now with some usability improvements.  

How do these libraries relate?  

- Trove is intended for **library authors** that want to emit rich logging (incl. un/structured logging) _without_ forcing their users to adopt any particular backend (like [Telemere](https://www.taoensso.com/telemere), [Timbre](https://www.taoensso.com/timbre), [μ/log](https://github.com/BrunoBonacci/mulog), [tools.logging](https://github.com/clojure/tools.logging), [SLF4J](https://www.slf4j.org/), etc.).  

- Telemere is intended for **general users** that want to emit rich logging (incl. un/structured logging) from their own applications.  

The two share the same map-oriented logging API:  

```clojure
(trove/log!    {:level :info, :id :auth/login, :data {:user-id 1234}, :msg "User logged in!"})
(telemere/log! {:level :info, :id :auth/login, :data {:user-id 1234}, :msg "User logged in!"})
```

Both support traditional and **structured** logging:  

- Traditional logging outputs **strings** (messages).  
- Structured logging in contrast outputs **data**. It retains **rich data types and (nested) structures** throughout the logging pipeline from logging callsite → filters → middleware → handlers.

A data-oriented pipeline can make a huge difference - supporting **easier filtering**, **transformation**, and **analysis**. It’s also usually **faster**, since you only pay for serialization if/when you need it. In a lot of cases you can avoid serialization altogether if your final target (DB, etc.) supports the relevant types.  

So the structured (data-oriented) approach is inherently more flexible, faster, and well suited to the tools and idioms offered by Clojure and ClojureScript.  

### Other stuff  

Other misc releases included:  

- [http-kit](https://www.taoensso.com/http-kit) [v2.8.1](https://github.com/http-kit/http-kit/releases/tag/v2.8.1) and [v2.9.0-beta2](https://github.com/http-kit/http-kit/releases/tag/v2.9.0-beta2)  
- [Timbre](https://www.taoensso.com/timbre) [v6.8.0](https://github.com/taoensso/timbre/releases/tag/v6.8.0)  

As usual please see the linked release notes for details.  

### Upcoming work  

Next couple months I expect my focus to include [Tempel](https://www.taoensso.com/tempel) and likely [Carmine](https://www.taoensso.com/carmine), both of which are long overdue for some love :-)  

Cheers everyone!    


