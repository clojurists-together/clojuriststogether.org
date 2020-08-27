---
title: "July 2020 Monthly Update"
date: 2020-08-27T10:00:54+08:00
author: Alyssa Parado
summary: Read more updates from Fireplace, Cider, Figwheel, Practicalli, and Re-frame
---

## New members

Big welcome to our newest members, [Roam Research](https://roamresearch.com) and [Cisco](https://www.cisco.com)!

## Cider

**July 1-15**

- Got married :-)
- My computer broke and I had to get a new one
  - Then I had to troubleshoot AMD GPU driver issues :-)
- Specified docs license (https://github.com/clojure-emacs/cider/issues/2740)
- Tried to get CIDER's ClojureDocs integration working with ClojureScript, but it turned out to be more complex than I initially expected. I'll revisit this later. (https://github.com/clojure-emacs/cider/issues/2874)
- Fixed a dependency bug in Orchard and cider-nrepl (https://github.com/clojure-emacs/orchard/issues/91)
- Fix an eldoc issue on Emacs 28 (https://github.com/clojure-emacs/cider/issues/2875)
- Cut a new Orchard release (0.5.11)
- Bundle clojuredocs export with Orchard and cut version 0.6 (this eliminates the need to delete the data on demand when starting CIDER)
- New docs sections
	- Code Evaluation (https://docs.cider.mx/cider/usage/code_evaluation.html)
	- Working with Documentation (https://docs.cider.mx/cider/usage/working_with_documentation.html)
	- Profiling (https://docs.cider.mx/cider/debugging/profiling.html)
- Various small documentation improvements
- Discussed the addition of a potential UNIX socket transport for nREPL (https://github.com/nrepl/nrepl/pull/204)

Between the unexpected wedding and my hardware issues I'm a bit behind my schedule, but I still managed to do some pretty good progress. On the bright side - I feel I'm way more productive with my new computer, especially when the GPU works. :D



**July 16-31**

Here's the latest update.

* Investigated a mysterious spike in the traffic of clojuredocs-edn.netlify.app (it was 4 times higher this month compared to the previous month) - half a terabyte of clojuredocs-export.edn
* Released cider-nrepl 0.25.3 (mostly to address the complaints about downloading the ClojureDocs export automatically)
* Released inf-clojure 3.0 (no new functionality, but massive improvements to the internals)
* Released nREPL 0.8 (as promised in https://metaredux.com/posts/2020/06/15/nrepl-0-8-evolving-the-protocol.html)
* Extended the nREPL docs on building new middleware (https://nrepl.org/nrepl/building_middleware.html)
* Fixed cider-mode not being auto-activated for babashka (https://github.com/clojure-emacs/cider/issues/2882)
* Documented how to manually update one's copy of the clojuredocs data
* Merged Krell REPL support (https://github.com/clojure-emacs/cider/pull/2861)
* Fixed an init bug when using babashka (https://github.com/clojure-emacs/cider/issues/2882)
* Made connection-info babashka/generic nREPL server aware (https://github.com/clojure-emacs/cider/pull/2883)
* Added cider-eval-list-at-point for evaluating code between paired delimiters (lists, vectors, etc)
* Released CIDER 0.26 ("Nesebar") (https://github.com/clojure-emacs/cider/releases/tag/v0.26.0)

I'm glad that despite some unforeseen circumstances I managed to finish the funding cycle with several major releases!
There's a bit of work in progress that I plan to finish in the next couple of weeks and I'll credit ClojureDocs for supporting it:

* write a couple of blog posts about recent changes and the profiler
* add more gif-like demos to the docs
* polish the sideloading functionality (have user and system sideloading, add better docs)
* add more examples of CIDER features in hard-cider



## Figwheel

**July 1-15**

The past two weeks were mostly dedicated getting React Native support
up and running.

#### Configuring SSL certificate hosts with `:ssl-valid-hosts`

Added a new
[`:ssl-valid-hosts`](https://figwheel.org/config-options#ssl-valid-hosts)
option will let you configure which hostnames and ips the generated
certificate considers valid.

#### React Native Support

React Native support is complete!

The minimal configuration for a React Native project in
`figwheel.main` is now:

    ^{:react-native :cli}
    {:main example.main}

This also supports React Native Expo CLI as well.

React Native support includes the compiler passes from
[Krell](https://github.com/vouch-opensource/krell) which allows
`js/require` of assets like CSS and images.

Also spent time getting good production compilation support as well.

Please see the new [React Native figwheel
documentation](https://figwheel.org/docs/react-native.html) for more
details.

This is pretty exciting because the time to get a native app up and
and under development is quite fast. Using Figwheel to do these things
is great because we get the familiar Figwheel reloading patterns and
other bells and whistles.

As a final test I was able to create a MacOS app via
[react-native-macos](https://github.com/Microsoft/react-native-macos)
and it just worked without a hitch.

I really hope folks give the new React Native support a try as it's a
joy to code a native app this way.

#### Released `figwheel.main 0.2.10`

All of the above and more has been released in `figwheel.main 0.2.10`.



**July 16-31**

#### Fixes

* Fixed bug introduced by a refactor that broke `:connect-url` this
  has been deployed to 0.2.11

#### Updated the figwheel.main template

The figwheel main template was in need of some major updates.

* om support has been removed
* added the `+npm-bundle` option

The `+npm-bundle` option will generate a Figwheel project that uses
the Figwheel's new `:auto-bundle` feature and is a quick way to get
started with NPM in a Figwheel project.

I went through all the permutations of the CLI options for each to
ensure that the template works for the different tools and frameworks
and fixed several bugs in the process.

#### Added a tutorial/doc on how to use Nodejs with Figwheel

It's always been possible to use Figwheel to develop Nodejs
applications in ClojureScript. But it certainly isn't that clear how
to do so.

I added a document that shows how to set up a Nodejs application
includeing an example of how to create a hot reloadable `express`
application.

#### Much Much thanks!

A big thanks again to everyone who has come together to support this
work. All in all it has allowed me to make some major improvements. My
hope is that folks will find time to try out these new features, if
anyone encounters any problems please let me know.



## Practicalli

**July 1-15**

Continuous integration and deployment was the main focus of this period, with some Clojure spec generative testing.

Added Practicalli website and YouTube channel to the [Clojure.org community resources](https://clojure.org/community/resources).

#### Practicalli study group
Continuing a tools theme by setting up continuous integration (CI) for Clojure projects.  The CI service partially written in Clojure provides good support for Clojure projects.  Building on CircleCI to deploy Clojure applications to the Cloud using the Heroku service.

Broadcasts also cover using Kaocha generative test runner, both locally and on CircleCI

* [077 - Continuous Integration - Clojure deps.edn and Leiningen projects](https://youtu.be/WLcaXuAH1Ew)
* [78 - Continuous Integration - Clojure deps.edn projects and CircleCI](https://youtu.be/sXZKrD4cAFk)
* [079 - Continuous Integration - Deploying deps.edn project on Heroku via CirecleCI](https://youtu.be/P0D3W_ugfdA)

Content developed for Practicalli Clojure and Practicalli Clojure Webapps books.

#### Practicalli Clojure
Created several guides for new and existent projects, using CircleCI as a continuous integration service.  Kaocha is also used to run generative tests as well as unit tests.

Created [an introduction to CircleCI as a continuous integration service](http://practicalli.github.io/clojure/testing/integration-testing/circle-ci/random-clojure-function.html) and identified and documented recommended docker images to use for Clojure deps.edn and Leinigen projects.  The CircleCI examples are a little dated and includes only Leinginen project examples.

**Random Clojure Function project**.
Created a guide to develop a project that [generates a random function](http://practicalli.github.io/clojure/simple-projects/random-clojure-function.html) from the namepaces available in the REPL or the functions from specified namespaces.

Using the [random Clojure function project](http://practicalli.github.io/clojure/simple-projects/random-clojure-function.html), created a [guide to develop a project with the help of CircleCI](http://practicalli.github.io/clojure/testing/integration-testing/circle-ci/random-clojure-function.html) as the continuous integration service.


**Banking on Clojure**
Updated the [banking-on-clojure project](http://practicalli.github.io/clojure/clojure-spec/projects/bank-account/) using a TDD approach with Clojure spec.

Using Kaocha test runner run unit tests and Clojure spec generative tests locally and via CirceCI. Kaocha can run the same tests as clojure.spec.test.alpha/test function calls, without having to add code to the project. Adding the spect-check-plugin via the test.edn config did not run the generative tests, only the unit tests. Use the spec-test-check plugin to run the reports works when included as a command line flag.


#### Practicalli Clojure Webapps
Created [a guide to deploy a Clojure application via CirceCI onto Heroku](https://practicalli.github.io/clojure-webapps/projects/status-monitor-deps/). Think of Heroku as AWS without the cognative load to use it, simply push source code to Heroku and it builds and deploys the resulting application.

Updated the status monitor project to deps.edn to use as the basis for a guide to deploy Clojure applications via CircleCI to Heroku (a cloud platform as a service). The project takes a simple approach so the focus remains on the continuous integration pipeline.

CircleCI has an Heroku Orb, providing common configuration for deploying to Heroku.  The Heroku orb is used to deploy the project from its source code, building an uberjar and running the application from that uberjar.

Updated details of using postgresql with Clojure (documentation will be extended soon) and recommended next.java as a library to use for SQL.

Simplified the overall navigation on the Practicalli Clojure Webapps book.


#### Practicalli Spacemacs
Rewrite of the switch to develop page, using in-page tabs to simplify the guide into the two different approaches.


#### Hacking on Spacemacs
Added key bindings to refactor namespace forms in clojure-mode

`"ran" 'clojure-insert-ns-form`
`"raN" 'clojure-insert-ns-form-at-point`
`"rsn" 'clojure-sort-ns`

Updated [practicalli/.spacemacs.d](https://github.com/practicalli/spacemacs.d/) repository with doom modeline configuration, providing a very clean and simple UI experience for Emacs whilst still providing the most useful information.


**July 16-31**

Started series called Banking on Clojure to cover application servers, sql and relational databases.  This will cover the full development and deployment workflow, including clojure.spec for specifications and generative testing.

Added more tools to practicalli/clojure-deps-edn

Add anchors to all sub-headings across all books, making content easier to navigate by enabling navigation to specific sections in a page.  This helps keep relevant information together on one page and reference a specific section from other pages.


#### Practicalli Clojure WebApp
Started a section on [Application servers](https://practicalli.github.io/clojure-webapps/app-servers/), covering approaches to server configuration and server start/stop/reload.

Started a section on Databases that will initially cover H2 and Postgresql relational databases, using Sql with next.jdbc

Created Banking on Clojure WebApp content for the live broadcasts and book.  The project uses CircleCI for continuous integration and Heroku pipelines for deployment to staging and production.


#### Practicalli Clojure
Configure REPL startup using `dev/user.clj` file and `:dev` alias in practicalli/clojure-deps-edn configuration.  Added examples of [requiring namespaces and starting component lifecycle services at REPL starup](http://practicalli.github.io/clojure/clojure-tools/configure-repl-startup.html) added to Practicalli Clojure book.

Add section on [data browser tools](https://practicalli.github.io/clojure/clojure-tools/data-browsers/), extending REBL and Clojure Inspector with new projects Reveal and Portal.


#### practicalli/clojure-deps-edn
Identified main purpose of the practicalli/clojure-deps-edn project, to provide a large set of meaningful and consistently named aliases that would be available in all projects and less likely to be over-ridden by project specific deps.edn configuration.

Any experimental or alpha state tools are clearly marked as 'experimental - used at own risk' to set clear expectations.

Updated libraries used in aliases are using their fully qualified names, e.g. cider/cider-nrepl as this will be required for future versions of the Clojure CLI tool.

Added Google Storage mirrors for Maven Central for Americas, Asia and Europe to library repository configuration.  Also added a community mirror in Asia (China) for Clojars.

Recent alias additions include
- `:dev` - include the `/dev/ path to configure REPL startup with a /dev/user.clj file
- `rebel-nrepl` - run rebel REPL with nrepl connection for editor connections (eg. CIDER, Calva)
- `:nrebl` - REBL data browser on nREPL connection (e.g. CIDER, Calva)
- `:deploy-locally` to add a jar to _/.m2 directory
- `:deploy-clojars` to deploy a jar on clojars.org
- `:deploy-clojars-signed` to sign and deploy a jar on clojars.org
- `:carve` - a new project to carve out unused vars in code
- `:repl-reveal` - a REPL with data browser


#### Practicalli Spacemacs
Add Emacs profiler use to the [Spacemacs troubleshooting guide](https://practicalli.github.io/spacemacs/install-spacemacs/troubleshooting.html)


#### Pull requests
- carve - fixed alias in docs



## Re-frame

Most of the Clojurists Together work was completed in the first sprint earlier in the year in May.

However we have released a stable v1.0.0 version of re-frame which is major milestone in the project's history.

Also, many improvements to the docs have been made recently. Too many to mention but some are notable:

  - added a new FAQ on [laggy input](https://day8.github.io/re-frame/FAQs/laggy-input/)
  - added a new FAQ on [field focus](https://day8.github.io/re-frame/FAQs/FocusOnElement/)
  - added to existing FAQ on [global interceptors](https://day8.github.io/re-frame/FAQs/GlobalInterceptors/#answer-v100-onwards)
  - Additions made to [External-Resources](https://day8.github.io/re-frame/External-Resources/)
  - completely reworked [Infographics for Dominoes 1,2 3](https://day8.github.io/re-frame/event-handling-infographic/)
  - completely reworked [Infographics for Interceptors](https://day8.github.io/re-frame/Interceptors/#infographics). See also other explanations added to that tutorial.

Also:
  - closed #590
  - created #627 and developed a corresponding PR #628
  - React native and other niggles resolved in v1.0.0-rc* releases. See #604  #614  #615.

Numerically, we're at 16 issues and 4 pull requests.
