---
title: "Clojurists Together 2026 Annual Funding Announcement"
date: 2025-12-16T14:00:00+12:00
author: Kathy Davis
summary: "Clojurists Together is funding 5 developers $1.5k/month for 12 months"
draft: True  


---

The votes are in!! Clojurists Together members have voted to to fund 5 developers $1.5k/month for 12 months ($90k USD total!). We're pleased to announce the following developers/teams who will be funded:


[Bozhidar Batsov](#bozhidar-batsov)   
[Michiel Borkent](#michiel-borkent)   
[Clojure Camp](#clojure-camp)   
[Eric Dallo](#eric-dallo)   
[Jeaye Wilkerson](#jeaye-wilkerson)    
  

We are very excited to see what these developers will do over the next year. They will share updates of what they've been working on, and we'll post and email them as part of  our regular updates. You'll find summaries below of what each of these developers plan to work on (although they are just a starting points and they're free to change their plans as they see fit). If you want more detail, you can refer back to the proposals submitted on our [website](https://www.clojuriststogether.org/news/vote-on-2026-annual-funding/). 

Thanks to the all of you - the Members -  who took the time to review the applications and make the difficult decision to select just 5 from many excellent proposals. Congratulations are in order to Bozhidar, Michiel, Eric, Jeaye, and the Clojure Camp team! For everyone else who submitted their projects, there will be more grant opportunities in 2026 - so keep posted. 


### Bozhidar Batsov  
https://github.com/bbatsov

Continuously improving CIDER, nREPL, clojure-ts-mode and their related projects. Building a formal nREPL specification is one of the bigger challenges I hope to tackle next year. As is fully decoupling clojure-mode from CIDER.  
Most of the projects are hosted under the following GitHub orgs:  
https://github.com/clojure-emacs
https://github.com/nrepl   

 

### Michiel Borkent  
https://github.com/borkdude

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



### Clojure Camp  
(Rafal Dittwald, Jordan Miller, and others)   
https://clojure.camp  

**Continue various existing Clojure Camp projects:**   
- supporting new developers to attend Conj and other Clojure conferences (travel bursaries, and or/ organizing and subsidizing shared accommodation)  
- continue our peer-to-peer pairing program, with a new goal to also appeal to existing Clojurians  
- continue offering weekly online vents (mobs, office hours, etc)  
- improve communication of our offerings (by regularly posting)   

**Explore new Clojure Camp projects:**  
- develop our "curriculum map" into a learning roadmap and badge system   
- facilitate a mentorship program, pairing Clojure OSS devs with other devs (junior and senior)   
-  experiment with hosting "micro-conjs" (6-10 person, in-person weekend coding retreats)   



### Eric Dallo  
https://github.com/ericdallo  

[**ECA (Editor Code Assistant)**](https://github.com/editor-code-assistant/eca)    
This project was born 5 months ago (with clj-together's help!) and it's growing, there are so many improvements to this project related to features and parity with other big tools like Claude/Cursor, compatibility with more editors, fix and support community issues. 
This AI tool is written in Clojure but works in any language which is huge for discoverability of Clojure community outside Clojure bubble, it has a lot of potential in my opinion, and projects for each editor to maintain and evolve.  

[**Clojure-lsp**](https://github.com/clojure-lsp/clojure-lsp)    
I want to work on custom code actions, support for new clojure.java.javadoc lib, improve java interop support like go to class locations, methods etc, lots of this would require me working in clj-kondo too  

[**metrepl**](https://github.com/ericdallo/metrepl)  
I want to help with guide and examples of metrics of your repl and how that can help companies understand developers REPL usage  
  
  

### Jeaye Wilkerson    
https://github.com/jeaye  

I will be working full-time on [jank.](https://github.com/jank-lang/jank)  

jank's alpha release is going out in December 2025, which will lead us into a hectic start to 2026 as we'll be collecting/fixing as many bugs as we can. Meanwhile, I will be developing jank's build system for native system dependencies, improving stability, significantly improving performance, and adding the remaining features to achieve strong Clojure parity (records, protocols, futures, etc). Furthermore, I will be authoring the jank book, which will be a free standalone resource for learning jank (and Clojure), inspired by and very similar to the Rust book.  

I will also continue to lead the clojure-test-suite initiative, which provides thorough unit test coverage for all core Clojure functions and is currently serving Clojure JVM, Clojure CLR, ClojureScript, babashka, and jank. I aim to capture ClojureDart and Basilisp in 2026 and I have already started discussions with the creators of both dialects.  

I am currently mentoring four different open source contributors, who're learning to be compiler hackers, and I aim to pick up a fifth during 2026. I'm specifically looking to add a Clojure lady to the gang and I began outreach for this at the Conj.  

Additionally, I will continue streaming on Twitch regularly, to raise Clojure awareness. I will generally use that time to either develop jank itself or to use jank to develop Clojure-related tooling.  

Overall, my goal for EOY 2026 is production readiness for jank. This is an aggressive goal, but I will work aggressively on it.  


## Thanks
Thanks again  to all of our members for selecting these developers. If it wasn't for your generous support, this wouldn't be possible. A special thanks goes to Latacora, Roam Research, Whimsical, Nubank, Cisco, JUXT, Metosin, Solita, Grammarly, Nextjournal, ClojureStream, Shortcut, Flexiana, Toyokumo,doctronic, and 180° Seguros. They have all contributed significant amounts to Clojurists Together which helps us award $90,000 in long-term funding to Clojure developers in the coming year.   
