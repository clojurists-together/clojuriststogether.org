title: April 2018 Monthly Update
date: 2018-04-03T20:36:31+12:00
draft: false
type: post

## Clojurists Together news

This is the third and final update from clj-http and Figwheel. Both projects finished their work at the end of April. Both projects have done great work, and we are really impressed with what they've been able to achieve. We recently [announced](/news/q2-2018-funding-announcement/) that we are funding CIDER and ClojureScript for our Q2 funding round. Those two projects have just started, we'll have more details on what they're working on next month.

We continue to work on automating the sign-ups on the website, to make a faster and smoother flow for people who'd like to join. If you'd like to help out on that, or other issues, our site is now up on [GitHub](https://github.com/clojurists-together/clojuriststogether.org).

## clj-http updates

### April 1 - 15

Hello again Clojurists!  

These past two weeks have been focused on two particular tasks for clj-http:  

A user opened up [an issue](https://github.com/dakrone/clj-http/issues/444) about clj-http's cookie policy configuration, talking about the documentation being incorrect with regard to plugging your own cookie policy in. I looked into this and discovered he was correct, the wrong part of cookie policies selection was configurable. I spent a while reading up on how Apache implements their cookie spec and cookie spec providers, and [added a couple of new ways to configure the cookie parsing](https://github.com/dakrone/clj-http/commit/2608c3014cf49a800999c43771a8fccd1bfce363) in clj-http. These new options are the `:cookie-spec` and `:cookie-policy-registry` settings in the body. These settings will let someone either provide a spec provider (there are several that the Apache HTTP client makes available) or to complete write their own cookie spec (I've also added documentation for how to do this). From there, a user has complete control over how cookies are parsed from headers, how the cookie values are validated, and how headers get transformed back into cookies.  

The other thing I've been focusing on has been the Apache 5.0 upgrade. I'm happy to report that the [apache5-upgrade branch](https://github.com/dakrone/clj-http/compare/apache5-upgrade) now compiles, and that you can use it in a REPL to perform requests! There are still some features that are commented out, as I have not yet discovered the equivalent 5.0 versions (there are **many** changes in the 5.0 upgrade and all the documentation has not been released). I've been going through clj-http's tests to get them passing, but so far this is a much smoother transition than the 3.x -&gt; 4.0 upgrade in the past. While that upgrade took many, many months to transition to the new and un-deprecated APIs, I'm hoping to be much more on top of this major release. Having this branch ready means that it will be a much shorter transition once the new Apache client version is out of beta and 5.0 is released.  

That's it for these past two weeks!  

### April 15 - 30

Alright on to what I've been up to in the last couple of weeks:  

- A user opened [an issue asking for a cache config option](https://github.com/dakrone/clj-http/issues/445) for the internal `HttpClient` in clj-http, so I looked into this and ended up [adding support for transparent caching of requests from clj-http](https://github.com/dakrone/clj-http/commit/2843e45023850f63906aa976cbcffbb94204d8f0). You can now specify `{:cache true}` in a request for it to use a cache. This cache is the Java equivalent of a browser cache and does all the required validation/expunging/etc as needed. In order to use caching you'll need to use a reusable connection manager and http-client (with the `:connection-manager` and `:http-client` parameters), and I've added documentation about doing this in the readme. In a response the `:cached` key now is one of `nil`, `:CACHE_HIT`, `:CACHE_MISS`, `:CACHE_MODULE_RESPONSE`, or `:VALIDATED` to indicate whether the response was served from the cache or not. 
- I continued to work on getting tests to pass with the new Apache 5.0 Http client. I pushed commits fixing a lot invocation exceptions that were caused due to method renames from Apache changes. As part of driving this upgrade forward I've made the `3.x` branch of clj-http the primary Github branch (so that is what shows up when visiting [https://github.com/dakrone/clj-http/](https://github.com/dakrone/clj-http/)) and merged the `apache5-upgrade` branch to master. Things may be in a state of flux a bit more while I continue to work towards the new client, but by having it on master I hope to keep on top of the upgrade overall. 
- I pushed [a commit](https://github.com/dakrone/clj-http/commit/d2914602c77c48db02dbf56e82972ec60cc1c32c) attempting to fix NTLM authentication, it turns out there are multiple places credential providers can be specified on the client, and setting it on the http client itself may help solve a situation where authentication wasn't working for a user.  
- In order to make sure the momentum from Clojurists Together is not lost, I've added a [Gitter room](https://gitter.im/clj-http/Lobby) for clj-http linked in the Readme for anyone that would like to ask questions in a more "chat-like" manner. I'm hoping to receive more feature/enhancement requests from users that use clj-http to help keep development humming along, so please drop in if you have any comments or feedback you'd like to leave. (or email always works too)  
- I'd really like to add [code coverage](https://github.com/dakrone/clj-http/issues/446) to clj-http so that I can make sure that the APIs have as much test coverage as possible, so I spent a while investigating the different tools this week. Unfortunately, there don't seem to be a lot of code coverage tools for Clojure currently, and the only one I was able to find I was not able to get working (if you know of one I should try, please do open an issue or hop into the Gitter room). This did, however, lead me to [add the lein-nvd plugin](https://github.com/dakrone/clj-http/commit/edf6fe9d48eb1987709190d26432c299f679573a) for checking clj-http's dependencies against the National Vulnerability Database. It's important that clj-http does not rely on dependencies that have existing CVEs since I don't want to put anyone's systems at risk when using clj-http.  
- Finally, with this being the last report for cljtogether, I've released clj-http [3.9.0](https://github.com/dakrone/clj-http/tree/3.9.0) with all the most recent changes. This version includes the work for reusable http clients, asynchronous request future cancelling, supporting the `:cookie-spec` and `:cookie-policy-registry options`, the `:cache` options I mentioned above, as well as a fix for asynchronous connection pools putting the connection manager in an illegal ACTIVE state. Please try it out!  

Thank you Clojurists Together members again for your support of the project and the honor of being picked and being able to work on clj-http!

## Figwheel updates

### April 1 - 15

#### Complete Figwheel REPL and start Figwheel Main

During the last 20 days, I have put most of my energy into completing
Figwheel REPL. 

The most difficult part of working on it was the design and trade-offs
that surrounded the server that is used for the REPL connection. In the
end, I settled on using the Jetty Server because it is the most
commonly used Ring server in the ecosystem.

Getting a nice design together so that the server can be used for both
a REPL connection and an initial development server was challenging. I settled on using ring-defaults along with some middleware that makes
the server smarter about caching compiled ClojureScript assets.

I'm happy with the end product because it exposes all the
`ring.jetty.adapters/run-jetty` configuration options which in turn
should allow things like SSL support. It also exposes all of the `ring/ring-defaults` configuration options
as well, to allow configuration of common ring server middleware.

The final REPL also allows you to completely customize/replace the
what I am calling the `:ring-stack` (the ring-stack is what wraps the
`:ring-handler`) which is currently ring-defaults composed with some
extra middleware for Figwheel. Also, for the brave you can replace the
`:ring-server` itself, which would be a much more involved task.

Of course this setup will still also allow you to supply a
`:ring-handler` configuration option. You will still be able to use your own server for development and have
the Figwheel server operate as simple websocket server for REPL only
communication.

The Figwheel REPL also now serves a default `index.html` so that
establishing an initial REPL connection is a simple matter. Unfortunately the REPL is not complete yet, but the rest of its
development will be better informed by starting work on `figwheel-main`.

#### figwheel-main

Work on figwheel-main has started and I'm still getting my bearings on
getting the best behavior from it. Much like the work on the REPL the really hard part is getting a good
design.

The main idea driving figwheel.main is that it should provide all the
functionality of `cljs.main` in addition to figwheel functionality. I
want ClojureScript users to have less cognitive overhead when it comes
to tooling.

Figwheel main defaults to the `figwheel-repl` and adds a `--build` or
`-b` flag so that you can supply the name of a build. So the typical invocation would be:

```
clj -m figwheel.main -b dev -r
```

This command will start the familiar figwheel experience.

In this case `dev` refers to a file named `dev.cljs.edn`.

The contents of which are:

```
{:main example.core}
```

This is all the figwheel needs now. It will supply smart defaults for
`:output-to`, `:output-dir`, `:asset-path`. It will also supply a
smart default for what directory to watch.

So in other words you can have a development server, repl, figwheel
reloading, by just supplying a build edn file like above and a
`src/example/core.cljs`, and a `deps.edn`.

This much is working already, but there is still much more work to do. I'm really hoping to get initial releases of these libraries out by
the end of the month.

#### Thanks again!

A big thanks to everyone for supporting this work!!

### April 15-30 Figwheel Main - Feature Complete not Friendly Complete

#### Summary

Figwheel Main now provides all the features of Figwheel Sidecar and
more. However, it is not yet as friendly as I am wanting it to be.

I'm hoping to publish an alpha snapshot of Figwheel Main soon with a
more hardened one out by the end of May.

#### A Rewrite

Figwheel initially evolved in an environment that was very different
from today. The ClojureScript compiler has changed a great deal, along
with the tooling landscape, and my experience writing Clojure.

Early code design decisions were made, with the objective of exploring
a relatively unknown problem space. The priority being was to get
these tools out to programmers sooner than later, not to write an
exemplary codebase.

The figwheel-main rewrite sheds oodles of accidental complexity
introduced by an earlier me. This earlier Bruce was attempting to
provide a design structure to give himself the illusion of control
over the problem. In reality, structure was only there to comfort, and
help me convince myself that I was doing things the "right" way. In
the end, this structure just made it difficult to evolve the codebase.

Side-note: Rewriting figwheel was a fantastic experience that allowed
my growing Clojure experience to guide me to much better solutions.

A rewrite has been an opportunity for me to address so many holes and
long running issues that would have been very hard to fix in Figwheel
Sidecar.

The first of these issues was to minimize the configuration that's
needed to get an initial ClojureScript development session up an
running. `figwheel-main` delivers this:

In your `deps.edn`

```
;; deps.edn
{:deps {com.bhauman/figwheel-main {:mvn/version "0.1.0-SNAPSHOT"}
 :paths ["src" "target"]]}
```

And on the command line:
```
clojure -m figwheel.main
```

The above will launch a browser and connect a REPL to it, from here
you can do REPL driven development.

And if you want to in work on a particular build with figwheel style
reloading just add a build config file.

For example in `dev.cljs.edn`
```
{:main example.core}
```

And in `src/example/core.cljs`

```
(ns example.core)
(enable-console-print!)
(prn "hello world!")
```

and run the command:
```
clojure -m figwheel.main -b dev
```

You'll notice there is no need for an `index.html`, `:output-to`,
`:output-dir`, `:asset-path`, `:source-paths`, and oodles of other
configuration that is needed to get up an running with ClojureScript.

So little in fact, I'm considering adding a `--gen` command line
option to add namespaces, index.html, and other initial config. I'm
thinking these will be backed up by github gists.

This is a much needed clean up of the initial configuration to get
started with a hot loading ClojureScript environment. It is easier to
understandable and friendlier. I can't imagine what newcomers think
when they see the `project.clj` generated by `lein new figwheel` but
it certainly not a feeling of comfort.

Another thing I wanted to fix was the overall complexity of the
configuration options. The new configuration is also much simpler and
flatter.

There is a main configuration file: `figwheel-main.edn`

For example:
```
{:watch-dirs ["src" "dev"]
 :ring-server-options {:port 9500}}
```

And if you want to modify that configuration for an individual build
add meta-data to the build config file:

For example: in your `dev.cljs.edn`
```
^{:watch-dirs ["src"]
  :ring-server-options {:port 9501}}
{:main example.core}
```

This makes configuration much more understandable as there are no
longer two levels of config with different options available. Only one
level that can be overridden by a the meta-data on a build.

The use of meta-data here also enables you to effortlessly re-use the
configuration with `cljs.main` commands.

Another, major issue was the promiscuous REPL connections or "where
the heck is this being evaluated?"

As mentioned before the new REPL allow names the individual
connections and allows you to choose the connection so send
evaluations to. When you evaluate code in the REPL it only goes to one
connection unless you specify otherwise in your config.

Add to the above changes a long list of other improvements that I was
able to make with the introduction of `rebel-readline-cljs`,
`figwheel-repl` and `figwheel-core`.

The biggest feature is that the figwheel-main codebase is much much
smaller and much easier to grok. The new figwheel is now in a much
better position to move into the future and integrate with other
tooling.

I already have a long list of improvements that I want to make now
that the codebase much more approachable.

#### Conclusion
 
I consider the Clojurists Together funding of Figwheel to be a
success! The funding gave me both an excuse and an opportunity to
really focus on Figwheel, and do the things that I knew needed to
happen for it to continue to provide value to the ClojureScript
community.

Much thanks goes to Daniel Compton and everyone who participated in
Clojurists Together.

