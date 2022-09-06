---
title: "Q3 2022 Funding Announcement"
date: 2022-09-06T09:30:00+00:00
summary: Clojurists Together is funding 11 projects for Q3 2022. These projects are Maria.cloud, ClojureDart, mathbox-cljs, Clj-kondo and related, Clojure Data Cookbook, Kaocha and related, Biff, Clojupedia, Exo, portal and cljfx.
author: Alyssa Parado, Maria Geller
---

Clojurists Together is very excited and happy to announce that for Q3 2022 we are funding a total of 11 projects:

**USD 9,000**
* [Maria.cloud](https://www.maria.cloud/)
* [mathbox-cljs](https://github.com/mentat-collective/mathbox.cljs)
* [Clojure Data Cookbook](https://github.com/scicloj/clojure-data-cookbook)
* [ClojureDart](https://github.com/Tensegritics/ClojureDart)
* [clj-kondo, babashka, SCI, nbb, scittle and related](https://github.com/borkdude)
* [Kaocha and related projects](https://github.com/lambdaisland/kaocha)


**USD 2,000**
* [portal](https://github.com/djblue/portal)
* Exo (unreleased)
* [cljfx](https://github.com/cljfx/cljfx)
* [Biff](https://github.com/jacobobryant/biff)
* [Clojupedia](https://github.com/clojupedia/main)


# Q3 2022 Projects
Each project's plans are detailed below.


## [Maria.cloud](https://www.maria.cloud/)

**Grantee**
Matthew Huebert

**Why is this project important to the Clojure community?**

Maria combines carefully written curriculum with a clean, no-install editor. It is designed to introduce complete beginners to fundamental ideas of programming, with an editing environment that encourages REPL-driven development and structural editing from day one. It has been widely appreciated as a good tool for introducing Clojure to beginners and used at several workshops like ClojureBridge. 

We think it's important to keep Maria up-to-date/maintained to prevent users (especially beginners) from encountering bugs while using the tool, to support the latest versions of Clojure, and to enable additional feature development down the road.

**What are you wanting to achieve with this funding?**

We built Maria 5 years ago and would like to bring it back into active development, starting with a refactor/simplification of the core of the editor so that we have a good base to build on top of. 

Top priorities would be (1) replace the selfhost compiler with sci for a more lightweight runtime, (2) replace the code editor with CodeMirror 6 using the cm6 clojure mode I wrote last year for Nextjournal, (3) upgrade to latest version of ProseMirror, (4) add a "publish" feature so that people can share what they make with the world. This funding will not cover all of the work listed above, so we'll also be looking for funds from elsewhere. This would be the first funding we have ever taken for this project.


## [mathbox-cljs](https://github.com/mentat-collective/mathbox.cljs)

**Grantee**
Sam Ritchie

**Why is this project important to the Clojure community?**

Python could never have succeeded in the data science space without matplotlib and other tools that made Jupyter a lovely environment for data exploration. Mathematica's built-in 2d and 3d viewers are also essential for that platform's success in mathematical work.

For data analysis and 2d work, Clojure has Vega and Vega-Lite bindings... but there is nothing analogous in the community for 3d visualization and animation, certainly not at the level of abstraction (declarative scenes!) that Mathbox provides.

Mathbox is best-in-class, and only recently with mathbox-react is its power available without mastering a mutable API. Making this power available to the Clojurescript community will make the language a serious contender for top choice on anything requiring 3d visualization.

**What are you wanting to achieve with this funding?**

**Background**

This project aims to make the incredible Mathbox project to the community of artists, researchers, students and developers using Clojurescript.

Mathbox is a React-like system for declaratively building [three.js](https://github.com/mrdoob/three.js/) scenes full of interactive, animating mathematical objects. Steven Wittens built Mathbox in 2015 (launch post: https://acko.net/blog/mathbox2/) and used the system to build interactive explorables like "How to Build a [Julia Fractal](https://acko.net/blog/how-to-fold-a-julia-fractal/)".

I believe that Mathbox is the most stunning option in the JS world for writing mathematical visualizations. Some examples of work done via Mathbox include:

- an interactive 3d graphing calculator: https://www.math3d.org/
- "Introduction to Linear Algebra" executable textbook: https://textbooks.math.gatech.edu/ila/
- Kinetic Graphs declarative language: https://kineticgraphs.org/

Unfortunately Mathbox as built in 2015 isn't compatible with the package-based infrastructure that Clojurescript requires.

To address this, I adopted Mathbox last summer and spent the next six months converting the project from Coffeescript to Javascript, upgrading its 30kloc codebase to ES6-compatible packages, upgrading the dozens of existing examples, getting the project onto a proper Github Actions CI (and doing the same for two dependent projects, Shadergraph and Threestrap).

I then worked with Christopher Chudzicki to build React wrappers over the full zoo of Mathbox primitives: https://github.com/christopherChudzicki/mathbox-react

Finally, I was able to hook the new CLJS-compatible Mathbox into sicmutils (https://github.com/sicmutils/sicmutils), the Clojure(script) computer algebra system that I maintain, to demonstrate the ability to live-code mathematical visualizations, including realistic physics animations, from a REPL.

**Proposed Scope**

I'm applying for funding to create the following stack of projects, designed to make Mathbox easy to use from any Clojurescript project, or within any of the various Clojurescript computational notebooks (Clerk, Clojupyter, the scicloj.ml) or rich REPLs (Gorilla REPL, Reveal).

Clojurescript's data visualization tooling is lovely to use and garnering more attention. The tools in that space deserve a rich, idiomatic library of primitives for mathematical and statistical visualizations, built in a lovely, component-based way.

These two projects are already complete and managed by me:

- `mathbox`, which I resuscitated (already released)
- `mathbox-react`, by Chris (released)

Next I'll be creating the following layers:

- `mathbox.cljs`, which will expose all mathbox-react components via Reagent, fill in all documentation and smooth over JS-related warts in the component API. mathbox.cljs will also ports of the dozens of examples that live in the Mathbox repo. I've built this out with React's Storybook, so that each example becomes a tutorial on its own: https://mentat-collective.github.io/mathbox.cljs.

- `sicmutils-mathbox`, a library of higher-level components that allow you to add primitives from sicmutils like manifolds, polynomials, derivatives of polynomials, complex numbers etc directly to a Mathbox scene, plus patterns for writing algorithmic visualizations

`sicmutils-clerk`, a library of viewers for the Clerk (https://github.com/nextjournal/clerk) project. Clerk allows you to drive visualizations from a JVM process, so hooking together Clerk and mathbox.cljs will provide tooling and patterns for users looking to do full-stack data visualization. 

I will use the funding to create and ship these projects and provide best-in-class documentation and examples.

**Why?**

In addition to the community benefit, I need these tools for my Executable Textbooks project. I am currently building out executable versions of Sussman and Wisdom's "Structure and Interpretation of Classical Mechanics" (https://github.com/mentat-collective/sicm-book) and "Functional Differential Geometry" (https://github.com/mentat-collective/fdg-book). I want these books to use standardized components in the community, instead of feeling like hand-rolled boutique projects that no one can copy.


## [portal](https://github.com/djblue/portal)

**Grantee**
Chris Badahdah

**Why is this project important to the Clojure community?**

Portal already has some traction in the Clojure community because it provides an easy way to generically inspect Clojure/ClojureScript data structures. By making it easier to add custom viewers, users will be empowered to visualize their own data and solve their specific problems.

**What are you wanting to achieve with this funding?**

A common question I get around Portal is how to add custom viewers. Currently, Portal supports extensibility via SCI. However, it's not very well documented or easy to use. I would like to provide users with documentation on extensibility and a proper REPL (tty, socket-repl and nREPL) to make it easier to extend. 


## Exo (unreleased)

**Grantee**
Will Acton

**Why is this project important to the Clojure community?**

In my experience building front end applications, the majority of time spent managing global state is fetching and caching data. It is a concern that 99% of all application developers have to deal with, and the current ecosystem in ClojureScript has a hole that I think this library would fill.

**What are you wanting to achieve with this funding?**

**What**

Exo (working name, may change) is a ClojureScript library for fetching and caching data using EQL. It builds on top of the fundamental ideas behind Fulcro, Apollo-client, and Relay, while providing an easy to use interface for developers to adopt it a la carte into their existing front end applications.

It exposes two React hooks that can be used in any ClojureScript application built on React:
- use-query: fetch the result of an EQL query from the server, tracking the state of the request, and update the component it's used in if any of the entities that are returned in the result change from any other fetches or mutations
- use-mutation: send a mutation to the server, with the option of optimistically updating the cache, and update the component it's used in with the status and result of the mutation

These two hooks, combined with the excellent pathom library, would allow developers to leverage the power of pathom in their any React applications in a similar way that Fulcro does if you go all-in.

On the other hand, it has a different philosophy from Fulcro. Components can subscribe to any query, even the same query or multiple queries, rather than coupling queries to components directly. This allows a flexible architecture for application developers and eases adoption. This is very similar to the way that Apollo-client and the latest version of Relay work with GraphQL.

**Why**

In my experience building front end applications, the majority of time spent managing global state is fetching and caching data. It is a concern that 99% of all application developers have to deal with, and the current ecosystem in ClojureScript has a hole that I think this library would fill.

Currently, if you are building a new React application in ClojureScript today, you have three options:
1. Use re-frame and write a lot of events, subscriptions, and either build yourself or kludge on some library for data fetching effects
2. Use Fulcro, which provides a great (if scarily large) API if you go all in, or a second class experience if you try to use the "raw" API a la carte
3. Use a JS library like react-query or Apollo-react, which requires a good knowledge of JS interop and has some tricky corners

This library would provide a 4th option, which would be a similar library that JS developers get with react-query and Apollo-react, but in native ClojureScript with EQL and Pathom at its heart.

**How**

The secret sauce of the library is the combination of a normalized cache that can be queried via EQL (and mirrors your pathom backend) with the learnings I have made with other experiments trying to build a caching layer that supports React Suspense. It builds on top of another of my libraries, Pyramid (https://github.com/lilactown/pyramid), which is a library for normalizing data into a map and querying it using EQL.

I have already started building Exo. Currently the caching layer is done, including notifying listeners when any entities that their query depends on changes, and the "use-query" hook. There are a few important things that I want to finish before v1.0 release
1. use-mutation hook
2. Stable API for configuring the connection to a pathom backend
3. A solution for "data masking." See https://relay.dev/docs/principles-and-architecture/thinking-in-relay/
4. A plugin API for things like refetching on focus and other more advanced behaviors
5. Documentation

**Goals & Expectations**

If I am awarded the 3 month funding, my goal would be to complete the use-mutation hook, have a stable API for configuring with a pathom backend, and a solution for data masking.
Sketches for the plugin API for refetching and garbage collection may be done, but I won't commit to them at this time.

Documentation and example projects will also be made available for the things considered "done" at the end of this 3 month period, and an alpha release will be made to collect feedback.


## [Clojure Data Cookbook](https://github.com/scicloj/clojure-data-cookbook)

**Grantee**
Kira McLean

**Why is this project important to the Clojure community?**

The Clojure data science community is growing quickly and there is currently no “central” reference for how to do simple data science tasks with existing libraries and tools, only a proliferation of disconnected posts, articles, and documentation. I want to create a resource that demonstrates how to use the main tools that the community is coalescing around to accomplish basic data science tasks with Clojure.

The outline is already drafted (https://github.com/scicloj/clojure-data-cookbook/blob/outline/outline-draft.md) and has been discussed with the community. The book will include a brief introduction to Clojure, basic data science operations like data input, output, and data manipulation, as well as more advanced topics like data visualization and statistics.

Several of the libraries mentioned in the most recent Clojurists together survey are becoming key components of the scicloj ecosystem, and although many do have excellent API docs, not all do and either way it’s important to also document them in the context of solving actual problems. Some libraries that were mentioned and would be used in the book include:
- tooling
    - oz
    - portal
-  data visualisation
    - vega/vega-lite
- data processing
    - tablecloth
    - tech.ml.dataset
    - dtype-next
    - meander
- linear algebra
    - Neanderthal
- probability/statistics
    - fastmath
There are other libraries that would also be used, including hanami, kixi.stats, inferme, clerk, and scicloj.ml.

This project would build upon prior efforts in the community like the [SciCloj Clojure Data Science Handbook](https://github.com/scicloj/scicloj-data-science-handbook) and complement current efforts to put together a [data science course for Clojurians](https://scicloj.github.io/docs/community/groups/ds4clj/). There is [great interest](https://clojureverse.org/t/a-data-science-course-for-clojurians-are-you-interested/9074) in this content, and this book will be essential as a companion study guide to the course.

It will also complement my work co-organizing the visual tools study group in the scicloj community. One of the main topics we discuss is figuring out an approach to literate programming in Clojure. Many of the tools showcased there are relevant to the underlying technical implementation of how we’ll deliver the book. The goal is for it to be very much a living document that stays relevant beyond the first few months after release, with updatable, executable/copy-paste-able, and tested examples. This example-based approach to teaching builds upon my experience organizing and teaching workshops, such as the one I gave about [data visualization last November](https://www.youtube.com/watch?v=C3kwcAJWJmE).

The book, along with the course, will be an essential resource bridging the gap between data science practitioners who are new to Clojure and Clojure developers new to data science. The long term vision is to place Clojure and the scicloj ecosystem as a top choice for data scientists, alongside R and Python. For this to happen, clear and easy-to-follow guides need to exist and this book would be one step toward that end.

**What are you wanting to achieve with this funding?**

I would like to start writing the contents of the book, completing at least the core chapters (i.e. excluding data viz and statistics) this year and continuing with the project into next year. The outline is drafted and I have been discussing it and working on getting buy-in from other members of the Clojure data science community this year. If I get this funding I will start taking an afternoon or a day off per week from my full time job to work on the book.


## [cljfx](https://github.com/cljfx/cljfx)

**Grantee**
Vlad Protsenko

**Why is this project important to the Clojure community?**

At ~800 stars, this is my most popular Clojure project on GitHub. Cljfx has 45K downloads on Clojars, where latest significant version (1.7.19) has 4.7K downloads. While desktop java apps are quite niche, they have their use and cljfx-powered desktop apps exist.

**What are you wanting to achieve with this funding?**

The developer experience of cljfx has some issues:
- what are the allowed props for different javafx types is not clear and requires looking it up in the source code;
- what are allowed javafx type keywords requires looking it up in the source code;
- errors when using non-existent props are unhelpful.
- generally, errors that happen during cljfx lifecycle are unhelpful because the stack traces have mostly cljfx internals instead of user code

I want to improve that by creating:
- specs for cljfx descriptions so they can be validated with descriptive errors;
- dev-time tooling for looking up available props and their expected values — at least in the REPL, maybe as a UI builder if that works out;
- dev-time cljfx component tree inspector similar to browser's html element inspector that will contextualize ocurring errors in terms of user components.


## [ClojureDart](https://github.com/Tensegritics/ClojureDart)

**Grantee**
Christophe Grand and Baptiste Dupuch

**Why is this project important to the Clojure community?**

- targeting native desktop and mobile with Flutter

**What are you wanting to achieve with this funding?**

- better dev experience: better hot reload, LSP, kondo, maybe REPL


## [Biff](https://github.com/jacobobryant/biff)

**Grantee**
Jacob O'Bryant 

**Why is this project important to the Clojure community?**

The latest Clojurists Together survey mentioned that "Documentation, Tutorials and Beginner experience" and "a popular web development framework" are both areas in need of support. Biff addresses both of these directly.

The Biff community is still small, but we do have a few regulars, like Jeff Parker who is already a core contributor to Biff's flagship example project (https://github.com/jacobobryant/platypub/commits?author=jeffp42ker). 


**What are you wanting to achieve with this funding?**

I want to write a bunch more documentation. Currently we only have reference docs. I want to add:

 - a series of tutorials that have you build a forum + real-time chat application (like Discourse and Slack in one)

 - a page that curates/recommends resources for learning Clojure and getting a dev environment set up. Aimed at those who are brand new to Clojure and want to use it for web dev.

 - a series of tutorials/explanatory posts that teach the libraries Biff uses. Each tutorial will have readers implement some web dev functionality without using Biff (like HTML rendering), after which they'll be shown how to do it with Biff. (Spoiler: the tutorials will secretly have readers implement all the helper functions that Biff provides--by the end, readers will have implemented all of Biff from scratch.) This is intended for those who prefer a bottom-up approach to learning.

Along the way I'll also restructure the website (https://biffweb.com), while studying https://documentation.divio.com/.

This is a long-term project and will not be finished within the next three months, especially since I'll be doing it in tandem with my other Biff work. But I'll publish the posts on a rolling basis. With the Clojurists Together funding, I'd hope to at least complete the forum tutorials.


## [clj-kondo, babashka, SCI, nbb, scittle and related](https://github.com/borkdude)

**Grantee**
Michiel Borkent

**Why is this project important to the Clojure community?**

Babashka, clj-kondo and related were mentioned in reply to "Are there any particular libraries, tools, or projects that are important to you that you would like to see supported?".

Clj-kondo is a Clojure linter that is used by a wide variety of individual users and companies (https://github.com/borkdude/clj-kondo/blob/master/doc/companies.md).

Babashka is a scripting environment that can execute a significant subset of JVM Clojure programs with instant startup. It is used by a individual users and companies (https://github.com/babashka/babashka/blob/master/doc/companies.md). It is currently my project with the most stars on Github.

SCI is the Clojure interpreter powering babashka and several other projects (see https://github.com/borkdude/sci#projects-using-sci).

Nbb is gaining traction (currently 500 stars, being used as viable Clojure option on AWS Lambda).

I'll probably also work on more experimental libraries like cherry (tiny CLJS transpiler which directly outputs ES modules).

**What are you wanting to achieve with this funding?**

**Clj-kondo**
- .cljc configuration/refinements: e.g. support for ClojureDart and :bb conditionals
- numerous other issues, there's always plenty to do: See the project board:
  https://github.com/clj-kondo/clj-kondo/projects/1 and see here for a list of most upvoted issues: https://github.com/clj-kondo/clj-kondo/issues?q=is%3Aissue+is%3Aopen+sort%3Areactions-%2B1-desc
- As clj-kondo is the foundation for the static analysis in clojure-lsp:
  improvements on those

**Babashka**
- Integrate the new babashka.cli library into babashka (including usage with tasks)
- bb install: install script straight from a remote repository and make it available as a globally installed tool (bb install foo && foo)
- babashka.http-client library: a library to consolidate the existing http clients in babashka
- improve documentation in book.babashka.org
- addressing issues of several built-in libs in bb: babashka.cli, fs, process, etc.

**SCI**
- Error messages improvements (https://github.com/babashka/babashka/issues/1322)
- Interop performance improvements
- Improvements to sci.async (async evaluation in CLJS)
- Make keyword argument functions accept maps in CLJS (catch up with clojure 1.11)

**Scittle**
- Take advantage of async SCI evaluation (see above) to be able to load JS
  libraries from CDNS using dynamic impport

**Nbb**
- nbb.edn to declare dependencies from clojars
- built-in string interpolation (a la JS)


## [Kaocha and related projects](https://github.com/lambdaisland/kaocha)

**Grantee**
Arne Brasseur / Gaiwan

**Why is this project important to the Clojure community?**

Kaocha has become the default way to run tests, and better test tooling means less friction when working on tests, which means more and better testing, and thus better software.

**What are you wanting to achieve with this funding?**

We'd like to use this budget to give some much needed attention to our testing-related tools and libraries, in particular:

- kaocha
- kaocha-cljs
- kaocha-cljs2
- kaocha-cucumber
- deep-diff2
- kaocha-junit-xml
- kaocha-cloverage
- facai
- faker
- chui
- funnel
- funnel-client

Most of these have been around for a while, and have found widespread adoption in the community. They are working and stable, but most of them have not seen much attention in the last few years. Issues and maintenance tasks have piled up, and many ideas have been brought up on how to improve these tools, which we haven't had a chance to explore. All of them are in need of better and more complete documentation.

We (Gaiwan) have funded Alys Brooks over the last 1~2 years to help with the maintenance of Lambda Island libraries, in particular Kaocha, but only for a few hours per week. Our plan with the $9k budget would be to match it, partly with funding from our Open Collective, and partly directly by Gaiwan, to get to a budget of $18k. With that money we'd fund the equivalent of a full-time developer over the course of 4 months, spread across a few different people on the Gaiwan team.

It’s hard to say what exactly we’ll manage to accomplish in that time, but these are some of the areas we’d like to focus on:

- General upkeep
    - triage issues
    - close old/stale issues
    - close or revive stale PRs
    - prioritize remaining issues
    - fix CI and cljdoc builds
- Docs
    - revise / update / expand READMEs
    - expand and reorganize docs based on the 4 types of documentation [https://documentation.divio.com/](https://documentation.divio.com/)
    - document specific usage patterns (Recipes)
        - "test system"
        - rollback db for each test
        - effective use of factories
        - CI setup
- bigger chunks
    - wrap up Kaocha parallelism
    - kaocha-cljs2 convenience layer
        - automate funnel install & launch
        - make the 80% case (shadow+browser+chui+funnel) trivial to set up
    - Get Facai to v1


## [Clojupedia](https://github.com/clojupedia/main)

**Grantee**
Adam Helins

**Why is this project important to the Clojure community?**

It aims to offer visibility and discoverability of the Clojure ecosystem, libraries and miscellaneous tools. A powerful way of having a glimpse at the state of the ecosystem, organized in a knowledge graph ; how projects and libraries are related, linked, all of this complemented by a powerful search engine.

This project is implemented in Logseq (itself written in Clojurescript). The current prototype demonstrates key features: https://clojupedia.github.io/#/page/contents

Please, follow the "About" section and related links which explain what it is, how it works, and how it differs from previous attempts. Other sections, albeit poor in content at the moment, offer a glimpse at what this project will become if a bit of funding can be found to formalize it enough to bootstrap a community effort.

**What are you wanting to achieve with this funding?**

Strenghten the project and evolve it to a community-driven effort.
