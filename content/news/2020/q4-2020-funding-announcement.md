---
title: "Q4 2020 Funding Announcement"
date: 2020-11-30T19:29:54+13:00
author: Daniel Compton
summary: "Clojurists Together is happy to announce that for Q4 of 2020 (December-February) we are funding four projects for $9,000 each"
---

Clojurists Together is happy to announce that for Q4 of 2020 (December-February) we are funding four projects for $9,000 each:

- [clj-kondo](https://github.com/borkdude/clj-kondo)/[babashka](https://github.com/borkdude/babashka)/[sci](https://github.com/borkdude/sci) with Michiel Borkent
- [ClojisR](https://github.com/scicloj/clojisr) with Ashima Panjwani
- [O'Doyle Rules](https://github.com/oakes/odoyle-rules) with Zach Oakes
- [Calva](https://github.com/BetterThanTomorrow/calva) with Brandon Ringe


## Clj-kondo

### What are you wanting to achieve with this funding?

**Clj-kondo**

- Documentation of all available linters and options
- Warn on usage of non-existing vars in required namespaces
- Skip already linted gitlibs
- Type warning improvements
- Dozens of small enhancements: warn on nested function literals, discarded constants/pure expressions, ...
- Binary release of carve, a project based on clj-kondo for removing unused vars

**Babashka**

- babashka.edn project file: a file that describes paths, deps and possible pods that are used in a babashka project
- easier installation of pods
- babashka.fs: utility namespace with file-related functions
- Windows test suite

**Sci**

- Performance improvements (https://github.com/borkdude/sci/issues?q=is%3Aissue+is%3Aopen+label%3Aperformance)
- Add support for *print-fn* (CLJS)
- Smaller sci CLJS builds by configuration

### Why is this project important to the Clojure community?

Clj-kondo is a Clojure linter that is used by a [wide variety of individual users
and companies](https://github.com/borkdude/clj-kondo/blob/master/doc/companies.md).

Babashka is a scripting environment that can execute a significant subset of JVM
Clojure programs with instant startup. It is used by [individual users and
companies](https://github.com/borkdude/babashka/issues/254). It is currently my
project with the most stars on Github.

Sci is the Clojure interpreter [powering babashka and several other projects](https://github.com/borkdude/sci#projects-using-sci).


## ClojisR

### What are you wanting to achieve with this funding?

ClojisR library seeks to combine Clojure's unique approach towards data exploration with R's huge collection of data visualization and statistical libraries.

I’ll be working in collaboration with the main authors (Tomasz Sulej and Daniel Slutsky) of this library to contribute to it with the objective of easing the onboarding experience for developers starting out with ClojisR, thereby helping to increase the popularity and adoption of Clojure.

During the project, we plan to focus on the following tasks:

- So far, the authors of ClojisR have been focused mainly on building the core functionalities of the library, thus leaving a few missing gaps in terms of good documentation and tutorials showcasing demo examples with detailed explanations. Therefore, preparing entry-level documentation for the library, including basic use cases and showing interop with tech.ml.dataset/tablecloth (maybe libpython-clj) + highly developed notebook solutions (saite/hanami, oz, gorilla) are some of the issues that need to be worked on.
- Currently, there are minimal test-cases leading to issues related to broken releases. Hence, I’ll be working on building test suites, starting from building test-cases for the most important parts of the library.
- There’s a need for comprehensive ML / Stats workflows with code examples and good documentation, especially for newer users to refer to. I’ll be working on that as well.
- Enhance the library by wrapping chosen R packages from ML / Stats areas (scope to be defined with the help of the authors)
- Assist the core team by helping with bug fixing and development of the library
- (optional) Translate select Kaggle ML kernels

As guidance, we will be using the R4DS book for which we have already received permission from the authors and publisher.

### Why is this project important to the Clojure community?

The purpose of this project is to take an active part in improving Clojure's data science stack, especially in aspects of usability, user-facing features, and documentation.

A large part of that effort is happening in the Scicloj community (https://scicloj.github.io/) with which this project is associated.

The work that we’ll be doing with this funding will enable us to get closer to our goal of making Clojure a beginner-friendly solution for data science, thereby allowing us to expose the Clojure ecosystem to a different culture and to more diverse groups of users/programmers - starting with new developers and data scientists studying R as their main language.

I am a user of ClojisR and previously contributed to the library’s documentation by creating tutorials that introduced first-time users to some of the features offered by this library. I am collaborating with the main authors of Clojisr (Tomasz Sulej and Daniel Slutsky), and we are planning this project together.



## O'Doyle Rules

### What are you wanting to achieve with this funding?

I have a short term and long term plan.

In the short term, I want to improve o'doyle to match the performance and feature set of clara rules. I have a lot of ideas on the perf front, and features i want to add include:

1. Serialization - saving a session (or at least its facts) to a string so it can be read later with edn/read-string
2. Debugging - adding a mechanism to trace facts and determine why a rule didn't fire

My long term plan is completely speculative and not something i'll accomplish in the three months i'm funded, but I believe rules engines are the key to creating a "generalized React" in pure clojure.

By that I mean a library that can completely replace React to drive front end UIs, but also be used in other contexts such as non-browser UIs (e.g. JavaFX), games, or non-UI business logic (where rules engines are mostly used today).

In that way, your entire application can be made out of the same "stuff" from one end to the other; state on the frontend and backend could be managed with the same rules engine and even share rules/facts.

### Why is this project important to the Clojure community?

Clojure needs another good rules engine. Clara is great but the lack of ability to update facts makes it difficult to use outside of "pure" logic problems. O'Doyle implements the same RETE algorithm but makes it more useful for general purpose "messy" use cases like managing the state of a frontend app or a game. And i also have more speculative ideas (see above) that could either be amazing or crash and burn into a pit of failure -- and those are the only ideas i want to try!



## Calva

### What are you wanting to achieve with this funding?

I am looking at adding static analysis via clojure-lsp. This will have a large impact on Calva's users and also lays the foundation for future improvements, if it goes as we expect.

To expand on the above, adding static analysis will hopefully allow us to add features (without repl dependencies) like:

- Find references/usages
- Intellisense
- Sorting ns requires

In addition to adding static analysis support, I'll be working on bug fixes or documentation as necessary. Also, we think we can reduce our repl dependencies which are injected at Calva's jack-in, resulting in smoother experience for developers and maintainers.

### Why is this project important to the Clojure community?

We've seen that Calva helps new Clojurists get up and running with Clojure without having to worry so much about tooling. Perhaps more importantly, VS Code has become such a major platform for developers and it's important to have Clojure(Script) support in the developers' editor of choice.
