## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve with this funding?

- Integration of Clojure specs 
- Integration of user-contributed examples 
- Offline documentation downloads (Dash/Zeal integration) 
- Improve overall user experience / UI 
- Support users and make them aware cljdoc exists 
- Build an active community around this project, ensure there's more than just one developing it 

### Why is this project important to the Clojure community?

Cljdoc is a website that builds and hosts documentation for Clojure and ClojureScript libraries. Poor and out-of-date documentation has long been a complaint of Clojure developers. It improves the state of Clojure documentation (libraries + language) by providing minimal-effort documentation building and hosting for all Clojure jars on Clojars and Maven.

## Work log

Septemeber 2018

Things that have been shipped in September:

- A toggle to view raw docstrings ([PR #117](https://github.com/cljdoc/cljdoc/pull/117))
- A first iteration at what may become an interactive article TOC, currently just showing what section you're in ([PR #116](https://github.com/cljdoc/cljdoc/pull/116))
- OpenGraph meta tags (cljdoc links should render much nicer on Slack, Twitter & co)
- An [issue](https://github.com/cljdoc/cljdoc/issues/113) with UTF-8 article slugs has been fixed
- Cleanups in various places of the code removing unused code
- Improvements to the way the classpath is constructed for analysis ([commit](https://github.com/cljdoc/cljdoc/commit/422f4636167d3534a9b636faf3d5c2ca7fa04eeb))
- A bug with links in offline docs has been fixed ([commit](https://github.com/cljdoc/cljdoc/commit/125f4f6c6ccd0e93e3c89bd44834e16248f2d55d))

October 2018

### The Good News

The influx of people to cljdoc has been really amazing, there were about 10 new contributors, some of them really stepping up by helping to review and merge pull requests as well as supporting other newcomers in [our Slack channel](https://clojurians.slack.com/messages/C8V0BQ0M6/).

[**27(!) pull requests**](https://github.com/cljdoc/cljdoc/pulse/monthly) by 10 authors were merged, 40 issues were active, with 28 of them now closed. Shout out to Avichal, Saskia, Daniel, Albrecht, Jorin, Greg, Martin, Travis, Randy and everyone else who contributed through discussions and feedback!
I believe a wide contributor base is critical to ensure longterm success of cljdoc and I look forward to welcome more contributors in the future.

### The Bad News

Examples... well. I didn't ship them. I spent a fair amount of time on it but eventually decided that it's not the right thing to focus on at this point. For examples to really make an impact cljdoc adoption needs to be much higher and there's a lot more stuff that I perceive as more impactful at this stage.

There also needs to be more discussion with the wider ecosystem to make examples useful and maintainable (~testable). Please hit me up if you have thoughts in that direction!

More details in [ADR-0014](https://github.com/cljdoc/cljdoc/blob/master/doc/adr/0014-add-support-for-examples.md) (initial decision to integrate examples) and [ADR-0015](https://github.com/cljdoc/cljdoc/blob/master/doc/adr/0015-cancel-work-on-examples.md) (reversal of that decision with more context/reasoning).

- We migrated from cljdoc.xyz to cljdoc.org and made lots of tiny improvements to make sure cljdoc is being indexed by search engines properly.
- Avichal added build stats to [cljdoc.org/builds](https://cljdoc.org/builds) giving us some insight into failure rates of documentation builds. [Help lower it.](https://github.com/cljdoc/cljdoc/blob/master/…)
- Randy improved our [404 page](https://cljdoc.org/clojurists-together-rules) by adding the familiar search that is available on the front page.
- We set up JS packaging making docs load even faster. cljdoc's Lighthouse performance score now is 99.
- @rakyi helped set up Prettier so our JavaScript code is consistently formatted. This is something that we might also do for Clojure code in the future.
- @jsimpson-ovo built out proper support for GitLab. This mostly worked before but now it's is on par with GitHub. Source URLs, article edit links and more just work now.
- Lots of improvements aimed at new contributors. Better support for Cursive, an improved [`CONTRIBUTING`](https://github.com/cljdoc/cljdoc/blob/master/CONTRIBUTING.adoc) and better instructions for [running cljdoc locally](https://github.com/cljdoc/cljdoc/blob/master/doc/running-cljdoc-locally.md).

