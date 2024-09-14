---
title: "July & August 2024 Project Updates"
date: 2024-07-22T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Janet Carr, Thomas Clark, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Peter Taoussanis"  
draft: True


---
 
This month we have reports from both our 2024 long-term developers as well as from two shorter-term projects. Thanks everyone for the great work!

## Short-Term Projects
[Janet A. Carr: Enjure](#janet-a-carr-enjure)  
[Thomas Clark: Wolframite](#thomas-clark-wolframite)   


## 2024 Long Term Projects
[Bozhidar Batsov:](#bozhidar-batsov) CIDER. Orchard       
[Michiel Borkent:](#michiel-borkent) squint, babashka, neil, cherry, clj-kondo, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs  
[Kira McLean:](#kira-mclean) Scicloj Libraries. tcutils, Clojure Data Cookbook, Mentoring, and more   
[Nikita Prokopov:](#nikita-prokopov) Humble UI, Clojure Sublimed  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli  
[Peter Taoussanis:](#peter-taoussanis) Tempel, Tufte, Telemere  


## Short-Term Projects  

## Janet A Carr: Enjure  
Progress has been a bit slow in this latter half of the project. I do have freelance clients to keep happy after all.

I haven't elaborated too much of the implementation of Enjure, rather I've written some documentation in the github repository as well as deployed a website for the project over at [enjure.org](https://enjure.org). The website covers the basic philosophy and architecture as well as how to get started with Enjure by using the CLI. I'm hoping to give both Enjure and it's website some more love in the future when I have time. However, for the couple month span of the project thus far, I think it's been relatively successful in flushing out some of the ideas I have for the project. A true OSS MVP, if you will.  <br>

---

## Thomas Clark: Wolframite  

### Table of Contents  

1.  [Overview (TL;DR)](#orgaa044d6)  
2.  [Initialization](#org857f3f5)  
3.  [Evaluation model](#orgfc8c659)  
4.  [Documentation, packaging and literate programming](#org9025375)  
5.  [Future outlook](#org096ce99)  

<a id="orgaa044d6"></a>  

### Overview (TL;DR)  

In a nutshell, our proposal was about reawakening and increasing the ease of Wolfram interop with Clojure. In this second part of the project we continued to fix some minor bugs across the board (*quelle surprise*), as well as some bigger ones that had crept in with respect to jlink setup and initialization. The main focus however, was on user experience, particularly in regards to customizability: we don't just want access to Wolfram, but to a user-configurable Wolfram! In this regard, we fundamentally changed the initialization system, such that the user can now pass options to the library at runtime: particularly in regard to user-defined function and symbol aliasing. In a similar vein of explicitness, we remodelled the evaluation system such that expressions are only computed on command. This, as described below, should not only make computations more efficient to run, but also expressions more efficient to write. Last, but certainly not least, we put effort into the otherwise Sisyphean task of documentation. Aside from the usual docs, we put significant effort into revamping demo namespaces and creating short and long-form tutorials: integrating everything into a literately-programmed quarto-based website. On top of this, we created (with new build script) our first clojars package. In fact, it's time to introduce Wolframite 1.0!  


<a id="org857f3f5"></a>  

### Initialization  

The initialization system has been rebuilt to avoid implicit background loading on namespace imports. Making this wholly explicit adds an extra line to a user's program, but clarifies that the operation is side-affecting, while givng the user control over how and when the Wolfram kernels are started and stopped. Most importantly, introducing a start function (with an options map) gives the user access to the internals of the bridge, such that user preferences can be delivered at runtime, on a document by document basis.  

We hope that this will be increasingly useful in the future, but already allows the user to define their own aliases to Wolfram functions: greatly shortening and improving the readability of long expressions. Below, we can see what this looks like in practice. More than just giving the user the chance to rename symbols to their liking, the alias system (thanks to unicode support in clojure) allows the user to use real mathematical symbols in their analysis, rather than reverting to awkward function naming. Remember, Wolfram only allows lower- and upper-case latin and numbers!  

```clojure
    (def aliases
      '{** Power
        ++ Conjugate
        ++<-> ComplexExpand
        >< Simplify
        >><< FullSimplify
        ⮾ NonCommutativeMultiply
        √ Sqrt
        ∫ Integrate})
    
    (wl/start {:aliases aliases})
    
    (w/>><< (w/∫ (w/++<-> (w/+ (w/** 'x 4)
                               (w/√ (w/++ 'x))
                               (w/* 3 'x)))))
```
  
<a id="orgfc8c659"></a>  

### Evaluation model    

A big part of this work has been rebuilding the evaluation model. It was not an easy decision, but was motivated by several factors. First of all, Wolfram expressions can be long and yet, previously, all loaded symbols became executable functions. This meant that every nested s-expression led to a separate call to the underlying kernel, which did not seem efficient. Similarly to the case of initialization, part of the solution was to drop auto-evaluation entirely and to require an explicit `eval` call. This adds a small step for the user, but gives back control. It also allows you to pass runtime options and preferences to the evaluation, such as last-minute aliasing.

Another problem was the ergonomics of mixing Clojure and Wolfram expressions. Previously, familiar numerical clojure operators were reused as Wolfram operators and so the first line below would run fine when evaluated with Wolfram. Unfortunately however, it was very easy to write the second line, which would fail. Also, when relying on the quoting of symbols, you quickly run into trouble with standard macros (third line). In general therefore, we want to separate Clojure and Wolfram functions and have as little raw symbol manipulation as possible (even though it's still useful to fall back on).

```clojure
    '(+ 2 (Plus 3 4))
    
    (+ 2 '(Plus 3 4))
    
    (-> 'x
        '(Integrate x)
        wl/eval) ;; fails with error!
```

A previous solution to these problems was to load Wolfram functions at runtime under a clojure symbol; this helped alleviate the situation, but,  as it wasn't a *real* namespace, then the functions could not be required and therefore not consistently used in a library: what if someone picked a different symbol next time? Instead, Wolframite comes with a pre-generated Wolfram symbol namespace that can then be updated and extended by the user using the provided function, as demonstrated below.

```clojure
    (comment (write/write-ns! "src/wolframite/wolfram.clj"
                              {:aliases '{** Power
                                          badger Subtract}}))
    (ns physics.cavity.simple-PRs
      (:require [wolframite.core :as wl]
                [wolframite.impl.wolfram-syms.write-ns :as write]
                [wolframite.wolfram :as w]))
```

This system is slightly brittle at the moment, but so far provides a best of both worlds approach. Wolfram symbols are now loaded (not as executable functions) as a real namespace and this leads to more composable expressions:

```clojure
    (-> 'x
        (w/Integrate 'x)
        wl/eval) ;; (* 1/2 (Power x 2))
```
In general, it also removes the need to overload Clojure operators with Wolfram ones (different namespaces), allows you to treat aliases exactly the same as Wolfram functions and maintains editor autocompletion.  


<a id="org9025375"></a>

### Documentation, packaging and literate programming  

With usability in mind, another big part of this project was documentation and code hygiene. We're still far from Knuth's idea that:   
> Programs are meant to be read by humans and only incidentally for computers to execute.  

, but we're happy to have taken a significant steps in this direction. We moved the dial on API docstrings and the README; laid out a couple of substantial real-world physics examples and incorporated these into a website, along with updated versions of the introductory demos (where every page is a literate namespace). We also, with thanks to Daniel Slutsky, made Knuth happier by introducing a reliable method of displaying Wolfram expressions using TeX: particularly in the context of literate namespaces. Unfortunately, we have not yet got much feedback from the wider community (because we were late in launching and publicizing), but we have made a tentative start on integrating the library and maintainers with the data science community on [zulip](https://clojurians.zulipchat.com/#narrow/stream/151924-data-science). We realize however, that there is still much to do in this space. And so it is worth mentioning that we are strongly commited to the development of this library in the medium term, with ongoing tech. support (of the *IT*, rather than *Knuth* variety).  


<a id="org096ce99"></a>  

### Future outlook  

Some things took less time than expected (making a convenient package system); other things took more (the unexpected new evaluation model). Such is the case, and since we're currently waiting to hear back from the Wolfram staff, there are a couple of outlying goals that need more work. One of these is more explicit work on the underlying kernel parallelism. In principle, this hasn't been affected by our work but we are currently still missing the promised documentation. The other goal that was approached more implicitly was the laying of foundations for integration with [emmy](https://github.com/mentat-collective/emmy). The actual integration with emmy will take a lot of work, but the fundamentals are now here, with the advanced aliasing system now allowing for functional transforms, rather than simply 1-1 symbol replacements. The best parts of this project therefore, we hope, are still to come.  <br>

---

## 2024 Long-Term Projects  

## Bozhidar Batsov  

While the summer was a bit slower than the previous periods, we still made some solid progress on multiple fronts. The highlight of recent work is undoubtedly nREPL 1.3, which is one of the most ambitious nREPL releases in recent years. Most of the improvements are invisible to the users, but they improve the stability and predictability of nREPL. Here are the highlights:  
- Stacktraces got drastically shorter. Where the previous versions of nREPL would add 26 frames to the stack, now they are a lot shorter. (like 20 frames shorter)  
- `clojure.main/repl` has been replaced with a custom REPL implementation that is closer to how nREPL operates. This gave us more control over classloaders (which caused multiple issues in the past) and shortened the stack.  
- Support for sideloading has been removed. This experimental feature was not fully fleshed out and hasn't seen much use by nREPL clients. It might be revived in the future in a different form.
- nREPL now uses custom threadpools instead of calling future for its internal asynchronous actions.  

CIDER's snapshot already makes use of it and we've gotten reports that the big internal changes haven't caused any issues. Good stuff!  

Some other highlights from the past couple of months:  

- CIDER (and `cider-nrepl`) now support the new way of calling Java methods introduced in Clojure 1.11 (see https://github.com/clojure-emacs/cider-nrepl/pull/889)  
- Orchard now features its own implementation of tracing that replaces the use of `tools.trace` (see https://github.com/clojure-emacs/orchard/pull/284)  
- The CI setup for Orchard and cider-nrepl was reworked and now it takes a lot less time to run the tests there  
- Support for sideloading was removed from CIDER (following its removal from nREPL)  
- Several bug-fixes here and there  

You can play with all of this in the current snapshot version of CIDER. I expect we'll cut a new CIDER release in the next couple of weeks. Cheers!  <br>

---

## Michiel Borkent  
**Updates**
In this post I'll give updates about open source I worked on during July and August 2024. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

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

Here are updates about the projects/libraries I've worked on in July and August.   
* [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.
    * Unreleased:
    * [#2386](https://github.com/clj-kondo/clj-kondo/issues/2386): fix regression introduced in [#2364](https://github.com/clj-kondo/clj-kondo/issues/2364) in `letfn` (unreleased)  
    * v2024.08.29:  
    * [#2303](https://github.com/clj-kondo/clj-kondo/issues/2303): Support array class notation of Clojure 1.12 (`byte/1`)  
    * [#916](https://github.com/clj-kondo/clj-kondo/issues/916): New linter: `:destructured-or-binding-of-same-map` which warns about `:or` defaults referring to bindings of same map, which is undefined and may result in broken behavior  
    * [#2362](https://github.com/clj-kondo/clj-kondo/issues/2362): turn min-version warning into lint warning  
    * [#1603](https://github.com/clj-kondo/clj-kondo/issues/1603): Support Java classes in `:analyze-call` hook  
    * [#2369](https://github.com/clj-kondo/clj-kondo/issues/2369): false positive unused value in quoted list  
    * [#2374](https://github.com/clj-kondo/clj-kondo/issues/2374): Detect misplaced return Schemas   ([@frenchy64](https://github.com/frenchy64))  
    * [#2364](https://github.com/clj-kondo/clj-kondo/issues/2364): performance: code that analyzed fn arity is ran twice  
    * [#2355](https://github.com/clj-kondo/clj-kondo/issues/2355): support `:as-alias` with current namespace without warning about self-requiring namespace
    * v2024.08.01:
    * [#2359](https://github.com/clj-kondo/clj-kondo/issues/2359): `@x` should warn with type error about `x` not being an IDeref, e.g. with `@inc`
    * [#2345](https://github.com/clj-kondo/clj-kondo/issues/2345): Fix SARIF output and some enhancements ([@nxvipin](https://github.com/nxvipin))
    * [#2335](https://github.com/clj-kondo/clj-kondo/issues/2335): read causes side effect, thus not an unused value
    * [#2336](https://github.com/clj-kondo/clj-kondo/issues/2336): `do` and `doto` type checking ([@yuhan0](https://github.com/yuhan0))
    * [#2322](https://github.com/clj-kondo/clj-kondo/issues/2322): report locations for more reader errors ([@yuhan0](https://github.com/yuhan0))
    * [#2342](https://github.com/clj-kondo/clj-kondo/issues/2342): report unused maps, vectors, sets, regexes, functions as `:unused-value`
    * [#2352](https://github.com/clj-kondo/clj-kondo/issues/2352): type mismatch error for `or` without arguments
    * [#2344](https://github.com/clj-kondo/clj-kondo/issues/2344): copying configs and linting dependencies can now be done in one go with `--dependencies --copy-configs`
    * [#2357](https://github.com/clj-kondo/clj-kondo/issues/2357): `:discouraged-namespace` can have `:level` per namespace
* [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.
    * Mostly bumped library dependencies and improvements in SCI
* [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata
    * Support new Clojure 1.12 array notation
* [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs
    * Fix [#923](https://github.com/babashka/sci/issues/923): check for duplicate keys in dynamic set or map literals
    * Fix [#926](https://github.com/babashka/sci/issues/926): Support `add-watch` on vars in CLJS
* [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
    * [#135](https://github.com/squint-cljs/cherry/issues/135): Fix UMD build
* [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
    * Bump org.babashka/cli
    * Bump SCI
* [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
    * Fix [#39](https://github.com/borkdude/quickdoc/issues/39): fix link when var is named multiple times in docstring
* [http-server](https://github.com/babashka/http-server): serve static assets
    * [#13](https://github.com/babashka/http-server/issues/13): add an ending slash to the dir link, and don't encode the slashes ([@KDr2](https://github.com/KDr2))
    * [#16](https://github.com/babashka/http-server/issues/16): support range requests
* [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs! \

    * Fix [#102](https://github.com/babashka/cli/issues/102): `format-table` correctly pads cells containing ANSI escape codes
* [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
    * Upgrade/sync with clojure CLI v1.11.4.1474
    * deps.clj is now available as `clojure.exe` via the [clj-msi](https://github.com/casselc/clj-msi) installer and the official installation method on plain Windows!
* [fs](https://github.com/babashka/fs) - File system utility library for Clojure
    * [#132](https://github.com/babashka/fs/issues/132): add `read-link` to resolve symbolic link, without target of link needing to exist
* [tools](https://github.com/borkdude/tools): a set of [bbin](https://github.com/babashka/bbin/) installable scripts
    * Updated antq
    * Added `--minimize` option to the ddiff script
* [http-client](https://github.com/babashka/http-client): babashka's http-client \

    * Ensure that http-client works with Clojure 1.10 as the minimum supported Clojure version
* [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser
    * Mostly changes to accomodate running sci.nrepl with [clerk](https://github.com/nextjournal/clerk) viewer functions
* [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
    * Mostly worked on making viewer functions available from `.cljs` files and allow working on them via a nREPL session
* [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
    * Nikita Prokopov made the [squint logo](https://github.com/squint-cljs/squint/blob/main/logo/logo.svg)!
    * [#542](https://github.com/squint-cljs/squint/issues/542): fix `run` on Windows  

### Other projects  
There are many other projects I’m involved with but that had little to no activity in the past month. Check out the **Other Projects** section (more details) of [my blog here](https://blog.michielborkent.nl/oss-updates-jul-aug-2024.html) to see a full list.  
Published: 2024-08-30 <br> 

---

## Toby Crawley  
### August 2024  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/0d6640ae647f8b075e74c1b3d20128dccbadebc5...fc00a38a5814dcea44522f91750e981df8893f7e), [`infrastructure`](https://github.com/clojars/infrastructure/compare/c4cfac16c9bbdd1cb6907a4a0daf3c775cd1ce14...6cf9c100e38408016cd979f1611602523766200e)  

-   Bug fix: [Ensure jetty patch to send status message is always applied](https://github.com/clojars/clojars-web/commit/978eeab87928772c158fa454aeb9c8c1e630b8d2)  
-   Bug fix: [Properly rotate log files](https://github.com/clojars/clojars-web/commit/2df51fb53ed39fdd75e1637828b786015edce314)  
-   [Upgrade to Clojure 1.12.0-rc1](https://github.com/clojars/clojars-web/commit/ac9e6733a261ebb18d305379ef95f34d42fe3ef5)  
-   [Upgrade to clojure 1.12.0-rc2](https://github.com/clojars/clojars-web/commit/78a7408d3b1f614c477b047a17c5938aa60687b8)  



### July 2024  
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/662e10febf1267b1e67c321b1aa337550f3d7a29...0d6640ae647f8b075e74c1b3d20128dccbadebc5), [`infrastructure`](https://github.com/clojars/infrastructure/compare/59cec6c2924811f07d5e231923e642f6306fee05...c4cfac16c9bbdd1cb6907a4a0daf3c775cd1ce14)  

-   Bug fix: [Properly delete artifacts from S3](https://github.com/clojars/clojars-web/commit/b7d7d99bacfb863ddc9139d895640b8bf9a7b3fa)  
-   clj-kondo ugrade and linting cleanup
-   [Upgrade to Clojure 1.12.0-beta2](https://github.com/clojars/clojars-web/commit/0d6640ae647f8b075e74c1b3d20128dccbadebc5)  
-   [Use latest AL2023 AMI as base when building our AMI](https://github.com/clojars/infrastructure/commit/ea1bfd2dbd3dd47fb48e849439a6072cfcb3032a)  <br>

---

## Thomas Heller  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).  

Current shadow-cljs version: 2.28.14 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  

### Notable Updates  
- Updated nREPL Server to 1.3.0  <br>

---

## Kira McLean  

This is a summary of the open source work I've spent my time on throughout July and August, 2024. There was a blog post, some library updates, and lots of community work.

### Sponsors  
This work is made possible by the generous ongoing support of my sponsors. I appreciate all of the support the community has given to my work and would like to give a special thanks to Clojurists Together and Nubank for providing me with lucrative enough grants that I can reduce my client work significantly and afford to spend more time on these projects.  

If you find my work valuable, please share it with others and consider supporting it financially. There are details about how to do that on my [GitHub sponsors page](https://github.com/sponsors/kiramclean). On to the updates!  

### Blog post  
At the beginning of the summer Daniel Slutsky and I were feeling very ambitious and thought we might be able to put together a course for data scientists coming to Clojure from other languages. For many reasons, this hasn't materialized yet, but in service of these plans I wrote a [blog post comparing tablecloth](https://codewithkira.com/2024-07-18-tablecloth-dplyr-pandas-polars.html) to other common data processing tools, like `dplyr`, `pandas`, and `polars`. My goal was to put tablecloth in perspective, illustrating some of the key differences between it and other standard, more popular, data processing tools.  

### `tcutils`  
I added a few more helpers to `tcutils`, like `between` and `duplicate-rows`, and also made a [docs website](https://scicloj.github.io/tcutils/) for the project. I also had many interesting conversations with people in the community about how Clojure's data processing tools "feel" to work with, and how we might adopt APIs and conventions that are familiar to data scientists in the interest of making their transition to Clojure's ecosystem as smooth as possible.  

### Clojure Data Cookbook  
This month I added a chapter about working with data from databases, starting with SQL, and also continued to work on the end-to-end example for the introductory section. Working with real data is very difficult and interesting, and it's a fun challenge to try to figure out the right balance between getting into the weeds and compromising on the final result. So much of data science is just cleaning up messy data from the world, but surprisingly often you have to make some assumptions about how you're going to use the data in order to make decisions about how to do the cleaning. And there are tons of different ways to "clean" data, but the strategies you use depend on what information you're after.  

In the particular example of the housing dataset I'm working with there are many missing values to handle, and some questionable rows that look like duplicates but aren't _exactly_ duplicates. There are also lots of illogical data points, like house sales from the future or multiple sales for the same property on a given date. Deciding how to handle these cases to build up a "clean" dataset to actually work with is a very interesting exercise in domain modelling and goal setting.  

### Scicloj mentoring program  
This one is really mostly Daniel Slutsky's amazing work, but we collaborated on launching it and it's definitely worth mentioning. We put together a [structured way for people to get involved](https://scicloj.github.io/docs/community/groups/open-source-mentoring/) in contributing to Clojure's open source data science ecosystem, and got an overwhelmingly positive response. Over 25 people reached out to express an interest in contributing their time to Scicloj projects. The structured parts of the program include having some help choosing a meaningful and impactful project to work on, and up to an hour per week of one-on-one time with a mentor to help things progress. Daniel is doing all the heavy lifting coordinating the mentors, but it's been great so far participating as one and meeting some very keen and smart people who are willing to help us move things forward.  

Another big part of this is thinking of the projects to work on. We came up with a list of projects that would deliver high value to the community but remain small enough to tackle by a single developer. We also tried to come up with ones that would require a wide range of skills and interests to try to accommodate as many people as possible. I am super excited to see how things go over the next few months with all of these projects.  

### Other community connections  
I'm still doing my weekly data-science drop-in streaming with Clojure Camp. I really enjoy connecting with other people who are interested in Clojure for data science, and I often get great suggestions and tips, too.  

I also met with a couple of groups of people who are presenting at the Conj this year to help brainstorm some ideas for how to make the most of the talks. Daniel has amazing vision for the community and organized these calls that I was lucky enough to join. The goal is to connect all of the people who are giving data-related talks to optimize the overall messaging, like minimizing duplication across talks or drawing examples from each other's presentations. I love conference speaking and hope to do more of it in future years when my personal commitments allow for it, but in the meantime it's really amazing getting to connect with such cool people in the community to learn about their talks and brainstorm ideas for making them the best they can be. I'm hoping to attend the conference this year to see some of these great talks in person.  

### Personal Updates  
This has been a really amazing year professionally, having had the opportunity to spend much more time than in the past on open source and public work for the Clojure community. I've been trying to make the most of it and it's been really rewarding. Over the next couple of months, there are some other parts of my life that will be taking precedence, however.  

The main one is my relationship. I'm getting married in a couple of weeks and will be taking almost a month off between getting ready for the wedding, wrapping up all the loose ends afterward, and a nearly 3-week-long honeymoon. I've never taken this long off of work in my life, so I'm both excited and curious to see what it's like. For over a decade now my career has been taking up most of my time and energy. It's been well worth it and I'm really happy with my work now, but I'm also excited to be stepping into a new chapter of life where things can be more balanced.  

Related to this, the other major update I have to share is that I've accepted a full time job with a company called BroadPeak which I will be starting as soon as I'm back from my honeymoon. It's a small fintech company built primarily with Clojure that handles trade data management, commodities transaction surveillance, regulatory compliance, and other things related to the behind-the-scenes of commodities trading. I think it's a perfect fit for my skills and interests, and I'm hoping to have a chance to build some bridges between a really exciting, growing company that uses Clojure for real-world financial data processing and the Clojure open source community. Initial conversations about how the engineering team there feels about open source and community involvement have been really promising, so I'm optimistic that it will work out well for everyone. I'm not sure yet what exactly my open source work will look like once this job starts, but at a minimum I will still be working on the various side projects, like I always was before I tried giving it a go full time.  

No matter how things go, I'll be back in two more months with another update. Thanks for reading. As always, feel free to reach out, and hopefully see some of you at the Conj! :)  <br>  

---

## Nikita Prokopov  
Hello dear sponsors, thank you all for your continous support. Last two months have been really heavy on Humble UI and Clojure Sublimed.  

### [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed)  
**Clojure support for Sublime Text 4:**  
- New syntax that can highlight reader comments `comment.reader` together with the following form,  
- Highlight `(comment ...)` blocks as `comment.form`,  
- Highlight namespaces in symbols as `meta.namespace.symbol`,  
- Highlight unused symbols as `source.symbol.unused`,  
- Properly highlight `entity.name` in `def*` forms only at second position, skipping all meta/comments,  
- Quote & syntax quote highlight following form as `meta.quoted` and `meta.quoted.syntax`,  
- Metadata highlights following form as `meta.metadata`,  
- Octal & arbitrary radix integers [71](https://github.com/tonsky/Clojure-Sublimed/issues/71),  
- Better keyword detection,  
- Built-in color scheme to utilize REPL and new syntax features,  
- `clojure_sublimed_reindent` command that reindents entire buffer if selection is empty and only selected lines if not,  
- Option to use `cljfmt` for code formatting,  
- Removed separate EDN syntax, merged with main Clojure (Sublimed),  
- Settings can now be specified in main `Preferences.sublime-settings` as well. Just prepend `clojure_sublimed_` to each setting’s name,  
- REPL can detect namespaces with meta on ns form [116](https://github.com/tonsky/Clojure-Sublimed/issues/116),
- Detect `.shadow-cljs/nrepl.port` and `.shadow-cljs/socket-repl.port` [114](https://github.com/tonsky/Clojure-Sublimed/issues/114),  
- Connect commands now accept `timeout` argument for automation scenarios like “start clojure, start trying to connect to REPL until port is available”, 
- Eval previous form at current level [118](https://github.com/tonsky/Clojure-Sublimed/issues/118),
- Auto-detect UNIX sockets, support relative paths,  
- Correctly parse escaped comma [120](https://github.com/tonsky/Clojure-Sublimed/pull/120) via [@oakmac](https://github.com/oakmac)  

### [Humble UI](https://github.com/HumbleUI/HumbleUI)
**Clojure Desktop UI framework:**
- Renamings (halign/valing → align, width/height → size, rect → bounds, vscroll → vscrollable, vscrollbar → vscroll, use-size -> with-bounds, rounded-rect → rect, clip-rrect → clip),  
- Normalized most of the components to have `opts` as first argument,  
- Declarative font management,  
- Viewport & optimized rendering for components outside it,  
- `.-parent` node field on all `ANodes`,  
- `-reconcile-opts` to migrate props,  
- Built-in profiler,  
- Full-fledged demos & source examples for some components,  
- New components: ui/link,  
- New demos: whiteboard,  
- Better code organization,  
- Fixed REPL reloading,  
- Fixed crash on scale change,  
- Removed `dynamic/with-context-classic`,  
- Simplified `memo-fn`,  
- Added `:window-scale-change` event  

### [JWM](https://github.com/HumbleUI/JWM/)   
**Cross-platform window management and OS integration library for Java:**  
- macOS: Stable screen ids  
- macOS: Do not stop cvdisplaylink  

### [DataScript](https://github.com/tonsky/datascript)  
**immutable database and Datalog query engine for Clojure, ClojureScript, and JS:**  
- Regression: transacting many ref value as a set of inline maps [476](https://github.com/tonsky/datascript/issues/476)  
- Fixed find-datom on empty DB [477](https://github.com/tonsky/datascript/issues/477)  

### [Clj-Simple-Router](https://github.com/tonsky/clj-simple-router)  
**Simple order-independent Ring router:**  
- Add a complete example to the README [2](https://github.com/tonsky/clj-simple-router/issues/2)  
- Added clj-kondo analyzer for routes macro [3](https://github.com/tonsky/clj-simple-router/issues/3) with [goshatch](https://github.com/goshatch)  

### [Sublime Executor](https://github.com/tonsky/Sublime-Executor)
**Executable runner for Sublime Text:**  
- 1.5.1 Trim command name in status bar [7](https://github.com/tonsky/Sublime-Executor/issues/7)  

### [Uberdeps](https://github.com/tonsky/uberdeps)  
**Uberjar builder for deps.edn:**
- merge :override-deps from :aliases [49](https://github.com/tonsky/uberdeps/issues/49) [53](https://github.com/tonsky/uberdeps/pull/53) via [@dkick](https://github.com/dkick)  

### Blogging:  
- [Clojure macros continue to surprise me](https://tonsky.me/blog/clojure-macros/)  
- [Where Should Visual Programming Go?](https://tonsky.me/blog/diagrams/)  

### Designing:  
- A logo for [squint](https://github.com/squint-cljs/squint), a light-weight dialect of ClojureScript.  <br>

---

## Tommi Reiman  
Two big release of [malli](https://github.com/metosin/malli) and lot's of WIP work. Explored existing and new Clojure-bindings (with Malli) with latest features of both Anthropic and OpenAIs APIs, one gist [here](https://gist.github.com/ikitommi/e643713719c3620f943ef34086451c69).  

Added [Ambrose Bonnaire-Sergeant](https://github.com/frenchy64) as a maintainer to malli. Lot's of good disucssions on how the library should go forward. Here's the [Q3 Clojureist Together funding announcement](https://www.clojuriststogether.org/news/q3-2024-funding-announcement/#malli-constraints-and-humanization-ambrose-bonnaire-sergeant) for his work.  

### Library Releases  

### malli 0.16.4  
* Distribute `:merge` over `:multi` [#1086](https://github.com/metosin/malli/pull/1086), see [documentation](README.md#distributive-schemas)  
* `:multi` with keyword `:dispatch` accumulates data to generated values [#1095](https://github.com/metosin/malli/pull/1095)  
* Allow `m/-proxy-schema` child to be a `delay` [#1090](https://github.com/metosin/malli/pull/1090)
* `json-transformer` decodes 123.0 into 123 for schemas like `:int`, `pos-int?` etc. [#986](https://github.com/metosin/malli/issues/986)  
* Fix `malli.dev.pretty` throws when explaining errors in nested maps [#1094](https://github.com/metosin/malli/issues/1096)  
* Fix ClojureScript [arithmetic warning](https://github.com/metosin/malli/issues/1093)  
* Fix `(-some-pred [])` should return `false` [#1101](https://github.com/metosin/malli/pull/1101)  
* Doc `mu/assoc` and `mu/dissoc` only handle one key at a time [#1099](https://github.com/metosin/malli/pull/1099)  
* Try to make `map-of-min-max-test` less flaky by fixing seed [#1098](https://github.com/metosin/malli/pull/1098)  
* Updated dependencies:  

```
borkdude/edamame '1.4.25' to '1.4.27'
```

### malli 0.16.3  
* `:->` added to default registry, see [documentation](https://github.com/metosin/malli/blob/master/docs/function-schemas.md#flat-arrow-function-schemas).  
* New `:seqable` and `:every` schemas [#1041](https://github.com/metosin/malli/pull/1041), see [docs](https://github.com/metosin/malli#seqable-schemas)  
* Fix OOM error with infinitely expanding schema [#1069](https://github.com/metosin/malli/pull/1069)  
* Correctly form prop-less schemas that have map/nil as first child [#1071](https://github.com/metosin/malli/pull/1071)  
* Support min/max on uncountables like eductions [#1075](https://github.com/metosin/malli/pull/1075)  
* Fix clj-kondo can't parse config.edn written by `(malli.dev/start!)` [#1083](https://github.com/metosin/malli/issues/1083)  
* unstrument before instrumenting [#1081](https://github.com/metosin/malli/pull/1081)  
* Replace `.entryAt` with `.valAt` during validation [#1079](https://github.com/metosin/malli/pull/1079)  
* Corrected DEPRECATED warning for `m/-simple-schema` [#1077](https://github.com/metosin/malli/pull/1077)  

### Something Else  
Back in the baltics, this time in Lithuania.  
<img width="1728" alt="vilna" src="https://gist.githubusercontent.com/ikitommi/8c904bfaa17f3871f80d3e20ea84ebb7/raw/88484414839a3e81984f730993b1bef95c29a5b6/vilna.jpg">  <br>

---

## Peter Taoussanis  

### Open source update  
A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), and [other sponsors](https://www.taoensso.com/sponsors) of my open source work! I realise that it's a tough time for a lot of folks and businesses lately, and that sponsorships aren't always easy 🙏  
\- [Peter Taoussanis](https://www.taoensso.com)  



### Recent work  
Hi folks! 👋 I hope those of you in summer are enjoying the nice weather ☀️  
Have some chonky updates for today!  

### Timbre  
> [Timbre](https://www.taoensso.com/timbre) is a pure Clojure/Script logging library.  

Timbre **v6.6.0-RC1** is [out now](https://github.com/taoensso/timbre/releases/tag/v6.6.0-RC1). There's some internal improvements, but the highlight is added out-the-box support for **Java logging interop** via [SLF4J](https://www.slf4j.org/).  

This is handled via a new SLF4J provider/backend dependency [available on Clojars](https://clojars.org/com.taoensso/timbre-slf4j) - instructions [here](https://github.com/taoensso/timbre/wiki/4-Interop#java-logging).  

The implementation is back-ported from [Telemere](https://www.taoensso.com/telemere) and includes support for legacy and modern ("fluent") SLF4J logging APIs, MDC (context key/vals), markers, etc.  

This was one of the [upvoted](https://github.com/taoensso/roadmap/issues/11) items on my [open source roadmap](https://www.taoensso.com/roadmap/vote), and definitely something nice to have officially supported.  

Previously many Timbre users used the separate [slf4j-timbre](https://github.com/fzakaria/slf4j-timbre) library for such interop - and I'd like to say a warm thanks to that library's original author [Farid Zakaria](https://github.com/fzakaria) and steward / maintainer [rufoa](https://github.com/rufoa).  

Their job was a challenging one - needing to keep up with internal changes to Timbre over time, etc. With the new approach, Timbre’s SLF4J support will now always be automatically kept in sync and tested with Timbre’s latest release, which should help minimize future maintenance headaches.  

### Telemere  
> [Telemere](https://www.taoensso.com/telemere) is a modern rewrite of [Timbre](https:/www.taoensso.com/timbre) that offers an improved API to cover traditional logging, structured logging, tracing, basic performance measurement, and more.  

My big focus recently has been Telemere, with 8 new beta releases since June.  

The latest as of this post is [v1.0.0-beta22](https://github.com/taoensso/telemere/releases/tag/v1.0.0-beta22) (2024-08-28).  

The basic API has been stable, including re: [signal creation](https://cljdoc.org/d/com.taoensso/telemere/CURRENT/api/taoensso.telemere#help:signal-creators) and [signal content](https://cljdoc.org/d/com.taoensso/telemere/CURRENT/api/taoensso.telemere#help:signal-content) - but I've been continuing to polish things in preparation for RC1 (expected next month).  

### Recent improvements include:
#### Significantly improved OpenTelemetry interop  
This was another [upvoted item](https://github.com/taoensso/roadmap/issues/16) from my [open source roadmap](https://www.taoensso.com/roadmap/vote), and quite a bit of work to get just right.  

Telemere can now send signals as OpenTelemetry [`LogRecords`](https://opentelemetry.io/docs/specs/otel/logs/data-model/) with correlated tracing data to configured [OpenTelemetry Java](https://github.com/open-telemetry/opentelemetry-java) [exporters](https://opentelemetry.io/docs/languages/java/exporters/).  

This allows output to go (via configured exporters) to a wide variety of targets like [Jaeger](https://www.jaegertracing.io/), [Zipkin](https://zipkin.io/), [AWS X-Ray](https://aws.amazon.com/xray/), [AWS CloudWatch](https://aws.amazon.com/cloudwatch/), etc.  

See [here](https://github.com/taoensso/telemere/wiki/3-Config#opentelemetry) for instructions.  

A couple nice properties of the way Telemere handles OpenTelemetry interop:  
1. Aside from configuring OpenTelemetry Java itself, Telemere's OpenTelemetry interop **doesn't require any use of or familiarity with the OpenTelemetry Java API or concepts**. So if you're like me, you'll be pleased to know that you can basically avoid all the usual OpenTelemetry API nonsense. You just use Telemere as you normally would, and it'll act as your interface to OTel.  
   
2. Creating OpenTelemetry tracing spans and attaching attributes can be expensive, which violates a unique [design goal](https://github.com/taoensso/telemere?tab=readme-ov-file#next-gen-observability) of Telemere - to make it possible to **dynamically filter costs as you filter signals**. Telemere works around this by creating only *minimal* spans at first (to enable full interop with auto-instrumentation and other OTel tools), and saving all the expensive work for the post-filter signal handlers. Basically this gives you best of both worlds: full auto interop, but also lazy costs well-suited to Telemere's rich filtering architecture.  

For feedback and/or experience reports, please feel free to ping me on the [Slack channel](https://www.taoensso.com/telemere/slack) or [GitHub](https://www.taoensso.com/telemere).  

#### Significant doc improvements  
A major focus for Telemere has been the docs and examples.  
My hope is to build something friendly enough for **complete beginners**.  

If you haven't looked at them yet (or recently), it might be worth checking out the [README](https://www.taoensso.com/telemere), [Wiki](https://www.taoensso.com/telemere/wiki), or [API docs](https://www.taoensso.com/telemere/api).   

Some good starting points:  
1. [Introduction](https://github.com/taoensso/telemere/wiki/1-Getting-started#introduction) (concepts, terminology)  
2. [Creating signals](https://github.com/taoensso/telemere/wiki/1-Getting-started#creating-signals) (how to log stuff)  
3. [Signal content](https://cljdoc.org/d/com.taoensso/telemere/CURRENT/api/taoensso.telemere#help:signal-content) (what gets logged)  
4. [Filtering signals](https://github.com/taoensso/telemere/wiki/3-Config#filtering) (decide what does/not get logged)  
5. [Video demo](https://www.youtube.com/watch?v=-L9irDG8ysM) (concepts, usage - 24 mins)  

#### Facade API for library authors  
Telemere now offers [guidance](https://github.com/taoensso/telemere/wiki/9-Authors) for library authors/maintainers that might like to use Telemere in their own library, and offers a new super lightweight [Facade API](https://clojars.org/com.taoensso/telemere-api).  

For a library using the facade:  
- If end users **already have** Telemere, then they’ll get Telemere signals out of the library. (And full filtering capabilities, etc.).  
- If end users **don’t have** Telemere, then the library can instead log using something like `tools.logging`.  

#### General comments / status  
Telemere may seem a bit of an odd animal at first since it looks like a logging library that's hesitating to call itself a logging library. As it took shape, I struggled with how to describe it.  

What I'd say today: Telemere's basically meant to be a one-stop API for all the most common observability needs. These include traditional (string-oriented) logging, structured logging (arb nested Clojure data), events, tracing, and basic performance monitoring.  

What I realised thinking about this problem space over the years - is that all of the above share fundamental ideas and needs:  
- We want to capture info in our systems at some point  
- We want to do it conditionally  
- We want to do something useful with that info  

I suspected that a single API would do - but I wasn't sure until I tried. When I did try, what I ended up with was actually a *single macro* ([`signal!`](https://cljdoc.org/d/com.taoensso/telemere/CURRENT/api/taoensso.telemere#signal!)) that handles all of the above through a handful of [options](https://cljdoc.org/d/com.taoensso/telemere/CURRENT/api/taoensso.telemere#help:signal-options).  

I'm honestly very happy with the result - I find it elegant, easy to use and to understand, and deeply flexible. And I'm proud of the particular combination of ease-of-use and power - since these goals are often in conflict.  

I'll note that Telemere's approach is possible **only in a Lisp**. And certain aspects of its implementation (esp. re: interop) are possible **only in Clojure**. So the value proposition here is unique.  

At the risk of being overly blunt: observability matters, and in my opinion Telemere offers a code-side observability story that's competitive with anything else I'm aware of in any language.  

Part of my motivation in pursuing this particular project has been to try to **use Clojure's unique strengths** to offer a unique and compelling ***advantage*** to Clojure projects/businesses/teams. Clojure enables Telemere, Telemere helps enable inexpensive and effective observability, observability helps enable more **robust and debuggable systems**. And used wisely, robustness and debugability can help enable **successful projects**.  

So the idea (hope) is to be both a **practical tool**, and a reference example of one of the kinds of **real-world advantages** made possible by Clojure.  

### Upcoming work  
> As always please do help [vote](https://www.taoensso.com/roadmap/vote) on what you'd like to see me working on!  

### Telemere  
My aim is to release v1 RC1 in September, then v1 final before the end of 2024.  

The big stuff is all done, what remains is to give folks the opportunity to kick tyres and report issues or request changes before locking down the API.  

Please also [vote](https://github.com/taoensso/roadmap/issues/12) on which signal handlers would be most useful.  

After that, my hope will be for Telemere to basically be stable - and to redirect future efforts toward education and promotion. Telemere enables a lot of powerful but non-obvious stuff, which I'll need to do more work to explain.  

### Tempel  
> [Tempel](https://www.taoensso.com/tempel) is a new data security framework for Clojure.  

I'd like to release v1 final before the end of 2024. Still have some last features to think through and design ([multi-factor authentication](https://github.com/taoensso/roadmap/issues/17), etc.).  

### Tufte  
> [Tufte](https://www.taoensso.com/tufte) offers simple performance monitoring for Clojure/Script.  

Once Telemere v1 RC1 is out, I'll be ready to prepare Tufte v3 - which'll include a move to the exact same signal architecture powering Telemere.  

Tufte and Telemere will then share identical terminology, capabilities, and API for filtering, handlers, etc.  

The two already [work well together](https://github.com/taoensso/telemere/wiki/6-FAQ#does-telemere-replace-tufte), but interop will be even smoother after v3. The aim is to offer Telemere and Tufte as partner (complementary) libraries to enable unique [next-gen observability](https://github.com/taoensso/telemere?tab=readme-ov-file#next-gen-observability) capabilities for Clojure and ClojureScript applications.  














