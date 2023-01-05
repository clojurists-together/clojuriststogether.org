---
title: "December 2022 Monthly Update"
date: 2023-01-05T08:30:00+08:00
summary: "As we enter 2023, here are the latest updates from our Q3 2022 projects. Happy New Year!"
author: Kathy Davis
draft: true
---
## **Project Biff: Jacob O'Bryant**

I started and finished [the tutorial](https://biffweb.com/docs/tutorial/build-a-chat-app/). It has 8 main sections:

 - Start a new project
 - Make a landing page
 - Deploy to production
 - Communities
 - Channels
 - Messages
 - Realtime updates
 - Inbound RSS

I also wrote [an announcement](https://biffweb.com/p/tutorial/) blog post with a few more details, for example: 
In building eelchat, you'll get a tour of all of Biff's main parts, including:


  - Creating and deploying a new project (I like to get new apps in production right away)
  - Rendering pages with Rum and Tailwind
  - Modeling your app's domain with Malli schemas
  - Doing CRUD with XTDB and htmx
  - Pushing updates to the client with transaction listeners, htmx, and hyperscript
  - Handling background jobs with scheduled tasks and in-memory queues



##  **Project Cljfx: Vladislav Protsenko**

**[Cljfx dev tools](https://github.com/cljfx/dev) got a new release â€” `1.0.36`.**  The new 
version adds 2 major enhancements:

- interactive props&types reference UI that also shows javadoc of defined cljfx types;
- live cljfx component tree explorer shown on &lt;kbd>F12&lt;/kbd> that is automatically
enabled if you use cljfx dev-time configuration.


Both features are showcased with this gif:
![Demo](https://raw.githubusercontent.com/cljfx/dev/main/demo.gif)



## **Project: Clojupedia, Adam Helinski**

Here is a link to the presentation I gave at Dutch Clojure Days in October 2022 
that explains the project and our work to date.  https://youtu.be/fuhaYvqJe-E
<p align *center*>

Here is the  [Repository](https://github.com/clojupedia/clojupedia.org) 
[Website](https://github.com/logseq/logseq).



##  **Project Exo: William Acton**

November and December have been extraordinarily busy for me, both professionally and personally. 
Clojurists Together graciously extended the deadline for me to work on Exo so that I could provide a more sizable update.

### **Overview**
Exo is a ClojureScript library for fetching and cachind data using EQL. It builds upon the ideas 
behind Fulcro, apollo-client and Realy, while providing an easy to use interface for developers 
to adopt it a la carte into their existing ClojureScript applications. Previously, Exo added support 
for efficiently querying and caching requests to a Pathom backend. It uses a normalized cache to ensure 
that entities that are shared amongst queries are stored compactly, and all queries that need data 
in those entities are updated when a change occurs.

Alas, the progress hasn't been as big as the first update despite spending a considerable amount of time on it. The majority of the time was spent researching and experimenting with ways of handling _mutations_ - i.e. things that update a remote data store - and how to handle updates to the cache that would not be an accretion of data.

### **Mutations**
"Mutations" are the name that pathom and GraphQL use to refer to requests that change the world, e.g. updating the name of my profile, sending a friend request, or blocking someone. Both pathom and GraphQL use a specific syntax for describing mutations that are distinct from queries that are meant to read data. For those that are familiar, it follows from the Command and Query Responsibility Segregation (CQRS) model of developing systems.

In general, Exo expects that the update to the actual world will be handled for us by some backend service. Exo's responsibility is to ensure that the cache which our application uses to show the state of the world to the user accurately reflects the changes that are made as they occur.

There are 3 broad categories of updates, which may occur together or separately in one mutation, that each present a challenge to front-end applications. I'll walk through each category and how Exo intends to handle them today, and how that might change in the future after further development.

### **Accretion**
A mutation may add new information to an entity, which means that a new or existing attribute may appear with new data. This is easily handled by either adding new data to the store, or by re-querying the remote endpoint to get the latest data.

You can imagine this as pseudo-code

```

(def mutation ,,,)

(-> (send-mutation! mutation)

    (.then (fn [result]

             (add-data! cache result))))

```

Since the cache is a normalized data structure, any new information about entities in the result will be added and any queries that use that information will automatically be updated. This is easily supported with no additional changes to Exo's caching mechanisms today.

### **Deletion of attributes**
A mutation may also delete an entities attribute. There can be ways to model our domain to avoid this, but it is common enough that Exo ought to support it. Exo's default model of adding new data is to merge the latest data with what was previously there, which doesn't account for attributes that may not be present in the latest data but were previously present.

Mutations that may do this can use the new `update-entity!` function, which is takes a lookup ref like `[:id 1]` and a function of the current entity and the new entity map.

### **Deletion of entities**
Some mutations may delete an entire entity from the system. In Exo, when an entity appears in the data of another entity, it is replaced with a reference that tells the query engine to look up the current state of it when reading.

If we naively remove an entity from the cache, these references may be left dangling, taking up space and leading to strange behavior. To help with this, Exo now provides a `delete-entity!` which correctly cleans up all references to the entities in the cache, which can be done after a successful mutation on the backend.

### **Evicting queries**
The above sections defines 3 categories of mutations and tools to handle each of them. These tools work well individually. However, there are some operations that don't quite fit the entity model of thinking, or are too complex to be described as strictly a deletion or accretion.

As a catch all for the things that don't fit nicely into the above categories, there's also the `evict-queries!` function which removes all data in the cache specific to the queries and triggers an update to any watches. With a bit more work on the network side of things, this can be used to refetch a query and populate its data regardless of what kind of change was made on the backend.

### **Programmatic vs declarative**
So far, I've described ways of programmatically acting on the cache after a mutation has been sent on the backend. I wanted to make sure I had the right mental model for tackling these problems before I tried to handle these operations in a more automatic way.

As stated in the beginning, Pathom has specific syntax for mutations. It uses EQL, similar to its queries, but uses symbols in a quoted list, similar to a Clojure function call

```clojure

[`(pokemon/update-name 25 "Pikachu")]

```

These mutations respond with a result map which can be added to the Exo cache. If the mutation responds with data that contains references to the updated entity, simply adding this result to the cache will cause all related queries to update.

The next thing you could do is add queries that you want to re-run after the mutation has occurred.

```clojure

[`(pokemon/update-name 25 "Pikachu")

 pokemon-query

 evolution-query]

```

Again, adding the result of this query sent to pathom will update the cache such that all of the related queries will be updated. However, detecting attribute or entity deletion isn't possible using this syntax. An additional way of notifying Exo about this is needed. There are also considerations around optimistic updates and rollback which I am exploring.

The tools described in the section before this give us the fundamental tools to handle each case. The next step is to build the network machinery to refetch a query when it is evicted, and then build a wrapper around all of this to expose it as a React hook that can be used declaratively in applications.

### **Conclusion**
I had high hopes coming out of the first half of my 3 months working on Exo, funded by Clojurists Together, that I would be able to finish mutations and begin documenting the library for humans to use. Ultimately, I was not satisfied with the solutions I was trying when doing this declaratively, so I sufficed to build a programmatic solution that could handle all the cases I could think of and continue to experiment with declarative wrappers.

If you're interested in trying out Exo, reach out to me at hello@willacton.com or DM me on Clojurians slack @lilactown. It has been a pleasure working with Clojurists Together, and I appreciate them funding this project. It has given me the boost I needed to make a ton of progress over the last few months, and I look forward to continuing to work on it and many of my other open source projects as time permits. Thanks!



##  **Project Maria.cloud: Matthew Huebert**

This is my third and final update for the Fall/2022 funding of Maria.cloud by ClojuristsTogether. A huge thank-you to everyone involved for making this possible!

Work concluded this month includes:
1. Popovers for adding & editing **links** and **images** graphically.
2. Improved **stacktrace view** shows doc/metadata for vars, and highlights relevant code regions on hover.
3.  **Autocomplete** is now implemented on top of sci.
4. Keymaps are now consistent with Maria.cloud and the "eval-region" extension was simplified.
5. **Async module loading:** we can now include additional dependencies in a release, to be loaded on-demand via `ns` or `require`. Suggestions/PRs for additional modules are welcome.

My top goals for this 3-month effort were to :
✅ rewrite the document model using a single ProseMirror instance, with code cells handled by a "Node View" using CodeMirror 6,

✅ eliminate all custom "paredit" code in favour of the existing CodeMirror 6 clojure-mode,

✅ use sci instead of the self-hosted compiler. Along the way I also added some improvements over the existing code:

✅ Top-level "await" for asynchronous document evaluation (primarily so that evaluator waits for `require` and `ns` forms to finish before proceeding, but can now be used for other purposes as well).

✅ A stacktrace viewer that can highlight relevant code.

✅ Curriculum files are now "proper" cljs/sci files complete with namespace declarations, so they can be used from Clojure (when clj-compatibility is complete). This will let students use their own editor to work through the curriculum, should they choose.

Much of this work went very well and even faster than expected, but a large number of other features also need to be updated or re-implemented in order to work with the new code. A few of these remain incomplete, and are blockers for deploying this work to Maria.cloud:

    🔲 command-palette

    🔲 curriculum browsing & loading

    🔲 integration with GitHub (gists) for persistence and sharing

Meanwhile, the latest (in-progress) code continues to be auto-deployed to 2.maria.cloud.



##  **Project Mathbox: Sam Ritchie**

This last month I shipped a number of completed libraries for Clerk and Reagent, and worked on a number of upgrades to Clerk, JSXGraph and Mathbox that appeared over the course of development. I've come out of this month thinking that these tools could really be the basis of an open-source, Clojure-based Mathematica competitor. After porting a few of the JS examples from the Mathbox repository, I think we can write faster code than hand-tuned three.js in natural Clojure style. Here's my progress broken down into buckets:

### **Libraries Shipped:**
**I've shipped final versions of the following libraries:**
- [https://github.com/mentat-collective/jsxgraph.cljs](https://github.com/mentat-collective/jsxgraph.cljs) ([https://jsxgraph.mentat.org](https://jsxgraph.mentat.org/))

- [https://github.com/mentat-collective/mathlive.cljs](https://github.com/mentat-collective/mathlive.cljs) ([https://mathlive.mentat.org](https://mathlive.mentat.org/))

- [https://github.com/mentat-collective/clerk-utils.cljs](https://github.com/mentat-collective/clerk-utils.cljs) ([https://clerk-utils.mentat.org](https://clerk-utils.mentat.org/))

Each features my best effort at well-documented code, interactive docs notebooks (via Clerk), workflows for release to cljdocs and Clojars... these projects will be easy to upgrade and maintain going forward. The JSXGraph library was very well received by the JSXGraph community!

**I've also shipped draft versions of:**
- [https://github.com/mentat-collective/leva.cljs](https://github.com/mentat-collective/leva.cljs) ([https://leva.mentat.org](https://leva.mentat.org/))

- [https://github.com/mentat-collective/mathbox.cljs](https://github.com/mentat-collective/mathbox.cljs) ([https://mathbox.mentat.org](https://mathbox.mentat.org/))

- [https://github.com/mentat-collective/emmy-viewers](https://github.com/mentat-collective/emmy-viewers) ([https://emmy-viewers.mentat.org](https://emmy-viewers.mentat.org/))

### **Mentat Collective**
I've collected a directory of all of my work around Mathbox, Clerk and computer algebra into a directory of projects listed at [https://github.com/mentat-collective](https://github.com/mentat-collective). 
I'll add a shout-out to Clojurists Together for support on this page. There is a ton here!

### **JSXGraph and Mathlive**
I submitted a [bunch of bugs and improvements to the Mathlive project ([https://github.com/arnog/mathlive/issues?q=is%3Aopen+author%3Asritchie](https://github.com/arnog/mathlive/issues?q=is%3Aopen+author%3Asritchie)+),

most of which are now resolved and folded into `mathlive.cljs`. JSXGraph is far more mature, but the way that I'm passing state around in the library is unusual, so I found a few bugs with the way events are propagated
that are now fixed.

I am quite proud of the interactive documentation notebooks that live at:
[https://mathlive.mentat.org/](https://mathlive.mentat.org/) and [https://jsxgraph.mentat.org/](https://jsxgraph.mentat.org/). Writing these made the library much better, and some of the examples, like the [Vector Field]([https://jsxgraph.mentat.org/#vector_field](https://jsxgraph.mentat.org/#vector_field)) page, are really exciting.

### **MathBox**
- I shipped a static site to [https://mathbox.org](https://mathbox.org/) with all of the demos in the mathbox repository.

- I've started porting these examples over to Clojure at [https://mathbox.mentat.org/dev/mathbox/examples/index.html](https://mathbox.mentat.org/dev/mathbox/examples/index.html). 

See that page for a progress report. (I decided to kill Storybook and go all-in on the Clerk style of documentation notebook.) I also figured out how to make my Clojure ODE solver VERY fast, probably faster than most Javascript you'd write, since I have my own compiler.

### **leva.cljs**
I found a beautiful JavaScript library called "Leva" that lets you add interactive controls to a web page, and wrote a CLJS wrapper that allows you to bidirectionally bind a control panel to a CLJS or Reagent atom.
I've shipped a draft to [https://leva.mentat.org/](https://leva.mentat.org/).

For an example of where this is useful, see: [https://emmy-viewers.mentat.org/src/phase_portrait.html](https://emmy-viewers.mentat.org/src/phase_portrait.html), where I use the `dat.gui` library, a clunkier version of leva. This is useful for ANY Clerk or Reagent application where you want some way to play with state.

### **sicmutils-viewers**
I've renamed this library to "emmy-viewers", in anticipation of a rename of the SICMUtils computer algebra system to Emmy, after [Emmy Noether]([https://en.wikipedia.org/wiki/Emmy_Noether](https://en.wikipedia.org/wiki/Emmy_Noether)).

I've shipped an example physics simulation to: [https://emmy-viewers.mentat.org/src/phase_portrait.html](https://emmy-viewers.mentat.org/src/phase_portrait.html). Each of the lines on the phase portrait to the right is an independent physics simulation; all 256 simulations, plus the pendulum, are running at 60fps.

### **Clerk**
I published a small library for Clerk users to make it easier to write documentation notebooks like the ones in these projects: [https://github.com/mentat-collective/clerk-utils](https://github.com/mentat-collective/clerk-utils). This has its own docs notebook, of course, at [https://clerk-utils.mentat.org/](https://clerk-utils.mentat.org/).

I also figured out how to publish libraries with custom cljs to Clerk's "garden" static publishing system. See, for example: [https://github.clerk.garden/mentat-collective/leva.cljs](https://github.clerk.garden/mentat-collective/leva.cljs).

### **Final Push in this final month:**
### **Clerk project templates**
Each of these published libraries works brilliantly as a "Clerk Plugin", as demonstrated by the docs notebooks. But this is a new thing for Clerk, and there are no good docs for how to include custom CLJS in a Clerk build.
I'm working with the Clerk team to write the following `deps-new` templates for Clerk users:
- Standard Clerk notebook project (compatible with Github Pages)
- Clerk notebook project with custom cljs (Github Pages-aware)
- A template for libraries like mine, shipped to Clojars, with interactive docs created via Clerk
These will live at [https://github.com/nextjournal/clerk-cljs-demo](https://github.com/nextjournal/clerk-cljs-demo)

### **Final libraries**
### **mathbox.cljs**
I'll ship a final version of mathbox.cljs, along with a number of the the examples at [https://mathbox.mentat.org/dev/mathbox/examples/index.html](https://mathbox.mentat.org/dev/mathbox/examples/index.html). 
I am going to use these to show off best practices for how to write visualizations in a way that is very fast.

### **leva.cljs**
This needs a final release to Clojars; there is a LOT that this library can do that I'll probably document and leave for future releases, like creating multiple control panels for a page.

###  **Stretch: ultra-fast ODE solvers**
I realized during my Mathbox work that I can make function compilation with
[sicmutils]([https://github.com/sicmutils/sicmutils/pull/523](https://github.com/sicmutils/sicmutils/pull/523)) much better. In fact, I HAVE to do this for the physics simulations I'm imagining to work very fast.

It may not be obvious how amazing [https://emmy-viewers.mentat.org/src/phase_portrait.html](https://emmy-viewers.mentat.org/src/phase_portrait.html) is, since I am not displaying the code that generates the system being simulated... but all of this is coming from function that's only a couple of lines long, describing the "Lagrangian" of a pendulum. On my machine, if I change the Lagrangian function, the whole animation live-updates and can display new crazy physics.

This is totally unheard of, and I think unique to this cljs stack. We are in Mathematica's territory here, but shareable, much faster, and using a modern JS stack. So I'm going to try and get the interface for fast function compilation fixed up.



## **Project Portal: Chris Bahdahdah**

Portal has seen [149 commits][commits] and 3 release ([0.33.0][0], [0.34.0][1],  [0.35.0][2]) since the last update on October 15th, 2022. Similar to previously,  these changes include many small bug fixes and UX improvements, however now with a much larger focus on documentation.

Portal now includes a `portal.api/docs` function which will open viewer docs. They include fully interactive examples, doc string and specs. This should help users better understand and leverage all viewers in Portal.
![portal viewer docs](https://user-images.githubusercontent.com/1986211/204972402-ac9a25ec-b779-4cfd-ac10-80aed378d8f8.png)

### **New Guides**
In addition, Portal has many new guides to help with the specific following use cases:

- [`Custom viewer guide`][`custom-viewers`]
- Describes how to leverage the new [`portal.api/repl`][`portal-repl`] to interactively build custom viewers for Portal.
- Also, demonstrates how to [`automatically load viewers`][`auto-loading`] via the `:on-load` hook.
- [`Calva notebook guide`][`calva-notebooks`]
- Illustrates how Portal can be used embedded in Calva notebooks.
- [`Clerk viewer guide`][`clerk`]
- Demonstrates how to setup a custom Clerk viewer which will embed Portal viewers.
- [`Portal console guide`][`portal-console`]
- Shows how to leverage portal.console instead of `tap>` to help with  traceability.
- [`nREPL customization`][`nrepl-customization`]
- Shows how to add a tap handler to customize how `portal.nrepl` eval results can be rendered.

**[commits]: https://github.com/djblue/portal/compare/0.32.0...0.35.0**
[0]: https://github.com/djblue/portal/releases/tag/0.33.0
[1]: https://github.com/djblue/portal/releases/tag/0.34.0
[2]: https://github.com/djblue/portal/releases/tag/0.35.0

**[custom-viewers]: https://github.com/djblue/portal/tree/master/examples/portal-present**
[portal-repl]: https://github.com/djblue/portal/tree/master/examples/portal-present#portal-nrepl
[auto-loading]: https://github.com/djblue/portal/tree/master/examples/portal-present#auto-loading
[calva-notebooks]: https://github.com/djblue/portal/blob/master/doc/editors/vs-code-notebook.md
[clerk]: https://github.com/djblue/portal/blob/master/doc/guides/clerk.md
[portal-console]: https://github.com/djblue/portal/blob/master/doc/guides/portal-console.md
[nrepl-customization]: https://github.com/djblue/portal/blob/master/doc/guides/nrepl.md#customization
