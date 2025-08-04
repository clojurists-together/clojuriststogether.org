---
title: "July 2025 Short-Term Project Updates Q2 2025 Projects"
date: 2025-08-04T14:00:00+12:00
author: Kathy Davis
summary: "News from Bling, CALVA, CIDER, and Jank"
draft: True


---
This is the July and final project update for four of our Q2 2025 Funded Projects. (Reports for the other two are on a different schedule). A brief summary of each project is included to provide overall context. Thanks everyone for your awesome work!

[Jeremiah Coyle: Bling](#bling-jeremiah-coyle)  
Add support for using hiccup to style and format messages, a template string syntax to style and format messages, and 1-3 additional formatting templates for callouts, headers, and points-of-interest.  

[Brandon Ringe: CALVA](#calva-brandon-ringe)    
A new REPL output view for Calva, which is a webview in VS Code. The webview will allow us to add more rich features to the output webview, while also providing better performance.  

[Bozhidar Batsov: CIDER](#cider-bozhidar-batsov)   
Provide continued support for CIDER, nREPL and the related libraries (e.g. Orchard, cidernrepl, etc) and improve them in various ways.  

[Jeaye Wilkerson: Jank](#jank-jeaye-wilkerson)     
Build jank's seamless C++ interop system.  
<br>


## Bling: Jeremiah Coyle  
Q2 2025 $2k. Report No. 2, Published 14 July 2025  

<br>

I'm happy to report that all of the Q2 primary and secondary goals were completed. In addition, some tertiary goals and user requests that popped up along the way were addressed. Many thanks to Clojurists Together for supporting this work!
<br>
### Summary of goals achieved in Q2:  

- **Primary goals**

  - Enable Hi-Fidelity, theme-able, colorized, structural printing of values via Fireworks. 
[#29](https://github.com/paintparty/bling/issues/29). [Docs](https://github.com/paintparty/bling?tab=readme-ov-file#high-fidelity-printing)
  - Utilize `callout` to create a specialized template for Malli validation errors. 
[#28](https://github.com/paintparty/bling/issues/28).  <a href="https://github.com/paintparty/bling?tab=readme-ov-file#usage-with-malli">Docs </a>
  - Figlet banners with lolcat-style gradient coloring. [Docs](https://github.com/paintparty/bling?tab=readme-ov-file#figlet-banners)
  - Opt-in optimization for enhanced contrast in light or dark terminals. [#30](https://github.com/paintparty/bling/issues/30). [Docs](https://github.com/paintparty/bling?tab=readme-ov-file#enhanced-contrast)
  - Respect `NO_COLOR` env var [#27](https://github.com/paintparty/bling/issues/27). [Docs](https://github.com/paintparty/bling?tab=readme-ov-file#respecting-the-users-preference-for-no-color)

<br>

- **Secondary goals**

  - Add enhanced support for using hiccup to style and format messages. [#15](https://github.com/paintparty/bling/issues/15)
  - Create 1-3 additional formatting templates for [callouts](https://github.com/paintparty/bling?tab=readme-ov-file#with-theme-minimal), [headers](https://github.com/paintparty/bling?tab=readme-ov-file#side-label), and points-of-interest.
  - Add/update [documentation](https://github.com/paintparty/bling?tab=readme-ov-file#templates-for-errors-and-warnings) about how to leverage Bling to create great-looking warnings and errors.
 
<br>

- **Tertiary goals that arose in the course of solving the above**  
  - Enhanced test suite for JVM Clojure and Node (JS)  
  - Enhanced test suite for Babashka  
  - Hifi printing support for custom datatypes whose size cannot be determined [#69](https://github.com/paintparty/fireworks/issues/69)  
  
<br>

The latest release is [`v0.8.7`](https://clojars.org/io.github.paintparty/bling/versions/0.8.7), which features most of the enhancements listed above.  
<br>
<br>

Detailed descriptions and screenshots of the above features:  

<h3>Specialized template for Malli validation errors</h3>

[#28](https://github.com/paintparty/bling/issues/28)

This is experimental, with more work to be done on optimizing for disjunctions and cases with multiple errors on same value. <a href="https://github.com/paintparty/bling?tab=readme-ov-file#usage-with-malli">Docs here</a>

Bling offers `bling.explain/explain-malli` to present [Malli](https://github.com/metosin/malli) validation errors:

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/malli-explain-missing-key_dark_source-code-0.8.0.png" width="700px"/></div>

<br>
The above code would print the following:  
<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/malli-explain-missing-key_dark-0.7.0.png" width="700px"/></div>

You can also pass an option trailing options map to customize the appearance of the printed output. In the example below, we are leaving out the display of the schema within the callout block:  

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/malli-explain-bad-set-value-with-no-schema_dark_source-code-0.7.0.png" width="700px"/></div>


<br>
The above code would print the following:  

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/malli-explain-bad-set-value-with-no-schema_dark-0.7.0.png" width="700px"/></div>

<h3>High Fidelity Printing</h3>  

[#29](https://github.com/paintparty/bling/issues/29)  

<a href="https://github.com/paintparty/bling?tab=readme-ov-file#high-fidelity-printing">Docs here</a>

Bling offers `bling.hifi` for colorized pretty-printing of values. `bling.hifi/hifi` will return an ansi-sgr decorated string, while `bling.hifi/print-hifi` will print such a string.  

Under the hood, the formatting/colorizing is achieved with <a href="https://github.com/paintparty/fireworks" target="_blank">Fireworks</a>.  

By default, the theme of this output will be `Universal Neutral`.  

If you [set a valid `BLING_MOOD` env var](https://github.com/paintparty/bling?tab=readme-ov-file#enhanced-contrast), the theme of the `hifi` output will be `Alabaster Light` or `Alabaster Dark`.   

You can choose <a href="https://github.com/paintparty/fireworks?tab=readme-ov-file#theming" target="_blank">one of the other available themes</a> by following the instructions in the Fireworks readme and <a href="https://github.com/paintparty/fireworks?tab=readme-ov-file#step-3" target="_blank">setting up a `config.edn` on your system</a>, with a corresponding `BLING_CONFIG` env var. This config will also let you control many other aspects of the formatting with the `hifi` output.  

Check out <a href="https://github.com/paintparty/fireworks?tab=readme-ov-file#theming" target="_blank">the other available themes here</a>  


<br>

<div align="center"><sub><b><i>Alabaster Dark&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i></b></sub></div>
<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/themes/dark/Alabaster-Dark.png" width="534px"/></div>  

<div align="center"><sub><b><i>Alabaster Light&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i></b></sub></div>
<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/themes/light/Alabaster-Light.png" width="534px"/></div>

<br>


<h3>Figlet banners:</h3>
<a href="https://github.com/paintparty/bling?tab=readme-ov-file#figlet-banners">Docs here</a>

<br>
<br>

Bling now features basic support for composing <a href="https://en.wikipedia.org/wiki/FIGlet" target="_blank">Figlet</a> ascii-art
banners with <a href="https://github.com/busyloop/lolcat" target="_blank">lolcat-like gradient overlays</a>. Bling ships with a
small handful of ported Figlet fonts. The glyph layout implementation is bare-bones and there is currently no support for standard figlet
smushing. Figlet banners only work in terminal context (JVM Clojure or Node.js ClojureScript).

<p align="center">
  <img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/bling-banner-fonts_dark-0.7.0.png"
       width="700px" />
</p>

<br>


<h3>Enhanced contrast</h3>  

[#30](https://github.com/paintparty/bling/issues/30)  

Docs [here](https://github.com/paintparty/bling?tab=readme-ov-file#enhanced-contrast).  

You can set a `BLING_MOOD` env var to enhance the contrast of Bling-formatted output. Only takes effect on your system, for your eyes only. 

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/colors-contrast-scale_dark-0.8.7.png" width="700px"/></div>

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/colors-contrast-scale-0.8.7.png" width="700px"/></div>



<br>

### Respect `NO_COLOR` env var  

[#27](https://github.com/paintparty/bling/issues/27)  

You can set a `NO_COLOR="true"`env var, which will disable any colorization on from any output generated by Bling. The de-colorization only applies locally, for your eyes only. More info on the [informal standard](https://no-color.org/).

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/no-color-callout_dark-0.8.7.png" width="700px"/></div>

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/no-color-callout-0.8.7.png" width="700px"/></div>



<br>

### Enhanced support for hiccup  

[#15](https://github.com/paintparty/bling/issues/15)

Deeper hiccup support for a cleaner template syntax

- Utilize `[:br]`tags for line breaks
- Introduce a `[:p]`tag, to enclose logical paragraphs and automatically add trailing newlines.
- Properly support nesting of styles ala `[:bold "Bold, " [:italic "bold italic, "] [:red "bold red, "] "bold."]`


```Clojure
'(require bling.core :refer [print-bling])

(println "\n\n")
(print-bling [:p "First paragraph"]
             [:p [:bold
                  "Bold, "
                  [:italic "bold italic, "
                   [:red "bold italic red, "]]
                  "bold."]]
             "Last line")
```

The above code renders the following:

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/nested-styles-example-0.9.0.png" width="700px"/></div>


An initial working implementation of the above is complete on branch `0.9.0`. Next step will be to consider alternate implementations (perf), test, refine, then merge into main branch.


<br>

### New callout templates  

Callout docs [here](https://github.com/paintparty/bling?tab=readme-ov-file#callout-blocks)

New callout template with `{:theme :minimal}`:

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/callouts_minimal_dark.png" width="700px"/></div>

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/callouts_minimal_light.png" width="700px"/></div>

<br><br>
New option `:side-label` which may be useful for file info etc.

With `{:theme :sideline :label-theme :marquee :side-label "foo.core:11:24"}`: 

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/callouts_sideline_marquee_side-label_dark.png" width="700px"/></div>

<br>
<br>

### Enhanced support & improved docs for creating custom and warning callouts  

`bling.core/callout` now accepts any number of arguments, which makes the templatization a little more flexible:
```Clojure
(defn my-error-callout [{:keys [header body source]}]
  (callout {:type        :error
            :padding-top 1}
           header
           source
           body))

(my-error-callout
 {:header "Your header message goes here\n"
  :source (point-of-interest 
           {:type                  :error
            :file                  "example.ns.core"
            :line                  11
            :column                1
            :form                  '(+ foo baz)
            :text-decoration-index 2})
  :body   (str "The body of your template goes here.\n"
               "Second line of copy.\n"
               "Another line.")})
```

The above callout would render like this your terminal emulator: 


<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/callout-with-poi_sideline_minimal_dark.png" width="700px"/></div>

<div align="center"><img src="https://github.com/paintparty/bling/raw/main/resources/docs/chromed/callout-with-poi_sideline_minimal_light.png" width="700px"/></div>

<br>
<br>

### Hifi printing support for custom datatypes  
This was a bug fix + enhancement [#69](https://github.com/paintparty/fireworks/issues/69) in the upstream Fireworks lib, which enables the representation of custom datatypes when printing with `bling.hifi/hifi`


<br>

### Enhanced test suites  
A new suite of tests for both JVM Clojure and [Babashka was added](https://github.com/paintparty/bling/commit/baf2f9685ea1d2b9ffb7fc55f86d59c0247ae5fc). Docs [here](https://github.com/paintparty/bling?tab=readme-ov-file#testing).  <br>

---

## CALVA: Brandon Ringe  
Q2 2025 $9k. Report No. 3, Published 13 July 2025  

Since the last report, I spent some time adding content reloading support to the output view. By that I mean that when the output view is closed and reopened, and when the VS Code window is reloaded, the output view would reload its contents. However, this became problematic in multiple ways, so I decided to remove the feature in favor of reducing maintenance burden. The benefit of the feature did not outweight the cost of maintaining it, in my opinion. [The PR for removing it](https://github.com/BetterThanTomorrow/calva/pull/2896) can serve as a reference for anyone who might want to try implementing the feature in the future.  

Here's the list of changes to the output view that have happened since the last report:  

- [Reload contents of output view when VS Code window is reloaded](https://github.com/BetterThanTomorrow/calva/issues/2827)  
- [Reload contents of output view when it's closed and reopened (without closing VS Code)](https://github.com/BetterThanTomorrow/calva/issues/2867)  
- Fix: [After VS Code reload a new separate output view is opened on connect when one is already open](https://github.com/BetterThanTomorrow/calva/issues/2883)  
- Fix: [Output view sometimes opens with broken code highlighting](https://github.com/BetterThanTomorrow/calva/issues/2870)  
- [Remove content reloading from output view](https://github.com/BetterThanTomorrow/calva/issues/2894)  
- Fix: [Output view syntax highlighting breaks when it's dragged into or out of main VS Code window](https://github.com/BetterThanTomorrow/calva/issues/2895)  

Thank you to Clojurists Together and its members for supporting this work! I hope Calva users will enjoy using this output view.  <br>  

---

## CIDER: Bozhidar Batsov  
Q2 2025 $9k. Report No. 3, Published 16 July 2025  

This month wasn't as productive as the last two (mostly due to a bit of vacation time and my seasonal allergies draining my energy), but we still made some good progress:  

- [CIDER 1.19 ("Skiathos")](https://github.com/clojure-emacs/cider/releases/tag/v1.19.0)  
	- The highlight of this release is that Java source downloading is now enabled by default, as we're feeling quite confident about its stability  
	- There are also plenty of small improvements here and there (mostly in the inspector)  
	- You can now also specify default connection params, which will save you a bit of typing in certain cases  
- clojure-ts-mode  
	- Small improvements to the built-in completion source  
	- Added the clojure-mode functionality for browsing the Clojure guides and reporting issues  

There are a lot more things in progress that I didn't have manage to get to the finish line (or remained only the realm of research for the time being):  
- A new starter configuration for CIDER users (to be released soon)  
- I looked into replacing Antora (the docs engine CIDER and friends are using) with mdBook (made popular by Rust)  
	- I'm on the fence about this and I probably will stick with Antora  
- I've started work on a couple of blog posts (e.g. the long overdue analysis of the CIDER survey results), but I didn't manage to finish them. Coming soon, though!  
- I've encountered a few small blockers while working on removing the hard dependency on clojure-mode in CIDER, but we'll get there  
- I've played with some screencast apps to record some new CIDER tutorials and I think I have a winner with which to proceed  

Anyways, I plan to drive those over the finish line and once again I'm I'd like to thank the members of "Clojurists Together" for their support of my work on Clojure development tooling. You rock!  <br>

---

## Jank: Jeaye Wilkerson  
Q2 2025 $9k. Report No. 3, Published 11 July 2025  

Hi folks! Thank you so much for the sponsorship this quarter. jank has grown
from not being able to reach into C++ at all, in April, to now working with some of
the most popular C++ libraries. This past month, the highlights are:  

1. Added support for manual memory management via `cpp/new` and `cpp/delete`  
2. Added support for explicit C++ bools via `cpp/true` and `cpp/false`  
3. Added complex type literals via `(cpp/type "std::map<std::string, int>")`   
4. Added opaque boxing of native values via `cpp/box` and `cpp/unbox`  
5. Greatly improved stability and portability  

Now, at the end of the quarter, jank's seamless C++ support is incredible. For
all of the details, you can check out the related blog post here:  
https://jank-lang.org/blog/2025-07-11-jank-is-cpp/

There's still so much work to do, so I hope to write to you again next quarter!  







