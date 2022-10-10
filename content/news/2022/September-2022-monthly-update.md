---
title: "September 2022 Monthly Update"
date: 2022-10-10T16:30:00+08:00
summary: Read updates from Tablecloth and the final updates from our Q3 2021 long term projects with Bozhidar Batsov, Michiel Borkent, Dragan Djuric, Thomas Heller, and Nikita Prokopov.
author: Alyssa Parado
published: true
---

## Tablecloth

This is the update for my "Column for Tablecloth"
project, which has been generously funded by [Clojurists
Together](https://clojuriststogether.com/). The goal of this project --
explained in more detail in my [first
update](https://humanscode.com/columns-for-tablecloth-i) -- is to give
the `column` a presence within the data processing library
[Tablecloth](https://github.com/scicloj/tablecloth). In other words, by
the end of the project, users of Tablecloth should be able to interact
exclusively with a `column` using functions that generally take a column
and return a column: `(column argx argy) => column`.

Over the past three months, I developed some conversation about the
project within the online Clojure and
[Scicloj](https://twitter.com/scicloj) communities and on a piece of
unexpected work: the datatype API for Tablecloth columns. These two
efforts haven't necessarily overlapped, but they've certainly been
mutually reinforcing. I'll start by discussing the work I've done on
datatypes.

### Checking Datatypes on the API

The work on datatypes came out of my [first
PR](https://github.com/scicloj/tablecloth/pull/71) for this project,
which established the new `tablecloth.column.api` namespace and added a
few key functions to get things rolling. One of the functions I added
was `typeof` -- inspired by R's function of the same name -- that
returns the data type of the elements in the column. E.g.:

    (typeof (column [1 2 3]))
    ;; => :int64

It turned out that the story about types in Tablecloth, unbeknownst to
me, was a bit more complicated. Unlike tech.ml.dataset, Tablecloth has a
type hierarchy ([defined in
tablecloth.api.utils](https://github.com/scicloj/tablecloth/blob/master/src/tablecloth/api/utils.clj#L56-L67)).
So how should `typeof` work with this hierarchy? Should `typeof` return
the child or "concrete" types? (e.g. `:int64`) or the "general" type
(e.g. `:integer`)?

After [some
discussion](https://github.com/scicloj/tablecloth/discussions/73) and
experimentation we settled on the following:

-   `typeof` will return the concrete type; and
-   `typeof?` can confirm the concrete or general type

So:

    (def mycol (column [1 2 3]))

    (typeof mycol)
    ;; => :int64

    (typeof? mycol :int64)
    ;; => true

    (typeof? mycol :boolean)
    ;; => false

    (typeof? mycol :integer)
    ;; => true

    (typeof? mycol :logical)
    ;; => false

That the focus is on the concrete type with an ability to check the
general type follows the tendency visible in both Python's Numpy and in
R. In both, checking the datatype will yield the concrete type. Both
also have type hierarchies. In Numpy, there's a somewhat clunky function
`issubdtype` that can be used to check the parent-child relationship; in
R, you can ask: `is.numerical` or `is.logical`, etc.

We accomplish something similar to R with just the `typeof` and
`typeof?` because we can ask about the concrete type with
`(typeof? col :int64)` and the general type with
`(typeof? col :numerical)`. So for now this is the type syntax we
settled on.

The most interesting design decision was whether or not to return the
concrete type or the general type when querying the type of the column.
It is true that this is the behavior of at least two of the main data
processing libraries. So we are in good company. In conversation, Jon
Antony (author of the visualization library
[Hanami](https://github.com/jsa-aerial/hanami), among other things) gave
this further reason for choosing the concrete type:

> FWIW, count me in for returning the concrete type as well. At root
> this stuff is (or should be) about performance and when I'm checking
> types in this context I always want the concrete type. Some extra
> 'higher level' stuff might be nice, but likely won't be used
> much.[^1^](https://humanscode.com/columns-for-tablecloth-ii#fn1){#fnref1
> .footnote-ref}

I like this reasoning because it is based in a sense of what is useful
in practice. Another reason that occurred to me is that, if we ask for
the type and get back the general type, we are actually throwing away
information. The column elements' types have a concrete type that
provides more information about what they are and how they are stored in
memory. Why throw that away by default?

If the user wants to know the general type of the elements columns, we
will let them ask about that like so:

    (->general-types (typeof (column [1 2 3])))
    ;; => #{:integer :numerical}

That is where we've landed so far on the datatype API. Please if you
have any comments or questions, reach out and let me know what you
think.


## Bozhidar Batsov

Here are the highlights from my Clojure work for the past couple of months:

- [CIDER's 10th year anniversary](https://metaredux.com/posts/2022/07/10/cider-turns-10.html)
- [inf-clojure 3.2.0](https://github.com/clojure-emacs/inf-clojure/releases/tag/v3.2.0)
- compliment 0.3.14 (fixes broken Clojars deployment)
- [clojure-mode 5.15](https://github.com/clojure-emacs/clojure-mode/releases/tag/v5.15.0)
- [nREPL 1.0](https://metaredux.com/posts/2022/08/25/nrepl-1-0.html)
- [CIDER 1.5](https://github.com/clojure-emacs/cider/releases/tag/v1.5.0)
- Small documentation improvements to most projects
- I also wrote a few Clojure blog posts for the first time in ages
	- https://batsov.com/articles/2022/07/31/clojure-tricks-replace-in-string/
	- https://batsov.com/articles/2022/07/31/clojure-tricks-zipping-things-together/
	- https://batsov.com/articles/2022/08/01/clojure-tricks-number-to-digits/
- I also wrote about [Leiningen adding support for nREPL's Unix sockets](https://metaredux.com/posts/2022/08/25/nrepl-1-0.html)

As usual there are also many things that are in the works and are yet to be released. 





## Michiel Borkent

### [ClavaScript](https://github.com/clavascript/clavascript)

This is a new project: a CLJS syntax to JS compiler for the niche use
case where you want to write JS, but do it using CLJS syntax and tooling
instead. ClavaScript comes with a standard library that resembles CLJS
but is built on bare JS ingredients. As such, Clava comes with the usual
JS caveats, but we can still have our parens and enjoy a slim bundle
size!

### [Cherry](https://github.com/clavascript/cherry)

Cherry is similar to ClavaScript, but it does emit CLJS-compatible code
(with the persistent data structures, etc). The compiler code is almost
identical to Clava\'s, but with a few tweaks here and there. E.g.
`{:a 1}` in Clava means: a JS object with a `"a"` key and `1` value, but
in cherry, `{:a 1}` means the same as in CLJS. The goal of both Clava
and Cherry are to reduce friction between CLJS and JS tooling. Both
projects should be considered experimental for now. Challenges in both
Clava and Cherry is the REPL, since both projects compile to ES6 modules
and ES6 module imports are immutable.

On [ClojureDays 2022](https://clojuredays.org/) I will give a talk
titled \"ClojureScript reimagined\" which will shed more light on both
projects.

### [Scittle](https://github.com/babashka/scittle)

Execute Clojure(Script) directly from browser script tags via SCI. See
it in [action](https://babashka.org/scittle/).

Scittle received two new plugins: one for `promesa.core` and one for
`cljs.pprint`. Also error messages were improved.

### [Babashka toolbox](https://babashka.org/toolbox/)

Babashka toolbox is a port of [Clojure
toolbox](https://www.clojure-toolbox.com/) and gives an overview of
bb-compatible libraries and projects.

### [Babashka CLI](https://github.com/babashka/cli)

Turn Clojure functions into CLIs!

Several new options have been added: `:validate`, `:require`,
`:restrict`. Also error handling was made more flexible.

Babashka CLI proper is now part of babashka. Also see my blog posts
about it:

-   [Babashka tasks meets babashka
    CLI](https://blog.michielborkent.nl/babashka-tasks-meets-babashka-cli.html)
-   [Babashka CLI: turn Clojure functions into
    CLIs](https://blog.michielborkent.nl/babashka-cli.html)

### [Babashka](https://github.com/babashka/babashka)

Native, fast starting Clojure interpreter for scripting.

-   Compatibility with
    [malli](https://github.com/metosin/malli#babashka)
-   `-x`: a way to execute functions from the command line using
    babashka CLI
-   Many bugfixes and enhancements

### [Nbb](https://github.com/babashka/nbb)

Scripting in Clojure on Node.js using SCI

-   A new
    [bundle](https://github.com/babashka/nbb/tree/main/doc/bundle#bundle)
    command to bundle nbb scripts to standalone scripts, which can then
    be processed further with e.g. `esbuild` to minify and tree-shake
    them.
-   `nbb.edn`: you can now declare dependencies within this file, e.g.
    from Clojars and nbb will add them automatically to the classpath,
    so you can `require` them.
-   [Malli](https://github.com/metosin/malli) compatibilty
-   Many small bugfixes and improvements

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)

Static analyzer and linter for Clojure code that sparks joy

### [Bebo](https://github.com/borkdude/bebo)

Run Clojure scripts on [Deno](https://deno.land/) via SCI. I\'m not
exactly sure how useful this is to the wider Clojure community, but I
got curious about deno and decided to give this a go.

### [Quickblog](https://github.com/borkdude/quickblog)

Light-weight static blog engine for Clojure and babashka

A lot has been happening in this project, with the help of Josh Glover.
Check out the
[changelog](https://github.com/borkdude/quickblog/blob/main/CHANGELOG.md#changelog).
The blog you\'re currently reading is made with quickblog.

### [SCI](https://github.com/babashka/sci)

Configurable Clojure interpreter suitable for scripting and Clojure
DSLs.

This is the workhorse that powers babashka, nbb, bebo, and many other
projects.

Several bugfixes and enhancements were made in the last two months.

### [Neil](https://github.com/babashka/neil)

A CLI to add common aliases and features to deps.edn-based projects.

Neil now has a `new` subcommand which defers to
[deps-new](https://github.com/seancorfield/deps-new). Also `neil test`
was added to run tests using the Cognitect-labs test runner. Much thanks
to [rads](https://github.com/rads) who contributed a lot.

### [Process](https://github.com/babashka/process)

Clojure library for shelling out / spawning subprocesses

Minor updates and fixes.

### [Fs](https://github.com/babashka/fs)

File system utility library for Clojure.

Minor updates and fixes.

### [Pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy)

A babashka pod around buddy core (Cryptographic Api for Clojure).

The latest release adds wrappers for `buddy.sign.jwt` and provides an
`aarch64` binary.

See
[changelogs](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#20220803).

### [Dynaload](https://github.com/borkdude/dynaload)

The dynaload logic from clojure.spec.alpha as a library

This library was made compatible with nbb.

### [Deps.clj](https://github.com/borkdude/deps.clj)

Upgrades and minor fixes.

### [Sci.configs](https://github.com/babashka/sci.configs)

A collection of ready to be used SCI configs

Moved `cljs.pprint` config from nbb into this project.


## Dragan Djuric

For this period, I have a pretty good stuff to show: I've produced a fully working RNN implementation on CPU and GPU!
It was somewhat harder than I had hoped, but I've put some extra work to it and I can finally say that Deep Diamond
supports recurrent neural networks. And even better, for the user, everything is automatic: the user only needs
to put something like (rnn [128]) at the desired place in the network description! I've also written a tutorial
on RNN that showcases a Hello World example from start to finish.

I won't dwell too much on the details other than notice that this big milestone for Deep Diamond coincides
perfectly with the end of this CT funding round.

Besides that, I continued my long-term efforts to learn about music and sound and develop Clojure Sound.
In this period, I mainly used already released Clojure Sound to make a Clojure program for ear training.
I've started a series of blog posts that explain every detail of its development, and showcases
Clojure Sound's features for dealing with MIDI hardware.

Software produced in this period:

- Deep Diamond, 2 releases (0.24.0 0.25.0)
- Clojure Sound, 1 release (0.3.0)
- Neanderthal, release (0.45.0)
- ClojureCUDA, release (0.16.0)

Blog posts published (and several other sound related in the pipeline):

- Clojure Sound 4 - Ctrl Left Pedal
- Clojure Sound - 5 - Double Click with Foot Control
- Recurrent Networks Hello World in Clojure with new Deep Diamond RNN support on CPU and GPU





## Thomas Heller

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

Current shadow-cljs version: 2.20.1 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

Notable Updates

- Made Java 11 the minimum supported version. The Closure Compiler did this, so it kinda forces us to go along. The current LTS version is Java 17, so I think this was overdue anyways.
- Some optimizations for [:target :esm](https://shadow-cljs.github.io/docs/UsersGuide.html#target-esm) and finally made it an officially supported (and documented) build target.
- Added a protection against running shadow-cljs server twice in the same project. Running two competing instances will lead to very confusing and hard to debug problems and commonly happened when "jacking in" to projects, while also running `shadow-cljs watch` separately.



## Nikita Prokopov

Final two months of Clojurists Together long-term funding. What a year!

We started in September last year intending to build Clojure-native desktop UI framework, but nothing but ideas at our hands. After several Zoom interviews, discussions, blogging, and A LOT of coding, one year after, we have a working prototype.

It’s far from complete, unfortunately (which is expected, given how enormous the task is), but it’s real, you can touch it, you can play with it, you can build simple stuff in it, and it already feels like magic.

Early adopters report that overall developer experience is way superior to anything web or other frameworks can offer. And it’s in Clojure! With REPL!

So, what exactly happened in the last two months, and where are we now?

### [HumbleUI](https://github.com/HumbleUI/HumbleUI):

- Huge improvements in how exceptions are handled. They don’t bring down your app anymore, instead, a nicely formatted stack trace is printed (with custom clojure-aware formatter, yes)
- Lots of refactorings and fixes in event propagation
- Built-in debug overlay that shows frame and event timings
- Text Field work: undo/redo, compising regions (umlauts/acutes/Chinese/etc), emoji, placeholders, refactoring, cleanup
- Focus system
- Theme system
- Toggle widget
- Couple of cool macros: loopr (idea stolen from @aphyr), match (case on steroids)
- Added Goal, Motivation, Differences, and Architecture to README

### [JWM](https://github.com/HumbleUI/JWM):

- 3 new versions released
- X11: adds setTitleBarVisible by @mgxm
- X11: Fixed mouse scroll amount
- App::openSymbolsPalette
- Window::requestFrame can be called from any thread and even on closed windows
- Report mouse position in EventMouseButton/EventMouseScroll

### [Skija](https://github.com/HumbleUI/Skija):

- Skia version was bumped to m105
- New sampling methods, new Bitmap, and Canvas operations
- It is now possible to shape TextLine with your own iterators (useful for passing different locales and other options)
- Maintenance

### [DataScript](https://github.com/tonsky/datascript):

- 2 new versions released
- Inspired by Christian Weilbach, work has started on durable/lazy-loading DataScript. We are not there yet, but it all seems very doable
- Ben Sless has contributed a lot of small performance improvements to JVM version
- Entity comparison now takes DB identity into account

I also created a new Sublime plugin, which may or may not be useful for Clojure development (I use it every day now and enjoy it a lot): https://github.com/tonsky/Sublime-Executor

Overall, working with Clojurists Together has been a fantastic experience and I’m really excited about what we were able to do together.

Work on Humble UI is not stopping, though. It is now full steam ahead and aiming at its first debut at Dutch Clojure Days this Autumn. Wish me luck!




