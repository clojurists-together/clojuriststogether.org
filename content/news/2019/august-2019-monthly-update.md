---
title: August 2019 Monthly Update
date: "2019-09-10T00:00:00+12:00"
type: "post"
author: Rachel Magruder
summary: "August was our first month with this new round of projects, check out their monthly update!"
---

August was our first month with this new round of projects, check out their monthly update!

- [Shadow CLJS](http://shadow-cljs.org) with Thomas Heller
- [Meander](https://github.com/noprompt/meander) with Joel Holdbrooks
- [Calva](https://github.com/BetterThanTomorrow/calva) with Peter Strömberg
- [CIDER](https://cider.mx) with Bozhidar Batsov



## Shadow CLJS

Released shadow-cljs versions 2.8.42 up to 2.8.51.
Released shadow-cljs versions 2.8.52

### Upgraded `:target :react-native` support

Changed the way the `:react-native` build target emits code so that during development source maps work in some limited fashion (ie. only when using Debug JS remotely in Chrome). This is meant to work around the [limitation](https://github.com/facebook/metro/issues/104) that metro itself currently has regarding source maps directly.

Enhanced the code processing so that `js/require` calls are automatically detected and don't require any additional configuration. More info in the [related discussion](https://clojureverse.org/t/upgrading-the-react-native-support/4669) on Clojureverse.

Added the groundwork to allow code-splitting to work with `react-native` builds so that parts of the code can be loaded lazily when needed (to boost initial startup speed). The JS side of things is done but it also requires work on the `react-native` side as described [here](https://facebook.github.io/react-native/docs/performance#ram-bundles-inline-requires). Hope to create an actual working example of this in the future. **I am looking for beta testers willing to dive into this.**


### Bugfixes/Features

- Smoothed out some rough edges in the nREPL code which would not display compiler warnings when loading files via `load-file` or `require` properly
- Simplified the nREPL middleware setup, mostly for internal purposes. Will only require updating the middleware config when using [embedded mode](https://shadow-cljs.github.io/docs/UsersGuide.html#_embedded_nrepl_server)
- Enhanced the macro reload logic: previously macro dependencies would not be watched and therefore would only trigger rebuilds when macros themselves were changed. This should now be more reliable since more namespaces are tracked
- Introduced [:js-package-dirs](https://shadow-cljs.github.io/docs/UsersGuide.html#alt-node-modules)
- Optimized the CLI parsing so everything coming after run|clj-run was passed to that directly instead of being interpreted as arguments for shadow-cljs itself (eg. `shadow-cljs run your.helper/fn --foo` would error out). Previously this required adding an additional `--` separator after `run`
- removed log spam if CLJ macro namespaces were renamed or deleted
- [ongoing] cleaning up the internal REPL implementation

### Documentation

- Started a section about [Publishing Libraries](https://shadow-cljs.github.io/docs/UsersGuide.html#publish) using Leiningen
- Started a section about [REPL troubleshooting](https://shadow-cljs.github.io/docs/UsersGuide.html#repl-troubleshooting)
- Added a brief section about [:jvm-opts](https://shadow-cljs.github.io/docs/UsersGuide.html#jvm-opts)
- Blogged about [Hot Reload in ClojureScript](https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html)


## Meander

Much of the work I've been doing is getting the new version of Meander, epsilon, ready for release. This has involved numerous internal architectural changes, bug fixes, and documentation additions.

One of the most important changes to the library was to qualify (namespace) the core pattern matching and pattern substitution operators such as `and`, `or`, `scan`, etc. Prior to epsilon these operators were available via their unqualified symbols. This led to some friction for folks. From now forward, beginning with epsilon, these operators will be fully qualified and available from the namespace `meander.epsilon`.

With qualified operators I also further developed a syntax extension mechanism which allows for controlled extension of the pattern matching and/or substitution syntax. This will also be available from the `meander.epsilon` namespace as `defsyntax`. This macro allows new symbolic, syntax to be defined.

With the syntax extension and definition mechanism in place, I was able to begin working on real documentation. Because the `defsyntax` macro compiles to code which emits a `defn`, which in turn compiles to code which emits a `def`, we can attach real meta data such as `:doc` and `:arglists` to the vars. So now, not only can we define operators via the provided extension mechanism, we can also document them. And that's exactly what I spent a good deal of time doing. To date, I've fully documented all but two operators and I expect those will be documented during the week.

Apart from the operator qualification and documentation efforts, Meanders epsilon branch also received several bug fixes to the improvement of the pattern matching compiler. Meanders Clojure spec definitions have also been given attention and mistakes preventing the use of instrumentation have now been corrected.

Another small but (hopefully) useful addition is the allowance of the search operator to return results in breadth first order. Prior to this option, results were always returned in depth first order. I anticipate the further allowance of configuration to search in the future.

Within the week I plan to release Meander epsilon. During the weeks ahead I intended to aggressively enhance the pattern matching and substitution compilers, put in place infrastructure for guides and tutorials, and begin putting together a rewrite specific compiler if time permits.

Since the last update the epsilon branch of the project has seen a few important improvements and fixes. The two primary improvements have been to pattern matching and to substitution.

For map pattern matching, keys can be logic variables if Meander can determine that the pattern could not have more than one solution. For example, if we know that we have an already solved for a logic variable earlier on in a pattern match, it may be used as a key in the map. This improvement also had transitive effects to our code generation for searching as well. Previously, if a map had logic variables keys we emitted search code regardless. This meant that in some cases we were producing code that was doing more work than necessary.

For substitution, code generation has also been improved as Meander is employing a different approach. We're now emitting much smarter, faster, and in some cases (dramatically) shorter code. To do this, the substitution compiler collects and returns data as it compiles so that at each point in the compilation there is an accumulation of knowledge which can be used to make better decisions. The generated code is also subsequently optimized by rewriting it to eliminate redundant code by applying functional identities.

## Calva

### August 1 - 15

Theme: _Project Maintainability_

This has many aspects, including: _Cooperation with other tool smiths, Code factoring, User documentation, and Ease of use._

#### Orchard task force
Since CIDER and Calva are sharing a lot of the tooling, we have been trying to figure out how to pool our efforts into [The Orchard](https://metaredux.com/posts/2018/11/09/ciders-orchard-the-heart.html). Bozhidar Batsov has formed a task force with people building libraries and tools for, and/or using, Orchard related things. Kevin and I have joined from the Calva team and we now try to apply an **Orchard first** mindset when designing how to add certain features.

* So far, a concrete improvement to Calva out of this is that when helping to test a new version of `cider-nrepl`, which adds code completion of JavaScript interop powered by [suitable](https://github.com/rksm/clj-suitable), we realized that Calva isn't taking full advantage of the completion features provided. Next release of Calva will:
  * **NEXT RELEASE** Use top level form context when collecting code completion candidates_.

#### Documentation

* The [Calva wiki](https://github.com/BetterThanTomorrow/calva/wiki) has gotten some needed attention. (All too little still, but anyway.)
* A [video showing how to Jack in using a Custom CLJS REPL](https://www.youtube.com/watch?v=a2vRDYXDAug). This was mainly an experiment to try to learn some about how to produce tutorial videos.
* Some features are being added **documentation first**, meaning we write the user documentation for the feature first and use that to know what needs to be done. See below for an example. We intend to continue with this habit.

#### Refactoring Calva
We're preparing for making some major cleanings up of the Calva code base. And even while we have yet to start any of those efforts we are already cleaning up while we are working with updating Calva. This means the project is more maintainable now than it was two weeks ago, and we intend to continue in this spirit of Kaizen. A more drastic change is that we:
* **DONE**: Moved all REPL Window code into the Calva repository and archived the `repl-interactor` repository for now.

#### Jack in/Connect
We've continued with the strong focus on making it easy to connect Calva to your Clojure project.

* **RELEASED**: https://github.com/BetterThanTomorrow/calva/blob/master/CHANGELOG.md#2028---01082019 Operating system peculiarities. Especially Windows, with its cmd.exe and Powershell and Git Bash and MINGW and WSL and… You get the picture. Jack-in has been really hard to get working reliably, but I think we got it nailed down now. (There is still some bugs on Linux, that we have yet to figure out.)
* **RELEASED**: [Reworked how Calva deals with figuring out the current project root](https://github.com/BetterThanTomorrow/calva/pull/275). This used to be a setting in the workspace, but now it is determined based on the file in the active editor. This change also made it easier to use multi-project workspaces (Mainly because of supporting custom settings per project in the workspace).
* **RELEASED**: [Support launching with Leiningen aliases](https://github.com/BetterThanTomorrow/calva/pull/279), while also improving how we deal with `main` arguments when launching with `deps.edn` aliases.
* **IN PROGRESS**: [Support for launching with user aliases/profiles](https://github.com/BetterThanTomorrow/calva/pull/289). A test build with this feature is out and it is probably going to be release within a few days.
* **IN PROGRESS**: Support of custom Jack-in and connect sequences. This is taking the recently added _Custom CLJS REPL_ feature several steps further. It is done in a [documentation first](https://github.com/BetterThanTomorrow/calva/issues/282) manner. This also cleans up a lot of all too complicated code as we are redefining the built-in connect sequences using the same configuration mechanism as the custom ones.
* **IN PROGRESS**: [Custom workflow Clojure commands](https://github.com/BetterThanTomorrow/calva/issues/281). This is a feature inspired from Cursive that has been worked with on and off for quite a while. Right now it is more *on* and it might be released before August ends.

#### Refactoring tooling
We've investigated our options for adding refactoring tools.

* [Using `nrepl-refactor`](https://github.com/BetterThanTomorrow/calva/pull/269) to add a *Clean namespace form* feature is going to get included in Calva soon. But *Find symbol references*, seems a bit farther away yet. It is being discussed within that Orchard task force.
* [Experiment with `clojure-lsp](https://github.com/kstehn/clojure-lsp-client). Which reveals that a lot of functionality is ready to be tapped by Calva from [clojure-lsp](https://github.com/snoe/clojure-lsp)

#### Various
* **DONE**: Calva Legacy was removed from the VS Code Extension Marketplace, along with Calva Paredit and Calva Formatter. Maintainability bliss.
* **RELEASED**: Quite a few bug fixes.


## August 15 - 31

Theme: _Project Maintainability - through ease of use_

#### Jack-in and Connect Sequences

We want to try help contribute to the community such that fewer Clojure and ClojureScript projects out there need to specify their editor tooling dependencies and setup. To this end Calva has a Jack-in feature that supports the most common project types and cljs repl types. It also supports customizing the cljs repl configuration so that projects with special cljs types can use Jack in as well. But there are still too many projects out there not supported by Calva Jack in, and this is what we have been working on almost solely.

We are adding something we call Connect Sequences, more on that below. When this is released we will be able to say we enabled the following:

* Support for configuring custom connect sequences. These can be:
  * Clojure only, supporting [Leiningen](https://leiningen.org/), [Clojure CLI](https://clojure.org/reference/deps_and_cli), and [shadow-cljs](http://shadow-cljs.org).
  * Clojure + ClojureScript, supporting [Figwheel Main](https://figwheel.org/), shadow-cljs, Nashorn, [lein-figwheel](https://github.com/bhauman/lein-figwheel), and fully customized cljs repl types.
* Much improved custom cljs repl configuration.
* Improved help for the user to get the cljs repl connected.
* Improved ClojureScript _Build switching_. (Since Calva only allows for two REPL connections (yet), one for Clojure and one for ClojureScript. It instead makes it easy to attach the ClojureScript repl connection to different ClojureScript builds in your project.)
* A feature to add custom Clojure code to be evaluated when the Clojure repl has been attached. Maybe your project needs to start a web server before attempting to start a web client and connect a ClojureScript repl?
* All built in ClojureScript repls are written using the custom repl type configuration. (This is a huge win when it comes to maintainability.)
* Nashorn added as a built in ClojureScript repl type.
* [Support for launching with user aliases/profiles](https://github.com/BetterThanTomorrow/calva/pull/289)

Except for the last one, this all works in development, and we are cleaning up the code and considering to add this additional feature, before release:

* **No-prompting Jack-in**. A way to ”answer” all Jack-in prompts as part of the custom Connect Sequence configuration.

The custom Connect Sequence feature is developed [documentation first](https://github.com/BetterThanTomorrow/calva/issues/282). And you can follow the commits in [this PR](https://github.com/BetterThanTomorrow/calva/pull/285). (The reason there is awfully little feedback there, is because that happens offline).

#### Other

Other than jack-in, time has been spent mostly in support and general talking to users. Plus:

* **RELEASED** Use top level form context when collecting code completion candidates_.
* **DONE** Insourced the REPL Window (npm) repository into the Calva code base. Since Calva is the only project using it. This might be extracted again if someone turns up willing to maintain it as a project of its own.
* **DONE** Refactored the updated completion context some, to make the code easier to understand.
* **IN PROGRESS**: [Support for launching with user aliases/profiles](https://github.com/BetterThanTomorrow/calva/pull/289). Test results in and it seems to be a useful implementation. However, we are now moving this configuration into the new Jack-in and connect sequences feature. (See above.)

## CIDER

* CIDER now provides improved code completion for ClojureScript (powered by the suitable library)
* Orchard now provides access to data from ClojureDocs
* I've started replacing the Grimoire integration with ClojureDocs integration
* I've cut a new release of Drawbridge that adds support for custom http headers
* I've removed the hard Clojure dep from sayid and refactor-nrepl
* I've reorganized a bit some namespaces in the most recent beta of Orchard, so it's easier to understand their purpose
* sayid's legacy landing page was converted to a README and the content was improved
* I've investigated a thread leak issue in nREPL and some Java 9+ compatibility issues in CIDER
* We've kicked off a Dev Tooling for Clojure task force with some other tool writers with the goal to collaborate better with each other.
- released cider-nrepl 0.22
- released CIDER 0.22
- released Orchard 0.5
