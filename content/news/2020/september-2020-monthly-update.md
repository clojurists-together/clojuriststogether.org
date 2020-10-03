---
title: "September 2020 Monthly Update"
date: 2020-10-03T14:10:00+08:00
author: Alyssa Parado
summary: Read more updates from Malli, Practicallli, Clj-kondo/babashka/sci, and Datahike
draft: true
---

Here are the updates from our [new projects](/news/q3-2020-funding-announcement/).

# Malli

### **September 1-15**

Small improvements based on user feedback, finalized the EntrySchemas and drafted implementation for *heterogenus sequences*, aka regex schemas. 

### Done stuff

* [#212](https://github.com/metosin/malli/pull/212) merged in master, closing 2 other PRs and 3 Issues
* [#251](https://github.com/metosin/malli/pull/251) transformation terms
* [#250](https://github.com/metosin/malli/pull/250) api-docs for public api
* [#116](https://github.com/metosin/malli/issues/116) all known issues for initial alpha are now resolved!

### Ongoing

* [#252](https://github.com/metosin/malli/pull/252) 
  * adding support for lazy registeries (suppors pulling e.g.AWS CloudFormation Schemas in at runtime, when needed)
  * lazy `:multi` impl
  * first class support for String Schema references
  * spell-specking of `:multi` and `:enum` values

```clj
;; given a lazy registry is provided, pulling the schemas when needed
(def Schema
  [:multi {:dispatch :Type, :lazy true}
   "AWS::ApiGateway::Stage"
   "AWS::ApiGateway::UsagePlan"
   "AWS::AppSync::ApiKey"])

(-> (m/explain
      Schema
      {:Type "AWS::AppSync::ApiKey"
       :Descriptionz "kikka"})
    (me/with-spell-checking)
    (me/humanize))
; ...loaded "AWS::AppSync::ApiKey"
; => {:ApiId ["missing required key"]
;     :Descriptionz ["should be spelled :Description"]}

(-> Schema
    (m/explain
      {:Type "AWS::ApiGatway::UsagePlan"
       :Description "kikka"
       :UsagePlanName "kukka"})
    (me/with-spell-checking)
    (me/humanize))
; => {:Type ["did you mean AWS::ApiGateway::UsagePlan"]}
```

* **WIP**: Heterogenous sequences, aka regex schemas. Studied implementation of [clojure.spec](https://clojure.org/guides/spec), [plumatic schema](https://github.com/plumatic/schema), [seqexp](https://github.com/cgrand/seqexp) and [minimallist](https://github.com/cgrand/seqexp) and drafted an initial clean slate impl. Validation and parsing mostly work, but lot of rough edges and explaining & generation not yet implemented. Kudos to [Vincent Cantin](https://github.com/green-coder) for the initial work on this!

```clj
;; internal api
(re/parse
  (re/* (re/cat [:prop (re/fn string?)]
                [:val (re/alt [:s (re/fn string?)]
                              [:b (re/fn boolean?)])]))
  ["-server" "foo" "-verbose" true "-user" "joe"])
;[{:prop "-server", :val [:s "foo"]} 
; {:prop "-verbose", :val [:b true]} 
; {:prop "-user", :val [:s "joe"]}]

; malli with named branches
[:* [:cat*
     [:prop string?]
     [:val [:alt*
            [:s string?]
            [:b boolean?]]]]]
            
; malli with indexed branches
[:* [:cat string? [:alt string? boolean?]]]
```

#### Misc

All old issues for first alpha are now resolved. Will release it soon, without the regexs (take some time to finish)



### **September 16-30**

Polishing the code, updated dependencies, updated libraries using malli, helped people on slack, though about time, lot's of small improvements, curving out bundle size on js, added `type-properties`, rewrote a lot of core code to use it, started to write alpha release blog post.

### Done stuff

* [kwrooijen/gungnir#31](https://github.com/kwrooijen/gungnir/pull/31) - update & test Gugnir to use latest malli
* [lambdaisland/regal#20](https://github.com/lambdaisland/regal/pull/20) - update & test Regal to to use latest malli
* [metosin/reitit#fbff819](https://github.com/metosin/reitit/commit/fbff819909dd8a6b325678f1ed5c49b1f5d8034f) - update & test Reitit to use latest malli
* [#260](https://github.com/metosin/malli/pull/260) - `:map-of` keys should decode like String with JSON  
* [#116](https://github.com/metosin/malli/issues/116) - just done
* [#254](https://github.com/metosin/malli/issues/254) - easy way to attach custom errors to Schemas
* [#253](https://github.com/metosin/malli/issues/253) - spell checking for :multi map dispatch keys
* [#252](https://github.com/metosin/malli/pull/252) - lazy registries & multis
* [#25](https://github.com/metosin/malli/issues/25) - support core predicates or just keyword types?
* [#244](https://github.com/metosin/malli/issues/244) - won't fix, too special case

### Ongoing

* Writing the alpha release post. Have found out many small inconsistencies and even bugs while writing sample code into the post, need to fix all of those to get the library & post out. Still, really happy how it's all clicking together.

* Designing how to support schemas for time and dates [#49](https://github.com/metosin/malli/issues/49). There is `java.time`,  on JS, [Temporal](https://github.com/tc39/proposal-temporal) is coming, but while waiting, we have [tick](https://github.com/tc39/proposal-temporal) and [cljc.java-time](https://github.com/henryw374/cljc.java-time), but they add a whopping 40-50kb gzipped to bundle size (currently, malli starts ~2kb gzipped). Good discussion about this in [clojureverse](https://clojureverse.org/t/progress-update-on-the-clojure-script-date-time-libraries/5344/6).

* carving out js bundle size with great tooling & tips by [Thomas Heller](https://github.com/thheller) via slack. This could be done later, but is so much fun.

#### Misc

Thought releasing a library was easy, but want to do it too(?) right, seems to take time more than expected. Had a flu.





# Practicallli

### **September 1-15**

Invested time to understand the changes coming to the Clojure CLI tools and understand the opportunities that [Clojure exec](https://insideclojure.org/2020/09/04/clj-exec/) (`:exec-fn` & `:exec-args`) brings to aliases. These changes provided a catalyst to start redesigning the aliases used in [practicalli/clojure-deps-edn](https://github.com/practicalli/clojure-deps-edn/tree/qualified-alias-keywords-and-new-flags).


### Practicalli Study Group
Continuing the Banking on Clojure project, especially around database access.  The database schema was refined along with improved approaches to creating database schema, using transactional updates and ensuring connections are closed.  The next.jdbc friendly functions were explored and a CRUD approach implemented.

- [082 - Banking On Clojure - Part3 - next.jdbc for SQL in Clojure](https://youtu.be/sBdmwDUp1Ho)
- [083 - Banking On Clojure - Part4 - Updating data in the database](https://youtu.be/DmYlNTe7Gds)

Next steps will be to use Clojure spec for generative testing with the database, add connection pooling and using lifecycle management libraries.


### Practicalli WebApps
Updated sections on [H2 Database](https://practicalli.github.io/clojure-webapps/relational-databases-and-sql/h2-database/) and Banking on Clojure

- [Design and Create Database Tables](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/database-tables.html)
- [Defining Database Queries - different approaches](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/database-queries.html)
- [Create database records](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/create-records.html)
- [Read Database Records](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/read-records.html)
- [Update Records in the database](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/update-records.html)
- [Delete Records in the database](https://practicalli.github.io/clojure-webapps/projects/banking-on-clojure/delete-records.html)


### Practicalli Clojure
Added [core principles for writing effective unit tests](https://practicalli.github.io/clojure/testing/unit-testing/) in Clojure. Included project configuration examples of unit tests for Leiningen and Clojure CLI tools.  Also included example configuration for Emacs CIDER test runner and link to the test runner configurations in [practicalli/clojure-deps-edn](https://github.com/practicalli/clojure-deps-edn/tree/qualified-alias-keywords-and-new-flags).


### Clojure deps.edn
Created a [draft guide to changes in the next release of the Clojue CLI tools](https://gist.github.com/jr0cket/ae7dd745eb45870109ace59fe835ce80), to understand the significance of the changes it introduces.  The `-X` flag for executing a function with EDN arguments (hash-map arguments) has already been introduced and the new release depreciates the generic `-A` alias in favor of `-M`.  In the long term using the `-X` flag with functions that take a structured argument looks to be an excellent approach.

Started a [redesign of practicalli/clojure-deps-edn using qualified keywords for alias names](https://github.com/practicalli/clojure-deps-edn/tree/qualified-alias-keywords-and-new-flags).  The Library repositories keyword, `:mvn/repos`, is already qualified, so this redesign brings the aliases in line with that style.  The alias keywords are prepended with names to communicate the category of purpose for each alias, e.g. repl, project, env, lib, inspect, build, deploy.

As part of the redesign, the new flags introduced with Clojure CLI tools, `-M` and `-X`, are used.  The `-X` flag is the preferred Clojure command line flag to use for the aliases, where the library supports executing a function from the library that takes a hash-map of arguments.  Otherwise `-M` flag replaces `-A` flag and continues to use `clojure.main` to call the `-main` function of the given main namespace.

Add project/check to give detailed report on compilation issues for a project


### Practicalli Spacemacs
Resolved simple bug fixes raised by the community.



### **September 16-30**

### Practicalli Study Group
Continuing the Live broadcasts covering the developmment of the Banking on Clojure web application.
- [084 - Banking On Clojure - Part5 - Generated database records from clojure.spec](https://youtu.be/Cn5QX9nL7jU)
- [085 - Banking On Clojure - Part6 - Refactor database schema, specs and namespaces](https://youtu.be/e4QInyWa1bM)


### Practicalli Clojure
New sections and Pages:
* [Coding Challenges section](http://practicalli.github.io/clojure/coding-challenges/)
* [Unit testing fixtures](http://practicalli.github.io/clojure/testing/unit-testing/fixtures.html)

Added Code Challenges section, covering the Clojure challenges available in 4Clojure.com, Exercism.io, CodwWars.com, ClojureScript Koans and Advent of Code.  A quick guide to using each of the Code challenge websites was provided and tips to using them effectively.  GitHub code repositories for the Practicalli [4Clojure guides](https://github.com/practicalli/four-clojure/), [codewars-guides](https://github.com/practicalli/codewars-guides) and [exercism-guides](https://github.com/practicalli/exercism-clojure-guides) were included, along with the [4Clojure guides video playlist](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiDB_KGrbliCsCUrmcBvdW16) which walks through the solution to over 60 challenges and different approaches to solving them. Several 4Clojure and Exercism challenges have been added as solution walk-through, showing the design thinking behind the solution in the website.  More of these will be converted from the solution code repositories as time allows, along with a video showing the REPL driven development experience.

Added [Unit testing fixtures](http://practicalli.github.io/clojure/testing/unit-testing/fixtures.html) page with examples from Banking on Clojure project.  Also mentioned test selectors as a way to organize slower fixtures.

Started creating project templates for use with clj-new, to create deps.edn projects useful for beginners and experienced developers alike.  A section on writing your own custom templates will be added to the Practicalli Clojure book in October.

Continuing to create scripts for video screencasts for a series on the Clojure CLI tools, covering the usage from the latest release (1.10.1.697). The series will convey the developer experience and common practices.

Continue testing Clojure CLI pre-release and enhancing the aliases in practicalli/clojure-deps-edn, no issues found so far.


### Clojure WebApps
Redesign the database schema and clojure.spec specifications to simplify the use of generative testing with specifications and in general make the specifications easier to work with.

Using generative testing with the database.  Specifications are used to generate random data to test the database CRUD functions, validating the results of those functions against clojure specs.

Added code to create and delete the development database which is called from fixture functions within the handler-helper-test namepace.  The tests now run successfully via the CirceCI service.

Using kaocha profiles to configure different behavior in the development environment and when running on the CI server, specifically file change watcher and test output.


### Practicalli Spacemacs
Now Emacs 27 is the default, trying out Ligature support in Emacs.  Added the unicode layer with variables to include ligatures.  Initially switching to the Fira code font which contains a wide range of ligatures in the font already.

Will try the [Ligaturizer project](https://github.com/ToxicFrog/Ligaturizer) to add Fira Code ligatures to the Ubuntu Mono font, the preferred font used by Practicalli.





# Clj-kondo/babashka/sci

### **September 1-15**

Here is an overview of the work I did per project. In the last two weeks I spent
most of my time focusing on a new release for clj-kondo.

### Clj-kondo

- Add `--parallel` option to lint sources in parallel. This will speed up
  linting an entire classpath. [#632](https://github.com/borkdude/clj-kondo/issues/632), [#972](https://github.com/borkdude/clj-kondo/issues/972)

- Fix false positive type warning with `into` and custom transducer [#952](https://github.com/borkdude/clj-kondo/issues/952)

- Recognize aliases in quoted keywords [#981](https://github.com/borkdude/clj-kondo/issues/981)
e

- Support ignore hints [#872](https://github.com/borkdude/clj-kondo/issues/872):

    ``` clojure
    (inc 1 2 3)
    ^--- clojure.core/inc is called with 3 args but expects 1

    #_:clj-kondo/ignore
    (inc 1 2 3)
    ^--- arity warning ignored

    #_{:clj-kondo/ignore[:invalid-arity]}
    (do (inc 1 2 3))
    ^--- only redundant do is reported, but invalid arity is ignored
    ```
  Also see [config.md](doc/config.md).

- Merge config from `$HOME/.config/clj-kondo`, respecting `XDG_CONFIG_HOME`. See
  [config.md](doc/config.md) for details. [#992](https://github.com/borkdude/clj-kondo/issues/992)

- New `:config-paths` option in `<project>/.clj-kondo/config.edn`. This allows
  extra configuration directories to be merged in. See
  [config.md](doc/config.md) for details.

- [Config tool](https://github.com/clj-kondo/config) that can spit out library
  specific configurations that can be added via `:config-paths`. Contributions
  for libraries are welcome.

- [Clj-kondo inspector](https://github.com/clj-kondo/inspector), a POC of
  inspecting Clojure specs and turning them into clj-kondo type annotations.

- Rum hooks for `defc` and `defcs` now support multi-arity definitions [#987](https://github.com/borkdude/clj-kondo/issues/987)

### Babashka

- Made differences with Clojure on the JVM clearer in README
- Investigate inclusion of http-kit client [#561](https://github.com/borkdude/babashka/issues/561)
- Investigate inclusion of http-kit server [#556](https://github.com/borkdude/babashka/issues/556)
- Bumped clj-yaml to 0.7.2 [#562](https://github.com/borkdude/babashka/pull/562)
- Reported bug and implemented patch for test.check related to GraalVM native-image [TCHECK-157](https://clojure.atlassian.net/browse/TCHECK-157)
- Error when using `use` [#565](https://github.com/borkdude/babashka/issues/565)

### Sci

- Experimented with faster loop implementation [#394](https://github.com/borkdude/sci/issues/394)
- Hammock time: controlling number of iterations or execution time length [#348](https://github.com/borkdude/sci/issues/348)
- Hammock time: resolving external macro namespaces [#397](https://github.com/borkdude/sci/issues/397)
- Throw when trying to re-define referred var [#398](https://github.com/borkdude/sci/issues/398)
- Fix libsci, an example of how to use sci as a shared native library (https://github.com/borkdude/sci/commit/d0b10f25bbbd6b09fb5c4ac99cf9887d33115845)


### **September 16-30**

Here is an overview of the work I did per project. Most of the time went into
improvements to sci and a [new babashka release](https://github.com/borkdude/babashka/releases/tag/v0.2.1).

### Babashka

- Include `org.httpkit.client`, a high performance async http client [#561](https://github.com/borkdude/babashka/issues/561)
- Include `org.httpkit.server`, an HTTP server
  [#556](https://github.com/borkdude/babashka/issues/556). This namespace should
  be considered experimental and may stay or be removed in a future version of
  babashka, depending on feedback from the community. See [example](examples/httpkit_server.clj)
- Add `java.io.FileNotFoundException`, `java.security.DigestInputStream`, `java.nio.file.FileVisitOption` classes
- Support implementing `IDeref`, `IAtom` and `IAtom2` on records [sci#401](https://github.com/borkdude/sci/issues/401)
- Support compatibility with [version-clj](https://github.com/xsc/version-clj) [#565](https://github.com/borkdude/babashka/issues/565) [@lread](https://github.com/lread) and [@borkdude](https://github.com/borkdude)
- Support YAML roundtrip through `*input*` [#583](https://github.com/borkdude/babashka/issues/583)
- Support `clojure.core/find-var` [sci#420](https://github.com/borkdude/sci/issues/420) [@RickMoynihan](https://github.com/RickMoynihan)
- Fix location printing in REPL (`--repl`) [#598](https://github.com/borkdude/babashka/issues/589)
- Babashka.curl sends form params incorrectly as multipart [babashka.curl#25](https://github.com/borkdude/babashka.curl/issues/25)
- Update Windows build instructions [#574](https://github.com/borkdude/babashka/issues/574)
- Set minimum macOS version in build explicitly [#588](https://github.com/borkdude/babashka/pull/588)
- Fix NPE in error handling logic [#587](https://github.com/borkdude/babashka/issues/587)
- Fix namespace switch in REPL (`--repl`) [#564](https://github.com/borkdude/babashka/issues/564)
- Fix location of errors in REPL (`--repl`) [#589](https://github.com/borkdude/babashka/issues/589)
- https://github.com/borkdude/babashka/issues/571

### Sci

- Expose `parse-string`, `reader`, `parse-next` and `eval-form` [#404](https://github.com/borkdude/sci/issues/404)
- Added REPL docs [here](https://github.com/borkdude/sci#repl) and examples [here](https://github.com/borkdude/sci/tree/688a4addda7cb9e630a49042743ae254571ac5f0/examples/sci/examples)
- Namespace dependency tree [example](https://github.com/borkdude/sci/blob/688a4addda7cb9e630a49042743ae254571ac5f0/examples/sci/examples/ns_tree.clj)
- Support multi-arity methods in `defprotocol` [sci#406](https://github.com/borkdude/sci/issues/406)
- Constructor call not recognized in protocol impl [sci#419](https://github.com/borkdude/sci/issues/419)
- Improve handling of top-level do in macro expansion [sci#421](https://github.com/borkdude/sci/issues/421)
- Performance improvements suggested by [@joinr](https://github.com/joinr) [sci#415](https://github.com/borkdude/sci/issues/415)
- Throw when trying to redefine referred var [sci#398](https://github.com/borkdude/sci/issues/398)

- Xterm-sci: https://github.com/babashka/xterm-sci
- Eval single quote ' should return eof https://github.com/borkdude/edamame/issues/61

- Fix import from hyphened namespace https://github.com/borkdude/sci/issues/410

### Misc

- dynaload 0.2.0, 0.2.1 and 0.2.2: https://github.com/borkdude/dynaload#graalvm
- https://clojure.atlassian.net/browse/CLJ-2582




# Datahike

### **September 1-15**

Due to vacation in our team we could only work on the implementation of [entity specs](https://github.com/replikativ/datahike/issues/197).

### Entity Specs

The required attributes and entity predicates were [implemented](https://github.com/replikativ/datahike/pull/229), added to Datahike `0.3.2-SNAPSHOT`, and will be release with Datahike 0.3.2. Users can now create new specs with `:db.entity/attrs` and `:db.entity/preds` and assert entities in transactions with `:db/ensure`.

Beyond implementation, we cleaned out older stale branches and PRs. Also we started planning our next feature roadmap after `0.3.2` release.



### **September 16-30**

Everybody is back from vacation now and we continued working together again on the open topics.

### Release 0.3.2

We released our latest version [0.3.2](https://github.com/replikativ/datahike/releases/tag/v0.3.2) that added entity specs that were implemented in Datahike in the last iteration as well as the following updates that we worked on over the last months:

- fixed hash computation
- improved printer
- fixed history upsert
- added database name to environ
- added circle ci orbs for ci/cd across all libraries
- fixed reverse schema update
- added automatic releases
- added benchmark utility
- extended time variance test
- updated dependencies
- adjusted documentation

### Datomic Compatibility

Within our [comparison project](https://github.com/lambdaforge/vertailu) we created a document on comparing functions from both Datahike and Datomic, and continued discussing adjustments both for signature and naming. Also we [started](https://github.com/TimoKramer/datahike/tree/add-datomic-compatibility) adjusting our API documentation in order to provide better examples on how to use them. 

### Tuple Support

The schema definition for composite tuples was added to the transaction validation, while the definition for heterogenous and homogenous tuples are still drafted and tested. We started discussing the design and architecture for the search of the tuples in Datahike's index for both read and write schema flexibility.

### Beyond Clojurists Together Tasks
Within the task of porting of our own dependencies to ClojureScript, we unified the CI/CD for circleci, moving from leiningen to Clojure CLI tools.
Continued with improving the [upsert performance](https://github.com/replikativ/datahike/pull/201).
We started discussions on features and next steps for [datahike-server](https://github.com/replikativ/datahike-server) and possible http clients. 
Worked on [garbage collection](https://github.com/replikativ/datahike/pull/232) for our backend. 
Continued on the larger tasks for [attribute reference support and partitions](https://github.com/replikativ/datahike/tree/211-attr-refs) that also incorporates vast refactoring of Datahike's tests. 





