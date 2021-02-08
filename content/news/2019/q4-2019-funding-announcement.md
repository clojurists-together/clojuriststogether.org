---
title: Q4 2019 Funding Announcement
date: "2019-11-05T14:00:00+13:00"
type: "post"
author: Daniel Compton
summary: "Clojurists Together is happy to announce that for Q4 of 2019 (November-January) we are funding four projects: Expound with Ben Brinckerhoff, Deep Diamond (Neanderthal) with Dragan Djuric, Libpython-clj with David Levy, Oz with Christopher Small."
---

Clojurists Together is happy to announce that for Q4 of 2019 (November-January) we are funding four projects:

- [Expound](https://github.com/bhb/expound) with Ben Brinckerhoff
- [Deep Diamond (Neanderthal)](https://github.com/uncomplicate/deep-diamond) with Dragan Djuric
- [Libpython-clj](https://github.com/cnuernber/libpython-clj) with David Levy
- [Oz](https://github.com/metasoarous/oz) with Christopher Small

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. Previously we have supported [datascript](https://github.com/tonsky/datascript), [kaocha](https://github.com/lambdaisland/kaocha), [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [Figwheel](https://github.com/bhauman/lein-figwheel), [ClojureScript](https://clojurescript.org), [CIDER](http://www.cider.mx/en/latest/), [Aleph](https://aleph.io), [Neanderthal](https://neanderthal.uncomplicate.org), [Fireplace](https://github.com/tpope/vim-fireplace), and [other projects](/projects/).

## Funding details

We support our grantees by paying them a total of $9,000 over three months ($3,000/mo).

## Deep Diamond (Neanderthal)

### Why is this project important to the Clojure community?

It is the infrastructure for deep learning, machine learning, and other high performance scientific software based on high-performance tensor operations.

### What are you wanting to achieve with this funding?

Create a lean, high performance, infrastructure for working with tensors in Clojure, with emphasis on Deep Learning.

This is something that is currently poorly supported on the JVM, let alone Clojure. The state of the art implementation of low-level operations is provided by Nvidia's cuDNN library on Nvidia GPUs and, very recently, by Intel on the CPU through its open source library MKL-DNN (renamed to DNNL). Leading high-level libraries for DL, TensorFlow and PyTorch, build on these libraries with lots of C++/Python cruft on top.

I propose to implement:

- A clean low-level Clojure-oriented integration with Intel's DNNL (aka MKL-DNN) for tensors on the CPU
- A clean and developer-friendly mid-level tensor API for Clojure that integrates well with leading low-level libraries (DNNL and cuDNN)
- A user-friendly and developer-friendly high-level API for deep neural networks in Clojure
- A developer-friendly implementation of fully-connected (dense) neural network layers in Clojure
- An extension infrastructure for various backend implementations, with at least 2 implementations: DNNL-based and pure Neanderthal-based (for fully connected layers)
- _optional_ A clean low-level integration with Nvidia's cuDNN for tensors on the GPU
- _optional_ An implementation of convolutional layers on the CPU and/or GPU (DNNL and/or cuDNN)

For most of these items, I have already created several prototypes while DNNL was in development, before it was officially released, so I am sure I can deliver nice results during the Clojurists Together funding period. I have already solved most of the tricky problems, and most of my work would consist of revisiting bits and pieces, updating and testing them, and polishing them into a nice, user-friendly, usable library.

Items marked as _optional_ are also going to be provided, but I cannot promise that I'll be able to deliver them during the three-month funding period.

This would serve as:

- A fast and simple Clojure-first infrastructure library that people can build on top of for everything tensor-related (which includes lots of ML)
- A fast and user-friendly deep neural networks implementation for Clojure
- A showcase for Clojure's capabilities in expressiveness and performance (it will be as fast or faster than alternatives in Python, Julia, Java, etc.)

This would NOT be a port of TensorFlow, or PyTorch, or MXnet, or DL4J to Clojure. It does not even aim for the same features. As an JVM analogy, it would stand in a similar position as Clojure vs Spring framework. It should leverage DNNL and cuDNN to provide high-performance tensor operations similarly to how Clojure leverages JVM, and enable users to then use Clojure to find simpler and more effective ways to build higher-level functionality on top of it, some of which it would also provide.

### Anything else we should know?

This project is applied for as part of Neanderthal, since this is the keyword that people in the community associate my work with, but this work kick-starts a separate library, Deep Diamond, that extends Neanderthal, living in its own github repository, [uncomplicate/deep-diamon](https://github.com/uncomplicate/deep-diamond).

## Expound

### Why is this project important to the Clojure community?

Expound is useful for any project that uses spec to validate external or internal data, since it allows developers to more quickly understand spec failures.

### What are you wanting to achieve with this funding?

1. Work through bug backlog
2. The project has evolved considerably since it started and has added several features. Unfortunately, that means the core is a bit brittle since it is based on building up strings. I'd like to refactor the core to build up a representation of the error message as a document, inspired by https://github.com/brandonbloom/fipp/blob/master/doc/primitives.md. This refactor will make further features possible like allowing users to remove parts of the error message.
3. I don't know when spec2 will come out, but I'd like to start a branch of Expound which uses spec2 so that when spec2 is released, a compatible version of Expound is also available.

## Oz

### Why is this project important to the Clojure community?

Clojure is rapidly gaining momentum as a language for Data Science, with numerous numerical computing and machine learning advancements in the last year or two. In contrast with established players in this space such as R and Python, Clojure has a unique value proposition in offering a robust front-end ecosystem for developing dynamic web applications to visualize and explore data.

However, while ClojureScript offers a first class front-end development experience, data visualization specifically has long been an under-served need in both Clojure & ClojureScript. The ability to flexibly leverage Vega-Lite and Vega, with their Clojuresque data-driven/declarative design, has the potential to close this gap.

Oz has already positioned itself as a leading data visualization tool in the Clojure ecosystem. This funding would let me take the project to the next level, and make it a more powerful and flexible tool. In so doing, I hope to help position our beloved language as a leader in this rapidly growing and increasingly important space.

### What are you wanting to achieve with this funding?

- Static compilation of Vega-Lite/Vega specs to SVG or JPG/PNG formats, enabling generation of PDF and fully-static HTML output (also solving issues with existing Jupyter integration)
- Static site generation features around Oz's existing Hiccup and Markdown compilation/rendering abilities
- Extend the Reagent API to allow for plugging into Vega signal graph, enabling tighter dataflow integration with the enclosing application
- Figwheel style live reloading of Clojure, Hiccup & Markdown files
- Interoperability with other notebook environments, such as Nextjournal & Gorilla-REPL, to complement existing Jupyter integration
- Miscellaneous: LaTeX support, grid/table components/helpers, resolve outstanding issues, improve documentation

**Note:** Christopher will work on Oz from January-March to allow him enough time to wrap up existing commitments.

## Libpython-clj

### Why is this project important to the Clojure community?

Nowadays, a bridge with Python would be the only practical way to access best-in-class libraries in several fast-growing fields, such as machine learning and probabilistic computing.

Even though the Libpython-clj bridge is already standing and functional, some additional work is necessary to make it actually comfortable to walk over.

Paving the bridge is not only an essential step in making Clojure a viable platform for data science, but also has the potential to make Clojure relevant to the needs and expectations of the huge audiences of new developers and data scientists, who typically study Python as their main language nowadays.

For additional background, one may look into the video of the community meeting we have had about this project:
https://www.youtube.com/watch?v=ajDiGS73i2o"

### What are you wanting to achieve with this funding?

Libpython-clj is a new library that allows Clojurians access to the whole Python ecosystem.

The goal of the proposed project is to improve developer experience around Libpython-clj in several aspects:

- Document important parts of Libpython-clj internals and API.
- Use it to turn a chosen Python library (e.g., tensorflow) into a Clojure library.
- Match typical Python errors and exceptions with informative Clojure error messages.
- Match Python docstrings with corresponding Clojure functions.
- Further document the library with tutorials.
- Support a user-friendly container-based easy installation.
- Reflect on the above process, and assess the resulting developer experience.
- Suggest a roadmap for automating the relevant parts of the process to support up-to-date, well documented Clojure wrappers of Python libraries.
- Possibly improve relevant aspects of Libpython-clj, such as session management (multiple interpreters) and resource management.
- One possible model for this process may be the Panthera Clojure library, that wraps the Pandas Python library. The approach of the current project will by inspired by that, but may be different.

Through the process of work, the precise form of API and the library to wrap will be chosen in dialogue with the community. The emerging developer experience will be continuously assessed by implementing realistic use cases, and by communicating the results with the community. Concrete steps will be prioritized accordingly.

### Anything else we should know?

David Levy, the applicant will be working in coordination with the original author (Chris Nuernberger), with the author of the related Panthera library (Alan Marazzi) and with the [Scicloj](https://twitter.com/scicloj) community.

## Voting details

The projects that applied this quarter were:

- Fireplace
- Light Table
- Deep Diamond
- Expound
- [Clojure Goes Fast](github.com/clojure-goes-fast)
- Shadow CLJS
- Paravim
- Saite
- Libpython-clj
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

We've always had lots of great applications, but this quarter in particular there were a bunch of awesome projects that we wished we could have funded. Thanks to everyone who applied!

## Q4 2019 Funding

We had a bunch of great applications from great projects. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, we will re-use that application for future funding cycles. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
