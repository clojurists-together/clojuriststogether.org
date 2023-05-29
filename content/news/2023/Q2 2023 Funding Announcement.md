---
title: "Q2 2023 Funding Announcement"
date: 2023-05-29T08:30:00+08:00
summary: "We're funding 8 projects for a total of $44K USD in Q2 2023."
author: Kathy Davis
draft: True
---
Greetings all. We’re excited to announce the projects we'll be funding for the Q2 2023 round. (8 projects $44K USD  in total).
There were a lot of great projects - so making a decision was, as usual, difficult. The good news, however, is
that proposals will automatically be reconsidered for the next 2 cycles.
We have supported many projects in the past that did not make it in the initial round - so stay tuned!
Thanks to everyone for your thoughtful proposals - this is the kind of thinking and sharing that makes the Clojurist Together community so awesome! 

### Projects Awarded $9K USD<br>
[**Emmy:** Sam Ritchie](#emmy-sam-ritchie)<br>
[**Clj-kondo, babashka, cherry, SCI:** Michiel Borkent](clj-kondo-babashka-cherry-sci-michiel-borkent)<br>
[**Clojure Camp:**  Daniel Higginbotham](clojure-camp-daniel-higginbotham)<br>
[**Neanderthal, Clojure CUDA, Deep Diamond:** Dragan Duric](neanderthal-clojure-cuda-deep-diamond-dragan-duric)<br>

### Projects Awarded $2K USD<br>
[**clj-nix:** José Luis Lafuente](clj-nix-jose-luis-fuente)<br>
[**Jank:** Jeaye Wilkerson](jank-jeaye-wilkerson)<br>
[**Lucene Grep:** Dainius Jocas](lucene-grep-dainius-jocas)<br>
[**Portfolio:** Christian Johansen](portfolio-christian-johansen)<br>

### LARGER PROJECTS AWARDED $9K USD<br>
---


## [Emmy](http://github.com/sicmutils/sicmutils): Sam Ritchie
I want to make the SICMUtils computer algebra system into a best-in-class, modular set of tools for doing serious work and exploration in math and physics, and sharing that work in the browser. When used with NextJournal's Clerk, https://maria.cloud/, and the components I built with last quarter's funding, I think SICMUtils could take the place of Mathematica for students and educators and help cement Clojure as a serious contender for top data science language. 

Concretely, I would use the quarter to:  
 - Rebrand SICMUtils as "Emmy" after [Emmy Noether](https://en.wikipedia.org/wiki/Emmy_Noether)
 - split the 30kloc project into modules, to make the components easy to use from other projects. Examples of useful components include: 
- A battle-tested symbolic expression simplifier and pattern-matching rules 
- An extensible symbolic expression compiler, capable of emitting LaTeX, fast numerical functions, fast matrix-valued functions, etc 
- Forward- and reverse-mode automatic differentiation 
- Modules for mathematical objects like polynomials, rational functions, quaternions, and geometric algebra
 - Differential geometry code used in general relativity research   

With last quarter's funding, I produced a number of components for creating and interacting with 2d and 3d visualizations, as well as tooling for NextJournal's Clerk to make it easy to create interactive documents embedding these components. 

I'll extend this work by building out a library of Clerk viewers that make it easy to visualize and work with interactive versions of all of the important mathematical objects in the SICMUtils library. 

Finally, I'll use the system to produce a series of interactive essays that explore mathematics and physics, as a way of showing off the power of the system.   
A stretch goal would be to ship these interactive essays both using Clerk and using https://www.maria.cloud/, so that all text and code would become interactive as well. 

As an example of what is possible, see [this interactive visualization of a (p, q) torus knot](https://mathbox.mentat.org/dev/mathbox/examples/math/pq_knot.html).   

I was able to use the SICMUtils automatic differentiation and function compilation features to turn the following inefficient 
description of the curve for one of these knots into a highly optimized JavaScript function that ran 10-20x faster 
than the hand-tuned JavaScript of the example I was porting: ```clojure (defn torus-knot "Returns a function of `theta` 
that produces a 3-vector of the XYZ coordinates of a `(p, q)` torus knot wrapped around a torus (donut) with major radius `R` and minor radius `r`." <br>

---

## Clj-kondo, babashka, cherry, SCI: Michiel Borkent  

### [Clj-kondo](https://github.com/clj-kondo/clj-kondo):
 - Support an arbitrary amount of reader conditionals in .cljc files via configuration. This is mostly useful for ClojureDart (`:cljd`), 
 - babashka (`:bb`), nbb (`:org.babashka/nbb`) and shadow-cljs targets like `:node`. It's often not selfevident if people are writing both for JVM Clojure + ClojureScript (which is how clj-kondo by default understands `.cljc` files) or for other combinations of Clojure dialects. I want to add support for configuring what Clojure dialects apply to your project and what Clojure dialects should be linted as (e.g. `:node` should just be linted as `:cljs`). If necessary I'll also add support for specific dialects like ClojureDart. 
- Help with clj-kondo configuration for Electric Clojure 
- Numerous other issues, there's always plenty to do! 
See the [clj-kondo project board:](https://github.com/clj-kondo/clj-kondo/projects/1) and 
see [here for a list of most upvoted issues:](https://github.com/clj-kondo/clj-kondo/issues?q=is%3Aissue+is%3Aopen+sort%3Areactions-%2B1-desc) 
- As clj-kondo is the foundation for the static analysis in clojure-lsp: improvements in the interaction between the two, most specifically the analysis data. 

### [Babashka](https://github.com/babashka/babashka)  
 - Make it easier to use scripts in projects from anywhere on the [system. bbin](https://github.com/babashka/bbin) is already helping a lot with this. However, if we allow scripts that are on the path to be combined with their relative `bb.edn` file to be invoked while taking into account the dependencies from `bb.edn`, this would be a lower bar of entry for having system wide scripts and might also simplify the implementation of bbin itself. Eventually I'd like to include some of bbin's features into babashka itself, as a `bb install` command and this would be the first step towards that.
 ### Port Clojure 1.12 features to babashka   
- Allow user-land middleware to participate in the babashka nREPL server
- babashka.http-client: this library is now included in babashka but still needs some better configuration options for SSL contexts and http proxy. 
- Addressing issues of several other built-in libs in bb: babashka.cli, fs, process, etc. 
- Better error locations for clojure.test output (https://github.com/babashka/babashka/issues/1518) 
- Report exception causes in console error report (https://github.com/babashka/babashka/issues/1515) 
- Several other issues: https://github.com/babashka/babashka/issues 

### [Cherry](https://github.com/squint-cljs/cherry)
 - Cherry is a CLJS compiler that can be used as a standalone tool but also to (partially) replace self-hosted CLJS in exising projects. This mode is known as [cherry.embed:](https://github.com/squint-cljs/cherry/blob/main/doc/embed.md) The embed mode is already used in malli as an alternative to SCI: instead of interpreted CLJS we now get compiled CLJS for serializable functions. To be more generally useful, cherry needs to emit namespaced functions which it currently doesn't. Eventually something like [scittle](https://github.com/babashka/scittle) may be implemented using cherry which would give the same convenience but a smaller bundle and better performance.   

### [SCI](https://github.com/babashka/sci): 
- Add the ability to request the stacktrace at arbitrary execution points in user land (https://github.com/babashka/sci/issues/871) (also related to the babashka issue about clojure.test locations) 
- Multiple minor issues related to JVM and CLJS interop: https://github.com/babashka/sci/issues<br>  

---

## [Clojure Camp](https://theclouncil.com): Daniel Higginbotham 
 **Vision**  
At Clojure Camp, we are creating a free learning community to help new developers of all backgrounds build a secure foundation for professional programming success — with Clojure! We hope to model a positive tech environment through a commitment to diversity and through our emphasis on creating a fun, friendly, supportive, and kind environment.  

Clojure Camp is rooted in an equity ethos: it's open to all while prioritizing the experience of those who face the most hurdles in tech. Our Community is a place where Women, BIPOC folks, and LGBTQ+ folks belong.  

We believe that learning Clojure is a great long-term investment because it helps people learn a better way of approaching programming, and because it brings creative satisfaction. We want to make it accessible to more people.  

We believe this will have a long-term positive impact on the Clojure community by improving our reputation as a welcoming and helpful community, and by increasing the pool of Clojure developers. <br>  

**Product**<br>
For the next six months, we are focusing on two “products”: a study group matchmaking service and a metacircular project incubator.  <br>

**Study Group Matchmaking**  
We have created a study group format and matchmaking service that’s proving to be effective with our initial cohort of learners. About five learners are paired with two mentors who guide the learners during weekly video calls, reviewing the work the learners have done over the previous week. In between video calls, learners use a tool to match them with other learners in their group for pair programming and support.  

We want to continue building out this model by refining the curriculum and developing other avenues of learning and collaboration. Also, we want to start doing some outreach to attract new developers to participate. We’ve received a lot of interest from potential mentors; now we just need to find some students for them!<br>  

**Metacircular Project Incubator**  
We’ve designed Clojure Camp to be a kind of project incubator, where motivated volunteers can propose their learning projects and get our support. The kinds of support we provide include:<br>
• **Implementation feedback.** We are available to brainstorm ideas or otherwise spend time chatting with project leaders to help them refine their offering.  
• **Networking.** We can refer project leaders to others who might be able to help, and we run a discord server for project leaders to reach out to each other.  
• **Moral Support.** Part of our motivation for creating this structure is that we want to help people avoid burnout. It’s a lot easier to do this sustainably when you’re in community.  
• **Advertising.** We help project leaders get their projects in front of a qualified audience, allowing them to focus on what they do best: helping people learn!  

Our core team already has a number of projects we’ve begun working on in this spirit, including:
- Speaker coaching
- Email newsletter <Br>
<br>  

---

## [Neanderthal, Clojure CUDA, Deep Diamond](https://github.com/uncomplicate): Dragan Duric  

In short, I propose to implement:
 - a port of Neanderthal's MKL engines and CPU related stuff to JavaCPP instead of neanderthal-native (for sure).
 - a port of ClojureCUDA to JavaCPP's CUDA. (probably, depending how 1 goes) - a port of Neanderthal's GPU matrix engine to new, JavaCPP-based ClojureCUDA (almost sure, if ClojureCUDA port goes well) 
- update Deep Diamond to use new infrastructure.
 - improve relevant internal parts of Neanderthal code with more macrology (double-edged sword, I know, but also concentrates bugs at one place each). 
- TESTS! to make sure everything works as well as before (of course!)<br>  



### EXPERIMENTAL OR SHORT-TERM PROJECTS AWARDED $2K USD<br>
---
 
## [clj-nix](https://github.com/jlesquembre/clj-nix): José Luis Lafuente<br>

**Why is this project important to the Clojure community?**
Nix is a great tool to build and deploy software, but Clojure is not well supported in 
the Nix ecosystem. clj-nix makes easier to integrate Clojure on a Nix project.

**What are you wanting to achieve with this funding?**  
I want to refactor the CLI to be more granular about the dependencies used at build time. 
Currently, all dependencies in all aliases are added to the lock file. I want to add some 
flags to the CLI to allow users to manually define the `deps.edn` files, and the aliases to use. 
Once CLI refactor is done, I want to add support for calling Babashka at build time, adding
the possibility to use Babashka, and the dependencies defined in the `bb.edn` file, in the Nix build phase.<br>

---

## [Jank](https://github.com/jank-lang/jank): Jeaye Wilkerson   
jank is the *only* native Clojure dialect which has its own JIT and embraces interactive programming. It unlocks REPL-driven development for a whole ecosystem previously unserved by Clojure.   

Clojurists have demonstrated their desire for native executables with their use of GraalVM. Compared to Graal, jank will offer REPL support, better native interop, smaller binaries, and faster compilation times.   

Clojure's object model is intense. In fact, representing it 1:1 from Java to C++ is impossible, since C++ has stricter rules are duplicate base classes and bases with same-name fields and different types. Furthermore, C++, even with the Boehm GC, is much slower at creating objects than the JVM is. It's bread and butter work for the JVM. Also, while everything is an Object in the JVM, C++ doesn't have the same notion. If every jank class were to inherit from the same Object class, it would have _very_ serious performance implications when it comes to allocations.   

So jank has two key problems here: 
1. Creating new boxed values is slow, compared to the JVM 
2. Not every jank type can actually be turned into an object, which sometimes means doing some weird dancing to get from one type, through another, and finally to the object base; this generally requires virtual function calls  

 So far, I've worked around the first one by optimizing other things, so jank can be faster than Clojure in a given program for example, but when measuring just the object creations, Clojure is still around twice as fast. I want to fix this.  

This task would entail implementing and benchmarking a few different solutions, all of which move jank's object model away from C++ inheritance and toward something more dataoriented. This gets us around C++'s virtual inheritance allocation costs, but it can also allow _every jank type_ to be treated as an object, which will not only simplify jank's runtime code, but will itself be a key optimization.  

Right now, I have two key design ideas:  
1. A very template-heavy approach, which uses bit fields to keep track of which behaviors an object has, as well as which data storage it has  
2. An ECS-based approach, which separates object identity from storage, which would aid in cache performance and data locality issues  

So far, I have prototyped the first approach and found object creation is nearly twice as fast. This funding 
would allow me to implement both of these solutions fully, benchmark them, and research further ways
to improve them. Finally, I will integrate the fastest solution into jank and reap the benefits.<br>  

---

## [Lucene Grep](https://github.com/dainiusjocas/lucene-grep): Dainius Jocas  

**What are you wanting to achieve with this funding?** I want to tear the lucene-grep project apart into several reusable idiomatic Clojure libraries/tools/tutorials that would help the Clojure community to leverage the Apache Lucene library, e.g. a Clojure library whose single purpose is to create Lucene Analyser in a data driven fashion https://github.com/dainiusjocas/lucene-custom-analyzer. 

Also, I want to combine the knowledge encoded in other Clojure projects when it comes to using Apache Lucene and either contribute to existing or fill in the gaps by creating several single purpose idiomatic Clojure libraries.   

**Why is this project important to the Clojure community?**  Apache Lucene is a treasure trove of a battle tested functionality for 
building search and data analysis applications. As of now, there are few ok'ish Lucene wrapper 
options e.g. https://github.com/jaju/lucene-clj. But Apache Lucene is already a dependency in 
multiple Clojure systems such as Datomic, Crux, etc. I wan't to spend some quality time going 
through the use cases and expose modularise the functionality. <br>

---

## [Portfolio](https://github.com/cjohansen/portfolio): Christian Johansen  

**What are you wanting to achieve with this funding?**
I will use the funding to improve the experience for new users. Specifically I will add some self-hosted documentation such that Portfolio can guide users via its UI once set up. I will also improve Portofolio's error handling and make it provide more helpful pointers when things go wrong. If time permits, I will keep working on a recently started accessibility testing feature for Portfolio.  

**Why is this project important to the Clojure community?** 
The community has voiced a desire for more developer tooling. Portfolio is a developer tool for frontend ClojureScript work. While it is good that Clojure(Script) makes it easy to use tools built for the underlying platform (e.g. Storybook), strong native CLJ(S) tooling is necessary to fully harness the power of our amazing language and runtime, and make it an attractive alternative. My hope is that Portfolio eventually is one piece of what makes ClojureScript the greatest frontend toolkit out there.


