---
title: "February 2020 Update"
date: 2020-03-05T02:10:55+13:00
author: Daniel Compton
summary: "Read updates from Expound, Oz, Ring, Calva, and Reagent"
---

{{< toc >}}

## Housekeeping

We're now one month into our Q2 2020 round, funding Ring, Reagent, Calva, and fireplace.vim. fireplace.vim will be starting later in the grant period, but the rest of the projects started at the start of February.

This is the final update from Ben Brinckerhoff's grant to work on Expound. Thanks Ben! Christopher Small's grant to work on Oz finishes at the end of March.

## Around the web

Clojurists Together was [mentioned](https://insideclojure.org/2020/02/20/clojure-survey/) a few times in the [recent Clojure survey](https://clojure.org/news/2020/02/20/state-of-clojure-2020):

> Thank you all for this awesome language and special thanks to Daniel Compton for Clojurists Together.

> [...] It was also very nice to see [the] tremendous positive impact Clojurists Together had on the general community!

lvh was also on the [Cognicast](http://blog.cognitect.com/cognicast/149) recently and discussed Clojurists Together and Clojurists Together in-progress move to a new trade association (more to come on this soon). The section on Clojurists Together starts at 39:43.

## Expound

**Project Update 5: 2020-02-01 - 2020-02-15**

I'm taking a break from designing [a better way to customize Expound error messages](https://github.com/bhb/expound/issues/189) to work on a version of Expound that will work with `clojure/spec-alpha2` AKA `clojure.alpha.spec` AKA `spec2`.

The new Expound namespace is `expound.alpha2`. This version will only work with `spec2` and will NOT be backwards compatible with `expound.alpha`. Both versions will coexist in the same JAR, so you can use whichever one you want, depending on the version of Spec you use.

In addition to supporting `spec2`, `expound.alpha2` will include a number of changes (all of which are subject to change):

* [Hide "Relevant specs" section by default](https://twitter.com/bbrinck/status/1204595098207444993)
* Remove deprecated `expound/def` macro (you can still use `defmsg`)
* Make option names more consistent: one option includes the verb "show" while another includes "print" and I don't think there's any meaningful difference
* Remove headers like "Spec failed" which add almost no information
* Include new API for customizing error messages
* Rework internal multi-methods and namespaces to simplify code

I'm happy to report that my `spec2` branch has a few passing tests, only 85 or so failing tests to go.

**Project Update 6: 2020-02-16 - 2020-02-29**

I've been working on `expound.alpha2` and I'm happy to report I have an early version that works with `clojure.alpha.spec` AKA `spec2`.

If you are experimenting with `spec2` and want to use Expound, you can use commits from the [`spec2` branch](https://github.com/bhb/expound/pull/186). I won't be releasing JARs for some time, but you can use `tools.deps` or [`lein-git-down`](https://github.com/reifyhealth/lein-git-down) to depend on specific commits.

Here's an example using the "movie-times-user" example from the ["Schema and select" wiki page](https://github.com/clojure/spec-alpha2/wiki/Schema-and-select)

```clojure
(ns example)
(require '[clojure.alpha.spec :as s])
(require '[expound.alpha2 :as expound])

(s/def ::street string?)
(s/def ::city string?)
(s/def ::state string?) ;; simplified
(s/def ::zip int?) ;; simplified
(s/def ::addr (s/schema [::street ::city ::state ::zip]))
(s/def ::id int?)
(s/def ::first string?)
(s/def ::last string?)
(s/def ::user (s/schema [::id ::first ::last ::addr]))
(s/def ::movie-times-user (s/select ::user [::id ::addr {::addr [::zip]}]))

(s/explain ::movie-times-user {})
;; {} - failed: (fn [m] (contains? m :example/id)) spec: :example/movie-times-user
;; {} - failed: (fn [m] (contains? m :example/addr)) spec: :example/movie-times-user

(expound/expound ::movie-times-user {})

;;-- Spec failed --------------------
;;
;;  {}
;;
;;should satisfy
;;
;;  (fn [m] (contains? m :example/id))
;;
;;or
;;
;;  (fn [m] (contains? m :example/addr))
;;
;;-- Relevant specs -------
;;
;;:example/movie-times-user:
;;  (clojure.alpha.spec/select
;;   :example/user
;;   [:example/id :example/addr #:example{:addr [:example/zip]}])
;;
;;————————————
;;Detected 1 error
```

As you can see, there are many rough edges, but it should work for common cases. Please feel free to [report issues](https://github.com/bhb/expound/issues)!

### Caveats

* The API for `expound.alpha2` is in flux: breaking changes are expected!
* No ClojureScript support
* `fspec` is currently broken
* Many new features in spec2 may not work, but most of the features ported from spec1 should work fine (except `fspec`, see above)

## Oz

[Update 3](http://metasoarous.com/blog/oz-clojurists-together-update-3)

### Feb 2, 2020

I got a deploy to Clojars set up today. However, I am finding testing it out that it's not working. Will have to look into this more tomorrow.

### Feb 3, 2020

I've tracked down the issue from yesterday. It seems that in the process of tweaking my build process to avoid creating an uberjar, I thought it would be safe to remove the line :auto-clean false from my project.clj. This turns out not to be the case, and was found to ultimately be what was causing the JS to not load. (...)

### Feb 7, 2020

Eek! I'm only just now getting around to taking the project out of SNAPSHOT and minting an actual release! At long last though, you should now be able to require [metasoarous/oz "1.6.0-alpha6"]. Again, this release comes with all of the most up to date Vega libraries, and is no longer uberjar'd, which will hopefully ease folks development/dependency pains with the project.

[Update 4](http://metasoarous.com/blog/oz-clojurists-together-update-4)

### Feb 7, 2020

I just finished publishing my latest update and am looking for things to dig into now.

One of the things which has come up recently is the need for increased control of styling and other document header information. In fact, at this very moment this site (metasoarous.com) has the Oz logo as its favicon because that's the only thing it knows how to do :grimacing:

So this is the next thing I'll be working on. (...)

### Feb 26, 2020

Well... I've been in Mexico on vacation for the last couple of weeks, and while I had hoped to have some time to work on this, the beaches & tacos have kept me rather more busy than I anticipated. However, I'll be leaving back for Seattle tomorrow and have a bit of time to myself tonight to sip mezcal and continue hacking on html styling.

Last time I started in on this, I realized it's a somewhat thorny design problem. Taking a bit of time off has been a bit helpful though, and in my background mind I've settled on some particular options for control over styling and layout for now. I've also been using spec as a sketching/thinking tool for solidifying these ideas, and so will present these options in spec form:

```
(s/def ::title string?)
(s/def ::description string?)
(s/def ::author string?)
(s/def ::keywords (s/coll-of string?))
(s/def ::shortcut-icon-url string?)

(s/def ::omit-shortcut-icon? boolean?)
(s/def ::omit-styles? boolean?)
(s/def ::omit-charset? boolean?)
(s/def ::omit-vega-libs? boolean?)

(s/def ::hiccup
  (s/and vector?
         #(let [x (first %)]
            (or (keyword x) (string? x)))))

(s/def ::header-extras ::hiccup)

(s/def ::html-opts
  (s/keys :opt-un [::title ::description ::author ::keywords ::shortcut-icon-url ::omit-shortcut-icon? ::omit-styles? ::omit-charset? ::omit-vega-libs? ::header-extras]))
```

The options are now accepted as entries in the opts argument of (oz.core/html spec opts), and do their best to play nice with the options which are currently supported as metadata of the spec argument. In case you didn't know, it's possible to specify hiccup document metadata as markdown metadata like so (in following with the Jekyl pattern):

```
---
title: A great blog post
published-at: Today
tags: [clojure, oz]
---
## Hello world
Cool blog post, bro.
```

These options will be passed through as metadata on the spec object. Moreover, for options which should affect the html head content, such as [:title :description :author :keywords :tags], this info will get merged into the html-opts for constructing the appropriate html head section. The :tags metadata in particular gets merged in with any keywords which might have been specified explicitly as opts.

### Feb 27, 2020

I'm now on an airplane on my way back to Seattle, and have been finishing up my implementation of the above, and testing my assumptions about how everything will work together. In particular, I've gone back and edited out a few of the options out which I'm not 100% committed to exposing yet, but may add back in later in some form or another.

As I've been tinkering around to get things working, I've realized that the live view currently does not provide correct styling if you are operating offline, since it uses the references to the stylesheets and fonts up on ozviz.io. This has me realizing that ideally the live view server would serve these assets from the project resources. Similarly, for output compiled from export, it may be worth looking at how we can embed these assets directly, so that they aren't as dependent on ozviz.io.

### Feb 28th, 2020

Today I've been taking another look at my diagram and thinking about how to marry a number of threads of progress I have in mind for Oz's document processing features. In many ways, these threads converge towards a major step up in the abstract conception of what Oz is as a scientific document processing tool.

* code block highlighting/prettifying
* how to flexibly embed visualizations
    * so they can come with pre-rendered images
    * can "come alive" (interactive, etc) in an html context
* data table components
* the ability to extend or customize the base interpretation of Oz hiccup

Of course, this is made all the more challenging by the fact that this spec has to get interpreted in multiple ways in multiple places (to static HTML content, to live render view, etc). My initial stab at this is to generalize the processing of hiccup content with a function like this:

```
(defn process-form
  "Processes a form according to the given processors map, which maps tag keywords
  to a function for transforming the form."
  [[tag & _ :as form] processors]
  (let [tag (keyword tag)]
    (if-let [processor (get processors tag)]
      (processor form)
      form)))

(defn process
  [spec
   processors]
  (clojure.walk/prewalk
    (fn [form]
      (cond
        ;; If we see a function, call it with the
        ;;  args in form, so that you can
        ;; use fns as components, like Reagent
        (and (vector? form) (fn? (first form)))
        (apply-fn-component form)
        ;; apply processor function if applicable
        (vector? form)
        (process-form form processors)
        ;; Else, assume hiccup and leave form alone
        :else form))
    spec))

(process
  [:div
   [:h1 "Hello"]
   [:foo "What it be like?"]]
  {:foo
   (fn [form]
     (into ["BAR!!!"] (rest form)))})
;; => [:div [:h1 "Hello"] ["BAR!!!" "What it be like?"]]
```

This is a pretty flexible general purpose approach to processing content, and gives us ways of exposing and overriding defaults as necessary. Some questions remain about how this will get used in different contexts, and how using with a combination of static and live views:

* Generally, how will these options "bubble up" to higher level parts of the Oz API?
* Will different functions have their own processing defaults?
* Is there a better way of organizing or "registering" processing functions for application in different contexts?

These are some pretty big implications to work through as far as how this impacts the overall design and functionality of Oz, and so I'm going to take some more time to think through the various use cases and make sure everything makes sense.

### Conclusions

This month has been a bit challenging time wise. Still, I've managed to improve on Oz's static HTML output flexibility and styling. The good news is that I've been able to secure a bigger chunk of dedicated time to work on the project this month, and am optimistic that I'll be able to get a lot accomplished in the coming weeks!

Please stay tuned :-)

## Reagent

**Update 1**

I started right away by testing and implementing the idea I had about creating
functional components from Reagent components (i.e. Hiccup form where first
element is a function). Previously Component Class API was used to trigger
render when dependent RAtoms changed, and state was used to store Reagent
internal state. With new approach, state hooks are used instead:

https://github.com/reagent-project/reagent/pull/477

With some tuning, even form-2 and form-3 components can be supported. Of course,
form-3 component still creates Component Class, and hooks won't be usable
inside those. Current status is that 36 tests out of 128 are failing.

I also started investigating how big performance effect the change will have.
Because Reagent needs to effectively emulate Component Class API with
Hooks, to keep backwards compatibility, there will be some performance hit.

During implementation of functional components, I've also merged some
previously implemented changes to master, and refactored test checks
to provide better failure messages.

**Update 2**

I've continued the work in functional components branch, and I managed to fix several of remaining broken tests and commented out rest for now. Some of the fixed tests were related to how Reagent added component stack information into the error messages, which was retrieved from React class component internal properties. I changed the error handling to just mention the name of component where error originated (which can be retrieved for both Class and functional components). React now provides a proper way to get the component stack information for errors using Error Boundaries.

Tests which are now commented out were related to checking how many times a component was rendered when properties changed. These are broken because functional components can't choose if they are rendered after properties change, like Class components using `shouldComponentUpdate` method. In theory, it is possible to keep track properties and check if they changed, but render call can't be omitted due to how Hooks work. When a component uses Hooks, every render call has to use the same number of Hooks,
and omitting render call if props didn't change breaks this rule. (https://reactjs.org/warnings/invalid-hook-call-warning.html)

Advanced optimization still breaks some tests, but after those are taken care of, I plan to test some real work projects with this version and to release SNAPSHOT version for others to do some very early testing.

## Ring

**Update 1**

The previous two weeks have been spent designing a specification for
Ring 2.0 ([SPEC-2.md](https://github.com/ring-clojure/ring/blob/2.0/SPEC-2.md)), and writing up an architecture decision
record ([adr-spec-2.md](https://github.com/ring-clojure/ring/blob/2.0/doc/adr-spec-2.md)) justifying the design decisions taken. This
is currently just a first draft, and won't be finalized until it's
been extensively tested with an alpha release.

The initial proposal for funding was relatively modest, and after two
weeks of investigation and design work, I believe that this project
can be a little more ambitious and still remain achievable. This
ambition resolves itself into two additional features: support for
websockets and HTTP/2 push promises.

As of writing the first draft of the Ring 2.0 spec is complete. The
ADR is ongoing, with the justification for the websocket and push
promise designs currently still being written.

Once the ADR is complete, the next step is to open a public Github
issue for collecting feedback and constructive criticism from
interested parties.

While that's happening, I'll begin work on an experimental adapter
that implements the draft specification. A working adapter is critical
for testing whether the new specification is viable in practice, and
whether there are any issues with the spec that were missed during the
design process.

**Update 2**

Over the last two weeks the [ADR](https://github.com/ring-clojure/ring/blob/2.0/doc/adr-spec-2.md) for Ring 2.0 has been completed,
and [a Github issue](https://github.com/ring-clojure/ring/issues/393) has been posted in order to gather feedback on
the specification from the community.

There's been a good response to the new spec, with some insightful
questions asked. One of the comments has prompted a rethink of the
websocket send method. Other comments have pointed out parts where
the ADR or spec could be extended for clarity.

I'm going to try and reply to every comment while I begin work on the
initial Ring 2.0 alpha.


## Calva

**Update 1**

This first couple of weeks involved mostly information gathering about our primary goal of providing debugging features to Calva.

### Debug Adapter

It seems best to use [VS Code's debugger extension API](https://code.visualstudio.com/api/extension-guides/debugger-extension) since it will be familiar to users of VS Code. It will also help us to display debug-related information like variable values, current point of execution, etc. In order to do this we need to create a debug adapter, which implements the [debug adapter protocol](https://microsoft.github.io/debug-adapter-protocol/).

I spent some time learning about this protocol, looking at other implementations, and reading VS Code's documentation on creating a debug adapter. I also wrote the foundational code for Calva's debug adapter, which so far allows the user to start a debug session with the built-in Start Debugging command (this method of starting will likely change, see below), which starts in attached mode, and disconnect that session.

### Using cider-nrepl Debug Middleware

I also spent some time looking at options for providing debugging features in Clojure. Ultimately, cider-nrepl looked to be the best fit:

- It's very mature and actively maintained (including the debug middleware), and we can get assistance for it when needed.
- Calva already utilizes cider-nrepl, so extending that usage for debugging makes sense.
- Since the goal is to provide a very similar debugging experience to [Cider's debugger](https://docs.cider.mx/cider/debugging/debugger.html), it makes sense to use what Cider uses.

I've just started to explore how the communication with the debug middleware works and how it will fit into Calva's architecture (this involved gaining more in-depth knowledge of how nREPL itself works). Ideally, a debug session would start when a form containing the #break or #dbg reader macros is evaluated, and would not be started manually by the user. This debug session would also end when that form's evaluation is complete, after performing debug actions like stepping through the code, inspecting values, etc.

### Other

My journey into learning nREPL has lead me to see opportunities for our other goal of handling large results gracefully, and I've relayed that information to Peter, who is doing that work.

- Version 2.0.75 released
  - [Support cljs-suitable JavaScript completion](https://github.com/BetterThanTomorrow/calva/issues/552)
  - [Fix Printing to Calva REPL prints <repl#7> before each print out](https://github.com/BetterThanTomorrow/calva/issues/548)
- Version 2.0.76 released
  - [Fix Calva locking up when opening files with very long lines](https://github.com/BetterThanTomorrow/calva/issues/556)

**Update 2**

### Debugger

Evaluating a call to a function that has been evaluated with the #break reader macro in it now causes the VS Code debugger to start automatically. Execution stops at the location of the breakpoint, indicated with the yellow marker and line highlight, and the value of local variables can be seen in the left pane.

I've implemented the continue debug feature, which continues execution to the next breakpoint, and also made the debugger exit once the current form being debugged has finished evaluating. This also involved using information from cider-nrepl to find the correct location of breakpoints - this part still requires some work, but the basic functionality and flow are there.

I've also refactored the debugger's communication with cider-nrepl and some other parts of the code as it becomes clearer how the debug adapter and cider-nrepl debug communication flows should work.

### Handling Large Data Sets and Better Parsing

While handling large data sets better is a goal of this funding period, we've discovered that the foundations of parsing Clojure in Calva need some work, so Peter is giving that side of things much needed attention.

We're also setting up a test harness for our tokenizer, which is important because our TokenCursor implementation is used in many places throughout Calva.

### Other Improvements / Fixes

#### Version 2.0.78 released - 2020-02-28
- [Improve structural navigation through unbalanced brackets](https://github.com/BetterThanTomorrow/calva/issues/524)
- [Fix lexer going into some weird state after flexing certain patterns](https://github.com/BetterThanTomorrow/calva/issues/566)

#### Version 2.0.77 released - 2020-02-23
- [Make rainbow parens and highlight use the same lexer as Paredit](https://github.com/BetterThanTomorrow/calva/issues/561)
- [Fix: Some character literals throws paredit out of whack](https://github.com/BetterThanTomorrow/calva/issues/563)
- [Fix: Initial expand selection sometimes fails](https://github.com/BetterThanTomorrow/calva/issues/549)
- [Change line comment characters to ;;](https://github.com/BetterThanTomorrow/calva/issues/564)
- [Use editor namespace for custom REPL commands w/o `ns` specified](https://github.com/BetterThanTomorrow/calva/issues/558)
- [Add support for comment continuation](https://github.com/BetterThanTomorrow/calva/issues/536)
