title: September 2018 Monthly Update
date: 
draft: true
type: post

## Clojurists Together news

September was a successful month. We have new project updates and results from our quarterly survey. Don't forget, CT is currently accepting applications for our 4th Quarter [Call to Proposals](https://www.clojuriststogether.org/open-source/). 

Check out this great video presentation from Martin Klepsch on [_Documenting the Clojure/Script Ecosystem._](https://www.youtube.com/watch?v=mWrvd6SE7Vg)

Thanks to all of our members who support Clojurists Together. It's thanks to your generous support that we can do this.

## cljdoc updates

Hey dear ClojuristsTogether crew! This is [Martin](https://twitter.com/martinklepsch) with a quick update on cljdoc.

September has been a somewhat slow month. I've been focused on
preparing my ClojuTRE talk about cljdoc the first half of September
and was mostly offline — on a sailboat — the second half of
September. You can watch [the talk on
YouTube](https://www.youtube.com/watch?v=mWrvd6SE7Vg). I'm also
thanking all of you at the end of the talk.

Despite me being absent
[some](https://github.com/cljdoc/cljdoc/pull/116)
[PRs](https://github.com/cljdoc/cljdoc/pull/117) were opened &
merged. Thanks to [Travis McNeill](https://tavistock.github.io) and
[Saskia Lindner](http://www.saskialindner.com) for that. It's great to
see people contributing and I hope to grow the cljdoc community
further over the next months.

In the same vein Saskia and I are putting together a cljdoc hackday in
Berlin on Thursday 11th October from 2pm, join the `#cljdoc` Slack
channel on [clojurians.net](http://clojurians.net) for details.

Things that have have been shipped in September:

- A toggle to view raw docstrings ([PR #117](https://github.com/cljdoc/cljdoc/pull/117))
- A first iteration at what may become an interactive article TOC, currently just showing what section you're in ([PR #116](https://github.com/cljdoc/cljdoc/pull/116))
- OpenGraph meta tags (cljdoc links should render much nicer on Slack, Twitter & co)
- An [issue](https://github.com/cljdoc/cljdoc/issues/113) with UTF-8 article slugs has been fixed
- Cleanups in various places of the code removing unused code
- Improvements to the way the classpath is constructed for analysis ([commit](https://github.com/cljdoc/cljdoc/commit/422f4636167d3534a9b636faf3d5c2ca7fa04eeb))
- A bug with links in offline docs has been fixed ([commit](https://github.com/cljdoc/cljdoc/commit/125f4f6c6ccd0e93e3c89bd44834e16248f2d55d))

After ClojuTRE and my vacation I'm feeling energized to work on cljdoc
again in October. Priorities will be the integration of examples and community building.

**If you want to help cljdoc with 5 minutes of your time:** [add a
badge](https://github.com/cljdoc/cljdoc/blob/master/doc/userguide/for-library-authors.adoc#basic-setup)
to your project's Readme. In order to achieve the vision I outlined in
my [ClojuTRE talk](https://www.youtube.com/watch?v=mWrvd6SE7Vg) the community needs to be aware this thing exists —
and that's not something I'll ever be able to achieve on my own.

So point people to cljdoc and — if you're feeling particularly excited — tweet
or write a blogpost about it.

Thanks for your support <3

## Shadow CLJS updates

Release of shadow-cljs versions to 2.6.7 to 2.6.8

### UI Work
Here's a [preview video](https://clojureverse.org/t/shadow-cljs-ui-preview/2826) of the shadow-cljs UI. I've also tweaked the UI to display warnings better.

### Minor changes
- Added support for [:parallel-build false](https://github.com/thheller/shadow-cljs/commit/99741e3edd07ef8ba8a20e5fc3e2e0cad14051ad), `true` by default but wasn't configurable before.
- Copied [clojure.core.specs.alpha](https://github.com/thheller/shadow-cljs/commit/0416ea27e9a031c4a39c49df820855aa4b72575c) and adjusted them for CLJS. Clojure 1.10 alpha renamed a few specs and broke the direct use that was previously used.

Release of shadow-cljs versions to 2.6.9 to 2.6.10

### UI Work
- [WIP] Implemented basic REPL support in the UI
- [WIP] Implemented basic standalone Launcher using Electron

### Bugfixes
- fixed a [REPL issue](https://github.com/thheller/shadow-cljs/commit/f6694aaa5459591556a5e83f939885b70924d3b0) related to require and browser reload
- added support for [overriding npm resolves](https://github.com/thheller/shadow-cljs/commit/a61eea6d7f74fb7d0d806bc030442cf554ab5a24) to exclude packages from a build
- fixed a [race condition](https://github.com/thheller/shadow-cljs/commit/f321b390d52b69bf89e4568cf096a8d51e04575c) related to async loading the web-related namespaces
