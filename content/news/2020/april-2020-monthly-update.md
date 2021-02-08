---
title: "April 2020 Monthly Update"
date: 2020-06-15T14:00:54+12:00
author: Alyssa Parado
summary: Read more updates from Ring, Calva, Reagent, Fireplace, and Clojars
---

## Ring

**Apr 1-15**

Ring 2.0.0-alpha1 has been released, with the adapter and servlet
utility functions supporting both Ring 1 and Ring 2 keys by default,
though there is also an option for forcing a particular version.

Based on community feedback, the Ring 2.0 request and response headers
have been changed to be closer to the standard laid out in the Ring
1.x specifications.

I've begun setting up a project using the [JMH](https://openjdk.java.net/projects/code-tools/jmh/) benchmarking harness
to compare Ring 1 with Ring 2 performance, particularly around
building the request map. Higher level tests with ApacheBench proved
too imprecise to draw meaningful conclusions, as the variance between
results was higher than any measurable difference between Ring
versions.

I've also been reconsidering some of the ideas around adding request
and response protocols to Ring as a way of supplementing map key
access. Initially I considered them out of scope, as they're a
performance enhancement, however they could also be used as a
compatibility mechanism for middleware that supports both Ring 1 and 2
formats.

**Apr 16-30**

I've constructed a [JMH](https://openjdk.java.net/projects/code-tools/jmh/)-based benchmarking project for Ring 1.8 and
Ring 2.0, in order to get a better idea of the relative performance of
both versions, specifically around converting to and from Java servlet
objects. I've written up the [initial findings](https://github.com/ring-clojure/ring/issues/393#issuecomment-622516908) and published them
as a comment on the Ring 2.0 GitHub issue.

I've also been fixing various smaller issues with Ring 1.8.0, and
should be ready to release 1.8.1 by the end of the day.

Work on websocket support has been on pause while performance and
compatibility concerns were investigated, but that work is now ready
to be resumed. Work on Ring 2.0 will continue over the next few
months, and further alphas will be released to allow community
feedback and testing of new features.

The first stable version of Ring 1.0 was released a little over 8 years
ago. With a little luck, Ring 2.0 will last even longer.



## Calva

**Apr 1-15**

##### Debugger

The debugger has been released! [Documentation for the debugger](https://calva.io/debugger/) was written and published that covers the existing functionality and lists current and upcoming features.

Since then, I've added step over, step in, and step out functionality as well as annotations in the editor to show the value of the current form as execution moves to each breakpoint. I've updated the docs to reflect these changes as well as made some general improvements to the docs.

I'm currently working on polishing the latest changes and then will release them along with the updates to the docs.

##### Docs

The [Calva docs](https://calva.io/) have a new look and a new home! We switched to using mkdocs for building our docs and are using the material theme.


**Apr 16-30**

##### Debugger

I've added a command to instrument functions for debugging as an alternative and more convenient method than adding `#dbg` reader tags to the code file. I've also added editor decorations to visually indicate which functions are instrumented. The instrumented function definitions as well as the usages gain a top and bottom border and a background. clj-kondo is used [as a JVM library](https://github.com/borkdude/clj-kondo/blob/master/doc/jvm.md#api) to get the var definitions and usages, and [this data](https://github.com/borkdude/clj-kondo/tree/master/analysis) is matched up with cider-nrepl's [`debug-instrumented-defs`](https://docs.cider.mx/cider-nrepl/nrepl-api/ops.html#_debug_instrumented_defs) response data to create the decorations on document change.

The instrument command and decorations features were released and I've started working on displaying structured values, for locals, in the VS Code debugger side pane (currently non-primitives are shown as strings). This should allow a user to "drill down" into a structured value like a map or collection.

## Reagent

**Apr 1-30**

Reagent 1.0.0-alpha1 is out and contains implementation of both
creating React function components from Reagent components
and options. The default is still to create class components
by default, to ensure compatibility, but an option can be used
make function components the default.

After the last update, I had pretty much everything ready, but
I wanted to change the API a bit before first release.
The first implementation of
options had one problem related to caching, as different
options could result in different results but the cache was shared.
This was one of the reasons I wanted to create "Compiler" object
which is created using the options. This object can hold the cache
(or unique key for caches) and is implemented as a reified
protocol. This object currently holds the implementation for a few
Reagent internal functions, most importantly `as-element`, and in
future the function or macro creating this object from options map,
can generate different code for this (and other) methods.

Implementing and getting all the tests passing with this approach
took quite long, but I finally got everything working. After
finishing the code, I added some new documentation pages
and a new example showing making Function components default
and using Hooks with Reagent, and created the first release for testing.

Based on the comments from users, I've already added
two more "shortcuts" to Reagent: `:f>` to create
Function Reagent component (using the default
options), and `:r>` to use React
components without properties conversion done by
`:>` and `adapt-react-class`.

Additionally I've followed latest developments in ClojureScript
compiler, and added new test environment for Reagent, using the
new `:bundle` compilation target. This target generates
JS tooling compatible output. This output can be passed to
Webpack, or for testing, Karma.

## Fireplace

**April 1-30**

* [Handle `:Require` exceptions](https://github.com/tpope/vim-fireplace/issues/374)
* Spike out a async `:Eval` via an interface to background a blocking eval
  with a key binding.  This is a substantial retooling that also allows among
  other things sending text to stdin. Needs a bit more polish to push to
  master but should be done by the second update.

## Clojars

**[April 1-30](https://tcrawley.org/clojars-worklog/#apr-2020)**

This month included work finishing up the migration to AWS, including:

-   Setting up a way to build new AMIs for the server
-   Modifying the deploy process to allow new instances to deploy the current release
-   Moving the server to an auto-scaling group to make it more robust and allow zero-downtime new AMI releasing
-   [Documenting the new server architecture](https://github.com/clojars/clojars-server-config#system-diagram)
-   Upgrading from Java 8 to Java 11

I then started on improving security. The work here was focused on adding a [deploy token](https://github.com/clojars/clojars-web/issues/726) feature. This was [released in early May](https://groups.google.com/forum/#!topic/clojars-maintainers/nqV5yc-05BI), but 90% of the work was completed in April.

I also [fixed an issue](https://github.com/clojars/clojars-web/issues/495) where the cookies weren't being set as secure.
