---
title: October 2018 Monthly Update
date: "2018-11-04"
type: post
author: Rachel Magruder
---

## Clojurists Together News

We are happy to close the month of October with a successful third quarter at Clojurists Together. Earlier this month, we announced our [new project selections](https://www.clojuriststogether.org/news/q4-2018-funding-announcement/) for Q4, Datascript and Kaocha.

Welcome to all of our new members that joined this month. Special thanks to new Filter company members [Pitch](https://pitch.com/), [AdGoji](http://www.adgoji.com), and [Funding Circle](https://www.fundingcircle.com/de/). It's because of your support that we can continue to grow at Clojurists Together.

There is a new part of our monthly newsletter and website, with job listings in the Clojure community. We've partnered with our company Filter members to offer more career opportunities to our developer members. Check out the new [Jobs](https://www.clojuriststogether.org/jobs/) page.

This month in listings, [Senior Backend Engineer](http://careers.pitch.io/p/e1ff8b25796e01-senior-backend-engineer) at Pitch and [Senior Frontend Engineer at Pitch](http://careers.pitch.io/p/4977a65eeaea01-senior-frontend-engineer).

Last week we sent out an email to members about collecting addresses in order to send out CT stickers. If you want one and still haven't submitted your address, you can do so [here](https://docs.google.com/forms/d/e/1FAIpQLSewql7CecP0beNQMV6YLlD-tjbvnQLtNcxkR7SGBojbk9juzQ/viewform?usp=sf_link) before Nov. 14th in order to receive a sticker in the first round of mail.

And finally, we are holding elections for new Clojurists Together committee members. More information [here](https://www.clojuriststogether.org/news/2018-committee-nominations/).

## ClojureScript update

A lot of the work that Mike Fikes did on ClojureScript is part of a [new release of ClojureScript](https://clojurescript.org/news/2018-11-02-release), version 1.10.439. A big highlight is that compiler performance has been greatly improved, with projects sometimes seeing a 2x speed-up. There is also some really interesting work with supporting a Graal.js REPL environment. Thanks Mike!

## cljdoc Update

Hey again dear Clojurists Together crew! Those last three months flew by and so much stuff has happened around cljdoc!

For me the most important things were onboarding more contributors and allowing library authors and users to add examples to their APIs.

### The Good News

The influx of people to cljdoc has been really amazing, there were about 10 new contributors, some of them really stepping up by helping to review and merge pull requests as well as supporting other newcomers in [our Slack channel](https://clojurians.slack.com/messages/C8V0BQ0M6/).

[**27(!) pull requests**](https://github.com/cljdoc/cljdoc/pulse/monthly) by 10 authors were merged, 40 issues were active, with 28 of them now closed. Shout out to Avichal, Saskia, Daniel, Albrecht, Jorin, Greg, Martin, Travis, Randy and everyone else who contributed through discussions and feedback!
I believe a wide contributor base is critical to ensure longterm success of cljdoc and I look forward to welcome more contributors in the future.

### The Bad News

Examples... well. I didn't ship them. I spent a fair amount of time on it but eventually decided that it's not the right thing to focus on at this point. For examples to really make an impact cljdoc adoption needs to be much higher and there's a lot more stuff that I perceive as more impactful at this stage.

There also needs to be more discussion with the wider ecosystem to make examples useful and maintainable (~testable). Please hit me up if you have thoughts in that direction!

More details in [ADR-0014](https://github.com/cljdoc/cljdoc/blob/master/doc/adr/0014-add-support-for-examples.md) (initial decision to integrate examples) and [ADR-0015](https://github.com/cljdoc/cljdoc/blob/master/doc/adr/0015-cancel-work-on-examples.md) (reversal of that decision with more context/reasoning).

### Things that have been shipped in October:

- We migrated from cljdoc.xyz to cljdoc.org and made lots of tiny improvements to make sure cljdoc is being indexed by search engines properly.
- Avichal added build stats to [cljdoc.org/builds](https://cljdoc.org/builds) giving us some insight into failure rates of documentation builds. [Help lower it.](https://github.com/cljdoc/cljdoc/blob/master/…)
- Randy improved our [404 page](https://cljdoc.org/clojurists-together-rules) by adding the familiar search that is available on the front page.
- We set up JS packaging making docs load even faster. cljdoc's Lighthouse performance score now is 99.
- @rakyi helped set up Prettier so our JavaScript code is consistently formatted. This is something that we might also do for Clojure code in the future.
- @jsimpson-ovo built out proper support for GitLab. This mostly worked before but now it's is on par with GitHub. Source URLs, article edit links and more just work now.
- Lots of improvements aimed at new contributors. Better support for Cursive, an improved [`CONTRIBUTING`](https://github.com/cljdoc/cljdoc/blob/master/CONTRIBUTING.adoc) and better instructions for [running cljdoc locally](https://github.com/cljdoc/cljdoc/blob/master/doc/running-cljdoc-locally.md).

### What next?

With examples on hold and spec integration [still being semi-blocked](https://github.com/cljdoc/cljdoc/issues/67) there is some time to explore other areas. Some things I'm looking forward to in particular:

- A ubiquitous search interface to find functions, articles and switch between recently viewed projects. ([#194](https://github.com/cljdoc/cljdoc/issues/194))
- Integration of download statistics from Clojars ([#68](https://github.com/cljdoc/cljdoc/issues/68))
- Showing a project's dependencies and license (also [#68](https://github.com/cljdoc/cljdoc/issues/68))
- Various search engine optimisations ([#192](https://github.com/cljdoc/cljdoc/issues/192), [#164](https://github.com/cljdoc/cljdoc/issues/164) & [#160](https://github.com/cljdoc/cljdoc/issues/160))

### Tell people about cljdoc

Quoting somebody who came by the [#cljdoc Slack channel](https://clojurians.slack.com/messages/C8V0BQ0M6/) recently (emphasis mine :P):

> Hey. Just wanted to say thanks to the authors. Was looking for a way to document my cljs library, tested a few other tools, none of them would document the (hundreds of) dynamically generated functions. Even thought about writing my own. Forgot about it until I stumbled on cljdoc and the docs are already built! And it works perfectly! **This project needs more promotion.**

So point people to cljdoc and — if you're feeling particularly excited — tweet or write a blogpost about it.

### Thanks

Thanks for your support! I'm excited to follow Nikita and Arne's work over the next months and feel truly grateful that an initiative like Clojurists Together exists in our community.

## Shadow CLJs Update

![launcher-screenshot1](/images/launcher-screenshot1.png)
![launcher-screenshot2](/images/launcher-screenshot2.png)
![launcher-screenshot3](/images/launcher-screenshot3.png)

Released shadow-cljs versions to 2.6.14 to 2.6.21

### UI Work
- Launcher work will be released soon-ish. Didn't have time to work on in the last two weeks.

### Improvements
- Switched HTTP handling to use mostly Undertow built-in functionality for serving files to support Range requests with `206 Partial Content` responses and `Transfer-Encoding: chunked`.
- Massivly increased performance of Source Maps handling. The raw data was way too costly to serialize to disk and would often take longer than actual compilation. Instead now only the compacted VLQ encoded data is stored. Cache reads/writes are significantly faster, eg. `cljs.core` used to take 2.5s to write the cache and now `~250ms`.
- Increased parallel compilation speeds by splitting analysis and "compilation" (ie. converting AST to JS code) into two different phases. Parallel compilation used to wait for both but now only waits for analysis since thats all thats required.

### Bugfixes
- Stricter checks for `:modules` when `:entries` get moved out of their specified module with incorrect `:require` dependency graphs.
- Fixed all test targets to ensure that `:runner-ns` is always compiled last to ensure side-effecting macros return the expected results.
- Fixed a bug in classpath Closure JS processing (ie. none `node_modules/**.js` files) where cache would increase exponentially in size on each compile.
