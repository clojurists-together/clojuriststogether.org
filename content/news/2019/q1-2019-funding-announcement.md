---
title: Q1 2019 Funding Announcement
date: 2019-01-23
type: "post"
author: Rachel Magruder
summary: "Clojurists Together is happy to announce that for Q1 of 2019 (February-April) we are funding two projects: Neanderthal with Dragan Djuric, and Aleph with Oleksii Kachaiev."
---

Clojurists Together is happy to announce that for Q1 of 2019 (February-April) we are funding two projects: [Neanderthal](https://neanderthal.uncomplicate.org) with Dragan Djuric, and [Aleph](https://aleph.io) with Oleksii Kachaiev.

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. This funding cycle is our fifth. Previously we have supported [datascript](https://github.com/tonsky/datascript), [kaocha](https://github.com/lambdaisland/kaocha), [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [Figwheel](https://github.com/bhauman/lein-figwheel), [ClojureScript](https://clojurescript.org), and [CIDER](http://www.cider.mx/en/latest/).

## Funding details

We're still working on funding details for this quarter and will update this post and announce the amounts once they are finalised.

**Update**: We are very happy to announce that due to the growth in member support, particularly over the last few months of 2018, we have been able to increase our support for projects from $5,400 over three months ($1,800/mo), to $9,000 over three months ($3,000/mo).

## Neanderthal

Neanderthal is a fast native-speed matrix and linear algebra library written in Clojure. Matrices and linear algebra are the infrastructure for high performance scientific software, including machine learning. Dragan is going to be working on adding higher-level tutorials that give people the bigger picture and shows them good practices.

### Dragan's plans for the next three months are:

- Writing an introductory series Deep Learning from the ground up with Clojure (a companion to [Neural Networks and Deep Learning](http://neuralnetworksanddeeplearning.com/) but with Clojure + Neanderthal + CPU + GPU)
- Trying (and I hope succeeding) to integrate Nvidia's cuSolver into Neanderthal's CUDA GPU engine (provides some key LAPACK functions that are currently only available on the CPU)
- Working on promoting Neanderthal and the Uncomplicate suite of libraries in the non-Clojure programming/ML community
- General improvements to the documentation and tutorials for Neanderthal
- General bug fixes and improvements to the Neanderthal suite of libraries

## Aleph

Aleph is one the best options for creating high-performance communication systems in Clojure, including but not limited to HTTP & websocket clients & servers. It is based on [Netty](https://netty.io), a high performance network application framework. A partial list of projects/companies who rely on Aleph to be maintained can be found at [ztellman/aleph#450](https://github.com/ztellman/aleph/issues/450).

Development of Aleph also has an impact on the entire server-side libraries ecosystem, including direct influence by backporting bug fixes to such projects like clj-http (Clojure), Netty (Java) and potentially Ring (Clojure); and indirect influence by pushing boundaries and expending interest in the field.

### Oleksii's plans for the next three months are:

- Stabilize and release a new version with latest development (quite a few new features)
- Tweak internals of the library and interactions with Netty to ease operational burden, to improve performance and to make sure Aleph can stay robust in all use cases
- Adding missing parts of the websocket protocol (pings, issues with resources cleanup)
- Dealing with large requests (100-continue handling, finishing multipart upload for client/server, safety limitations & timeouts, client-level decompress)
- Tests coverage for tricky features (requests pipelining, read-level backpressure)
- Operational improvements (connections management, graceful shutdown)
- SSL-related improvements (better way to manage contexts, SNI)
- Adding a more stable way for handling errors & error responses
- Other bug fixes and improvements from https://github.com/ztellman/aleph/issues

## Voting details

The projects that applied this quarter were (in alphabetical order):

- Aleph
- Boot
- Calva
- Form Validator CLJS
- Hoplon
- Klipse
- A fork of Light Table
- Neanderthal
- Shadow CLJS

## Q1 2019 Funding

We had a bunch of great applications from great projects; we would have liked to fund several more projects if we had the money. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, you can re-use that application to [apply](/open-source/) for Q1 2019. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
