---
title: "October 2022 Monthly Update"
date: 2022-10-30T09:30:00+08:00
summary: Read the first updates from our Q3 2022 projects with Vlad Protsenko, Chris Badahdah, William Acton, Adam Helinski, Jacob O'Bryant, Matt Huebert, Michiel Borkent, Sam Ritchie, and Christophe Grand  
author: Alyssa Parado
draft: true
---

## Project: Cljfx, Vlad Protsenko

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

---

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





## Project: Maria.cloud, Matth Huebert

We’re excited to announce that I ([Matt](https://twitter.com/mhuebert)) have received a three-month grant from [ClojuristsTogether](https://www.clojuriststogether.org) to work on [Maria.cloud](https://www.maria.cloud). This is the first of three monthly updates.

### What is Maria?

Maria combines carefully written curriculum with a clean, no-install editor. It is designed to introduce complete beginners to fundamental ideas of programming, with an editing environment that encourages REPL-driven development and structural editing from day one. It has been widely appreciated as a good tool for introducing Clojure to beginners and used at several workshops like ClojureBridge.

### What did we propose?

_We built Maria 5 years ago and would like to bring it back into active development, starting with a refactor/simplification of the core of the editor so that we have a good base to build on top of._

_Top priorities would be (1) replace the selfhost compiler with sci for a more lightweight runtime, (2) replace the code editor with CodeMirror 6 using the cm6 clojure mode I wrote last year for Nextjournal, (3) upgrade to latest version of ProseMirror, (4) add a "publish" feature so that people can share what they make with the world. This funding may not cover all of the work listed above, so we'll remain open to receiving funds from elsewhere. This would be the first funding we have ever taken for this project._

### What are some benefits of these changes?

- Using [sci]([url](https://github.com/babashka/sci)) instead of ClojureScript’s self-hosted compiler should dramatically reduce the bundle size and speed up Maria on mobile/old/slow devices.

- Using Nextjournal’s [clojure-mode](https://github.com/nextjournal/clojure-mode) means we depend on a community-supported & standard structural editor and I can delete my own old unmaintained implementation.

- Using [ProseMirror](https://prosemirror.net) to manage a single toplevel doc means we can leverage tools in the ProseMirror ecosystem for new features, eg. [yjs-prosemirror](https://github.com/yjs/y-prosemirror) for real-time collaboration, and I can delete my own old unmaintained Clojure parser and block system.

- Keybindings should be more reliable/stable.

- The Maria codebase should be smaller and easier to work with.

### What has been achieved so far?

Progress is automatically deployed to https://2.maria.cloud on every push to `main`. So far I’ve implemented:

- A ProseMirror view which renders code blocks using CodeMirror 6 with clojure-mode.
- Conversion of Clojure source files into Markdown which can be managed by ProseMirror, and a reverse step to convert Markdown back to Clojure. (This is an inversion of top-level forms - when in “markdown mode”, comments are treated as prose and code is wrapped in fenced code blocks.)
- A sci context that includes Maria’s shapes library
- The REPL tools `doc` and `dir`
- Evaluating selections, blocks, and entire docs via hotkeys
- Showing results next to code, with rendering support for shapes

The editor itself is a nuanced, fidgety thing which requires a lot of careful attention to get right.

### Next steps

- Bring in Maria’s value-viewer code
- Support the cells library
- Test & support remaining curriculum
- Figure out how to integrate ProseMirror/CodeMirror keymaps with Maria’s command bar and “which-key” features
- Integrate or re-implement Maria’s auth & persistence features

### Ancillary tools (aka the scenic route)

In the course of this work I’ve also spent time on a couple of support tools.

In [js-interop](https://github.com/applied-science/js-interop) I’m working on a `j/js` macro, which is like a “deep” version of `j/lit`, meaning that literals become JavaScript data structures ({} => object, [] => array, keywords => strings), and destructuring forms in let/fn/defn are treated as js by default. Literals identified as belonging to Clojure proper or tagged ^:clj are not touched. This was inspired by [@borkdude’s](https://twitter.com/borkdude) experiments in [new cljs compilers](https://github.com/squint-cljs). `j/js` can make interop-heavy code easier to read and write but is not without tradeoffs; one needs to be extra-aware of whether one is looking at code in a “js” or “clj” context. It was particularly helpful in writing code related to ProseMirror/CodeMirror. I'm quite sure I want something like this to exist but the API/behaviour remains in flux. See the [PR](https://github.com/applied-science/js-interop/pull/32).

I’ve resumed work on [yawn](https://github.com/mhuebert/yawn), a hiccup compiler/interpreter that targets React. I was planning to stick with Reagent but it lacks a protocol that would enable custom rendering of arbitrary types, which we need for our viewers (eg. to render shapes properly). Yawn is designed with performance in mind and processes hiccup forms at compile-time where possible. It is REPL-friendly via support for react-refresh, so re-evaluating a view will immediately update on-screen while preserving state & without re-rendering from root.

### The end

Thanks again to Clojurists Together & all its supporters for making this work possible!




## Project: Clj-kondo and related, Michiel Borkent






## Project: Mathbox-cljs, Sam Ritchie






## Project: ClojureDart, Christophe Grand

















