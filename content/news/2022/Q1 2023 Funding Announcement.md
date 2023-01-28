---
title: "Q1 2023 Funding Announcement"
date: 2023-01-28T08:30:00+08:00
summary: "Clojurists Together is pleased to announce that we are funding 7 projects for a total of $42K USD in Q1 2023."
author: Kathy Davis and Maria Geller
draft: true
---
# Q1 2023 Funding Announcement

Clojurists Together is pleased to announce that we are funding 7 projects for a total of $42K USD in Q1 2023. The projects and descriptions are listed below. Congratulations to all and we look forward to updates as the work progresses. 

**$9,000 USD**
* **Aleph/Manifold:** Matthew Davidson
* [**Clerk:**](https://github.com/nextjournal/clerk) Martin Kavalar
* [**Donut:**](https://github.com/donut-power) Daniel Higginbotham
* [**Uncomplicate Neanderthal:**](https://github.com/uncomplicate/neanderthal) Dragan Duric

**$2,000 USD**
* [**Clojure-ts-mode:**](https://github.com/clojure-emacs/clojure-ts-mode) Danny Freeman
* [**Clojure track on exercism.org:**](https://github.com/exercism/clojure) Bobbi Towers
* [**Doxa:**](https://github.com/ribelo/doxa) Rafał Krzyważnia
  
  
  
## Aleph/Manifold

**Grantee:** Matthew Davidson

**What are you wanting to achieve with this funding?**

There's a base level of maintenance involved in just keeping deps up-to-date and squashing bugs that takes up time. For new features and improvements, I'd like to add HTTP 2 and 3 support, refactor our error handling and websocket code, and explore/support Loom virtual threads.

**Why is this project important to the Clojure community?**

Aleph is one of the most popular HTTP libraries in the Clojure community, especially for streaming applications, and has been downloaded over 1.6 million times, according to Clojars.
  
  

## Clerk

**Grantee:** Martin Kavalar

**What are you wanting to achieve with this funding?**

* Improve documentation https://book.clerk.vision
* Fix performance bottlenecks to make the Tap inspector useable when tapping large values
* Improve error handling
* Extend Clerk to serve reactive dashboards, including a getting started project

**Why is this project important to the Clojure community?**

It's becoming one of the more popular options for data science and moldable development.
  
  

## Donut

**Grantee:** Daniel Higginbotham

**What are you wanting to achieve with this funding?**

Donut is a single-page app framework that builds on existing well-regarded libraries like reitit and re-frame.

With Donut, I'm trying to create a beginner-friendly Clojure product: a collection of libraries, tutorials, and community resources that place the beginner experience front-and-center. This purpose of this product is to help developers of all levels be able to get their business ideas into production quickly, and to be able to grow and maintain their projects with minimum frustration.

I want the libraries and instructional materials to be the most comprehensive, easy-to-follow materials for launching an SPA that can make money with Clojure, but beyond that I also want to cultivate a community that values both beginner-friendliness and technical excellence. I want to rally Clojurists around the idea that together we can create an awesome foundation for apps that will help real people improve their lives, without sacrificing technical quality.

I think that the code is good enough to start publicizing, but the code is only one part of what needs to get done. Clojurists Together funding will help me pay for other aspects of building a product:

- Paying for branding and design
- Building a web site that serves as both marketing vehicle and excellent technical instruction and documentation
- Community building, e.g. spending time doing presentations, managing a slack channel, reaching out to other devs, etc

**Why is this project important to the Clojure community?**

This project is important to the Clojure community because we still lack a clear, comprehensive beginners' story.

We're also lacking a higher foundation for SPA development. For example, we don't have off-the-shelf drop-in solutions for common problems like Stripe integration or Google/Facebook/Apple authentication, and I want Donut to become that foundation.



## Uncomplicate Neanderthal

**Grantee:** Dragan Duric

**What are you wanting to achieve with this funding?**

My goal with this round is to implement Sparse Matrix support in Neanderthal. Neanderthal is a Clojure library for fast matrix computations based on the highly optimized native libraries and computation routines for both CPU and GPU.

It is a lean, high performance, infrastructure for working with vectors and matrices in Clojure, which is the foundation for implementing high performance computing related tasks, including, but not limited to, machine learning and artificial intelligence. It currently supports operations on vectors, general matrices, and structured sparse matrices. A major missing part is support for arbitrary, unstructured, sparse matrices, and functions operating on them. Sparse matrices are matrices with mostly *zero* values. Although in theory the same data can be represented by general matrices, sparse matrices enable efficient support for gigantic matrices, since we do not need space for storing all these zeroes, and we can optimize operations to only compute the affected values (which are small percentage of the total).

Sparse matrices are useful wherever general matrices are useful (which is in most places where we work with many numbers) and the ratio of non-zero to zero values is relatively small (which is often the case). Besides machine learning, this includes common problems where we represent sparse conectedness between items (say, which out of many millions of items each of hopefully millions of users bought) and subsequent operations of such data. This could potentially be very useful for developers of graph-based software (lots of Clojure Code works with graphs, implicitly, or explicitly).

The state of the art implementation of optimized low-level operations will be provided by integrating Intel's oneDNN (CPU). Native API's in this domain are very heavy, convoluted, and rather unclojure-y; but I've already learned how to solve that problem in Neanderthal. This is something that is currently poorly supported in Java, let alone Clojure, but this project can bring an elegant support to Clojure and the JVM.

I propose to implement:

- A developer-friendly implementation of common sparse matrix structures in Clojure
- Support relevant operations as Clojure functions
- An extension infrastructure for various future backend implementations.
- A clean low-level integration with Intel's oneDNN RNN on the CPU
- Assorted improvements to Deep Diamond to support this additions
- Related bugfixes
- TESTS (of course!)

This would improve Neanderthal and make it more complete as:

- A fast and simple Clojure-first infrastructure library that people can build on top of for everything matrix-related (which includes lots of ML and lots of non-ML)
- A fast and user-friendly sparse matrix implementation in Clojure

I will follow up this work with continuous improvement of Neanderthal, and its ecosystem of books, docs, tutorials, etc. (I have already published two books covering this)

**Why is this project important to the Clojure community?**

It is the infrastructure for high performance computing, deep learning, machine learning, and other high performance scientific software based on high-performance vector and matrix operations.




## Clojure-ts-mode

**Grantee:** Danny Freeman

**What are you wanting to achieve with this funding?**

I would like to get as much of clojure-mode (font-locking, indentation, imenu, navigation, potentially refactoring tools) ported over to clojure-ts-mode as possible. I also help maintain tree-sitter-clojure (https://github.com/sogaiu/tree-sitter-clojure) the tree-sitter grammar that powers clojure-ts-mode. As I find issues with it, I will help fix them in the grammar repository. This will help benefit other editors and tools like neovim that also use the tree-sitter-clojure grammar.


**Why is this project important to the Clojure community?**

Tree-sitter support is being released with Emacs 29 early next year. Tree sitter provides more performant syntax highlighting and code introspection than what Emacs is traditionally capable of. Most of the heavy lifting is done by the tree-sitter grammar that is used by Emacs and other tools. Having clojure-ts-mode ready by the time Emacs 29 is out will allow Emacs Clojure user to take advantage of tree-sitter right out of the gate.




## Clojure track on exercism.org

**Grantee:** Bobbi Towers

**What are you wanting to achieve with this funding?**

I am wanting to expand the track syllabus by creating more concept exercises to improve the learning path to provide a more complete educational experience.

Right now we are focusing on building analysis tooling to provide automated feedback on exercise submissions, since we receive hundreds each week many of which are extremely similar, which results in our mentors having to needlessly repeat themselves. The way we are solving this problem is through building *representers*, a system of analyzing code and grouping them into *approaches*, and integrating them into our UI which enables our trusted mentors to provide standardized feedback for the approaches. More info: https://exercism.org/blog/introducing-representers

**Why is this project important to the Clojure community?**

Exercism has been one of the most popular Clojure learning communities and is one of the 2 sources of practice problems listed at https://clojure.org/guides/getting_started\Over the years we have evolved from curating exercises from around the web, to creating a proper learning path that guides the student through the knowledge graph of key language concepts, and building an in-browser editor and test runner.What sets us apart from other "learn to code" websites is the aspect of human mentorship, and our continued mission is to maximize the value of human connection in learning a language.



## Doxa

**Grantee:** Rafał Krzyważnia

**What are you wanting to achieve with this funding?**

Doxa was born as an experiment and an attempt to learn how to use a meander. An example of extreme programming. In fact, almost every function is based on it. Despite this approach, it has proven useful and community interest has exceeded expectations. I'd like to rewrite all the code in a way that doesn't rely on mender, which is brilliant, but as such contains a lot of bugs and let's be honest, it makes the whole thing look like crap. Also, doxa documentation is basically non-existent and I would like to write it too.

**Why is this project important to the Clojure community?**

Over time, despite the initial enthusiasm, it became apparent that datascript was too big, complex and slow to use on the front end. True, complex and large databases only show their advantage over a simple map when we have a amount of data that exceeds what is usually found on the frontend. But pull syntax and datalog query have pretty much become the standard among the community, and there seems to be room for a small, clever, normalised database with support for both.

