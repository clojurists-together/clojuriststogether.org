---
title: "July 2023 Project Updates"
date: 2023-07-19T08:30:00+08:00
summary: clj-Nix, Clojure Camp, Emmy, Jank, Lucene Grep, Neanderthal, Portfolio
author: Kathy Davis  

---  
<br>
Hope all of you are having a great summer (or winter in the southern hemisphere)..Here is the first round of reporting from our Q2 2023 project developers. Catch up on all of their great work!   


## Q2 2023 Project Updates
[clj-Nix: Jose Luis Lafuente Esquembre](clj-nix-jose-luis-lafuente-esquembre)  
[Clojure Camp: Daniel Higginbotham](#clojure-camp-daniel-higginbotham)  
[Emmy: Sam Ritchie](#emmy-sam-ritchie)  
[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)   
[Lucene Grep: Dainius Jocas](lucene-grep-dainius-jocas)    
[Uncomplicate Neanderthal, Clojure CUDA, Deep Diamond: Dragan Djuric](#uncomplicate-neanderthal-clojure-cuda-deep-diamond-dragan-djuric)   
[Portfolio: Christian Johansen](#portfolio-christian-johansen)  <br>
<br>


## clj-Nix: Jose Luis Lafuente Esquembre   

Published 12 July 2023.  Q2 2023 Funding Round Report 1.

### Updates

For the first half of the funding round, I refactored the
[clj-nix](https://github.com/jlesquembre/clj-nix) CLI interface, which is used
to generate the `deps-lock.json` files. Now, if your project has multiple
`deps.edn` files and aliases, you can define what dependencies to include in the
lock file.  

The CLI added the following flags: `--deps-include`, `--deps-exclude`,
`--alias-include` and `--alias-exclude`.  

An optimized lock file saves bandwidth and speeds up the nix build. I also added
support to use Babashka as a build tool. To do that, use the `--bb` flag, see
the [clj-nix](https://github.com/jlesquembre/clj-nix) README for more info.  

As part of the work to support Babashka, I updated Babahska in nixpkgs
[PR NixOS/nixpkgs#241119](https://github.com/NixOS/nixpkgs/pull/241119). Those
changes benefit all nix users, not only
[clj-nix](https://github.com/jlesquembre/clj-nix) users.

I also updated `clojure-lsp` to use the latest
[clj-nix](https://github.com/jlesquembre/clj-nix) version:
[PR clojure-lsp/clojure-lsp#1623](https://github.com/clojure-lsp/clojure-lsp/pull/1623).
Try it with `nix run github:clojure-lsp/clojure-lsp -- --version`

Some other minor improvements I did:

- Refactored the `flake.nix` file, to fix the `nix flake check` command.

- Added GitHub Actions to update the clojure and nix dependencies automatically.

- `deps-lock` command checks if `deps-lock.json` is tracked by git. If not, runs
  `git add --intent-to-add`

- Add `extraJdkModules` option to `customJdk`

- Add `wrap` option to `mkBabashka`

- Check that the `main-ns` has a `:gen-class` in `mkCljBin`

### Next steps

Feedback welcome! For the second half of the round, I plan to continue working
on some minor improvements based on users feedback. If something is important to
you, feel free to leave a comment on any of the
[current open issues](https://github.com/jlesquembre/clj-nix/issues), or open a
new one.

I also want to add the option to configure a `clj-nix` build using
[NixOS modules](https://nixos.org/manual/nixos/stable/index.html#sec-writing-modules).
With a module interface, we don't need to expose the user to the internal
machinery of `clj-nix`. (The `NixOS modules` name is misleading, you can use
modules without NixOS. Projects like [devenv](https://devenv.sh/) or
[home-manager](https://nix-community.github.io/home-manager/) use that
approach). This is a big change, I don't think I'll be able to finish it in the
second half of the roundReport 1 Q2 2023 Funding Round. , but I'll start to work on it.<br>

---

## Clojure Camp: Daniel Higginbotham  

Published 30 June 2023.  Q2 2023 Funding Round Report 1.

We are so grateful to Clojurists Together for funding Clojure Camp! Our next milestone is to expand our program to include two more study groups,  with two mentors and five students each. Over the past month, we’ve focused on clarifying and documenting the systems we’ve been using to effectively run a study group and help people learn Clojure.

**Accomplishments include:**
- Conducted weekly study group sessions with our first batch of learners
- Began migrating ClojoDojo, a study partner pairing app, over to Clojure Camp
- Made progress on setting up Clojure Camp operations, building out our digital workspaces for knowledge management
- Documented our curriculum
- Updated learner exercises<br>

---

## Emmy: Sam Ritchie  

Published 3 July 2023.  Q2 2023 Funding Round Report 1.

### Done in June
I cut a first release of the
[Emmy-Viewers](https://github.com/mentat-collective/emmy-viewers) library last
week!

Emmy-Viewers currently contains powerful 2D and 3D plotting primitives,
based on the [Mafs.cljs](https://mafs.mentat.org) and
[MathBox.cljs](https://mathbox.mentat.org) libraries, as well as support for
embedding interactive controls into these 2D and 3D scenes using
[Leva.cljs](https://leva.mentat.org).

Here is an example of a namespace full of 3D plots:
https://emmy-viewers.mentat.org/dev/examples/mathbox/functions

This is a crazy powerful system, capable now of representing most of what you'd
need for a math curriculum up through high school and on into graduate-level
physics and calculus.

### How do I use it?

The developer experience goes like this. You:

  - install Emmy-Viewers via [Clojars](https://clojars.org/org.mentat/emmy-viewers) as a normal Clojure dependency
  - choose
    [Portal](https://github.com/mentat-collective/emmy-viewers#quickstart-via-portal)
    or
    [Clerk](https://github.com/mentat-collective/emmy-viewers#quickstart-via-clerk)
    as your presentation environment. (Only Clerk can handle 3D for now.)

Once you've configured a namespace using the [README
instructions](https://github.com/mentat-collective/emmy-viewers), you can use
the Emmy-Viewers API to write down values like this:

```clojure
(mafs/of-x sin {:color :blue})
```

And the presentation environment (Clerk, for example) will generate an
interactive scene:

![Mafs plot example](https://github.com/mentat-collective/emmy-viewers/assets/69635/446cd61f-0795-4375-bb67-4a2b687c9a2e)

None of this requires any ClojureScript compilation; Emmy-Viewers works as a
pure Clojure dependency.

### Examples

The [examples
directory](https://github.com/mentat-collective/emmy-viewers/tree/main/dev/examples)
in the Emmy-Viewers repo has a bunch of usage examples. Here are some highlights:

- 2D examples gallery via Mafs: https://emmy-viewers.mentat.org/dev/examples/mafs
- 3D examples gallery via MathBox: https://emmy-viewers.mentat.org/dev/examples/mathbox/functions

Both of these are actually FULLY EDITABLE in the browser! Open the links above
with `/edit` appended to the URL, and you'll see a mini IDE on the left side of
the page, with available commands listed at the bottom. Try changing values and
hitting option-Enter to re-evaluate the page. Any change you make will flow down
through the full namespace.

- editable 2D: https://emmy-viewers.mentat.org/dev/examples/mafs/edit
- editable 3D: https://emmy-viewers.mentat.org/dev/examples/mathbox/functions/edit

Some more examples:

- (p, q) torus knot: https://emmy-viewers.mentat.org/dev/examples/manifold/pq_knot
- more 3D surfaces and manifolds: https://emmy-viewers.mentat.org/dev/examples/manifold/fdg
- Klein bottles and mobius strips: https://emmy-viewers.mentat.org/dev/examples/manifold/klein
- 3D geometry primitives: https://emmy-viewers.mentat.org/dev/examples/mathbox/geom

Next we have some physics and differential equation simulations:

- Strange attractor: https://emmy-viewers.mentat.org/dev/examples/mathbox/ode
- particle stuck on an ellipse: https://emmy-viewers.mentat.org/dev/examples/simulation/ellipsoid
- editable version!! https://emmy-viewers.mentat.org/dev/examples/simulation/ellipsoid/edit
- two particles stuck together with a spring: https://emmy-viewers.mentat.org/dev/examples/simulation/double_ellipsoid
- geodesics of a torus: https://emmy-viewers.mentat.org/dev/examples/simulation/toroid

### What's Next?

Next, I'm going to use the exercises in Sussman's ["Structure and Interpretation
of Classical Mechanics"](https://tgvaughan.github.io/sicm/) as a guide for
adding more physics-aware functions to the API. These exercises will serve as
examples and documentation.

I also want to think through how to improve this "editable online" experience by
possibly fleshing out the ability to save and persist these scenes, maybe by
integrating with https://maria.cloud/.

Finally, there is a lot of work on the plotting API that would be great for new
contributors, like adding support for color schemes to the 3D plots. If you find
that interesting and would like to get involved, please let me know!<br>

---

## Jank: Jeaye Wilkerson
Published 13 July 2023.  Q2 2023 Funding Round Report 1.

The terms of the work are to research a new object model for jank, with the goal of optimizing allocations, while also making jank code faster across the board. This is a half-way report and I'm excited to share my results!  

Please note that I have a very detailed breakdown of my work, the design choices made, and the implementation details here: https://jank-lang.org/blog/2023-07-08-object-model/  

To briefly summarize, I spent the first half of this quarter:
1. Exploring the top ECS frameworks as an option for jank's object model
2. Prototyping a tagged object implementation
3. Benchmarking everything along the way
   
### ECS
The ECS frameworks aren't going to be a good fit, since they'll require multiple allocations per object and the key metric I'm trying to shrink here is object allocation time. However, there are good learnings to be had around their data locality designs and judicious usage of compile-time computation to set the runtime up to be fast.  

### Tagged objects
Most Clojure code is built using the same numbers, strings, maps, vectors, and lists. The hyper-polymorphic cases of completely custom types and protocols is not the general case and shouldn't have to impact the performance of it. I've prototyped a solution which optimizes the default case of using Clojure's built-in types, while still allowing for entirely dynamic objects.  

The results have been very promising.  

### Map allocations
Map allocations were the primary target. Details for why they were slow are in my blog post, but the new object model brings us within a very small margin of Clojure.  

<object type="image/svg+xml" data="https://jank-lang.org/img/blog/2023-07-08-object-model/allocations-tagged.plot.svg" width="50%">
  <img src="https://jank-lang.org/img/blog/2023-07-08-object-model/allocations-tagged.plot.svg" width="50%"></img>
</object>  


### Map utilities
Map utilities like count and get were already very fast, but the new object model cuts them down even more.  


<object type="image/svg+xml" data="https://jank-lang.org/img/blog/2023-07-08-object-model/extra-benchmarks.plot.svg" width="50%">
  <img src="https://jank-lang.org/img/blog/2023-07-08-object-model/extra-benchmarks.plot.svg" width="50%"></img>
</object>

### What's left?  
I'm now ripping apart the jank runtime to add in the tagged object system. At the end of the quarter, I'll have holistic benchmarks showing the improvements across the board. These benefits, once integrated into jank, will impact every jank object. Numbers, strings, vectors, maps, lists, and so on will all be significantly faster to both allocate and use.<br>  


---


## Lucene Grep: Dainius Jocas
Published 15 July 2023.  Q2 2023 Funding Round Report 1.

### TL;DR  
It was a little busy lately so my update is a bit uneventful.
But my summer holidays are coming and I hope to spend some quality time working on making the Lucene library a little easier to use for the fellow Clojurists.

### Context  
The goal of the project is to tear apart `lucene-grep` project into a bunch of libraries.  
`lucene-grep` is a CLI app that was created to scratch an itch of making Lucene to be compiled by GraalVM native image during the COVID lockdowns.
Due to the nature of the effort the code was not designed to be used elsewhere.
By participating in the Clojurist Together I want to refactor and re-design the existing codebase for reuse.

### Updates  
As of now, out of the `lucene-grep` 3 libraries are extracted:  
- [lucene-custom-analyzer](https://github.com/dainiusjocas/lucene-custom-analyzer): data-driven Lucene Analyzers; 
- [lucene-query-parsing](https://github.com/dainiusjocas/lucene-query-parsing): data-driven Lucene Query Parsers;
- [lucene-text-analysis](https://github.com/dainiusjocas/lucene-text-analysis): helpers to play with the Lucene Analyzers.

All these libraries were updated to depend on the newest Lucene version.
I hope to write proper blog posts and/or demo apps with potential uses of the libraries.

### Other things I've worked on  
I've been having fun with other hacks.

#### The new GraalVM  
I've upgrade `lucene-grep` to be compiled with the new [GraalVM](https://github.com/dainiusjocas/lucene-grep/commit/7343db1413ef5d3c1c89547b091a1bdb7c5d2fe2).
The biggest adventure there was that GraalVM changed the way how compile time environment variables are used.
Now, the env vars should be passed with `-E` [property](https://www.graalvm.org/latest/reference-manual/native-image/overview/BuildOptions/#non-standard-options) e.g. `-ELMGREP_FEATURE_STEMPEL=true`.

#### Clerk based publishing  
I've set up a publishing system based on [Clerk](https://github.com/nextjournal/clerk) and Github Pages: [journal](https://www.jocas.lt/journal/), [source](https://github.com/dainiusjocas/journal).
Hopefully, in the Journal I'll publish proper writeups and presentations of the work I'm about to do in this project.

### What is next?  
In the coming months I want to design a Clojure library to use the Lucene Monitor Library.

### That is it!<br>

---

## Uncomplicate Neanderthal, Clojure CUDA, Deep Diamond: Dragan Djuric
Published 30 June 2023.  Q2 2023 Funding Round Report 1.

My goal with this round is to port Neanderthal, ClojureCUDA, and Deep Diamond to JavaCPP-based native libraries.

This first month was dedicated to the work on the first releasable version of the ClojureCPP library,
and on porting ClojureCUDA to the JavaCPP-provided bindings.

So far I've been able to fully port ClojureCUDA, and to improve JavaCPP to the level that it could be released
as standalone library that does not require many changes.

Next month will be dedicated to porting Neanderthal's CPU and CUDA backends to use JavaCPP (via ClojureCPP).
I hope that I discovered and solved most hard problems by now, so this won't bring big surprises.
On the other hand, I expect do gather addition ideas for improvement and fixes in JavaCPP and JavaCUDA
during the Neanderthal port.

All in all, I feel that the project is on track and the progress goes as expected, which means that
I think I'll be able to port ClojureCUDA, Neanderthal, and Deep Diamond to ClojureCPP by the end
of the third month, to the level that I can release the new version of all 4 libraries.

What's equally important, I'm using every opportunity to *improve* everything that I could,
instead of just taking the shortest cut at every corner. So the benefits will be felt long-term in
these libraries, as well as this domain in Clojure.

I released most of the resulting code in a couple of gigantic commits instead of many smaller ones because I didn't want any release to contain broken, non-compilable library.<br>

---

## Portfolio: Christian Johansen
Published 14 July 2023.   Q2 2023 Funding Round Report 1.

In the first half of the Q2 funding period I have focused my work on the main
aspirations from my application: error handling, and improved first use
experience.

Portfolio now catches more errors than before, and also displays more helpful
information about them. As far as I can tell, Portfolio now catches and properly
handles and renders all runtime errors that you can produce from your code. A
new heads-up display was added to display some asynchronous errors that can't
necessarily be attributed to the currently rendered components. If you manage to
produce an error that is only visible in the browser console with the latest
version of Portfolio, please report it as a bug.

Portfolio now has a more friendly, and hopefully more helpful first use
experience. Documentation has been expanded and improved - and importantly - is
now self-hosted by Portfolio. When you first set up Portfolio, the UI displays
the relevant documentation to help you get fully started, and all of Portfolio's
documentation is always available via a new button in the sidebar. I will keep
working on the content for this solution.

The solution for self-hosting documentation still uses markdown files that can
be readily browsed on Github as well, so you don't have to run Portfolio to read
it. But when you do, you have relevant references close at hand.

Portfolio's React adapter has been upgraded with an error boundary that
integrates with the above described error handling features. It now also
supports using hooks directly in `defscene`.  

I added a small REPL utility to Portfolio. You can now `tap>` a component
instance, and Portfolio will render it in a temporary REPL folder.

Finally I have done some exploratory work on an accessibility extension to make
it easier to develop accessible components. I will continue this work in the
next half of the funding period.

The funding from Clojurists Together has allowed to me to set aside dedicated
time to focus on Portfolio, which has helped me raise the level of polish. For
this I am very grateful. Thank you all so much! 🙏






