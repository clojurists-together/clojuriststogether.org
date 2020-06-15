---
title: October 2019 Monthly Update
date: "2019-12-06"
type: post
author: Nola Stowe
---

October was our third and final month with this round of projects, check out their monthly update!

<!--more-->

* [Shadow CLJS](http://shadow-cljs.org/) with Thomas Heller
* [Meander](https://github.com/noprompt/meander) with Joel Holdbrooks
* [Calva](https://github.com/BetterThanTomorrow/calva) with Peter Strömberg
* [CIDER](https://cider.mx/) with Bozhidar Batsov

# Shadow CLJS

Released version 2.8.69

## Inspect

I spent the last few weeks implementing the first version of a new "Inspect" feature. It was inspired by REBL, fulcro-inspect and re-frame-10x. My first goal with this is to bring the `console.log` experience from the Browser to all other JS platforms and Clojure itself. `console.log` in the Browser is great because it is basically a `prn` on steroids. Instead of getting one large blob of text you get a structured output you can easily inspect visually. It is however limited in the Browser, basically abusing the built-in Browser devtools. "Inspect" runs in an independent UI (currently the shadow-cljs Web UI) and can be used to inspect any supported runtime. These include react-native, node and Clojure which previously had a rather basic `prn` experience.

This combines a generic remote Data Viewer along with support for `tap>` in the runtime to "expose" new values to the UI. Later versions of "Inspect" will also include direct REPL intergration and some other features found in similar tools.

I'm very excited about this feature and it is just getting started. I wrote a bit more about it [here](https://clojureverse.org/t/introducing-shadow-cljs-inspect/5012). This is all still undergoing a lot of changes but has become part of my normal workflow already. I hope others will find it useful as well.

## Bugfixes

- Optimized macro reload logic to consume less resources

- Optimized npm reload logic to consume less resources

- Fixed an nREPL bug related to cloned sessions (causing problems in cider-nrepl)

- Updated to most recent releases of the Closure Compiler and Library


# Meander

## UX

Some users had intermittenly reported problems with `defsyntax` and its use of `clojure.spec.alpha/conform`. After some digging around I discovered the problem was due to a patch to the core specs library which changed the names of some of the keys thus breaking the use of conform in `defsyntax`. To fix the problem, `defsyntax` now has its own specs and no longer relies on `clojure.core.specs.alpha`.

In other areas corrections to or movement of specs were made to improve the quality of life for users using spec instrumentation.

One of the most significant improvements in October came in the form of ClojureScript build size. We had some reports of Meander resulting in very large ClojureScript builds (even with advanced compilation) causing people to be unable to use the library seriously. Fortunately, with the help of Thomas Heller and Jimmy Miller, we were able to completely eliminate the build size problem altogether.

## Performance/Code generation improvements

The performance of n or more patterns when using the `search` macro by define runtime was given a boost by writing functions specially suited to the task.

Continuing with improvements to reference (the symbols that look like `%foo`) compilation last month, this month I fixed a subtle bug which could cause code size to increase substantially with by combining cyclic references and `or` patterns. While patterns would still match as expected, the resulting code size and compiler performance in these cases was unacceptable. Additionally, unused functions are no longer produced by reference code generation.

To reduce code size, the internal process which attempts to merge two nodes in the intermediate representation made some progress. Merging is attempted when two nodes have similar properties. If their properties are the same, then an attempt is made to merge their children. In the past, if any of the submerges failed, the whole merge would fail. This is not the desired outcome. Since the two nodes are similar enough to combine the merge process now combines the two nodes and produces a branch node (a choice between patterns) with the children. This reduces the generated code size significantly and avoids doing extra work.

Substitution got better this month with `with` pattern enhancements/fixes. Recursive references to map patterns which used memory variables i.e. `%m {!k !v & %m}` used to stack overflow, now they don't. The substitution compiler now uses the same acyclic reference inlining as the match compiler avoiding the cost of function calls at runtime.

## Enhancements

Toward the end of the month I wanted to shift my attention to making the `cata` pattern matching operator available on the substitution side of `rewrite` and `rewrites` clauses. The semantics of the substitution version of the `cata` pattern is to recursively rewrite with a constructed value via substitution. So `(cata [?x ?y])` on the substitution side would first construct `[?x ?y]` then recurse. This provides an elegant way to write non-trivial recursive rewrite systems which are very useful when work with recursively structured data. While my goal was to have it complete by the end of the month, I just couldn't quite pull it off in time. However, this work is near completion and I expect to make it available soon.

# Calva

## October 1-15

Theme: _Changes Spree_

Two weeks, seven new Calva versions. We got into a flow, changing, testing, releasing. Christian Fehse kept a speed where I had a hard tine keeping up with testing and giving feedback. But I managed 😄, and even got a few changes in myself, plus some documentation.

### Changes

- [Fix Toggle the "Use WSL" setting requires extension restart to effect definition provider](https://github.com/BetterThanTomorrow/calva/issues/397)
- [Fix Go to Definition and Peek Definition not working on Windows 10 when using WSL](https://github.com/BetterThanTomorrow/calva/issues/132)
- [Fix Highlight extension settings are uninitialized if no Clojure editor active on activation ](https://github.com/BetterThanTomorrow/calva/issues/401)
- [Fix Overly aggressive paredit in REPL window](https://github.com/BetterThanTomorrow/calva/issues/255)
- [REPL window use it own set of paredit hotkeys and these are not configurable](https://github.com/BetterThanTomorrow/calva/issues/260)
- [Fix Completion in REPL window should work like in the editor](https://github.com/BetterThanTomorrow/calva/issues/394)
- [Fix bugs in comment form selection](https://github.com/BetterThanTomorrow/calva/issues/374)
- [Use of undeclared var in REPL window resets the namespace](https://github.com/BetterThanTomorrow/calva/issues/257)
- [Remove warning that extensions use the `vscode-resource:` scheme directly](https://github.com/BetterThanTomorrow/calva/issues/391)
- [**Support Jack-in without file open for single-rooted workspace**](https://github.com/BetterThanTomorrow/calva/issues/366)
- [**Show argument list of fn**](https://github.com/BetterThanTomorrow/calva/issues/238)
- [Make code more robust in case Jack-in task fails](https://github.com/BetterThanTomorrow/calva/issues/367)
- [Fix dimming out of stacked ignored forms](https://github.com/BetterThanTomorrow/calva/issues/385)
- [The extension should specify the default schemes for document selectors](https://github.com/BetterThanTomorrow/calva/issues/368)
- [Connect warnings and errors as popups](https://github.com/BetterThanTomorrow/calva/issues/356)
- [Don't remove default indents when Calva is not the auto-formatter](https://github.com/BetterThanTomorrow/calva/pull/383)
- [**Support for custom project/workflow commands**](https://github.com/BetterThanTomorrow/calva/issues/281)
- [Insourcing @tonsky's Clojure Warrior, now named Calva Highlight](https://github.com/BetterThanTomorrow/calva/pull/362)
- [Update pprint status bar when configuration changed](https://github.com/BetterThanTomorrow/calva/issues/358)

### Documentation and promotion

I wrote a [small blog post](https://clojureverse.org/t/it-is-super-easy-to-hack-on-calva-why-dont-you-try-it-out/4953) about the streamlined, and friendly, Calva contribution process. This had me start thinking about the mess that was supposed to pass for user documentation. It got me to:

- Move user documentation from the wiki to: https://calva.readthedocs.io/
- Change [the repo wiki](https://github.com/BetterThanTomorrow/calva/wiki) to be about Calva development only.~

## October 16-31

Theme: _Quality through issue hunting_

As the Calva user base grows (which is currently happening) the influx of [issues](https://github.com/BetterThanTomorrow/calva/issues) is also growing. During the funding period we have been able shrink the total issues count from near the 100s down to, at the time of this writing, 62. This even though close to 150 issues where opened during the same period! The last two weeks of October continued with the issue-squashing.

Also, with help from Michiel Borkent, aka [@borkdude](https://github.com/borkdude), creator of [clj-kondo](https://github.com/borkdude/clj-kondo), we managed to bring the Clojure linting story for VS Code users down to:

– _”Install the [Calva Extension](https://marketplace.visualstudio.com/items?itemName=betterthantomorrow.calva).”_

🎉 Even for Windows users. 🥂

### Changes

- Add setting for wether to open REPL Window on connect or not
- [Re-open REPL windows where they were last closed](https://github.com/BetterThanTomorrow/calva/issues/300)
- Lexer performance considerably improved. Fixes [this](https://github.com/BetterThanTomorrow/calva/issues/228) and [this](https://github.com/BetterThanTomorrow/calva/issues/128))
- [REPL colours and logo a bit toned down](https://github.com/BetterThanTomorrow/calva/issues/303)
- Removed `useWSL`configuration option because the use of Calva is fully supported through the [Remote - WSL](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-wsl) extension.
- [Add commands for interrupting the current evaluation as well as all running evaluations](https://github.com/BetterThanTomorrow/calva/issues/237)
- [Calva asks for user input when `stdin` needs it (e.g. `read-line`)](https://github.com/BetterThanTomorrow/calva/issues/377)
- Command for clearing the REPL history reworked and now also ”restarts” the REPL window.
- Commands are now added to REPL window history only if they are not identical to the previous command on the history stack.
- [Fix floating promises in evaluation module](https://github.com/BetterThanTomorrow/calva/issues/411)
- REPL Window Evaluation errors now initially hide the stack trace. The user can show it with a click.
- [Stop linting, start bundling clj-kondo](https://github.com/BetterThanTomorrow/calva/issues/423)
- [Fix hang when user input is requested](https://github.com/BetterThanTomorrow/calva/issues/377)
- Upgrade to `cider-nrepl 0.22.4`
- [Add info box for VIM Extension users](https://github.com/BetterThanTomorrow/calva/issues/396)
- [Fix undefined namespace when starting a shadow-cljs cljs REPL Window ](https://github.com/BetterThanTomorrow/calva/issues/115)
- [Make opening the REPL window on connect async](https://github.com/BetterThanTomorrow/calva/issues/399)
- [Fix shadow-cljs menuSelections for Custom Connect Sequences](https://github.com/BetterThanTomorrow/calva/issues/404)

### Documentation

In an effort to help potential contributors to Calva figure out the project, I wrote a sort of a manifest: [The Tao of Calva](https://github.com/BetterThanTomorrow/calva/wiki).

We have also worked on closing some gaps in the developer documentation, adding these articles to the wiki:
- [Code Style Guidelines](https://github.com/BetterThanTomorrow/calva/wiki/Coding-Style)
- [Smoke testing](https://github.com/BetterThanTomorrow/calva/wiki/Smoke-Testing)
- [Testing VSIX packages](https://github.com/BetterThanTomorrow/calva/wiki/Testing-VSIX-Packages)

### Other

This report concludes the Clojurists Together 2019 Q3 sponsorship of Calva. We will probably apply again, sometime soon, Calva is far from finished!

Meanwhile, a few days ago I got my GitHub Sponsors profile approved, so now you can [sponsor me](https://github.com/sponsors/PEZ) there. Please do!


# CIDER

* Shipped CIDER 0.23
* Shipped cider-nrepl 0.22.4 (fixes several bugs)
* Shipped orchard 0.5.3 (fixes a small bug with ClojureDocs)
* Shipped piggieback 0.4.2 (it doesn't blow up in the absence of ClojureScript)
* Shipped nREPL 0.7.0-alpha2 with support for sideloading (https://nrepl.org/nrepl/design/middleware.html#_sideloading)
* Participated in "The REPL" :-)
* Published a couple of CIDER-related blog posts (https://metaredux.com/posts/2019/10/05/hard-cider-project-specific-configuration.html and https://metaredux.com/posts/2019/10/07/hard-cider-navigating-cider-buffers-ninja-style.html)

* More details on CIDER 0.23 - https://metaredux.com/posts/2019/10/08/cider-0-23-lima.html

* Launched "The State of CIDER" survey - https://metaredux.com/posts/2019/11/02/state-of-cider.html
* Wrote a couple of "Hard CIDER" blog posts - https://metaredux.com/posts/2019/11/07/hard-cider-hard-restart.html and https://metaredux.com/posts/2019/11/02/hard-cider-understanding-the-jack-in-process.html
* A bunch of small CIDER improvements as documented here https://github.com/clojure-emacs/cider/blob/master/CHANGELOG.md#master-unreleased Most compatibility with Emacs 25 has been restored and the debugger's menu and keybindings are configurable.
* Cut a new Orchard release which fixes a bug with empty namespaces (https://github.com/clojure-emacs/orchard/pull/76). Cross-project collaboration for the win! :-)
* Published cider-nrepl's API - https://docs.cider.mx/cider-nrepl/nrepl-api/ops.html
* Misc improves to CIDER and cider-nrepl's doc site
