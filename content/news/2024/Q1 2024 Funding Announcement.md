---
title: "Q1 2024 Funding Announcement"
date: 2024-01-10T08:30:00+08:00
summary: "We are funding 8 projects for a total of $44K USD in Q1 2024."
author: Kathy Davis
draft: True

---

Greetings as we start the new year! We’re excited to announce the projects we'll be funding for Q1 2024. (8 projects $44K USD in total).  

There was a groundswell of great project proposals - so making a decision was definitely not easy. 
The good news, however, is that new proposals will be automatically reconsidered for the next project cycle. We have supported many projects in the past that were funded in 2nd or even 3rd review.  

Thanks to everyone for your hard work and creativity in putting these proposals together - this is the kind of thinking and sharing that makes the Clojurist Together community so awesome! 

### Q1 2024 Projects Awarded $9K USD<br>
CIDER – VEMV  
Clojure-lsp – Eric Dallo  
Instaparse – Mark Engelberg  
Scicloj – Daniel Slutsky  

### Q1 2024 Projects Awarded $2K USD<br>
Jank – Jeaye Wilkerson  
Sitefox – Chris McCormick  
UnifyBio – Benjamin Kamphaus  
Wolframite – Thomas Clark and Jakub Thomas  

Please join us in congratulating this talented group. We're looking forward to their project updates  in the coming months. 


### Q1 2024 Projects Awarded $9K USD  
### CIDER – VEMV
Work will be based on CIDER's issue trackers (cider, cider-nrepl, orchard), based on the daily rate that's been reflected for the majority of the https://opencollective.com/cider collaboration.
Some of the larger themes will be: cider-inspector-mode refinements, cider-log-mode refinements, and refactoring our Java parsing machinery.

**Why is this project important to the Clojure community?** It Is the main tool for static analysis in a editor that supports all/most of IDE features. It's the base for Calva, clojure-lsp-intellij and other plugins as it provides completion, diagnostics, code navigation and lots of other features.  <br> 

---


### Clojure-lsp: Eric Dallo  
I will work on clojure-lsp project and related ones like lein-clojure-lsp, lsp4clj, and clojure-lsp-
intellij. My project will include adding missing LSP features, options to create clojure project from scratch for fast onboarding, better performance and stability.  

**Why is this project important to the Clojure community?**  For IntelliJ users, is the only open source free tool to use the main robust clojure-lsp and have static analysis features as an IDE like Calva for VScode, emacs and vim.  

**I have in mind some activities like:** 
- create a section in clojure-lsp webpage for guides and tutorials, with contents like for example "how to work in mono-repo projects"  
- Add more code actions and refactors, especially move-form and some highly requested by community
- Improve the "work out of the box" experience for developers, requiring less configs or files to be able to use clojure-lsp features  
- Improve memory/performance, a tough constant work to make clojure-lsp a lightweight tool to run along with editors  
- Add more custom linters for diagnostics  
- Add more features to clojure-lsp-intellij, to make it a reliable tool for Clojure development on IntelliJ.
Besides those tasks, I'll keep actively helping the community in slack and github for LSP related issues and questions.  <br>

---


### Instaparse: Mark Engleberg  
My focus  will be on evaluating and incorporating pull requests for instaparse. Then, I will look at new features requested by the community. Along the way, I will make sure to keep the documentation updated.  

**Why is this project important to the Clojure community?** Instaparse has been incorporated into a number of other libraries, including some of the routing libraries (for parsing URLs) in some web stacks. Knowing how many libraries rely on its stability has placed an additional burden on me to carefully review and test pull requests. <br>

---


### Scicloj – Daniel Slutsky  
Scicloj is a Clojure group developing a stack of tools & libraries for data science.
Alongside the technical challenges, community building has been an essential part of its
efforts since the beginning of 2019.  
Its current main community-oriented goal is making the existing data-science stack easy to
use. In particular, we need example-based documentation, easy setup, and recommended
workflows for common tasks. All these, and the tools to support them, need to grow
organically, driven by real-world use-cases.  

I am currently working, in collaboration with others, on various components to support these
needs:  
1. [maintaining the Scicloj website](https://scicloj.github.io/)  
2. [running dev groups and study groups](https://scicloj.github.io/docs/community/groups/)  
The most active ones are:  
2a. visual-tools -- collaboration of tool-makers and discussion of literate programming, data
visualization, and UI design.  
2b. llm-meetups -- discussions of the Clojure stack for large language models.  
2c. jointprob -- studying probability & statistics together with other tech communities.  
3. developing tools & libraries:  
3a. Kindly -- a simple data-visualization standard to support compatibility across different
tools.  
3b. Clay -- a notebook tool for interactive data exploration and literate programming.  
3c. Note-to-test -- support for testable documentation.  
3d. Noj -- a wrapper for a few of the relevant data-science libraries to support easy setup
and some convenience functions around it.  
4. running public meetups.  
5. helping out individuals to get started with data science in Clojure.   

Starting in Jan 2024, I am concentrating on two additional projects:  
6. clojure-data-scrapbook -- a community space for documenting data analysis workflows --
similar to the clojure-data-cookbook, but less curated and more community-driven.  
7. real-world-data -- a study group where individuals will bring their own data problems and
collaborate on common practices.  

The project is to initiate items 6 & 7 as a core activity of Scicloj to and keep maintaining
items 1-5, driven by the needs realised in 6 & 7. Everything will be done.  

**Why is this project important to the Clojure community?** Clojure has a huge potential as a data science platform thanks to its REPL, simplicity, data-driven approach, visual support, interop support, and stability. This potential may apply not only to software developers and not only to scientists, but also to much broader crowds, practically anybody who has a data question.  

Since 2019, Scicloj has been addressing this potential. On the technical side, the emerging stack of tools and libraries has matured very well. On the human side, there is still a long way to go till Clojure can become an obvious and welcoming choice for any person exploring some data. Even for people familiar with Clojure, some parts of the stack are not very well-known and not discoverable enough.  

We have been continually addressing these challenges at Scicloj in multiple study groups and one-on-one sessions, and in developing tooling and workflows. Now, when the tooling is maturing, we are transitioning to the next phase, with the upcoming clojure-data-scrapbook and real-world-data projects. These will provide more Clojurians with the necessary support to get comfortable with their data analysis, and will allow us to keep evolving our workflows and documentation for more diverse workflows.  

A well-documented and well-tested Clojure data science stack is expected to help many Clojure teams who often use Python when facing a data analysis need.  

As a byproduct of our documentation efforts, some useful tools are emerging. We believe Clay and Note-to-test can be useful for documenting libraries in general, not just in the data-science scene.  <br>

---

### Q1 2024 Projects Awarded $2K USD<br>

### Jank – Jeaye Wilkerson  
There are some crucial missing pieces in jank which have been getting in the way of me reaching parity with Clojure. In order of importance, we have:  

**Dynamic vars**  
Most people would be surprised how extensively the Clojure compiler and runtime use dynamic vars. Everything from the current locals in the scope to the current line/file info to the currently loaded namespaces and the current function is kept in dynamic vars. These vars are thread-local and stack-based; Clojure handles carrying them over to new threads automatically and pushes/pops new values to not clobber what was already there (i.e. binding).  
 
So far, I've worked around this by using mutable data, but that doesn't benefit from thread locality or the stack-based nature of dynamic vars. Having this support will allow me to go back and improve existing features, like parsing and module loading, to better keep track of this data (and be more easily parallelized).  

**Syntax quoting**
jank has macro support, currently, but no syntax quoting, unquoting, or splicing. That means writing non-trivial macros like ns, and, or, and defn is not sane, so jank either doesn't have them or has very stripped down versions of them. By supporting this, I can then go in and add these common macros and jank will immediately feel much more complete.  

**Bonus**  
I think the above two items are fairly complex and I don't want to over-promise. However, I can see the possibility that I can push through them with some time remaining in the quarter, so I have a couple of other tasks I'll tackle if that happens.  
  
**Meta hints**
jank doesn't currently support ^:foo or ^{:foo true} meta hints on forms, though it does support functions like reset-meta!. Supporting these hints, in the lexer and parser, will clean up some existing jank code (which uses reset-meta! everywhere) and pave the way for type hinting, too. 

**Normalized interpolation syntax** 
Within a native/raw, one can reach back into jank using the #{ foo } interpolation syntax. However, the community has pointed out that CLJS already uses ~{ foo } and it'd be more consistent for jank to do the same. On top of that, jank's parsing for these interpolation forms is pretty rough right now and there is C++ code which would trip it up. It can be improved by hooking more tightly into jank's parser, but that will require adding metadata to the parsed forms to convey their line/column info.  

**Why is this project important to the Clojure community?** jank is the *only* native Clojure dialect which has its own JIT and embraces interactive programming. It unlocks REPL-driven development for a whole ecosystem previously unserved by Clojure.   

Clojurists have demonstrated their desire for native executables with their use of GraalVM. Compared to Graal, jank will offer REPL support, better performance, better native interop, smaller binaries, and faster compilation times.  <br>

---

### Sitefox – Chris McCormick  
Hear from Chris first-hand. **Check out Chris' video about his Clojurists Together project [here.](https://youtu.be/DPT_METwf70?feature=shared)**

Here's what I would like to accomplish in Sitefox using the Clojurists Together funding:   
* Bring dependencies up to date.   
* Tidy up the key-value database module & write basic tests.   
* Improve the end-to-end tests to ensure greater reliability under change. * Improve error handling by making errors more verbose.   
* Test and document deployment of Sitefox sites on 3 or more Node.js providers.   
* Make two concise how-to videos showing people how to building simple sites and apps with Sitefox.   
* Edit together text tutorials based on AI transcripts of the videos.   
* Remove "WIP" from the project description and coordinate a "ready for the public" release   

**Why is this project important to the Clojure community?** Sitefox is a full stack ClojureScript library. That means it is deployed on Node.js rather than Java. I think this architecture is under-explored and has benefits for people who come from the non-JVM side of things.  <br>  

---

 
### UnifyBio – Benjamin Kamphaus  
Broad project scope: Converting the CANDELBio ecosystem I helped create at the Parker Institute for Cancer Immunotherapy from its open source, but semi-abandoned state to UnifyBio, a world class data harmonization platform: Clojure-first and permissively open source (Apache 2.0). 

The successor to CANDELBio’s pret tool will be the more generally useful Unify, at https://github.com/vendekagon-labs/unify and broadly useful for data harmonization or importing data into datalog-oriented stores. I’ll be streaming the dev process over video and sharing selection of this via YouTube.   

The first major need I will be addressing under Clojurists Together funding is a documentation and tutorial overhaul: building out examples, CLI/API and use documentation, end-to-end tutorials, likely example Clerk notebooks (or comparable) showing all the steps from raw data collection, to the data-driven import process, to query, then analysis and visualization. 

I'll also be getting an automated release process in place, with supporting CI/CD, etc. From there, I'll be looking to harden the existing functionality with better example systems and tests covering more use cases beyond biology.   

From there, the priorities are:  
 - Vetting integration with other components of the Clojure data science stack and supporting use of data integrated via Unify (eg access data through tech.ml.dataset's api surface).   
- Building more compelling use cases for distributed processing for biology, especially around single cell and -omics data, expanding Unify’s functionality as necessary to do this.   
- Part of this will be assessing and possibly contributing to or deriving from or otherwise building successors to Onyx, Clojask, etc. (non-spark based Clojure distributed processing tools).   
- I’ll also be writing the current Datomic database layer backend to be more generalizable and able to be written to other stores, assessing Datahike as the first priority here.   

As the project hits the maturity point for use (anticipated very soon, likely early January) with sufficient docs, tutorials, and examples, I’ll be sharing in Clojure community channel areas, especially data-science, data-engineering, datomic users, etc. and spend time supporting users who think they might benefit. I will also reach out to former PICI informatics employees at several organizations that I know are having trouble with data integration, to let them know there’s a successor to CANDEL that’s open source and staffed/supported, to see if I can get inroads for adoption from external bio users not otherwise involved in Clojure.    

**Why is this project important to the Clojure community?** This is a pilot project that solves a variety of problems relevant to many, such as automating the process of converting ad hoc tables into harmonized entities that can be transacted into Datomic and other Datalog oriented databases. Also, this is already a first-in-class set of data harmonization tools for biological data science, some of which are being imitated by startups with many millions in funding. If it can be given more love and effort as an open source project, it can help establish Clojure in the life sciences, much like NextFlow did for Groovy.  

The streams and dev blogs will also give provide a helpful resource for anyone who wants a better understanding of how production grade Clojure projects are built.  <br>

---

### Wolframite – Thomas Clark and Jakub Thomas  
Currently, there is an existing library for wolfram<>clojure interop but it's lack of documentation, reliability and usability mean that it is not widely used or known.  We hope to fix all of these issues and so bring wolfram interop up to the standards of similar bridges like clojisr and libpython-clj. Specifically, fundamental goals would be to:  
- merge recent work  
- create comprehensive inline documentation  
- create big-picture example namespaces: including onboarding tutorials and real-world examples (publically available in visible places like the sci-cloj website and high-impact blogs).  
- expand kernel parallelism  
- expand wolfram external package integration  
- streamline user setup so that wolfram functions can be used almost as easily as other data libraries   
- build the foundations for closely integrating wolfram with the [emmy symbolic clojure system](https://github.com/mentat-collective/emmy)  

**Why is this project important to the Clojure community?** Of the areas of desired improvement that the Clojure survey highlighted, this project would address the specific categories of data analysis, documentation and user growth.  

Data analysis/processing, as also expressed by the Clojure leadership team, is a key growth area for the language but library coverage can be fragmented, with missing depth of functionality. 

Having access to specialised algorithms/functions however, can make or break analysis projects and so reestablishing a stable bridge between clojure and a first-in-class analysis language like Wolfram would go a long way in reassuring language choosers that Clojure can always meet their needs, even when there is no pure clojure library available.  

Another key community area is the expansion of the community itself. Following on from above, there is a large section of the numerical scientific community who are not programmers but who rely on tools like Mathematica and Matlab and so interop in these areas will be crucial for community cross-over in the future. Generalized language interop is particurly important for safe onboarding of new users and experience suggests that there is a willing 'market' for integrating specialist tools within more comfortable general languages like Clojure.  

Finally, this project would not only have its own benefits but it could lay the groundwork for future integration with the 'emmy' symbolic physics library. The emmy system is bringing open-source symbolic computation to both the back- and front-ends but it is missing key features and advanced libraries. A wolfram bridge could serve as a sure foundation and help create the real possibility of an almost unique physics programming space that clojure would be a great fit for but is not quite ready for.  


