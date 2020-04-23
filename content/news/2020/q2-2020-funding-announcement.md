---
title: "Q2 2020 Funding Announcement"
date: 2020-04-23T14:15:00+12:00
author: Daniel Compton
summary: "Clojurists Together is funding re-frame, Practicalli, CIDER/nREPL/Orchard, and Figwheel"
---

Clojurists Together is happy to announce that for Q2 of 2020 (May-July) we are funding four projects:

- [re-frame](https://github.com/day8/re-frame) with Isaac Johnston
- [Practicalli](https://practicalli.github.io) with John Stevenson
- [CIDER/nREPL/Orchard](https://cider.mx) with Bozhidar Batsov
- [Figwheel](https://figwheel.org) with Bruce Hauman

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. Previously we have supported [datascript](https://github.com/tonsky/datascript), [kaocha](https://github.com/lambdaisland/kaocha), [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [ClojureScript](https://clojurescript.org), [Aleph](https://aleph.io), [Neanderthal](https://neanderthal.uncomplicate.org), [Reagent](https://reagent-project.github.io), [Ring](https://github.com/ring-clojure/ring), [Fireplace](https://github.com/tpope/vim-fireplace), and [other projects](/projects/).

## Funding details

We support our grantees by paying them a total of $9,000 over three months ($3,000/mo).

## re-frame

**Disclosure:** Daniel Compton previously worked at Day8 and worked on re-frame. This was disclosed to the committee while discussing the submissions and was not seen as a conflict of interest.

### Why is this project important to the Clojure community?

re-frame is one of the most widely used ClojureScript projects with 4.3k stars on GitHub and 1.1 million downloads on Clojars.

### What are you wanting to achieve with this funding?

First, some spring cleaning and TLC for the project. Re-frame hasn’t had enough concerted attention recently, and I’d initially like to review and triage all existing open issues and PRs. From this process, I’d like to tackle the highest value set of issues which should be immediately addressed. On completion of this phase, I’d like to cut a new release.

Second, I’d then like to move on to one of the “EPs” written up in the [/docs/EPs](https://github.com/day8/re-frame/tree/master/docs/EPs) section of the repo. This step would enhance re-frame with at least one new significant feature (to be decided).  I’m in the fortunate position that I can get advice and opinion from Mike Thompson, the author of re-frame and a coworker at Day8, throughout the process.

It is likely that some of what I do would impact sibling projects such as re-frame-template and re-frame-10x on which I am also an active maintainer. So, I’d be bringing them along for the journey too.

## Practicalli

### Why is this project important to the Clojure community?

High quality and up to date information helps any developer using Clojure find the help they need, whether they are experienced or new to Clojure. Finding the time to teach developers how to work with Clojure is time consuming for developers, so a reliable source of information speeds up on-boarding and skilling-up of developers.

### What are you wanting to achieve with this funding?

Create and complete 3 on-line detailed books with extensive video tutorials covering:

1) Clojure interactive development with Clojure CLI & deps.edn, clojure.core examples, idiomatic code, thinking functionally, testing (unit, performance, generative, spec).

2) Server-side web application development, starting with ring-compojure and scaling up and out through Luminus duct, reitit and pedestal.  Including component lifecycle management (mount, component, integrant), data stores (postgres, redis, firebase, couchbase, kafta), security, access management, etc.

3) Clojure development tools - Emacs/Spacemacs has largely been created and looking to add or work with other tool developers to enhance their developer support content (Calva, Chlorine, Cursive, etc).

Funding will also give me time to go into deeper subjects on the weekly live broadcast I am currently working on.

All content is freely available under a Creative Commons license.  Code examples and projects will be available via the [practicalli](https://github.com/practicalli) GitHub organisation.

## CIDER/nREPL/Orchard

### Why is this project important to the Clojure community?

CIDER's the most popular IDE in the Clojure community, and nREPL and CIDER's Orchard are at the heart of most other tools that exist out there.

### What are you wanting to achieve with this funding?

* Work towards nREPL 0.8 (e.g. built-in completion/info ops)
* Work towards CIDER 1.0 (e.g. implementing nREPL's new sideloader)
* Finish extracting generic functionality from cider-nrepl to orchard
* Improve the documentation for creating nREPL servers and clients
* Improve the documentation of CIDER's debugger and how it can leveraged by other editors
* Help with the work on new nREPL implementations (e.g. the one for ClojureScript and babashka)
* Make CIDER usable with alternative nREPL servers

There are many other small improvements that I can potentially tackle, depending on how things go with the main tasks.

## Figwheel

### Why is this project important to the Clojure community?

lein-figwheel has 1,093,154 downloads on clojars and figwheel-main has 99,766 downloads

### What are you wanting to achieve with this funding?

There is a decent backlog of issues for figwheel/lein-figwheel and I have recently taken a sabbatical from my job and I'd love to not only work on this backlog but really iron out some glaring figwheel-main usage issues that keep cropping up for people.  On accomplishing that I'd like to look at making it even easier to get started with a ClojureScript project. If I get all that done I'd like to look at the documentation again and clarify some FAQs like how to deploy a ClojureScript project etc.

## Voting details

The projects that applied this quarter were:


- Clojure Goes Fast
- clj-kondo, babashka, sci
- CIDER, nREPL, Orchard
- Practicalli
- Fulcro RAD
- typed.clj/spec
- GraphQLize, HoneyEQL
- Spire
- re-frame
- Figwheel
- Rum
- Calva
- Conjure
- Walkable
- iSpooge Live
- Piggy
- Tupelo Forest
- Light Table
- Saite
- Clojure's Boring Web Framework
- vim-iced
- Chlorine
- Datahike
- Cloxp 2.0
- Perun
- Formic
- Intro to Programming w/Clojure in Georgian Language
- Pathom
- Ohmyclj
- Klipse
- Origami
- Oz
- form-validator-cljs

## Q2 2020 Funding

We had a bunch of great applications from great projects. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, we will re-use that application for future funding cycles. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
