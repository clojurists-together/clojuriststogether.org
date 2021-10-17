---
title: "October 2021 Monthly Update"
date: 2021-10-17T09:30:00+08:00
author: Alyssa Parado
summary: Read updates from Clojure LSP, Polylith, Holy Lambda, and Typed Clojure.
draft: true
---

#### Here are the project updates from the month of September to the first half of October.

# Clojure LSP

This was the month with the most changes, updates, and fixes of the project! 
Also, this is the new clojure-lsp webpage: https://clojure-lsp.io

### release [2021.09.13-19.32.00](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2021.09.13-19.32.00)

This release added a new `diagnostics` feature to clojure-lsp API, making clojure-lsp the only tool that can do most Clojure lint tasks in a single tool!
Besides that, it was fixed a lot of issues and improved some defaults to make clojure-lsp a tool that just works out of the box along with some preparations for changing the way clojure-lsp persists the analysis cache.

Here is the changelog of this release:

- General
  - Create .clj-kondo folder if not exists in the project root. #528
  - Fix exception when `:clojure-lsp/unused-public-var` linter is `:off`. #546
  - Bump clj-kondo to `2021.08.07-20210903.210340-28` to fix a false-positive with potemkin. https://github.com/clj-kondo/clj-kondo/issues/1370.
  - Bump clj-kondo to `2021.08.07-20210909.131804-29` fixing issues with built-in clj-kondo cache not present on graalvm binaries. #548 
  - Exclude `cljs.test/deftest` from unused public vars linter.
  - Migrate default db file from `.lsp/sqlite.db` to `.lsp/.cache/sqlite.db`, this is necessary as in the future we will replace sqlite with other db implementation, for users they just need to consider/gitignore the `.lsp/.cache` folder, this way any next change on db implementations or any other cache will not affect user.
  - Auto migrate existing `.lsp/sqlite.db` to new `.lsp/.cache/sqlite.db` to avoid unnecessary project re-scan.
  - Deprecates `:sqlite-db-path` in place of `:cache-path`.

- Editor
  - Fix `didChangeWatchedFiles` to correctly create the file on the server, properly change file content and re-scan with clj-kondo, or remove file analysis. This should improve LSP analysis reliability when changing files outside the editor. #536
  - Improve completion only showing valid local vars for the current cursor.
  - Improve completion sorting adding priority to each item, showing most used symbols like variables and functions first before other completion items.
  
- API/CLI
  - New `diagnostics` command, which returns all diagnostics found by clojure-lsp (using clj-kondo). Check the API section for more details.
  

### release [2021.09.30-12.28.16](https://github.com/clojure-lsp/clojure-lsp/releases/edit/2021.09.30-12.28.16) 

This release focused on improving user UX giving better feedback during clojure-lsp startup both on API and editor. Also, it fixed a lot of issues making clojure-lsp a more mature tool with fewer false positives and more settings to work with more Clojure projects.

- General
  - Use lower-case for refer/import/require sorting. #560 #561
  - Avoid removing comments when sorting/cleaning namespace. #559
  - Break lines when sorting refers along with then new `:clean :sort :refer :max-line-length` setting with a default of `80`. #562
  - Deprecate `lens-segregate-test-references` in favor to `:code-lens :segregate-test-references`
  - Check for a default `.cljfmt.edn` config file for cljfmt config settings if no `:cljfmt-config-path` was provided. #563
  - Bump clj-kondo to `2021.09.25` fixing false-positives with potemkin import-var analysis.
  - Re-scan whole project if any clj-kondo config changed for better consistence. #331
  - Fix clojure-lsp not initializing when empty `project.clj`. #579
  - Support finding config in classpath via new setting`:classpath-config-paths ["my-org/my-lib"]`. #580

- Editor
  - Fix `resolve macro as` code action after regression introduced recently.
  - Fix `unused-public-var` not being suppressed during project startup. #554
  - Improve `hover` feature to return elements when inside a function call. #569
  - Fix `create-private-function` command and code action to consider when new function is inside thread macros.
  - Support `$/progress` LSP feature, sending notifications for client when server is starting, improving the feedback for the user.
  - Improve semantic tokens support for java classes and methods.
  
- API/CLI
  - Support renaming namespaces as well with `rename` feature.
  - Use relative paths instead of absolute paths on diff messages.
  - Add `analyze-project!` to analyze whole project only and cache analysis, useful for REPL usage for example. 
  - Follow same exit status from clj-kondo for `diagnostics` feature. #572
  - Improve start project feedback reporting the percentage and specific message.





# Polylith

I've released [version 0.2.13-alpha](https://github.com/polyfy/polylith/releases/tag/v0.2.13-alpha) of the poly tool, which includes the interactive shell command and a few more issues:

* Shell command with history and auto-complete, issue [#106](https://github.com/polyfy/polylith/issues/106)
* Show Indirect changes for projects, Issue [#124](https://github.com/polyfy/polylith/issues/124)
* Move the poly documentation to gitbook, issue [#128](https://github.com/polyfy/polylith/issues/128)
* Ignore top level data readers, issue [129](https://github.com/polyfy/polylith/issues/129)
* Polylith should manage its own brew tap, issue [#131](https://github.com/polyfy/polylith/issues/131)
* Use tools.build, issue [#134](https://github.com/polyfy/polylith/issues/134)
* Restrict naming of bricks, issue [#135](https://github.com/polyfy/polylith/issues/135)
* Use deps and paths in development when running tests, issue [#137](https://github.com/polyfy/polylith/issues/137)
* Symbolic links to non-existing source files throws exception, issue [#138](https://github.com/polyfy/polylith/issues/138)





# Holy Lambda

I've successfully refactored the holy-lambda core. Handlers can now be defined via regular `defn` and composed freely before passing to the `entrypoint` macro. The only functionality exposed from the core is `entrypoint` that now accepts additional parameter `init-hook` for side effect initialization of dependencies before the runtime loop executes. The following addition is handy if you want to initialize dependencies only once.

I've also decoupled holy-lambda from AWS SAM. As a result, tasks are faster, less error-prone, and minimal. My plans for the next few months are to remove problematic tasks: `hl:sync` & `hl:compile` and focus almost entirely on runtime. However, I plan no changes around native compilation and native configuration generation.

I've also published a brand new documentation, which is available [here](https://fierycod.github.io/). Documentation is almost complete, but additional explanations for the native backend regarding the java agent are required. 

**Some other changes**:
 - Bump of the docker images:
  - :ce - GraalVM CE 21.2.0
  - :dev - GraalVM DEV 21.3.0-dev-20210910_2147
 - Babashka backend now supports bb 0.6.1
 - I've submitted the [patch](https://github.com/metosin/jsonista/pull/58) to `jsonista` to make the library compatibile with GraalVM >= 22.0, and got accepted. This is important, because HL will remain compatibile with future GraalVM versions and no additional configurations for native-image will be required.

I'm also finishing the benchmarks for the new release. I hope the new release to be a much faster one, especially for the subsequent handler calls. The results will be published in the next month of funding.

Next month I'm also planning to release an Ring adapter for HL. The significant progress has been done in that area, so stay tuned.





# Typed Clojure

The goal of [this project funded by Clojurists Together](https://www.clojuriststogether.org/news/q3-2021-funding-announcement/) is to
improve static type error messages in [Typed Clojure](https://github.com/typedclojure/typedclojure),
specifically to replace expanded code in error messages with surface-level syntax.

In the first half of the project, I have concentrated on three main areas:
1. Increase direct support for problematic clojure.core macros
2. Improve error messages for inlining functions
3. Identify classes of implementation quirks in core Clojure macros to prepare for potential typing rules

### Increase direct support for problematic clojure.core macros

**Problem**: Typed Clojure expands macros that it does not have special rules for. This
works well when the expansion is simple (eg., `binding`, `future`, or `delay`), but this strategy
backfires horribly for complex macros like `doseq`.

For example, `doseq` does not have direct support in Typed Clojure `1.0.17` and any usage of it results
in an incomprehensible error message (note: `t/cf` type [c]hecks a [f]orm):

```clojure
$ clj -Sdeps '{:deps {org.typedclojure/typed.clj.checker {:mvn/version "1.0.17"}}}}'
Clojure 1.10.3
user=> (require '[clojure.core.typed :as t])
nil
user=> (t/cf (doseq [a [nil :a 3]] (inc a)))
Type Error (NO_SOURCE_PATH:1:7)
Loop requires more annotations


in:
(loop*
 [seq_30744
  (clojure.core/seq [nil :a 3])
  chunk_30745
  nil
  count_30746
  0
  i_30747
  0]
 (if
  (clojure.core/< i_30747 count_30746)
  (clojure.core/let
   [a (.nth chunk_30745 i_30747)]
   (do (inc a))
   (recur
    seq_30744
    chunk_30745
    count_30746
    (clojure.core/unchecked-inc i_30747)))
  (clojure.core/when-let
   [seq_30744 (clojure.core/seq seq_30744)]
   (if
    (clojure.core/chunked-seq? seq_30744)
    (clojure.core/let
     [c__6014__auto__ (clojure.core/chunk-first seq_30744)]
     (recur
      (clojure.core/chunk-rest seq_30744)
      c__6014__auto__
      (clojure.core/int (clojure.core/count c__6014__auto__))
      (clojure.core/int 0)))
    (clojure.core/let
     [a (clojure.core/first seq_30744)]
     (do (inc a))
     (recur (clojure.core/next seq_30744) nil 0 0))))))



Execution error (ExceptionInfo) at clojure.core.typed.errors/print-errors! (errors.cljc:274).
Type Checker: Found 1 error
```

We need explicit support for `doseq` and similar macros to both improve inference and error messages.

**Prior work**: Before custom typing rules were possible in Typed Clojure, an alternative macro `clojure.core.typed/doseq`
was provided for compatibility with the type checker. For example, instead of `(doseq [a [1]] ..)` you would write `(t/doseq [a :- Int, [1]] ...)`.

This macro had some downsides:
1. all bindings required annotations.
2. it had poor discoverability as `doseq`'s error message makes no mention of this alternative.

**Approach**: Create typing rule for `doseq` and create or enhance typing rules for other  problematic `clojure.core` macros.

**Results**:

`doseq` is now supported and error messages are pleasant. Note the error msg for `inc` is also new--see next section.

```clojure
$ clj -Sdeps '{:deps {org.typedclojure/typed.clj.checker {:mvn/version "1.0.19"}}}}'
Clojure 1.10.3
user=> (require '[clojure.core.typed :as t])
nil
user=> (t/cf (doseq [a [nil :a 3]] (inc a)))
Type Error (NO_SOURCE_PATH:1:29)
Function inc could not be applied to arguments:


Domains:
        Number

Arguments:
        (t/U (t/Val 3) (t/Val :a) nil)

Ranges:
        Number




in:
(inc a)



Execution error (ExceptionInfo) at clojure.core.typed.errors/print-errors! (errors.cljc:276).
Type Checker: Found 1 error
```

**Commits**:
- [doseq rule + tests](https://github.com/typedclojure/typedclojure/commit/c2ee870edfd76a98d4f0d763aae4814b9f9250ef)
- [support clojure.core/for :when/:while/:let](https://github.com/typedclojure/typedclojure/commit/886fdcc6c182a39bae7926362cf118206d16f8e1)
- [defmethod rule](https://github.com/typedclojure/typedclojure/commit/f550286c2e39cc711c9da2b738fd60b4b8451cfa)
- [defn rule](https://github.com/typedclojure/typedclojure/compare/c2ee870edfd76a98d4f0d763aae4814b9f9250ef...b1c07def655e132a460aab324dac44f6f1df3b97)

### Improve error messages for inlining functions

**Problem**: inline functions are an experimental Clojure feature that enables the compiler to treat
a var as a macro in operator position and a function in higher-order contexts. Typed Clojure
expands inline functions for `:tag` inference purposes, but if a type error occurs in the inlined expansion, the original form is lost and the expansion
is blamed. This results in an unhelpful error message.

For example, `inc` blames its expansion `clojure.lang.Numbers/inc`:
```clojure
$ clj -Sdeps '{:deps {org.typedclojure/typed.clj.checker {:mvn/version "1.0.17"}}}}'
Clojure 1.10.3
user=> (require '[clojure.core.typed :as t])
nil
user=> (t/cf (inc nil))
Type Error (NO_SOURCE_PATH:1:7)
Static method clojure.lang.Numbers/inc could not be applied to arguments:


Domains:
        Number

Arguments:
        nil

Ranges:
        Number




in:
(clojure.lang.Numbers/inc nil)



Execution error (ExceptionInfo) at clojure.core.typed.errors/print-errors! (errors.cljc:274).
Type Checker: Found 1 error
```


**Prior work**: There is a similar problem in ClojureScript's compiler via the `js*` form. The ClojureScript analyzer added
`:js-op` metadata to the analyzed form so linters like Typed Clojure can infer better error messages. However this only
helped marginally as the expanded code was still checked, and the inlining was not always easy to infer (eg., different order
of arguments).

A briefly considered approach in fixing this problem was to define a custom typing rule for each of the ~80 clojure.core inline functions. This was
discarded in favor of the following once-and-for-all solution.

**Approach**: Check inlining _before_ expansion, and propagate tag information after type checking. This is not possible
if using tools.analyzer (as Typed Clojure did pre-2019), but is relatively straightforward with [typed.clj.analyzer](https://github.com/typedclojure/typedclojure/blob/main/typed/clj.analyzer/README.md) (see [maybe-check-inlineable](https://github.com/typedclojure/typedclojure/commit/2b3ba3bbfcf615b5d4e92b4e7bae7a356100c772#diff-a4006cf0fe797e50023948e873f147c6f37a8af7b354509709fdf29377c8954fR289) for the required juggling).

**Results**
This change improved error messages for [around 78 functions](https://github.com/typedclojure/typedclojure/commit/2b3ba3bbfcf615b5d4e92b4e7bae7a356100c772#diff-c32ff2e4f53b6e6da9e2a1b3f79e1f3f6cf7d080d7b59f9b1b682116c47c0e9dR205) in `clojure.core`. Now inline functions never blame their expansions and unsupported inline functions consistently throw type errors in first- and higher-order contexts
(instead of expanding in inline contexts and erroring in higher-order ones).

For example, `inc` now blames its form instead of its expansion (see `in: (inc nil)`).

```clojure
$ clj -Sdeps '{:deps {org.typedclojure/typed.clj.checker {:mvn/version "1.0.19"}}}}'
Clojure 1.10.3
user=> (require '[clojure.core.typed :as t])
nil
user=> (t/cf (inc nil))
Type Error (NO_SOURCE_PATH:1:7)
Function inc could not be applied to arguments:


Domains:
        Number

Arguments:
        nil

Ranges:
        Number




in:
(inc nil)



Execution error (ExceptionInfo) at clojure.core.typed.errors/print-errors! (errors.cljc:276).
Type Checker: Found 1 error
```


**Commits**
- [check inlines before expansion to improve error msg](https://github.com/typedclojure/typedclojure/commit/2b3ba3bbfcf615b5d4e92b4e7bae7a356100c772)

### Identify classes of implementation quirks in core Clojure macros to prepare for potential typing rules

**Problem**: To improve static type error messages for a macro, a custom typing rule is needed.
However, typing rules for macros need to simulate the macro expansion of the original macro accurately in order to be sound.
Some macros in clojure.core are known to [leak implementation details](https://clojure.atlassian.net/browse/CLJ-2573)--this would
influence how typing rules are written, so we need to investigate similar issues for other macros.

**Prior work**:
- https://clojure.atlassian.net/browse/CLJ-2573

**Approach**: Study the definition of macros and try and break them.

**Results**: I found 5 classes of implementation leakage in core Clojure macros.
1. In macros that wrap try/finally around a body, `catch` syntax is leaked to the user.
The following macros expand to `(try (catch Exception e :foo) (finally ...))`. In all
of these cases, `catch` is not bound so we might expect an error instead of the below behavior.
```clojure
$ clj
Clojure 1.10.3
user=> (binding [] (catch Exception e :foo))
nil
user=> (locking 1 (catch Exception e :foo))
nil
user=> (with-in-str "a" (catch Exception e :foo)) 
nil
```
2. In macros that wrap `fn` around a body, a `recur` target is available. In all of these
cases, a compilation error might be more appropriate. (also plays poorly with `:once` fns).
```clojure
$ clj
Clojure 1.10.3
user=> (delay (recur))
#object[clojure.lang.Delay 0x3d7fa3ae {:status :pending, :val nil}]
user=> (future (recur))
#object[clojure.core$future_call$reify__8477 0x5f462e3b {:status :pending, :val nil}]
user=> (do (lazy-seq (recur)) nil)
nil
user=> (let [a (Object.)] @(delay (when a (recur)))) ;; infinite loop? no: `^:once fn*` clears bindings.
nil
user=> (let [a (Object.)] (lazy-seq (when a (recur))))
()
```
3. In macros that wrap `fn` around a body, `:pre`/`:post` syntax is leaked to the user.
```clojure
$ clj
Clojure 1.10.3
user=> (with-bindings [] {:pre [false]} 1)
Execution error (AssertionError) at user/eval164$fn (REPL:1).
Assert failed: false
```
4. Double macro expansion and evaluation.
```clojure
$ clj
Clojure 1.10.3
user=> (vswap! (do (prn "created") (volatile! 0)) inc)
"created"
"created"
1
```
5. Unreliable `:tag` propagation.
```clojure
$ clj
Clojure 1.10.3
user=> (set! *warn-on-reflection* true)
true
user=> (defmacro id [a] a)
#'user/id
user=> (vswap! (id (identity (volatile! 0))) inc)
Reflection warning, NO_SOURCE_PATH:1:1 - reference to field deref can't be resolved.
Reflection warning, NO_SOURCE_PATH:1:1 - call to method reset can't be resolved (target class is unknown).
1
```

As a result of this (and some prior) work, the following macros are now known to leak implementation details in some combination of the aforementioned ways and need special
handling in potential typing rules:
- `locking` ([upstream report](https://clojure.atlassian.net/browse/CLJ-2573)), `binding`, `with-bindings`, `sync`, `with-local-vars`, `with-in-str`, `dosync`, `with-precision`, `with-loading-context`, `with-redefs`, `delay`, `vswap!`, `lazy-seq`, `lazy-cat`, `future`, `pvalues`, `clojure.test/{deftest,deftest-,testing,with-test,with-test-out}`, `clojure.java.shell/with-sh-{dir,env}`, `clojure.test.tap/with-tap-output`, `clojure.pprint/with-pprint-dispatch`, `clojure.core.async/thread`, `clojure.core.logic.pldb/with-{db,dbs}`, `clojure.tools.trace/dotrace`, `clojure.test.check.properties/for-all`, `clojure.test.check.generators/let`, `clojure.java.jmx/with-connection`, `clojure.core.match.debug/with-recur`

**Reference**:
- [non-leaky clojure.core macros](https://frenchy64.github.io/fully-satisfies/latest/io.github.frenchy64.fully-satisfies.non-leaky-macros.clojure.core.html)
- [non-leaky clojure.test macros](https://frenchy64.github.io/fully-satisfies/latest/io.github.frenchy64.fully-satisfies.non-leaky-macros.clojure.test.html)




