---
title: February 2018 Monthly Update
date: 2018-03-02T08:42:41+13:00
type: post
---

Hi folks!

Welcome to the first monthly update for Clojurists Together. Now that things are a bit more settled, we are going to be issuing more regular updates keeping you up to date with what is going on with Clojurists Together.

## Clojurists Together news

In February we had three new company members, and 25 new developer members sign up. In total, we were supported by 14 companies, and 75 developers. We are really thrilled with how developers and companies are responding to Clojurists Together and the two projects that were [selected](/news/q1-2018-funding-announcement/) for the first funding round: clj-http and Figwheel. We have updates below from Lee Hinman (clj-http) and Bruce Hauman (figwheel), talking about what they've been working on this month.

## clj-http updates

**-- February 1 - Feb 15**

Hi Clojurists, here's my report for the past two weeks.

First, many PRs from the community were merged:

* [#426](https://github.com/dakrone/clj-http/pull/426) to fix the socks proxied connection managers (an argument was not being passed correctly)
* [#424](https://github.com/dakrone/clj-http/pull/424) added a :mime-subtype parameter so the particular type for multipart requests can be specified
* [#422](https://github.com/dakrone/clj-http/pull/422) added :http-multipart-mode which allows selecting which type of multipart mode is used (defaulting to strict)
* [#428](https://github.com/dakrone/clj-http/pull/428) fixed a minor issue in the readme related to "unexceptional statuses"
* [#432](https://github.com/dakrone/clj-http/pull/432) added a test for an issue fixed by updating the apache dependencies where multipart pieces didn't work when using the async client

Next, I spent quite a while on a tricky bug when nested query params are specified in addition to form params leading to the encoding of the body being incorrect. This led to [adding some parameters](https://github.com/dakrone/clj-http/commit/47a7762ed42e1d772e51a6f5bdaa61b436b54cb3) to allow fine-grained control over whether or not to flatten `:query-params` and `:form-params`.

I also added a way that users can modify the http client in arbitrary ways. This [added](https://github.com/dakrone/clj-http/commit/2ab22cb75841c712997dc57bfc3e393cdbcd41d1) the `:http-builder-fns` and `:async-http-builder-fns` so users aren't blocked if they need to make low-level changes to the http client builders directly.

I went through all boolean flags clj-http currently supports and changed them to use the utility method so users can use things like `:insecure true` or `:insecure?` true without having to remember the `?` suffix.

Updated the readme as it was a little out of date, and added a CONTRIBUTING.md document

**-- February 15 - Feb 28**

Hi Clojurists (together!),
Here's the report for what I've been up to for the last two weeks:

* I worked on an issue where redirects to an invalid location (`https:///` in the example a user was running into) caused an asynchronous request to hang forever, rather than either returning an error or failing. [#435](https://github.com/dakrone/clj-http/pull/435) added the :validate-redirects option to disable this extra validation
* I wrote a [guide](https://github.com/dakrone/clj-http/blob/master/SSL.org) that a user [requested](https://github.com/dakrone/clj-http/issues/376) on how to use clj-http with a self-signed certificate so that they could test locally without turning on the :insecure option.
* I started experimenting with the Apache 5.0 client. This new major version supports HTTP 2, however, it's a major change and breaks a lot of clj-http's code so the work is still ongoing.
* I made the cookie policy selection pluggable by the user, a user can now extend the get-cookie-policy method to allow selecting their own cookie policy for validation.
* [#257](https://github.com/dakrone/clj-http/issues/257) I fixed an issue where if a server returned an empty response with headers indicated the body was gzipped, and the client requested that the body be coerced, an EOFException would be thrown trying to decode the empty response. This is now fixed by doing a pre-read phase where we tentatively attempt to read the stream, gracefully handling the situation where we it cannot be read.
* Finally, I triaged all of the existing clj-http issues, there were around ~35 open issues, there are now 16 open issues and they are labeled accordingly. Hopefully this helps any new contributors since stale or over-old issues are no longer an issue.

## Figwheel updates

**-- February 1 - 15**

When I first created the Figwheel REPL I really wanted to include a readline library so that when folks ran `lein figwheel` they would get a REPL with readline editing enabled and wouldn't have to futz with an external readline program like `rlwrap`. I attempted to create one with JLine but it was really impractical and there was very little in the way of JLine documentation.

Time has passed and things have changed, over the last month and a
half I have written [Rebel Readline](https://github.com/bhauman/rebel-readline) to provide a readline functionality for Clojure and ClojureScript.

The benefit of only needing to run `lein figwheel` to get into a nice comfy REPL with full featured terminal editing can't be overstated. It will provide newcomers and old hands alike a more worthy environment to work in and reduce the frustration of trying to explore ClojureScript in such an impoverished environment. I've also written a [longer explanation](https://github.com/bhauman/rebel-readline/blob/master/rebel-readline/doc/intro.md) of about why I wrote Rebel Readline.

I have been working full time for the last two weeks on trying to get Rebel Readline to a point where I can integrate it into Figwheel. A few days ago I finally got this done in this figwheel [commit](https://github.com/bhauman/lein-figwheel/commit/55de952de19eb69e1f121f21f69d8b8a6fc0eda2).

I have spent more time trying to fix the CLJS language interaction in Rebel Readline. Inline eval was broken and I actually just fixed that this morning.

There are some completion issues still and a Figwheel specific output issue that can create a weird experience when you print things from the repl. I'm going to work on these next.

Even with these flaws I think it is a significant and welcome change to Figwheel. A change that in total took about a month and a half of full time work, so far :)

**Other things:**

I spent some time thinking about the architecture and future design of Figwheel.

I wrote a brainstorming document outlining some design ideas. The main take away for me is that the major parts of Figwheel on the client side and the server/compile side should be able to communicate solely through a standard CLJS ReplEnv. In other words, you should be able to require figwheel on the client and then call the figwheel.client.cljs functions trigger the various client behavior. This constraint should provide compiler-side and client-side libraries that are decoupled from the rest of the Figwheel code base. Once these libs take shape, I can focus on creating a better CLJS ReplEnv, possibly one that doesn't have to communicate over a web-socket.

Also the advent of pREPL will provide a good example of a better
foundation for communication design in Figwheel's strange multiplexed REPL environment. This could potentially provide a good solution to the multiple client behavior of the Figwheel REPL. The goal being, that you can switch between clients and know which browser client sent the result you are looking at.

I also spent some time thinking about the interaction between figwheel and Clojure CLI tools. It is pretty clear that there should be a `figwheel-sidecar.main` that mirrors the behavior of `lein figwheel`. The idea is that you will be able to start figwheel with `clj -m figwheel-sidecar.main dev`

Yesterday I explored the relationship between figwheel and the new `cljs.main` that is under development. I was able to
proof-of-concept a `cljs.repl.figwheel` that will give you a full
blown Figwheel with the new rebel-readline experience all from a
standard `cljs.main`. So it will be possible to type `clj -m cljs.main -re figwheel` and this will read from the `figwheel.edn` and create a complete figwheel environment. I've recorded an [asciinema screencast](https://asciinema.org/a/163042) demonstrating this.

Now how much of this I will get done before the end of April is
anybody's guess. But I'm excited to be working on it...

Big thanks to Clojurists Together!

**-- February 15 - February 28**

Today I finally released [Figwheel 0.5.15](https://github.com/bhauman/lein-figwheel/releases/tag/v0.5.15) and the initial release of [Rebel Readline 0.1.1](https://github.com/bhauman/rebel-readline/releases/tag/v0.1.1).

The work for the last 15 days has included working out the final
hiccups with integrating Rebel Readline into Figwheel. This involved looking at how the Figwheel REPL handled printing. A significant improvement to Figwheel came out of this. Now when you type `(prn 1)` at the REPL prompt you will only get a single `1` printed at the output.

I also spent a significant amount of time making sure that the
readline worked on Windows. I had to use my wife's older windows laptop for this. It was the familiar painful process of having to ferry code from my development machine to the Windows machine to see if code fixes were working. In this process I also fixed color output on Windows. Figwheel detects what kind of terminal you are in and provides ANSI color codes only if the terminal can handle it.The upshot of this is that Figwheel is working better than it ever has on Windows.

There was also a significant investment into refactoring rebel
readline into a much better architecture. I have noticed when I
greenfield new projects, the initially chosen namespaces turn out to not really represent the actual dependency structure of the final system. So I spent some time refactoring rebel into a much more sensible layout.

I also made changes so that one can easily nest a ClojureScript REPL inside of a Clojure REPL and still have all the Rebel Readline features work for the ClojureScript REPL.  This work should allow arbitrary nesting of readline capable programs.

I also spent some time working with the ClojureScript core team
because they are working on cljs.main. It seemed important to get
involved here because cljs.main is positioned to be the core command line experience for ClojureScript. My contributions here were to ask that the watcher can launch on a background thread, so that the REPL can run at the same time. I also promoted the use of a default HTML page so that the browser REPL can simply launch. Both of these features provide a very Figwheel like experience to the core ClojureScript experience. I'm going to continue to be part of the cljs.main conversation as it progresses over the next few months.

As an investigation into the new Figwheel architecture I created a
proof of concept minimal Figwheel [gist](https://gist.github.com/bhauman/d731eb4cb54fa187c341aec75f62dd83) that demonstrates a very simple but capable Figwheel implementation that only uses the CLJS REPL for communication.

The success of this experiment lead to an important [commit](https://github.com/clojure/clojurescript/commit/0f2a407ef6169da2836d560f5ad72527635f9606) to
ClojureScript ([CLJS-2581](https://dev.clojure.org/jira/browse/CLJS-2581)) that will allow simple tooling communication with the client JavaScript environment using eval. There are a lot of
possibilities here.

**--  Next --**

Going forward I am looking at working on a `figwheel-sidecar.main` so that Figwheel can easily be used with the Clojure CLI tools.

I'm also going to be spending some time refactoring the Figwheel client so that it will work with any CLJS REPL.

I will then focus on refactoring how to get the compile side information to trigger reloads so that it isn't dependent on the
current figwheel architecture.

So there will be two sides, the client and compile sides, and they
will just rely on the information that would only be available to
anyone running a compiler or a client would have available.

This will free the core of Figwheel from the mass of complected code that is Figwheel now. This should make incorporating figwheel behavior into Boot or cljs.main a trivial matter.

## Minutes/Committee Communications

This month we made the definitive decision that projects will be paid in a grant fashion and not an hourly rate. We had had some concerns from people about the high hourly rate that we were paying projects, and moving to a grant also better reflects the intention of what we are trying to do with Clojurists Together's funding.

We also had a meeting with SFC to discuss preparing grant reports for each project we have funded, automating more of the day-to-day management of the project, and setting a budget to forecast for the year ahead.

## Payments

* We have allocated $1,800 this month to pay both projects ($3,600 total). **N.B.** The amount we pay projects is set based on our income from the previous three months. At the time of starting a funding cycle, we have the cash on hand to honour our commitments for the full cycle, even if everyone cancelled their membership at once 😱.
