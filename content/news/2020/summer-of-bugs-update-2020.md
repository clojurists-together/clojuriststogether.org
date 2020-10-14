---
title: "Summer of Bugs Update 2020"
date: 2020-10-24T16:00:00+08:00
author: Alyssa Parado
summary: "The Summer of Bugs Updates includes: clj-kondo vim-iced, DataScript, Calva, reitit, Keycloak-Clojure, cljc.java-time"
---



## clj-kondo

[Clj-kondo](https://github.com/borkdude/clj-kondo/) is a Clojure linter that
uses static analysis. This means it only looks at source code, but does not
execute it. While the information available to produce good lint warnings is
more limited with static analysis, static analyzing is generally more
performant, and works independently from a runtime (JVM, nodeJS, browser,
etc.). Static analysis does not suffer from causing unwanted side effects when
executing code. It often yields good enough results. Where static analysis falls
short, clj-kondo offers
[configuration](https://github.com/borkdude/clj-kondo/blob/master/doc/config.md)
options where the user can help clj-kondo understand more of their code.

One area where static analysis of Clojure code becomes hard is macros. Macros
can introduce new syntactical constructs. Often macros are syntactically similar
to existing Clojure core macros. This is where you can use clj-kondo's
`:lint-as` configuration. In places where this isn't possible, for example
because the macro had irregular binding patterns, one could use
`:unresolved-symbol` + `:exclude` which would simply ignore unresolved symbol
errors in an entire s-expression.

I've been asking myself the following question for a while now: can clj-kondo
make more sense of custom macros with a little help from the user? Clj-kondo
could invent some DSL to express a transformation, but DSLs often cover just 80%
of what you want to achieve. To get 20% more power, you'd have to turn the DSL
into something like Clojure itself. So why not just use Clojure directly?

Clj-kondo is distributed in a couple of different ways. A widely used
distribution is the binary compiled with GraalVM. One limitation of a
GraalVM-compiled binary is that one cannot introduce new classes at runtime. And
this is what `clojure.core/eval` does, so that's off the table. Since August
2019 I've been working on the [Small Clojure
Interpreter](https://github.com/borkdude/sci). It's not a compiler, like
Clojure, but it allows you to interpret Clojure expressions within a GraalVM
binary. The interpreter is used in [babashka](http://babashka.org/) but it has
other uses as well and also works in JavaScript.

This interpreter can be used in clj-kondo to execute hooks that users can
provide to transform custom macro calls into constructs that clj-kondo can
understand. And this is what I've worked on.

Clj-kondo uses a vendored version of
[rewrite-clj](https://github.com/xsc/rewrite-clj) to analyze source code. My
first attempt at the hooks API was to transform the rewrite-clj nodes into
Clojure s-expressions. Then the user's hook function would transform these
s-expressions in a similar fashion as the macro would, returning new
s-expressions. Lastly clj-kondo would then translate these s-expressions back
into rewrite-clj nodes and continue analysis. Ostensibly this worked great for
several test cases, but ultimately it wasn't good enough. The main problem is
that numbers, strings and keywords cannot carry metadata. Metadata on sexprs was
used to keep track of the original locations. When (some of) these locations are
lost, clj-kondo cannot accurately position lint warnings anymore. And this is
unacceptable in my opinion. You can read more about this problem on ClojureVerse
[here](https://clojureverse.org/t/feedback-wanted-on-new-clj-kondo-macroexpansion-feature/6043)
and in the issue on Github
[here](https://github.com/borkdude/clj-kondo/issues/811).

After more experimentation I decided that the transformation should happen
direcly on rewrite-clj nodes in order to preserve location information. This led
to the current implementation of the `:analyze-call` hook, documented
[here](https://github.com/borkdude/clj-kondo/blob/master/doc/config.md#hooks).
Additionally, some library specific example config + hook code is provided
[here](https://github.com/borkdude/clj-kondo/tree/master/libraries), showing how
to make clj-kondo understand [Rum](https://github.com/tonsky/rum)'s `defc` macro
and [slingshot](https://github.com/scgilardi/slingshot)'s `try+` macro.

I consider this new feature a powerful feature but not an easy to use one. It
does provide a higher degree of linting quality while still enjoying the
benefits of static analysis. Luckily we only have to figure out the right code
for each library once. I urge library authors and users to contribute their
configurations to the clj-kondo
[repository](https://github.com/borkdude/clj-kondo/tree/master/libraries) so we
can all benefit.

[Clojurist Together](https://www.clojuriststogether.org/) has sponsored this
work as part of their [Summer of
Bugs](https://www.clojuriststogether.org/news/announcing-summer-of-bugs/)
program. Thanks to the people who have made this possible: the Clojurists
Together staff and of course the people who donate.

Hope you enjoy. Happy linting!

Michiel Borkent (a.k.a. [@borkdude](https://twitter.com/borkdude))




## vim-iced

#### What we were trying to do

I tried to add support test integration on plain nREPL server including [Babashka nREPL](https://github.com/borkdude/babashka/blob/master/doc/repl.md#nrepl).

Concretely tried to add following functionalities.
* Test single var under cursor.
  * Show the test result on vim.
* Test vars in a namespace.
* Test all vars.


#### How we solved the issue

I wrote a simple custom [clojure.test/report](https://clojure.github.io/clojure/clojure.test-api.html#clojure.test/report) function to be able to gather test reports.

Then I fixed vim-iced to run tests with this `report` function and parse reports, so users can run tests on plain nREPL server, and display summarized results on vim.




## DataScript

The main goal was to bring latest Datomic API improvements to DataScript: 

- Support composite tuples [#323](https://github.com/tonsky/datascript/issues/323)
- Support return maps (:keys/:syms/:strs) in query [#322](https://github.com/tonsky/datascript/issues/322) [#345](https://github.com/tonsky/datascript/issues/345)

Another big quality-of-life improvement was allowing any hashable values in `:db.cardinality/many` and `:db/indexed` attributes. Previously those attribute values had to be `Comparable`/`IComparable`.

- Support any hashable values in cardinality-many and indexed attributes [#274](https://github.com/tonsky/datascript/issues/274) [#356](https://github.com/tonsky/datascript/issues/356)

Validation was improved significantly as well:

- Throw when init-db is used with anything but datoms [#276](https://github.com/tonsky/datascript/issues/276)
- Throw on using unindexed attribute in :avet index access [#344](https://github.com/tonsky/datascript/issues/344)
- Throw on referencing undefined rule [#319](https://github.com/tonsky/datascript/issues/319)
- Validate tempids only used as values in transaction [#304](https://github.com/tonsky/datascript/issues/304)
- Throw if transacted entity id is out of range [#292](https://github.com/tonsky/datascript/issues/292)
- Better validate rules syntax [#300](https://github.com/tonsky/datascript/issues/300)
- Throw when pred/fn called on unbound variable [#309](https://github.com/tonsky/datascript/issues/309)
- Validate inputs count in `:in` and `d/q` [#297](https://github.com/tonsky/datascript/issues/297)

And few minor fixes:

- Fix `[:db/retract e a false]` working as `[:db/retract e a]`
- Add `keyword` to the built-ins [#231](https://github.com/tonsky/datascript/issues/231)

The result is published as [DataScript 1.0.0](https://github.com/tonsky/datascript/releases/tag/1.0.0)



## Calva

Moving towards a file based REPL window replacement I decided to start with changing how Calva prints in-editor evaluation results.

Currently the first line of the evaluation results are displayed inline in the file being edited, while the full results are printed to a VS Code output channel (titled **Calva says**). This channel is plain text, readonly and provides very little help in navigating/editing/copying the results.

The *Summer of Bugs* version of Calva now uses a ”regular” file window for printing the results. I've put _regular_ in quotes there, because there is some supporting code around it making it more convenient as a REPL results window.

The rationale for using this regular window instead of the output channel or the REPL window, is that there are tons of work put into making Calva clojure windows interactive and Clojure friendly. (There is almost as much work put into the REPL window, but that is another story). So some of the things the new output window sports are:

* Clojure syntax highlightning
* Rainbow brackets and indent guides
* [Paredit](https://calva.io/paredit/)
* REPL connection, making it an alternative to the REPL window for explorations (which was the goal of the Summer of Bugs effort). It is treated as a CLJC file, so the user can toggle between the Clojure and ClojureScript REPL at will.
* A Clojure debugger (made possible by previous CT funding)
* VIM extension support – a huge win over the current REPL window.

Demo of debugger active in the new output window:
![image](https://user-images.githubusercontent.com/30010/88337560-37209900-cd37-11ea-8683-2c2a99fcca98.png)


Stacktraces, which were not worth printing to the previous output channel, are printed to this output window (when available) with clickable links to the file locations in the stack frames. (It is printed as an Clojure/EDN vector so the user can wield the REPL on it.)

Demo of output window stacktrace with clickable file locations and peek hovers:
![output window stack trace](https://user-images.githubusercontent.com/30010/88339069-af885980-cd39-11ea-85b3-467cad9396e0.png)


#### Lessons Learned

While the rationale behind making use of all the previous work on Calva to make this output/repl window extra powerful was sound, VS Code was not made with LISPs in mind and it sometimes feels like a battle making it support Clojure coding. Most of the things I thought would be easy turned out quite tricky, and I had to yield to the VS Code API and adapt several of my intended features a bit more than I liked. But we in the Calva Team are fighters, and, all things considered, this new output window unfolded to be quite a lot sweeter than I had envisioned.

![theseus vs code api](https://user-images.githubusercontent.com/30010/88337775-8c5caa80-cd37-11ea-872f-39676382097a.png)

#### Soon to be Released

I expect to release this version of Calva within the following week. It will not yet replace the current REPL window, but the ground work is done, and this mini project has certainly proven that this regular-file approach is viable.



## reitit

Tommi Jalkanen 



## Keycloak-clojure

Grodziski 



## cljc.java-time

I am planning to drop the npm/foreign-lib dependencies (npm library ‘js-joda’ & related) from https://github.com/henryw374/cljc.java-time 8.

Doing this should mean that other Clojure(Script) libraries which make any use of dates and times could now depend on cljc.java-time library because:

There will be no transitive dependency headaches for users (ie excluding foreign-libs if you have a :bundle cljs build etc)
End users will benefit from dead code elimination: If they are not using part of a library that uses cljc.java-time, then nothing of cljc.java-time library will be included in an advanced optimization cljs build.
IOW, cljc libraries that need to use dates (even if dates are not the main focus of that library) can depend on cljc.java-time and get a banana without a gorilla holding on to it.

… and being cross-platform and using java.time on the jvm, I hope cljc.java-time could become the de-facto platform time library for Clojure and Clojurescript.

Of course https://github.com/juxt/tick and any other end-user-focused date-time libraries that depend on cljc.java-time will benefit

How will this work?

I am creating a custom build of the js-joda source that results in a single js file that does a goog.provide() - ie something cljs compiler treats as native.

In order for the jsjoda addon libs (timezone & locales) to work they will also need custom builds.

I am assuming here that no one is using any js libraries that are using jsjoda - but if you are please shout. If people are then I could provide an option to use cljc.java-time with npm dependency instead.

The downside for me is that each release of jsjoda that people want will need a new release of this custom build. However, since jsjoda just replicates java.time and that is stable, I don’t expect that many releases.

There is a new platform library for JS in the works, ‘proposal-temporal’, but I decided it is best to focus on the custom-jsjoda approach for now since:

it may be years before proposal temporal is fully ready
jsjoda lib is still openly considering deferring to proposal-temporal method/objects when it is ready - ie jsjoda does the mapping to java.time so cljc.java-time doesn’t have to
I am not sure how long until the new version of cljc.java-time will be ready, but weeks rather than months hopefully.

FYI I have gratefully received some funding from Clojurists Together for this work.
