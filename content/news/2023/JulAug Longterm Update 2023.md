---
title: "July and August 2023 Long Term Project Updates"
date: 2023-09-02T08:30:00+08:00
summary: Check out the latest from our 11 developers
author: Kathy Davis


---  

Even though it was vacation season, our developers share their progress (and challenges) for an incredible outpouring of work on their 2023 long term projects! Check it out.<br>  

[Calva: Peter Stromberg](#calva-peter-stromberg)  
[Carmine V4: Peter Taoussanis](#carmine-v4-peter-taoussanis)  
[CIDER/REPL: Bohzidar Batsov](#ciderrepl-bohzidar-batsov)  
[clj-Kondo, babashka, nbb, SCI, Cherry, Squint: Michiel Borkent](#clj-kondo-babashka-nbb-sci-cherry-squint-michiel-borkent)  
[Clojar.org: Sean Corfield](#clojarorg-sean-corfield)  
[Clojars: Toby Crawley](#clojars-toby-crawley)  
[ClojureDart: Christophe Grande](#clojuredart-christophe-grande)  
[Clojure-lsp: Eric Dallo](#clojure-lsp-eric-dallo)  
[Humble UI: Nikita Prokopov](#humble-ui-nikita-prokopov)  
[Malli: Tommi Reiman](#malli-tommi-reiman)  
[Shadow-cljs: Thomas Heller](#shadow-cljs-thomas-heller)  
<br>  

---



## Calva: Peter Stromberg  

These two months were not just about Calva. 😀

### Calva

The biggest change was one I thought would be really tiny. We upgraded our dependency on cljfmt, and it got to be a much bigger change than I had expected. cljfmt had changed its configuration format, and it was a bit of work to figure out how to adapt Calva's formatter to this. What made matters much worse is that I initially missed this configuration change and released a quite broken version of Calva. Doing something that already is a bit tricky in fire fighting mode is not making it easier. Or nicer. Wonderful then to notice that Calva and Clojure users had my back. I got some really wonderful help. I'm working in the best community imaginable.

**Some more changes:**

- Support for workflows involving Fiddle Files, of [Clojure Design Podcast fame](https://clojuredesign.club/episode/014-fiddle-with-the-repl/), was added. Please let us know if you use this support and how you fare with it!  
- Improve nREPL message diagnostics.
- Fixing issues around shadow-cljs REPL start and connect
- Restructure and complement the docs around using Calva with shadow-cljs  
- Improvements with evaluating expressions inside Rich Comment Forms  
- Improvements in how the nREPL server collects output side effects from the REPL process, following CIDER's model  
- Improvements in the handling of, and messaging around, clojure-lsp binary downloads  <br>


### Lingy  

When testing and providing feedback on the development of [Lingy, a new Clojure implementation running on Perl](https://github.com/lingy-lang/lingy), I wanted to be able to connect Calva to the Lingy REPL. So that caused me to start and participate in the development of a [Lingy nREPL server](https://github.com/lingy-lang/lingy#nrepl-support). Now users of Calva, and any other nREPL client, can interact with their Lingy application from the editor. (Yes, so this was partly about Calva 😄.)<br>  


---

## Carmine V4: Peter Taoussanis  

### Open source update  
A big thanks to [Clojurists Together](https://www.clojuriststogether.org/), [Nubank](https://nubank.com.br/), [lambdaschmiede](https://www.lambdaschmiede.com/), and [other sponsors](/sponsors) of my open source work!

### Recent work  
This was a productive couple of months! Managed to dedicate 100% of my time to open source. Recent work includes:  

- [14 library updates](https://www.taoensso.com/clojure/news), including pre-releases for major updates to [Nippy](https://github.com/taoensso/nippy/releases/tag/v3.3.0-RC1) and [Carmine](https://github.com/taoensso/carmine/releases/tag/v3.3.0-RC1).
- Significant improvements to all **library documentation** are underway, including:
	- Cross-platform API docs via a [Codox fork](https://github.com/taoensso/codox) (work also [submitted upstream](https://github.com/weavejester/codox/issues/216)).
	- Improved compatibility with [clj-doc](https://cljdoc.org/). Thanks to [Lee Read](https://github.com/lread) for assistance with some [upstream improvements](https://github.com/cljdoc/cljdoc/issues/779) & troubleshooting advice 🙏.  
	- After much experimentation, I've settled on new tooling and a new workflow for writing documentation.  This'll allow me to much more easily write and maintain docs moving forward.  The first change that will be apparent is the move to a wiki format ([example](https://github.com/taoensso/sente/wiki)) that allows more structure and easier searching.  
   	- Relatedly- I've started updating and expanding all pre-existing docs, with a focus on more beginner-oriented info. I'm tackling this in steps, so it'll take some time to migrate and update everything. 
- I've moved all my open source under a dedicated [GitHub group](https://github.com/taoensso) to help keep things organized and more easily searchable.  
- I've updated my homepage to include [better info](https://www.taoensso.com/clojure) on my open source - including [release news](https://www.taoensso.com/clojure/news).  
- I've started added basic GraalVM compatibility (and tests) to all libraries. Many are already covered, the rest will be covered whenever they get their next release.  
- (Just for fun) - I made a [new logo](https://github.com/http-kit/http-kit/blob/master/http-kit-logo.png) for [http-kit](https://www.taoensso.com/http-kit).  
- I've been deep in experimentation on a new telemetry library that I'm quite excited about. I'll have more to share on this in future, but in the meantime I can say that I'm very happy with how the initial prototype is coming along 👍  

### Upcoming work

My current [roadmap](https://www.taoensso.com/clojure/roadmap) includes:

- **September**: stable [Nippy](https://www.taoensso.com/nippy) v3.3 and [Carmine](https://www.taoensso.com/carmine) v3.3 releases.
- **October**: first public release of [Tempel](https://www.taoensso.com/tempel), a new **data security framework** that I plan to talk more about closer the time.

I'll also be continuing work on the new telemetry library (currently named [Telemere](https://www.taoensso.com/telemere)), and on the ongoing documentation improvements. - [Peter Taoussanis](https://www.taoensso.com) <br>


---

## CIDER/REPL: Bohzidar Batsov  
The last couple of months were very productive for CIDER and all of its related projects. This was mostly due to our long-term contributor `vemv` being between jobs and eager to tackle some of the major items on our TODO list. I've decided to use a sizeable chunk of the donation money CIDER had accumulated over at OpenCollective to fund `vemv`'s work on CIDER & friends, and the results were great. Some highlights:  

- We've got a brand new and much improved https://github.com/clojure-emacs/enrich-classpath Now CIDER support using `enrich-classpath` with Clojure's CLI (`tools.deps`) as well.  
- The test runner now has a fail-fast mode and shows timing information about the tests  
- We finally started to make use of Clojure 1.10's improved error message. (see https://github.com/clojure-emacs/cider/issues/3418) This prevents stacktraces from showing up whenever the [:clojure.error/phase](https://clojure.org/reference/repl_and_main#_at_repl) indicates that it's a compilation error.  
- Add new customization variable `cider-clojurec-eval-destination` to allow specifying which REPL CLJC evals are sent to.  
- CIDER can now infer the indentation for many macros, without the need to provide indentation metadata for them (similar to how SLIME works for Common Lisp)  
- There have been many small improvements around the ClojureScript support  

We've also did a big cleanup of the backlog and got the open issues under 100 for the first time in quite some time!  

There are also a ton of other small improvements and bug-fixes that have been implemented in those two months. I encourage everyone to check the full list of changes [here](https://github.com/clojure-emacs/cider/blob/master/CHANGELOG.md#master-unreleased). All this work is the reason why CIDER 1.8 is still not out - we want to pack more into this release before we focus on CIDER 2.0  

Right now it seems that CIDER 1.8 will be the biggest and most important CIDER release in the past 2-3 years! The release should happen any day now (this time for real)!  

On an unrelated note - `clojure-ts-mode` is now available on NonGNU ELPA. Its submission there triggered a few long conversations about potentially including `clojure-mode` and/or `clojure-ts-mode` in Emacs itself. It's a complex topic that we'll be discussing with the `clojure-emacs` maintainers and our users in the months to come.  

That's all I have for you today! I expect to write a few more detailed blog posts on some of the topics I mentioned here once CIDER 1.8 has been released. <br>

---


## clj-Kondo, babashka, nbb, SCI, Cherry, Squint: Michiel Borkent  

**Sponsors**
I'd like to thank all the sponsors and contributors that make this work possible! 
Top sponsors:  
•	[Clojurists Together](https://clojuriststogether.org/)  
•	[Roam Research](https://roamresearch.com/)  
•	[Nextjournal](https://nextjournal.com/)  
•	[Toyokumo](https://toyokumo.co.jp/)  
•	[Cognitect](https://www.cognitect.com/)  
•	[Kepler16](https://kepler16.com/)  
•	[Adgoji](https://www.adgoji.com/)  

If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
•	[Github Sponsors](https://github.com/sponsors/borkdude)  
•	The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective  
•	[Ko-fi](https://ko-fi.com/borkdude)  
•	[Patreon](https://www.patreon.com/borkdude)  
•	[Clojurists Together](https://www.clojuriststogether.org/)  

If you're used to sponsoring through some other means which isn't listed above, please get in touch.  
On to the projects that I worked on in July!  

### July Updates
- **[clj-kondo](https://github.com/clj-kondo/clj-kondo):** static analyzer and linter for Clojure code that sparks joy.  
	* A big fat new release: 2023.07.23. Several new linting rules and lots of fixes. See [changelogs](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md#20230713) here.  
- **[http-client](https://github.com/babashka/http-client):** Babashka's http-client  
	* Added a websocket API, a fix for the :ssl-context {:insecure true} option and more. See [CHANGELOG](https://github.com/babashka/http-client/blob/main/CHANGELOG.md).  
- **[pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher):** babashka filewatcher pod  
	* The events emitted from the file watcher are now automatically deduplicated.
	* An aarch64 binary for Mac is now available Thanks to @fjsousa and @lispyclouds.  
- **[edamame](https://github.com/borkdude/edamame):** Configurable EDN/Clojure parser with location metadata  
	* A small bugfix release around reading malformed reader conditional expressions
- **[lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo):** a leiningen plugin for clj-kondo
	* This plugin now follows the version number of clj-kondo  
- **[squint](https://github.com/squint-cljs/squint):** CLJS syntax to JS compiler and [cherry](https://github.com/squint-cljs/cherry) Experimental ClojureScript to ES6 module compiler  
	* Add [defclass](https://github.com/squint-cljs/squint/blob/main/doc/defclass.md) in squint, inspired by shadow-cljs  
	* More work on getting squint and cherry to work in one build  
	* Provide UMD build which works better in Firefox Webworkers  
	* cherry can now be used in a playground at [livecodes.io](https://dev.livecodes.io/?template=clojurescript)  
	* Fix doseq and add doall and dorun in squint  
- **[lein2deps](https://github.com/borkdude/lein2deps):** leiningen to deps.edn converter  
	* Allow anonymous function literals in project.clj  
- **[babashka](https://github.com/babashka/babashka):** native, fast starting Clojure interpreter for scripting.  
	* Version 1.3.182 released, mostly library bumps and small bugfixes. See changelogs [here](https://github.com/babashka/babashka/blob/master/CHANGELOG.md#13182-2023-07-20).  
- **[deps.clj](https://github.com/borkdude/deps.clj):** A faithful port of the clojure CLI bash script to Clojure
	* More robust handling of downloading and unzipping tools jar
- **[scittle](https://github.com/babashka/scittle):** Execute Clojure(Script) directly from browser script tags via SCI  
	* Fixed a small bug with evaluating tags: when there would be whitespace + a "src" attribute, the whitespace would be executed and the attribute was ignored.
- **[tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild):**
	* This EXPERIMENTAL combo allows you to use tools.build from babashka. In this release a reflection issue was addressed.  
- **[nbb](https://github.com/babashka/nbb):** Scripting in Clojure on Node.js using SCI  
	* Add missing function to promesa  
- **[jet](https://github.com/borkdude/jet):** CLI to transform between JSON, EDN, YAML and Transit using Clojure    
	* Release version 0.7.27 (see [changelogs](https://github.com/borkdude/jet/blob/master/CHANGELOG.md#0727-2023-08-02)) with missing 1.11 functions and options for easier kebab/camel/etc. casing.  

**Other projects:**  
These are (some of the) other projects I'm involved with but little activity in July.  Click for more details:  
- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs  
	* Support :require-macros  
	* Introduce eval-string+ which received an optional initial :ns key and also returns the last active :ns so you can preserve the namespace state over multiple evaluations.  
	* Released v0.8.40  
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub- processes  
	* Implement :out :bytes to receive output as bytes (thanks Hans Bugge Grathwohl)  
	* Make :dir option accept java.nio.file.Path  
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3   
- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure   
Published: 2023-08-02  


### August Updates  
In August, my attention was mostly directed at the upcoming [Strange loop](https://www.thestrangeloop.com/2023/babashka-a-meta-circular-clojure-interpreter-for-the-command-line.html) talk. I'm very excited to be part of the last iteration of this conference. It will also be my first time flying to the USA!  

Rahul De and Anupriya Johari will be giving a workshop at JavaZone on Tuesday the 5th of September. Check the details [here](https://2023.javazone.no/program/19a5cab3-7afd-4dc1-b60a-bea8562d3186).  

Here are updates about the projects/libraries I've worked in August:    
- **[jet](https://github.com/borkdude/jet):** CLI to transform between JSON, EDN, YAML and Transit using Clojure  
	* Release version 0.7.27 (see [changelogs](https://github.com/borkdude/jet/blob/master/CHANGELOG.md#0727-2023-08-02)) with missing 1.11 functions and options for easier kebab/camel/etc. casing.  
- **[quickdoc](https://github.com/borkdude/quickdoc):** Quick and minimal API doc generation for Clojure  
	* No update in quickdoc, but happy to see that Github have resolved a bug on their side with local anchors in HTML, which quickdoc relies on
	* Require clojure 1.11 as the minimal clojure version  
- **[sci.configs](https://github.com/babashka/sci.configs):** A collection of ready to be used SCI configs.  
	* Worked together with [@niwinz](https://github.com/niwinz) to make sci.configs upgradable to promesa 10 and 11. Many thanks to Andrey for making promesa backward-compatible again, since sci.configs relies on Clojure libraries to be always upgradable without breaking changes.  
- **[nbb](https://github.com/babashka/nbb):** Scripting in Clojure on Node.js using SCI.
	* Bumped sci.configs and promesa  
- **[deps.clj](https://github.com/borkdude/deps.clj):** A faithful port of the clojure CLI bash script to Clojure    
	* The tools jar relocated to Github releases so deps.clj was updated to this new location, with backward compatibility.  
	* Per my request, Alex added a .sha256 file to Github releases so the downloaded jar file could be verified against corruption   
- **[SCI](https://github.com/babashka/sci):** Configurable Clojure/Script interpreter suitable for scripting and Clojure DSLs  
	* Clojure compatibility: allow (def foo/foo 1) in namespace foo  
	* Clojure compatibility: reset file metadata on var when it's re-evaluated from other file  
	* Add sci.async/eval-form and sci.async/eval-form+  
- **[babashka](https://github.com/babashka/babashka):** native, fast starting Clojure interpreter for scripting.  
	* expose sci.core in babashka  
	* Asahi linux support (linux on Apple m1/m2)  
	* Several other library upgrades and Clojure compatibility fixes  
	* Compatibility with the newest integrant version  
- **[pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3):** A babashka pod for interacting with sqlite3  
	* Upgrade sqlite version so it supports json fields  
- **[cherry](https://github.com/squint-cljs/cherry)**  
	* Add [defclass](https://github.com/squint-cljs/squint/blob/main/doc/defclass.md) to cherry (similar to squint)  
	* Expose clojure.string and clojure.walk namespaces  
	* Fix overriding core vars  
- **[clj-kondo](https://github.com/clj-kondo/clj-kondo):** static analyzer and linter for Clojure code that sparks joy.  
	* working towards a new release with a large number of small bug fixes, see upcoming [changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)
- **http-client](https://github.com/babashka/http-client):** babashka's http-client  
	* A number of small bugfixes and additions  
 
### Other projects  
These are (some of the) other projects I'm involved with but little to no activity happened in August. Click for more details.   
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod  
- [edamame](https://github.com/borkdude/edamame): Configurable EDN/Clojure parser with location metadata  
- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo): a leiningen plugin for clj-kondo  
- [squint](https://github.com/squint-cljs/squint): CLJS syntax to JS compiler and [cherry](https://github.com/squint-cljs/cherry) Experimental ClojureScript to ES6 module compi  
- [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter  
- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI  
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild)  

### Other projects  
These are (some of the) other projects I'm involved with but with little or no activity in July and August. Click for more details.  
- [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases  
- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!  
- [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI  
- [fs](https://github.com/babashka/fs) - File system utility library for Clojure
- 
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes  
- [babashka.book](https://github.com/babashka/book): Babashka manual  
- [instaparse-bb](https://github.com/babashka/instaparse-bb)  
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn  
- [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).  
- [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently  
- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects  
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars  
- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes  
- [quickblog](https://github.com/borkdude/quickblog): Light-weight static blog engine for Clojure and babashka  
- [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!  
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka  
- [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))  
- [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp  
- [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!  
- [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter  
- [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI  
Published: 2023-08-30  

Tagged: [clojure](https://blog.michielborkent.nl/tags/clojure.html) [oss updates](https://blog.michielborkent.nl/tags/oss-updates.html)  Discuss these posts [here](https://github.com/borkdude/blog/discussions/categories/posts).  To see previous [posts](https://blog.michielborkent.nl/tags/oss-updates.html) go here.<br>


---


## Clojar.org: Sean Corfield  

In my [previous Long-Term Funding update](https://corfield.org/blog/2023/06/30/long-term-funding-3/)
I said I would review/overhaul the "ecosystem" and "tutorials" sections.<!--more-->

### On a personal note...

I ended the previous update with a personal note but I'm going to start this
update with one. It's been a very difficult couple of months. My mother
passed away in early July (on my birthday!) and much of the month involved
a lot of back and forth with the funeral home in England and family around
the world. My wife & I attended the service via Zoom at the end of July and then
it took some additional time to get the service booklet and recording
distributed to family and friends.

Then in early August, both my wife and I got COVID-19 -- after three and a
half years of avoiding it, and having all our shots and boosters. My wife
tested positive soon after symptoms started and got paxlovid. I had all the
same symptoms too, but kept testing negative until I was outside the window for
paxlovid -- and then started testing positive. It took about two weeks for us
both to test negative reliably. We're both still recovering from the fatigue
and brain fog but every day is an improvement. Wear your masks, folks, and
get all your shots and boosters!

Consequently, I didn't get as much done as I'd hoped in the past two months.

### `clojure-doc.org`

I incorporated feedback from the community on the `tools.build` cookbook.
Many thanks, in particular, to [@phronmophobic](https://github.com/phronmophobic)
who provided extensive feedback and Pull Requests!

I reviewed the "ecosystem" and "tutorials" sections but only so far as to
remove outdated content. I reviewed and updated the main **Content** page
to reflect the current state of the site, reordering sections and removing
outdated content.

Feedback from the community suggested that I should
review sections in a different order to my initial plan,
so I turned my attention to the "language"
section and updated the following pages from Clojure 1.5 to Clojure 1.11:

* [Language: Functions](https://clojure-doc.org/articles/language/functions/)
* [Language: clojure.core](https://clojure-doc.org/articles/language/core_overview/)
* [Language: Collections and Sequences](https://clojure-doc.org/articles/language/collections_and_sequences/)
* [Language: Namespaces](https://clojure-doc.org/articles/language/namespaces/)
* [Language: Java Interop](https://clojure-doc.org/articles/language/interop/)
* [Language: Polymorphism](https://clojure-doc.org/articles/language/polymorphism/)

In some cases that just meant double-checking the code examples still worked
as shown, but in other cases it meant updating the examples to use newer
functions, adding new examples showing alternative approaches that are now
possible, or changing the error messages shown to match the current behavior
(since Clojure 1.10 did a lot to improve error messages).

I still need to make additional passes over several of these pages to address
the remaining "TBD" items (mostly adding more examples).

### HoneySQL  
Several complicated changes were made to HoneySQL this period, leading to the
[2.4.1066 release](https://github.com/seancorfield/honeysql/releases/tag/v2.4.1066),
which included the first pass at supporting temporal queries, and reworking
how `:insert-into`, `:columns`, and `:values` work together which should
make it easier to avoid generating invalid SQL as well as making it easier
to extend HoneySQL to support additional features around `INSERT` statements.

### Polylith  
The [Polylith project](https://github.com/polyfy/polylith)
(and documentation) was still using my old (archived) `build-clj`
wrapper so I worked on a Pull Request to switch everything to plain
`tools.build` usage as a better example for the community. That has been
merged in and updated documentation will be released soon (on cljdoc.org
instead of GitBook!).  

### `clj-new`  
This project also still used `build-clj` so I updated all the project
templates to use `tools.build` directly and released version
1.2.404 so that, going forward, newly-generated projects will be better
examples for the community. `deps-new` had previously been updated to
generate projects using `tools.build` directly.

### `clj-commons`  
Information about CLJ Commons governance was spread across the
[`clj-commons`](https://clj-commons.org) website and the
[`meta`](https://github.com/clj-commons/meta) repository's `README`
and other pages.
Based on some community feedback in June, I wanted to consolidate
that information and bring it up to date.  

As I started on that, I realized that the
[`clj-commons` project list](https://clj-commons.org/projects.html)
was very outdated so I decided to regenerate it (there's a
[Clojure script](https://github.com/clj-commons/clj-commons.github.io/blob/master/src/clj_commons/projects.clj)
for this). That uncovered a number of projects that were missing either the
`ORIGINATOR` file in the root of the repo (how `clj-commons` identifies the
original author of a project) or the `.github/CODEOWNERS` file that lists
the current active maintainers.

I went through every `clj-commons` repo and added the missing files,
updated the `projects.clj` script to support `#` comments in `CODEOWNERS`,
and regenerated the `projects.html` page.

Finally, I consolidated and updated the information about how CLJ Commons accepts and maintains
projects, and updated the `README` in the `meta` repo to reflect that this
information is all on the main website now:

* [Accepting projects into clj-commons](https://clj-commons.org/accepting-projects.html)
* [Maintaining projects in clj-commons](https://clj-commons.org/maintaining-projects.html)

## What's Next?

In September/October, I'm hoping to complete a review and update of the
"tutorials" and "ecosystem" sections of clojure-doc.org, and then in the
remaining period, I'll tackle the "cookbooks" section and make another
pass of "TBD" items in the "language" section.  

:tags "clojure" "clojure-doc.org" "honeysql" "clj-commons" "open source" "community" "clojurists together" "polylith"
<br>  

---


## Clojars: Toby Crawley  
                                                                                                                                                                         
**Commit Logs:** [`clojars-web`](https://github.com/clojars/clojars-web/compare/5fdef58b17d710fdf6b2ea886a520c84e45af624...0a5eb2175e7b417fc9e64bcb6fb87f6d15cbddbc), [`infrastructure`](https://github.com/clojars/infrastructure/compare/5d2811bdfd95cf1320af2a2bea2fed2ce0cf9b87...ad8335b312a81567a4c78ef4fe1587741794534c)
                                                                                                                                                                       
I took this month to clean up some long-standing issues:  
-   [Fixed an issue with error reporting from the repository routes](https://github.com/clojars/clojars-web/issues/659)
-   [Include release date for each version in feed.clj](https://github.com/clojars/clojars-web/issues/563)
-   [Include scm tag for each version in feed.clj](https://github.com/clojars/clojars-web/issues/564)
-   [Require MFA group wide to deploy](https://github.com/clojars/clojars-web/issues/823)

And a few quality-of-life improvements:  
-   [Add denylist to email sender](https://github.com/clojars/clojars-web/commit/0d33a469744f71aa965eac40c6a9cdebd44edefa)
-   [Migrate from project.clj to deps.edn](https://github.com/clojars/clojars-web/pull/872)
-   [Upgraded to latest clj-kondo for code linting](https://github.com/clojars/clojars-web/commit/0a5eb2175e7b417fc9e64bcb6fb87f6d15cbddbc)

The [CHANGELOG file](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org) also covers any user-facing changes.

Note: this report is also available on [tcrawley.org](https://tcrawley.org/clojars-worklog/2023/index.html#aug-2023) <br>

---


## ClojureDart: Christophe Grande

### ClojureDart
ClojureDart keeps to steadily get new users. [We got featured on a Youtube channel for Flutter devs](https://www.youtube.com/watch?v=ziPIzvA60co). Hopefully we increase the reach of Clojure both by allowing Clojurists to reach mobile/desktop but also by drawing more people to Clojure.

We are also glad to have received quality PRs adding missing functions or fixing divergences in the way things print.

We started working on a REPL (limited to Flutter apps for now), we expect to make it public in September.

The lack of multimethods begins to feel like a blocker for porting existing libs so it's another tppic we should address in the next two months/

Last, we keep publishing short videos on Youtube https://www.youtube.com/@clojuredart/shorts and we also started a newsletter on ClojureDart and nerdy stuff.

### Fixes
Several fixes to the compiler, cljd.core or flutter.cljd. Amongst them:  
* fix CI for Dart 2 which was broken by tests for records support (which are a Dart 3 feature)  
* allow destructuring in the arg list of protocol implementations  
* fix type-inference regression on a combination of `if` and `recur`  
* `*-array*` functions were not properly hinted  
* workaround for a Dart parser bug https://github.com/dart-lang/sdk/issues/52904  
* fix aliases in emitted code for when a protocol is extended from another namespace  
* fix hot reloading error when a protocol extension is removed -- this issue and the previous one were triggered by the REPL work  
* make `set!` more robust (the implementation was a bit hacky and didn't perform some checks)  
* `rseq` wasn't behaving properly on vectors whose size was a multiple of 32    
* some code was improperly optimized away when a `throw` appeared in some nested `if`s in statement position   
* widgets below a `:vsync` were not refresh on app state changes, only on animation updates    
* issues with `set!` expressions used in argument position   
* potential double evaluation when casting to a nullable type  
* ...  

### Improvements  
* refactoring of the Subscribable protocol in `cljd.flutter` (breaking change but almost no users had implemented it) to simplify the handling of default values by pushing the responsibility to the consumer rather than to the producer.  
* support of namespaced maps in the reader (the cljd one used at runtime, not the clj one used to read source).  
* `retriable`/`retry!`: `(retriable expr)` behaves a bit like `future`, except that:  
	* its body is the one of a `try`, you can have `catch`es and `finally`: `(retriable expr (catch Object o st ...))`
	* it can be retried by calling `retry!` on it -- returning a future which yields when the current retry is done (completed or failed).
  The intent is to allow to retry failed IO (or trigger refreshes) and be able to tie a spinner lifetime to the current try.  
* the compiler now watches for changes into `:local/root` deps to ease codevelopment of libs and apps.  

### Future Work  

### ClojureDart  
* Publish the Flutter-only REPL  
* Multi-methods to allow some libs to be ported  
* Look into porting Datascript and SCI to ClojureDart  
* New APIs to leverage our persistent data structures:  
	* maps (hash and sorted) in ClojureDart are original implementations (not the same as CLJ/CLJS)  
        * hash maps could be seen as another refinement of the original, sorted maps constitute a novel implementation.  
	* Sorted colls should be good enough for direct use by Datascript.  
	* Both hash and sorted maps can support accelerated merge/diff/join/etc. operations.  
* `cljd` CLI written in `cljd` for easier project creation etc.  
* gen tests  
* …<br>   


---

## Clojure-lsp: Eric Dallo  

During this year I've been working on clojure-lsp support for IntelliJ, it was the only mainstream editor that didn't have a good way to use clojure-lsp features, it was really hard to integrate with the LSP protocol because of Intellij's API but making the plugin in Clojure helped a little bit with that, so after a lot of work, I'm excited and happy to announce [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij), a new plugin for IntelliJ to have all clojure-lsp features available!<br>

![eric dallo image](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/efebc614-912d-4f69-b60d-d073154f134c)


### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)

### 0.1.0 - 0.9.0  
- Avoid noisy exception after startup  
- Add `textDocument/codeAction` support. #3  
- Add support for refactorings via workspace/executeCommand. #4  
- Add support for textDocument/rename feature. #6  
- Add support for workspace/applyEdit. #7  
- Add support for LSP notification window/showMessage and request window/showMessageRequest.  
- Improve status bar to show icon instead of text.  
- Add troubleshooting section to 'Tools > Clojure LSP'  
- Add brace matcher for `[]`, `{}` and `()`  
- Fix completion of items with `/`  
- Add support for comments.    
- Add support for quote handlers.  
- Add support for completion. #2  
- Support find defintion of external dependencies. #1  
- Fix LSP startup messages to properly mention the task being done  
- Require plugin restart after install because of Clojure load in Classloader.  
- Support more intellij versions until 2021.3  
- Improvements to plugin compatibility  
- Support `initialize` and subsequent requests.  
- Support `textDocument/didChange`, `textDocument/didClose`, `textDocument/didOpen`.  
- Support `textDocument/hover`.  
- Support `textDocument/references`.  
- Support `textDocument/formatting`.  
- Support `textDocument/codeLens` and `codeLens/resolve`.  
- Add status bar with support for restarting server.    

### [clojure-lsp](https://clojure-lsp.io/)  
The main highlight are performance and memory improvement, also we had formatting improvements to match latest cljfmt features.

### 2023.08.06-00.28.06

**General**
- Fix truncation of namespaced keywords #1640  
- Add rewrite-clj node to cursor-info. - Fixing semantic-tokens, collons not managed by lsp anymore. #1550  
- Fix `:paths-ignore-regex` setting to consider settings reload 
- Bump clj-kondo to `2023.07.14-20230717.090255-3`. #1624  
- Fix inconsistencies with `:defined-by->lint-as`  
- Improve memory usage during cache save, avoiding "Out of memory" exceptions.  
- Prevent file rename when a namespace is defined in multiple files #1574  
- Fix user formatting setting being override by :style/indent metadata in macros  
- Bump cljfmt to `0.11.2`. #1634  
- Bump lsp4clj to `1.8.1`  

**Editor**  
- Avoid returning all known keywords on empty keywords completion for performance reasons.  
  
**API/CLI**  
- Improve mem/cpu usage using less analysis for tasks.<br>  


---


## Humble UI: Nikita Prokopov  

Last two months have been DataScript-focused. I’ve implemented a major new feature: pluggable durable storages. I’ve also wrote SQL adapter for them and migrated Grumpy Website to DataScript + SQLite to battle-test the implementation.

It has been working well so far (~2 weeks, lots of updates).

### [DataScript](https://github.com/tonsky/datascript):**  
- New incremental/lazy storage implementation. Store large databases on disk/in other DBs efficiently, load back only the parts you actually use, all completely transparent for user. It even has docs!  
- [New SQL storage implementations](https://github.com/tonsky/datascript-storage-sql), supporting MySQL, PostgreSQL, H2 and SQLite.  
- Schema altering (compatible changes only) via `d/reset-schema!` or `d/with-schema`  
- JVM: `branching-factor` can now be set per-database  
- New API: `d/find-datom`. Works like `d/datoms`, but only returns single datom and is faster than `(first (d/datoms ...))`  
- CLJS: Optimized various parts of CLJS version related to compilation and index access  
- Do not throw from `d/touch` when finding hanging refs  

### [Grumpy Website](https://github.com/tonsky/grumpy/):**  
- Migrate from storing posts in EDN to storing them in DataScript + persist in SQLite storage  
- Battle-test DataScript storage  
- Migrate from Component to Mount  
- Implement full-text search, powered by Lucene  
- Cross-post to Mastodon  
- Highlight mentions and tags in posts  
- Pagination, suggest, subscribe and about pages  
- Accumulate and render [statistics](https://mastodon.online/@nikitonsky/110980158381537112)  

### [Skija](https://github.com/HumbleUI/Skija/):  
- Catch up with current Skia version (m109 → m116, ~year of updates)  
- Extract library file atomically #54 #56 w/ @dzaima  

### [Humble UI](https://github.com/HumbleUI/HumbleUI):  
- OkLCH example  
- Fixed NPE in text-field undo #80  
- Non-macos Ctrl + X/C/V/Y #81  

### [Sublime-Executor](https://github.com/tonsky/Sublime-Executor/):  
- Kill running process on Sublime exit  
- Strip away escape sequences from output  
- Better gitignore matching  
- Allow running any command on top of another one, implicitly killing previous one  

### [Clojure Sublimed](https://github.com/tonsky/Clojure-Sublimed):  
- Show file/line/column information when `clojure_sublimed_eval_code` fails  

### New project — [Dark Mode Toggle](https://github.com/tonsky/DarkModeToggle):  
- A simple menubar utility to quickly switch between dark and light modes <br>  

---

## Malli: Tommi Reiman  

I was off the grid on July, back to OS on August.

### Malli  
* Continued with the [new effective type system](https://github.com/metosin/malli/issues/264) - WIP  
* Small fixes and reviews and released 0.12.0:  

#### 0.12.0 (2023-08-31)  
* FIX: retain order with `:catn` unparse, fixes [#925](https://github.com/metosin/malli/issues/925)  
* **BREAKING**: Do not require timezone data directly for cljs [#898](https://github.com/metosin/malli/pull/898) with `malli.experimental.time`  
* Remove non-root swagger definitions [#900](https://github.com/metosin/malli/pull/900)  
* FIX: `malli.core/-comp` keeps interceptor order with long chains [#905](https://github.com/metosin/malli/pull/905)  
* FIX: `malli.dev/start!` exception does not contain source [#896](https://github.com/metosin/malli/issues/896)  
* FIX: don't add extra :schema nil to swagger :parameters [#939](https://github.com/metosin/malli/pull/939)  
* Add `:gen/return` support in malli.generator [#933](https://github.com/metosin/malli/pull/933)  
* Make uuid transformer to be case insensitive [#929](https://github.com/metosin/malli/pull/929)  
* Add `:default/fn` prop for default-value-transformer [#927](https://github.com/metosin/malli/pull/927)  
* Updated dependencies:  
```clojure
borkdude/edamame 1.3.20 -> 1.3.23
```

### Reitit  
* OpenApi3-support ended to be a a much bigger change than anticipated, still alpha  
* Got the [big refactor](https://github.com/metosin/reitit/pull/628) done  
* Paired with [Joel](https://github.com/opqdonut) on remaining issues & reviewed lot of issues, soon....  

### Spec-tools  
* First new release in 30 months! [0.10.6](https://github.com/metosin/spec-tools/blob/master/CHANGELOG.md#0106-2023-08-28)  

### Something else  
The coffee store is closed-look
![image](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/2ed1848f-be0c-4a6f-94a3-14129c2601a7)

---

## Shadow-cljs: Thomas Heller  
Time was mostly spent on doing maintenance work and some bugfixes. As well as helping people out via the typical channels (eg. Clojurians Slack).  

Current shadow-cljs version: 2.52.3 [Changelog](https://github.com/thheller/shadow-cljs/blob/master/CHANGELOG.md)  

### Blog Posts  
Wrote a blog series about CLJS Frontend development alternatives to full Single Page Apps.  
- [The Lost Arts of CLJS Frontend](https://code.thheller.com/blog/shadow-cljs/2023/07/13/the-lost-arts-of-cljs-frontend.html)  
- [Applying the Art of CLJS Frontend](https://code.thheller.com/blog/shadow-cljs/2023/07/16/applying-the-art-of-cljs-frontend.html)  
- [Mastering the Art of CLJS Frontend](https://code.thheller.com/blog/shadow-cljs/2023/07/18/mastering-the-art-of-cljs-frontend.html)  
- [shadow-graft: A Case Study](https://code.thheller.com/blog/shadow-cljs/2023/07/21/shadow-graft-a-case-study.html)  

### Notable Updates  
- Replaced the babel-js based ES Module -> CommonJS rewriter, which is used for ESM based npm modules. This had been [planned for a long time](https://clojureverse.org/t/shadow-cljs-2-25-2-looking-for-testers/10206) and was the only remaining part of shadow-cljs that actually required `node` at runtime. Now this is done entirely within the JVM, which as a side benefit got a bit faster. The old behavior is still available in case problems arise, but will be removed in the future.   

















