---
title: "Nov. 2024 Short-Term Project Updates"
date: 2024-12-09T14:00:00+12:00
author: Kathy Davis
summary: "clj-Nix, Clojure Goes Fast, Jank, Kushi, Malli, SciCloj, Standard Clojure Style"
draft: True


---

We've got our second (and one third) set of reports from developers working on short-term projects funded in Q3 2024. You'll find a brief description of each project at the top of the page to provide some context -- followed by current project updates.

### [clj-Nix: José Luis Lafuente](#clj-nix-josé-luis-lafuente)  
- Develop an alternative builder for Nix that uses Babashka / SCI instead of Bash. It provides a way
for constructing complex Nix derivations using Babashka/SCI entirely, eliminating the need to
write any Bash code. 
- Also, will be adding a Babashka writer to nixpkgs. Nixpkgs supports creating self-contained scripts, called "nix writers." Traditionally written in Bash, recent versions of nixpkgs include the ability to write these scripts in Python, Ruby, or Rust.   

### [Clojure Goes Fast: Oleksandr Yakushev](#clojure-goes-fast-oleksandr-yakushev)
- Create a new web application for clj-async-profiler that will allow users to host and share the generated flamegraphs. At the moment, even though flamegraphs are just self-contained HTML files, sending them to
somebody is a chore. The new service can make this much easier and offer extra features
like saving and sharing dynamic transforms on the fly. Additionally, I'd like to focus on the UI
side of clj-async-profiler - add new dynamic transforms, improve aesthetics and the UX.  
- For clj-java-decompiler, expand its preprocessing abilities to present clearer results to the user and integrate it more tightly with established Clojure IDEs like CIDER and Calva, which requires some groundwork.
- Adapting and updating benchmarking content in the [Clojure Goes Fast knowledge base](https://clojure-goes-fast.com/kb/)

### [Jank: Jeaye  Wilkerson](#jank-jeaye--wilkerson)
Jank feels like Clojure now, with 92% syntax parity and nearly 40% clojure.core parity. But it only feels like Clojure to me because none of you are using it yet. My top priority is to change that.  I'll be working on building out jank's nREPL server, which involves implementing bencode support, `clojure.test`, improving native interop, supporting pre-compiled binary modules, and ultimately adding AOT compilation support.  

### [Kushi: Jeremiah Coyle](#kushi-jeremiah-coyle)
Continue development of Kushi, a foundation for building web UI with ClojureScript. Work this funding cycle will focus on finishing the new css transpilation pipeline, significant build system performance upgrades, and implementing a reimagined theming system.  

### [Malli: Ambrose Bonnaire-Sergeant](#malli-ambrose-bonnaire-sergeant) 
This project (Constraints and Humanization) aims to drastically improve the expressivity of Malli schemas to help address current user feedback and enable future extensions. The basic idea is to add a constraint language to each schema to express fine-grained invariants and then make this constraint language compatible with validators/explainers/generators/etc so that Malli users can write high-level, precise schemas without resorting to partial workarounds. See prototype here: https://github.com/frenchy64/malli/pull/12  

### [SciCloj: Daniel Slutsky](#scicloj-daniel-slutsky)
Check out Daniel's video: https://www.youtube.com/watch?v=WO6mVURUky4. Scicloj is a Clojure group developing a stack of tools & libraries for data science.
Alongside the technical challenges, community building has been an essential part of its
efforts since the beginning of 2019. Our community-oriented goal is making the existing data-science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on
example-based documentation, easy setup, and recommended workflows for common tasks. All these, and the tools to support them, grow organically, driven by real-world use-cases. See updates for progress on Q3 projects and documentation.  
 
### [Standard Clojure Style:Chris Oakman](#standard-clojure-style-chris-oakman)
Continue work on Standard Clojure Style - which is a "no config, runs everywhere, follows simple rules" formatter for Clojure code. More information about the genesis of the project can be found on Issue #1:
https://github.com/oakmac/standard-clojure-style-js/issues/1  
<br>

---


## clj-nix: José Luis Lafuente  
Q3 2024 Report No. 2 (final), Published Nov. 29, 2024  

### Nix babashka builder  

I've made some progress with the Babashka builder. The builder should still be
considered experimental, and the API may change . It's now possible to create
and override derivations, as well as have interdependencies between different
derivations created with the Babashka builder.  

I'm particularly happy with the reduction in Nix code. The Babashka builder
allows you to create Nix derivations with minimal Nix code. While it might still
feel a bit hacky, it's possible to define Nix dependencies between derivations
directly within Clojure code.  

On the less positive side, I think it's currently difficult to reuse some of the
build helpers available in Nixpkgs. I definitely don't want to reimplement all
those helpers, so I'll try to find a way to reuse those Nixpkgs helpers (even if
they're written in Bash) from the Babashka builder.  

I also wrote some documentation for it. If you want to know more, [see](https://jlesquembre.github.io/clj-nix/bb-env/)

I also created a repository with some examples, covering four topics:  

- A basic derivation  
- An override  
- How to wrap other programs to capture all runtime dependencies  
- Dependencies between multiple derivations created with the Babashka builder  

Here is the [link:](https://github.com/jlesquembre/bb-pkgs)

### Improvements for clj-nix  

While it's not directly related to this proposal, I'd also like to mention some
improvements I made to clj-nix on my own time. These improvements primarily
focus on how clj-nix generates lock files.  

I've also released a new version with these changes:  
[Release 0.4.0](https://github.com/jlesquembre/clj-nix/releases/tag/0.4.0)  

The Nix Babashka builder is not included in this release (as I believe it's
still too early for a stable release). However, I've already merged it into the
main branch, making it easier for people to test it out.  <br>

---

## Clojure Goes Fast: Oleksandr Yakushev  
Q3 2024 Reports No. 2 and 3 (final), Published Oct. 31 and Nov. 30, 2024   

**Report 2:**  
During the second month of my Clojurists Together project I published a major update of clj-async-profiler. The new release contains a redesigned and improved profile list page with better UI, convenient sorting, and easy access to diffing functionality. The rest of the time I've spent working on the flamegraph sharing webapp that I plan to release into a public betatest at the beginning of the third month. As a bullet list:  

- Published the release of clj-async-profiler 1.4.0. Big changes:  
  - New index page design.  
  - Diffgraph generation functionality accessible from UI.  
  - Flamegraph rendering fixes - prevent horizontal scrollbar from appearing and the tooltip from clipping near screen borders.  
  - Stability fix for JDK23.  
- Wrote a blogpost about 1.4.0 release [highlighting the main changes:](https://clojure-goes-fast.com/blog/clj-async-profiler-140/).   
- Set up infrastructure, deployment, and proof-of-concept implementation of the flamegraph sharing site. Got very close to the initial public release.  

**Report 3:**  
I've spent the third month of my Clojureists Together project working primarily on Flamebin — a new web application that allows uploading and sharing flamegraphs. At the beginning of the month I launched it as public beta, and by the end of the November I finally published it as a first stable release. A more detailed report below:  
- Made a public release of [Flamebin](https://flamebin.dev)  
  - Set up a [public repository](https://github.com/clojure-goes-fast/flamebin)  
  - Created and configured a VPS instance to host the web application  
  - Configured CI for testing, buildign the application and deploying it to two environemnts (QA and prod)  
  - Flamebin allows uploading profiles to later render them as flamegraphs:  
    - Supports two formats of uploads — collapsed stackframes (compatible with perf, async-profiler, and other profiling tools) and dense profiles.  
    - Uploading is possible using a web page or through clj-async-profiler integration.  
    - An uploaded profile can either be publicly visible or private. Private uploads are encrypted by a secret read-token that is required for viewing the profile and is not stored on the server.  
    - Accidentally uploaded profiles can be deleted by providing a secret edit-token.  
  - Uploaded flamegraphs have a stable URL that can be viewed by anyone (for public flamegraphs) or only by those with a read-token (for private flamegraphs). The flamegraph renderer is reused from clj-async-profiler. User can explore the flamegraph interactively, change the viewing preferences, and also save those preferences so that other users can open the pre-configured flamegraph later.  

I also made two releases of clj-async-profiler — 1.5.0 and 1.5.1 follow-up. These releases unified the approach to flamegraph configuration, enabling different ways to change the configuration:  
- Specify the configuration before generate a flamegraph, using code.  
- Save the current configuration (set in UI) to the server for the subsequently generated flamegraphs to inherit.  
- Copy and paste configuration between different flamegraphs in the UI.  
- Provide configuration in the URL parameter.  
All of the configuration improvements were ported to Flamebin as well.  

Finally, I've been updating the [Clojure Goes Fast knowledge base](https://clojure-goes-fast.com/kb/profiling/clj-async-profiler/) to cover the new clj-async-profiler and Flamebin features.  <br>  

---

## Jank: Jeaye Wilkerson  
Q3 2024 Report No. 2, Published Nov. 30, 2024   
Hi everyone! It's been a very busy couple of months as I've been developing
jank's LLVM IR generation, improving jank's semantic analysis, and furthering
jank's module loading system. Thank you to all of my Github sponsors and to
Clojurists Together, who help me pay the bills. As of January 2025, I'll be
working on jank full-time and every new sponsor means that much more.
Without further ado, let's dive into the details of the past couple of months.

### LLVM IR
The main focus of the past couple of months has been filling out jank's LLVM IR
generation. This has required further improving some of its semantic analysis
since I was previously able to cut some corners when generating C++ code.  

At this point, all AST nodes in jank have working and tested IR generation
except for `try`, since doing so requires hooking into the C++ runtime's unwind
mechanism and I've been saving that rabbit hole for last.  

IR generation has caused so many fun bugs the past couple of months that I had
to look into a better way of organizing my notes for this quarter. There was
just too much. When developing a language, especially in a pre-alpha stage like
jank, when something crashes or is otherwise completely wrong, the issue could
be anywhere from lexing to parsing to semantic analysis to code generation to
object modeling to core function algorithms to data structures. I think there
were some of each discovered in the past couple of months, but the majority of
them were in the new IR generation.  

### Named recursion  
One of the fun bugs I ran into was with how Clojure handles recursion through
the function's own name. This doesn't go through the var; it just references the
function object directly. The same applies with just a self-reference. For
example:  

```clojure
(fn foo []
  foo)

(defn foo []
  foo)
```

In Clojure, and in jank with C++ generation, each function is an object (struct
or class). A self-reference can literally just mean `this`. But with the LLVM IR
generation, each function, and more specifically each function arity, is
compiled to a dedicated C function. Aside from closures, which get an implicit
first argument which is a generated struct of the captured values, the function
itself has no other identifying state or object. This means a self-reference
actually needs to build a new object. Previously, I would always just generate a
local into each function like so:  

```cpp
auto const foo{ this };
```

Then I would register that local automatically during semantic analysis for
functions. A self-reference would then just be a `local_reference` AST node.
This can't carry over well to IR generation, so I've added two new AST nodes:  

1. `recursion_reference`, which is created when we analyze that we're referring
   to the current function by name somewhere  
2. `named_recursion`, which is created when we're analyzing a `call` and find
   that the source expression is a `recursion_reference`  

This does mean that a self-reference within a function wouldn't be `identical?`
to the invoking object, but that's something we can document and otherwise
really not care about.  

### Startup performance  
Ultimately, the main reason for generating LLVM IR is that C++ is too slow to
compile. If compiling `clojure.core` means generating 100K lines of C++ to
compile, we end up waiting far too long for jank to start up. On my machine, it
took around **12 seconds**.  

Now, with IR generation, and a lot more functionality baked into jank itself,
rather than JIT compiled, compiling `clojure.core` from source takes only **2 seconds**.
This is fast enough to where we can easily include it as part of jank's
build system. We can then pre-compile the sources to binaries and load those
instead.  

When I tried this with C++ generation, it took **4 minutes** to compile all of
the C++ generated for `clojure.core` into a C++20 module with a backing shared
library. It then took **300ms** to load at runtime, which dropped the start time
from **12 seconds** to **300ms**. That AOT compilation cost was huge, but the gain was
also big.  

With IR generation, we can also generate object files. Amazingly, it can be done
within the same **2 seconds** used to compile `clojure.core` in the first place.
When loading that object file at runtime, jank can now start up in **150ms**. So, we
spend a fraction of the time actually compiling the code and even less time
loading it. Overall, for startup performance, LLVM IR has been a huge win. This
is exactly what I wanted and I'm very pleased with the results.  

<figure>
  <object type="image/svg+xml" data="https://jank-lang.org/img/blog/2024-11-29-llvm-ir/startup-time.plot.svg" width="50%">
    <img src="https://jank-lang.org/img/blog/2024-11-29-llvm-ir/startup-time.plot.svg" width="150%"></img>
  </object>
</figure>

Note, when all of this is baked into the executable AOT, startup time is around
**50ms**. jank doesn't support AOT compilation of full programs yet, but I've
manually added the object files to jank's CMake build in order to test this out.
Once we do have AOT compilation to binaries, we can also add direct linking,
link-time optimizations (LTO), etc. and drop these numbers down even further.  

### Runtime performance
Runtime performance will be negatively impacted by IR generation, at least to
start. The C++ code jank used to generate was quite optimized. I was taking
advantage of various C++ features, like function overloading, type inference,
and easy (yet ambiguous) unboxing of numerical values. With IR gen, we need to
do all of those manually, rather than rely on a C++ compiler to help. This will
take more work, but it also allows us to tailor the optimizations to best fit jank.  

I'm not ready to report any benchmark results for runtime performance
differences yet, since I don't think measuring the initial IR generation against
the previous C++ generation is a good usage of time. Optimizing IR can happen as
we go, without breaking any ABI compatibility. I'm more focused on getting
jank released right now.  

## Build system and portability improvements
Apart from working on LLVM IR generation the past couple of months, I've put a
fair amount of time into improving jank's builds system and dependency
management. In particular, vcpkg has been removed entirely. I was using vcpkg to
bring in some C and C++ source dependencies, but some of them regularly fail to
build from source on very normal setups and vcpkg on its own causes issues with
build systems such as Nix. Altogether, it's well known that the build system and
dependency tooling for C and C++ is terrible. While I aim for jank to improve
that, in its own way, we still need to suffer through it for the compiler
itself.  

In order to remove vcpkg, I had to address all of the dependencies it was
pulling in.  

* bdwgc (Boehm GC) requires compilation
  * Added a submodule and hooked into CMake
  * [Required a PR for CMake compatibility](https://github.com/ivmai/bdwgc/pull/675)
* fmt requires compilation
  * Added a submodule and hooked into CMake
* libzipp requires compilation
  * Added a submodule and hooked into CMake
* immer is header-only
  * Added a submodule
* magic_enum is header-only
  * Added a submodule
* cli11 is header-only
  * Added a submodule
* doctest is in all major package repos
* boost is in all major package repos
  * `boost::preprocessor` isn't found by CMake on Ubuntu, but it's there
    * Doesn't exist in brew's package, though
    * Had to add as a submodule
  * Causes Clang to crash while building `incremental.pch`

All in all, this required **seven new git submodules**. Even something as
commonplace as boost led to dependency issues across various platforms. I did
all of my testing on Ubuntu, Arch, NixOS, and macOS. Once I had all of those
submodules, I still ran into some issues with Clang hard crashing while trying
to compile an incremental pre-compiled header (PCH) with boost. This was the
final straw with PCHs for me.  

### Pre-compiled headers  
jank started out with PCHs from the beginning. I was concerned about
compile-times and I knew that many source files would need access to the whole
object model in order to compile. While this remains true, I didn't expect
that PCHs would be such a headache when JIT compiling C++. There have been a
handful of Cling bugs and then Clang bugs related to loading PCHs into the
incremental C++ environment. I've spent entire days compiling Clang/LLVM while
bisecting in order to find root causes.
[Haruki](https://github.com/jank-lang/jank/pull/94) has been so close to building
jank on Nix but has been running into issues when compiling the incremental PCH.
This has been a long time coming.  

After removing the PCHs, fixing all source files to include what they need,
refactoring some heavy headers so they can be used less often, and running some
tooling to further clean things up, we can wipe our hands of all of that. jank
does still need to JIT compile C++ code, but it can do so using both a C API and
a C++ API. Devs using jank can include what they need.  

Previously, just loading the incremental PCH with all of jank's headers took a
whopping 2.5 seconds every start up and we always paid that cost. Now, better
following the (intended) nature of C++, we can pay for what we use.  

### Community update  
I have not been the only one working on jank. The past couple of months, my
newest mentee through the [SciCloj mentorship program](https://scicloj.github.io/docs/community/groups/open-source-mentoring/),
[Monty Bichouna](https://github.com/stmonty), has wrapped up Unicode lexing support. This
allows jank the important ability to properly represent Unicode symbols and
keywords. Monty's work builds on Saket's recent work to add Unicode support for
character objects.  

To further jank's interop story, [Saket Patel](https://github.com/Samy-33) also
recently merged some changes which allow jank to accept include paths, linker
paths, and linked shared libraries. This means that you can now include other C
and C++ libraries from your JIT compiled bridge code and have the JIT linker
resolve those symbols in your libraries. In other words,
**it's now possible to use jank to wrap arbitrary C and C++ libraries**.
Saket took this further by adding an opaque pointer wrapper object which can
store any non-owned native pointer to be passed through any jank function and
stored in any jank data structure. Each of these is a small step toward a much
richer interop story. Saket also added persistent history to jank's CLI REPL and
improved its usability by hooking into LLVM's line editing capabilities.  

[Jianling Zhong](https://github.com/jianlingzhong) added support for ratio objects in
jank, including the whole polymorphic math treatment necessary for them. He also
implemented jank's delay object and corresponding `clojure.core/delay` macro as
well as jank's repeat object, which backs the `clojure.core/repeat` function.  

Finally, [Paula Gearon](https://github.com/quoll) has been making excellent
progress on a sister project to jank,
[clojure.core-test](https://github.com/jank-lang/clojure.core-test), which is a
cross-dialect test suite for all of `clojure.core`. Ultimately, my goal with
this is to aid Clojure dialect developers by providing a thorough test suite for
Clojure's core functions. By being able to run and pass this suite, my
confidence in jank will be strong. I'm sure other dialect developers will feel
similarly.  

### What's next?  
I need to fix a couple remaining bugs with the LLVM IR generation and then
implement IR generation for `try` nodes, in the next couple of weeks. After
that, the next big goal is error reporting. This is an exciting task to tackle,
since the impact of it is going to feel so rewarding. I have been suffering
jank's terrible error messages for years. Even worse, we as an industry have
been suffering terrible error messages for decades. There's been some exciting
progress in [Elm](https://elm-lang.org/news/compiler-errors-for-humans),
[Rust](https://blog.rust-lang.org/2016/08/10/Shape-of-errors-to-come.html), etc
for improving the way errors are reported, providing
actionable feedback, and including sufficient context to make errors less
cryptic. I don't think that Clojure does well in this area, currently, and I aim
to raise the bar.  

If that sounds interesting, stay tuned for my next update!  <br>

---

## Kushi: Jeremiah Coyle  
Q3 2024 Report No. 2 (final), Published Nov. 30, 2024   

### Q3 Milestones  
Thanks to the funding from Clojurists Together, the Q3 development of Kushi pursued the following 3 milestones:  
1. Finishing the new css transpilation API.  
2. Reimplementing the build system for enhanced performance.  
3. A reimagined theming system.  


### Progress  

#### Milestone #1: Finishing the new css transpilation API.  
- **Goals** 
  1. Solidify the API design and implementation of Kushi's CSS transpilation functionality.  
  2. Incorporate the use of lightingcss for CSS transformation (older browsers) and minification.  
  3. Refactor existing public functions for injecting stylesheets, google fonts, etc.  
- **Progress:** Complete on branch `new-build-system`. The majority of the time spent working on Kushi in the first half of the funding period was focused on implementing a new CSS transpilation pipeline. An updated set of public macros and supporting functions was designed and implemented around this. A broad test suite was written, which consists of 7 tests containing 54 assertions. Additional refinement of the implementation was done during the course of realizing milestone #2. Updated documentation of this API  is reflected in the `README.md` on this branch.  
- **Next steps:** Continue to test this new API by upgrading existing UI work (such as Kushi's interactive documentation site) that uses the current (soon to be previous) API, then adjust and refine implementation details as necessary.   

#### Milestone #2: Reimplementing the build system for enhanced performance.  
- **Goal**: Reimplement the build system for performance, and eliminate the use of side-effecting macros.  
- **Progress:** Complete on branch `new-build-system`  
- **Next steps:**  
  - Support the use of the new version of the `sx` macro.  
  - Adopt new conventions to Kushi documentation site source.  
  - Merge this work into main branch.   

#### Milestone #3: A reimagined theming system.  
- **Goal**: Based on learnings from using Kushi to build a lot of production UI over that last 2-3 years, redesign and implement a new theming system. This will involve harmonizing 3 principled subsystems:  
  1. Design tokens (a global system of CSS custom properties).  
  2. Utility classes.  
  3. Component-level data-attribute conventions.  
- **Progress:** 80% complete implementation on branch `oklch`.  
- **Next steps:** .  
  - Refine syntactical conventions of `data-` attributes  
  - Reconcile new (oklch) and legacy (hsl) color system, with realistic backwards compatibility  
  - After merging `new-build-system` branch into main branch, adopt new conventions to Kushi documentation site source.  
  - Merget this work into main branch  <br>

  ---

## Malli: Ambrose Bonnaire-Sergeant  
Q3 2024 Report No. 2, Published Nov. 30, 2024 

This is the second report of three in the project to extend Malli with constraints.

[Report 1](https://www.clojuriststogether.org/news/sept.-and-oct.-2024-short-term-project-updates/#malli-ambrose-bonnaire-sergeant)

## Background  
In this project, I proposed to extend the runtime verification library
Malli with constraints with the goal of making the library more expressive
and powerful.  

With this addition, schemas can be extended with extra invariants (constraints) that must be
satisfied for the schema to be satisfied. Anyone can add a constraint to a schema.
Crucially, these extensions should work as seamlessly as if the author of the schema
added it themselves.  
 
## Progress  

I finished my [prototype](https://github.com/frenchy64/malli/pull/20/files) porting existing `:min`/`:max` schema properties to a new "constraint" framework.
This seems like a useful framework in general but I'm not fully convinced it's the simplest solution
to having more expressive `:map` keyset constraints.  

I spent the rest of the month iterating and reviewing various design decisions.  

First I learnt how cljs bundle size works, and [refactored](https://github.com/frenchy64/malli/pull/21/files)
the constraint to live in `malli.core` while still being removable from the cljs bundle. This simplified a
few things, but at this point I reviewed my notes and started pulling apart the solution by
the problems they solved.  

This constraints project was inspired as a "big" solution to multiple problems:   
- `:map` keyset constraints are not expressive enough, forcing use of `:fn` schemas   
- `:and` and `:not` generators are not reliable enough for complex keyset constraints  
- humanization for `:not`, `:and` and `:or` errors are too confusing for complex keyset constraints  

If all of these problems were solved, we could possibly avoid changing `:map` constraints
at all and just add one new schema `[:contains k]` that tests `#(contains? % k)`.  
A schema like this could be used for a map that either has `:secret` or both `:user` and `:pass`:  

```clojure
[:and [:map
       [:secret {:optional true} :string]
       [:user {:optional true} :string]
       [:pass {:optional true} :string]]
      [:or
       [:and
        [:contains :secret]
        [:not [:contains :user]]
        [:not [:contains :password]]]
       [:and 
        [:not [:contains :secret]]
        [:contains :user]
        [:contains :pass]]]]
```

My thinking is, if we can make `m/explain` and `mg/generate` work with this kind of schema,
we could reapply those insights to other schema transformations like OpenAPI or JSON Schema
and perhaps move away from monolithic schemas like `:map` which accumulate features (`::m/default`,
`:min`, `:max`, `:optional`) and instead decomplect their features into independent repurposable
schemas that can be combined with composite schemas like `:and` and `:or` without compromising
performance or expressivity.  

My [prototype](https://github.com/frenchy64/malli/pull/23/files) for this idea introduces
a new namespace `malli.solver` that transforms schemas into descriptions of all the values they
represent. By rewriting the `:and` generator to propagate extra information to the first
schema conjunct from the other conjuncts, we could, for example have a schema like
`[:and :string [:min-count 10]]` generate identically to `[:string {:min 10}]` by utilizing
the extra information in the `:string` generator. Perhaps the latter could even `m/deref` to the former,
which starts to look very similar to my original "constraint" solution where schemas contain
other schemas at the same "level", without extra machinery.  

Here's a list of some of the directions I've worked on in the last few months:  
- [min/max constraints](https://github.com/frenchy64/malli/pull/20/files)  
  - finish constraints for schemas supporting `:min`/`:max` properties:  
    - `:int`, `:double`, `:float`, `:string`, `:vector, `:sequential`, `:seqable`, `:set`  
- [min/max constraints by default](https://github.com/frenchy64/malli/pull/21/files)  
  - like previous work, but move constraints to `malli.core`  
  - can disable constraints via system property to preserve cljs bundle size  
- opened issues  
  - [`:seqable` generates nil when :min is greater than 0](https://github.com/metosin/malli/issues/1121)  
  - [`:float` missing humanizer](https://github.com/metosin/malli/issues/1122)  
  - [Double generator fails if `{:max (- Double/MAX_VALUE)}`](https://github.com/metosin/malli/issues/1128)  
  - [`[:= ##NaN]` rejects `##NaN` yet generates it](https://github.com/metosin/malli/issues/1129)  
  - [Opaque `such-that` error when using `##NaN` as bound](https://github.com/metosin/malli/issues/1130)  
  - [`:float` accepts doubles but never generates them](https://github.com/metosin/malli/issues/1132)  
  - [Opaque assertion errors with infinite bounds](https://github.com/metosin/malli/issues/1133)  
- merged pr's  
  - [Require generator for `:gen/fmap`](https://github.com/metosin/malli/pull/1120)  
  - [only assume string interning in cljs](https://github.com/metosin/malli/pull/1125)  
- wip  
  - [reliable `:and` generator](https://github.com/frenchy64/malli/pull/23/files)  
    - includes new namespace `malli.solver` which turns schemas in to maps describing values they represent
    - includes three demonstrated usages:  
      - `malli.generator`: new `:and` generator that never guesses  
        - e.g., these always succeed to generate values  
          - `[:and [:>= 2] [:<= 2]]`  
          - `[:and [:<= 3] [:fn {:gen/schema :int} int?]]`  
        - better error message for unsolvable schemas:  
          - e.g., `[:and :int [:>= 1.5] [:<= 1.5]]` => `Exception: :malli.generator/unsatisfiable-schema`  
      - `malli.optimize`: faster version of `m/validate`  
        - e.g., checks `[:map [:a :int] [::m/default [:map-of :int :int]]]` in single pass, checks `map?` once
      - `malli.simplify`: simplifies schemas  
        - e.g., `[:and number? [:<= 10] [:<= 20] [:<= 30]]` => [:and number? [:<= 10]])  
    - includes new schema `[:contains k]` that checks if `:k` key exists  
      - next: connect with generator to reliably generate schemas like:  
        - `[:and [:map] [:or [:contains :secret] [:and [:contains :user] [:contains :password]]]]`  
      - next: humanizer for `[:not [:contains k]]` (pr below)  
  - [rewrite `malli.generator` and fix bugs](https://github.com/frenchy64/malli/pull/26)  
    - ~20% shorter  
- proposed pr's  
  - [`:not` humanizer](https://github.com/metosin/malli/pull/1138)  
    - relevant so `[:not [:contains k]]` can be explained  

## Next  
I'm going to see if [decomplecting the constraints solution](https://github.com/frenchy64/malli/pull/23/files)
is actually usable in practice and propose various smaller improvements to malli based on my experience so far.  <br>

---

## SciCloj: Daniel Slutsky  
Q3 2024 Report No. 2, Published Nov. 3, 2024 


The [Clojurists Together](https://www.clojuriststogether.org/) organisation has decided [to sponsor](https://www.clojuriststogether.org/news/q3-2024-funding-announcement/) Scicloj community building for Q3 2024, as a project by Daniel Slutsky. This is the second time the project is selected this year. Here is Daniel's update for October. Comments and ideas would help. :pray: 

## Update for October 2024  
[Scicloj](https://scicloj.github.io/) is a Clojure group developing a stack of tools and libraries for data science. Alongside the technical challenges, community building has been an essential part of its efforts since the beginning of 2019. Our current main community-oriented goal is making the existing data-science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on example-based documentation, easy setup, and recommended workflows for common tasks.  

All these, and the tools to support them, grow organically, driven by real-world use cases.  

I serve as a community organizer at Scicloj, and this project was accepted for Clojurists Together funding in 2024 Q1 & Q3. I also receive regular funding through Github Sponsors, mostly from Nubank.  

In this post, I am reporting on my involvement during October 2024, as well as the proposed goals for October.  I had 57 meetings during September. Most of them were one-on-one meetings for open-source mentoring or similar contexts.  All the projects mentioned below are done in collaboration with others. I will mention at least a few of the people involved. For consistency, I use people's github handles when mentioning their work on the projects, and their full names when mentioning public talks.  

## October 2024 highlights  

### [Scicloj open-source mentoring](https://scicloj.github.io/docs/community/groups/open-source-mentoring/)
This month, we contined working with mentees under the open-source mentoring program.  

@generatme, @phronmophobic, @jeaye, and myself have been active as mentors this month. Since the program's beginning at the middle of August, 62 people have applied -- 15 of them during the last month. 39 are still actively exploring various topics, and out of them, 15 have already made important contributions.  

One notable change happened during the last few months: we started working with a few mentees who are completely new to Clojure, some even to programming. This is part of Scicloj's gradual process of opening up to broader audiences.  

Some of the recent experiences and insights on this project were discussed in our recent [video report](https://www.youtube.com/watch?v=STnFMpIZlkk) (2024-10-25).  

### [Noj](https://scicloj.github.io/noj/)  
The Noj library is the entry point to data science with Clojure, collecting a stack of relevant libraries. This month, we worked towards its release into Beta stage, which is almost complete.  

At [the Zulip chat](https://scicloj.github.io/docs/community/chat/), we had quite a few insightful discussions with important feedback by community members about the scope and the organization of the project.  

Most of our efforts in this project have been into writing additional tutorials. A broad group of people are working on these, and a few promosing tutorials are currently in draft stage. See some details below in the Tutorials section.  

@behrica has made additional improvements to the automation and whole workflow of Noj in Github Pages.  

I worked on clarifying many details and improving the main documentation pages.  

### [Tableplot](https://scicloj.github.io/tableplot/) (previously called Hanamicloth)  
Towards reaching Beta stage, our acively-developed plotting library recieved a new name: Tableplot.  

I continued working on extending its features, adding flexibility to the way data can be specified throughout the pipelines, and integrating [metamorph.ml](https://github.com/scicloj/metamorph.ml) to allow for a more flexible smoothing functionality, where the user can specify the model details and the design matrix.  

### Composing [Fastmath](https://github.com/generateme/fastmath) with [Tablecloth](https://scicloj.github.io/tablecloth)  
During this month, a few substantial design discussions took place in the Zulip chat, with quite a few community members helping with their insights. One of the important realizations was that we should integrate Fastmath (the math library) into Tablecloth (the user-friendly table-processing library). This will allow for better composability, ergonomics, and performance in various cases where data processing meets math and statistics.  

### [Scittle](https://github.com/babashka/scittle) [Emmy-viewers](https://github.com/mentat-collective/emmy-viewers) plugin  
Recently, @reedho has made some progress in the Scittle plugin that supports Emmy-viewers data visualizations. I was helping in testing and figuring out some of the details. This progress is expected to extend the reach of Emmy-viewers to a broader set of use cases and integrate it better into Scicloj workflows.  

### [Clay](https://scicloj.github.io/clay/)  
Clay, the REPL-friendly notebook and data visualation tool, recieved a few new features:  
- initial Emmy-viewers support  
- support for nesting of special visualization kinds  
- support for live-reload on file save by @whatacold  

Additionally, @a13 and I are working on improving the process of reading Clojure code, building on previous work by @timothypratley at the [read-kinds](https://github.com/scicloj/read-kinds) project.  

### [Kindly-advice](https://scicloj.github.io/kindly-noted/kindly_advice)  
Kindly-advice is a small library that helps tools know how certain values should be visualized.  

Following the developments with Emmy-viewers, it can now recognize these automatically, so that tools can handle them appropriately.  

### [Kindly-render](https://github.com/scicloj/kindly-render)  
Kindly-render is a tool-agnosic implementation of the [Kindly](https://scicloj.github.io/kindly/) standard.  

During October, @timothypratley and @kpassapk kept working on this project. I started exploring its intergation into Clay.  

### Tutorials  
Documenting and demonstrating the use of the Scicloj stack is one of the main goals at the moment, and quite a few of us are working on various tutorials. This is often a slow process that involved introspection and discussion of the recommended ways to explain certain notions and perform certain tasks.  

I was involved in writing some tutorials and in reviewing a few others.  

A few of the current drafts people have been working on can already be shared:  
- [intro to probability and statistics](https://mavbozo.github.io/clj-probstat-tutorial/clay/) by @mavbozo  
- [intro to linear algebra with Fastmath](https://scicloj.github.io/noj/noj_book.linear_algebra_intro.html) by @radovanne  
- [2d and 3d geometry with Fastmath](https://scicloj.github.io/noj/noj_book.fastmath_vector_geom2d3d.html) by @radovanne and @Epidiah  
- [intro to datavis with Apache Echarts](https://scicloj.github.io/noj/noj_book.echarts) by @zuzhi and @whatacold  
- [analysing Chicago bike times](https://scicloj.github.io/noj/noj_book.chicago_bike_times.html) by myself  
- [Bayesian statistics with PyMC](https://scicloj.github.io/clojure-data-tutorials/projects/stats/pymc/intro.html) by myself  

### Website  
This month has been a usual month in terms of website maintenance.  

### real-world-data group  
The [real-world-data group](https://scicloj.github.io/docs/community/groups/real-world-data/) is a space for Clojure data practitioners to share their experiences. During October, the group had two meetings. We decided to share some parts of the recordings publicly:  
- Oct 4th 2024 - meeting 15:  
  - [Kyle Passarelli: OAuth2 with Clojure and Temporal](https://www.youtube.com/watch?v=mmOh5fYkX7Q)  
  - [Adham Omram: Experience Conducting a Clojure Training](https://www.youtube.com/watch?v=G1vpz_43YpI)  
- Oct 16th 2024 - meeting 16:  
  - [Jarkko Saltiola: tools for pipelines, bb-glitchtip](https://www.youtube.com/watch?v=nC86hEglyLQ)  

### Scicloj weekly catchup  
During October, we tried the approach of weekly group meetings for the open-source-mentoring program, in addition to the small and 1-1 meetings.  

We had four group meetings of this kind. A few people have shared their work, and we explored some topics as a group. Eventually, we realized that the timing and format were not optimal for most people, so this series is currently on hold.  

### Linear Algebra meetings  
A few of use are working on tutorials related to linear algebra and vector processing, and have started meeting weekly on these topics. We had four meetings of this kind.  

### Clojure Conj  
Eventually, we organized only one talk run before the Clojure Conj conference. This was an early run of the talk by Thomas Clark. The actual talk has already been published [online](https://www.youtube.com/watch?v=_D5d6Ls6pBw). It is a fantastic overview of the Clojure stack for scientific computing.  

On my side, I helped a little bit in the preparations for the talk.  

## November 2024 goals  

### Noj  
- Annouce Beta stage.  
- Continue the current efforts on documenting the libraries.  

### Fastmath  
- Continue the documetation effort.  

### Tablecloth  
- Start working on drafts integrating Fastmath-related functionality.  

### Tableplot  
- Annouce Beta stage after a minor changes.  
- Keep extending and documenting the library.  

### Tooling  
- Return to the work on kindly-render.  
- Explore improving the support of various tools for scientific Clojure. Most importantly: VSCode, Quarto, and Jupyter.  
- Continue Clay maintenance.  <br>  

---

## Standard Clojure Style: Chris Oakman  
Q3 2024 Report No. 2 (final), Published Dec. 3, 2024 

> Standard Clojure Style is a project to create a "follows simple rules, no config, runs everywhere" formatter for Clojure code.

### tl;dr

* [Clojure/conj talk] was well received
* [Standard Clojure Style is available in Lua] 🌙
* several bugs found and fixed - thank you testers!
* [try it online here](https://tinyurl.com/43abayj2)

### Update

- I gave a 10-minute introduction and demo of Standard Clojure Style at Clojure/conj 2024
  - you can [watch the talk online here](https://www.youtube.com/watch?v=VhjxvEabOX0)
  - the talk and project were well-received
  - thank you to everyone who said Hello and shared feedback 😁

- Standard Clojure Style is available as a [single-file Lua library](https://github.com/oakmac/standard-clojure-style-lua)
  - hopefully this opens the door for more editor integrations
  - I am seeking an aspirational Neovim user to help make a Standard Clojure Style plugin 🤓

- As of [v0.18.0], Standard Clojure Style is ready for most codebases
  - you can [try it online here](https://tinyurl.com/43abayj2) (no purchase necessary 😜)
  - Give it a try on your codebase!

- Thank you to all the testers who ran Standard Clojure Style on your codebase and reported bugs 🙌
  - If you want to help test, please see the instructions [in the README]

### Next Up

- road to 1.0.0
  - I want to stabilize the algorithm and release a v1.0.0
  - the target goal for this is in the next few months
  - here is the [punchlist of issues] currently blocking that release

- documentation and website are still needed
  - I would like to create a website for the project in order to socialize the idea
    and be a reference for teams who are deciding to to adopt a formatter tool

### Thank you!

Thank you to Clojurists Together for the funding and everyone who has contributed
kind words and encouragement for this project. Your support is greatly appreciated ♥️

[Clojure/conj talk]:https://www.youtube.com/watch?v=VhjxvEabOX0
[Standard Clojure Style is available in Lua]:https://github.com/oakmac/standard-clojure-style-lua
[in the README]:https://github.com/oakmac/standard-clojure-style-js
[v0.18.0]:https://www.npmjs.com/package/@chrisoakman/standard-clojure-style
[punchlist of issues]:https://github.com/oakmac/standard-clojure-style-js/issues?q=is%3Aissue+is%3Aopen+label%3A%22v1+blocker%22
