---
title: "Vote on 2025 Long-Term Funding Applications"
date: 2024-11-16T14:00:00+12:00
author: Kathy Davis
summary: "It's Up To You to Decide Who Will Receive Funding. "  
draft: True


---

Greetings Clojurist Together Members! This year, we are trying a new process to determine who will receive a monthly stipend of $1,500 to support their development work. 

We put out the call  - and we received 18 thoughtful applications for you to consider. You can also review past long-term project updates on our [website](https://clojuriststogether.org) to get an idea of what past long-term grant recipients have been able to accomplish with your past support (pretty amazing!)

Please review the applications below and select 6 developers to receive funding in 2025. Be on the lookout for an email that contains your link to a Ranked Vote ballot. Here we go ..in alphabetical order (by last name)....

### Deadline for your vote to be counted: December 4, 2024 Midnight Pacific Time  



-[Michiel Borkent](#michiel-borkent)  
-[Thomas Clark](#thomas-clark)  
-[Jeremiah Coyle](#jeremiah-coyle)  
-[Toby Crawley](#toby-crawley)  
-[Eric Dallo](#eric-dallo)  
-[Rafal Dittwald](#rafal-dittwald)  
-[Dragan Djuric](#dragan-djuric)  
-[Robert Gersak](#robert-gersak)  
-[Kira Howe](#kira-howe-mclean) (McLean)  
-[Jack Rusher](#jack-rusher)  
-[Roman Liutikov](#roman-liutikov)  
-[Mateusz Mazurczak and Anthony Caumond](#mateusz-mazurczak-and-anthony-caumond)  
-[Adrian Smith](#adrian-smith)  
-[Dan Sutton](#dan-sutton)  
-[Daniel Slutsky](#daniel-slutsky)   
-[Peter Strömberg](#peter-strömberg)   
-[Peter Taoussanis](#peter-taoussanis)   
-[Oleksandr Yakushev](#oleksandr-yakushev)   <br>

---


## Michiel Borkent
https://github.com/borkdude

**What do you plan to work on throughout the year?** 
My main projects are clj-kondo, babashka / SCI, and squint/cherry. I will develop new features, specified below per project. As usual I'll also work on ongoing maintenance and offering support on Slack and Github.

Links:
[clj-kondo](https://github.com/clj-kondo/clj-kondo), [babashka](https://github.com/babashka/babashka), [SCI](https://github.com/babashka/sci), [squint](https://github.com/squint-cljs/squint)  

#### [Clj-kondo](https://github.com/clj-kondo/clj-kondo)  
- Linter for partially extended protocols
- Extend :discouraged-var to allow specifying arities
- Ongoing maintenance, there's always plenty to do! See the project board and see here for a list of most upvoted issues
- Experiment with adding the notion of a project classpath such that command line linting becomes easier. Possibly configurations could be read from the classpath as well.
- As clj-kondo is the foundation for the static analysis in clojure-lsp: improvements in the interaction between the two, most specifically the analysis data.

#### [Babashka / SCI](https://github.com/babashka/babashka)  
- Maintenance of included bb libraries: SCI, babashka.fs, babashka.process, deps.clj, babashka.http-client, edamame
- Babashka tasks: prefix parallel prcoess output such that you can distuingish tasks
- Better error locations for clojure.test output (https://github.com/babashka/babashka/issues/1518)
- Report exception causes in console error report (https://github.com/babashka/babashka/issues/1515)
- Several other issues: https://github.com/babashka/babashka/issues

#### [Squint (and cherry)](https://github.com/squint-cljs/squint)  
- Browser REPL support
- Source map support (some work has been done, but far from finished)
- Increase overall compatibility with CLJS
- Support dependencies from the ClojureScript ecosystem from clojars and git depsAll other related projects
- Nbb, jet, scittle, neil, edamame, rewrite-edn, carve   

**Why is this work important to the Clojure community?**
Babashka is used by 90% of Clojure Survey respondents as shown in Alex Miller's Conj 2024 talk. Babashka and clj-kondo have 4.1k and 1.7k stars on Github respectively, their slack channel on Clojurians have almost 1800 + 1000 users. Clj-kondo and babashka are used by a large portion of the community. I think it's fair to say they are established project.  

**Is there anything else you would like us to know?** Thank you so much for Clojurists Together and keeping the Clojure OSS ecosystem sustainable!  <br>  

---

## Thomas Clark  
https://clojurians.zulipchat.com/#user/386018  

**What do you plan to work on throughout the year?** 
In a nutshell, my plan would be to help expand [Clojure's scientific ecosystem](https://www.youtube.com/watch?v=_D5d6Ls6pBw), particularly in regards to the mathematical sciences. This would take the form of developing and creating libraries, continuing and creating documentation and to initiate a serious attempt at academic outreach.

#### Libraries

#### Wolframite  
Last year, CT supported Jakub Holy and I for a quarter in our attempt to resurrect and document the Wofram-Clojure bridge. We're very happy with the progress we made in this time, but of course there are many other things that could (and should?) be done - we really feel like we're just getting started. In particular, a key feature of Wolframite, that is missing in Wolfram itself, is the REPL experience. 

In this, we want to fully integrate Wolframite with Clojure's visual tools for all manner of datatypes that Wolfram supports. And in particular, to make data passing and memory management efficient. More functionally, we want to create a special viewer for symbolic expressions, that not only allows the user to copy and switch between maths in Wolfram, LisP and TeX forms but that automatically generates sliders for each parameter for exploration, as inspired by [Wolfram's Manipulate function](https://reference.wolfram.com/language/ref/Manipulate.html).

#### exp (name to be decided)  
In Clojure, there are now two libraries to interact with symbolic expressions and this new project would seek to integrate them: both Wolframite and Emmy. This would be powerful because although Emmy brings symbolic manipulation to Clojure and Clojurescript simultaneously, it is still missing many key features and algorithms that only Wolfram can provide. One can then imagine a situation where mathematical problems could be defined and explored in the browser (Emmy), passed to the server to be simplified algebraically (Wolframite) and then crunched numerically (Emmy).  

Not only would this bridge two existing libraries but it might even lay the foundation for developing a standard for representing equations more generally.  

#### SciCloj  
More generally, there are many SciCloj libraries that I have been involved with and so with funding I would happily step up my contributions.  

I can particularly see a lot of scope in the further development of `Clay`. I have particular notions regarding it's use for generating presentations and can imagine creating a specific API around this purpose. I can also imagine it being the first visual tool with which I could create the special equation viewer mentioned above.  

Also in regards to `tableplot`. Specifically, I would like to be involved in its generalization to deal with 3D plotting as I have a lot of specific use cases in this area.  

Another area of interest at the moment is in improving the SciCloj API for working with common matrix operations (specifically the generation and manipulation of Hadamard-like matrices). This is an open area and it's not clear whether this will be abstracted into a separate library or wheter this would be an extension of dtype-next.  

#### Documentation  
Another big area of focus would be improving SciCloj documentation, at both the library and project level.

#### Libraries  
Putting aside the libraries that I have contributed code to, there are many SciCloj libraries that I use that I would be happy to contribute documentation. A bridging example would be regarding a Clojure-Blender bridge. Although I started to develop a new library for this, basilisp-blender has emerged as potentially a stronger candidate and so I would like to make detailed tutorial examples of creating 'scientific' outputs using this.  

#### Projects  
As discovered while writing the [Wolframite documentation](https://scicloj.github.io/wolframite/for_scientists.index.html), what is often needed is not so much a manual but examples of how to use the tool (or how to integrate many tools) in a real project. With this in mind, I would like to document the overall process of how to use the ecosystem to solve real problems, supplying both real problems and real solutions, e.g. the source and details behind my [past talk](https://www.youtube.com/watch?v=SE5Ge4QP4oY) and large contributions to [noj](https://github.com/scicloj/noj).  

#### Outreach  
Outside of building library bridges to Clojure for specialist academic projects as well as collecting SciCloj talk sources in a centralised location, I would make a concerted effort to reach out to non-Clojure scientists about the benefits of Clojure.  

Where I might have an advantage here is that I am a scientist before I am a programmer and so have direct connections into a world where very few people have heard of the language. With funding (see the biases section for why) I could potentially give department seminars at different institutions as well as international and online conferences: using existing academic contacts.  

Furthermore, something that I am particularly excited about, I have started to initiate a grouping of research-minded Clojurians to consider publishing papers in the field. With the right support, I think this could really help raise awareness.  

**Why is this work important to the Clojure community?**  
I guess I should really have checked the following questions before filling up the previous box... :)  

In regards to library creation and expansion, I think that scientific computing is an exciting growth area for Clojure. It is an area that really benefits from Clojure's key principles and one that already has a growing number of users: users who will benefit from the changes. It is also an area that is at a tipping point in regards to reaching practical parity with other key languages and the tools that I would like to focus on are not so much now about matching competitors, but rather about providing new features and features that lead towards a completely integrated ecosystem: something that I would like to find, no matter which language provided it!  

The Emmy system in particular is bringing open-source symbolic computation to both the back- and front-ends but it is missing key features and advanced libraries. A Wolfram-Emmy bridge could serve as a sure foundation and help create the real possibility of an almost unique physics programming space.  

This work would also be important in regards to expanding the community itself. Following on from above, there is a large section of the numerical scientific community who are not programmers but who rely on tools like Mathematica and Matlab and so interop in these areas will be crucial for community cross-over in the future. Generalized language interop is particularly important for safe onboarding of new users and experience suggests that there is a willing 'market' for integrating specialist tools within more comfortable general languages like Clojure.  

Beyond this however, the interactive development and documentation experience that is available now is a solution that simply needs to be shown to scientists' problems. The proposal to organise academic publishing and presentation of what the Clojure experience is like feels like an important stepping stone to the future.   

**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**  
Depending on what you mean by 'systemic bias', my disadvantage is a classical one. Living in eastern Europe and working in the public sector, I suspect my Clojure friends would be shocked at what is paid to scientists here. I don't pretend that Hungary is as cash-strapped as some other continental countries, but compared to most other countries in 'the west', even those a two hour drive away, the salaries are low. If it helps, I can be more specific, but suffice it to say that this funding would go much further here and would enable a much bigger shift in what I'm able to contribute than it would for many other applicants.<br>  


---

## Jeremiah Coyle  
https://github.com/paintparty  

**What do you plan to work on throughout the year?**  
These are the 5 projects that I am planning to work on throughout the year :)
All are fairly mature alpha-stage projects.  Kushi and Fireworks have already been announced. I plan to announce the  latest Fireworks release, and the other 3, over the next few weeks.

#### #1 - Kushi  
[Kushi](https://kushi.design/) is a base for building web UI with ClojureScript.  

**Goals for Kushi in 2025:**
- Universal transpiler for kushi UI components to automatically generate components for established ClojureScript UI Rendering frameworks such as Uix, Helix, Biff, shadow-grove, etc.  
- Add a quickstart repo for each supported framework Uix, Helix, Biff, shadow-grove etc., with a deluxe todo-mvp/kitchen sink example set to make it very easy to cut and paste working UI. 
- Browser-based interactive design tweaking, with round trip to file system.  
- Add more functionality, docs, and examples for working with the `kushi.playground` namespace. This gives the user a very elegant way to build their own white-labeled interactive documentation site that features Kushi’s components as well as any custom components specific to their project. It is similar to Storybook.js or Portfolio (cjohansen/portfolio). The playground namespace was used to create the current project website at kushi.design.  
- Design and implement 4-5 global themes, using a variety of styles. In order to show off the power of the new theming system, I would like to add at least a couple very playful themes (sci-fi, fantasy-rpg) and incorporate them via a theme switcher into the kushi.design site.  
- Add additional ui library components. Components followed by an asterisk are internally complete and just need to be turned into public functions with documentation:  
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

#### #2 Domo
[Domo](https://github.com/kushidesign/domo) is a modern ClojureScript DOM utility library.  

**Goals for Domo in 2025:**
- Curate the current API of approximately 75 public functions  
- Write docstrings for all public functions  
- Add simple validation and dev-time warnings for all public functions  
- Figure out best approach for automated test suite  
- Leverage kushi.playground to create interactive documentation  

#### #3 - Fireworks  
[Fireworks](https://github.com/paintparty/fireworks) is a themeable tapping library and color pretty-printing engine.  

**Goals for Fireworks in 2025:**
- Solidify current public API  
- Address all 12 current issues (mostly enhancements)  
- Publish editor plugins/extensions for Emacs and VS Code. These are fairly simple extensions that just involve some basic clj-rewrite functionality for wrapping/unwrapping forms. I've already created initial working versions of both (emacs and VSCode) locally.  
- Produce written and/or video documentation of my current live hot-reloading dev environment for JVM Clojure, with versions for both Leiningen and Deps. I recently issued a PR to add this to [test-refresh](https://github.com/jakemcc/test-refresh/pull/91). This sort of thing could also potentially be incorporated into other similar projects such as metabase/hawk and tonsky/clj-reload.  
- For ClojureScript developers using Fireworks in a browser dev console, I went off the deep-end and made a dedicated Chrome extension to enable the setting of the Chrome DevTools console background and foreground color with a very nice GUI interface. Would be cool to get this working in most other Chromium-based browsers, and potentially Firefox, if there is any demand for it. 
- https://github.com/paintparty/fireworks?tab=readme-ov-file#setting-the-background-color-and-font-in-chrome-devtools-clojurescript


#### #4 - Bling  
[Bling](https://github.com/paintparty/bling) is a library for rich text in the console  

**Goals for Bling in 2025:** 
- Support automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to https://github.com/chalk/supports-color  
- Support arbitrary hex colors, and their conversion, if necessary, to 256. I’ve already created an efficient Manhattan distance algorithm for doing [this conversion in Fireworks](https://github.com/paintparty/fireworks/blob/035ec4cb048db05f4cca3691f946931b3a87f624/src/fireworks/color.cljc#L26)    
- Create more formatting templates for callouts, headers, and points-of-interest  
- Add documentation about how to leverage Bling to create great-looking warnings and errors in any project. Example of using bling's templates to create nice warnings can be [found here:](https://github.com/paintparty/fireworks?tab=readme-ov-file#helpful-warnings-for-bad-option-values)    
- Add documentation about using Bling in conjunction with existing libraries which format Spec and Malli messages into human readable form.  
- Although lower priority, it would be fun to explore enhancing the flexibility and utility of bling.core/stack-trace-preview, and exposing it as a public function, as it may be a cheap way to get give some additional context to many kinds of error messages.  

#### #5 Lasertag  
[Lasertag](https://github.com/paintparty/lasertag) is a utility for categorizing types of values in Clojure, ClojureScript, and Babaskha.  

**Goals for Lasertag in 2025:**  
- Solidify current public API  
- Extensive coverage for all Java and Javascript types/classes  
- Extensive test suite for all types and classes  
- Leverage kushi.playground to make cool demo for usage in ClojureScript  


**Why is this work important to the Clojure community?**  
I believe work on these projects could benefit the Clojure community for the following reasons (listed out project-by-project):

[**#1 - Kushi**](https://kushi.design/)    
Kushi aims to provide a complete solution to the design layer of web UI projects.  

It is an ambitious project, but I believe there is an opportunity for Kushi to offer a UI dev experience that is unique and more compelling than any equivalent in any other language. This could lead to increased organizational awareness and consideration of ClojureScript as an attractive choice for building mission-critical UI. If more companies used ClojureScript to build UI, I think it would be very beneficial for the community.  


[**#2 - Domo**](https://github.com/kushidesign/domo)    
This lib has been slow-cooking over a couple years to serve various web projects, including Kushi.  

The syntax is very Clojure-y, and feels much nicer than writing gnarly JS interop code.
To my knowledge, there are only a few ClojureScript DOM libs (Dommy, Enlive, Enfocus), and all were written 9-10 years ago.  

Compared to these libs, Domo offers even more specific functionality such as:  
- copy-to-clipboard  
- Getting viewport information  
- Geometry for elements  
- Getting screen quadrant for element or event  
- Attribute querying and manipulation  
- Computed styles of elements  
- Adding, removing, and toggling attributes and classes  
- Optional zipper-like syntax for selecting elements  
- matches-media? helpers  
- a11y-friendly on-mouse-down helper (faster alternative to on-click)  
- Helper for keyboard-based tab navigation  
- & much more!  

[**#3 - Fireworks**](https://github.com/paintparty/fireworks)    

Fireworks is the only lib to provide a colorizing and themeable pretty-printer that works beautifully in both Clojure and ClojureScript (and Babashka).  

The output is, arguably, orders of magnitude faster and easier to read than equivalent output from clojure.pprint/pprint. This is especially true in the case of maps with data-structures as keys, or any kind of collection that features metadata.  

The library also provides very powerful debugging and tapping macros. These macros provide a lightweight complement to discovery-centric, UI-heavy data exploration tools such as Portal and Flowstorm. With a simple hot-reloading setup (in clj or cljs), and minimal editor integration, Fireworks can drive an extremely compelling live-feedback dev experience without any reliance on a repl connection, or repl-related concepts. I think the continued codification, documentation, and demonstration of such a workflow could make a difference in bringing new people to Clojure. The maturation of this kind of workflow could also benefit existing users of community tools such as playback, debux, hashp, telemere, ken, spyscope, omni-trace, postmortem, and sayid.  
 

[**#4 - Bling**](https://github.com/paintparty/bling)    

Many mature language communities have a library for rich text printing in the terminal, for example Rich (Python), Chalk (JS), LipGloss (Go), etc.  

With Clojure, the main existing option seems to be org.clj-commons/pretty. The original impetus for creating Bling came out of my experience of trying to use this lib extensively for formatting error and warning messages.  

The most unique thing that Bling offers is a carefully curated palette of 11 basic colors which are readable on both light and dark backgrounds. Because these colors are located in the 16-255 ansi range, they are guaranteed to look the same on almost any terminal, regardless of the user's theme. Most libraries leave the colorizing up to the user's emulator profile/theme, which often leads to wildly different colorized output in the user space.  

Bling also offers two simple functions for creating blocks of text in the console. Check out the readme for exhaustive visual examples. These can be semantically colored (errors, warnings, info). Bling also offers a simple function for constructing a point-of-interest diagram, like when you want to call out the namespace, line, column and show some source code with a red squiggly underline. I think the Clojure community would benefit from more library authors adding neatly-formatted, actionable warning and error messages to their codebases. It would also be great if such warnings and messages where formatted in a way that loosely followed some set of conventions. Perhaps Bling could play a small role in fostering this within the Clojure community.  


[**#5 - Lasertag**](https://github.com/paintparty/lasertag)    

Lasertag is a utility library that was spun out of Fireworks. I believe it to be quite unique in the current landscape of cljc libraries that deal with reflection and categorization of values.  
Lasertag makes it easy to get detailed information about values, particularly in the context of interop with [Java or JavaScript. See this section of the readme for an example](https://github.com/paintparty/lasertag?tab=readme-ov-file#instance-methods-on-javascript-built-ins).  

I think that Lasertag could potentially offer great utility to rich-tooling projects developed in the Clojure community of the present and future.  


**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?** Although I did not study computer science,  I do think of myself as an individual with a unique perspective and something to offer.  <br>  

---


## Eric Dallo  
@ericdallo

**What do you plan to work on throughout the year?** 
I want to dedicate more time on big features for anything related to the Clojure LSP ecosystem, this includes: clojure-lsp and all integrations with editors, intellij, calva, lsp-mode, and so on.
Also, I want to improve the IntelliJ experience to be as good as Cursive, improving clojure-repl-intellij and clojure-lsp-intellij projects so we can have good options for LSP in any editors.  


**Why is this work important to the Clojure community?**  
Because with that everyone coding Clojure will be using the same features improving dev xp.  <br>  

---

## Toby Crawley
tobias  

**What do you plan to work on throughout the year?**   
https://github.com/clojars/  

**Why is this work important to the Clojure community?**  
Clojars provides critical infrastructure to the Clojure community. This funding would cover routine maintenance and adding new features as needed.  <br>   


---


## Rafal Dittwald  
https://github.com/clojure-camp/  

**What do you plan to work on throughout the year?**  
#### Clojure Camp

My primary focus will be on our [topic map](https://map.clojure.camp), particularly, creating an interface to add Clojure resources from around the web and link them to learning outcomes. Later, crowd-sourcing submissions and doing some of that linking manually. Also exploring using LLMs to automate of that process.  

Additionally, I would like to mentor community contributions to other resources at Clojure Camp: developing faded example / parson's problems for our library of [exercises](https://exercises.clojure.camp); a badge system; and improvements to our pairing scheduling tool and mob programming tool. I will offer up some of the  funding as bounties for our community members.  

And of course, I will continue to run regular mob sessions and stewarding the community.  

I maintain a list of "TODOs" in this [spreadsheet:](https://docs.google.com/spreadsheets/d/1XTuw3E5Eu28hX3jtuDeI0bS5PhNRNl5z0mpZ9KEtGFk/edit?gid=0#gid=0)

[Jordan and I presented](https://www.youtube.com/watch?v=xOPoYHxZjdc&t=5s) a Clojure Camp update at the Conj.   

**Why is this work important to the Clojure community?**  
Clojure has sustained itself, so far, as a language (and community) "for experts, by experts." But for long term sustainability, we should be open to exploring other avenues for people to enter the Clojure-verse.  

Clojure Camp aims to offer a learning experience to support would-be Clojure developers currently under-served by existing offerings. We achieve this by:  
  - translating best practices of pedagogical research of teaching programming to Clojure  
  - fostering a welcoming community of learners and mentors  
  - providing supportive infrastructure to motivate learning  <br>  

---

## Dragan Djuric  
https://dragan.rocks/  

**What do you plan to work on throughout the year?**  
My goal with this funding in 2025 is to support Apple silicon (M cpu) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

This will hugely streamline user experience regarding high performance computing in Clojure for Apple macOS users, which is a considerable chunk of Clojure community. They ask for it all the time, and I am always sorry to tell them that I still don't have a solution. Once upon a time, Neanderthal worked on Mac too, but then Apple did one of their complete turnarounds with M1... This basically broke all number crunching software on macs, and the world is slow to catch up.  

Several Clojure developers started exploring high performance computing on Apple, but didn't get too far; it's LOTS of functionality. So, having Neandeathal support Apple would enable several Clojure data processing communities to leapfrog the whole milestone
and concentrate on more high-level tasks.  

This affects Neanderthal (matrices & vectors), and Deep Diamond (tensors & deep learning) the most. Nvidia's CUDA is not physically available on Macs at all, while OpenCL is discontinued in favor of Apple's proprietary Metal (and who knows what else they've came up with since).  

**Neanderthal** is a Clojure library for fast matrix computations based on the highly optimized native libraries and computation routines for both CPU and GPU. It is a lean, high performance, infrastructure for working with vectors and matrices in Clojure, which is the foundation for implementing high performance computing related tasks, including, but not limited to, machine learning and artificial intelligence.  

Deep Diamond is a tensor and deep learning Clojure library that uses Neanderthal and ClojureCUDA under the hood (among other things).  

So, what are the missing parts for Apple silicon?  
1. A good C++/native interop. That is more or less solved by JavaCPP, but their ARM support is still in its infancy, especially regarding distribution. But it is improving.  
2. A good BLAS/LAPACK alternative. There's OpenBLAS, and there's Apple's Accelerate. Both support only a part of Intel's MKL functionality. But, if we don't insist on 100% coverage (we're not) and are willing to accept missing operations to be slower, I could implement the most important ones in Clojure if nothing else is available.  
3. A good GPU computing alternative. CUDA is not supported on Apple, and OpenCL has been discontinued by Apple. So that leaves us with Apple's Metal, which is a mess (or so I hear). So I wouldn't put too much hope on GPU, at the moment. Maybe much, much, later, with much, much, more experience...  
4. Assorted auxiliary operations that are not in BLAS/LAPACK/Apple Accelerate, which are usually programmed in C++ in native-land. I'd have to see how many appear, and what I have to do with them.  
5. Explore what's the situation related to tensors and deep learning on Apple. I doubt that Intel's DNNL can cover this, but who knows. Also, Apple certainly supports something, but how compatible it is with cuDNN and DNNL, is a complete unknown to me...  
6. Who knows which roadblocks can pop up.   

So, there's a lots of functionality to be implemented, and there's a lots of unknowns.  

I propose to * Implement an Apple M engine for Neanderthal.* This involves:  
- buying an Apple M2/3 Mac since I don't have it (the cheapest M3 in Serbia is almost 3000 USD with VAT).  
- learning enough macOS tools (Xcode was terrible back in the days) to be able to do anything.  
- exploring JavaCPP support for ARM and macOS.  
- exploring relevant libraries (OpenBLAS may even work through JavaCPP).  
- exploring Apple Accelerate.  
- learning enough JavaCPP tooling to be able to see whether it is realistic that I build Accelerate wrapper (and if I can't, at least to know how much I don't know).  
- I forgot even little C/C++ that I did know back in the day. This may also give me some headaches, as I'll have to quickly pick up whatever is needed.  
- writing articles about relevant topics so Clojurians can pick this functionality as it arrives.  

It may include implementing Tensor & Deep Learning support for Apple in Deep Diamond, but
that depends on how far I get with Neanderthal. I hope that I can do it, but can't promise it.  

By the end of 2025, I am fairly sure that I can provide Apple support for Neanderthal (and ClojureCPP) and I hope that I can  even add it for Deep Diamond.  

Projects directly involved:
https://github.com/uncomplicate/neanderthal  
https://github.com/uncomplicate/deep-diamond  
https://github.com/uncomplicate/clojure-cpp  


**Why is this work important to the Clojure community?**   
This will hugely streamline user experience regarding high performance computing in Clojure for Apple macOS users, which is a considerable chunk of Clojure community. They ask for it all the time, and I am always sorry to tell them that I still don't have a solution since I don't have a recent Mac hardware. Once upon a time, Neanderthal worked on Mac too, but then Apple did one of their
complete turnarounds with M1... This basically broke all number crunching software on macs, and the world is slow to catch up.  

Several Clojure developers started exploring high performance computing on Apple, but didn't get too far; it's LOTS of functionality. So, having Neandeathal support Apple would enable several Clojure data processing communities to leapfrog the whole milestone
and concentrate on more high-level tasks.   

**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**  
The major obstacle that I have is that I live in a country outside USA and EU, so most work opportunities are unavailable from here (Serbia is a small country with poor economy that is still recovering from civil wars and whatnot, and the local IT market is mostly based on outrourcing). Other than that, I guess I'm in a similar position like everyone else.<br>   

---

## Robert Gersak 
https://github.com/gersak  

**What do you plan to work on throughout the year?** 
https://github.com/neyho/eywa-core  


**Why is this work important to the Clojure community?**  
This project is about providing sane approach to identity access management based on OAuth2.1 specification and OpenID connect, specification that is common standard for identity management across professional services. ( it is important for everyone)  

In addition, this project provides Data Modelling with out of the box GraphQL generic API exposure, that can be managed and controlled through UI. In terms of modelling entities and relations and what role can read, write, delete or own some entity or relation.  

Currently this is applicable to PostgreSQL DB, but in future more similar DB will be covered.   

Summary would be that this project is well suited for rapid development as well as for minimizing maintenance and change cost. It is fusion of IAM, Data Modelling and replacement for CRUD approach.  

**Is there anything else you would like us to know?** I would like to share that IAM has been one of important subjects when working on enterprise level projects. Project applied above helped us in many ways to overcome enterprise level presence and onboard people towards common goal through usage of Data Modeling <br>   

---

## Kira Howe (McLean)  
http://github.com/kirahowe

**What do you plan to work on throughout the year?** 
I would like to continue working on stewarding Clojure's data science ecosystem into a state of maturity. This would include specifically working on developing ggclj (a grammar of graphics implementation in Clojure -- https://github.com/kirahowe/ggclj), guides and tutorials for Clojure's data science stack (including the Clojure data cookbook and Clojure Tidy Tuesdays, https://github.com/scicloj/clojure-data-cookbook, https://github.com/kirahowe/clojure-tidy-tuesdays), and conference talks. I would like to pitch some talks to broader data-focused conferences in 2025 outside the Clojure community in hopes of reaching a broader audience. These would include at least PyData Global, Fossdem, Open Data Science Conference, and Lambda world.  

**Why is this work important to the Clojure community?**  
I think this work will help expand Clojure's user base and reach new audiences who can benefit from the unique tools and approaches Clojure brings to some of the biggest problems in data science today.  

**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?**  I'm female and feel women are underrepresented in tech and especially in open source.  <br>   

---


## Jack Rusher  
https://github.com/jackrusher  

**What do you plan to work on throughout the year?** I would like to devote a certain number of hours every month in 2025 to work on Clerk.  

**Why is this work important to the Clojure community?**  Clerk is used by a large number of community members for literate programming, data analysis and visualization, &c. <br>  


---

## Roman Liutikov  
https://github.com/roman01la  

**What do you plan to work on throughout the year?** https://github.com/pitch-io/uix

**Why is this work important to the Clojure community?** UIx is one of the most dev friendly React wrappers, currently used by a number of companies known in Clojure community, including Pitch, Metosin and Cognician. UIx builds on  modern React and encourages to rely more on existing React ecosystem in JS world. The library was in particular developed to be beginner friendly and close to React conceptually so that Clojure companies could onboard JavaScript developers quicker, allowing them to reuse most of their experience from JS/React world. I think UIx succeeded at this, at least judging based on feedback from companies mentioned above.   

Both Pitch and Cognician hired frontend folks from JS community and they were able to pick up UIx relatively quickly. I also gave a talk on UIx at [London Clojurians](https://youtu.be/4vgrLHsD0-I?si=RPs7AS4IVDA56WWo)  
I have a couple of ideas on improving the library further, preparing for React v19, also documentation needs more work and interactive examples. In general today after some years UIx is pretty stable, now I want to invest more in learning materials and templates for various types of projects, to make the library more accessible to devs. <br>    


---

## Mateusz Mazurczak and Anthony Caumond  
https://github.com/Kaspazza  
https://caumond.com/#/


**What do you plan to work on throughout the year?** 
We are planning to work on discrete event simulation and discrete optimization.  
Planning to add:  
- A data oriented language to be able to model simulation by non-technical people.  
- Add scenario management for creating multiple simulations.  
- Add rendering module to give the highest possible quality insights to the modeler about the behavior of a model  

Project name: Automaton simulation discrete event  
Link: https://github.com/hephaistox/automaton-simulation-de  

**Why is this work important to the Clojure community?**  
First, as a little introduction:  
We have over two decades of expertise in the supply chain on board and a decade of software development with software craftsmanship in our hearts. We plan to create it as an open-source alternative to existing solutions (like Rockwell Arena https://www.rockwellautomation.com/en-us/products/software/arena-simulation.html or https://www.anylogic.com/).  

With the difference, that thanks to Clojure and data-centric approach our software will be at much higher flexibility.   
This is crucial for this kind of software, as each supply-chain industry's needs for simulation are different. So current off-the-shelf software is either not flexible enough for their usage or to expensive to adapt (ranging in millions of euro).  

Future development of this library can bring more interest in Clojure for supply-chain-related projects (especially since we are putting related topics to separate libraries, e.g., https://github.com/hephaistox/automaton-optimization).  

By being highly flexible in architecture, other people can extend this library to meet their needs. This can benefit college students of the supply chain and people working in supply-chain (e.g. warehouses, factories). Especially when their company or revenue is too small to require consultants and high-cost software.  

This will be also a great showcase for clojure data-oriented approach, as this kind of scheduling systems are much harder to create to be so flexible using classical languages (java, c++) while keeping costs small (small team).  


**Is there anything else you would like us to know?** As automaton-simulation-de is built with a non-monolith approach, each of its parts could be seen, on its own as a product providing value to the community.  

For example:  
- PRNG and probabilistic distribution clojure / clojurescript lib  
- Technology agnostic discrete optimization library (random walk, descent, genetic algorithm, ...)  
- Scenario management,  
- Scheduler engine  
- Data-oriented UI components library (separating well the HTML+css part, behavior, and where the setup data comes from).  
- Or other libraries that are already separated as a side-effect of the main work e.g. https://github.com/hephaistox/automaton-build for working with monorepo  <br>  

---

## Daniel Slutsky
https://github.com/daslu  

**What do you plan to work on throughout the year?** Scicloj    https://github.com/scicloj/   
Scicloj community building and open-source projects   


**Why is this work important to the Clojure community?** Clojure can be a fantastic fit for many kinds of scientific and data-intensive projects. Fulfilling that potential is a years-long effort, on the technical as well as the community side.  

Since early 2019, we, the Scicloj group, have been working on creating a stack of tools and libraries for data and science in Clojure. https://scicloj.github.io/  

The stack we are building is addressing many needs which are useful non only in scientific context. High-performance computing, data processing, data visualization, literate programming, and literate testing are a few examples.  

You may find more details about current developments in a few recent talks by Scicloj members:  
[Kira Howe (McLean) at London Clojurians](https://www.youtube.com/watch?v=eUFf3-og_-Y)   
[Sami Kallinen at Heart of Clojure](https://www.youtube.com/watch?v=ckQllKUw3b4)   
[Thomas Clark at Clojure Conj](https://www.youtube.com/watch?v=_D5d6Ls6pBw)    

We have also been continuously working on building the Clojure community: running workshops, meetup groups, study groups and dev groups, and helping with the organization of a couple of conference. We have been mentoring many Clojurians in becoming involved in open-source and in expanding their use of Clojure to new contexts.   

Based on that experience, we recently initiated the [Scicloj open-source-mentorship program.](https://scicloj.github.io/docs/community/groups/open-source-mentoring/)   
63 people have applied, and 34 of them are currently active in looking into projects and meeting regularly.
The program was discussed in our [recent status report (2024-10-25):](https://www.youtube.com/watch?v=STnFMpIZlkk)   

A few project mentees are new to Clojure and sometimes new to programming. We meet regularly and help them in their learning process.  

We have been persistently exploring the expansion of Clojure to new fields of application and research, such as biology, linguistics, physics, statistics, and geography. We are actively working with practitioners in various fields (some new to programming and some experienced) to help them out and learn from their use cases.   

**Is there anything else you would like us to know?** My role in Scicloj is both in community building and as an open-source maintainer of a few tools and libraries. I have been involved since the beginning (2019). Since I left my day job in 2023, Scicloj has been my main focus.  <br>   

---


## Adrian Smith  
https://github.com/phronmophobic/


**What do you plan to work on throughout the year?** 
- [Grease](https://github.com/phronmophobic/grease): The goal for this year is to make a free, open source app similar to pythonista(http://www.omz-software.com/pythonista/), but for clojure. This project is already in-progress. More info at https://clojurians.slack.com/archives/C0260KHN0Q0 on the clojurians slack.  
- AI tools like [llama.clj](https://github.com/phronmophobic/llama.clj): llama.clj is a library that allows running open source LLMs directly from the JVM with a clojure-friendly API.  
- [Dewey](https://github.com/phronmophobic/dewey): Dewey is a public dataset that scans and analyzes clojure github repos weekly. These datasets are currently being used by tools like tutkain and clojure-lsp. The goal is to improve access to ecosystem data in order to be even more useful for developer tooling.  
- [Membrane](https://github.com/phronmophobic/membrane): A pure clojure, cross-platform UI library  


**Why is this work important to the Clojure community?** The goal for the Grease project is to make a fully scriptable iOS app. This would allow any clojure developer to write apps for their iPhone without requiring developers to jump through hoops like the Apple submission process. The app (currently code named LearnLisp) is compiled using graalvm's native-image and scripts are executed using an embedded sci interpreter. A subgoal for this project is to make the app approachable to any developer that might be interested in learning a Lisp while also having fun making something useful for their phone. In principle, the same approach can be used to target android. A stretch goal is to also release an android app with the same features.  

Many of the latest AI tools are written in python and c++. I have 15 years of experience writing python and c++. One of the goals for the next year is to continue writing clojure libraries like llama.clj that make the best AI tools available to clojure. Some examples of similar libraries are whisper.clj, usearch.clj, and clip.  clj.

One of the challenges commonly cited by new clojure users is finding libraries. Dewey collects and indexes information about the clojure open source ecosystem. The dewey frontends are already quite capable of finding the right library for a particular task, but the UX needs to be improved.  

The current dewey frontends:  
- [Library Search](https://phronmophobic.github.io/dewey/search.html) - Search for clojure libraries by keyword, name, author, and description.  
- [Cloogle](https://cloogle.phronemophobic.com/doc-search.html) - Search for any clojure function by its doc string. Queries are indexed by semantic meaning using a vector db.   

The goals for this year:  
- Consolidate frontends into a single, unified frontend  
- Make the UI prettier  
- Create an API to support developer tools  
- Extract the semantic search code into its own standalone library  

Membrane is a UI library written in pure clojure aimed at building complex, interactive UI applications in a functional style. One pitfall I'm trying to avoid with membrane is to solve the easy problems, but ignore the hard problems. The most recent work on membrane has been about solving some of the hard problems facing desktop UIs so that membrane can be a real alternative to the browser for complex desktop applications. These projects include clj-media (video playback), clj-cef2 (an embedded web browser based on chromium), clj-webgpu (3d graphics), and membrane.term (terminal emulator). Now that I feel comfortable that I won't be leading users down a dead end, the goal for this year is to improve documentation and provide a good looking, high quality, component library. Membrane UIs aren't tied to any particular graphics library. This means that membrane UIs can be embedded in any UI library that can draw shapes, texts, and rectangles.  <br>   

---

## Peter Strömberg  
 https://github.com/PEZ  

**What do you plan to work on throughout the year?**  
Calva - https://calva.io - I'd like to tackle repl session management, technical and ux debt and integration with CoPilot.  

**Why is this work important to the Clojure community?**
Calva runs on the important VS Code platform, which includes editors like Cursor. The uptake of Calva increases steadily and is now close to 25% of Clojurians. Especially the lacking session management and the technical debt are increasingly problematic.  
<br>   

---  


## Dan Sutton  
https://github.com/dpsutton/  


**What do you plan to work on throughout the year?** 
inf-clojure  


**Why is this work important to the Clojure community?** The Clojure Community always benefits from editor integrations. I think the socket repl is the underused super power of Clojure. It is the great equalizer of development environments and is built into the language.  

I would want to do two things:  
1. Work on inf-clojure to make it more robust and accessible to both beginners and experienced devs alike. NREPL does lots of amazing and convenient stuff. But it can leave developers unable to run tests without a UI. It requires lots of configuration and takes over your main entrypoint. The socket repl is incredibly helpful. A REPL based on source is almost completely indistinguishable from one based on an AOT'ed, production jar.  

2. To complement this, I will also write tutorials and create videos showcasing how easy it is to (a) start repls, (b) work with repls, and (c) lots of helpful additions that make it so powerful and flexible.  
I've got lots of local functions that I need to package up and get into the inf-clojure repo. Some examples:  
- starting a repl: `clj -J"$(socket-repl 6000)"` which expands out to the command line args required for a socket repl  
- `m-x personal/repl [RET] 6000` will connect to a socket repl running on port 6000 and wire up a sub repl in it that shortens the displayed prompt to the last segment of the namespace and hook up clojure.pprint/pprint as the pretty printer  
- save-to/eval-from registers. Using registers in emacs is _lovely_. Throwing different snippets into registers (t for run all tests, i for individual test, etc) is an amazing way to run. I've used this system for years now and it's time it made it back into inf-clojure.  
- a hotkey to require the repl-requires: `(inf-clojure--send-string inf-proc "(apply require clojure.main/repl-requires)")`  
- a helpful debugging REPL. We have macros that create temporary things during testing. It's often the easiest way to create some complicated state to exercise and inspect a bug. But the tests run so quickly that I can't play around. I wanted something that would be blocking and still allow me to play around with the state: THIS IS A REPL. Using subrepls to pause the world is an amazing super power that is fundamentally incompatible with nrepl. But it's transparently powerful with socket repls. I wrote a macro that throws all locals into a namespace and lets you introspect it.  
- using a remote repl is amazing with a bit of ssh tunneling. The loveliest usecase is a failing test in Circle CI, rerunning with ssh and then connecting your REPL to your CI instance! This is straightforward with a socket repl.  

I also want to highlight how _queryable_ the Clojure runtime is. The repl helpers: source, apropos, dir, pst, doc, find-doc, add-libs, etc are INCREDIBLE. Learning to use the tools built into the language and not built into the IDE is a super power. Every Clojure REPL is an amazingly helpful repl.  

I don't want this to appear as it would be tutorial focused. I would hope that is 40% or less of my output. I want to contribute to inf-clojure, watch it grow, and share videos and articles about how it can unlock so much productivity.  

**Is there anything else you would like us to know?** If this project sounds interesting, I'm also open to doing a shorter commitment. I wonder if there are others who have just a few months of work and we could combine to have several smaller projects.  
But I could also improve this over 12 months as well. I think the clojurescript story is a bit underdeveloped. Shadow-cljs provides a socket repl that can easily upgrade. Using cljs.main is a bit harder but achieved with a different "accept" function. The documentation should be much easier.  

I also want an easy way for it to just use a terminal repl easier. Using ssh tunneling is one thing, but it would be amazing to have a vterm window open and easily sending forms over to the repl.  

In short, I think this tooling is a super power. I would love to build this out over a few months or the full year and share the knowledge and improve the tools.  <br>   


---


## Peter Taoussanis  
https://www.taoensso.com  

**What do you plan to work on throughout the year?**   
Multiple projects, incl. further work on Telemere, Tufte, Tempel, and more  

**Why is this work important to the Clojure community?**
Have been trying to focus recent work on practical areas where Clojure either doesn't already have great solution/s, or where Clojure might be able to offer something uniquely advantageous over other languages/platforms. There's still a lot of low-hanging fruit re: both observability and data security for example. Beyond that, just focusing on practical tools that work at scale while being approachable for beginners.  

**Is there anything else you would like us to know?**  
Mostly, just thank you! I've been lucky and grateful to already receive long-term funding from Clojurists Together for 2023 and 2024.

I'd definitely be up for continuing this way if there's interest from the community. It's been awesome to dedicate more time to my open source, and Clojurists Together funding has undoubtedly helped with that (esp. for the more complex / high-effort stuff).

Have a lot of exciting things that I'd love to still expand on or explore in 2025. But I also understand that I've already received a couple years of funding now - and that there's probably many great and deserving applicants with exciting proposals :-)

Whatever the outcome for 2025, I plan to continue to try contribute as much as I can.

Clojurists Together is a really awesome undertaking, and an inspiration. So thanks to everyone that's been involved in helping make it reality and in keeping it running so smoothly.

Cheers! :-) - Peter  <br>   

---


## Oleksandr Yakushev  
https://github.com/alexander-yakushev    
https://github.com/clojure-goes-fast   

**What do you plan to work on throughout the year?**   
I plan to continue working on Clojure Goes Fast tooling. During Q3 2024, I've covered a lot of ground with new features and redesign of clj-async-profiler and the first release of Flamebin. This also opened a lot of ideas and opportunities for further improvement of those tools. I have a large backlog of tasks for:
- Flamebin – implement authorization and social login, client-side encrypted private flamegraphs, collaborative commenting, configuration editing and saving;
- clj-async-profiler – unified configuration, configuration reuse between flamegraphs, automatic analysis and performance improvement suggestions;
- Clojure Goes Fast knowledge base – populate the list of bad performance practices that can be later be linked to by the clj-async-profiler analyzer;
- clj-java-decompiler – CIDER integration which I wished to do in Q3 but couldn't get to in this quarter.

Also, in 2024 I've once again become an active contributor and co-maintainer of CIDER, cider-nrepl, Orchard, and Compliment (250 total commits in 2024) and I want to continue doing this in 2025.  


**Why is this work important to the Clojure community?**  
Clojure Goes Fast continues to be the primary spot of performance-related projects and guides about Clojure (1.1K stars, 1.1M total Clojars downloads, 7000 unique site visitors in 2024).

CIDER is used by 40% of Clojure developers according to State of Clojure 2024 survey.  































