---
title: "June 2025 Short-Term Project Updates Q2 2025 Projects"
date: 2025-06-26T14:00:00+12:00
author: Kathy Davis
summary: "News from CALVA, CIDER, Jank, SciCloj Bridge to New Users"
draft: True


---
This is the June project update for four of our Q2 2025 Funded Projects. (Reports for the other two are on a different schedule). A brief summary of each project is included to provide overall context.

[Brandon Ringe: CALVA](#calva-brandon-ringe)   
A new REPL output view for Calva, which is a webview in VS Code. The webview will allow us to add more rich features to the output webview, while also providing better performance.  

[Bozhidar Batsov: CIDER](#cider-bozhidar-batsov)   
Provide continued support for CIDER, nREPL and the related libraries (e.g. Orchard, cidernrepl, etc) and improve them in various ways.  

[Jeaye Wilkerson: Jank](#jank-jeaye-wilkerson)     
Build jank's seamless C++ interop system.   

[Siyoung Byun: SciCloj Building Bridges to New Clojure Users](#scicloj-building-bridges-to-new-clojure-users-siyoung-byun)
Scicloj aims to improve the accessibility of Clojure for individuals working with data, regardless of their programming backgrounds. The project aims to develop standardized templates to encourage greater consistency across the documentation of existing Scicloj ecosystem libraries, making those libraries more robust and user-friendly.  <br>




## CALVA: Brandon Ringe  
Q2 2025 $9K. Report 2. Published June 16, 2025.  

Since the last project update, several improvements have been made to Calva's new [output view](https://calva.io/output-view/).  

One of those improvements is a significant performance boost with high frequency output, such as logging to stdout hundreds or thousands of times within a minute. I realized that replicant (and rendering libraries like it), which is an awesome library, is not well-suited for this kind of use case. So I switched to using the DOM API directly, which is much faster and more efficient for this purpose.  

Here's a list of the changes to the output view since the last project update:  
- Fix: [Whitespace is not preserved in non-code output in output view](https://github.com/BetterThanTomorrow/calva/issues/2825)  
- [Add padding to bottom of output view](https://github.com/BetterThanTomorrow/calva/issues/2824)  
- [Strip ANSI encoding in output view](https://github.com/BetterThanTomorrow/calva/issues/2837)  
- [Label evaluated code in output view so it's easy to differentiate it from results](https://github.com/BetterThanTomorrow/calva/issues/2839)  
- Fix: [Some output from stderr and/or stdout is being broken up in the output view](https://github.com/BetterThanTomorrow/calva/issues/2847)  
- Fix: [Extra newline is printed before error output in output view](https://github.com/BetterThanTomorrow/calva/issues/2846)  
- Fix: [Performance of output view degrades as content grows](https://github.com/BetterThanTomorrow/calva/issues/2852) (This is the change mentioned above, which removes replicant and uses the DOM API directly for rendering.)  
- [Add copy button to code snippets in output view](https://github.com/BetterThanTomorrow/calva/issues/2836)
- Fix: [Some output is not printed on a newline as expected in the output view](https://github.com/BetterThanTomorrow/calva/issues/2858) <br>


---


## CIDER: Bozhidar Batsov  
Q2 2025 $9K.  Report 2. Published June 17, 2025.  
The last month was another strong one for CIDER and friends, which featured many releases across the different tools and libraries. Below are some of the highlights:  

- [inf-clojure 3.3](https://github.com/clojure-emacs/inf-clojure/releases/tag/v3.3.0)
    - This was the first inf-clojure release in 3 years
    - It adds support for `clojure-ts-mode` and also features some fixes and small improvements  
- [clojure-mode 5.20](https://github.com/clojure-emacs/clojure-mode/blob/master/CHANGELOG.md#5200-2025-05-27)  
	- Adds `clojuredart-mode` and `jank-mode` for better support of ClojureDart and Jank  
	- Also features a couple of small fixes  
- [clojure-ts-mode 0.5](https://github.com/clojure-emacs/clojure-ts-mode/releases/tag/v0.5.0) was the biggest news in the past month for several reasons  
	- It uses an experimental version of the Clojure Tree-sitter grammar that allows us to deal better with Clojure metadata  
	- It features support for font-locking embedded JavaScript code in ClojureScript (and C++ code in Jank)  
	- Introduces the `clojure-ts-extra-def-forms` customization option to specify  additional `defn`-like forms that should be treated as definitions (as it's hard to reliably infer those)  
	- It features some simple built-in completion powered by Tree-sitter
		- I blogged about the concept [here](https://emacsredux.com/blog/2025/06/03/tree-sitter-powered-code-completion/)  
- CIDER also saw some work, but no new release. You can expect in the next release:  
	- better support for `clojure-ts-mode`  
	- more improvements to the Inspector  
	- user manual (documentation) improvements  

On top of this, I've been reviewing the data from CIDER's survey, improving the CI setups of various projects, providing user support and working on some ideas about restructuring the documentation of CIDER and nREPL to be more approachable. The process for streamlining (slimming down) CIDER, cider-nrepl and orchard is ongoing as well. (e.g. we've stopped bundling print engines with cider-nrepl and now those have to be supplied by the users)  

`clojure-ts-mode` is shaping up really nicely and has mostly matched the features of `clojure-mode`. On top of this it does a few things better than `clojure-mode`, so I'm optimistic that we'll mark it as ready for general usage before the end of the year. We've expanded the [design docs](https://github.com/clojure-emacs/clojure-ts-mode/blob/main/doc/design.md) recently and I think they can be useful both to potential contributors and to other people looking to create Tree-sitter based major modes. I'm also working in the background on a couple of article for best practices when working with Tree-sitter in Emacs.  

Thanks to Clojurists Together for their continued support of my OSS work! You rock!  <br>


---


## Jank: Jeaye Wilkerson  
Q2 2025 $9K. Report 2. Published June 17, 2025.  

### Thank you!  
Hi folks! Thanks so much for the sponsorship this quarter. Clojurists Together is my largest form of income this year, which makes this even more special.  
 
### Seamless interop  
In the past month, I've implemented:  
* Many, many tests for the new interop functionality  
* Calling global C++ functions  
* Calling C++ member functions  
* Accessing C++ member variables  
* C++ reference semantics, including automatic referencing and dereferencing  
* Dozens of C++ operators  
 
You can find all of the details in my latest blog post, [here](https://jank-lang.org/blog/2025-06-06-next-phase-of-interop/).  
 
### Next up  
In the final month of this quarter, I aim to expand the test suite, fix the
remaining known bugs, add manual memory management support, better template
support, full destructor support, and finally the portability fixes necessary to
make all of this work on both macOS and Linux.  <br>  


---


## SciCloj Building Bridges to New Clojure Users: Siyoung Byun  
Q2 2025 $2K. Report 1. Published June 25, 2025.

### Work Completed, In Progress and Further Plans  

### CFD Python into Clojure Project   
I initiated the [CFD Python into Clojure](https://github.com/scicloj/cfd-python-in-clojure) 
project, which translates computational fluid dynamics (CFD) learning steps from Python 
into Clojure. The project also includes a currently evolving [notebook page](https://scicloj.github.io/cfd-python-in-clojure) that shows the learning steps progressed so far for the project.  
This project showcases interactive, real-world scientific computing examples 
with an initiative to diversify the set of data science use cases available in Clojure.  

I am working toward completing and polishing the project as a featured example of Clojure's 
capability in handling scientific computing and numerical simulation use cases. I am planning to discuss the results and experiences at a future SciCloj Light Conference.  

### Conference Presentation at SciCloj Light #1  
I presented [a talk](https://scicloj.github.io/scinoj-light-1/sessions.html#d-viscous-fluid-flow-data-analysis-using-burgers-equation) at [SciCloj Light #1](https://scicloj.github.io/scinoj-light-1/), 
highlighting the progress (implementing one-dimensional CFD using Burgers' equation), simulations, and future directions from the CFD Python into Clojure initiative. 
The talk demonstrated how Clojure can serve as a powerful tool in data science and data analysis.  

### Conference Reflections and Scicloj Community Video  
I co-authored and released [a video](https://youtu.be/n6ICeRyXHsI?si=tzq_ZKzuRqux6nKQ) 
summarizing the SciCloj Light #1 conference experience, along with publishing 
a written conference survey to gather feedback and reflect on future SciCloj Light conferences with [Daniel Slutsky](https://github.com/daslu). 
The video discusses the preparation process, key takeaways, and future directions 
for Scicloj as a community-driven initiative and as an individual contributor.  

### Community Outreach Initiative for Broader Participation  
I initiated and participated in a community discussion on [Clojurian Zulip](https://scicloj.github.io/docs/community/chat/) 
to encourage Scicloj contributors and users to present at external data science conferences 
and share their experiences using Clojure and Scicloj libraries for their data science work. 
This outreach aims to amplify Clojure's visibility in broader scientific and data communities  to obtain more attention and bring newcomers to the community.   
 
### Documentation Improvements for Noj  
I joined as one of the maintainers of [Noj](https://github.com/scicloj/noj), an out-of-the-box  Clojure library designed to simplify data science workflows. 
Currently, I've been focusing on improving the library's introductory documentation, setup guides, and [use cases in notebooks](https://scicloj.github.io/noj/) to make it more welcoming and useful to newcomers.  
There are already good use cases, ideas, and documentation in the library, so my main goal is to make those resources more coherent, consistent, and organized to be easily searchable. 
Once we have well-tailored documentation in place for Noj, we hope to roll out 
the same documentation template for the rest of the Scicloj libraries.  

#### Library Documentation Review and Feedback Collection  
I am actively gathering feedback on gaps and pain points in the documentation of various 
Scicloj libraries through the [Clojurian Zulip](https://scicloj.github.io/docs/community/chat/) channel.  

#### Improving "Getting Started" Experiences  
I am continuing to improve the beginner documentation and setup processes across Scicloj libraries, with a goal of creating a smoother onboarding experience for users with diverse backgrounds.  

### Organizing 'macroexpand' Gatherings  
I will co-organize a series of regular online community meetups called [macroexpand gatherings](https://scicloj.github.io/docs/community/groups/macroexpand/). 
These are aimed at welcoming both new and existing Clojure users and will foster communication across communities working on professional data projects (data engineering, analysis, visualization, etc.) 
and identify shared challenges and opportunities for collaboration.
By hosting a space to discuss the current status of the community/individuals and challenges together, we hope to prioritize our todos and initiatives better and create actionable items to move forward.  <br>



[def]: #scicloj-building-bridges-to-new-clojure-users-siyoung-byun