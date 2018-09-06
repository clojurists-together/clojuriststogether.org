title: August 2018 Monthly Update
date: 2018-09-06T20:30:31+12:00
draft: true
type: post

## Clojurists Together news

This was the first month of funding for Shadow CLJS and cljdoc. ...


Thanks to all of our members who support Clojurists Together. It's thanks to your generous support that we can do this.

## cljdoc updates



## Shadow CLJS updates

### August 1 - 15

I released shadow-cljs versions 2.4.31 up to 2.5.1.

Noteworthy changes include:

- Started reworking the shadow-cljs [standalone launcher](https://clojureverse.org/t/poll-reworking-the-launcher/2633) which is responsible for downloading dependencies and launching the actual tool. Intent here is to gain access to git-deps and dynamically loading dependencies without requiring a hard restart.
- Started publishing AOT compiled shadow-cljs versions which can significantly [improve start-up times](https://clojureverse.org/t/faster-startup-via-aot/2603). Used by default when using the standalone launcher.
- Fixed a few minor bugs and cleaned up some rogue log output.

### August 16-31

I released shadow-cljs versions 2.6.0 up to 2.6.6

## UI Work

The UI is still a Work-in-Progress but it is now possible to control builds via the Web UI. Builds can be started/stopped and inspected (although the UI doesn't visualize this well yet).

## Launcher Re-Work

I reverted the launcher changes I did recently due to some compatibility issues where `tools.deps` was handling dependencies differently than `pomegranate` and thus breaking builds that were working previously. Need to investigate this a bit more before making the switch again. There were also a few classloader issues that need to be sorted out.

## Minor bugfixes

- Fixed an issue with [npm link](https://github.com/thheller/shadow-cljs/commit/d59b91d5dbc83a1b2b9147a3f55d3fd38077f099)
- Fixed an issue with CSS reloading in [Electron](https://github.com/thheller/shadow-cljs/commit/17309326ab440b86618294b31f468f6c40438800)
- Fixed an `ns` parsing [issue](https://github.com/thheller/shadow-cljs/issues/377) where an invalid `ns` form would pass without warnings

## New features

- Added support for private `s3` maven repos via [s3-wagon-private](https://github.com/s3-wagon-private/s3-wagon-private)
