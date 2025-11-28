---
title: "Annually-Funded Developers' and Clojars Update: Sept./Oct. 2025"
date: 2025-11-28T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Oleksandr Yakushev, Peter Taoussanis, Toby Crawley"  
draft: True  


---

Hello Fellow Clojurists!
This is the fifth report from the 5 developers receiving Annual Funding in 2025. This update also includes Toby Crawley's latest report on Clojars maintenance and support for Aug., Sept/, and October 2025.


[Dragan Duric](#dragan-duric): Apple M Engine Neanderthal, Deep Diamond CPU   
[Eric Dallo](#eric-dallo): ECA, clojure-lsp, clojure-lsp-intellij  
[Michiel Borkent](#michiel-borkent): clj-kondo, babashka, SCI, clj-kondo, scittle, and more...  
[Oleksandr Yakushev](#oleksandr-yakushev): CIDER, clj-async-profiler, nREPL   
[Peter Taoussanis](#peter-taoussanis): Sente, Truss, Trove, Telemere  
[Tony Crawley](#toby-crawley): Ongoing Clojars Maintenance and Support


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

## [eca](https://github.com/editor-code-assistant/eca)  
ECA (Editor Code Assistant) is a OpenSource, free, standardized server written in Clojure to make any editor have AI features like Cursor, Continue, Claude and others.  
There were so many releases and changes to ECA making it way more stable, with new features and fixing lots of bugs, especially with way more people using it daily and contributing!  

### 0.44.0 - 0.78.1  
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

## [clojure-lsp](https://clojure-lsp.io/)  
I worked together with @weavejester from cljfmt to fix some bugs and finally deliver vertical alignment of maps to Clojure via LSP!  
I should release these changes when coming back from Conj.  <br>  

---


## Michiel Borkent  
2025 Annual Funding Report 5. Published November, 2025.  



## Oleksandr Yakushev  
2025 Annual Funding Report 5. Published November , 2025.  



## Peter Taoussanis        
2025 Annual Funding Report 4. Published September 2, 2025.   



## Toby Crawley   
2025 Critical Infrastructure: Clojars Maintentance and Support  
Published xxxx,xx  2025   

Toby's report includes links to Clojars Changelogs for August through xxx 2025 as well as an overview of fixes and updates. He monitors community channels on a regular basis.  
If you have any issues or questions about Clojars, you can find him in the [`#clojars` channel on the Clojurians Slack](https://clojurians.slack.com/archives/C0H28NMAS), or you can file an issue on the [main Clojars GitHub repository](https://github.com/clojars/clojars-web/issues/new/choose).  





