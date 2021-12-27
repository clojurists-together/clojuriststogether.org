---
title: "December 2021 Monthly Update"
date: 2021-12-27T09:30:00+08:00
author: Alyssa Parado
summary: Read updates from Dependabot Core, Clojure LSP, Typed Clojure, Polylith, and Holy Lambda
draft: true
---

I hope all of you had a wonderful time with your family this holiday season. Below are the recent project updates. 



## Dependabot Core

The dependabot core project has successfully been quick-started by having a technical conversation with Daniel.

We rapidly figured out that the people at github are currently considering a major rewrite/upgrade of the current apis, one that wouldn't
"require us to write a whole bunch of ruby" we realized later on.

Shortly after, Github's stakeholders were contacted involved in the process of figuring out the best way to build the system.
No meeting has been set yet but daniel's went ahead and set up a doodle for all us.
I also made sure to create a slack channel on the clojurians slack for communications on this.

For the next weeks, we're hoping to understand where the dependabot team is heading towards in terms of designs, and hopefully be able to tag along and start writing code that would benefit from the new apis.
I'm also hoping to be able to involve as many people as possible in the work.





## Clojure LSP

#### release [2021.11.16-16.52.14](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2021.11.16-16.52.14)

This release was focused mostly on housekeeping, minor fixes and improvements to keep features more mature and stable for most cases. We had huge improvements on  keywords completion, completion snippets improvements adding documentation and other details to common clojure.core snippets, code actions tune and more.
One of the new features was the ability to suggest require an already refer required before in other namespace, a very requested feature.

Here is the changelog of this release:

- General
  - Improve rename feature to not heavily rely on valid source-paths for most cases.
  - Fix setTrace exception logs for graalvm native images.
  - Huge improvements on namespaces renames and namespaces references find. #573
  - Fix/Remove warnings during datalevin access.
  - Improve freezing for some MacOS cases. #631
  - Bump clj-kondo to `2021.10.20-20211116.110002-7` improving code parsing and other fixes.
  
- Editor
  - Fix "Add require" code actions adding multiple requires instead of the selected.
  - Improve "Add require" wording, making it easier to understand what each different action will do.
  - Smart check all available refers to require, adding refer options to `Add require` code actions. #627
  - Big improvements on keyword completions. #630
  - Add setting `keep-parens-when-threading?` to keep parens for single arity functions when threading. #636
  - Avoid adding duplicate requires when adding a new require via code action. #640
  - Improve common known snippets to replace completion items, improving completion UX. #638
 

#### release [2021.12.01-12.28.16](https://github.com/clojure-lsp/clojure-lsp/releases/edit/2021.12.01-12.28.16)

This was a important release for community, a lot of new features we added, the main highlights are: It's possible to generate stubs for specific namespaces, this is a huge step for clojure-lsp as now it's possible to get most clojure-lsp features working even for closed source libraries like `datomic.api`.
There are new code actions like `Sort map` to sort a clojure map keys alphabetically and `Create function` which creates a unknown public function on the specified namespace or even create the namespace and require it automatically if doesn't exist yet.
We improved the `deps.edn` source-path discovery, improving support mostly for mono-repo projects like `polylith`! 

Here is the changelog of this release:

- General
  - Add support for LSP method `textDocument/prepareRename` which it's the proper way to check if the rename will work correctly. #642 
  - Expose new custom method `clojure/cursorInfo/raw` for custom hack on current cursor information code. #645
  - Support stub generation using `clj-easy/stub`, adding analysis and linting support for closed sources codes like Datomic. Check `:stubs` settings for more details. #637
  - Handle config deep merge differently for collections, concating instead of overwriting. 
  - Fix unnecessary exception thrown on graal images during startup.
  - Support `deps.edn` `:local/root` source-paths discovery, improving support for monorepo projects like `polylith`. #652
  - New setting value for `:clean :sort :require`: `:lexicographic`. #654
  - Bump clj-kondo to `2021.10.20-20211126.151305-16`.

- Editor
  - Support completion on aliased keywords. #649
  - Add new `Sort map keys` refactoring code action. #651
  - Add new `Create function` code action, allowing to create a function on a existing namespace or creating a new namespace + the function. #646
  - Improve `Extract function` refactoring to consider comments above current function. 
  - Experimental: new `:linters :clj-kondo :async-custom-lint?` setting, when true, scan unused-public-vars async improving lint/analysis UI feedback for huge buffers (> ~1000 lines). Default `false`.





## Typed Clojure

The goal of [this project funded by Clojurists Together](https://www.clojuriststogether.org/news/q3-2021-funding-announcement/) is to
improve static type error messages in [Typed Clojure](https://github.com/typedclojure/typedclojure),
specifically to replace expanded code in error messages with surface-level syntax.

In the first half of the project (last update), I concentrated on three main areas:
1. Increase direct support for problematic clojure.core macros
2. Improve error messages for inlining functions
3. Identify classes of implementation quirks in core Clojure macros to prepare for potential typing rules

In the second half (this update), I:
- Improved the experience of mixing clojure.spec and Typed Clojure
  - Typed Clojure no longer complains about the expansion of `s/def` `s/fdef`
  - Approach: implemented custom typing rules for both macros that ignores the body
  - [Commit](https://github.com/typedclojure/typedclojure/commit/2c423fb3daacaeb120b54a390c315588bba531a5)
- Trimmed error messages
  - Some hints in error messages have become irrelevant noise.
  - [Commit](https://github.com/typedclojure/typedclojure/commit/613691ff176d05dd886a5d387979868a5f5ab2bb)
- Created a proof-of-concept cljs.analyzer variant that can partially macroexpand
  - inspired by the inlining work from the first half of this project
  - Problem: CLJS uses lots of macros. Typed Clojure errors are even worse in CLJS.
    - eg., (+ 1 2) => (js* "(~{})" 1)
    - we want to pass (+ 1 2) to the type checker, not (js* "(~{})" 1)
      - but + is a macro call... not representable by cljs.analyzer
  - Solution: make partially expanded forms representable to the type checker
    - Type checker uses tools.analyzer-style AST's to analyze code
      - so does cljs.analyzer
    - I created typed.clj.analyzer to add an :unanalyzed form for partially expanded forms
      - but I didn't know how to port to cljs
  - Promising early results
    - unit tests for pausing the expansion of the children of each AST :op
  - Next: integrate into cljs checker (which was on pause for several years and needs a lot of other updates)
  - [Commit](https://github.com/typedclojure/typedclojure/commit/c8c2f870d7f58ff8a8abfaa83f939b5a824bd5cb)





## Polylith

I haven't been able to work near as much this quarter as the previous one (where I worked too much to finish everything) and I couldn't manage to finish the big task "Custom commands" (issue 113) but is working on it. 

These tasks are finished but I will wait with a release till issue 133 is finished:

These tasks are finished but I will wait with a release till issue 133 is finished:
- [Issue 161](https://github.com/polyfy/polylith/issues/161): If test setup fails, tests should not run
- [PR 148](https://github.com/polyfy/polylith/pull/148): restore description/url to generated pom.xml
- [Issue 154](https://github.com/polyfy/polylith/issues/154): Allow entities to be symbols in "clojure -T" commands
- [Issue 144](https://github.com/polyfy/polylith/issues/144): poly flags :dev alias if it has no :extra-paths in it
- [Issue 145](https://github.com/polyfy/polylith/issues/145): Calculate the size of the library even if using short shas





## Holy Lambda

#### Version 0.6.0

Finally, I've removed the mandatory download of dependencies to the local `.holy-lambda` directory. Previously both `deps.edn` and `bb.edn` required a special `:mvn/local-repo` keyword pointing to `.holy-lambda` directory to compile the project in Docker context or to pack `babashka` dependencies into deployable zip. Now uberjaring Clojure sources happen without Docker. Additionally, `bb hl:babashka-sync` packs `babashka` dependencies via a combination of `bb uberjar` and `unzip`. Thanks to packaging changes, it will be easier to cache dependencies in CI by pointing to standard `$HOME/.m2` directory. It's also possible to use `polylith` now since HL stopped requiring mounting local dependencies in the Docker context.
Moreover, packaging changes allowed me to reduce the deployment artifact size for `babashka`.

I'm also pleased to announce a new extension for Holy Lambda request/response model that is [holy-lambda-ring-adapter](https://github.com/FieryCod/holy-lambda-ring-adapter).
The adapter supports the Ring request/response specification. Users may now utilize an adapter to run full-featured Ring applications on AWS Lambda. To boost productivity for API Lambda development, one can plug in an adapter with Jetty instead of relying on slow AWS Lambda API emulators.

AWS recently announced an AWS Lambda support for ARM-based processors. Running code on Graviton2 is beneficial for the users since both the cost per 1M invocations and the cold start time are lower. I've introduced a new [holy-lambda-builder](https://github.com/FieryCod/holy-lambda/pkgs/container/holy-lambda-builder) images hosted in Github Registry with the support to both ARM64 (aarch64) and AMD64 architectures. Furthermore, HL works now great with M1-powered Macs and aarch64 based builder images.

Babashka backend layers for each architecture are available in `AWS Serverless Repository`:

- [AMD64 Babashka backend Layer](https://serverlessrepo.aws.amazon.com/applications/eu-central-1/443526418261/holy-lambda-babashka-runtime-amd64)
- [ARM64 Babashka backend Layer](https://serverlessrepo.aws.amazon.com/applications/eu-central-1/443526418261/holy-lambda-babashka-runtime-arm64)


#### Version 0.6.1

Holy Lambda now automatically parses the inner body of the event according to `content-type` to `:body-parsed` field.
I've bumped the inner `babashka` dependency in the backend layer to version `0.6.3`.

#### Version 0.6.2

The new version brings some improvements in tasks and `babashka` backend. Users can now update tasks to the latest stable revision by invoking the `bb hl:update-bb-tasks` command. I was also able to speed up the `babashka` backend by removing the costly classpath calculation in the init step by taking into account the recent changes to packaging.

Babashka's backend layer now also doesn't depend directly on `holy-lambda`.

Why it's important?

-   users can now deploy the backend layer and provide their implementation of the custom runtime,
-   users can now update the `holy-lambda` independently by packaging a new version in the dependencies layer.







