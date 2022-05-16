---
title: "April 2022 Monthly Update"
date: 2022-05-10T08:30:00+08:00
summary: Read updates from Overtone Playground, Datahike Server, Biff, Orchard, Typed Clojure, Reveal, Deep Diamond, Firefox, Clojure LSP and more updates from our long term projects with Bozhidar Batsov, Michiel Borkent, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov
author: Alyssa Parado
published: true
---

## Overtone Playground

My goal for this project is to provide a clear instruction on how to set-up and use Overtone without the hassle of going through bunch of errors and wrong steps which can amplify the frustration and prevent users from exploring programming and music through live coding. Since [Sonic-Pi Tutorial](https://sonic-pi.net/tutorial.html) is a good example of how starting guide for live coding should look like, my goal is to make similar one for experimenting with Overtone.

So far I have covered:
- System audio setup: The first step, and usually overlooked is that Overtone needs JACK (or PipeWire alternative) to work, and I have covered audio setup step-by-step in [Pipewire: Effortless Linux Audio](https://savo.rocks/posts/pipewire-effortless-linux-audio/) article on my blog. Although JACK is more mature and widespread software I was positively surprised that Pipewire is also very stable and much simpler to setup/use, so I absolutely recommend it.
- Overtone Setup From Scratch: This part covers creating a Clojure project, loading Overtone as a dependency, various little things that could come up as obstacles in the process, connecting audio inputs and outputs, and finally, trying to produce some sounds with basic Overtone functions to test if everything is set up correctly. More details about the process can be found in my [Overtone: Basic Setup](https://savo.rocks/posts/overtone-basic-setup/) article.
- Editor Setup (VS Code + Calva): I wanted to cover one of the most popular editors (if not THE most popular) when we look at general programming population: VS Code. Since my goal is not only to attract just Clojure programmers to try Overtone, but everyone regardless of the programming language they use, if they are programmers at all! The [Setting up VS Code for Clojure and Overtone development](https://savo.rocks/posts/setting-up-vs-code-for-clojure-and-overtone-development/) article covers complete setup of the environment to be used with Clojure and Overtone, as well as some more Overtone code that slowly introduces us to the interesting capabilities of the software.
- Complete Emacs Setup (CIDER + Prelude + Custom Tweaks): It was very important for me, as an avid Emacs user to properly cover advanced Emacs setup because a lot of newcomers to Clojure or generally programming will just pick what's the "easiest", so they will go with "standard" editors and later when they hear about Emacs again it will be much harder to make the switch. In article named [Starting Your Computer Music Journey with Clojure and Overtone in Emacs](https://savo.rocks/posts/starting-your-computer-music-journey-with-clojure-and-overtone-in-emacs/) I covered in great detail and explained the following aspects: Emacs setup, what is CIDER and how to set it up, introduced Prelude enhancements for Emacs which are probably the most important as they significantly boost Emacs experience. Finally, I have also mentioned several of my custom tweaks and why I recommend them. To make article complete, I covered in detail how to start Clojure project with Overtone, some of the Emacs' most used keybindings and provided more Overtone test examples that build on the previous code, so everybody can start experimenting on their own. The article attracted significant crowd, the [HackerNews Post](https://news.ycombinator.com/item?id=30971282) was featured on front page and many people on other platforms had positive reactions.

This is the advantage of continuous guide publishing through blog form; it keeps people interested and they engage more often, providing valuable feedback which helps me to write more useful guides in the future, covering areas which maybe I won't think of.

I have updated the [Overtone Playground GitHub](https://github.com/savorocks/overtone-playground) project directory which can be cloned and used right away. Some features from the first few chapters of Sonic-Pi tutorial are implemented and can be used in similar manner right away. Guides in my following blog post will cover that process in a similar fashion to Sonic-Pi tutorials, where possible. All my posts that are part of this guide are listed on [My Website](https://savo.rocks) under the category [Clojurists Together 2022 Q1](https://savo.rocks/categories/clojurists-together-2022-q1/), so you can bookmark the link if you want to stay tuned.




## Datahike Server

The first iteration focused on planning and updating the server API to the latest library API as well as design and implementation of relevant metrics in Datahike. Additionally we started on the first history integration into the server API.

#### Server REST API

The basis for any updates was a comparison table between available functions on `datahike.api` versus the current REST API in `datahike-server` and `datahike-client.api`. Datahike has grown since our last update on the server, so several additional functions needed to be added and some existing functions needed to be updated with additional functions. This resulted in the current PR 39 (<https://github.com/replikativ/datahike-server/pull/39>) While updating the API we also improved the testing behavior but we're not fully satisfied with the result. So we plan to extend the tests in the next iteration with better coverage.

#### Server History

With history as one of the core functionality in Datahike, we needed to integrate this to the REST API as well. After some experiments and approaches, we decided on simple header arguments for history database selection together with selected REST API endpoints that can support history like `q` or `datoms`.  The first basic implementation was finished with the branch `historical-db`(<https://github.com/replikativ/datahike-server/tree/historical-db>) together with first tests. Together with the better test coverage plan from the Server API we want to extensively test these functions as well in the next iteration.

#### Database Metrics

We began implementation of database index count metrics with per-transaction updates, but found that the opacity of whether a net addition of a datom has occurred during upsert, plus the unavailbility of a cheap `count` field or operation on the hitchhiker tree, make a performant and precise synchronous counter for arbitrary index structures infeasible. For now, we have settled on an on-demand count operation with caching as applicable (see in-progress [pull request](https://github.com/replikativ/datahike/pull/515)), with additional or improved implementation options in the future. Database-level metrics reporting backend and lib versions are also in progress.

#### Outlook

The next phase will see three milestones. First we will finish up the tasks from the first iteration, that means first the testing framework with refactoring of the current testing namespaces into separate ones, and in paralllel the integration of metrics into library and server. Second the work on the definition of the JSON specification will start with a proposition on how to handle different data models accordingly and consequently. And third we will collect different platforms, where we want to create a simple client as a wrapper around the REST API. Currently we're thinking about either C#, Go or Rust there.

#### Beyond Clojurists Together Tasks

This month saw some fixes in Datahike. The fix of the migration with attribute references, improved docs, major dependencies update with breaking changes for the storage layout and insertion behavior fixes. With the latest release we solved a problem with retractions within our index.




## Biff

Since writing the last update, I have:

 - Finished the [main documentation](https://biffweb.com/docs/)
 - Wrote the [API documentation](https://biffweb.com/api/com.biffweb.html)
 - Released [the new Biff version](https://biffweb.com/p/new-release/)!
 - Made two screencasts ([one](https://biffweb.com/p/demo-getting-started/) for
   setting up a new project, and [the
   other](https://biffweb.com/p/demo-dev-in-prod/) for developing in production)
 - Made plans for [a large example
   project](https://biffweb.com/p/platypub-plans/). In addition to giving
   people more code to learn from, I hope it presents a fun opportunity to
   contribute PRs to an open-source Biff project.
 - Made [a couple tiny releases](https://github.com/jacobobryant/biff/releases)

The [Biff newsletter](https://biffweb.com/newsletter/) also has 54 subscribers
as of writing this which is dandy.

Thanks again for sponsoring Biff! I'm excited for the future of the project.



## Orchard

#### Cljs compiler investigation

I've checked the cljs compiler state for any possibility of getting the executed function calls.
The good news is that `cljs.analyzer.api/analyze` returns a data structure that contains most of the data that is needed. The structure is a little awkward to handle since it's used to create the js code.

Here's an abbreviated version that shows the parts that are useful to get the references:

```clojure
(ana/analyze test-env '(defn abc [] (map #(inc %) [1])))
;; =>
{
:init {
    :methods [{
        :body {
            :ret {
                :args [{
                    :methods: [{
                        :body {
                            :ret {
                                :js-op cljs.core/+
                            }
                        }}]
                    :op :fn-method
                }
                {
                    :op :vector 
                }]
            }
            :fn {
                :name /map
            }
        }
        }]
}

}
```

The actual structure is a lot larger, but can mostly be ignored. The advantage here, compared to the Clojure version, is that it's 
possible to get anonymous function calls directly and that inlined calls aren't optimized away yet.

Sadly since the output of the analyzer is meant to be used to create js there's already a transformation happening before the output is returned.
For example `inc` is turned into a js `+` call, but this will mostly be a problem for very low-level calls and can probably be reversed.

#### The bad news

You can instruct the cljs compiler to dump an analysis cache during compilation. Unless I'm mistaken, this cache does not hold the needed information though. It seems to be mostly a cache of metadata. So to be able to use the information that the analyzer can provide I will have to call it on the actual source files. 

At that point, I'd be using the cljs compiler like a static analyzer. This does have advantages over stand-alone tools like clj-kondo (output would always be "correct"), but would not be nearly as performant as the Clojure version.

#### Next steps

1. try to extend the caching, so that functions don't have to be reanalyzed
2. create a proof of concept version to test performance, if step one fails
3. integrate the results into orchard



## Typed Clojure

The goal of [this project funded by Clojurists Together](https://www.clojuriststogether.org/news/q1-2022-funding-announcement/) is to
(resurrect) support for type checking ClojureScript files in [Typed Clojure](https://github.com/typedclojure/typedclojure).

Since the [last update](https://github.com/typedclojure/typedclojure/blob/main/doc/clojurists-together-q1-2022-update1.md), support for type-checking ClojureScript has improved enough to consider the CLJS type checker "resurrected" (but still young).

To stress test the ability for Typed Clojure to check `.cljc` files, I have been [type checking malli's implementation](https://github.com/frenchy64/malli/pull/1).

Checking in CLJS mode has revealed many bugs and shortcomings in the checker. Clojure mode has been outrageously successful--[it found a bug in malli](https://github.com/metosin/malli/pull/690)!

Some other feature work has been landing in Typed Clojure that has support for both Clojure and ClojureScript (enabled by this Clojurists Together project).
- [using malli schemas as types](https://www.patreon.com/posts/static-type-64236939)
- [using specs as types](https://www.patreon.com/posts/static-type-64321657) (CLJS WIP)
- [support defprotocol and implements? in CLJS](https://www.patreon.com/posts/typed-clojure-1-64869793)
- [checking programs without depending on Typed Clojure](https://www.patreon.com/posts/typed-clojure-1-65065388)
- [new syntactic sugar for function types](https://github.com/typedclojure/typedclojure/blob/main/CHANGELOG.md#1028)

See the [CHANGELOG.md](https://github.com/typedclojure/typedclojure/blob/main/CHANGELOG.md) for full details on everything else
accomplished in the time period since the last update (versions 1.0.21-1.0.28).




## Reveal

After completing [the test runner](https://vlaaad.github.io/reveal/feature/test-runner) I still had some time left, so I spent it updating [Reveal's website](https://vlaaad.github.io/reveal/). It is now much easier to navigate, and documentation was significantly improved:
- [Setup](https://vlaaad.github.io/reveal/setup) section was rewritten and expanded so it's easier to start using Reveal;
- All previously undocumented shortcuts are now [properly documented](https://vlaaad.github.io/reveal/keyboard-shortcuts);
- I added a new [Tips and Tricks](https://vlaaad.github.io/reveal/tips-and-tricks) page that shows some useful patterns I found during my time using Reveal.



## Deep Diamond 

My goal with this round is to implement Recurrent Neural Networks (RNN) support in Deep Diamond.
The first month was dedicated to literature review (and other hammock-located work), exploration
of OneDNN implementation of RNN layers, and implementation of RNN prototype in Clojure in Deep Diamond.

Now, in the second month, I managed to make the first iteration of fully functional RNN layer
based on the DNNL backend (currently only Vanilla RNN implementation) that fits into the existing
high-level network building code. (more details follow)

Deep Diamond currently supports general tensor operations, fully connected NN layers, and convolutional (CNN) layers, on CPU and GPU.
Based on this, relatively stable, infrastructure, in the first month I added Vanilla RNN _operation_ implementation backed by OneDNN (Intel, CPU).

Now, in the second month, I only managed to make a couple of commits, but the last one is pretty big.
I couldn't break it into many smaller ones, because I had to make a couple of subtle changes
that affected the internal implementation and broke existing code (_does not affect users, just me!).
I didn't want to commit a completely broken code into the github repository.

Now, what does this commit do?
A. It adds the first complete implementation of RNN layer that
    1. technically works and pushes the numbers to the right places connected to it
    2. is tested in isolation
    3. is supported in high-level Deep Diamond code together with other layers type
B. I implemented a supporting "Ending" layer for feeding the time-based RNN output to other layer types (Dense, CNN, etc.)
C. I improved the existing infrastructure to support this, and also made it more flexible
D. I fixed bugs and wrote tests...

That is fine, but I am not completely satisfied with this milestone. I hoped to make a fully working example of a network
that learns something useful, but that proved a bit harder than I expected. I couldn't make
the RNN I have so far actually learn. It appears to be working, but even simple examples for RNN
applications are not _that_ simple, so I wasn't be able to make it learn the artificial one
I tried it with. I am not sure whether it's due to bugs, or the example is simply pathological,
and the Vanilla RNN (which is currently the only type I implemented) can't do anything with it anyway.
This is something that I'll be doing the following days/weeks, and I expect to discover more when I implement
more advanced LSTM and/or GRU RNN types.

Beside that, the CUDA backend is broken in the current commit since I haven't updated it to the last changes,
but it's not a problem; once I nail DNNL, making CUDA compatible will be more or less routine.





## Firefox

### Custom Formatters for Firefox

#### Current status

After some back and forth between the reviewer and me, [my patch](https://phabricator.services.mozilla.com/D140119) for introducing the display of JsonML will land soon.

In order to handle custom formatters correctly and provide the user with some useful information in case of errors in the custom formatters themselves, there needs to be some exception handling. Therefore I've created [bug 1764439](https://bugzilla.mozilla.org/show_bug.cgi?id=1764439).

There were also several discussions between the Firefox DevTools team and me regarding different implementation details. For example, there needs to be some handling for configuration objects. So I've created [bug 1764443](https://bugzilla.mozilla.org/show_bug.cgi?id=1764443) to add that.

#### What's next?

[Bug 1734840](https://bugzilla.mozilla.org/show_bug.cgi?id=1734840) is still missing, which is the main thing to actually get the custom formatters to work. Once that's done, the other bugs mentioned above need to be tackled.





## Clojure-LSP

This month we had incredible important performance improvements, especially for big projects, along with support for ClojureDart, and new features!

#### Release [2022.04.18-00.59.32](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2022.04.18-00.59.32)

We started to check how to improve some points of performance and CPU spikes on clojure-lsp, since this release we started to fix logs of cpu spikes in multiple places like: completion, startup, file changes, test tree and others.
Now we have a cache layer for every external file analyzed, which means if user change anything on project that requires a full scan, it should be way faster than before.
We moved the core of LSP to a separate project called `lsp4clj` making possible to create other LSPs for any languages in Clojure.
Multiple improvements on java analysis for faster analysis and better UX settings.
Multiple improvements on the Drag feature.

Here is the changelog of this release:

- General
  - Improve settings documentation.
  - Fix completion performance regression from previous release.
  - Consider `.bb` and `.cljd` files as clj files. #906
  - Bump to clojure 1.11.0
  - Improve analysis query performance as a whole for lots of features. #916
  - Bump clj-kondo to `2022.04.09-20220414.123207-3` fixing semantic tokens for `:require`, `:refer` and `:as`. [#1609](https://github.com/clj-kondo/clj-kondo/issues/1609)
  - Move `lsp4clj` to outside clojure-lsp to its own repo/jar release.
  - Fix local files outside source-paths not being linted even if opened/changed.
  - Consider filename + lastModified as checksum for external files, avoiding analyzing it if analysis is already present.
  - graalvm: Compile static linux with --musl for better compatibility. #868
  - java: Use `XDG_CACHE_HOME` or `.cache/clojure-lsp` instead of config files for JDK cache.
  - java: Cache JDK analysis globally avoiding high CPU usages after startup.
  - java: Add `:java :home-path` setting for easier way to specify java location for JDK source analysis. #907

- Editor
  - Introduce ALPHA move-form command. #566
  - Rename "Move coll entry up/down" to "Drag forward/backward", matching Calva/Paredit terminology
  - drag: clauses move intuitively in `clojure.test/are`
  - drag: top-level forms can be dragged #891
  - Improve completion performance for most cases, reducing time to compute clj/cljs core symbols.
  - completion: suggest functions defined in Clojure 1.10 and 1.11
  - completion: Fix to no require extra ns when alias is already required. #920
  - promote-fn: *new feature* Promote a fn to a top-level defn. #783 @mainej
    - promote-fn can also promote a literal #() to a fn
  - demote-fn: Demote a fn to a literal #()
  - *breaking* remove cycle-fn-literal, since the same refactorings can be performed with the more clearly named promote-fn and demote-fn
  - drag: Fix to drag element-wise in destructured keys, not pair-wise. #927
  - test-tree: reduce CPU usage, especially during startup

- CLI
  - Reduce CPU and wall-clock time in cli commands clean-ns and diagnostics


#### Release [2022.05.03-12.35.40](https://github.com/clojure-lsp/clojure-lsp/releases/tag/2022.05.03-12.35.40)

This release added the feature of when renaming files via editor, clojure-lsp will automatically rename the namespace and all references.
A huge improvement on uncache startup with changes on clj-kondo which resulted on faster analysis.
It's now possible to sort package imports inside `:import` forms during `clean-ns`.
Also, the progress report during startup is more precise resulting in a better UX.

Here is the changelog of this release:

- General
  - Bump clj-kondo to `2022.04.26-20220429.192438-2`.
  - Decrease uncached startup time by 60-70%, by instructing clj-kondo to skip var definition bodies when analyzing deps. [#1674](https://github.com/clj-kondo/clj-kondo/pull/1674)
  - Improve speed of alias/ns completions.
  - Change alias/ns completions to return a label that matches the input.
  - Support sorting classes inside package imports during `clean-ns`. #932
  - Avoid saving duplicate source-paths, not calling clj-kondo to analyze same filenames multiple times, improving startup speed.
  - Improve progress report both on editor and CLI using new clj-kondo callback feature.

- Editor
  - Support `workspace/willRenameFiles`, renaming namespaces and all its references when a file is renamed.
  - Don't save cache when classpath lookup failed.
  - Wait for editor to apply edits before requesting cursor re-positioning. Fixes cursor positioning after drag in Calva.
  - drag: Request edit only of changed clauses, not entire parent, reducing flicker.

- CLI/API
  - Bump lsp4clj to `0.3.0`.





## Bozhidar Batsov

My main focus in the past couple of months was the preparation of CIDER 1.4. The release is almost ready and I expect to cut it any day now. It includes:

- lots of small improvements and bug-fixes
- improved interop with lsp-mode for things like completions and xref
- the ability to store the last result automatically in a register (?e by default) or to quickly recall the result using `cider-last-result`
- the iconic inspirational messages on connect have been restored after being absent for a few years and now you can even display them manually with `cider-inspire-me`
- misc documentation improvements

The whole changelog is [here](https://github.com/clojure-emacs/cider/blob/master/CHANGELOG.md#master-unreleased).
We've also celebrated 10 years since the first CIDER commit on the 17th of April. You can read a bit more about CIDER's history [here](https://docs.cider.mx/cider/about/history.html) 

**Note:** Technically speaking [CIDER 1.3](https://github.com/clojure-emacs/cider/releases/tag/v1.3.0) and [clojure-mode 5.14](https://github.com/clojure-emacs/clojure-mode/releases/tag/v5.14.0) were released in March, but I already mentioned them in my previous update. 

Also as usual - I've spent a lot of time discussing/investigating tickets on GitHub and providing support to various CIDER users. 

Outside of CIDER:

- I've updated CIDER's and nREPL's websites to use the latest versions of Antora and Algolia's Docsearch (you might notice the search is a lot better these days).
- You can now disable eldoc in inf-clojure if it causes performance issues for you.
- clojure-mode now supports ClojureDart source files. 
- nREPL's built-in client is about the get support for Unix sockets (see https://github.com/nrepl/nrepl/pull/270/files)





## Michiel Borkent

I'd like to thank all the sponsors and contributors that make this work possible.


#### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)
A linter for Clojure (code) that sparks joy.

Published versions: 2022.03.04, 2022.03.08, 2022.03.09, 2022.04.08,
2022.04.23, 2022.04.25

Read the changelog
[here](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md).

Highlights:

-   New linters: `:namespace-mismatch`, `:non-arg-vec-return-type-hint`,
    `:keyword-binding`, `:discouraged-var`
-   More analysis output for Java classes, protocols and multi-methods



#### [Babashka](https://github.com/babashka/babashka)
Native, fast starting Clojure interpreter for scripting.

Published versions: 0.7.7, 0.7.8, 0.8.0, 0.8.1

Read the changelog
[here](https://github.com/babashka/babashka/blob/master/CHANGELOG.md).

Highlights:

-   Declarative pod configuration in `bb.edn`
-   Compatibility with
    [specter](https://github.com/redplanetlabs/specter)
-   Fixes for `reify`, calls to interface default methods are now
    correctly implemented



#### [SCI](https://github.com/babashka/sci)
Configurable Clojure interpreter suitable for scripting and Clojure
DSLs.

Published versions: 0.3.2, 0.3.3, 0.3.4

Read the changelog
[here](https://github.com/babashka/sci/blob/master/CHANGELOG.md).

Summary: many small incremental improvements.



#### [Nbb](https://github.com/babashka/nbb)
Ad-hoc CLJS scripting on Node.js using SCI.

Published versions: v0.2.1 - v0.3.10.

Read the changelog
[here](https://github.com/babashka/nbb/blob/main/CHANGELOG.md).

Highlights:

-   [Features](https://github.com/babashka/nbb/blob/main/doc/dev.md#features)
    mechanism which allows you to re-package nbb with your favorite CLJS
    libraries.
-   Videos: [London Clojurians](https://youtu.be/7DQ0ymojfLg), [On the
    Code Again](https://youtu.be/_-G9EKaAyuI)
-   `nbb.core/await` REPL helper to \"block\" on promises and get their
    results
-   nREPL completion improvements



#### [Joyride](https://github.com/BetterThanTomorrow/joyride)
Modify VSCode by executing ClojureScript (SCI) code in your REPL and/or
run scripts via keyboard shortcuts.

Read the changelog
[here](https://github.com/BetterThanTomorrow/joyride/blob/master/CHANGELOG.md).

This is a new project!



#### [Grasp](https://github.com/borkdude/grasp)
Grep Clojure code using clojure.spec regexes.

Highlights:

-   Support `:keep-fn`, allowing you to keep track of the conformed
    value in one go



#### [Obb](https://github.com/babashka/obb)
Ad-hoc ClojureScript scripting of Mac applications via Apple\'s Open
Scripting Architecture.

Published versions: 0.0.3

Read the changelog
[here](https://github.com/babashka/obb/blob/main/CHANGELOG.md).



#### [Neil](https://github.com/babashka/neil)
A CLI to add common aliases and features to deps.edn-based projects.

Highlights:

-   List available versions
-   Dependency search
-   New `license` subcommand



#### [Rewrite-edn](https://github.com/borkdude/rewrite-edn)
Utility lib on top of rewrite-clj with common operations to update EDN
while preserving whitespace and comments.

Read the changelog
[here](https://github.com/borkdude/rewrite-edn/blob/master/CHANGELOG.md).



#### [Tools.misc](https://github.com/clj-easy/tools.misc)

Tools library similar to clojure.tools.logging but for misc. tools.

So far this is just an experiment, but join the discussion if you want.



#### [Fs](https://github.com/babashka/fs)

File system utility library for Clojure.

Published versions: 0.1.4.

Read the changelog
[here](https://github.com/babashka/fs/blob/master/CHANGELOG.md).

Discuss this post
[here](https://github.com/borkdude/blog/discussions/categories/posts).





## Dragan Djuric

In this reporting period, I continued work on Clojure Sound, and finally made it ready for
the first release into Clojars.

In the previous period (January and February) the main objective was to learn the fundamentals of
sound processing and communicating with control devices on the JVM, and start the work towards
an initial version of full blown Clojure library that is capable of serious work in this area.
Now I've wrapped up the first milestone with the initial release, that includes:

   #### From the previous period:
-   Full initial Clojure-friendly coverage of Java Sound API
-   Extracting common protocols for MIDI and Sampled parts of Java Sound into a core package
-   Clojurising the MIDI namespace quite a lot
-   UX layer for the MIDI standard that goes further than Java Sound
-   Full code examples for a large part of the Java MIDI tutorial, in the form of fully working tests.
-   Acquired and experimented with several hardware MIDI-compatible controllers

#### Added and improved in this reporting period
-   Support for midi synthesizers
-   Support for audio/file formats
-   Support for audio mixers
-   Support for audio controls
-   General improvements for listeners support in both midi and sampled namespaces
-   Various improvements and bugfixes of the existing code
-   Improved support for reading/writing/converting between files and formats
-   Fully covered the Java Sound Tutorial with automated midje tests
-   Released version 0.1.0 (fully operational and decently tested, with a fairly polished UX)

Of course, I continued with lots of dedication to learning the fundamentals of this, including music theory, music instruments and devices, and whatnot.
That is not something I can immediately convert into useful music related Clojure software (beyond Clojure Sound). However,
month by month I feel the foundations are building so in a several months, or a year, or a couple of years (depends on how optimistic I am)
I might be having some pleasant surprises for Clojurians!

As for the motivation, let me repeat (copy) what I wrote in the Jan-Feb report:

Although the motivation might be unclear to many Clojurists - who needs _sound_ stuff in Clojure - I predict
that this will be a very interesting and attractive project to many Clojurists, beginners and veterans alike.
First of all, sound-related stuff is interesting, so it's a good hobby coding and experimenting platform.
As such, it's a good choice for demonstrating various code techniques while avoiding boring topics.
Next, it's great for beginners to give them a topic that they know and like, music, as a playing ground
for learning the topics that they don't (yet) understand (Clojure and programming in general).
Further, it covers things only incidentally related to music: communication with external hardware
controller devices, which are often created for musicians in mind, but can be utilized in ingenious
ways in general computing, with their abundance of knobs, slider, pads, and other convenient control methods.
Last (but there will be more), digital signal processing is one of fundamental domains where high performance
computing is applied. This will be an excellent demonstration platform for showing off Clojure's HPC
computing capabilities. Who knows, maybe we'll even be able to build something that kicks-ass. Even if we
don't it will be an excellent platform for many Clojurists to make the first steps in DSP and HPC, and may
even attract some people to look into Clojure.





## Thomas Heller

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

Current shadow-cljs version: 2.18.0 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

Notable Updates

- Removed some old and mostly unused EQL related experiments. The only consumer was the UI loading its initial data and using the pathom backend for this was entirely overkill. Removed without replacement, as it was never part of any public API it shouldn't affect anyone.
- Removed the graaljs scriptengine dependency. It was only used for conflict resolution for `deps.cljs` declaring `:npm-deps`. Since this was rare to begin with it now uses the simpler variant of using the first declared dependency. Users can manually resolve conflicts by installing the desired version directly. Dropping this dependency also removes some annoying Graal-related warnings.
- Tweaked npm-resolve some more to match webpack even closer
- Fixed build reports. They didn't properly categorize npm dependencies anymore and instead put them all in "Generated Files". Now properly split by npm package again.




## David Nolen

- Clojure 1.11 compatibility
  - review and merge clojure.math patch CLJS-3347
- self-host fix CLJS-3288
- review MERGE 20+ arity apply fn fix CLJS-3348
- fix global shadowing issue CLJS-3368
- investigate potential externs issue CLJS-3373
- evalute dep shading solutions for ClojureScript AOT artifact




## Nikita Prokopov


Hello everybody, this is Niki Tonsky and we are still building Clojure Desktop UI framework in public.

[Humble UI](https://github.com/HumbleUI/HumbleUI/):

- A lot of under-the-hood work
- Scrollbars and clickables can now be nested
- New components: button, image, svg, checkbox, canvas
- Initial work on text fields
- Apache 2.0 license

First few apps built with Humble UI:

- Folcon has built [a game prototype for 7DRL](https://folcon.itch.io/fruit-economy-7drl-2022).
- Luis Thiam-Nye has built [a dependency graph visualisation](https://twitter.com/LuisThiamNye/status/1509564956643057666) and [a text editor](https://twitter.com/LuisThiamNye/status/1509569953531322381).

I am beyond excited!

[JWM](https://github.com/HumbleUI/JWM):

- Clipboard on macOS
- Better Clojure compatibility
- Fixed C++ name clash with AWT
- Several X11 fixes

[Skija](https://github.com/HumbleUI/Skija):

- Bumped Skia to m102
- Cached Font::getMetrics

DataScript, Rum and Tongue saw some maintenance releases.

Talks:

- Talked with Jacek Schae at ClojureStream [about Clojure Sublimed](https://soundcloud.com/clojurestream/sublimed-with-nikita-prokopov).
- Presented at HYTRADBOI [where DBs fits in client-server apps](https://www.hytradboi.com/2022/your-frontend-needs-a-database).






