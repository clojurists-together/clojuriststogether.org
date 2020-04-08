---
title: "January 2020 Monthly Update"
date: 2020-02-12T11:53:57+13:00
author: Daniel Compton
summary: "Read updates from Expound, Oz, and Deep Diamond"
---

## Housekeeping

[Our next funding round](/news/q1-2020-funding-announcement/) started on February 1st. For Q1 2020 we're funding Ring, Reagent, Calva, and fireplace.vim.

This is the last update from Dragan for his work on Deep Diamond. Ben Brinckerhoff's grant to work on Expound finishes at the end of February, and Christopher Small's grant to work on Oz finishes at the end of March.

David Levi had been selected to work on libpython-clj. However due to other commitments, he wasn't able to continue his work on libpython-clj in the compressed three month grant period and declined funding. We wish David the best and look forward to following developments with libpython-clj. Carin Meier [has](https://gigasquidsoftware.com/blog/2020/01/10/hugging-face-gpt-with-clojure/) [been](https://gigasquidsoftware.com/blog/2020/01/18/parens-for-pyplot/) [writing](https://gigasquidsoftware.com/blog/2020/01/24/clojure-interop-with-python-nlp-libraries/) about how you can interoperate with Python libraries from Clojure. Very cool!

## Deep Diamond

### January 1-15 2020

In these two weeks of the grant period, I'm continuing the work on
cuDNN-based GPU tensors.

I'm doing the work on CUDA tensors and cuDNN integration layer, and went
a bit further on achieving the parity and integration with DNNL tensors.

As a part of this, I integrated both cuDNN tensors and DNNL tensors
into the existing Neanderthal convenience API, where possible.

This is a continuation of the work described in the last biweekly report,
and is still not in a stable state. It works (most of the time), but it is not properly tested yet,
and not feature complete. Bugs are expected.

### January 16-31 2020

In these two weeks of the grant period, I made lots of progress regarding the
integration of cuDNN-based tensors and integrating it in the skeleton that
emerged while I worked on DNNL tensors, fully connected layers, and neural network
infrastructure and API.

The (unpolished and only partly tested) work on tensors is pretty much there,
but I still need to create the support for fully connected layers to achieve
parity with the DNNL implementation.

This is still not ready for first release to Clojars (unfortunately, since it would be a
nice output for the funding round), but it is getting there. I'll continue working
on this after this funding period, and expect it to happen in a month or two.

The good news is that the infrastructure work and hard parts are mostly there,
but there is bunch of fighting with DNNL/cuDNN incompatibilities, bugs, etc,
solving and testing the little things. I will work on this while writing the
appropriate chapters of the Deep Learning for Programmers book, so I expect
that the end product would be a polished, tested, and nicely documented API,
and that it will happen relatively quickly.

## Expound

### January 1-15 2020

#### Improving the display of "context" values

Expound error messages display the specific problem within the context of the entire invalid value. For instance, if `"456"` should be an integer within `{:ids [123 "456" 789]}`, Expound will print:

```
  {:ids [... "456" ...]}
             ^^^^^

should satisfy

  int?
```

There are a number of unresolved issues in Expound that all relate to how Expound prints the "context" value - either by showing too much information (which creates very long error messages) or showing too little (which hides important information about where an invalid value is located).

I've been doing a lot of design work (writing an ADR and spiking some code) on how I might resolve such issues.

#### spec-alpha2 support

Separately, I've been beginning to lay some early foundations for `expound.alpha2` which will support `spec-alpha2`

* Created a new Expound namespace
* Created very basic test that old and new namespaces can be loaded with the corresponding version of spec
* Read up on `spec-alpha2` documentation

### January 16-31 2020

#### Improving the display of "context" values

I've continued work on the problem of how to allow users to adapt the error messages for their specific use case.

I drafted an [ADR](https://github.com/bhb/expound/blob/master/doc/arch/adr-003.md) (still a work in progress) and spent time spiking out some solutions.

This is a fairly tricky problem and I certainly have lots to learn about designing a "data API". If anyone has tips, ideas, or links to prior work, please leave a comment on the [GitHub issue](https://github.com/bhb/expound/issues/189) or start a discussion in the `#expound` channel on [Clojurians Slack](http://clojurians.net).

## Oz

Note: Christopher Small is also blogging about his work, you can find more details on his progress at [metasoarous.com](http://metasoarous.com/blog).

### January 1-15 2020

My first funded task for Oz has been to mint new cljsjs releases of the various Vega libraries. The latest Vega-Lite release (4.0.x) in particular comes with quite an impressive set of new features! These include several useful transforms (density plots, regression, loess and quantiles), responsive width/height, image marks, and interactive legends (to name just a few)!

As I've begun to dig in, I've decided to keep a sort of running log of my development activities to keep folks up to date on what I've been working on. Unfortunately, as you'll see below, the process this time around has been somewhat fraught.

Cljsjs has always felt a bit challenging to work with for me. For a family of libraries like Vega, it can be rather cumbersome getting all the right versions, checksums, Google Closure externs all organized into a set of build.boot files, each of which may refer to the versions and such of other build.boot files, and each of which in turn needs it's own PR for review, etc.

A while ago I started working on a script for automating this process. It's a beautiful (read: terrifying) bit of bash, of which I'm very proud. In general, it's made things quite a bit more manageable for me, but it still seems to take a bit of expertise to wield.

This time around, not only did I hit some of the usual turbulence of working with this system, I also hit a number of miscellaneous issues which have kept me from getting the release out (yet!). However, I'm taking this as an opportunity to smooth out this part of the process more generally so that it'll be easier for myself and others in the future. What I'd ultimately like to do is empower developers to quickly run this script themselves when they need an updated version of the Vega libs, instead of necessarily having to wait for me to mint a new release of Oz.

[Oz Updates - Misadventures with Cljsjs](http://metasoarous.com/blog/oz-clojurists-together-update-1)

### January 16-31 2020

[Oz Updates - Finishing up release of updated Vega libraries with Cljsjs](http://metasoarous.com/blog/oz-clojurists-together-update-2)

Continuing to work on the script for updating new versions of Vega in CLJSJS.

[Oz Updates - Finishing Oz release & working on Vega-Leaflet](http://metasoarous.com/blog/oz-clojurists-together-update-3)

Now that I have Cljsjs releases under way for Vega, I look forward to being able mint a new release of Oz. However, I still have to wait for the releases to come out the other side. So until then, I'm going to take advantage of the fact that the Cljsjs build process is fresh in my head, and look at extending it towards some of the other projects I'd like Oz to be able to integrate with:

* Vega-Leaflet
* Voyager

Unfortunately it appears as if Voyager isn't currently supported. The CLJSJS PRs were merged, and I started exploring Shadow CLJS more. Shadow seems very promising, and may be a better fit for wrapping the core Vega/Vega-Lite functionality than CLJSJS. I'm getting close to creating a new release.
