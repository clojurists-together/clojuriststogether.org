---
title: "Nov. and Dec. 2024 Project Updates"
date: 2025-01-08T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Daniel Slutsky, Peter Taoussanis"  
draft: True


---
 

Happy New Year all! This is the last set up updates from our developers who received 2024 annual funding - and the final report from Daniel Slutsky who was funded in Q3 2024. Thanks to everyone for their amazing work throughout the year!


## Long-Term Project Updates  

[Bozhidar Batsov:](#bozhidar-batsov) CIDER       
[Michiel Borkent:](#michiel-borkent) squint, babashka, neil, cherry, clj-kondo, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs, shadow-grove    
[Kira McLean:](#kira-mclean) Scicloj Libraries. tcutils, Clojure Data Cookbook, and more   
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Datascript, AlleKinos, Clj-reload, and more  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli, jsonista, and more    
[Peter Taoussanis:](#peter-taoussanis) Carmine, Nippy, Telemere, and more  <br>

## Q3 2024 Project Update
[Daniel Slutsky:SciCloj](#)

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



<br>  

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

<img width="1728" alt="pizza" src="https://gist.githubusercontent.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/fefaf3a6befa7966c557197f2ecd5cbfe7e2dbfa/pizza.jpg">
