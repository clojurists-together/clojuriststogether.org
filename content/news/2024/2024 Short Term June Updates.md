---
title: "June & July 2024 Short-Term Project Updates"
date: 2024-07-20T08:30:00+08:00
summary: Updates from clj-merge tool, Compojure-api, Enjure, Jank, and Lost in Lambduhhs Podcast
author: Kathy Davis
draft: True


---


We've got several updates to share from our Q2 project developers. Check out the latest in their June and July Reports following the project list below.  

[clj-merge tool: Kurt Harriger](#clj-merge-kurt-harriger)   
This project focuses on developing a git diff and merge tool for edn and clojure code with the aim of creating a [git mergetool](https://github.com/kurtharriger/clj-mergetool) that can be used as a replacement for git’s default merge tool for clj(s) and edn files.  

[Compojure-api: Ambrose Bonnaire-Sergeant](#compojure-api-ambrose-bonnaire-sergeant)     
[This project](https://github.com/metosin/compojure-api) will deploy the first new releases since 2019 (and include compojure-api 1.x, 2.0.0-alpha branch, ring-swagger), compojure-api/reitet migration tools, and Swagger 3.0.    

[Enjure: Janet A. Carr](#enjure-janet-a-carr)  
This project focuses on MVP for the [Enjure CLI tool](https://github.com/janetacarr/enjure/blob/main/notes.org) and providing the ability to create new projects and view/controller templates as well as delete templates.  

[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)   
[Jank's](https://github.com/jank-lang/jank) library parity with Clojure.core is around 20%. The next step is to fill out the language to make it feel more like Clojure - including Lazy sequences, Loop/recur, Destructuring, Symbol interning, and for and doseq macros.  


[Lost in Lambduhhs Podcast: L. Jordan Miller](#lost-in-lambduhhs-podcast-l-jordan-miller)  
Rejuvenate and streamline production of the [Lost In Lambduhhs Podcast](https://linktr.ee/lambduhhh), where the audience gets the opportunity to “meet the person behind the Github” -  illuminating the personal narratives and insights of tech luminaries, giving them a platform to share their perspectives while promoting their library or tool.  
\
&nbsp;
\
&nbsp;


## Clj-merge: Kurt Harriger  
Q2 2024 Report No. 2. Published July 1, 2024  
### Introduction  
This tool aims to reduce unnecessary conflicts due to whitespace and syntax peculiarities by using a more semantic approach to diffing and merging. I'm grateful for the support from ClojuristsTogether and the invaluable feedback and support from the Clojure community.  

### Recent Progress  
This month, I focused on the following improvements:  
- **Bug Fixes**: Several bugs were fixed to enhance the stability of the tool.  
- **CI/CD Pipeline**: A CI/CD pipeline was added to streamline the installation process and prevent additional regressions.  
- **Error Reporting**: Simplified error reporting to make it easier for users to provide useful feedback when the tool does not work as expected.  
Due to an exceptionally busy schedule, progress on diff visualization and project promotion was limited.  

### Milestones Overview  
The project was structured around several key milestones:  
1. Development of the MVP - Mostly complete  
2. Enhancement of diff handling and presentation - Ongoing  
3. Community engagement and feedback integration - Ongoing  
4. Performance optimization and cross-platform compatibility - Done  

### Milestone Progress  
1. **Development of the MVP**  
    - **Goals**: To create a minimal viable product using `editscript` and `rewrite-clj`.  
    - **Recent Updates**: Bug fixes were implemented to enhance the stability of the tool.  
    - **Status**: Mostly Complete. From a technical perspective I have been able to test the feasibility of the implementation and learned a lot. Its hard to say when this is "done,"  I don't quite feel ready to push the adoption until more work has been done on the diff visualization.  

1. **Enhancement of Diff Handling and Presentation**  
    - **Goals**: To improve the readability and utility of diffs for developers.  
    - **Recent Updates**: Limited progress on diff visualization due to time constraints.  
    - **Status**: Much more work still needs to be done here.  

1. **Community Engagement and Feedback Integration**  
    - **Goals**: To actively engage with the community to gather detailed feedback and real-world merge conflict examples.  
    - **Recent Updates**: Simplified error reporting to facilitate better feedback.  
    - **Next Steps**: Increase efforts to engage the community and aim to present at a Clojure meetup in the near future.  

1. **Performance Optimization and Cross-Platform Compatibility**  
    - **Goals**: Simplify the installation process.  
    - **Recent Updates**: A CI/CD pipeline was added to streamline the installation process.  
    - **Status**: Done  

### Conclusion  
Thank you for your support and contributions to the clj-mergetool project.  <br>

---


## Compojure-api: Ambrose Bonnaire-Sergeant  
Q2 2024 Report No. 3. Published July 8, 2024   

Last month I successfully [added support](https://github.com/metosin/compojure-api/compare/23b96cb7ac39180160cb8e967889aa03b1d7c119...master)
for compojure-api 1.x coercions in the 2.x branch. This is one of the last steps towards backwards-compatibility of 1.x code using the 2.x branch.
I did not make any progress on this front this month, but the remaining steps are starting
to crystalize, which I will talk a bit about here.  

Implementation wise, a 1.x coercion is detected with `fn?`, and implies the Schema backend (Spec support was addedin 2.x). Such a coercion is a nested function with the shape `request->field->schema->coercer`, often
often implemented like `(constantly nil)` or `(constantly {:body matcher})`.  

Several insights during this 3 months project made backwards-compatibility particularly clean.  

Once I grokked the main differences between 1.x and 2.x coercions at the end of month 2, I realized that
my efforts to restore support for ring-middleware-format was misguided. I could instead
translate 1.x coercions to muuntaja's expected format.  

There are two steps to this. The first was to add support for "legacy" coercions in 2.x's coercion
abstraction. This involved changing the `coerce-request` implementation in [compojure.api.coercion.schema](https://github.com/metosin/compojure-api/compare/23b96cb7ac39180160cb8e967889aa03b1d7c119...master#diff-7267ec6276ef7f826245f8be37898dcaa3b2ce6915d1cede0abec0aba8f5935a).  

The second step is to translate ring-middleware-format's `:format` options to muuntaja's `:formats`.
I have only done this for the default options, and currently any custom `:format` extensions do not work.  

One wrinkle that is difficult to reconcile in all cases is that 2.x dropped implicit support for several coercion formats such as yaml. In order to maintain backwards compatibility with 1.x coercions, we want to ensure that yaml formats are supported by default for legacy coercions.  

I have not completely solved this problem, but I identified that coercions are usually configured in terms of
the `api-defaults` var, which is a map with containing `:format` in 1.x and `:formats` in 2.x.
In both branches, I introduced a breaking change, renaming `api-default` to `api-defaults-v1` and `api-defaults-v2`
using `:format` and `:formats` respectively. This might help decide whether to include yaml coercion by default, but requires more thought.  

Finally, I removed any attempt to add ring-middleware-format support in the 2.x branch since I realized it was unnecessary.  

### recompojure  

As part of this 3-month project, I am releasing a [recompojure](https://github.com/frenchy64/recompojure), which is a library providing compojure-style macros that expand to reitit.  

It does not have a stable release yet, but there are some interesting problems to solve.  

I worked on recompojure for the first month of this project, but stopped after I ported it out of a corporate repo I prototyped it in. I realized that compojure-api has a different set of features than reitit, and decided that my time would be better served working on compojure-api itself.   

One big difference between reitit and compojure-api is reitit accepts a local configuration (`opts`) map
which compojure-api is extended using global state. To preserve this local configuration style, recompojure
is structured as a macro-generating-macro that is passed a top-level options map. An additional subtlety is
that this options map is needed at compile-time when compojure-api does most of its work.  

For example, the `load-api` call here defines all the compojure-api macros such as `GET`, `POST`, `context`, etc., but their extensions can (eventually) be centralized in the `options` var. This attempts to
address a common concern using compojure-api where care must be taken to ensure extension are loaded before
any routing macros are expanded---this style should centralize extensions such that they are
deterministic without further safe guards.  

```clojure
(ns com.recompojure.compojure-api1
  "Exposes the API of compojure.api.core v1.1.13 but compiling to reitit."
  (:require [com.recompojure.compojure-api1.impl :as impl]
            [clojure.set :as set]))

(def ^:private options {:impl :compojure-api1})

(impl/load-api `options)
```

From here, I would like to add compojure-api 2.x support, and fully take advantage of the implicit options map
as described. The next big feature would be to reconcile compojure-api and reitit's middleware support
so that compojure-api-style applications can easily be translated to reitit via recompojure. In particular,
most compojure-api app use the `api` function to create an app, but recompojure does not yet support translating this to reitit.  

### Project Summary  

This 3-month project had two main focuses.  

The first half concentrated on performance of the 2.x branch of compojure-api and ensuring stable versions
of security fixes were deployed.  

My main goal for the second half of this 3-month project was to ease future maintenance of compojure-api by
retiring the 1.x branch. That way, features need only be developed in the 2.x branch and can
still be enjoyed by 1.x users.  

This was much more challenging and onerous than I anticipated, and I would not have
been able to invest time in this if Clojurists Together had not funded the project.
My main activity was attempting to understand and compare two versions of the same project and reverse-engineer
the evolution of the project.  

I'd like to thank Clojurists Together for selecting this project for funding.  <br>

---


## Jank: Jeaye Wilkerson  
Q2 2024 Report 3. Published June 30, 2024  

Welcome back to another jank development update! For the past month, I've been
pushing jank closer to production readiness primarily by working on multimethods
and by debugging issues with Clang 19 (currently unreleased). Much love to
[Clojurists Together](https://www.clojuriststogether.org/) and all of my
[Github sponsors](https://github.com/sponsors/jeaye) for their support this
quarter.  

### Multimethods  
I thought, going into this month, that I had a good idea of how multimethods
work in Clojure. I figured we define a dispatch function with `defmulti`:  

```clojure
(defmulti sauce-suggestion ::noodle-type)
```

Then we define our catch-all method for handling types:

```clojure
(defmethod sauce-suggestion :default [noodle]
  (println "You can't go wrong with some butter and garlic."))
```

Then we define some specializations for certain values which come out of our
dispatch function.  

```clojure
(defmethod sauce-suggestion ::shell [noodle]
  (println "Cheeeeeeeese!"))

(defmethod sauce-suggestion ::flate-white-rice [noodle]
  (println "Hor fun gravy."))
```

Then, when you call the `sauce-suggestion` function, first the dispatch
function is called and then the correct method is looked up and called.  

```clojure
(sauce-suggestion {::noodle-type ::shell})
Cheeeeeeeese!

(sauce-suggestion {::noodle-type ::spaghetti})
You can't go wrong with some butter and garlic.
```

This is as much as I knew. But wait, there's more!  

### Hierarchies  
It turns out that multimethods match dispatch values based on a couple of
different hierarchies, too. If you're matching actual class types, like
`String`, you could have a method which is parameterized on `Object` and it will
be a catch-all. So this would allow you to match on everything which inherits
from `IRenderable`, for example, and then use that interface to render the
object. I wasn't concerned about this, since jank's object model isn't based on
inheritance. I figured I could leave this whole feature out of multimethods.  

However, it turns out that Clojure supports another form of hierarchies! Even
crazier, we have full control over those hierarchies at run-time and we can
build as many as we want. Check this out.  

```clojure
; We can classify spaghetti and penne as Italian.
; They will both be considered children of ::italian.
(derive ::spaghetti ::italian)
(derive ::penne ::italian)

; Then we can define a method based on the parent.
(defmethod sauce-suggestion ::italian [noodle]
  (println "Sugo al pomodoro."))

; This allows us to match multiple dispatch values in a
; deterministic and intuitive way.
(sauce-suggestion {::noodle-type ::penne})
Sugo al pomodoro.
```

There are a handful of related core functions for working with these
hierarchies. jank now implements all of them.  

* `make-hierarchy`
* `isa?`
* `parents`
* `ancestors`
* `descendents`
* `derive`
* `underive`

As I was implementing multimethods, I needed a few more core functions, so those
were all implemented as well:  

* `hash-set`
* `disj`
* `defmulti`
* `alter-var-root`
* `bound?`
* `thread-bound?`

Notably, this includes `bound?`, which required me to actually create a
dedicated unbound var object so I could distinguish between unbound vars and
vars holding `nil`.  

### Clang/LLVM 19  
Most of my time this past month was not spent developing new features for jank,
which is why I only have multimethods and 13 new functions to report. Instead,
my time was spent trying to get jank ported over to the latest Clang/LLVM
version, which will allow us to leave Cling behind. jank uses these for JIT
compiling C++ code and upgrading to the upstream Clang will unlock huge
performance wins, make compiling jank easier, and will allow for jank to follow
the bleeding edge of the native JIT space. However, before we get there, we have a
couple of bugs to get past.  

### Extern templates  
The [first bug](https://github.com/llvm/llvm-project/issues/97137), which was causing JIT
linking issues, I reduced down to a simple test case involving an extern
template which is linked either in the current process or in a loaded shared
library. Clang will be unable to resolve the address of the definition of that
function. As it happens, the fmt library uses this pattern to provide some
optimized versions of certain templates. However, we can fortunately work around
this, since fmt wraps those definitions in a `FMT_HEADER_ONLY` preprocessor flag.
The relevant fmt source is [here](https://github.com/fmtlib/fmt/blob/b61c8c3d23b7e6fdf9d44593877dba1c8a291be1/include/fmt/format.h#L4283).  

The process of narrowing this down from the entire jank runtime is cumbersome,
ruling out chunks of code at a time while still trying to keep things compiling
and correct.  

### Optimization crash  
This is the [blocking bug](https://github.com/llvm/llvm-project/issues/95581)
preventing jank from switching to Clang. It only happens in release builds,
which also makes it harder to debug. This month, I traced the bug down from
a crash in jank all the way to a minimal test case involving assignments with an
implicit constructor. However, when testing whether or not the bug existed in
Clang 18, I found that it indeed did not. This meant that it's since been
introduced in the yet unreleased Clang 19. So I bisected around 1300 commits,
each time requiring a fresh Clang/LLVM compilation and taking ~30m. It was an
entire day of all 32 cores on my machine being busy compiling, but fortunately I
could script all of the hard work just using some bash. Bisecting allowed me to
find the commit which introduced the issue. This has yet to be fixed and I don't
have the expertise to know what's wrong with that commit, but I've provided a
test case, pinged the relevant people, and now I'm hoping the real experts can
come in for the save.  

### Clang status  
Aside form those two issues, only one of them being a blocker, the port to Clang
is ready. In debug builds (which avoid the second bug), jank can pass its full
test suite using Clang 19. Even better, some early benchmarking has shown that
Clang 19 is **more than twice as fast** as Cling when it comes to JIT compiling
large amounts of generated C++ code (such as all of `clojure.core`). That will
mean faster startup times and shorter REPL iteration loops.  

### What's next?  
Implementing multimethods identified a couple of issues related to certain
sequence types in jank which I'm still investigating. Once those are sorted,
I'll continue working through the requirements to implement `clojure.test`,
which is why I was implementing multimethods in the first place. From there, I
can start testing my jank code using more jank code and the dogfooding cycle can
really begin. Stay tuned, folks!  <br>

---


## Enjure: Janet A. Carr  
Q2 2024 Report. Published June 12, 2024  

Progress has been good. I regularly stream Enjure to my audience on Twitch which seem to be bootstrapping Enjure's Github stars.  

Despite the progress, I intentionally expanded the scope of the project by implementing an
HTTP router for Enjure. I'm not entirely sure that this was a wise decision, but
it adheres to Enjure's guiding philosophy. Enjure's HTTP router is implemented
with a Radix tree and supports path parameters, in pure Clojure. I'm hoping to come up with a scheme for query, body, path, and form coercion soon, but I
haven't decided on a scheme I like. The router lives in a dynamic var managed
by Enjure and is updated by macros for defining pages and controllers.  

Enjure has several macros enforcing a similar convention to define HTTP resources.
The purpose of which brings together a resource's routes, contract, coercion and handling
expressions to a single namespace. Often Clojure web applications are structured with several
libraries. For example, Consider an application with Reitit with ring, next.jdbc with postgreSQL,
The application will likely have its routes in one namespace, it's handlers in another namespace,
its business logic in another namespace and data modification language (DML) in another namespace;
Necessitating opening several source files to accomplish a small task. Rarely do the
Routes, contracts, and handlers change. If they do, it's from minute changes.
Bringing these together cuts down on the cyclomatic complexity of developing web
Applications in Clojure. Thanks to homoiconicity, I can create constructs to help with
Exactly this:  

```clojure
;; Example from Enjure repo
(defpage user "/users/:user-id"
  [req]
  (let [{:keys [path-params]} req
        {:keys [user-id]} path-params]
    (format "<h1>Hello, %s</h1>" user-id)))

```

This "page" construct is simply a function var under the hood, but also manages the routing-table
Var. There's no middleware required to update the routing table upon REPL reload. Simply evaluating
The buffer/namespace will change the routing table. defpage expects a string as its return value as
it's largely tied to the content-type text/html. In the future I hope to have other, similar view
constructs to support other popular application mime types.  

Similarly, there are actions, changes, and removals that correspond to POST, PUT, and DELETE
HTTP methods, respectively. Since Enjure places a high emphasis on convention, I'll only show a
Simple example of a Sign In action for a user:  

```clojure
(defaction signin "/signin"
  [req]
  (let [{:keys [email password]} (:form-params req)]
    (if (check-db email password)
      (redirect pages/home :see-other) ;; redirects to whatever route pages/home var has.
      (pages/siginin req) ;; this is a page var to render
  )))
```
Since resources in Enjure are just function vars, they can be called directly, and also reverse-routed
to using some of the response macros. In the above example, if the signing check passes, redirect
Redirects to whatever route pages/home has declared. (Reverse-routing is largely for convenience, and
Not mandatory, the redirect macro supports redirecting to static paths/URLs, Enjure resources like pages, and
Values from functions).  

Ideally, resources would interact with the database through a data model supported by the framework.
My ideas for this are still experimental and can be found in the repository under the internal â€œfrmâ€
namespace. Currently, it's some simple templating of basic queries by querying the information_schema in
Postgres, and interning the query functions as vars. These are queries I've seen regularly over the years,
and I'm sure that, once implemented, will give developers a boost in productivity. Plus, there's the added
benefit of being decoupled from whatever mechanism I choose for supporting migrations/entities in Enjure (still
A TBD). However, this model does not alienate developers who opt to create more complex queries
as those are supported as well with next.jdbc.  

Another idea I've been experimenting with is something I call the ReactiveRecord. ReactiveRecord uses
Software transactional memory to synchronize with the database, providing an in-memory DB representation.
I think given the functional interface provided by FRM above and information schema data. It might be interesting, but I do believe this kind of transacting might be faux-pas or even dangerous, so more thought is needed here on my part.  

All of this will ideally be controlled with the Enjure CLI. Enjure puts a heavy emphasis on reducing
developer friction. Given a base installation of Clojure, installing Enjure should allow for the creation and
management of Enjure projects very easily. I'll admit this is an area I've been slacking on a bit since I
wanted to finished with other core components first. As of writing this, the Enjure CLI has two basic commands:
Notes and help.  Notes search a project for comments containing NOTES, FIXME, TODO, and HACK. Help just
prints out the help dialog. Soon enough Enjure CLI will support creating and deleting resources, migrations,
Entities, dependencies, etc., as well as creating new projects. The Enjure CLI can already be installed to a user's path as a CLI utility written entirely in Clojure.  

Finally, documentation of the project has become my lowest priority and definitely at risk. However, I'm not too concerned about the documentation faltering. In some sense, I'm a technical writer thanks to my blog, so I believe writing documentation for Enjure won't be as challenging as the rest of the project.  <br>

---


## Lost in Lambduhhs Podcast: L. Jordan Miller  
Q2 2024 Report 2. Published June 27, 2024.  

I have made continued progress on my new podcast series, thanks to the support from Clojurists Together. Here are the key milestones I've achieved since my last update and my plans moving forward:  

### Theme Music and Audio Engineering  
- **Created Theme Music and Audio Engineering Template:** Developed the theme music and an audio engineering template to ensure a consistent and professional sound for each episode.  

### Riverside.fm Proficiency  
- **Learned Riverside.fm Editing Software:** Gained proficiency in using Riverside.fm's editing software and created a workflow for efficiently editing audio.  

### Episode Releases  
- **Released Two Episodes:**  
  - David Nolan  
  - Arne Brasseur  

### Guest Coordination and Diversity Efforts  
- **Gender Diversity Challenge:** I am striving to ensure gender diversity in my episodes, which has been challenging.  
- **Reached Out to Prospective Guests:** Contacted three prospective guests, with two having returned my communications.  

### Recent Challenges  
- **Scheduling Conflicts:** Faced scheduling conflicts due to a death in my family followed by getting sick with strep throat. I am now on day 8 of recovering from the sickness and have recordings scheduled for next week.  

### Next Steps  
- **Continue Outreach:** Continue to reach out to schedule recordings, ensuring a diverse lineup of guests.  
- **Timely Editing and Release:** Edit and release episodes in a timely manner, promoting on Clojurians Slack, Clojure Weekly updates, LinkedIn, and Twitter.  
- **Expand Promotion Channels:** Create a Mastodon account to help promote the podcast.  

### Conclusion  

Despite recent challenges, I am on track with my project timeline and excited about the content I am creating. I will continue to provide updates as I progress further.  <br>

---



