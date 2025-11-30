---
title: "Vote on 2026 Annual Funding"
date: 2025-11-30T14:00:00+12:00
author: Kathy Davis
summary: "It Is Up To You to Decide Who Will Receive $18K per Year"  
draft: True


---

Greetings Clojurists Together Members! Last year, based on your feedback, we tried a new process to identify annual funding recipients.  It was a vast improvement from years past, so this year we are using it again. 

We put out the call  - and we received 15 applications for you to consider. The board reviewed and voted to move all 15 to the 2026 Ranked Vote ballot. 

Now it is up to you, the members, to select the 5 developers who will each receive $1,500 monthly stipends (for a total of $18K USD). **Be on the lookout for an email that contains the link to your Ranked Vote ballot.** Deadline for voting is **12 December 2025** midnight Pacific Time. 

You can review past long-term project updates on our [website](https://www.clojuriststogether.org/news/) to get an idea of what past annual (or long-term) funding recipients have been able to accomplish with your support (pretty amazing!)

Thanks again to all the members for making this happen. We know from our surveys that you and the larger Clojurist Community that receive our project updates refer to this work on a regular basis - 83% daily or several times a week! This is a testament to its importance of these projects. 


### Deadline for your vote to be counted: December 12, 2025 Midnight Pacific Time  

Here we go ..in alphabetical order (by last name)....  
[Bozhidar Batsov](#bozhidar-batsov)   
[Adrian Bendel](#adrian-bendel)   
[Michiel Borkent](#michiel-borkent)  
[Clojure Camp (Rafal Dittwald, Jordan Miller, and others)](#clojure-camp-rafal-dittwald-jordan-miller-and-others)  
[Thomas Cothran](#thomas-cothran)  
[Alex Coyle](#alex-coyle)  
[Jeremiah Coyle](#jeremiah-coyle)  
[Eric Dallo](#eric-dallo)  
[Dragan Djuric](#dragan-djuric)   
[Siavash Mohammady](#siavash-mohammady)   
[Nikita Prokopov](#nikita-prokopov)   
[Tomasz Sulej](#tomasz-sulej)   
[Peter Taoussanis](#peter-taoussanis)  
[Jeaye Wilkerson](#jeaye-wilkerson)  
[Oleksandr Yakushev](#oleksandr-yakushev)   <br>

---

### Bozhidar Batsov  
https://github.com/bbatsov


**What do you plan to work on throughout the year?**  
Continuously improving CIDER, nREPL, clojure-ts-mode and their related projects. Building a formal nREPL specification is one of the bigger challenges I hope to tackle next year. As is fully decoupling clojure-mode from CIDER.  

Most of the projects are hosted under the following GitHub orgs:  

- https://github.com/clojure-emacs  
- https://github.com/nrepl  

**Why is this work important to the Clojure community?**  
CIDER and nREPL are two of the pillars of the Clojure community and are widely used by Clojure developers. The libraries developed originally for CIDER (cider-nrepl, refactor-nrepl, orchard, etc) are used by many other development tools as well.  <br>

---


### Adrian Bendel   
https://github.com/onbreath  

**What do you plan to work on throughout the year?**  

[**Clay**](https://github.com/scicloj/clay)   

Recently I've started to collaborate with people in the Scicloj-community on extending and improving Clay. Clay is superficially similar to other notebook environments, literate programming and visualization systems, but unique in that it acts as a companion to the REPL, situating itself in the workflow we're used to.  

We started work on features that were on halt for quite a while. For now this is primarily about extending Clay to be able to integrate with other runtimes, where we're using Babashka for a prototype. This is still in an exploratory and design phase, though we already have Clay working in Babashka, there is testing and backwards compatibility to ensure. Further, just building whole notebooks in different Clojure dialects is not necessarily the end goal here, because it is much more interesting to use the large ecosystem of the JVM and Clojure proper to integrate and work with data from other runtimes and their Clojure dialects.  

Overall this work is primarily to prepare for integrating with Jank, as soon as that is feasible. There are lots of C++-projects in scientific computing, machine learning and similar fields. Being able to interface with them directly, in a REPL integrated notebook environment, would potentially help a great deal in advancing Clojure's use in those fields.  Other Clojure dialects are of interest as well, but have a lower priority for now.  

The second area that needs work is integrating Clay more with the different Clojure programming environments. While solutions for integration with Emacs, Calva, Conjure and Cursive exist, the error reporting of Clay should be improved and better integrated with the development environments where possible. Since some of the primary work above, with different runtimes, already encompasses possible nREPL-based integration, this could also be leveraged for improved error reporting in packages/extensions.  

My great hope for Clay is that its use in Civitas (see link below), a community and publishing platform for articles, books, tutorials etc. on Clojure will, as its developer Timothy Pratley intended, further the exchange of ideas in the Clojure community and beyond.  

On Civitas there are also great opportunities to extend the Clay rendering system to support different kinds of Clojure code visualization. With Stratify (see below), clj-kondo and Flowstorm among many others, we have great sources for code structure, metrics, analysis and traces of our program. In many cases those tools are not used, only used very situated or are vendor locked, as Stratify's DGML based code structure graphs, which can only be viewed properly in Visual Studio on Windows.  

At the same time, we have a lot of the tools needed to mold this information into polished code quality reports, internal documentation and similar. A common complaint about Clojure is its dynamic nature and dense, opaque code structure. But what if we leveraged readily available technology to introspect and visualize our programs?  I want to approach this topic by writing articles on the work we do on Clay, in Clay, publishing to Civitas, showcasing Clay's architecture and operation. This should exemplify some of the tools available in everyday work with Clojure. It is also an opportunity to develop a library that uses readily available automatic graph layout engines to render DGML to SVG, to visualize and possibly interact with code structure. Other visualizations necessary for the reporting mentioned before are also of interest.  

I know, this seems very broad, but I rely on and deeply appreciate the collaboration with the other maintainers at Scicloj, to keep focus and set priorities as they seem feasible and necessary.  

https://scicloj.github.io  
https://clojurecivitas.github.io  
https://github.com/dundalek/stratify  


**Why is this work important to the Clojure community?**  
Integrating Clay with Clojure dialects on runtimes that are used in scientific computing etc.
Showcasing and extending the ecosystem of tools to make sense (and images!) of all our work.

**Is there anything else you would like us to know?**   
I have another, older GitHub account that shows I've been around for a while, though not as a prolific open source contributor, but [great explorer:](https://github.com/abp)  <br>

---


### Michiel Borkent   
https://github.com/borkdude  

**What do you plan to work on throughout the year?**  
My main projects are clj-kondo, babashka / SCI, and squint/cherry.  

Links:  
https://github.com/clj-kondo/clj-kondo  
https://github.com/babashka/babashka  
https://github.com/babashka/sci  
https://github.com/squint-cljs/squint  

I have identified the following long-term goals that I want to focus on in 2026:  
- Clj-kondo: run macros directly from source code  
- Clj-kondo: run exported configs/hooks directly from classpath (instead of having to copy files to a local dir)  
- Squint/Cherry: browser nREPL support  
- Squint/Cherry: source maps  
- Squint: protocolize coll functions so you can extend them to e.g. ImmutableJS or other custom collections  
- Scittle2 (working name): better/faster/smaller version of Scittle using Cherry (in-browser CLJS compiler)  
- Babashka: support CIDER middleware from source directly in bb  
- Babashka: distinguished parallel task output (e.g. colors or prefix)  
- Clj-kondo: add first-class support for Clojure dialects like ClojureDart and Jank  
- Clojure CLI: help improve UX via a new tools working group  
- Clj-kondo: performance improvements for bigger projects  

Apart from these long-term goals, there are plenty of maintenance-type issues to work on. Babashka consists of several libraries like SCI, fs, process, deps.clj, http-client and edamame that benefit not only babashka, but also the wider Clojure ecosystem. These libraries all need some love from time to time. There are several ideas to improve clj-kondo with more linter rules as well.  

**Why is this work important to the Clojure community?**  
Clj-kondo and babashka are used by a large portion of the community and available in popular editors like Calva and Cursive. I think it's fair to say they are established projects and lots of people rely on them from day to day.  

Babashka is used by 90% of Clojure Survey respondents who answered the question "What Clojure dialects do you use", as shown in Alex Miller's 2024 Conj talk. Babashka and clj-kondo have 4.4k and 1.8k stars on GitHub respectively. Their channels on Clojurians Slack have almost 1900 + 1000 users.  

**Is there anything else you would like us to know?**   
Thank you so much to Clojurists Together for keeping the Clojure OSS ecosystem sustainable!  <br>

---


### Clojure Camp (Rafal Dittwald, Jordan Miller, and others)  
https://clojure.camp  

**What do you plan to work on throughout the year?**  

**Continue various existing Clojure Camp projects:**   
- supporting new developers to attend Conj and other Clojure conferences (travel bursaries, and or/ organizing and subsidizing shared accommodation)  
- continue our peer-to-peer pairing program, with a new goal to also appeal to existing Clojurians  
- continue offering weekly online vents (mobs, office hours, etc)  
- improve communication of our offerings (by regularly posting)  

**Explore new Clojure Camp projects:**  
- develop our "curriculum map" into a learning roadmap and badge system  
- facilitate a mentorship program, pairing Clojure OSS devs with other devs (junior and senior)  
-  experiment with hosting "micro-conjs" (6-10 person, in-person weekend coding retreats)  

**Why is this work important to the Clojure community?**  
Clojure Camp aims to help new Clojurians integrate into the Clojure community, particularly those who may be under-served by existing self-directed methods. We'd like to be the place for existing Clojurians to send anyone struggling with getting started with Clojure. Our current approach is to foster a welcoming community and provide educational scaffolding (pairing, events, resource lists, roadmap). Going forward, we hope to focus more on "connection": creating more opportunities for new Clojurians and existing Clojurians to interact and work together.  

**Is there anything else you would like us to know?**   
Clojure Camp is now an incorporated non-profit (in Ontario, Canada). Ideally, this funding would go to the organization, not to an individual.  <br>

---


### Thomas Cothran   
https://github.com/thomascothran 

**What do you plan to work on throughout the year?**  
I plan to work on [pavlov:](https://github.com/thomascothran/pavlov)    

Pavlov is a functional implementation of behavioral programming, which is a programming paradigm invented by David Harel, the creator of statecharts. This [ACM article is a good introduction to the idea:]( https://cacm.acm.org/research/behavioral-programming/) 

Pavlov is currently in alpha status. It is well tested (in Clojure) and has been reliable in production use. It has a number of useful features on top of the behavioral programming core:  
1. A model checker that verifies safety properties (though not yet liveness properties)  
2. [Program states can be programmatically navigated using datafy and nav](https://github.com/thomascothran/pavlov/blob/master/doc/navigating-bprograms.md)   
3. [A portal-based program explorer, where you can point and click through program execution paths (including time travel)](https://github.com/thomascothran/pavlov/blob/master/doc/designing-business-programs-with-behavioral-threads.md#pavlov-program-explorer)  

**However, there are a few things I'd like to do to get it to a stable release:**
1. Better testing with Clojurescript. While the Clojure test suite is comprehensive, the Clojurescript tests do not provide as much coverage.  
2. Recipes document showing how to solve common problems with pavlov.   
3. LLM instructions. Context7 has not been adequate in production, and LLMs need a bit more guidance.  

**After the stable release, I would like to work on:**   
1. A more robust program explorer. The portal-based one uses the `nav` support. However, it is clunky and it doesn't visualize the program execution paths in a single view as a graph.  
2. Durable execution. Pavlov's behavioral programming implementation is designed to be fully serialized. Durable execution is not yet implemented.  
3. Demo applications. Since behavioral programming is not a well-known paradigm, demo applications will be useful to help the community understand it.  


**Why is this work important to the Clojure community?**  
Behavioral programming is, I believe, uniquely suited to Clojure. In part, this is because behavioral programming is, in principle, very loosely coupled. At the same time, it enables developer tooling that makes it easy to use and test.  

But Clojure's functional and data-oriented approach solves a lot of problems with implementing behavioral programs. For example, the canonical implementation on the JVM relies on serializing threads. With Clojure, we represent behavioral threads as data. This opens the door to a lot of nice tooling, such as program explorer GUIs and durable execution.  

I previously wrote an article expanding on this question [here:](https://thomascothran.tech/2024/10/a-new-paradigm/).  <br>

---


### Alex Coyle  
 https://github.com/alza-bitz  

**What do you plan to work on throughout the year?**  
My [Clojure API for Snowflake:](https://github.com/alza-bitz/snowpark-clj)  

This project is non-commercial and open-source. I planned to develop and test it in collaboration with academics, data scientists and others in the Clojure community, in order to help solve real-world data challenges.  

The aims are as stated in the readme, with specific focus on extending the [API coverage](https://github.com/alza-bitz/snowpark-clj/blob/main/doc%2Fapi-coverage.md) and adding [features that are planned but not yet elaborated](https://github.com/alza-bitz/snowpark-clj/blob/main/.github/instructions/problem_statement_and_requirements.instructions.md#features-that-are-planned-but-have-not-yet-been-elaborated)  

Note: the build is only failing because my Snowflake license has expired.  


**Why is this work important to the Clojure community?**  
I recently wrote on Civitas concerning the extent of Clojure support for the popular on-cluster data tools and why it's important. In the article I also introduced my new Clojure API for [Snowflake:](https://clojurecivitas.github.io/data_engineering/support_for_popular_data_tools/snowflake.html)  

I believe these efforts are beneficial to Clojure because it puts the language in front of a different community and makes it more accessible in a space where it doesn't really have any visibility at the moment.  

**Is there anything else you would like us to know?**   
This proposal isn't strictly limited to Snowflake, the scope could most definitely include other on-cluster tools that I write about in my article. For example, I identified some key fixes and improvements for using Spark from Clojure, either to be made in the existing Geni library or in a completely new library:  
1. Supporting [Spark Connect:](https://spark.apache.org/docs/latest/spark-connect-overview.html). This would reduce the complexity of using Spark SQL & Dataframe APIs directly from the Clojure REPL and also align with client usage for GoLang, Python, etc. I already did [some investigation on this:](https://github.com/zero-one-group/geni/issues/345)  
2. Supporting [Databricks:](https://github.com/zero-one-group/geni/issues/356)   <br>

---


### Jeremiah Coyle  
 https://github.com/paintparty  

**What do you plan to work on throughout the year?**  
These are the 5 projects that I have been working on in 2025, and plan to continue working on in 2026. All are fairly mature alpha-stage projects.

**Project #1 Bling**  
[Bling provides hifi console printing for Clojure, ClojureScript, and Babashka.](https://github.com/paintparty/bling)  
Thanks to the generous support from Clojurists Together, I'm happy to report that a great deal of progress was made in Q2 of 2025. Building on the momentum, I would love to continue to grow and improvement the project going forward.  

The 2026 Roadmap for Bling can be found [here:](https://github.com/paintparty/bling/blob/main/resources/docs/Bling-2026-roadmap.md)  


**Project #2 - Kushi**  
Kushi is UI design library for ClojureScript  
[Source repo:](https://github.com/kushidesign/kushi)   
[Interactive docs:](https://kushi.design/)    

Progress in 2025:  
Thanks to the generous support of an experimental projects grant from Clojurists Together in Q3 of 2024, great progress was made towards the goal of delivering a rich, first class, Clojure(Script)-native UI framework for building and evolving web projects. That momentum was leveraged to maintain an increased focus on the project in Q1 of 2025, with an emphasis on refining the architecture and performance of the build system. In q2, I updated the design for the project splash page. I resumed work on the project in Q3 & Q4, concentrating on the Malli-driven runtime safety / warning system, as well as rethinking and implementing an improved shared property system for styling of the library of stock UI components.   

**Goals for Kushi in 2026:**  
1) Continued refinement of the build system. This part of the project has recently been re-written and evolved to efficiently precompile all css via source analyzation at build-time. The build system currently assumes shadow-cljs. I would like to keep it moving in the direction it is currently headed, towards a completely standalone side-process.  
2) The styling story is very much evolving in a direction which promotes the tweaking of UI via making adjustments to Kushi’s well-thought-out design token system. In concert with the pre-built ui components, this means there is a reduced need for CSS and style syntax within components themselves.  
3) Like most UI libs, Kushi is designed to be agnostic with regard to the underlying rendering framework. I am currently working towards providing a comprehensive quickstart kitchen-sink project for both Reagent and Uix.  
4) Add more functionality, docs, and examples for working with the `kushi.playground` namespace. The playground gives the user an elegant way to build their own white-labeled interactive documentation site that features Kushi base components in addition to any custom components specific to their project. It is similar to Storybook.js or Portfolio (cjohansen/portfolio). The playground namespace was used to create the current project website at kushi.design. I would like to add a separate section for configurable cut-and-paste Design Blocks. Design Blocks are like recipes for composing library UI primitives into common UI patterns. Many of these could be transpiled from existing open-source examples in mainstream JS-land. I think this could offer tremendous value when combined with the above mentioned universal component transpiler.  
5) Design and implement 4-5 global themes, using a variety of styles, and incorporate them via a theme switcher into the kushi.design site. I think this would open up a lot of possibilities for users. I am confident that the implementation of this will pave the way for a next-level experience of pre-tuning a design system. On any given team, something like this this could be used in collaboration with a non-technical designer to save weeks of time.   
6) Add additional ui library components. Components followed by an asterisk are internally complete and just need to be turned into public functions with documentation:  
    - Avatar*  
    - Tabs*  
    - Blockquote  
    - Treeviewer  
    - Carousel  
    - Dropdown menu  
    - Context menu (right-click)  
    - Table  
    - Data List  
    - More card layouts (with inset margins)  
    - Keyboard input / hotkey e.g. `⌘ C` styled as button  
    - Aspect Ratio (displays content within a desired ratio)  
    - Wrapper for native color picker input  
7) Browser-based interactive design tweaking, with round trip to file system. An initial spike was prototyped last year, and I have since been wanting to revisit the idea. Even a simple version of this that worked reliably could massively reduce iteration cycles when implementing a dense, highly-polished UI.  


**Project #3 - Fireworks**  
[Fireworks is a themeable tapping library and color pretty-printing engine.](https://github.com/paintparty/fireworks)  

Thanks to the generous support from Clojurists Together, I'm happy to report that a great deal of progress was made in Q3 of 2025. Building on the momentum, I would love to continue to grow and improve the project going forward.  

Goals for Fireworks in 2026:  
- Address all current issues (mostly enhancements)  
- Editor extensions/integrations have been created for Cursive and VSCode. Initial work has commenced on an Emacs integration. I would like to turn the existing VSCode Joyride user script into an actual VSCode extension, in order to provide an even smoother beginner experience.  
	- https://github.com/paintparty/fireworks/blob/main/docs/editors/cursive/cursive.md  
	- https://github.com/paintparty/fireworks/blob/main/docs/editors/vscode/vscode.md  
- Produce written and/or video documentation of my current live hot-reloading dev environment for JVM Clojure, with versions for both Leiningen and Deps. I recently got a PR merged that adds this to [test-refresh](https://github.com/jakemcc/test-refresh/pull/91).   
- For ClojureScript developers using Fireworks in a browser dev console, I made a dedicated Chrome extension to enable the setting of the Chrome DevTools console background and foreground color with a very nice GUI interface. Would be cool to get updated and also work in most other [Chromium-based browsers, and potentially Firefox, if there is any demand for it.](https://github.com/paintparty/fireworks?  tab=readme-ov-file#setting-the-background-color-and-font-in-chrome-devtools-clojurescript)    


**Project #4 - Domo**  
[Domo is a modern ClojureScript DOM utility library.](https://github.com/kushidesign/domo)    

Goals for Domo in 2026:  
- Write quality docstrings with examples for all 82 public functions.  
- Add simple validation and dev-time warnings for all public functions (very useful when working with the DOM). Make sure this code is elided for production builds so as not to add extra weight  
- Continue to add useful utility functions as the need for them arises within the context of real-world project work.  
- Add more tests to the existing browser test suite at in `starter.browser.browser-tests.cljs` namespace.  
- The `starter.browser` namespace in the examples/browser dir currently serves as a quickstart and (exhaustive) API tour. I would like to get user feedback on this to determine its effectiveness/usefulness.    
- Many of the core functions should “just work” as they are written in the context of a Squint project. I would like to make most, if not all of the public API available in Squint-land.   

**Project #5 Lasertag**   
[Lasertag is a utility for categorizing types of values in Clojure, ClojureScript, and Babaskha.](https://github.com/paintparty/lasertag)    

Goals for Lasertag in 2026:  
- Solidify current public API  
- Extensive coverage for all Java and Javascript types/classes  
- Extensive test suite for all types and classes  
- Make cool demo for usage in ClojureScript   

 

**Why is this work important to the Clojure community?**  
I believe work on these projects could benefit the Clojure community for the following reasons (listed out project-by-project):  

[**#1 - Bling**](https://github.com/paintparty/bling)    

Many mature language communities have a library for rich text printing in the terminal, for example Rich (Python), Chalk (JS), LipGloss (Go), etc.  

With Clojure, the main existing option seems to be org.clj-commons/pretty. The original impetus for creating Bling came out of my experience of trying to use Pretty extensively for formatting error and warning messages.  

The most unique thing that Bling offers is a carefully curated palette of 11 basic colors which are readable on both light and dark backgrounds. Because these colors are located in the 16-255 ansi range, they are guaranteed to look the same on almost any terminal, regardless of the user's terminal theme. Other libraries, including Pretty, leave the colorizing up to the user's terminal emulator profile/theme, which often leads to wildly different colorized output in the user space.  

Bling also offers two simple functions for creating "blocks" of text in the console. Check out the readme for exhaustive visual examples - These can be semantically colored (errors, warnings, info). Bling also offers a simple function for constructing a point-of-interest diagram, like when you want to call out the namespace, line, column and show some source code with a red squiggly underline. I think the Clojure community would benefit from more library authors adding neatly-formatted, actionable warning and error messages to their codebases. It would also be great if such warnings and messages where formatted in a way that loosely followed some set of conventions. Perhaps Bling could play a small role in fostering this within the Clojure community.  


[**#2 - Kushi**](https://kushi.design/)    
Kushi aims to provide a complete solution to the design layer of web UI projects.
It is an ambitious project, but I believe there is an opportunity for Kushi to offer a UI dev experience that is unique and more compelling than any equivalent in any other language. This could lead to increased organizational awareness and consideration of ClojureScript as an attractive choice for building mission-critical UI. If more companies used ClojureScript to build UI, I think it would be very beneficial for the community.  

[**#3 - Fireworks**](https://github.com/paintparty/fireworks)    
Fireworks is the only lib to provide a colorizing and themeable pretty-printer that works beautifully in both Clojure and ClojureScript (and Babashka).  

The output is, arguably, orders of magnitude faster and easier to read than equivalent output from clojure.pprint/pprint. This is especially true in the case of maps with data-structures as keys, or any kind of collection that features metadata.  

The library also provides very powerful debugging and tapping macros. These macros provide a lightweight complement to discovery-centric, UI-heavy data exploration tools such as Portal and Flowstorm. With a simple hot-reloading setup in clj or cljs, and minimal editor integration (now available in Cursive and VSCode), Fireworks can drive an extremely compelling live-feedback dev experience without any reliance on a repl connection, or repl-related concepts. I think the continued codification, documentation, and demonstration of such a workflow could make a difference in the on-ramping of newcomers to Clojure. The maturation of this kind of workflow could also benefit existing users of similar community tools such as playback, debux, hashp, telemere, ken, spyscope, omni-trace, postmortem, and sayid.  
 

[**#4 - Domo**](https://github.com/kushidesign/domo)    
This lib has been slow-cooking over a 3 years to serve various web projects, including Kushi.
The syntax is very Clojure-y, and feels much nicer than writing gnarly JS interop code.
To my knowledge, there are only a few ClojureScript DOM libs (Dommy, Enlive, Enfocus), and all were written 9-10 years ago.  

Compared to these libs, Domo offers even more specific functionality across 83 public functions and macros such as:  
- copy-to-clipboard  
- Getting viewport information  
- Geometry for elements  
- Getting screen quadrant for element or event  
- Attribute querying and manipulation   
- Computed styles of elements  
- Adding, removing, and toggling attributes and classes  
- Optional zipper-like syntax for selecting elements  
- matches-media? helpers  
- a11y-friendly on-mouse-down helper (zippier alternative to on-click)  
- Helper for keyboard-based tab navigation  
- & much, much more!  

[**#5 - Lasertag**](https://github.com/paintparty/lasertag)    
Lasertag is a utility library that was spun out of Fireworks. I believe it to be quite unique in the current landscape of cljc libraries that deal with reflection and categorization of values.  

lasertag.core/tag-map makes it easy to get detailed information about values, particularly in the context of interop with Java or JavaScript.  

I think that Lasertag could potentially offer great utility to rich-tooling projects developed in the Clojure community of the present and future.    


**Is there anything else you would like us to know?**   
Very much appreciate the recent support from Clojurists Together for these projects, would love to keep it going!  <br>

---


### Eric Dallo  
ericdallo  

**What do you plan to work on throughout the year?**  
[**ECA (Editor Code Assistant)**](https://github.com/editor-code-assistant/eca)    
This project was born 5 months ago (with clj-together's help!) and it's growing, there are so many improvements to this project related to features and parity with other big tools like Claude/Cursor, compatibility with more editors, fix and support community issues. 
This AI tool is written in Clojure but works in any language which is huge for discoverability of Clojure community outside Clojure bubble, it has a lot of potential in my opinion, and projects for each editor to maintain and evolve.  

[**Clojure-lsp**](https://github.com/clojure-lsp/clojure-lsp)    
I want to work on custom code actions, support for new clojure.java.javadoc lib, improve java interop support like go to class locations, methods etc, lots of this would require me working in clj-kondo too  

[**metrepl**](https://github.com/ericdallo/metrepl)  
I want to help with guide and examples of metrics of your repl and how that can help companies understand developers REPL usage  

**Why is this work important to the Clojure community?**  
I believe those are really import tools for the developer, especially clojure-lsp and ECA which run in all major editors during daily programming. In my opinion, developer tools for programming are critical for user happiness with Clojure language, especially new users arriving in the Clojure land.  

**Is there anything else you would like us to know?**   
Thank you for the magnificent work you do!  <br>  

---


### Dragan Djuric  
https://dragan.rocks, https://github.com/uncomplicate  

**What do you plan to work on throughout the year?**  

**Uncomplicate,  Neanderthal, Clojure AI and ML** 
My goal with this funding in 2026 is to continuously develop Clojure AI, ML, and high-performance
ecosystem of Uncomplicate libraries (Neanderthal and many more), on Nvidia GPUs, Apple Silicon, and traditional PC.  
In this year, I will also focus on writing tutorials on my blog and creating websites for the projects involved, which is something that I wanted for years, but didn't have time to do because I spent all time on programming.  

Here's a word or two of how I hope to improve each of these libraries with Clojurists Together funding in 2026.  

**Neanderthal (Clojure's alternative to NumPy, on steroids)**  
In 2025, Neanderthal celebrated its 10th birthday. It started as a humble but fast matrix and vector library
for Clojure, but after 10 years of relentless improvements, now it boasts a general matrix/vector/linear algebra API
implemented by no less than 5(!) engines for CPUs, GPU (Nvidia CUDA), GPU (OpenCL: AMD, Intel, Nvidia), Apple Silicon (Accelerate),
and general CPU (OpenBLAS). And this is not a superficial support for the sake of ticking a checking; each of these
engines support much more operations on exotic structures, and configuration options, than I've seen elsewhere.
It has almost everything, but it doesn't (YET!) have a METAL-based engine for Apple GPUs. Let's work on that!  

**Deep Diamond (the Clojure Tensor and Deep Learning library, not quite unlike PyTorch, but of different philosophy)**  
In 2019, I started Deep Diamond as a demo showcase for Neanderthal's capabilities as the foundation
for high-performance number-crunching software. It quickly outgrew that, and became a general Tensor/Deep Learning
API, implemented by several fast, native optimized, backends, that run on both CPUs and GPUs, across
hardware platforms (x86_64, GPUs, arm64, Apple Silicon, you name it) and operating systems (Linux, MasOS, Windows).
Of course, it does not clash with Neanderthal, but complements it, in the best manner of highly focused
Clojure libraries that do one job and do it well.  

Deep Diamond is quite capable, but it cries for a METAL-based engine for Apple GPUs, too.  

**Diamond ONNX Runtime (the Clojure library for executing AI models)** 
This is the latest gem in Uncomplicate's store, and I developed it thanks to Clojurists Together
funding in Q3 2025. Similarly to how I started Deep Diamond mainly as a teaching showcase for Neanderthal,
I started this to show Clojure programmers how close we, Clojurists, are to the latest and shiniest AI stuff
that everyone's raving about. But of course, being close does not mean that we can close the gap
to the multi-billion funded Python ecosystem in a few afternoons. It needs laser-focused development
and knowing what to do, when, and where. Nevertheless, Clojure *is there*. Now we can run inference on the trained models
from Hugging Face and other vibrant AI communities directly in Clojure's REPL. Does this make an effortless billion-dollar
AI startup? NO. Does it bring Clojurians to the party? YES! And there's more to come.  

Not only that this library is new, but the whole wider ecosystem exploded in the last year
with the wide availability of open-weights model that you can run at home. So, lots of functionality
is added upstream all the time, and I hope to be able to stay current and have the best and newest stuff in Clojure..  

**ClojureCUDA (REPL-based low-level CUDA development)**
Not many Clojurians may prefer to work with GPU directly, or to write their own kernels. Neither do I.
But, this library is one of the un-celebrated workhorses that enables me to implement whatever I want
in Neanderthal, Deep Diamond, and Diamond ONNX Runtime, instead of just trying to wrap whatever there
is in upstream C++ libraries. ClojureCUDA gives us the superpower of choice: wrap whatever works,
but then implement the missing parts yourself!  

As CUDA is receiving a steady stream of changes and improvements, I'd like to improve and extend
ClojureCUDA to always be in top shape! It is not as easy as it seems to the casual onlooker.  

**ClojureCPP (the gateway to native C++ libraries)**  
From 20,000 feet, integrating a native library to JVM and Clojure may look straightforward.
Oh, how wrong they are. Virtually every C++ library is a special kind of jungle, with its
own structures, patterns and inventions. What might seem a minor technical detail might
require special acrobatics to support it on the JVM. Masking that mess under the hood
so that a Clojurian do not need to care might be insanely brittle if it weren't for
ClojureCPP! It is not as large as Neanderthal or Deep Diamond, but it is one of the reasons
that enables these upper level libraries stay on the 25,000 or 3,000 lines of code mark,
instead of being 500,000 or 50,000, as many of their counterparts in other languages.  

**Apple Presets (native JNI bindings for various Apple libraries)**
Yup. To support Apple Silicon in Neanderthal and Deep Diamond I had to make these bindings,
since there weren't any to "just" wrap. And to support more Apple high performance computing apis,
I'll have to create additional bindings (for example, for METAL) and only then
develop the Clojure part.  

**Fluokitten, Bayadera, ClojureCL, Commons, Clojure Sound, etc.**
These libraries will not be in focus in 2026., but will probably need some care and
assorted improvements here and there.  

**Summary:**
In short, my focus with this funding with Clojurists Together will have two main branches:  
1) Development of new functionalities, supporting more hardware and platforms for existing functionality,
   and fixing issues for a dozen Uncomplicate libraries that already exist. This is what is described
   in the text you've just read.  
2) Develop an unified website for Uncomplicate and stuff it with useful information in one place.
   Currently, some libraries have websites that I wrote many years ago, while some rely
   on GitHub Clojure tests, in-code documentation, tutorials on dragan.rocks and my books.
   There are many resources, some of which are quite detailed (2 full books!), but people
   without experience (which is the majority of Clojure programmers) have a hard time using all these in organized way.  
   I hope to solve this with the unified website!  

**Projects directly involved:** 
https://github.com/uncomplicate/diamond-onnxrt  
https://github.com/uncomplicate/neanderthal  
https://github.com/uncomplicate/deep-diamond  
https://github.com/uncomplicate/clojurecuda  
https://github.com/uncomplicate/clojure-cpp  
https://github.com/uncomplicate/clojurecl  
https://github.com/uncomplicate/apple-presets  
https://github.com/uncomplicate/fluokitten  


**Why is this work important to the Clojure community?**  
This will highly benefit the Clojure community as this is THE AI ecosystem for Clojure,
and supporting AI is arguably the main focus on probably all software platforms.
Clojure has something to offer on that front, beyond just calling OpenAI API as a web service!  

Uncomplicate grew to quite a few libraries (of which some are quite big; just Neanderthal is 28,000 lines of
highly-condensed, aggressively macro-ized, and reusable code): Diamond ONNX Runtime, Neanderthal, Deep Diamond, ClojureCUDA, ClojureCPP, Apple Presets, ClojureCL, Fluokitten, Bayadera, Clojure Sound, and Commons.  

**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
I'm outside the USA and EU, but other than that I guess it's just the usual challenges of open-source developers worldwide.  

**Is there anything else you would like us to know?**   
Yes! Clojurists Together funding in the past years greatly helped me in making Uncomplicate as awesome as it is today. Developing large open source projects for 10 years can be tough!  <br>

---


### Siavash Mohammady  
siavashmohammady66  

**What do you plan to work on throughout the year?**  
Easy data dashboard builder like [Shiny dashboards](https://rstudio.github.io/shinydashboard/)  + adding capability building dashboard using prompts.     

**Why is this work important to the Clojure community?**  
Development productivity is essential for  adaptation of a language, as python & R has such a feature which make life simpler for their data-scientists, Clojure so needs to become a viable data-science alternative choice.  <br>

---


### Nikita Prokopov  
https://github.com/tonsky  

**What do you plan to work on throughout the year?**  
I want to resume working on Humble UI, a desktop UI for Clojure that is based on Skia and doesn't involve a browser.  

In 2024, with Clojurists Together support, we got the basics of layout figured out, implemented a VDOM-like API, and started working on a self-documented component library. We’ve got labels, buttons, scroll containers, splits, and tables.  

In 2026, I would like to iron out the underlying API model, so the fundamentals will become stable and Humble can be extended with just implementing more components. I also aim at production-grade multi-line text based on ICU and Harfbuzz, finalizing text inputs, and figuring out packaging. This should get Humble to the point where real-world applications can be built with it.  

This will be accompanied by detailed progress reports describing the problem space and thinking process, in the form of [regular blog posts.](https://github.com/humbleui/humbleui)    

In addition to Humble, there’s a small amount of maintenance work with already existing projects like DataScript, clj-reload, Clojure+, fast-edn, Clojure Sublimed, and such.  


**Why is this work important to the Clojure community?**  
Desktop UIs are not in a good place right now, with only the viable option being Electron and other web-based solutions. These have downsides of a complex execution model and poor performance.  

At the same time, people are getting tired of slow, complex software that runs in a browser and that you can’t own. A lot of things simply can’t be done in a browser, or security is questionable, or it can disappear on a whim.   

Clojure really has a chance to make a difference here. Being so pragmatic, dynamic, and extensible, it is nice to use it to build web servers, but I always felt that a huge class of programs -- the ones with GUI -- is missing, and will be a great fit.  


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
I am an immigrant from Russia (living in Germany for 6 years now).    


**Is there anything else you would like us to know?**   
I loved working with Clojurists Together in the past -- would love to do it again.  <br>

---


### Tomasz Sulej  
https://github.com/genmeblog  

**What do you plan to work on throughout the year?**  
Here is the set of things I want to do in 2026:  

**[fastmath](https://github.com/generateme/fastmath)**   
• Document most of the functions and build more holistic documentation – it’s a really time consuming task, [current status:](https://generateme.github.io/fastmath/clay/)    
• Validate crucial functions by increasing test coverage  
• Implement GAM regression modelling as an enhancement of existing LM and GLM models. PoC is done.  

**[Clojure2D](https://github.com/Clojure2D/clojure2d)**  
• Extract a clojure2d.color namespace as a separate library  
• Add a SVG drawing support. PoC is done.  

**[cljplot](https://github.com/generateme/cljplot)**  
• Revisit currently postponed cljplot library and start to refactor it to give it a new life, inc. the following things:  
    - Highly decouple chart processing pipeline making each step testable and easy to enhance
    - Better use of grammar of graphics concept  
    - Add SVG rendering  
    - Build more specific, scientific charts  
    - (optional) declarative chart generation to allow binding to tableplot library  
• Revisit examples and create the library of chart types  

**Other**
• Review and update the following libraries:  
    - [wadogo – scales for charts](https://github.com/scicloj/wadogo)  
    - [fitdistr – distribution fitting / modelling](https://github.com/generateme/fitdistr)  
    - (optional) [inferme – Bayesian inference library](https://github.com/generateme/inferme)   
• Continue an user support which I do online though Slack/Zulip chats.  
• Prepare workshops and talks for incoming Clojure Jam / [Clojure Creative Coding conference in April 2026](https://clojurecivitas.github.io/scicloj/clojure_jam/clojure_jam_2026)  
• Build more Clojure Civitas notebooks showing Clojure2d capabilities.  


**Why is this work important to the Clojure community?**  
I'm a creator and maintainer of several projects in the mathematics, data science and visualization areas which I did under the nickname GenerateMe/genmeblog. My main and notable projects are:  
•	tablecloth - a dataset library  
•	fastmath – general mathematics and science library  
•	clojure2d – visualization library  
•	clojisr - a bridge for seamless R interoperability (co-creator)  
•	cljplot - pure Clojure charts library  
•	fitdistr - statistical distribution modelling  
•	inferme - Bayesian modelling and inference.  

As a member of the Scicloj community I want to build much more awareness of these libraries, boost the quality and make better adoption of them in existing or future projects.  

My other activities include also mentoring in the [Scicloj open-source mentoring program:](https://scicloj.github.io/docs/community/groups/open-source-mentoring/)  
2026 funding will help me with to focus on two libraries which are require much more care and quality:   

A fastmath is not only a pure math library but aims to be a complete toolset for doing fast scientific and numerical computing in Clojure. My personal goal is to bring the scope of SciPy (Python) or JuliaStats (Julia) suites. I believe that coverage is really high already but still needs a lot of work to validate and document all the functions.  

Clojure2d covers not only 2d drawing and binding to Java2d but also allow pixel manipulation, various pixel rendering methods and aims to cover all possible color manipulation functions including color space conversion, palette/gradient generation and colorimetry.  

I believe a Clojurists Together support will help me to focus more on Clojure open source and community support which I’ve been doing for more than 8 years.  


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
No really. But I quit my current job (I'm on the C-level, management side in a software house) as of  January 2026 and I am trying  to switch to a Clojure programming side.  

**Is there anything else you would like us to know?**   
As a Scicloj member I would like to be more visible next year in various on/offline activities like conferences, meetups, jams etc. The first opportunity will be on the [Clojure Jam 2026,](https://scicloj.github.io/clojure-jam-2026/) where I plan to present details of fastmath/clojure2d libraries as a creative toolset and also host some workshop around the topic.  <br>

---


### Peter Taoussanis  
https://www.taoensso.com/clojure  

**What do you plan to work on throughout the year?**  
Multiple projects, incl. significant work on Nippy and Carmine, possibly working on Babashka support for more libraries (e.g. Telemere)  

**Why is this work important to the Clojure community?**  
**Nippy:** want to experiment with porting a subset of Nippy (binary serialization library) to ClojureScript. Nippy's pretty widely used, and it'd be nice to have data portability between Clj and Cljs. Recently worked on a Clj/s implementation of MessagePack, which ended up being a nice PoC for this effort. MessagePack itself isn't ideal for Clojure data though, we can do better with a Clojure-first format. A cross-platform Nippy would be particularly useful for Sente and Clojure/Script web apps.  

**Carmine:** recently (finally!) pushed the first publicly available version of the core v4 API, but there's still tons of work to do to get it properly polished and documented for wider use. Also seeing increased enquiries recently re: Redis Cluster and/or Sentinel. Have an early prototype ready for Sentinel support, and foundations laid for Cluster - but both would also require substantial effort to bring to a usable state. This alone would probably be several months of work to do right.  

Increased Babashka support: plenty of folks have asked for this, esp. re: Telemere. Expect this may also be a non-trivial job, but should be doable in steps. Would need to investigate further re: specific timeline, etc.  


**Is there anything else you would like us to know?**   
Would just make a similar statement to last year: I'm super grateful to have been able to receive support from Clojurists Together for the past few years. From my side I'd of course be thrilled to continue as long as the community sees value in my work.  

But I also totally understand if it's not possible to continue with funding - I know you must get a lot of great applications each year!  

Whatever the outcome re: funding I'll of course keep trying to contribute where I can.   

And again, a big thanks to all the folks quietly keeping Clojurists Together running so well behind the scenes! This has been such a great project, and something awesome to be involved in.  <br>

---


### Jeaye Wilkerson   
https://github.com/jeaye  

**What do you plan to work on throughout the year?**  
I will be working full-time on [jank.](https://github.com/jank-lang/jank)  

jank's alpha release is going out in December 2025, which will lead us into a hectic start to 2026 as we'll be collecting/fixing as many bugs as we can. Meanwhile, I will be developing jank's build system for native system dependencies, improving stability, significantly improving performance, and adding the remaining features to achieve strong Clojure parity (records, protocols, futures, etc). Furthermore, I will be authoring the jank book, which will be a free standalone resource for learning jank (and Clojure), inspired by and very similar to the Rust book.  

I will also continue to lead the clojure-test-suite initiative, which provides thorough unit test coverage for all core Clojure functions and is currently serving Clojure JVM, Clojure CLR, ClojureScript, babashka, and jank. I aim to capture ClojureDart and Basilisp in 2026 and I have already started discussions with the creators of both dialects.  

I am currently mentoring four different open source contributors, who're learning to be compiler hackers, and I aim to pick up a fifth during 2026. I'm specifically looking to add a Clojure lady to the gang and I began outreach for this at the Conj.  

Additionally, I will continue streaming on Twitch regularly, to raise Clojure awareness. I will generally use that time to either develop jank itself or to use jank to develop Clojure-related tooling.  

Overall, my goal for EOY 2026 is production readiness for jank. This is an aggressive goal, but I will work aggressively on it.  


**Why is this work important to the Clojure community?**  
jank is still the *only* native Clojure dialect which has its own JIT and embraces interactive programming. It unlocks REPL-driven development for a whole ecosystem previously unserved by Clojure.  

Clojurists have demonstrated their desire for native executables with their use of GraalVM. Compared to Graal, jank will offer REPL support, better performance, better native interop, smaller binaries, and faster compilation times.  <br>  

---


### Oleksandr Yakushev  
https://github.com/alexander-yakushev, https://github.com/clojure-goes-fast  

**What do you plan to work on throughout the year?**  
On Clojure Goes Fast side, during 2025, I've released several versions of clj-async-profiler (added dynamic diff modes), clj-memory-meter (heap usage tracing), Flamebin (encrypted private flamegraphs). I've also started working on integrating the latest async-profiler features into clj-async-profiler and transitioning to more compact and efficient JFR-compatible profiles. For 2026, I plan to:  
- Wrap up the JFR transition in clj-async-profiler and upgrade to async-profiler 4.0+.  
- Release the new flamegraph type that includes the time dimension.  
- Implement CPU time and allocation heatmaps.  
- Flamebin: add support for the new flamegraph type supported by clj-async-profiler.  
- Add the converter between JFR and clj-async-profiler dense profiles, allowing to upload Java Flight Recorder profiles directly to Flamebin.  
- clj-java-decompiler: add support for alternative decompiler backends (e.g. Fernflower) and make the decompilers pluggable and auto-discoverable via dependencies.  
- New performance-oriented libraries (e.g., event-passport for efficient in-memory event timestamping).  

As for CIDER and related tooling, which I returned to as a co-maintainer and primary contributor, a great deal has been achieved in 2025 (multiple big releases of CIDER and nREPL), but there is still a lot to be done:  
- Adopt and modernize refactor-nrepl - a widely used part of CIDER that doesn't currently have an active maintainer.  
- Rework cider-nrepl codebase to rely less on dependency "shading" (which is done by mranderson).  
- Improve and unify nREPL configuration approach.  
- Enhance CIDER inspector, improve feature discoverability.  
- Improve multi-REPL experience.  


**Why is this work important to the Clojure community?**  
Clojure Goes Fast is the centerpiece of Clojure performance-related tools and information (1.2K stars, 2.8M total Clojars downloads, 4000 unique site visitors in 2025, 18k page views).  

CIDER is still the most used Clojure IDE according to the last State of Clojure survey. nREPL is the primary backend for all popular Clojure IDEs (including Calva and Cursive).  

