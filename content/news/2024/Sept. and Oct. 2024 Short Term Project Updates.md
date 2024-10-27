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



