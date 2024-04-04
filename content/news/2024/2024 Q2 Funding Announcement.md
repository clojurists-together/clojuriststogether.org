---
title: "Q2 2024 Funding Announcement"
date: 2024-04-01T08:30:00+08:00
summary: "We are funding 7 projects for a total of $35K USD in Q2 2024."
author: Kathy Davis


---
Clojurists Together is excited to announce that  we will be funding 7 projects in Q2 2024 for a total of $35K USD (3 for $9K and 4 shorter or more experimental projects for $2K). To date in 2024, we are funding 25 projects or developers for a total of $223,000 USD. This includes the 8 long-term developers whose project work we are funding for the year. We expect to be able to fund another 7 projects this year for a grand total of $258,000. Thanks to all our members for making this happen!

Based on our recent survey results we know that our members and larger Clojure community rely on this work on a regular (most on a daily) basis.  If we are able to engage more companies or individual developer members, we could support even more important work - so please get the word out.  

As usual, we received a LOT of great proposals - so the decision-making was not easy. However, proposals are returned to the pool for another 2 rounds for consideration. We're looking forward to developer updates over the next 3-6 months! Here is a general overview of what each developer plans to work on.

## $9K Projects
[Clj-kondo, Babashka, Squint/Cherry: Michiel Borkent](#clj-kondo-babashka-squintcherry-michiel-borkent)  
[Compojure-api: Ambrose Bonnaire-Sergeant](#compojure-api-ambrose-bonnaire-sergeant)  
[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)  

## $2K Projects  
[Clj-merge tool: Kurt Harriger](#clj-merge-tool-kurt-harriger)  
[Enjure: Janice A. Carr](#enjure-janet-a-carr)  
[Lost in Lambduhhs Podcast: L. Jordan Miller](#lost-in-lambduhhs-podcast-l-jordan-miller)  
[Plexus: John Collins](#plexus-john-collins)   

## Clj-kondo, Babashka, Squint/Cherry: Michiel Borkent
Work for Q2 2024 project will include:  
**Clj-kondo:** support Clojure 1.12, other Clojure dialects, most wanted open tickets  
**Babashka:** Support new Clojure 1.12 (interop) features, better error messages  
**Squint / cherry:** source map support, better nREPL support, better CLJS compatibility  <br>

---

## Compojure API: Ambrose Bonnaire-Sergeant
compojure-api was a popular and actively maintained library to use for web projects around 2014-2018. While its creators have moved on to reitit, there are likely many existing/legacy commercial projects that rely on compojure-api. Lack of support for "old" or "abandoned" libraries is a common pain point for Clojure users, and a common complaint about Clojure in the annual surveys. My focus for this project:   

**Deploy first releases since 2019**  
Since 2019 compojure-api (and ring-swagger, a sister project) have not seen releases since 2019, as of the time of writing. There are several issues that warrant new releases. 
- compojure-api 1.x   
  -- vulnerable dependencies   
- compojure-api 2.0.0-alpha* branch 
  -- release stable 2.0.0   
  -- finalize breaking changes from 1.x => 2.x   
- ring-swagger   
  -- include critical memory leak fix https://github.com/metosin/ring-swagger/pull/148  
  -- compojure-api will need to bump its ring-swagger version https://github.com/metosin/compojure-api/issues/454  
 
**House cleaning**  
- Review documentation, README's, templates, and wikis for outdated information that have gone stale over the last 5 years.  
- Review, update and add tests to example projects.  
-  Update CI, linting, and similar tooling that might be applicable.   
- Triage open issues on compojure-api and ring-swagger. Review open PRs.   
- Update tutorial https://github.com/metosin/compojure-api/wiki/Tutorial - don't assume knowledge of compojure.     

**compojure-api => reitit migration tools**    
I believe many compojure-api users now use reitit for new projects (as recommended in the compojure-api README), but also have old projects that they wish they could convert to reitit. There are significant performance benefits to using reitit but not all compojure-api projects can be ported since compojure routing is more dynamic than reitit. Even if possible, manually migrating from compojure-api to reitit is error prone and difficult to do incrementally. I would like to build tools to aid converting an existing compojure-api project to reitit.  

**Swagger 3.0**  
I would like to investigate what is necessary for supporting Swagger/OpenAPI 3.0 and implement it if time permits. https://github.com/metosin/ringswagger/issues/121   <br>

---

## Jank: Jeaye Wilkerson  
At this point, jank has over 80% syntax parity with Clojure. Library parity with `clojure.core` is around 20%. The next step is to fill out the language to make it feel more like Clojure. There are just a few things remaining  which show up in every Clojure program which I will address in  Q2 2024 project:  
**Lazy sequences**    
Once we have these, we can implement all sorts of core function goodies, lazy like a cat.  
**Loop/recur**   
jank only has function-level `recur` right now, but `loop` is even more common.   

**Destructuring:** This one's actually done at a macro level, but right now `let`, `defn`, and friends don't support any destructuring.  

**Symbol**  
interning This is a smaller one to tackle, but it's important for reaching parity. Clojure allocates all symbols separately right now, rather than interning them (though keywords and vars are interned).  

**Bonus for and doseq magic**  
If there's time, I'll implement all of the craziness that go into the `for` and `doseq` macros. All of this will get me where I want to be to start focusing on tooling, distribution, error handling, more robust interop, and overall usability. `clojure.core` bits will fill in over time and after this quarter we'll have even more of the essentials in there.<br>  

---

## Clj-merge tool: Kurt Harriger  
Clojure syntax and style tend to create unnecessary merge conflicts due to bunching of ))) on single line for non-local changes. Manual resolution is often not performed by same person that wrote it and may not be well understood or (re)tested after merge resulting in errors such as moving a form in or out of a else or block changing the code behavior. My project will focus on developing a  git diff and merge tool for edn and clojure code with the aim of creating a git mergetool that can be used as a replacement for git's default merge tool for clj(s) and edn files.  

 Git's line based merge algorithms can result in frequent and unncessary conflicts due changes in whitespace and indentation.   

**What will clj mergetool do differently?**  
 clj mergetool diffs the data structures rather then the lines of text, as a result conflicts resulting from whitespace and indentation changes are far less likley. One such structural diff/patch implementation used successfully in practice is editscript (https://github.com/jujiio/editscript).  
 
 However, these structural edits do not preserve whitespace and a single line of code of output even reformatted is not good enough for source code. However, building on rewrite-clj and the associated zipper structure I believe it is possible to preserve essential whitespace when applying structural changes.<br>

 ---


## Enjure: Janet A. Carr  
Since this is an experimental project, the focus should be on what might constitute a 'minimum-viable-project'(MVP) to demonstrate the value the Enjure aims to deliver, in accordance with the guiding principles.   

The first guiding principle "Easy to get going, easy to keep going" alludes to my starting: point: The Enjure CLI tooling, since it'll be the first experience all developers will have with it. Largely, I think the Enjure CLI should be able to create new projects and (view/controller) templates as well as delete templates. Migrations are a much bigger task I imagine, and probably outside the scope of an MVP.   

Naturally, This implies creating a basic view and controller system. Hardly a compelling idea, but the guiding philosophy of "A holistic approach" gives us a hint as to how it should function. Essentially, each constituent component of the system should have a defined _functional interface_ such that other components can interact with the system in a preordained manner. For example, consider the Enjure CLI, it should be entirely possible for the Enjure CLI to be a 'view' unto itself, AND should be easy for a developer to use the same functional interface the CLI might use to create a GUI to manage the Enjure project. <br>  

---

## Lost in Lambduhhs Podcast: L. Jordan Miller  
This proposal sets forth a strategic plan to rejuvenate the "Lost In Lambduhhs Podcast," an interview-style technology show for engineers and people working in tech. Through free-form conversational style questions the audience gets the opportunity to "meet the person behind the github".   

The goal of each episode is to illuminate the personal narratives and insights of tech luminaries, giving them a platform to share their perspectives while promoting their library or tool. Following a hiatus necessitated by the extensive demands of editing and production, the ambition is to leverage the learnings from the initial seasons, alongside the latest advancements in SaaS platforms and generative AI tools.   

My strategy aims to streamline my production process, elevate content quality, and achieve consistent episode releases. "Lost in Lambduhhs" has already cultivated a loyal audience by uncovering the human stories beneath technological breakthroughs. Now, with advancements in technology and renewed funding, I am excited for the opportunity to refine my production strategy, rendering the crafting of compelling content both more efficient and sustainable. <br>  

---

## Plexus: John Collins  
I will be developing a solid modelling library called Plexus (https://github.com/SovereignShop/plexus), as well as dependent libraries that bring the full power of Manifold (a state-of-the-art CSG modelling library written in c++) to the Java and, most importantly, Clojure ecosystems. Plexus is an effort to define a declarative data model for solid modelling on top of a robust CSG foundation and combine that with the unmatched interactive development experience that Clojure provides.  

The library is usable today. The goal of this work will be to (1) smooth out rough edges and make plexus an approachable, hardened, well-document, well-presented, and well-tested library suitable for consumption by non-experts and (2) extend the functionality to make it more powerful (and possibly more extensible) than it is today.   

**Here is a list of some of the tasks I hope to accomplish in rough order of priority:**
1. Clj-docs for Plexus   
2. Extesive tests  
3. Tutorials, including video tutorials.   
4. Improved error handling and schema validation.   
5. Examples gallery (mostly collected from things I've already built with it).   
6. Substial improvement to the Loft algorithm to make it more general and potentially replace a lot of union operations.   
7. Addition of text support to Manifold.   
8. Three-point arcs.   
9. Bezier curves.  
10. Support for more Manifold operations (SmoothOut, etc.)   
11. (Aspirational/experimental) Topology Projections, allowing the user to navigate and "draw" on the surface of arbitrary manifolds.  
12. ClojureScript Support (via. emscripten build of Manifold)  








