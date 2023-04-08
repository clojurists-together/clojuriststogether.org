---
title: "April 2023 Updates: Q1 2023 Projects Report 1"
date: 2023-04-08 T08:30:00+08:00
summary: Updates from Aleph Manifold, Clerk, Clojure_ts-mode, Donut, Exercism, Neanderthal
author: Kathy Davis
draft: true
---

Check out the first updates from developers working on projects funded for Q1 2023.   



## Project Aleph/Manifold: Matthew Davidson
Update 1

### HTTP/2 support

- Draft HTTP/2 ADR published [here] (https://github.com/clj-commons/aleph/blob/feature/http2/adr/adr-001-http2.adoc). Comments welcome in `#aleph` on Clojurians. I'd like to hear what people are hoping for re HTTP/2 in Aleph.
- Initial HTTP/2 support implemented on the client-side. SSL/TLS ALPN is working, Netty pipeline setup is working, input is implemented, and currently debugging codec issue on output side.
  - Long-overdue clean-up of internals to support moving forward.

### Other Aleph changes

- Fixed logging dependency conflict

- Ring validation PR in final review

### Supporting library work

- Brainstorming with Zach Tellman (back from the dead!), Arnaud, and Moritz on how to improve Manifold's executor behavior. No solution chosen yet.

- Working with figurantpp on making manifold deferreds implement CompletionStage

- Worked with bo-tato on debugging a long-standing gloss decode-stream bug

### Community support

- Helped debug figwheel SSL issue with Aleph

- Helped debug let-flow/executor interaction issue

- Helped debug middleware/Websocket issue

- Helped debug tricky connection timeout issue  


## Project Clerk: Martin Kavalar 
Update #1: Issue Gardening 
We took the Clojurists Together funding (which I'm very grateful for 🙏) as an opportunity to do housekeeping in Clerk's issues.
We took inspiration from how José Valim manages to keep Elixir's issue count below the threshold of a [single page on GitHub (25)] (https://github.com/elixir-lang/elixir/issues). 
 
I'm happy that we've been able to [close 49 issues since the beginning of Feburary] (https://github.com/nextjournal/clerk/issues?q=is%3Aissue+is%3Aclosed+closed%3A%3E2023-02-01) and are now also well [below this threshold](https://github.com/nextjournal/clerk/issues). We hope to be able to respond more quickly to issues that folks encounter going forward. 🤞 
We've updated [Clerk's changelog](https://github.com/nextjournal/clerk/blob/17df9a51028b8ab046f3080aba685ee72c8f4257/CHANGELOG.md#unreleased) (it's big!) and we're improving error messages to ease the upgrade process before cutting a release.  
Besides the various smaller fixes, the highlight features of this release will be: 
* 👁 Simplifying Clerk's Viewer API by switching viewer names and render-fns to fully-qualified symbols that support jump to definition add clarity
* 🔌 Support for working offline (once the browser had the chance to cache Clerk's render deps)
* 🌄 `clerk/image` and `clerk/caption` helpers
* 📇  support for `js/import`
* 🐞 Fixing a deficiency in Clerk's dependency analysis that could lead to incorrect caching behavior  
Next up is weaving those changes into the 📖 [Book of Clerk](https://book.clerk.vision) and improving Clerk's support for dashboards. 
Updated March 1, 2023  



## Project Clojure_ts_mode: Danny Freeman   
Update 1
** So far I have working:**
- Basic syntax highlighting
- Support automatic installation
- Simple non-semantic clojure indentation
- Fixed a bug related to comment-region reported by sogaiu
- Included readme instructions for install with `package-vc-install` (new to Emacs 29, pointed out by
- Imenu support
 
** Upcoming work for the rest of the project**
- More work on indentation, support semantic indentation style (like clojure-mode currently does)
- More imenu definitions (types, records, protocols, interfaces, multimethods, etc)
- Listing on MELPA/Non_GNU ELPA
- More testing, basic major mode things I might have missed
- A good readme  
Update March 15, 2023   

## Project Donut: Daniel Higginbotham  
Update 1 

For late January / February I mostly focused on
https://github.com/donut-party/system. The immediate goal is to get it ready for
a 1.0 release. Changes include:

* **Added helpers for documenting and inspecting a system**

  A challenge with working with DI libraries is that, over time, it's easy to
  lose track of what components do, why they're there, and how they relate. I've
  added helper functions that structure present top-level views and
  documentation of a system so that a system is more comprehensible to devs and
  maintainers.

* **Added `with-*system*` macro and testing tools and docs**

  Users brought up that there weren't clear ways to test projects built with
  donut.system. I added the `with-*system*` macro and `system-fixture` function
  to aid testing, and updated docs with testing recommendations.

* **Removed specter**

  Specter was thoroughly woven into the code base. I removed it to lighten the
  compilation size for ClojureScript projects, and to reduce the learning curve
  for contributing to the project.
  
* **Added plugin system**

  One of the goals for donut.system is to provide a foundation for composition,
  a more structured way to provide libraries that others can use to extend their
  system with minimal manual work to wire things up. I've added a plugin system
  to achieve this goal.
  
* **Make component selection independent of signal**
  
  In response to feedback, I've tried to simplify the way component selection works:

  * Disentangle it from signaling
  * Make `select-components` dumber by making it always set the specified
    components; no logic around detecting `::start` signal
  * Add component selection to `system` function. Lets you do `(system
    :system-name nil #{[:env] [:component-group :component-name]}`

  This also seems to be a bit more semantically accurate, in that selecting
  components is more about defining the system you're using than it is about
  signalling

* **Significantly updated README**

  I've made numerous improvements to the README to make it easier to learn how
  to use the library and to cover more use cases, like mocking components.  


## Project Exercism: Bobbi Towers
Update 1

The work proposed included expanding the track syllabus by teaching additional concepts, and improving the automated analysis tooling to provide better feedback.<br> 

As part of the Exercism #12in23 challenge (to learn 12 languages in 2023), I was invited to host Functional February on YouTube and Twitch. So I developed the syllabus in real time, with the live audience representing our most popular type of student, which is one who has skills in some language, but may be completely foreign to functional programming concepts. On one stream we ported an exercise that teaches `map`, `reduce` and `filter`. On another, we built an exercise generator and used it to automate the porting of additional practice exercises using the canonical data from Exercism's problem-specifications repo. The streams have been so much fun we've decided to extend the series with Functional Forever. <br> 

An area of improvement *not* mentioned in the proposal, but certainly valuable is the in-browser editor. While there are those who are most at home in their text editor, the online editor exists for a good reason and the experience should be good enough that should one choose to use it, they won't be missing out on the interactive style of development using the REPL which makes working with Clojure so special. Conveniently the Exercism editor uses Codemirror 6 which has been wrapped by Nextjournal for outstanding Clojure support for structural editing/navigation and inline evaluation. I've prototyped my vision for a more interactive coding experience and plan to help integrate it into the site in the coming months. Rather than add a separate output window, I've opted to show the results entirely inline, but the text will be selectable, and will be cleared when the user presses a key. The evaluation context is powered by SCI, and will pair well with the exercise test runner which runs the unit tests and submits the solution. <br>  

The Clojure representer is responsible for grouping exercise submissions into common approaches. It does this by running the code through a series of normalizations, like formatting, names and macro-expansion. This enables automated feedback comments to be attached to approaches, which is done using the dashboard available to a select group of super-mentors. The representer is functioning well and usage statistics are available on the build status page which at the time of writing reflects that 1637 representer comments have been shown from the 47 feedback submissions I've written so far. <br>  
Udate March 15, 2023.  


## Project Neanderthal: Dragan Duric
Update 1

My goal with this round is to implement Sparse Matrix support in Neanderthal.

I started by exploring the vendors engines and APIs, with the main goal on deciding whether to write
a custom C layer to MKL (as I did for Neanderhtal up to this point), or to use machine-generated JavaCPP MKL bindings (which were not available in the early days of Neanderthal). The current state of JavaCPP's MKL turned out to suit well for Neanderthal's needs, so I decided to try to use these instead of writing C from scratch.

I found a way to use JavaCPP side-by side with my custom C bindings for the existing parts of Neanderthal. (It might be a good thing to completely port Neandrthal to JavaCPPs bindings, but that is a larger task more appropriate for the focused project of its own).

I compiled neanderthal-native to work with recent MKL that is compatible with JavaCPP, which made it more compatible with up-to-date distributions.

I released Neandeathal and neanderthal-native 0.46.0 as a starting point for this project.

Since the support for sparse matrices will be based on JavaCPP, while the rest of neanderthal is still on my custom bindings,
I decided that the sparse parts fit into a new add-on library neanderthal-sparse (https://github.com/uncomplicate/neanderhtal-sparse), which might eventually be merged into Neanderthal.

JavaCPP might be a good base for integrating many native libraries other than MKL, so I decided to create a dedicated library Clojure CPP. It integrates the general parts of JavaCPP into the Clojure ecosystem.

The first version is fairly featureful, and is now available at https://github.com/uncomplicate/clojure-cpp.

Having implemented these general parts, I set to discover how JavaCPP+MKL cobination works, and how to fit it into Neanderthal. So far, I did a port of Neanderthal's CPU vector engines to this new backend.
I identified the glitches and tricky spots, and found the best ways to solve them.

It seems that the stage is ready for the actual sparse vector implementations during the next reporting period, and further ironing out the rough edges. The sparse implementation would follow in the third period, or sooner if possible.

I expect that by the end of this funding round I'll be able to release a fully functional neanderthal-sparse, and clojure-cpp.
