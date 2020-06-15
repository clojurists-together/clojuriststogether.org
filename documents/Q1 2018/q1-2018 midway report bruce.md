## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve?

The Figwheel codebase is  an exploration of the problem space of creating a friendly ClojureScript. As a UI to ClojureScript the codebase is complex and complected.

I would love to devote a solid 3 months to working solely on Figwheel. I would like revisit its functionality and condense it down into an architecture and a set of libraries that will allow it to be reused in other contexts (i.e boot etc.) more easily. For example: other projects should be able to use a figwheel's compiler wrapper that produces attractive error messages, or perhaps just figwheel's error message parsing code.

I would also us the time to explore feedback for spec errors at runtime.

Most importantly, I'd like to set Figwheel up for a long future of service to the Clojure community.

### Why is this project important to the Clojure community?

Figwheel is quite probably the ClojureScript tool with the highest adoption in the community.

## Work log

### Revisit it’s functionality (new features)

Over the last month and a
half I have written [Rebel Readline](https://github.com/bhauman/rebel-readline) to provide a readline functionality for Clojure and ClojureScript. I have been working full time for the last two weeks on trying to get Rebel Readline to a point where I can integrate it into Figwheel. A few days ago I finally got this done in this figwheel [commit](https://github.com/bhauman/lein-figwheel/commit/55de952de19eb69e1f121f21f69d8b8a6fc0eda2). I have spent more time trying to fix the CLJS language interaction in Rebel Readline. Inline eval was broken and I actually just fixed that this morning.

I also made changes so that one can easily nest a ClojureScript REPL inside of a Clojure REPL and still have all the Rebel Readline features work for the ClojureScript REPL.  This work should allow arbitrary nesting of readline capable programs.

I also spent some time working with the ClojureScript core team
because they are working on cljs.main. It seemed important to get
involved here because cljs.main is positioned to be the core command line experience for ClojureScript. My contributions here were to ask that the watcher can launch on a background thread, so that the REPL can run at the same time. I also promoted the use of a default HTML page so that the browser REPL can simply launch. Both of these features provide a very Figwheel like experience to the core ClojureScript experience.

#### Multiple REPL behavior

The new `figwheel.repl` namespace currently offers some ClojureScript
functions to help you list and choose which connected client to focus on.

I think this goes a long way toward solving a problem that has existed
since the very beginning of Figwheel.


### Condense it into a reusable project/future maintainability

I spent some time thinking about the architecture and future design of Figwheel.

I wrote a brainstorming document outlining some design ideas. The main take away for me is that the major parts of Figwheel on the client side and the server/compile side should be able to communicate solely through a standard CLJS ReplEnv. In other words, you should be able to require figwheel on the client and then call the figwheel.client.cljs functions trigger the various client behavior. This constraint should provide compiler-side and client-side libraries that are decoupled from the rest of the Figwheel code base. Once these libs take shape, I can focus on creating a better CLJS ReplEnv, possibly one that doesn't have to communicate over a web-socket.

Also the advent of pREPL will provide a good example of a better
foundation for communication design in Figwheel's strange multiplexed REPL environment. This could potentially provide a good solution to the multiple client behavior of the Figwheel REPL. The goal being, that you can switch between clients and know which browser client sent the result you are looking at.

I also spent some time thinking about the interaction between figwheel and Clojure CLI tools. It is pretty clear that there should be a `figwheel-sidecar.main` that mirrors the behavior of `lein figwheel`. The idea is that you will be able to start figwheel with `clj -m figwheel-sidecar.main dev`

Yesterday I explored the relationship between figwheel and the new `cljs.main` that is under development. I was able to
proof-of-concept a `cljs.repl.figwheel` that will give you a full
blown Figwheel with the new rebel-readline experience all from a
standard `cljs.main`. So it will be possible to type `clj -m cljs.main -re figwheel` and this will read from the `figwheel.edn` and create a complete figwheel environment. I've recorded an [asciinema screencast](https://asciinema.org/a/163042) demonstrating this.

There was also a significant investment into refactoring rebel
readline into a much better architecture. I have noticed when I
greenfield new projects, the initially chosen namespaces turn out to not really represent the actual dependency structure of the final system. So I spent some time refactoring rebel into a much more sensible layout.

As an investigation into the new Figwheel architecture I created a
proof of concept minimal Figwheel [gist](https://gist.github.com/bhauman/d731eb4cb54fa187c341aec75f62dd83) that demonstrates a very simple but capable Figwheel implementation that only uses the CLJS REPL for communication.

The success of this experiment lead to an important [commit](https://github.com/clojure/clojurescript/commit/0f2a407ef6169da2836d560f5ad72527635f9606) to
ClojureScript ([CLJS-2581](https://dev.clojure.org/jira/browse/CLJS-2581)) that will allow simple tooling communication with the client JavaScript environment using eval. There are a lot of
possibilities here.

#### Figwheel Core

I published a [`figwheel-core`](https://github.com/bhauman/lein-figwheel/tree/master/figwheel-core) project to the master branch of
the `lein-figwheel` repository.

Figwheel-Core provides all the main code for determining what to
reload and how to reload/notify the client. It is completely
independent of the REPL and server implementations.
It only has one real dependency and that is ClojureScript itself. It uses the established `IJavaScriptEnv` interface for
communication with the client. This means it doesn't matter what your client is.

#### Figwheel REPL

I have published a [`Figwheel-repl`](https://github.com/bhauman/lein-figwheel/tree/master/figwheel-repl) project to
the master branch of the `lein-figwheel` repository. 

It is intended to be a single `repl-env` that will work on as many
platforms as possible: including Browser, Node, Worker, ReactNative,
etc. It is also intended to handle multiple clients, think browser tabs, much more gracefully than the current Figwheel REPL.

It is also different in that it only evaluates code on a single client
by default. You will still be able to choose to broadcast an eval
operation to all connected clients if you prefer. You can also provide
a filter function when you create the Figwheel repl-env, to filter the
connections to the set of connected clients you want an eval operation
to be sent to.


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

### Fixing bugs


The work for the last 15 days has included working out the final
hiccups with integrating Rebel Readline into Figwheel. This involved looking at how the Figwheel REPL handled printing. A significant improvement to Figwheel came out of this. Now when you type `(prn 1)` at the REPL prompt you will only get a single `1` printed at the output.

I also spent a significant amount of time making sure that the
readline worked on Windows. In this process I also fixed color output on Windows. Figwheel detects what kind of terminal you are in and provides ANSI color codes only if the terminal can handle it. The upshot of this is that Figwheel is working better than it ever has on Windows.

### Maintenance

Today I finally released [Figwheel 0.5.15](https://github.com/bhauman/lein-figwheel/releases/tag/v0.5.15) and the initial release of [Rebel Readline 0.1.1](https://github.com/bhauman/rebel-readline/releases/tag/v0.1.1).

### Spec errors





## Commits

git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="FEB 1 2018" --until "MAR 30 2018"

* edb12ce - repl can start server if not started yet (2018-03-29 17:34:03 -0400) <Bruce Hauman>
* f7a1318 - WIP (2018-03-29 08:45:39 -0400) <Bruce Hauman>
* 933e01c - correct spelling (2018-03-28 22:02:54 -0400) <Bruce Hauman>
* b3d1d16 - Add ETag to static files (#667) (2018-03-20 19:57:12 -0400) <Daniel Compton>
* 6774e05 - Add SUPPORT.md file (#672) (2018-03-19 14:40:28 -0400) <Daniel Compton>
* b0a74f4 - figwheel-core (2018-03-17 12:47:40 -0400) <Bruce Hauman>
* 060c382 - Migrate to CircleCI 2.0 (#669) (2018-03-13 08:34:57 -0400) <Daniel Compton>
* 6a65b7f - accept new compiler options (2018-03-09 13:29:28 -0500) <Bruce Hauman>
* 362a7de - ignore checkouts (2018-03-08 16:34:22 -0500) <Bruce Hauman>
* 61bb2d8 - update (2018-03-08 16:03:28 -0500) <Bruce Hauman>
* db2cd28 - Cljs 1.10.x worker target support (#659) (2018-03-05 09:23:04 -0500) <Milton Reder>
* ffe0d10 - update cljs dep in readme (2018-03-01 16:46:11 -0500) <Bruce Hauman>
* d811067 - another refinement (2018-03-01 16:01:30 -0500) <Bruce Hauman>
* 1eabf79 - initial rebel readline edits to the README (2018-03-01 15:54:21 -0500) <Bruce Hauman>
* a46cb2c - Typo (#658) (2018-02-28 20:54:42 -0500) <Ikuru K>
* 93a8864 - bump to 0.5.16-SNAPSHOT (2018-02-28 15:49:47 -0500) <Bruce Hauman>
* 72b7a67 - (tag: v0.5.15) update change log (2018-02-28 15:09:55 -0500) <Bruce Hauman>
* 7f4365e - don't reference figwheel.tools (2018-02-28 14:59:18 -0500) <Bruce Hauman>
* c6f8e95 - prepare for 0.5.15 release (2018-02-28 12:04:36 -0500) <Bruce Hauman>
* 2165f39 - update rebel dep (2018-02-28 12:02:59 -0500) <Bruce Hauman>
* 2b576f4 - punctuate (#656) (2018-02-24 20:34:12 -0500) <Gary Fredericks>
* 96137e6 - enable-repl-print no longer needed to catch eval printing (2018-02-24 19:24:46 -0500) <Bruce Hauman>
*   9f41863 - Merge branch 'capture-one-output' (2018-02-24 14:41:05 -0500) <Bruce Hauman>
|\  
| * 7b9c1d5 - capture and print out (2018-02-24 14:29:45 -0500) <Bruce Hauman>
|/  
* dfe8b4b - more dev setup (2018-02-24 13:46:42 -0500) <Bruce Hauman>
* a44e3b0 - ease development a bit (2018-02-24 13:20:00 -0500) <Bruce Hauman>
* bf3e046 - use a promise instead of an asyc channel (2018-02-24 13:17:29 -0500) <Bruce Hauman>
* 4a9641f - clean up color usage and make it smarter on windows (2018-02-24 12:18:05 -0500) <Bruce Hauman>
* b3aa6b8 - update deps (2018-02-23 18:34:24 -0500) <Bruce Hauman>
* b12cfdd - update clojurescript version (2018-02-23 17:37:57 -0500) <Bruce Hauman>
* 58b5ab2 - exception type changed (2018-02-21 13:24:06 -0500) <Bruce Hauman>
* 1be5b96 - missed a throw (2018-02-21 13:15:50 -0500) <Bruce Hauman>
* bc775f1 - (tag: v0.5.15-snapshot) don't trampoline if readline isn't required (2018-02-21 10:43:18 -0500) <Bruce Hauman>
* d328047 - make the inclusion of readline configurable with :readline opt (2018-02-21 10:34:33 -0500) <Bruce Hauman>
* fc382b7 - update for new rebel cljs namespace layout (2018-02-21 10:03:31 -0500) <Bruce Hauman>
* bc8bf6b - reduce references into rebel-readline and use new top level fns (2018-02-17 15:55:36 -0500) <Bruce Hauman>
* 1e1d40d - update helper app (2018-02-15 19:24:42 -0500) <Bruce Hauman>
* 79631dc - add color output in repl! (2018-02-15 19:24:42 -0500) <Bruce Hauman>
* e037fce - Fix function parameters in 'figwheel-sidecar.system/repl-function-docs' (#648) (2018-02-13 21:11:38 -0500) <zachcurry>
* c2f5ae6 - display rebel readline help message (2018-02-12 13:24:00 -0500) <Bruce Hauman>
* e21a810 - add :repl/help-figwheel to rebel-deadline commands (2018-02-12 12:40:04 -0500) <Bruce Hauman>
* fe8c2a3 - fix intermittent test failure (2018-02-11 22:41:37 -0500) <Bruce Hauman>
* e410bbb - exit doc (2018-02-11 14:40:16 -0500) <Bruce Hauman>
* 7c494ca - force trampoline for tasks that start a repl (2018-02-11 14:25:12 -0500) <Bruce Hauman>
* 55de952 - add rebel-readline to figwheel repl! (2018-02-11 11:11:07 -0500) <Bruce Hauman>
