---
title: "May 2025 Short-Term Project Updates Q2 2025 Projects"
date: 2025-06-02T14:00:00+12:00
author: Kathy Davis
summary: "News from Bling, CALVA, CIDER, Jank"
draft: True


---

This is the April/May project update for four of our Q2 2025 Funded Projects. (Reports for the other two are on a different schedule). A brief summary of each project is included to provide overall context.

[Jeremiah Coyle: Bling](#bling-jeremiah-coyle)  
Add support for using hiccup to style and format messages, a template string syntax to style and format messages, and 1-3 additional formatting templates for callouts, headers, and points-of-interest.  

[Brandon Ringe: CALVA](#brandon-ringe-calva)  
A new REPL output view for Calva, which is a webview in VS Code. The webview will allow us to add more rich features to the output webview, while also providing better performance.  

[Bozhidar Batsov: CIDER](#bozhidar-batsov-cider)  
Provide continued support for CIDER, nREPL and the related libraries (e.g. Orchard, cidernrepl, etc) and improve them in various ways.  

[Jeaye Wilkerson: Jank](#jeaye-wilkerson-jank)   
Build jank's seamless C++ interop system.  
<br>


## Bling: Jeremiah Coyle  
Q2 2025 $2k. Report No. 1, Published 1 June 2025  

<br>
Upon commencing Q2 work on Bling, I reordered the list of priorities set forth in the initial project description.  
<br>
Updated TO DO list:  

- Primary goals for Q2 
  - Respect `NO_COLOR` env var
  - Opt-in optimization for [enhanced contrast](https://github.com/paintparty/bling?tab=readme-ov-file#enhanced-contrast) in light or dark terminals.
  - Enable [Hi-Fidelity, themeable, colorized, structural printing of values via Fireworks](https://github.com/paintparty/bling?tab=readme-ov-file#high-fidelity-printing).
  - Utilize `callout` to create a <a href="https://github.com/paintparty/bling?tab=readme-ov-file#usage-with-malli">specialized template for Malli validation errors</a>.
  - <a href="https://github.com/paintparty/bling?tab=readme-ov-file#figlet-banners">Figlet banners</a>
 with <a href="https://github.com/busyloop/lolcat" target="_blank">lolcat-style</a> gradient coloring.

- Secondary goals for Q2  
  - Add enhanced support for using hiccup to style and format messages
  - Create 1-3 additional formatting templates for callouts, headers, and points-of-interest.
  - Add documentation about how to leverage Bling to create great-looking warnings and errors in your own projects.

<br>
All the primary goals listed above are realized in [`v0.8.0`](https://clojars.org/io.github.paintparty/bling/versions/0.8.0).
<br>

Some highlights...  
<br>

<h3>Figlet banners:</h3>
<a href="https://github.com/paintparty/bling?tab=readme-ov-file#figlet-banners">Docs here</a>

<p align="center">
  <img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/bling-banner-fonts_dark-0.7.0.png"
       width="600px" />
</p>

<h3>Specialized template for Malli validation errors</h3>

This is experimental, with more work to be done on optimizing for disjunctions and cases with multiple errors on same value.

<a href="https://github.com/paintparty/bling?tab=readme-ov-file#usage-with-malli">Docs here</a>

Bling offers `bling.explain/explain-malli` to present [Malli](https://github.com/metosin/malli) validation errors:

```Clojure
(require '[bling.explain :refer [explain-malli]])

(def Address
  [:map
   [:id string?]
   [:tags [:set keyword?]]
   [:address
    [:map
     [:street string?]
     [:city string?]
     [:zip int?]
     [:lonlat [:tuple double? double?]]]]])

(explain-malli
 Address
 {:id "Lillan",
  :tags #{:coffee :artesan :garden},
  :address
  {:street "Ahlmanintie 29", :zip 33100, :lonlat [61.4858322 87.34]}})
```

<br>

The above code would print the following:

<div align="center"><img src="https://github.com/paintparty/bling/blob/main/resources/docs/chromed/malli-explain-missing-key_dark-0.7.0.png" width="600px"/></div>


You can also pass an option trailing options map to customize the appearance of the printed output. In the example below, we are leaving out the display of the schema within the callout block:  

```Clojure
(explain-malli
 Address
 {:id "Lillan",
  :tags #{"coffee" :artesan :garden},
  :address
  {:city "Tempare" :street "Ahlmanintie 29", :zip 33100, :lonlat [61.4858322 87.34]}}
 {:display-schema? false})
```
<br>

The above code would print the following:  

<div align="center"><img src="https://github.com/paintparty/bling/blob/main/resources/docs/chromed/malli-explain-bad-set-value-with-no-schema_dark-0.7.0.png" width="600px"/></div>

<h3>High Fidelity Printing</h3>

<a href="https://github.com/paintparty/bling?tab=readme-ov-file#high-fidelity-printing">Docs here</a>

Bling offers `bling.hifi` for colorized pretty-printing of Clojure, Java, and JavaScript data structures.
`bling.hifi/hifi` will return an ansi-sgr decorated string, while `bling.hifi/print-hifi` will print such a string.

Under the hood, the formatting/colorizing is achieved with <a href="https://github.com/paintparty/fireworks" target="_blank">Fireworks</a>.

By default, the theme of this output will be `Universal Neutral`.

If you [set a valid `BLING_MOOD` env var](https://github.com/paintparty/bling?tab=readme-ov-file#enhanced-contrast), the theme of the `hifi` output will be `Alabaster Light` or `Alabaster Dark`. 

You can choose <a href="https://github.com/paintparty/fireworks?tab=readme-ov-file#theming" target="_blank">one of the other available themes</a> by following the instructions in the Fireworks readme and <a href="https://github.com/paintparty/fireworks?tab=readme-ov-file#step-3" target="_blank">setting up a `config.edn` on your system</a>, with a corresponding `BLING_CONFIG` env var. This config will also let you control many other aspects of the formatting with the `hifi` output.

<br>

<div align="center"><sub><b><i>Alabaster Dark&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i></b></sub></div>
<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/themes/dark/Alabaster-Dark.png" width="534px"/></div>

<div align="center"><sub><b><i>Alabaster Light&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i></b></sub></div>
<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/themes/light/Alabaster-Light.png" width="534px"/></div>

Check out <a href="https://github.com/paintparty/fireworks?tab=readme-ov-file#theming" target="_blank">the other available themes here</a>

<br>

<h3>Enhanced contrast</h3>

You can set a `BLING_MOOD` env var to enhance the contrast of bling-formatted printings. 

Docs [here](https://github.com/paintparty/bling?tab=readme-ov-file#enhanced-contrast).
<br>

---

## Brandon Ringe: CALVA  
Q2 2025 $9K. Report 1, Published 13 May 2025.  

The new REPL output view was released! It's an opt-in feature for now and is not currently the default REPL output destination. It's currently in a very MVP state. I'll be fixing bugs and adding features in the coming weeks.  

This output view is web-based (a VS Code webview), and is implemented entirely in ClojureScript. We use [replicant](https://github.com/cjohansen/replicant) for the UI.  

There's [project board on GitHub](https://github.com/orgs/BetterThanTomorrow/projects/3/views/1) that I've been using to track my work on this. I wanted the issues to be draft issues until the first version was released. I'll be converting all of them to issues soon.  

Here are a few issues I intend to tackle in the coming weeks:  

- [Whitespace is not preserved in stdout in output view](https://github.com/BetterThanTomorrow/calva/issues/2825)
- [Allow writing to output view even if it's hidden](https://github.com/BetterThanTomorrow/calva/issues/2826)
- [Persist contents of output view between closing and reopening](https://github.com/BetterThanTomorrow/calva/issues/2827)

Documentation:  
- [The Output View](http://calva.io/output-view/)
- [Output Destinations Feature Comparison](https://calva.io/output/#output-destinations-feature-comparison)  

Thanks to Thomas Heller for helping us (some time ago) explore ways of integrating ClojureScript and TypeScript in Calva. Thanks to Christian Johansen for replicant.  <br>


---

## Bozhidar Batsov: CIDER  
Q2 2025 $9K. Report 1, Published 16 May 2025.  

The last month extremely fruitful for CIDER and friends and resulted in many notable releases and improvements. Below are some of the highlights:  

- [clojure-ts-mode 0.3](https://github.com/clojure-emacs/clojure-ts-mode/blob/main/CHANGELOG.md#030-2025-04-15)
	- lots of improvements to the font-locking and indentation logic
	- indentation rules are now consistent with cljfmt
- [clojure-ts-mode 0.4](https://github.com/clojure-emacs/clojure-ts-mode/blob/main/CHANGELOG.md#030-2025-05-15)
	- regular expressions are font-locked with a Tree-sitter grammar
	- adds support for aligning forms
	- implements many of the refactoring commands from `clojure-mode`
- [CIDER 1.18](https://github.com/clojure-emacs/cider/releases/tag/v1.18.0)
	- I also wrote a [blog post](https://metaredux.com/posts/2025/04/30/cider-1-18.html) about it
- [refactor-nrepl 3.11.0](https://github.com/clojure-emacs/refactor-nrepl/blob/master/CHANGELOG.md#3110)
	- Before this update the project was broken on Clojure 1.12
	- clj-refactor.el was also updated to use the latest version of the middleware

The were also several releases of cider-nrepl and orchard, related to CIDER 1.18. 
Work on CIDER 1.19 is already underway, and I'm also happy to report that `clojure-ts-mode`
is now in a pretty good shape for general use.  <br>

---


## Jeaye Wilkerson: Jank  
Q2 2025 $9K. Report 1, Published 7 May 2025.  

### Thank you!  
To start with, thank you, Clojurists Together, for the sponsorship this quarter.
This is the largest income I have this year, so far, so it really makes a
difference.  

### Seamless C++ interop  
This quarter, I'm focusing on seamless interop with C++ from jank. Doing this
from a lisp is unprecedented. One month in, I have implemented the following.  

1. JIT compiling arbitrary C++ code
2. Reaching into C++ values
3. Constructing stack-allocated C++ values
4. Overload resolution
5. Implicit conversions from jank objects into native types and vice versa
6. Explicit casts

You can read about all of these in detail on the jank blog
[here](https://jank-lang.org/blog/2025-05-02-starting-seamless-interop/).

### Next month  
We're one month into the quarter and I'm pleased with the progress so far.
However, there's a lot remaining work to do. I still need to tackle free/static
function calls, member access, member function calls, operators, dynamic
allocations, complex type support, and automatic destructors for locals with the
same guarantees C++ provides. On top of that, I need to make sure we can process
C++ headers in a portable way. This will definitely keep me busy for the quarter!
Stay tuned for my next update in a month.  <br>  

---

