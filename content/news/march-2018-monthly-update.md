---
title: March 2018 Monthly Update
date: 2018-04-03T20:36:31+12:00
draft: false
type: post
---

Hi folks!

Welcome to the second monthly update for Clojurists Together.

## Clojurists Together news

This month we had around 45 members sign up (we still need to process any signups at the end of this month), and one company member join. We've had a really strong response from individual contributors, but not so much from companies. If you work for a company that uses Clojure, please consider talking to your manager about supporting Clojurists Together. We recently conducted a survey of our members and will be publishing the results of the survey, and a request for proposals, for our second round of funding. More on that soon.

We continue to work on automating the sign-ups on the website, to make a faster and smoother flow for people who'd like to join.

## clj-http updates

From Lee Hinman:

### - March 1 - March 15

The last couple of weeks have seen the following changes in clj-http:

* I released clj-http 3.8.0. This includes many fixes and new features that were added in the first part of the Clojurists Together work, check out the [changelog](https://github.com/dakrone/clj-http/blob/master/changelog.org#380) for the full list of changes that went into 3.8.0, one of the bigger releases!
* A user requested a way to get the full HTTP request/response string for requests as sent on the socket level, I implemented the sending side of this by adding an option to override the socket factory, returning a socket `OutputStream` that captures the raw data so it can be inspected later. This is behind the new `:capture-socket` option. In future work I plan to add a way to capture data read in at the socket level (think curl's `-v` option).
* It's not required for clj-http to build a new `HttpClient` object for every request, and up until a recent change there was no way to tell clj-http that you'd like to reuse a particular client. With a commit this week you can now retrieve the HttpClient used and send your own with the `:http-client` parameter. This allows users to do more in-depth customization of the client without relying on the option being added in clj-http first. Part of this also includes polishing the client creation functions (for synchronous and asynchronous clients) that clj-http uses so they can be used by anyone using the library.
* When performing an asynchronous http request, a cancellable BasicFuture is returned. However, this future did not cancel the HttpRequest object, it now aborts the HttpRequest and I've added documentation around how to perform request cancellation (if for instance a certain timeout has been reached).
* [Aleph](https://github.com/ztellman/aleph) uses some of clj-http middleware and ran into a problem this week with transit+json responses that were coming back empty. I spent a while triaging this and determining where aleph and clj-http diverge, discovering that this is not an issue with clj-http, so that's nice to hear.
* Finally, I'm working on setting up an environment to test NTLM credentials. A user reported that the NTLM currently implemented in clj-http wasn't working, so I need to have a way to reproduce this issue.

That's it for this block!


### - March 15 - March 30

Here's what I've been up to for the last couple of weeks:

* While Clojure has its own logging framework, the Apache HTTP client prefers to use log4j for logging. Since this can be an invaluable debugging tool I wrote documentation and example code for how to configure clj-http (or rather, a project using clj-http), see [this commit](https://github.com/dakrone/clj-http/commit/1e530f7679c1497da6b845d4a52ff06c102e9ba0) for the work.
* Speaking of logging, I am hoping it can help track down an issue I spent time trying to reproduce where the synchronous connection pool is [blocking forever](https://github.com/dakrone/clj-http/issues/407) for a user.
  Additionally, one I enabled the logging I notice that Apache is [logging an "Illegal state ACTIVE"](https://github.com/dakrone/clj-http/issues/443) error when the integration tests are run. I spent quite a while tracking this one down and it ended with [rewriting the way that asynchronous connection pools are handled](https://github.com/dakrone/clj-http/commit/10018154ae6db5db08751ce3f7bad96a20c35aa4), no more error now!
* Sometimes HTTP servers want to read a request's headers, then do something without dealing with the body of the request, for example, reading a header with some authorization token before consuming a large binary upload body. A user was running into an issue where instead of returning a 413 like the server told it to, it [throws an exception about the socket having its pipe broken](https://github.com/dakrone/clj-http/issues/277) due to the connection being prematurely closed. Turns out there's an [RFC about this](https://tools.ietf.org/html/rfc2616#section-8.2.2), but it's unevenly implemented by browsers. To make things even more complicated, apache's http client incorrectly (throws the exception) handles it when used synchronously, but correctly returns the 413 when using asynchronously. I believe this is due to a difference in Apache's HTTP implementation. I'm planning on revisiting this with the 5.0 client to see if the bug persists.
* Speaking of the 5.0 client, I have [started a branch called apache5-upgrade](https://github.com/dakrone/clj-http/tree/apache5-upgrade) for moving clj-http to be compatible with it. With compilation as the first target I have been working to get everything switched over to the new classes. Not everything is 1-to-1 and there are many breaking changes, but I have moved headers.clj, cookies.clj, multipart.clj, and a stripped-down version of conn_mgr.clj over to the new classes. Next up is core.clj and client.clj, the two biggest ones.

Here's to more improvements in the future!


## Figwheel updates

From Bruce Hauman:

### -March 1 - March 15

**Figwheel Core**

Today I published a [`figwheel-core`](https://github.com/bhauman/lein-figwheel/tree/master/figwheel-core) project to the master branch of
the `lein-figwheel` repository.

Figwheel-Core provides all the main code for determining what to
reload and how to reload/notify the client. It is completely
independent of the REPL and server implementations.
It only has one real dependency and that is ClojureScript itself. It uses the established `IJavaScriptEnv` interface for
communication with the client. This means it doesn't matter what your client is.

`figwheel-core` really feels like a Figwheel thesis project for me, a
Rich Hickeyan distilling of Figwheel down to its essentials. It has
been a very rewarding experience.

This `figwheel-core` work has been my focus for the first half of
March. After this I'm going to tackle a `figwheel-repl` which is
planned to be completely free of reloading code and will focus
on a strong stable connection and sane multiplexing.

Much thanks to Clojurists Together and its fantastic supporters!

### March 15 - March 30

#### Figwheel REPL

Much like 2 weeks ago, I have published a [`Figwheel-repl`](https://github.com/bhauman/lein-figwheel/tree/master/figwheel-repl) project to
the master branch of the `lein-figwheel` repository.

Figwheel-REPL is **only** a ClojureScript `repl-env` and doesn't do anything specific to help with automatic file reloading. As such, it is more similar to Weasel in function than to Figwheel.

It is intended to be a single `repl-env` that will work on as many
platforms as possible: including Browser, Node, Worker, ReactNative,
etc. It is also intended to handle multiple clients, think browser tabs, much more gracefully than the current Figwheel REPL.

It is also different in that it only evaluates code on a single client
by default. You will still be able to choose to broadcast an eval
operation to all connected clients if you prefer. You can also provide
a filter function when you create the Figwheel repl-env, to filter the
connections to the set of connected clients you want an eval operation
to be sent to.

#### Multiple REPL behavior

The new `figwheel.repl` namespace currently offers some ClojureScript
functions to help you list and choose which connected client to focus on.

The `figwheel.repl/conns` macro allows you to list the connected clients:

For example:

```
cljs.user> (figwheel.repl/conns)
Will Eval On:  Darin
Session Name     Age URL
Darin            25m /figwheel-connect
Judson          152m /figwheel-connect
nil
```

The above `figwheel.repl/conns` call lists the clients available for the REPL to target.

All connections are given easy to remember session names. The
intention is that this will help you easily identify which browser tab
your, through the REPL client feedback in the browsers dev-tool
console.

The `Will Eval On: Darin` indicates that the `Darin` client is where
the next eval operation will be sent to because this is currently the
**youngest** connected client.

This **youngest client** heuristic for choosing which client to
evaluate on, allows for a simple understanding of which REPL is the
current target of eval operations. Open a new browser tab, or start an
new node instance and that becomes the new eval target.

If you want to focus on a specific client:

```
cljs.user> (figwheel.repl/focus Judson)
Focused On: Judson
```

From now on all evals will go to `Judson` unless the connection to
`Judson` is lost in which case the behavior will revert to selecting
the youngest connection.

You can confirm that the repl is currently focused with:

```
cljs.user> (figwheel.repl/conns)
Focused On: Judson
Session Name     Age URL
Darin            28m /figwheel-connect
Judson          155m /figwheel-connect
nil
```

I think this goes a long way toward solving a problem that has existed
since the very beginning of Figwheel.

#### Attention toward embedding the figwheel-repl endpoint

The other problem that I'm currently trying to work out is how to best
support embedding the Figwheel REPL endpoint in your server.

For larger projects it simplest to use figwheel connection as a
side-channel, a separate REPL connection, that is distinct from your
projects HTTP server. Figwheel's use of Web-sockets and CORS make this
side connection a simple matter. But inevitably there are situations
where you want to embed the Figwheel endpoint in your server. So I'm
giving this some serious attention.

In addition to the Web-socket connection, I have implemented a simple
HTTP polling connection which should allow anyone to embed
figwheel-repl ring middleware into their stack. (Side note: I'm also
looking at long polling).

It is too bad that as a community we haven't landed on an agreed upon
Ring web-socket interface, as this makes it much harder to allow simple
embedding of a web-socket endpoint into the server of your choice. But
I'm going to do my best to facilitate this by making it easier to
create a web-socket endpoint from the provided api.

On a side note: I'm also considering making the default server a the
`ring.jetty.adapter` as it is such a common dependency.

#### Work for the last month of Clojurists Together

My work over the next month is to complete both `figwheel-repl` and `figwheel-core`.

I'm hoping that I can also finish a `figwheel-main` (a proxy for
cljs.main) to tie it all together. My intention for `figwheel-main` is
that it will the most important functionality of figwheel-sidecar but
will follow the `cljs.main` arg conventions. It will provide solid
file watching capabilities along with a solid figwheel-repl connection
and make it trivial to turn on auto file reloading.

I'm excited by what amounts to a completely new Figwheel, which
will be a simpler Figwheel minus a tremendous amount of complexity.

## Minutes/Committee Communications

In our recent survey and in previous member communications, a few people have mentioned that they'd like to have more visibility into how the projects are chosen. We had chosen not to show the voting results for the projects that didn't get in, as we didn't want unsuccessful applicants to feel bad if the results were released showing that their project wasn't selected. We have a few changes planned to increase transparency without discouraging applicants:

* Show the voting results and voting rounds for all applications, but redact the names of all projects but the top 5
* List all projects that applied.

These two measures should help backers understand how we voted. If you have any thoughts on this, either as a backer or an open source contributor, please get in touch.
