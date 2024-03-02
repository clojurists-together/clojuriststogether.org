---
title: "Feb. 2024 Short-Term Project Updates"
date: 2024-03-02T08:30:00+08:00
summary: "CIDER, clojure-lsp, Jank, Scicloj, sitefox, UnifyBio"
author: Kathy Davis  
draft: True


---  
<br>
Our first round of reports are in from some of our Q1 2024 deveopers.  

Benjamin Kamphaus: UnifyBio  
Chris McCormack: sitefox  
Daniel Slutsky: Scicloj  
Eric Dallo: clojure-lsp  
Jeaye  Wilkerson: Jank  
VEMV: CIDER  

## Benjamin Kamphaus: Unify Bio
2024 Q1 Project Report 1. Published 15 February 2024.  

**Accomplished so far:**

- Completed lightweight schema definition and spec, an example of which can be found
  [here](https://github.com/vendekagon-labs/unify/blob/main/test/resources/systems/patient-dashboard/schema/unify.edn)
- Users can now stand up a local system with the
  [plain JSON datalog query service](https://github.com/vendekagon-labs/datomic-query-service) and
  [schema browser](https://github.com/vendekagon-labs/unify-schema-browser) (see image below)
  using docker compose
- Very basic quickstart steps are now included in the updated README
- Added the ability to retract a Unify managed dataset
- Streamed on twitch for a few weeks but paused that, moving all Unify dev to YouTube (see more below).

**For the short term, my goals are:**  
- Prioritize dataset lifecycle management, dataset lifecycle scenario tests, tutorials
- Delay work on additional backends and streaming processing until I hit a stable point re: ^
<img width="1368" alt="schema-view" src="https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/0a5eb26d-14a1-488e-ad3a-bbecefccfba1">
*View of the schema browser, now part of the local Unify system (adapted from alzabo by Mike Travers)

At this point, Unify is in a decent shape as an alpha for prototyping/experimental work.
I’d hold on using it for production, though things are stabilizing pretty quickly.  

**Two ways to make use of Unify that I think would be straightforward for its current state:** 

- For anyone already using Datomic, who wants to import data that is in tabular form, or can be turned into a table,
  they could have a go at
  [adding Unify annotations](https://github.com/vendekagon-labs/unify/blob/main/test/resources/systems/candel/template-dataset/schema/metamodel.edn)
  to their existing schema and writing an
  [import config](https://github.com/vendekagon-labs/unify/blob/main/test/resources/systems/candel/small-reference-import/config.edn)
- For anyone wanting to start working incrementally at data modeling for a new domain, Unify can provide a good
  interactive environment for vetting how data out in the world, e.g. in tables, might map on to your schema, and you can
  visualize the data model and connections between entities as you go with the schema browser.

Edges are sharp and documentation is still limited but more detailed tutorials are coming soon!

If anyone is interested in getting stated with using Unify for data integration, or just importing tables into their Datomic schema, but are a bit wary of jumping into this blind, I'm on the Clojurians Slack as:  
`@Ben Kamphaus`

In the channels:  
`[#unify, #datomic, #data-science, #data-engineering]`

### Notes on Streaming

I started streaming dev on Unify with Twitch but have had some frustrations with the platform
and gotten feedback that YouTube would be better for streaming also, not just collecting videos.
It's not worth going into too much detail on this, mainly I want to point people to the
[YouTube channel](https://www.youtube.com/@VendekagonLabs) if they want to follow along as I
get this aspect of the work going again.

Thanks to Clojurists Together and the Rare Cancer Research Foundation for supporting this work!  <br>

---

## Chris McCormack: sitefox 
2024 Q1 Project Report 1. Published 15 February 2024. 

Hello! The first half of my Clojurists Together funded work on Sitefox is complete. I made around 50 commits to the project since the start of the year and this is a quick summary of the progress I've made.

My broad goal with the Clojurists Together funding is to make it safer and easier for other people to get started and build sites and apps on Sitefox. There are two components to this:<br>
  a) improving documentation and setup tooling   
  b) improving security and stability  

### Updating dependencies

I started the process of updating dependencies with an `npm audit`. This command finds npm packages with known vulnerabilities. I updated the npm package `nodemailer` due to a vulnerability, and I also `express`, `nbb`, and `react`. I also updated Clojure deps Promsea, and js-interop. This was the first part of the dependency updates item.

The second part of the dependency updates item was to replace the deprecated `csurf` module. This Express module is responsible for securing POST requests against CSRF attacks. Express is the web server that Sitefox depends upon and they have decided to no longer provide this module themselves.

Replacing this module turned out to be a bigger task than I anticipated. I started by evaluating several libraries as replacement candidates. Next I created a branch to try out one of these replacements. At first it looked like the library was a good replacement, but it was only upon more rigorous testing that I realized it had serious flaws that made it unsuitable.

This experience made it clear to me that I needed comprehensive testing of the form submission and authentication flows in order to tell if a library was a good replacement and did not introduce any bugs or regressions. This led me to work on the second part of the work.

### End-to-end testing

I spent the greater proportion of the commits after this working on end-to-end tests of basic Sitefox functionality including:

* Tests for basic server functionality - does the web server run, render pages, and respond to requests?
* Tests for the main authentication flows such as signing up, signing in, signing out, forgot password.
* Tests for form submission and field validation, including CSRF protection.
* Tests for CSRF protection on Ajax POSTs.

I managed to get all of these tests implemented and passing except for the final Ajax test which I am still working on. After that I got the tests running headless on every commit via a GitHub action. This will ensure that the most basic functionality Sitefox offers won't be inadvertently broken when I make major changes in future. In particular I can ensure the CSRF protection continues to work after upgrading to a different CSRF library.

### What is next

From my initial plan I have only managed to make progress on these two items. My goal for the remainder of Q1 is:

1. Finish the CSRF Ajax testing.
2. Finish replacing the `csurf` dependency with a well-tested alternative.
3. Update the key-value database module list and filtering code.
4. Move the `npm create` helpers into the main repo, and document them properly.
5. Tutorials showing how to get started building on top of Sitefox.

Given the progress of the first half of the quarter, I have tempered my goals a bit. If I can get these items done that will still provide a solid foundation for people wanting to get started with Sitefox and rely on it to build sites and apps.

You can see the board I am using to track progress here:
https://github.com/users/chr15m/projects/13/views/1

Thanks for your interest and your support!<br>  

---

## Daniel Slutsky: Scicloj  
2024 Q1 Project Report 1. Published 29 February 2024.

February 2024 was the first of three months on the Clojurists Together project titled "Scicloj Community Building and Infrastructure".

Scicloj is an open-source group developing Clojure tools and libraries for data and science.  

As a community organizer at Scicloj, my current role is to help the emerging Scicloj stack become easier and more accessible for broad groups of Clojurians. This is done in collaboration with a few Scicloj members.   

In February 2024, this has been about the following projects:  
### [Clay](https://scicloj.github.io/clay/)  
Clay is a namespace-as-a-notebook tool for literate programming and data visualization developed in collaboration with Timorthy Prately. It is used for documenting [libraries and tutorials](https://scicloj.github.io/clay/#example-projects-using-clay) as well as an analysis tool for dynamic data exploration.  

In February 2024, Clay got five new releases, mostly around the following:  
* improved compatibility of various visual elements with [Quarto](https://quarto.org/)
* improved support for making books
* simplified live-reload experience
* support for [Observable](https://observablehq.com/) visualizations
* additional user-reported bugs and feature requests

Many thanks to generateme for the extensive testing and the numerous issues discovered.  
In March 2024, the plan is mostly to continue user support and move from the alpha to the beta stage.  

### [Noj](https://scicloj.github.io/noj/)  
Noj is a project that collects a few recommended libraries for data and science in a bundle and adds some convenience layers for using them together.

During February 2024, we had some rethinking of this project's scope and goals in discussion with Carsten Behring, who got involved in the project and has made some substantial contributions to its documentation.

My goal has been mostly to review Carsten's work and add some documentation. For March 2024, the hope is to clarify the project's goals and reorganize its relationships with other libraries accordingly.  

### [Clojure Data Scrapbook](https://scicloj.github.io/clojure-data-scrapbook/)  
The Clojure Data Scrapbook is intended to be a community-driven collection of tutorials around data and science in Clojure. 

In February 2024, I added a few tutorials and made some changes to the project structure. For March 2024, the main goal is to clarify the contribution workflow and encourage contributions.

### [Dev and study groups](https://scicloj.github.io/docs/community/groups/)

### [visual-tools](https://scicloj.github.io/docs/community/groups/visual-tools/)

In the visual-tools group, we began [a new series](https://clojureverse.org/t/visual-tools-meeting-21-ggplot-study-session-1-summary-recording/10604) of study sessions around [ggplot2](https://ggplot2.tidyverse.org/) and the grammar of graphics. 

The intention is to implement a similar library in Clojure.

In February 2024, my attention has been around prototyping possible solutions, [exploring ggplot and its internals](https://scicloj.github.io/clojure-data-scrapbook/projects/datavis/ggplot/index.html), and creating a group of people who are interested in learning it. A few datavis-minded Clojurians have gathered a group and started thinking together, [in meeting](https://www.youtube.com/watch?v=6BYObNV2pxk) and [over chat](https://scicloj.github.io/docs/community/chat/).

In March 2024, we will keep learning together. Hopefully, the ideas will be clarified, and some of the group members will start building something.

### [real-world-data](https://scicloj.github.io/docs/community/groups/real-world-data/)

The real-world-data group will be a space for Clojure data and science practitioners to bring their own data projects, share experiences, and evolve common practices.

In February 2024, I restarted the group organization, mostly in one-on-one calls and chats with a few of the people who are planning to join.

In March 2024, we are expecting to have the first meeting.

### Your feedback would help

Scicloj is in transition. On the one hand, quite a few of the core members have been very active recently, developing the emerging stack of libraries. At the same time, new friends are joining, and it seems like soon, more people will enjoy the Clojure for common data and science needs.

If you have any thoughts about the current directions, or if you wish to discuss how the evolving platform may fit your needs, please [reach out](https://scicloj.github.io/docs/community/contact/).  <br>

---

## Eric Dallo: clojure-lsp  
2024 Q1 Project Report 1. Published 29 February 2024.

### January 2024 Update:  

### [clojure-lsp](https://clojure-lsp.io/)  
The main highlight is the new custom project tree feature which shows project source-paths, dependencies, jars and namespaces!
![project-tree](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/f501b1a0-3ffe-41e7-8d69-a35d9a359d55)

<br>  

### 2024.02.01-11.01.59  
- General
  - Fix binary not wokring on some aarch64 linux. #1748
  - Add new `Project tree` feature via the `clojure/workspace/projectTree/nodes` custom method. #1752
  - Fix `--log-path` setting to work with `listen`/empty arg, starting clojure-lsp server and logging properly.

- Editor
  - Fix `didChangeConfiguration` throwing exception. #1749
  - Fix `rename` of ns causing wrong ns names because of duplicate rename actions. #1751
  - Fix `range-formatting` throwing exceptions when unbalanced parens are sent from client. #1758
  - Fix rename functions need to clean up LSP state or restart in some clients implementing LSP method `didRenameFiles`. #1755
  - Fix `thread last all` failed after comment form `#_(...)`. #1745  
  
Besides the clojure-lsp work, I worked too on some related projects that I think are important to improve clojure-lsp visibility and standardization as a tool, for example clojure-repl-intellij which without it make the use of clojure-lsp-intellij less valuable.  

### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

### 1.14.16 - 1.14.17

- Bump clojure-lsp to `2024.02.01-11.01.59`.
- Add shortcuts to backward slurp and barf.
- Add shortcut documentation to all features, check the features doc page.
- Fix Rename feature not being available for some cases.

### [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)

- Support for starting local repl besides the already available remote repl.  

### February 2024 Update  

### [clojure-lsp](https://clojure-lsp.io/)  

The main highlight is the new linter different-aliases helps guarantee consistency across alias in your codebase!  
![different-aliases](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/940cd94d-8714-4d67-90d7-30f204bd45cf)

<br>  

### 2024.03.01-11.37.51  
- General  
  - Bump clj-kondo to `2024.02.13-20240228.191822-15`.
  - Add `:clojure-lsp/different-aliases` linter. #1770
  - Fix unused-public-var false positives for definterface methods. #1762
  - Fix rename of records when usage is using an alias. #1756

- Editor  
  - Fix documentation resolve not working for clients without hover markdown support.
  - Added setting to allow requires and imports to be added within the current comment form during code action and completion: `:add-missing :add-to-rcf` #1316
  - Fix `suppress-diagnostics` not working on top-level forms when preceded by comment. #1678 
  - Fix add missing import feature on some corner cases for java imports. #1754
  - Fix semantic tokens and other analysis not being applied for project files at root. #1759
  - Add support for adding missing requires and completion results referring to JS libraries which already have an alias in the project #1587   
  
### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)

### 1.14.8 - 1.14.10  
- Fix exception when starting server related to previous version.
- Fix some exceptions that can rarely occurr after startup.
- Bump clojure-lsp to `2024.02.01-11.01.59`.  <br>

---

## Jeaye  Wilkerson: Jank   
2024 Q1 Project Report 1. Published 26 February 2024.

tl;dr I'm well ahead of schedule, so far this quarter! There's a more detailed update
here, with technical explanations: https://jank-lang.org/blog/2024-02-23-bindings/

### Dynamic bindings
Dynamic bindings are implemented! I'm starting to use these within the compiler,
as well, to track state. jank doesn't have `future` yet, but everything
is in place for binding conveyance. To go hand in hand with this, I tackled meta
hints next, allowing us to do things like `^:dynamic` on a var.

### Meta hints
jank now supports hints in the form of `^:keyword`, as well as `^{:keyword true}`.
Multiple hints can be specified, they can be nested, etc. Not many parts
of jank are using these hints yet, since I didn't go through all existing code
to update usages, but we can do that iteratively.

### What else?
Well, my quarter was booked for the following tasks:

* 🗹 Dynamic vars
* 🗹 Meta hints
* ☐ Syntax quoting
* ☐ Normalized interpolation syntax

The first two are done and the latter two remain, to be done in the next month.
But is that all I did in two months? Just dynamic vars and meta hints? Nah.

### Support for exceptions
I've added support for the special `throw` form, as well as for `try/catch/finally`.
Previously, I was using inline C++ to throw exceptions, but the work on the
dynamic vars gave me a good excuse to get proper exception support in there. I
needed to ensure bindings were gracefully handled in exceptional scenarios!

My plan is to do a compiler deep dive, next post, going into how exceptions were
implemented in jank. Stay tuned for that.

### Escaped string literals
jank previously didn't properly support strings like `"foo \"bar\" spam"`, since
they require mutation in order to unescape them. This is an interesting edge
case, because all of the other tokens within jank's lexer work based on memory
mapped string views. String views allow jank's lexer to run without any dynamic
allocations at all. However, for escaped strings, we need to allocate a string,
since we need to mutate it when unescaping. It's straight-forward, but just
something I hadn't tackled yet.

### Reader macros
Since I was improving the reader to support meta hints, I figured I'd add reader
macro support. In jank, you can now use `#_` to comment out forms. Also, you can
use `#{}` to create sets. Finally, you can use `#?(:jank foo)` and `#?@(:jank [])` to
conditionally read in jank code. The `:default` option is supported, too.
Support for shorthand functions, regex values, and var quotes will be
coming soon.

### New core functions and macros
While implementing the above features, I also added support for the following
vars in `clojure.core`:

* `assert`
* `when-not`
* `comment`
* `zipmap`
* `binding`
* `with-redefs`
* `drop` (not lazy)
* `take-nth` (not lazy)

Since jank doesn't have lazy sequences yet, some of these functions are a little
hacky. Lazy sequences come next quarter, though, as well as `loop` support!

### Community involvement
jank received its first non-trivial code contributions
this quarter, from a helpful man named [Saket Patel](https://github.com/Samy-33).
He's working on a [jank plugin](https://github.com/Samy-33/lein-jank) for leiningen
and it's currently in a state where you can set up a multi-file jank project and
use `lein jank run` to make magic happen. 😁 He's also submitted some C++
improvements to aid in that quest and to help jank compile on Apple M1 hardware.
I'll have a more detailed demonstration of this in my next development update.

On top of that, in order to get him set up for contributing, I've done the
following:  
* Added a [Code of Conduct](https://github.com/jank-lang/jank/blob/main/CODE_OF_CONDUCT.md)
* Added a [CLA](https://cla-assistant.io/jank-lang/jank), which is managed by a
  bot that will prompt you to sign upon submitting a PR with a large enough
* Added automated code formatting and re-formatted the whole codebase, using
  clang-format. I don't like the look of it, but at least it's consistent <br> 
  
  
---


## VENV: CIDER  
2024 Q1 Project Report 1. Published 7 February 2024.

January CT-sponsored work resulted in CIDER [1.13.0](https://github.com/clojure-emacs/cider/releases/tag/v1.13.0) ("Santiago") and a subsequent [1.13.1](https://github.com/clojure-emacs/cider/releases/tag/v1.13.1) bugfix release.  

Highlights have included:  
- all-new Timbre compatibility for [cider-log-mode](https://docs.cider.mx/cider/debugging/logging.html).
- misc bugfixes, a couple of which were highly requested over the Clojurians Slack `#cider` channel.

There's also been some WIP to improve our cljfmt integration.  





