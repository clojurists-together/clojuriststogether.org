---
title: "August 2023 Short Term Project Updates" 
date: 2023-09-02T08:30:00+08:00 
summary: Aleph, clj Nix, Clojure Camp, Clojure Cookbook, Donut, Jank, Lucene Grep, Neanderthal, Portfolio
author: Kathy Davis


---

Check out the latest from the shorter term projects we funded in Q1 and Q2 2023 and Q3 2022. We've got updates from Christian Johansen, Daniel Higginbotham, Danius Jocas, Dragan Duric, Jeaye Wilkerson, José Luis Lafuente Esquembre, Kira McLean, and Matthew Davidson.

A quick reminder to seriously consider volunteering for a 2-year term on our board. We have 4 positions opening up this year. The deadline for submitting your name is Sept. 15. You can help us to support all the great work underway and continue to get the word out about the Clojurists Together funding opportunities and our awesome community. Find more information [here](https://www.clojuriststogether.org/news/2023-board-nominations-and-our-annual-meeting).<br>


[Aleph Manifold: Matthew Davidson](#aleph-manifold-matthew-davidson)  
[clj-Nix: José Luis Lafuente Esquembre](#clj-nix-josé-luis-lafuente-esquembre)  
[Clojure Camp: Daniel Higginbotham](#clojure-camp-daniel-higginbotham)  
[Clojure Cookbook: Kira McLean](#clojure-cookbook-kira-mclean)    
[Donut: Daniel Higginbotham](#donut-daniel-higginbotham)    
[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)    
[Lucene Grep: Dainius Jocas](#lucene-grep-dainius-jocas)   
[Neanderthal: Dragan Duric](#neanderthal-dragan-duric)    
[Portfolio: Christian Johansen](#portfolio-christian-johansen)  <br>



## Aleph Manifold: Matthew Davidson  
Project Update: 31 August 2023  
Q1 2023 Funding Round: Report 3

### HTTP/2 – server  
- Final HTTP/2 support on the server is now under review. The code is written, but the existing test suite is fragile and specific to the HTTP/1 code, so many tests still need updating.
- Highlights
  - Massive refactoring and documentation improvements  
  - HTTP/1 code adapted for independent HTTP/2 multiplexed streams  
    - Much simplified core   
      - prior connection state no longer required since streams are independent
      - client and server streams now similar enough that more code can be shared between them, unlike the HTTP/1 code
  - Application-level protocol negotiation (ALPN) now the default for all servers
    - specify which HTTP versions you support, and the server will pick as needed for incoming connections
    - falls back to HTTP/1 if needed
  - Cleartext H2C supported on the server
  - Header and date code in hot loop (used literally every response) now optimized
    - Upgraded deps to Netty 4.1.96.Final, byte-streams 0.3.4  

### Other Aleph improvements
- Added instructions to CONTRIBUTING on SSL/TLS certs and CA setup and debugging, which are vital for HTTP/2 work  

### Planned future work  
- Straighten out the tests
- Add compression support
- Add more handler options for settings and window updates
- Add multipart support
  - HTTP/2 doesn't need it, and unfortunately, Netty's HTTP2 code doesn't support it, but some people said they'd like to keep using it to minimize disruption to their code base
- Add proxy support  

### Potential new directions  
- HTTP/3
  - Should be relatively easy (🤞), since the gap between HTTP/2 and 3 is much smaller than between 1 and 2. Would mostly need to handle the switch to QUIC/UDP.
- Flow control
  - HTTP/2's flow control mechanism was deprecated for complexity and disagreement between browsers, but not actually removed. We could add code to support this if people really wanted it. A better option would be to support HTTP/3's new flow control headers. <br>

---


## clj-Nix: José Luis Lafuente Esquembre
Project Update: 30 August 2023  
Q2 2023 Funding Round: Report 2  
  
In the second half of this funding round, I worked on making the project more user-friendly.

### New documentation  
I created a new website for the documentation:
https://jlesquembre.github.io/clj-nix/

### New nix module
I added the possibility of configuring the build using a nix module, see
[clj-nix Module](https://jlesquembre.github.io/clj-nix/nix-module/).

The nix module is marked as experimental, but usable. It's the way I recommend
using `clj-nix`, since it hides the project internals. If you already are a
`clj-nix` user, you can continue calling `mkCljBin` and related functions. Under
the hood, the module calls those functions, and I'll continue maintaining the
[functional API](https://jlesquembre.github.io/clj-nix/api/).

I'll continue working on the module interface, feel free to
[open an issue on GitHub](https://github.com/jlesquembre/clj-nix/issues) to
share any feedback.

### Maintenance  
I also spent some time fixing some bugs and doing some minor improvements, see the
[full list on GitHub](https://github.com/jlesquembre/clj-nix/pulls?q=is%3Apr+is%3Aclosed+created%3A2023-07-14..2023-09-01) <br>

---

## Clojure Camp: Daniel Higginbotham  
Project Update: 31 July 2023  
Q2 2023 Funding Round: Report 2  

At Clojure Camp we continue to build our materials for mentors and students and to hold a study group. We’ve also begun reaching out to industry stakeholders and mentors to shape a long-term roadmap for this effort that increases the likelihood that participants will be able to find paid work.  
### Accomplishments include:
- Continuing weekly study group sessions
- Building out [https://currmap.clojure.camp/](https://currmap.clojure.camp/), a clear path for learning Clojure
- Made more progress on setting up our operations
- Started industry outreach, looking for partners that can help on both the jobs side and on the marketing side <br>

--- 

## Clojure Cookbook: Kira McLean  
Project Updates: 1 June and 2 September 2023  
Q3 2022 Funding Round: Reports 2 & 3  

**Report 2:** 
The last few months of development on the Clojure Data Cookbook have been focused on filling out the main reasons for the book, which have been translated in to some of the introductory chapters, as well as implementing more of the examples that are the core of the book. Whilst development has been slower than I would like due to the nature of being an adult with a hectic life, I'm excited about the progress that's been made and look forward to polishing the first three chapters over the summer. Some specific work that's been done in this last phase includes:
- Sub-chapters 2.2 and 2.3 have been filled out more, along with more of the introductory part of the book. Examples for working with different types of databases are implemented as well as ones for exporting data from a notebook. Commits including this output are available on github.
- Much effort has gone into coming to a consensus within the community around what default data science "stack" to implicitly endorse by its inclusion and usage in the book. We've settled on tablecloth for tabular data processing, Hanami for data visualisation, Neanderthal for math, along with several other libraries that are emerging as the "standard" ones for certain tasks working with data in Clojure.
 - This, and much of the thought that went into the introductory chapters, was mostly what formed the core of the talk I gave at the conj this year. More of the libraries that will be used are on slide 6 in the talk.
- An initial solution for publishing the book online has been implemented. I'll want to improve the process as updates are published more regularly, but for now a basic skeleton of the book website is available online here: https://scicloj.github.io/clojure-data-cookbook/ (URL is likely to change for the final version, this one should work from June 1, 2023 for at least few months.)  

**Report 3:** 
In the last couple of months I've made progress toward completing the contents of the cookbook, but I've had to admit to myself that I took on more than I could realistically do in my spare time. Despite my best efforts there is still much work to be done compiling the book into a consumable format.  

I will continue this work indefinitely and still plan on finishing this project, but the demands of work and other commitments on my time have meant that progress is slower than I would have hoped on this side project.  

For now, the information is mostly complete and, with help from the community, best practices for performing a variety of data manipulation and analysis tasks are now documented in the cookbook repo. These are not filled in yet with prose or detailed explanations and not organized in a way that would be ideal to consume for someone who wasn't already comfortable navigating a Clojure codebase. But progress has definitely been made. The contents of the first three chapters are ready to be edited into a consumable format -- the examples are written, the code works, and the steps are documented. The next task on my list is organizing all of the code into sensible sections with explainer text surrounding them so that the book can be published somewhere online.<br>

---


## Donut: Daniel Higginbotham  
Project Update: 6 August 2023  
Q1 2023 Funding Round: Report 3  

Since the last update I've made progress on creating an auth plugin. There are implementations in place for cookie-based auth and a Sign In With Google button. The next step is to abstract these for more general usage.  
Per-repo changes include:
### [donut-party/endpoint](https://github.com/donut-party/endpoint)  
* **Re-worked the implementation of ring middleware**
  I had been using the ring-defaults library to compose a foundational
  middleware stack but I found it too difficult to debug. The middleware are all
  configured as donut components so that it's easier to inspect what middleware
  are being used and what their configurations are, and so that there's a
  consistent interfact for changing middleware options.
  
* **Cookie session store component**
  Added a donut component that lets you configure a cookie session store  
  
### [donut-party/email](https://github.com/donut-party/email)  
A new library that provides a helper for constructing emails independent of
whatever email service you use. Features include:
* Use text and HTML email templates for email body
* Use text template for email subject
* Set global options (like :from) and easily override those options
* Parameterizes the email sending function so you can replace it with a mock in
  tests
* Parameterizes template rendering function so you can use something other than
  selmer if you want

### [donut-party/frontend](https://github.com/donut-party/frontend)  
* Added `load-script` helper function for asynchronously loading js scripts
* Updated form handling so that you can set initial form values adjacent to the
  form's components, as opposed to having to set the values via a re-frame
  dispatch
* Handle auth and identity concerns
* Created a frontend route writer, compiling frontend routes to a cljc file so
  that the backend can access the routes
 
### [donut-party/system](https://github.com/donut-party/system)  
* Introduced registry refs. This allows components to refer to each other using
  a registry name, as opposed to an absolute path in the system. Useful for
  plugin development.
* Added a mock-component helper which can be used to mock function components.
  In cases where your component initializes to a function (e.g. `send-email`),
  you can now easily mock those components for tests. The mock function records
  arguments for test comparisons, and you can set the value it should return.  

I appreciate the support of Clojurists Together and the donut community!<br>

---

## Jank: Jeaye Wilkerson  
Project Update: 26 August 2023  
Q2 2023 Funding Round: Report 2   
 
The terms of this work were to research a new object model for jank, with the goal
of optimizing allocations, while also making jank code faster across the board.
This is my second and final report and I'm excited to share my results!

Please note that I have a very detailed breakdown of my results, with several
more graphs, here: https://jank-lang.org/blog/2023-08-26-object-model/

To briefly summarize, this quarter I:

* Prototyped a new object model for jank
* Ported the whole jank compiler and runtime to the new object model
* Benchmarked thoroughly and continuously

### Overall ray tracing speeds
I'm very pleased to report that **jank is now nearly twice as fast at running the same ray tracing code as Clojure JVM**,
with jank clocking in at 36.96ms versus Clojure's 69.44ms. Since jank was only
marginally faster than Clojure at the start of the quarter, this also means the
improvements in the past quarter have been nearly 2x overall.

<object type="image/svg+xml" data="https://jank-lang.org/img/blog/2023-08-26-object-model/ray-tracing.plot.svg" width="50%">
  <img src="https://jank-lang.org/img/blog/2023-08-26-object-model/ray-tracing.plot.svg" width="50%"></img>
</object>

### Other runtime objects
Maps, vectors, and strings have all seen performance improvements to both
allocations and usage. Some improvements were marginal, while others were in the
2x region. My full post linked above goes more into detail and talks about
potential future gains.

### Fast math
Math has sped up the most out of anything, which bodes very well for our ray
tracing numbers. Here are the results for fully boxed subtraction, where no
type info is known, subtraction between an unknown box and an unboxed
double, and fully unboxed subtraction. In all cases, jank is now significantly
faster than Clojure JVM. These wins apply across the board for all binary math
operations.

<object type="image/svg+xml" data="https://jank-lang.org/img/blog/2023-08-26-object-model/boxed-sub.plot.svg" width="50%">
  <img src="https://jank-lang.org/img/blog/2023-08-26-object-model/boxed-sub.plot.svg" width="50%"></img>
</object>

### Thank you!
Thank you, Clojurists Together, for sponsoring this work. jank has climbed quite
the mountain of performance gains to now challenge the JVM in these benchmarks.

This is the last performance-oriented bout of work for a while. jank is where it
needs to be, I think, in order for me to start investing more in pushing the
compiler and runtime features closer to parity with Clojure JVM.  

I'm very happy to know that Clojurists Together is sponsoring jank development
*again*, for the upcoming quarter. The sponsored work will be focused on
building out jank's module system, implementing `clojure.core/require`,
preparing for iterative compilation, and setting the stage for AOT compilation
and leiningen integration.  

After this work, using jank for multi-file projects will be possible. Soon after
that, I hope, we can start using leiningen to manage jank projects. This will
mean adventurous devs can start actually using jank themselves, which I expect
will only add to the momentum I currently have. <br>  

---

## Lucene Grep: Dainius Jocas  
Project Update: 1 September 2023  
Q2 2023 Funding Round: Report 2  

The highlight of this update is the release of the [lucene-monitor](https://github.com/dainiusjocas/lucene-monitor) Clojure library.

`lucene-monitor` is a Clojure wrapper around the [Apache Lucene Monitor framework](https://lucene.apache.org/core/9_7_0/monitor/org/apache/lucene/monitor/package-summary.html).
I've tried to make the library simple to get started and flexible **when** needed.
`lucene-monitor` provides a pretty tasty data-driven API and, given my recent interest in Clojure transducers, a transducer compatible API.
Check it out and let me know what you think!  

### Updates  

As of now, 4 Clojure libraries are extracted out of the `lucene-grep`:  
- [lucene-monitor](https://github.com/dainiusjocas/lucene-monitor): a wrapper for the Lucene Monitor framework;
- [lucene-custom-analyzer](https://github.com/dainiusjocas/lucene-custom-analyzer): data-driven builder for Lucene Analyzers;
- [lucene-query-parsing](https://github.com/dainiusjocas/lucene-query-parsing): data-driven builder of Lucene Query Parsers;
- [lucene-text-analysis](https://github.com/dainiusjocas/lucene-text-analysis): helpers to experiment with the Lucene Analyzers.

All these libraries were updated to the newest Lucene version.  

### Other things I've worked on:  

### Lucene Grep  
The `lucene-grep` was updated and released with these improvements:
- Lucene 9.7.0;
- The prepared Lucene Analyzers can now be added and loaded via SPI.  

Also, I've experimented with [Apache OpenNLP integration](https://github.com/dainiusjocas/lucene-grep/pull/219) into the `lucene-grep`.
So far I don't know whether to include new dependencies by default, because it makes the binary bigger, compile times longer,
and the `lucene-opennlp` includes a pretty outdated version of the OpenNLP library.

### Lucene in Vespa.ai  
I've [contributed a Lucene Linguistics](https://github.com/vespa-engine/vespa/pull/27929) component to [Vespa.ai](https://vespa.ai/).
This is not strictly based on `lucene-grep` or related to Clojure 
(maybe it is possible to start a REPL in Vespa container nodes after all?)
but the work is heavily inspired by [lucene-custom-analyzer](https://github.com/dainiusjocas/lucene-custom-analyzer) library
(and learnings while making it) which was extracted from the `lucene-grep`.
Sample apps with the Lucene Linguistics component are coming [hopefully soon](https://github.com/vespa-engine/sample-apps/pull/1264).
The component should make the transition from Lucene based search engines like Elasticsearch to Vespa almost a mechanical task.
I encourage anyone interested in search, recommendation systems, or information retrieval in general, to give Vespa.ai a try.
it is great!

### What is next?  
Even though the sponsorship from Clojurist Together is over I'm planning to have more fun with Clojure and Lucene: 
- I'll try to integrate the extracted libraries to other Clojure projects that depend on Lucene.
- Build a demo of an [Elasticsearch-Percolator-like](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-percolate-query.html) 
system that does monitoring in a scalable and distributed mode.
- A blog post on using the shiny new [Word2VecSynonymFilter](https://lucene.apache.org/core/9_7_0/analysis/common/org/apache/lucene/analysis/synonym/word2vec/Word2VecSynonymFilter.html) from Clojure. <br>

---

## Neanderthal: Dragan Duric  
Project Update: 31 July and 31 August 2023  
Q2 2023 Funding Round: Reports 2 and 3  

**Report 2:**
My goal with this round is to port Neanderthal, ClojureCUDA, and Deep Diamond to JavaCPP-based native libraries.

This second month was dedicated to porting the CPU data structures and engines from DirectByteBuffer-based
custom C bindings (Neanderthal Native) to JavaCPP-based MKL bindings. I managed to port all structures
and engines, and make all related tests pass. I also hoped to do the CUDA engines and data structures
to JavaCPP from the old JCUDA bindings, but I'm late on that. It's something that I hope to do in the
first half of the third month.

Of course, that wasn't a straight port. On one hand, I had to make internal changes and adaptations
to support JavaCPP's way of doing things. On the other hand, I used this as an opportunity to
explore avenues to decrease the code size through more macrology and to push more intensely towards
"don't repeat yourself" style. It's a double-edged sword, I know, but MKL and other similar APIs
are massive, and I am a sole person who has to support all these exotic obscure functionalities...

Next month will be dedicated to porting Neanderthal's CUDA backend to use JavaCPP (via ClojureCPP).

All in all, I feel that the project is still on track even though I would have liked to achieve more
in the first two months, but it is what it is. I still think I'll be able to port ClojureCUDA, Neanderthal,
and Deep Diamond to ClojureCPP by the end of the third month, or at least everything except Deep Diamond.

What's equally important, I'm using every opportunity to *improve* everything that I could,
instead of just taking the shortest cut at every corner. So the benefits will be felt long-term in
these libraries, as well as this domain in Clojure.

I released most of the resulting code in a couple of gigantic commits instead of many smaller ones
because I didn't want any release to contain a completely broken library.<br>



**Report 3:** 
My goal with this round was to port Neanderthal, ClojureCUDA, and Deep Diamond to JavaCPP-based native libraries.

More specifically, I proposed to implement:
- a port of Neanderthal's MKL engines and CPU related stuff to JavaCPP instead of neanderthal-native (for sure).
- a port of ClojureCUDA to JavaCPP's CUDA. (probably, depending how 1 goes)
- a port of Neanderthal's GPU matrix engine to new, JavaCPP-based ClojureCUDA (almost sure, if ClojureCUDA port goes well)
- update Deep Diamond to use new infrastructure.
- improve relevant internal parts of Neanderthal code with more macrology (double-edged sword, I know, but also concentrates bugs at one place each).
- TESTS! to make sure everything works as well as before (of course!)

In the third month, I continued with porting the Neanderthal's CUDA engine. This wasn't without challenges,
but, surprisingly, it went well after all. After this, I hoped that Deep Diamond's port would be
a bit easier, since a lot of internal details are behind the Neanderthal's and ClojureCUDA's API.
However, for proper MKL API support in JavaCPP I had to use the very latest 1.5.10 snapshots.
These snapshots then mandate DNNL (Intel's tensor and DL library) upgrade to 3.2 (from the 2.7 that
was used in Deep Diamond previously). Alas, Intel introduced many breaking changes in the API and
implementation, so I have to play the good old discover-how-it-works-by-poking-around,
and do the full blown re-implementation of DNNL API internal support in Deep Diamond,
instead of just porting a few internals to JavaCPP's way of doing things. I've started
doing that, and as of this writing, I've discovered how to solve most tricky changes, and
I am in the middle of switching the internal/dnnl part of Deep Diamond.

More specifically, here's the status by the proposed bullet points:
- DONE: a port of Neanderthal's MKL engines and CPU related stuff to JavaCPP instead of neanderthal-native (for sure).
- DONE: a port of ClojureCUDA to JavaCPP's CUDA. (probably, depending how 1 goes)
- DONE: a port of Neanderthal's GPU matrix engine to new, JavaCPP-based ClojureCUDA (almost sure, if ClojureCUDA port goes well)
- PARTIAL (in progress): update Deep Diamond to use new infrastructure.
- DONE: improve relevant internal parts of Neanderthal code with more macrology (double-edged sword, I know, but also concentrates bugs at one place each).
- DONE (but of course, this is something that is ongoing anyway): TESTS! to make sure everything works as well as before (of course!)

In short, although I haven't managed to do everything that I hoped I will, I'm satisfied
since I've done the major parts, and the unexpected DNNL upgrade would have to be done anyway
eventually.

As I've been lucky to be granted one more CT funding round, which is centered on improving
the overall quality of Uncomplicate libraries, upgrading the Deep Diamond support for DNNL
and new CUDA infrastructure would fit well with the tasks that I'm planning to do in the Q3
round anyway, so I expect that by the end of it I'd be very satisfied with the result.<br>

----

## Portfolio: Christian Johansen  
Project Update: 31 August 2023  
Q2 2023 Funding Round: Report 2  

In the second half of the Q2 funding period I have worked on the plugin capabilities in Portfolio, the new accessibility plugin, and barked up some wrong trees in an attempt at fixing a tricky problem 😅

Plugins can now inject JavaScripts into the iframe document where Portfolio renders your "scenes" (your component examples). In order for these scripts to be able to do something useful, Portfolio will use `window.postMessage` to announce when components have been rendered, and listen for commands via the same channel - allowing custom scripts to interact with Portfolio's data storage (and, by extension render interesting things in its UI).  

I built a new accessibility extension on top of the new capabilities. It uses
[axe-core](https://github.com/dequelabs/axe-core) to assess your components. Validation can be switched on and off in the UI (or completely disabled in
code). When on, components will be assessed as you navigate through them. There
is also a global option to assess all components and have results highlighted in
the left-hand menu, which should make it easier to track down non-conforming
components.

The accessibility feature isn't quite ready yet, but I'm expecting to release it in September.
As for my barking at trees, I have tried to solve an outstanding issue in Portfolio's rendering. Portfolio invokes components in the main process, then renders the result in an iframed document. This breaks down for components that are side-effects, such as some solutions for CSS-in-CLJS (which stick style tags
in `<head>`). I have tried to a few ways to solve this in a way that supports the current API, but all of them have had worse side-effects than the current situation, so I have put this issue on the back-burner.  

Once again thank you so much for the support. Portfolio is a much better tool thanks to it.






