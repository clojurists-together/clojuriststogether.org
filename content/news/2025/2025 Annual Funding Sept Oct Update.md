---
title: "Annually-Funded Developers' and Clojars Update: Sept./Oct. 2025"
date: 2025-11-28T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Oleksandr Yakushev, Peter Taoussanis"  
draft: True  


---

Hello Fellow Clojurists!
This is the fifth report from the 5 developers receiving Annual Funding in 2025. Thanks everyone for the fantastic work! 


[Dragan Duric](#dragan-duric): Clojure ML, Clojure CUDA, Neanderthal, Deep Diamond, Commons   
[Eric Dallo](#eric-dallo): ECA, clojure-lsp  
[Michiel Borkent](#michiel-borkent): Eucalypt, Reagent, Squint, babashka, and more...  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER, nREPL, JDK25  
[Peter Taoussanis](#peter-taoussanis): Sente, Tempel, Carmine  



## Dragan Duric  
2025 Annual Funding Report 5. Published November 9, 2025.  

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

Having a decent Apple CPU engine for Neanderthal and a BNNS-based CPU engine for Deep Diamond completed in the previous periods,  my main focus was on improving things achieved in the previous periods to support features and fix bugs that discovered while working on Clojure ML (diamond-onnxrt), the other major project funded by CT.  

The first milestone for THIS project is that I supproted Intel's DNNL (the Deep Diamond's CPU backend)
on Apple's MacOS (both arm64 and older intel based). Due to Deep Diamond's flexible design, this required only small changes to DD code, while the main issue was enabling and testing that architecture in the build of upstream libraries.  

**Several other areas fixed/improved throughout Uncomplicate libraries during this period include:**  
- Upgrading CUDA to the major new version 13.0, including improvements in code  
- Working with upstream libraries on improving support for functionality that we need in Clojure/Uncomplicate  
- Improving the UX of the distribution of dependency artifacts for CUDA and other dependencies that we use.  
- Upgrading DNNL to 3.9.2  
- Enable standalone activations, and other small changes to make sure DD accomodate onnx models inside DD network machinery  
- Assorted improvements in supported types  
- Helped in enabling CoreML in upstream onnxruntime build on MacOS  

**I released several new versions of Uncomplicate libraries with these user-facing improvements including:**   
- Deep Diamond  
- Neanderthal  
- ClojureCUDA  
- Commons  

**Hammock time.** Researched available native libraries that we can integrate next into Clojure ecosystem, including Nvidia'r TensorRT and Triton, Intel's OpenVino, and similar AI-related stuff.

I wrote two tutorials and published them on dragan.rocks blog. They were received quite favorably,
and it seems to me that Clojure programmers are quite eager to learn how to apply the stuff I've worked on.  <br>

---

## Eric Dallo  
2025 Annual Funding Report 5. Published November 10, 2025.  

In these last 2 months I mainly focused on ECA development and related projects and improvements along with my Clojure Conj talk that's coming this week, excited for it!  

### [eca](https://github.com/editor-code-assistant/eca)  
ECA (Editor Code Assistant) is a OpenSource, free, standardized server written in Clojure to make any editor have AI features like Cursor, Continue, Claude and others.  
There were so many releases and changes to ECA making it way more stable, with new features and fixing lots of bugs, especially with way more people using it daily and contributing!  

#### 0.44.0 - 0.78.1  
**Most important changes:**  
- __Hooks support__: This makes ECA one of the few opensource public tools with high customizable hooks support, hooks allow users to react to certain events like pre or post request or tool call.  
- __Rewrite__: A new brand feature allowing to select a code and tell LLM to do something.  
- __Completion (alpha)__: First big step to allow completion, requiring only improvements in LLM output quality via model/prompt tunning.  
- __Plan behavior__: Hugely improve plan behavior, making ECA one of the best tools for planning code changes, really a key feature of ECA.  
- __Clojure MCP integration__: ECA is extensible so we can make it behave differently for MCPs, and we did for clojureMCP with Bruce's help, making ECA show diffs beautifully before they actually happen.  
- __Contexts improvements__: better context management via user/system prompts with options to unwrap files or just mention, making more token effective/controllable.  
- __More providers__: Support google and more providers via more API improvements, making ECA compatible with most models at market and local.  
- __Native tool improving__: Improved lots of ECA tools, based on tests and user feedback, making way more assertive and precise.  
- __Metrics via Opentelemetry__: If you wanna take metrics of prompts and tools, ECA supports that now, really useful for companies that wanna track LLM usage.  

**And many more, check all changes below:**  
- Fix regression: completion broken after rewrite feature API changes.  
- Prefix tool name with server to LLM: <server>__<toolname>. #196  
- Remove `eca_` prefix from eca tools, we already pass server prefix (eca) after #196.  
- Add `approval` arg to preToolCall hook input.  
- Add error rewrite message content message.  
- Fix token renew when using rewrite feature.  
- Improve rewrite error handling.  
- Custom providers do not require the existense of `key` or `keyEnv`. #194  
- New feature: rewrite. #13  
- Updated instructions for `/login` command and invalid input handling.  
- Fix server name on `chat/contentReceived` when preparing tool call.  
- Fix variable replacing in some tool prompts.  
- Improve planning mode prompt and tool docs; clarify absolute-path usage and preview rules.  
- Centralize path approval for tools and always list all missing required params in INVALID_ARGS.  
- Fix 0.75.3 regression on custom openai-chat providers.  
- Support custom think tag start and end for openai-chat models via `think-tag-start` and `think-tag-end` provider configs. #188  
- Bump MCP java sdk to 0.15.0.  
- Add missing models supported by Github Copilot  
- Fix regression: openai-chat tool call arguments error on some models.  
- Improve protocol for tool call output formatting for tools that output json.  
- Fix inconsistencies in `eca_read_file` not passing correct content to LLM when json.  
- Improved file contexts: now use :lines-range  
- BREAKING ECA now only supports standard plain-text netrc as credential file reading. Drop authinfo and gpg decryption support. Users can choose to pass in their own provisioned netrc file from various secure source with `:netrcFile` in ECA config.  
- Improved `eca_edit_file` to automatically handle whitespace and indentation differences in single-occurrence edits.  
- Fix contexts in user prompts (not system contexts) not parsing lines ranges properly.  
- Support non-stream providers on openai-chat API. #174  
- Support use API keys even if subscription is logged. #175  
- Fix tool call approval ignoring eca tools.  
- Fix tool call approval ignoring configs for mcp servers.  
- Fix tool call approval thread lock.  
- Improve chat title generation.  
- Fix completion error handling.  
- Default to `openai/gpt-4.1` on completion.  
- Add `:config-file` cli option to pass in config.  
- Add support for completion. #12  
- Run `preToolCall` hook before user approval if any. #170  
- Only include `parallel_tool_calls` to openai-responses and openai-chat if true. #169  
- Support clojureMCP dry-run flags for edit/write tools, being able to show preview of diffs before running tool.  
- Assoc `parallel_tool_calls` to openai-chat only if truth.  
- Fix regression in `/compact` command. #162  
- Fix to use local zone for time presentation in `/resume`.  
- Use web-search false if model capabiltiies are not found.  
- Support `/resume` a specific chat.  
- Fix `openai-chat` api not following `completionUrlRelativePath`.  
- Fix web-search not working for custom models using openai/anthropic apis.  
- Support `visible` field in hooks configuration to show or not in client.  
- Deprecate prePrompt and postPrompt in favor of preRequest and prePrompt.  
- Fix model capabilities for models with custom names.  
- Fix prePrompt hook.  
- Add hooks support. #43  
- Fix regression on models with no extraPayload.  
- Support multiple model configs with different payloads using same model name via `modelName` config. (Ex: gpt-5 and gpt-5-high but both use gpt-5)  
- Add `anthropic/haiku-4.5` model by default.  
- Unwrap mentioned @contexts in prompt appending as user message its content. #154  
- Improved flaky test #150  
- Obfuscate env vars in /doctor.  
- Bump clj-otel to 0.2.10  
- Rename $ARGS to $ARGUMENTS placeholder alias for custom commands.  
- Support recursive AGENTS.md file inclusions with @file mention. #140  
- Improve plan behavior prompt. #139  
- Add support for secrets stored in authinfo and netrc files  
- Added tests for stopping concurrent tool calls. #147  
- Improve logging.  
- Improve performance of `chat/queryContext`.  
- Added ability to cancel tool calls. Only the shell tool currently. #145  
- Bump mcp java sdk to 0.14.1.  
- Improve json output for tools that output json.  
- Fix duplicated arguments on `toolCallPrepare` for openai-chat API models. https://github.com/editor-code-assistant/eca-emacs/issues/56  
- Add `server` to tool call messages.  
- Fix last word going after tool call for openai-chat API.  
- Fix retrocompatibility with some models not working with openai-chat like deepseek.  
- Add `gpt-5-codex` model as default for `openai` provider.  
- Support "accept and remember" tool call per session and name.  
- Avoid generating huge chat titles.  
- Add `claude-sonnet-4.5` for github-copilot provider.  
- Add `prompt-received` metric.  
- Use a default of 32k tokens for max_tokens in openai-chat API.  
- Improve rejection prompt for tool calls.  
- Use `max_completion_tokens` instead of `max_tokens` in openai-chat API.  
- Support context/tokens usage/cost for openai-chat API.  
- Support `anthropic/claude-sonnet-4.5` by default.  
- More tolerant whitespace handling after `data:`.  
- Fix login for google provider. #134  
- Fix chat titles not working for some providers.  
- Enable reasoning for google models.  
- Support reasoning blocks in models who use openai-chat api.  
- Support google gemini as built-in models. #50  
- Deprecate repoMap context, will be removed in the future.  
  - After lots of tunnings and improvements, the repoMap is no longer relevant as `eca_directory_tree` provides similar and more specific view for LLM to use.  
- Support `toolCall shellCommand summaryMaxLength` to configure UX of command length. #130  
- Fix MCP prompt for native image.  
- Improve progress notification when tool is running.  
- Bump MCP java sdk to 0.13.1  
- Improve MCP logs on stderr.  
- Support tool call rejection with reasons inputed by user. #127  
- Greatly reduce token consuming of `eca_directory_tree`  
  - Ignoring files in gitignore  
  - Improving tool output for LLM removing token consuming chars.  
- Fix renew oauth tokens when it expires in the same session.  
- Fix metrics exception when saving to db.  
- Fix db exception.  
- Fix usage reporting.  
- Return new chat metadata content.  
  - Add chat title via prompt to LLM.  
- Add support for Opentelemetry via `otlp` config.  
  - Export metrics of server tasks, tool calls, prompts, resources.  
- Use jsonrpc4clj instead of lsp4clj.  
- Bump graalvm to 24 and java to 24 improving native binary perf.  
- Avoid errors on multiple same MCP server calls in parallel.  
- Fix openai cache tokens cost calculation.  
- Improve welcome message.  
- Improve large file handling in `read-file` tool:  
  - Replace basic truncation notice with detailed line range information and next-step instructions.  
  - Allow users to customize default line limit through `tools.readFile.maxLines` configuration (keep the current 2000 as default).  
- Moved the future in :on-tools-called and stored it in the db. #119  
- Support `compactPromptFile` config.  
- Fix tools not being listed for servers using mcp-remote.  
- Add `/compact` command to summarize the current conversation helping reduce context size.  
- Add support for images as contexts.  
- Support http-streamable http servers (non auth support for now)  
- Fix promtps that send assistant messages not working for anthropic.  
- Fix manual anthropic login to save credentials in global config instead of cache.  
- Minor log improvement of failed to start MCPs.  
- Bump mcp java sdk to 1.12.1.  
- Fix mcp servers default timeout from 20s -> 60s.  
- Support timeout on `eca_shell_command` with default to 1min.  
- Support `@cursor` context representing the current editor cursor position. #103  
- Fix setting the `web-search` capability in the relevant models  
- Fix summary text for tool calls using `openai-chat` api.  
- Bump mcp-java-sdk to 0.12.0.  
- Added missing parameters to `toolCallRejected` where possible.  PR #109  
- Improve plan prompt present plan step.  
- Add custom behavior configuration support. #79  
  - Behaviors can now define `defaultModel`, `disabledTools`, `systemPromptFile`, and `toolCall` approval rules.  
  - Built-in `agent` and `plan` behaviors are pre-configured.  
  - Replace `systemPromptTemplateFile` with `systemPromptFile` for complete prompt files instead of templates.  
- Remove `nativeTools` configuration in favor of `toolCall` approval and `disabledTools`.
  - Native tools are now always enabled by default, controlled via `disabledTools` and `toolCall` approval.  
- Add `totalTimeMs` to reason and toolCall content blocks.  
- Add nix flake build.  
- Stop prompt does not change the status of the last running toolCall. #65  
- Add `toolCallRunning` content to `chat/contentReceived`.  
- Support more providers login via `/login`.  
  - openai  
  - openrouter  
  - deepseek  
  - azure  
  - z-ai  
- Remove the need to pass `requestId` on prompt messages.  
- Support empty `/login` command to ask what provider to login.  
- Support user configured custom tools via `customTools` config. #92  
- Fix default approval for read only tools to be `allow` instead of `ask`.  
- Fix renew token regression.  
- Improve error feedback when failed to renew token.  
- Support `deny` tool calls via `toolCall approval deny` setting.  

### [clojure-lsp](https://clojure-lsp.io/)  
I worked together with @weavejester from cljfmt to fix some bugs and finally deliver vertical alignment of maps to Clojure via LSP!  
I should release these changes when coming back from Conj.  <br>  

---


## Michiel Borkent  
2025 Annual Funding Report 5. Published November 3, 2025.  

In this post I'll give updates about open source I worked on during September and October 2025.  
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
The summer heat has faded, and autumn is upon us. One big focus for me is
preparing my talk for [Clojure Conj
2025](https://www.2025.clojure-conj.org/schedule), titled "Making tools
developers actually use". I did a test run of the talk at the Dutch Clojure
Meetup. It went a bit too long at 45 minutes, so I have to shrink it almost by
half for the Conj. The more I work on the talk the more ideas come up, so it's
challenging!  

<img width="445" height="414" alt="image" src="https://github.com/user-attachments/assets/fe19fe55-ed04-4645-aaee-6466580aa719" />  

Presentation at Dutch Clojure meetup in October 2025.


Of course I spent a ton of time on OSS the past two months as well.
Some special mentions:  
- I'm pretty excited by [Eucalypt](https://github.com/chr15m/eucalypt), a remake
  of [Reagent](https://github.com/reagent-project/reagent) for
  [Squint](https://github.com/squint-cljs/squint) without React by Chris
  McCormick. It lets you build UIs with the Reagent API in less than 10kb of
  gzip'ed JS. The code was initially generated by an LLM, but now work is going
  into making the code base thoroughly tested and simplified where possible.  
- After studying Eucalypt's code I figured that making an even more minimal
  Reagent-like by hand would be fun. This is where I came up with
  [Reagami](https://github.com/borkdude/reagami). The API looks like a hybrid
  between Reagent and [Replicant](https://replicant.fun/). You can build apps with Reagami starting around 5kb gzip'ed.  
- [Edamame](https://github.com/borkdude/edamame) got [Clojure CLR](https://github.com/clojure/clojure-clr) support thanks to Ambrose Bonnaire-Sergeant.  
- [SCI](https://github.com/babashka/sci) Clojure CLR support is underway. The `sci.impl.Reflector` code, based on `clojure.lang.Reflector` was ported to Clojure with the purpose that it would then be easier to translate to Clojure CLR.  
- [Cljdoc](https://github.com/cljdoc/cljdoc/blob/488fe6282737c1237c5394a66a7e8392a000c6bb/doc/cljdoc-developer-technical-guide.adoc#front-end-code) chose squint for its small bundle sizes and easy migration off of TypeScript towards CLJS  
- Via work on Squint, I found a way to optimize `str` in ClojureScript (worst case 4x, best case 200x)  

**Here are updates about the projects/libraries I've worked on in the last two months in detail.** 
- [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
  - Bump to clojure 1.12.3
  - [#1870](https://github.com/babashka/babashka/issues/1870): add `.addMethod` to clojure.lang.MultiFn
  - [#1869](https://github.com/babashka/babashka/issues/1869): add `clojure.lang.ITransientCollection` for `instance?` checks
  - [#1865](https://github.com/babashka/babashka/issues/1865): support `reify` + `equals` + `hashCode` on `Object`
  - Add `java.nio.charset.CharsetDecoder`, `java.nio.charset.CodingErrorAction`, `java.nio.charset.CharacterCodingException` in support of the [sfv](https://github.com/outskirtslabs/sfv) library
  - Fix `nrepl-server` completions and lookup op to be compatible with rebel-readline
  - Add `clojure.lang.Ref` for `instance?` checks
  - Bump SCI: align unresolved symbol error message with Clojure
  - Use GraalVM 25
  - Bump deps.clj to 1.12.3.1557
  - Change unknown or REPL file path to `NO_SOURCE_PATH` instead of `<expr>` since this can cause issues on Windows when checking for absolute file paths
  - [#1001](https://github.com/babashka/babashka/issues/1001): fix encoding issues on Windows in Powershell. Also see this [GraalVM](https://github.com/oracle/graal/issues/12249) issue
  - Fixes around `java.security` and allowing setting deprecated Cipher suites at runtime. See this [commit](https://github.com/babashka/babashka/commit/ace237832a5844330f5f9c342e1498eb0ca5f7ac).
  - Support Windows Git Bash in bash install script

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - ClojureCLR support in progress (with Ambrose Bonnaire Sergeant)

- [edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
  - 1.5.33 (2025-10-28)
  - Add ClojureCLR support ([@frenchy64](https://github.com/frenchy64))

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.<br>
  - Unreleased
  - [#2651](https://github.com/clj-kondo/clj-kondo/issues/2651): resume linting after paren mismatches
  - 2025.10.23
  - [#2590](https://github.com/clj-kondo/clj-kondo/issues/2590): NEW linter: `duplicate-key-in-assoc`, defaults to `:warning`
  - [#2639](https://github.com/clj-kondo/clj-kondo/issues/2639): NEW `:equals-nil` linter to detect `(= nil x)` or `(= x nil)` patterns and suggest `(nil? x)` instead ([@conao3](https://github.com/conao3))
  - [#2633](https://github.com/clj-kondo/clj-kondo/issues/2633): support new `defparkingop` macro in core.async alpha
  - [#2635](https://github.com/clj-kondo/clj-kondo/pull/2635): Add `:interface` flag to `:flags` set in `:java-class-definitions` analysis output to distinguish Java interfaces from classes ([@hugoduncan](https://github.com/hugoduncan))
  - [#2636](https://github.com/clj-kondo/clj-kondo/issues/2636): set global SCI context so hooks can use `requiring-resolve` etc.
  - [#2641](https://github.com/clj-kondo/clj-kondo/issues/2641): fix linting of `def` body, no results due to laziness bug
  - [#1743](https://github.com/clj-kondo/clj-kondo/issues/1743): change `:not-empty?` to only warn on objects that are already seqs
  - Performance optimization for `:ns-groups` (thanks [@severeoverfl0w](https://github.com/severeoverfl0w))
  - Flip `:self-requiring-namespace` level from `:off` to `:warning`
  - 2025.09.22
  - Remove `dbg` from `data_readers.clj` since this breaks when using together with CIDER
  - 2025.09.19
  - [#1894](https://github.com/clj-kondo/clj-kondo/issues/1894): support `destruct` syntax
  - [#2624](https://github.com/clj-kondo/clj-kondo/issues/2624): lint argument types passed to `get` and `get-in` (especially to catch swapped arguments to get in threading macros) ([@borkdude](https://github.com/borkdude), [@Uthar](https://github.com/Uthar))
  - [#2564](https://github.com/clj-kondo/clj-kondo/issues/2564): detect calling set with wrong number of arguments
  - [#2603](https://github.com/clj-kondo/clj-kondo/issues/2603): warn on `:inline-def` with nested `deftest`

- [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  - Support passing keyword to `mapv`
  - Inline `identical?` calls
  - Clean up emission of paren wrapping
  - Add `nat-int?`, `neg-int?`, `pos-int?` ([@eNotchy](https://github.com/eNotchy))
  - Add `rand`
  - Fix rendering of `null` and `undefined` in `#html`
  - [#747](https://github.com/squint-cljs/squint/issues/747): `#html` escape fix
  - Optimize nested `assoc` calls, e.g. produced with `->`
  - Avoid object spread when object isn't shared (`auto-transient`)
  - Optimize `=`, `and`, and `not=` even more
  - `not=` on undefined and false should return `true`
  - Optimize code produced for `assoc`, `assoc!` and `get` when object argument can be inferred or is type hinted with `^object`
  - Optimize `str` using macro that compiles into template strings + `?? ''` for null/undefined
  - Fix [#732](https://github.com/squint-cljs/squint/issues/732): `take-last` should return `nil` or empty seq for negative numbers
  - [#725](https://github.com/squint-cljs/squint/issues/725): `keys` and `vals` should work on `js/Map`
  - Make `map-indexed` and `keep-indexed` lazy
  - Compile time optimization for `=` when using it on numbers, strings or keyword literals
  - Switch `=` to a deep-equals implementation that works on primitives, objects, `Arrays`, `Maps` and `Sets`
  - Fix [#710](https://github.com/squint-cljs/squint/issues/710): add `parse-double`
  - Fix [#714](https://github.com/squint-cljs/squint/issues/714): `assoc-in` on `nil` or `undefined`
  - Fix [#714](https://github.com/squint-cljs/squint/issues/714): `dissoc` on `nil` or `undefined`
  - Basic `:import-maps` support in `squint.edn` (just literal replacements, prefixes not supported yet)

- [reagami](https://github.com/borkdude/reagami): A minimal zero-deps Reagent-like for Squint and CLJS
  - First releases

- [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
  - Support evaluation of quoted regex
  - Support macros defined in notebooks
  - Bump cherry

- [cljs-str](https://github.com/borkdude/cljs-str)
  - More efficient drop-in replacement for CLJS str. This work was already upstreamed into CLJS, so coming near you in the next CLJS release.

- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
  - Support finding unused git deps

- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
  - Fix SCI regression where interop on keywords like `(.catch ...)` was accidentally munched

- [Nextjournal Markdown](https://github.com/nextjournal/markdown)
  - Add `:start` attribute to ordered lists not starting with 1 (@spicyfalafel)

- [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - Bump squint compiler common component and standard library
  - Bump other deps
  - Optimize `=`, `str`, `not=`
  - Support `:macros` option + `:refer` so you can use unqualified macros using compiler state (see `macro-state-test`)

- [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
  - Released several versions catching up with the clojure CLI

- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
  - Add `close-connection`
  - Fix #38: add `get-connection` to cache connection
  - Fix potential memory leak
  - Better handling of parent process death by handling EOF of stdin
  - [#25](https://github.com/babashka/pod-babashka-go-sqlite3/issues/25): use musl to compile linux binaries to avoid dependency on glibc

- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
  - Fix extra newline in codeblock

Contributions to third party projects:

- [ClojureScript](https://github.com/clojure/clojurescript)
  - Optimize `str` (4x worst case, 200x best case)
  - Make `munge-str` public

- [Selmer](https://github.com/yogthos/Selmer)
  - [Handle error for template without closing delimiter](https://github.com/yogthos/Selmer/commit/7588259c9e356372c0e010594ae0d3d35a89ffca)

- [clojure-lsp](https://github.com/clojure-lsp/clojure-lsp)
  - Optimize `:ns-groups` handling by caching regex creation and usage

### Other projects  
These are (some of the) other projects I'm involved with but little to no activity
happened in the past month.  

<details>
<summary>Click for more details</summary>
- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
- [fs](https://github.com/babashka/fs) - File system utility library for Clojure
- [http-server](https://github.com/babashka/http-server): serve static assets
- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
- [http-client](https://github.com/babashka/http-client): babashka's http-client
- [quickblog](https://github.com/borkdude/quickblog): light-weight static blog engine for Clojure and babashka
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
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

</details>

<br>

---


## Oleksandr Yakushev  
2025 Annual Funding Report 5. Published November 5 , 2025.  

Hello friends! Here's my update on September-October 2025 Clojurists Together work. I was mostly focused on CIDER-related work during these months.  

### nREPL  
nREPL has received [1.5.0](https://github.com/nrepl/nrepl/blob/master/CHANGELOG.md#150-2025-10-15) and [1.5.1](https://github.com/nrepl/nrepl/blob/master/CHANGELOG.md#151-2025-10-18) updates which improve the reliability of `load-file` middleware and enable further improvements to CIDER debugger (mentioned below).  
-   [#393](https://github.com/nrepl/nrepl/pull/393): Add `forward-system-output` op for forwarding System/out and System/err output to the client.  
-   [#386](https://github.com/nrepl/nrepl/pull/386): Add support for `XDG_CONFIG_HOME`.  
-   [#385](https://github.com/nrepl/nrepl/pull/385): Preserve filename in functions compiled during regular eval.  

### CIDER  
We shipped [CIDER 1.20](https://clojurians.slack.com/archives/C06MAR553/p1762335579153309) which includes several highly-requested features and fixes.  
-   Debugger tags like `#dbg` are correctly processed when the whole buffer is compiled with `C-c C-k` ([cider-nrepl#951](https://github.com/clojure-emacs/cider-nrepl/pull/951))  
-   Tidy namespaced keywords in the inspector ([orchard#354](https://github.com/clojure-emacs/orchard/pull/354))  
-   Miscellaneous fixes and improvements to inspector and source file resolution.  

### CIDER-adjacent libraries  
-   Released orchard 0.37.0 and [0.37.1](https://github.com/clojure-emacs/orchard/blob/master/CHANGELOG.md#0371-2025-10-14).  
-   Released [cider-nrepl 0.58.0](https://github.com/clojure-emacs/cider-nrepl/blob/master/CHANGELOG.md#0580-2025-10-16).  
-   Released [compliment 0.7.1](https://github.com/alexander-yakushev/compliment/blob/master/CHANGELOG.md#071-2025-09-19) (improved handling of namespace aliases)  

### JDK25 release support  
As it happens semi-anually, the release of new JDK prompts ensuring that the libraries and tools are compatible with the newest version.  
-   Added JDK25 image to [docker-clojure](https://github.com/Quantisan/docker-clojure/pull/266).  
-   Added JDK25 support to [virgil 0.5.0](https://github.com/clj-commons/virgil/blob/master/CHANGELOG.md#050-2025-09-29).  
-   Updated CI pipelines to test against JDK25 in all CIDER libraries and all [clojure-goes-fast.com](http://clojure-goes-fast.com/) tools.  <br>

---

## Peter Taoussanis        
2025 Annual Funding Report 5. Published November 7, 2025.   

### Open source update  
A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of [my open source work](https://www.taoensso.com/my-work#open-source)! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  
\- [Peter Taoussanis](https://www.taoensso.com)  

Hi everyone, hope you've all been well! 👋 Almost the end of another year, unbelievable 🫣 Note that some releases ran a bit late this period so were actually published in November, but I'm including them here since they're part of my Sep-Oct tasks.  

### Recent work  
> (See [here](https://www.taoensso.com/news#open-source) for all releases)  

### Sente  
> [Sente](https://www.taoensso.com/sente) provides an easy-to-use abstraction over WebSockets and AJAX to help write realtime web apps with Clj and Cljs.  
Sente v1.21 is [now out](https://github.com/taoensso/sente/releases/tag/v1.21.0)!  

This is a **major release** with plenty of new features, performance improvements, reliability improvements, and a number of bug fixes. Big thanks to all contributors and testers! As usual please see the [release notes](https://github.com/taoensso/sente/releases/tag/v1.21.0) for details. Some highlights include:  
- [Support](https://github.com/taoensso/sente/issues/470) for **binary serialization**, and a new high-speed **MessagePack** [implementation](https://github.com/taoensso/sente/commit/757ebd26e96762d25ab1153ab1ffbe0063027b16) ([benchmarks](https://github.com/taoensso/sente/wiki/5-Packers#which-to-use))  
- [Support](https://github.com/taoensso/sente/commit/f8a3fad4856a78f341679e5e80ce3bdad7498952) for **binary compression**, and a new gzip [implementation](https://cljdoc.org/d/com.taoensso/sente/CURRENT/api/taoensso.sente.packers.gzip)  
- A new **lightweight timer** [implementation](https://github.com/taoensso/sente/commit/173652df2874b4e6bfc2edb9d884743f8b2d7b2e) based on work from http-kit  
- Decreased dependency size  
- Pluggable un+structured logging via [Trove](https://www.taoensso.com/trove)  

### Tempel  
> [Tempel](https://www.taoensso.com/tempel) is a new high-level data security framework for Clojure  
After an extended release candidate period, Tempel [v1 is finally out](https://github.com/taoensso/tempel/releases/tag/v1.0.0)!  

Tempel provides high-level crypto utils to help **protect user data**. It offers a coherent and opinionated API focused on helping with the toughest parts of actually **using encryption in practice**:  
- Understanding **what keys you'll need** (algorithms, parameters, etc.).  
- Understanding how **the various algorithms/schemes fit together** (when and how to use hybrid schemes, etc.).  
- **Maintaining best-practices over time** (e.g. auto migrating from compromised algorithms, auto incrementing work factors, etc.).  
- **Key management** (key rotation, password resets, admin backups, etc.).  

It includes extensive **beginner-oriented** [documentation](https://github.com/taoensso/tempel/wiki/1-Getting-started), docstrings, and error messages.  

I'm proud of how Tempel came out, and my hope is that it might be a practical and approachable tool that goes beyond the usual "here are all these complicated low-level crypto utils, good luck!".  

There's a 36 min [demo video](https://www.youtube.com/watch?v=sULZVFhR848) (36m) that showcases some if the main functionality.  

### Carmine  
> [Carmine](https://www.taoensso.com/carmine) is a mature, idiomatic [Redis](https://www.redis.io) for Clojure  

Carmine [v3.5.0 is out now](https://github.com/taoensso/carmine/releases/tag/v3.5.0), the first major release in >1 year.  

**This release includes:**  
- Structured logging support via [Trove](https://www.taoensso.com/trove)  
- Support for the latest Redis commands  
- [Truss contextual exceptions](https://github.com/taoensso/truss#contextual-exceptions)  
- Many other small improvements  

It also includes the first publicly released (though experimental and undocumented!) next-gen Carmine v4 core. This is basically a complete rewrite of Carmine, using the latest Redis protocol - and incorporating tons of performance and flexibility improvements. This has been a very large and ongoing job, and there’s still plenty of work to do. But I now have the new core happily running in a couple production environments, so there’s definite forward progress.  

BTW for folks that might not have been following- Redis itself has had a lot of really cool developments recently. The new dev team seems to be doing a great job and has been [very productive](https://github.com/redis/redis/releases) re: new features and performance improvements. Salvatore (the original Redis author) is also actively [involved again](https://redis.io/blog/welcome-back-to-redis-antirez/), and introduced a very useful new [vector set type](https://redis.io/blog/announcing-vector-sets-a-new-redis-data-type-for-vector-similarity/) that’s a great fit for similarity searching against vectors produced by LLMs, etc.  

### Talk: Effective Open Source Maintenance Maintenance  
So I've been actively doing Clojure open source now for almost 14 years (cue clichés about time flying). I've made some observations in that time that I think might be useful, so I recorded a [talk on the subject](https://www.youtube.com/watch?v=IlV8R6k8XvY).  

The focus is on better understanding some of the **emotional dynamics** of doing (esp. prolonged) open source work. The talk's directed at **OSS users** and includes actionable tips on how users can help **reduce creator burnout** in some small/simple ways.  

### Other stuff  
Lots of other small stuff, including minor releases for [Trove](https://www.taoensso.com/trove) (modern structured logging facade for Clj/s), and [http-kit](https://www.taoensso.com/http-kit) (lightweight HTTP server+client).  

And of course the usual support on GitHub and Slack, etc. 👍  

### Upcoming work  
- Want to focus on ensuring that all pre-existing work is in good shape as the year comes to a close. This'll include updating libraries to sync dependencies, reviewing outstanding issues, updating docs and examples, etc.  

- Have some efficiency improvements planned for [Nippy](https://www.taoensso.com/nippy) based on similar optimizations I made while implementing Sente's new MessagePack implementation. Likewise, want to consider adding cross-platform support to Nippy for efficient binary Clj<->Cljs serialization. That'd be something for 2026, though I may start sketching out a prototype soon.  
  
- First steps toward a big cleanup of [Encore](https://www.taoensso.com/encore) to make it smaller and/or more modular. This'll be the first step toward trying to improve Babashka support across more of my libs (esp. Telemere).  <br>






