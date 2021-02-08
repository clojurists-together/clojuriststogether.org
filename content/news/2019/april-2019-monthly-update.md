---
title: April 2019 Monthly Update
date: "2019-05-16"
type: post
author: Rachel Magruder
summary: "Here is our final Q1 monthly update from projects, Neanderthal and Aleph - thanks so much for all your hard work this quarter."
---

## Clojurists Together News

Hello CT Community -

Here is the final update from our Q1 projects. We appreciate the work of Neanderthal and Aleph.

Our Project Leader, Daniel Compton, is speaking about Clojurists Together soon! Catch him at one of the following local events:

[NYC Meetup: Funding OSS- Clojurists Together](https://www.meetup.com/Clojure-nyc/events/260728030/)
<br /> May 15th in New York City, NY
<br /> 6:30 - 8:30pm

[Heart of Clojure](https://heartofclojure.eu/)
<br /> August 2nd in Leuven, Belgium

Thanks for your continued support of Clojurists Together!

## Neanderthal update

13.  [Simple Neural Network Training API](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-13-Initializing-Weights)
April 3, 2019
The stage has been set for wrapping up the simplest version of a complete neural network API, and its key part that offers the entry for the /learning/ functionality - the training API.

14. [Initializing Weights](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-14-Learning-Regression)
April 10, 2019
As the iterative learning algorithm has to start somewhere, we have to decide how to initialize weights. Here we try a few techniques and weight their strengths and weaknesses until we find one that is good enough.

15. [Learning a Regression](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-15-Weight-Decay)
April 15, 2019
A great moment has arrived. We are going to apply our neural networks implementation to a regression problem. The network is going to learn a known function, which enables us to see how well it learns, and why it doesn't do a great job. We are also going to get some hints for improvements. But, hey, it works!

16. [Weight Decay](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-16-Momentum)
April 23, 2019
In this article we explore a simple but useful technique for keeping weights from growing too big. Weight Decay is useful as a regularization technique that improves generalization, and can help with improving even the basic learning on the technical level.

17. Momentum
April 29, 2019
Today we are going to implement momentum, a ubiquitous learning optimization technique. What's more, we'll do it without any performance penalty. Find out how many lines of Clojure code it will take.

18. GPU Accelerated Neural Networks in 6 Lines of Clojure
Waiting to be published next week
Is it possible to program a fast implementation of neural networks in 6 lines of any language? What about neural networks
that run on both CPU and GPU?

### Promoting Neanderthal in the non-Clojure community

During the Clojurists Together funding period, I have published 17 long articles. 6 articles got some nice publicity by reaching the frontpage of Hacker News.

From February 1st to April 29th, the articles had 57,294 views by 28,570 unique users. This data is from Google Analytics *after the bots and spam were heavily filtered out*, so the reported traffic seems genuine. Cloudflare shows much higher numbers, as expected.

I plan to continue writing on this topic and release a series of beautifully typeset books that use Clojure to explain the attractive and popular topics of Deep Learning, Data Analysis, GPU programming and HPC to wider programming community. This series roughly cover a third of the book "Deep Learning for Programmers" that I will open for early access in May.

### General bug fixes and improvements to the Neanderthal suite of libraries

During these 3 months, I released versions 0.22.0, 0.22.1, and 0.23.0, which contain new functions useful in deep learning and general high performance programming and upgraded the underlying OpenCL engine.

<br />

## Aleph Update

1. [MQTT support](https://github.com/ztellman/aleph/pull/510)

The biggest and the most time-consuming chunk of the effort. Right now the implementation covers MQTT3.1 client publish flows (including fire-and-forget and at-most-once use cases). Subscriptions are still in progress. Flow control is implemented on top of Manifold's stream put/take operations. It makes code easier to read/maintain, but probably not the most performant approach. Potentially we can get better performance implementing flows as Netty handlers. To make the final decision I still need to implement both and run performance tests to compare both approaches.

2. [Helper to extract SSL session](https://github.com/ztellman/aleph/pull/505)

This information is critical to have when working with mutual TLS. TCP connection exposes SSL session through the field in `def-map-type`. Exposing new field with Java-object value for HTTP might introduce problems with backward compatibility because Java objects usually play badly with print/read/eval cycles. I think helper covers the use case tho’.

3. [Activity logger for HTTP & TCP servers](https://github.com/ztellman/aleph/pull/508)

API looks the same way it looks for clients. :log-activity param includes LoggingHandler as a top-level handler. There’s still an open question on how to log activity for each child handler (read “for each open connection”). Not to confuse users, I would probably merge both loggers to be instantiated using the same configuration key. So you don’t need to know what “handler” and “childHandler” are.

4. [Accurate handling of proxy exceptions](https://github.com/ztellman/aleph/pull/509)

The previous implementation throws ProxyConnectionTimeoutException in case of any proxy connection error. Which is obviously misleading. The fix provided is capable of dealing with 3 different types of exceptions: connection timeout, HTTP proxy exception with input headers attached (introduced https://github.com/netty/netty/pull/8824), generic connection exception (e.g. "disconnect", "no response" etc). Note, that HTTP proxy response headers usually are the only available source of debugging information, so it’s critical to keep them available for end users.

5. [FileNotFoundException is finally fixed](https://github.com/ztellman/aleph/pull/471)

The branch is merged with other changes from master, I think file regions support was the biggest blocker there. The response format is updated in order to hide exception details. Now server return by default generic error. Mostly due to safety reasons.

6. [Properly initialize per-message deflate handshaker](https://github.com/ztellman/aleph/pull/506)

The problem was originally reported and discussed in https://github.com/ztellman/aleph/issues/494. In short words, WebSocket server compression didn’t work because the server was not able to communicate available extensions during the handshake. To be able to test the result of the handshake, the result was exposed through the stream description (https://github.com/ztellman/aleph/pull/498) and after some communication with Netty’s community, the initial problem was solved in a somewhat “hacky” way by firing HTTP request through the pipeline after WebSocket deflator was added. It would be great if we could bury this state propagation deeper not exposing the event to the rest of the pipeline e.g. using EmbeddedChannel, but that’s almost impossible to do because the handler itself is not marked to be Sharable, so it fails to be added to more than one pipeline. The solution works even tho’ it’s not beautiful enough.

7. [Update Netty to 4.1.36](https://github.com/ztellman/aleph/pull/507)

“WebSocket client handshaker to support "force close" after timeout” https://github.com/netty/netty/pull/8896 was merged into Netty 4.1.35.Final. The idea behind that PR was to introduce force timeout for WebSocket client instead of waiting for the server to close the connection forever. Initially I was going to implement this functionality for Aleph but finally ended with Netty’s PR. It took quite some time to polish and merge, but as soon as Netty version is bumped in Aleph, I’m going to port force close settings here. BTW, it will work even without exposed settings as the timeout defaults to 10 seconds in Netty.
