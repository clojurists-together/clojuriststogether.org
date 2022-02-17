---
title: "Funding development on Custom Formatters for Firefox"
date: 2022-02-17T14:30:00+12:00
summary: Clojurists Together is funding work to bring custom formatters to Firefox.
author: Daniel Compton
---

Clojurists Together is funding work to bring custom formatters to Firefox. This will make Firefox a much more powerful tool for ClojureScript development, and bring more browser choice to the ClojureScript community.

## What are Custom Formatters?

One of the most useful tools for developing with ClojureScript is [cljs-devtools](https://github.com/binaryage/cljs-devtools). It implement's Chrome's [custom formatters spec](https://docs.google.com/document/d/1FTascZXT9cxfetuPRT2eXPQKXui4nWFivUnS_335T3U/preview) to render ClojureScript's persistent data structures in a much more readable format.

CLJS Devtools example:

![](https://camo.githubusercontent.com/142c820fadf564e7ca87c7f08b091759e404dbb5d77afbbe547312cd50ba71c0/68747470733a2f2f626f782e62696e6172796167652e636f6d2f636c6a732d646576746f6f6c732d73616d706c652d66756c6c2e706e67)

The only downside of cljs-devtools is that it is only available in Chrome. Many ClojureScript developers would like to use Firefox, but the lack of cljs-devtools made it hard to switch. At Clojurists Together we saw that and have worked with the Mozilla team and an independent contractor [Sebastian Zartner](https://github.com/SebastianZ) to bring custom formatters to Firefox! We're funding Sebastian $18,000 USD to develop, test, and ship custom formatters.S

Sebastian is a long-time contributor to Firefox DevTools and was previously a core contributor to [Firebug](https://getfirebug.com).

## How can you help?

When Sebastian is ready for more testing, we'll put out a call to the ClojureScript community, explaining how to test this beta feature and to give your feedback.

In the meantime, you can follow the [Custom Formatter meta issue](https://bugzilla.mozilla.org/show_bug.cgi?id=1734614) on the Firefox bugtracker.

## Thanks!

Thanks to the Mozilla team for their support in developing this plan, to Sebastian for taking on this project, to Antonin Hildebrand for developing cljs-devtools, and to every Clojurists Together member for your help in funding this project.
