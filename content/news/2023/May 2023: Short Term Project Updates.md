---
title: "May 2023: Short Term Project Updates"
date: 2023-05-24 T08:30:00+08:00
summary: Updates from Clerk, Clojure-ts-mode, Exercism, Kaocha, Neanderthal
author: Kathy Davis
draft: true
---  

With several project cycles winding down, we have final reports in from 
Clerk, Clojure-ts-mode, Exercism, Kaocha, and Uncomplicate Neanderthal. 
Great work all!  As always thanks for your commitment and 
contributions to the Clojurists Together community.

[Clerk: Martin Kavalar](#project-clerk-martin-kavalar)<br>
[Clojure-ts-mode: Danny Freeman](#project-clojure-ts-mode-danny-freeman)<br>
[Exercism: Bobbi Towers](#project-exercism-bobbi-towers)<br>
[Kaocha: Arne Brasseur](#project-kaocha-arne-brasseur)<br>
[Uncomplicate Neanderthal: Dragan Duric](#project-uncomplicate-neanderthal-dragan-duric)

## Project Clerk: Martin Kavalar<br>  
Update: 2 May 2023; Final Report<br>
Funding Round: Q1 2023<br>

###  Interactive Links & Homepage, Book Updates, Viewer Customization<br>  
In April we worked on the following:  

* **🔗 Interactive Links, Index and Homepage**<br>
Links can now be followed in interactive mode and the index can be viewed. 
Previously this could only be seen after a `build!`. Add support evaluating 
a given doc by entering it in the browser's address bar.  Use these features to build a new welcome page that gives more useful information,
including links to potential notebooks in the project.  
Getting this right took a bit longer than we hoped but we're looking forward to 
build on top of those features to better surface the docs going forward. 
Wikidata-style links to other notebooks and vars are also on the roadmap.<br>

* **👁 Improved support for customization of viewer options**<br>
Customization of viewer options like the budget or auto-expansion
of results has gotten an overhaul. They can now be set per-doc or 
per-result consistently using the same keys.<br>  

* **🍒 Experimental support for cherry for compiling viewer fns**<br> 
Took a first step towards supporting compiling Clerk viewer functions 
using [cherry](https://github.com/squint-cljs). This opens the door to more performant
viewer fns since it removes the interpretation overhead.<br> 

* **📖 Book Updates**<br>
Documented the [Viewer Customization Options](https://github.clerk.garden/nextjournal/book-of-clerk/commit/c47477cb3ca72740de82c9fd3ddcb384f7c7eeb9/#customizations) in the Book of Clerk.<br>

* **🐞 Smaller Bugfixes & Improvements**<br>
We plan to follow up with a v0.14 release shortly. What we had planned but didn't yet get around to is documenting how to use Clerk 
as a library for Dashboards. We did settle on an approach that we're happy with 
however, so getting the docs updated should not be long. Thanks again for supporting 
Clerk with this funding! 🙏  
___
  
  
## Project clojure-ts-mode: Danny Freeman<br>
Update: 15 May 2023; Final Report<br>
Funding Round: Q1 2023  

### Project Update:  

Clojure-ts-mode is now in a pretty usable state and should be ready for 
people to try out. Through this funding project I've implemented the following features:  

- Syntax Highlighting

- Fixed style indentation

- Complete imenu support

- S-expression navigation  

Additionally I've been working on the documentation to make getting started 
with clojure-ts-mode easier.  It is available for install now through package-vc on Emacs 29  

```

(package-vc-install "https://github.com/clojure-emacs/clojure-ts-mode")

```

And should be available on MELPA package repository soon.   

### Next steps  

I was not able to get semantic indentation implemented yet, as I found the
indentation mechanisms in tree-sitter difficult to use at first. However,
I have a much better understanding now of them (and how to debug them).
Over the coming months I will be working on semantic indentation to more
closely match what clojure-mode does today. Once I have semantic indentation
in a good place I will probably consider clojure-ts-mode ready for
a 1.0.0 release. Until then it will stay in the 0.*.* version range.  

Documentation is always a work in progress and will continue to improve over time.  

Additionally, I will request to have clojure-ts-mode listed in 
Non-GNU ELPA package repository. This should make it easier to install
for people that do not have MELPA configured.<br>  

---

## Project Exercism: Bobbi Towers<br>  
Update 29 April 2023: Final Report<br>
Funding Round: Q1 2023<br> 

### Inline evaluation via SCI  
I'm very excited about this feature because it transforms the online 
editor into a proper interactive Clojure environment. When the track 
was first launched in 2021 we received some valuable feedback from
fellow Clojurists, who said that the online editor was quite lacking
to someone familiar with using an editor connected REPL. I too consider
this an inseparable part of the Clojure development experience, so this
is something I've wanted to do for a long time.  

Once I [prototyped](https://github.com/bobbicodes/typomaniac) the desired
behavior using [Nextjournal's clojure-mode](https://github.com/nextjournal/clojure-mode), the next
task was to figure out how to best integrate it into the existing [Ruby on Rails/React application](https://github.com/exercism/website) while making as minimal impact on the codebase as possible. The first thing I tried was to use [scittle](https://github.com/babashka/scittle) to load [SCI](https://github.com/babashka/sci) via the `<script>` tag. This worked great, but I wanted something more modular and realized the best thing to do would be to make it into a proper plugin for Codemirror 6 and package it as an npm library similar to [@nextjournal/lang-clojure](https://github.com/nextjournal/lang-clojure) which I was able to fork and use as a template, which is itself based on [@codemirror/lang-javascript](https://github.com/codemirror/lang-javascript). This makes it extremely easy on the website team because it simply replaces the legacy plugin used previously, with the added benefit of creating a Clojure-evaluation plugin that can be used by the rest of the Clojure community to build highly interactive editors on the web. Repo: https://github.com/bobbicodes/clojure-eval  

### Syllabus  
#### Revised existing exercises:<br> 
- [Basic intro](https://github.com/exercism/clojure/pull/548) - I tried to give special attention to the very first lesson, because for the majority of students this is their first contact with Clojure and I take that as a considerable responsibility. Its objective is to provide the fastest possible onramp to get the student started without them needing to immediately look anywhere else. Since Exercism is all about teaching new languages to folks who already know how to code, our target experience level is someone who has never seen Clojure, Lisp, or functional programming concepts before, but is fluent in at least one other language. So far, feedback has been very positive :)  

- [Lists exercise](https://github.com/exercism/clojure/tree/main/exercises/concept/tracks-on-tracks-on-tracks) - This one needed to be revamped because the idiomatic solution included threading macros, which were not yet taught at that point. So this exercise now teaches threading macros, and the Card Games exercise (see below) is being ported from the Python track because it is a better exercise for teaching list operations.  

### New concept exercises in progress:  
- Hashmaps - [International Calling Connoisseur](https://github.com/exercism/clojure/tree/international-calling-coonoisseur/exercises/concept/international-calling-connoisseur)
- Chars - [Squeaky Clean](https://github.com/exercism/clojure/tree/main/exercises/concept/squeaky-clean)
- Regex - [Date Parser](https://github.com/exercism/clojure/pull/492)
- closures - [Coordinate Transformation](https://github.com/exercism/clojure/tree/main/exercises/concept/coordinate-transformation)
- Multimethods - [Wizards and Warriors](https://github.com/exercism/clojure/tree/wizards-and-warriors/exercises/concept/wizards-and-warriors)

### Concept exercises planned:  
- Lists - [Card games](https://github.com/exercism/python/tree/main/exercises/concept/card-games)
- Atoms
- Threading macros
- [Protocols](https://github.com/exercism/clojure/issues/542) - My idea for this is to do something inspired by [emmy](https://github.com/mentat-collective/emmy), which is a very nice example of protocols. For example, an exercise involving defining operations for complex numbers would be cool. A long term vision is to create a mathematics mini-course, since Clojure is superb for this and more people should try it! The main part of the syllabus takes priority, however.  
- [Partition](https://github.com/exercism/clojure/issues/479), `juxt`, `for`  
- Associative destructuring
- [Multiarity](https://github.com/exercism/clojure/issues/306), [variadic](https://github.com/exercism/clojure/issues/308), [anonymous](https://github.com/exercism/clojure/issues/309) functions  

### New practice exercises  
- Yacht
- Zipper  

### Test runner upgrade  
The learning exercise test suites now include metadata linking each test case to its respective task in the instructions. This enables a better user experience, because now after the tests are run, there is a button to return the student to the proper place.  

### Gratitude  
Besides allowing me to work on this full-time, the funding enabled me to adopt two beautiful kitty cats! Now, coding is even more fun. Many thanks to all who made this possible.  

```

## Project Kaocha: Arne Brasseur  <br>  
Update: 4 May 2023; Final Report<br>
Funding Round: Q3 2022<br>

In this last month of Clojurists Together funding the Gaiwan Team, in particular
Alys and Laurence, worked on a few different fronts. Kaocha, kaocha-cljs, and
kaocha-cucumber all saw smaller maintenance releases. Kaocha-cljs in particular
had some documentation improvements done. Testing ClojureScript is a bit
trickier than testing Clojure. The reason is that the compiler and the runtime
live in different processes (one runs in the JVM, the other in a JS environment
like your browser), and so there's significantly more communication and
coordination involved.  

You might also be wondering about Kaocha-cljs vs Kaocha-cljs2. Why are we still
releasing updates to the old version? In fact we maintain both, since they take
radically different approaches, and so are suitable for different situations.
The short version is that kaocha-cljs is much simpler in its setup, but is only
usable with a very "vanilla" ClojureScript setup. We use it a lot for testing
our libraries for instance.  

If kaocha-cljs doesn't cut if for you, you can use kaocha-cljs2. This one is
adaptable to virtually any cljs setup and compiler config, but it takes more
effort. We have some stuff in the pipeline though to hopefully make that easier,
so stay tuned!  

If you want to know more about what's involved in testing ClojureScript, and
about the differences between the two kaocha-cljs versions, you can listen to
the [ClojureScript Podcast with Jacek Schae Ep30](https://podcasts.apple.com/us/podcast/s4-e30-testing-wtih-arne-brasseur-part-2/id1461500416?i=1000523138440&l=da)
(since renamed to ClojureStream) where Arne and Jacek talk at length about these Topics.  

April also saw the first release of [Plenish](https://github.com/lambdaisland/plenish) a tool we (the Gaiwan Team)

have been using to help one of our clients do analytics over Datomic. It lets
you can transfer your Datomic database to a Postgres database, allowing for
analytical workloads to run on Postgres. For a, watch [Arne's talk about it](https://www.youtube.com/watch?v=BxK44tRhKMQ). A related project is EmbedKit,
which allows you to use Metabase as a dashboard engine, which is also mentioned
in Arne's talk, and which also saw a maintenance release in April.  

Finally we released a new version of Ornament, our css-in-clj(s) styled component libraries, improving support for Tailwind 3 classnames.
We want to thank the community for putting their trust in us, and allowing us to
work on these different projects over the last 6 months. And a huge thanks to
the people who support us directly on
[OpenCollective](https://opencollective.com/lambda-island). Due to staffing
challenges we didn't yet dip into the OpenCollective budget. We're going to do
some internal reflecting on how to best approach our open source work going
forward, to make sure it's relevant, visible, and sustainable, so stay tuned!  

### [Kaocha](https://github.com/lambdaisland/kaocha) 1.83.1314 (2023-05-05 / 1438ce7)
-  The watcher now prints the output from plugins, eg. `bin/kaocha --watch --plugin kaocha.plugin/profiling` 

### [Kaocha-cljs](https://github.com/lambdaisland/kaocha-cljs) 1.5.154 (2023-04-17 / f969eae)
-  Add documentation of common issue when trying to run tests that reference the DOM in a context without a DOM (e.g., Node.js).

-  Add documentation for using Kaocha-cljs with Node.js dependencies.

-  Fix integration test Windows error when setting POSIX file permissions  

### [Kaocha-cucumber](https://github.com/lambdaisland/kaocha-cucumber) 0.11.100 (2023-05-08 / 5c7009a)  
-  Added documentation for working with step definitions in the REPL.

-  Added documentation about Gherkin tag support.

-  Updated Kaocha dependency.

-  Bump version prefix to reflect total number of releases.  

### [Ornament](https://github.com/lambdaisland/ornament) 0.9.87 (2023-04-15 / dac82f4)  
-  Added a `:tw-version` flag for the preflight, similar to `set-tokens!`
-  Document how to opt-in to Tailwind v3   

### [Plenish](https://github.com/lambdaisland/plenish) 0.4.50 (2023-04-24 / 1d5eca9)  
-  First public release
-  Convenience function `sync-to-latest`
-  Fixed issue where multiple cardinality-many attributes would lead to clashing constraint names  

### [Embedkit](https://github.com/lambdaisland/embedkit) 0.0.56 (2023-04-14 / fd0bc4a)  
-  [breaking] Support only metabase version >= `0.46.1`
-  Change the API call parameters on `/api/dashboard/:id/cards<br>  

---` 

## Project Uncomplicate Neanderthal: Dragan Duric<br>   
Project Update: 28 April 2023; Final Report<br>
Funding Round: Q1 2023<br>  

My goal with this round was to implement Sparse Matrix support in Neanderthal.  
During the initial phases of the project, I realized that the domain area is bigger than I thought.

I also decided to use JavaCPP MKL bindings instead writing my own JNI C code. This prompted me to create the ClojureCPP library, with lots of helpers and convenience functions. It proved a great help when dealing with JavaCPP pointers and native shenanigans.  

Since Neanderthal is not based on JavaCPP pointers, I needed to create a port of Neanderthal's native CPU engine. I could have attached the new JavaCPP parts to the existing ByteBuffer-based engines, but that would introduce complexity in code. So I decided to take the opportunity and implement a prototype of native CPU implementation of a part of Neanderthal core that is based on JavaCPP. This helped me in learning how to deal with JavaCPP itself, since it has sparse documentation and a ton of functionality.  

So all this work went into another new project, Neanderthal Sparse, which I eventually plan to merge into Neanderthal. It contains both the port of existing CPU engines for vectors and GE matrices to JavaCPP, and the new sparse vector and sparse matrix implementations.  

Regarding sparse matrices, the domain is larger than I expected, which was not helped by extreme sparseness of the documentation, and even related information on the Internet in general. I had to discover many things by poking and experimenting (Clojure REPL wins big here!). In the end, I managed to implement compressed sparse vectors (CSV), and compressed sparse row matrices (CSR) (with both row and column layout support), all related data structure

interfaces that make sense with sparseness, the Float and Double engines with sparse vector operations, and the Float and Double engines with CSR operations for GE CSR matrices. I tested a lot of this, but it still needs polishing.  

I could only release snapshots, as I discovered a few omissions in JavaCPP MKL preset that are then fixed in the new version 1.5.9, which is still only a snapshot, so Neanderthal Sparse is still only available only as a snapshot on GitHub [https://github.com/uncomplicate/neanderthal-sparse](https://github.com/uncomplicate/neanderthal-sparse).  

All in all, I am satisfied with what has been done in this round. It may not be exactly what I imagined when I started, but become much broader, with a huge chunk of work that is more general and will benefit Uncomplicate libraries in future.  

** In short, Q12023 resulted in:**  
- The core of [Spare Matrix and Vectors implementations](https://github.com/uncomplicate/neanderthal-sparse) that is not as complete and polished as the rest of Neanderthal but that is a great first milestone.   

- The port of the core Neanderthal CPU engine (GE matrices and vectors) to JavaCPP, which will be the base of porting the rest of Neanderthal's CPU and CUDA implementation to JavaCPP.  

- A pretty complete [ClojureCPP library](https://github.com/uncomplicate/clojure-cpp), which opens the door for convenient integration of the whole JavaCPP native library ecosystem in Clojure.  

