---
title: "June & July 2024 Short-Term Project Updates"
date: 2024-07-20T08:30:00+08:00
summary: Updates from babashka, clj-kondo, clj-merge, Compojure-api, Enjure, Jank, Lost in Lambduhhs Podcast and more
author: Kathy Davis
draft: True


---



We've got several updates to share from our Q2 project developers. Check out the latest in their June and July Reports following the project list below.  

[clj-kondo, babashka, squint/cherry: Michiel Borkent](#clj-kondo-babashka-and-more-michiel-borkent)  
[Several projects underway](https://github.com/borkdude) including Clj-kondo: support Clojure 1.12, other Clojure dialects, most wanted open tickets. Babashka: Support new Clojure 1.12 (interop) features, better error messages. Squint/Cherry: source map support, better nREPL support, better CLJS compatibility.  

[clj-merge tool: Kurt Harriger](#clj-merge-kurt-harriger)   
This project focuses on developing a git diff and merge tool for edn and clojure code with the aim of creating a [git mergetool](https://github.com/kurtharriger/clj-mergetool) that can be used as a replacement for git’s default merge tool for clj(s) and edn files.  

[Compojure-api: Ambrose Bonnaire-Sergeant](#compojure-api-ambrose-bonnaire-sergeant)     
[This project](https://github.com/metosin/compojure-api) will deploy the first new releases since 2019 (and include compojure-api 1.x, 2.0.0-alpha branch, ring-swagger), compojure-api/reitet migration tools, and Swagger 3.0.    

[Enjure: Janet A. Carr](#enjure-janet-a-carr)  
This project focuses on MVP for the [Enjure CLI tool](https://github.com/janetacarr/enjure/blob/main/notes.org) and providing the ability to create new projects and view/controller templates as well as delete templates.  

[Jank: Jeaye Wilkerson](#jank-jeaye-wilkerson)   
[Jank's](https://github.com/jank-lang/jank) library parity with Clojure.core is around 20%. The next step is to fill out the language to make it feel more like Clojure - including Lazy sequences, Loop/recur, Destructuring, Symbol interning, and for and doseq macros.  


[Lost in Lambduhhs Podcast: L. Jordan Miller](#lost-in-lambduhhs-podcast-l-jordan-miller)  
Rejuvenate and streamline production of the [Lost In Lambduhhs Podcast](https://linktr.ee/lambduhhh), where the audience gets the opportunity to “meet the person behind the Github” -  illuminating the personal narratives and insights of tech luminaries, giving them a platform to share their perspectives while promoting their library or tool.  
\
&nbsp;
\
&nbsp;

## clj-kondo, babashka, squint/cherry: Michiel Borkent  
Q2 2024 Report No. 1. Published June 30, 2024  

## Clj-merge: Kurt Harriger  
Q2 2024 Report No. 2. Published July 1, 2024  

## Compojure-api: Ambrose Bonnaire-Sergeant  
Q2 2024 Report No. 3. Published July 8, 2024  

## Jank: Jeaye Wilkerson  
Q2 2024 Report 3. Published June 30, 2024  

## Enjure: Janet A. Carr  
Q2 2024 Report. Published June 12, 2024  

Progress has been good. I regularly stream Enjure to my audience on Twitch which seem to be bootstrapping Enjure's Github stars.  

Despite the progress, I intentionally expanded the scope of the project by implementing an
HTTP router for Enjure. I'm not entirely sure that this was a wise decision, but
it adheres to Enjure's guiding philosophy. Enjure's HTTP router is implemented
with a Radix tree and supports path parameters, in pure Clojure. I'm hoping to come up with a scheme for query, body, path, and form coercion soon, but I
haven't decided on a scheme I like. The router lives in a dynamic var managed
by Enjure and is updated by macros for defining pages and controllers.  

Enjure has several macros enforcing a similar convention to define HTTP resources.
The purpose of which brings together a resource's routes, contract, coercion and handling
expressions to a single namespace. Often Clojure web applications are structured with several
libraries. For example, Consider an application with Reitit with ring, next.jdbc with postgreSQL,
The application will likely have its routes in one namespace, it's handlers in another namespace,
its business logic in another namespace and data modification language (DML) in another namespace;
Necessitating opening several source files to accomplish a small task. Rarely do the
Routes, contracts, and handlers change. If they do, it's from minute changes.
Bringing these together cuts down on the cyclomatic complexity of developing web
Applications in Clojure. Thanks to homoiconicity, I can create constructs to help with
Exactly this:  

```clojure
;; Example from Enjure repo
(defpage user "/users/:user-id"
  [req]
  (let [{:keys [path-params]} req
        {:keys [user-id]} path-params]
    (format "<h1>Hello, %s</h1>" user-id)))

```

This "page" construct is simply a function var under the hood, but also manages the routing-table
Var. There's no middleware required to update the routing table upon REPL reload. Simply evaluating
The buffer/namespace will change the routing table. defpage expects a string as its return value as
it's largely tied to the content-type text/html. In the future I hope to have other, similar view
constructs to support other popular application mime types.  

Similarly, there are actions, changes, and removals that correspond to POST, PUT, and DELETE
HTTP methods, respectively. Since Enjure places a high emphasis on convention, I'll only show a
Simple example of a Sign In action for a user:  

```clojure
(defaction signin "/signin"
  [req]
  (let [{:keys [email password]} (:form-params req)]
    (if (check-db email password)
      (redirect pages/home :see-other) ;; redirects to whatever route pages/home var has.
      (pages/siginin req) ;; this is a page var to render
  )))
```
Since resources in Enjure are just function vars, they can be called directly, and also reverse-routed
to using some of the response macros. In the above example, if the signing check passes, redirect
Redirects to whatever route pages/home has declared. (Reverse-routing is largely for convenience, and
Not mandatory, the redirect macro supports redirecting to static paths/URLs, Enjure resources like pages, and
Values from functions).  

Ideally, resources would interact with the database through a data model supported by the framework.
My ideas for this are still experimental and can be found in the repository under the internal â€œfrmâ€
namespace. Currently, it's some simple templating of basic queries by querying the information_schema in
Postgres, and interning the query functions as vars. These are queries I've seen regularly over the years,
and I'm sure that, once implemented, will give developers a boost in productivity. Plus, there's the added
benefit of being decoupled from whatever mechanism I choose for supporting migrations/entities in Enjure (still
A TBD). However, this model does not alienate developers who opt to create more complex queries
as those are supported as well with next.jdbc.  

Another idea I've been experimenting with is something I call the ReactiveRecord. ReactiveRecord uses
Software transactional memory to synchronize with the database, providing an in-memory DB representation.
I think given the functional interface provided by FRM above and information schema data. It might be interesting, but I do believe this kind of transacting might be faux-pas or even dangerous, so more thought is needed here on my part.  

All of this will ideally be controlled with the Enjure CLI. Enjure puts a heavy emphasis on reducing
developer friction. Given a base installation of Clojure, installing Enjure should allow for the creation and
management of Enjure projects very easily. I'll admit this is an area I've been slacking on a bit since I
wanted to finished with other core components first. As of writing this, the Enjure CLI has two basic commands:
Notes and help.  Notes search a project for comments containing NOTES, FIXME, TODO, and HACK. Help just
prints out the help dialog. Soon enough Enjure CLI will support creating and deleting resources, migrations,
Entities, dependencies, etc., as well as creating new projects. The Enjure CLI can already be installed to a user's path as a CLI utility written entirely in Clojure.  

Finally, documentation of the project has become my lowest priority and definitely at risk. However, I'm not too concerned about the documentation faltering. In some sense, I'm a technical writer thanks to my blog, so I believe writing documentation for Enjure won't be as challenging as the rest of the project.  <br>

---


## Lost in Lambduhhs Podcast: L. Jordan Miller  
Q2 2024 Report 2. Published June 27, 2024.  

