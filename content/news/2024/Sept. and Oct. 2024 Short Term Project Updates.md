---
title: "Sept. and Oct. 2024 Short-Term Project Updates"
date: 2024-10-27T14:00:00+12:00
author: Kathy Davis
summary: "clj-Nix, Clojure Goes Fast, Jank, Kushi, Malli, SciCloj, Standard Clojure Style"
draft: True


---

We've got our first set of reports from developers working on short-term projects funded in Q3 2024. You'll find a brief description of each project at the top of the page to provide some context -- followed by current project updates.

[### clj-Nix: José Luis Lafuente]
- Develop an alternative builder for Nix that uses Babashka / SCI instead of Bash. It provides a way
for constructing complex Nix derivations using Babashka/SCI entirely, eliminating the need to
write any Bash code. 
- Also, will be adding a Babashka writer to nixpkgs. Nixpkgs supports creating self-contained scripts, called "nix writers." Traditionally written in Bash, recent versions of nixpkgs include the ability to write these scripts in Python, Ruby, or Rust.   

### Clojure Goes Fast: Oleksandr Yakushev  
- Create a new web application for clj-async-profiler that will allow users to host and share the generated flamegraphs. At the moment, even though flamegraphs are just self-contained HTML files, sending them to
somebody is a chore. The new service can make this much easier and offer extra features
like saving and sharing dynamic transforms on the fly. Additionally, I'd like to focus on the UI
side of clj-async-profiler - add new dynamic transforms, improve aesthetics and the UX.  
- For clj-java-decompiler, expand its preprocessing abilities to present clearer results to the user and integrate it more tightly with established Clojure IDEs like CIDER and Calva, which requires some groundwork.
- Adapting and updating benchmarking content in the Clojure Goes Fast knowledge base (https://clojuregoes-fast.com/kb/).  

### Jank: Jeaye  Wilkerson  
Jank feels like Clojure now, with 92% syntax parity and nearly 40% clojure.core parity. But it only feels like Clojure to me because none of you are using it yet. My top priority is to change that.  I'll be working on building out jank's nREPL server, which involves implementing bencode support, `clojure.test`, improving native interop, supporting pre-compiled binary modules, and ultimately adding AOT compilation support.  

### Kushi: Jeremiah Coyle  
Continue development of Kushi, a foundation for building web UI with ClojureScript. Work this funding cycle will focus on finishing the new css transpilation pipeline, significant build system performance upgrades, and implementing a reimagined theming system.  

### Malli: Ambrose Bonnaire-Sergeant  
This project (Constraints and Humanization) aims to drastically improve the expressivity of Malli schemas to help address current user feedback and enable future extensions. The basic idea is to add a constraint language to each schema to express fine-grained invariants and then make this constraint language compatible with validators/explainers/generators/etc so that Malli users can write high-level, precise schemas without resorting to partial workarounds. See prototype here: https://github.com/frenchy64/malli/pull/12  

### SciCloj: Daniel Slutsky  
Scicloj is a Clojure group developing a stack of tools & libraries for data science.
Alongside the technical challenges, community building has been an essential part of its
efforts since the beginning of 2019. Our community-oriented goal is making the existing data-science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on
example-based documentation, easy setup, and recommended workflows for common tasks. All these, and the tools to support them, grow organically, driven by real-world use-cases. See updates for progress on Q3 projects and documentation.  
 
### Standard Clojure Style:	Chris Oakman  
Continue work on Standard Clojure Style - which is a "no config, runs everywhere, follows simple rules" formatter for Clojure code. More information about the genesis of the project can be found on Issue #1:
https://github.com/oakmac/standard-clojure-style-js/issues/1  
<br>

---

## Project Updates: Sept. and Oct. 2024  <br>

---


## clj-Nix: José Luis Lafuente  
Q3 2024 Report No. 1, Published Oct. 13, 2024

In this first half of the funding round, I made progress on two fronts:  

### Babashka writer on nixpkgs  
I added a new writer, `writeBabashka`, to nixpkgs. It's already merged and
currently available on the
[nixpkgs and nixos unstable branch](https://nixpk.gs/pr-tracker.html?pr=343510).  

Documentation about this new helper function can be found here:
[writeBabashka on Noogle](https://noogle.dev/q?term=writeBabashka)  

As you can see in the documentation, there are two versions, `writeBabashka` and
`writeBabashkaBin`. Both produce the same output script. The only difference is
that the latter places the script within a `bin` subdirectory. That's a common
pattern in nixpkgs, for consistency with the convention of software packages
placing executables under `bin`.  

Something I still want to do is to create a repository with some examples about
how to use the Babashka writer.  

### Nix Derivation Builder with Babashka  
The build step of a Nix derivation is defined by a Bash script. I want to
provide an alternative builder written in Clojure (using Babashka).  

I have a working prototype, but the API is still under development and may
change in the future. You can find the initial version on the bbenv branch:
[clj-nix/extra-pkgs/bbenv](https://github.com/jlesquembre/clj-nix/tree/bb-env/extra-pkgs/bbenv)  

A pull request for early feedback is available here:
[clj-nix PR #147](https://github.com/jlesquembre/clj-nix/pull/147)  

Here's a glimpse of how it currently works. This example builds the
[GNU Hello Project](https://www.gnu.org/software/hello/):  

```nix
mkBabashkaDerivation {
  build = ./my/build.clj;
  deps-build = [ pkgs.gcc ];
}
```

```clojure
(require '[babashka.process :refer [shell]])

(defn build
  [{:keys [out src]}]
  (shell {:dir src} (format "./configure --prefix=%s" out))
  (shell {:dir src} "make install"))

(def version  "2.12.1");

(def pkg
  {:name "hello"
   :version version
   :src {:fetcher :fetchurl
         :url (format "mirror://gnu/hello/hello-%s.tar.gz" version)
         :hash "sha256-jZkUKv2SV28wsM18tCqNxoCZmLxdYH2Idh9RLibH2yA="}
   :build build})
```
<br>


----
## Clojure Goes Fast: Oleksandr Yakushev  
Q3 2024 Report No. 1, Published Sept.30, 2024  

I've spent the firt month of my Clojurists Together project polishing the user experience for clj-async-profiler. The profile viewer UI (the flamegraph renderer) received big improvements in navigation, ease of access, consistency, and overall look. As a bullet list:  
- Published one big release of clj-async-profiler (1.3.0) and two small releases (1.3.1 and 1.3.2). Most important changes:  
  - Completely redesigned collapsible sidebar.  
  - Better rendering performance and responsiveness.  
  - New custom on-hover tooltip.  
  - Fewer configuration options for better out-of-the-box experience.  
- Blogged about 1.3.0 release describing in detail and with demonstration what has changed: https://clojure-goes-fast.com/blog/clj-async-profiler-130/  
- Prepared multiple UI mockups for the flamegraph sharing website that I'm starting to work on in the second month.  <br>

---
## Jank: Jeaye  Wilkerson   
Q3 2024 Report No. 1, Published Oct. 14, 2024  

Hi everyone! It's been a few months since the last update and I'm excited to
outline what's been going on and what's upcoming for jank, the native Clojure
dialect. Many thanks to Clojurists Together and my Github sponsors for the
support. Let's get into it!  

### Heart of Clojure
In September, I flew from Seattle to Belgium to speak at Heart of Clojure. For
the talk, I wanted to dig deep into the jank details, so I created a walk-through
of implementing exception handling in jank. You can watch my talk [here](https://www.youtube.com/watch?v=5ejOkeNCbXY).  

### Announcement
Part of my Heart of Clojure talk was an announcement that, starting in January
2025, **I'll be quitting my job at EA to focus on jank full-time**. Two years ago, I
switched from full-time to part-time at EA in order to have more time for jank.
Now, with the momentum we have, the interest I've gathered, and the motivation
backing this huge effort, I'm taking things all the way.  

I don't have funding figured out yet, though. It's hard for companies to invest
in jank now when they're not using it, when it's not providing them value. So my
goal is to get jank out there and start creating value in the native Clojure
space. If using jank interests you and you want white glove support for
onboarding jank once it's released, reach out to me.  

### Mentoring
On top of working on jank full-time, next year, I have joined the
[SciCloj mentorship program](https://scicloj.github.io/docs/community/groups/open-source-mentoring/)
as a mentor and have two official mentees with whom I meet weekly (or at least
once every two weeks) in order to help them learn to be compiler hackers by
working on jank. This is in tandem with the other mentee I had prior to the
SciCloj program.  

What's so inspiring is that there were half a dozen interested people, who
either reached out to me directly or went through the application process, and
we had to pare down the list to just two for the sake of time. Each of those
folks wants to push jank forward and learn something along the way.  

### JIT compilation speeds
Now, jumping into the development work which has happened in the past few
months, it all starts with me looking into optimizing jank's startup time. You
might think this is a small issue, given that jank needs more development
tooling, improved error messages, better Clojure library support, etc. However,
this is the crux of the problem.  

jank is generating C++ from its AST right now. This has some great benefits,
particularly since jank's runtime is implemented in C++. It allows us to take
advantage of C++'s type inference, overloading, templates, and virtual dispatch,
whereas we'd have none of those things if we were generating LLVM IR, machine
code, or even C.  

However, JIT compiling C++ as our primary codegen comes with on big problem: C++
is one of the slowest to compile languages there is. As a concrete example, in
jank, `clojure.core` is about 4k (formatted) lines of jank code. This codegens
to around 80k (formatted) lines of C++ code. On my beefy desktop machine, it
takes 12 seconds to JIT compile all of that C++. This means that starting jank,
with no other dependencies than `clojure.core`, takes 12 seconds.  

To be fair, all of this disappears in AOT builds, where startup time is more
like 50ms. But starting a REPL is something we do every day. If it takes 12
seconds now, how long will it take when you start a REPL for your company's
large jank project? What if your machine is not as beefy? A brave user who
recently compiled jank for WSL reported that it took a minute to JIT compile
`clojure.core` for them.  

So, this leads us to look for solutions. jank is already using a pre-compiled
header to speed up JIT compilation. Before abandoning C++ codegen, I wanted to
explore how we could pre-compile modules like `clojure.core`, too. Very
pleasantly, the startup time improvements were great. jank went from 12 seconds
to 0.3 seconds to start up, when `clojure.core` is pre-compiled as a C++20
module and then loaded in as a shared library.

<figure>
  <object type="image/svg+xml" data="https://jank-lang.org/img/blog/2024-10-14-llvm-ir/pcm.plot.svg" width="30%">
    <img src="https://jank-lang.org/img/blog/2024-10-14-llvm-ir/pcm.plot.svg" width="30%"></img>
  </object>
</figure>

There's a catch, though. It takes 2 full minutes to AOT compile `clojure.core`
to a C++20 pre-compiled module. So, we're back to the same problem. jank could
compile all of your dependencies to pre-compiled modules, but it may take 30
minutes to do so, even on a reasonable machine. For non-dependency code, your
own source code, jank could use a compilation cache, but you'll still need to
pay the JIT compilation cost whenever you do a clean build, whenever you eval a
whole file from the REPL, etc.

Before digging deeper into this, I wanted to explore what things would look like
in a world where we don't codegen C++. 

### LLVM IR  
LLVM has support for JIT compiling its own intermediate representation (IR),
which is basically a high level assembly language. Compared to generating C++,
though, we run into some problems here:  
1. Calling into C++ is tough, since C++ uses name mangling and working C++ value
   types involves non-trivial IR  
2. We can't do things like instantiate C++ templates, since those don't exist
   in IR land  

So we need to work with jank at a lower level. As I was designing this, in my
brain, I realized that we just need a C API. jank has a C++ API, which is what
we're currently using, but if we had a C API then we could just call into that
from assembly. Heck, if we can just write out the C we want, translating that to
assembly (or IR) is generally pretty easy. That's what I did. I took an example
bit of Clojure code and I wrote out some equivalent C-ish code, using a made-up
API:  

### Clojure  
```clojure
(defn say-hi [who]
  (println "hi " who "!"))
```

### C  
```c
static jank_object_ptr const_1 = jank_create_string("hi ");   
static jank_object_ptr const_2 = jank_create_string("!");  

jank_object_ptr say_hi(jank_object_ptr who)  
{
  jank_object_ptr println_var = jank_var_intern("clojure.core", "println");  
  jank_object_ptr println = jank_deref(println_var);  
  return jank_call3(println, const_1, who, const_2);  
}  

static jank_object_ptr fn_1()  
{  
  jank_object_ptr say_hi_var = jank_var_intern("clojure.core", "say-hi");  
  jank_object_ptr say_hi_obj = jank_create_function1(&say_hi);  
  jank_var_bind_root(say_hi_var, say_hi_obj);  
  return say_hi_var;  
}  
```  

This was motivating. Furthermore, **after two weekends, I have the LLVM IR codegen almost entirely done!**  
The only thing missing is codegen for closures (functions with captures) and `try` expressions, since those involve some extra work. I'll give an example of how this looks, with exactly the IR we're generating, before
LLVM runs any optimization passes.   

### Clojure  
```clojure
(let [a 1
      b "meow"]
  (println b a))
```

### LLVM IR  
```llvm
; ModuleID = 'clojure.core-24'
source_filename = "clojure.core-24"  

; Each C function we reference gets declared.  
declare ptr @jank_create_integer(ptr)
declare ptr @jank_create_string(ptr)
declare ptr @jank_var_intern(ptr, ptr)
declare ptr @jank_deref(ptr)
declare ptr @jank_call2(ptr, ptr, ptr)

; All constants and vars are lifted into internal  
; globals and initialized once using a global ctor.
@int_1 = internal global ptr 0
@string_2025564121 = internal global ptr 0
@0 = private unnamed_addr constant [5 x i8] c"meow\00", align 1
@var_clojure.core_SLASH_println = internal global ptr 0
@string_4144411177 = internal global ptr 0
@1 = private unnamed_addr constant [13 x i8] c"clojure.core\00", align 1
@string_4052785392 = internal global ptr 0
@2 = private unnamed_addr constant [8 x i8] c"println\00", align 1  

; Our global ctor function. It boxes all our
; ints and strings while interning our vars.
define void @jank_global_init_23() {
entry:  
  %0 = call ptr @jank_create_integer(i64 1)
  store ptr %0, ptr @int_1, align 8
  %1 = call ptr @jank_create_string(ptr @0)
  store ptr %1, ptr @string_2025564121, align 8
  %2 = call ptr @jank_create_string(ptr @1)
  store ptr %2, ptr @string_4144411177, align 8
  %3 = call ptr @jank_create_string(ptr @2)
  store ptr %3, ptr @string_4052785392, align 8
  %4 = call ptr @jank_var_intern(ptr %2, ptr %3)
  store ptr %4, ptr @var_clojure.core_SLASH_println, align 8
  ret void  
}
  
; Our effecting fn which does the work of the actual code.
; Here, that just means derefing the println var and calling it.
define ptr @repl_fn_22() {
entry:
  %0 = load ptr, ptr @int_1, align 8
  %1 = load ptr, ptr @string_2025564121, align 8
  %2 = load ptr, ptr @var_clojure.core_SLASH_println, align 8
  %3 = call ptr @jank_deref(ptr %2)
  %4 = call ptr @jank_call2(ptr %3, ptr %1, ptr %0)
  ret ptr %4
}  
```  

There's still more to do before I can get some real numbers for how long it
takes to JIT compile LLVM IR, compared to C++. However, I'm very optimistic. By
using a C API, instead of our C++ API, handling codegen optimizations
like unboxing ends up being even more complex, but we also have even more power.  

### How this affects interop  
Currently, jank has two forms of native interop (one in each direction):  
1. A special `native/raw` form which allows embedding C++ within your jank code  
2. The ability to require a C++ as though it's a Clojure namespace, where that
   C++ code then uses jank's runtime to register types/functions  

When we're generating C++, a `native/raw` just gets code-generated right into
place. However, when we're generating IR, we can't sanely do that without
involving a C++ compiler. This means that `native/raw` will need to go away, to
move forward with IR. However, I think this may be a good move. If we buy into
the second form of interop more strongly, we can rely on actual native source
files to reach into the jank runtime and register their types/functions. Then,
in the actual jank code, everything feels like Clojure.  

This means that we still have a need for JIT compiling C++. Whenever you `require`
a module from your jank code, which is backed by a C++ file, that code is JIT
compiled. Generally, what the C++ code will do is register the necessary functions
into the jank runtime so that way you can then drive the rest of your program
with jank code. I think this is a happy medium, where we still have the full
power of C++ at our disposal, but all of our jank code will result in IR, which
will JIT compile much more quickly than C++.  

This means the answer to the question of C++ or IR is: **why not both?**  

### jank as THE native Clojure dialect  
There's another reason which leads me to explore LLVM IR within jank. While jank
is embracing modern C++, it doesn't need to be so tightly coupled to it. By
using just the C ABI as our runtime library, everything can talk to jank. You
could talk to jank from Ruby, Lua, Rust, and even Clojure JVM. Just as
importantly, jank can JIT compile any LLVM IR, which means any language which
compiles on the LLVM stack can then be JIT compiled into your jank program.  

Just as jank can load C++ files as required namespaces, seamlessly, so too could
it do the same for Rust, in the future. Furthermore, as the public interface for
jank becomes C, the internal representation and implementation can change
opaquely, which would also open the door for more Rust within the jank compiler.  

In short, *any native work you want to do in Clojure* should be suited for jank.
Your jank code can remain Clojure, but you can package C, C++, and later
languages like Rust inside your jank projects and require then from your jank
code. The jank compiler and runtime will handle JIT compilation and AOT
compilation for you.  

### Community update  
This has been a long update which hopefully created some more excitement for
jank's direction. I want to wrap up with what the community has been up to
recently, though, since that alone warrants celebration.  

### Characters, scientific notation, and to_code_string  
[Saket](https://github.com/Samy-33) has been improving jank's runtime character
objects, which he originally implemented, to be more efficient and support
Unicode. He also recently added scientific notation for floating point values,
as well as an extension of jank's object concept to support `to_code_string`,
which allows us to now implement `pr-str`.  

At this point, Saket has the most knowledge of jank's internals, aside from me,
so I've been giving him heftier tasks and he's been super helpful.  

### More robust escape sequences  
One of my SciCloj mentees, [Jianling](https://github.com/jianlingzhong),
recently merged support for all of the ASCII escape sequences for jank's
strings. Previously, we only had rudimentary support. Now he's working on
support for hexadecimal, octal, and arbitrary radix literals, to further jank's
syntax parity with Clojure.  

### Nix build  
We have a newcomer to jank, [Haruki](https://github.com/haruki7049), helping to
rework the build system and dependencies to allow for easy building with Nix!
There's a draft PR [here](https://github.com/jank-lang/jank/pull/94). I'm
excited for this, since I'm currently using NixOS and I need to do a lot of jank
dev in a distrobox for easy building. This will also help with stable CI builds
and ultimately getting jank into nixpkgs (the central package repo for Nix).  

### LLVM 19 support  
The last JIT hard crash fix in LLVM is being backported to the 19.x branch,
which means we should be able to start using Clang/LLVM binaries starting 19.2!
This is going to drastically simplify the developer experience and allow for
packaging jank using the system Clang/LLVM install. My
[backport ticket](https://github.com/llvm/llvm-project/issues/111068)
has been closed as complete, though [the PR](https://github.com/llvm/llvm-project/pull/111953)
into the 19.x branch is still open.  

### Summary  
More people are working on jank now than ever have; I expect this number to
keep growing in the coming year. I'll see you folks at the Conj and, after that,
in my next update during the holiday season, when I'll have some final numbers
comparing jank's startup times with LLVM IR vs C++, as well as some updates on
other things I've been tackling.<br>

---
## Kushi: Jeremiah Coyle  
Q3 2024 Report No. 1, Published Oct. 15, 2024  

### Q3 Milestones

Thanks to the funding from Clojurists Together, the Q3 development of Kushi is aimed at achieving the following 3 milestones:  
1. Finishing the new css transpilation API.  
2. Reimplementing the build system for enhanced performance.  
3. A reimagined theming system.  

### Progress  

### Milestone #1: Finishing the new css transpilation API.  
- **Goals**   
  1. Solidify the API design and implementation of Kushi's CSS transpilation functionality.  
  2. Incorporate the use of lightingcss for CSS transformation (older browsers) and minification.  
  3. Refactor existing public functions for injecting stylesheets, google fonts, etc.  
- **Progress:** Complete. The majority of the time spent working on Kushi in the first half of the funding period was focused on implementing a new CSS transpilation pipeline. An updated set of public macros and supporting functions was designed and implemented around this. A broad test suite was written, which consists of 7 tests containing 54 assertions.  
- **Next steps:** When the work on the new build system (milestone #2) is complete, test this new API by upgrading existing UI work (such as Kushi's interactive documentation site) that uses the current (soon to be previous) API, then adjust and refine implementation details as necessary.   

### Milestone #2: Reimplementing the build system for enhanced performance.  
- **Goal**: Reimplement the build system for performance, and eliminate the use of side-effecting macros.  
- **Progress:** 75% of the initial design, discovery, and experimentation phase is complete.   
- **Next steps:** I anticipate moving it into the implementation phase by the last week of October. Roughly half of the the remaining 6 weeks of the Q3 period will be spent building this out.  


### Milestone #3: A reimagined theming system.  
- **Goal**: Based on learnings from using Kushi to build a lot of production UI over that last 2-3 years, redesign and implement a new theming system. This will involve harmonizing 3 principled subsystems:  
  1. Design tokens (a global system of CSS custom properties).  
  2. Utility classes.  
  3. Component-level data-attribute conventions.   
- **Progress:** 90% of the initial design, discovery, and experimentation phase is complete.  

- **Next steps:** I am hoping that enough progress will be made on the build system so that I can focus Kushi dev time on this new theming system for the last 3 weeks of November.  

### Details  
All of the work related to Milestone #1 has been happening in [a sandbox repo called kushi-css](https://github.com/kushidesign/kushi-css). Additional in-depth detail and documentation around this work can be found [here](https://github.com/kushidesign/kushi-css/blob/main/doc/towards-kushi-v1.md). When all 3 of the above grated into the main kushi repo.<br>  

---
## Malli: Ambrose Bonnaire-Sergeant  
Q3 2024 Report No. 1, Published Oct. 18, 2024  

### Malli Constraints - Report 1

This is the first report of three in the project to extend Malli with constraints.

tldr; I gave a [talk](https://www.youtube.com/live/28S96Ms8WOc) and started [implementation](https://github.com/frenchy64/malli/pull/20/files) and reflect its successes and failures below.  

Thanks to Tommi (Malli lead dev) for working with me to propose the project, and the encouragement
I received from the Malli community and my friends.  

This is a long update that really helps me get my thoughts in order for such a complex project. Thanks for reading and feel free to email me any questions or comments.  

### Background  
In this project, I proposed to extend the runtime verification library
Malli with constraints with the goal of making the library more expressive
and powerful.  

With this addition, schemas can be extended with extra invariants (constraints) that must be
satisfied for the schema to be satisfied. Anyone can add a constraint to a schema.
Crucially, these extensions should work as seamlessly as if the author of the schema
added it themselves.  

Before the project started, I had completed an extensive prototype that generated many
ideas. The authors of Malli were interested in integrating these ideas in Malli and this
project aims to have their final forms fit with Malli in collaboration with the Malli devs.  

### Talk  
It had been several months since I had built the prototype of Malli constraints, so
I gave a talk at [Madison Clojure](https://madclj.com) which I live-streamed.
You can watch it [here](https://www.youtube.com/live/28S96Ms8WOc).  

It was well received and very enjoyable to give. I'm thankful to the attendees for their
engagement and encouragement, and for checking in on my progress during the project.  

In the talk, I motivate the need for more expressive yet reliable schemas, propose
a solution in the form of constraints, and sketch some of the design ideas for
making it extensible. I gave this talk a few days before the project started and I hit the ground running.  

### Design Goals (Constraints)  
I've had many fruitful interactions with the Malli community its developers
and I have a good idea what the project values. If this constraints project is to be
successful, it must check all the boxes as if it came straight from
the brain of Tommi (well, that's my goal, Tommi is busy and has enjoyably
high standards). Given how deeply this project aims to integrate with
Malli, that attitude has definitely helped prune ideas (when was the
last time `:string` or `:int` changed it's implementation? We're
doing exactly that here).  

There is a mundane but critical issue that Malli has been steadily
increasing its CLJS bundle size. I decided early on that my design
for constraints would be opt-in, so that the Malli devs can decide
whether its worth including by default. If adding constraints irreversibly
increased the CLJS bundle size to the point that Malli devs started worrying,
this project would be in jeopardy.  

My prototype made constraints an entirely custom construct, unrelated
to the rest of Malli. It's helpful to look at a related project under
similar circumstances: extending Malli to add sequence expressions
like `:alt` and `:*`. Sequence expressions
are a different abstraction than schemas, and yet they share many implementation
concepts, both even implementing `m/Schema`. Sequence expressions then
implement additional protocols for their characterizing operations.
I wanted to take inspiration from this: constraints should be like
schemas in their overlapping concepts, introducing new abstractions
only for differences.  

I would like the constraints framework be merged incrementally, starting
with very simple constraints on the count and size of collections
and numbers. However, the framework itself should be fully realized
and be able to support much more complex constraints.  

The last few goals are easy to list, but maximizing them all simultaneously
might be difficult as seem in deep tension.
Constraints should be fast, extensible, and robust.
It should be possible to achieve equivalent performance to a "hand-coded"
implementation of the constraint. It should be possible to implement as
many kinds of constraints as possible without having to change the constraint
framework itself. Constraints should have predictable, reliable semantics
that are congruent to the rest of Malli's design.  

**Summary of goals:**  
- control bundle size  
- backwards compatibility  
- equivalent performance  
  - in tension with extensibility and compilation
- extensibility  
  - provide expressivity and power of primitives
- robustness  
  - think about Malli's unwritten rules (properties round-trip)  
- first iteration should be fully realized but minimal  

### Development  
[Diff](https://github.com/frenchy64/malli/pull/20/files)
[Branch](https://github.com/frenchy64/malli/tree/min-max-constraints)

My first attempt at an idiomatic implementation of schema constraints
was completed in the first half of September. Since then it's been
hammock-time pondering the results. I have surprisingly strong feelings
in both directions.  

I go into more detail below.  

### Opt-in for CLJS Bundle size  
I was able to separate the constraints framework from
`malli.core` so it can be opt-in to control CLJS bundle size.
The main code path adds several functions and a couple of protocols,
but the constraints themselves are loaded separately via an atom
that also lives in `malli.core`. This atom `m/constraint-extensions`
can be empty which will disable constraints, kicking in a backwards-compatibility mode for schemas
that migrated their custom properties to constraints (like `:string`'s `:min` and `:max`).  

I went back and forth about whether to use a single global atom or
to add some internal mutable state to each schema that could be upgraded
to support constraints. In this implementation, I decided a global atom was
more appropriate for two reasons. First, registries can hold multiple copies
of the same schema but only one will "win". We don't want situations where
we extend a schema with constraints and then it gets "shadowed" by another
instance of the same schema, since that is functionally equivalent in all
other situations. Second, we already have an established way of extending schemas
to new operations in the form of a multimethod dispatching on `m/type`. I wanted
a similar experience where an entire class of extension is self-contained in one
global mutable variable.  

Extending schemas with constraints is subtly different to many other kinds of
schema extensions, in that it is much finer grained. `defmulti` is appropriate
for defining generators or JSON Schema transformers where a schema extension
maps precisely to a function (`defmethod`), but extending constraints
is more like having a separate atom for each schema containing a map where
each field can themselves be extended with namespaced keywords. A single global
atom containing a map from schemas to constraint configuration became the natural
choice (an atom of atoms is rarely a good idea).  

Ultimately the constraint implementation is activated by calling
`(malli.constraint/activate-base-constraints!)`.  

### Reusing existing abstractions  
Constraints implement `m/Schema` and their `m/IntoSchema`'s live in the registry.
They differ from schemas in how they are constructed and print
(it depends which schema the constraint is attached to) so they have their own
equivalent to `m/schema` in `malli.constraint/constraint`.  

As I outlined in my talk, it was important to have multiple ways to parse
the same constraint for maximum reuse. For example, `[:string {:min 10}]` and `[:vector {:min 10}]`
should yield equivalent constraints on `count`, while `[:int {:min 10}]` and `[:float {:min 10}]`
yield constraints on size. This is useful when solving constraints for generators
(malli.constraint.solver).  

### Extensibility  
The new implementation converts the `:min`, `:max`, `:gen/min`, and `:gen/max`
properties on the `:string` schema to constraints. They are implemented
separately from `:string` itself in a successful test of the extensibility
of constraints.  

`malli.constraint/base-constraint-extensions` contains the configuration
for these `:string` constraints, which consist of the `:true`, `:and`, and `:count`
constraints. There are several ways to attach `:count` constraints to a `:string`,
each of which has a corresponding parser. For example, a string with a count between
1 and 5 (inclusive) can be created via `[:string {:min 1 :max 5}]` or
`[:string {:and [:count 1 5]}]`. The `:string :parse-properties :{min,max}` configuration shows how
to parse the former and `:string :parse-constraint :count` the latter.  

### Performance  
Extensibility and performance are somewhat at odds here. While it's great that two
unrelated parties could define `:min` and `:max` in `[:string {:min 1 :max 5}]`,
we are left with a compound constraint `[:and [:count 1] [:count nil 5]]` (for the `:min`
and `:max` properties, respectively). To generate an efficient validator for the overall constraint we
must simplify the constraint to `[:count 1 5]`. The difference in validators before
and after intersecting are `#(and (<= 1 (count %)) (<= (count %) 5))` and #(<= 1 (count %) 5)`.
Depending on the performance of `count`, choosing incorrectly could be a large regression in performance.  

Constraints have an `-intersect` method to merge with another constraint
which `:and` calls when generating a validator. While we regain the performance of validation,
we pay an extra cost in having to create multiple constraints and then simplify them.  

### Robustness  
My main concern is a little esoteric but worth considering. Malli has specific
expectations about properties that constraints might break, specifically that properties
won't change if roundtripping a schema.  

A constrained schema such as `[:string {:min 1}]` is really two schemas: `:string`
and `[:count 1]`, the latter the result of the new `-get-constraint` method on
`-simple-schema`'s like `:string`. The problem comes when serializing this schema
back to the vector syntax: how do we know that `[:count 1]` should be serialized to
`[:string {:min 1}]` instead of `[:string {:and [:count 1]}]`? I don't think
this is a problem for simple constraints like `:min` since we can just return
the same properties as we started with. There are several odd cases I'm not
sure what do with.  

For instance, when the `:min` property is changed:  
```clojure
(-update-properties [:string {:min 1}] :min inc)
;=> [:string {:min 2}]
```
In this case, the `:string` schema is recreated along
with a new constraint `[:count 2]`.

Or, the constraint itself is updated with the new `-set-constraint`:

```clojure
(-set-constraint [:string {:min 1}] [:count 2])
;=> [:string {:min 2}] OR [:string {:and [:count 2]}] ?
```

Here `-set-constraint` removes all properties related to constraints (since we're replacing
the entire constraint) and then must infer the properties to serialize the new constraint to.
In this case the constraint configuration in `:string :unparse-properties ::count-constraint`
chooses `[:string {:min 2}]`, but its resemblance to the initial schema is coincidental
and might yield surprises.  

The big task here is thinking about (future) constraints that contain schemas. For example,
you could imagine a constraint `[:string {:edn :int}]` that describes strings that
`edn/read-string` to integers. This is very similar to `[:string {:registry {::a :int}}]`
in that the properties of the schema are actually different before and after parsing the
schema (in this case, `m/-property-registry` is used to parse and unparse the registry).  

Part of the rationale of using `-get-constraint` as the external interface for extracting
a constraint from a schema is to treat each schema as having one constraint
instead of many small ones is for schema-walking purposes. Property registries don't play
well with schema walking and it takes a lot of work to ensure schemas are walked correctly
(for example, ensuring a particular OpenAPI property is set on every schema, even those in
local registries). Walking schemas inside constraints will be more straightforward. To support constraints,
a schema will extend their `-walk` algorithm to automatically walk constraints with a separate
"constraint walker", and constraints like `:edn` will revert to the original "schema walker"
to walk `:int` in `[:string {:edn :int}]`. This logic lives in `malli.constraint/-walk-leaf+constraints`.  

This walking setup is intended to cleanly handle refs inside schemas such as:
```clojure
[:schema {:registry {::a :int}}
 [:string {:edn ::a}]]
```

Having schemas in properties leaves us in a fragile place in terms of the consistency of schema
serialization. For example, after walking
`[:string {:edn :int}]` to add an OpenAPI property on each schema, we might end up
with either
```clojure
[:string {:edn [:int {:a/b :c}], :a/b :c}]
;; or
[:string {:and [:edn [:int {:a/b :c}]], :a/b :c}]
```
depending on the `:unparse-property` attached to `:edn` constraints under `:string`.

Or even more fundamentally, the properties of `[:string {:edn :int}]` become `{:edn (m/schema :int)}`
when parsed, but how do we figure out it was originally `{:edn :int}`? The current approach
(which is a consequence of treating each schema as having one constraint via `-{get,set}-constraint`)
depends on the unparser in `:string :unparse-properties ::edn-constraint` to guess correctly.  

It is unclear how big of a problem this is. My fundamental worry is that schemas will not round-trip syntactically,
but is this lot of worry about nothing? Plenty of schemas don't round-trip syntactically at first, but stabilize
after the first trip, for example `[:string {}] => :string => :string`. The important
thing is that they are semantically identical. This is similar to what I propose for constraints:
deterministically attempt to find the smallest serialization for the constraint within
the properties. If inconsistencies occur, at best might annoy some users, or at worst
it could make constraints incomprehensible (to humans) be restating them in technically-equivalent ways.  

### Next
I need to resolve this roadblock of constraint serialization inconsistency. Is it a problem?
If it is, do I need to throw out the entire design and start again?  <br>

---
## SciCloj: Daniel Slutsky  
Q3 2024 Report No. 1, Published Oct. 3, 2024  
The [Clojurists Together](https://www.clojuriststogether.org/) organisation has decided [to sponsor](https://www.clojuriststogether.org/news/q3-2024-funding-announcement/) Scicloj community building for Q3 2024, as a project by Daniel Slutsky. This is the second time the project is selected this year. Here is Daniel's update for September.

Comments and ideas would help. :pray:  

[Scicloj](https://scicloj.github.io/) is a Clojure group developing a stack of tools and libraries for data science. Alongside the technical challenges, community building has been an essential part of its efforts since the beginning of 2019. Our current main community-oriented goal is making the existing data-science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on example-based documentation, easy setup, and recommended workflows for common tasks.  

All these, and the tools to support them, grow organically, driven by real-world use cases.  

I serve as a community organizer at Scicloj, and this project was accepted for Clojurists Together funding in 2024 Q1 & Q3. I also receive regular funding from Nubank.  
In this post, I am reporting on my involvement during September 2024, as well as the proposed goals for October.  

I had 77 meetings during September. Most of them were one-on-one meetings for open-source mentoring or similar contexts.  All the projects mentioned below are done in collaboration with others. I will mention at least a few of the main people involved.  

### September 2024 highlights  

### [Scicloj open-source mentoring](https://scicloj.github.io/docs/community/groups/open-source-mentoring/)
Scicloj is providing mentoring to Clojurians who wish to get involved in open-source. This initiative began in August and has been growing rapidly in September. This program is transforming Scicloj, and I believe it will influence the Clojure community as a whole.  

We are meeting so many incredible people who are typically experienced, wise, and open-minded and have not been involved in the past. Making it all work is a special challenge. We have to embrace the uncertainty of working with people of varying availability and dynamically adapt to changes in the team. Building on our years-long experience in community building and open-source collaboration, we know we can support at least some of our new friends in finding impactful paths to contribute. We are already seeing some fruits of this work and still have a lot to improve.  

47 people have applied so far. 34 are still active, and 10 have already made meaningful contributions to diverse projects.  

I am coordinating the process, meeting all the participants, and serving as one of the mentors alongside generateme, Kira McLean, Adrian Smith, and Jeaye Wilkerson. The primary near-term goals are writing testable tutorials and docs for the [Fastmath](https://github.com/generateme/fastmath) and [Noj](https://scicloj.github.io/noj/) libraries. Quite a few participants will be working on parts of this core effort. A few other projects where people get involved are [Clay](https://scicloj.github.io/clay/), [Kindly](https://scicloj.github.io/kindly-noted/), [Jank](https://jank-lang.org/), and [ggml.clj](https://github.com/phronmophobic/ggml.clj).  

A few notable contributions were by Avicenna (mavbozo), who added a lot to the Fastmath documentation and tutorials; Jacob Windle, who added printing functionality to Fastmath regression models; Muhammad Ridho, who started working on portability of [Emmy Viewers](https://github.com/mentat-collective/emmy-viewers) data visualizations; Lin Zihao, who improved the Reagent support to the Kindly standard; Epidiah Ravachol, who worked on insightful tutorials for [dtype-next](https://github.com/cnuernber/dtype-next) array-programming; Oleh Sedletskyi, who started working on statistics tutorials; Ken Huang, who've made various contributions to Clay; and Prakash Balodi, who worked on [Tablecloth](https://scicloj.github.io/tablecloth/) issues and started organizing the Scicloj weekly group (see below).  

### [Noj](https://scicloj.github.io/noj/)  
Noj is an entry point to data and science. It integrates a set of underlying libraries through a set of testable tutorials. Here, there were great additions by generateme and Carsten Behering, and I helped a bit with the integration.
- generateme has made a big release of Fastmath version 3.0.0 alpha - a result of work in the last few months - which is affecting a few of the underlying libraries.
- Carsten Behring has released new versions of a few of the machine learning libraries.
- Carsten also made important changes to Noj in adding integration tests and automating the dev workflow.
- I helped in gradually adapting and testing a few of the underlying libraries.
- I helped initiate a few documentation chapters that are being written by new community members.

### [Kindly](https://scicloj.github.io/kindly-noted/)  
Kindly is the standard of data visualizations used by Scicloj tutorials and docs.
- Timothy Pratley has improved the way the user controls various options.
- I helped test and integrate the new features.
- We collaborated in creating a kindly-dev team, and a few of the new friends have started contributing to the stack of libraries around Kindly.

### [Kinldy-render](https://github.com/scicloj/kindly-render)  
Kindly-render is a general rendering library which serves as a foundation for tools to support Kindly.
- Timothy Pratley has reinitiating this project.
- I joined in design discussions and testing.  

### [Clay](https://scicloj.github.io/clay/)  
Clay is a REPL-friendly tool for data visualization and literate programming.
- I worked on two new release versions. Each was a combination of bugfixes and feature requests.  

### [real-world-data group](https://scicloj.github.io/docs/community/groups/real-world-data/)
The real-world-data group is a space for people to share updates on their data projects at work.  

Meeting #13 was dedicated to talk runs and discussions preceding the Heart of Clojure conference. 
Meeting #14 was an interactive coding session of a data science tutorial.  

### Scicloj weekly  
Together with Prakash Balodi, we initiated a new weekly meeting for new community members working on open-source projects.  

Intentionally, we use a time slot which is more friendly to East and Central Asia time zones, unlike most Clojure meetups.  

We have had three meetings so far, with 4, 15, and 6 participants.  

### Linear Algebra meetings  
We organized a new group that will collaborate on implementing and teaching applied linear algebra algorithms in Clojure.  

The first meeting actually took place in October 2nd, so we will update more in the next month.  

### [Heart of Clojure](https://2024.heartofclojure.eu/)  
Sami Kallinen represented Scicloj at Heart of Clojure with an incredbible [talk about data modelling](https://2024.heartofclojure.eu/talks/sailing-with-scicloj-a-bayesian-adventure/). The talk was extremely helpful in exploring and demonstrating a lot of the new additions to the Scicloj stack.  

I collaborated with Sami on preparing the talk and improving the relevant tools and libraries to support the process.  

### October 2024 goals  
This is the tentative plan. Comments and ideas would be welcome.  

### Noj and Fastmath  
- Both these libraries will recieve lots of attention in the form of (testable) tutorials and docs. I will be working with a few people on vairous chapters of that effort.  
- We will keep working on stabilizing the set of libraries behind Noj and improving the integration tests.  

### Open-source mentoring  
We are expecting more participants to join.  
- I will keep working on supporting participants in new beginnings and ongoing projects.  

### [Hanamicloth](https://scicloj.github.io/hanamicloth/)  
Hanamicloth is a layered grammar of graphics library.  
- The goal for the coming few weeks is to bring it to beta stage and mostly improve the documentation.  

### Tooling  
- We will keep working on maturing kindly-render and refactoring Clay to use it internally.  
- Clay will be in active development for code quality, bugixes, and user requests.  

### Clojure Conj  
The coming [Clojure Conj](https://2024.clojure-conj.org/) conference will feature a few Scicloj-related talks. At Scicloj, we have a habit of helping each other in talk preparations. We will do that as much as the speakers will find it helpful. We will also organize a couple more pre-conference meetings with speakers, as we did in August.  <br>

---
## Standard Clojure Style:	Chris Oakman 
Q3 2024 Report No. 1, Published Oct. 14, 2024  

> Standard Clojure Style is a project to create a "follows simple rules, no config, runs everywhere" formatter for Clojure code.  

### tl;dr  

* project is usable for most codebases in its current state  
* many bugs fixed  
* I will be presenting Standard Clojure Style at Clojure/conj 2024  
* website is next  

### Update  
- As of [v0.7.0], Standard Clojure Style is ready for most codebases  
  - Give it a try!  
  - Standard Clojure Style is **fast**: Shaun Lebron shared some benchmarking on [Issue #77]  

- Several adventurous Clojure developers have ran Standard Clojure Style against their codebases and found bugs.  
  - I have fixed most of the reported ones.  
  - Seems like most new bugs are "small edge cases" as opposed to "large, fundamentally broken"  
  - A big thank you to these developers and their helpful bug reports!  
  - If you want to help test, please see the instructions [in the README]  

- I will be presenting Standard Clojure Style next week at Clojure/conj 2024 :tada:  
  - Come say hello if you will be attending  
  - I will also socialize the project at the conference  

[Issue #77]:https://github.com/oakmac/standard-clojure-style-js/issues/77
[in the README]:https://github.com/oakmac/standard-clojure-style-js
[v0.7.0]:https://www.npmjs.com/package/@chrisoakman/standard-clojure-style

### Next Up
- I will continue work to stabilize the library and algorithm  
- I will work on a website to explain the project  
  - There should be a "try it online" demo  
  - Explanation of the formatting rules (what are the rules? and why?)  
  - Something that teams can reference when they are deciding to adopt a formatter tool for their Clojure project  

















