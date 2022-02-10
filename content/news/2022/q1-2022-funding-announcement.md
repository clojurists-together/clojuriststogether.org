---
title: "Q1 2022 Funding Announcement"
date: 2022-02-08T09:30:00+08:00
summary: Clojurists Together is funding 12 projects for Q1 2022. These projects are practicalli, clojure-lsp, deep diamond, datahike server, tablecloth, and clj-kondo, conjure, reveal, overtone playground biff, orchard, and Typed Clojure
author: Alyssa Parado
draft: true
---

We're off to a great start! Clojurists Together is very excited and happy to announce that for Q1 2022 we are funding a total of 12 projects: 

**$9,000**
* [Practicalli](https://github.com/practicalli) - John Stevenson
* [Clojure-LSP](https://github.com/clojure-lsp/clojure-lsp) -	Eric Dallo
* [Deep Diamond](https://github.com/uncomplicate/deep-diamond) - Dragan Djuric
* [Datahike Server](https://github.com/replikativ/datahike-server) - Konrad Kühne
* [Tablecloth](https://github.com/scicloj/tablecloth) -	Ethan Miller
* [Clj-kondo](https://github.com/borkdude/clj-kondo/blob/master/doc/companies.md), [Babashka](https://github.com/babashka/babashka/blob/master/doc/companies.md), and other related projects - Michiel Borkent

**$2,000**
* [Conjure](https://github.com/Olical/conjure) - Oliver Caldwell
* [Reveal](https://vlaaad.github.io/reveal) -	Vlad Protsenko
* [Overtone Playground](https://github.com/savorocks/overtone-playground) -	Savo Djuric

**$1,000**
* [Biff](https://github.com/jacobobryant/biff) - Jacob O'Bryant
* [Orchard](https://github.com/clojure-emacs/orchard) -	Lukas Domagala
* [Typed Clojure](https://github.com/clojure/core.typed) - Ambrose Bonnaire-Sergeant


# Q1 2022 Projects
Each project's plans are detailed below

## Practicalli
**Funding details:** $9,000 USD (3 months)

**Why is this project important to the Clojure community?**

Supports Clojure developers deliver projects effectively, capturing and sharing common practices from the community.
Also helps those new to Clojure navigate the myriad of choices when developing apps and services, to lower the barriers to adoption.  Providing a regularly updated source of information that the community can share with confidence.

**What are you wanting to achieve with this funding?**

Extend code examples, specific guides and video content across the Practicall Books, including the following topics
* Clojure Web Services - production grade project guides
  - Building REST API's with Reitit and clojure.spec
  - Building GraphQL API's with lacinia and juxt/grab
  - Authentication & Authorization (with Auth0 service)  
  - Integrant REPL and Integrant with Aero
   - structured logging with mulog / log 
* Clojure CLI 
  - using tools, project templates for deps-new
  - tools.build guide 
  - deployment (CLI specific heroku build pack, GitHub actions, Docker)
* Essential Clojure - a curated guide to the most common tools and libraries to support developers navigate the myriad of options in the Clojureverse 
  - Unit testing & Test runners: clojure.test (humane output) Koacha or Cognitect Labs
  - Repl Terminal UI - Rebel & addlibs
  - REST API Reitit and Clojure.spec
  - App server - jetty or httpkit
  - Logging - mulog
  - Relational dB - next.jdbc and postgresql &migration libraries
  - Clojure editor - gitpods for evaluating (Calva, Spacemacs/Doom, Cursive, Conjure, Clover) 
  - System components lifecycle - Integrant, Mount, Component

Each book also has additional ideas for the community to vote on in their respective repositories https://github.com/practicalli/




## Clojure-LSP
**Funding details:** $9,000 USD (3 months)

**Why is this project important to the Clojure community?**

It's probably the only tool for Clojure that has most IDE's features in a single place that can be used on multiple editors.

**What are you wanting to achieve with this funding?**

-  Keep improving current features (cliche but takes a lot of time), clojure-lsp has a lot of features, some have performance issues that can be enhanced and others have corner cases that doesn't work for some user/project cases.
- Support downstream issues/questions (Mainly Calva and Emacs requests)
- Add more docs/guides about how to setup on other editors like IntelliJ, Sublime, vim etc.
- Improve test support, like running and getting feedback of tests.
- Add more code actions, these make the editor a lot more like a IDE giving quick actions to do refactors on current code.
- Support upcoming LSP 3.17 official specification, including new features.
- Add minimal Java interop support (find classes/method definition, see method arity), needs considerable work on clj-kondo side
- Improve CLI/API support, making clojure-lsp not just a tool for editor but something to be used on CIs for example.
- I want to try add a minimal debugger following DAP (Debugger Adapter Protocol), not sure if that will be bundled in clojure-lsp or as a separated project, but it will probably need some time/work





## Deep Diamond
**Funding details:** $9,000 USD (3 months)

**Why is this project important to the Clojure community?**

It is the infrastructure for deep learning, machine learning, and other high performance scientific software based on high-performance tensor operations.

**What are you wanting to achieve with this funding?**

My goal with this round is to implement Recurrent Neural Networks (RNN) support in Deep Diamond.

Deep Diamond is a Clojure library for fast tensors and neural network computations based on the highly optimized native libraries and computation routines for both CPU and GPU.
It is a lean, high performance, infrastructure for working with tensors in Clojure, with emphasis on Deep Learning.
It currently supports general tensor operations, fully connected NN layers, and convolutional (CNN) layers, on CPU and GPU.

The major missing part is RNN support, which is commonly used in native language processing, timeseries, speech, translation, etc.

The state of the art implementation of low-level operations will be provided by integrating Nvidia's cuDNN (GPU), and Intel's oneDNN (CPU). Leading high-level frameworks for DL, TensorFlow and PyTorch, are based on the same libraries with lots of C++/Python cruft on top.
All these are very heavy, convoluted, and rather unclojure-y; Clojure can use them only as a second-class citizen, without much opportunity to extend and build on top. Clojure community found it a challenge even learning how to use these, yet alone build anything on top of them,
even though the area may be the most popular IT subject today. This is something that is currently poorly supported on the JVM, let alone Clojure.

My work is aiming to make a path for Clojure community to learn this area, and (hopefully) develop serious competence leading to application and tools on top of the infrastructure.

I propose to implement:
- a developer-friendly implementation of common RNN layers in Clojure
- LSTM (long short-term memory cell) support
- GRU (gated recurrent unit cell) support
- an extension infrastructure for various backend implementations of RNN.
- a clean low-level integration with Nvidia's cuDNN RNN on the GPU
- a clean low-level integration with Intel's oneDNN RNN on the CPU
- assorted improvements to Deep Diamond to support this additions
- related bugfixes
- TESTS (of course!)

This would improve Deep Diamond and make it more complete as:
- A fast and simple Clojure-first infrastructure library that people can build on top of for everything tensor-related (which includes lots of ML)
- A fast and user-friendly RNN implementation in Clojure
- A showcase for Clojure's capabilities in expressiveness and performance (the existing implementation is already as fast or faster than alternatives in Python, Julia, Java, etc.)

This will NOT be a port of TensorFlow, PyTorch, MXnet, nor DL4J, to Clojure. It does not even aim for the same features. As an JVM analogy, it stands in a similar position as Clojure vs Spring framework. It leverages DNNL and cuDNN to provide high-performance tensor operations similarly to how Clojure leverages JVM, and enable users to then use Clojure to find simpler and more effective ways to build higher-level functionality on top of it.

I will follow up this work with continuous improvement of Deep Diamond, and its ecosystem of books, docs, tutorials, etc. (I have already published two books covering this).





## Datahike Server
**Funding details:** $9,000 USD (3 months)

**Why is this project important to the Clojure community?**

As one of the common Clojure Datalog database Datahike has matured over the last years from an experimental DataScript fork to a self-contained project. A mostly compliant API with Datomic and shared code with DataScript makes Datahike a good target for small to medium-sized projects that want to leverage the power of Datomic's Datalog flavor and the simplicity of DataScript's setup together with a simple extensible persistence layer that supports backends like JDBC or Azure CosmosDB. With more users running Datahike in production environments this year, the focus lies now on scalability through the remote server, and robustness through benchmarks, regression tests, security design as well as migrations in both schema and version.


**What are you wanting to achieve with this funding?**

We will use the funding to bring both client and server into a usable state where it could be also used from other platforms as a REST system:
- history support: https://github.com/replikativ/datahike-server/issues/30
- latest Datahike API support: https://github.com/replikativ/datahike-server/issues/31
- healthcheck: https://github.com/replikativ/datahike-server/issues/32
- database metrics: https://github.com/replikativ/datahike-server/issues/33
- general support documentation: https://github.com/replikativ/datahike-server/issues/34
- client adjustment: https://github.com/replikativ/datahike-client/issues/2
- foreign platform client: https://github.com/replikativ/datahike-client/issues/3





## Tablecloth
**Funding details:** $9,000 USD (3 months)

**Why is this project important to the Clojure community?**

This project is vital for the Clojure community in two related but distinct ways. 

First, this project is a necessary piece of the broader goal of making the emerging Clojure data science ecosystem more accessible to beginning users. The tablecloth library is essential in this ecosystem because it is comfortable for beginners and fast. Adding support that is easy and intuitive (like tablecloth itself) and that leverages dtype-next’s high-performance abstractions for array-processing will help make this vital library still more complete and powerful.

Secondly, this project will promote a new style of programming that is suitable for exploring and managing large amounts of data fluidly. Dtype-next offers abstractions -- primarily a typed, countable random-access buffer -- that support optimized array processing on the JVM analogous to a tool like Python’s Numpy. However, its API operates at a lower level of abstraction than your average or beginning user would want to work daily. By providing a concrete interpretation of what it means to work with dtype-next at a higher-level of abstraction and in the style of tablecloth’s existing API, this project will introduce dtype-next’s array processing capabilities to a broader community by making them easy to use.

**What are you wanting to achieve with this funding?**

Currently, tablecloth provides an easy-to-use wrapper over tech.ml.dataset’s high-performance dataset processing constructs. One part of the tech.ml stack that tablecloth has not directly covered is dtype-next, which provides a highly performant basis for array-like numerical processing, similar to Numpy. The project I am proposing aims to wrap dtype-next within tablecloth, providing a new easy-to-use API for numerical structures for the emerging Clojure data processing ecosystem.

During this project, I will focus on the following tasks:

1. Add a new function to tablecloth (perhaps named `column` or `array`) that will return a typed, countable, random-access data structure backed by dtype-next’s abstractions;

2. Design two API pathways to interact with this structure: one that realizes the data fully at each step, providing more straightforward but less efficient interaction; and another, more performant but slightly harder to use, that allows users to wrap processing steps in a "transaction";

3. Mimic the Numpy (and possibly R vector) APIs ensuring an equivalently complete functional interface for numerical processing;
Ensure support reading-friendly format for printing columns in the Clojure REPL (see https://github.com/techascent/tech.ml.dataset/issues/203);

4. Validate the usefulness of the API by implementing real-world examples with various characteristics (missing values, various data types, challenging sizes, etc.) and comparing the ergonomics with other platforms such as Python and R.

While working on the above tasks, I will also lean on the resources within the SciCloj community to ensure that the API is, in fact, easy to use. In particular, I will seek to validate the clarity of the API by testing experimental versions within the Scicloj "study group" (live-coding) sessions.





## Clj-kondo, babashka, and other related projects
**Funding details:** $9,000 USD (6 months)

**Why is this project important to the Clojure community?**

Babashka and clj-kondo were mentioned in reply to "Are there any particular libraries, tools, or projects that are important to you that you would like to see supported?". 

* Clj-kondo is a Clojure linter that is used by a wide variety of individual users
and companies(https://github.com/borkdude/clj-kondo/blob/master/doc/companies.md).

* Babashka is a scripting environment that can execute a significant subset of JVM
Clojure programs with instant startup. It is used by a individual users and
companies (https://github.com/babashka/babashka/blob/master/doc/companies.md). It is currently my
project with the most stars on Github.

* Sci is the Clojure interpreter powering babashka and several other projects (see
https://github.com/borkdude/sci#projects-using-sci).


**What are you wanting to achieve with this funding?**

* Clj-kondo
  - linting for .clj-kondo/config.edn files (it's about time!)
  - discouraged var linter
  - warn on non-matching file and namespace names
  - analysis and API improvements for clojure-lsp
  - .cljc configuration/refinements
  - datalog linting improvements
  - numerous other little issues, see the project board:
    https://github.com/clj-kondo/clj-kondo/projects/1

* Babashka
  - declarative pod configuration in bb.edn
  - babashka.nrepl-client library: a library to connect to running nREPL processes and evaluate expressions
  - babashka.http-client library: a library to consolidate the existing http clients in babashka
  - improve documentation in book.babashka.org
  - addressing issues of several libs in bb: fs, process, etc.

* SCI
  - interop performance improvements
  - async documentation for usage in CLJS/JS

* Nbb (SCI on Node.js)
  - (n)REPL improvements
  




## Conjure
**Funding details:** $2,000 USD (3 months)

**Why is this project important to the Clojure community?**

Conjure has become a popular (in my opinion) tool among Neovim users and is enough of a pull to convert some people over from Vim just for the support of the tool. It's not just a fluent way to interact with Clojure programs but it acts as a platform to work with other languages to increase the user base immensely.

This nexus of (mostly lispy) languages means improvements and bug fixes come from many sources and all accidentally help each other. The person that added TreeSitter support initially was doing it for the Julia lang, for example, but the Clojure ecosystem can benefit from it!


**What are you wanting to achieve with this funding?**

Closing out various Clojure related feature requests and bugs as well as refactoring to make those changes possible. Conjure supports many languages, but my main audience and my main use is Clojure. I would love to integrate TreeSitter (a new feature in Neovim 0.5) more, allowing much richer and efficient evaluation mechanics.

I'd like to rewrite and refine documentation and onboarding, especially https://github.com/Olical/magic-kit and video content sharing workflows and introducing people to the language / tooling.

I'd also like to work on a companion tool that bridges various REPL implementations to other REPLs, meaning Conjure can talk to more languages and servers while also benefiting tools such as Chlorine (the author of Chlorine has expressed interest in this idea and would love me to build it!). This means Conjure (or ANY Clojure client) could talk to socket (p)repls even though it only speaks nREPL since this bridge can perform the translation.





## Reveal
**Funding details:** $2,000 USD (3 months)

**Why is this project important to the Clojure community?**

Reveal is a visual inspector overlay that runs in the JVM, thus making it available in any IDE/text editor capable of sending forms to REPL. Having a good test runner UI independent of IDE/text editor makes it available to any Clojure developer. I think clojure.test is a bit cumbersome to use from the REPL, and I want to improve it.

**What are you wanting to achieve with this funding?**

I want to add a test runner to Reveal — a way to run Clojure tests that is more useful than running from the CLI: 
- include UI elements that separate test output between different tests;
- have access to test results as values in the VM, e.g. be able to inspect and evaluate code on expected/actual values;
- have REPL integration for running and re-running tests, e.g. Reveal actions to run var/ns tests, or watch vars and re-run var's tests on var re-definitions





## Overtone Playground
**Funding details:** $2,000 USD (3 months)

**Why is this project important to the Clojure community?**

Overtone is one of most recognizable and popular projects in Clojure community. However, the development has ceased in recent years, and the tutorials are almost non-existent.
The potential of Overtone is massive, as it can be used to help newcomers to learn Clojure & to program music.

**What are you wanting to achieve with this funding?**

I aim to open the door for learning and exploring music with Clojure and Overtone and (in the long term) resurrect Overtone development.

Overtone Playground builds on top of Overtone and aims to provide users with clear instructions on how to learn music and programming from scratch.

My minimal goals for this project are:
- Transcribe tutorial from Sonic-Pi so it can be followed with Overtone
- Discover missing features in Overtone (while working on the tutorial)
- Explore how to provide these features and work on prototypes
- Music Theory Integration
- Future Goal (not part of this application): Resurrect Overtone in the long run

Overtone was one of the most popular Clojure projects, but the development has stalled since Sam Aaron (the main developer) switched all his efforts to
Sonic-Pi years ago. Sonic-PI is popular, actively developed, well funded, Ruby based project with lots of learning materials. Overtone has almost no instructions on how to use it; the little that is available is related to environment setup and technicalities without high-level learning materials. I aim to transcribe as much of Sonic-Pi book and tutorials as possible to Overtone code and examples.

Most of Sonic-Pi learning materials cannot be applied directly. Overtone is missing lots of high-level features of Sonic-Pi, and some low-level features due to different platforms. While transcribing the tutorial I will discover and systematize all these missing features, issues and challenges.

I will play with code examples and prototype the discovered features that are missing from Overtone. I am pretty sure that these prototypes will be far from perfect, but they will be the foundation for a more serious future work that will aim to resurrect Overtone. The goal of this future project would be to bring Overtone to usability level of Sonic-Pi.

Even Sonic-Pi is not perfect. While having good technical examples, its literature is fairly undeveloped regarding music theory. Since I am an experienced guitar player I can improve on both Sonic-Pi and Overtone literature in the area of music theory and creative aspects of those projects. I hope to make Overtone a good tool for learning both Clojure programming AND non-trivial music creation.

If this project goes well I hope that I'll be able to port lots of these improvements to Overtone itself and resurrect Overtone development. I hope this can be a good fit for a $9k funding in some of the future Clojurists Together rounds.





## Biff
**Funding details:** $1,000 USD (3 months)

**Why is this project important to the Clojure community?**

I think the Clojure ecosystem would benefit from having a strong, well-adopted web framework with lots of documentation and community support. Biff is IMO a good candidate for this, despite not having much adoption yet. I've used it in my own bootstrapped business (https://thesample.ai/) for the past 20 months and it's been wonderful. It's been tried out by a handful of people but I don't think anyone other than me has used it in production. It gets a lot of upvotes whenever I post about it on Reddit at least: https://www.reddit.com/r/Clojure/search/?q=biff

**What are you wanting to achieve with this funding?**

I'd like to finish up a major release that's been half done for a couple months. Most of the coding is finished, but I need to redo the new project template (in particular, I'm replacing ClojureScript with HTMX, and switching to Railway.app as the default/recommended hosting provider) and also rewrite most of the documentation on https://biff.findka.com.

After this release is finished, I'd like to focus on writing tutorials, creating more example projects, and in general trying to get more people to use it and then iterating on the pain points. I only mention this because I am trying to allocate more time to Biff starting next week, so on the off chance that I both finish the release by next month and also get accepted, that's what I'd use the funding for.





## Orchard
**Funding details:** $1,000 USD (3 months)

**Why is this project important to the Clojure community?**

ClojureScript tooling is always worse than Clojure tooling, partly because it's harder to write, but also because it's a chicken and egg problem. This project is one of the underpinnings of most Clojure tooling, so it would be a good first step to reduce the gap.
Runtime tooling is always a great driver for more things to build on top, so having more of it in ClojureScript would also help the ecosystem.

**What are you wanting to achieve with this funding?**

Try to implement ClojureScript function dependency lookup similar to xref/fn-deps. I've just extended the Clojure side of fn-deps and there is no such runtime tooling for ClojureScript anywhere.




## Typed Clojure
**Funding details:** $1,000 USD (3 months)

**Why is this project important to the Clojure community?**

It provides static type checking for Clojure code as another tool for program verification. It is also a handy framework from which to study and conceptualize Clojure's semantics.


**What are you wanting to achieve with this funding?**

#### Typed ClojureScript stable release

Typed Clojure is a type system for Clojure. It once supported ClojureScript, but it was put on hold in order to concentrate on improving the core type system.

The type system can seen many improvements, and I want to resurrect Typed ClojureScript so both Clojure and ClojureScript users can benefit from them. 

In my last funding round for Clojurists Together I created an analyzer for ClojureScript code that supports partial macro expansion[1]. Building on this, I want to integrate the new analyzer with the Typed ClojureScript type checker.

Most of the Typed ClojureScript code base will need to be updated to support the latest ClojureScript version, the aforementioned analyzer, and the latest type system. If it goes well, we should be able to release a stable version of Typed ClojureScript so people can try it out.

[1] https://github.com/typedclojure/typedclojure/blob/d02755ed6fc6101ca9acc9e478079f5893fb4085/doc/clojurists-together-q3-2021-update2.md





