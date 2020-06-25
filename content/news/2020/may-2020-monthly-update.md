---
title: "May 2020 Monthly Update"
date: 2020-06-15T15:00:54+12:00
author: Alyssa Parado
summary: Read more updates from Fireplace, Cider, Figwheel, Practicalli, Re-frame, and Clojars
---


## Fireplace

* Retool `:Eval` to support streaming response.  In my async `:Eval` spike I
  wrote this piece off as being a trivial change but it took quite a bit of
  refactoring to get this working.
* Implement sending text to stdin.

## Cider

**May 1-15**

##### nREPL:

* Implemented an initial version of the built-in code completion middleware
* Started work on the built-in lookup middleware
* Reviewed and merged dynamic middleware loading functionality (some really great work by Shen Tian)
* Provided default print functions via `nrepl.util.print`.
* Reviewed and merged a couple of other bug fixes by Shen Tian.
* Small documentation updates

##### cider-nrepl:

* Normalized the interface of several ops by making the params they accept consistent

##### Piggieback:

* Cut Piggieback 0.5 with pprint support
* Wrote some design documentation

##### CIDER:

* Added support for the new nREPL completions op
* Fixed a couple of long-standing bugs related to streamed printing in the REPL (https://github.com/clojure-emacs/cider/issues/2628 and https://github.com/clojure-emacs/cider/issues/1971)
* Make it possible to configure the print buffer size that nREPL uses via CIDER
* Reviewed and merged some inspector improvements
* Added a couple of small command (e.g. `cider-repl-toggle-clojure-font-lock`) and made many documentation improvements
* Dropped support for Nashorn

##### inf-clojure:

* Worked with Dan Sutton on simplifying inf-clojure and making it more robust (probably I'll cut a new major release by the end of the funding round)


**May 16-31**

##### nREPL:

* Implemented an initial version of the build-in lookup middleware
* Started work on some best practices documentation for middleware authors
* Started working on adding some extra language/runtime metadata to nREPL that might make it easier for client to
support multiple nREPL implementation (related to https://github.com/clojure-emacs/cider/issues/2848).

##### Orchard:

* Merged a fix for #86 that fixes the resolution order in the info function
* Cut a new release (0.5.9)
* Spent a lot of time debugging an issue with the new release (https://github.com/clojure-emacs/orchard/issues/91)
* Cut a new release (0.5.10)

##### cider-nrepl:

* Cut a new release (0.25)

##### CIDER:

* Many small documentation improvements (including some new page navigation)
* Improved the behavior of commands based on `cider-jump` (https://github.com/clojure-emacs/cider/issues/2850)
* Fixed eldoc on Emacs 28.1
* Discussed ideas on how to make CIDER somewhat Clojure-agnostic (https://github.com/clojure-emacs/cider/issues/2848)
* Cut a new CIDER release (0.25)

##### Misc:

* Fixed a deployment issue with the clojuredocs-edn-export tool (it generates data that orchard uses)


## Figwheel

**May 16-31**

##### Thanks!

This last couple of weeks have been fun and productive. I'm grateful to
have had the time to work on the new `:bundle` target and simplify how
figwheel integrates with the larger NPM eco-system. As a result, the
NPM workflow in Figwheel has been vastly improved.

The following `dev.cljs.edn` config is enough to get you started with
the new bundle target and webpack.

```clojure
^{:auto-bundle :webpack}
{:main example.core}
```

Thanks to everyone who contributed to Clojurists Together and to those
great early adopters of the new `:bundle` target who've helped me
understand what folks are needing.

If you use these new features in Figwheel please reach out with any
comments/questions on the `#figwheel-main` Slack channel as I'd like
to really understand what folks are running into.

##### Overview

I spent the last two weeks continuing to improve the `:bundle` target
support in [figwheel-main](https://figwheel.org).

You can see this work reflected it the `com.bhaumsn/figwheel-main
0.2.7` release and the newly updated [documentation for working with
NPM](https://figwheel.org/docs/npm.html).

If you head over to that page you will see a vastly improved document
that details the improvements. You will also see that I spent a
weekend updating the look of the [Figwheel
website](https://figwheel.org). The site is still a work in progress
as I only implemented about half of [Lubov
Soltan](https://lubovsoltan.com/)'s design.

To ensure that I wasn't doing anything that would complicate working
with React Native and the new `:bundle` target, I spent some time
looking at
[react-native-figwheel-bridge](https://github.com/bhauman/react-native-figwheel-bridge). I
ended up spending some time updating it so that it works with the new
ClojureScript compiler. In addition I refactored RNFB and added
support for `expo`. During that work I formulated a decent plan to
bring React Native support directly into figwheel-main.

I also spent some time on Devcards and removed its dependency on a
global `js/React` object. It now works much better when using NPM
dependencies for React.

##### Itemized

* Added a notion of `:final-output-to` so that figwheel can
  potentially know where the final output bundle is. This allows us
  to bootstrap an environment without having to create a host
  page.
* Added templating keywords that can be interpolated in the
  `:bundle-cmd`. So if you put `:output-to`, `:final-output-to`,
  `:final-output-path`, of `:final-output-filename` in a `:bundle-cmd`
  the will be replaced with the appropriate file strings.
* Fixed the default REPL host page so that it works with bundling
* Fixed [Extra-Mains](https://figwheel.org/docs/extra_mains.html) functionality so that it works with bundling
* Fixed [Auto-Testing](https://figwheel.org/docs/testing.html#auto-testing) so that it works with bundling
* Added bundle command logging so that the bundle command that is executed gets logged to the console
* Added bundle failure logging so that the output of a failed bundle command is logged to the console
* Added the new Figwheel logo and new website style to [figwheel.org](https://figwheel.org)
* Extensively updated the Figwheel [NPM documentation](https://figwheel.org/docs/npm.html)
* Made it so that `:log-level :debug` will always reveal the stacktrace of a logged Error
* added support for the new `--install-deps` **cljs.main** cli option
* added a validation error for when the `:output-to` is not in the
  `:output-dir` when using the `:bundle` target (bundling wont work otherwise)
* added the `:auto-bundle :webpack` figwheel option which will fill in
  all the defaults to get you up and running with NPM and webpack quickly
* added `:parcel` support to the `:auto-bundle` config option
* added `:bundle-freq` figwheel option which controls how often the
  bundler is called, the value can be either `:once`,`:always` or
  `:smart`. The `:smart` setting re-bundles when the `:output-to` or
  the `npm_deps.js` file for a build changes.
* debugged and reported couple big bugs with `:install-deps` in the
  ClojureScript compiler
* removed [Devcards](https://github.com/bhauman/devcards) dependence on `js/React` global

##### Future work

* launching a Webpack watch process
* React Native support
* Devcards support

During the course of this work my Figwheel to-do list has grown quite
large so stay tuned, there are more changes to come.




## Practicalli

**May 1-15**

Unfortunately illness caught up with me towards the end of this period, but otherwise I have been busy with the weeekly broadcasts and improvements to the Practicalli Clojure book, updating the install guides to Clojure CLI tools and repl driven development sections.

##### Practicalli Study Group
The weekly live broadcasts continue, finalizing the data science series and starting a new series on `clojure.spec`

##### Visualising data science
Concluded the [series of 7 live broadcasts on Visualising data science](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiDUXIR2z8Y8wvhpoPyl0t_D).

The series started with extraction of data from various sources and how to transform data into more relevant structures for the task required.  Initially using ascii-graph to visualize data in the REPL before moving on to create a professional looking dashboard usings Oz and Bulma CSS framework.

The dashboard project was developed around the continually changing coronavirus data available in the UK. The broadcasts has several examples of how to deal with things when the data format changes (which it did several times).

The later broadcasts includes tips and examples on how to refactor a project as it grows.

##### Introduction to clojure.spec.alpha
Started a new video series covering how to use spec in the REPL and with Clojure projects

Initially covering the foundation functions of the `clojure.spec.alpha` library and why its called alpha.  [practicalli/leveraging-spec](https://github.com/practicalli/leveraging-spec) project was created, covering Clojure predicates, spec/conform, spec/valid?, literal values (Clojure sets), the spec registry, fully qualified namespaces, map literal syntax, spec/def and spec/explain.

Started a spec for an online bank account that will be used in further episodes of the series.


##### Practicalli Clojure
Continued to migrate the book to Clojure CLI tools and deps.edn projects. The overall book content design is being refactored.

Update install guide to use Java 11 and added more editor options to the install guides, including NeoVim Conjure and Atom.io Chlorine.

Updated the [practicalli/clojure-deps-edn](https://github.com/practicalli/clojure-deps-edn) repository with also contains a collection of commonly used aliases.  This repository greatly simplifies the installation of Clojure CLI tooling.

Started creating transcripts for videos.  Creating transcripts first helps increase the quality of videos created and reduces the amount of effort required post processing videos.

Added basic introduction to spec which will be expanded as Practicalli study group video series continues


##### Practicalli Spacemacs
Supported the community with issues on Spacemacs gitter and #spacemacs channel of Clojurians Slack.

##### Practicalli Clojure WebApps
Refactor overall book content design for Practicalli Clojure WebApps - plan to extend the scope of the book.

**May 16-31**

Unfortunately I was ill for most of this period, so not as much achieved this time.  I am (and always intended to) extending the work passed the Clojurists Together sponsor period.

##### Summary
Continuing the weekly broadcasts on Clojure spec, a topic that will be added to the Practicalli Clojure book.

Added GitHub issues / PR shields for each book on the website to make tracking and contributing more convenient.

Pages on how to use Magit forge to list and create issues and pull requests as well as fork repositories on GitHib, all from within Spacemacs (Emacs).


##### Practicalli Website
Added [Shields](https://github.com/badges/shields) for each book with links to content ideas and pull requests on the respective repositories.  Aids in the tracking of book progress and providing another way for others to contribute.

Added YouTube playlists to the Practicalli website to make specific video content easier to find.

Updated the Creative commons license notice on the front pages of all books and GitHub README files, ensuring compliance with the [Software Freedom Conservancy](https://sfconservancy.org/).

Add favicon to each book website


##### Practicalli Study Group
Continuing the new series on `clojure.spec.alpha`, this time comparing function definition validation with `:pre` / `:post` conditions and spec `fdef`.

* [072 - Clojure Spec - Part 2 validation with :pre & :post and spec fdef](https://www.youtube.com/watch?v=fOv_z6E30l0&list=PLpr9V-R8ZxiBWGAuncfBRYhZtY5-Bp75s&index=3)


##### Practicalli Spacemacs
Several updates on using the Magit client

Guide on using Magit Forge to list and create issues and pull requests on GitHub, including forking remote repositories on GitHub.
https://practicalli.github.io/spacemacs/source-control/magit/forge.html

Updated and tested the Magit Forge configuration page
https://practicalli.github.io/spacemacs/source-control/forge-configuration.html

Add page on how to staging changes with Magit, including the scope of changes that can be selected, from multiple files, hunks or individual lines.

Video to how to update an existing pull request added to the contributing section, to complement the existing video showing how to contribute a pull request to Spacemacs (which can be used for any other project).

[Practicalli Spacemacs playlist](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiCHMl2_dn1Fovcd34Oz45su) updated with related Spacemacs videos from jr0cket channel.

Supported the community with issues on Spacemacs gitter and #spacemacs channel of Clojurians Slack.


##### Practicalli Clojure WebApps
Add the [high level plan to extend the book contents](http://practicalli.github.io/clojure-webapps/content-plan.html) to the Clojure WebApps book, along with project ideas to implement.

Updated the cover of the book to use the new Practicalli ClojureWebApps book banner

Improvements to various content sections based on feedback from the community.
* Updated webapps overview with additional project links
* Completely revised the introduction to sets and hash-maps
* Improved descriptions for the ring introduction, creating a project, creating a webserver, defining handlers, compojure defroutes and using the let function
* Clarified use of Heroku for deploying applications



## Re-frame

**May 1-15

Based on https://github.com/day8/re-frame/issues/589

Progress on the early, low-hanging-fruit step:

- [x] thoroughly review all the existing issues and PRs.
- [x] close any issues which need it
- [x] assemble the candidate list of issues we identify as "low hanging fruit"
- [x] some distracting issues with re-frame-template, shadow-c;lj and Lein-Shadow. See https://github.com/day8/re-frame-template/issues/128 and https://gitlab.com/nikperic/lein-shadow/-/merge_requests/3
- [x] some distractions with re-frame-10x (almost there)
- [x] #570 - Implement a way to add global interceptors
- [x] about to cut a new release which includes all the low hanging fruit
- [x] #544
- [x] #456
- [x] #216


Closed with explanations:
- [#553](https://github.com/day8/re-frame/issues/553)
- [#572](https://github.com/day8/re-frame/issues/572)
- [#555](https://github.com/day8/re-frame/issues/555)
- [#112](https://github.com/day8/re-frame/issues/112)
- [#481](https://github.com/day8/re-frame/issues/481)
- [#164](https://github.com/day8/re-frame/issues/164) 
- [#554](https://github.com/day8/re-frame/issues/554)
- [#480](https://github.com/day8/re-frame/issues/480)

Picked Fruit, so far:

Issues arising:
- [#588](https://github.com/day8/re-frame/issues/588)

Building a grown-up website [#582](https://github.com/day8/re-frame/issues/582): 
- [x] A review of options and likely paths has commenced. 

##### New website progress

- [x] learning new static site generator  (good decision so far, but there's always stuff to learn)
- [x] we've spent far too long getting Klipse going (to supply a page of live coding)
- [x] a GithubActions based build system for new website 
- [x] a LOT of rewriting, writing and thinking 
- [x] integration with API docs 
  
New docs include the following:
- Reusable Components - Addresses issue #264
- On Dynamics
- Subscriptions - Previously a neglected area. 

We're down to about 18 issues and 3 PRs. When we started, I think it was 30 issues and 9 PRs. 


**May 16-31

Based on https://github.com/day8/re-frame/issues/589

The task of addressing meatier issues is now slightly delayed.

New FAQ item added which closes [#538](https://github.com/day8/re-frame/issues/538)

[#478](https://github.com/day8/re-frame/issues/478) - cljs-oss/canary build support has been done, but not yet enabled. 

We are down to 13 open issues and 2 PRs.

In light of some negative feedback we have received of the initial release, we are reworking the new website. 
Goal is to present code earlier and require less initial reading. 



## Clojars

**[May 1-31](https://tcrawley.org/clojars-worklog/#may-2020)**

I continued working on deploy tokens, adding:

-   [An endpoint](https://github.com/clojars/clojars-web/commit/f6fc33168c76298bd084e8903ec9bf22a9ec226e) that GitHub will use to report when a deploy token is found in source code that will notify the user and disable the token
-   The option to [scope a deploy token to a group or artifact](https://github.com/clojars/clojars-web/commit/fc572b5cf1acdbaf17655b1b8a6f32bfcc89015e)

I also [implemented the bulk of two-factor authentication](https://github.com/clojars/clojars-web/pull/758). This wasn't released in May, but will be released by mid-June.

Part of the two-factor implementation is [an internal eventing system](https://github.com/clojars/clojars-web/pull/758/commits/62e5e2313bd47530b44de732c7a2844ffe1783ee) that will make it easier to add additional email notifications in the future.
