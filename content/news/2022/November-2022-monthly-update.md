---
title: "November 2022 Monthly Update"
date: 2022-11-30T11:45:00+08:00
summary: Here are the updates from our Q3 2022 Projects. Read more about Cljfx, Portal, Exo, Clojupedia, Biff, Maria.cloud, Mathbox-cljs, and Clj-kondo and related.
author: Alyssa Parado
draft: true
---

## Project: , 

I implemented and released [cljfx/dev](https://github.com/cljfx/dev) library — a set of tools that help with developing cljfx applications but should not be included in the production distribution of cljfx app.

### Features

The cljfx/dev library has following features that help with developing cljfx applications:

#### Types and props reference

Now it's not necessary to browse cljfx source code to see the available cljfx types and their props, instead, you can look it all up in the REPL:

```clj
(require 'cljfx.dev)
;; look up available types:
(cljfx.dev/help)
;; Available cljfx types:
;; Cljfx type                             Instance class
;; :accordion                             javafx.scene.control.Accordion
;; :affine                                javafx.scene.transform.Affine
;; ...etc

;; look up information about fx type:
(cljfx.dev/help :label)
;; Cljfx type:
;; :label
;; 
;; Instance class:
;; javafx.scene.control.Label
;; 
;; Props                            Value type     
;; :accessible-help                 string
;; :accessible-role                 either of: :button, :check-box, :check-menu-item, ...
;; ...etc

;; look up information about a prop:
(cljfx.dev/help :label :graphic)
;; Prop of :label - :graphic
;; 
;; Cljfx desc, a map with :fx/type key
;; 
;; Required instance class:
;; javafx.scene.Node¹
;; 
;; ---
;; ¹javafx.scene.Node - Fitting cljfx types:
;;  Cljfx type               Class
;;  :accordion               javafx.scene.control.Accordion
;;  :ambient-light           javafx.scene.AmbientLight
;;  ...etc
```

### Improved error messages with spec

Exceptions that happen inside cljfx lifecycle are usually not informative, since they contain mostly cljfx internals and not user code. But with cljfx/dev, the exceptions now report more useful information:
- cljfx descriptions are validated with spec and generate more informative error messages when they are invalid;
- a stack of cljfx components is attached to the exception to help with debugging;

For example, this is how label reports an error when its text is set to invalid type:
```clj
;; clojure.lang.ExceptionInfo: Invalid cljfx description of :label type:
;; :not-a-string - failed: string? in [:text]
;; 
;; Cljfx component stack:
;;   :label
;;   user/message-view
;;   :scene
;;   :stage
;;   user/root-view
;;   
;;     at cljfx.dev$ensure_valid_desc.invokeStatic(validation.clj:62)
;;     at cljfx.dev$ensure_valid_desc.invoke(validation.clj:58)
;;     at cljfx.dev$wrap_lifecycle$reify__22150.advance(validation.clj:80)
;;     at ...
```

### Future plans

I plan to extend cljfx/dev library with more useful features:

- UI for types and props reference that is easier to search than `cljfx.dev/help`;
- add javadoc information about corresponding JavaFX classes in cljfx type help;
- add ability to inspect the dynamic component tree of a live cljfx app.




## Project: Portal, Chris Badahdah

Portal has seen [127 commits][commits] and 3 releases ([0.30.0][0], [0.31.0][1], [0.32.0][2]) since Clojurists Together funding started on September 1st, 2022. These changes include many small bug fixes, UX improvements, performance enhancements and documentation updates.

Aside from improvements, two new (experimental) features have also landed. The addition of [nREPL middleware][nrepl] and a [documentation viewer][cljdoc].

The nREPL middleware allows users direct REPL access to the ClojureScript Portal UI runtime. This eases Portal UI extension development and advances the first goal of the funding proposal.

The documentation viewer is the start of producing interactive documentation for Portal viewers within Portal. It will allow Portal to enhance it's existing documentation, mostly static markdown files, and add new sections of live / interactive demos and usage docs.

![doc viewer](https://user-images.githubusercontent.com/1986211/196014235-86cc1723-f48e-4f98-bb91-ca7e637108eb.png)

The remainder of the funding period will heavily focus on these live docs and other general docs improvements.

[commits]: https://github.com/djblue/portal/compare/0.29.1...master
[0]: https://github.com/djblue/portal/releases/tag/0.30.0
[1]: https://github.com/djblue/portal/releases/tag/0.31.0
[2]: https://github.com/djblue/portal/releases/tag/0.32.0
[nrepl]: https://github.com/djblue/portal/commit/e32f38a6951c0c28c23b54e7efe83c8ddea9195e
[cljdoc]: https://github.com/djblue/portal/commit/de8a6d76a337101ca227769bf34687d265824819





## Project: Exo, William Acton

September 2022 saw more progress on Exo than originally anticipated. However,
there were also some unanticipated work required outside of exo itself that I'll
talk about here as well.

### Overview

Exo (working name, may change) is a ClojureScript library for fetching and
caching data using EQL. It builds on top of the fundamental ideas behind Fulcro,
Apollo-client, and Relay, while providing an easy to use interface for
developers to adopt it a la carte into their existing front end applications.

Since receiving Clojurists Together support, I have open sourced the code at
[https://github.com/lilactown/exo](https://github.com/lilactown/exo). Docs
are sparse and the API is currently considered unstable, but it is functional
for read-only usage.

### PokéAPI example

A necessary part of any project is having a test bed to verify the correctness
and ergonomics of features as they're built.

Prior to Clojurists Together support, I had already built the fundamental
ability to send EQL queries to pathom, cache the results and subscribe to the
cache using React Hooks. Therefore my first task during this first month was to
build an example app that could exercise that basic functionality, and could be
extended to include more features as exo grows.

The example I chose is a single page app that would fetch and display data from
the [PokéAPI](https://pokeapi.co). The example uses pathom to resolve data from
the external API, and exo to orchestrate fetching and caching the data.

Initially, it showed some simple data about whatever pokemon was selected. I
then extended it to conditionally show data dependent on the initial data
loaded, i.e. the selected pokemon's evolution chain.


The code for this example can be found at [in the GitHub repo](https://github.com/lilactown/exo/tree/c40185ea415933f4c17c2dc187d7bb8fbaff026d/examples/pokemon-api),
and the live app can be seen at [https://visionary-kitten-ba80c7.netlify.app](https://visionary-kitten-ba80c7.netlify.app).

### Deferred queries

Exo's `use-query` hook precisely reflects the current state of whatever is in
the cache based on the query provided to it. However, in some cases (e.g. paging
results, where each page is a new query) we want to continue to show the results
from the previous query while we fetch the next query's results.

The PokéAPI example brought this use case to forefront, as fetching the data
from the API after changing which pokemon one is looking at can take a few
seconds, and in the meantime we can still show the previous pokemon with an
indication that we are loading the next one.

To support this use case, a new hook called `use-deferred-query` was added. This
way developers can easily choose whether they want to show the previous results
while loading, or always show the current value of the query in the cache even
if it's empty.

### Data masking & fragments

A problem I wanted to explore with Clojurists Together's support is one that
shows up in many large applications I have worked in: impilicit data
dependencies between sibling components. The [Relay docs](https://relay.dev/docs/principles-and-architecture/thinking-in-relay/) have a great explanation that I'll
quote below:

> With typical approaches to data-fetching we found that it was common for two components to have implicit dependencies. For example <Story /> might use some data without directly ensuring that the data was fetched. This data would often be fetched by some other part of the system, such as <AuthorDetails />. Then when we changed <AuthorDetails /> and removed that data-fetching logic, <Story /> would suddenly and inexplicably break. These types of bugs are not always immediately apparent, especially in larger applications developed by larger teams. Manual and automated testing can only help so much: this is exactly the type of systematic problem that is better solved by a framework.

The solution I landed on is similar in concep to the one Relay uses: create
pieces of queries called "fragments" that can be composed together, and then
_hide the data_ in the result unless a consumer also has a reference to the
fragment that was used to construct the query.

An example can be helpful to illustrate. Let's use a simplified version of the
PokéAPI example mentioned above:

```clojure
(defn pokemon-query-by-id
  [id]
  [{[:pokemon/id id] [:pokemon/id
                      :pokemon/name
                      :pokemon/height
                      :pokemon/weight
                      {:pokemon/sprites [:pokemon.sprites/front-default]}]}])


(defnc pokemon
  [{:keys [data]}]
  (let [{:pokemon/keys [id name height weight sprites]} data]
    ,,,))


(defnc evolutions
  [{:keys [data]}]
  (let [{:pokemon/keys [id]} data]
    ,,,))


(defnc example
  []
  (let [{:keys [data loading?]} (exo.hooks/use-query (pokemon-query-by-id 1))]
    (if loading?
      (d/div "Loading...")
      (d/div
       ($ pokemon {:data (get data [:pokemon/id 1])})
       ($ evolutions {:data (get data [:pokemon/id 1])})))))
```


In the above code, we have three components: the parent `example` and two
children, `pokemon` which would show some simple information about the selected
pokemon and `evolutions`, which would use the `pokemon/id` value to fetch
additional info.

The `pokemon-query-by-id` function returns an EQL query that is sent to pathom
by exo, on resolution of which the result will be stored in exo's cache and
re-render the `app` component.

Later, we refactor the `pokemon` component to no longer need the `pokemon/id`
key. Erroneously, we assume that no one else must be using it either - checking
to see if anything depends on `pokemon/id` might show a large number of results,
which would then need to be checked to see if they show up in the tree below the
`example` component.

In this small example, it's easy to fit everything on the screen; in a larger
app, these components might be spread across multiple files with many layers of
nesting.

The solution to making these breaking changes easier for a developer to discover
is to move the impilicit dependency into an explicit one. We'll move the data
requirements that `pokemon` and `evolutions` share into a fragment definition,
and then compose our query with it.

```clojure
(def pokemon-info-fragment
  (exo/fragment
   [:pokemon/id :pokemon/name :pokemon/height :pokemon/weight
    {:pokemon/sprites [:pokemon.sprites/front-default]}]))

(defn pokemon-query-by-id
  [id]
  [{[:pokemon/id id] pokemon-info-fragment}])
```

When exo returns the result of this query now, it will now contain an opaque
`FragmentRef` instead of the data for `[:pokemon/id 1]`:

```clojure
{[:pokemon/id 1] #<exo.FragmentRef>}
```

This fragment ref can be passed a new hook, `use-fragment` which will look up
the data in the cache and subscribe to it:


```clojure
(defnc pokemon
  [{:keys [data]}]
  (let [{:pokemon/keys [id name height weight sprites]} (exo.hooks/use-fragment
                                                         data
                                                         pokemon-info-fragment)]
    ,,,))


(defnc evolutions
  [{:keys [data]}]
  (let [{:pokemon/keys [id]} (exo.hooks/use-fragment
                              data
                              pokemon-info-fragment)]
    ,,,))
```

When we later go to modify the `pokemon-info-fragment`, we can easily discover
who depends on this data by searching for usages of it in our code base. This
isn't as automatic as Relay, which leverages the Flow static type system for
JavaScript to detect breaking changes, but is a vast improvement of the
situation before where past a certain size these dependencies are nigh
undetectable.

Another benefit of using fragments is that since the value of the `FragmentRef`s
are opaque, the parent component that runs the query doesn't need to re-render
when the cache updates with new data; instead, only components that are using
the fragment will be updated in that case. Not only does it improve the
maintainability of our applications, it also improves the performance!

### Side quest: updates to pyramid

The fragment support discussed above motivated some changes in the underlying
[pyramid](https://github.com/lilactown/pyramid) library, which exo uses to
normalize data before adding to the cache, and querying the cache using EQL.
I'll briefly note them because they enabled me, as a single developer, to build
the functionality in a month. Frome the [CHANGELOG](https://github.com/lilactown/pyramid/blob/356d8fbd0665a638a6dae0ecf011bfdd0bc3c12a/CHANGELOG.md):

* New pyramid.core/identify function which takes a DB and entity map and returns the lookup ID of the map if it were added to the DB.
* New visitor pattern support

*Visitor pattern*: you can now annotate parts of an EQL query with 
`{:visitor (fn visit [db data] ,,,)}` metadata, which will replace the location
with the return value of the visit function in the final result of the pull or
pull-report call.

It is similar to doing a postwalk on the results of pull or pull-report, but is
done in the same pass as pulling data out of the DB - so less traversals - and
annotated directly on the query.



The pyramid README was also rewritten to reflect many of the updates that have
been made to it in the last few months. Check it out!





## Project: Clojupedia, Adam Helinski

The first phase of this grant achieved the following goals:

- Improve visuals and CSS theme
- Purchase and configure the [clojupedia.com](https://clojupedia.org) domain
- Automate website building and deploymenht
- Update to new [Logseq](https://github.com/logseq/logseq) version
    - Fix issues related to page properties, page hierarchies, and queries
- Improve and finalize the structure of the project
- Enforce project structure with re-usable page templates
- Improve browsing by providing simple queries on relevant pages
- Improve overall documentation

Clojupedia is now ready for listing Clojure applications and libraries with rich
linking. In write-mode, users can quickly write custom simple queries for searching
projects jointly by platforms, themes, and type (e.g. find a Datalog library
supporting the browser).

The second phase will focus on:

- Add more content, libraries and applications, now that the structure is finalized
- Add support for listing upcoming Clojure events
- Add a sponsor page
- Prepare a talk for announcing Clojupedia at the Dutch Clojure Days on October 29th
- Best-effort for creating a logo

--

[Repository](https://github.com/clojupedia/clojupedia.org)
[Website](https://github.com/logseq/logseq)





## Project: Biff, Jacob O'Bryant

 - Investigated existing frameworks for documentation websites. Decided to
   write the docs website from scratch.

 - Created the new [docs section](https://biffweb.com/docs/get-started/intro/)
   of the Biff website and migrated all the existing documentation there.
   Previously I used [Slate](https://github.com/slatedocs/slate) for the
   reference docs and [Codox](https://github.com/weavejester/codox) for the API
   docs. Now that I'm using custom code, the documentation looks like it's
   actually part of the Biff website, I'm able to fine-tune it to Biff's needs,
   and it'll be easy for me to add new sections (like a section for the
   tutorial, which will come between the "Get Started" and "Reference"
   sections on the sidebar).

 - Made a batch of updates to Biff's code
   [releases v0.5.0 - v0.5.3](https://github.com/jacobobryant/biff/releases).
   This isn't part of my Clojurists Together-funded work since it wasn't
   documentation, but I mention it because it was a prerequisite to starting on
   the build-your-own-forum tutorial. (Whenever I start a new project, I like to
   get any pending code updates into Biff first.)

 - Wrote up some [design notes](https://forum.tfos.co/t/forum-chat-app-plans/51)
   for the forum tutorial.

With all that out of the way, I'm now ready to actually start writing the
tutorial. This will happen mostly in November since I'll be on paternity leave
starting next week.

As an aside, two people have asked me in the past week about tutorials for
Biff, and it's been nice to say that I have one coming soon!

A few people have also asked about video tutorials--it occurs to me that it
wouldn't be hard to record myself working through the tutorial once it's
complete, so maybe I'll manage to do that in November as well.





## Project: Maria.cloud, Matt Huebert

This is my second update for the Fall/2022 funding of Maria.cloud by ClojuristsTogether.

Work has progressed well overall, with most of Maria’s original curriculum now rendering nicely in the new editor. Highlights include:

*   The **Cells** library has been re-implemented to work in sci.
*   A large portion of Maria’s **value viewing** behaviour has been implemented, including views for shapes and cells.
    *   TODO: The plan is to bring in [Clerk](https://github.com/nextjournal/clerk)’s viewer code into Maria so that we can use all of Clerk’s [built-in viewers](https://github.clerk.garden/nextjournal/book-of-clerk/commit/70d0459fbe941e689e0c2e7df0afc887eaf5900b/#🔍_viewers), and any custom viewers built for Clerk would also work in Maria.
*   I’ve added support for top-level `await` so that evaluation can pause for blocks that must complete before the rest of the document can run. Vars are handled as a special case, so that one can `await` the value of a `def` form.
    *   TODO: implement an `async-load-fn` for sci as described [here](https://github.com/babashka/sci/blob/master/doc/async.md) so that users can import arbitrary js deps and lazy-load ClojureScript namespaces.
*   **Namespace lookup** When evaluating within a code block, we determine the current namespace by moving “up” from the node until we find an evaluated ns form (defaulting to `user`). The alternative (default) behaviour would be to use the namespace from the REPL’s internal state, which is more likely to confuse users who are looking at a document as a top-to-bottom flow.
*   **Namespaces in curriculum**: all Maria docs will now include an `ns` form instead of the previous behaviour of having lots of built-in tools in scope automatically.
*   **doc/arglists coverage** was improved, by [fixing this in sci](https://github.com/babashka/sci/pull/827)
*   Implemented the **eldoc** feature for seeing docs/arglists for the current operation
*   Implemented UI for editing links and images via a tooltip within prose blocks

For more details see the [commit log](https://github.com/mhuebert/maria/commits/main).

Thanks again to [ClojuristsTogether](https://www.clojuriststogether.org) for supporting this work!

Remaining high-priority tasks include:

*   Command palette (needs to handle ProseMirror, CodeMirror, hybrid & other commands)
*   Curriculum (integration into UI / sidebar)
*   Persistence (local storage and publishing to GitHub gists)
*   Error messages (a lot of the original code for this won’t work now that we’ve changed the evaluator)






## Project: Clj-kondo and related, Michiel Borkent

### [Babashka](https://github.com/babashka/babashka)

Native, fast starting Clojure interpreter for scripting.

*   The first [1.0 release](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#10164-2022-10-17) was released!
*   Optimizations for `let` (in SCI) which is now up to 8x faster.
*   Many small improvements. See the [changelogs](https://github.com/babashka/babashka/blob/master/CHANGELOG.md).

### [Squint](https://github.com/squint-cljs/squint) and [Cherry](https://github.com/squint-cljs/cherry)

Squint and cherry are two flavors of the same CLJS compiler.

Squint is a CLJS _syntax_ to JS compiler for use case where you want to write JS, but do it using CLJS syntax and tooling instead. Squint comes with a standard library that resembles CLJS but is built on bare JS ingredients. As such, squint comes with the usual JS caveats, but we can still have our parens and enjoy a slim bundle size.

Cherry comes with the CLJS standard library and is as such much closer to the normal ClojureScript, but the minimal amount of JS is a little bigger.

I've working on unifying the compiler code of cherry and squint into one code base, which is still in progress. I've also worked on REPL code.

I've also given a [presentation on squint and cherry](https://twitter.com/borkdude/status/1586662315805450240) at the [Dutch Clojure Days](https://clojuredays.org/). The video will appear online in the future!

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)

Static analyzer and linter for Clojure code that sparks joy

Two new releases with many fixes and improvements. [Check the changelogs](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md) for details.

Among several new linters, there is a new `:unused-value` linter which detects unused values, which is particularly helpful for detecting unused transient operation results which can lead to bugs.

### [Clj-kondo configs](https://github.com/clj-kondo/configs)

Library configurations as dependencies for clj-kondo.

The idea of this repository is that you can add configuration for libraries as a dependency to your `deps.edn` or `project.clj`. If you invoke the right command or if you are using Clojure LSP, then the configuration is written into your `.clj-kondo` directory and clj-kondo will understand custom constructs in your library. Normally you can provide these configurations as part of your library, but this is not always an option, so the remaining configurations can live over here.

### [SCI](https://github.com/babashka/sci)

Configurable Clojure interpreter suitable for scripting and Clojure DSLs.

This is the workhorse that powers babashka, nbb, Joyride, and many other projects.

Several bugfixes and enhancements were made in the last two months in two new releases. Performance of `let` bindings are now up to 8x faster, as already mentioned in the babashka entry of this post.

See [changelogs](https://github.com/babashka/sci/blob/master/CHANGELOG.md) for more details.

### [Nbb](https://github.com/babashka/nbb)

Scripting in Clojure on Node.js using SCI

The first 1.0 version was released.

Many small bugfixes and improvements in the last two months. See [changelogs](https://github.com/babashka/nbb/blob/main/CHANGELOG.md).

### [Clj-yaml](https://github.com/clj-commons/clj-yaml)

In the past two month, I became one of the maintainers, together with [@lread](https://github.com/lread), of [clj-yaml](https://github.com/clj-commons/clj-yaml). Clj-yaml is a built-in library of babashka.

### [Deps.clj](https://github.com/borkdude/deps.clj)

A faithful port of the clojure CLI bash script to Clojure

A lot of Windows improvements in the last two months. Deps.clj is now also available as part of an [MSI installer](https://github.com/casselc/clj-msi/releases) that installs `deps.exe` as `clj.exe`. This installer might form the basis for an official Clojure MSI installer.

### [Gh-release-artifact](https://github.com/borkdude/gh-release-artifact)

Upload artifacts to Github releases idempotently

This tool has been in use within babashka, clj-kondo and other projects to automate uploading release artifacts from various CI systems to Github releases, idempotently. It is now open source and ready to be used by others.

### [Jet](https://github.com/borkdude/jet)

CLI to transform between JSON, EDN, YAML and Transit, powered with a minimal query language.

The latest release adds support for YAML (by using clj-yaml), thanks to [@qdzo](https://github.com/qdzo).

### [Babashka CLI](https://github.com/babashka/cli)

Turn Clojure functions into CLIs!

See [changelogs](https://github.com/babashka/cli/blob/main/CHANGELOG.md).

### [Process](https://github.com/babashka/process)

Clojure library for shelling out / spawning subprocesses

Minor updates and fixes. See [changelogs](https://github.com/babashka/process/blob/master/CHANGELOG.md).

### [Quickdoc](https://github.com/borkdude/quickdoc)

Quickdoc is a tool to generate documentation from namespace/var analysis done by clj-kondo. It's fast and spits out an `API.md` file in the root of your project, so you can immediately view it on Github. It has undergone significant improvements in the last two months. I'm using quickdoc myself in several projects. In the last two months, there have been improvements in the table of contents linking and linking to source code.

### [Fs](https://github.com/babashka/fs)

File system utility library for Clojure.

Minor updates and fixes. See [changelogs](https://github.com/babashka/fs/blob/master/CHANGELOG.md#changelog).

### [Carve](https://github.com/borkdude/carve)

Carve out the essentials of your Clojure app by removing unused vars

Version 0.2.0 was released, after a long hiatus, with an updated version of clj-kondo and some minor fixes.

### [Grasp](https://github.com/borkdude/grasp)

Grep Clojure code using clojure.spec regexes.

I use this tool to analyze code patterns to make informed choices for e.g. SCI and clj-kondo. E.g. see [this](https://github.com/borkdude/grasp/blob/master/examples/let_bindings.clj) example that shows how many let bindings are typically used. See the example in action [here](https://twitter.com/borkdude/status/1582320503049826304).

A new version was released with minor fixes.

### [Rewrite-edn](https://github.com/borkdude/rewrite-edn)

Utility lib on top of rewrite-clj with common operations to update EDN while preserving whitespace and comments.

Minor fixes and enhancements. Repeated usage of `assoc` is now a safe operation. Thanks to [@lread](https://github.com/lread) for the improvements.

### [lein2deps](https://github.com/borkdude/lein2deps)

Lein to deps.edn converter

This new little tool can convert a `project.edn` file to a `deps.edn` file. It even supports Java compilation and evaluation of code within `project.clj`.

### [Neil](https://github.com/babashka/neil)

A CLI to add common aliases and features to `deps.edn`\-based projects.

Neil now comes with a `dep upgrade` command, thanks to [@teodorlu](https://github.com/teodorlu) and [@russmatney](https://github.com/russmatney), together with other improvements.

### [Respeced](https://github.com/borkdude/respeced)

Finally, after 4 years, a new release of respeced, a testing library for clojure.spec fdefs.

### [Quickblog](https://github.com/borkdude/quickblog)

Light-weight static blog engine for Clojure and babashka

Small improvements. See [changelog](https://github.com/borkdude/quickblog/blob/main/CHANGELOG.md#changelog). The blog you're currently reading is made with quickblog.

### [Sci.configs](https://github.com/babashka/sci.configs)

A collection of ready to be used SCI configs

Added a `doseq` macro in [promesa](https://github.com/funcool/promesa) which also is available via this configuration. Sci.configs is used in [Clerk](https://github.com/nextjournal/clerk), [nbb](https://github.com/babashka/nbb), [Joyride](https://github.com/BetterThanTomorrow/joyride/) and other SCI-based CLJS projects.





## Project: Mathbox-cljs, Sam Ritchie

### Overview

I realized at the beginning of October that Mathbox would be much more useful as
an embeddable notebook component if it were paired with

- Some UI-based, non-code way to tweak and explore a visualization
- An equation editor, so users could write math in a WSIWYG style without
  necessarily having to read S-expressions

Sam Zhang's ["Curve
Shortening"](https://sam.zhang.fyi/2020/10/29/curve-shortening/) essay is an
excellent example. This essay opens with a JSXGraph interactive component on the
left and a Mathbox view on the right. The shape of the essay is defined by code,
but the reader can explore in a number of author-defined dimensions without
digging into the code.

My goal for the project was to produce a number of libraries:

- `mathbox.cljs`
- `sicmutils-mathbox`
- `sicmutils-clerk`

Into this mix I'm adding as goals:

- `jsxgraph.cljs` and `jsxgraph-clerk`, exposing the amazing
  [JSXGraph](http://jsxgraph.org/wp/index.html) library to Clojurescript with a
  Reagent based interface
- `mathlive.cljs` and `mathlive-clerk`, which does the same thing for the
  [Mathlive equation editor](https://cortexjs.io/compute-engine/demo/).

As I'll detail below, I've got the core pieces of these two new libraries
working, though not yet released as their own libraries.


### Mathbox

I've built out a library called
[sicmutils-clerk](https://github.com/sicmutils/sicmutils-clerk) with many
experiments and ports of Mathbox examples, all living inside
[Clerk](https://github.com/nextjournal/clerk) (see the [namespaces
here](https://github.com/sicmutils/sicmutils-clerk/tree/main/src), and the
[README.md](https://github.com/sicmutils/sicmutils-clerk) for instructions on
how to run this code). I'm using these experiments to work with the Clerk team
on the problem of how to synchronize state between client and server, so that
the server can keep track of the state of some running animation, for example,
and send it across the wire to a collaborator.

### JSXGraph

- Converted the 65kloc [JSXGraph project](https://github.com/jsxgraph/jsxgraph)
  over to ES6 modules from its old Clojurescript-incompatible AMD build. (The
  library author was receptive to the idea and has merged in a bunch of work
  here, for which I am very grateful)

  - Discussion: https://github.com/jsxgraph/jsxgraph/issues/464
  - 8 PRs: https://github.com/jsxgraph/jsxgraph/pulls?q=is%3Apr+author%3Asritchie+

- Added Github Actions, code formatting via Prettier, lots of nice improvements
  to the main project's dev experience.

- Published my own temporary Clojurescript compatible build here:
  https://www.npmjs.com/package/@mentatcollective/jsxgraph

I've been building out the bones of the Clojurescript wrapper in the
[sicmutils-clerk](https://github.com/sicmutils/sicmutils-clerk) library. I have
a working Reagent wrapper for JSXGraph. The library is very mutable, but the
wrapper lets you build scenes declaratively.

- Demo of a scene pushing state to a Reagent atom inside of a [Clerk
notebook](https://github.com/nextjournal/clerk):
https://twitter.com/sritchie/status/1585765084554412032, [code
here](https://github.com/sicmutils/sicmutils-clerk/blob/0c130a5e792452e32e633c963348d6d517be85da/src/jsxgraph.clj)

- Port of a complex JSXGraph demo:
  https://twitter.com/sritchie/status/1586057171111845888, with [code
  here](https://github.com/sicmutils/sicmutils-clerk/blob/0c130a5e792452e32e633c963348d6d517be85da/src/circles.clj)

### Mathlive

Chris Chudzicki is my collaborator on mathbox-react, and the author of the
Mathbox-based math3d.org. He has adopted
[Mathlive](https://github.com/arnog/mathlive) as his equation editor and I'm
following his lead.

Mathlive lets the user type LaTeX into a UI element, and then parses the LaTeX
into a format called MathJSON. In October, I:

- [Built a Reagent wrapper around the stateful Mathfield component](https://github.com/sicmutils/sicmutils-clerk/blob/0c130a5e792452e32e633c963348d6d517be85da/src/demo/mathlive.cljs)

- Wrote a Mathlive->CLJ Parser (not complete yet), which of course exposed a
  number of bugs in LaTeX parsing. I'm working with the project creator to get
  these fixed up.

- Wrote a [Clerk](https://github.com/nextjournal/clerk) viewer that updates
  shared state with a Clojure representation of the equation that the user is
  typing.

What is great about this is that the output is compatible with SICMUtils, so I
can run simplification on the output and re-render it as TeX, live in the Clerk
notebook.

Here is a demo of that code:
https://twitter.com/sritchie/status/1582475087621390336

### Next

This month was about making sure that each these components could actually work
and communicate state via Reagent atoms.

Next, I'll extract these viewers and Reagent wrappers out from sicmutils-clerk
into separate libraries. I'm going to use Clerk to document the libraries, like
Matt Huebert did with his [interactive documentation for the inside-out forms
library](https://inside-out.matt.is/).

Then I'll work on a layer that is aware of all three of these components. This
will allow users to write some notebook that, for example:

- lets you set parameters for a simulation by moving points around a JSXGraph scene
- see the simulation in an explorable Mathbox window
- change the model behind the simulation by editing the equations in the equation editor
- PUBLISH the whole thing as an interactive blog post or research papge.

and the rest of the notebook exploring the simulation will live-update on any of
these changes.

Onward!













