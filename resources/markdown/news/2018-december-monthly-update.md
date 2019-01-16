title: December 2018 Monthly Update
date: 01/16/2019
type: post

## Clojurists Together News

Hello all!

Thanks again to those who participated in our [Q1 2019 survey](https://www.clojuriststogether.org/news/q1-2019-survey-results/), we greatly value your feedback.

Project [applications](https://www.clojuriststogether.org/open-source/) for our Q1 2019 round close on 15th Jan, midnight PST. The selections will be announced shortly afterwards. The Q1 2019 projects will start on February 1.

## Datascript update

The most noticeable thing that happened in December so far is an implementation of or/or-join/not/not-join predicates in Datascript datalog and accompanying 0.17.1 release. The original [ticket](https://github.com/tonsky/datascript/issues/50) for this request was opened back in 2015, so it was about time that got fixed. It was also higly requested and probably one of the last things that was lacking in Datascript from Datomic API. Well, now it’s there, feel free to use it. I also added/merged [support for idents expansion](https://github.com/tonsky/datascript/pull/245), and the [ability to call transaction fns through idents directly](https://github.com/tonsky/datascript/commit/34c122e0b5192bb58a797137fbb1f6bd1b236e6d).

I’m pacing my work unevenly, so nothing much happened in DataScript in second half of December. I started experimenting with persistence API and best datastructures for that, but that’s about it. Most of the action will be happening in January.

## Kaocha Update

Kaocha-cljs saw it's first official release last week! This is a major milestone
in Kaocha's mission to be a universally applicable test tool. So far it's tested
with Nodejs and using a browser REPL as the backend, although in theory any
environment that can provide a ClojureScript REPL can be used to execute the
actual tests.

The general coordinating and reporting of the test run still happens on the
Clojure side, as with other Kaocha test types, so that you can use your existing
Kaocha reporters and plugins and use them with ClojureScript.

kaocha-cloverage has been split out of the main kaocha artifact and into its own
project, which received two bugfix releases these past two weeks.

Kaocha itself also saw three new releases with a combination of bugfixes,
supporting code for the ClojureScript test type, and improvements. A small but
nice touch is that an equality assertion with only one value is now considered a
failure.

I find it's quite easy to get your parentheses wrong and write something like
this, which trivially passes.

```clojure
(is (= :this-doesnt-test-anything) (keyword "this-doesnt-test-anything"))
```

This is similar to the existing feature where a test without assertions is
considered a failure.

```clojure
(deftest keyword-test
  (= :this-doesnt-test-anything (keyword "this-doesnt-test-anything")))
```

Kaocha-cucumber saw two releases with a combination of bug fixes and ergonomic
improvements.

- The test identifiers are now based on the feature and scenario names, rather than on line numbers.
- In case of failures or when using the documentation reporter it's more clear which feature/scenario executed.
- Cucumber tags on features are converted to metadata so they can be used for filtering.
- Loaded step definitions are cached, so in case of watch or repl use you get a nice speedup.

Finally Kaocha-junit-xml saw two smaller releases, one to address a compatibility issue with Kaocha-cucumber.

The big thing for this period has been the first release of Boot integration for
Kaocha. With that addition the three major Clojure tools (Leiningen, Boot,
Clojure CLI) are all supported.

For the main part this works identically to Kaocha on Leiningen or Clojure CLI,
accepting the same command line arguments and `tests.edn` configuration, and
producing the same output.

One difference is that command line options that are added by plugins can't be
used directly, due to constrains posed by Boot's `deftask`. To work around this
kaocha-boot accepts a new `--options` flag which takes an EDN map of additional
options.

Kaocha now also includes a TAP (test anything protocol) reporter function. This
was requested early on, and should make Kaocha usable in a greater number of
CI/automation scenarios.

The junit.xml output has been improved (again), to provide clearer failure
messages.

Kaocha-cljs now captures the exception type and message on the JavaScript side,
so these can be accurately reported by Kaocha's (Clojure/JVM-based) reporter
functions, or plugins like kaocha-junit-xml.

A few smaller improvements have been added to Kaocha. The `--help` output no
longer assumes you are using Clojure CLI. The `--print-invocations` plugin's
output has been tidied up by not adding a redundant `--config-file tests.edn`,
an issue with incorrect file/line reporting on Java9+ has been addressed, and an
extra `--print-env` option has been added to the `kaocha.plugin.alpha/info`
plugin, which prints out the Clojure and Java/JVM version in use.
