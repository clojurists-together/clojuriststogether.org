---
title: "Sept. and Oct. 2024 Short-Term Project Updates"
date: 2024-10-26T14:00:00+12:00
author: Kathy Davis
summary: "Check-in from xxx"
draft: True


---

We've got our first set of reports from developers working on short-term projects funded in Q3 2024. You'll find a brief description of each project at the top of the page to provide some context -- followed by current project updates.

### clj-Nix: José Luis Lafuente  
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

### ScioCloj: Daniel Slutsky  
Scicloj is a Clojure group developing a stack of tools & libraries for data science.
Alongside the technical challenges, community building has been an essential part of its
efforts since the beginning of 2019. Our community-oriented goal is making the existing data-science stack easy to use through the maturing of the Noj library, mentioned below. In particular, we are working on
example-based documentation, easy setup, and recommended workflows for common tasks. All these, and the tools to support them, grow organically, driven by real-world use-cases. See updates for progress on Q3 projects and documentation.  
 
### Standard Clojure Style:	Chris Oakman  
Continue work on Standard Clojure Style - which is a "no config, runs everywhere, follows simple rules" formatter for Clojure code. More information about the genesis of the project can be found on Issue #1:
https://github.com/oakmac/standard-clojure-style-js/issues/1  


## Project Updates: Sept. and Oct. 2024  

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

## JIT compilation speeds
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
  <object type="image/svg+xml" data="https://jank-lang.org/img/blog/2024-10-14-llvm-ir/pcm.plot.svg" width="100%">
    <img src="https://jank-lang.org/img/blog/2024-10-14-llvm-ir/pcm.plot.svg" width="100%"></img>
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

## jank as THE native Clojure dialect  
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

## Summary  
More people are working on jank now than ever have; I expect this number to
keep growing in the coming year. I'll see you folks at the Conj and, after that,
in my next update during the holiday season, when I'll have some final numbers
comparing jank's startup times with LLVM IR vs C++, as well as some updates on
other things I've been tackling.<br>

---







