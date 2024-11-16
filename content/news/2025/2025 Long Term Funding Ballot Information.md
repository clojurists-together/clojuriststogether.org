---
title: "2025 Long-Term Funding Applications"
date: 2024-11-16T14:00:00+12:00
author: Kathy Davis
summary: "It's Up To You, the Members, to Decide Who will Receive Funding. "  
draft: True


---

Greetings Clojurist Together Members! This year, we are trying a new process to determine who will receive a monthly stipend of $1,500 to support their development work. 

We put out the call  - and we received 17 thoughtful applications for you to consider. Please review the applications below and select 5 developers to receive funding in 2025. Be on the lookout for an email that contains your link to a Ranked Vote ballot. Here we go ..in alphabetical order (by last name)....

#### Deadline for your vote to be counted: December xx, 2024 Midnight Pacific Time  



-[Michiel Borkent](#michiel-borkent)  
-[Thomas Clark](#thomas-clark)  
-[Jeremiah Coyle](#jeremiah-coyle)  
-[Eric Dallo}  
Rafal Dittwald  
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













