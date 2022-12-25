---
title: "Clojurists Together 2023 Long-Term Funding Announcement"
date: 2022-12-25T14:00:00+12:00
author: Daniel Compton
summary: "Clojurists Together is funding 10 developers $1.5k/month for 12 months"
---

Clojurists Together members have voted to to fund 10 developers $1.5k/month for 12 months (180k USD total!). We're excited to announce the following developers/teams who will be funded:

* Bozhidar Batsov
* Christophe Grand
* Eric Dallo
* Michiel Borkent
* Nikita Prokopov
* Peter Stromberg
* Peter Taoussanis
* Sean Corfield
* Thomas Heller
* Tommi Reiman

We are very excited to see what these developers will do over the next year. They will post updates every two months of what they've been working on, and we'll share them in our regular monthly updates. Here's what each of these developers plan to work on (although this is just a starting point and they're free to change their plans as they see fit):

## Bozhidar Batsov

Provide continued support for [CIDER](https://cider.mx), [nREPL](https://nrepl.org/nrepl/index.html#) and the related libraries and improve them in various directions. Some ideas that I have in my mind:

- Make CIDER play better with alternative Clojure(Script) runtimes
- Simplify some of CIDER's internals (e.g. jack-in, session management)
- Better CIDER documentation and a new tutorial video
- Experiment with a tree-sitter based version of clojure-mode (for Emacs 29+)
- Continue to move logic outside of cider-nrepl (as recently done with the stacktrace analysis functionality)
- Improvement to the nREPL specification and documentation; potentially built some test suite for nREPL specification compatibility
- Various improvements to the nREPL protocol

## Christophe Grand

Continue working on [ClojureDart](https://github.com/Tensegritics/ClojureDart).

## Eric Dallo

I will work developing and maintaing mostly projects related to tooling for Clojure, mainly [clojure-lsp](https://clojure-lsp.io) and related projects like lsp4clj, clj-kondo, emacs-lsp.

I will keep helping and supporting the community in the Clojurians slack channel #lsp along with creating tutorials and documentation to increase clojure-lsp reach.

## Michiel Borkent

Continue work on:

- [clj-kondo](https://github.com/clj-kondo/clj-kondo)
- [babashka](https://github.com/babashka/babashka)
- [nbb](https://github.com/babashka/nbb)
- [SCI](https://github.com/babashka/sci)
- [cherry](https://github.com/squint-cljs/cherry)
- [squint](https://github.com/squint-cljs/squint)

and any other related projects.

## Nikita Prokopov

- Continue developing [Humble UI](https://github.com/HumbleUI), getting it into state where real apps could be developed with it
- Documenting, promoting and teaching Humble UI
- Develop durability for [DataScript](https://github.com/tonsky/datascript)
- Redo Clojure Sublimed parser & smaller improvements
- Contribute nREPL improvements needed for Clojure Sublimed
- Some new Clojure library ideas? Who knows :)

## Peter Stromberg

[Calva](https://calva.io) will continue with its no-roadmap approach, where user feedback about painpoints and opportunities guide where Calva is improved. Expect Calva to be ”A year better” next year. That said, I do want to make Calva treat newcomers to Clojure better, so that will be the main theme.

I will put my 2023 Calva time towards these three main goals:

1. A Getting Started with Clojure experience that only requires VS Code. (The current one requires VS Code + Java.)
2. A Start your First Clojure or ClojureScript Mini-project experience. (The current Getting Started experience leaves the user hanging about this.)
3. Leverage Joyride out of the box for Calva features/configuration.

The main vehicle for creating the Java-free Getting Started with Clojure experience is Joyride, or should I say, SCI, on which Joyride is built.

As for leveraging Joyride more: A lot of Calva functionality could be moved over to Joyride. Porting things to Joyride would make more of this functioinally be implemented in ClojureScript and also make it easier for users to adapt things to their needs. This goal is mainly about putting the infra structure in place to facililtate porting. I think the Java-free Getting Started experience might be a good first candidate for porting.

To help users more fully leverage Joyride we also need to make it easier to use Joyride's REPL in parallell with your Clojure project's REPL, something which Calva does not handle all that well today. Let's name this a stretch goal for 2023.

## Peter Taoussanis

- Finish work on [Carmine](https://github.com/ptaoussanis/carmine) v3 (a major modernized rewrite of the Clojure Redis client).
- General ongoing maintenance + development of my [existing open-source libraries](https://github.com/ptaoussanis).
- Further work TBD later (I have a number of options I'd like to consider + get feedback on).

## Sean Corfield

A while back I resurrected [clojure-doc.org](https://clojure-doc.org) as a Cryogen-powered static site but the content needs a huge amount of work in terms of modernization and this funding would allow me to spend time working on that, with a focus on onboarding beginners, specifically around tooling as a supplement to what’s on clojure.org – providing more of a “cookbook” approach. Some of the work and material might go into clojure.org as part of improving both the deps and CLI guide/reference and the tools.build guide to make it much more beginner-friendly.

I would also like to work on improvements to Clojure CLI tooling that could offer some official solution to the “create a new project” conundrum that my community projects deps-new (and clj-new) currently offer, so that’s another area of tooling and beginner-focused documentation that I could carve out time to work on with this funding.

## Thomas Heller

Maintenance and Improvements for [shadow-cljs](http://shadow-cljs.org), and other more experimental libraries (eg. shadow-grove, shadow-css).

## Tommi Reiman

I'm planning on working mostly with [Malli](https://github.com/metosin/malli), to lead and deliver the most-wanted (and needed) missing core features, including simplified type tagging, improving map schemas, revisiting global configuration options and many more. I'm also keen on exploring better development time tooling, built both with and for malli.

There is also a lot of interesting work to be done on other libs, including reitit, virhe, viesti and talvi, just haven't had any time to focus on those. Shipping reitit 1.0.0 would be awesome.

And who knows, maybe there will be a [juustometsae](https://github.com/metosin/juustometsae) too.


## Thanks

Thank you to all of our members for selecting these developers. If it wasn't for your generous support, this wouldn't be possible. I wanted to add a special thanks to Latacora, Roam Research, Whimsical, Stylitics, AppsFlyer, Pitch, Nubank, Cisco, Logseq, JUXT, Metosin, Solita, Adgoji, Nextjournal, ClojureStream, Shortcut, Flexiana, Toyokumo, doctronic, and 180° Seguros. They have all contributed significant amounts to Clojurists Together which lets us fund $180,000 in long-term funding to Clojure developers.
