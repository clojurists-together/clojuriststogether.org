---
title: "Q3 2021 Funding Announcement"
date: 2021-09-01T02:00:00+02:00
summary: "Clojurists Together is funding 9 projects for Q3 2021: shadow-cljs, clojure-lsp and Malli with $9,000, clojurians-log-v2, PCP and Holy Lambda with $2,000, and Dependabot Core, Typed Clojure and Polylith with $1,000."
author: Maria Geller
---

Clojurists Together is very happy to announce that in Q3 of 2021 we have selected a total of 9 projects to fund:

**$9,000**

* [Shadow CLJS](http://shadow-cljs.org/) - Thomas Heller
* [clojure-lsp](https://github.com/clojure-lsp/clojure-lsp) - Eric Dallo
* [Malli](https://github.com/metosin/malli) - Tommi Reiman

**$2,000**

* [Clojurians-log-v2](https://github.com/oxalorg/clojurians-log-v2) - Mitesh Shah
* [PCP](https://github.com/alekcz/pcp) - Alexander Oloo
* [Holy Lambda](https://github.com/FieryCod/holy-lambda) - Karol Wójcik

**$1,000**

* [Dependabot Core](https://github.com/dependabot/dependabot-core) - Othman Azil
* [Typed Clojure](https://github.com/clojure/core.typed) - Ambrose Bonnaire-Sergeant
* [Polylith](https://github.com/polyfy/polylith) - Joakim Tengstrand


Thanks to the [feedback](/news/the-next-phase-of-clojurists-together/) we received from developers and our members, we now offer a range of funding levels for our quarterly grant and more flexibility for grantees accepting the funds. In addition to postponing the funding start by 2 months, grantees of the $9,000 funding can also choose to take the funding over a variable time, from one month to twelve months.

Together with the [6 long-term fundings](/news/long-term-funding-selections/), this puts us at a total of 15 projects which we are funding at the moment and amounts to $144,000 USD.

Each project's plans are detailed below:

### Shadow CLJS
**Funding period:** 01 Sep 2021 - 01 Sep 2022

**Why is this project important to the Clojure community?**

shadow-cljs is a replacement for figwheel+cljsbuild with additional features on top. It integrates nicely with npm and fixes several other CLJS tooling concerns. figwheel is only a glimpse of what CLJS tooling could be.

My goal is a to build a complete standalone solution for ClojureScript so no knowledge of other Clojure tools is required to get started with ClojureScript. It still plays nice with Clojure though.

It does cover everything from the first steps in a REPL to full blown `:advanced` optimized code-splitting release builds today and could do so much more ...

**What are you wanting to achieve with this funding?**

shadow-cljs still is a bit of an underdog since it is still relatively unknown. I've been constantly working on it for the past few years whenever I find the time to do so. With funding I could do more in less time. More docs, more Examples, more Guides, more Features, ...

The project is already gaining users steadily and I expect that to continue as people like it.

### clojure-lsp
**Funding period:** 01 Sep 2021 - 01 Dec 2021

**Why is this project important to the Clojure community?**

It's an implementation of the [Language Server Protocol spec](https://microsoft.github.io/language-server-protocol/). This lets users write Clojure in any editor with an LSP plugin (VS Code, Ecliipse, emacs, vim, Sublime Text, etc.).

**What are you wanting to achieve with this funding?**

I'd like to add more features following the LSP spec like: call hierarchy, semantic-tokens, batch file renaming.

I'd also like to spend more time improving clojure-lsp performance, that would help us use clojure-lsp in almost any editor without any problems.

### Malli
**Funding period:** 01 Sep 2021 - 01 Mar 2022

**Why is this project important to the Clojure community?**

Clojure is a data-oriented language and we should have a solid fully data-driven schema library too! Besides validation and value transformations, we should be able transform, persist, generate and infer schemas just like normal data. Malli tries to be develop-friendly library to companion to other data-driven libs like EQL, Bidi, HoneySQL, Hiccup, Integrant, Reagent and Reitit. 

Malli has an open development model, design decisions are discussed in Slack (#malli) and in GitHub issues. Not a top goal, but eventually, Malli should be spec-compatible.

Despite being pre-alpha, malli has 21 contributors, 391 stars on GitHub and 54k+ downloads on Clojars.

Some background on my [ClojureD talk](https://www.youtube.com/watch?v=MR83MhWQ61E), [slides](https://www.slideshare.net/metosin/malli-inside-datadriven-schemas).

**What are you wanting to achieve with this funding?**

Malli is pre-alpha, 90% ready for the community. Due to private and business reasons, I haven't had enough extra time to lead out a robust stable version. With funding, I would have time to:

1) get a stable release out! lot's of small and some bigger design decisions, tracked via https://github.com/metosin/malli/issues/116
2) help early adopters (users and libraries like reitit, regal, aave and gungnir) to upgrade to use the initial version

After the release, would work on the following:

3) finalize sequence schemas, https://github.com/metosin/malli/pull/187
4) enhance developer tooling:
  - function schemas with clj-kondo integration https://github.com/metosin/malli/issues/125
  - pull out and reuse the reitit development time error pretty printer as a separate library (https://github.com/metosin/virhe) 
5) implement pluggable schema inference
6) parsers

### Clojurians-log-v2
**Funding period:** 20 Sep 2021 - 20 Dec 2021

**Why is this project important to the Clojure community?**

I feel the amount of knowledge being shared on the clojurians slack server is immense. Conserving and making this discourse complete, easily accessible, and searchable should greatly benefit the community as a whole.

**What are you wanting to achieve with this funding?**

The current clojurians-log-app (v1) has aged a bit. There are lots of operational issues cropping up and there is a huge scope of improvement which I can see (being one of it's current maintainers).

I have already started work on clojurians-log-v2 (standing on top of the great work done in v1) which will revamp the way we archive and access the information from clojurians slack.  It will make sure we can idempotently backfill all history from the beginning of time without losing any data (even if the server ever goes down), log in real time, and make it all searchable (indexed).

I have written detailed feature set and documentation as to why and what the v2 will encompass in the [README](https://github.com/oxalorg/clojurians-log-v2).

Back-filling entire history, full text searching, mobile responsive, recovering history despite downtimes, real-time logging with perma-links to threads & conversations, improved SEO for better google indexing, custom slack bot commands, using emojis to save important threads, ability to view most popular conversations/content of the week, etc are some of the features which will make clojurians logs more accessible and help disperse the knowledge more easily.

### PCP (Clojure Processor -- A Clojure replacement for PHP)
**Funding period:** 01 Oct 2021 - 01 Jan 2022

**Why is this project important to the Clojure community?**

It would make deploying Clojure website simpler for newbies. And cheaper for all. 

**What are you wanting to achieve with this funding?**

I would like to get PCP to a point where deployments can be done from the CLI or automatically via Github. And to allow users to do a 1-click deployment from the readme to a cloud platform. 

### Holy Lambda
**Funding period:** 01 Sep 2021 - 01 Dec 2021

**Why is this project important to the Clojure community?**

Holy Lambda allows Clojurians to write Lambda functions in Clojure that start fast and have low memory requirements. Holy Lambda proves that Clojure is not a slow language. From now on, Clojure is a viable choice for Serverless computing. 

**What are you wanting to achieve with this funding?**

The fund would let me spend some more time on the essential aspects of the successful projects: simplicity and complete documentation. Before the fund, I could triage only the most severe bugs and left minor inconsistencies in the code and documentation.


Plan:

1. Refactor

Finish the refactor of the Holy Lambda. Most of the stuff described in the following discussion I did last week. However, some more attention is needed to test the new release, do benchmarks, close the issues, write a detailed rationale post on the slack #news-and-articles channel, and on mentioned above discussion to notify all the active users. 

1.1 Expectations

- performance improvements around runtime,
- performance improvements around tasks,
- much simpler api, only one macro `entrypoint` exposed,
- much faster pure Clojure runtime compared to official AWS Java runtime,
- reduced configuration, environment variables options, easier way to combine HL with CI/CD, and various deployment tools,
- no more changes to the core api, stable and performant core!

2. Documentation

Now it's difficult for the new users to understand how Holy Lambda operates, especially for native runtime. I was trying to make the project too "easy" and failed. I believe that "good" documentation, fewer features, stable and performant core are the key to project success.

2.1. Expectations

- beautiful documentation covering the project written in AsciiDoc

### Dependabot Core
**Funding period:** 01 Nov 2021 - 01 Feb 2022

**Why is this project important to the Clojure community?**

This is important since most (F)OSS projects are hosted on GitHub.

**What are you wanting to achieve with this funding?**

I would like to add features to dependabot that would allow it to bump the dependencies of any Clojure project. It currently does that by making PRs to all the projects automatically. Since dependabot has been acquired by GitHub and is available automatically and free of charge to all, it would improve the Clojure community as a whole and make the ecosystem more secure. 

### Typed Clojure
**Funding period:** 01 Sep 2021 - 01 Dec 2021

**Why is this project important to the Clojure community?**

It is an effort to bring static type checking to Clojure and hence be another tool for Clojure program verification.

**What are you wanting to achieve with this funding?**

Make Typed Clojure easier to use by improving error messages. Instead of seeing gensyms and expanded code in your static type error messages (which is the current state of things), I want to see surface syntax with helpful explanations in plain language.

### Polylith
**Funding period:** 01 Sep 2021 - 01 Dec 2021

**Why is this project important to the Clojure community?**

Polylith helps the community organising codebases/projects in a way that they become easier to understand, reuse, and test. It makes you more productive.

**What are you wanting to achieve with this funding?**

I want to add support for generating documentation, compiling a native version of the poly command and/or improve the prompt command to support `<arrow up>` for history.
