---
title: "June 2022 Monthly Update"
date: 2022-06-30T08:30:00+08:00
summary: Read updates from Tablecloth, Deep Diamond, Overtone Playground, Datahike Server, Conjure and more updates from our long term projects with Bozhidar Batsov, Michiel Borkent, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov
author: Alyssa Parado
published: draft
---


## Tablecloth

During the first period of this project (April - June), I conducted some initial
research to plan and scope the project. I also opened an [initial PR](https://bit.ly/3yr3P8O) into
tablecloth that establishes a new \`tablecloth.column.api\` and some core
functions setting up a \`column\` "primitive." For a fuller account, of my thought
process and the work I've been doing, I've written a blog post ["Columns for
Tablecloth"](https://bit.ly/3ua77L8).

I've included a copy of that text below:


#### Columns for Tablecloth

For a few years now, I have been working with a unique, global group of people associated with the [SciCloj](https://scicloj.github.io/) community, many of whom are interested in promoting the use of Clojure for data-centric computing. As part of this effort, I recently applied for and then received funding from [Clojurists Together](https://www.clojuriststogether.org/open-source/) -- an organization in the Clojure community that provides funding for open source work on Clojure tools -- to contribute to an important new data-processing library called [tablecloth](https://github.com/scicloj/tablecloth).

#### The context for the project

Before delving into the nature of the project itself, I want to quickly explain my understanding of where this library tablecloth fits into the emerging Clojure data stack so it's clear why I think this project is worthwhile. Generally, speaking much of the work that I've been doing within SciCloj has been focused on the question of usabilty. The problem of usability has emerged only because in the last years a number of very talented individuals have created a set of powerful new tools that provide the bedrock for highly performant data-processing in Clojure.

One of the tools that has become particularly prominent is the so-called "tech" stack developed by [Chris Nurenberger](https://github.com/cnuernber). This stack consists of a low-level library called [dtype-next](https://github.com/cnuernber/dtype-next), which provides a method for handling typed arrays or buffers (see a workshop I gave on this library [here](https://www.youtube.com/watch?v=5u3_k_D5KSI&t=565s&ab_channel=LondonClojurians)), and [tech.ml.dataset](https://github.com/techascent/tech.ml.dataset) that provides a column-based tabular `dataset` much like the "dataframes" one finds in R or Python's Pandas library.

Using just tech.ml.dataset, one can already perform the kind of data analyses over large amounts of data. Indeed, in many cases, thanks to Chris Nurenberger's amazing work, this stack can outperform equivalent tools in Python, R, and even Julia.[^1] However, although very usable in its own right, tech.ml.dataset is somewhat low level and its API is not always consistent. For this reason, many people start out with the library to which I will be contributing: [tablecloth](https://github.com/scicloj/tablecloth).

Tablecloth is essentially a wrapper on top of tech.ml.dataset. Authored by Tomasz Sulej ([@generateme_blog](https://twitter.com/generateme_blog)), who has created many other useful libraries and has a knack for creating beautiful tools, it provides a consistent API for interacting with `datasets` that is inspired by the user-friendly syntax of R's tidyverse libraries among others.

#### My project: columns for tablecloth

What I will work on during this project, then is adding a new dimension to tablecloth's API. Currently, the focus of tablecloth's API is the `dataset`. This, of course, makes perfect sense since in many cases when working with data, especially when manipulating data as a preparation for feature engineering, you are working primarily at the level of the whole `dataset`.

Sometimes, however, you may want to perform operations not across a whole dataset (or set of columns), but on a single column. And that's where my project comes in. It will add add a new API for the `column` to tablecloth. In other words, once we've added this API we should, at the very least, be able to do something like:

```clojure
(def a (column [20 30 40 50]))
(def b (column (range 4)))
(- a b)
;; => [20 29 38 47]
```

The goal is to make the `column`, like the `dataset`, a first-class primitive within the tech stack. Like R's vectors and Numpy's `array`, the hope is that when people are working with tablecloth, users can reach for the `column` when they need to do some focused processing on a 1d (or perhaps 2d set of items).

#### A big open question: n-dimensionality

When I originally conceived of this project, I thought what we might be doing is bringing full-fledged support for n-dimensional arrays into tablecloth. Indeed, I orgiinally conceived of the project as an adjunct library called `tablecloth.array`. My thought was that this distinct library might eschew reliance on `tech.ml.dataset` -- which has some startup costs -- and simply rely on the lower-level library dtype-next, which has the key tools for efficient array processing and is in fact the basis for tech.ml.datset's columns.

However, for a number of reasons this is not practical. First, there is already a solid tool for array processing in the Clojure world: the [neanderathal](https://github.com/uncomplicate/neanderthal) library by Dragan Djuric ([@draganrocks](https://twitter.com/draganrocks)). Second, dtype-next is so low-level that some of the things that one might need, such as automatic scanning of the items in an array to determine their datatype, are not present by default. Right now, anyway, those features only exist within tech.ml.dataset's `Column` type. As such, what we decided to do is build directly on the tech.ml.dataset's `Column` as we get so much for free.

One consequence of this approach is that we cannot easily add n-dimensional support. The `column` in tech.ml.dataset, as the name might suggest, is not designed for multi-dimensionality. It is built on a single dtype-next buffer. There are possible approaches for layering on support for n-dimensionality, for example, as Chris Nurenberger put it:

> [A]s far as if a column could be N-D, you can take a linear random access
> array and make it ND via an addressing operator...[^2]

This solution sounds like a promising approach, but involves sufficient complexity that I think it will remain out of scope for this project. Another idea that was batted around briefly was whether we could support just two dimensions if we just allowed tablecloth's column API to pass around a dataset internally as a kind of representation of a two dimensional matrix.

However, I think ghosting behind these technical implementation questions is a more general question of what people need. The impetus for this project was about usability. We already have powerful tools such as neanderthal and core.matrix that handle matrix operations. We wanted to build support for those kind of operations within the syntactical vernacular of tablecloth, which so many people have found pleasant to use. It's about making it so that users don't need to change tools, and with it their whole mental model for working with the tool they are using.

Yet I think it is fair to say we still don't know what people really need in this space. In that respect, I think of this project as an experiment. It is better that we do not go whole hog and try to get n-dimensions right away. What we'll do first is try to build a sensible column API for tablecloth and then see how (and if) it is used. Perhaps there will be a need for more dimensions; perhaps not. 

#### What I've done so far

Now for what work has been done so far. Much of what I have been doing so far has been research. I have reviewed some key tutorials for Python Numpy ([here](https://numpy.org/doc/stable/user/quickstart.html)) and R's vectors ([here](https://adv-r.hadley.nz/vectors-chap.html)) in an attempt to study the other dominant APIs. I've also had a planning session with the author of tablecloth, Tomasz Sulej, to think about dimensionailty issue detailed above as well as the kinds of functionality we want to target during this project.

Here roughly, are the main areas of work that we think now will emerge more or less in order:

1. Establish the core "primitive" of the `column`, i.e. the entity that users will be able to create and manipulate;
2. Build out any necessary API for indexing, slicing and interating;
3. Lift various relevant operations and linear algebra functions that are in dtype-next into tablecloth, in particular dtype-next's "functional" namespace (i.e. [`tech.v3.datatype.functional`](https://github.com/cnuernber/dtype-next/blob/master/src/tech/v3/datatype/functional.clj)); 
4. finally, consider importing ideas from R's "factors" and lapply iterators.

So far the hard coding work that has been done relates to the first item above: establishing the `column` primitive. This [PR](https://github.com/scicloj/tablecloth/pull/71) does two things:

1. It establishes a new api domain: `tablecloth.column.api`. Rather than mixing column support into tablecloth's main api (`tablecloth.api`), Tomasz suggested we keep it distinct. This should help clarify when we are dealing with a column and when we are dealing with a dataset. It also means that we don't end up with naming collisions (e.g. for example there's already a `tablecloth.api/column` function).
2. It adds a basic set of core functions that establish the `column` primitive. These are:
   - `column` - creates a column
   - `column?` - identifies a column
   - `typeof`/`typeof?` - identify the datatypes of the column's elements
   - `ones` / `zeros` - create columns filled with ones or zeros
   
There's not a lot to say about these functions that is probably not rather obvious. One thing to note is that the inspiration is here being taken from both the R and Python worlds. R uses the name `typeof` for its typed "atomic" vectors, and Python's Numpy uses the functions `ones` and `zeros`. This practice of drawing on (hopefully the best) of the existing libraries in other languages is something that I intend to continue and do even more deeply, and which I believe is key to the Tomasz's strategy in building tablecloth to begin with.

#### Next steps

Having established the `tablecloth.column.api` namespace, the next major step I think will be to build out from this simple core of functions making it possible to do more with columns. This means considering either basic operations or indexing, slicing, and iterating over columns. I have a sense that indexing, slicing, and iterating will take precedence.

I also want to try to quickly conduct a rough survey of the operations that we might include, a kind of working spec or planning document. This way I can look at the full set of operations in one place, and also understand what we may take from the various existing APIs from which we want to learn. In other words, what can concepts/functions may we want to darw on from Numpy, from R, from Julia, and so on.

[^1]: Chris Nurenberger, "High Performance Data with Clojure."
    *YouTube*, 9 June 2021, https://youtu.be/5mUGu4RlwKE.
[^2]: Chris Nurenberger. Message posted to #data-science channel,
    topic: "matrix muliplication in dtype-next". *Clojurians Zulip*, 20
    September 2021.
  


## Deep Diamond

My goal with this round (Q1-2022) is to implement Recurrent Neural Networks (RNN) support in Deep Diamond.
The first month was dedicated to literature review (and other hammock-located work), exploration
of OneDNN implementation of RNN layers, and implementation of a RNN prototype in Clojure with Deep Diamond.
In the second month, I made the first iteration of fully functional vanilla RNN layer
based on the DNNL backend that fits into the existing high-level network building code.

Building on this, in the third month I finally managed to crack that tough nut called "recurrent networks implementation libraries"
and make:

1. A nice RNN layer implementation that seamlessly fits into the existing low-level and high level Deep Diamond infrastructure.
2. The DNNL implementation of Vanilla RNN, LSTM, and GRU layers for the CPU (fully functional, but not ideal).
3. The low-level integration of Nvidia's CUDNN GPU library, including TESTS.
4. The high-level integration of CUDNN into Deep Diamond's layers (tests show that I need to improve this).
5. A functional test that demonstrates learning of the said layers with Adam and SGD learning algorithms.
6. I released a new version of Deep Diamond 0.23.0 with these improvements (RNN support should be considered a preview, as I still need to fix and polish some parts of CUDNN GPU implementation).

Some notes about this milestone:

- Some things were smoother and easier than I expected. In general, I managed to find a way to fit RNNs into existing DD learning implementations, which was a pleasant surprise, as well as a sign of Clojure's qualities (and my ever increasing Clojure skills, if I am to stop being modest :)
- Some other things were harder than I expected. Notably, RNN implementation in both DNNL and CUDNN has many moving parts and are notoriously cumbersome to configure.
- There is total lack of simple examples of RNNs. Low level code examples of both of these libraries are practically non-existent in the wild. That slowed me a lot, since I had to discover everything by poking around.
- The documentation for DNNL and CUDNN related to RNNs is helpful, but most details still had to be discovered through trial and error. That too made me go at a snail's pace compared to my earlier work on other parts of Deep Diamond.
- Lacking simple examples, RNNs are very difficult to test. I am sure that this milestone, although usable, needs a lot more care to reach the level of usefulness of other parts of Deep Diamond.
- On the bright side, I am very satisfied with the high level API. The user virtually has three simple functions: rnn, lstm, gru, and ending, which fit into the existing infrastructure, and don't require any work from the user other than putting them at the right place; everything gets configured automatically under the hood by Deep Diamond. This part, I like a lot.

All in all, I am 80% satisfied with what I achieved in these 3 months. I hoped for a more complete implementation. On the other hand, I solved all tricky problems, and have clear idea how to fix and improve what is still not up to Uncomplicate standards, so I'll have something to work on in the following weeks/months. And it's finally time to update the book with these new goodies!

Thank you Clojurists for your continued trust in my work!





## Overtone Playground

Since the last project update I have been exploring overtone possibilities and finding ways to implement Sonic-Pi tutorial through my project. I have pushed the updates to the [Overtone-Playground GitHub Page](https://github.com/savorocks/overtone-playground) where I have partially or fully covered the functionalities from the beginning chapters, such as playing notes, groups of notes, samples, playing melodies, looping what is played (similar to the powerful live_loop function in sonic-pi, but work still in progress), using function scheduler to play multiple sounds at the same time (which works well, but needs to be upgraded to allow more control to the user) and controlling loops with stop-loop function. The code is in the repository and the guides for using those functionalities are in the making and will be published soon. However, I do need an extension because I've underestimated the complexity of the problem and overestimated the speed at which I can discover Overtone issues.
You can follow me on twitter [@savorocks](https://twitter.com/savorocks) for future updates on the project, and there is also a category [Clojurists Together 2022 Q1](https://savo.rocks/categories/clojurists-together-2022-q1/) on my website where you can see all the articles that are, and will be published regarding this funding.





## Datahike Server

### Second Update

The second iteration saw a lot of research and discussions on the JSON interface and the new target platform. Since a part of our team was on vacation we focused on fewer things this month.

#### Error Handling & Testing

Better error handling and testing was added with the finalization of the historical database headers (<https://github.com/replikativ/datahike-server/pull/40>). We separated dev mode and production configuration to better test both scenarios without re-using an existing database all the time.

#### New platform

While finding a new target platform different languages were considered. While checking different surveys (<https://insights.stackoverflow.com/survey/2021>, <https://spectrum.ieee.org/top-programming-languages>, <https://redmonk.com/sogrady/2022/03/28/language-rankings-1-22>, <https://pypl.github.io/PYPL.html>) of popular languages we were discussing which platform would be a good fit outside of JVM and JavaScript. The language should not be so esoteric that nobody would use it and not a specialised language where a database connection would not make sense. We agreed on a popular language that would support proper REST support, commonly used and maintained libraries, and that is also distinct to Lisp. In the end the decision was between Python, Ruby, PHP and Go. Python would be too much focused on Data Science and high performant databases, Ruby would be only work well with Ruby on Rails, and PHP is well PHP (part of us had to use it on their job a couple of years ago and were not a fan of it). So the new target platform will be Go with a simple REST client to the server.

#### JSON support

We are implementing support for JSON requests and responses, using the Muuntaja middleware stack for basic encoding and decoding, with route-specific parsing in handlers. Where feasible, we strive to include transparent conversion between strings and Clojure keywords and symbols. We investigated supporting fully transparent JSON-Datahike interoperability without requiring any usage of Clojure or Datalog syntax. However, that seems infeasible for `query` and `pull`, which can be too complex for reliably correct parsing of JSON. Still, for the next most complex Datahike API call, `transact`, it seems feasible at least in principle for common, simpler use cases. Thus, we take a two-tier approach: one with out-of-the-box JSON support for simple API calls, sufficient to cover most casual usage; and another, allowing or requiring JSON strings containing Clojure and Datalog syntax, for more advanced usage, including calls to `pull` and `query`. Please note that some uncertainty remains: for instance, we currently need to resolve a problem in implementing support in `transact` involving default decoding into `java.lang.Integer` where a `Long` is needed instead.

#### Outlook

The last month will see the implementation of the new JSON interface and subsequently the implementation of the new platform client. With the server API finished up, we will focus on adjusting the client API as well as the documentation while polishing the last open PRs from this iteration.

#### Beyond Clojurists Together Tasks

We had a fix on the read handler and config tests. Additionally we adjusted extensively our benchmarks and backwards compatibility tests. Finally we started with better migration tooling that would support older versions and better intermediary export files.


### Last Update

In the last iteration we focused heavily on the JSON interface and the Go client, as well as documentation and overall cleanup.

#### Cleanup and PRs

We finished up the open PRs like the [historical-db-fix](https://github.com/replikativ/datahike-server/pull/40) and [additional DB data](https://github.com/replikativ/datahike-server/pull/47), so we have better database information for the clients to consume.

#### Go Client

Since we only worked with Clojure for some time, it took a little bit of effort getting back into the Go language. The [datahike-go-client](https://github.com/replikativ/datahike-go-client) library was set up with very simple functionality without any caching for now and a basic rest client setup. First we started with basic GET handlers like fetching databases or fetching the current schema since the basic JSON responses were easily supported. Most time was spent on writing out the proper data types and coercing them from the JSON definitions. Yeah, we totally forgot how long it takes to write this boiler plate in all that typed languages out there and without a REPL the whole development cycle was event slower since everything needed to be covered with tests. Finally with the implementation of the JSON interface we could also implement the POST interfaces that would include transactions and queries. Now we have our first foreign platform client and are eager to test it.

#### JSON support

We added support for JSON requests and responses across Datahike Server API endpoints. This is done via a two-tier approach (mentioned in the last report): one tier with out-of-the-box JSON support for simple API calls, sufficient to cover most casual usage; and another, requiring JSON strings containing Clojure and Datalog syntax, for more advanced usage, including calls to `pull` and `query`. At the moment, most endpoints belong only to one or the other (or neither, if no arguments are required), with the only exception being `load-entities`, which requires Clojure syntax in JSON requests to databases with `:schema-flexibility` `:read`, and vanilla JSON otherwise.

Subject to syntax requirements (documentation in progress), both tiers encode and decode keywords (including namespaced keywords), symbols, and lists. Sets are also supported in server responses, but not requests, since they are to the best of our knowledge rarely or never used there.

In terms of database-specific expressions, our JSON support includes cardinality-many attributes, lookup refs, and attribute references.

The "advanced" tier is written to support arbitrarily complex expressions, with no loss of expressivity relative to EDN. As for the "simple" or rather out-of-the-box tier, its functionality should be largely identical to that available using EDN data, with limitations largely relevant to the `transact` endpoint, which does not support tuple-valued attributes, transacting datoms directly, and the operations `:db.fn/call`, `:db/cas` or `:db.fn/cas`, `:db.install/_attribute`, `:db.install/attribute`, and `:db.entity/preds`--which are rarely used anyway, as far as we know.

#### Limitations and future work

Types and tagged literals are not yet fully supported, though that is a high-priority to-do.

We plan to extend `transact`, `datoms`, `seek-datoms`, `entity`, `schema`, `reverse-schema`, and `index-range`, in the out-of-the-box tier, to allow "advanced" Clojure-inclusive syntax as well.

In addition, we would like to revisit the feasibility of minimizing handler-level parsing by maximizing usage of Muuntaja and Jsonista functionality in the middleware chain, for improved performance and cleaner code.

#### Database and server metrics

We are finalizing on-demand (as opposed to pre-computed) datom and index metrics for Datahike, as well as query and transaction time logging on the Server. Backend and library versions are in the works, pending updates to upstream libraries, primarily Konserve.

#### Outlook

With a JSON interface now integrated into the server we are able to experiment with other platforms where people can harness the power of Datalog. For that we started writing a blog post on how to do that in plain Go so other contributors can implement a simple REST wrapper in their favorite language.
Since the development in this context heavily focussed on moving the server forward, we now want to step back a little bit and work on more basic issues in Datahike, mainly performance improvements with the index, convenience functions for import/export through the [wanderung](https://github.com/lambdaforge/wanderung) library, [documentation with clerk](https://github.com/replikativ/datahike/pull/546) instead of plain markdown files, [benchmarking](https://github.com/replikativ/datahike/pull/538) improvements so we can see regressions, and more [compatibility checks](https://github.com/replikativ/datahike/pull/537). Additionally, we're trying to introduce [polylith](https://polylith.gitbook.io/polylith/) to our stack since there are too many namespaces that share the same code and a lot of dependencies that need to be updated collectively.

We're very grateful to Clojurists Together for allowing us to work on the things that we care for. Thanks guys! :)





## Conjure

### First Update
[Conjure](https://github.com/Olical/conjure) has been moving forward on many fronts over the last few months. Some of that work is related to non-Clojure language support (such as Julia and Common-Lisp), some is to do with the underlying Fennel system ([Aniseed](https://github.com/Olical/aniseed)) but the majority of the commits were improving the Clojure client is various ways.

It's also worth noting that the release I put out today for this Clojurists Together checkpoint was the 100th release! That's 100 tags, each containing many commits, since 2018 or so when I started work on my perfect Clojure interactive evaluation environment. I'm so happy with the state of Conjure today, I'm proud of what I managed to build and I'm so impressed with the community interaction and contributions. Seeing the (almost) 1000 stars on GitHub and hundreds of members in [our Discord](https://conjure.fun/discord) is wonderful.

You can read all the juicy details of every recent release on the [GitHub releases page](https://github.com/Olical/conjure/releases) but I'll highlight my favourite parts here.

 * Automatic support for nREPL 0.8+ `lookup` and `completions` operations when you don't have the CIDER middleware up and running.
 * Improvements to `:ConjureClientState` so you can swap your active REPLs around a little easier.
 * Support for `cljs.test` when executing your tests (this requires configuration to switch over).
 * Further tree-sitter fixes and support when extracting code from your buffer for evaluation.
 * Safer Shadow CLJS interactions so you can execute the Shadow select commands as many times as you want without weird errors. Essentially idempotent now.
 * Prevent weird duplicate logs on connection to an nREPL that announced the type of the REPL you were connecting to. It used to print "timeout" then "Clojure", that's fixed.
 * Handle _so many_ Neovim breaking changes in backwards compatible ways to support the ecosystem of Neovim + Conjure users as they transition to the latest stable version.
 * Default to using tree-sitter if all the conditions are met, you probably won't notice this, but things will get faster in large buffers with lots of code as autocompletions pop up and you pick code for evaluation.
 * Better checking for nREPL / shadow-cljs port files at each directory level. The logic makes more sense now, you'll just notice connecting to the "right" nREPL port file in some cases now. Thank you so much to @stelcodes for this one!
 * Created a Conjure client compatibility matrix for contributors and users to rely on https://github.com/Olical/conjure/wiki/Client-features

There's also been so much work done to Aniseed, the underlying Fennel Lisp system Conjure is built with as well as the various clients alongside the core Clojure one. I've managed to close of a bunch of bugs and clean up tickets that have been lingering for far too long.

The next batch of work under Clojurists Together will be the long awaited interactive debugger support. Hopefully everything goes well and it's possible, it's what I've wanted to do as part of this funding all along. My second and final update here in a few months should hopefully involve interactive stepping debugging of Clojure from Neovim!

Thank you so very much to each and every one of you who uses, supports, funds or contributes to Conjure and it's associated projects. I cannot express my gratitude enough here, so I will instead carry on trying to build the best conversational software development platform out there for you.

### Second Update

Since my last update on [Conjure](https://github.com/Olical/conjure) I've managed to get a few more key fixes and features in as well as learned enough to realise I need to take on yet another open source project (you'll be interested in this even if you don't use Neovim!). I've completed the main feature I wanted to finish as part of this funding round (debugging) and started a longer journey to supersede it eventually (even better editor agnostic debugging).

Here's a quick overview of [the changes since the my last project update](https://github.com/Olical/conjure/compare/v4.34.0...v4.36.0):

 * Added CIDER debugger support! You can now initialise the CIDER debugger then step through break points, inspecting and modifying values as you go. See the wiki page on the [Clojure nREPL CIDER debugger](https://github.com/Olical/conjure/wiki/Clojure-nREPL-CIDER-debugger) for more information.
 * Guarded against various subtle type errors such as `nil` REPL ports and attempts to concatenate strings with `nil` values.
 * Refactored the non-tree-sitter based evaluation code into a lazy loaded module that isn't executed at all if you rely on tree-sitter for your Conjure evaluations.
 * Improved `:ConjureSchool` so it only appends the next lesson once, so you can perform a lesson many times without it filling the school file with repeated instructions.
 * Support evaluating Clojure sets, inline functions, reader conditionals and some quoted forms as long as you're using tree-sitter. No more evaluating `#{:foo :bar}` as `{:foo :bar}`! It now correctly includes the `#` prefix!
 * Fixed nREPL session operations, such as creating, cycling and listing, when you have a long running process blocking the current session's thread. So now you can start a long running process and then cycle to a new session to keep evaluating things in another, unblocked, thread. This was a subtle bug introduced quite a long time ago by another fix. My apologies!

The debugger support is the star of the show, I hope you enjoy this new support and help me improve it into the future with issue reports and pull requests. It's fairly minimal but that's where the new open source project I mentioned comes into play.

Introducing the [Clojure CIDER DAP server](https://github.com/Olical/clojure-cider-dap-server) (which is an empty repository at the time of writing)! This project fell out of all of my Clojure debugging research, it will bridge the gap between DAP compatible tools (such as [nvim-dap](https://github.com/mfussenegger/nvim-dap)) in any editor and the CIDER debugger tooling.

This new, editor agnostic, standalone, CLI tool will allow you to plug your editor's debugger support into a running nREPL server. You will be able to perform interactive debugging with powerful tools, in or outside of Neovim, in a shared nREPL connection with Conjure.

The goal will be to make this the primary way of debugging your Clojure applications with Conjure's built in support being a simpler fallback for when you don't have a choice or when you don't need a rich GUI.

So, I hope you've enjoyed the features, fixes, improvements and optimisations I've brought to Conjure over my Clojurists Together funding period. And I really hope you enjoy my upcoming further work in the debugger tooling space, regardless of your editor or REPL tooling choices.

I'd love to hear your thoughts, opinions, feelings and feedback on Twitter [@OliverCaldwell](https://twitter.com/OliverCaldwell). Bye for now!






## Bozhidar Batsov

- CIDER 1.4 ("Kyiv") and 1.4.1 were released
	- https://github.com/clojure-emacs/cider/releases/tag/v1.4.0
	- https://github.com/clojure-emacs/cider/releases/tag/v1.4.1
- compliment 0.3.3
	- https://metaredux.com/posts/2022/06/20/compliment-0-3-13.html
- cider-nrepl 0.28.4 and 0.28.5
- Discussions around nREPL improvements
	- Missing features discussion https://github.com/nrepl/nrepl/discussions/275
	- Protocol improvements and protocol compliance https://github.com/nrepl/nrepl/discussions/275
- Making CIDER more Clojure agnostic
	- CIDER was making some assumptions about the runtime (it was basically expecting Clojure or ClojureScript)
	- I've adjusted some code in CIDER so it'd play better with alternative Clojure implementation (e.g. nbb)
	- Down the road we can make CIDER play nicer with any nREPL implementation
- Unreleased CIDER improvements:
	- Add custom var `cider-merge-sessions` to allow combining sessions in two different ways: Setting `cider-merge-sessions` to `'host` will merge all sessions associated with the same host within a project. Setting it to `'project` will combine all sessions of a project irrespective of their host.
	- Improve cider-browse-ns interface to allow selective hiding of var types as well as grouping options. Include private vars in result list.
- Unreleased nREPL improvements
	- Allow reader conditionals for tty transport.





## Michiel Borkent

### [Babashka CLI](https://github.com/babashka/cli)

Turn Clojure functions into CLIs!

This is one of my newest projects. It aims to close the gap between good command line UX and calling Clojure functions. It is very much inspired by the clojure CLI, but solves a problem which sometimes causes frustration, especially among Windows users: having to use quotes in a shell. It also offers support for subcommands. One project benefiting from that is [neil](https://github.com/babashka/neil). I blogged about babashka CLI [here](https://blog.michielborkent.nl/babashka-cli.html).

### [Http-server](https://github.com/babashka/http-server)

Serve static assets.

Another new project is http-server, which can be used in Clojure JVM and babashka to serve static assets in an http server.

### [Clj-kondo workshop](https://github.com/clj-kondo/hooks-workshop-clojured-2022)

In June I had the honor and pleasure to give a workshop about [clj-kondo](https://github.com/clj-kondo/clj-kondo) at [ClojureD](https://clojured.de/). You can work through the material yourself if you'd like [here](https://github.com/clj-kondo/hooks-workshop-clojured-2022). Feel free to join the clj-kondo channel on Clojurians Slack for questions. Here are some [pictures](https://twitter.com/borkdude/status/1542521071588347905) from the event.

### [Jet](https://github.com/borkdude/jet)

CLI to transform between JSON, EDN and Transit, powered with a minimal query language.

[Changelog](https://github.com/borkdude/jet/blob/master/CHANGELOG.md)

The `jet` binary is now available for Apple Silicon and adds [specter](https://github.com/redplanetlabs/specter) as part of the standard library for transforming data. Also, the output is colorized and pretty-printed using [puget](https://github.com/greglook/puget) now.

### [Edamame](https://github.com/borkdude/edamame)

[Changelog](https://github.com/borkdude/edamame/blob/master/CHANGELOG.md)

Configurable EDN/Clojure parser with location metadata. It has been stable for a while and reached version 1.0.0. The API is exposed now in [babashka](https://github.com/babashka/babashka) and [nbb](https://github.com/babashka/nbb) as well.

### [Quickdoc](https://github.com/borkdude/quickdoc)

Quickdoc is a tool to generate documentation from namespace/var analysis done by clj-kondo. It's fast and spits out an `API.md` file in the root of your project, so you can immediately view it on Github. It has undergone significant improvements in the last two months. I'm using quickdoc myself in several projects.

### [Nbb](https://github.com/babashka/nbb)

Scripting in Clojure on Node.js using SCI.

[Changelog](https://github.com/babashka/nbb/blob/main/CHANGELOG.md)

Added `edamame.core`, `cljs.math`, nREPL improvements and now has significant faster startup due to an improvement in [SCI](https://github.com/babashka/sci).

### [Clojure-lsp](https://github.com/clojure-lsp/clojure-lsp)

Clojure/Script Language Server (LSP) implementation.

This project is driven by the static analysis done by [clj-kondo](https://github.com/clj-kondo/clj-kondo) and used by many people to get IDE-like features in editors like emacs and VSCode.

I added support for Apple Silicon using [Cirrus CI](https://cirrus-ci.org/).

### [Babashka](https://github.com/babashka/babashka)

Native, fast starting Clojure interpreter for scripting.

[Changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md)

Two new version of babashka were released:

0.8.2 and 0.8.156. The last segment of the version number now indicates the release count, so the last release is the 156th release.

Babashka now also has a new Apple Silicon binary built on [Cirrus CI](https://cirrus-ci.org/). What is very exciting is that babashka can now execute [schema](https://github.com/plumatic/schema) from source. Compatibility with [malli](https://github.com/metosin/malli/pull/718) is underway.

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)

A linter for Clojure code that sparks joy.

[Changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)

New linters:

*   [`:warn-on-reflection`](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#warn-on-reflection)
*   [`:redundant-call`](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#redundant-call)
*   [`:discouraged-namespace`](https://github.com/clj-kondo/clj-kondo/blob/master/doc/linters.md#discouraged-namespace)

Clj-kondo now also has a new Apple Silicon binary built on [Cirrus CI](https://cirrus-ci.org/).

### [SCI](https://github.com/babashka/sci)

Configurable Clojure interpreter suitable for scripting and Clojure DSLs. Powering babashka, nbb, joyride and many other projects.

[Changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md)

New releases: 0.3.5 - 0.3.32

Highlights:

*   New `sci.async` namespace for asynchronous loading of code
*   Reduce advanced compiled JS output with about 20% (~900kb -> ~740kb)
*   Many improvements to `defrecord`

### [SCI configs](https://github.com/babashka/sci.configs)

A collection of ready to be used SCI configurations.

This project contains configurations for reagent, promesa, etc. and are used in nbb, clerk and other projects.

A recent addition was a configuration for `cljs.test` which is now shared by nbb and [joyride](https://github.com/BetterThanTomorrow/joyride).

### [Process](https://github.com/babashka/process)

[Changelog](https://github.com/babashka/process/blob/master/CHANGELOG.md)

New releases: 0.1.2 - 0.1.4

Highlights:

Support `exec` call in GraalVM `native-images` - this means you can replace the current process with another one.

### [Scittle](https://github.com/babashka/scittle)

The Small Clojure Interpreter exposed for usage in browser script tags.

Added support developing CLJS via nREPL. See [docs](https://github.com/babashka/scittle/tree/main/doc/nrepl).

### [Etaoin](https://github.com/clj-commons/etaoin)

Pure Clojure Webdriver protocol implementation.

This project is now compatible with babashka! Most of the work on this project was done by Lee Read. If you appreciate his work on this, or other projects like [rewrite-clj](https://github.com/clj-commons/rewrite-clj), consider [sponsoring](https://github.com/sponsors/lread) him.

### Misc

Brief mentions of miscellaneous other projects I worked on:

*   [nbb-action-example](https://github.com/borkdude/nbb-action-example) - example of how to build a Github Action with [nbb](https://github.com/babashka/nbb).
*   [joyride](https://github.com/BetterThanTomorrow/joyride) - Making VS Code Hackable since 2022
*   [dynaload](https://github.com/borkdude/dynaload) - The dynaload logic from clojure.spec.alpha as a library
*   [deps.clj](https://github.com/borkdude/deps.clj) - A faithful port of the clojure CLI bash script to Clojure.
*   [fs](https://github.com/babashka/fs) - File system utility library for Clojure
*   [fly-io-clojure](https://github.com/borkdude/fly_io_clojure) - A fly.io example for Clojure. Also see examples for [babashka](https://github.com/babashka/babashka/tree/master/doc/fly_io) and [nbb](https://github.com/babashka/nbb/tree/main/doc/fly_io).
*   [pod-babashka-etaoin](https://github.com/babashka/pod-babashka-etaoin) - Babashka pod wrapping Etaoin
*   [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher) - Babashka filewatcher pod



## Dragan Djuric

During this reporting period, I focused more on behind-the-scenes work, due to having
a tough schedule on other Clojurists Together project in May (Q1 Deep Diamond). That
left me with less energy for other coding and releases, but OTOH enabled me to work on
foundations of the planned work, specifically Clojure Sound devices and music skills,
Neanderthal sparse matrices, Deep Diamond backends and stability work, etc.

This does not mean that I don't have anything to show, of course! I do, but maybe not as
much as I showed in other reports :)

So, let's start with releases:

- Neanderthal, 2 releases (0.44.0, 0.44.1)
- Deep Diamond (not including releases that were part of Q1-2022), 1 release (0.23.1)
- ClojureCUDA, 2 releases (0.23.0, 0.23.1)
- Clojure Sound, 1 release (0.2.0)

I finally started writing tutorials on my blog. This June I published 4 articles

- Maple Leaf Rag with Clojure Sound
- Clojure Sound 2 - A better piano
- My experience with Clojurists Together open source funding (attracted some mild attention on Hacker News).
- Clojure Sound 3 - Hello MIDI Controller

Fundamentals and hammock time (this is actually sometimes that I spent most of my time on):

- Continued poking and researching cuDNN backend for Deep Diamond
- Investigated how to implement sparse matrices in Neanderthal
- Looked at the possibility of implementing another backend for MKL based on javacpp's API and distribution (still undecided about this).
- Debugged issues in Uncomplicate libraries that users reported on various OS configurations (mostly related to user errors, but this is valuable since it indicates how to improve documentation and tutorials)
- And, as during the last few reporting periods, most of my time was spent on music fundamentals, including learning about theory, instruments, and controlling devices (which could be used for making music, but also as input devices for programming-related tasks; think Emacs pedals). This is a long process, but I think it will pay off handsomely in the mid-term future, as I'm planning a course/book/tutorials that teach Clojure programming through music, and music through programming. I hope that this could attract people who have some music skills (kids and adults alike) but would like to learn programming, to choose Clojure. Also, Clojurists would hopefully benefit, as they can learn music and have a more interesting domain for Clojure showcases (not every example needs to be a TODO web-app!).






## Thomas Heller

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).

Current shadow-cljs version: 2.19.5 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)

Notable Updates

- Updated to the newest ClojureScript and Closure Compiler releases
- Fixed support for data_readers.cljc to match CLJS behavior
- A few smaller cleanups and bugfixes




## David Nolen

- ClojureScript core.async: #js literal support
- ClojureScript Test GitHub CI for Windows
- General ClojureScript JIRA issues review
- Support Node.js "exports" in package.json (Fixes Firebase v9 usage)




## Nikita Prokopov

Hello everybody, this is Niki Tonsky and we continue working for the greater good of Clojure Community.

[HumbleUI](https://github.com/HumbleUI/HumbleUI):

Main topic for last two months: text fields! Surprisingly complicated beasts. I have a list of 47 tasks related to it, so far I’ve managed to complete 35 of them, or \~75%.

- Text field: Support macOS emacs shortcuts in 
- Use BreakIterator to iterate chars, jump by word
- clipboard/get, /set, /get-text, /set-text, cut-copy-paste
- Align
- Scroll follows cursor, horizontal paddings
- Fix move word after delete 
- Click, drag, double click, triple click support
- ui/with-cursor, :padding-h/:padding-top/:padding-bottom

Also:

- Merged tooltip example by @Folcon
- Better error containment in dynamic

[JWM](https://github.com/HumbleUI/JWM):

- Catch exception in eventListener.accept(e) to remove it from the queue
- macOS: fixed Window.setIcon
- Helped @dzaima merge resize cursors
- macOS: fixed race condition in requestFrame
- macOS: fixed requestFrame calls before setVisible
- macOS: Merged window focus events by @LuisThiamNye
- macOS: Merged restoring cursor when re-entering window by @LuisThiamNye

[Skija](https://github.com/HumbleUI/Skija):

-  Surface::makeImageSnapshot can now correctly return null #23

ClojureScript:

- [CLJS-3382 Fixed regression in 1.11.54 that broke Rum](https://github.com/clojure/clojurescript/commit/7af8c42f6a9c045b4c5e213163a04816935ebe42)

[DataScript](https://github.com/tonsky/datascript):

- Check for nils in upsert attributes
- Mention externs for shadow-cljs users #432

[Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed):

- Fixed clojure_sublimed_eval_code #75 
- Fixed clojure_sublimed_insert_newline with multicursor #72

And some Clojure-related blogging:

- [Ideas for Clojure Network Eval API](https://tonsky.me/blog/network-eval/)
- [Missing features in nREPL](https://github.com/nrepl/nrepl/discussions/275)











