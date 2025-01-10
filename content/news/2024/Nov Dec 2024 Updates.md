---
title: "Nov. and Dec. 2024 Project Updates"
date: 2025-01-09T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Daniel Slutsky, Peter Taoussanis"  



---
 

Happy New Year all! This is the last set up updates from our developers who received 2024 annual funding - and the final report from Daniel Slutsky who was funded in Q3 2024. Thanks to everyone for their amazing work throughout the year!


## Long-Term Project Updates  

[Bozhidar Batsov:](#bozhidar-batsov) CIDER       
[Michiel Borkent:](#michiel-borkent) squint, babashka, neil, cherry, clj-kondo, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs, shadow-grove    
[Kira Howe:](#kira-howe) Scicloj Libraries. tcutils, Clojure Data Cookbook, and more   
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Datascript, AlleKinos, Clj-reload, and more  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli, jsonista, and more    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, Telemere, and more  <br>

## Q3 2024 Project Update
[Daniel Slutsky:SciCloj](#daniel-slutsky-scicloj)   




## Bozhidar Batsov   
Report 6. Published January 1, 2025

Happy New Year, everyone!  
The last couple of months were a bit slower than usual for CIDER and friends, but still we managed to make some good progress. Below are a list of highlights:  

- CIDER 1.16.1 was released in early December with a [bunch of bug-fixes](https://github.com/clojure-emacs/cider/blob/master/CHANGELOG.md#1161-2024-12-03)  
- nREPL 1.3.1 is also out with a [couple of small fixes](https://github.com/nrepl/nrepl/blob/master/CHANGELOG.md#131-2025-01-01).  
- Work has been done in Orchard to simplify the parsing of Java source files
	- We've dropped support for Java source parsing on Java 8 to make the codebase more maintainable
	- Now you no longer need to have the JDK sources on the classpath (Orchard will look for them in several common places)  
	- This (and some other improvements) with be integrated with CIDER for its next release (1.17), which I'm hoping to launch in the next month or so.  
- [Piggieback 0.6](https://github.com/nrepl/piggieback/blob/master/CHANGES.md#060-2021-12-31) is out with improved compatibility for nREPL 1.3.  
	- That's the first release in over 3 years, btw!  
- We've launched a [State of CIDER 2024](https://metaredux.com/posts/2024/12/23/state-of-cider-2024.html) to gather input from the users about they way they are currently using CIDER. The data from this survey will help us shape up the roadmap for 2025. The survey will remain open until the end of January.  

That's all from me for now. Thanks again for supporting CIDER's development in 2024!  <br>

---

## Michiel Borkent  
Report 6. Published December 30, 2024  

**Updates**
In this post I'll give updates about open source I worked on during November and December 2024. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors  
I'd like to thank all the sponsors and contributors that make this work possible. Without _you_, the below projects would not be as mature or wouldn't exist or be maintained at all.  

Current top tier sponsors:  
* [Clojurists Together](https://clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Nubank](https://nubank.com.br/)

### Sponsor info  
If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)

If you're used to sponsoring through some other means which aren't listed above, please get in touch. On to the projects that I've been working on!  


### Updates
Clojurists Together announced that I'm among the 5 developers who were voted to receive the Long Term Funding in 2025. You can see the announcement [here](https://www.clojuriststogether.org/news/clojurists-together-2025-long-term-funding-announcement/). Thanks so much!  
Here are updates about the projects/libraries I've worked on in the last two months.  

* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * [#1771](https://github.com/babashka/babashka/issues/1771): `*e*` in REPL should contain exception thrown by user, not a wrapped one
    * [#1777](https://github.com/babashka/babashka/issues/1777) Add `java.nio.file.attribute.UserDefinedFileAttributeView`
    * [#1776](https://github.com/babashka/babashka/issues/1776) `Add java.nio.file.attribute.PosixFileAttributes`
    * [#1761](https://github.com/babashka/babashka/issues/1761) Support calling `clojure.lang.RT/iter`
    * [#1760](https://github.com/babashka/babashka/issues/1760) For compatibility with [Fireworks v0.10.3](https://github.com/paintparty/fireworks), added the following to `:instance-checks` entry in `babashka.impl.classes/classes`([@paintparty](https://github.com/paintparty))
        * `clojure.lang.PersistentArrayMap$TransientArrayMap`
        * `clojure.lang.PersistentHashMap$TransientHashMap`
        * `clojure.lang.PersistentVector$TransientVector`
        * `java.lang.NoSuchFieldException`
        * `java.util.AbstractMap`
        * `java.util.AbstractSet`
        * `java.util.AbstractList`
    * [#1760](https://github.com/babashka/babashka/issues/1760) For compatibility with [Fireworks v0.10.3](https://github.com/paintparty/fireworks), added `volatile?` entry to `babashka.impl.clojure.core/core-extras`([@paintparty](https://github.com/paintparty))
    * Bump `babashka.cli` to `0.8.61`
    * Bump `clj-yaml` to `1.0.29`
    * [#1768](https://github.com/babashka/babashka/issues/1768): Add `taoensso.timbre` `color-str` function
    * Add classes:
        * `javax.crypto.KeyAgreement`
        * `java.security.KeyPairGenerator`
        * `java.security.KeyPair`
        * `java.security.spec.ECGenParameterSpec`
        * `java.security.spec.PKCS8EncodedKeySpec`
        * `java.security.spec.X509EncodedKeySpec`
        * `java.security.Signature`
    * Add `java.util.concurrent.CompletionStage`
    * Bump `core.async` to `1.7.701`
    * Bump `org.babashka/cli` to `0.8.162`
    * Include [jsoup](https://jsoup.org/) for HTML parsing. This makes bb compatible with the [hickory](https://github.com/clj-commons/hickory) library (and possibly other libraries?).
    * [#1752](https://github.com/babashka/babashka/issues/1752): include `java.lang.SecurityException` for `java.net.http.HttpClient` support ([@grzm](https://github.com/grzm))
    * [#1748](https://github.com/babashka/babashka/issues/1748): add `clojure.core/ensure`
    * Upgrade `taoensso/timbre`to `v6.6.0`
    * Upgrade `babashka.http-client` to `v0.4.22`
    * Add `:git/sha` from build to `bb describe` output ([@lispyclouds](https://github.com/lispyclouds))
    * Fix NPE with determining if executing from self-contained executable
* [squint](https://github.com/squint-cljs/squint): CLJS *syntax* to JS compiler
    * Fix [#255](https://github.com/squint-cljs/squint/issues/255): fn literal with rest args
    * [#596](https://github.com/squint-cljs/squint/issues/596): fix unary division to produce reciprocal
    * [#592](https://github.com/squint-cljs/squint/issues/592): fix `clj->js` to not process custom classes
    * [#585](https://github.com/squint-cljs/squint/issues/585): fix `clj->js` to realize lazy seqs into arrays
    * [#586](https://github.com/squint-cljs/squint/issues/586): support extending protocol to `nil`
    * [#581](https://github.com/squint-cljs/squint/issues/581): support docstring in `defprotocol`
    * [#582](https://github.com/squint-cljs/squint/issues/582): add `extend-protocol`
    * Add `delay`
    * Fix [#575](https://github.com/squint-cljs/squint/issues/575): `map?` should not return `true` for array
    * Fix [#577](https://github.com/squint-cljs/squint/issues/577): support `$default` + `:refer`
    * Fix [#572](https://github.com/squint-cljs/squint/issues/572): prevent vite page reload
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
    * Fix [#109](https://github.com/babashka/cli/issues/109): allow options to start with a number
* [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
    * [#99](https://github.com/babashka/babashka/issues/99): make `js/import` work
    * [#55](https://github.com/babashka/babashka/issues/55): create gh-pages dir before using.
    * [#89](https://github.com/babashka/babashka/issues/89): allow `evaluate_script_tags` to specify individual scripts.
    * [#87](https://github.com/babashka/scittle/issues/87): prod build on fresh checkout fails
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy. \

    * Unreleased
    * [#2272](https://github.com/clj-kondo/clj-kondo/issues/2451): Lint for nil return from if-like forms
    * Add `printf` to vars linted by `analyze-format`. ([@tomdl89](https://github.com/tomdl89))
    * [#2272](https://github.com/clj-kondo/clj-kondo/issues/2272): Report var usage in `if-let` etc condition as always truthy
    * [#2272](https://github.com/clj-kondo/clj-kondo/issues/2272): Report var usage in `if-not` condition as always truthy
    * [#2433](https://github.com/clj-kondo/clj-kondo/issues/2433): false positive redundant ignore with hook
    * Document `:cljc` config option. ([@NoahTheDuke](https://github.com/NoahTheDuke))
    * [#2439](https://github.com/clj-kondo/clj-kondo/issues/2439): uneval may apply to nnext form if reader conditional doesn't yield a form ([@NoahTheDuke](https://github.com/NoahTheDuke))
    * [#2431](https://github.com/clj-kondo/clj-kondo/issues/2431): only apply redundant-nested-call linter for nested exprs
    * Relax `:redundant-nested-call` for `comp`, `concat`, `every-pred` and `some-fn` since it may affect performance
    * [#2446](https://github.com/clj-kondo/clj-kondo/issues/2446): false positive `:redundant-ignore`
    * [#2448](https://github.com/clj-kondo/clj-kondo/issues/2448): redundant nested call in hook gen'ed code
    * [#2424](https://github.com/clj-kondo/clj-kondo/issues/2424): fix combination of :config-in-ns and :discouraged-namespace
    * 2024.11.14
    * [#2212](https://github.com/clj-kondo/clj-kondo/issues/2212): NEW linter: `:redundant-nested-call` ([@tomdl89](https://github.com/tomdl89)), set to level `:info` by default
    * Bump `:redundant-ignore`, `:redundant-str-call` linters to level `:info`
    * [#1784](https://github.com/clj-kondo/clj-kondo/issues/1784): detect `:redundant-do` in `catch`
    * [#2410](https://github.com/clj-kondo/clj-kondo/issues/2410): add `--report-level` flag
    * [#2416](https://github.com/clj-kondo/clj-kondo/issues/2416): detect empty `require` and `:require` forms ([@NoahTheDuke](https://github.com/NoahTheDuke))
    * [#1786](https://github.com/clj-kondo/clj-kondo/issues/1786): Support `gen-interface` (by suppressing unresolved symbols)
    * [#2407](https://github.com/clj-kondo/clj-kondo/issues/2407): support ignore hint on called symbol
    * [#2420](https://github.com/clj-kondo/clj-kondo/issues/2420): Detect uneven number of clauses in `cond->` and `cond->>` ([@tomdl89](https://github.com/tomdl89))
    * [#2415](https://github.com/clj-kondo/clj-kondo/issues/2415): false positive type checking issue with `str/replace` and `^String` annotation
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * 1.3.196 (2024-11-25)
    * Add `locking` macro for compatibility with CLJS
    * 1.3.195 (2024-11-07)
    * [#343](https://github.com/babashka/nbb/issues/343): support `:reload` for reloading CLJS namespaces and JS code
* [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
    * Fix parsing of `b//` symbol
* [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3
    * [#19](https://github.com/babashka/pod-babashka-go-sqlite3/issues/19): Restore mac intel support (this time for real)
* [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
    * Fix [#30](https://github.com/babashka/tools-deps-native/issues/30): pod won't run on newer versions of macOS
* [http-client](https://github.com/babashka/http-client): babashka's http-client \

    * [#73](https://github.com/babashka/http-client/issues/71): Allow implicit ports when specifying the URL as a map ([@lvh](https://github.com/lvh))
    * [#71](https://github.com/babashka/http-client/issues/71): Link back to sources in release artifact([@lread](https://github.com/lread))


### Other projects  
There are many other projects I’m involved with but that had little to no activity in the past month. Check out the **Other Projects** section (more details) of [my blog here](https://blog.michielborkent.nl/oss-updates-nov-dec-2024.html) to see a full list. <br>  


---

## Toby Crawley   
Report 6. Published December 31, 2024  

[CHANGELOG](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org#2024-december) | [`clojars-web` commits](https://github.com/clojars/clojars-web/compare/7da06ec54d579d01578b2ee62bd20deeb1d87fe6...739b3a062370a75a8f6217a3d73ce5e42225c145)  

-   Added a new API to provide a [release feed](https://github.com/clojars/clojars-web/pull/896)  

#### November 2024  

[CHANGELOG](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org#2024-november) | [`clojars-web` commits](https://github.com/clojars/clojars-web/compare/7414339136d6ac3455525fd949ee8372ff15ea03...7da06ec54d579d01578b2ee62bd20deeb1d87fe6) | [`infrastructure` commits](https://github.com/clojars/infrastructure/compare/e801cb297560b911c9b1ad01cd46c805d16eeed3...dea9364b1bf31d0018909d2a34c68d628118d65a)  

-   Worked with a new contributor ([Osei Poku](https://github.com/opoku)) to [implement a sitemap](https://github.com/clojars/clojars-web/pull/893)  
-   Constrained heap usages for cron tasks to prevent competition with the webapp  
    (this was causing the server instance to become unavailable on occasion)  <br>  

---

## Thomas Heller  
Report 6.  Published January 6, 2025  

Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (e.g. Clojurians Slack).  

Current shadow-cljs version: 2.28.20 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  <br>  

---

## Kira Howe  
Report 6. Published December 31, 2024.  

This is a summary of the open source work I spent my time on throughout November and December 2024. This was the last period of my ongoing funding from Clojurists together. It's been such a magical year in many ways and I'm so grateful to have had the opportunity to spend so much time focusing on open source this year. It was a fantastic experience and I hope to be able to take another professional hiatus at some point in the future to do it again.  

I've really enjoyed writing these summaries, too, and I find knowing they're coming helps motivate me to stay more organized and keep better track of things, so I'll probably continue to publish them even though I'll be spending less time on side projects as my focus shifts to other priorities this year.  

### Sponsors  

I always start these posts with a sincere thank you to the generous ongoing support of my sponsors that make this work possible. I can't say how much I appreciate all of the support the community has given to my work and would like to give a special thanks to Clojurists Together and Nubank for providing incredibly generous grants that allowed me reduce my client work significantly and afford to spend more time on projects for the Clojure ecosystem for nearly a year.  

If you find my work valuable, please share it with others and consider supporting it financially. There are details about how to do that on my [GitHub sponsors page](https://github.com/sponsors/kirahowe). On to the updates!  

### BOBKonf 2025  

One exciting update is that [a workshop I proposed got accepted to BOBKonf](https://bobkonf.de/2025/howe.html), which will be in Berlin next March. It'll be similar to the types of talks and workshops I've been doing over the last couple of years at e.g. the Conj and London Clojurians, of course updated to show off the latest and greatest developments in the Clojure-for-data ecosystem. I spent some time in December beginning work on the content and now I'm in full conference-driven-development mode, figuring out what's realistic to finish in time to demo at the event and what we should consider stable "enough" for now and just include. This preliminary work also sparked a couple of minor conversations, one about [quarto theming of Clay notebooks](https://github.com/scicloj/clay/issues/192) and another about [parsing dates from Excel workbooks](https://clojurians.zulipchat.com/#narrow/channel/236259-tech.2Eml.2Edataset.2Edev/topic/excel.20dates/near/486781881).  

Anyway there are still a couple of months to work on it, which on one hand feels like a long time but on the other hand is also no time at all. Before I know it I'll be landing in Berlin ready to share the wonders of Clojure with a new eager audience.  

### Clojure Data Cookbook  

This has been a very long-running, very ongoing project of mine. The high level goal was always (and still is) to create resources that would allow people to figure out how to be productive with Clojure's data stack. In reality what this particular project morphed into was a process for discovering the gaps in the ecosystem and guiding development of new tools, uncovering missing features to implement or new libraries to write every time I'd start work on a new chapter.  

We've come a long way over the past couple of years and there's still work to do but the ecosystem is reasonably mature now. The [Noj book](https://scicloj.github.io/noj/) has taken on covering a lot of the topics I wanted to document thanks to Daniel Slutsky's incredible efforts at coordinating the community to produce this amazing content. The list of draft articles demonstrates many of the areas where work is still very ongoing in the development of the various libraries. Tutorials are mostly not left unfinished because the authors haven't gotten around to finishing them, but more because the question of what exactly to write about is yet to be answered.  

On the Clojure Data Cookbook itself, the current work in progress is [available here](https://scicloj.github.io/clojure-data-cookbook/) and includes only sections that document stable and established functionality. The goal of making Clojure's data stack accessible and easy to work with is still at the top of my priority list but conversations are underway about what the best way to do that is in the context of the current ecosystem.  

### ggclj  

Another project I've been poking away at the last couple of months is my implementation of the [grammar of graphics in Clojure](https://github.com/kirahowe/ggclj). Most of my effort here is spent learning more and more about the core concepts of data visualization and how graphics can be represented using a grammar, and then how that grammar could be implemented in an existing programming language. This along with exploring prior implementations in other languages. I have a very rudimentary build process working for transforming an arbitrary dataset into a standardized, graph-able dataset, but nothing yet on the actual graphic rendering. It's very interesting and satisfying but I'm not sure how useful. But, in the spirit of heeding Rich's advice from the last Conj about doing projects for fun, I haven't let it go completely. It's still something I'd love to get working someday.  

### Reflecting on a year of open source  

As I mentioned above I really enjoyed having the time this year to work on so many interesting projects for the Clojure community. It's so rewarding to see how far we've come. Even though it feels like there is still so much to do, I think it's important to reflect on the progress we have made and think about how the problems we encountered along the way shaped the path we took.  

When I first started working on the Clojure Data Cookbook, there wasn't even a way to publish a book made out of Clojure files. Clerk was brand new and Clay barely existed. Now we can render a pile of Clojure files as a Quarto book! And the need for better documentation has spurred tons of amazing development in this space. The literate programming story in Clojure is better than in any other language.  

We've also made huge strides in connecting the various libraries of the ecosystem together. At the beginning of the year there were many amazing but disconnected libraries. I've been really inspired by the ideas behind the tidyverse and have been trying to communicate the idea of sharing common idioms and data structures. An ecosystem is starting to emerge in Noj that offers a coherent, standardized, shared paradigm for using all of the amazing tools of the Clojure data ecosystem together. The default stack has been chosen, and serious efforts are now underway toward making these libraries feature complete and interoperable. And I plan to continue working on tutorials, guides, and workshops as much as I can to help promote it all.   

I'm grateful for all the changes in my life that have taken my time away from working on side projects as much as I used to, like marriage and a great new job, but in many ways I miss doing more of this work and I sincerely hope I find myself in a position to veer off of this "standard" life track in the future to take a period to focus on this kind of stuff full time again. Even better would be figuring out a way to make it sustainable so that I could continue to do it full time. If you have any idea  how to make that work, let me know :)  

It turns out I am not the kind of market-oriented, entrepreneurially-minded person who can turn coding skills into a business that generates steady income for my family. I like contracting and the slow-and-steady community building type of work that constitutes a career in open source, but unfortunately continuing down this road is just not in the cards for me this year. Though I'll never be able to completely resist working on it whenever I can :) Thanks so much for reading this far, and hope to see you around the Clojureverse!  <br>  

---

## Nikita Prokopov  
Report 6. Published December 30, 2024  

Hi, this is Niki Tonsky and past two month I was working on Fast EDN parser.  

### [Fast EDN](https://github.com/tonsky/fast-edn/), a faster EDN parser for Clojure.  

This project started with a question: why does EDN parse so much slower than JSON? Is there a good reason for that? Turned out, no! That’s how Fast EDN was born.  
- ~6× speed improvement over `clojure.edn`.  
- Outperforms transit-clj.  
- More consistent error reporting.  
- Much cleaner code.  

### [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed), Clojure support for Sublime Text 4:  
- New `Select Topmost Form` command  
- New `Align Cursors` command  
- Fixed evaluation of `()` (closes [131](https://github.com/tonsky/Clojure-Sublimed/issues/131))  
- Add `|` to the allowed symbols chars [132](https://github.com/tonsky/Clojure-Sublimed/issues/132)  
- Clarified some symbol/keyword edge cases in syntax  
- Color scheme adjustments  

### [Sublime Executor](https://github.com/tonsky/Sublime-Executor), executable runner for Sublime Text:  
- Put 0.2 sec limit on `find_executables`. Large project shouldn’t be a problem anymore  

**Misc:**
- [New icons](https://github.com/clojure-goes-fast/clj-async-profiler/pull/42) for [clj-async-profiler](https://github.com/clojure-goes-fast/clj-async-profiler/)  
- [Throw on fail](https://github.com/clj-commons/virgil/pull/39) for [virgil](https://github.com/clj-commons/virgil)  

Both of these libraries were crucial in developing Fast EDN, so I contributed back.  

And the most important news of all. [tonsky.me](https://tonsky.me/), my personal website:  
- Now has a winter theme!  

Overall, what a year! Thanks Clojurists Together and my patrons for making it possible to work almost entire year on open-source full time. Best year of my life (so far!)  

Happy New Year everyone!  <br>  

---

## Tommi Reiman  
Report 6. Published  January 1, 2025  

Reflected on my year of working with Open Source. Having authored tens of Clojure Open Source Libraries makes you look things from another angle. Instead of just adding more (fun) features, I dedicated time to go through all old issues and open PRs from 2024 for the [active](https://github.com/topics/metosin-active) and [stable](https://github.com/topics/metosin-stable) libraries where there is no clear lead developer or it's me.  

Closed a lot of support tickets, answered questions and reviewed and merged PRs. Thanks to all contributors and sorry to not doing this earlier!  

Some releases:  

### Malli 0.17.0  
* Don't output `:definitions nil` in swagger. [#1134](https://github.com/metosin/malli/issues/1134)  
* **BREAKING**: `:gen/fmap` property requires its schema to create a generator.  
  * previous behavior defaulted to a `nil`-returning generator, even if the schema doesn't accept `nil`  
  * use `:gen/return nil` property to restore this behavior  
* Support decoding map keys into keywords for `[:map` schemas in `json-transformer` [#1135](https://github.com/metosin/malli/issues/1135)  
* `:not` humanizer [#1138](https://github.com/metosin/malli/pull/1138)  
* FIX: `:seqable` generates `nil` when `:min` is greater than 0 [#1121](https://github.com/metosin/malli/issues/1121)  
* FIX: `malli.registry/{mode,type}` not respected in Babashka [#1124](https://github.com/metosin/malli/issues/1124)  
* FIX: `:float` accepts doubles but never generates them [#1132](https://github.com/metosin/malli/issues/1132)  
* FIX: `:float` missing humanizer [#1122](https://github.com/metosin/malli/issues/1122)  
* Updated dependencies:  

```
fipp/fipp '0.6.26' to '0.6.27'
```

### Ring-http-response 0.9.5  
- Fix pre-expr syntax [#34](https://github.com/metosin/ring-http-response/pull/34)
- updated dependencies:  

```clojure
[ring/ring-core "1.13.0"] is available but we use "1.12.2"
```

### Going Forward to 2025  

Yes, it's already 1.1! I did not apply for Clojurists Together funding as I didn't have the time I wished for to work on Open Source. Not going anywhere, but need to figure out a way to continue the work in a sustaineable way. Cheers.  

### Something Else  

Vegan Pizza from Metosin [pikkujoulu](https://en.wikipedia.org/wiki/Pikkujoulu).

<img width="1728" alt="pizza" src="https://gist.githubusercontent.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/fefaf3a6befa7966c557197f2ecd5cbfe7e2dbfa/pizza.jpg">  <br>  

---

## Peter Taoussanis  
Report 6. Published December 31, 2024  

A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  

\- [Peter Taoussanis](https://www.taoensso.com)  

Hey folks, it's the end of the year again! 🍾🎉  

Will keep today's update short-  

This was another busy year for open source work, with almost 50 public [releases](https://www.taoensso.com/news#2024) (🫣). The biggest can be easily seen on my [2024 roadmap](https://www.taoensso.com/roadmap/2024).  

The headline focus was [Telemere](https://www.taoensso.com/telemere) - a modern rewrite of [Timbre](https://www.taoensso.com/timbre) that offers an improved API to cover traditional logging, structured logging, tracing, basic performance measurement, and more.  

That's been a lot of ongoing work - getting things polished for v1 final. I'm pretty happy with the present state: [v1 RC2](https://github.com/taoensso/telemere/releases/tag/v1.0.0-RC2) was recently released, and I expect that to become v1 final in January 🎉  

The agenda for next year (2025) currently includes:  

- [Telemere](https://www.taoensso.com/telemere) v1 final  
- [Tempel](https://www.taoensso.com/tempel) v1 final (sorry for the delay on this!)  
- [Sente](https://www.taoensso.com/sente) v1.21 (with lots of [planned improvements](https://github.com/taoensso/sente/issues/456))  
- [Tufte](https://www.taoensso.com/tufte) v3 (incl. sharing Telemere's core)  
- [Truss](https://www.taoensso.com/truss) v2 (incl. improved integration with Telemere)  
- Continued long-term work on [Carmine](https://www.taoensso.com/carmine) v4 (currently in early alpha)  

Looking forward to it :-)  

As usual, my up-to-date roadmap will be available [here](https://www.taoensso.com/roadmap) once it's ready.  

Thank you everyone, see you next year! 👋🫶  <br>  

---

## Daniel Slutsky: SciCloj  
Report 3 (Q3 2024). Published December 19, 2024.    

The [Clojurists Together](https://www.clojuriststogether.org/) organization has decided [to sponsor](https://www.clojuriststogether.org/news/q3-2024-funding-announcement/) Scicloj community building for Q3 2024, as a project by Daniel Slutsky. This is the second time the project has been selected this year. Here is Daniel's last update for this period.  

This overview of November's work is extended by a few recent updates from the first half of December.  

Comments and ideas would help. :pray:   

### November 2024  
[Scicloj](https://scicloj.github.io/) is a Clojure group developing a stack of tools and libraries for data science. Alongside the technical challenges, community building has been an essential part of its efforts since the beginning of 2019. Our current main community-oriented goal is making the existing data science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on example-based documentation, easy setup, and recommended workflows for common tasks.  

All these, and the tools to support them, grow organically, driven by real-world use cases.  

I serve as a community organizer at Scicloj, and this project was accepted for Clojurists Together funding in 2024 Q1 & Q3. I also receive regular funding through Github Sponsors, mostly from Nubank.  

As some parts of November's work have only matured during the beginning of December, I am writing this report a couple of weeks late. It overviews my involvement during November, the first half of December, and then comments about the proposed directions for the near future.  

I had 31 meetings during November and 25 meetings during the first half of December. Most of them were one-on-one meetings for open-source mentoring or similar contexts.  

All the projects mentioned below are done in collaboration with others. I will mention at least a few of the people involved.  

### November 2024 and early December highlights  

### Design discussions  
This has been a thoughtful period in [the Clojurians Zulip chat](https://scicloj.github.io/docs/community/chat/). We faced a few questions regarding how we organize functionality across libraries and went through a careful thinking process to reach a common ground of understanding. A lot of the details shared below are the fruit of those discussions.  

### [Noj](https://scicloj.github.io/noj/)  
The Noj library is the entry point to data science with Clojure, collecting a stack of relevant libraries. During this period, it was officially announced as Beta stage. We kept extending it and mostly worked on documentation. Carsten Behring kept improving the documentation workflow, partially automating it, and creating extensive documentation for a few major Noj components. I also took part in documentation and in helping other community members contribute a few drafts.  

### [Tableplot](https://scicloj.github.io/tableplot/)  
Tableplot, the plotting library based on the [layered grammar of graphics](https://vita.had.co.nz/papers/layered-grammar.html) idea, was one of my main foci of development. I implemented quite a few additional types of plots and control parameters and put a lot of work into the documentation reference.  

### [Tablemath](https://scicloj.github.io/tablemath/)  
Tablemath is one result of our recent design discussions. It is a new library for math and statistics that composes well with [tech.ml.dataset](https://github.com/techascent/tech.ml.dataset) and [Tablecloth](https://scicloj.github.io/tablecloth) datasets and uses the functionality of [Fastmath](https://github.com/generateme/fastmath). Tablemath is highly inspired by [R](https://www.r-project.org/) and its packages and is intended to compose well with [Tableplot](https://scicloj.github.io/tableplot/) layered plotting.
During this period, I released an initial version after exploring a few directions.  

### [Clay](https://scicloj.github.io/clay/)  
Clay, the REPL-friendly notebook and data visualization tool, received a few updates from other community members. On my side, I explored the different ways it consumes JS and CSS dependencies. We want to make this part more modular for various use cases. At the moment, I am struggling with a few technical difficulties.  

### [Kindly-render](https://github.com/scicloj/kindly-render)  
Kindly-render is a tool-agnosic implementation of the [Kindly](https://scicloj.github.io/kindly/) data visualization standard. Timothy Pratley kept developing it, and it looks very promising. My involvement was not intensive, except for a few joint coding sessions and reviews of the code.  

### [Scicloj open-source mentoring](https://scicloj.github.io/docs/community/groups/open-source-mentoring/)
In this period, we continued the collaboration of quite a few mentees and the current four mentors, including myself. To focus on progress with currently ongoing projects, we did slow down the growth of the project and could only accept a few new mentees.  

### Meeting new community members  
In the last few weeks, I did meet a few new community members: Clojurians who wish to get involved in data science and people of scientific or data background who wish to get involved in Clojure. There is a good momentum in adopting the Scicloj toolkit for various needs. With a few of the new friends, I started meeting more or less regularly and exploring their concrete use cases.  
 
### Clojure in Academia  
Following an initiative of Thomas Clark, we started an active exploration of pathways to make Clojure more present in academia. I reached out to about a dozen people who are active in academia and known to be involved in Clojure and scheduled a couple of meetings to discuss possible directions. There has been a warm response from most of the relevant people, who expressed their willingness to help explore this direction.  

Three main directions were proposed: (1) Working on academic papers to discuss technical aspects of Clojure's scientific stack. (2) Collaborating with researchers on specific use cases of Clojure. (3) Demonstrating the potential of Clojure in academic teaching.  

The teaching perspective, which was proposed by Blaine Mooers, will receive the highest priority in the short term.  

As a first step, we are considering organizing an online conference to make one or more of the above directions more visible and encourage further interest.  

### Tutorial meetings  
During our work on documenting Noj, we experimented with various workflows of writing tutorials together. Recently, we converged on a format that is working. We meet quite often and write tutorial drafts together. The same draft will typically be handled by different people in different meetings. Each time, we review everything so that the session is self-contained. This way, more people learn about topics they care about, and the content we write gets to be reviewed by more people and more perspectives.  

### Linear Algebra meetings  
The weekly linear algebra meetings keep happening every week. They typically go by the tutorial format mentioned above.  

### Website  
This period has been a usual period in terms of website maintenance.  
 
### real-world-data group  
The [real-world-data group](https://scicloj.github.io/docs/community/groups/real-world-data/) is a space for Clojure data practitioners to share their experiences. It keeps going, meeting every two weeks. In the three meetings we had in November and the one we had in December so far, we mainly discussed the topics mentioned above in this report, as well as a few work experiences of group members.  

## Near term goals  

### Noj  
In the near future, we should bring the Noj documentation to a state that is good enough to be clear and welcoming to new users.

### Tablemath  
Tablemath will probably be the main experimental project on my agenda. The main goals are to combine the underlying libraries (Fastmath, dtype-next, tech.ml.dataset, Tablecloth) to benefit from the advantages of each of them in terms of ergonomics as well as performance and to provide a user-friendly API inspired by R. (2)  

### Clojure in academia  
This project is still in its very early stages. We should explore various directions and carefully pick those which might be promising.  

### Tooling  
I will join Timothy Pratley on the goal of helping different tools support the Kindly standard. Cursive, Calva, Quarto, and Clojupyter are a few of the relevant candidates.  <br>


