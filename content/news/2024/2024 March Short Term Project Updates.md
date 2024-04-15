---
title: "March 2024 Short Term Project Updates"
date: 2024-04-14T08:30:00+08:00
summary: Updates from clojure lsp, Instaparse, Jank, scicloj, SiteFox, UnifyBio
author: Kathy Davis
draft: True

There are a lot of projects to report on this month - all funded in Q1 2024.

clojure-lsp: Eric Dallo   
Instaparse: Mark Engelberg  
Jank: Jeaye Wilkerson  
scicloj: Daniel Slutsky  
SiteFox: Chris McCormick  
UnifyBio: Benjamin Kamphaus  

## clojure-lsp: Eric Dallo  
Q1 2024 Funding. Report 2. Published March 1, 2024  

### [clojure-lsp](https://clojure-lsp.io/)  
The main highlight is the new linter different-aliases helps guarantee consistency across alias in your codebase!  

![different-aliases](./different-aliases.png)

### 2024.03.01-11.37.51  

- General
  - Bump clj-kondo to `2024.02.13-20240228.191822-15`.  
  - Add `:clojure-lsp/different-aliases` linter. #1770  
  - Fix unused-public-var false positives for definterface methods. #1762  
  - Fix rename of records when usage is using an alias. #1756  

- Editor  
  - Fix documentation resolve not working for clients without hover markdown support.  
  - Added setting to allow requires and imports to be added within the current comment form during code action and completion: `:add-missing :add-to-rcf` #1316  
  - Fix `suppress-diagnostics` not working on top-level forms when preceded by comment. #1678  
  - Fix add missing import feature on some corner cases for java imports. #1754  
  - Fix semantic tokens and other analysis not being applied for project files at root. #1759  
  - Add support for adding missing requires and completion results referring to JS libraries which already have an alias in the project #1587   
  
### [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

### 1.14.8 - 1.14.10  
- Fix exception when starting server related to previous version.  
- Fix some exceptions that can rarely occurr after startup.  
- Bump clojure-lsp to `2024.02.01-11.01.59`.  

## clojure-lsp: Eric Dallo  
Q1 2024 Funding. Report 3. Published April 1, 2024  

### clojure-lsp

This month I spent some time fixing and improving clojure-lsp for Calva, but most of the time working on the IntelliJ support for LSP and REPL, improving both `clojure-lsp-intellij` and `clojure-repl-intellij`.  

### [clojure-lsp](https://clojure-lsp.io/)  

### 2024.03.31-19.10.13  

- Editor
  - Adding require command fails for requires without alias. #1791  
  - Add require command without alias now add requires with brackets.  
  - Project tree feature now support keyword definitions like re-frame sub/reg.#1789  
  - Support `textDocument/foldingRange` LSP feature. #1602  
  - Improve `textDocument/documentSymbol` considering keyword definitions and returning flatten elements.  
  - Fix Add require/import usages count in code actions. #1794.  

### 2024.03.13-13.11.00  
- General  
  - Bump clj-kondo to `2024.03.13` fixing high memory usage issue.  

- Editor  
  - Fix `workspace/didChangeConfiguration` exception causing noise on logs. #1784  
  
## [clojure-lsp-intellij](https://github.com/clojure-lsp/clojure-lsp-intellij)  

There was a major change to how the plugin starts clojure-lsp, now it starts a clojure-lsp process under the hood (like all other editors) instead of using clojure-lsp as a JVM deps, this fixed a lot of macos bugs.  
Also this adds support for "find implementations" of defmultis and protocols, something that it was never possible in any other IntelliJ plugin.  

### 2.0.0 - 2.3.2  

- Use clojure-lsp externally instead of built-in since causes PATH issues sometimes. Fixes #25 and #26  
- Fix multiple code lens for the same line. #29  
- Fix os type for macos non aarch64 when downloading clojure-lsp server.  
- Fix references for different URIs when finding references.  
- Fix only noisy codelens exception. #33  
- Support "Find implementations" of defmultis/defprotocols. #31  
- Fix commands, code actions not being applied after 2.0.0.  
- Improve "find declaration or usages" to show popup for references.  
- Improve Find references/implementations to go directly to the usage if only one is found. #39  
- Wait to check for client initialized to minor cpu usage improvmenet.  
- Support multiple projects opened with the plugin. #37  
- Fix Stackoverflow exception when renaming. #32  
- Add common shortcuts to DragForward and DragBackward.  
- Fix race condition NPE when intellij starts slowly.  

## [clojure-repl-intellij](https://github.com/afucher/clojure-repl-intellij)  

Although this is not related with clojure-lsp, it's a critical library for IntelliJ usage since without it, there is no REPL usage using only LSP. 
I spent a considerable time adding the missing feature to make this plugin good enough for a stable release.  

Now the plugin has test support!  

### 0.1.7 - master 

- Use cider-nrepl middleware to support more features.  
- Add test support. #46  
- Fix freeze on evaluation. #48  
- Improve success test report message UI.  
- Support multiple opened projects. #51  
- Fix eval not using same session as load-file. #52  

![Test Support](./clojure-repl-intellij-demo.png)  <br>

---


## Instaparse: Mark Engelberg   
Q1 2024 Funding. Report 1. Published March 30, 2024.

Thanks to funding from Clojurists Together, I have been able to review instaparse pull requests that have been submitted over the past couple of years. I began by incorporating some "low hanging fruit" pull requests, which addressed some quality of life issues raised by users with minimal changes to the code. Although these were small changes and code was contributed by other users, I needed to test the code and make sure the changes were adequately documented.  

I also engaged with users who submitted issues where I needed more explanation or input to carefully consider their proposals. In some cases, I spent time evaluating pull requests but eventually decided *not* to incorporate that pull request. A good example of this was tonsky's proposal to change the way that parsers and error messages print at the REPL. His proposal was logical but would be a breaking change, which posed a dilemma. After collecting information from him, I consulted with other Clojurists who are involved with instaparse as well as pretty printing and REPLs. I came to the conclusion that it was better to leave as-is.  

The most interesting issue I began looking at was a suggestion to incorporate namespaced non-terminals. This is an excellent suggestion because namespaced keywords have taken on much more importance in Clojure since the time instaparse was initially released, due to their critical role in Datomic and Spec. This will be my next area of focus.  

I had hoped to complete more work on namespaced keywords, but I spent most of March ill with covid (my first time getting covid), which delayed my work on this more substantive issue. So, rather than wait for the completion of that work, I deployed a new release with the pull requests I had incorporated so far (actually two releases in quick succession: 1.4.13 and 1.4.14) and I look forward to making progress on namespaced keywords in the coming weeks.  

---

## Jank: Jeaye Wilkerson  
Q1 2024 Funding. Report   . Published March  , 2024.


