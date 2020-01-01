---
title: June 2019 Monthly Update
date: "2019-07-01"
type: post
summary: "It's been a successful second month with our selected Q2 project, Fireplace, check out the updates below. The Call for Proposals for Q3 Projects at Clojurists Together is now open!"
---

## Clojurists Together

Hi CT Community -

It's been a successful second month with our selected Q2 project, Fireplace, check out the updates below.

The [Call for Proposals](https://www.clojuriststogether.org/open-source/) for Q3 Projects at Clojurists Together is now open!

Just a reminder that Daniel is speaking about Clojurists Together again soon. Catch him at one of the following local events:

[Heart of Clojure](https://heartofclojure.eu/) - [Money for Nothing: The past and future of funding OSS](https://heartofclojure.eu/program#daniel-compton)
<br /> August 2nd in Leuven, Belgium

[Strange Loop](https://thestrangeloop.com) - [A Stitch in Time - The future of OSS Sustainability](https://thestrangeloop.com/2019/a-stitch-in-time---the-future-of-oss-sustainability.html)
<br /> September 12-14 in St. Louis, Missouri, USA

Thanks for your continued support of Clojurists Together.

### Fireplace update

Job support at an API level is live on master for both Vim and Neovim.
The first user facing feature to take advantage of this is the test
runner, which is now fully asynchronous.

Commits:

* Warn, don't error, on missing eval value
* Replace load-file hack with eval file/line/column
* Remove nREPL version check
* Use native quickfix title support
* Tighten match for stacktrace entry
* Process test results incrementally
* Use quickfix list id if available
* Accept job style errors
* Remove hack for newlines in shell arguments
* Make python socket connection lazy
* Eliminate interactive host/port input
* Tweak regexp to better match URL
* Accept a bang to :Connect
* Fix yet another Python 3 buffering issue
* Fix convolution getting Boot class path
* Fix infinite loop on disconnect
* Fix syntax error
* Preliminary support for Vim 8 and Neovim jobs

This update brings us a handful of miscellaneous user facing features
plus a lot of refactoring to get the data model in line with async
support.

Commits:

* Make omnicomplete (sort of) async
* Try tightening polling loop
* Eliminate session.process()
* Eliminate session.prepare()
* Retool stacktrace retrieval
* Move interrupt handling from eval to all ops
* Track sessions at transport level
* Rename nrepl namespace to session
* Rename s:nrepl to s:session
* Move class path retrieval to transport layer
* Add support for spec-form and spec-example
* Fix documented option names
* Better ClojureScript REPL configuration
* Return request id for asynchronous message
* Support :Connect ./.nrepl-port
* Return transport rather than session when registering port file
* Update Piggieback support for transport object
* Move describe to transport layer
* Move message handing logic to transport layer
* Rewrite connection namespace as transport namespace
* Further tighten quickfix stacktrace match
* Remove outdated documentation references to if_pyth
