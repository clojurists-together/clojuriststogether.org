---
title: "Q1 2020 Funding Announcement"
date: 2020-01-26T11:53:57+13:00
author: Daniel Compton
summary: "This quarter we are funding Reagent, Ring, Fireplace, and Calva!"
---

Clojurists Together is happy to announce that for Q1 of 2020 (February-April) we are funding four projects:

- [Reagent](https://reagent-project.github.io) with Juho Teperi
- [fireplace.vim](https://github.com/tpope/vim-fireplace) with Tim Pope
- [Ring](https://github.com/ring-clojure/ring) with James Reeves
- [Calva](https://github.com/BetterThanTomorrow/calva) with Brandon Ringe

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. Previously we have supported [datascript](https://github.com/tonsky/datascript), [kaocha](https://github.com/lambdaisland/kaocha), [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [Figwheel](https://github.com/bhauman/lein-figwheel), [ClojureScript](https://clojurescript.org), [CIDER](http://www.cider.mx/en/latest/), [Aleph](https://aleph.io), [Neanderthal](https://neanderthal.uncomplicate.org), [Fireplace](https://github.com/tpope/vim-fireplace), and [other projects](/projects/).

## Funding details

We support our grantees by paying them a total of $9,000 over three months ($3,000/mo).

## Reagent

### Why is this project important to the Clojure community?

Based on Clojars downloads Reagent is the most used ClojureScript React wrapper. Reagent is also one of the most popular ClojureScript projects on GitHub with 3.7k stars, and [re-frame](https://github.com/day8/re-frame), a  framework built on top of Reagent has even more stars.

### What are you wanting to achieve with this funding?

I can work on Reagent at work, during our 10% time and in some cases
during customer projects, but this is mostly maintenance and small
fixes, as it is hard to find time for bigger changes.

To implement larger changes I'd need some weeks of dedicated time.
Two of the bigger changes I've been thinking about are:

- Supporting React hooks
- Configurable (Reagent-)hiccup compiler

One of the most asked features is if Reagent support for React hooks. Unfortunately hooks are only usable with React functional components. and currently Reagent RAtom implementation uses Stateful Component state. RAtoms need to know which components depend on them, and be able to re-render the component when RAtoms change. This should be possible by storing state and triggering re-render using hooks. The trick will be implement this in a way that doesn't break existing Reagent components, which presume stateful components.

As this affects how Reagent turns Hiccup to React elements and components, I have some ideas on allowing users configure the Reagent Hiccup compiler, similar to what [Hicada](https://github.com/rauhs/hicada) does. This would also allow introducing optional features which would break existing Reagent code, by making users opt-in to these. One case would be to make React component interop simpler.

As hooks is the more important feature, I'd start by testing out the ideas I have for that, and then see if that it makes sense to implement that alone, or if Hiccup-compiler changes are needed/make sense to implement at the same time.

I think a good outcome would be to have Reagent alpha/rc release with Hooks support with the funding, and even better if I also have time to test ideas for Hiccup-compiler feature.


## fireplace.vim

Note: Tim has other work commitments for the next few months, and will start work on fireplace once those finish up.

### Why is this project important to the Clojure community?

Fireplace.vim was used by 10% of Clojure developers in the most recent Clojure survey.

### What are you wanting to achieve with this funding?

- Leverage the recently added asynchronous APIs for user facing features, starting with eval.
- Better automatic configuration for projects, notably Shadow CLJS which is currently a pain to get up and running on.
- Attend to various feature requests in the issue tracker (I got the low hanging fruit during the last sponsorship so most of what's left is pretty meaty).

## Ring

### Why is this project important to the Clojure community?

Ring is the most commonly used HTTP abstraction layer for Clojure.

### What are you wanting to achieve with this funding?

I'd like to bring out a draft spec and experimental alpha for Ring 2.0, in order to better support asynchronous HTTP connections, and to pave the way to support HTTP/2 and HTTP/3 in future. As a secondary objective, I'd also like to remove the provided dependency on Java servlets from Ring core.

## Calva

### Why is this project important to the Clojure community?

We've seen that Calva helps new Clojurists get up and running with Clojure without having to worry so much about tooling. Perhaps more importantly, VS Code has become such a major platform for developers and it's important to have Clojure(Script) support in the developers' editor of choice.

### What are you wanting to achieve with this funding?

We would like to add the ability to work with large data sets. Currently, large data sets can freeze up the REPL session, and this makes Calva unusable for some developers who routinely work with such data. We would also like to add Clojure debugging to Calva. These are the two main goals, but handling large data sets is the priority. If at any point one of these problems seems to require a lot more time than we anticipated, we may shift our focus to handling the one task for the duration of the funding period, if necessary. In addition to those goals, we'd like to handle issues here and there and maintain the issue log where needed.

## Voting details

The projects that applied this quarter were:

- Conjure
- Calva
- Reagent
- Graphqlize
- Ring
- Tupelo Forest
- iSpooge Live
- fireplace.vim
- Light Table
- Clojure Goes Fast
- Saite
- Clojure's Boring Web Framework
- Practicalli Spacemacs
- vim-iced
- Chlorine
- Typed Clojure
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

## Q1 2020 Funding

We had a bunch of great applications from great projects. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, we will re-use that application for future funding cycles. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
