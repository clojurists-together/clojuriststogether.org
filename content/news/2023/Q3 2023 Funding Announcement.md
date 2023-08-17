---
title: "Q3 2023 Funding Announcement"
date: 2023-08-17T08:30:00+08:00
summary: "We're funding 8 projects for a total of $44K USD in Q3 2023."
author: Kathy Davis

---
Hi folks. We’re excited to announce the projects we'll be funding for Q3 2023. (8 projects $44K USD in total).
As usual, there were a lot of great projects - so making a decision was not easy. 

The good news, however, is that proposals are automatically reconsidered for the next 2 cycles. We have supported many 
projects in the past that were funded in the 2nd or even 3rd review.  

Thanks to everyone for your hard work and creativity in putting these proposals together - this is the kind of thinking and sharing
that makes the Clojurist Together community so awesome! 

### Projects Awarded $9K USD<br>
[**Bosquet:** Zygis Medelis](#bosquet-zygis-medelis)<br>
[**Emmy:** Sam Ritchie](#emmy-sam-ritchie)<br>
[**Neanderthal, Diamond, ClojureCUDA, ClojureCL, ClojureCPP:** Dragan Djuric](#neanderthal-dragan-djuric)<br>
[**Quil:** Jack Rusher and Charles Comstock](#quil-jack-rusher-and-charles-comstock)<br>


### Projects Awarded $2K USD<br>
[**Biff:** Jacob O'Bryant](#biff-jacob-obryant)<br>
[**Deps-try:** Gert Goet](#deps-try-gert-goet)<br>
[**GDL:** Michael Sappler](#gdl-michael-sappler)<br>
[**Jank:** Jeaye Wilkerson](#jank-jeaye-wilkerson)<br>

### LARGER PROJECTS AWARDED $9K USD<br>
---
## [Bosquet:](https://github.com/zmedelis/bosquet/) Zygis Medelis
Bosquet's goal is to make AI/LLM-based application building simple and productive. There are many types of AI applications and equally many approaches to building them. The scope of this work is to expand AI agent-based architecture support in Bosquet. 

**Main tasks:**   
1. Input/output specs. Agents are functions with specific inputs and outputs. Add LLM input/output specification and coercion inspired by or using Malli/Clojure spec.   
2. Agents and Tools. Expand on initial LLM Agent support. More agent types, more out-of-the-box tools. The best outcome would be a clear architecture and infrastructure for users to add any type of agents and tools. Thus adding new tools and agent types is guided not by the blind expansion of supported integrations but by the illustration of different modes and aspects of agent applications.   
3. Memory. Without LLM memory, any larger processing tasks would be impossible to achieve. Vector DB (or other types of memory implementation) support is essential to add.   

When implementing the above, the growing scope of work should not reduce the library's usability. Documentation, Clerk notebooks, examples, and sound architecture are to take priority over adding functionality.<br>  

---

## [Emmy:](https://github.com/mentat-collective/emmy) Sam Ritchie  
Last quarter's funding enabled me to build out a suite of visualization tools powered by Emmy's mathematical functions, shipped in the Emmy-Viewers library. Emmy is now powerful enough to back a math and physics curriculum up through the college level; the only other Web-enabled system with this ability is Mathematica, with a $3,000 price tag and many restrictions.  

The next step is to build out a community of learning and growth around these ideas, so that we can get kids and classrooms excited and using Clojure as the foundation of their journeys through math and physics.  

This quarter, I want to build out a collaborative experience for all of this math visualization tooling. I want a Google Docs-style experience for interactive documents with sophisticated mathematical visualizations, where multiple users can work together on the same page, save and share the work they've created and build on existing mathematical play and exploration. The system should allow for programmable side-notes, with access to the same computing environment that the original author had. This same mechanic could power an "executable Wikipedia", a wiki-style editable environment where pages contained running code and felt more like namespaces.  

Research groups like Ink&Switch have done amazing work (https://www.inkandswitch.com/upwelling/, https://www.inkandswitch.com/peritext/) building out the technology to power an experience like this. We have glimmers of the final product with Clerk and Maria, but the final push will take some work.<br>

---

## [Neanderthal:](https://github.com/uncomplicate) Dragan Djuric
My goal with this round is to polish the Uncomplicate libraries (mainly Neanderthal, Deep Diamond, ClojureCUDA, ClojureCL, ClojureCPP), rather than develop new functionality. 

Uncomplicate libraries include several infrastructure libraries for high-performance computing, AI, Deep Learning, Math, statistics, data science, etc. Over the years, they grow to be really large. Although they contain orders of magnitude fewer lines of code than the equivalent Python or Java libraries, they cumulatively have 40.000 lines of highly dense Clojure code, especially Neanderthal, which clocks at around 25.000. 

Over the years, I managed to do improve these libraries constantly, including several large refactorings, without breaking backwards compatibility of public-facing API. This was possible in part due to Clojure's awesomeness, but also due to the many thousand of automatic tests that I've developed, and my constant insistence on fixing code issues before they become forgotten hard-to-trace bugs.   

However, as the number of features grows, the proportion of hard-to-reach branches in edge cases grows. They hide cases that I didn't have time to cover with automatic tests. They might have been tested once upon a time, but do they still hold after the major ports? I hope they do, but it's impossible to never introduce a slight coding mistake, especially when porting a thousand lines of code to new backends.  

In addition to this, there are parts of public API that might have stale documentation (Neanderthal), or without proper docstrings (parts of Deep Diamond). I just didn't have the time and resources to dedicate enough time to this. But it's something that users see every day, and it needs improvement!  

In short, I propose to:  
- Do a thorough re-visiting of existing code in major Uncomplicate libraries. 
- Read the code without rushing to implement new features.
- Write more tests for edge cases. 
- Discover bugs and fix them. 
- Discover places where code could be improved (without rushing the new functionality). 
- Re-visit documentation and improve it to better match the current state of Uncomplicate libraries. 
- If opportunities arise, implement some new functionality based of all the aforementioned items.  

In even shorter terms, I propose to do some hammock time, combined with some unexciting chores that enhance the stability and user-friendliness of Uncomplicate libraries.<br>

---

## [Quil:](https://github.com/quil) Jack Rusher and Charles Comstock  
Quil is a wrapper around the Processing computer graphics system that's used by artists and teachers around the world. It has been moribund for the last several years, does not work with the latest version of Processing, nor on JVMs >1.8, nor on Apple arm processors. We would like to fix all of that, improve the quality of the codebase (maintainability, testing, &c), and update the examples/playground website. Ultimately, we would also like to train some more junior people to help maintain the project going forward.  

**Why is this project important to the Clojure community?**
Quil is often used to teach beginners, rapidly prototype visualizations and GUIs, produce art and design artifacts, &c. It has been an important part of the Clojure ecosystem since Sam Aaron created it 10+ years ago.<br>

---

### EXPERIMENTAL OR SHORT-TERM PROJECTS AWARDED $2K USD <br>


## [Biff:](https://github.com/jacobobryant/biff) Jacob O’Bryant  
I'd like to:  
- Write a bunch of how-to articles  
- Write some essays/background info  
- Start writing "Biff from scratch," a series of guides that will show you how to put together a web app in the style of Biff without actually using any Biff code   

Along the way I'll be reading up on SEO, posting things to Hacker News, and thinking about what content topics might be helpful for a non-Clojure audience. I want to use Biff to bring more people into the Clojure community.  

For the specific content I have planned, see the Roadmap section of Biff's [content library](https://biffweb.com/docs/library/) which I'll be keeping up-to-date. **I'll only be able to get to a fraction of those** within the funding timeframe, and I'm sure the list will change as I go. So if any of those topics catch your eye, let me know so I can prioritize them.<br>  

**Why is this project important to the Clojure community?**  
Biff has been slowly gaining traction; currently, the #biff channel on Clojurians gets about 15 unique participants per month, trending upward (https://cdn.findka.com/biff/slack-participants.png). More importantly, I think there's a real opportunity for Biff to drive growth for the Clojure community as a beginner-friendly, comprehensive web framework.<br>

---

## [Deps-try:](https://github.com/eval/deps-try) Gert Goet  
The plan is to add a possibility to deps-try (a CLI to start a rebel-readline powered REPL with libraries you want to try) to load recipes. A recipe (i.e. a local or remote Clojure file) essentially seeds the REPL-history which would make it easy for someone to 'step through' some code. E.g. library authors could add such a recipe-file to their repositories to demonstrate how to use a library.<br> 

**Why is this project important to the Clojure community?**
lein-try was what got me into programming Clojure: no distraction from setting up/learning a new editor, but just fire up a REPL with some specific libraries and try out snippets from the Clojure cookbook.  

I thought it was valuable to have this for tools-deps as well (using rebel-readline for user-friendliness) and launched deps-try almost 5 years ago.  

As an experienced Clojure developer, I still fire up deps-try whenever I run into a new library (not in the least a new Clojure alpha version) and I want to quickly try it out.  After 3 years since the last commit, I recently started hammocking and coding what a new version would look like.<br>

---

## [GDL](https://github.com/damn/gdx) Michael Sappler  
My proposal is to connect two things that are not connected yet - game development and clojure.  

I believe it is possible to achieve a new generation, a new dimension of games written in an evolving language and in an open manner. 
Games need to grow and breathe. The most successful games were games with lots of mods.  

I believe Clojure can have some advantages over the traditional way of creating games: functional programming, but also embracing state and being practical, macros which can make the language evolve.  

My original proposal was one project gdx. Since then I have created 3 public repositories in GitHub: 
- x.x is probably the most useful invention, it is an entity component system using just plain maps and multimethods. 
- The other is now GDL and not gdx which should mean game development language. Till now it is just a helper/wrapper around libgdx. 
- And the third project from which everything came is cyber Dungeon Quest.  

Right now there is not much to the game in terms of content (although much more is not shown). I am working on all 3 projects at once and trying to find the most simple and effective way to organise game code. Cyber Dungeon Quest is around 8500 lines of code and it was 10K a few months ago and I just focused only on cleaning the code and understanding the best way to do things.  

More information can be found on my [GitHub] (https://GitHub.com/damn).  

My near-term goals are to integrate the component system in cyber Dungeon Quest and make all the hard-coded values configurable and save most of the game state in only one place, and then build a ui to easily edit the domain objects. 
Next is to find a way to reduce the amount of Side effects by using some kind of transaction system that is close in spirit to datomic architecture. I am not sure yet if I can directly use datomic for performance reasons, but it would make a lot of things quite easy like replays, timetravel, networking etc. 
I am not fixing myself too much and see where the project grows. In this manner, in the last months I have made amazing discoveries, mostly with x.x ECS and have been led to interesting places.<br>  

---

## [Jank:](https://github.com/jank-lang/jank) Jeaye Wilkerson  
The next big step of jank entails implementing `clojure.core/require` and everything involved. Right now, jank compiles everything from one file (and re-compiles `clojure.core` every time it starts); the runtime/compiler understand namespaces, but there is no compilation cache, no class paths for looking up modules, no dependency loading, etc.  

Implementing `require` would involve these broad strokes (IN SCOPE):  
* Adding a notion of class paths to the compiler in a Clojure-compatible way 
* Adding the necessary `load-libs`, `load-lib`, `load-one`, `load-all`, etc 
* Generating the equivalent of class files, for jank, when loading files 
* Iteratively compiling, based on cached files 
* Support for `:reload` and `:reload-all` code paths 
* Pre-compiling `clojure.core`, which will greatly speed up start times 
* Testing and distribution of this on Linux and macOS, primarily To keep things sane, this Q will likely not include these, but I'll do as much as I can (OUT OF SCOPE): 
* Namespace aliasing 
* The `ns` macro, since jank's macro system isn't quite ready for it yet 
* Benchmarking load times against Clojure 
* Windows support for this new feature 
* STM for module loading; this will be needed, eventually, but is too much to tackle for one Q 
* Project management tooling (leiningen, deps.edn) integration 
* REPL server  

After this work is done, jank will be in a position to integrate with leiningen and actually be used for multi-project files.


