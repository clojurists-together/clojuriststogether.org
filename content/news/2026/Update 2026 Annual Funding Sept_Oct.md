---
title: "Annually-Funded Developers' Update: Sept. and October 2026"
date: 2026-11-11T14:00:00+12:00
author: Kathy Davis
summary: "Bozhidar Batsov, Clojure Camp, Eric Dallo, Michiel Borkent, Jeaye Wilkerson"  
draft: True 


---

Hello Fellow Clojurists!

This is the fifth of six reports from the developers who are receiving annual funding for 2026. Thanks to everyone for supporting their work and these important contributions to the Clojure community. 
Their previous reports can be found here:  
[January/February 2026](https://www.clojuriststogether.org/news/annually-funded-developers-update-january-february-2026/)   
[March/April 2026](https://www.clojuriststogether.org/news/annually-funded-developers-update-march-april-2026/)  
[May/June 2026](https://www.clojuriststogether.org/news/annually-funded-developers-update-may-june-2026/)  
[July/Aug 2026](https://www.clojuriststogether.org/news/annually-funded-developers-update-july-august-2026/)  <br>   

<br>




[**Bozhidar Batsov:**](#bozhidar-batsov) nREPL, neorepl, piggieback, CIDER    
[**Clojure Camp:**](#clojure-camp)  Conj activities, Recurse Center  
[**Eric Dallo:**](#eric-dallo) ECA, ECA CLI, clojure-lsp  
[**Jeaye Wilkerson:**](#jeaye-wilkerson) Jank portability, parity, stability, usability, and performance  
[**Michiel Borkent:**](#michiel-borkent) Squint, Babashka.ffi, clj-kondo, antq, and much more  



## Bozhidar Batsov  
2026 Annual Funding Report 5. Published Nov. 6, 2026.  

For a change, most of my focus in September and October was on nREPL itself,
not on CIDER. The big themes were the nREPL protocol spec and how well the
servers and clients out there actually stick to it. For years building an
nREPL server or client that plays well with the rest of the ecosystem came
down to trial and error, and this time I finally did something about it. It
paid off much sooner than I expected. The highlights:  

- [proof](https://github.com/nrepl/proof), a compatibility suite for nREPL servers and clients, is out, and it's already found (and helped fix) problems in nREPL itself, Babashka, CIDER, Calva, Conjure and many other servers and clients
- [neorepl](https://github.com/nrepl/neorepl), a brand new terminal REPL meant to be something like "CIDER in a terminal" and "CIDER for agents", went from 0.1 to 0.4 in two weeks
- nREPL 1.8 is out, and master already has fixes for some of the bugs proof found in it
- [Piggieback](https://github.com/nrepl/piggieback) 0.7.1 and 0.8.0 fixed a few long-standing problems with ClojureScript REPLs
- nrepl.org became a proper landing page (with an nREPL server running right in your browser), the manual moved to docs.nrepl.org, and CIDER got a landing page of its own at [cider.mx](https://cider.mx)
- CIDER's master gained streaming test results and commands for editing the `ns` form, backed by new cider-nrepl and Orchard releases

Below are the details, project by project.

## proof and the nREPL spec

[proof](https://github.com/nrepl/proof) is the big new project this time
around, and the one I'm most excited about. I've been talking about some
automated way to check nREPL compatibility for ages (it made my list of
[things people hate about nREPL](https://metaredux.com/posts/2023/03/26/bad-nrepl.html)
back in 2023), so it feels really great to finally see it become a reality.
Good things take a while to happen, they said... Does your server work with
CIDER? Until now the only way to find out was to implement the protocol,
connect CIDER (or Calva, or whatever your users run) and see what breaks. Now
you can point proof at your server and it'll tell you. It's a single binary
that runs about 80 checks against a server. A check fails only when a real
client would break (with a link to the client code that depends on the
behavior), and everything else is just a warning. proof also comes with client
profiles that send exactly what CIDER, Calva, Conjure, vim-fireplace, REPLy
and neorepl send, profiles for the servers I know of, and a GitHub Action for
running it in a server's CI.

Once the server side was in place, I started on the clients as well.
`proof proxy` sits between a client and a server and checks what the client
sends, and `proof serve` is a small server for the test suites of clients,
which can mimic the quirks of nREPL, Babashka, jank and others
(`proof serve -like jank`). I wrote about proof in a lot more detail in
[proof: How Strong Is Your nREPL?](https://metaredux.com/posts/2026/11/05/proof-how-strong-is-your-nrepl.html).

- [0.1.0](https://github.com/nrepl/proof/releases/tag/v0.1.0) came out on October 26.
- [0.2.0](https://github.com/nrepl/proof/releases/tag/v0.2.0) followed a week later, along with a [compatibility matrix](https://nrepl.github.io/proof/) that runs proof against every server it knows about daily.

proof goes hand in hand with the [nREPL spec](https://spec.nrepl.org), the
draft Phil Hagelberg wrote earlier this year. When the spec and the existing
clients disagree, proof goes with the clients (a spec that breaks CIDER isn't
much use to anyone), and every such conflict, along with the gaps proof ran
into, ends up on a
[list of spec changes](https://github.com/nrepl/proof/blob/main/doc/spec-changes.md).
The first few of them are already PRs against the spec: how replies relate to
their requests, the status values, how interrupts are answered and the fields
of lookup replies.

What I like the most about proof is how quickly it started paying off:

- nREPL itself sent the `done` of an eval interrupted from another connection to the wrong connection (fixed on master), drops the evals waiting behind an interrupted one ([#503](https://github.com/nrepl/nrepl/issues/503)) and can't interrupt an eval that hasn't started yet ([#504](https://github.com/nrepl/nrepl/issues/504)). Not great!
- Between proof and neorepl, Babashka got a couple of dozen issue reports from me in October, from test ops that never answered to a session that stopped answering after its client went away in the middle of an eval. Michiel Borkent fixed nearly all of them the same day or the next. You rock, Michiel!
- CIDER didn't finish setting up connections to servers without cider-nrepl (fixed), Calva and Conjure didn't sort the keys of the dictionaries they sent (both fixed within days), vim-fireplace and REPLy never close their sessions, and REPLy's and Conjure's interrupts don't say which eval to stop.
- Basilisp and jank got a few reports as well (lookup replies, and a request that throws taking its whole connection down).
- My own toy implementations (nrepl-beam, mezcaml and neat) were the first projects I ran proof against (more on them below).

I was also super happy to see other people using proof already. Tom Waddington
has been running it against his nREPL implementations for
[Janet](https://github.com/waddie/nrepl-janet) and
[Steel Scheme](https://github.com/waddie/nrepl-steel) since early October
(nrepl-janet even tests its client with `proof serve`), Jeaye Wilkerson
[suggested](https://github.com/jank-lang/jank/issues/1104) using it for jank's
nREPL server, and Kyle Cesare is already
[fixing](https://github.com/jank-lang/jank/pull/1175) what it found there.
That's exactly what I was hoping for!

## neorepl

[neorepl](https://github.com/nrepl/neorepl) is the other brand new project,
and it grew up together with proof. It's a terminal REPL for Clojure (and
anything else that runs an nREPL server), and the best way I can describe it
is "CIDER in a terminal": it uses cider-nrepl when it's around, the same way
CIDER does, and falls back to nREPL's built-in ops when it isn't. Every lookup
the REPL can do is also a command with structured output, and there's an MCP
server and a skill for coding agents, so it's "CIDER for agents" as well. Five
releases in three weeks:

- [0.1.0](https://github.com/nrepl/neorepl/releases/tag/v0.1.0) had the REPL with a proper line editor (highlighting, completion, arglists as you type, history per project, a Ctrl-C that actually interrupts), CIDER-style REPL commands, jack-in for Leiningen, the Clojure CLI, Babashka and shadow-cljs, and commands for evaluating code and looking things up that keep their session between calls. It runs on the JVM and on Babashka.
- [0.2.0](https://github.com/nrepl/neorepl/releases/tag/v0.2.0) added a test runner (Clojure and ClojureScript), a full-screen inspector and a stacktrace browser.
- [0.3.0](https://github.com/nrepl/neorepl/releases/tag/v0.3.0) was all about coding agents: `neorepl mcp`, a skill that teaches agents to check, load, test and debug Clojure code with the CLI, and `neorepl check --fix`, which fixes mismatched delimiters the way parinfer does.
- [0.4.0](https://github.com/nrepl/neorepl/releases/tag/v0.4.0) brought tracing, switching the REPL into another named session (e.g. to take a look at what your coding agent is doing), and a huge list of fixes.
- [0.4.1](https://github.com/nrepl/neorepl/releases/tag/v0.4.1) caught up with cider-nrepl 0.64.

proof checks every server with the exact requests neorepl sends, and neorepl
went through `proof proxy` and every scenario of `proof serve` before 0.1 was
out. Its commands are separate processes sharing a session, so neorepl hits
nREPL's interrupt bugs harder than most clients, and making it work as well on
Babashka's server as with cider-nrepl is where most of the Babashka reports
came from. Even JLine got a few bug reports along the way.

By the time I got around to writing this update,
[neorepl 0.5](https://github.com/nrepl/neorepl/releases/tag/v0.5.0) was out as
well, with a much smarter line editor, a namespace browser, automatic
reloading and a CLI that editors can use without a plugin. I wrote about it in
[neorepl 0.5: CIDER in a Terminal](https://metaredux.com/posts/2026/11/05/neorepl-0-5.html).

## nREPL

[nREPL 1.8.0](https://github.com/nrepl/nrepl/releases/tag/v1.8.0) came out on
October 2 with the URL-based connections and the TLS improvements I mentioned
last time, plus a few session fixes. Sessions survive evaluated code closing
`*in*` or `*out*`, a race that could split responses between concurrent reads
in `nrepl.core/client` is gone, and Sashko made persistent sessions re-create
their thread when a faulty exec thunk kills it. He also moved the Unix sockets
to the `SocketChannel` API.

Master moved on quickly after the release, much of it because of proof and
neorepl:

- the `done` of an eval interrupted from another connection goes to the eval's own connection, where the client that sent the eval never got it
- the server sets `TCP_NODELAY` on accepted connections, so on Linux an eval's `done` no longer waits up to 40ms behind its value
- Sashko replaced `:out-limit` with separate `:out-quota` and `:err-quota` limits, made the bencode transport turn empty lists into `nil` for `?`-ending keys and made building the middleware stack faster

The interrupt bugs that are still open are next on my list.

## Piggieback

- [0.7.1](https://github.com/nrepl/piggieback/releases/tag/0.7.1) fixed `load-file` failing with "No such namespace" when the file requires a foreign lib (e.g. a cljsjs package).
- [0.8.0](https://github.com/nrepl/piggieback/releases/tag/0.8.0) fixed `:cljs/quit` on nREPL 1.3+, where the session stayed in ClojureScript mode, and made evaluation use the same dynamic bindings as `cljs.repl`. That means the `cljs-repl` options (e.g. `:warnings`) and the bindings tools like figwheel-main set up around it finally take effect, which closes an issue from 2018. It also picked up `:as-alias` keywords and the `:cljs` branch of `data_readers.cljc`.

## nrepl.org and cider.mx

nrepl.org got a facelift at the end of October. The manual moved to
[docs.nrepl.org](https://docs.nrepl.org), and [nrepl.org](https://nrepl.org)
itself is now a landing page that explains what nREPL is and how its protocol
works, with real exchanges between a client and a server next to every
feature. It even has a ["Try it"](https://nrepl.org/#try) section with an
nREPL server running right in your browser, which turned out to be a story of
its own (see the blog posts below). That playground is how
[nrepl-sci](https://github.com/nrepl/nrepl-sci), a small nREPL server for SCI
that runs entirely in a web page, came to be, and it even led me to take a
closer look at [clojure-mode](https://github.com/nextjournal/clojure-mode) for
CodeMirror, which powers the playground's editor.

CIDER got a landing page of its own at [cider.mx](https://cider.mx), made to
look like an Emacs frame running org-mode (mode line, `M-x` and all), with mini screencasts of CIDER
in action. I had a lot of fun working on both sites!

## neat, mezcaml and nREPL on the BEAM

My toy nREPL implementations were the first things I ran proof against, and
all of them got a round of fixes in early October:

- dialtone and repartee, the Erlang and Elixir servers in [nrepl-beam](https://github.com/nrepl/nrepl-beam), now answer `close` with `session-closed`, echo the op in `unknown-op` replies and return a value for every Erlang form in an eval
- [mezcaml](https://github.com/nrepl/mezcaml) no longer lets late output from one eval end the next one early, interrupts on Ctrl-C, answers stdin requests, closes its session on exit and completes on jank and Basilisp
- [neat](https://github.com/nrepl/neat) shows late and id-less output, reports error statuses that come without `err`, handles unknown sessions and stdin EOF, and checks `describe` before using completions, lookup and interrupt

neat's own integration suite now tests just the client and leaves the servers
to proof. None of the three has had a release yet, though.

## CIDER

No CIDER release this time (2.1 is still brewing), but master kept moving:

- Test results now stream in while the tests run (`cider-test-stream-results`, on by default): the echo area keeps a running count, and failing tests land in the report as soon as they finish. This closes an issue from 2014! `cider-test-fail-fast` is now off by default to go with it.
- A family of commands for editing the `ns` form: `cider-ns-add-require`, `cider-ns-add-import`, `cider-ns-add-missing` (adds what the symbol at point needs), magic requires (typing `alias/` adds the require the alias needs) and `cider-ns-clean`. These are some of clj-refactor's most popular features, and they're backed by new cider-nrepl ops that read your code instead of loading it.
- Connecting to a server without cider-nrepl (e.g. plain nREPL or Babashka) finishes setting up the connection again, where `cider-connected-hook` and friends never ran. proof found this one.
- Keys with a `nil` value are left out of nREPL requests instead of being sent as empty lists, which closes an issue from 2020.
- Natively compiled builds no longer have broken menu options, Emacs 31 is on CI, and the manual got per-build-tool setup tabs, the refcard and CI checks for its cross references.

## cider-nrepl and Orchard

[cider-nrepl](https://github.com/clojure-emacs/cider-nrepl) releases:

- [0.63.0](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.63.0) runs tests with `orchard.test` and streams their progress to the client, which is what CIDER's test streaming builds on.
- [0.63.1](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.63.1) was Sashko's quick bump to compliment 0.8.2.
- [0.64.0](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.64.0) added the `cider/resolve-missing`, `cider/resolve-alias` and `cider/clean-ns` ops behind CIDER's `ns` commands (after three alphas), made the test ops always answer and send what tests print with their own responses on nREPL 1.3+, and fixed the inspector and ClojureScript tests on shadow-cljs.

[Orchard](https://github.com/clojure-emacs/orchard) releases:

- [0.45.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.45.0) added `orchard.test`, the `clojure.test` runner that used to live in cider-nrepl and can now report progress as it goes, plus Sashko's string analytics in the inspector and his fix for the stack overflows when printing recursive collections.
- [0.46.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.46.0) and [0.47.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.47.0) added `orchard.ns-form`, which takes apart an `ns` form and tells what it requires, refers and imports that the code doesn't use, all without loading anything, and fixed some Java doc rendering problems.
- [0.48.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.48.0) made tracing show calls that throw and stopped tracing a function tracing itself uses (say, `str`) from overflowing the stack.

Sashko also added JDK 27 to the CI of nREPL, cider-nrepl and Orchard. Thanks,
Sashko!

## refactor-nrepl and clj-refactor

[refactor-nrepl 3.15.0](https://github.com/clojure-emacs/refactor-nrepl/releases/tag/v3.15.0)
documented the limitations of its AST-based analysis and deprecated the ops
cider-nrepl has taken over. Back in the spring I said I'd like to move the
most useful refactor-nrepl features to CIDER and cider-nrepl eventually, and
the `ns` editing commands above are the first step in that direction.

## clojure-ts-mode

No release, but quite a few fixes on main. I fixed clojure-ts-mode on Emacs 31
(e.g. the ClojureScript mode asking for the jsdoc grammar, `M-q` indenting
only the innermost form) and a pile of refactoring commands that went wrong
around metadata, `#_` forms and reader conditionals. Jason Jackson sent a
round of indentation and alignment fixes (threading macros, reader prefixes,
metadata and comments before arguments), and Kyle Cesare kept up with jank's
syntax. Thanks, Jason and Kyle!

## MrAnderson and smaller things

[MrAnderson 0.7.2](https://github.com/benedekfazekas/mranderson/releases/tag/v0.7.2)
stopped bundling pomegranate's Maven classes in its jar, where they clashed
with any other resolver on the classpath and broke cider-nrepl's build once
tools.build pulled in a newer tools.deps. The artifact went from 8 MB to about
200 KB in the process.

There was also a lot of release and CI maintenance across nREPL, cider-nrepl,
Orchard, Piggieback and friends (e.g. release notes taken from the changelogs,
the docs of a release published by its release workflow), and I revived the CI
of parseclj and parseedn.

## Blog posts

- [neorepl: A Terminal REPL for Clojure (and Friends)](https://metaredux.com/posts/2026/10/10/neorepl-a-terminal-repl-for-clojure-and-friends.html)
- [neorepl 0.4: Tests, Inspector, Tracing and Agents](https://metaredux.com/posts/2026/10/30/neorepl-0-4.html)
- [nREPL, Right in Your Browser](https://metaredux.com/posts/2026/11/01/nrepl-right-in-your-browser.html)
- [neorepl 0.5: CIDER in a Terminal](https://metaredux.com/posts/2026/11/05/neorepl-0-5.html)
- [proof: How Strong Is Your nREPL?](https://metaredux.com/posts/2026/11/05/proof-how-strong-is-your-nrepl.html)

## What's next

Last time I said CIDER 2.1 was the obvious next milestone. It still is, but it
got delayed, as most of my time went into nREPL instead. nREPL 1.8 shipped as
planned, and mezcaml and the BEAM servers got a lot more reliable thanks to
proof, even if neither got a release. While we've made solid progress on CIDER
and friends as well, my main focus until the end of the year will likely be
the broader nREPL ecosystem: fixing the interrupt bugs proof found in nREPL,
turning the list of spec changes into PRs against the spec, and getting more
servers and clients to run proof.

Thanks to Clojurists Together for the continued support of my Clojure OSS work!
You rock!  <br>  

---

## Clojure Camp  
2026 Annual Funding Report 5. Published Nov. 7, 2026.  

## What Happened

### Workshops at Conj
- Ran two 3-hour workshops, which were well received.
- Prepared three self-guided "learning modules": [clojure-camp/code-jam](https://github.com/clojure-camp/code-jam)
- Updated and re-released **Clojure Warrior** (a port of Ruby Warrior): [warrior.clojure.camp](https://warrior.clojure.camp/)
- Updated our remote mob tooling to support multiple mob servers simultaneously.

### Bringing New Clojurians to Conj
- Sponsored 5 new Clojurians to attend Conj.

### Hosting an Airbnb at Conj
- As an experiment, we organized a group stay at Conj, in preparation for doing it at other times and places.

### Rewrote Our Pairing Tool

### Clojure at the Recurse Center
- I've been running a Clojure workshop at the beginning of every Recurse batch. It's been successful at convincing a few people per batch to spend some of their time on Clojure.

### Corporate Bookkeeping and Filing
- Not much to say here, other than that Plain Text Accounting + Claude work well together.

## Plans

- Continue on badges site content
- Continue on new event management tooling
- Continue on research project on how Clojure has changed over time (just starting)
- Ongoing events:
  - Monthly mob at Recurse
  - Weekly book club
  - Monthly remote mob  <br>


---

## Eric Dallo  
2026 Annual Funding Report 5. Published Nov. 6, 2026.  

2 months of big milestones! ECA passed 1000 stars on GitHub :proud:, got an official terminal client written in Clojure and became much more resilient for long-running agents, with lots of new people contributing. On clojure-lsp, we had a new release full of community contributions, followed by a big round of refactoring fixes. Thanks ClojuristsTogether and everyone helping with feedback, issues and contributions! :heart:

### [ECA](https://github.com/editor-code-assistant/eca)

ECA reached __1000 stars__ on GitHub in September :star:, a really special milestone for a project created a bit more than a year ago, and eca-emacs passed 500 stars too, thank you all! These 2 months were focused on reliability for long-running tasks, better support for subscriptions and the terminal experience. I'm starting to plan a stable release soon, since we already consider ECA pretty stable for some time.
As usual [the changelog is huge](https://github.com/editor-code-assistant/eca/blob/0.169.0/CHANGELOG.md), so here are the highlights since the last report:

#### 0.158.0 - 0.169.0

- __Subscription quota__: For Claude Pro/Max, ChatGPT and GitHub Copilot subscriptions, chat usage and provider status now show the remaining quota and when it resets instead of costs, plus a new `/quota` command. Chat usage also shows the output tokens per second (TPS) of the responses.
- __Multiple accounts of the same provider__: Use work and personal subscriptions side by side via provider `inherit`, like `"anthropic-work": {"inherit": "anthropic"}`.
- __Resumable subagents__: `spawn_agent` can continue a previous subagent conversation keeping its history and model, and the new subagent `timeoutSeconds` gives subagents a final turn to report their findings on timeout or max steps instead of losing their work.
- __MCP tool search__: New `mcpToolSearch` config keeps MCP tool schemas out of the context until the LLM loads them with the new `eca__search_tools` tool, a big context saver for people with lots of MCP servers.
- __`view_image` tool__: The agent can now load png/jpg/gif/webp files from disk into the context, useful to check screenshots or generated images.
- __Chat-scoped approvals__: "Approve & remember" is now scoped to the chat and its subagents instead of all chats, with new `/approvals` and `/forget-approvals` commands to list and forget them.
- __Better compaction__: New `autoCompactTokensLeft` config to auto-compact when a fixed number of context tokens remain instead of a percentage, and a new compaction prompt for higher quality summaries.
- __Performance__: New `incrementalUpdates` capability makes rollback, flags and forks fast on long chats, updating them incrementally instead of replaying the whole history. Native binaries are now built with GraalVM for JDK 25, fixing an idle server burning CPU when gateways close connections mid-stream.
- __More providers and models__: Claude Fable 5.1, Mythos 5.1 and Opus 5.5, gpt-6-astra, gpt-6-sol, gpt-6-luna and gpt-6.1-sol, a smarter GitHub Copilot model catalog hiding models the account can't use, `no_proxy` support and more.

That's 238 commits from 19 people in the server repo alone, thanks zikajk, emecii, mikemikimike, itkonen, rschmukler and everyone else contributing with issues, PRs and discussions, love this community!

Also, Jeremiah Coyle (@paintparty), funded by ClojuristsTogether as well, started a redesign of the [eca.dev](https://eca.dev) docs with better typography and icons, a persistent docs sidebar and a logo wall with some of the companies using ECA, like Nubank, Flexiana, Biolit and Team Ohana, a really amazing work so far!

### [ECA CLI](https://github.com/editor-code-assistant/eca-cli)

<img width="875" height="538" alt="eca-cli" src="https://github.com/user-attachments/assets/846798d7-4002-427f-8f7d-5aa62e62f86c" />



The terminal client got a huge push and is now an official ECA client! It's written in Clojure with [Babashka](https://babashka.org) and [charm.clj](https://github.com/TimoKramer/charm.clj), and ships as a single self-contained binary that downloads and updates the ECA server by itself, no Babashka, JVM or editor needed. It has most of the features of the editor clients: markdown rendering with syntax highlighting, tool approvals with diffs, questions, steering, task panel, rollback, flags and forks, `@` contexts, provider login and MCP management, plus a print mode for scripts and pipes, like `git diff | eca-cli -p "review this change"`. Thanks to Sam O'Leary for starting the project, it's really nice to see a Clojure TUI like this one!

### Other ECA clients

Most clients got the subscription quota and TPS in the chat usage, the subagent model and variant in the `spawn_agent` card and lots of fixes, plus client specific work:

- **[eca-emacs](https://github.com/editor-code-assistant/eca-emacs)**: Faster rendering of long chats with incremental updates and coalesced streaming, inline thumbnails for images mentioned in the chat, file buffers reverted after ECA edits them, TRAMP hangs fixed, and lots of UX fixes for evil, Doom and Emacs 31.
- **[eca-vscode](https://github.com/editor-code-assistant/eca-vscode)** (0.54.1 - 0.56.0): New `eca.sendProcessId` setting and starting the server from the workspace dir, making it easier to run ECA inside a [sandbox](https://eca.dev/config/sandboxing/), plus a fix for copying chat messages in VS Code 1.143.
- **[eca-intellij](https://github.com/editor-code-assistant/eca-intellij)** (0.31.1 - 0.33.0): Fix chat panel freezing on `/login` and slow server replies, and high CPU while long tool calls stream.
- **[eca-desktop](https://github.com/editor-code-assistant/eca-desktop)** (0.10.1 - 0.12.0) and **[eca-web](https://github.com/editor-code-assistant/eca-web)**: Shared webview improvements, like proper markdown lists, and pending questions restored when opening remote chats.
- **[eca-nvim](https://github.com/editor-code-assistant/eca-nvim)**: Reload open buffers when the server edits a file shown in the sidebar.

The community also built 2 new clients that are now part of the org: [eca-omarchy](https://github.com/editor-code-assistant/eca-omarchy), a native client for the [Omarchy](https://omarchy.org) shell by Oliver Marks, and [eca-pharo](https://github.com/editor-code-assistant/eca-pharo), an ECA integration for Pharo Smalltalk by Larry Staton Jr., showing how the ECA protocol makes it easy to bring the same experience anywhere!

### [clojure-lsp](https://clojure-lsp.io/)

A new release is out with the changes mentioned in the last report, plus lots of community contributions: new code actions, better `:refer` suggestions, hover for special forms and more. We also had eoliphan reporting a batch of 9 detailed bugs in refactorings like `move-form`, `clean-ns`, `promote-fn` and `extract-to-def`, all fixed and already on master for the next release, thanks!

#### 2026.10.15-11.42.38

Besides the changes from the last report, like the `cycle-namespaced-map` code action and the API/CLI rename fix:

- New code action to replace a fully-qualified namespace with its alias, adding the `:as` to the `:require` form when missing. #1810
- Multi-file refactorings now prompt for approval, if supported by the client. #2370
- Sort namespace suggestions by usage when suggesting `:refer`. #1997
- Show built-in documentation when hovering Clojure special forms. #2457
- Report the processing of external file changes (`workspace/didChangeWatchedFiles`) as work done progress, ending only once dependent files are re-analyzed, so clients can wait for those changes to be fully processed. #2473
- Fix the "Fetching libs for completion" progress never showing up in clients.
- Fix navigation and other features inside dependency jars when clients send single-slash jar URIs, including escaped Calva URIs. #2477
- Prevent Java test fixtures from being written to the global JDK cache, and rebuild caches missing analysis or checksums. #2285
- Fix `:cache-path` setting being ignored. #2475
- Skip deleted files in CLI `clean-ns` and `format`. #2470
- Bump GraalVM to 21 for the Windows native binary, in sync with the other archs.
- Bump clj-kondo to `2026.08.05-20261014.211514-15`.

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)

#### 3.5.6

- Fix IDE freeze while typing with the completion popup open: LSP4IJ was waiting for `completionItem/resolve` on the UI thread, now the item is resolved in the background and the completion documentation is computed asynchronously.

### [metrepl](https://github.com/ericdallo/metrepl)

#### 0.5.3

- Replace jsonista with charred, dropping the jackson-core dependency.  <br>

---

## Jeaye Wilkerson  
2026 Annual Funding Report 5. Published Oct. 27, 2026.  

Hello everyone! Thank you so much for sponsoring me this year. It's been an incredible year for jank. As I mentioned at Clojure Conj 2026, the next milestone for jank is a beta
release. I'm targeting January 2027. In this post, I'd like to spell out exactly
what that release will entail and how we'll get there. The beta release has four
priorities, in no particular order:
- Stability
- Portability
- Parity
- Usability

The progress of all of these is being tracked by this [Github milestone](https://github.com/jank-lang/jank/milestone/2).



### Stability  
jank still has many bugs and we're not going to catch them all prior to the beta
release, but we do want to catch the most obvious and impactful bugs. Over the
past couple of years, we've added a ton of new functionality to jank and we need
to ensure it works reliably. We have great test coverage for most systems, but we
still need to fill in the gaps and address the systems which are lacking
coverage. The main targets here are:  

Core Clojure libs (as tested by jank's [clojure-test-suite](https://github.com/jank-lang/clojure-test-suite) -- help wanted!)
Various C++ interop edge cases
nREPL and native build system tooling


### Portability  
We have jank packages for macOS Homebrew (arm64), Ubuntu (x86_64), Arch
(x86_64), and Nix (x86_64). Each of these packages is bleeding edge, since jank
doesn't yet have versioned releases. The biggest change here for beta will be
that jank releases will become versioned and immutable. The existing bleeding
ege jank packages will be renamed to jank-dev and the default jank packages will
track versioned releases. Releases will happen at whatever cadence feels right.  

I'd like to grow the package area to include Homebrew packages for Linux
x86_64 and macOS x86_64, as well as statically linked Alpine packages and
[Windows x86_64 MSYS2](https://github.com/jank-lang/jank/pull/945). If you have
an OS where you'd like to see jank, let me know!  

Part of portability is to improve jank's embedding story. This involves filling
out jank's C API, improving error handling, and addressing the common gotchas
around embedding an LLVM-based JIT runtime.  

### Parity  
Some areas of Clojure parity, such as protocols and records, have yet to be
tackled. We're also missing a couple of dozen key vars in `clojure.core`, like
rseq, unchecked arithmetic, etc. For the beta release, I want to achieve a
strong parity with upstream Clojure JVM and track it in terms of Clojure's
versioned releases. We should be able to say that a particular version of jank
tracks a particular version of Clojure JVM.  

On the C++ interop side, we need more parity with how we handle native arrays,
pointer decays, and initializer lists. I also want to improve our feature set
for C++ interop to allow for defining statically typed C++ functions, globals,
and structs within jank code.



### Usability  
As part of the beta release, the jank website will be updated to include starter
examples for game development, GUI apps, and terminal apps. Part of this will
include more packaged native libraries in [awesome-jank](https://github.com/jank-lang/awesome-jank).
I will also focus more on implementing guides and references for Clojure devs to
onboard into jank land, understand the interop, jank's type system, and start
building an intuition for how jank's host systems work.



### Performance  
It's intentionally not a goal to improve jank's performance for the beta
release. There are plenty of areas in jank to optimize and plenty of benchmarks
where jank will not do favorably against Clojure JVM, but I will be focusing on
those next year, leading up to jank's gamma release. Don't worry! When I do
focus on performance, we're going to make some huge gains. I'm already prepared
for many of them, but they're simply not a priority right now compared to
everything else.  


### How's the progress so far?  
If you check out the milestone as of writing this, you'll see that we're around 50% of
the way through already. Thanks to the help of [Chris Badahdah](https://github.com/djblue),
of [Portal](https://github.com/djblue/portal) fame, we've been melting through
the bug list at quite a good clip. I'm expecting, and hoping, that we'll get more
bug reports in the coming weeks, thanks to the [Lisp Game Jam](https://itch.io/jam/autumn-lisp-game-jam-2026). That's exactly where folks like you come in!  



### Summary  
So, in summary, the next milestone is a beta release, targeted for January 2027.
We're focusing on portability, stability, parity, and usability. We need
all of you to try jank out and report issues in order to do a great job of that.
Find any excuse you can to kick the tires and please file a
Github issue if the tires fall off!  <br>

---

## Michiel Borkent  
2026 Annual Funding Report 5. Published Nov. 8, 2026. 

Title: OSS updates September and October 2026
Date: 2026-10-30
Tags: clojure, oss updates
Description: My Clojure OSS updates for September and October 2026

In this post I'll give updates about open source I worked on during September and October 2026.

To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).

## Sponsors

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


## Intro

A lot happened again in the last two months! One major highlight was my visit to
the [Clojure Conj](https://2026.clojure-conj.org) together with Rahul Dé, where we
organized a Babashka workshop. It was great seeing lots of
familiar and new faces over there. To give people at the workshop the best
experience possible, lots of improvements went into babashka prior to the
Conj... workshop driven development I guess. The major milestones I focused on during the last 6-8 months were:

- Automatic command line help and completions in babashka tasks via [babashka.cli](https://github.com/babashka/cli)
- Resolving dependencies without a JVM (by vendoring tools.deps and rewriting the maven part)
- [Babashka FFI](https://github.com/babashka/ffi): a foreign function library to call C-ABI functions from libraries
- Better nREPL support: support popular nREPL middleware like CIDER's test middleware, the CIDER inspector, etc
- TUI support via JLine

All of this combined made for a great workshop, where we developed a stateless password manager that used cryptography either by shelling out or by FFI. This project combined the most-used libraries in babashka, like [babashka.fs](https://github.com/babashka/fs), [babashka.process](https://github.com/babashka/process), [babashka.cli](https://github.com/babashka/cli), [babashka.ffi](https://github.com/babashka/ffi), etc. so even if you didn't end up using this password manager (most people probably didn't) you certainly were exposed to what you would typically use in a bb script or project.
Rahul and I will give this workshop at [EuroClojure 2027](https://2027.euroclojure.org/) as well, so in case you missed it, you'll get one more chance!

<img width="479" height="357" alt="image" src="https://github.com/user-attachments/assets/a9364952-6030-4031-873f-60020c0620aa" />  
<br>
Babashka workshop at Clojure/conj 2026, seen from the back of the room.  <br>  
<br>  

<img width="481" height="357" alt="image" src="https://github.com/user-attachments/assets/da42bd40-0d72-4805-b8d7-3ec3ca4a116f" />  
<br>
Rich Hickey and Rahul Dé getting his hamster hammock signed at Clojure/conj 2026.  <br>

## Highlights

Here are some personal favorite highlights from the last two months.

- [Squint](https://github.com/squint-cljs/squint)
  - now has [source-map](https://github.com/squint-cljs/squint#source-maps) support
  - was adopted by [Hyper](https://github.com/dynamic-alpha/hyper), a Datastar-centric web framework, to produce JS server side. Also see this [blog post](https://blog.michielborkent.nl/squint-tree-shaking-hyper-esbuild.html)
  - has a new [playground design](https://squint-cljs.github.io/squint/) thanks to [@paintparty](https://github.com/paintparty) ([#1045](https://github.com/squint-cljs/squint/pull/1045))

- [Antq](https://github.com/clj-commons/antq), a tool to update outdated deps, now runs with bb!

- You can now run `build.clj` in babashka with `tools.build` (which comes bundled with bb) and get `--help` and shell completions for your build tasks. [Sean Corfield](https://github.com/seancorfield) has been adopting this in projects he maintains. See for example: [next.jdbc](https://github.com/seancorfield/next-jdbc/blob/develop/bb.edn), [HoneySQL](https://github.com/seancorfield/honeysql/blob/develop/bb.edn), [logging4j2](https://github.com/seancorfield/logging4j2/blob/develop/bb.edn) and [rephrase](https://github.com/seancorfield/rephrase/blob/main/bb.edn)

- [Babashka.ffi](https://github.com/babashka/ffi)
  - First Clojars release on 2026-09-18, for the JVM, native image, CLJS and nbb. Node.js 26.1+ through `node:ffi`
  - JVM performance PRs by [@Ramblurr](https://github.com/Ramblurr) ([#36](https://github.com/babashka/ffi/pull/36), [#37](https://github.com/babashka/ffi/pull/37))
  - Adopted by [sqlite4clj](https://github.com/andersmurphy/sqlite4clj), which moved from coffi to `babashka.ffi` ([#26](https://github.com/andersmurphy/sqlite4clj/pull/26), [@Ramblurr](https://github.com/Ramblurr))

- [Clj-kondo](https://github.com/clj-kondo/clj-kondo)
  - Type system improvements: `(when (and (string? x) (pos? n)) (inc x))` will now warn since `(string? x)` implies `x` is a string and `(inc x)` will crash on that input
  - `:unknown-map-key` linter that warns when requesting keys from maps that provably aren't there: given `(defn opts [] {:host "localhost"})`, `(:hots (opts))` will warn.

## Blog posts

Blog posts that I published in this period:
- Babashka 1.13.222: the conj release (2026-09-14)
  https://blog.michielborkent.nl/babashka-1.13.222.html
- Tree shaking squint on the server with esbuild (2026-09-25)
  https://blog.michielborkent.nl/squint-tree-shaking-hyper-esbuild.html

## Updates

Here are some highlights per project. See each project's `CHANGELOG.md` for the full list.

- [Babashka](https://github.com/babashka/babashka): native, fast-starting Clojure interpreter for scripting.
  - Resolve dependencies without a JVM by default. Set `:deps-resolver :jvm` in `bb.edn` or the `add-deps` map, or `BABASHKA_DEPS_RESOLVER=jvm`, to use Java as before. See [the blog post](https://blog.michielborkent.nl/babashka-1.13.222.html)
  - Make `clojure.tools.deps` and its script namespaces, including `clojure.tools.deps.script.make-classpath2`, available to scripts
  - Bundle `clojure.tools.build.api`, with patched `install` and `javac` for babashka. A tools.build dependency on the classpath takes precedence and runs from source
  - Add `bb repl --connect [addr]` to connect to an nREPL server with completion, eldoc, documentation lookup and Ctrl-C interruption. Accepts `host:port`, a port or `unix://path`. Reads `.nrepl-port` by default.
  - Bind the nREPL server to `127.0.0.1` by default instead of `0.0.0.0`. Use `bb --nrepl-server 0.0.0.0:1667` in Docker
  - Bundle nREPL ops for the CIDER inspector, test runner (`C-c C-t`) and `cider-version`
  - Add the nREPL `interrupt` op. Interruption is cooperative and does not stop tight loops
  - Smaller binary: the bundled Clojure sources are stored gzipped, and jline's class files are no longer embedded a second time as resources.
  - Add the CIDER debugger to the nREPL server: `#dbg`, `#break`, `C-u C-M-x` and the step commands
  - Add CIDER's track-state to the nREPL server: font-lock of loaded vars and the box around instrumented functions
  - Add `babashka.deps.maven` with `find-versions`, `with-repository-credentials`, `active-proxy` and `model-repos`
  - Block the certificates of the eMudhra intermediate CA compromise ([@stathissideris](https://github.com/stathissideris))
  - The macOS amd64 binary requires macOS 11.0 or later
  - [Full changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md)

- [babashka.ffi](https://github.com/babashka/ffi): call C functions in shared libraries from Clojure.
  - 0.1.1: First standalone release of `babashka.ffi`, available on Clojars for JVM Clojure, ClojureScript and nbb
  - Call C libraries from JVM Clojure on JDK 25 or newer, or from applications compiled with GraalVM 25 or newer. ClojureScript, nbb and shadow-cljs can call C libraries on Node.js 26.1 or newer through `node:ffi`
  - [#63](https://github.com/babashka/ffi/issues/63): `callback` accepts struct layouts as argument and return types
  - JVM call performance by [@Ramblurr](https://github.com/Ramblurr) ([#36](https://github.com/babashka/ffi/pull/36), [#37](https://github.com/babashka/ffi/pull/37))
  - New [examples](https://github.com/babashka/ffi/tree/main/examples): JavaScriptCore, pinball, SDL3, libusb and QuickJS

- [babashka.esbuild](https://github.com/babashka/babashka.esbuild): use esbuild from babashka and the JVM
  - New project. Bundle and transform JavaScript, TypeScript, JSX and CSS through `babashka.ffi`, without Node.js
  - Expose esbuild's metafile and metafile analysis ([#3](https://github.com/babashka/babashka.esbuild/pull/3), [@lread](https://github.com/lread))
  - Used by [cljdoc](https://cljdoc.org) and [hyper](https://github.com/dynamic-alpha/hyper)

- [deps-deploy](https://github.com/babashka/deps-deploy): deploy jars to Clojars and Maven Central from babashka
  - New project. Takes the options of [slipset/deps-deploy](https://github.com/slipset/deps-deploy), with transfers through `babashka.http-client`
  - GPG signing, local install, snapshots and several jars in one deploy
  - Maven Central through the Publisher Portal

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - Clojure 1.13 map destructuring as of clojure/clojure 33fb064d (1.13.0-alpha11): `:req!`, `:excess` and `:missing` directives, `:defaults` removed. Adds the `selector` macro to `clojure.core`.
  - Speed up `binding` (about 4x on the JVM, 2x in native bb) and `try` without a catch (about 4x)
  - `ns-map` is 6x faster and `ns-refers` 60x
  - Fix [#1094](https://github.com/babashka/sci/issues/1094): a call to a multi-arity fn created a new fn for the called arity on every call, 25x slower than a single-arity call in CLJS
  - CLJS: creating a closure inside a jitted fn is 4x to 9x faster, and creating a multi-arity fn compiles instead of escaping to the interpreter
  - Fix `instance?` on a protocol returning `true` for a type the protocol is extended to, such as `nil` or `Object`, on the JVM
  - [Full changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md)

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
  - NEW linter `:unknown-map-key`: warn on a keyword lookup of a key not present in a map. E.g. `(:hots (opts))` will warn when `opts` returns `{:host "localhost"}`.
  - Catch up with Clojure 1.13.0-alpha9 destructuring
  - Type checker: a type predicate narrows the type of a local in `if-not`, `cond` and `and`. E.g. `(when (and (string? x) (pos? n)) (inc x))` will warn.
  - Type checker: `merge` and `merge-with` require a map or `nil` as first map argument. E.g. `(merge [1 2] [3 4])` will warn.
  - [#2774](https://github.com/clj-kondo/clj-kondo/issues/2774): `:warn-on-reflection` also warns on Java constructor calls. E.g. `(Date. x)` will warn.
  - [#3006](https://github.com/clj-kondo/clj-kondo/issues/3006), [#3014](https://github.com/clj-kondo/clj-kondo/issues/3014), [#3015](https://github.com/clj-kondo/clj-kondo/issues/3015), [#3016](https://github.com/clj-kondo/clj-kondo/issues/3016): fixes for hooks generated with `:clj-kondo/macroexpand-hook`: referred macros, helper namespaces, requires without alias and nested hooks ([@imrekoszo](https://github.com/imrekoszo))
  - [#2945](https://github.com/clj-kondo/clj-kondo/issues/2945): support `:order-by`, `:having` and `:timeout` in Datalevin and Datahike queries
  - [#2998](https://github.com/clj-kondo/clj-kondo/issues/2998): analyze Java 25 class files ([@skylightis666](https://github.com/skylightis666))
  - [Full changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)

- [Squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  - [#453](https://github.com/squint-cljs/squint/issues/453): Add source maps for compiled files
  - [#594](https://github.com/squint-cljs/squint/issues/594): support `for await` in `doseq`, with `^:await` on a binding, `(doseq [^:await x xs] ...)`, or on the binding vector for every binding ([#1053](https://github.com/squint-cljs/squint/pull/1053), [@shaunlebron](https://github.com/shaunlebron))
  - Add `eduction`, `implements?` and `keyword-identical?`
  - Optimize compiled output for `or`, `and` and `reify`
  - Optimize compiled output for core predicates such as `even?` and `string?` in `if`, `and`, `or` and `not`
  - [#1032](https://github.com/squint-cljs/squint/issues/1032): `#html`: escape attribute values from `{:& m}` ([@mskyan](https://github.com/mskyan))
  - Playground: Implement draggable resizing of editor and compile panes ([#1045](https://github.com/squint-cljs/squint/pull/1045), [@paintparty](https://github.com/paintparty))
  - Playground: Support codemirror dark theme across editor, repl output, and compiled code instances ([#1045](https://github.com/squint-cljs/squint/pull/1045), [@paintparty](https://github.com/paintparty))
  - [Full changelog](https://github.com/squint-cljs/squint/blob/main/CHANGELOG.md)

- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects
  - 0.3.71: neil requires babashka 1.13.229 or later
  - [#254](https://github.com/babashka/neil/issues/254): auto-generated CLI help and [shell completions](https://github.com/babashka/neil#shell-completions) via babashka.cli. `neil dep --help` no longer throws
  - [#272](https://github.com/babashka/neil/issues/272): make `babashka.neil/-main` easier to call from code ([@lread](https://github.com/lread))
  - [#268](https://github.com/babashka/neil/issues/268): `neil dep upgrade LIB` upgrades only `LIB` instead of all deps
  - [#267](https://github.com/babashka/neil/issues/267): `neil dep add`, `dep upgrade` and `dep versions` find versions in the `:mvn/repos` of `deps.edn`. `dep versions` no longer lists snapshots

- [babashka CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
  - [#226](https://github.com/babashka/cli/pull/226): use metadata and docstrings from `:fn` and `:exec-fn` vars in babashka and ClojureDart
  - `dispatch`: add `:supplied` to the opts map's `:org.babashka/cli` metadata with the set of option keys given on the command line
  - [#233](https://github.com/babashka/cli/issues/233): Generated help no longer prints extra spaces when `:prog` is not specified ([@lread](https://github.com/lread))
  - [Full changelog](https://github.com/babashka/cli/blob/master/CHANGELOG.md)

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
  - Add `babashka.ffi` built-in library for calling C functions. Requires Node.js 26.1 or newer
  - Bump SCI: faster multi-arity function dispatch, bumps edamame to `1.6.44`

- [Edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
  - Performance: speed up parsing plain decimal numbers on the JVM [#155](https://github.com/borkdude/edamame/pull/155)
  - Performance: build map literals with transients [#159](https://github.com/borkdude/edamame/pull/159)
  - Require Clojure 1.10 or newer [#158](https://github.com/borkdude/edamame/pull/158)

- [deps.clj](https://github.com/borkdude/deps.clj): a faithful port of the Clojure CLI Bash script to Clojure
  - Allow `--version`, `--help`, `-Sdescribe` and a cached `-Spath` to run without Java.
  - Add `*make-classpath-fn*` to allow in-process classpath resolution.
  - Catch up with Clojure CLI 1.12.6.1673

- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of rewrite-clj
  - [#39](https://github.com/borkdude/rewrite-edn/issues/39): Support namespaced maps like `#:a{:x 1}`
  - [neil#239](https://github.com/babashka/neil/issues/239): Read and update maps with metadata like `^{:x 1} {:a 1}`
  - Fix `get`, `assoc`, `update` and `dissoc` when a map value equals the key

- [http-client](https://github.com/babashka/http-client): HTTP client for Clojure and babashka
  - [#86](https://github.com/babashka/http-client/issues/86): `uri-with-query` no longer decodes the existing query, fragment and user info when `:query-params` are used ([@dylanpulver](https://github.com/dylanpulver))
  - [#91](https://github.com/babashka/http-client/issues/91): `decode-body` passes a nil body through, e.g. of a 407 to `CONNECT`

- [fs](https://github.com/babashka/fs): file system utility library for Clojure
  - [#284](https://github.com/babashka/fs/issues/284): non-recursive `match` and `glob` now return immediate child directories, not only files
  - [#269](https://github.com/babashka/fs/issues/269): `match` and `glob` no longer drop pattern text after a 2nd colon on Unix ([@marksto](https://github.com/marksto))
  - [#279](https://github.com/babashka/fs/issues/279): minimum supported version of Clojure is now 1.11 ([@lread](https://github.com/lread))

- [html](https://github.com/borkdude/html): HTML generation library inspired by squint's HTML tag
  - Omit attributes whose value is `nil`, or `false` in html mode: `[:input {:disabled false}]` rendered `disabled="false"`, which disables the input. Matches hiccup ([#19](https://github.com/borkdude/html/pull/19), [@telekid](https://github.com/telekid))
  - Render `true` and `false` as `"true"` and `"false"` for `aria-*`, `data-*`, `contenteditable`, `draggable`, `focusable` and `spellcheck`. Names match in any case, so `:spellCheck` works as well

- [quickblog](https://github.com/borkdude/quickblog): lightweight static blog engine for Clojure and babashka
  - Add `:pre-process-markdown-fn` and `:post-process-html-fn` options to transform each post's Markdown and HTML ([README](https://github.com/borkdude/quickblog#pre--and-post-processing-markdown)) ([@jmglov](https://github.com/jmglov))

- [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to GitHub releases idempotently
  - Throw when Github does not accept an upload. A failed upload no longer leaves a green build
  - Upload the new bytes before touching the existing asset when replacing one

- [Buzz](https://github.com/borkdude/buzz): write a web application with the JVM or babashka only
  - New `observe`, `atom-source` and `buzz.source/Source` API. `:watch` and `invalidate!` are gone ([#20](https://github.com/borkdude/buzz/pull/20))

- [raylib-bb-ffi](https://github.com/borkdude/raylib-bb-ffi): raylib bindings for babashka via `babashka.ffi`
  - New experiment, ported with an LLM. Tests whether the examples of [raylib-jlt](https://github.com/jlt-commons/raylib-jlt) and [raylib-clj](https://github.com/b12n-oss/raylib-clj) run in babashka and on the JVM through `babashka.ffi`

- [cljbang](https://github.com/borkdude/cljbang.el): a Clojure-like language that runs as Emacs Lisp
  - Add `for` ([#12](https://github.com/borkdude/cljbang.el/pull/12)) and `juxt` ([#9](https://github.com/borkdude/cljbang.el/pull/9), [@kpassapk](https://github.com/kpassapk))
  - Fix [#10](https://github.com/borkdude/cljbang.el/issues/10): `str/join` with a separator and an empty coll returns `""`

- [Cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - Bump squint: don't crash the nREPL server on a SyntaxError from eval ([#208](https://github.com/squint-cljs/cherry/pull/208))

- [Cream](https://github.com/borkdude/cream): Clojure + GraalVM [Crema](https://github.com/oracle/graal/issues/11327) native binary
  - Compare load times, tooling and GraalVM build with babashka ([#7](https://github.com/borkdude/cream/pull/7))

- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo) and [clj-kondo-bb](https://github.com/clj-kondo/clj-kondo-bb): released alongside each clj-kondo release

Contributions to third party projects:

- [antq](https://github.com/clj-commons/antq): antq runs on babashka ([#316](https://github.com/clj-commons/antq/pull/316), [#380](https://github.com/clj-commons/antq/pull/380)) and is installable through bbin ([#386](https://github.com/clj-commons/antq/pull/386))
- [hyper](https://github.com/dynamic-alpha/hyper): compile `h/expr` against a served squint core, tree-shaken with esbuild. See [the blog post](https://blog.michielborkent.nl/squint-tree-shaking-hyper-esbuild.html). Serve source maps for `defc` components behind `:source-map?`
- [nextjournal/markdown](https://github.com/nextjournal/markdown): [#71](https://github.com/nextjournal/markdown/issues/71): render the link title in the default `:link` renderer
- [clojure-mode](https://github.com/nextjournal/clojure-mode): bind Option+S to unwrap on macOS ([#62](https://github.com/nextjournal/clojure-mode/pull/62))

## Other projects

These are some other projects I'm involved with, but little to no activity
happened in the past two months.

<details>
<summary>Click for more details</summary>

- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
- [pod-babashka-gozxing](https://github.com/babashka/pod-babashka-gozxing): a babashka pod for QR code and barcode decoding/encoding
- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready-to-use SCI configs.
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
- [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one command
- [graal-build-time](https://github.com/clj-easy/graal-build-time): initialize Clojure classes at build time for GraalVM native-image
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
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
- [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
- [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
- [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
- [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning subprocesses
- [parmezan](https://github.com/borkdude/parmezan): fixes unbalanced or unexpected parens or other delimiters in Clojure files
- [Reagami](https://github.com/borkdude/reagami): A minimal zero-deps Reagent-like for Squint and CLJS
- [Scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
- [Choq](https://github.com/squint-cljs/choq): a ~5 MB binary running the cherry compiler on embedded QuickJS
- [squint-inline](https://github.com/squint-cljs/squint-inline): write squint functions and inline expressions in a ClojureScript project
- [graaljs-cherry](https://github.com/borkdude/graaljs-cherry): a native-image cherry REPL on GraalJS
- [clj-kondo-browser](https://github.com/borkdude/clj-kondo-browser): a static Clojure source browser built from clj-kondo analysis
- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes

</details>






