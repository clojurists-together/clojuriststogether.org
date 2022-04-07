---
title: "March 2022 Monthly Update"
date: 2022-04-08T04:30:00+08:00
summary: Read the first update from one of our 2022 Q1 projects Clojure-LSP, and the updates from the remaining 2021 projects Malli and our long term projects with Michiel Borkent, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov
author: Alyssa Parado
draft: true
---

## Biff

At the start of the project, I had mostly finished updating Biff's library code.
Since then, I've finished updating the template project. As a refresher, some
of the changes from the previous version of Biff include:

 - No more ClojureScript or React. Just server-side rendering with HTMX. This
   has let me delete a bunch of code. The template project project still
   includes the same real-time chat example, all done with HTMX now. It's
   wonderful. See
   [https://biffweb.com/docs/#htmx](https://biffweb.com/docs/#htmx) for some
   code examples that are mostly the same as what's in the template project.
   You can also take a look at
   [app.clj](https://github.com/jacobobryant/biff/blob/dev/example/src/com/example/feat/app.clj)
   from the template project (search for `:hx-`).

 - The entire project has been structured to accommodate "developing in prod."
   You can run `./task prod-dev` which will watch your local files for changes,
   rsync them to the server, and then run a command on the server via nREPL to
   evaluate all the changed files.

So the code is pretty much complete. There's just a handful of cleanup things
that I'll do right before the release. (I also still need to test everything on
Mac, especially develop-in-prod). Besides that:

 - I've made a [spiffy new website](https://biffweb.com/).
 - I set up [a Biff newsletter](https://biffweb.com/newsletter/) and published
   a funding announcement.
 - I have *almost* finished rewriting [the
   documentation](https://biffweb.com/docs/) from scratch (I just need to
   finish the "System Composition" and "Production" sections at the end).

Once the main documentation page is complete, I'll write a bunch of doc strings
and update the [API docs](https://biffweb.com/api/) page. Then it'll be time to
release.





## Orchard

I've invested a day to look into my preferred method of solving this: inspecting the produced JS objects inside the running VM, in a similar manner to the JVM implementation.

It turns out even the only kind of viable way to do that is [Function.caller](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function/caller) which has multiple problems:

1. It's desecrated
2. We'd have to rewrite every generated JS function to add this
3. We would have to execute every function
4. Worst: There is no general way to even run every code path

#### Further investigation

The next possibility I will check is a hybrid version, which is less useful but still a lot better than nothing:
Using the cljs compiler to provide the references while compiling the code.

This is not ideal since it doesn't allow investigating code that is already running and will probably not allow investigating hierarchies inside JS code. It should be good enough for most consumers of orchard though since it's mostly used during development.





## Typed Clojure

The goal of [this project funded by Clojurists Together](https://www.clojuriststogether.org/news/q1-2022-funding-announcement/) is to
(resurrect) support for type checking ClojureScript files in [Typed Clojure](https://github.com/typedclojure/typedclojure).

[Roughly 10,000 lines](https://github.com/typedclojure/typedclojure/compare/25a3433b1c0c05c4f06e2583440713834371d8c8...f56fe2c4eab493ab0ff4661c71e16412008fed9e) of refactoring, improvements, and feature work has culminated to a [working minimal project](https://github.com/typedclojure/typedclojure/tree/f56fe2c4eab493ab0ff4661c71e16412008fed9e/example-projects/minimal) that can check a `.cljc` file in both Clojure and ClojureScript.

The [work](https://github.com/typedclojure/typedclojure/blob/f56fe2c4eab493ab0ff4661c71e16412008fed9e/doc/clojurists-together-q3-2021-update2.md) I completed for the previous Clojurists Together funding for Typed Clojure has been transferred to ClojureScript, as my [proposal](https://www.clojuriststogether.org/news/q1-2022-funding-announcement/) speculated. The [minimal project](https://github.com/typedclojure/typedclojure/tree/f56fe2c4eab493ab0ff4661c71e16412008fed9e/example-projects/minimal) shows off how a type error involving `(clojure.core/inc a)` is presented as if `inc` were a regular function--in Clojure it is inlinable yet Typed Clojure prints `(inc a)` instead of `clojure.lang.Numbers/inc`, and in ClojureScript is it a macro call yet prints `((do inc) a)` instead of a `js*` call (some room for improvement).

A new macros namespace [typed.clojure](https://github.com/typedclojure/typedclojure/blob/f56fe2c4eab493ab0ff4661c71e16412008fed9e/typed/clj.runtime/src/typed/clojure.cljc)
has been created for cross-platform use. Instead of using `clojure.core.typed` or `cljs.core.typed`, you can use `typed.clojure`
and the correct implementation will be chosen automatically. A new namespace was created so then we can (eventually) target self-hosting ClojureScript forcing reader conditionals on users.

The base type environment for both Clojure and ClojureScript has been moved to [typed.ann.clojure](https://github.com/typedclojure/typedclojure/blob/f56fe2c4eab493ab0ff4661c71e16412008fed9e/typed/lib.clojure/src/typed/ann/clojure.cljc). It houses 2400 lines of annotations and serves as a real-world example of how to annotate functions, protocols, and records for multiple platforms using reader conditionals.

Typing rules for macros are now shared across Clojure and ClojureScript implementations. This means the [work](https://github.com/typedclojure/typedclojure/blob/f56fe2c4eab493ab0ff4661c71e16412008fed9e/doc/clojurists-together-q3-2021-update2.md) completed for the previous Clojurists Together project can be transferred to ClojureScript, such as the improved error messages for let-destructuring.





## Reveal

The test runner is done! New versions of Reveal ([Free 1.3.270](https://vlaaad.github.io/reveal/) and [Pro 1.3.339](https://vlaaad.github.io/reveal-pro)) provide:
- a UI to run and re-run tests and view the results in a structured tree view output;
- contextual `test` action on namespaces and vars to make running tests easier;
- `diff` action for 2-element tuples and maps with `:expected` and `:actual` keys (these are present in the test output tree);
- test sticker window that can be configured to run tests on the classpath (all or filtered based by test var/ns metadata).

Here is what it looks like:

<video controls><source src="https://vlaaad.github.io/assets/reveal/test-runner.mp4" type="video/mp4"></source></video>

![test runner](https://github.com/clojurists-together/clojuriststogether.org/blob/master/content/news/2022/test-runner.gif)

During the remaining month, I plan to revamp the Reveal site to make it more structured and approachable, and in turn, make Reveal easier to set up, understand, and use.





## Deep Diamond 

My goal with this round is to implement Recurrent Neural Networks (RNN) support in Deep Diamond.
The first month was dedicated to literature review (and other hammock-located work), exploration
of OneDNN implementation of RNN layers, and implementation of RNN prototype in Clojure in Deep Diamond.

Deep Diamond currently supports general tensor operations, fully connected NN layers, and convolutional (CNN) layers, on CPU and GPU.
Based on this, relatively stable, infrastructure, I started adding Vanilla RNN implementation backed by OneDNN (Intel, CPU).

OneDNN RNN implementation is, like all the low-level backends that leading DL frameworks use, very heavy, convoluted, and rather unclojure-y.
So it takes some time to discover how it should be properly used, and how to best hide its complexity under a nice high level Clojure.

Specifically, this was implemented in the first month of Q1:

- a prototype of developer-friendly implementation of RNN in Clojure
- Vanilla RNN support (as the stepping stone for more serious LSTM and GRU)
- The first iteration of an extension infrastructure for various backend implementations of RNN.
- a clean low-level integration with Intel's oneDNN RNN on the CPU (for Vanilla RNN).
- TESTS. Not ideal amount of, but enough for this phase.

So far, I'm pretty satisfied with the progress, as I think I have discovered the roughest edges of
Intel's implementation, and found ways to fit this into existing Deep Diamond code. I expect
the following 2 months of the project to require lots of work and tests, but I feel I shouldn't
expect nasty surprises. I believe that by the end of the funding period I'll be able to release
the version of Deep Diamond that will have the functionality I've proposed.





## Firefox

#### Custom Formatters for Firefox

Hello everyone, I am Sebastian! I am currently working on bringing Custom Formatters to Firefox, and by that allow tools like [cljs-devtools](https://github.com/binaryage/cljs-devtools) to work with Firefox.

#### What are Custom Formatters?

Custom Formatters allow to display specific objects within the DevTools using formats and styles provided by the website. Those Custom Formatters are used in different places throughout the DevTools - everywhere were the objects are displayed. Mainly this is done in the [Web Console](https://firefox-source-docs.mozilla.org/devtools-user/web_console/) and the [Debugger](https://firefox-source-docs.mozilla.org/devtools-user/debugger/).

Daniel also provided a short introduction to the feature with links to more information in his [article about the funding of this feature](https://www.clojuriststogether.org/news/funding-development-on-custom-formatters-for-firefox/).

#### Current status

In [bug 1746830](https://bugzilla.mozilla.org/show_bug.cgi?id=1746830) I introduced a preference `devtools.custom-formatters` behind which this feature is implemented. This preference can be enabled via `about:config`.

I've also added an option called *Enable custom formatters* to the [Settings panel](https://firefox-source-docs.mozilla.org/devtools-user/settings/index.html) in [bug 1746831](https://bugzilla.mozilla.org/show_bug.cgi?id=1746831). This option controls the preference `devtools.custom-formatters.enabled`.

Currently, I am working on implementing the formatting itself in [bug 1746824](https://bugzilla.mozilla.org/show_bug.cgi?id=1746824). This includes interpreting [JsonML](http://www.jsonml.org) and using it to style the object.

A work-in-progress patch of this can be seen at https://phabricator.services.mozilla.com/D140119.

If you want to follow the progress, please have a look at the [meta-issue](https://bugzilla.mozilla.org/show_bug.cgi?id=1734614).

#### What's next?

I'll finalize the patch mentioned above. This lays the foundation to display the Custom Formatters. Though by itself this change won't have a visual difference. It requires the Custom Formatters defined within the page to be interpreted. This is covered in [bug 1734840](https://bugzilla.mozilla.org/show_bug.cgi?id=1734840), which will be the next thing I am working on.





## Clojure-LSP

We had huge performance improvements and a new long waited feature requested, Java interop!

#### Release [2022.03.25-12.02.59](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2022.03.25-12.02.59)

This release fixed an annoying performance issue we had with our custom linter for unused public vars, on one of the big projects we tested where before use to take 30s to analyze async the whole project for only that linter, it takes 0.2s now!
A new feature was included, the ability to find definition of `defmulti`s, finding all `defmethod`s!
We now have more built-in completion snippets on clojure-lsp following @practicalli-john suggestions.
Regarding the API/CLI usage, we fixed a issue where could cause some false-positives regarding clean-ns and format and we started working on a big refactor, creating a new submodule that may be extracted out of clojure-lsp in the future, called `lsp4clj`, with that we could implement other LSP servers (for probably other languages) in clojure easier!

Here is the changelog of this release:

- General
  - Fix cljfmt settings merge during refresh/classpath configs merge to avoid multiple config vectors on same symbol.
  - Fix install script for aarch64. #794
  - Fix handling cljfmt config files that end in `.clj` https://github.com/weavejester/cljfmt/issues/190
  - parser: more efficiently seek to cursor position, improving performance especially in large files. #793 @mainej
  - Fix clean-ns not sorting properly node requires for cljs. #815
  - Fix move-to-let to ensure locals don't move out of scope. #830
  - Improve logic around require suggestions. #837
  - Enhance move-to-let to introduce and expand let if an existing one doesn't exist. #829
  - Bump `org.clojure/clojure` to `1.11.0`.
  - Fix move-coll-entry to maintain cursor position instead of a range. #862
  - Clean ns automatically after adding missing require/imports, enabled by default under new `:clean :after-ns-refactor` flag. #558

- Editor
  - extract-function: Fix wrong args when extracting from multi-arity fn. #683
  - extract-function: Fix wrong args when extracting after a local usage. #812 @mainej
  - move-coll-entry: clauses move intuitively in `assoc`, `case`, `cond`, and similar functions. #780 @mainej
  - move-coll-entry: fix NPE when when invoked from top-level #803 @mainej
  - Generate stubs async after startup, improving startup time. #788
  - Improve and add lots of new snippets following practicalli config. #797
  - Improve how watched new files are analyzed avoiding infinite loops and performance issues. #796
  - Avoid infinite loops when several files are changed simultaneously. #796 @mainej
  - Fix "incoming call hierarchy" not considering usages inside defmethods. #808
  - range-formatting: more efficiently locate extent of range and reduce number of calls to cljfmt, improving performance especially when formatting large ranges. #795 @mainej
  - cycle-fn-literal: *new feature* convert between function-literal syntaxes `(fn [] ...)` <-> `#(...)`. #774
  - Add find-implementation feature to `defmulti` and `defmethod`. #751
  - Make find-implementation of `defprotocol` names find its implementations and find-implementation on `deftype`/`defrecord` methods find other implementations.
  - Add new code action `Introduce let` for existing command. #825
  - Make find-implementations consider `reify`. #827
  - Fix namespace on file creation when nested source-paths are available. #832
  - unused-public-var: fix to show warnings on vars defined with declare. #840
  - unused-public-var: large performance improvements, especially for large projects. #861 @mainej

- API/CLI
  - Extract lsp4clj as a seperate library. #807 @Cyrik Supported by [Scarlet](https://www.scarletcomply.com)
  - Fix inconsistency with clean-ns/format not copying kondo configs.


#### Release [2022.03.31-14.21.14](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2022.03.31-14.21.14)

This was a release with fewer new features/improvements but things we were researching/working for a long time, with 
this release and the help from clj-kondo, we now have support for Java interop. This is a long-waited feature that now makes 
clojure-lsp be able to find definition of java classes, and even decompile the `.class` if no java source is found. We also automatically 
try to find the JDK source or download it if the setting is enabled.
This is a huge step for clojure-lsp, making it a better IDE tool and helping the development of clojure projects that use java interop, make sure to [check docs](https://clojure-lsp.io/settings/#java-support)
Besides that, we had another performance improvement regarding big projects/files, removing CPU spikes for those cases.

Here is the changelog of this release:

- General
  - Avoid high CPU usage and freezes by more efficiently finding referenced files to notify on file change. #844 @mainej
  - Bump clj-kondo to `2022.03.10-20220331.135739-32` improving java analysis and fixing a critical regression for re-frame. #888
  
- Editor
  - Add java class find-definition support, decompiling .class files when available. #762 
  - Add JDK source discoverability feature, searching for installed JDK for later analyze with clj-kondo and support java classes interop.
  - Add `:java :download-jdk-source?` setting to download JDK source after startup if not cached before globally or found locally. Disabled by default.






