title: Q4 2018 Funding Announcement
date: 2018-08-03T16:41:59+13:00
type: "post"
draft: false

Clojurists Together is happy to announce that for Q4 of 2018/19 (November-January) we are funding two projects: Datascript and Kaocha.

## Recap

For those who are new to Clojurists Together, our goal is simple: Fund critical Clojure open-source projects to keep them healthy and sustainable. Clojure companies and individual developers sign up for a monthly contribution, and we pick projects to fund each quarter. This funding cycle is our fourth. Previously we have supported [cljdoc](https://cljdoc.xyz), [Shadow CLJS](https://github.com/thheller/shadow-cljs), [clj-http](https://github.com/dakrone/clj-http/), [Figwheel](https://github.com/bhauman/lein-figwheel), [ClojureScript](https://clojurescript.org), and [CIDER](http://www.cider.mx/en/latest/).

## Datascript

[Datascript](https://github.com/tonsky/datascript) is a Clojure and ClojureScript database. DataScript has been around for 3+ years, has gotten massive interest from the community, is used in in several production projects (listed on GitHub page), and has been starred on GitHub 3000+ times. Nikita Prokotov is the project creator, and currently maintains Datascript.

Nikita's plans for the next three months are:

- Extend query language to achieve parity with Datomic (OR, NOT predicates)
- Optimizing query execution speed (work on both has started, but not finished)
- Faster bulk transactions with transient implementation of BTree Set
- After that, experiment with adopting DataScript for more interesting UI development: reactive entities and queries, subscriptions, efficient disk persistence (for server-side and client-side via localStorage)

If optimized sufficiently, DataScript could become the de-facto, default go-to database for small and middle sized Clojure projects, both on the server-side and client-side.

## Kaocha

[Kaocha](https://github.com/lambdaisland/kaocha) is a Clojure test tool. Kaocha raises the bar for what to expect from tools and provides a platform for innovation and collaboration. Clojure does not have the strongest testing culture, and has not had as much investment into test tooling as other communities. Better tooling would encourage better testing practices, a better testing culture would encourage investing in tooling. Arne Brasseur is the main (and sole) developer of Kaocha.

Arne's plans for the next three months are:

- ClojureScript support
- Expectations support
- Improved Midje support
- Cloverage support
- Boot support
- Prettier diffing of `(is (= ...))`
- TAP reporter
- Junit.xml reporter
- Run tests in parallel

## Funding details

Each project receives a grant of $1,800USD/mo for three months.

## Voting details

This quarter proved to be a close race between Kaocha and Neanderthal, who tied for the place of the second funded project. We broke the tie using [random.org](https://www.random.org) to pick the winner. For the first time we used the [CIVS](https://civs.cs.cornell.edu) online voting service fron Cornell.

The other projects that applied were:

- [Neanderthal](https://github.com/uncomplicate/neanderthal)
- [Klipse](https://github.com/viebel/klipse)
- [clj-debugger](https://github.com/razum2um/clj-debugger)
- [Doo](https://github.com/bensu/doo)
- [Hoplon](https://github.com/hoplon/hoplon)
- [Origami](https://github.com/hellonico/origami)
- [Clojurecademy](https://github.com/clojurecademy/clojurecademy)
- [Cloud-Native-Clojure](https://github.com/cloudnativeclojure)
- [cljsh](https://github.com/razum2um/cljsh)

## Q4 Funding

We had a bunch of great applications from great projects; we would have liked to fund several more projects if we had the money. If you'd like to see more projects get funded, then please join. If you applied for the last funding cycle, you can re-use that application to [apply](/open-source/) for next years Q1 funding round. If you maintain a Clojure/ClojureScript project that is important to the community, consider applying for funding so we can help you keep it sustainable.

Lastly, a big thank you to all of our [members](/members/). We couldn't have done it without your support.
