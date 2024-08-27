---
title: "Q3 2024 Funding Announcement"
date: 2024-08-27T08:30:00+08:00
summary: "We are funding 7 projects for a total of $35K USD in Q3 2024."
author: Kathy Davis
draft: True

---

Clojurists Together is excited to announce that  we will be funding 7 projects in Q3 2024 for a total of $35K USD (3 for $9K and 4 shorter or more experimental projects for $2K). To date in 2024, we have or are funding 32 projects or developers for a total of $258,000 USD. This includes the 8 long-term developers whose project work we are supporting for the year. Thanks to all our members for making this happen!

Based on your input on our last two surveys, we know that our members and larger Clojure community rely on this work on a regular (most on a daily) basis.  If we are able to engage more companies or individual developer members, we could support even more important work - so please get the word out.  

As usual, we received a LOT of great proposals - so the decision-making was not easy. However, proposals are returned to the pool for another 2 rounds for consideration. We're looking forward to developer updates over the next 3-6 months! Here is a general overview of what each developer plans to work on. We're looking forward to their regular updates as the projects progress.  

### 9K Projects
[Clojure Goes Fast: Oleksandr Yakushev](#clojure-goes-fast-oleksandr-yakushev)  
[Malli: Ambrose Bonnaire-Sergeant](#malli-constraints-and-humanization-ambrose-bonnaire-sergeant)  
[Scicloj: Daniel Slutsky](#scicloj-daniel-slutsky)  

### 2K Projects  
[clj-nix, nixpkgs: Jose Luis Lafuente](#clj-nix-nixpkgs-jose-luis-lafuente)  
[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)  
[Kushi: Jeremiah Coyle](#kushi-jeremiah-coyle)   
[Standard Clojure Style: Chris Oakman](#standard-clojure-style-chris-oakman)  


## $9K Projects  

### Clojure Goes Fast: Oleksandr Yakushev  
I plan to spend these three months improving different tools under the Clojure Goes Fast umbrella.  

For clj-async-profiler, the most accurate performance profiler for Clojure, I want to create a brand new web application that will allow users to host and share the generated flamegraphs. At the moment, even though flamegraphs are just self-contained HTML files, sending them to somebody is a chore. The new service can make this much easier and offer extra features like saving and sharing dynamic transforms on the fly. Additionally, I'd like to focus on the UI side of clj-async-profiler - add new dynamic transforms, improve aesthetics and the UX.  

For clj-java-decompiler, I have some ideas to expand its preprocessing abilities to present clearer results to the user. I also want to eventually integrate it more tightly with established Clojure IDEs like CIDER and Calva, which requires some groundwork.  

Another focus of mine is populating the Clojure Goes Fast knowledge base (https://clojure-goes-fast.com/kb/) with more articles. I have several topics I want to cover in the knowledge base regarding different types of benchmarking and some fresh ones, and some are written as blog posts and require adapting and bringing the information up to date.  


### Malli (Constraints and Humanization): Ambrose Bonnaire-Sergeant  
This project aims to drastically improve the expressivity of Malli schemas to help address current user feedback and enable future extensions.  
The basic idea is to add a constraint language to each schema to express fine-grained invariants and then make this constraint language compatible with validators/explainers/generators/etc so that Malli users can write high-level, precise schemas without resorting to partial workarounds.  
A real-world frequent user request on #malli Clojurians is the ability for Malli :map schemas to express constraints about keysets, such as two keys being both present or both absent. Specs/keys supports this using a limited constraint language consisting of (or (and ..)).  

It is undesirable to work around this using :fn and :and because it breaks many features of Malli, usually generators and error messages. A better solution would be to support this directly by Malli for maximum compatibility. Unfortunately, this is usually the best and only
suggestion, even though it introduces other problems.  

I have prototyped this project here: https://github.com/frenchy64/malli/pull/12  I will collaborate with the Malli maintainers to extract and redevelop parts of the prototype. By the end of the project, I hope to have merged support for constraints in Malli along with documentation for its use.  


### Scicloj: Daniel Slutsky  
Scicloj is a Clojure group developing a stack of tools & libraries for data science. Alongside the technical challenges, community building has been an essential part of its efforts since the beginning of 2019.  

Our current main community-oriented goal is making the existing data-science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on example-based documentation, easy setup, and recommended workflows for common tasks.  

All these, and the tools to support them, grow organically, driven by real-world use-cases.   

Here is the current plan for the coming quarter. While the plan focuses on my tasks, many of these tasks are done in collaboration with others. The plan and priorities are frequently updated through discussions at the Scicloj group.  

#### Stabilize the Noj v2 project:  
https://scicloj.github.io/noj  Noj is an entry point for the main data and science libraries. Some parts are under active development and will be stabilized as a group effort in the coming quarter. Specifically, stabilization will involve the following main tasks:  
- Help with the documentation effort of Fastmath (math & stats) version 3.  
- Help develop current branches of Metamorph.ml (machine learning) and scicloj.ml.tribuo.  
- Help with issues in Tablecloth (dataset processing).  
- Stabilize and extend the API of Hanamicloth (data visualisation).  

#### Improve the Noj documentation:  
The goal of the Noj documentation is to introduce the main libraries and explain how they can be composed in typical use cases.  

#### Coordinate the Scicloj open-source mentoring program:  
https://scicloj.github.io/docs/community/groups/open-source-mentoring/
- Meet all people who apply to the program.  
- Coordinate the assignment of projects and mentors.  
- Take part in mentoring most participants.  
** Between Aug 10th to Aug 27th:**   
- 21 people have applied and started meeting  

#### Run dev groups and study groups:  
https://scicloj.github.io/docs/community/groups/ In the coming quarter, the following will be most active:  
- real-world-data -- a space to discuss data and science projects at work.   
- jointprob (restarting these days) -- studying probability & statistics together with other tech communities.   
- visual-tools -- collaborations of tool-makers and discussion of literate programming, data visualization, and UI design.   
- data-recur -- recurring updates & discussions around building the emerging data stack.   

#### Work on tooling for data & science: 
- Kindly - a simple data-visualization standard to support compatibility across different tools.  
- Clay - a notebook tool for interactive data exploration and literate programming.  

#### Work on additional libraries:  
- Stabilize cmdstan-clj -- a Clojure bridge to the Stan probabilistic programming language.  
- Initiate the development of a grammar-of-graphics library (ggplot-inspired), continuing our experience with Hanamicloth.  

### Maintain the Scicloj website:  
https://scicloj.github.io/  <br>

---

## 2K Projects  

### Clj-nix, nixpkgs: Jose Luis Lafuente  
I plan to add an alternative builder for Nix that uses Babashka/SCI instead of Bash. It provides a way for constructing complex Nix derivations using Babashka/SCI entirely, eliminating the need to write any Bash code. My plan is to initially develop the builder under the clj-nix project, but if it gains enough traction I'd consider extracting the new builder to its own project, or even upstreaming it to nixpkgs.   

I also plan to add a Babashka writer to nixpkgs. Nixpkgs supports creating self-contained scripts, called "nix writers." Traditionally written in Bash, recent versions of nixpkgs include the ability to write these scripts in Python, Ruby, or Rust. I propose adding a new writer to support Babashka.  


### Jank: Jeaye Wilkerson  
Jank feels like Clojure now, with 92% syntax parity and nearly 40% clojure.core parity. But it only **feels** like Clojure to me because none of you are using it yet.  

My top priority is to change that.  

I'll be working on building out jank's nREPL server, which involves implementing bencode support, `clojure.test`, improving native interop, supporting pre-compiled binary modules, and ultimately adding AOT compilation support.  

This is a great deal of work, with many side quests along the way, but I'm aiming to have the nREPL server working from my editor by the end of the year.  


### Kushi: Jeremiah Coyle  
I'll continue development of Kushi, a foundation for building web UI with ClojureScript. Work during the Q3 funding cycle will focus on finishing the new css transpilation pipeline, significant build system performance upgrades, and implementing a reimagined theming system.   


### Standard Clojure Style: Chris Oakman  
I'll be developing Standard Clojure Style which is a "no config, runs everywhere, follows simple rules" formatter for Clojure code.  
More information about the genesis of the project can be found on Issue #1: https://github.com/oakmac/standard-clojure-style-js/issues/1  



