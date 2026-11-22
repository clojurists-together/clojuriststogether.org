---
title: "Nov. 2026 Short Term Project Updates"  
date: 2026-11-21T14:00:00+12:00  
author: Kathy Davis  
summary: News from Jeremiah Coyle, Ethan Miller, John Newman, Peter Taoussanis   
draft: True  



---
Here are the first updates for projects funded in Q3 2026.  You can find overviews of these projects in the [original funding announcement](https://www.clojuriststogether.org/news/q3-2026-funding-announcement/). There is a lot to dig into here - Thanks everyone!   

- [Jeremiah Coyle: Documentation and Design Services for Key Community Projects: Malli, Squint, Flowstorm, ECA](#design-services-for-key-community-projects-jeremiah-coyle)  
- [Ethan Miller: Tablecloth](#tablecloth-ethan-miller)    
- [John Newman: EVE (Extended Value Encoding)](#eve-extended-value-encoding-john-newman)  
- [Peter Taoussanis: Finish Carmine 4](#finish-carmine-4-peter-taoussanis)  <br>

<br>


## Design Services for Key Community Projects: Jeremiah Coyle   
Q3 2026 $9K Report 1. Published November 1, 2026   

**Focus for October 2026:**
- **Squint Playground design updates**
- **ECA docs/marketing site design updates.**

<br>



### Squint Playground UI Updates, Part 1
#### [PR #989](https://github.com/squint-cljs/squint/pull/989)


- Add standalone main.css
- Add some initial global css vars (light/dark mode friendly)
- Minor tweaks to size, layout, labels of buttons and repl mode control in sidebar
- De-emphasized panel borders
- Fix clipping (by scrollbar) of panel borders in right corners
- Remove visual noise created by light background color of gutter in compiled code block
- Add `position: fixed` mini-header in top right with “Squint Playground” label,  star counter, and light/dark theme toggle icon button. The toggle is hidden for now until the codemirror theme reloading is working (that can happen later), but checkout screen recording for preview of the dark mode. The event listener for the toggle is just in a script tag at end of body, there is probably a better place for it.
2312  

<img width="800" alt="phase-1-before-after-white" src="https://github.com/user-attachments/assets/948511aa-efbe-4aa1-857a-5e04d6dc86f7" />


<hr>


### Squint Playground UI Updates, Part 2
#### [PR #1045](https://github.com/squint-cljs/squint/pull/1045)

<br>

All features and fixes included are described in the sections below.<br>

- **Global layout**
- **Draggable pane resizing**
- **Compiled code visibility toggle**
- **Codemirror dark-theme**
- **OS color scheme sync**
- **Native tooltip component**
- **Unified text selection styling**
- **Misc**

<br>

#### Global layout and framing refactor

Fixes:
- Scrolling behavior
- Superfluous borders
- Inconsistent vertical space below main section

##### Before:
<img width="798" alt="Before: global layout and framing refactor" src="https://github.com/user-attachments/assets/10b1884c-b27d-4e03-b678-5a432bad6ef1" />

##### After:
<img width="798" alt="After: global layout and framing refactor" src="https://github.com/user-attachments/assets/4e1c7be6-b3c4-4e53-a67d-9c118b20bdb2" />


##### Related

- Fix pane scrolling mechanics (.cm-scroller horizontal scrollbar not in view)
- Optimize scrollbar styling

<br>

### Draggable resizing of editor and compile panes
<img width="798" alt="Draggable resizing of editor and compile panes" src="https://github.com/user-attachments/assets/0641bbeb-b354-4163-9ee2-86b1c46de6cf" />

##### Related
- Add Playwright tests for pane resizing

<br>
<br>

### Compiled code section visibility toggle
<img width="798" alt="Compiled code section visibility toggle" src="https://github.com/user-attachments/assets/4074bbb2-b1a3-411d-8afb-3b1c93ae9ff1" />

<br>
<br>
<br>

### Support codemirror dark theme across editor, repl output, and compiled code instances
<img width="798" alt="CodeMirror dark theme across editor, repl output, and compiled code instances" src="https://github.com/user-attachments/assets/b103dfb4-bf7e-4278-9945-2f736d1f0a45" />


<br>
<br>

### Respect OS color scheme for initial theme state
<img width="798" alt="Respect OS color scheme for initial theme state" src="https://github.com/user-attachments/assets/048cb6e3-5611-4681-954d-364b636c8dc2" />

<br>
<br>

### Native tooltips optimized for light-dark themes
<img width="798" alt="Native tooltips optimized for light-dark themes" src="https://github.com/user-attachments/assets/6beaa800-3177-48d5-b226-6f52a6d501b0" />

<br>
<br>
<br>

### Unified text selection styling
<img width="798" alt="Unified text selection styling" src="https://github.com/user-attachments/assets/c0ace075-abb5-429e-b6c2-aaca38a34c30" />

##### Related

- Add Playwright tests for text selection

<br>
<br>

### Misc

- Normalized, uniform font size across all elements
- Add pnpm instructions to readme (i couldn't get it running w/o using `pnpm install`)
- Add bb task to stop the port 5173 listener (for detached/leftover server scenario)
- Add local (default) tictactoe example, and make it dark-mode friendly
- Added a small handful of [Radix Icons](https://www.radix-ui.com/icons) (MIT license) to `playground/public/graphics/icons`


<hr>

<br>

### Squint Playground UI Updates, Part 3
#### [PR #1056](https://github.com/squint-cljs/squint/pull/1056)
- [Layout](#layout)
- [Output pane](#output-pane)
- [Example menu](#example-menu)
- [Examples](#examples)
- [Advent of Code](#advent-of-code)
- [Error Slot](#errror-slot)
- [Misc](#misc)

This introduces a more flexible layout for the Squint playground.
- Examples render in a resizable DOM frame
- The output pane is split into accordion sections
- The footer now holds the theme toggle and layout controls
- Every example is built in and selectable via a popover/dropdown menu

<br>

### Layout

- Changed: Examples render into `#app` inside a resizable DOM frame above the code and output panes, with a size readout and drag handles on the sides, bottom and bottom corners
- Changed: The DOM frame can sit above the panes, move into a section at the top of the output pane, or fill the screen, picked from the layout icons in the footer
- Changed: Switching examples keeps the DOM frame at its previous height while the next example loads, then resizes it to fit
- Changed: Control bar buttons have a pill-shaped hover background
- Changed: Pane borders, scrollbars and the draggable divider share one opaque border color, so crossing lines no longer brighten where they meet

<br>

#### Global layout

**Before**: Two panes, with the controls in a row at the top of the output pane.<br>
**After**: Example menu above the code pane, Compile and Share above the output pane, and a footer.<br>
<br><img width="798" alt="Before: Two panes, with the controls in a row at the top of the output pane. After: Example menu above the code pane, Compile and Share above the output pane, and a footer." src="https://github.com/user-attachments/assets/02869f93-4aa4-4ffd-99dc-3af1fe8c84dd" />

<br>

#### Code pane

**Before**: Editor only.<br>
**After**: "Select example" bar above the editor.<br>
<br><img width="520" alt="Before: Editor only. After: &quot;Select example&quot; bar above the editor." src="https://github.com/user-attachments/assets/76f80e8c-a8a4-40e1-ad11-f0e87247f9e6" />

<br>

#### Adjustable DOM frame

Resizing the DOM frame from its side, bottom and corner handles:
<img width="798" alt="Resizing the DOM frame from its side, bottom and corner handles" src="https://github.com/user-attachments/assets/3d78eb18-afb5-4adc-b794-0bba0c381eb1" />

<br>

#### DOM layout options

The DOM frame can now go in 3 different positions:
- At the top
- On the right, in the output pane section
- Full screen<br>

Layout selector in footer:
<img width="418" alt="Switching the DOM frame between above the panes, the output pane section and full screen" src="https://github.com/user-attachments/assets/fb876812-05ea-4fe2-91b3-093e2a6b8f90" />

Switching the DOM frame between the top pane, the output pane section, and full screen:
<img width="798" alt="The DOM frame above the panes, in the output pane section and full screen" src="https://github.com/user-attachments/assets/869f66d9-bb7d-4d1e-a602-e990d9e448f9" />

<br>

### Output pane

- Changed: REPL-mode and Compiled JS are collapsible sections with full-width headers
- Changed: REPL-mode helper text rewritten
- Changed: Compile and Share sit in a control bar at the top, and "Blank AOC Playground", the theme button and the GitHub star moved out

<br>

**Before**: Buttons in a row, REPL-mode checkbox and "Compiled code".<br>
**After**: Control bar, then REPL-mode and Compiled JS sections.
<br><img width="796" alt="Before: Buttons in a row, REPL-mode checkbox and &quot;Compiled code&quot;. After: Control bar, then REPL-mode and Compiled JS sections." src="https://github.com/user-attachments/assets/f6223a80-a531-4d47-8482-ad4e6bc110bf" />

<br>

Collapsing and expanding the REPL-mode and Compiled JS sections:
<img width="797" alt="Collapsing and expanding the REPL-mode and Compiled JS sections" src="https://github.com/user-attachments/assets/436184f6-1f1b-49ce-867d-71652882e0d3" />

<br>

### Example menu

- Added: "Select example" opens a popover menu with "DOM examples", "Examples (no DOM)" and Advent of Code columns, and marks the current example
- Added: Small screens get a native select with the same groups
- Added: Every example is built in and loads from `?example=<name>`, and the README links use these URLs instead of encoded `?src=` links and gists

<br>

Opening the example menu and picking an example:
<img width="798" alt="Opening the example menu and picking an example" src="https://github.com/user-attachments/assets/4a40a1e6-1805-4e96-8e9d-0ded6fe5f4be" />

<br>

#### Error Slot

Runtime errors were being rendered into the REPL result box, which is meant for the value of the evaluated form, and it is hidden when REPL-mode is off. So an error with no source location, such as a module that fails to resolve, was invisible unless user opened the dev console. Errors with a location still got an underline in the editor, but nothing told you what went wrong.

Errors now have their own slot in output pane, below the `Compiled JS` section:

- Shows only when the most recent run failed, and clears at the start of the next run
- Works the same with REPL-mode on or off
- Grows up to half the space below the Compiled JS header, then scrolls
- Errors with a source location are also underlined in the editor, as before
- The full stack trace still goes to the dev console
- Foreground color, background color, and red error icon colorways are adaptive for light/dark mode

Errors thrown later, from event handlers or animation frames, still only appear in the dev console.

Example error from unresolve module:
<img width="1596" height="909" alt="error-slot-white" src="https://github.com/user-attachments/assets/24518601-613d-4726-ba60-4bab37674153" />


### Examples

- Added: Built-in examples: Pinball, Tic-tac-toe, Wordle, React, Preact, Preact + hiccup, MobX, MobX + atoms, SolidJS, Vue.js, Mithril, VanJS, Web components, Lit, Quill, Vega-lite, Game of life, Snabbdom, Ohm's law, Idiomorph, Eucalypt, docx and Immer
- Added: Examples without DOM output: edn-data, TC39 Records and Tuples, Immutable-js, Date-fns, defmulti, mutative, Generators and Loading a UMD module
- Changed: Examples draw into `#app`, the playground's convention for DOM output
- Changed: Pinball is redrawn in one orange: ring bumpers that glow and show their points on a hit, a rounded table outline, a slowly rotating lambda drawn as three bands, and a default height of `555px`
- Changed: Pinball restarts with the space bar as well as R
- Changed: Buttons, text fields and sliders in examples share one style that works in light and dark: foreground text on a translucent gray, thin gray slider tracks, and no 2px press dip or focus ring on click
- Changed: Ohm's law calculators have more room between sliders
- Fixed: Mithril and MobX examples were broken. They should now work again.
- Fixed: Vega-lite no longer cuts off its actions button or shows scrollbars with nothing to scroll
- Fixed: Examples that were unreadable in dark mode use colors that work in both themes: Mithril, Vue.js, VanJS, React, Preact, Ohm's law, Snabbdom, Eucalypt, Idiomorph and Quill
- Fixed: Game of life uses a white background in light mode
- Fixed: Wordle is adaptive light / dark mode
- Fixed: Rough.js is adaptive light / dark mode
- Fixed: Pinball left flipper active on touch device

<br>

#### Pinball table example, upgrades

- Single color
- Unified stroke width
- Bumpers light up & show value
- Lambda bumper spins
- Chromed balls
- Tapered flippers
- Space bar restarts game.
- Higher scores

<img width="520" alt="The new pinball table in dark mode" src="https://github.com/user-attachments/assets/5d3553bb-7dae-47ef-9046-d2cbaf281ebb" />

<br>

### Advent of Code

- Changed: The AOC sections are consolidated to the DOM stage area at the top of the viewport, and its helper text is a link that searches for the token instructions in a new tab

<br>

### Misc

- Fixed: No console error when a page has no `data-tip` elements


<br>

<hr>

<br>

### ECA docs site updates, phase 0

This is a lightweight first pass. It covers some basic typography, legibility, icons, sidebar and table of contents styling, and layout cleanup on the homepage and install page. It also sets up a visual changelog in `docs/evolution/changes` to track ui changes going forward (optional).

Summary of the main things that were changed, detailed in the sections below:
- [Documentation page typography](#documentation-page-typography)
- [Sidebars and table of contents](#sidebars-and-table-of-contents)
- [Homepage icons and feature cards](#homepage-icons-and-feature-cards)
- [Install page](#install-page)
- [Protocol page arrows](#protocol-page-arrows)
- [Misc](#misc)

Screenshots compare the last commit (before) with the working tree (after), both built with `mkdocs-material` 9.6 at 1440x900.

<br>

### Documentation page typography

- Changed: Inter typeface as the site font, loaded through `theme.font`, plus Inter typeface `600` from Google Fonts for headings
- Changed: Article column capped at `700px`, body text `0.85rem` (`17px`), line height `1.7`
- Changed: Headings in `rem` so they no longer scale with body text: `h1` `36px`, `h2` `24px`, `h3` `20px`, all weight `600`, color `#333`, letter spacing `-0.025em`
- Changed: Body links use the text color at weight `600` with a gray underline set `0.25em` below the text, darkening on hover
- Changed: Hover and focus accent is black instead of accent (teal)
- Changed: Content tab labels keep their accent (teal) through a dedicated `--eca-teal` token

<br>

<br>

#### Docs page

**Before**: Roboto typeface and accent (teal).<br>
**After**: Inter typeface and neutral.<br>
<img src="https://github.com/user-attachments/assets/96c9d98c-f844-4c4c-9672-e0c13d56adfe" alt="Before: Roboto typeface and accent (teal). After: Inter typeface and neutral." width="798">



<br>

<br>

#### Article

**Before**: Accent (teal) headings and links.<br>
**After**: Inter typeface and underlined links.<br>
<br>
<img src="https://github.com/user-attachments/assets/b2c54a50-2176-4933-8823-ef345ed7024f" alt="Before: Accent (teal) headings and links. After: Inter typeface and underlined links." width="798">


<br>

### Sidebars and table of contents

- Changed: Items indented `1.3em` with a faint left rule that lines up with the section heading
- Changed: About 20% more vertical space per item
- Changed: Active page bold with a dark segment on the rule, instead of accent (teal) text
- Changed: Top-level links weight `500`, nested links weight `400` at opacity `0.8`
- Changed: Section heading indented `0.9em`, dark gray instead of accent (teal), hover is black

<br>

<br>

#### Sidebar

**Before**: Accent (teal) and flat.<br>
**After**: Indented with rule.<br>
<br>
<img src="https://github.com/user-attachments/assets/fa47498d-6a0b-4037-b7db-02e2383137a0" alt="Before: Accent (teal) and flat. After: Indented with rule." width="346">


<br>

<br>

#### Table of contents

**Before**: Accent (teal).<br>
**After**: Indented with rule.<br>
<img src="https://github.com/user-attachments/assets/9d802712-3fb4-470c-96d8-bbb001c08ba0" alt="Before: Accent (teal). After: Indented with rule." width="346">


<br>

### Homepage icons and feature cards

- Changed: All emoji replaced with Lucide icons (`lucide-static` 1.57.0, ISC license) in `docs/overrides/.icons/lucide`, drawn at `stroke-width: 1.5`
- Changed: "Free & Open Source" uses the Lucide `git-fork` icon instead of the oversized green OSI logo
- Changed: Top three feature cards use the same icon-left, two-column layout as the cards below
- Changed: Card text one step tighter: line height `1.45` and `1.4`
- Changed: Homepage accent tokens (`--eca-brand-*`) neutral (grays and black) instead of accent (teal)

<br>

<br>

#### "Why ECA?"

**Before**: Emoji icons.<br>
**After**: Lucide icons.<br>
<img src="https://github.com/user-attachments/assets/2b76f8e5-6f05-43af-931e-0ca9ce57cd05" alt="Before: Emoji icons. After: Lucide icons." width="796">


<br>

<br>

#### Feature cards

**Before**: Emoji icons and stacked.<br>
**After**: Lucide icons and two columns.<br>
<img src="https://github.com/user-attachments/assets/01a3b470-aa34-4894-be15-1efb28c537ab" alt="Before: Emoji icons and stacked. After: Lucide icons and two columns." width="798">


<br>

<br>

#### Config tabs

**Before**: Emoji icons.<br>
**After**: Lucide icons.<br>
<img src="https://github.com/user-attachments/assets/b20332a1-8009-40df-8222-960d265dc23e" alt="Before: Emoji icons. After: Lucide icons." width="798">


<br>

### Install page

- Changed: Intro, question, tabs, "Install the ECA server manually" section and "Something not working?" section share one `60rem` left edge
- Changed: "Auto install" callout full width with a Lucide `info` icon
- Changed: "Where do you want to use ECA?" matches the doc page `h1`
- Changed: Plugin copy split into two paragraphs, dash replaced with a period
- Changed: "Install the ECA server manually" section no longer a hidden toggle, always shown
- Changed: Editor tabs and manual install tabs share one underline style with accent (teal) labels
- Changed: "Something not working?" section is a bulleted list with the doc page link style

<br>

<br>

#### Install page

**Before**: Centered intro.<br>
**After**: Left aligned.<br>
<img src="https://github.com/user-attachments/assets/d7d1064d-ae96-46b6-bf65-c1e213b5a66d" alt="Before: Centered intro. After: Left aligned." width="798">


<br>

<br>

#### Install intro

**Before**: Centered and narrow.<br>
**After**: Left aligned and full width.<br>
<img src="https://github.com/user-attachments/assets/29a44079-b7f8-47af-ace6-c926b1dd56b9" alt="Before: Centered and narrow. After: Left aligned and full width." width="798">


<br>

<br>

#### Plugin copy

**Before**: One paragraph.<br>
**After**: Two paragraphs.<br>
<img src="https://github.com/user-attachments/assets/71a61ca8-f3dc-48d2-965b-1089c39c3ebf" alt="Before: One paragraph. After: Two paragraphs." width="798">


<br>

<br>

#### Manual install

**Before**: Collapsed toggle click target is too distant, small, and faint: the user may miss it.<br>
**After**: The instructions are always open and visible. Reduced noise on the section heading.<br>
<img src="https://github.com/user-attachments/assets/8cdfc6a2-3dfe-49eb-a1c2-733061b586cd" alt="Before: Collapsed toggle click target is too distant, small, and faint: the user may miss it. After: The instructions are always open and visible. Reduced noise on the section heading." width="798">


<br>

<br>

#### Help

**Before**: Centered sentence.<br>
**After**: Bulleted list with aligned hyperlink styling.<br>
<img width="798" alt="Before: Centered sentence. After: Bulleted list with aligned hyperlink styling." src="https://github.com/user-attachments/assets/e8c2e14b-2fa9-4f69-93cb-f6e6e1311aac" />



<br>

### Protocol page arrows

- Changed: Emoji arrows replaced with plain Unicode text arrows in 61 headings: → ← ↩ ↪

<br>

<br>

#### Protocol headings

**Before**: Emoji arrows.<br>
**After**: Text arrows.<br>
<img width="336" alt="Before: Emoji arrows. After: Text arrows." src="https://github.com/user-attachments/assets/365ae05d-29d3-4e48-8bb2-d992eb2fc2f6" />



<br>

### Misc

Header, homepage hero and footer keep their original accent (teal).


<br>

<hr>

<br>

### ECA docs site, phase 1: persistent docs sidenav

The left menu now shows the whole docs map on every page, and the left menu and the table of contents share one set of styles.

- [Top bar](#top-bar)
- [Documentation menu](#documentation-menu)
- [Menu items](#menu-items)
- [On This Page](#on-this-page)
- [Install page](#install-page)
- [Misc](#misc)

<br>

### Top bar

- Changed: "Features" tab replaced by a "Documentation" tab, which opens the Install page
- Changed: Install, Configuration, Protocol and Troubleshooting stay in the bar and link to the same pages as before
<img width="730" alt="Home, Documentation, Install, Configuration, Protocol, Troubleshooting, Plugins and Support Us" src="https://github.com/user-attachments/assets/ebc5167b-22c2-48ee-8c4c-b9d0cb917692" />

<br>

<br>

### Menu items

Both menus, the left menu and the right-hand table of contents, use the same styles.

- Changed: Left rule is `1px` and `#00000024`, one unbroken line at every depth. Nested text indents `0.6rem + 0.5ch` per level and the rule stays put
- Changed: Rule darkens to `rgba(0, 0, 0, 0.44)` on hover, and is black on the current item and its top-level parent
- Changed: Every item is `38px` tall, top-level and nested alike
- Changed: Top-level text is `rgb(27, 27, 27)` and nested text is `rgb(67, 67, 67)`, both black on hover
- Changed: Current item keeps the weight of its neighbors and gets an `hsl(0, 0%, 96%)` background instead of bold text
- Changed: Both headings have the same height and the same space below them
- Fixed: Menus no longer flash a scrollbar on page load. Material's `-.4rem` nav margin let the list overflow by `8px` until the sidebar was sized

<br>

<br>


### Persistent documentation sidenav

- Changed: Left menu lists the whole docs map under one "Documentation" heading on every page, instead of only the pages of the current tab
- Changed: Order is Install, Features, Workflows, Configuration, ECA Protocol, "read-chat CLI" and Troubleshooting
- Changed: Only Configuration collapses, with its 19 pages. The other items are plain links
- Changed: Collapsible entries show Material's arrow to the left of the label, centered between the rule and the text, turning down when open

<br>

<br>

Configuration open with the current page highlighted, the other items plain links:<br>
<img width="332" alt="Configuration open with the current page highlighted, the other items plain links" src="https://github.com/user-attachments/assets/66297773-7616-4545-ae3f-4b525d055813" />

<br>

<br>

### "On This Page" (formerly "Table of Contents")

- Added: Menu scrolls to keep the current entry in view, with `toc.follow`
- Changed: Heading reads "On This Page" instead of "Table of contents"
- Changed: Menu is `14.2rem` wide instead of `12.1rem`, growing toward the article, so fewer long entries wrap
- Fixed: Clicking an entry near the end of a page highlights that entry. It used to highlight a later one, often the last, because the page cannot scroll that heading to the top

<br>

<br>

<img width="372" alt="Wider menu with the current entry highlighted" src="https://github.com/user-attachments/assets/e6e63883-acc1-4098-aa44-22299a6affab" />

<br>

<br>

### Misc

- Added: `extra.toc_collapse` in `mkdocs.yml`, off, collapses all but the current section of "On This Page" with arrow buttons. Script is `docs/js/toc-collapse.js`. This is NOT enabled, but functionality is in place if it is ever needed in the future.
- Added: `extra.nav_open_first` in `mkdocs.yml`, off, makes a collapsed menu group open its first page when clicked. Script is `docs/js/nav-open-first.js`. This is NOT enabled, but functionality is in place if it is ever needed in the future.
- Fixed: Menu text no longer shifts weight while pages load. The site used Inter `500` and `600` without loading them, so the browser swapped in a nearby weight. One variable Inter file now covers `300` to `700`. <br>

<br>

---



## Tablecloth: Ethan Miller    
Q3 2026 $9K Report 1. Published November 1, 2026  


### Overview

This grant continues work on Tablecloth's Column API that I added [in 2022](https://humanscodes.com/columns-for-tablecloth-i) thanks to a previous Cojurists Together grant. The Column API is the part of Tablecloth that lets you work directly with columns (math, statistics, missing values) rather than whole datasets. My three goals for the current grant are designed to solidify and enhance this API within Tablecloth. They include:  

1. A fuller tutorial that showcased what the Column API can do and when it comes in handy.
2. Making the code generation behind the Column API more transparent and easier to work with.
3. "Lifting" more operations from the lower-level processing library dtype-next into the Column API.

Much of both the Column API and the Dataset API is produced by a code generator: templates that write out the public functions so we don't have to write hundreds of near-identical wrappers by hand.  Before writing a tutorial or adding more operations, I wanted that machinery to be easy to run, safe, and checked automatically, because every later change depends on it. So October went mostly into goal 2.  

### Planning and community  

- Re-familiarized myself with the codebase after a long break, and mapped where and how code generation is used across the project.
- Broke the code-generation cleanup into a sequence of small, reviewable steps (described below), and sketched a plan for the Column API tutorial.
- Joined the Scicloj real-world-data meeting to talk about Tablecloth and the grant.
- Read through recent Clojurians Zulip discussions to collect problems people have hit with Tablecloth, so the work lines up with real pain points.  

### Code generation work  

**Merged: Column API no longer loads the whole Dataset API ([#220](https://github.com/scicloj/tablecloth/pull/220))**
The Column API's missing-value code pulled in the entire generated Dataset API just to use two small helpers. Now it requires only those helpers. Loading the Column API is lighter, and regenerating it no longer drags in unrelated code. Behavior is unchanged, and a new test guards against the dependency coming back.  

**In review: one command to regenerate everything ([#221](https://github.com/scicloj/tablecloth/pull/221))**
Regenerating the four generated API files used to mean opening the source and evaluating four separate `comment` blocks by hand. Now a single command, `clojure -M:regen`, does it all:  

- *Safe:* it backs up all four files first and restores them if anything fails, since the generators wipe their target file before writing.  
- *Repeatable:* running it twice gives byte-identical output. Before, generated code used random local names, so every run produced noisy diffs.  
- Running it also caught some generated files that had quietly gone stale (missing docstrings and linter metadata), which this PR brings up to date.  

**In review: CI check for stale generated files ([#222](https://github.com/scicloj/tablecloth/pull/222))**
Until now nothing stopped a hand edit to a generated file, or a template change without regenerating, from being merged unnoticed. This adds a check on pull requests that regenerates the files and fails if they differ, naming the stale file and telling the contributor to run `clojure -M:regen`. It is read-only, so it is safe to run on pull requests from forks.  

**In review: generated code no longer loads the generator ([#223](https://github.com/scicloj/tablecloth/pull/223))**
Every generated column operator called a helper that lived inside the code generator, so using Tablecloth at runtime also loaded the build-time generator. Some generated namespaces also relied on other namespaces they never required, which only worked because the generator happened to load them. This moves the helper to a runtime namespace and makes each generated file require exactly what it uses. The public API is unchanged.  

**Docstring audit**
I audited the docstrings in the generated APIs. That turned up several problems, which I've ordered into follow-up work for November:  

- Pass options through correctly in the Dataset aggregators.
- Fix or remove the Dataset percentiles function.
- Rewrite the Column and Dataset operator docs.
- Correct the docs on exported functions.
- Add a CI check for docs.

### Lag and lead  

`lag` and `lead` shift a column's values up or down, which is common in time-series work (comparing each value to the previous one, for example). I first improved these in tablecloth.time ([#67](https://github.com/scicloj/tablecloth.time/pull/67)):  

- About 10–100× faster on a million rows (for example, roughly 190 ms down to 1.4 ms for a float column), by using typed column operations instead of lazily rebuilding each column.
- A new `:fill` option fills only the rows that the shift leaves empty, without also filling values that were already missing in the data.  

I'm now bringing a lag function built on the Column API into Tablecloth itself.  

### Next month

- Get #221–#223 reviewed and merged, then simplify and add tests to the generator itself.
- Work through the docstring fixes listed above.
- Finish lag/lead in Tablecloth.
- Start on the Column API tutorial. <br>

---

<br>


## EVE (Extended Value Encoding): John Newman   
Q3 2026 $2K Report 1. Published November 20, 2026   

eve is a persistent data-structure library - maps, vectors, sets, lists, etc, that live in shared memory as atoms. It’s implemented in .cljc, so it targets over a dozen clojure-likes. Clojurists Together funded eve this quarter and my goal was to improve the Clojure impl (the cljs version came first), improve the docs and get the library to a 1.0 status. I feel like we’re half way there.  

### The tl;dr:  

* The jvm version is mostly unified and at parity with the cljs version and can really be considered the reference implementation now. This round of sprints has not really been focused on performance but I think the jvm is beating cljs now in most areas.On my birthday I released 0.9.0 (yay!) and iterated on a few feature releases up to 0.9.3.2. Things are still very fluid and the final api is starting to land.
* A write ahead durability journal for disk atoms that I forgot to impl for the prior release was missing. The journal is a sliding window of epochs that are being flushed to disk. (Thanks for the report, Thomas van der Veen!)
* We added thread facilities (similar to cljs-thread) to eve, letting you pass threads between runtimes, so you can orchestrate clusters of runtimes from any of those runtimes - an executor service, threads, future, promise, pools, etc. The impl api is not 100% ready but eventually I’d like it to cover down on the clojure threading api as much as possible.
* Network atoms - more to come on these in the next update, but network atoms are now out and you can kick the tires on them. Cross network thread passing between runtimes has not yet been exercised in eve but it has been proven out in some experimental languages I’ve been working on for the last year.
* We use wide hashes instead of murmur now to future proof eve and make it more runtime agnostic.
* I made a new thing called cohost that runs stock core.clj and its friends on over a dozen different clojure-likes. And those runtimes’ memory can be fused together at eve, letting them act like clojure threads in a many-body cohosting scenario - which I call runtime fusion, but if there’s already some word that feel free to let me know.  

There are three new demo apps showcasing eve’s abilities - eve.db.ds, the cohost media demo, and narcis.  

### One implementation, ten hosts  

So our current line-up is:  

JVM Clojure, ClojureScript on Node and in the browser, Babashka, nbb, ClojureCLR, Basilisp, Jolt, clojerl and Glojure - with jank and ValueScript support soon. Jank basically works but there’s still some wrinkles I have to iron out.

The code base is now much more uniform as cljc across all the target platforms. It will take a while to sort out what the best common abstractions are for the various clojure-likes to plug into, but as it evolves I hope the shape helps inform how the community might increase the scope and functionality of the default cljc experience in the future.

### Atoms that cross processes and networks  

We’ve got some tests going with writers from eight hosts banging on a disk atom under contention safely. We have Rich Hickey’s ant demo running in the terminal, with hosts from different runtimes running agent ants together on a grid of refs living as atoms on disk. It’s all coordinated by STM between the runtimes over the disk atoms in a leaderless and permissionless way, lockfree and with optimistic updates. STM also works over network atoms - I’m calling that NTM - network transactional memory! Not as much testing has been done there yet though. More to follow there next update.  

### Durability and memory  

We have a 72 point crash suite to stress test our journaling behavior. It actually increased speed. For workloads like incrementing counters we’re going 1.62x before. The journal allows the threads to optimistically run ahead a little bit. We do go 15 to 25% slower under contention though.  

We also brought back allowing for multiple atoms under one domain, which allows you to share memory between atoms of a single domain. However, doing so requires tracking mutual ownership with regard to retirement and epochs and the journal, which brought the perf hit under contention. We removed reflection from the JVM’s memory access and made a counter swap 10x faster.
Disk atoms are tiered atoms: the in-memory tier is a cache over the disk tier, so an atom that grows past resident memory spills to disk instead of failing. For network atoms, the disk becomes a second, larger cache tier that spills over to the network. In principle, there is no need to have a logical limit on the memory size of an eve atom. Therefore, the address space for eve atoms has been made deliberately future proof.  

### Data formats and flat values  

Murmur is out of both hash and HAMT key placement and wide hashes now agree on JVM and ClojureScript. A #char reader tag (codec tag 0x14) landed so character values round-trip across hosts.  
In the 0.9.1 release, instantiating eve data outside of an eve transaction would throw. Now it automatically produces a flat variant - a lean Valhalla-like value form with no identity of its own. The flat machinery is one portable implementation, and the documentation now describes Eve’s two value types, persistent and flat, side by side. More to come on this.  

### Seeing it run: the media transcoding app and narcis  

To show how eve can make an application “stateless” with respect to RAM, I forked Burin’s Jonquil and made narcis. It lets you edit very large code bases while keeping the runtime state of your editor durable, even surviving machine restarts:  

<img width="720" height="456" alt="Narcis" src="https://github.com/user-attachments/assets/4183fc30-776a-4200-b182-2e62c7a887df" />  <br>  
(narcis text editing app, based on jonquil)  

The cohost media editor shows how you can fuse multiple clojure-like runtimes together to create a media transcoding app - runtime fusion. In my opinion, it’s quite ugly, but that’s not the point! The idea is to show how the different runtimes are working together:  

<img width="720" height="636" alt="cohost v2" src="https://github.com/user-attachments/assets/57d1bdef-39df-4137-acaa-a9537ab21a36" /> <br>  
(Cohost Media Example App)

And here's the ant demo running from the cli:  

<img width="306" height="680" alt="ant" src="https://github.com/user-attachments/assets/5279327a-dcc2-4512-bd97-57d7fa749158" /> <br>  
(A CLI ant demo, based on Rich Hickey's)

### What’s next  

There is a lot going on over here, but the bits are starting to settle and I’d love to have folks poking at eve. Sorry again for the delayed update. I would also like to figure out a development process that integrates human checks on critical namespaces and functions for large point releases. I’m not sure if I’ll have something together by the end of the funding period but I've been hosting some conversations about it with the community.  

Thanks to Clojurists Together and its supporters for funding this work! (edited)  <br>

---



## Finish Carmine 4: Peter Taoussanis    
Q3 2026 $2K Report 1. November 16, 2026   

Hi folks! 👋  

Making good progress on the upcoming release of Carmine v4 (next gen Clojure Redis client). Am hoping to release alpha1 for early testing/feedback **later this month** (November).  

The last few weeks have been focused on refinement: last passes on the UX/API, documentation, performance tuning, and lots of testing.  

I've also been backporting improvements where possible to the v3 line (see the recent [v3.6](https://github.com/taoensso/carmine/releases/tag/v3.6.0) and [v3.7](https://github.com/taoensso/carmine/releases/tag/v3.7.0) releases for example).  

### What to expect  

- v4 artifact will include both v3 and v4 APIs, making it easy to mix and match
- Full **RESP3 support** (latest Redis protocol)
- A far more **flexible connections API**
- Full **Redis Sentinel** support (high availability)
- Preliminary **Redis Cluster** support (high availability + sharding)
- Data-compatible **v4 message queue**
- Significantly **improved Pub/Sub** API
- Numerous **usability and reliability improvements** throughought

### Testing + feedback  

Once alpha1 is out, next step will be testing and feedback. Particularly hoping for folks to try out the new systems like Sentinel, Cluster, and the new Pub/Sub API.

These involve the most tricky decisions and interactions, so will also need the most scrutiny.

My hope is to aim for something like beta1 before the end of the year / early next - depending on how many folks are available to help test. Feel free to [ping on GitHub](https://github.com/taoensso/carmine/issues/347) if you're interested 👍  

### Wrapping up  

It's been a long, multi-year road - but almost there now. Looking forward to announcing the first alpha soon. Cheers! 🫶

\- [Peter])(https://www.taoensso.com/)  

