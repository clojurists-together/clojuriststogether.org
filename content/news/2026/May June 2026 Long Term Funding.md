---
title: "Annually-Funded Developers' Update: May & June 2026"
date: 2026-07-14T14:00:00+12:00
author: Kathy Davis
summary: "Bozhidar Batsov, Clojure Camp, Eric Dallo, Michiel Borkent, Jeaye Wilkerson"  
draft: True


---

Hello Fellow Clojurists!

This is the third of six reports from the developers who are receiving annual funding for 2026. Thanks to everyone for supporting their work and important contributions to the Clojure community. 

[**Bozhidar Batsov:**](#bozhidar-batsov) cider-nREPL, neat, Sayid, port, Orchard, CIDER, and more   
[**Clojure Camp:**](#clojure-camp)  Badges, nano-conj, exercises  
[**Eric Dallo:**](#eric-dallo) eca, eca-desktop, clojure-lsp, clojure-lsp-intellij  
[**Jeaye Wilkerson:**](#jeaye-wilkerson) Jank compiler architecture and optimization  
[**Michiel Borkent:**](#michiel-borkent) babashka, ClojureScript async/await, Rebel, squint, and much more  

## Bozhidar Batsov  
2026 Annual Funding Report 3. Published July 10, 2026.  

The past two months were some of the most productive I've had in a long while.
I had a lot of inspiration during this period and managed to tackle plenty of
long-standing ideas and issues across the entire nREPL/CIDER ecosystem. I even
grew the ecosystem with a couple of brand new projects. The highlights:  
- CIDER 1.22 is out
- CIDER 2.0 is essentially ready and needs more user testing
- [Sayid](https://github.com/clojure-emacs/sayid) is reborn
- Two brand new projects saw the light of day: [port](https://github.com/clojure-emacs/port) and [neat](https://github.com/nrepl/neat)
- [Piggieback](https://github.com/nrepl/piggieback) 0.7.0 is out (and [Weasel](https://github.com/nrepl/weasel) got modernized while I was in the area)
- [clj-refactor](https://github.com/clojure-emacs/clj-refactor.el) and [refactor-nrepl](https://github.com/clojure-emacs/refactor-nrepl) got some love as well

Below are the details, project by project.

### CIDER  

CIDER 1.22 ("São Miguel") landed in mid-June, wrapping up the 1.x series. Its
main features:  

- a registry for jack-in tools, so third parties can plug new build tools and
  Clojure dialects into `cider-jack-in`
- a "default session" escape hatch from sesman's project-based dispatch
- keyword-argument versions of the low-level request APIs, alongside a proper
  decoupling of the nREPL client layer from CIDER's UI

It also fixed a long list of small annoyances: severe editor lag in unlinked
buffers, several TRAMP and SSH tunnel problems, request id leaks, and a bunch
of broken menu entries.

- [CIDER 1.22.0](https://github.com/clojure-emacs/cider/releases/tag/v1.22.0)
- [CIDER 1.22.2](https://github.com/clojure-emacs/cider/releases/tag/v1.22.2) (plus a quick 1.22.1 fixing a docs site problem)

Right after that I switched the development version to 2.0 and most of the
planned work is already done. The headline items so far:  

- [transient menus](https://docs.cider.mx/cider/usage/cider_mode.html#command-menus) for all the command groups (plus new menus for the debugger
  and the inspector)
- [inline stepwise macroexpansion](https://docs.cider.mx/cider/debugging/macroexpansion.html#inline)
- SLIME-style [call graph browsers](https://docs.cider.mx/cider/usage/misc_features.html#browsing-the-call-graph) (who-calls, who-implements and friends)
- source-based [find-references](https://docs.cider.mx/cider/usage/misc_features.html#find-references)
- a `tap>` buffer and a dedicated [trace buffer](https://docs.cider.mx/cider/debugging/tracing.html)
- namespace [load-state indicators](https://docs.cider.mx/cider/usage/code_evaluation.html#load-state-indicators)
- the revival of [rich content in the REPL](https://docs.cider.mx/cider/repl/configuration.html#displaying-rich-content-in-the-repl)  

That last one deserves a special mention: evaluation results that are images
now [render inline out of the box](https://docs.cider.mx/cider/usage/code_evaluation.html#rich-results), and file/URL results offer their content
on demand, six years after the feature had to be disabled over its safety
problems. There was also a big cleanup pass: consolidated configuration
options, the REPL history browser renamed to `cider-history` to end a
long-standing naming clash, theme-aware faces instead of hardcoded colors,
refreshed docs and a regenerated refcard.  

CIDER 2.0 is available from MELPA snapshots and I'd love for more people to
take it for a spin before the final release.  

### cider-nrepl  
Lots of [cider-nrepl](https://github.com/clojure-emacs/cider-nrepl) releases, driving the CIDER work above:  

- [0.60.0](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.60.0) added the ops backing the new protocol exploration commands (`cider/who-implements`, `cider/type-protocols`, `cider/protocols-with-method`).
- [0.61.0](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.61.0) brought ClojureScript test support, a ClojureScript macroexpansion fix, formatting that honors the project's cljfmt configuration, and a `pprint` backed by `orchard.pp`.
- [0.62.0-alpha1](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.62.0-alpha1) and [0.62.0-alpha2](https://github.com/clojure-emacs/cider-nrepl/releases/tag/v0.62.0-alpha2) hardened the content-type and slurp middleware (URL scheme allowlist, size caps, graceful fetch errors) and cleaned up the response protocol, which is what made it safe to turn rich content on by default in CIDER 2.0.

Along the way the project's build was migrated from Leiningen to tools.deps,
which required a new [MrAnderson](https://github.com/benedekfazekas/mranderson) release (see the blog posts below).  

### Orchard  

[Orchard](https://github.com/clojure-emacs/orchard), the library that powers much of cider-nrepl's functionality, kept pace:  

- [0.42.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.42.0) and [0.43.0](https://github.com/clojure-emacs/orchard/releases/tag/v0.43.0) continued the inspector polish, added symbol classification to `orchard.meta`, a programmatic listener API for the tracer, and protocol/multimethod introspection in `orchard.xref`. The project also moved to tools.deps and its CI now covers JDK 26.  

### Sayid  
Sayid, the omniscient Clojure debugger, had been dormant for years and I
finally gave it the revival it deserved:  
- [0.2.0](https://github.com/clojure-emacs/sayid/releases/tag/v0.2.0) was the big modernization pass: new `mx.cider/sayid` coordinates, a documented nREPL middleware API, a consolidated op surface (37 ops down to 26) and fixes for the most annoying Emacs client breakages.
- [0.3.0](https://github.com/clojure-emacs/sayid/releases/tag/v0.3.0) followed with usability work: no more frozen Emacs during the reload workflow, simpler query commands and help buffers generated from the keymaps.  

### port  
[port](https://github.com/clojure-emacs/port) is a brand new project I started in May: a minimalist Clojure
interactive programming environment for Emacs, built on prepl instead of
nREPL. It went from nothing to three releases in the course of the month:  

- [0.1.0](https://github.com/clojure-emacs/port/releases/tag/v0.1.0)
- [0.2.0](https://github.com/clojure-emacs/port/releases/tag/v0.2.0)
- [0.3.0](https://github.com/clojure-emacs/port/releases/tag/v0.3.0), which added eldoc with active argument highlighting, a wire-level message log for debugging and a roughly 10x speedup in handling large prepl responses.  

I don't have any particular plans for the future of this project - it was just
something I'd wanted to experiment with for a while. I see it as an interesting
option for people looking for some middle ground between `inf-clojure` and
CIDER.  

### neat  

[neat](https://github.com/nrepl/neat) is the other new arrival: a small, language-agnostic nREPL client
for Emacs. [0.1.0](https://github.com/nrepl/neat/releases/tag/v0.1.0) has the essentials in place: a pure-elisp bencode codec, a
comint-based REPL, and a source-buffer minor mode with eval, completion,
eldoc, xref and doc lookup, tested against Clojure, Babashka and Basilisp.
It's early days, but it's a nice testbed for exercising the nREPL protocol
outside CIDER.  

This project also means I've dropped any plans to try to make CIDER a
language-agnostic development environment. Going forward CIDER will focus only
on Clojure-like languages, and everything else will be covered by `neat`.  

### Piggieback and Weasel  

The nREPL org saw some ClojureScript-flavored action:  

- [Piggieback 0.6.2](https://github.com/nrepl/piggieback/releases/tag/0.6.2) and [Piggieback 0.7.0](https://github.com/nrepl/piggieback/releases/tag/0.7.0). The 0.7.0 release makes `load-file` evaluate the editor's buffer contents instead of re-reading from disk, tears down ClojureScript REPLs when their sessions close (no more leaked Node processes) and surfaces ClojureScript status in the `describe` response.
- [Weasel 0.8.0](https://github.com/nrepl/weasel/releases/tag/0.8.0) modernized the WebSocket REPL: the client now uses the platform's native `WebSocket`, so it runs in any modern JavaScript runtime (browsers, Node 22+, Deno, Bun, workers), and the minimum requirements moved to Clojure/ClojureScript 1.12.  

I also backfilled proper GitHub releases for the historic tags of both
projects, so their release history is finally browsable.  

Improving the ClojureScript support in CIDER has long been a major objective
for me, and these small changes were some initial steps in that direction.  

### refactor-nrepl and clj-refactor  

refactor-nrepl got three releases: [3.12.0](https://github.com/clojure-emacs/refactor-nrepl/releases/tag/v3.12.0), [3.13.0](https://github.com/clojure-emacs/refactor-nrepl/releases/tag/v3.13.0) and [3.14.0](https://github.com/clojure-emacs/refactor-nrepl/releases/tag/v3.14.0), the last one
making the AST-based indexing much faster and more reliable. clj-refactor.el
received a round of maintenance on master as well, and will get a new release
after I wrap up the work on CIDER 2.0.  

I'm still pondering the future of both projects, as I plan to move the most
useful refactor-nrepl features (those that don't carry a lot of complexity) to
CIDER and cider-nrepl eventually, and I'm not sure the flagship AST-powered
refactorings are very competitive these days (compared to `clojure-lsp` and
static project-wide analysis a la `clj-kondo` in general). I'd certainly
appreciate more feedback from the users of clj-refactor on the subject.  

### Blog posts  

I wrote a few articles related to the work above:  

- [Port: a minimalist prepl client for Emacs](https://batsov.com/articles/2026/05/12/port-a-minimalist-prepl-client-for-emacs/)
- [neat: a language-agnostic nREPL client for Emacs](https://batsov.com/articles/2026/05/20/neat-a-language-agnostic-nrepl-client-for-emacs/)
- [nREPL Forever](https://batsov.com/articles/2026/05/20/nrepl-forever/)
- [CIDER 1.22 ("São Miguel")](https://metaredux.com/posts/2026/06/16/cider-1-22.html)
- [MrAnderson 0.6](https://metaredux.com/posts/2026/06/22/mranderson-0-6.html)
- [CIDER 2.0 is Brewing...](https://metaredux.com/posts/2026/06/30/cider-2-0-is-brewing.html)
- [nREPL and ClojureScript: Demystifying Piggieback](https://metaredux.com/posts/2026/07/01/demystifying-piggieback.html)
- [Sayid Redux](https://metaredux.com/posts/2026/07/01/sayid-redux.html)

### What's next  

Getting CIDER 2.0 across the finish line is the main priority, followed by a
clj-refactor release once that settles. I've plucked most of the low-hanging
fruit by now, but there's always more to do.  

Thanks to Clojurists Together for the continued support of my Clojure OSS work!
You rock!  <br>

---

## Clojure Camp  
2026 Annual Funding Report 3. Published July 10, 2026.  

