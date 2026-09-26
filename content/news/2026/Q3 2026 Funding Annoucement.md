---
title: "Q3 2026 Funding Announcement"
date: 2026-09-25T14:00:00+12:00
author: Kathy Davis
summary: "4 projects are awarded a total of $22K"
draft: True



---

Clojurists Together is excited to announce that we will be funding 4 projects in Q3 2026 for a total of $22K USD (2 for $9K and 2 shorter or more experimental projects for $2K).  

Thanks to all our members for making this happen! You'll find more information about the proposed projects below. 

Congratulations to our awardees. We're looking to their great work ahead! 

### $9,000 USD  
- Jeremiah Coyle: Documentation and Design Services for Key Community Projects: Malli, Squint, Flowstorm, ECA  
- Ethan Miller: Tablecloth  

### $2,000 USD  
John Newman: EVE (Extended Value Encoding)  
Peter Toausannis: Finish Carmine 4    <br>


## Jeremiah Coyle: Documentation and Design Services for Key Community Projects: Malli, Squint, Flowstorm, ECA   
The goal of this project is to significantly improve the documentation and marketing of four established community projects.  
1. [Malli](https://github.com/metosin/malli) Malli is high-performance data-driven data specification library for Clojure(Script). The team behind Malli (Metosin) has recently leveraged cljdoc to create a well-organized, stand-alone documentation site for Reitit, powered by markdown files from the Reitit repo. The goal of this funding is to achieve a similar outcome for the official Malli documentation, while improving the general structure, narrative, and getting started guides.   
2. [Squint](https://github.com/squint-cljs/squint) Squint is a light-weight dialect of ClojureScript with a compiler and standard library. Project goals: 1) The creation of a professionally designed, easily maintainable project site. 2) Transformation of the Squint Playground into a polished, focused ancillary devtool for trying, debugging, compiling, and previewing code.   
3. [FlowStorm](https://www.flow-storm.org/) FlowStorm is an omniscient time-travel debugger for Clojure(Script). Project goals: 1) Explore tightening the look and feel of key debugger UI views. 2) Take the project site to the next level (building off the improved baseline UI presentation). 3) Enhance the flow and structure of the official documentation, with a focus on reducing friction for first-time users.   
4. [ECA](https://eca.dev/) Project goals: Editor Code Assistant is a free, open-source, editor-agnostic tool that connects LLMs to your editor through a well-defined protocol. 1) Replace the existing project site with a more considered treatment of the UX writing, typography, iconography, animations, and layout. 2) Clean up and improve on the documentation, working within the constraints of the mkdocs-material framework that the ECA project is currently using. 3) Discover and identify key improvement area's in the tool's UX/UI (time allowing).   <br>

---

## Ethan Miller: Tablecloth
**Tablecloth Column API, Phase II**  

This project continues my earlier Clojurists Together work that added a Column API to tablecloth, a core library in Clojure's data science toolkit. The goal is to make the Column API
more capable and easier to use. Planned work includes refactoring the implementation so it's easier to maintain, improving the documentation, and adding more operations from
underlying libraries such as dtype-next and fastmath. I also plan to write a tutorial that shows the API on a real data-processing task. The exact scope may change as the work goes on, in conversation with the Scicloj community.  

**2. Enhancing Column API Capabilities:** To expand the epxressive range of the the Column API, I will introduce a range of new operations sourced from various operational sets. Likely candidates for inclusion are dtype-next, offering valuable operations for indexing space, advanced reductions, and datetime conversions. Moreover, the scicloj community has expressed enthusiasm for integrating fastmath's math utilities into the Column API, specifically highlighting features like [linear regression and statistical summaries](https://clojurians.zulipchat.com/#narrow/channel/151924-data-science/topic/composing.20fastmath.20and.20tech.2Eml.2Edataset/near/476450931).

**3. Tutorial Development:** To raise awareness of the Column API's capabilities, I aim to craft a clear, concise tutorial highlighting its power and utility for users. The tutorial will be shared in strategic venues such as Scicloj meetings, including the real-world data meeting, and potentially at relevant conferences to maximize outreach.  <br>  

---

## John Newman: EVE (Extended Value Encoding)  
This project is related to improving EVE (the Extensible Value Encoding) so that it better serves the Clojure community. This includes improving the Clojure implementation (the CLJS
version came first), improving user documentation, getting the library to a "1.0" status and other related tasks.  <br>  

---

## Peter Taoussanis: Finish Carmine v4  
Finally release the first publicly usable version of Carmine v4!

Carmine is a mature Redis client for Clojure v4 is a complete rewrite with highlights including RESP3 (modern Redis protocol) support, better performance, better connection management, and support for both Redis Sentinel and
Cluster. Also a much better message queue. I've been working on v4 for years, it's been a massive undertaking. The core's been basically done for a while, and recently finished draft Sentinel + Cluster support, plus a major redesign
of the message queue. Now need one more big push on testing, documentation, and polish to get the first public prerelease out. Estimate 1-3 more months of work, then the usual ongoing support after that.  










