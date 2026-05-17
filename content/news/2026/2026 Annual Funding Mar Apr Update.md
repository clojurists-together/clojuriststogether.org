---
title: "Annually-Funded Developers' Update: March & April 2026"
date: 2026-05-17T14:00:00+12:00
author: Kathy Davis
summary: "Bozhidar Batsov, Clojure Camp, Eric Dallo, Michiel Borkent, Jeaye Wilkerson, Thomas Clark"  
draft: True


---

Hello Fellow Clojurists!

This is the second of six reports from the developers who are receiving annual funding for 2026. Thanks to everyone for supporting their work and important contributions to the Clojure community. 

[**Bozhidar Batsov:**](#bozhidar-batsov) nREPL, Clojure Mode, ts-mode, Orchard, CIDER, and more   
[**Clojure Camp:**](#clojure-camp)  Badges, nano-conj, excercises  
[**Eric Dallo:**](#eric-dallo) eca, eca-desktop, clojure-lsp, clojure-lsp-intellij  
[**Jeaye Wilkerson:**](#jeaye-wilkerson) Jank compiler architecture and optimization  
[**Michiel Borkent:**](#michiel-borkent) babashka, ClojureScript async/await, Rebel, squint, and much more  

## Bozhidar Batsov  
2026 Annual Funding Report 2. Published May 1, 2026. 

The last two months have been some of the busiest I've had in `clojure-emacs`
land in a long while. No big "X.Y" CIDER release to point at yet, but there's
been a steady stream of important work across most of the sibling projects.
Below are some of the highlights.

### clojure-mode 5.22 and 5.23

Two `clojure-mode` releases back-to-back, after a long stretch of relative
quiet:

- **[5.22](https://github.com/clojure-emacs/clojure-mode/releases/tag/v5.22.0)** (March 3): a big bug-fix dump — `clojure-sort-ns` no longer mangles `:gen-class`, `clojure-thread-last-all` doesn't eat closing parens into line comments, `clojure-add-arity` preserves arglist metadata, `letfn` bindings get a function face, and `edn-mode` indents data like data. On the feature side: a new `clojure-preferred-build-tool` for projects that have several build tool files lying around, a dedicated `clojure-discard-face` for `#_` forms, and project root detection for ClojureCLR's `deps-clr.edn`.
- **[5.23](https://github.com/clojure-emacs/clojure-mode/releases/tag/v5.23.0)** (March 25): adopts the modern indent spec tuple format (`((:block N))`, `((:inner D))`, ...) that `clojure-ts-mode` and `cljfmt` already use. The legacy format (integers, `:defn`, positional lists) still works, but it's slated for removal in `clojure-mode` 6. There's a new public `clojure-get-indent-spec` API, and CI moved off CircleCI onto GitHub Actions.

Unifying the indent format across our Clojure tooling has been on my todo list
for years - happy to see it finally happen!

### clojure-ts-mode

No release in this window, but I poured a lot of effort into the test suite -
proper indentation and font-lock test coverage, a new configuration option
test suite, and integration tests against sample files. Boring infrastructure
work, but it's the kind of thing that pays compound interest as the mode keeps
maturing.

*Side note:* In the past several months I've spent a bit of time hacking on [neocaml](https://neocaml.org/) and my work there provided quite a bit of inspiration for improvements to `clojure-ts-mode`. (and, of course - `clojure-ts-mode` inspired me to create neocaml in the first place)

### Orchard 0.41

[Orchard v0.41.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.41.0)
shipped on April 13 with a rewrite of `orchard.xref`, a refactor of
`orchard.indent`, and a test modernization pass. I chipped in some smaller
fixes - a cycle guard in `fn-transitive-deps`, validating input to
`stacktrace/analyze`, compiling a hot regex once instead of on every call, and
standardizing the `-test` suffix across the test suite.

We've also migrated from Lein to `tools.deps` for Orchard and probably we'll continue in this direction for the rest of our Clojure projects in the nREPL and Clojure Emacs GitHub orgs. No rush on that front, though. 

### cider-nrepl 0.59

[cider-nrepl v0.59.0](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.59.0)
landed on April 14. The headline change from my side is that **the ops are now
properly namespaced** ([#710](https://github.com/clojure-emacs/cider-nrepl/issues/710)) -
something that's been sitting in the issue tracker for a very long time. CIDER
on `master` already speaks the new namespaced ops with a fallback to the legacy
names for older `cider-nrepl` versions, so upgrades should be painless.

Other notable bits: Compliment and clj-reload are no longer shaded, the test
middleware properly binds `*report-counters*`, and we extract inline maps from
composite Lein profiles correctly.

### refactor-nrepl

`refactor-nrepl` got a serious modernization push in April - no release yet,
but a lot of cleanup landed:

- Dropped `http-kit` in favor of the JDK's built-in HTTP client (one fewer dependency, one less thing to break).
- Restored `hotload-dependency` on top of `tools.deps`.
- A new `def-op` macro to simplify how ops are defined and how errors are handled.
- A shared test session helper, a `make lint` target, and modernized CircleCI executors.
- A full dependency bump.

Expect a `refactor-nrepl` release once the dust settles.

### clj-refactor.el

Just a small [cleanup pass](https://github.com/clojure-emacs/clj-refactor.el/pull/564)
to align with the new Emacs 28+ baseline. `clj-refactor` is in maintenance mode
these days, but I'm trying to keep it healthy.

Eventually I'll be taking a closer look at moving some of its functionality to clojure-mode and CIDER, but there's more ground-work that needs to be done first.

### CIDER

No CIDER release this cycle, but `master` accumulated a *lot* of structural
work that will land in CIDER 1.22:

- **`nrepl-client.el` decoupled from CIDER.** It no longer depends on `sesman` or any of CIDER's UI layer, which makes the nREPL client genuinely reusable outside CIDER. I also split `cider-connection.el` into `cider-session.el` + `cider-connection.el` and decoupled `cider-eval.el` from `cider-repl.el`.
- **`cider-jack-in` got a serious overhaul:** a proper jack-in tool registry, unified entry points around shared helpers, TRAMP fixes, sane runtime defaults, and `cider--resolve-command` now actually works over TRAMP.
- **A pile of resilience fixes** for the nREPL client: tear down the SSH tunnel on abnormal client disconnect, spawn the tunnel without a shell, plug request-id leaks in raw response handlers, stop response-handler errors from poisoning the response queue, and put a FIFO cap on `nrepl-completed-requests` so it can't grow without bound.
- **Namespaced ops everywhere**, matching `cider-nrepl` 0.59 - with a fallback to the legacy op names so older `cider-nrepl` keeps working.
- A new `nrepl-make-eval-handler` with a keyword-arg API (and removal of the trivial wrappers that used to live around it).
- Mode-line spinner while tests are running, which is a small thing but I missed it every time I ran a long test suite.
- Lots of test coverage backfill on the client side.

This is shaping up to be one of those releases that doesn't look flashy on the
outside but cleans up a ton of long-standing internal mess. The decoupling work
in particular has been on my mind for years.

I've also started working on experimental support for prepl in CIDER. I'm not sure if this will ever land
or be properly supported, but it's been a fun side quest to me to ensure that CIDER logic is decoupled properly
from its bundled nREPL client that I plan to eventually spin out of CIDER.

### MrAnderson

Slightly outside the `clojure-emacs` org, but related: I've been helping
[MrAnderson](https://github.com/benedekfazekas/mranderson) get back on its
feet. Three PRs landed in March/April:

- [Modernize deps and migrate CI to GitHub Actions](https://github.com/benedekfazekas/mranderson/pull/94)
- [Fix resource leaks, null safety, and minor code issues](https://github.com/benedekfazekas/mranderson/pull/96)
- [Exclude `clj-kondo.exports` from jar extraction](https://github.com/benedekfazekas/mranderson/pull/93)

MrAnderson is what `cider-nrepl` (and others) use to shade dependencies, so a
healthy MrAnderson is in everyone's interest. There's more to come here!

### What's next

CIDER 1.22 once the jack-in and decoupling work settles, a `refactor-nrepl`
release to ship all the April cleanup, and hopefully a fresh MrAnderson cut
soon after.

As always - thanks to everyone who pitched in, especially Sashko Yakushev who
once again did much of the the heavy lifting on `cider-nrepl` and Orchard. 

And a HUGE THANKS to the members of Clojurists Together for supporting my Clojure OSS work! You rock!<br>

---


## Clojure Camp
2026 Annual Funding Report 2. Published May 12, 2026.  

- Some of our CT funds are being set aside to support three efforts this year:
  - sponsoring conference attendance for new Clojurians,
  - supporting Clojure and non-Clojure meetups with a “pizza fund” (WIP),
  - hosting an experimental nano-conj (in-person multi-day open-ended hack-on-clojure “retreat”) (WIP)
- On the “dev side”....
  - Worked through most of the backlog of exercises done in mobs and released on https://exercises.clojure.camp/
  - Continued on badge system, mostly wrestling with display of the “learning graph”
- Other happenings
  - Hosted an in-person workshop at the Recurse Center (with another planned)
  - Started a new book club reading Knesl’s “Applied High Order Functions in Clojure”
  - Early planning for our presence at Conj this year (may be doing a “Learning Together” track on the workshop day, featuring Mobbing and Pairing sessions)
- Raf has a major commitment finishing up in May, so June+July should see a big push on Camp projects (and resuming regular Mobs) <br>

---

## Eric Dallo
2026 Annual Funding Report 2. Published May 8, 2026.   

Excited 2 months of lots of work and help from Clojurians! We had improvements in eca and clojure-lsp mainly, and new projects as well.  

### [ECA](https://github.com/editor-code-assistant/eca)  
ECA keeps growing a lot, receiving lots of contributions, with more than 800 stars already I'm planning a stable release soon, in these 2 months we had lots of releases with [ton of stuff](https://github.com/editor-code-assistant/eca/blob/master/CHANGELOG.md), so I will focus on the main highlights:

#### 0.109.1 - 0.131.1  
- __Plugins__: New plugin system to load external configuration from git repos or local paths, with an official marketplace at [plugins.eca.dev](https://plugins.eca.dev). Plugins can provide skills, MCP servers, agents, commands, hooks, rules and arbitrary config overrides, managed via `/plugins`, `/plugin-install` and `/plugin-uninstall`.
- [ ] __Remote web control__: New remote web control server for browser-based chat observation and control via [web.eca.dev](https://web.eca.dev), allowing you to observe and drive ECA chats from any browser.
- __Trust mode__: Clients can now auto-accept tool calls that would require manual approval, with regex patterns support for fine-grained `shell_command` approval.
- __Task tool__: Built-in task tracking tool to let the agent plan and follow multi-step work more reliably.
- __Background shell commands__: New `background` parameter on `shell_command` and a dedicated `bg_job` tool to manage long-running processes like dev servers and watchers.
- __Chat list, open, fork and `/model`__: New `chat/list`, `chat/open` and `/fork` commands let clients render a chat sidebar and clone existing conversations, plus a new `/model` command to switch model mid-chat.
- __Message flags__: Named checkpoints inside a chat for resuming and forking from a specific point, with full chat history preserved across compactions via tombstone markers.
- __`ask_user` tool__: LLMs can now ask the user questions with optional selectable options, fully integrated with hooks and trust modes.
- __Image generation__: Support for OpenAI's built-in `image_generation` tool via the Responses API, including image edits across turns and MCP tools that return image content.
- __Prompt steering__: New `chat/promptSteer` notification to inject user messages into a running prompt at the next LLM turn boundary, without stopping it.
- __MCP improvements__: New `mcp/addServer`, `mcp/removeServer`, `mcp/updateServer`, `mcp/enableServer` and `mcp/disableServer` requests to manage MCP servers at runtime, plus much better OAuth spec compliance and a switch from the Java SDK to [plumcp](https://github.com/editor-code-assistant/plumcp).
- __More providers and models__: Added LiteLLM, LM Studio, Mistral and Moonshot as built-in providers with login support, Claude Opus 4.7, deepseek-v4-pro, gpt-5.4 and gpt-5.5 variants, and GitHub Enterprise Copilot.

#### [ECA Desktop](https://github.com/editor-code-assistant/eca-desktop)

<img width="960" height="562" alt="eca-desktop eric mar apr" src="https://github.com/user-attachments/assets/c32d9779-43a4-48d4-ae7c-d177fe50f4fb" />  


Since ECA has been pretty stable and built in top of a nice extensible protocol, it worked so well that we decide to offer the same server capabilities to a Desktop client, similar to Claude Desktop but reusing the same server, this makes possible to have the same experience without an Editor, especially useful for non techinical people in price for a thin layer connecting to the server.

__That's a lot for ECA and all part of the amazing community that's been activelly helping with issues, feedback and contributions.__

### [clojure-lsp](https://clojure-lsp.io/)

We had some important bumps with lots of fixes, new code actions and contributions! The main highlight here is the arrival of performance tests in the project, which is a long waited thing which would help unblock performance optmizations in clojure-lsp since now we have a way to reliable know a mean, p90, p80 of how much time clojure-lsp spend on its features like initialization.

#### 2026.05.05-12.58.26

- Fix `cyclic-dependencies` linter falsely reporting cycles for `(require ...)` calls inside `(comment ...)` forms. #2107
- Support find-definition for fully qualified vars even when the namespace is not explicitly required. #2028
- Fix `create-test` code action appending a duplicate `deftest` when one with the matching name already exists, now navigating to the existing deftest instead. #2274
- Change the default of `:clean :ns-inner-blocks-indentation` from `:next-line` to `:keep`, so `clean-ns` (including the automatic run after `add-missing-libspec`, `add-require-suggestion`, `add-missing-import`, and `move-form`) no longer reflows the `:require`/`:import` block when the user has not configured an indentation style. Users who want the previous behavior can set `:clean :ns-inner-blocks-indentation :next-line` explicitly. #2261
- Fix `add-missing-require` refer suggestions leaking across languages, so a `.clj` file no longer offers refers defined only in `.cljs` files (and vice versa). #2271
- Add `:private-by-default-on-extract?` setting to control whether extracted functions and defs are private by default. #2258
- Measure performance of code actions
- Avoid incorrect circular dependency errors from `:as-alias` by working around clj-depend bug.
- Fix inline-def to work with defs with metas.
- Bump clj-kondo to `2026.04.16-20260503.191510-9`.
- bump up timeout for code action performance measurement, include p90 measurement #2236
- implementation of inline function. #1827
- Fix initialization crash when a source file has syntax errors (e.g. unbalanced parens) by using safe parser in unused-public-var linter's `:gen-class` check. #2242
- Bump rewrite-clj to `1.2.54`.
- implement move to :let refactoring #1732
- Measure performance of didOpen and didChange
- if code-action selection end-position args aren't provided, don't try to use them #2276
- add unit tests for command actions location args #2279
- New code actions: replace `:refer` with `:as` and replace `:as` with `:refer`, with support for merging into existing `:refer` vectors.

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)

#### 3.5.1 - 3.5.5

 - Implement `createServerInstaller` and `createLanguageServerSettingsContributor` on `ClojureLanguageServerFactory`. Newer LSP4IJ versions added these as default interface methods, but our `def-extension`/`gen-class`-backed factory always overrides interface methods, so explicit stubs are required.
- Fix `slurp-action-test` against newer LSP4IJ versions. Bump `clj4intellij` to `0.9.0` and switch the test fixture to `setup-heavy` so the project base has a real filesystem path (LSP4IJ's `FileSystemWatcherManager` calls `VirtualFile.toNioPath()`, which throws on the in-memory `TempFileSystem` used by light fixtures).
- Fix new namespace creation incorrectly creating files under the absolute host filesystem path. #83
- Fix `QuoteHandler` compile error by merging `BAD_CHARACTER` into the quote-handler `TokenSet`.
- Fix auto closing single quotes. 
- Improve CI to have plugin zips in all releases, avoiding wait for Jetbrains approval. <br>

---


## Jeaye Wilkerson  
2026 Annual Funding Report 2. Published May 8, 2026.  

Howdy folks! Thank you so much for the sponsorship this year. For the last two
months, I have been focused on compiler architecture and optimization for jank.

On the compiler architecture side, I have designed and implemented a custom
intermediate representation (IR) for jank programs which sets the stage for
writing Clojure-specific optimization passes. This IR operates at the level of
Clojure's semantics, which is much higher level than LLVM IR, and so we are able
to perform optimizations which LLVM could never do for us.

On the optimization side, I have taken the first benchmark of many, recursive
fibonacci, and I have optimized it to be nearly twice as fast as Clojure JVM,
for the same exact code. This benchmark is the first of many and I will be
following up with more benchmark optimization results in the coming two months,
using our new IR as a platform for optimizing jank's performance.

To read all about the details of jank's IR and the benchmark optimization, take
a look at this [blog post:](https://jank-lang.org/blog/2026-05-08-optimization/) <br>  

---


## Michiel Borkent  
2026 Annual Funding Report 2. Published May 11, 2026.   

In this post I'll give updates about open source I worked on during March and April 2026.

To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).

## Sponsors

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

## Updates

### Babashka conf and Dutch Clojure Days 2026

[Babashka Conf 2026](https://babashka.org/conf/) was held on May 8th in the OBA Oosterdok library in Amsterdam! David Nolen, primary maintainer of ClojureScript, was our keynote speaker. We're excited to have [Nubank](https://international.nubank.com.br/careers/), [Exoscale](https://www.exoscale.com/jobs/), [Bob](https://github.com/bobisageek), [Flexiana](https://flexiana.com) and [Itonomi](https://itonomi.com) as sponsors. Nubank and Exoscale are hiring. Wendy Randolph was our event host. For the schedule and other info, see [babashka.org/conf](https://babashka.org/conf/).  
The day after babashka conf, [Dutch Clojure Days 2026](https://clojuredays.org/) was also held - so it was a great weekend in Amsterdam!  
Hope to have seen many of you there!  

### Projects

In the last two months I spent significant time organizing babashka conf, but made progress in several projects as well.

My upstream work to enable `async/await` in ClojureScript was merged in the beginning of March. The implementation mirrors squint. Thanks David for reviewing and merging. Also `deftest` now supports an `^:async` annotation so you can use `async/await` and don't need to mess around with the `cljs.test/async` macro anymore:

- [CLJS-3470](https://clojure.atlassian.net/browse/CLJS-3470) `async/await`
- [CLJS-3476](https://clojure.atlassian.net/browse/CLJS-3476) `async deftest`

I'll be presenting this work at the Dutch Clojure Days.

[Rebel-readline](https://github.com/bhauman/rebel-readline/tree/master/rebel-readline) is now bb compatible. The work involved mainly exposing more JLine stuff and making sure rebel-readline didn't hit any internal JLine APIs.
One step to drive this to completion was to make a dependency, [compliment](https://github.com/alexander-yakushev/compliment/), bb compatible. Thanks both to Bruce and Alexander for the cooperation.

[Squint](https://github.com/squint-cljs/squint) now supports `cljs.test` and multimethods! [clojure-mode](https://github.com/nextjournal/clojure-mode) was ported to use the new `cljs.test`.

On the [cream](https://github.com/borkdude/cream) front, I put in effort to make the binary smaller and have been keeping up with the new GraalVM EA releases. I've been posting bug reports to the crema maintainer. Currently there's still an unfixed bug around core.async that I have trouble reproducing in pure Java. I also added lots of library tests to CI so I can ensure stability in the long run. For now it remains experimental, but the direction is promising.

A performance PR to [weavejester/dependency](https://github.com/weavejester/dependency) speeds up `depend`, `depends?` and `topo-sort` significantly, so [clerk](https://github.com/nextjournal/clerk) notebooks render faster.

The [cljfmt](https://github.com/weavejester/cljfmt) library, also by [@weavejester](https://github.com/weavejester), now fully runs from source in babashka. The Java diff library that wasn't bb-compatible was replaced with [text-diff](https://github.com/borkdude/text-diff), but only for the babashka path. The JVM build of cljfmt still uses the original Java diff library, with a possible switch later once text-diff has matured.

Several SCI fixes were made to improve Clojure compatibility between babashka and Clojure. E.g. records can now support extending to `IFn` which was a blocker for some Clojure libs that tried to run in `bb` so far.

Clj-kondo `2026.04.15` got a few new linters thanks to [@jramosg](https://github.com/jramosg) for stewarding most of these. It also has better out of the box [potemkin](https://github.com/clj-commons/potemkin) support, and [@alexander-yakushev](https://github.com/alexander-yakushev) contributed a wave of performance improvements.

Updates per project below. Bullets are highlights; see each project's `CHANGELOG.md` for the full list.

- [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
  - Released 1.12.216, 1.12.217 and 1.12.218
  - Support rebel-readline as external REPL provider:
    - Add proxy support for `Completer`, `Highlighter`, `ParsedLine`, `Writer`, `Reader`
    - Add `clojure.repl/special-doc` and `clojure.repl/set-break-handler!`
    - Add `clojure.main/repl-read`
    - Add `org.jline.reader.Buffer` to class allowlist
  - Add `clojure.java.javadoc` namespace with `javadoc` available in REPL [#1933](https://github.com/babashka/babashka/issues/1933)
  - Fix `(doc var)`, `(doc set!)` and other special forms [#1932](https://github.com/babashka/babashka/issues/1932)
  - Support `(source inc)` and `(source babashka.fs/exists?)` for built-in vars [#1935](https://github.com/babashka/babashka/issues/1935)
  - Support `BABASHKA_REPL_HISTORY` env var for configurable REPL history location [#1930](https://github.com/babashka/babashka/issues/1930)
  - Fix `deftype` and `defrecord` inside non-top-level forms (e.g. `let`, `testing`) [#1936](https://github.com/babashka/babashka/issues/1936)
  - [#1948](https://github.com/babashka/babashka/issues/1948): add `java.util.HexFormat` interop support
  - [#1403](https://github.com/babashka/babashka/issues/1403): fix uberscript warnings with `:as-alias`
  - [#1955](https://github.com/babashka/babashka/issues/1955): support `-version` as an alias for `--version`
  - [#1954](https://github.com/babashka/babashka/issues/1954): add `clojure.lang.EdnReader$ReaderException`
  - [#1951](https://github.com/babashka/babashka/issues/1951): fix `--prepare` flag skipping next token
  - [#1967](https://github.com/babashka/babashka/issues/1967): expose `clojure.data.xml.tree/{flatten-elements,event-tree}`, `clojure.data.xml.event` record constructors, and `clojure.data.xml.jvm.parse/string-source`
  - [#1969](https://github.com/babashka/babashka/issues/1969): include `java.net.Proxy` and `java.net.Proxy$Type` Java classes ([@jeeger](https://github.com/jeeger))
  - [#1939](https://github.com/babashka/babashka/issues/1939): disable JLine backslash escaping/shell history commands ([@bobisageek](https://github.com/bobisageek))
  - Performance improvements for math operations and for calling functions on locals
  - Add many new classes to reflection config: `java.lang.reflect.Constructor`, `java.lang.reflect.Executable`, `java.util.stream.Collectors`, `java.util.Comparator` (for `reify`), and more
  - Bump JLine to 4.0.12, cheshire to 6.2.0, `nextjournal.markdown` to 0.7.255, edamame to 1.5.39, `data.xml` to 0.2.0-alpha11, `jsoup` to 1.22.2, rewrite-clj to 1.2.54, tools.cli to 1.4.256, transit-clj to 1.1.357, fs to 0.5.32
  - [Full changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md)

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - Fix `recur` with 20+ args in `loop` ([#1035](https://github.com/babashka/sci/issues/1035))
  - Check `recur` arity, throw when it doesn't match ([#1034](https://github.com/babashka/sci/issues/1034))
  - Support `IFn` on `defrecord`, `deftype` and `reify` ([#808](https://github.com/babashka/sci/issues/808), [#1036](https://github.com/babashka/sci/pull/1036))
  - Validate single binding pair in let-like conditional macros ([#1037](https://github.com/babashka/sci/pull/1037))
  - Normalize SCI types in hierarchy lookups ([#1033](https://github.com/babashka/sci/pull/1033))
  - Expose `IPrintWithWriter` as protocol ([#1032](https://github.com/babashka/sci/pull/1032))
  - Optimize tight loops: fused binding nodes + specialized inlined calls ([#1031](https://github.com/babashka/sci/pull/1031))
  - Support special form documentation in `doc` macro
  - Include SCI types in `ns-map`
  - [Full changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md)

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
  - Released 2026.04.15
  - [#2788](https://github.com/clj-kondo/clj-kondo/issues/2788): NEW linter: `:not-nil?` which suggests `(some? x)` instead of `(not (nil? x))`, and similar patterns with `when-not` and `if-not` (default level: `:off`)
  - [#2520](https://github.com/clj-kondo/clj-kondo/issues/2520): NEW linter: `:protocol-method-arity-mismatch` which warns when a protocol method is implemented with an arity that doesn't match any arity declared in the protocol ([@jramosg](https://github.com/jramosg))
  - [#2520](https://github.com/clj-kondo/clj-kondo/issues/2520): NEW linter: `:missing-protocol-method-arity` (off by default) which warns when a protocol method is implemented but not all declared arities are covered
  - [#2768](https://github.com/clj-kondo/clj-kondo/issues/2768): NEW linter: `:redundant-declare` which warns when `declare` is used after a var is already defined ([@jramosg](https://github.com/jramosg))
  - [#1878](https://github.com/clj-kondo/clj-kondo/issues/1878): support potemkin's `import-fn`, `import-macro`, and `import-def`
  - [#2498](https://github.com/clj-kondo/clj-kondo/issues/2498): support new potemkin `import-vars` `:refer` and `:rename` syntax
  - Performance optimizations across many linting paths ([@alexander-yakushev](https://github.com/alexander-yakushev)) and hook-fn lookup caching to avoid repeated SCI evaluation
  - Add type support for `pmap` and future-related functions (`future`, `future-call`, `future-done?`, `future-cancel`, `future-cancelled?`) ([@jramosg](https://github.com/jramosg))
  - [#2762](https://github.com/clj-kondo/clj-kondo/issues/2762): fix false positive: `throw` with string in CLJS no longer warns about type mismatch ([@jramosg](https://github.com/jramosg))
  - [#2770](https://github.com/clj-kondo/clj-kondo/issues/2770): linter-specific ignores now correctly respect the specified linters
  - [#2773](https://github.com/clj-kondo/clj-kondo/issues/2773): align executable path for images to be `/bin/clj-kondo` ([@harryzcy](https://github.com/harryzcy))
  - [#2621](https://github.com/clj-kondo/clj-kondo/issues/2621): load imports from symlinked config dir ([@walterl](https://github.com/walterl))
  - [#2798](https://github.com/clj-kondo/clj-kondo/issues/2798): report correct filename and error details when `StackOverflowError` occurs during analysis
  - [Full changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)

- [cream](https://github.com/borkdude/cream): Clojure + GraalVM [Crema](https://github.com/oracle/graal/issues/11327) native binary
  - Followed each GraalVM EA release: EA21 shrunk the binary to ~175MB, EA22 brought a virtual-thread fix, EA23 fixed the forkjoin segfault, EA24 finally allowed re-enabling `clojure.core.async-test`
  - Added smoke tests for `httpkit`, `nextjournal/markdown`, `clj-yaml`, core.async ioc-macros
  - Updated 10M loop benchmark numbers for EA21
  - Added Windows test status notes (still some failures on EA22)

- [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  - Released 0.10.186, 0.11.187, 0.11.188 and 0.11.189
  - Add multimethod support: `defmulti`, `defmethod`, `get-method`, `methods`, `remove-method`, `remove-all-methods`, `prefer-method`, `prefers`, plus hierarchy ops `isa?`, `derive`, `underive`, `make-hierarchy`, `parents`, `ancestors`, `descendants` ([#806](https://github.com/squint-cljs/squint/issues/806))
  - `cljs.test/report` is now a multimethod, extensible via `defmethod`. `test-var` now fires `:begin-test-var` / `:end-test-var` events.
  - Accept plain `await` in async functions, in anticipation of CLJS next. The legacy `js-await` and `js/await` forms continue to work as aliases for now.
  - Add built-in `cljs.test` / `clojure.test` support: `deftest`, `is`, `testing`, `are`, `use-fixtures`, `async`, `run-tests`
  - Fix `with-meta` now preserves callability when applied to a function
  - [#783](https://github.com/squint-cljs/squint/issues/783): auto-load macros from `.cljc` files via `:require` (no need for `:require-macros`); resolve qualified symbols from macro expansions
  - [#784](https://github.com/squint-cljs/squint/issues/784): resolve transitive macro deps and auto-import runtime deps from macro expansion
  - [#809](https://github.com/squint-cljs/squint/issues/809): add `squint.compiler/compile*` and `squint.compiler/transpile*` which accept either a string or a sequence of pre-parsed forms, skipping the `forms -> string -> forms` roundtrip for SSR use cases
  - [#810](https://github.com/squint-cljs/squint/issues/810): shorthand classes in `#html` / `#jsx` were erased when an attrs map was present without a `:class` key
  - [Full changelog](https://github.com/squint-cljs/squint/blob/main/CHANGELOG.md)

- [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - Accept plain `await` as a special form, in anticipation of CLJS next
  - Multiple `:require-macros` clauses with `:refer` now properly accumulate instead of overwriting each other
  - Add `cherry.test` with `clojure.test`-compatible testing API: `deftest`, `is`, `testing`, `are`, `use-fixtures`, `async`, `run-tests`. Macros are compiler built-ins (shared with squint), so no `:require-macros` plumbing is needed in user code.

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
  - Released 1.4.207
  - [#408](https://github.com/babashka/nbb/issues/408): support `IFn` on `defrecord` and `reify`
  - Fix REPL and nREPL not awaiting promesa thenables (e.g. `p/then` results)

- [fs](https://github.com/babashka/fs): file system utility library for Clojure
  - Released 0.5.32 and 0.5.33
  - [#232](https://github.com/babashka/fs/issues/232): add `touch` fn ([@lread](https://github.com/lread) & [@borkdude](https://github.com/borkdude))
  - [#197](https://github.com/babashka/fs/issues/197): docstring review: consistent arg naming, improved docstrings, added `Coercions and Returns` / `Argument Naming Conventions` sections to README ([@lread](https://github.com/lread))
  - [#231](https://github.com/babashka/fs/issues/231): get/set attribute functions were never following links. They now respect the `:nofollow-links` option ([@lread](https://github.com/lread))
  - [#254](https://github.com/babashka/fs/issues/254): fix `split-ext` and `extension` on dotfiles with parent dirs (e.g. `foo/.gitignore`)
  - [#202](https://github.com/babashka/fs/issues/202): `gzip` & `gunzip` now honor dest dir when specified ([@lread](https://github.com/lread))
  - [#215](https://github.com/babashka/fs/issues/215): document effect of `umask` on created files and directories ([@lread](https://github.com/lread))
  - [#182](https://github.com/babashka/fs/issues/182): enable soft & hard link tests on Windows ([@lread](https://github.com/lread))
  - [#242](https://github.com/babashka/fs/issues/242): test: add JDK 26 to CI test matrix ([@lread](https://github.com/lread))

- [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
  - Improve analysis performance by bumping `weavejester/dependency` ([#808](https://github.com/nextjournal/clerk/pull/808))
  - Bump SCI to `v0.12.51` ([#793](https://github.com/nextjournal/clerk/pull/793)), enables `async`/`await` in viewer functions
  - Improve presentation performance ([#803](https://github.com/nextjournal/clerk/pull/803))
  - Remove bb-specific code for array-map data structure ([#805](https://github.com/nextjournal/clerk/pull/805))
  - Preserve TOC opts ([#806](https://github.com/nextjournal/clerk/pull/806))
  - Remove redundant declare of `present+reset!` ([#809](https://github.com/nextjournal/clerk/pull/809))
  - Fix `build-graph` crash on non-Clojure source files ([#810](https://github.com/nextjournal/clerk/pull/810))

- [edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
  - Released 1.5.38 and 1.5.39
  - `parse-ns-form`: include `:use` and `:require-macros` in `:requires`
  - Check if object is iobj before attaching metadata [#141](https://github.com/borkdude/edamame/issues/141) [#142](https://github.com/borkdude/edamame/pull/142)

- [Nextjournal Markdown](https://github.com/nextjournal/markdown): A cross-platform Clojure/Script parser for Markdown
  - Released 0.7.225
  - Add option `:disable-footnotes true` to disable parsing footnotes [#67](https://github.com/nextjournal/markdown/issues/67)

- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
  - Released 0.2.6
  - [#42](https://github.com/borkdude/quickdoc/issues/42): fix var name not recognized in docstring when preceded by multiline backtick expression
  - [#52](https://github.com/borkdude/quickdoc/issues/52): fix formatting of function signature when `:or` destructuring uses namespaced keyword fallback value
  - Dedent indented docstrings before rendering [#53](https://github.com/borkdude/quickdoc/issues/53)

- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
  - Released 0.2.5
  - Bump SCI to 0.12.51, Clojure to 1.12.4
  - Upgrade CI to GraalVM 25; move Windows CI from Appveyor to GitHub Actions
  - Fix bug in native which dropped all match results ([@bsless](https://github.com/bsless))
  - Fix circular reference in `grasp.impl`

- [babashka.nrepl](https://github.com/babashka/babashka.nrepl): The nREPL server from babashka as a library
  - Lock output stream in `send` to prevent interleaved bencode frames from concurrent writes
  - `info` and `lookup` op refinements: `lookup` carries nested `info` map whereas `info` is a flatmap

- [pod-babashka-instaparse](https://github.com/babashka/pod-babashka-instaparse): instaparse from babashka
  - Expose `add-line-and-column-info-to-metadata`
  - Drop macOS Intel builds, now building for macOS aarch64 only
  - Migrate Windows CI from Appveyor to GitHub Actions
  - Upgrade CI to GraalVM 25
  - Add `--features=clj_easy.graal_build_time.InitClojureClasses` to native-image

- [instaparse-bb](https://github.com/babashka/instaparse-bb): Use instaparse from babashka
  - Released 0.0.7
  - Bump pod to 0.0.7
  - Add `add-line-and-column-info-to-metadata` and `get-failure`
  - Fix opts passing in `parser` (e.g. `:output-format :enlive`)
  - Support `java.net.URL` for grammars

- [babashka-sql-pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
  - Released 0.1.5 and 0.1.6
  - [#74](https://github.com/babashka/babashka-sql-pods/issues/74): add DB2 support ([@janezj](https://github.com/janezj))
  - [#72](https://github.com/babashka/babashka-sql-pods/issues/72): handle concurrent requests ([@katangafor](https://github.com/katangafor))
  - Upgrade to Oracle GraalVM 25.0.2; bump `next.jdbc`, `cheshire` (Jackson 2.12 -> 2.20), PostgreSQL, MSSQL, HSQLDB, MySQL Connector/J drivers
  - Remove DuckDB support
  - [#51](https://github.com/babashka/babashka-sql-pods/issues/51): macOS binaries are now aarch64 only

- [http-client](https://github.com/babashka/http-client): HTTP client built on java.net.http
  - Replace defunct `httpstat.us` examples with `httpbin.org` in tests

- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
  - Fix README instructions for dev installation ([@teodorlu](https://github.com/teodorlu))

- [deps.clj](https://github.com/borkdude/deps.clj): a faithful port of the clojure CLI bash script to Clojure
  - Released 1.12.4.1618
  - [#145](https://github.com/borkdude/deps.clj/pull/145): support for installing in FreeBSD and Windows bash environments including MINGW64, MSYS_NT and Cygwin ([@ikappaki](https://github.com/ikappaki))
  - Catch up with Clojure CLI 1.12.4.1618

Contributions to third party projects:

- [ClojureScript](https://github.com/clojure/clojurescript):
  - [CLJS-3470](https://clojure.atlassian.net/browse/CLJS-3470): added `async/await` support (merged!)
  - [CLJS-3476](https://clojure.atlassian.net/browse/CLJS-3476): added `async deftest` support (merged!)
- [weavejester/dependency](https://github.com/weavejester/dependency): improve performance of `depend`, `depends?`, and `topo-sort`
- [weavejester/cljfmt](https://github.com/weavejester/cljfmt): [#404](https://github.com/weavejester/cljfmt/pull/404) babashka compatibility via new [text-diff](https://github.com/borkdude/text-diff) lib (merged!)
- [Engelberg/instaparse](https://github.com/Engelberg/instaparse): submitted [#242](https://github.com/Engelberg/instaparse/pull/242) for babashka compatibility. Required `:bb` reader conditionals to replace the `AutoFlattenSeq` deftype with plain vectors plus metadata markers, swap the `Segment` deftype for a `reify`-based `CharSequence`, and add a CI test runner. Open, awaiting review.

## Other projects

These are (some of the) other projects I'm involved with but little to no activity
happened in the past two months.

<details>
<summary>Click for more details</summary>

- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
- [http-server](https://github.com/babashka/http-server): serve static assets
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
- [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag
- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of rewrite-clj
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
- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo): a leiningen plugin for clj-kondo
- [http-kit](https://github.com/http-kit/http-kit): Simple, high-performance event-driven HTTP client+server for Clojure.
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
- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
- [quickblog](https://github.com/borkdude/quickblog): light-weight static blog engine for Clojure and babashka
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
- [parmezan](https://github.com/borkdude/parmezan): fixes unbalanced or unexpected parens or other delimiters in Clojure files
- [reagami](https://github.com/borkdude/reagami): A minimal zero-deps Reagent-like for Squint and CLJS

</details>
