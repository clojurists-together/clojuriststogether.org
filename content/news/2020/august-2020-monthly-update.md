---
title: "August 2020 Monthly Update"
date: 2020-09-07T10:00:54+08:00
author: Alyssa Parado
summary: Read more updates from Malli, Practicallli, Clj-kondo/babashka/sci, and Datahike
---

## Malli

**August 16-31**

First goal is to get a stable release of [Malli](https://github.com/metosin/malli) out. This has involved some hammock time, revisiting the design decisions and both designing and implementing features that still could effect the shape of the core apis. And a lot of refactoring. Work is mostly tracked via [#116](https://github.com/metosin/malli/issues/116).

### Done stuff

### Simple Schemas

* Add utilities for users to easily create simple schemas with property-based validation, including `:min` and `:max` ranges for numbers, collections and dates.
* More built-in schemas: `:int`, `:double`, `:boolean`, `:keyword`, `:symbol`, `:qualified-keyword`, `:qualified-symbol` and `:uuid`
* Closed PRs
  * Int [#243](https://github.com/metosin/malli/pull/243)
  * Simple schemas [#249](https://github.com/metosin/malli/pull/249)

### EntrySchemas

* One of the last big things that needs to get done right. Handling of Map Schemas is rewritten lifting MapEntry properties as first class concept for schema applications. This includes new entry value schema type (`m/-val-schema`), conditional entry walking and a new reusable syntax parser. It's a breaking change for many early adopters and because of that, still in review.
* PR in review
  * Schema Applications can use MapEntry properties [#212](https://github.com/metosin/malli/pull/212)
* Related issues and PRs
  * More Robust Humanized Errors [#80](https://github.com/metosin/malli/issues/80)
  * Feature request or bug: adding properties directly to map keys [#86](https://github.com/metosin/malli/issues/86)
  * Define Child/Parent relationship to Schemas [#120](https://github.com/metosin/malli/issues/120)
  * Rough edges for swagger documentation [#182](https://github.com/metosin/malli/issues/182)
  * feat: call map-key w/ parent-schema information [#119](https://github.com/metosin/malli/pull/119)
  * Allow specifying gen/gen and friends on map entries [#197](https://github.com/metosin/malli/pull/197)

### Misc

* Studied [Specter Source](https://github.com/redplanetlabs/specter/blob/1.0.4/src/clj/com/rpl/specter/navs.cljc#L243-L367) for finding more performant ways for doing value transformations. Good stuff.




## Practicallli

**August 16-31**

Continued support for Practicalli by Clojurists together is much appreciated and there are [several hundred content ideas](https://practicalli.github.io/clojure-webapps/relational-databases-and-sql/managing-connections.html) for the continued work.

Highlights of the last two weeks includes:
- [Live broadcasts](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiDjyU7cQYWOEFBDR1t7t0wv) - continuing the Banking on Clojure project with next.jdbc, H2 database and DBeaver.
- [Database access and design](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/development-database.html) for Banking on Clojure project
- Updated [practicalli/clojure-deps-edn](https://github.com/practicalli/clojure-deps-edn) repository to [use Congnitect dev-tools for REBL](https://practicalli.github.io/clojure/clojure-tools/data-browsers/rebl-data-visualization.html) and a way to [configure CIDER to work with REBL](https://practicalli.github.io/clojure/clojure-tools/data-browsers/rebl-data-visualization.html#run-rebl-with-nrepl).
- Test drive of [Conjure](https://github.com/Olical/conjure), an impressive Clojure environment for Neovim


### Clojure WebApps
Continuing the [Banking on Clojure project](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/database-tables.html) as a major project, which started from the specifications created in Practicalli study group and will be expanded into a production grade web application.

The application server system, UI, routing and initial handler have been defined and CircleCI used for system integration and generative testing, deploying to a staging environment on Heroku on successful builds.

Currently adding content for database connectivity, database design and using next.jdbc and H2 for a development environment database.

PostgreSQL provisioned using Heroku for staging and production database.  Explored JDBC database connection strings, understanding well formed jdbc connection strings, very useful for working with Heroku environment variable for Postgres databases. Using different variations on the db-spec mapping for next.jdbc to minimise the number of environment variables to create.

- [Design and create database tables](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/database-tables.html)
- [Namespace design](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/namespace-design.html)
- [h2 Relational database](https://practicalli.github.io/clojure-webapps/relational-databases-and-sql/h2-database/) and [development tools for H2](https://practicalli.github.io/clojure-webapps/relational-databases-and-sql/h2-database/database-tools.html)
- [Relational databases - Managing connections](https://practicalli.github.io/clojure-webapps/relational-databases-and-sql/managing-connections.html)
- [Production dabase - Heroku Postgres](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/production-database.html)

Once the project is completed with compojure, other routing libraries (bidi, reitit) will be used to show a working comparison, communicating the different approaches taken in a practical way.  The same will be done for component lifecycle libraries, eg. mount, integrant and component.


### Clojure deps.edn
Added new aliases and updated existing aliases

- `:cognitect-rebl` and `:nrepl` updated to use the Cognitect dev-tools release for REBL, testing with latest version of CIDER.
- `:database-h2` library dependency to separate the development environment database from staging and production deployment
- `:outdated` unsing antq rather than depot (which is no longer under development) to manage dependency versions

[Additional aliases added for lambdaisland/kaocha][https://github.com/practicalli/clojure-deps-edn/blob/live/deps.edn#L318-L350] to support ClojureScript test runner, BDD style tests, code coverage and junit-xml reports for CI tools and wall-boards.


### Practicalli Clojure
Added [several small projects](https://practicalli.github.io/clojure/simple-projects/) to help people learn the basics of Clojure, many of which cover the concept of data transformation using the Clojure standard library.

Added docs to [use CIDER and Calva with REBL](https://practicalli.github.io/clojure/clojure-tools/data-browsers/rebl-data-visualization.html#configure-rebl-with-cider-for-spacemacs--emacs)

### Conjure - vim tooling for Clojure development
Expanding the Clojure aware tools recommendations with Conjure, an excellent development environment for Neovim.

Created a [install walk through guide for Conjure](https://gist.github.com/jr0cket/6c475137ee57fbb14f9289bd76889512) that supports those new to Neovim (as I was) which will be added to the [Clojure aware editors section](http://practicalli.github.io/clojure/clojure-editors/) in Practicalli Clojure.  Also adding an [example init.vim](https://gist.github.com/jr0cket/fc7c6ec08d584675105667a0e483a643) configuration that is documented and explains the purpose of the plugins included, supporting the adoption of the Conjure tool.

Planning a video of a REPL based workflow using [Conjure](https://github.com/Olical/conjure) (and all other editors) to show the tool in action and support effective.



## Clj-kondo/babashka/sci

**August 16-31**

Here is an overview of the work I did per project. During this period I focused mostly on a new babashka release,
[v0.2.0](https://github.com/borkdude/babashka/blob/master/CHANGELOG.md#v020-2020-08-28). Most of the issues worked on
in [sci](https://github.com/borkdude/sci) also end up in
[babashka](https://babashka.org/).

### Babashka

- Allow resources to be read from jar files [#528](https://github.com/borkdude/babashka/issues/528)
- Add support for `clojure.datafy` in babashka [#468](https://github.com/borkdude/babashka/issues/468)
- Use absolute paths rather than canonical paths on var metadata [#532](https://github.com/borkdude/babashka/issues/532)
- Can't `set!` `*warn-on-reflection*` in babashka nREPL [#25](https://github.com/babashka/babashka.nrepl/issues/25)
- Uberscript can put output of namespaces in wrong order [#535](https://github.com/borkdude/babashka/issues/535)
- Support for uberjars [#536](https://github.com/borkdude/babashka/issues/536)
- Ignore unresolved symbols while generating uberscript [#538](https://github.com/borkdude/babashka/issues/538)
- Babashka shows wrong file name when error is from required ns [#508](https://github.com/borkdude/babashka/issues/508)
- Print stacktrace, context and locals on exception [#534](https://github.com/borkdude/babashka/issues/543)

I also started to work on [babashka.process](https://github.com/babashka/babashka.process), a library that will make it easier to work with `java.lang.ProcessBuilder`. This did not yet end up in a release.

Another sub-project I started working on is integrating clojure.spec with
babashka ([#558](https://github.com/borkdude/babashka/issues/558)). It's unclear
if this will make it in babashka, but it will give me an idea if it's feasible
at all. According to a
[poll](https://twitter.com/borkdude/status/1299975678885072898) 60% of babashka
user like to wait for spec2. An option is to include spec1 under a feature flag
for those who like to compile babashka on their own machines. Here is a [Github
issue](https://github.com/borkdude/babashka/issues/558) where you can post your
ideas on this.

### Sci

- Fixed an with `with-redefs`: core vars could not be rebound [#375](https://github.com/borkdude/sci/issues/375)
- Dynamic binding with `false` value is evaluated as `nil` [#379](https://github.com/borkdude/sci/issues/379)
- Implement CI performance test [#385](https://github.com/borkdude/sci/issues/385)
- Records produced by data readers are parsed as maps [#386](https://github.com/borkdude/sci/issues/386)
- Support for callstack and locals for babashka [#374](https://github.com/borkdude/sci/issues/374)
- Wrong arity reported when calling single-arity macro [#392](https://github.com/borkdude/sci/issues/392)
- Implement `clojure.walk/macroexpand-all` [#398](https://github.com/borkdude/sci/issues/389)
- Recursive function bound by var should not introduce local binding [#384](https://github.com/borkdude/sci/issues/384)

### Clj-kondo

- Enhanced `rum/defcs` [hook code example](https://github.com/borkdude/clj-kondo/commit/9f004622d0c69a6baf93bb63243306a7098f0941) (also see this [issue](https://github.com/borkdude/clj-kondo/issues/960))
- Don't use return values from `analyze-input` invocations [#972](https://github.com/borkdude/clj-kondo/issues/972). This will make linting in parallel easier.
- Work started on linting on parallel [#632](https://github.com/borkdude/clj-kondo/issues/632)
- Catch calling non-functions as functions in bindings [#948](https://github.com/borkdude/clj-kondo/issues/948)

### Misc

- Released [dynaload](https://github.com/borkdude/dynaload) 0.1.0 to clojars
- Released [clj-reflector-graal-java11-fix](https://github.com/borkdude/clj-reflector-graal-java11-fix) 20.2.0
- Released [jet](https://github.com/borkdude/jet) v0.0.13




## Datahike

**August 16-31**

In the first iteration we started working primarily on analysis and planning on the following issues:

- Datomic API compatibility (https://github.com/replikativ/datahike/issues/196)
- Entity Specs (https://github.com/replikativ/datahike/issues/197)
- Homogeneous Tuple Value Type (https://github.com/replikativ/datahike/issues/104)

### Datomic API

A comparison project was created that calls common functions from Datomic and Datahike in order to find out what functions differ. The result from this iteration will be a report that highlights the differences.

### Entity Specs

Datomic's entity specs (https://docs.datomic.com/on-prem/schema.html#entity-specs) were analysed and a first draft for Datahike's system schema was derived. Further planning for integration into the current transactor was done.

### Tuples

Homogeneous tuple value types were analysed both in DataScript and Datomic (https://docs.datomic.com/on-prem/schema.html#homogeneous-tuples) in order to understand the differences and to define a proper design that would fit well within our system schema.


