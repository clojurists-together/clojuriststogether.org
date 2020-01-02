---
title: November/December 2019 Monthly Update
date: "2019-12-31"
type: post
---

November was the first month of our next funding round. libpython-clj and Deep Diamond started in November, Expound started in December, and Oz is starting in January. This is a combined update due to the holiday break.

<!--more-->

We've recently rebuilt our website and now have an RSS feed available. You can find a link in the footer of the site.

## Deep Diamond

### November 1-15

In the first two weeks of the grant period, I've been working on the Intel DNNL implementation.

### November 16-30

In the second two weeks of the grant period, I've continued working on the Intel DNNL implementation, and the general API for dense NN layers that use DNNL.

### December 1-15

In these two weeks of the grant period, I've continued working on
the general API for dense NN layers that use DNNL, and also made some
improvements to the Intel DNNL implementation. I implemented a general
sequential network type and general infrastructure, that can use the
previously implemented DNNL layers and learning algorithms, but is also
ready for other backend implementations (with improvements when necessary).
I also made progress on documentation and examples.

## December 16-31

In these two weeks of the grant period, I'm tackling cuDNN-based GPU tensors.
I've implemented a proof of concept
for an alternative implementation of fully connected layers based on
neanderthal's matrix multiplication, which is a testing bed for
a similar implementation on the GPU, which is needed because
cuDNN does not come with inner product implementation.

I've also in the middle of work on CUDA tensors and cuDNN integration layer.
Since it's a large task that needs lot of poking around and exploring an
exotic API, most of this is not yet in a consistent enough state to be
commited. I expect to dedicate the following month to taming this beast,
polish it and test it a bit, and set up a consistent infrastructure that
would put Deep Diamond on the track of consistently adding user-facing features.

## libpython-clj

### November 1-15

We wanted to make machine learning and deep learning python libraries available to Clojure developer, using libpython-clj
for a start, we have developed a utility that wraps python code to Clojure code importing functions classes as well as documentation from python to Clojure.
The utility can be found here:
https://github.com/pycloj/pycloj-exporter

We have taken some initial decisions on how to transform a python Class into Clojure (a namespace) and how to handle named argument and positional arguments.
The idea is to have an initial paradigm - which will be the basis for an open discussion with the community on the different possibilities.
Using the pycloj-exporter we have created this initial repositories:
https://github.com/pycloj/pycloj-keras
https://github.com/pycloj/pycloj-keras-examples
https://github.com/pycloj/pycloj-sklearn

Our goals for the current month is to "translate" tutorials written on python for machine learning and deep learning libraries and show how it can be done with Clojure.

We would also like to improve the automatic translation tools - pycloj-exporter based on the community's input.

## Expound

### December 1-15

I've been focusing on working through the backlog of bugs. I fixed three bugs:

* Bug with printing failures for multi-specs.
* Bug with registering message for set-based specs
* Bug with duplicate custom messages in `alt` or `or` specs

These three bugs are the last known bugs in Expound (not including one bug that is blocked on a `clojure.spec` bug).

I've released Expound [0.8.2](https://github.com/bhb/expound/blob/master/CHANGELOG.md#082---2019-12-11) which includes these fixes.

### December 16-31

Over the past year or so, I've received a number of feature requests that fall into the following two categories:

* users want to embed Expound messages in a larger error message or insert extra information into the Expound message
* users want to modify how the invalid value is printed

Most of my time has been spent doing some design thinking on how I could support these features.

In addition, I've completed a few small tasks:

* fixed several boxed math warnings
* fixed a bug with using Expound in ClojureScript

I've released Expound [0.8.3](https://github.com/bhb/expound/blob/master/CHANGELOG.md#083---2019-12-29) and [0.8.4](https://github.com/bhb/expound/blob/master/CHANGELOG.md#084---2019-12-31) which includes these fixes.
