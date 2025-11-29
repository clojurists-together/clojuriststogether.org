---
title: "Vote on 2026 Long-Term Funding Applications"
date: 2025-11-30T14:00:00+12:00
author: Kathy Davis
summary: "It Is Up To You to Decide Who Will Receive Funding. "  
draft: True


---

Greetings Clojurists Together Members! Last year, we tried a new process to determine who will receive a monthly stipend of $1,500 to support their development work. It was a vast improvement, so this year we are using it again. 

We put out the call  - and we received 15 thoughtful applications for you to consider. The board reviewed and moved all 15 to the Ranked Vote ballot. Now it is up to you, the members, to select the 5 developers who will each receive $1,500 monthly stipends for a total of $18K USD. **Be on the lookout for an email that contains the link to your Ranked Vote ballot.** Deadline for voting is **12 December 2025** minight Pacific Time. 

 You can review past long-term project updates on our [website](https://www.clojuriststogether.org/news/) to get an idea of what past annual (or long-term) funding recipients have been able to accomplish with your support (pretty amazing!)

Thanks again to all the members for making this happen. We know from our surveys that you and the larger Clojurist Community that receives our regular updates refer to this work on a regular basis - xxx daily. This is a testament to its importance of these projects. 


### Deadline for your vote to be counted: December xx, 2025 Midnight Pacific Time  

Here we go ..in alphabetical order (by last name)....
Bozhidar Batsov  
Adrian Bendel  
Michiel Borkent
Clojure Camp (Rafal Dittwald, Jordan Miller, and others)
Thomas Cothran
Alex Coyle
Jeremiah Coyle
Eric Dallo
Dragan Djuric
Siavash Mohammady
Nikita Prokopov
Tomasz Sulej
Jeaye Wilkerson
Peter Taoussanis
Oleksandr Yakushev


### Bozhidar Batsov  
https://github.com/bbatsov


**What do you plan to work on throughout the year?**  
Continuously improving CIDER, nREPL, clojure-ts-mode and their related projects. Building a formal nREPL specification is one of the bigger challenges I hope to tackle next year. As is fully decoupling clojure-mode from CIDER.  

Most of the projects are hosted under the following GitHub orgs:  

- https://github.com/clojure-emacs  
- https://github.com/nrepl  

**Why is this work important to the Clojure community?**  
xxCIDER and nREPL are two of the pillars of the Clojure community and are widely used by Clojure developers. The libraries developed originally for CIDER (cider-nrepl, refactor-nrepl, orchard, etc) are used by many other development tools as well.  <br>

---


### Adrian Bendel   
https://github.com/onbreath  

**What do you plan to work on throughout the year?**  

**Clay**   
https://github.com/scicloj/clay  

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
I have another, older GitHub account that shows I've been around for a while, though not as a prolific open source contributor, but great explorer: https://github.com/abp  <br>

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
Clojure Camp is now an incorporated non-profit (in Ontario, Canada). Ideally, this funding would go to the organization, not to an individual.  


### Thomas Cothran   
tmcothran@gmail.com  

**What do you plan to work on throughout the year?**  
I plan to work on pavlov: https://github.com/thomascothran/pavlov  

Pavlov is a functional implementation of behavioral programming, which is a programming paradigm invented by David Harel, the creator of statecharts. This ACM article is a good introduction to the idea: https://cacm.acm.org/research/behavioral-programming/  

Pavlov is currently in alpha status. It is well tested (in Clojure) and has been reliable in production use. It has a number of useful features on top of the behavioral programming core:  
1. A model checker that verifies safety properties (though not yet liveness properties)  
2. Program states can be programmatically navigated using datafy and nav (https://github.com/thomascothran/pavlov/blob/master/doc/navigating-bprograms.md)   
3. A portal-based program explorer, where you can point and click through program execution paths (including time travel) (https://github.com/thomascothran/pavlov/blob/master/doc/designing-business-programs-with-behavioral-threads.md#pavlov-program-explorer)  

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

I previously wrote an article expanding on this question here: https://thomascothran.tech/2024/10/a-new-paradigm/.  <br>

---


### Alex Coyle  
 https://github.com/alza-bitz  

**What do you plan to work on throughout the year?**  
My Clojure API for Snowflake: https://github.com/alza-bitz/snowpark-clj  

This project is non-commercial and open-source. I planned to develop and test it in collaboration with academics, data scientists and others in the Clojure community, in order to help solve real-world data challenges.  

The aims are as stated in the readme, with specific focus on extending the API coverage  https://github.com/alza-bitz/snowpark-clj/blob/main/doc%2Fapi-coverage.md and adding features that are planned but not yet elaborated https://github.com/alza-bitz/snowpark-clj/blob/main/.github/instructions/problem_statement_and_requirements.instructions.md#features-that-are-planned-but-have-not-yet-been-elaborated  

Note: the build is only failing because my Snowflake license has expired.  


**Why is this work important to the Clojure community?**  
I recently wrote on Civitas concerning the extent of Clojure support for the popular on-cluster data tools and why it's important. In the article I also introduced my new Clojure API for Snowflake:  
https://clojurecivitas.github.io/data_engineering/support_for_popular_data_tools/snowflake.html  

I believe these efforts are beneficial to Clojure because it puts the language in front of a different community and makes it more accessible in a space where it doesn't really have any visibility at the moment.  

**Is there anything else you would like us to know?**   
This proposal isn't strictly limited to Snowflake, the scope could most definitely include other on-cluster tools that I write about in my article. For example, I identified some key fixes and improvements for using Spark from Clojure, either to be made in the existing Geni library or in a completely new library:  
1. Supporting Spark Connect: https://spark.apache.org/docs/latest/spark-connect-overview.html. This would reduce the complexity of using Spark SQL & Dataframe APIs directly from the Clojure REPL and also align with client usage for GoLang, Python, etc. I already did some investigation on this: https://github.com/zero-one-group/geni/issues/345  
2. Supporting Databricks: https://github.com/zero-one-group/geni/issues/356   <br>

---


### Jeremiah Coyle  
 https://github.com/paintparty  

**What do you plan to work on throughout the year?**  

These are the 5 projects that I have been working on in 2025, and plan to continue working on in 2026. All are fairly mature alpha-stage projects.

***Project #1 Bling***
Bling provides hifi console printing for Clojure, ClojureScript, and Babashka.
https://github.com/paintparty/bling
Thanks to the generous support from Clojurists Together, I'm happy to report that a great deal of progress was made in Q2 of 2025. Building on the momentum, I would love to continue to grow and improvement the project going forward.  

The 2026 Roadmap for Bling can be found here:
https://github.com/paintparty/bling/blob/main/resources/docs/Bling-2026-roadmap.md  


**Project #2 - Kushi**  
Kushi is UI design library for ClojureScript  
Source repo: https://github.com/kushidesign/kushi  
Interactive docs: https://kushi.design/  

Progress in 2025:  
Thanks to the generous support of an experimental projects grant from Clojurists Together in Q3 of 2024, great progress was made towards the goal of delivering a rich, first class, Clojure(Script)-native UI framework for building and evolving web projects. That momentum was leveraged to maintain an increased focus on the project in Q1 of 2025, with an emphasis on refining the architecture and performance of the build system. In q2, I updated the design for the project splash page. I resumed work on the project in Q3 & Q4, concentrating on the Malli-driven runtime safety / warning system, as well as rethinking and implementing an improved shared property system for styling of the library of stock UI components.   

Goals for Kushi in 2026:  
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
Fireworks is a themeable tapping library and color pretty-printing engine.  
https://github.com/paintparty/fireworks  

Thanks to the generous support from Clojurists Together, I'm happy to report that a great deal of progress was made in Q3 of 2025. Building on the momentum, I would love to continue to grow and improve the project going forward.  

Goals for Fireworks in 2026:  
- Address all current issues (mostly enhancements)  
- Editor extensions/integrations have been created for Cursive and VSCode. Initial work has commenced on an Emacs integration. I would like to turn the existing VSCode Joyride user script into an actual VSCode extension, in order to provide an even smoother beginner experience.  
	- https://github.com/paintparty/fireworks/blob/main/docs/editors/cursive/cursive.md  
	- https://github.com/paintparty/fireworks/blob/main/docs/editors/vscode/vscode.md  
- Produce written and/or video documentation of my current live hot-reloading dev environment for JVM Clojure, with versions for both Leiningen and Deps. I recently got a PR merged that adds this to test-refresh - https://github.com/jakemcc/test-refresh/pull/91.   
  
- For ClojureScript developers using Fireworks in a browser dev console, I made a dedicated Chrome extension to enable the setting of the Chrome DevTools console background and foreground color with a very nice GUI interface. Would be cool to get updated and also work in most other Chromium-based browsers, and potentially Firefox, if there is any demand for it.  
    - https://github.com/paintparty/fireworks?  tab=readme-ov-file#setting-the-background-color-and-font-in-chrome-devtools-clojurescript  


**Project #4 - Domo**  
Domo is a modern ClojureScript DOM utility library.  
https://github.com/kushidesign/domo  

Goals for Domo in 2026:  
- Write quality docstrings with examples for all 82 public functions.  
- Add simple validation and dev-time warnings for all public functions (very useful when working with the DOM). Make sure this code is elided for production builds so as not to add extra weight
- Continue to add useful utility functions as the need for them arises within the context of real-world project work.  
- Add more tests to the existing browser test suite at in `starter.browser.browser-tests.cljs` namespace.
- The `starter.browser` namespace in the examples/browser dir currently serves as a quickstart and (exhaustive) API tour. I would like to get user feedback on this to determine its effectiveness/usefulness.    
- Many of the core functions should “just work” as they are written in the context of a Squint project. I would like to make most, if not all of the public API available in Squint-land.  

**Project #5 Lasertag** 
Lasertag is a utility for categorizing types of values in Clojure, ClojureScript, and Babaskha.  
https://github.com/paintparty/lasertag  

Goals for Lasertag in 2026:  
- Solidify current public API  
- Extensive coverage for all Java and Javascript types/classes  
- Extensive test suite for all types and classes  
- Make cool demo for usage in ClojureScript  

**Why is this work important to the Clojure community?**  
I believe work on these projects could benefit the Clojure community for the following reasons (listed out project-by-project):  

**#1 - Bling**  
https://github.com/paintparty/bling  

Many mature language communities have a library for rich text printing in the terminal, for example Rich (Python), Chalk (JS), LipGloss (Go), etc.  

With Clojure, the main existing option seems to be org.clj-commons/pretty. The original impetus for creating Bling came out of my experience of trying to use Pretty extensively for formatting error and warning messages.  

The most unique thing that Bling offers is a carefully curated palette of 11 basic colors which are readable on both light and dark backgrounds. Because these colors are located in the 16-255 ansi range, they are guaranteed to look the same on almost any terminal, regardless of the user's terminal theme. Other libraries, including Pretty, leave the colorizing up to the user's terminal emulator profile/theme, which often leads to wildly different colorized output in the user space.  

Bling also offers two simple functions for creating "blocks" of text in the console. Check out the readme for exhaustive visual examples - These can be semantically colored (errors, warnings, info). Bling also offers a simple function for constructing a point-of-interest diagram, like when you want to call out the namespace, line, column and show some source code with a red squiggly underline. I think the Clojure community would benefit from more library authors adding neatly-formatted, actionable warning and error messages to their codebases. It would also be great if such warnings and messages where formatted in a way that loosely followed some set of conventions. Perhaps Bling could play a small role in fostering this within the Clojure community.  


**#2 - Kushi**  
https://kushi.design/  

Kushi aims to provide a complete solution to the design layer of web UI projects.
It is an ambitious project, but I believe there is an opportunity for Kushi to offer a UI dev experience that is unique and more compelling than any equivalent in any other language. This could lead to increased organizational awareness and consideration of ClojureScript as an attractive choice for building mission-critical UI. If more companies used ClojureScript to build UI, I think it would be very beneficial for the community.  

**#3 - Fireworks**  
https://github.com/paintparty/fireworks  

Fireworks is the only lib to provide a colorizing and themeable pretty-printer that works beautifully in both Clojure and ClojureScript (and Babashka).  

The output is, arguably, orders of magnitude faster and easier to read than equivalent output from clojure.pprint/pprint. This is especially true in the case of maps with data-structures as keys, or any kind of collection that features metadata.  

The library also provides very powerful debugging and tapping macros. These macros provide a lightweight complement to discovery-centric, UI-heavy data exploration tools such as Portal and Flowstorm. With a simple hot-reloading setup in clj or cljs, and minimal editor integration (now available in Cursive and VSCode), Fireworks can drive an extremely compelling live-feedback dev experience without any reliance on a repl connection, or repl-related concepts. I think the continued codification, documentation, and demonstration of such a workflow could make a difference in the on-ramping of newcomers to Clojure. The maturation of this kind of workflow could also benefit existing users of similar community tools such as playback, debux, hashp, telemere, ken, spyscope, omni-trace, postmortem, and sayid.  
 

**#4 - Domo**  
https://github.com/kushidesign/domo  

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

**#5 - Lasertag**  
https://github.com/paintparty/lasertag  

Lasertag is a utility library that was spun out of Fireworks. I believe it to be quite unique in the current landscape of cljc libraries that deal with reflection and categorization of values.  

lasertag.core/tag-map makes it easy to get detailed information about values, particularly in the context of interop with Java or JavaScript.  

I think that Lasertag could potentially offer great utility to rich-tooling projects developed in the Clojure community of the present and future.    

**Is there anything else you would like us to know?**   
Very much appreciate the recent support from Clojurists Together for these projects, would love to keep it going!  <br>

---


### Eric Dallo  
ericdallo  

**What do you plan to work on throughout the year?**  
- **ECA (Editor Code Assistant)** - https://github.com/editor-code-assistant/eca  
This project was born 5 months ago (with clj-together's help!) and it's growing, there are so many improvements to this project related to features and parity with other big tools like Claude/Cursor, compatibility with more editors, fix and support community issues. 
This AI tool is written in Clojure but works in any language which is huge for discoverability of Clojure community outside Clojure bubble, it has a lot of potential in my opinion, and projects for each editor to maintain and evolve.  

- **Clojure-lsp** - https://github.com/clojure-lsp/clojure-lsp  
I want to work on custom code actions, support for new clojure.java.javadoc lib, improve java interop support like go to class locations, methods etc, lots of this would require me working in clj-kondo too  

- **metrepl** - https://github.com/ericdallo/metrepl  
I want to help with guide and examples of metrics of your repl and how that can help companies understand developers REPL usage  

**Why is this work important to the Clojure community?**  
I believe those are really import tools for the developer, especially clojure-lsp and ECA which run in all major editors during daily programming. In my opinion, developer tools for programming are critical for user happiness with Clojure language, especially new users arriving in the Clojure land.  

**Is there anything else you would like us to know?**   
Thank you for the magnificent work you do!  <br>  

---


### Dragan Djuric  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx


### Siavash Mohammady  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx


### Nikita Prokopov  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx


### Tomasz Sulej  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx



### Jeaye Wilkerson  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx



### Peter Taoussanis  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx



### Oleksandr Yakushev  


**What do you plan to work on throughout the year?**  
xxx

**Why is this work important to the Clojure community?**  
xxxx


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**   
xxxx


**Is there anything else you would like us to know?**   
xxxxxx



