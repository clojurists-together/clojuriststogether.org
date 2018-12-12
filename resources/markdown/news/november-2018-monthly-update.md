title: November 2018 Monthly Update
date: 12/12/2018
draft: true
type: post

## Clojurists Together News

We are happy to close the month of November with a successful start to the fourth quarter at Clojurists Together. Our Q4 projects, Datascript and Koacha, are well on their way. 

Special thanks to Erik Assum for the shoutout to Clojurists Together in his talk at [Clojure eXchange 2018](https://skillsmatter.com/skillscasts/12774-are-you-writing-java-in-clojure) recently.

![CT Nov 2018](resources/public/images/CT Nov 2018.jpg)

## Datascript update

I spent most of November working on docstrings and long-overdue PRs and issues. At a glance:

- 23 commits
- 3 released versions

#### 0.16.8

- Extenstive docstrings for most core functions, published at https://cljdoc.org/d/datascript/datascript

#### 0.16.9

- Move AOTed builds to separate artifacts  (related: #241, #279, #280)

#### 0.17.0

- Implement `clojure.data/diff` on `datascript/DB` (#281)
- Drop Clojure 1.7 and 1.8 support
- Fix externs.js syntax (PR #216, thx @thheller)
- Support `:as` in Pull API an attr-with-opts syntax (#270, PR #271, thx @Jumblemuddle)
- Support idents expansion (PR #245, thx bamarco)
- JS API correctly handles nested maps with `{":db/id"}` in transactions (#228, thx @serebrianyi)
- Calling transaction fns through idents directly (PR #185, thx @refset)

I’m looking at query OR/NOT predicates as my next target.

## Kaocha Update

### Nov. 1 - 15

It's been a busy two weeks! My initial focus has been on things
that improve Kaocha's own development process, as well as usability
improvements.

A first step was to implement the Ant JUnit XML format. This is a (somewhat)
standardized format to provide information about which tests ran, what their
results were, how long they took, etc. Many Continuous Integration tools can
parse this format to provide rich metrics.

Kaocha's own tests are run by CircleCI, which becomes a lot more useful when
provided with a junit.xml report.

This feature is available as a plugin: [lambdaisland/kaocha-junit-xml](https://github.com/lambdaisland/kaocha-junit-xml).

``` shell
bin/kaocha --plugin junit-xml --junit-xml-file report.xml
```

I also wanted to get some insight into Kaocha's own test coverage. Despite
working on test tooling I'm not always disciplined enough to immediately write
exhaustive tests, and I too suffer from the Clojure curse of developing things
at the REPL and not going the extra mile of converting those expressions into
tests.

Gamefication to the rescue! I integrated Kaocha with
[Cloverage](https://github.com/cloverage/cloverage), which will measure which
forms and lines are evaluated during a test run. The integration is again a
plugin, which allows you to configure Cloverage either with command line
options, or through `tests.edn`

``` clojure
#kaocha/v1
{:plugins [kaocha.plugin/cloverage]
 :cloverage/opts
 {:output "target/coverage"
  :codecov true
  :low-watermark 70}}
```

With this in place I managed to set up [codecov.io](http://codecov.io/), which
keeps track of how coverage evolves, as well as providing colorful badges for
the README.

The Cloverage plugin is currently still included in the main Kaocha repository
but will be split out soon. In general plugins that require extra dependencies
will be separate projects, so people who don't use them don't pay the cost.

Finally I implemented a [Cucumber](https://docs.cucumber.io/) test type. This is
the first fully supported test type after clojure.test. With Cucumber you write
your tests in lightly marked up natural language. It's a bit of an oddity, and
can invoke [strong reactions](https://twitter.com/andreiursan/status/1058181414603104256), but it's
quite popular in some circles.

Cucumber will be powering Kaocha's integration (or "feature") tests. Before
these were written as directories with a bunch of text files like `args`, `out`,
and `err`, which were used by a shell script to invoke Kaocha and diff the
output. With Cucumber the result [is a single
file](https://github.com/lambdaisland/kaocha/blob/eb984b796157b21d19dca05f9c585d434e747310/test/features/command_line/fail_fast.feature)
which acts as a form of executable documentation. The Ruby testing tool RSpec
uses this with great results, when you look [at their
docs](https://relishapp.com/rspec/rspec-core/v/3-8/docs/command-line/failure-exit-code-option-exit-status)
you hardly realize you're also looking at their integration tests.

Finally I made some changes to [improve startup
time](https://github.com/lambdaisland/kaocha/issues/14), and minor usability
improvements, like allowing plugins in the `kaocha.plugin` namespace to be
specified without a namespace prefix

```
# before
bin/kaocha --plugin kaocha.plugin/print-invocations
# after
bin/kaocha --plugin print-invocations
```
### Nov. 16 - 30

After the flying start of the first two weeks, these past two weeks have been
about consolidating the work done so far, fixing issues and polishing up the
handful of plugins and test formats that are already available.

The output of the JUnit.xml plugin has been tightened up. Originally it was only
tested on CircleCI, but a user reported the Azure Devops Pipeline didn't like
the output it was generating. This was in part due to `clojure.xml/emit` not
doing any escaping, so it was extremely easy to generate invalid XML.

I learned some new things about XML along the way and made sure the output is
always compliant with JUnit.xml's XSD schema.

Kaocha is also starting to see contributions, just today an issue was fixed in
Kaocha's Cloverage (code coverage) plugin. (Thanks Ryan!)

I improved the Cucumber support as part of converting Kaocha's integration tests
to Cucumber, so that the user gets better feedback when tests have syntax errors.

But the big thing these last two weeks has been the work on ClojureScript
support. Part of Kaocha's vision is to have a single tool that you can drop into
your project, so you can comfortably run both Clojure and ClojureScript tests
with little to no configuration, local or on CI, without having to learn a
second tool.

To accomplish this, kaocha-cljs spins up a ClojureScript runtime environment in
the background, and sends forms to it which load and run your tests. This
results in events from `cljs.test` (things like `:begin-test-var`, `:fail`,
`:pass`, ...) which are intercepted and sent back to Kaocha in realtime via a
websocket.

There are two asynchronous streams of events involved, one coming from the
ClojureScript (p)repl interface which provides printed output and return values,
and one with test events coming from the websocket. It's a fairly complex thing
to implement, and something I was hoping to get funding for, so I'm very happy
Clojurists Together is giving me this opportunity.

As of today I was able to run the first ClojureScript tests through Kaocha. It's
really a prototype at this point with plenty of loose ends to tie up, but it's a
milestone nevertheless. I'm more confident now that Kaocha will be able to
deliver a solid and reliable testing experience for ClojureScript.
