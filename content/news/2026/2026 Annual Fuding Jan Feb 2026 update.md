---
title: "Annually-Funded Developers' Update: January & February 2026"
date: 2026-03-25T14:00:00+12:00
author: Kathy Davis
summary: "Bozhidar Batsov, Clojure Camp, Eric Dallo, Michiel Borkent, Jeaye Wilkerson, Thomas Clark"  



---

Hello Fellow Clojurists!

This is the first of six reports from the developers who are receiving annual funding for 2026. We've also added in the final FastMath report from Thomas Clark (Q3 2025 project).  There is a lot of great work here - so have fun exploring!

[**Bozhidar Batsov:**](#bozhidar-batsov) nREPL, Clojure Mode, info-Clojure, CIDER, drawbridge    
[**Clojure Camp:**](#clojure-camp)  Badges, Parson's Problems, Mobs   
[**Eric Dallo:**](#eric-dallo) eca, clojure-lsp, metrepl   
[**Jeaye Wilkerson:**](#jeaye-wilkerson) Jank  
[**Michiel Borkent:**](#michiel-borkent) SCI, babashka, Cream, clj-kondo, squint, and more

[**Thomas Clark:**](#fastmath-thomas-clark)FastMath   <br>  
<br>  



## Bozhidar Batsov  
2026 Annual Funding Report 1. Published March 3, 2026.  

The period was extremely productive and solid progress was made on almost every front.
CIDER nad nREPL saw important releases, but so did also:  
- clojure-mode
- inf-clojure
- drawbridge (first new release in years)

I've also did some work on updating REPLy to use jline 3 and tools.reader (instead of the abandoned sjacket)   
Below are some highlights from the releases.

### nREPL

#### Release: 1.6.0 (Feb 26)  

**New Features**  
- **`^:optional` middleware metadata** — middleware can now be marked optional, so missing middleware is handled gracefully instead of blowing up (#415).  
- **`^:concat` config merging** — already shipped in an earlier cycle, but part of this release (#402).  

**Bug Fixes**  
- **EDN transport with Unix domain sockets** — long-standing issue #351 finally resolved (#421).  
- **`load-file` nil message for empty files** — loading empty or comment-only files no longer sends a nil message (#422).   
- **`err-exit` throwing a string** instead of a proper Exception.  
- **Middleware deduplication** — duplicate middleware entries are now cleaned up when constructing a handler (#410).  

**Internal Improvements (January)**  
- Major refactoring wave: handler construction & middleware application (#409), stdin middleware (#408), `nrepl.core` functions, and conditional loading of `nrepl.ack`.  
- Deprecated automatic inclusion of middleware via `:requires`/`:expects` — still works but will error in the future (#412).  
- Replaced `Thread.sleep` with `Thread.join` in cmdline, removed unnecessary sleep in threading.  
- Atomic update for `running-repl` atom, replaced deprecated `#^{}` metadata syntax.  

**Documentation Overhaul (February)**  
A massive documentation push covering:  
- New troubleshooting guide, expanded FAQ with practical Q&As.  
- Session lifecycle docs for client developers.  
- Configuration keys reference table for server docs.  
- Documented `forward-system-output` op, `respond-to` helper, `.nrepl-port` file/server discovery, dynamic var defaults, and `^:optional`/`^:concat` metadata.  
- Cleaned up stale labels ("experimental", "work in progress"), removed dead links and abandoned projects from listings.  
- Documented the release process itself.  

**Housekeeping**  
- Bumped dependencies and copyright years.  
- Removed Fastlane from code and docs.  
- Fixed typos/grammar across source and documentation.  
- Renamed `lein maint` to `lein docs`.  

---

### clojure-mode 5.21.0 (Feb 18) + some ongoing work for the next release  

I did the biggest updates of clojure-mode in years in the past month. This might sound odd, given that clojure-ts-mode has been in a great shape for a while now, but I felt that in the spirit of Clojure's stability we shouldn't force people to change their workflows if they are happy with them. And I understand that no everyone likes the complexity of using TreeSitter (or can run a new Emacs). So, I've decided to tackle the issues that seemed most important and I think the final result was pretty good. Some of the changes below are unreleased, but will be released soon, as everything's merged already and I haven't received any reports of regressions.  

**New Features**  
- **`edn-mode`** — a new lightweight major mode derived from `clojure-mode` with data-appropriate indentation for `.edn` files (#650).
- **Shebang recognition** — `interpreter-mode-alist` entries for `clojure`, `clj`, `planck`, `joker`, and `jank` so scripts auto-activate the right mode (#439).
- **`clojure-discard-face`** for `#_` reader discard forms, separately stylable from comments (#688).
- **`clojure-preferred-build-tool`** — controls project root detection when multiple build files exist, with `.git` as tiebreaker (#687).
- **ClojureCLR project root detection** (`deps-clr.edn`).  
  
**Bug Fixes (many long-standing)**  
- `clojure-sort-ns` mangling `:gen-class` (#527).
- `clojure-thread-last-all` breaking forms with line comments (#619).
- `clojure-add-arity` severing arglist metadata (#649).
- Nested indent specs rejected by validation (#600).
- `clojure-find-def` failing on special characters and comments before the symbol name (#637, #686).
- `clojure-mode-version` returning nil (#658).
- Font-lock protocol method docstrings (#402) and `letfn` function names (#365).

**Housekeeping**  
- Bumped minimum Emacs to 27.1, added Emacs 30 to CI.
- Major performance pass: cached regexps, iterative loops instead of recursion, eliminated duplicate `syntax-ppss`/`thing-at-point` calls.
- Updated `clojure-mode-extra-font-locking` for Clojure 1.10-1.12 (new functions, removed non-existent entries, fixed misplacements).
- Rebound `clojure-align` to `C-c C-a` (old `C-c SPC` deprecated).
- Comprehensive README and CHANGELOG cleanup.

---

### inf-clojure 3.4.0 (Feb 27)

inf-clojure rarely gets much love from me, but the project has been in a good shape for a while now anyways. Still, I felt that an annual cleanup and bug-fixing session was in order and I hope you'll appreciate it. I've also tried to restructure the docs to be easier to follow and I finally added some comparison with CIDER.  

**New Features**  
- **`inf-clojure-show-var-meta`** (`C-c C-S-m`) — display a var's full metadata (#57).
- **Namespace-aware eval** via `inf-clojure-eval-ns-aware` (#205).
- **`clojure-clr` REPL type** for `clr.tools.deps`.
- Arglists now shown **in the REPL buffer** instead of the minibuffer (#143).

**Bug Fixes**  
- `inf-clojure-connected-p` now checks for a live process.
- Preoutput filter handles chunked comint output correctly, fixing spurious newlines (#136/#153).
- `project-root` error when no project is detected (#219).
- Truncated autoload cookie on `inf-clojure-connect`.
- `prefix-arg` vs `current-prefix-arg` in REPL startup.
- Emacs 28-29 compatibility fix for `derived-mode-p`.
- Confirmation message after `inf-clojure-set-ns` (#149).

**Housekeeping**  
- Deduplicated REPL feature definitions across Clojure-family REPLs (shared base alist).
- Unified `reload`/`reload-all` forms into `inf-clojure-repl-features`.
- Switched to modern `eldoc-documentation-functions` hook.
- Dropped `lein-clr` support (abandonware).
- Expanded test coverage, removed dead code, fixed docstrings and lint warnings.
- Comprehensive README improvements: inf-clojure vs CIDER comparison (#74), nREPL TTY transport docs (#155), load/reload/set-ns docs (#192), completion docs (#111).

---

### CIDER 1.21.0 "Gracia" (Feb 7)   

Last, but never least... :D I did't do that much with CIDER for this release, as I wanted to focus on
nREPL, clojure-mode and inf-clojure, but I think it's still turned out pretty well.  

- **Moved elisp source under `lisp/`** to improve repo structure (#3477).
- **Dropped Emacs 27 support** (prompted by upstream deps).
- `cider-ns-browser` items are now clickable buttons (evil-compatible `RET`).
- Removed deprecated `cider-*-global-opts` and remaining Boot references.
- Fixed mangled printed representations (caused by an nREPL bug fixed in 1.5.2).

After the release I've introduced the concept of **default session** — `cider-set-default-session` / `cider-clear-default-session` to bypass sesman's project-based dispatch (#3865). This was in the back of my mind for years  

I've also spend some time cleaning up internals, improving the CI and the docs. A lot more CIDER improvements are currently brewing. :-)   
As you can imagine - I have many ideas on what to tackle next, so I hope the next couple of months will be just as exciting and productive.  

Thanks to everyone for your support! You rock!  <br>  

---


## Clojure Camp    
2026 Annual Funding Report 1. Published March 7, 2026.   

- Some of our CT funds are being set aside to support three efforts this year:   
  - sponsoring conference attendance for new Clojurians,   
  - supporting Clojure and non-Clojure meetups with a “pizza fund”,   
  - hosting an experimental nano-conj (in-person multi-day open-ended hack-on-clojure “retreat”)  

- On the “dev side”..:   
  - Most of the time this period has been spent on a badge system (unreleased) to reorganize our learning roadmap ( [https://map.clojure.camp/](https://map.clojure.camp/) )  
    - The functionality is mostly complete, and some UI polish and content organizing remains  
    - The goal is to have three levels of “badges” for topics, with clear expectations on what to do prove knowledge for a badge (and help direct learners)  
    - Learners will be able to self-grant, but community members will also be able to “verified grant”  
  - Mobs continue to happen regularly; our community site now lets people indicate their ability which we’ve started to use to schedule mobs more dynamically  
  - Released Parson’s Problems (alpha) on our Exercise Site  
    - Parson’s Problems are like multiple-choice mad-libs  
    - These should be more accessible to new devs compared to word problems  
    - You can check it out at [https://exercises.clojure.camp/exercises/count-if](https://exercises.clojure.camp/exercises/count-if) (switch the “Word Problem” select input to “Fill in the Blanks”)  
  - Some early experiments with a “one-file Clojure starter environment installer”  

- By next checkpoint, hopefully:   
  - Continue mobs, get back to weekly mob cadence, promote more regularly in more places  
  - Publish backlog of exercises (based on past year of mobs)  
  - Release the Badges  
  - Engage a community member to help with logistical efforts (pizza fund, nano-conj, mentor program) <br>

---

## Eric Dallo 
2026 Annual Funding Report 1. Published March 8, 2026.   

Starting 2026 with so much energy! In these first 2 months I've been working hard on multiple projects, with most of the focus on ECA which is growing really fast with more and more people using it and contributing, we reached 0.109.0 with lots of features and improvements! Besides that, I worked on clojure-lsp, clojure-mcp, brepl and more, really happy with the progress and thankful for ClojuristsTogether sponsorship! :heart:  

### [eca](https://github.com/editor-code-assistant/eca)

ECA keeps growing with lots of new features, bug fixes, and improvements in stability and compatibility. In these 2 months we had lots of releases with some really exciting features, [the changelog is huge](https://github.com/editor-code-assistant/eca/blob/master/CHANGELOG.md) but here are the highlights:

- __Subagents__: ECA now supports foreground subagents, allowing the main agent to spawn focused sub-agents for specific tasks like code exploration, review, and more. This is a huge improvement for complex workflows.
- __Skills support__: Following the [agentskills.io](https://agentskills.io) spec, ECA now supports skills that can be loaded dynamically by the LLM, with a new `/skill-create` command to create skills from prompts.
- __Auto compact__: Automatic context compaction based on a configurable percentage of context window usage, avoiding context overflow and keeping conversations efficient.
- __Context overflow recovery__: When context limit is hit, ECA now recovers gracefully instead of failing.
- __Variants__: A new feature to configure model variants (low, medium, high, max) for different providers, allowing fine-grained control over model behavior.
- __MCP OAuth support__: Support for HTTP MCP servers that require OAuth authentication.
- __Agents (formerly behaviors)__: Renamed behaviors to agents with improved configuration and prompt customization.
- __New config API for prompts__: Complete overhaul of prompt configuration, allowing override of any tool description, system prompts, and more.
- __Network configuration__: Support for custom CA certificates and mTLS client certificates.
- __More providers and models__: Added Codex login, moonshot models, kimi-k2.5, claude-sonnet-4-6, claude-opus-4-6, gpt-5.3-codex and many more.

That's really a lot of things done, showing how users are excited with the project and asking for new features, ECA is in a really good shape after 6 months, closer to Claude code, Cursor and other tools, but free and more extensible!

Also, we have a new webpage for [eca.dev](https://eca.dev)!

<img width="600" height="321" alt="Dalle website image" src="https://github.com/user-attachments/assets/7ac60b1b-fbdb-4037-a1c8-0fbc60ad69ea" />


#### ECA editor plugins

All ECA editor plugins received significant updates to keep up with the new ECA server features:

- **[eca-emacs](https://github.com/editor-code-assistant/eca-emacs)**: Support for subagents UI, variants, git worktree, improved tables UI, TRAMP fixes, light theme improvements, queue messages support, and many bug fixes.
- **[eca-vscode](https://github.com/editor-code-assistant/eca-vscode)** (0.34.1 - 0.40.0): Support for subagents, variants, diagnostics, prompt queuing, cycle history, export chat, image pasting, chat rename and timeline.
- **[eca-intellij](https://github.com/editor-code-assistant/eca-intellij)** (0.17.1 - 0.21.0): Support for subagents, variants, diagnostics, export chat to markdown, querying files via `#` in prompt.
- **[eca-nvim](https://github.com/editor-code-assistant/eca-nvim)**: Tool call improvements, implicit manual approval flow, better auto scroll behavior, stream queue improvements.

### [clojure-lsp](https://clojure-lsp.io/)

We had a big release with lots of dependency bumps that were long overdue, new code actions, and important bug fixes. The Extract Function code action got much more powerful with selection and threading support!
I have plans for next months to have custom code actions, memory management, performance and classpath scan improvements!

#### 2026.02.20-16.08.58

- New code actions: refactor cond to if, and refactor if to cond.
- Allow finding references of multiple definitions at cursor. #2176
- Fix CLI format `:style/indent` support via project type flag.
- Fix format `:style/indent` conflicts with core macros. #2197
- Add selection to Extract Function code action. #2118
- Add threading to Extract Function code action. #2175
- Fix replace-refer-all mangling unrelated symbols. #2150
- Fix unused-public-var `:exclude-when-contains-meta` not being considered for `.lsp/config.edn`.
- Fix unused-public-var not working with `:config-in-ns` in kondo config.
- Wrap hovered function definitions in parentheses.
- Include reader functions in special cases for finding the last element in a container.
- Lots of bumps/fixes: clj-kondo 2026.01.19, rewrite-clj 1.2.51, Clojure 1.12.4, cljfmt 0.16.0, lsp4clj 2.0.1, babashka/sci 0.12.51, opentelemetry 1.59.0 and more.

### [metrepl](https://github.com/ericdallo/metrepl)

#### 0.5.1 - 0.5.2

- Fix middlewares metric to check response instead of payload.
- Add `java.classpath` missing dependency.  <br>

---

## Jeaye Wilkerson  
2026 Annual Funding Report 1. Published March 6, 2026.   

Hey folks! We're two months into the year and I'd like to cover all of the
progress that's been made on jank so far. Before I do that, I want to say thank you to
all of my Github sponsors, as well as Clojurists Together for sponsoring this
whole year of jank's development!  

### jank book  
To kick things off, let me introduce the [jank book](https://book.jank-lang.org/).
This will be the recommended and official place for people to learn jank and its
related tooling. It's currently targeted at existing Clojure devs, but that will
start to shift as jank matures and I begin to target existing native devs as well. The
jank book is written by me, not an LLM. If you spot any issues, or have any
feedback, please do create a [Github Discussion](https://github.com/jank-lang/jank/discussions).  

My goals for this book include:  
1. Introduce jank's syntax and semantics  
2. Introduce jank's tooling  
3. Walk through some small projects, start to finish  
4. Demonstrate common use cases, such as importing native libs, shipping AOT artifacts, etc.  
5. Show how to troubleshoot jank and its programs, as well as where to get help  
6. Provide a reference for error messages  

As the name and technology choice implies, the jank book is heavily inspired by
the [Rust book](https://doc.rust-lang.org/stable/book/).  

### Alpha status    
jank's switch to alpha in January was quiet. There were a few announcements made
by others, who saw the commits come through, or who found the jank book before I
started sharing it. However, I didn't make a big announcement myself since I
wanted to check off a few more boxes before getting the spotlight again. In
particular, I spent about six weeks, at the end of 2025 and into January, fixing
pre-mature garbage collection issues. These weeks will be seared into my memory
for all of my days, but the great news is that all of the issues have now been
fixed. jank is more and more stable every day, as each new issue improves our
test suite.  

### LLVM 22  
On the tail of the garbage collection marathon, the eagerly awaited LLVM 22
release happened. We had been waiting for LLVM 22 to ship for several months,
since it would be the first LLVM version which would have all of jank's required
changes upstreamed. The goal was that this would allow us to stop vendoring our
own Clang/LLVM with jank and instead rely on getting it from package managers.
This would make jank easier to distribute and, crucially, make jank-compiled AOT
programs easier to distribute. You can likely tell from my wording that this
isn't how things went. LLVM 22 arrived with a couple of issues.  

Firstly, some data which we use for very important things like loading object
files, adding LLVM IR modules to the JIT runtime, interning symbols, etc was
changed to be private. This can happen because the C++ API for Clang/LLVM is not
considered a public API and thus is not given any stability guarantees. I have
been in discussions with both Clang and LLVM devs to address these issues. They
are aware of jank and want to support our use cases, but we will need to codify
some of our expectations in upstreamed Clang/LLVM tests so that they are less
likely to be broken in the future.  

Secondly, upon upgrading to LLVM 22, I found two different performance
regressions which basically rendered debug builds of jank unusable on Linux
([here](https://github.com/llvm/llvm-project/issues/179611) and
[here](https://github.com/llvm/llvm-project/issues/182954)). Our startup time
for jank debug builds went from 1 second to 1 minute and 16 seconds. The way
jank works is quite unique. This is what allows us to achieve unprecedented C++
interop, but it also stresses Clang/LLVM in ways which are not always well
supported. I have been working with the relevant devs to get these issues fixed,
but the sad truth is that the fixes won't make it into LLVM 22. That means we'll
need to wait several more months for LLVM 23 before we can rely on distro
packages which don't have this issue.  

That's a tough pill to swallow, so I took a week or so to
[rework](https://github.com/jank-lang/jank/pull/702) the way we do
codegen and JIT compilation. I've not only optimized our approach, but I've also
specifically crafted our codegen to avoid these slower parts of LLVM. This
not only brings us back to previous speeds, it makes jank faster than it was
before. Once LLVM 23 lands, the fixes for those issues will optimize things
further.  

So, if you've been wondering why I've been quiet these past few months, I likely
had my head buried deep into one of these problems. However, with these issues
out of the way, let's cover all of the other cool stuff that's been implemented.  

### nREPL server  
jank has an nREPL server now! You can read about it in the relevant
[jank book chapter](https://book.jank-lang.org/getting-started/04-hello-nrepl.html).
One of the coolest parts of the nREPL server is that it's
[written in jank](https://github.com/jank-lang/jank/tree/16ace19a4dc771a540f86fbc4c1fbb3e0fae5fe8/compiler%2Bruntime/src/jank/jank/nrepl/server)
and yet also baked into jank, thanks to our two-phase build process. The nREPL
server has been tested with both NeoVim/Conjure and Emacs/CIDER. There's a lot
we can do to improve it, going forward, but **it works**.  

As Clojure devs know, REPL-based development is revolutionary. To see jank's
seamless C++ interop combined with the tight iteration loop of nREPL is
beautiful. Here's a quote from an early jank nREPL adopter, Matthew Perry:  
> The new nREPL is crazy fun to play around with. Works seamlessly with my
> editor (NeoVim + Conjure). It's hard to describe the experience of compiling
> C++ code interactively - I'm so used to long edit-compile-run loops and
> debuggers that it feels disorienting (in a good way!)

A huge shout out to Kyle Cesare, who originally wrote jank's nREPL server back
in August 2025. Thank you for your pioneering! If you're interested in helping
out in this space, there's still so much to explore, so jump on in.  

### C++ interop improvements  
Most of my other work on jank has been related to improving C++ interop.  

### Referred globals  
jank now allows for C/C++ includes to be a part of the `ns` macro. It also
follows ClojureScript's design for `:refer-global`, to bring native symbols into
the current namespace. Without this, the symbols can still be accessed via the
special `cpp/` namespace.  

```clojure
(ns foo
  (:include "gl/gl.h") ; Multiple strings are supported here.
  (:refer-global :only [glClear GL_COLOR_BUFFER_BIT])) ; Also supports :rename.

(defn clear! []
  (glClear GL_COLOR_BUFFER_BIT))
```

### Native loop bindings  
jank now supports native loop bindings. This allows for loop
bindings to be unboxed, arbitrary native values. jank will ensure that the
native value is copyable and supports `operator=`. This is great for looping
with C++ iterators, for example.  

```clojure
(loop [i #cpp 0]
  (if (cpp/== #cpp 3 i)
    (cpp/++ i)
    (recur (cpp/++ i))))]
```

There's more work to be done to automatically use unboxed values and use native
operators, when possible. For now it's opt-in only.  

### Unsafe casting  
jank had the equivalent of C++'s `static_cast`, in the form of `cpp/cast`.
However, for some C/C++ APIs, unsafe casting is necessary. To accomplish this,
jank now has `cpp/unsafe-cast`, which does the equivalent of a C-style cast.  

```clojure
(let [vga-memory (cpp/unsafe-cast uint16_t* #cpp 0xB8000)]
  )
```

### Type/value DSL  
This one is working, but not yet in `main`. jank now supports encoding C++ types
via a custom DSL. With this DSL, we can support any C++ type, regardless of how
complex. That includes templates, non-type template parameters, references,
pointers, const, volatile, signed, unsigned, long, short, pointers to members,
pointers to functions, and so on. The jank book will have a dedicated chapter on
this once merged, but here's a quick glimpse.  

<table>
<tr>
<td> C++ </td> <td> jank </td>
</tr>

<tr>
<td>

A normal C++ map template instantiation.

```cpp
std::map<std::string, int*>
```

</td>
<td>

```clojure
(std.map std.string (ptr int))
```

</td>
</tr>

<tr>
<td>

A normal C++ array template instantiation.

```cpp
std::array<char, 64>::value_type
```

</td>
<td>

```clojure
(:member (std.array char 64) value_type)
```

</td>
</tr>

<tr>
<td>

A sized C-style array.

```cpp
unsigned char[1024]
```

</td>
<td>

```clojure
(:array (:unsigned char) 1024)
```

</td>
</tr>

<tr>
<td>

A reference to an unsized C-style array.

```cpp
unsigned char(&)[]
```

</td>
<td>

```clojure
(:& (:array (:unsigned char)))
```

</td>
</tr>

<tr>
<td>

A pointer to a C++ function.

```cpp
int (*)(std::string const &)
```

</td>
<td>

```clojure
(:* (:fn int [(:& (:const std.string))]))
```

</td>
</tr>

<tr>
<td>

A pointer to a C++ member function.

```cpp
int (Foo::*)(std::string const &)
```

</td>
<td>

```clojure
(:member* Foo (:fn int [(:& (:const std.string))]))
```

</td>
</tr>

<tr>
<td>

A pointer to a C++ member which is itself a pointer to a function.

```cpp
void (*Foo::*)()
```

</td>
<td>

```clojure
(:member* Foo (:* (:fn void [])))
```

</td>
</tr>

</table>

This type DSL will be enabled *automatically* in type position for `cpp/new`,
`cpp/cast`, `cpp/unsafe-cast`, `cpp/unbox`, and so on. It can also be explicitly
introduced via `cpp/type`, in case you want to use it in value position to
construct a type or access a nested value. For example, to dynamically allocate
a `std::map<int, float>`, you could do:  

```clojure
(let [heap-allocated (cpp/new (std.map int float))
      stack-allocated ((cpp/type (std.map int float)))]
  )
```

### Other improvements  
jank will now defer JIT compilation of functions, when possible. In some
scenarios, such as during AOT compilation, this can cut compile times in half.
We do this by generating a stub object which will JIT compile the relevant code
when it's first called. It understands vars, too, so it will replace itself in
its containing var when called so that subsequent calls through the var just go
to the JIT compiled function. JVM folks happily don't need to worry about these
sorts of things, but we can have nice things, too.  

Also, jank's object model has been opened up. I
[previously documented](https://jank-lang.org/blog/2023-07-08-object-model/) my research into an
efficient object model. Over the past couple of years of hammock time, I have
found an approach which allows for JIT-defined objects while still avoiding the
costs of C++'s runtime type information (RTTI). This is worthy of its own post
entirely, which I will likely do once the transition is complete. For now, we
have most of our code still using the old model while some of it is using the
new model. This is great, though, since it allows us to port piece by piece
while keeping everything in `main`. The main outcome of opening up the object
model is that jank users can define their own jank objects which integrate well
into the system, can be stored within jank data structures, and used with jank
functions.  

Finally, to better support nREPL, jank added support for `clojure.core/future`.
This required an audit of all synchronization across the jank compiler and
runtime. Now, we should be in a good place from which to build multi-threaded
jank applications. Tools like Clang's thread sanitizer will help ensure we stay
there.

### What's next
In March, I am wrapping up work on the type DSL and getting that merged. I also
need to investigate why the Arch binary package for jank is broken. Beyond that,
I will be starting into some deep performance research for jank. That will mean
first collecting a series of benchmarks for jank versus Clojure and then profiling
and optimizing those benchmarks as needed. I would really like to get some
continuous benchmarking set up, so we can track performance over time, tied
to particular commits. The current plan is to spend all of Q2 on performance,
but there's a lot to do, so I won't be able to tackle everything. Benchmark
optimization posts are often quite fun, so stay tuned for the next one!  <br>

---

## Michiel Borkent  
2026 Annual Funding Report 1. Published March 6, 2026.   

In this post I'll give updates about open source I worked on during January and February 2026.
To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).

## Sponsors  
I'd like to thank all the sponsors and contributors that make this work
possible. Without you, the below projects would not be as mature or wouldn't
exist or be maintained at all! So a sincere thank you to everyone who
contributes to the sustainability of these projects.  

<img alt="gratitude" src="https://emoji.slack-edge.com/T03RZGPFR/gratitude/f8716bb6fb7e5249.png" width="50px" text-align="center">

Current top tier sponsors:  

- [Clojurists Together](https://clojuriststogether.org/)
- [Roam Research](https://roamresearch.com/)
- [Nextjournal](https://nextjournal.com/)
- [Nubank](https://nubank.com.br)

Open the details section for more info about sponsoring.  

<details>
<summary>Sponsor info</summary>  

If you want to ensure that the projects I work on are sustainably maintained,
you can sponsor this work in the following ways. Thank you!  

- [Github Sponsors](https://github.com/sponsors/borkdude)
- The [Babashka](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
- [Ko-fi](https://ko-fi.com/borkdude)
- [Patreon](https://www.patreon.com/borkdude)
- [Clojurists Together](https://www.clojuriststogether.org/)

</details>


### Babashka conf and Dutch Clojure Days 2026

Babashka Conf 2026 is happening on May 8th in the OBA Oosterdok library in Amsterdam! David Nolen, primary maintainer of ClojureScript, will be our keynote speaker! We're excited to have Nubank, Exoscale, Bob and Itonomi as sponsors. Wendy Randolph will be our event host / MC / speaker liaison :-). The CfP is now closed. More information [here](https://babashka.org/conf/). Get your ticket via [Meetup.com](https://www.meetup.com/the-dutch-clojure-meetup/events/312079164/) (there is a waiting list, but more places may become available).
The day after babashka conf, [Dutch Clojure Days 2026](https://clojuredays.org/) will be happening, so you can enjoy a whole weekend of Clojure in Amsterdam.
Hope to see many of you there!  

### Projects  
I spent a lot of time making SCI's `deftype`, `case`, and `macroexpand-1` match JVM Clojure more closely. As a result, libraries like riddley, cloverage, specter, editscript, and compliment now work in babashka.  

After seeing [charm.clj](https://codeberg.org/timokramer/charm.clj), a terminal UI library, I decided to incorporate JLine3 into babashka so people can build terminal UIs. Since I had JLine anyway, I also gave babashka's console REPL a major upgrade with multi-line editing, tab completion, ghost text, and persistent history. A next goal is to run rebel-readline + nREPL from source in babashka, but that's still work in progress (e.g. the compliment PR is still pending).  

I've been working on `async/await` support for ClojureScript ([CLJS-3470](https://clojure.atlassian.net/browse/CLJS-3470)), inspired by how squint handles it. I also implemented it in SCI (scittle, nbb etc. use SCI as a library), though the approach there is different since SCI is an interpreter.  

Last but not least, I started [cream](https://github.com/borkdude/cream), an experimental native binary that runs full JVM Clojure with fast startup using GraalVM's Crema. Unlike babashka, it supports runtime bytecode generation (`definterface`, `deftype`, `gen-class`). It currently depends on a fork of Clojure and GraalVM EA, so it's not production-ready yet.  

Here are updates about the projects/libraries I've worked on in the last two months in detail.  

- NEW: [cream](https://github.com/borkdude/cream): Clojure + GraalVM [Crema](https://github.com/oracle/graal/issues/11327) native binary
  - A native binary that runs full JVM Clojure with fast startup, using GraalVM's Crema (RuntimeClassLoading) to enable runtime `eval`, `require`, and library loading  
  - Unlike babashka, supports `definterface`, `deftype`, `gen-class`, and other constructs that generate JVM bytecode at runtime  
  - Can run `.java` source files directly, as a fast alternative to JBang  
  - Cross-platform: Linux, macOS, Windows  

- [babashka](https://github.com/babashka/babashka): native, fast starting Clojure interpreter for scripting.  
  - Released 1.12.214 and 1.12.215  
  - [#1909](https://github.com/babashka/babashka/issues/1909): add JLine3 for TUI support  
  - Console REPL (`bb repl`) improvements: multi-line editing, tab completion, ghost text, eldoc, doc-at-point (`C-x` `C-d`), persistent history  
  - Support `deftype` with map interfaces (e.g. `IPersistentMap`, `ILookup`, `Associative`). Libraries like core.cache and linked now work in babashka.  
  - Compatibility with riddley, cloverage, editscript, charm.clj  
  - [#1299](https://github.com/babashka/babashka/issues/1299): add new `babashka.terminal` namespace that exposes `tty?`  
  - Add keyword completions to nREPL and console REPL  
  - `deftype` supports `Object` + `hashCode`  
  - [#1923](https://github.com/babashka/babashka/issues/1923): support `reify` with `java.time.temporal.TemporalQuery`  
  - Fix `reify` with methods returning `int`/`short`/`byte`/`float`  
  - [Full changelog](https://github.com/babashka/babashka/blob/master/CHANGELOG.md)  

- [SCI](https://github.com/babashka/sci): Configurable Clojure/Script interpreter suitable for scripting
  - Released 0.12.51  
  - `deftype` now macroexpands to `deftype*`, matching JVM Clojure, enabling code walkers like riddley  
  - `case` now macroexpands to JVM-compatible `case*` format, enabling tools like riddley and cloverage
  - Support `async/await` in ClojureScript. See [docs](https://github.com/babashka/sci/blob/master/doc/async-await.md).  
  - Support functional interface adaptation for instance targets  
  - Infer type tags from let binding values to binding names  
  - `defrecord` now expands to `deftype*` (like Clojure), with factory fns emitted directly in the macro expansion  
  - `macroexpand-1` now accepts an optional env map as first argument  
  - Add `proxy-super`, `proxy-call-with-super`, `update-proxy` and `proxy-mappings`  
  - Support [#564](https://github.com/babashka/sci/issues/564): `this-as` in ClojureScript  
  - Store current analysis context during macro invocation, enabling tools like riddley to access outer locals  
  - [Full changelog](https://github.com/babashka/sci/blob/master/CHANGELOG.md)  

- [clj-kondo](https://github.com/clj-kondo/clj-kondo): static analyzer and linter for Clojure code that sparks joy.<br>
  [@jramosg](https://github.com/jramosg), [@tomdl89](https://github.com/tomdl89) and [@hugod](https://github.com/hugod) have been on fire with contributions this period. Six new linters!
  - Released 2026.01.12 and 2026.01.19
  - [#2735](https://github.com/clj-kondo/clj-kondo/issues/2735): NEW linter: `:duplicate-refer` which warns on duplicate entries in `:refer` of `:require` ([@jramosg](https://github.com/jramosg))
  - [#2734](https://github.com/clj-kondo/clj-kondo/issues/2734): NEW linter: `:aliased-referred-var`, which warns when a var is both referred and accessed via an alias in the same namespace ([@jramosg](https://github.com/jramosg))
  - [#2745](https://github.com/clj-kondo/clj-kondo/issues/2745): NEW linter: `:is-message-not-string` which warns when `clojure.test/is` receives a non-string message argument ([@jramosg](https://github.com/jramosg))
  - [#2712](https://github.com/clj-kondo/clj-kondo/issues/2712): NEW linter: `:redundant-format` to warn when format strings contain no format specifiers ([@jramosg](https://github.com/jramosg))
  - [#2709](https://github.com/clj-kondo/clj-kondo/issues/2709): NEW linter: `:redundant-primitive-coercion` to warn when primitive coercion functions are applied to expressions already of that type ([@hugod](https://github.com/hugod))
  - Add new types `array`, `class`, `inst` and type checking support for related functions ([@jramosg](https://github.com/jramosg))
  - Add type checking support for `clojure.test` functions and macros ([@jramosg](https://github.com/jramosg))
  - [#2340](https://github.com/clj-kondo/clj-kondo/issues/2340): Extend `:condition-always-true` linter to check first argument of `clojure.test/is` ([@jramosg](https://github.com/jramosg))
  - [#2729](https://github.com/clj-kondo/clj-kondo/issues/2729): Check for arity mismatch for bound vectors, sets & maps, not just literals ([@tomdl89](https://github.com/tomdl89))
  - [#2768](https://github.com/clj-kondo/clj-kondo/issues/2768): NEW linter: `:redundant-declare` which warns when `declare` is used after a var is already defined ([@jramosg](https://github.com/jramosg))
  - Add type support for `pmap` and future-related functions ([@jramosg](https://github.com/jramosg))
  - Upgrade to GraalVM 25
  - [Full changelog](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)

- [squint](https://github.com/squint-cljs/squint): CLJS _syntax_ to JS compiler
  [@tonsky](https://github.com/tonsky) and [@willcohen](https://github.com/willcohen) contributed several improvements this period.
  - Add `squint.math`, also available as `clojure.math` namespace
  - [#779](https://github.com/squint-cljs/squint/issues/779): Added `compare-and-swap!`, `swap-vals!` and `reset-vals!` ([@tonsky](https://github.com/tonsky))
  - [#788](https://github.com/squint-cljs/squint/issues/788): Fixed compilation of `dotimes` with `_` binding ([@tonsky](https://github.com/tonsky))
  - [#790](https://github.com/squint-cljs/squint/issues/790): Fixed `shuffle` not working on lazy sequences ([@tonsky](https://github.com/tonsky))
  - Multiple `:require-macros` with `:refer` now accumulate instead of overwriting ([@willcohen](https://github.com/willcohen))
  - Fix emitting negative zero value (`-0.0`)
  - Fix [#792](https://github.com/squint-cljs/squint/issues/792): `prn` `js/undefined` as `nil`
  - Fix [#793](https://github.com/squint-cljs/squint/issues/793): fix `yield*` IIFE
  - [Full changelog](https://github.com/squint-cljs/squint/blob/main/CHANGELOG.md)

- [scittle](https://github.com/babashka/scittle): Execute Clojure(Script) directly from browser script tags via SCI
  - Support `async/await`. See docs.
  - Implement `js/import` not using `eval`
  - Support `this-as`
  - nREPL: print `#<Promise value>` when a promise is evaluated

- [nbb](https://github.com/babashka/nbb): Scripting in Clojure on Node.js using SCI
  - Support async/await. See docs for syntax.
  - Print promise result value in REPL/nREPL: `(js/Promise.resolve 1) ;;=> #<Promise 1>`

- [fs](https://github.com/babashka/fs) - File system utility library for Clojure
  - Released 0.5.31
  - [#212](https://github.com/babashka/fs/issues/212): Introduce `:keep true` option in `with-temp-dir`
  - [#188](https://github.com/babashka/fs/issues/188) `copy-tree` now throws if `src` or `dest` is a symbolic link when not following links ([@lread](https://github.com/lread))
  - [#201](https://github.com/babashka/fs/issues/201) `gzip` now accepts `source-file` `Path` ([@lread](https://github.com/lread))
  - [#207](https://github.com/babashka/fs/issues/207) review and update `glob` and `match` docstrings ([@lread](https://github.com/lread))

- [clerk](https://github.com/nextjournal/clerk): Moldable Live Programming for Clojure
  - Fix browse when using random port by passing 0, fixes #801
  - bb now supports editscript

- [neil](https://github.com/babashka/neil): A CLI to add common aliases and features to deps.edn-based projects.<br>
  - [#258](https://github.com/babashka/neil/issues/258): `neil test` now exits with non-zero exit code when tests fail

- [cherry](https://github.com/squint-cljs/cherry): Experimental ClojureScript to ES6 module compiler
  - Multiple `:require-macros` clauses with `:refer` now properly accumulate instead of overwriting each other

Contributions to third party projects:

- [ClojureScript](https://github.com/clojure/clojurescript):
  - Working on `async/await` support ([CLJS-3470](https://clojure.atlassian.net/browse/CLJS-3470)). I also implemented this in SCI, scittle, and nbb.
  - [CLJS-3471](https://clojure.atlassian.net/browse/CLJS-3471): fix printing of negative zero
  - [CLJS-3472](https://clojure.atlassian.net/browse/CLJS-3472): `str` on var that is `set!` returns empty string
- [editscript](https://github.com/juji-io/editscript): Added babashka support, deps.edn for git dep usage, fixed CLJS tests
- [riddley](https://github.com/ztellman/riddley): Added babashka compatibility, clj-kondo config
- [cloverage](https://github.com/cloverage/cloverage): Added babashka compatibility, migrated tools.cli from deprecated `cli/cli` to `cli/parse-opts`, bumped riddley
- [specter](https://github.com/redplanetlabs/specter): Added babashka compatibility
- [compliment](https://github.com/alexander-yakushev/compliment): Added babashka compatibility ([PR #131](https://github.com/alexander-yakushev/compliment/pull/131))
- [rebel-readline](https://github.com/bhauman/rebel-readline): Removed JLine impl class dependencies for babashka compatibility, released 0.1.7
- [Selmer](https://github.com/yogthos/Selmer): Namespaced script tag context keys to avoid collisions, removed runtime require of clojure.tools.logging
- [charm.clj](https://codeberg.org/timokramer/charm.clj): Contributed JLine integration, FFM native terminal interface, babashka and native-image compatibility

### Other projects  

These are (some of the) other projects I'm involved with but little to no activity
happened in the past month.  

<details>
<summary>Click for more details</summary>  
  
- [pod-babashka-go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3): A babashka pod for interacting with sqlite3    
- [unused-deps](https://github.com/borkdude/unused-deps): Find unused deps in a clojure project  
- [pod-babashka-fswatcher](https://github.com/babashka/pod-babashka-fswatcher): babashka filewatcher pod  
- [sci.nrepl](https://github.com/babashka/sci.nrepl): nREPL server for SCI projects that run in the browser  
- [babashka.nrepl-client](https://github.com/babashka/nrepl-client)  
- [http-server](https://github.com/babashka/http-server): serve static assets  
- [sci.configs](https://github.com/babashka/sci.configs): A collection of ready to be used SCI configs.  
- [http-client](https://github.com/babashka/http-client): babashka's http-client  
- [html](https://github.com/borkdude/html): Html generation library inspired by squint's html tag  
- [instaparse-bb](https://github.com/babashka/instaparse-bb): Use instaparse from babashka  
- [sql pods](https://github.com/babashka/babashka-sql-pods): babashka pods for SQL databases
- [rewrite-edn](https://github.com/borkdude/rewrite-edn): Utility lib on top of
- [rewrite-clj](https://github.com/clj-commons/rewrite-clj): Rewrite Clojure code and edn
- [tools-deps-native](https://github.com/babashka/tools-deps-native) and [tools.bbuild](https://github.com/babashka/tools.bbuild): use tools.deps directly from babashka
- [bbin](https://github.com/babashka/bbin): Install any Babashka script or project with one command
- [qualify-methods](https://github.com/borkdude/qualify-methods)
  - Initial release of experimental tool to rewrite instance calls to use fully
    qualified methods (Clojure 1.12 only)
- [tools](https://github.com/borkdude/tools): a set of [bbin](https://github.com/babashka/bbin/) installable scripts
- [babashka.json](https://github.com/babashka/json): babashka JSON library/adapter
- [speculative](https://github.com/borkdude/speculative)
- [squint-macros](https://github.com/squint-cljs/squint-macros): a couple of
  macros that stand-in for
  [applied-science/js-interop](https://github.com/applied-science/js-interop)
  and [promesa](https://github.com/funcool/promesa) to make CLJS projects
  compatible with squint and/or cherry.
- [grasp](https://github.com/borkdude/grasp): Grep Clojure code using clojure.spec regexes
- [lein-clj-kondo](https://github.com/clj-kondo/lein-clj-kondo): a leiningen plugin for clj-kondo
- [http-kit](https://github.com/http-kit/http-kit): Simple, high-performance event-driven HTTP client+server for Clojure.
- [babashka.nrepl](https://github.com/babashka/babashka.nrepl): The nREPL server from babashka as a library, so it can be used from other SCI-based CLIs
- [jet](https://github.com/borkdude/jet): CLI to transform between JSON, EDN, YAML and Transit using Clojure
- [lein2deps](https://github.com/borkdude/lein2deps): leiningen to deps.edn converter
- [cljs-showcase](https://github.com/borkdude/cljs-showcase): Showcase CLJS libs using SCI
- [babashka.book](https://github.com/babashka/book): Babashka manual
- [pod-babashka-buddy](https://github.com/babashka/pod-babashka-buddy): A pod around buddy core (Cryptographic Api for Clojure).
- [gh-release-artifact](https://github.com/borkdude/gh-release-artifact): Upload artifacts to Github releases idempotently
- [carve](https://github.com/borkdude/carve) - Remove unused Clojure vars
- [4ever-clojure](https://github.com/oxalorg/4ever-clojure) - Pure CLJS version of 4clojure, meant to run forever!
- [pod-babashka-lanterna](https://github.com/babashka/pod-babashka-lanterna): Interact with clojure-lanterna from babashka
- [joyride](https://github.com/BetterThanTomorrow/joyride): VSCode CLJS scripting and REPL (via [SCI](https://github.com/babashka/sci))
- [clj2el](https://borkdude.github.io/clj2el/): transpile Clojure to elisp
- [deflet](https://github.com/borkdude/deflet): make let-expressions REPL-friendly!
- [deps.add-lib](https://github.com/borkdude/deps.add-lib): Clojure 1.12's add-lib feature for leiningen and/or other environments without a specific version of the clojure CLI
- [edamame](https://github.com/borkdude/edamame): configurable EDN and Clojure parser with location metadata and more
- [CLI](https://github.com/babashka/cli): Turn Clojure functions into CLIs!
- [quickblog](https://github.com/borkdude/quickblog): light-weight static blog engine for Clojure and babashka
- [process](https://github.com/babashka/process): Clojure library for shelling out / spawning sub-processes
- [deps.clj](https://github.com/borkdude/deps.clj): A faithful port of the clojure CLI bash script to Clojure
- [reagami](https://github.com/borkdude/reagami): A minimal zero-deps Reagent-like for Squint and CLJS
- [parmezan](https://github.com/borkdude/parmezan): fixes unbalanced or unexpected parens or other delimiters in Clojure files
- [quickdoc](https://github.com/borkdude/quickdoc): Quick and minimal API doc generation for Clojure
- [Nextjournal Markdown](https://github.com/nextjournal/markdown)

</details>

<br>

---

## FastMath: Thomas Clark  
Q3 2025 Funding Report 2. Published Feb. 6, 2026.   

### Table of Contents  
1.  [Overview](#org440362e)  
2.  [New Design](#orgfe07f59)  
3.  [New API (and implementations)](#org0372647)  
4.  [Outlook](#org146b826)  



<a id="org440362e"></a>

### Overview  

In the second term of this project, I refined and finalised the overall protocol structure for mathematical, representational and predicate operations, particularly confirming the split between what is &rsquo;necessary&rsquo; and what is &rsquo;extra&rsquo; when identifying and manipulating mathematical objects. Using this, I created several more protocols and  implemented more rigorous testing namespaces - involving multiple tiers - and leaning on another Clojurists Together project for generating references: **Wolframite**. By introducing flexible constructor namespaces for both (real and complex) matrices and numbers, as well as multimethod-based type definitions, I solidified the overall design into a generalised API. Such an API now facilitates different layers of use: allowing for operator overloading, domain promotion and variadic arguments across objects as standard, but leaving the concrete implementations separate and available for when speed really matters.  

Overall then, this work completes the funding period, successfully implementing the fastmath matrix protocol for complex matrices and extending this to a generalised API. There is however, still much to do, some of which really has to be done soon for these extensions to be practically useful. Below, I expand on what was done during this period: with a discussion of the overall design, some illustrative examples of the API and a reflection on the overall status and the necessary and hopeful next steps.   


<a id="orgfe07f59"></a>

### New Design  

Although an enthusiastic consumer of parts of `fastmath` previously, it only became apparent to me, in the first part of the project, just how vast the library is. This, combined with the notion that a complex matrix API necessarily requires interaction with complex numbers (and that complex matrix implementation requires a consistent real matrix implementation), naturally led to the need to consider wider interaction problems and so wider design concerns. As highlighted in the midterm report, it became clear that, for the library to continue to expand organically, the implementation details would need to be abstracted, so that we can swap alternative backends in and out more easily in the future. This created contradictions however.  

The contradictions arose as the demands for a user-friendly (and consistent) API, orients the solution towards having all of the compatible methods in the same namespace - who wants to import 20 nses to work an a single linear algebra problem? A decomplected (non-hierarchical) protocol structure however, mirrors mathematical implementations well, allows for maximal code reuse and seems to fit with Clojure&rsquo;s design philosophy. Furthermore, some hierarchies are good and necessary and yet these cannot be done using Clojure protocols. My initial attempt in the first term approximated this with aliasing &rsquo;lower&rsquo; (level of abstraction) protocols within higher ones, but trying to balance this with generalised (non implementation dependent) constructors led to cyclical dependencies.  

I settled in the end, therefore, for a complete separation of concerns, with protocols expanding modularly in the same way (there are now about 30), the introduction of separate, generalised constructors for numbers and matrices - that worked regardless of domain - and type implementations that were explicitly coupled to underlying libraries.  

The &rsquo;user friendliness&rsquo; was then implemented entirely separately using a new, generalised, library-level, version-controlled API that exposes the object protocols and constructors in meaningful ways, but independent of the implementation. This allows the &rsquo;backends&rsquo; to be changed simply by changing the constructor dependencies. On this layer, I then explicitly implemented the linear algebra generalization, using multimethods, such that matrices and numbers can interact consistently, subject to variadic operators, with automatic domain promotion.  

It is perhaps also worth mentioning, in addition to practical things like systematic naming conventions (see forthcoming documentation), that in order to integrate the new features with the existing library, I also started to abstract operations outside of the mathematical and physical needs. Fastmath is primarily used within the SciCloj/Noj ecosystem, and so &rsquo;representational&rsquo; protocols were introduced to model matrix access as a two dimensional computing structure (table), rather than a mathematical object. This helps to push the library towards integration with the wider ecosystem, with a long term goal of tighter consistency with tablecloth, tableplot and adjacent libraries.  


<a id="org0372647"></a>

### New API (and implementations)  

As a quick illustration of the APIs, consider the process of creating and working with complex numbers with pure protocols for efficiency. 

    (require '[fastmath.api.v2.algebra.complex :as C])
      (C/i 1.0)
    ;; #object[fastmath.algebra.object.number.complex.ejml.ComplexNumber 0x38306cba "1.00000+0.000000i"]
    
    (C/add (C/i 1 2) (C/i 3 4))
    ;; #object[fastmath.algebra.object.number.complex.ejml.ComplexNumber 0x7454ba0 "4.00000+6.000000i"]
    (C/negate (C/i 1 2))
    ;; #object[fastmath.algebra.object.number.complex.ejml.ComplexNumber 0x27ce1f84 "-1.00000-2.000000i"]
    (C/norm (C/i 1 2))
    ;; 2.23606797749979

Creating matrices consistently.

      (def m--r (mat/diagonal (repeat 3 5)))
     ;; #object[fastmath.algebra.object.matrix.rectangular.real.ejml.RealDense 0x4a419cba nil]
    ;; :shape [3 3] :type float64
    ;; [[5.000 0.000 0.000]
    ;;  [0.000 5.000 0.000]
    ;;  [0.000 0.000 5.000]]
    (mat/<-real ... )
    ;; #object[fastmath.algebra.object.matrix.rectangular.complex.ejml.ComplexDense 0x3913463b nil]
    ;; :shape [3 3] :type float64
    ;; [[5.000 0.000 0.000]
    ;;  [0.000 5.000 0.000]
    ;;  [0.000 0.000 5.000]]
    (def m--c (mat/<-coll 3 3 (partition 2 (range 18))))
    ;; #object[fastmath.algebra.object.matrix.rectangular.complex.ejml.ComplexDense 0x9a42bb7 nil]
    ;; :shape [3 3] :type complex128
    ;; [[0.000+1.000000i   2.000+3.000000i   4.000+5.000000i  ]
    ;;  [6.000+7.000000i   8.000+9.000000i   10.000+11.000000i]
    ;;  [12.000+13.000000i 14.000+15.000000i 16.000+17.000000i]]
    (mat/<-rows [[10.0 1.0  -5.0]
                 [0.0 4.0  2.789]
                 [-5 31 8]])
    ;; #object[fastmath.algebra.object.matrix.rectangular.real.ejml.RealDense 0x3c9a1815 nil]
    ;; :shape [3 3] :type float64
    ;; [[10.000 1.000  -5.000]
    ;;  [0.000  4.000  2.789 ]
    ;;  [-5.000 31.000 8.000 ]]
    (mat/<-rows [[(i 78 0.0) (i 0.0 77) (i 16.13456 56)]
                 [(i 9134 -341) (i 24 2341) (i 10 -56)]])
    ;; #object[fastmath.algebra.object.matrix.rectangular.complex.ejml.ComplexDense 0x113c5d0b nil]
    ;; :shape [2 3] :type complex128
    ;; [[78.000+0.000000i     0.000+77.000000i     16.135+56.000000i   ]
    ;;  [9134.000-341.000000i 24.000+2341.000000i  10.000-56.000000i   ]]

And mixing it altogether with domain promotion, general symbols and variadic arguments. 

    (refer-clojure :exclude [+ -])
    (require '[fastmath.api.v2.algebra.general :as alg :refer [+ *]])
    
    (+ 5 (C/i 1 2) (mat/identity 2))
    ;; #object[fastmath.algebra.object.matrix.rectangular.complex.ejml.ComplexDense 0x61223e14 nil]
    ;; :shape [2 2] :type complex128
    ;; [[7.000+2.000000i 6.000+2.000000i]
    ;;  [6.000+2.000000i 7.000+2.000000i]]
    
    (+ m--c m--r m--r m--c)
    ;; #object[fastmath.algebra.object.matrix.rectangular.complex.ejml.ComplexDense 0x4f72c29b nil]
    ;; :shape [3 3] :type complex128
    ;; [[10.000+2.000000i  4.000+6.000000i   8.000+10.000000i ]
    ;;  [12.000+14.000000i 26.000+18.000000i 20.000+22.000000i]
    ;;  [24.000+26.000000i 28.000+30.000000i 42.000+34.000000i]]

<a id="org146b826"></a>

### Outlook

As can be seen above, it is now possible to interleave fundamental matrix and numeric operations without overt concern for domain. This is simply scratching the surface of fastmath&rsquo;s potential however. At the end of the first term, it had already become clear that the original scope of the project was too large to be completed within the given timeframe, and so all work had to be done with future extension in mind.  

There are some immediate next steps however, that ideally would already have been completed. These are to coordinate the integration of this branch with generateme, to finalize the optimization and clay documentation and to publicize subsequent performance metrics. I hope to complete these promptly.  

Medium-term goals would then see expansion of the integration of this extension with other fastmath features. For, as discussed, fastmath is a large library and although the core matrix protocol has been implemented, there are many other functions that apply not just to matrices in particular but which could benefit from a unified numeric tower of mathematical operations. Other features within reach are also the exploitation of [EJML](https://github.com/lessthanoptimal/ejml)&rsquo;s parallelism for longer calculations, as well as an alternative implementaton using [ojAlgo](https://github.com/optimatika/ojAlgo).  

Following this, I would also like to develop explicit links between fastmath and other libraries. The lowest-hanging fruit would be to officially support fastmath-wolframite interop. Having already used **Wolframite** to provide tests and having already worked on the **Wolframite** library itself, this should be relatively straightforward - and would allow easier delegation from fastmath to **Wolframite** (and return) for currently unimplemented algorithms. Similarly, as part of my long-term goal to integrate various Clojure libraries towards physics research, I would like to make interop with **emmy** a smooth reality. Potentially, even using fastmath as an implementation for backend calculations. Likewise, and more easily than for **emmy**, I would like to see libraries like **qclojure** use fastmath for their quantum implementations.  

Overall then, although I would have liked to get further in the time available, I am excited by this experiment in the suitability of Clojure for general mathematics. I hope that the library, even just the algebraic namespace protocols themselves, provide a basis for future work in a wide array of applications and I hope that, having read this little report, you are inspired to try it out in your own work. Please don&rsquo;t hesitate to get in touch if that&rsquo;s the case.  












