title: Q2 2019 Funding Announcement
date: 2019-04-30
type: "post"
draft: true

Clojurists Together is happy to announce that for Q2 of 2019 (May-July) we are funding two projects: [Boot](https://boot-clj.com) with Matthew Ratzke, and [Fireplace](https://github.com/tpope/vim-fireplace) with Tim Pope.

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. This funding cycle is our fifth. Previously we have supported [datascript](https://github.com/tonsky/datascript), [kaocha](https://github.com/lambdaisland/kaocha), [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [Figwheel](https://github.com/bhauman/lein-figwheel), [ClojureScript](https://clojurescript.org), [CIDER](http://www.cider.mx/en/latest/), [Aleph](https://aleph.io), and [Neanderthal](https://neanderthal.uncomplicate.org).

## Funding details

We were able to increase our support for projects in the last quarter from $5,400 over three months ($1,800/mo), to $9,000 over three months ($3,000/mo).

## Boot

Boot is one of the main build tools used in the Clojure ecosystem and was used by 10% of Clojure developers in the most recent [Clojure survey](https://www.surveymonkey.com/results/SM-S9JVNXNQV/). It lets you build flexible build processes with Clojure code.

### Matthew's plans for the next three months are:

- Restructuring - I want to separate boot into libraries that more closely resemble their intended function:
  - Fileset (in-progress)
  - Pods
  - Tasks
  - Environment
  - Boot "App"
- Remove Included code from other projects (ie. remove all boot.from.\* namespaces)
- Remove Deprecated Code
- Optimize the Internal Libraries with minimal changes to Public API

Matthew has also written more about the [Future of Boot](https://medium.com/degree9/boot-future-boot-e1948562d8d3) and [spoke](https://www.youtube.com/watch?v=xqGmE4KyhzQ) at Dutch Clojure Days on the same topic.

## Fireplace

Fireplace is a vim plugin for Clojure. It adds REPL integration and was used by 10% of Clojure developers in the most recent Clojure survey.

### Tim's plans for the next three months are:

- Work through the issue tracker. Answer outstanding questions,
  implement the low hanging fruit, close anything old and invalid, be
  frank about the wontfixes, and categorize everything else.
- Implement support for Vim 8 and Neovim job support. If this goes
  well (doesn't turn out to be untenable), it will probably account for
  the bulk of the time.
- Assess the state of the ClojureScript ecosystem and see what
  improvements can be brought to Fireplace's support.
- Work through sibling plugin [salve.vim's](https://github.com/tpope/vim-salve) issue tracker.

## Voting details

The projects that applied this quarter were:

- Fulcro
- Boot
- Calva
- Formic
- Intro to Programming w/Clojure in Georgian Language
- Pathom
- Origami
- Klipse
- Fireplace
- LightTable
- Shadow CLJS

## Q2 2019 Funding

We had a bunch of great applications from great projects; we would have liked to fund several more projects if we had the money. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, you can re-use that application to [apply](/open-source/) for Q3 2019. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
