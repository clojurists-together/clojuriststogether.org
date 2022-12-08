---
title: "November 2022 Monthly Update"
date: 2022-11-30T11:45:00+08:00
summary: Here are the updates from our Q3 2022 Projects. Read more about Cljfx, Portal, Exo, Clojupedia, Biff, Maria.cloud, Mathbox-cljs, and Clj-kondo and related.
author: Alyssa Parado
draft: true
---

## Project: Cljfx, Vlad Protsenko 

[Cljfx dev tools](https://github.com/cljfx/dev) got a new release — `1.0.36`. 

The new version adds 2 major enhancements:
- interactive props&types reference UI that also shows javadoc of defined cljfx types;
- live cljfx component tree explorer shown on <kbd>F12</kbd> that is automatically enabled if you use cljfx dev-time configuration.

Both features are showcased with this gif:

![Demo](https://raw.githubusercontent.com/cljfx/dev/main/demo.gif)



## Project: Portal, Chris Badahdah

Portal has seen [149 commits][commits] and 3 release ([0.33.0][0], [0.34.0][1],
[0.35.0][2]) since the last update on October 15th, 2022. Similar to previously,
these changes include many small bug fixes and UX improvements, however now with
a much larger focus on documentation.

Portal now includes a `portal.api/docs` function which will open viewer docs.
They include fully interactive examples, doc string and specs. This should help
users better understand and leverage all viewers in Portal.

![portal viewer docs](https://user-images.githubusercontent.com/1986211/204972402-ac9a25ec-b779-4cfd-ac10-80aed378d8f8.png)

## New Guides

In addition, Portal has many new guides to help with the specific following use
cases:

- [Custom viewer guide][custom-viewers]
  - Describes how to leverage the new [`portal.api/repl`][portal-repl] to
    interactively build custom viewers for Portal.
  - Also, demonstrates how to [automatically load viewers][auto-loading] via the
    `:on-load` hook.
- [Calva notebook guide][calva-notebooks]
  - Illustrates how Portal can be used embedded in Calva notebooks.
- [Clerk viewer guide][clerk]
  - Demonstrates how to setup a custom Clerk viewer which will embed Portal
    viewers.
- [Portal console guide][portal-console]
  - Shows how to leverage portal.console instead of `tap>` to help with
    traceability.
- [nREPL customization][nrepl-customization]
  - Shows how to add a tap handler to customize how `portal.nrepl` eval results
    can be rendered.

[commits]: https://github.com/djblue/portal/compare/0.32.0...0.35.0
[0]: https://github.com/djblue/portal/releases/tag/0.33.0
[1]: https://github.com/djblue/portal/releases/tag/0.34.0
[2]: https://github.com/djblue/portal/releases/tag/0.35.0
[custom-viewers]: https://github.com/djblue/portal/tree/master/examples/portal-present
[portal-repl]: https://github.com/djblue/portal/tree/master/examples/portal-present#portal-nrepl
[auto-loading]: https://github.com/djblue/portal/tree/master/examples/portal-present#auto-loading
[calva-notebooks]: https://github.com/djblue/portal/blob/master/doc/editors/vs-code-notebook.md
[clerk]: https://github.com/djblue/portal/blob/master/doc/guides/clerk.md
[portal-console]: https://github.com/djblue/portal/blob/master/doc/guides/portal-console.md
[nrepl-customization]: https://github.com/djblue/portal/blob/master/doc/guides/nrepl.md#customization


## Project: Clojupedia, Adam Helinski

Relative to the output of report 1:

- Miscellaneous fixes to existing pages
- Add new pages and templates
- Add more information about contributing
- Update to newest Logseq version

In addition, a talk was prepared and presented at the Dutch Clojure
Days 2022 which served as an announcement of Clojupedia.

--

[Repository](https://github.com/clojupedia/clojupedia.org)
[Website](https://github.com/logseq/logseq)


## Project: Biff, Jacob O'Bryant

I started and finished [the tutorial](https://biffweb.com/docs/tutorial/build-a-chat-app/).
It has 8 main sections:

 - Start a new project
 - Make a landing page
 - Deploy to production
 - Communities
 - Channels
 - Messages
 - Realtime updates
 - Inbound RSS

I also wrote [an announcement](https://biffweb.com/p/tutorial/) blog post with a few more details,
for example:

> In building eelchat, you'll get a tour of all of Biff's main parts, including:
>
>  - Creating and deploying a new project (I like to get new apps in production right away)
>  - Rendering pages with Rum and Tailwind
>  - Modeling your app's domain with Malli schemas
>  - Doing CRUD with XTDB and htmx
>  - Pushing updates to the client with transaction listeners, htmx, and hyperscript
>  - Handling background jobs with scheduled tasks and in-memory queues


## Project: Maria.cloud, Matt Huebert

This is my third and final update for the Fall/2022 funding of Maria.cloud by ClojuristsTogether. A huge thank-you to everyone involved for making this possible!

Work concluded this month includes:

- Popovers for adding & editing **links** and **images** graphically.
- Improved **stacktrace view** shows doc/metadata for vars, and highlights relevant code regions on hover.
- **Autocomplete** is now implemented on top of sci.
- Keymaps are now consistent with Maria.cloud and the "eval-region" extension was simplified.
- **Async module loading:** we can now include additional dependencies in a release, to be loaded on-demand via `ns` or `require`. Suggestions/PRs for additional modules are welcome.

My top goals for this 3-month effort were to 

✅ rewrite the document model using a single ProseMirror instance, with code cells handled by a "Node View" using CodeMirror 6,

✅ eliminate all custom "paredit" code in favour of the existing CodeMirror 6 clojure-mode,

✅ use sci instead of the self-hosted compiler.

Along the way I also added some improvements over the existing code:

✅ Top-level "await" for asynchronous document evaluation (primarily so that evaluator waits for `require` and `ns` forms to finish before proceeding, but can now be used for other purposes as well).

✅ A stacktrace viewer that can highlight relevant code.

✅ Curriculum files are now "proper" cljs/sci files complete with namespace declarations, so they can be used from Clojure (when clj-compatibility is complete). This will let students use their own editor to work through the curriculum, should they choose.

Much of this work went very well and even faster than expected, but a large number of other features also need to be updated or re-implemented in order to work with the new code. A few of these remain incomplete, and are blockers for deploying this work to Maria.cloud.

🔲 command-palette

🔲 curriculum browsing & loading

🔲 integration with GitHub (gists) for persistence and sharing

Meanwhile, the latest (in-progress) code continues to be auto-deployed to 2.maria.cloud.







