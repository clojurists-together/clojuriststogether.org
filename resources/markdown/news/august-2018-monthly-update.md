title: August 2018 Monthly Update
date: 2018-09-06T20:30:31+12:00
draft: true
type: post

## Clojurists Together news

August was the first month of funding for the quarter, so today we present the first round of updates on new projects, cljdoc and Shadow CLJS. 


Thanks to all of our members who support Clojurists Together. It's thanks to your generous support that we can do this.

## cljdoc updates

I switched the **storage layer**. SQLite is now used instead of lib-grimoire. More details in the respective architecture decision record: [ADR-0013](https://github.com/cljdoc/cljdoc/blob/master/doc/adr/0013-move-to-sqlite-for-storage.md)

This was a lot of work but it sets the project up for more interesting extensions besides API documentation and articles (think specs & examples).

Also I looked more into **integrating specs** but without changes to spec it is impossible to determine if a spec originates from the artefact that is being analysed or from one of it's dependencies. To fix this specs will need to support metadata ([CLJ-2194](https://dev.clojure.org/jira/browse/CLJ-2194)) but the timeline for this is unclear.

In the light of this I'm considering focusing on examples first.  More details to come. 

Some more minor things that happened:

- I printed [**stickers**](https://twitter.com/martinklepsch/status/1037802412680126464) which I'm planning to send to contributors. 
- Bozhidar likes favicons so I added one :) 
- Work is underway to integrate cljdoc into [Dash](https://kapeli.com/dash)
- Various fixes to the analyser code, mostly to eliminate slight differences between Clojure and ClojureScript as well as some dependency related improvements.
- I shipped a [**quick switcher (demo)**](https://giant.gfycat.com/GoodCluelessKusimanse.mp4) that allows you to switch between projects that you opened recently. I hope to expand this to quickly finding vars, namespaces and articles in the current project.

I'll also be at [ClojuTRE](https://clojutre.org/2018/) next week. Say hi if you're around! 👋

Oh and after ClojuTRE I'll be on a sailboat for two weeks so there will be less activity than usual. 

## Shadow CLJS updates

### August 1 - 15

I released shadow-cljs versions 2.4.31 up to 2.5.1.

Noteworthy changes include:

- Started reworking the shadow-cljs [standalone launcher](https://clojureverse.org/t/poll-reworking-the-launcher/2633) which is responsible for downloading dependencies and launching the actual tool. Intent here is to gain access to git-deps and dynamically loading dependencies without requiring a hard restart.
- Started publishing AOT compiled shadow-cljs versions which can significantly [improve start-up times](https://clojureverse.org/t/faster-startup-via-aot/2603). Used by default when using the standalone launcher.
- Fixed a few minor bugs and cleaned up some rogue log output.

### August 16-31

I released shadow-cljs versions 2.6.0 up to 2.6.6

#### UI Work

The UI is still a Work-in-Progress but it is now possible to control builds via the Web UI. Builds can be started/stopped and inspected (although the UI doesn't visualize this well yet).

#### Launcher Re-Work

I reverted the launcher changes I did recently due to some compatibility issues where `tools.deps` was handling dependencies differently than `pomegranate` and thus breaking builds that were working previously. Need to investigate this a bit more before making the switch again. There were also a few classloader issues that need to be sorted out.

#### Minor bugfixes

- Fixed an issue with [npm link](https://github.com/thheller/shadow-cljs/commit/d59b91d5dbc83a1b2b9147a3f55d3fd38077f099)
- Fixed an issue with CSS reloading in [Electron](https://github.com/thheller/shadow-cljs/commit/17309326ab440b86618294b31f468f6c40438800)
- Fixed an `ns` parsing [issue](https://github.com/thheller/shadow-cljs/issues/377) where an invalid `ns` form would pass without warnings

#### New features

- Added support for private `s3` maven repos via [s3-wagon-private](https://github.com/s3-wagon-private/s3-wagon-private)
