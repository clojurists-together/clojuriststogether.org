---
title: "Clojurists Together Summer of Bugs Selections"
date: 2020-06-24T06:00:00+12:00
author: Alyssa Parado
summary: "The Summer of Bugs selections are: clj-kondo vim-iced, DataScript, Calva, reitit, Keycloak-Clojure, cljc.java-time"
---

About a month ago we [solicited proposals](/news/announcing-summer-of-bugs/) for a new funding project we are calling Summer of Bugs. Developers can apply for $500 or $1,000 funding to work on a project that they maintain or contribute to. Summer of Bugs awards are smaller in scope than our three month projects and don't require the grantee to be a maintainer of the project they are applying for. This means that we have been able to accept applications from a wider range of developers, and fund different kinds of projects which don't need three months work.

In our first round of funding we are funding $6,500 USD to clj-kondo vim-iced, DataScript, Calva, reitit, Keycloak-Clojure, and cljc.java-time. Below is a description of the projects and developers we are funding.

We'd like to say a big thanks to all Clojurists Together [members](/members/) for your help in funding these projects. In particular we'd like to thank new company members Latacora, Clubhouse, and Solita for helping to provide funding for these grants.

## clj-kondo

Michiel Borkent is the maintainer of clj-kondo. Clj-kondo is used by a lots of [companies](https://github.com/borkdude/clj-kondo/blob/master/doc/companies.md). It has 18,000 downloads in the Visual Studio marketplace.

Michiel plans to make it possible for clj-kondo users to deal with arbitrary custom macros, like Rum's defc macro, slingshot's try+ or core.match. See [this](https://github.com/borkdude/clj-kondo/issues/811).

This project will receive a grant of $1,000USD.

## vim-iced

Iizuka Masashi is a maintainer of vim-iced and has implemented docstring references in a plain nREPL environment. vim-iced is the only one that provides practical and interactive Clojure development environment for both Vim and Neovim. With the advent of babashka and babashka.nrepl, there are now more nREPL servers that are easy to use. By supporting them, we can make development in Clojure easier and more efficient.

Iizuka's plan is to test integration (individual or full execution) even when connected to a plain nREPL server.

This project will receive a grant of $500USD.


## DataScript

Nikita Prokopov is the author and maintainer of DataScript. It is a popular Clojure/Script project that is unique to Clojure community, providing a competitive advantage to everyone using Clojure.

Nikita's plan is to catch up DataScript API with what latest Datomic offers.

* Return maps in query https://github.com/tonsky/datascript/issues/322
* Composite tuples data type https://github.com/tonsky/datascript/issues/323
* Pull :as keyword https://github.com/tonsky/datascript/issues/350"

This project will receive a grant of $1,000USD.


## Calva

Peter Strömberg is the creator and main contributor to Calva. For the Calva experience, there are some quality issues with the REPL window so for some newcomers to Clojure, it taints their experience with the language as well. Clojure is a joy to use, and so should the IDE tooling be too.

Calvas REPL Window is an ambitious project. Due to the nature of VS Code API, the window is implemented in a Webview as a full Clojure editor, with formatting, syntax highlighting, paredit, the lot. (Think a Clojure only CodeMirror.) But it is too ambitious for the Calva project and a constant source of friction and bugs. In addition to that, the VS Code team seems to not have the time to fix some important issues with the Webview API, making fixing some of the REPL window bugs unreachable for the Calva devs.

Peter would like to eventually get rid of the REPL window. But first, he needs something that can replace it for the developers who fancy the workflow it offers. Some of Peter's quick experiments seem to confirm that he could use regular files, and Calva could have commands to create these ”REPL files”, to ”pair” them with project files/namespaces, and to make it easy to evaluate code in them in a predictable way. This is easy in project files, so he would almost be done ones Calva can help create and handle the REPL files, but he would still need to try. According to him, as far as he understand things, it would be quite similar to the CIDER REPL buffers, even if he thinks he is going for an even more file-like experience.

He also thinks that with some reasonable amount of work he'd be able to release it as an experimental feature for users to opt in. But further down the line, he hopes for being able to stop maintaining the REPL window and get rid of a whole category of quality issues with Calva.

This project will receive a grant of $1,000USD.


## reitit

Tommi Jalkanen an experienced user of reitit. He has previously contributed to reitit by fixing some typos in this [documentation](https://github.com/metosin/reitit/pull/211). He had used reitit in many APIs and worked with https://github.com/CSCfi/rems which included swagger related tasks as well.

Overall there are around 250,000 downloads of [reitit](https://clojars.org/metosin/reitit). A lot of people in the clojure community are using it but the project is relatively new. It would be beneficial to the community at large to improve swagger documentation and migrate to using the new version. Metosin is also looking for help. Having a swagger schema generated reitit template would also help people to get started with reitit easier or port their old APIs to use reitit.

There are a handful of open [issues](https://github.com/metosin/reitit/issues?q=is%3Aissue+is%3Aopen+swagger) related to swagger and some of which are marked as good first issue or help wanted. Tommi would like to push this issue forward as this has been open for a while: https://github.com/metosin/reitit/issues/84

In addition to that, these steps will help Tommi to gain enough information about swagger to be able to tackle https://github.com/metosin/reitit/issues/272 in the future.

This project will receive a grant of $1,000USD.


## Keycloak-clojure

Grodziski is the project creator and maintainer of Keycloak-Clojure. Top-notch and quick security is a must-have, Keycloak brings a solid IAM server compatible with OAuth and keycloak-clojure brings its integration in the Clojure ecosystem. Keycloak-clojure eases the integration and administration but documentation is mandatory to ease the integration process

keycloak-clojure needs better documentation about its integration in the Clojure ecosystem. The doc should clearly separate the Keycloak concepts and different interaction: administration of a realm, authentication and authorization checking with "common" Clojure libraries both on the front and serverside (ring, yada, re-frame, reagent, etc.)

This project will receive a grant of $1,000USD.


## cljc.java-time

Henry Widd is a maintainer of cljc.java-time. According to him, with regards to the section on proposal-temporal in this [post](https://github.com/henryw374/cljc.java-time), the best looking solution on the horizon is the new platform date-time library being developed for Javascript aka tc-39/proposal-temporal (hereafter PT). If JS had a good date-time api built-in then it wouldn’t be necessary to bring your own of course, so no need for Js-Joda and problems 1) and 2) go away, simples!

The PT api is currently being finalized and experimental work is underway to reimplement Moment.js with it. How far away it is from being included in the next version of Node, Chrome etc he really can’t tell. Even when it is there, the world’s browsers won’t get upgraded overnight so polyfills will be needed for some time in many cases.

That aside, what does PT look like? Unfortunately, from a cross-platform lib developer’s PoV, it is not that similar to java.time. Some obvious differences are naming and the entities where methods reside. Also, there is no ZonedDateTime and the Duration and Period classes have been combined. However, although it looks pretty different, he has a feeling that the differences are quite superficial. If you wanted a cross platform Clojure api that used PT and java.time, Henry thinks extending tick’s protocols to the PT entities would likely be a good starting point, so you might end up with a tick-lite that doesn’t use js-joda or any npm lib.

This library works on jvm clojure, clojurescript and babashka and is the basis for the popular 'tick' library. Adoption by other cross platform libraries is hindered by the npm dependency and it's size. Having it work on proposal-temporal would remove those barriers (as discussed here for [example](https://github.com/metosin/malli/issues/49)).

This project will receive a grant of $1,000USD.
