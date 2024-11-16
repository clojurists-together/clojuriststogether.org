---
title: "2025 Long-Term Funding Applications"
date: 2024-11-16T14:00:00+12:00
author: Kathy Davis
summary: "It's Up To You, the Members, to Decide Who will Receive Funding. "  
draft: True


---

Greetings Clojurist Together Members! This year, we are trying a new process to determine who will receive a monthly stipend of $1,500 to support their development work. 

We put out the call  - and we received 17 thoughtful applications for you to consider. You can also review past long-term project updates on our [website](https://clojuriststogether.org) to get an idea of what past long-term grant recipients have been able to accomplish with your past support (pretty amazing!)

Please review the applications below and select 5 developers to receive funding in 2025. Be on the lookout for an email that contains your link to a Ranked Vote ballot. Here we go ..in alphabetical order (by last name)....

### Deadline for your vote to be counted: December xx, 2024 Midnight Pacific Time  



-[Michiel Borkent](#michiel-borkent)  
-[Thomas Clark](#thomas-clark)  
-[Jeremiah Coyle](#jeremiah-coyle)  
-[Eric Dallo](#eric-dallo)  
- Rafal Dittwald  
Dragan Djuric  
Robert Gersak  
Kira Howe  
Jack Rusher  
Roman Liutikov  
Mateusz Mazurczak and Anthony Caumond  
Adrian Smith  
Dan Sutton  
Daniel Slutsky  
Peter Strömberg  
Peter Taoussanis  
Oleksandr Yakushev  

### Michiel Borkent
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

**Do you receive any other funding to work on this project?** Yes, via Github Sponsors

**Is there anything else you would like us to know?** Thank you so much for Clojurists Together and keeping the Clojure OSS ecosystem sustainable!  <br>

---

## Thomas Clark  
https://clojurians.zulipchat.com/#user/386018  

**What do you plan to work on throughout the year?** 
In a nutshell, my plan would be to help expand Clojure's scientific ecosystem (https://www.youtube.com/watch?v=_D5d6Ls6pBw), particularly in regards to the mathematical sciences. This would take the form of developing and creating libraries, continuing and creating documentation and to initiate a serious attempt at academic outreach.

#### Libraries

#### Wolframite  
Last year, CT supported Jakub Holy and I for a quarter in our attempt to resurrect and document the Wofram-Clojure bridge. We're very happy with the progress we made in this time, but of course there are many other things that could (and should?) be done - we really feel like we're just getting started. In particular, a key feature of Wolframite, that is missing in Wolfram itself, is the REPL experience. 

In this, we want to fully integrate Wolframite with Clojure's visual tools for all manner of datatypes that Wolfram supports. And in particular, to make data passing and memory management efficient. More functionally, we want to create a special viewer for symbolic expressions, that not only allows the user to copy and switch between maths in Wolfram, LisP and TeX forms but that automatically generates sliders for each parameter for exploration, as inspired by Wolfram's Manipulate function (https://reference.wolfram.com/language/ref/Manipulate.html).

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
As discovered while writing the Wolframite documentation (https://scicloj.github.io/wolframite/for_scientists.index.html), what is often needed is not so much a manual but examples of how to use the tool (or how to integrate many tools) in a real project. With this in mind, I would like to document the overall process of how to use the ecosystem to solve real problems, supplying both real problems and real solutions, e.g. the source and details behind my past talk (https://www.youtube.com/watch?v=SE5Ge4QP4oY) and large contributions to 'noj (https://github.com/scicloj/noj).  

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

**Do you receive any other funding to work on this project?** No

**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?** Depending on what you mean by 'systemic bias', my disadvantage is a classical one. Living in eastern Europe and working in the public sector, I suspect my Clojure friends would be shocked at what is paid to scientists here. I don't pretend that Hungary is as cash-strapped as some other continental countries, but compared to most other countries in 'the west', even those a two hour drive away, the salaries are low. If it helps, I can be more specific, but suffice it to say that this funding would go much further here and would enable a much bigger shift in what I'm able to contribute than it would for many other applicants.  <br>

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
- Produce written and/or video documentation of my current live hot-reloading dev environment for JVM Clojure, with versions for both Leiningen and Deps. I recently issued a PR to add this to test-refresh - https://github.com/jakemcc/test-refresh/pull/91. This sort of thing could also potentially be incorporated into other similar projects such as metabase/hawk and tonsky/clj-reload.  
- For ClojureScript developers using Fireworks in a browser dev console, I went off the deep-end and made a dedicated Chrome extension to enable the setting of the Chrome DevTools console background and foreground color with a very nice GUI interface. Would be cool to get this working in most other Chromium-based browsers, and potentially Firefox, if there is any demand for it.  
- https://github.com/paintparty/fireworks?tab=readme-ov-file#setting-the-background-color-and-font-in-chrome-devtools-clojurescript


#### #4 - Bling  
[Bling](https://github.com/paintparty/bling) is a library for rich text in the console  

**Goals for Bling in 2025:** 
- Support automatic detection of the 3 levels of color support (16-color, 256-color, or Truecolor), using an approach similar to https://github.com/chalk/supports-color  
- Support arbitrary hex colors, and their conversion, if necessary, to 256. I’ve already created an efficient Manhattan distance algorithm for doing this conversion in Fireworks - https://github.com/paintparty/fireworks/blob/035ec4cb048db05f4cca3691f946931b3a87f624/src/fireworks/color.cljc#L26  
- Create more formatting templates for callouts, headers, and points-of-interest  
- Add documentation about how to leverage Bling to create great-looking warnings and errors in any project. Example of using bling's templates to create nice warnings can be found here: https://github.com/paintparty/fireworks?tab=readme-ov-file#helpful-warnings-for-bad-option-values  
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

**#1 - Kushi** 
https://kushi.design/  
Kushi aims to provide a complete solution to the design layer of web UI projects.  

It is an ambitious project, but I believe there is an opportunity for Kushi to offer a UI dev experience that is unique and more compelling than any equivalent in any other language. This could lead to increased organizational awareness and consideration of ClojureScript as an attractive choice for building mission-critical UI. If more companies used ClojureScript to build UI, I think it would be very beneficial for the community.  


**#2 - Domo**
https://github.com/kushidesign/domo  
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

**#3 - Fireworks**  
https://github.com/paintparty/fireworks  

Fireworks is the only lib to provide a colorizing and themeable pretty-printer that works beautifully in both Clojure and ClojureScript (and Babashka).  

The output is, arguably, orders of magnitude faster and easier to read than equivalent output from clojure.pprint/pprint. This is especially true in the case of maps with data-structures as keys, or any kind of collection that features metadata.  

The library also provides very powerful debugging and tapping macros. These macros provide a lightweight complement to discovery-centric, UI-heavy data exploration tools such as Portal and Flowstorm. With a simple hot-reloading setup (in clj or cljs), and minimal editor integration, Fireworks can drive an extremely compelling live-feedback dev experience without any reliance on a repl connection, or repl-related concepts. I think the continued codification, documentation, and demonstration of such a workflow could make a difference in bringing new people to Clojure. The maturation of this kind of workflow could also benefit existing users of community tools such as playback, debux, hashp, telemere, ken, spyscope, omni-trace, postmortem, and sayid.  
 

**#4 - Bling**
https://github.com/paintparty/bling  

Many mature language communities have a library for rich text printing in the terminal, for example Rich (Python), Chalk (JS), LipGloss (Go), etc.  

With Clojure, the main existing option seems to be org.clj-commons/pretty. The original impetus for creating Bling came out of my experience of trying to use this lib extensively for formatting error and warning messages.  

The most unique thing that Bling offers is a carefully curated palette of 11 basic colors which are readable on both light and dark backgrounds. Because these colors are located in the 16-255 ansi range, they are guaranteed to look the same on almost any terminal, regardless of the user's theme. Most libraries leave the colorizing up to the user's emulator profile/theme, which often leads to wildly different colorized output in the user space.  

Bling also offers two simple functions for creating blocks of text in the console. Check out the readme for exhaustive visual examples. These can be semantically colored (errors, warnings, info). Bling also offers a simple function for constructing a point-of-interest diagram, like when you want to call out the namespace, line, column and show some source code with a red squiggly underline. I think the Clojure community would benefit from more library authors adding neatly-formatted, actionable warning and error messages to their codebases. It would also be great if such warnings and messages where formatted in a way that loosely followed some set of conventions. Perhaps Bling could play a small role in fostering this within the Clojure community.  


**#5 - Lasertag**  
https://github.com/paintparty/lasertag  

Lasertag is a utility library that was spun out of Fireworks. I believe it to be quite unique in the current landscape of cljc libraries that deal with reflection and categorization of values.  
Lasertag makes it easy to get detailed information about values, particularly in the context of interop with [Java or JavaScript. See this section of the readme for an example](https://github.com/paintparty/lasertag?tab=readme-ov-file#instance-methods-on-javascript-built-ins).  

I think that Lasertag could potentially offer great utility to rich-tooling projects developed in the Clojure community of the present and future.  

**Do you receive any other funding to work on this project?** I am currently receiving Clojurists Together Q3 short-term funding for Kushi, but no funding is yet scheduled for any of these projects in 2025.

**Are you part of a group that is affected by systemic bias, particularly in technology? If so, can you elaborate?** Although I did not study computer science,  I do think of myself as an individual with a unique perspective and something to offer.  <br>

---

## Eric Dallo  
@ericdallo

**What do you plan to work on throughout the year?** 
I want to dedicate more time on big features for anything related to the Clojure LSP ecosystem, this includes: clojure-lsp and all integrations with editors, intellij, calva, lsp-mode, and so on.
Also, I want to improve the IntelliJ experience to be as good as Cursive, improving clojure-repl-intellij and clojure-lsp-intellij projects so we can have good options for LSP in any editors.  


**Why is this work important to the Clojure community?**  
Because with that everyone coding Clojure will be using the same features improving dev xp.  

**Do you receive any other funding to work on this project?** GithubSponsors  <br>

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
  - providing supportive infrastructure to motivate learning  
Do you receive any other funding to work on this project? 23 USD/mth from a Github sponsor. Daniel H received funding previously. <br>

---

## 





















