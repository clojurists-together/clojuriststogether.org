---
title: "Nov. 2024 Short-Term Project Updates"
date: 2024-12-09T14:00:00+12:00
author: Kathy Davis
summary: "clj-Nix, Clojure Goes Fast, Jank, Kushi, Malli, Standard Clojure Style"
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


## clj-nix:José-Luis-Lafuente  
Q3 2024 Report No. 2, Published Nov. 29, 2024  

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

