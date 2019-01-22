title: Q1 2019 Funding Announcement
date: 2019-01-23
type: "post"
draft: true

Clojurists Together is happy to announce that for Q1 of 2019 (February-April) we are funding two projects: 

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. This funding cycle is our fifth. Previously we have supported [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [Figwheel](https://github.com/bhauman/lein-figwheel), [ClojureScript](https://clojurescript.org), and [CIDER](http://www.cider.mx/en/latest/).

## Neanderthal

“As there aren't any open issues with Neanderthal, ClojureCL and ClojureCUDA themselves, I think there is no point to do the usual "fix bugs Y, Z" as these I've already been taking care of immediately. This is more like "I continue doing what I've already been doing, just with additional publicity and extra care and the sense that the community is involved. And more frequently." My hunch tells me that the largest issue in this area is not so much the lack of tools, nor the lack of documentation, but the lack of the higher-level tutorials that give people the bigger picture and shows them good practices. I'm addressing this already, and this would help to address it more.

It is the infrastructure for high performance scientific software, including machine learning”

### Dragan's plans for the next three months are:

-	Improve the ecosystem around the project, especially the tutorials, or other areas that I discover people are interested at.

#### Perhaps something such as (and/or options; do not exclude other ideas that may surface later):
- Writing an introductory series Deep Learning from the ground up with Clojure (a companion to http://neuralnetworksanddeeplearning.com/ but with Clojure + Neanderthal + CPU + GPU) 
- Trying (and I hope succeeding) to integrate Nvidia's cuSolver into Neanderthal's CUDA GPU engine (provides some key LAPACK functions that are currently only available on the CPU)
- Working on promoting Uncomplicate and Clojure in non-Clojure programming/ML community


## Aleph

“Aleph is one the best/advanced option for creating high-performance communication systems, including but not limited to HTTP & websocket clients & servers. The list of projects/companies who relies on Aleph to be maintained might be found here: https://github.com/ztellman/aleph/issues/450. Development of Aleph has a pretty good impact on the entire server-side libraries ecosystem, including direct influence by backporting bug fixes to such projects like clj-http (Clojure), Netty (Java) and potentially Ring (Clojure); and indirect influence by pushing boundaries and expending interest to the field.”

### Oleksii's plans for the next three months are:

•	Stabilize and release a new version with latest development (quite a few new features)

•	Tweak internals of the library and interactions with Netty to ease operational burden, to improve performance and to make sure Aleph can stay robust in all use cases

•	Implement new features (ideas are listed below), 1 by 1 
    o   A lot of new ideas and improvement areas are described here: https://github.com/ztellman/aleph/issues



## Voting details

The projects that applied were:

-
-
-
-
-
-

## Q1 2019 Funding

We had a bunch of great applications from great projects; we would have liked to fund several more projects if we had the money. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, you can re-use that application to [apply](/open-source/) for Q1 2019. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
