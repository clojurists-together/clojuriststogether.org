## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve?

The Figwheel codebase is  an exploration of the problem space of creating a friendly ClojureScript. As a UI to ClojureScript the codebase is complex and complected.

I would love to devote a solid 3 months to working solely on Figwheel. I would like revisit it's functionality and condense it down into an architecture and a set of libraries that will allow it to be reused in other contexts (i.e boot etc.) more easily. For example: other projects should be able to use a figwheel's compiler wrapper that produces attractive error messages, or perhaps just figwheel's error message parsing code.

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

#### Figwheel Main

The main idea driving figwheel.main is that it should provide all the
functionality of `cljs.main` in addition to figwheel functionality. I
want ClojureScript users to have less cognitive overhead when it comes
to tooling. Figwheel Main now provides all the features of Figwheel Sidecar and more. However, it is not yet as friendly as I am wanting it to be.


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

The most difficult part of working on it was the design and trade-offs
that surrounded the server that is used for the REPL connection. In the
end, I settled on using the Jetty Server because it is the most
commonly used Ring server in the ecosystem.

The Figwheel REPL also now serves a default `index.html` so that
establishing an initial REPL connection is a simple matter. Unfortunately the REPL is not complete yet, but the rest of its
development will be better informed by starting work on `figwheel-main`

### Fixing bugs

The work for the last 15 days has included working out the final
hiccups with integrating Rebel Readline into Figwheel. This involved looking at how the Figwheel REPL handled printing. A significant improvement to Figwheel came out of this. Now when you type `(prn 1)` at the REPL prompt you will only get a single `1` printed at the output.

I also spent a significant amount of time making sure that the
readline worked on Windows. In this process I also fixed color output on Windows. Figwheel detects what kind of terminal you are in and provides ANSI color codes only if the terminal can handle it. The upshot of this is that Figwheel is working better than it ever has on Windows.

### Maintenance

Today I finally released [Figwheel 0.5.15](https://github.com/bhauman/lein-figwheel/releases/tag/v0.5.15) and the initial release of [Rebel Readline 0.1.1](https://github.com/bhauman/rebel-readline/releases/tag/v0.1.1).

### Spec errors

This [commit](https://github.com/bhauman/lein-figwheel/commit/af05109260b6f2738c30914e72ddf5f91de3b660) adds special handling for spec errors to make them easier to understand in the heads-up display.

## Commits

git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="FEB 1 2018" --until "APRIL 30 2018"

```
* f785b6a - figwheel.main: fix help formatting (2018-04-28 17:28:41 -0400) <Bruce Hauman>
* 0ee4de5 - figwheel.main: CLI no args start (2018-04-28 17:12:49 -0400) <Bruce Hauman>
* 1ac4953 - figwheel.main: formatted exceptions and warnings working in nREPL (2018-04-28 13:50:13 -0400) <Bruce Hauman>
* 1763c63 - figwheel.main: ability to launch piggieback in nrepl (2018-04-27 15:12:00 -0400) <Bruce Hauman>
* 91b178e - figwheel.main: remove spurious compile warnings (2018-04-27 13:34:10 -0400) <Bruce Hauman>
* 233da45 - figwheel.main: config :log-syntax-error-style (2018-04-27 11:25:40 -0400) <Bruce Hauman>
* 64e75ba - figwheel.main: :ansi-color-ouput config (2018-04-27 10:18:46 -0400) <Bruce Hauman>
* 3ad5321 - figwheel.main: logging, log-level and log-file config (2018-04-27 09:58:53 -0400) <Bruce Hauman>
* d1b748f - figwheel.main: log compile warnings (2018-04-27 07:43:02 -0400) <Bruce Hauman>
* 9a91b8b - verify that exception data is serializable (2018-04-26 16:38:16 -0400) <Bruce Hauman>
* 0136c9a - figwheel.main: spec exception logging (2018-04-26 16:11:29 -0400) <Bruce Hauman>
* af05109 - figwheel.core: handle spec syntax errors better (2018-04-26 15:38:47 -0400) <Bruce Hauman>
* 8069792 - figwheel.main: add a simple style fn to ansi (2018-04-26 15:37:38 -0400) <Bruce Hauman>
* 4a8f22d - fix formatting error and return what compile returns (2018-04-26 15:36:52 -0400) <Bruce Hauman>
* 007f20a - figwheel.main: add color feedback (2018-04-26 13:17:00 -0400) <Bruce Hauman>
* f665f27 - figwheel.main add logging (2018-04-26 11:21:38 -0400) <Bruce Hauman>
* 5845c8e - fix aget usage (2018-04-25 23:06:10 -0400) <Bruce Hauman>
* e4aeba0 - move figwheel.main to a cljc for easier repl access to api macros (2018-04-25 14:04:04 -0400) <Bruce Hauman>
* 14060e8 - figwheel.main: more repl-api commands (2018-04-25 13:45:21 -0400) <Bruce Hauman>
* 4e6322e - figwheel.main started repl api (2018-04-25 11:47:52 -0400) <Bruce Hauman>
* c6021aa - figwheel.main: background builds (2018-04-24 16:07:51 -0400) <Bruce Hauman>
* c7f9d8a - figwheel.main: cleaning up and preparing for background build support (2018-04-24 10:48:47 -0400) <Bruce Hauman>
* 2275257 - figwheel-main configure client-print-to (2018-04-23 15:08:49 -0400) <Bruce Hauman>
* c50598e - figwheel-repl: print captured output to console if its called for (2018-04-23 14:53:54 -0400) <Bruce Hauman>
* 761475b - add rebel-readline (2018-04-23 14:36:47 -0400) <Bruce Hauman>
* 14b8359 - dispatch css load event, provide examples of load hooks (2018-04-23 14:14:08 -0400) <Bruce Hauman>
* cc21eab - figwheel-main: refactor and css reloading (2018-04-23 12:08:19 -0400) <Bruce Hauman>
* 376679f - figwheel-main: open file command (2018-04-22 15:13:10 -0400) <Bruce Hauman>
* ee5ff80 - figwheel-core fix exception parsing for cli 1.9 throwable maps (2018-04-22 12:12:28 -0400) <Bruce Hauman>
* dca3fb4 - rename to scope validation functionality to main (2018-04-22 10:52:54 -0400) <Bruce Hauman>
* aadf55a - begin to add spec validation to figwheel-main (2018-04-22 10:29:47 -0400) <Bruce Hauman>
* a42e035 - figwheel.main reload changed clj files (2018-04-20 21:22:32 -0400) <Bruce Hauman>
* 03c2533 - parse clojure exceptions when the exception has a resource path (2018-04-20 21:21:53 -0400) <Bruce Hauman>
* d0d52b9 - allow figwheel.core/build to handle changes files as input (2018-04-20 21:20:56 -0400) <Bruce Hauman>
* d098787 - correct config spec docs for reload-cli (2018-04-20 21:20:01 -0400) <Bruce Hauman>
* 803a7de - add connection filtering to figwheel-main (2018-04-20 17:14:22 -0400) <Bruce Hauman>
* 175ee66 - handle server port correctly (2018-04-20 10:42:10 -0400) <Bruce Hauman>
* b6f9f93 - clean up figwheel-main a bit (2018-04-19 17:26:40 -0400) <Bruce Hauman>
* 964af19 - more logic for build once (2018-04-19 07:37:46 -0400) <Bruce Hauman>
* cfcc051 - decent progress on figwheel-main (2018-04-18 17:16:23 -0400) <Bruce Hauman>
* d469f70 - don't have connection-filter in an atom (2018-04-18 17:14:17 -0400) <Bruce Hauman>
* 6d2f3cb - add figwheel-main readme placeholder (2018-04-18 13:42:48 -0400) <Bruce Hauman>
* 3df3d09 - update for cider piggieback (2018-04-18 12:31:37 -0400) <Bruce Hauman>
*   7ec3bf5 - Merge remote-tracking branch 'origin/master' (2018-04-17 17:57:09 -0400) <Bruce Hauman>
|\  
| * 17d3a92 - Update for :npm-deps false and latest :npm-deps docs (#678) (2018-04-17 08:34:09 -0400) <Mike Fikes>
* | 361c70f - some project changes for figwheel-main (2018-04-17 17:57:05 -0400) <Bruce Hauman>
* | cc721b6 - make eval more robust (2018-04-17 17:21:11 -0400) <Bruce Hauman>
* |   0175edc - Merge branch 'figwheel-main' (2018-04-17 15:53:16 -0400) <Bruce Hauman>
|\ \  
| |/  
|/|   
| * 2354934 - (origin/figwheel-main) make --serve work (2018-04-17 15:52:48 -0400) <Bruce Hauman>
| * 2e448ab - dev script (2018-04-17 15:32:05 -0400) <Bruce Hauman>
| * e50777c - a working figwheel main (2018-04-17 15:26:12 -0400) <Bruce Hauman>
| * 710eb6d - save server itself not just a fn to kill it (2018-04-17 15:25:54 -0400) <Bruce Hauman>
| * 6c68f1d - wip (2018-04-16 20:16:44 -0400) <Bruce Hauman>
| * 5b4ea1f - small changes (2018-04-16 20:16:32 -0400) <Bruce Hauman>
| *   127f59c - Merge branch 'master' into figwheel-main (2018-04-16 10:08:59 -0400) <Bruce Hauman>
| |\  
| |/  
|/|   
* | d11d2cc - fix newline handling when capturing eval output (2018-04-13 09:09:25 -0400) <Bruce Hauman>
| * ea7862a - raise default port to var (2018-04-16 10:08:53 -0400) <Bruce Hauman>
| * c4cad9e - first figwheel main commit (2018-04-13 09:04:56 -0400) <Bruce Hauman>
|/  
* 48fa4ef - default connect url (2018-04-13 09:03:48 -0400) <Bruce Hauman>
* 7da0d7b - add default index.html capability (2018-04-11 12:09:26 -0400) <Bruce Hauman>
* bd04f4c - oopsy (2018-04-11 12:05:57 -0400) <Bruce Hauman>
* 3a1afad - figwheel main deps (2018-04-10 10:32:44 -0400) <Bruce Hauman>
* 2501331 - git ignore (2018-04-10 10:32:09 -0400) <Bruce Hauman>
* f1e3f0c - make repl minimally configurable form command line (2018-04-10 10:30:15 -0400) <Bruce Hauman>
* 0d12ab6 - fix promise usage (2018-04-10 10:29:35 -0400) <Bruce Hauman>
* 35ae00b - fix duplicate logging (2018-04-10 10:29:02 -0400) <Bruce Hauman>
* 64090ef - clean up deps (2018-04-10 09:37:22 -0400) <Bruce Hauman>
* b0d3a6a - require macros in figwheel core (2018-04-10 09:35:15 -0400) <Bruce Hauman>
* 1f3000d - server namespace not needed (2018-04-09 16:47:46 -0400) <Bruce Hauman>
* 1d06f68 - solid progress on the repl server (2018-04-09 16:44:46 -0400) <Bruce Hauman>
* eeb23e1 - default server wip (2018-04-07 17:04:30 -0400) <Bruce Hauman>
* 3e222c1 - fix missing def (2018-04-07 12:14:45 -0400) <Bruce Hauman>
* 105b318 - refactor (2018-04-06 19:02:29 -0400) <Bruce Hauman>
* 3f11f6a - printing works (2018-04-06 15:11:26 -0400) <Bruce Hauman>
* 061abef - whitespace (2018-04-06 12:37:32 -0400) <Bruce Hauman>
* da7074a - handle printing redirection on the client side (2018-04-06 12:36:51 -0400) <Bruce Hauman>
* 425f19a - make-url preserves query string (2018-04-06 11:12:42 -0400) <Bruce Hauman>
* 55948a1 - single connect function that dispatches based on scheme (2018-04-06 11:11:17 -0400) <Bruce Hauman>
* 472c2e5 - make storage work when session isn't available (2018-04-06 10:57:44 -0400) <Bruce Hauman>
* 0a8040d - point to new after-reloads (2018-04-06 10:43:46 -0400) <Bruce Hauman>
* 5baea78 - simplify promise usage (2018-04-06 10:39:02 -0400) <Bruce Hauman>
* 6250660 - dev notes (2018-04-05 21:47:10 -0400) <Bruce Hauman>
* 1d64ca3 - add more reliability to connections (2018-04-05 18:25:04 -0400) <Bruce Hauman>
* 69e8dbd - solid http polling (2018-04-05 14:49:56 -0400) <Bruce Hauman>
* b8b8516 - use jetty web socket server (2018-04-04 14:28:09 -0400) <Bruce Hauman>
* 44bff9f - more figwheel-repl readme updates (2018-04-03 10:35:15 -0400) <Bruce Hauman>
* 1a73cbc - update figwheel-repl readme (2018-04-03 10:33:19 -0400) <Bruce Hauman>
* e0ff72e - figwheel-repl readme additions (2018-04-03 10:26:52 -0400) <Bruce Hauman>
*   c3bc816 - Merge branch 'figwheel-repl' (2018-04-03 09:05:15 -0400) <Bruce Hauman>
|\  
| * 3411514 - added readme to figwheel-repl (2018-04-03 09:04:45 -0400) <Bruce Hauman>
| * 3671131 - http polling work (2018-04-02 20:48:23 -0400) <Bruce Hauman>
| * 269530c - significant abstraction of connection code (2018-04-02 19:02:42 -0400) <Bruce Hauman>
| * 9616e00 - http-polling connection (2018-04-02 13:05:23 -0400) <Bruce Hauman>
| * b3cb513 - improvements (2018-03-31 18:56:03 -0400) <Bruce Hauman>
| * 33d94e4 - session focus helpers (2018-03-31 12:21:27 -0400) <Bruce Hauman>
| * edb12ce - repl can start server if not started yet (2018-03-29 17:34:03 -0400) <Bruce Hauman>
| * f7a1318 - WIP (2018-03-29 08:45:39 -0400) <Bruce Hauman>
|/  
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
```