---
title: January 2019 Monthly Update
date: 02/11/2019
type: post
draft: false
---

## Clojurists Together News

Hello CT Community -

We are happy to announce the conclusion of Q4 2018 and the beginning of our Q1 2019 funding round where we are funding Aleph and Neanderthal. These are the final progress reports for projects Datascript and Kaocha. Special thanks to Nikita and Arne for all your hard work!

## Datascript update

### Extend query language

- Completed an implementation of or/or-join/not/not-join predicates in Datascript datalog and accompanying 0.17.1 release. The original ticket for this request was opened back in 2015, so it was about time that got fixed. It was also higly requested and probably one of the last things that was lacking in Datascript from Datomic API.
- Support `:as` in Pull API an attr-with-opts syntax ([#270](https://github.com/tonsky/datascript/issues/270), PR [#271](https://github.com/tonsky/datascript/pull/271), thx @Jumblemuddle)
- Support idents expansion (PR [#245](https://github.com/tonsky/datascript/pull/245), thx bamarco)
- Calling transaction fns through idents directly (PR [#185](https://github.com/tonsky/datascript/pull/185), thx @refset)

### Optimizing query execution speed

BTreeSet is a foundation of DataScript. Getting faster implementation will automatically improve everything: lookups, queries, transactions, serialization etc.

Transient BTreeSet will further improve transactions speed, taking advantage of the fact that database is not exposed to external observers during single transaction, meaning we can mutate some things in place and save a lot on CPU and GC time.

So far results are quite promising, with transact up to 6x times faster than before on JVM. I also developed a custom benchmark suite to compare different implementation strategies against each other.

All research is already in DataScript repo. What’s left before those gains are available for DataScript users is some massaging to get it to play nice with Clojure (implement required interfaces etc). I scheduled this work to be completed in the first week of February.

### Miscellaneous

- Extensive docstrings for most core functions, published at https://cljdoc.org/d/datascript/datascript
- Move AOTed builds to separate artifacts (related: #241, #279, #280)
- Implement `clojure.data/diff` on `datascript/DB` (#281)
- Drop Clojure 1.7 and 1.8 support
- Fix externs.js syntax (PR #216, thx @thheller)
- JS API correctly handles nested maps with `{":db/id"}` in transactions (#228, thx @serebrianyi)
- Made Clojure 1.10 the default build target.
- Adopted Kaocha test runner.
- Fixed four minor reported issues, [#285](https://github.com/tonsky/datascript/issues/285), [#287](https://github.com/tonsky/datascript/issues/287), [#283](https://github.com/tonsky/datascript/issues/283), [#289](https://github.com/tonsky/datascript/issues/289).
- Did a bit of research of different implementation strategies and developed faster JVM BTreeSet implementation with transient support.

## Kaocha Update

A lot has happened these last two weeks. There's also been an uptick in activity on Github and Slack, which I'm very happy about. It's going to take a village to deliver Kaocha's full potential. I've converted most of my personal notes into Github issues so people can see what's going, and jump in if they feel so inclined.

Watch mode has seen a big overhaul to address several issues with it, and
implement some features that were requested. It will report much more clearly on
syntax errors, and it now watches and reloads your `tests.edn`, so you can add
plugins and change configuration on the fly.

Pressing enter will cause a full reload and re-run, and you can now configure
glob patterns to be ignored, so e.g. temporary files from your editor don't
trigger a reload.

There are several new plugins included. One is a "version-filter", this lets you specify with metadata that tests require a certain minimum or maximum version of Clojure and/or Java. If the current version being used falls outside that range than the test is skipped. This is especially useful for open source projects that want to target a wide range of Clojure and Java versions, and need to account for differences in behavior across these platforms.

The notifier plugin integrates Kaocha with your system notifications, so a
bubble pops up whenever a test run passes or fails. Ryan McCuaig implemented the
first version as a hook function, I converted it to a plugin and made it
configurable. I've been using it non-stop since then, it works really great with
`--watch`.

A binding plugin got added, so you can configure dynamic vars directly from
`tests.edn`. Eventually I decided to roll this functionality into Kaocha itself,
to make sure it integrates on all levels. This can now also be used to configure
Kaocha's stacktrace filtering, which before was hard coded.

I also addressed a bunch of smaller usability issues. The randomization seed now only gets printed when a test run fails. The `:kaocha.plugin` namespace is now implied when configuring plugins, so built-in plugins can be enabled with less typing. A few other configuration keys have been given similar shorthands.

`--focus-meta` will now be ignored if no tests with the given metadata are found. This means you can leave a certain focus tag configured, and use it when you need to.

The Cucumber integration has been updated to be compatible with the latest cucumber-jvm.

Kaocha-junit-xml will now also render errors that happen at higher levels in the testable hierarchy. Certain things like syntax errors cause a test failure on the test suite level, rather than on the test var levels, which before wasn't visible in the junit.xml output.

To round off I'd like to mention a few outside contributions. Daniel Compton improved the error when a plugin can't be found, Magnar Sveen added a new `pre-report` hook that can be used by plugins, and then used it to implement [kaocha-noyoda](https://github.com/magnars/kaocha-noyoda). Michiel Borkent implement a [kaocha plugin for Speculative](https://github.com/borkdude/speculative-kaocha-plugin). Please keep them coming! I'd be more than happy to review and help out with pull requests! A number of issues have been labeled as "good first issue", these are fairly small and self contained issues that can provide a good introduction to the Kaocha codebase.
