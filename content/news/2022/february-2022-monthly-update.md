---
title: "February 2022 Monthly Update"
date: 2022-04-02T03:30:00+08:00
author: Alyssa Parado
summary: Read the first update from one of our 2022 Q1 projects Clojure-LSP, the updates from the remaining 2021 projects: Malli and Dependabot Core, and the updates from our long term projects with Bozhidar Batsov, Michiel Borkent, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov.
draft: true
---

## Clojure LSP

This first month we had one big release with performance and new features.

#### release [2022.02.23-12.12.12](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2022.02.23-12.12.12)

The focus of this release was to make clojure-lsp faster on a lot of places, especially on the post-startup time, which as a summary stands for the time editor/CLI is waiting for clojure-lsp start to then offer all IDE features clojure-lsp has, the most significant improvement was when clojure-lsp analyzed whole project with clj-kondo default linters but is yet linting whole project for unused-public-vars and looking for test trees.
We also had lots of performance improvements in different places since CLI usage using parallelism to code-actions and code completion features, offering a smoother IDE experience.

One big change is that now clojure-lsp, by default, discover the project __source-paths__ from the classpath string and not manually scanning deps/lein files, decreasing a lot the false-positives of wrong source-paths where users would need to manually configure it for more complex projects.

And as a new asked feature, and with the help of clj-kondo, we now have support for [finding implementations of defprotocols](https://clojure-lsp.io/features/#find-defprotocol-implementations) following LSP spec.

Here is the changelog of this release:

- General
  - Use `:source-aliases` setting during default deps.edn and lein project-spec aliases, avoiding the need to configure a whole project-spec just because of a additional alias.
  - Exclude from unused-public-var linter vars with metadata `^:export`. #753
  - Fix clean-ns multiple refers sort when there is a alias before the refers.
  - Bump clj-kondo from `2022.01.15` to `2022.02.09` adding support for implementation analysis and more improvements.
  - Medium performance improvement during startup when unused-public-var linter is disabled.
  - Medium performance improvement during startup on unused-public-var calculation parallelizing calculations.
  - Small performance improvement on code actions calcullation.
  - Add `:use-source-paths-from-classpath` setting defaulting to true, which makes clojure-lsp do not manually discovery source-paths but get from classpath excluding jar files and paths outside project-root. #752 #551
  - Improve completion performance when all clojure.core or cljs.core symbols are valid completions. #764, #771 @mainej
  - Fix scenarios where the lint findings in individual files differed from what you'd expect based on the .clj-kondo/config.edn settings.
  - Add `:exclude-regex` and `:exclude-when-definted-by-regex` to `:clojure-lsp/unused-public-var` linter.
  - Bump `org.clojure/clojure` to `1.11.0-rc1`.
  
- Editor
  - Fix exception during code actions calculation when in a invalid code of a map with not even key-pairs.
  - Don't return diagnostics for external files like files on jar dependencies, avoiding noise on lint when opening dependencies.
  - Support finding implementations of defprotocol and references of defrecord/deftype, implementing LSP method `textDocument/implementation`. #656
  - Make the actions and commands aware of when they were invoked from comments or whitespace. This will allow individual refactorings to be more deliberate about how they handle comments and whitespace. #716 @mainej
  - Correctly position the cursor after calling move-coll-entry-down on an entry with leading comments. #758 @mainej
  - Don't return completions when invoked from a comment, avoiding performance problems. #756
  - Fix small anomalies in parameter names of extracted private functions. #759 @mainej
  - Add semantic tokens for protocol implementations methods like defrecord and deftype.
  - Small performance improvevement on code lens calculation using transducers.

- API/CLI
  - Small performance improvement to `format`, `clean-ns`, `diagnostics`, and `rename` via parallelizing parts of the logic.
  - Fix edn->json parser of `serverInfo/raw` for Calva use cljfmt configuration. #763
  - Add `:cljfmt-raw` config to `serverInfo/raw` for Calva. #768
  - Add support for passing specific `--filenames` for most actions. #775





## Dependabot Core







## Malli

by [Tommi Reiman](https://twitter.com/ikitommi)

<img src="https://raw.githubusercontent.com/metosin/malli/master/docs/img/malli.png" width=150 align="right"/>

#### Done Stuff

* Shipped a major version of Malli, the 0.8.0 on Jan 23th! Biggest changes were: dev-tooling support for CLJS, Tools for Destructuring and support for the Plumatic Defn Syntax. Also the following smaller improvements: 
  * `malli.dev/start!` uses `malli.dev.pretty/reporter` by default
  * allow `:malli/schema` to be defined via arglist meta-data, [#615](https://github.com/metosin/malli/pull/615)
  * **BREAKING**: local registries with schemas in vector syntax are stored as identity, not as form
  * **BREAKING**: `:malli.provider/tuple-threshold` has no default value
  * FIX: `me/-resolve-root-error` does not respect `:error/path`, [#554](https://github.com/metosin/malli/issues/554)
  * FIX: `m/from-ast` does not work with symbols or unamespaced keywords, [#626](https://github.com/metosin/malli/issues/626)
  * FIX: `:+` parsing returns vector, not sequence
  * transformer names can be qualified, schema properties support `:decode` and `:encode` keys
  * updated dependencies
  * `malli.dev.pretty/explain` for pretty-printing explanations

<img src="https://raw.githubusercontent.com/metosin/malli/master/docs/img/pretty-explain.png" width=800>

* new `malli.destructure` ns for parsing Clojure & Plumatic destructuring binding syntaxes, see [Destructuring](https://github.com/metosin/malli#destructuring).

```clj
(require '[malli.destructure :as md])

(-> '[a b & cs] (md/parse) :schema)
; => [:cat :any :any [:* :any]]

(-> '[a :- :string, b & cs :- [:* :int]] (md/parse) :schema)
; => [:cat :string :any [:* :int]]
```

* new `malli.experimental` namespace with schematized `defn`, automatically registers the functions schemas with `m/=>`.

```clj
(require '[malli.experimental :as mx])

(mx/defn kakka :- :int
  "inline schemas (plumatic-style)"
  [x :- :int] (inc x))
```

* Released also small patch releases (0.8.1 - 0.8.3) with bug fixes and small improvements
* Read and reviewed all open PRs and managed to close most of them

#### TODO

Nothing, I'm happy with the things I managed to complete during the 6 month period despite the priorities changed during it. Looking at the original list:

1. First-class Schema inferring and effective schema types
  * **MOSTY DONE**: it's not first class but orders of magnitude faster with lot's of new features like inferring `:tuple`, `:map-of`, `:enum`, type-hints and inferring via decoding. I'm happy with the current state.
3. Re-visit Schema registries, lifecycle, caching and contexts
  * **DONE**: the registry can be swapped by default, workers are cached on instances etc
5. Error messages and tooling integration: pretty-printing of schema creation
  * **MOSTY DONE**: new tools for pretty explaining, more robust EDN printer etc

Extra stuff that was added:
1. SchemaAST - parseless method of creating Schemas
2. LiteSyntax - optional simple syntax for tools like [reitit](https://github.com/metosin/reitit)
3. Schema Creation Performance - massive improvements
4. Dev-tooling - improvements to clj, support for cljs (kudos to [Dan Vingo](https://github.com/dvingo)), clj-kondo support

#### Future of Malli

There would be so much to do, need to figure out a sustainable way to work on those. These include:

* Better support for Dates [#49](https://github.com/metosin/malli/issues/49)
* 2-way transformation with TypeScript [#656](https://github.com/metosin/malli/issues/656)
* Simplified Types [#264](https://github.com/metosin/malli/issues/264)
* On fixing Recursive Generation [#452](https://github.com/metosin/malli/issues/452)
* 2-way transformation for JSON Schema, [#54](https://github.com/metosin/malli/issues/54)


Cheers.





## Bozhidar Batsov






## Michiel Borkent






## Dragan Djuric

Since a dedicated project proposal for RNN support in Deep Diamond has been accepted by CT (Mar-Apr-May),
I got an unexpected opportunity to create and work on a new project as part of this funding: Clojure Sound.

The main objective during January and February was to learn the fundamentals of sound processing and
communicating on control devices on the JVM, and create an initial version of full blown Clojure library
that is capable of serious work in this area. So far, I have achieved this in code:

    - Full initial Clojure-friendly coverage of Java Sound API
    - Extracting common protocols for MIDI and Sampled parts of Java Sound into a core package
    - Clojurising the MIDI namespace quite a lot
    - UX layer for the MIDI standard that goes further than Java Sound
    - Full code examples for a large part of the Java MIDI tutorial, in the form of fully working tests.
    - Acquired and experimented with several hardware MIDI-compatible controllers

Although the motivation might be unclear to many Clojurists - who needs _sound_ stuff in Clojure - I predict
that this will be a very interesting and attractive project to many Clojurists, beginners and veterans alike.
First of all, sound-related stuff is interesting, so it's a good hobby coding and experimenting platform.
As such, it's a good choice for demonstrating various code techniques while avoiding boring topics.
Next, it's great for beginners to give them a topic that they know and like, music, as a playing ground
for learning the topics that they don't (yet) understand (Clojure and programming in general).
Further, it covers things only incidentally related to music: communication with external hardware
controller devices, which are often created for musicians in mind, but can be utilized in ingenious
ways in general computing, with their abundance of knobs, slider, pads, and other convenient control methods.
Last (but there will be more), digital signal processing is one of fundamental domains where high performance
computing is applied. This will be an excellent demonstration platform for showing off Clojure's HPC
computing capabilities. Who knows, maybe we'll even be able to build something that kicks-ass. Even if we
don't it will be an excellent platform for many Clojurists to make the first steps in DSP and HPC, and may
even attract some people to look into Clojure.





## Thomas Heller

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

Current shadow-cljs version: 2.17.5 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

Notable Updates

- Fixed a couple Compatibility issues encountered when updating to the latest Closure Compiler release.
- Rewrote most of the npm resolve code to match `webpack/node` more closely. Wrote a test suite that verifies against the current `enhanced-resolve` package (which is used by `webpack`). Also laid the foundation to support `package.json` `"exports"`, which will become essential once more packages start adopting it.
- Adjusted the `:as-alias` implementation to match the updated CLJ implementation (eg. allowing non-loading `:as-alias` in combination with other loading attributes, such as `:as` or `:refer`).
- Added a [shadow-remote](https://github.com/thheller/shadow-cljs/blob/master/doc/remote.md) nREPL bridge. This is meant for editor implementors giving more access to shadow-cljs data and APIs over existing nREPL connections. If you are interested please [reach out](https://github.com/thheller/shadow-cljs/issues/990).





## David Nolen

  * various CLJS tickets
  * fix issues with npm dependencies with package.json exports
    (firebase 9.X was affected in particular)
  * 1.10.914 release
  * 1.11 work started
    * :as-alias
    * port CLJ-2603
    * support in progress port of cloure.math





## Nikita Prokopov

Hi, this is Niki Tonsky and I continue writing a lot of Clojure! Progress has been steady on all fronts:

[Humble UI](https://github.com/HumbleUI/HumbleUI/)

- Layout in a good shape
- First demo apps built: [Calculator](https://github.com/HumbleUI/HumbleUI/blob/e0a0d5689dc8f08147a4eee6b369393f91d88518/dev/examples/calculator.clj) and [Wordle](https://github.com/HumbleUI/HumbleUI/blob/e0a0d5689dc8f08147a4eee6b369393f91d88518/dev/examples/wordle.clj)
- Blogged about progres here:
  - [Decomposition](https://tonsky.me/blog/humble-decomposition/)
  - [Layout](https://tonsky.me/blog/humble-layout/)
  - [Developer Experience](https://tonsky.me/blog/humble-dx/)
- Streamed [Wordle development](https://www.youtube.com/watch?v=qSswvHrVnvo)

Wordle implementation takes \~200 loc, which is very decent in my opinion. Especially considering that API is very rough and not designed for ease of use yet.

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed)

- Finally available through Package Control
- Support indentation and formatting rules from [Better Clojure formatting](https://tonsky.me/blog/clojurefmt/)
- Pretty print & expand long returned values
- Bug fixes & PRs

[DataScript](https://github.com/tonsky/datascript/)

- 6 new releases (patches and PRs)

[Uberdeps](https://github.com/tonsky/uberdeps)

- 3 new releases

[JWM](https://github.com/HumbleUI/JWM)

Helped contributors merge lots of PRs:

- macOS: change titlebar style by @mworzala
- macOS: set window progress by @mworzala
- macOS: represented filename by @mworzala
- macOS: work around -XstartOnMainThread by @mworzala
- Windows: focus events by @i10416
- X11: poll-based event loop by @dzaima
- X11: mouse buttons by @dzaima
- X11: focus events by @dzaima
- X11: input methods by @dzaima

[Skija](https://github.com/HumbleUI/Skija)

- Upgraded Skia to m100
- Bug fixes & PRs

People started expressing genuine interest and even build stuff in both Skija, JWM and Humble UI, which is a fantastic sign!




