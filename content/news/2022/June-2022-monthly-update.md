---
title: "June 2022 Monthly Update"
date: 2022-06-30T08:30:00+08:00
summary: Read updates from Deep Diamond, Overtone Playground, Datahike Server, Conjure and more updates from our long term projects with Bozhidar Batsov, Michiel Borkent, Dragan Djuric, Thomas Heller, David Nolen, and Nikita Prokopov
author: Alyssa Parado
published: draft
---



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

### 2nd Update

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


## Michiel Borkent


## Dragan Djuric


## Thomas Heller


## David Nolen


## Nikita Prokopov












