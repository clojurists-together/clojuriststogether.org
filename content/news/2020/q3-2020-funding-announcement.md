---
title: "Q3 2020 Funding Announcement"
date: 2020-08-29T10:10:00+12:00
author: Daniel Compton
summary: "Clojurists Together is funding clj-kondo/babashka/sci, Datahike, Malli, and Practicalli"
---

Clojurists Together is happy to announce that for Q3 of 2020 (mid-August-mid-November) we are funding four projects:

- [clj-kondo](https://github.com/borkdude/clj-kondo)/[babashka](https://github.com/borkdude/babashka)/[sci](https://github.com/borkdude/sci) with Michiel Borkent
- [Datahike](https://github.com/replikativ/datahike) with Konrad Kühne
- [Malli](https://github.com/metosin/malli) with Tommi Reiman
- [Practicallli](https://practicalli.github.io) with John Stevenson

This is the first full funding round run under the Clojurists Together Foundation. It's taken us a little bit longer to get up and running this time, but future rounds should be much quicker.

## clj-kondo/babashka/sci

### What are you wanting to achieve with this funding?

**clj-kondo**

High priority issues in clj-kondo are usually fixed very fast: in most cases
they are fixed and released within weeks. But there are several important
enhancements that have been in the backlog for a while. I have curated a list of
these issues and labeled them "clj-together". They are prioritized in order of
importance on the project board:
https://github.com/borkdude/clj-kondo/projects/1?card_filter_query=label%3Aclj-together
(see medium priority column).

A couple of examples:

- Lint files in parallel which speeds up linting entire projects
- Add warnings for bindings shadowing vars, nested function literals, discarded
  constants/pure expressions.

**Babashka / Small Clojure Interpreter**

- Integration of clojure.spec into babashka
- Add support for clojure.datafy
- Port tests to Windows
- Investigate possiblity of implementing reify and deftype
- Smaller sci CLJS builds by configuration
- More control over duration / interrupts


### Why is this project important to the Clojure community?

Clj-kondo is a Clojure linter that is used by a wide variety of individual users
and companies
(https://github.com/borkdude/clj-kondo/blob/master/doc/companies.md).

Babashka is a scripting environment that can execute a significant subset of JVM
Clojure programs with instant startup. It is used by a individual users and
companies (https://github.com/borkdude/babashka/issues/254). It is currently my
project with the most stars on Github.

Sci is the Clojure interpreter powering babashka and several other projects:
- Bootleg: An HTML templating CLI
- Clj-kondo: Clojure linter
- Chlorine: Atom editor plugin for Clojure
- Malli: Plain data Schemas for Clojure/Script
- Spire: Pragmatic provisioning using Clojure.

## Datahike

### What are you wanting to achieve with this funding?

The following requested features can be added with the help of the funding:

- [tuple support](https://github.com/replikativ/datahike/issues/104)
- adjustment of the general API to Datomic's client API where possible for easier interaction, [#196](https://github.com/replikativ/datahike/issues/196), [#188](https://github.com/replikativ/datahike/issues/188)
- [attribute predicates and entity specs](https://github.com/replikativ/datahike/issues/197)
- [lazy sequences for transactions](https://github.com/replikativ/datahike/issues/151)

### Why is this project important to the Clojure community?

Within the Clojure Datalog database world Datahike provides a persistent open source solution for medium-sized projects with a relatively small and extensible code base. It supports a variety of persistent storages like JDBC or Redis. Additionally Datahike supports parts of the well-known Datomic API to better adapt to its functionality.

By adding ClojureScript support in the future, Datahike goes beyond the backend market with a vision of a distributed cross-platform system environment that speaks Datalog everywhere. With two commercial projects in progress, Datahike becomes more battle-tested in production environments.

The core team now consists of five people contributing to the core libraries with more people from the community building useful extensions like for example a [RocksDB store](https://github.com/purrgrammer/konserve-rocksdb), a [console for database interaction](https://github.com/replikativ/datahike/issues/205), or [tutorials](https://alekcz.gitbook.io/datahike-tuts).

