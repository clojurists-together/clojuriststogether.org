---
title: "Annually-Funded Developers' Update: July & August 2026"
date: 2026-09-13T14:00:00+12:00
author: Kathy Davis
summary: "Bozhidar Batsov, Clojure Camp, Eric Dallo, Michiel Borkent, Jeaye Wilkerson"  



---

Hello Fellow Clojurists!

This is the fourth of six reports from the developers who are receiving annual funding for 2026. Thanks to everyone for supporting their work and these important contributions to the Clojure community. 
Their previous reports can be found here:  
[January/February 2026](https://www.clojuriststogether.org/news/annually-funded-developers-update-january-february-2026/)   
[March/April 2026](https://www.clojuriststogether.org/news/annually-funded-developers-update-march-april-2026/)   <br>
[May/June 2026]()

<br>




[**Bozhidar Batsov:**](#bozhidar-batsov) CIDER 2.0, cider-nREPL,Sayid, Orchard, Drawbridge, and more   
[**Clojure Camp:**](#clojure-camp)  Supporting and engaging new Clojurians at Conj  
[**Eric Dallo:**](#eric-dallo) ECA,clojure-lsp  
[**Jeaye Wilkerson:**](#jeaye-wilkerson) Jank optimization, runtime exceptions, Error pages, C++, Commons  
[**Michiel Borkent:**](#michiel-borkent) SCI, clj-kondo,Babashka, squint, Buzz, Choq: Cherry, Cljbang.el and more   



## Bozhidar Batsov  
2026 Annual Funding Report 3. Published Sept. 10, 2026.  

The summer turned out to be just as busy as the spring. CIDER 2.0 finally
shipped, and once it was out the door I used the momentum to sweep through
pretty much every corner of the nREPL/CIDER ecosystem - some long-neglected
projects got proper releases, and the nREPL protocol got a couple of brand new
implementations in languages I play on the side from time to time. The
highlights:  

- CIDER 2.0 ("Terceira") is out, followed by 2.0.1, and 2.1 is taking shape on master  
- [clj-refactor](https://github.com/clojure-emacs/clj-refactor.el) 4.0 is out  
- [Sayid](https://github.com/clojure-emacs/sayid) went from 0.4 to 0.8 in the span of three weeks  
- [Drawbridge](https://github.com/nrepl/drawbridge), nREPL's HTTP transport, got its first meaningful release in years  
- nREPL went polyglot: [nREPL servers for Erlang and Elixir](https://github.com/nrepl/nrepl-beam) and [an OCaml client](https://github.com/nrepl/mezcaml)  
- [clj-suitable](https://github.com/clojure-emacs/clj-suitable) 0.7 and 0.8 closed most of the gap between ClojureScript and Clojure completion  
- A lot of work landed on nREPL's master (TLS hardening, URL-based connections, docs) and a new release is right around the corner.  

Below are the details, project by project.  

### CIDER  

[CIDER 2.0 ("Terceira")](https://github.com/clojure-emacs/cider/releases/tag/v2.0.0) landed on July 15, right on the schedule I had
announced in the preview post. The big themes were covered in the last report
(transient menus, inline macro stepping, call-graph browsers, source-based
find-references, the tracing and tap buffers, rich content in the REPL), so
here's what changed between the preview and the final release:  

- `cider-doctor`, which checks your Emacs setup and the active nREPL session for common problems and produces a copy-pasteable report
- an `orchard` value for `cider-print-fn`, selecting cider-nrepl's much faster `orchard.pp` pretty-printer
- SSH tunnels now forward a free local port, so remote REPLs sharing a port no longer collide on localhost
- `C-c C-d` at the stdin prompt sends end-of-input, and stdin is routed to the exact connection that asked for it
- a long tail of nREPL client fixes: a slow memory leak on the eldoc/completion path, `nrepl-dict-merge` mutating a shared literal, notifications treated as format strings

[CIDER 2.0.1](https://github.com/clojure-emacs/cider/releases/tag/v2.0.1) followed a week later with fixes for the problems early
adopters ran into: evaluation in a dependency's source buffer erroring with
"No linked CIDER sessions" (in several variants), `cider-enlighten-mode` never
lighting anything up (a 1.22 regression), the macroexpansion commands refusing
to expand `let`/`fn`/`loop`, and `load-file` potentially freezing Emacs on a
huge result.  

After that master (the future CIDER 2.1) kept moving at a steady pace. A few
of the things that landed there:  

- CIDER's dynamic font-locking (REPL-defined macros, functions, deprecated/instrumented/traced symbols) now works better in `clojure-ts-mode` buffers via tree-sitter. Previously it worked "officially" only under `clojure-mode`. The debugging reader tags are highlighted there too.
- A new `cider-preferred-clojure-mode` controls which mode CIDER uses to font-lock the code it renders - REPL results, doc examples, overlays and its own display buffers. `clojure-ts-mode` is finally a first-class citizen in CIDER.
- Symbol prompts can go through `completing-read` (so Vertico/Ivy/Helm kick in) and completion annotations render as an aligned type/namespace column in Corfu, Vertico and the built-in `*Completions*`.
- Connecting got smarter. Container-published nREPL ports are resolved for `/docker:` and `/podman:` buffers, `lein trampoline` REPLs are detected, `.nrepl-port` files are no longer discarded on systems without `lsof`, and there's a new ["How CIDER Finds Ports"](https://docs.cider.mx/cider/basics/up_and_running.html#how-cider-finds-ports) section in the manual.
- Every form command got an "at point" variant (inspect, pprint, macroexpand, format, insert in REPL), there's a `cider-inspect-menu` listing every way to start an inspection, and the contents of `comment` forms are treated as top level by the whole defun command family.
- Stray output from long-lived background processes (say, a `core.async` go-loop still printing under a finished eval's id) is now routed to the REPL instead of being dropped with a warning.  

One more thing. I shipped "smarter form targeting"
on master - the evaluation commands resolving the form from where the cursor
actually is, rather than the form before it - wrote about it, got a lot of
feedback, and reverted it a few days later. CIDER 2.1
will keep the classic Emacs semantics. Fifteen years in, the existing
behaviour is the contract, not an implementation detail I get to tidy up. The
detour wasn't wasted, though:
it surfaced a bug where the text of a line comment was treated as code, and
the "at point" family of commands is a direct result of it.  

### cider-nrepl  

Three releases in July, wrapping up the tools.deps migration and driving the
CIDER 2.0 launch:  

- [0.62.0](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.62.0) finalized the Leiningen to tools.deps migration, simplified deferred middleware loading, documented the op response keys (with a test verifying the descriptor contract) and shipped the hardened content-type and slurp middleware that made rich content safe to enable by default.
- [0.62.1](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.62.1) fixed a whole cluster of debugger bugs: record literals no longer get downgraded to plain maps by instrumentation, `deftype`/`defrecord` method bodies are skipped (goodbye `Unable to resolve symbol: STATE__`), and enlightening `deftest` bodies works again.
- [0.62.2](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.62.2) pruned trace and tap subscriptions with dead transports (a dead subscriber used to break every traced evaluation), stopped the debugger from shadowing enlighten's evaluator, brought the docs back in sync with the code and added a [section for tool authors](https://docs.cider.mx/cider-nrepl/tool-authors/index.html).  

### Orchard  

[Orchard 0.44.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.44.0) shipped on July 4, mostly thanks to Sashko's inspector
work (a `replace` command, truncated table columns, `ARef` contents rendered
fully). My part was a round of tests for the less covered namespaces and,
later on master, a fix for `orchard.print` ignoring custom `print-method`
implementations for records and collections. Thanks, Sashko!  

### clj-refactor 4.0  

[clj-refactor.el 4.0](https://github.com/clojure-emacs/clj-refactor.el/releases/tag/v4.0.0) is the release I had been promising for a few
cycles. It requires Emacs 28.1+ and CIDER 2.0+, and it's a big one:  

- project-wide refactorings (rename symbol, change signature, inline symbol) now show a diff preview before touching disk, and `cljr-undo-last-refactoring` reverts the last one in a single step
- the slow refactorings run asynchronously, so Emacs no longer freezes while the middleware analyzes the project
- `cljr-change-function-signature` can add and remove parameters and handles multi-arity functions
- a `clj-refactor-menu` transient replaces the hydra menus, and the `multiple-cursors`, `hydra` and `inflections` dependencies are gone
- many commands degrade gracefully without a REPL (`cljr-clean-ns`, `cljr-slash`, `cljr-add-missing-libspec`, `cljr-remove-let`, `cljr-promote-function`)
- `cljr-slash` can add and hotload a missing library, artifact lists are cached, and the namespaced refactor-nrepl ops are used when available  

I still think the long-term home for the most useful bits is CIDER and
clojure-mode, but at least the project is in good shape while that's being
figured out. I'd still love to hear from clj-refactor users on this.  

### clj-suitable  

[clj-suitable](https://github.com/clojure-emacs/clj-suitable), the ClojureScript completion backend, was another project that
had been coasting for years:

- [0.7.0](https://github.com/clojure-emacs/clj-suitable/releases/tag/0.7.0) adapted to Piggieback 0.7's delegating repl-env, modernized every dependency, replaced the Leiningen build with tools.build, moved CI to GitHub Actions and added a shadow-cljs integration test over a real Node runtime.
- [0.8.0](https://github.com/clojure-emacs/clj-suitable/releases/tag/0.8.0) brought the static completion much closer to compliment: fuzzy matching (`pr-fn` completes `print-function`), compliment-style ranking, completion of local bindings (destructuring included) and of referred vars inside `:refer` vectors. It also fixed the REPL's `*1`/`*2`/`*3` getting clobbered by completions and a few long-standing shadow-cljs and Node.js issues.

### Sayid  

The Sayid revival continued at a brisk pace, with five releases between July 1 and July 17:

- [0.4.0](https://github.com/clojure-emacs/sayid/blob/master/CHANGELOG.md#040---2026-07-01) dropped the `com.billpiel` namespace prefix, added data-returning variants of the workspace and query ops, and introduced a client-rendered, foldable tree view of the recorded call tree built on CIDER's `cider-tree-view`.
- [0.5.0](https://github.com/clojure-emacs/sayid/blob/master/CHANGELOG.md#050---2026-07-01) made recording bounded: a record limit, per-function limits, sampling, a max trace depth and bounded printing, so tracing a namespace under a test suite can't eat all your memory anymore.
- [0.6.0](https://github.com/clojure-emacs/sayid/blob/master/CHANGELOG.md#060---2026-07-08) rebuilt inner tracing on `tools.analyzer.jvm`, replacing the fragile source-rewriting instrumenter.
- [0.7.0](https://github.com/clojure-emacs/sayid/blob/master/CHANGELOG.md#070---2026-07-10) added `sayid.data` (the recorded call tree as plain data, with `tap>` integration for Portal and friends) and `sayid.golden`, a golden-trace testing helper.
- [0.8.0](https://github.com/clojure-emacs/sayid/blob/master/CHANGELOG.md#080---2026-07-17) focused on the experience: a `sayid-menu` transient, plain-language feedback from the trace commands, getting-started hints in empty views, and a fix for the inspector integration that had been broken for years.

### Drawbridge  

Drawbridge is nREPL's HTTP transport, created by Chas Emerick in 2012 and
"technically maintained" ever since. I finally gave it the attention it needed:

- [0.3.1](https://github.com/nrepl/drawbridge/releases/tag/v0.3.1) updated the dependencies (nREPL 1.7, Ring 1.15) and throttled client polling so it stops flooding servers with GET requests.
- [0.4.0](https://github.com/nrepl/drawbridge/releases/tag/v0.4.0) is the interesting one: `drawbridge.bridge`, a local nREPL socket server that relays to a remote Drawbridge endpoint, so any socket-based client (CIDER, Calva, rebel-readline) can now talk to Drawbridge; a WebSocket transport with server push instead of long-polling; bearer-token authentication via `secure-ring-handler`, which refuses to run unauthenticated unless you insist; and a `deps.edn`, so it's usable as a git dependency.

### nREPL  

No release this cycle, but master is shaping up nicely for 1.8:

- the built-in command-line client can connect using a URL, including the `nrepls://` and `nrepl+unix:` URLs that TLS and filesystem-socket servers advertise, and `http(s)://` when Drawbridge is on the classpath
- TLS hardening: descriptive errors for invalid key material, Ed25519 keys, tolerating a swapped certificate order, and a [documented security model](https://nrepl.org/nrepl/usage/tls.html)
- the built-in client sends input to the server as raw text, so reader typos, auto-resolved keywords and custom tagged literals no longer crash it
- stdin fixes: EOF arriving behind buffered input is reported properly, and a race between the stdin consumer and producer is gone
- `nrepl.spec` finally matches what `describe` and `ls-sessions` actually send
- a pile of documentation debt cleared (`lookup` return values, the `session-closed` status, the `-f`/`--repl-fn` option, [middleware best practices](https://nrepl.org/nrepl/building_middleware.html)) and a CI check keeping `ops.adoc` in sync with the descriptors
- Clojure 1.10 is the new minimum and `nrepl.misc/requiring-resolve` is gone in favor of the core one

The nrepl.org site also picked up links to several new clients and servers
(Nautilos, nREPL.hx for Helix, Janet and Steel Scheme servers).

### nREPL on the BEAM  

[nrepl-beam](https://github.com/nrepl/nrepl-beam) is a brand new project I started in July, mostly because I
wanted to see how well the nREPL spec holds up when implemented from scratch
outside the JVM. It's home to:

- `dialtone`, an nREPL server for Erlang (and a server core for the whole BEAM)
- `repartee`, the Elixir server built on top of it
- `chaser`, a terminal nREPL client that works with any nREPL server

[0.1.0](https://github.com/nrepl/nrepl-beam/releases/tag/v0.1.0) shipped on July 14. Both servers implement the full op set (eval
with streamed output, sessions, interrupts, stdin, load-file, completions,
lookup) and pass neat's cross-implementation integration suite alongside
Clojure, Babashka and Basilisp. Writing them was a good test of the spec, and
it produced a few of the documentation fixes listed above. Turns out the best
way to find holes in a spec is to implement it in a language you barely know.

### mezcaml  

In the same spirit, [mezcaml](https://github.com/nrepl/mezcaml) is a minimal nREPL client for OCaml: a small
client library plus a command-line REPL, working against any nREPL server
regardless of the language on the other end. No release yet, but the core
protocol works, it reads whole forms, and it has server-driven completion.
Nothing serious - it was a fun way to combine my recent OCaml hacking with
nREPL.

### clojure-mode, clojure-ts-mode and MrAnderson  

Smaller things: the `#_` toggle commands in clojure-mode were renamed to
`clojure-toggle-discard` and friends (matching Clojure's own terminology, old
names kept as aliases), both modes got a `:to-have-face` matcher for font-lock
tests, and clojure-ts-mode now checks the indentation of its sources on CI.

[MrAnderson 0.7.1](https://github.com/benedekfazekas/mranderson/releases/tag/v0.7.1) added a command-line interface, so it can be run
without Leiningen, and reworked its downstream integration tests against
cider-nrepl and refactor-nrepl, which had silently stopped exercising local
changes. Oops.

### Blog posts  

I wrote a lot this summer, mostly a series on the notable changes in CIDER 2.0:

- [Lowering the Drawbridge](https://metaredux.com/posts/2026/07/14/lowering-the-drawbridge.html)
- [CIDER 2.0: Sky is the Limit](https://metaredux.com/posts/2026/07/15/cider-2-0.html)
- [clj-refactor.el 4.0](https://metaredux.com/posts/2026/07/16/clj-refactor-4-0.html)
- [Simplifying Session Management in CIDER](https://metaredux.com/posts/2026/07/16/simplifying-session-management-in-cider.html)
- [Stepping Through Macros in CIDER](https://metaredux.com/posts/2026/07/17/stepping-through-macros-in-cider.html)
- [Sayid 0.8](https://metaredux.com/posts/2026/07/18/sayid-0-8.html)
- [clj-suitable 0.8.0: Closing the Gap with Compliment](https://metaredux.com/posts/2026/07/20/clj-suitable-0-8-0.html)
- [Making CIDER More Discoverable](https://metaredux.com/posts/2026/07/23/making-cider-more-discoverable.html)
- [Modernizing CIDER's Completion](https://metaredux.com/posts/2026/07/25/modernizing-cider-completion.html)
- [Closing the Find-Usages Gap in CIDER](https://metaredux.com/posts/2026/07/27/closing-the-find-usages-gap-in-cider.html)
- [Sharpening CIDER's Debugging Tools](https://metaredux.com/posts/2026/07/28/sharpening-ciders-debugging-tools.html)
- [Leveling Up CIDER's ClojureScript Support](https://metaredux.com/posts/2026/07/29/leveling-up-ciders-clojurescript-support.html)
- [Smarter Form Targeting Is Coming to CIDER](https://metaredux.com/posts/2026/08/26/smarter-form-targeting-is-coming-to-cider.html)
- [Smarter Form Targeting Is Not Coming to CIDER](https://metaredux.com/posts/2026/08/29/smarter-form-targeting-is-not-coming-to-cider.html)

### What's next  

CIDER 2.1 is the obvious next milestone, and it's mostly a matter of letting
the clojure-ts-mode integration settle. After that I'd like to cut nREPL 1.8
with the TLS and URL work, and get mezcaml and the BEAM servers to a point
where they are genuinely useful to someone other than me.  

Thanks to Clojurists Together for the continued support of my Clojure OSS work!
You rock!  <br>

---

## Clojure Camp  
2026 Annual Funding Report 4. Published Sept. 13, 2026.  

### What happened:  
- Logistics for Conj ‘bursary’ (bringing 5 new Clojurians to Conj)
- Prep for Conj workshops (code jams, pairing/mobbing)
- Prep for our booth at Conj (will be trying out an “unconference conversation zone”)
- Logistics for “micro-Conj” experiment (hosting an AirBnB during Conj, future: sans the Conj)
- Sponsored a research project re: how beginner questions re: Clojure have changed over time (just starting)
- Remote mobs, book club  

### Plans:  
- Finish prep for Conj-related projects (workshops, booth, airbnb) and attend Conj
- Continue on badges site content
- Continue on new event management tooling
- Remote mobs, book club  <br>

---


## Eric Dallo  
2026 Annual Funding Report 4. Published Sept. 13, 2026.  


Lots of work in July and August! ECA got closer to the editor with inline chats and LSP navigation (clojure-lsp integration :heart:), alongside more work on reliability, chat history and the client experience. On clojure-lsp, I continued addressing edge cases after the huge performance improvements from the last report, with a new refactoring and help from contributors making the project easier to debug. Thanks ClojuristsTogether and everyone helping with feedback, issues and contributions! :heart:  

### [ECA](https://github.com/editor-code-assistant/eca)  
The main highlight this time is being able to talk to ECA directly from the code, without switching to the chat window! This builds on the same server and protocol used by the existing clients, so inline conversations can reuse chat history, call tools and ask for approvals too. As usual [the changelog is huge](https://github.com/editor-code-assistant/eca/blob/0.157.3/CHANGELOG.md), so here are the highlights since the last report:  

![ECA inline chat in Emacs](./eca-inline-chat.gif)

#### 0.145.0 - 0.157.3  

- __Inline chat__: New `chat/inlinePrompt` protocol method, with support in Emacs, VS Code and IntelliJ. Ask a question from the current file or selection, follow up beside the code, and optionally fork an existing conversation to reuse its context without changing the original chat.
- __LSP navigation__: New `editor_definition` and `editor_references` tools let the agent use the editor's language intelligence instead of relying only on text searches. A nice connection between ECA and tools like clojure-lsp, with support for other languages and editors too!
- __`/btw` side questions__: Ask a quick question in a separate, forked conversation without cluttering the main chat history, even while the main task is still running.
- __Granular tool approvals__: Approve and remember individual shell/git commands and subcommands instead of granting access to the whole tool, with clearer details showing what will be remembered.
- __Chat history performance__: Chats now live in separate cache files with a lazy-loaded index, avoiding CPU spikes and slow startup as history grows. History is also shared across git worktrees of the same repository, and concurrent servers merge cache writes instead of overwriting each other.
- __More predictable prompt caching__: Existing chats keep their system prompt stable by default, with `/sync-system-prompt` to explicitly apply updated instructions. Changes that invalidate the prompt cache are now visible in the chat.
- __Better recovery and cancellation__: More robust handling of dropped connections, interrupted streams and rate limits, configurable retries, and prompt cancellation across more providers. Failed subagents now return useful errors and partial output instead of appearing to succeed with an empty result.
- __Plugins and agent control__: Plugins can declare dependencies that are loaded automatically, while `spawnableBy` controls which primary agents can spawn a subagent. Agents can also disable groups of MCP tools by server name or regex.
- __MCP and login improvements__: Timeouts prevent MCP servers from staying stuck during discovery, and tools become available without waiting for slow prompt/resource listings. Provider login got interactive choices and clearer feedback, including consent before enabling a GitHub Copilot model policy.
- __Models and local providers__: Added GPT-5.6 variants and Claude Opus 5 support, automatic discovery of Copilot model APIs and reasoning variants, context-limit detection for llama.cpp/llama-swap, and token usage reporting for Ollama.  

Also, there were lots of improvements in eca clients repos related to those changes.   

The experimental [eca-cli](https://github.com/editor-code-assistant/eca-cli) also received community contributions for fuzzy file selection, background-job management, MCP status and diff previews before approving edits. Still early, but really nice to see the terminal client moving forward!  

### [clojure-lsp](https://clojure-lsp.io/)  

Following the memory and startup work covered in the last report, these 2 months were about fixing edge cases, adding a new refactoring and improving the contributor experience, still unreleased:  

- New `cycle-namespaced-map` code action to switch between ordinary and namespaced maps, for example `{:foo/bar 1}` and `#:foo{:bar 1}`. #994
- Fix API/CLI renaming when a symbol appears multiple times on one line, preventing corrupted replacements. #2450
- Keep dependency completion working while typing a key in an incomplete map in `deps.edn` or `project.clj`. #2384
- Fix cache writes failing when clj-kondo ignore hints precede Java interop code. #2380
- Identify clj-kondo snapshots by their Git SHA in `--version`, and fix native-binary version reporting.
- Correct missing-classpath handling during stub generation.
- More detailed initialization timing logs and contributor documentation covering performance tests, code coverage and FlowStorm debugging.  

Thanks to blueskyonmars for helping with the debugging and contributor documentation improvements!  <br>

---

## Jeaye Wilkerson  
2026 Annual Funding Report 4. Published Sept. 11, 2026.  

Hello Clojurists Together members! Thank you so much for the sponsorship this
year. Here is my update for July and August, which is extracted from my recent
blogpost [here](https://jank-lang.org/blog/2026-09-04-better-and-better/).  

### Uncaught runtime exceptions  
In today's modern C++ compilers, there's no standard, portable way to get a
stack trace. Coming from the JVM, this may sound surprising, but it's par for
course in the native world. Even worse, jank is JIT compiling C++ code and we
want to get accurate stack traces which include those frames as well. Even
worse, we need to map some of that C++ back to actual jank code. So, in order to
get beautiful, accurate stack traces for jank's uncaught runtime exceptions,
there was a lot of work to be done. Check out the results!  


IMAGE HERE



As you can see in the figure above, an exception was thrown from the C++ code backing
clojure.core/subs. jank properly reports the error by pointing at the nearest
user's Clojure call, skipping over the one in clojure.core which calls the C++
function. In the stack trace, we can see two Clojure-specific frames, numbered
as #1 and #3. First, we see the frame for clojure.core/subs. Then we see the
frame for user/foo, which actually does the call to subs. Note that both of
these frames include the exact arity that was used, as well as the precise
source location in their respective jank files.  

What you're not seeing here is that this stack trace is pulling debug info from
three separate places:  

The current executable, for all of the non-Clojure frames.
An AOT-compiled object file, which was loaded when clojure.core was required. This is equivalent to Clojure JVM's .class files.
A JIT-compiled object file, which was added to the LLVM JIT runtime when the
user/foo function was compiled.  

After my recent efforts, jank now weaves all of these together seamlessly to
provide you a lovely error report. This works reliably on macOS and on Linux.  


### Error pages  
Building on the error output above, you may also notice the URL that's tucked
into the bottom of the code snippet. Since the original error reporting design
last year, I have intended for jank to have a dedicated error page for each
error. Each page should provide more information about the error, common causes,
and suggested fixes. All of these pages have now been created and are part of
the jank book. Some of them are more bare-bones than others and I plan to
continue filling them in over time. Getting them created sooner will start
aiding in SEO, though, which will help ensure that if you search for any jank
errors you hit, the right resources will be shown to you.  

Here's an example of what I have in mind:  
[analyze/invalid-cpp-conversion](https://book.jank-lang.org/reference/error/analyze/invalid-cpp-conversion.html)  



### C++ candidates  
I have saved the best for last, as far as error messages go. We know that
Clojure is infamous for its error messages and I hope to have shown how jank
addresses that. However, C++ is also infamous for its error messages and jank
is just as much C++ as it is Clojure. C++ is a much scarier beast when it comes
to all of the possible things that can go wrong, though. So how can we reimagine
C++ error messages? Well, I gave it my best shot. Take a look. :)  


IMAGE HERE


The call is ambiguous because the second argument is an int, which directly
matches neither long nor short but can implicitly be converted to either of
them. jank's AST is intertwined with Clang's AST, so we can extract all of the
necessary information to render this neatly. Unlike Clang, or GCC, jank renders
these in a table format which I find to be incredibly succinct and appealing.  

Also, as a bonus, the signature and source information for these bar functions
is correct, even when they're declared inside of a cpp/raw in a jank file.
Let's take a look at another one.  

IMAGE HERE


When there are many candidates to report, jank optimizes useful output by
ranking the candidates based on argument count, required conversions, as well as
access levels. By default, jank will only show the top three candidates.  

Finally, I'll show one more image, which is of a special kind of ambiguity with
some jank-specific behavior. On top of normal C++ overloading, implicit
conversions, etc, jank also supports automatic trait conversions, which use a
well-known trait to convert to/from jank objects and native values. If an
argument to a native function is a jank object, the compiler will consider
whether or not a trait conversion can be used. However, this can result in
ambiguities, too, if multiple candidates are viable. Here's an example.


IMAGE 



There's a lot more that jank can already do with these C++ candidate failures,
but I can only show so much in a blog post. I'm sure you'll see more next time
you're writing some jank code!  

### Why bother with all of this?  
You may not be as excited as I am about these images of error reports. That's
understandable. It's partly a compiler nerd thing, since effective error
reporting can be quite tricky. However, it's also partly a huge usability win
over not only Clojure JVM, not only Clang and GCC, but also the status quo in a
lot of developer tooling. I am trying to build a language, and tooling
ecosystem, that is the best it can be. That's the language I want to use. I want
to entice others to try it by dedicating this time to usability, too.  

To me, this is incredibly important.  


### Native build system  
Another large system I've been working on is jank's native build system. This is
a Cargo-like build system, for those familiar with Rust. The goal of the build
system is to enable easy consumption of native libraries, both from the
installed system and from compiled sources. The jank build system stands on top
of Clojure's existing package management, namely through Clojars. When you add a
dependency, the jank tooling will automatically pick up if that dependency has a
native jank build script and will build the package locally. These scripts are
always run in a sandbox which has no access to your personal files. This is an
improvement over the default Cargo machinery.  

This build system was originally created by Kyle Cesare and I've been further
improving it these past few months by adding sandboxing support to macOS,
improving static linking support, and overall making things more robust and
stable. Now that we have a powerful native build system, what we need is a
repository of high quality packages. That is precisely why I started the jank
commons.  


### jank commons  
The [jank commons](https://github.com/jank-lang/commons) is an official repository of native jank packages published to Clojars. Each of these packages
integrates seamlessly into the jank build system and has an example project
which is continuously compiled. Following Rust/Cargo's naming scheme, the jank
commons is currently full of foo-sys packages. The -sys suffix conveys that
it's a package which provides a system library without providing a higher level
API. Writing a higher level API is left up to other packages which then depend
on the -sys packages. The key benefit here is that the higher level packages
don't need to bother with all of the system details of packaging native libs and
can just focus on writing good APIs. Another benefit is that generally only
-sys packages will need native build scripts, so isolating those can further
help with security. Even better, since jank has seamless C++ interop, idiomatic
Clojure APIs are optional.  

An even easier way to browse the native packages jank has is through the
[awesome-jank](https://github.com/jank-lang/awesome-jank) list. This is mainly
populated by the jank commons right now, but please take this to be a call to
action to get more native libs packaged for jank! The jank commons README has a
guide for exactly how to do this and the whole native build system is thoroughly
documented in the [jank book](https://book.jank-lang.org/jank-build/overview.html).



### Everything else  
There's a lot more that's been going on in the jank repos, but it's too much to
cover in detail here. For example:  

- The [terminal REPL client has been rewritten](https://github.com/jank-lang/jank/pull/949) to support syntax highlighting,
tab completion, and multi-line inputs
- Syntax quote expansion has been [drastically optimized](https://github.com/jank-lang/jank/pull/966), cutting generated code
size from macros down substantially
- [Multiple](https://github.com/jank-lang/jank/pull/954) Clang/LLVM [bugs](https://github.com/llvm/llvm-project/pull/217781) have been fixed and upstreamed for LLVM 23, resulting
in [more graceful jank updates](https://github.com/jank-lang/jank/pull/954) and more robust exception handling on macOS
- Support for throwing (and re-throwing) native values [has been added](https://github.com/jank-lang/jank/pull/943)
-The nREPL server received some [usability improvements](https://github.com/jank-lang/jank/pull/951)


### What's next  
This is the last post before my talk at [Clojure Conj 2026](https://2026.clojure-conj.org/schedule).
If you can't make it, **check out the free live stream!** In the coming weeks, I will be
racing to improve jank's stability, portability, and usability, leading up to
the Conj. After the Conj, and for the remainder of the year, I'll be focused on
much of the same.  


It's my goal to get jank into your hands, dear reader. For many of you, I think
that jank is already capable enough for you to begin your tinkering. With the
addition of the jank commons, starting a new raylib game in jank is as easy as
lein run. Getting a distributable binary is as easy as lein compile. What
follows is just polishing up all of the rough edges so that developing your
games and applications is a breeze.  


If you've been waiting to try jank, give it a go! If there's something you need
which jank is missing, let me know! I'll make sure it's noted down and
prioritized.  


I'll see you all at the Conj.  <br>

---

## Michiel Borkent  
2026 Annual Funding Report 4. Published Sept. 11, 2026.  

In this post I'll give updates about open source I worked on during July and August 2026.

To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).

### Sponsors  

I'd like to thank all the sponsors and contributors who make this work
possible. Without you, the projects below would not be as mature or would not
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
you can sponsor this work in the following ways. If you work for a company that
uses my OSS, please ask your employer, that would be even better. Thank you!  

- [GitHub Sponsors](https://github.com/sponsors/borkdude)
- The [Babashka](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
- [Ko-fi](https://ko-fi.com/borkdude)
- [Patreon](https://www.patreon.com/borkdude)
- [Clojurists Together](https://www.clojuriststogether.org/)

</details>


### Updates  

In the past two months it was summertime in Europe. Due to a couple of
heatwaves, it was the perfect time to spend inside and enjoy my new air
conditioning, while coding ;-).  

The first half of July was mostly spent on improving performance and
compatibility of [SCI](https://github.com/babashka/sci) on CLJS. SCI now
JIT-compiles interpreted function bodies to JavaScript at runtime, which closes
a lot of the gap with compiled ClojureScript: a tight numeric loop went from
~175ms to ~7ms, over 20 times faster than the interpreter. Implementing core
protocols on custom types now also works. There's hardly anything you can't do
in SCI that you can do in compiled CLJS. I released new versions of [scittle](https://github.com/babashka/scittle/releases/tag/v0.8.32) and [nbb](https://github.com/babashka/nbb/releases/tag/v1.5.211) that take full advantage of this.  

Also in the middle of July, [clj-kondo](https://github.com/clj-kondo/clj-kondo) got a pretty cool enhancement. It infers types of function arguments from how they are used.
E.g. when you write `(defn foo [x] (inc x))` we can infer that `foo` is a function that takes a number. I took this principle as far as I could while preventing false positives.
Of course, clj-kondo supports the latest Clojure 1.13 destructuring changes too.  

In the second half of July I spent significant time on improving babashka tasks with automatic help and completions, backed by [babashka.cli](https://github.com/babashka/cli).
You can read all about that in this blog post: [Babashka tasks with automatic help and completions](https://blog.michielborkent.nl/babashka-tasks-cli.html)  

In August I had the pleasure of giving a talk about [Reagami](https://github.com/borkdude/reagami) at [Func Prog Sweden](https://www.meetup.com/func-prog-sweden/events/315394699/). In the talk I gave an interactive demo of how to use Reagami in a
Squint project through a REPL. I also went into detail on the algorithm that
powers the fast DOM diffing. While preparing for the talk, I added SSR to Reagami too.
You can view the talk on YouTube:  

<iframe width="560" height="315" src="https://www.youtube.com/embed/X0PowSdliXs" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

The last few weeks of August I created [`babashka.ffi`](https://github.com/babashka/ffi), a new namespace in babashka to call C libraries.
See my previous blog post: [Babashka 1.13.220 gets FFI](https://blog.michielborkent.nl/babashka-ffi.html).
To validate the design I wrote four libraries with it:
[babashka.sqlite](https://github.com/babashka/babashka.sqlite),
[babashka.duckdb](https://github.com/babashka/babashka.duckdb),
[babashka.postgres](https://github.com/babashka/babashka.postgres) and
[filewatcher](https://github.com/babashka/filewatcher).
Each one exercised a different corner of the API, along with some examples based on raylib. PacMan is particularly cool:  

<img src="assets/1.13.220-pacman.png" style="max-width:420px;width:100%" alt="pac-man running in babashka through babashka.ffi and raylib">

Right now I'm looking forward to giving a [babashka
workshop](https://2026.clojure-conj.org/workshops) at the Clojure/conj together
with Rahul Dé. We're still polishing the workshop material behind the scenes and I'm excited to see how it's turning out.
I'm sure it'll be a lot of fun and hope to catch many of you there.  

In between all of this, I also worked on [squint](https://github.com/squint-cljs/squint). It now supports the core protocols, so you can plug in your own collections and use them with core functions.
E.g. you can use [Immutable.js](https://squint-cljs.github.io/squint/?src=gzip%3AH4sIAAAAAAAAE42UQW%2FjNhCF7%2FoVD%2BmFBFZKk7YXB2haFHtYNLlsgF4MHxhpHHNNUrKGUu0W%2Fe%2FFkJIdxHHaG4d6Mxp%2BfBwVGJ0zh5e%2BHUJTAGrR026wPWF5tYmx48X1NbGveHNtvR%2BieXb0y0%2FVTfXjFRaGYf1K66K4u8OX%2BXP1aDoYhsHa9hzL2hlm8G6wIcKbrlC0jxSaMh46gvXXj6YrgC8PbbsdZKVKl5ZYemwR1iuo6oUicqS1iH9lbmtroh0pZRiJc8Ioep70Y5Krsm5DNDZwuaXDfdKJamNYVLlkbkOVjT3WEk1DjiKdZL%2B1Q4jUTFWHELH0oivZ%2FkXwWfT7H1%2BpGerc23Ys%2BxRJzTVssFESpj0PtQ5YmrrGmP64hqxT60k7%2F9Y5qqNtw3yeb1JuvyoAQNk11Eh1bPt77HXawxGDCnGDPb7X8%2BpGHyVTF8ceaKYnQZLTMZEkUUtBph32WufWPvsuHuTm3%2FRIsj%2FRqR2ZfqbzeTfYMUtkJedo44Z6EdJuME5uJe3oM3M9WI4X3ZUJnBlMci44zMG%2BcliOPnSYSI4OS8H7DnOws8Mc7EXruJN13AcX7bAXYTfwBk7I%2Fw%2Fu7sTdXeDuzri7S9yfKC7gyT9Tzxvb4fmA0biBzmA%2FUXwP3xs8nA8keHg%2Bzzt4%2BISHP8AzVTNNc6r2H3T4RIcv0OEzOvyKjmpoHVD%2BbL0vgKuvVA8925HcAXUbRurjG1vyJzBFhgmNjEFGbHGcqdU3LgAx6ic8yesLDR5NV%2BGBzEgMGqk%2FxI0NLyDHBOPaQNVVASzTCFB1m%2BY3Xo8BqMn8FZQ3XW524gN5xPGoeqILIm%2B6LMqDotyOp1mRZ21%2BGGlc5NytnlejjAuVR3yl58G0SCfYzxDhD6U8pinnu7%2BXN7hdYXmDH1b%2FaF2o2Tr3b5RJpzXu7hB7ceJULNCfWZbM8W6OLpYqWyx%2F1jiFU7peSeFb3P4L62gMqiQHAAA%3D) with Squint. I'm thinking about lightweight immutable persistent data structures for squint, but so far I haven't had much need for them, outside of Advent of Code puzzles.  

The above was all about making existing projects better. But I also had a few new creative ideas:  

- [Choq](https://github.com/squint-cljs/choq): [Cherry](https://github.com/squint-cljs/cherry) hosted on QuickJS, nREPL included.
- [Buzz](https://github.com/borkdude/buzz): a cross client-server framework that lets you write web-apps on the JVM or babashka without any JS tooling, while still having full JS expressivity via Squint. I wrote [tube-pod](https://github.com/borkdude/tube-pod) and [multi-snake](https://github.com/borkdude/multi-snake) with it.
- [Cljbang.el](https://github.com/borkdude/cljbang.el): A Clojure-like language that runs as Emacs Lisp

Here are some highlights per project. See each project's `CHANGELOG.md` for the full list.  

- [Babashka](https://github.com/babashka/babashka): native, fast-starting Clojure interpreter for scripting.
  - 1.13.220: Add experimental [`babashka.ffi`](https://github.com/babashka/ffi): call C functions in shared libraries straight from babashka and JVM Clojure! See the [guide](https://github.com/babashka/ffi/blob/main/doc/guide.md)
  - 1.13.220: On Linux, the install script installs the dynamic binary by default. It installs the static binary on musl systems and on systems with glibc older than 2.17. The `--static` and `--dynamic` options override the automatic selection
  - 1.13.220: `:exec-args` can sit directly on a task, not only under `:cli`, the way `(exec ...)` already reads it. Before, it was ignored on an `:exec-fn` or `:cmd` task
  - 1.13.220: A task's `:cli` spec adds to the runner-level `:tasks {:cli {:spec ...}}` instead of replacing it. An option from the runner level keeps its coercion and default, and `--help` lists it under `Inherited options`
  - 1.13.220: A task with `:exec-fn` runs when another task `:depends` on it. Before, it did nothing
  - 1.13.220: Options declared by an `:exec-fn` task named in `:depends` also parse for the CLI task that runs, with their coercion and default. `--help` lists them under `Inherited options`
  - 1.13.220: `:cmd` can be a symbol naming a var that holds the command tree, like `:cli`. Its namespace loads on demand
  - 1.13.220: Shell completion offers inherited options (via `:depends`) too
  - 1.13.220: SCI: call site caching for instance and static methods, constructors and fields. Interop calls are up to 5x faster
  - 1.13.219: Tasks get automatic `--help` and shell completions, through the new `:exec-fn` and `:cmd` keys. See [the blog post](https://blog.michielborkent.nl/babashka-tasks-cli.html)! These task keys should be considered experimental and may change in a future version of babashka, depending on feedback from the community
  - 1.13.219: Clojure 1.13 map destructuring: `:keys!`, `:syms!`, `:strs!`, `&` inside a directive, `:select`, `:all` and `:defaults`. Adds `req!` and `some-vals` to `clojure.core`
  - [#1321](https://github.com/babashka/babashka/issues/1321): support implementing the `clojure.core/Inst` protocol on records, types and reify, and with `extend-protocol` and `extend-type`
  - [#2054](https://github.com/babashka/babashka/issues/2054): a `proxy` of `java.io.Writer` supports the one-argument `write` and `append`, so binding `*out*` to it works
  - [#1918](https://github.com/babashka/babashka/issues/1918): fall back to `$HOME` when the OS does not supply a home directory, e.g. for LDAP users in the static binary
  - [#1994](https://github.com/babashka/babashka/issues/1994): fix `:eval` and `:print` options of `clojure.main/repl` being ignored in the interactive REPL ([@jeroenvandijk](https://github.com/jeroenvandijk))
  - Bump jline to 4.4.0: security hardening, a rewritten signal path for the FFM terminal, Kitty keyboard protocol
  - [#2021](https://github.com/babashka/babashka/issues/2021): bump http-kit to 2.9.0-beta4, which fixes four security advisories
  - Class additions by [@weavejester](https://github.com/weavejester) ([#1985](https://github.com/babashka/babashka/issues/1985), [#1986](https://github.com/babashka/babashka/issues/1986), [#1987](https://github.com/babashka/babashka/issues/1987), [#1988](https://github.com/babashka/babashka/issues/1988)), [@paintparty](https://github.com/paintparty) ([#1982](https://github.com/babashka/babashka/issues/1982)) and [@christoph-frick](https://github.com/christoph-frick) ([#2003](https://github.com/babashka/babashka/issues/2003))
  - [Full changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md)

- [babashka.ffi](https://github.com/babashka/ffi): call C functions in shared libraries from Clojure. New library, also usable from JVM Clojure. See the [guide](https://github.com/babashka/ffi/blob/main/doc/guide.md) and the [examples](https://github.com/babashka/ffi/tree/main/examples). The API is experimental

- [babashka.sqlite](https://github.com/babashka/babashka.sqlite): SQLite for babashka through `babashka.ffi`
  - Uses the SQLite shared library that macOS, Linux and Windows already ship with, so there is nothing to install
  - `with-conn`, queries, aggregates, transactions, `last-insert-rowid`, interrupt, and `create-function!` for defining a Clojure function callable from SQL
  - CI green on three operating systems

- [babashka.duckdb](https://github.com/babashka/babashka.duckdb): DuckDB for babashka through `babashka.ffi`
  - Query CSV files directly with SQL, results as Clojure data
  - HoneySQL support, thread safety, prepared statement cleanup

- [babashka.postgres](https://github.com/babashka/babashka.postgres): PostgreSQL for babashka through `babashka.ffi` and libpq
  - `connect`, `close!`, `with-conn`, `query`, `execute!`, `with-transaction`, `in-transaction?`, `cancel!`, `json`, `jsonb`, `version`, `server-version`
  - Vectors map to arrays in both directions, maps map to JSON. Bring your own JSON library through `:read-json` and `:write-json`
  - clj-kondo export with a `with-conn` hook, CI on three operating systems

- [filewatcher](https://github.com/babashka/filewatcher): watch files and directories from babashka
  - Built on `babashka.ffi`: FSEvents on macOS, inotify on Linux, `ReadDirectoryChangesW` on Windows, and polling everywhere
  - The same event types on all three platforms, modeled after [chokidar](https://github.com/paulmillr/chokidar)
  - A watcher keeps the process alive until `close`

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - ClojureScript JIT compilation. SCI on CLJS compiles interpreted function bodies to JavaScript at runtime via `js/Function`. This is enabled by default and needs no configuration. When JIT is enabled, loops and numerical computations become much faster (and, in unrestricted contexts, JS interop too)
  - When `eval` is unavailable (e.g. under a Content Security Policy) SCI falls back to the interpreter. Results, error messages and error locations should be identical. And of course, it works under `:advanced` compilation
  - You can turn JIT off at runtime with `js/globalThis.SCI_DISABLE_JIT = true` before loading SCI, or in your Google Closure compile settings with `:closure-defines {sci.core/disable-jit true}`
  - More CLJS JIT performance improvements. Up to 20x on arithmetic-dense code for >2 arity. Keyword lookups, `instance?` and js globals no longer fall back to the interpreter
  - ClojureScript: native protocol support ([#639](https://github.com/babashka/sci/issues/639)). SCI code can implement CLJS protocols on `deftype`, `defrecord` and `reify`, and host code calling protocol methods on such instances dispatches into the sci implementations. Works under `:advanced` compilation
  - [#1063](https://github.com/babashka/sci/pull/1063): CLJS: `deftype` and `defrecord` fields are JS accessors on the type's prototype: `(.-field x)` works on instances, `(set! (.-field x) v)` mutates deftype fields
  - New `:unrestricted` option on `init` and `eval-string`: when `true`, evaluated code may mutate built-in vars and CLJS instance interop skips `:classes` checks. The option applies only to the context it was passed to
  - BREAKING: `enable-unrestricted-access!` now throws. Use the `:unrestricted` option instead. The old function set a process-global flag that leaked into nested contexts
  - Support async functions by adding `:async true` in the attr map of `defn`
  - Caches resolved JVM instance methods per call site for performance
  - Fix [babashka#2030](https://github.com/babashka/babashka/issues/2030): `aset` on a primitive array was reflective and 170x slower than `aset-double`
  - Errors thrown inside a `loop` now report a located stack frame for the `loop` form instead of a frame without location (all platforms, including babashka)
  - [Full changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md)

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
  - Type checker: infer the type of a function param from how it is used in the body. E.g. `(defn f [s] (subs s 1)) (f 42)` will warn, since the evidence `(subs s 1)` tells us that `s` should be a string.
  - Type checker: infer the value type of a destructured map key from how it is used in the body. E.g. `(defn f [{:keys [x]}] (inc x)) (f {:x "foo"})` will warn. A key whose use rejects nil and that has no `:or` default is required.
  - Type checker: a destructured binding gets the value type of its key when the map's type is known, including through function return maps. E.g. `(defn cfg [] {:port "8080"}) (let [{:keys [port]} (cfg)] (inc port))` will warn.
  - Type checker: a key missing from a map literal is provably nil, also through destructuring, keyword access chains and function return maps. E.g. `(inc (:y {}))` will warn.
  - Type checker: narrow the type of a local in the then-branch of `if` or the body of `when` when it is guarded by a known predicate. E.g. `(if (string? x) (inc x) ...)` will warn.
  - Built-in analysis now uses Clojure 1.13.0-alpha4. Param type inference over the core sources grows the arg type coverage of `clojure.core` from 23 to 150 vars. E.g. `(interleave 1 [2])` and `(mod "a" 2)` will warn.
  - [#721](https://github.com/clj-kondo/clj-kondo/issues/721): NEW linter: `:constant-condition`: warn on a condition whose truthiness is the same on every run. On by default. Replaces `:condition-always-true`, whose config and ignores still apply to always-true conditions, and takes over the `cond` catch-all warning from `:unreachable-code`
  - Clojure 1.13 CLJ-2961: infer required keys from `:keys!`, `:syms!` and `:strs!` and report them at call sites
  - [#2874](https://github.com/clj-kondo/clj-kondo/issues/2874): Clojure 1.13 CLJ-2964: support `:select` in map destructuring. The bound map's keys are known to the type checker
  - Clojure 1.13 CLJ-2966: support `:defaults` in map destructuring, error when used without `:or`
  - [#2943](https://github.com/clj-kondo/clj-kondo/issues/2943): Type checker: when an `:analyze-call` hook rewrites a call, clj-kondo checks the arity of the original function but not its parameter types.
  - [#2900](https://github.com/clj-kondo/clj-kondo/issues/2900): `:discouraged-var`: new per-var `:positions` option (a set or vector of `:call` and/or `:value`) to limit the warning to call position or value position. A var passed to a higher-order function such as `map` counts as `:value`.
  - [#2851](https://github.com/clj-kondo/clj-kondo/issues/2851): NEW linter: `:seq-rest`: suggest using `(next x)` over `(seq (rest x))`. Defaults to `:off` ([@tomdl89](https://github.com/tomdl89))
  - [#1882](https://github.com/clj-kondo/clj-kondo/issues/1882): built-in support for `clojure.test.check.clojure-test/defspec`
  - [#2877](https://github.com/clj-kondo/clj-kondo/issues/2877): warn when `#_` before an unmatched reader conditional discards the next form. E.g. `[#_#?(:cljs 1) 2]` reads as `[]` in `:clj` and will warn.
  - Vars defined in `comment` forms no longer count for `:shadowed-var`, `:unused-private-var` and `:inline-def`.
  - Performance: use a record for var usages: 13.5% less allocation, ~5-10% faster linting. More performance work by [@alexander-yakushev](https://github.com/alexander-yakushev)
  - The minimum Clojure version to run clj-kondo on the JVM is now `1.11`.
  - [Full changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)

- [babashka CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
  - [#197](https://github.com/babashka/cli/pull/197): [`:positional`](https://github.com/babashka/cli#positional) spec marker: positional args get their own `Arguments:` help section and may not be passed as options
  - [#197](https://github.com/babashka/cli/pull/197): [`:restrict-args`](https://github.com/babashka/cli#restrict-args): error on positional args not consumed by `:args->opts`
  - [#219](https://github.com/babashka/cli/issues/219): `:cmd-aliases` on a table entry or tree node gives a command one or more alternative names.
  - A short option that declares a non-boolean `:coerce` takes the rest of its token as its value, like getopt: `-J-Dfoo=bar` binds `"-Dfoo=bar"`, `-p80` binds `80`. Flag letters may precede the valued option in a cluster: with `:b` a flag and `:a` valued, `-ba x` parses as `-b -a x`
  - [#216](https://github.com/babashka/cli/issues/216): in a cluster of flags, where no letter takes a value, an interior hyphen is an error instead of silently ending option parsing.
  - Help: show the dispatch-level `:spec` options under `Inherited options:`. The parser always accepted these options, but help did not show them
  - Help: `format-command-help` accepts `:spec`, the dispatch-level spec, so a standalone call shows the same options as `dispatch`
  - `dispatch`: the command named on the command line wins over the `:exec-args` of its ancestors. A value the user typed at an ancestor level still wins over both
  - Add ordered `:enum` values for validation, help and completion
  - Support `:doc` and `:epilog` as a vector of lines, joined with newlines
  - [#198](https://github.com/babashka/cli/pull/198): `:cmd` may be a [vector of `[name command]` pairs](https://github.com/babashka/cli#command-formats), preserving command order without `:cmd-order`
  - [#199](https://github.com/babashka/cli/pull/199): fix hang on variadic arguments that weren't "collected" (e.g. `(repeat :k)`)
  - [#203](https://github.com/babashka/cli/pull/203): `parse-opts*` resolves `:spec` so its `:coerce`/`:collect` entries steer parsing like in `parse-opts`
  - Completion: the fish snippet registers with `--keep-order`, so fish offers options in the order they are emitted, long option before its short alias, rather than sorting short options first
  - zsh completion: offer a command's options without typing a dash first, by opting the registered program names out of zsh's `prefix-needed` style
  - Thanks to [@lread](https://github.com/lread) for continued documentation review and maintenance
  - [Full changelog](https://github.com/babashka/cli/blob/master/CHANGELOG.md)

- [Squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  - Preparatory release before adding immutable + persistent collections in `squint.immutable`. Added a lot of protocols and made sure core functions work properly with them
  - Add the `ILookup`, `IAssociative`, `IMap`, `ICounted`, `IKVReduce`, `ICollection`, `IEmptyableCollection` and `IEquiv` protocols. `get`, `assoc`, `contains?`, `find`, `dissoc`, `count`, `reduce-kv`, `conj`, `empty` and `=` dispatch to them on custom types. Plain objects and arrays keep their fast paths
  - Add the `IStack`, `IIndexed`, `IVector`, `IWriter` and `IPrintWithWriter` protocols, `write-all`, and an `ITransientVector` `-pop!` slot; `nth`, `peek`, `pop`, `pop!`, `subvec`, `vec`, `vector?`, `sequential?`, `set?`, `map?`, `seq`, `=` and printing dispatch to custom collection types
  - Add `equiv`, `hash`, `hash-ordered-coll`, `hash-unordered-coll` and the `IHash` protocol. `hash` follows `equiv`: plain mutable objects and arrays hash by reference
  - Add the `IMeta` and `IWithMeta` protocols; `meta` and `with-meta` dispatch through them and the internal meta symbol property is gone
  - `clojure.set` dispatches through the collection protocols: results keep the input's type, membership tests against a protocol set are value-based, and `rename-keys`/`map-invert` no longer mutate a record
  - Add `defrecord`, `record?` and the `IRecord` marker protocol. Records store their fields as own string-keyed properties and implement the map-facing protocols, so keyword lookup, `keys`, `seq`, `assoc`, `conj` and `=` work through the regular core functions. `assoc` keeps the record type, `dissoc` of a basis field gives a plain map, printing gives `#TypeName{:a 1}`
  - Clojure 1.13 destructuring: `:keys!`/`:syms!`/`:strs!` for required keys, `&` inside them for keys required but not bound, `:select`, `:all`, `:defaults`, and `:or` by key
  - Fix [#975](https://github.com/squint-cljs/squint/issues/975): `& {:keys [...]}` now destructures a map instead of the raw rest args, and a seq destructured as a map is read as kwargs
  - Fix [#977](https://github.com/squint-cljs/squint/issues/977): `recur` inside `try` no longer emits an illegal `continue`
  - Support `:as-alias` in `ns` `:require` like CLJS: no runtime import, only a compile-time alias so a namespaced keyword such as `::alias/x` resolves
  - Add `:require-global` and `:refer-global` to `ns`, binding globals loaded via a script tag to consts without emitting an import
  - Add `:squint/compile-time` opt-in mechanism for macro/compile-time namespaces. See [doc/compile-time.md](https://github.com/squint-cljs/squint/blob/main/doc/compile-time.md)
  - A `defmacro` is compile-time only: no longer emitted to the runtime module, and `:refer`ing a macro no longer emits a runtime import for it, matching CLJS
  - The CLI reports the file, line and column of a compile error and exits non-zero, instead of dumping the raw exception
  - Fix [#957](https://github.com/squint-cljs/squint/issues/957): vite HMR: support `^:dev/after-load` + `^:dev/before-load` hooks similar to shadow-cljs
  - `.indexOf` on a lazy seq now uses reference equality like a JS array, not value equality. This diverges from CLJS but keeps `=` out of any bundle that only builds lazy seqs, shrinking a `conj` bundle from 3801 to 2215 bytes
  - Use `Symbol.for` for protocol method dispatch, so pulling in multiple copies of squint.core (e.g. via http://esm.sh/) does not break protocol dispatch
  - [Full changelog](https://github.com/squint-cljs/squint/blob/main/CHANGELOG.md)

- [Cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - Add `cherry.test` with `clojure.test`-compatible testing API, requirable as `cljs.test` or `clojure.test`
  - `cherry.test/report` is a multimethod dispatching on `[*current-reporter* type]` like cljs.test, so reporting can be extended with `defmethod`
  - Add a vite plugin with browser REPL over nREPL and `^:dev/after-load` / `^:dev/before-load` hot-reload hooks, sharing squint's implementation: `import cherry from 'cherry-cljs/vite.js'`
  - Add `reify`, `defmulti`/`defmethod` and the `vswap!` macro. `#'foo` emits foo's value, like squint
  - Dynamic vars compile to squint's box scheme, so `set!` and `binding` work across ESM modules. cljs.core dynamic vars are exported as accessor boxes proxying the real var
  - `defprotocol` `:extend-via-metadata` impls resolve under the fully qualified method symbol, so replicant's mutation-log renderer works: replicant's own test suite passes under cherry
  - Fix `deftype` implementing cljs.core protocols such as `Inst`, `IIterable` and `IAtom`: their marker properties were Closure-renamed in the precompiled core and missing from the emitter's core protocol set. The externs list and the set are now generated from cljs.core's protocols (`bb gen-externs`) and the build fails on drift
  - Fix [#190](https://github.com/squint-cljs/cherry/issues/190): share `PROTOCOL_SENTINEL` with coexisting CLJS runtimes in the same JS realm
  - Share the macro scan and macro lookup with squint. Namespaces flagged `{:squint/compile-time true}` load only their compile-time part into the macro environment, like squint
  - CLI: `--help`/`-h`, argument validation and error messages via babashka.cli's `dispatch`, like squint. Adds `watch` and `nrepl-server` commands, shell tab completion, and reads options from `cherry.edn` instead of `squint.edn`
  - Fix emitted import specifiers on Windows: backslashes are normalized via the path resolution now shared with squint
  - [Full changelog](https://github.com/squint-cljs/cherry/blob/main/CHANGELOG.md)

- [Choq](https://github.com/squint-cljs/choq): a ~5 MB binary running the cherry compiler on embedded QuickJS
  - New project. Runs [cherry](https://github.com/squint-cljs/cherry) inside [quickjs-ng](https://github.com/quickjs-ng/quickjs) via [rquickjs](https://github.com/DelSkayn/rquickjs)
  - No JIT, so hot code is slower than Node.js, Bun or Deno, but the binary is small, startup is fast and memory use stays low. A Hono app serves around 30k requests per second locally, using less memory than the same app on Node.js or Bun
  - An install script for macOS, Linux and Windows, and dev release binaries
  - Clojure git and Maven deps, a module table covering `url` and `util`, `@babashka/fs`, and a test runner
  - Experimental

- [Buzz](https://github.com/borkdude/buzz): write a web application with the JVM or babashka only
  - New project. Server state is watched and updated from client code. The UI compiles through [squint](https://github.com/squint-cljs/squint) and renders with [Reagami](https://github.com/borkdude/reagami), so no ClojureScript toolchain and no Node.js
  - Rendering is asynchronous by default and coalesces at 20ms, and a failing render is contained to its own connection
  - Examples: a whiteboard, a tap viewer, and a Datalevin browser with a CodeMirror query editor
  - Highly experimental, the API will change

- [tube-pod](https://github.com/borkdude/tube-pod): turn YouTube videos into a private podcast
  - New project, written with Buzz. Add a link in the browser, tube-pod downloads the audio with `yt-dlp`, writes an RSS feed and serves both
  - Rsyncs the audio and the feed to a remote after each change, since a laptop is asleep when you want to listen

- [multi-snake](https://github.com/borkdude/multi-snake): snake for as many players as show up
  - New project, written with Buzz. Everyone plays on one board, in one world, held in one atom on the server
  - Runs at [multi-snake.michielborkent.nl](https://multi-snake.michielborkent.nl)

- [Reagami](https://github.com/borkdude/reagami): A minimal zero-deps Reagent-like for Squint and CLJS
  - Add `reagami.ssr` to render hiccup to an HTML string on the JVM, Babashka, Squint and CLJS. See [Server-side rendering](https://github.com/borkdude/reagami#server-side-rendering)
  - `reagami.core/render` (the regular render function) now hydrates a server-rendered page. It adopts the existing DOM instead of clearing the root
  - Add [create-reagami-app](https://github.com/borkdude/reagami/tree/main/create-reagami-app). Run `npm create reagami-app my-app` to create a Vite project with hot reload and a browser nREPL
  - **Breaking**: `:on-render` now takes a map: `(fn [{:keys [node lifecycle state save]}])`. Call `save` with a value to keep it for the next call, and read it back as `state`. In previous versions, the hook took three arguments and its return value became the state
  - Move reordered nodes with `moveBefore` where the browser has it, so a moved subtree keeps its iframe state, animations, focus and selection ([#54](https://github.com/borkdude/reagami/issues/54))
  - Set `value`, `checked`, `selected` and `disabled` on a tag with a hyphen as attributes, not as JS properties. A custom element observes attributes, so a property never had any effect. Native elements still handle them as properties
  - Custom events, e.g. `:on-rated`, now reach the element through `addEventListener`, because a browser only wires an `on*` property for standard events
  - Add [web component example](https://github.com/borkdude/reagami/tree/main/examples/web-component). A `<todo-list>` custom element, used from Squint, from JavaScript with and without Reagami
  - Fix memory leak with `:on-render` nodes and other `:on-render` improvements
  - I gave a talk about Reagami at [Func Prog Sweden](https://www.youtube.com/watch?v=X0PowSdliXs)

- [cljbang](https://github.com/borkdude/cljbang.el): a Clojure-like language that runs as Emacs Lisp
  - Compiles Clojure forms to Emacs Lisp forms and evaluates them in the running Emacs. No subprocess and no transpiled text, following the same approach as squint
  - Namespaces with per-namespace aliases, multiple arities in `fn` and `defn`, `loop`/`recur` with a tail position check, `try`/`throw`/`ex-info`, `case`, atoms, syntax quote including nesting, `&form` and `&env` in macros, regex and set literals, `#_`, `edn/read-string`, `slurp` and `spit`
  - `el!` for calling Emacs Lisp names that are not valid Clojure symbols

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
  - ClojureScript JIT compilation. Nbb now bundles a SCI that compiles interpreted function bodies to JavaScript at runtime via `js/Function`. This is enabled by default. This makes loops, numerical computations and JS interop much faster
  - Nbb now ships [babashka.fs](https://github.com/babashka/fs) as a built-in library. The full file system API (`glob`, `copy`, `move`, `create-dirs`, `delete-tree`, `with-temp-dir`, path helpers and more) is available via `(require '[babashka.fs :as fs])`, matching Babashka
  - Support implementing CLJS protocols (e.g. `ILookup`, etc) on `deftype` and `defrecord`
  - Support [editscript](https://github.com/juji-io/editscript): CLJS `deftype`/`defrecord` field interop, `set!` on `^:unsynchronized-mutable` fields, add `cljs.core` type classes like `PersistentHashMap`, `write-all` and `goog.math.Long`
  - [#416](https://github.com/babashka/nbb/issues/416): Fix problem with `prn` in nREPL
  - SCI now covers most CLJS capabilities, so nbb should run existing CLJS libraries unless they rely on very specific macros that require the JVM. If you have anything that does not run, please report it in [#nbb](https://app.slack.com/client/T03RZGPFR/C029PTWD3HR)!

- [Scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
  - ClojureScript JIT compilation. Scittle now bundles a SCI that compiles interpreted function bodies to JavaScript at runtime via `js/Function`. This is enabled by default
  - Include [helitorus demo](https://babashka.org/scittle/helitorus.html) to show improved JIT
  - Bump `reagent` to 1.2.0, `re-frame` to 1.4.7, `replicant` to 2026.06.2 and `shadow-cljs` to 3.4.11

- [squint-inline](https://github.com/squint-cljs/squint-inline): write squint functions and inline expressions in a ClojureScript project
  - New project. Squint operates on JavaScript objects and arrays, so `assoc`, `update-in` and `select-keys` work on those without `js->clj` and `clj->js`
  - Squint core is tree-shaken through `:js-provider :import`, and each function's tree-shaken size is recorded
  - Squint functions can call each other across namespaces, and JS module references work inside squint bodies

- [Edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
  - Speed up parsing by holding parse context in record fields instead of the extmap: ~10% faster on JVM, ~4% on ClojureScript
  - Respect `:refer` + rename in `:auto-resolve-ns`
  - With `:auto-resolve-ns`, qualify syntax-quoted imported classes (e.g. `` `Date `` with `(:import [java.util Date])`) with the full classname
  - With `:auto-resolve-ns`, leave method, constructor and dotted syntax-quoted symbols (`` `.toString ``, `` `Bar. ``, `` `foo.bar ``) as-is, matching Clojure
  - Do not resolve function literal params in a syntax quote
  - ClojureDart: fix parsing zero literals and make plain readers non-indexing, matching tools.reader ([#144](https://github.com/borkdude/edamame/pull/144))

- [fs](https://github.com/babashka/fs): file system utility library for Clojure
  - Released 0.5.34, which ships the Node.js support mentioned in the previous update as the `@babashka/fs` npm package

- [http-client](https://github.com/babashka/http-client): HTTP client for Clojure and babashka
  - [#80](https://github.com/babashka/http-client/issues/80): accept a function of the request URI in `:proxy` to select a proxy per request ([@jeeger](https://github.com/jeeger))

- [http-server](https://github.com/babashka/http-server): serve static assets
  - Range requests: inclusive `Content-Range` last-pos per RFC 9110, suffix ranges (`bytes=-N`, previously a 500), clamping last-pos beyond EOF, reading the full range, and a test suite ([@slagyr](https://github.com/slagyr))

- [Cream](https://github.com/borkdude/cream): Clojure + GraalVM [Crema](https://github.com/oracle/graal/issues/11327) native binary
  - Reduced the core.async virtual thread memory corruption I reported [upstream](https://github.com/oracle/graal/issues/13925) to a pure Java repro. GraalVM 25.0.3-ea.04 fixes it, and the pipeline test is back on now that the compile NPE is gone too
  - Enable the Ristretto JIT for runtime-loaded bytecode, and update the benchmarks for it
  - Clojure code runs without a JDK present, with the boot class loader warning suppressed
  - Pick up `pom.xml` when there is no `deps.edn`, and recompile Java sources when a dependency changed

- [graaljs-cherry](https://github.com/borkdude/graaljs-cherry): a native-image cherry REPL on GraalJS
  - New prototype. Compiles cherry expressions on the JVM and evaluates the resulting JS in an embedded GraalJS context
  - Two variants: a default Truffle JIT build, and a 49MB `--small` build without it

- [clj-kondo-browser](https://github.com/borkdude/clj-kondo-browser): a static Clojure source browser built from clj-kondo analysis
  - New prototype. Renders a codebase as a static HTML page where every symbol links to its definition and usages, scope-aware, so a local is linked only within its scope
  - Runs clj-kondo as a pod and gets the classpath from [deps.clj](https://github.com/borkdude/deps.clj)

- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
  - Babashka compatibility ([#34](https://github.com/borkdude/grasp/pull/34))

- [deps.clj](https://github.com/borkdude/deps.clj): a faithful port of the Clojure CLI Bash script to Clojure
  - As always, catching up with the most recent Clojure CLI versions

- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo) and [clj-kondo-bb](https://github.com/clj-kondo/clj-kondo-bb): released alongside each clj-kondo release

<!-- Contributions to third party projects: nothing big enough to list this
     cycle. Fill this in next time. What was there and got cut: clerk (SCI
     and cherry bumps), replicant (a test:cherry task), joyride (SCI bump),
     nextjournal/markdown (#69), crustimoney (babashka support). -->

### Other projects  

These are some other projects I'm involved with, but little to no activity
happened in the past two months.

<details>
<summary>Click for more details</summary>

- [quickblog](https://github.com/borkdude/quickblog): lightweight static blog engine for Clojure and babashka
- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
- [pod-babashka-gozxing](https://github.com/babashka/pod-babashka-gozxing): a babashka pod for QR code and barcode decoding/encoding
- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready-to-use SCI configs.
- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of rewrite-clj
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
- [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one command
- [graal-build-time](https://github.com/clj-easy/graal-build-time): initialize Clojure classes at build time for GraalVM native-image
- [html](https://github.com/borkdude/html): HTML generation library inspired by squint's HTML tag
- [qualify-methods](https://github.com/borkdude/qualify-methods): experimental tool to rewrite instance calls to use fully qualified methods (Clojure 1.12 only)
- [tools](https://github.com/borkdude/tools): a set of [bbin](https://github.com/babashka/bbin/) installable scripts
- [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter
- [speculative](https://github.com/borkdude/speculative)
- [squint-macros](https://github.com/squint-cljs/squint-macros): a couple of
  macros that stand-in for
  [applied-science/js-interop](https://github.com/applied-science/js-interop)
  and [promesa](https://github.com/funcool/promesa) to make CLJS projects
  compatible with squint and/or cherry.
- [http-kit](https://github.com/http-kit/http-kit): Simple, high-performance event-driven HTTP client+server for Clojure.
- [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
- [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
- [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
- [babashka.book](https://github.com/babashka/book): Babashka manual
- [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic API for Clojure).
- [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to GitHub releases idempotently
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
- [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
- [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
- [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
- [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning subprocesses
- [parmezan](https://github.com/borkdude/parmezan): fixes unbalanced or unexpected parens or other delimiters in Clojure files

</details>






