---
title: "June 2020 Monthly Update"
date: 2020-07-09T10:00:54+08:00
author: Alyssa Parado
summary: Read more updates from Fireplace, Cider, Figwheel, Practicalli, Re-frame, and Clojars
---


## Fireplace

* Implemented eval backgrounding.  Press CTRL-D during any interactive eval to
  detach from it and continue using Vim.  The results will open in a preview
  window when finished.

## Cider

**June 1-15**

Here's an update broken by project:

##### nREPL:

* fixed handling of aliased symbols in lookup middleware
* added var type to completion candidates
* cut nREPL 0.8.0-alpha1 and wrote a blog post about it
* small documentation fixes
* discussed with other tool authors adding some runtime info to the describe op

##### CIDER:

* Improved support for Babashka.
* Wrote docs on using CIDER with Babashka (and without Clojure in general).
* Fixed a couple of small bugs with REPL warning messages.
* Did some work towards making CIDER a bit Clojure-agnostic, so it's easier to use it with flavours of Clojure and other programming languages.
* Started work on sideloading support and all the nREPL 0.8 features.
* Reviewed a proposal for making auto-trimming REPL output (https://github.com/clojure-emacs/cider/pull/2857)
* Misc documentation improvements.

##### Misc:

* Helped with adding print middleware support to shadow-cljs
* Helped with adding eldoc support to babashka.nrepl


**June 16-30**

Here's the latest update.

##### nREPL

* Issued several alphas including small fixes to the new lookup and completion logic.

##### CIDER

* Fixed eldoc for `.` and `..`.
* Merged and polished REPL auto-trimming functionality (https://github.com/clojure-emacs/cider/pull/2857).
* Implemented initial support for sideloading.
* Started migration from Cask to Eldev for the project management.
* Reviewed and merged a couple of small bug fixes.
* Make some small improvements to the docs.
* Spent some time thinking on how to improve the eval handler creation in CIDER (e.g. by using an alternative API with named params)
* Investigated the xref.el API in relation to https://github.com/clojure-emacs/cider/issues/2831

##### cider-nrepl

* Issued to a bug-fix release that workarounds an incompatibility between clj-suitable and shadow-cljs 2.10.

##### orchard

* Merged a fix for a problem that caused orchard to hit the JVM max open files limits. (https://github.com/clojure-emacs/orchard/pull/95) Will issue a bug-fix release covering this soon.





## Figwheel

**June 1-15**

The last two weeks I spent some time documenting the NPM features a
bit more. Then I turned my attention to what I call the Jetty
conflicts problem. Which eventually lead me to investigate SSL support
and how to make connecting to a Figwheel server over HTTPS even
easier.

I also spent quite a bit of time looking into some advanced
compilation problems that occur when you are switching from Cljsjs
based project to an NPM `:bundle` based project. This lead me to
finally implement a `--clean` command which makes it easier to clean
your compiled artifacts out of your project.

#### The Jetty conflicts problem

The Jetty conflicts problem has dogged `figwheel-main` from the
beginning.

How did we get here? I switched to Jetty thinking that it would be
better to use the most common server used for Clojure development
(`ring-jetty-adapter`) and eliminate some bloat while making things
like configuring HTTPS easier. I didn't realize that switching to
Jetty would cause so many headaches for folks.

The main problem is that the way Jetty is packaged, makes version
conflicts extremely easy when we use slightly uncommon packages like
`org.eclipse.jetty.websocket/websocket-servlet` and
`org.eclipse.jetty.websocket/websocket-server`.

Jetty is developed across several "independent" packages.
Unfortunately these "independent" packages are tightly coupled so you
need to ensure that you are running the same version number for each
required Jetty package.

This gets easily thrown out of whack when another dependency like
`datomic-cloud` includes a Jetty dependency that is newer than the
Jetty dependency in Ring. The main problem is a UX problem because
when a conflict occurs the errors are about missing classes or methods
deep in Jetty leaving the user with little to go on.

I spent time looking at the server code and considering whether I
should get rid of the Jetty websocket packages and just use the HTTP
long-polling already in ring for the connection, or perhaps making the
websocket connection conditional on having an additional
figwheel-websocket package. I also looked at maybe going back to using
httpkit. All of these solutions were less than ideal.

Then it came to my attention that then next major release of Ring is
going to include websocket support. So I decided to just punt on this
until then and see if that improves things.

As it stands right now, I've updated to the latest Ring which has a
fairly recent version of Jetty and this should reduce collisions for
the time being.

#### HTTPS support

The look at the Jetty conflict problem drew me to consider the
necessity of using websockets, and considering if http long polling
could work.

The figwheel-main long polling support works really well, but it
becomes a problem if you are serving your development application with
SSL. That would require using HTTPS in figwheel as well for
long-polling to work.

Even though I decided not to use long-polling, this did lead me to
investigate how to make setting up HTTPS in `figwheel-main` easier.
Situations where folks are required to use HTTPS during development
are becoming more common and I wanted to see if I could make it
easier.

I eventually added a `:use-ssl` configuration option which is sure
that sets up and changes figwheel configuration to support SSL,
currently it still requires that you set up a Java Keystore. However I
wanted the `:use-ssl` option to work even if you didn't have a Java
Keystore configured.

Setting up a local dev certificate and getting into the form of a JAva
Keystore can be pretty esoteric. Creating a certificate, trusting it
in order to eliminate browser warings, and doing this all securely is
fairly challenging.  This eventually lead to the creation of the
[`certifiable`](https://github.com/bhauman/certifiable) library to
ease the creation of a local dev certificate and Java Keystore. Using
[`certifiable`](https://github.com/bhauman/certifiable) should enable
`figwheel-main` to have turn-key HTTPS support.

#### Cleaning

While `lein-figwheel` has support for cleaning up your compiled
development artifacts `figwheel-main` has chosen to allow you to
implement your own cleaning. Well, I've finally added cleaning to
`figwheel-main`.  You can either add the `--clean` to your CLI options
or or `:clean-ouputs true` to your Figwheel options.

This will clean all of your builds compilation output including
generated bundles AND all of the compiled output from and extra-mains
that you have configured as well.

Clean is a better option in many cases when you just want to target
what you are currently woring on.

It's handy to leave `--clean` or `:clean-outputs` in your
configuration while you work on debugging something.


**June 16-30**

Things are really moving along great! For the last two weeks I worked
on two main things. I spent most of my time working on getting
[certifiable](https://github.com/bhauman/certifiable) up and working
well on MacOS, Linux and Windows along with integrating it into
figwheel-main to enable zero-config HTTPS support.

Along with that during the last couple of days I've been getting
[React Native](https://reactnative.dev/) support started.

#### Very Simple HTTPS Configuration

Continued work on Certifiable to make it robust enough that it can
dependably create a Java Keystore across common platforms.

This allowed me to finish the implementation of the `:use-ssl`
Figwheel Option which completely automates the configuration of
Figwheel's server to use HTTPS.

Usually setting up your local server as an HTTPS server is tedious
combination of searching the web and several rounds of trial and
error. Now you can just set `:use-ssl true` and Figwheel will start
using HTTPS including a secure websocket.

Next steps are to allow users to provide the hostnames and ips they
want the certificate to be configured for.

This will ultimately be very helpful when you are interacting with
applications remote to the computer running the Figwheel, like on a
smartphone or touch pad.

Also started on the documentation for `:use-ssl`
[here](https://figwheel.org/docs/https.html).

#### React Native Support

I was able to complete an initial pass at React Native support.

The goal is to allow you to simply create a Figwheel project in the
root directory of a React Native CLI or React Native Expo project.

One can now set the new `:react-native` Figwheel option to `true` and
it will take care of the mechanics of hooking up Figwheel to the React
Native project.

The initial pass at this for React Native CLI is finished.

One problem is that the `npm_deps.js` file is regenerated on every
compile which causes the metro bundler to reload the whole application
on every compile. I solve this by creating a separate source directory
and copying `npm_deps.js` there only when it changes.

I will probably be working on React Native for the next two weeks as
there are several things that need to be done to make it all work well.

The most important problems to solve are:

* allowing asset requires in cljs code for images, json, css, etc.
  this will be taken from krell
* allowing customization of the index.js file
* providing a good pattern to support compiling for production
* providing good documentation

#### Thanks Again!

As always I want to thank everyone for this opportunity to dedicate
time to Figwheel.

I hope everyone is staying safe, and is finding time to care for
themselves, their loved ones and their community.




## Practicalli

**June 1-15**

Continued with Practicalli Clojure updates and weekly broadcasts covering Clojure spec and generative testing.  Also updates on Practicalli Spacemacs, including a move to doom modeline theme for a clean and modern look to Spacemacs.

I was still feeling the affects of illness for some of this period, but steadily improving.

#### Practicalli Study Group
Designing specifications for an online bank and basic generative testing video added to the [Practicall Clojure Spec playlist](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiBWGAuncfBRYhZtY5-Bp75s)

#### Practicalli Clojure Updates
Adding content and videos to the [Introducing Spec section](http://practicalli.github.io/clojure/clojure-spec/) of the book, providing live code examples via klipse enabling the reader to interact with the specs and functions in the Practicalli Clojure book website.

Spec is introduced by showing how it can be used in the REPL and added to project, with the leveraging-spec project containing many different examples.

Divided the Spec section into Spec data, that covers how the use of predicates, literals and custom functions as specifications along with the core functions to verify data against specs (conform, valid?, explain).

Expanded on Getting Started section, converting to Clojure CLI and tools.deps. Examples on using Clojure CLI tools to evaluate functions, load files and run applications.

Added details on configuring tools.deps and how to define and use multiple aliases.  Provided a collection of aliases for community tools, jcenter clojars mirror and how to use a local Artifactory instance.

Added section rebel readline for a feature rich command line REPL, including install, customisation and major features.

[Configuring a REPL on startup for deps.edn projects](https://practicalli.github.io/clojure/repl-driven-development/configure-repl-startup.html), examples of using `dev/user.clj` to require namespaces, call functions and manage component lifecycle services (mount, component, integrant, etc.).


#### Clojure deps.edn updates
- Using REBL from Emacs CIDER using nREBL middleware, alias and configuration
- Add example of a local Artifactory instance for a repository provider
- `:dev` alias - used to configure the Clojure repl automatically on startup by evaluating the content of `dev/user.clj`
- Update of dependency versions in the `deps.edn` file with depot
- Update unit testing aliases, add separate expectation aliases.
- `:test-path` alias - enable Emacs CIDER and other tools to add the test directory to the classpath


#### Practicalli Spacemacs Updates
Added page on calling component lifecycle services when refreshing the REPL from CIDER.

Updated practicalli/spacemacs.d to use doom modeline and doom-gruvbox-light theme to give a modern and clean look to Spacemacs.


**June 16-30**

After the broadcasts on Clojure spec, moving on to more tooling centric topics.  Starting with Unit test runners for Clojure CLI tool and preparing a series on continuous integration, packaging and deployment.

Flu symptoms have finally eased, so planning video tutorials in the later part of the sponsorship.

#### Practicalli Study Group
Wrap up of Clojure spec series of 5 broad, sharing my experiences with spec along with feedback from many others in the community.

Broadcast on unit testing and test runners, focusing on Cognitect Labs test runner and Koacha from Lambda Island.


#### Practicalli Clojure
Updated and extended the Getting Started section, using rebel readline for the command line REPL UI.  Added sections on configuring Clojure CLI and provided a wide range of community tools and other useful aliases.

Wrote a new section on Clojure Spec and generative testing, covering how to design specifications (composite vs hierarchical).  Also discussed organising specifications and how they fit into a project and along side Test Driven Development (TDD) and REPL drive development (RDD).

Wrote section on Regular Expressions in Clojure.  Added regular expressions for common string patterns, such as passwords, email addresses, etc.

Extended the Unit Testing section to cover useful practices with clojure.test library, refactor is assertions with are to work over data sets.

Added test runner section covering Cognitect Labs test runner and configuring categories of tests.  Started a section on kaocha covering the basic use and configuration.  This section to be extended as more projects are used with kaocha.


Configured git template to use live branch as the default branch name.  The word master has never made any sense as a term in a distributed version control system.

Added `:test-runner-cljs` alias to practicalli/clojure-deps-edn for the cljs-test-runner from Olical.  A test runner inspired by Cognitect Labs test runner for Clojure.

Discussion regarding some defacto approaches and conventions for Clojure tools.deps projects, especially around the idea of naming of aliases.  The practicalli/clojure-deps-edn repository is an example of how 30+ aliases could be defined to provide the most common tooling for all tools.deps projects.


#### Practicalli ClojureScript
Clarified the introduction to the ClojureScript book status and surfaced the work that remains current and functional. This content includes and several reagent based projects, building and deploying a ClojureScript website for ClojureBridge and creating a TicTacToe game with Scalable Vector Graphics.

Depreciated content developed several years ago, as much has changed in the ClojureScript world since then. The older content is still available and will be updated during July 2020 and onward.


#### Practicalli Spacemacs
Using CIDER test runner in Spacemacs with Clojure CLI projects.  Covering the Spacemacs specific key bindings, and how to configure the CIDER test runner in Spacemacs.

Hint on turning off custom themes to get the classic Emacs look.  The theme called default looks the same as classic Emacs.

Created a reference sheet for CIDER configuration variables, as there is no overall reference.

Using .dir-locals.el for project specific configuration.

#### Spacemacs Pull Requests
Refactor applications menu key bindings to create more room for key bindings and improve mnemonic keybinding use.  Sub menus added using the layer categories of the packages any existing key bindings originate from.



## Re-frame

**June 1-30**

As we had been working almost full-time on re-frame over the prior period we took a break from re-frame over this period and expect to resume work on re-frame's meatier enhancement proposals in July.



