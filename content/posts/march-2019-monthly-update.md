title: March 2019 Monthly Update
date: 04/18/2019
type: post
draft: false

## Clojurists Together News

Hello CT Community -

The second month of updates from Neanderthal and Aleph is here. 

Thanks for your continued support of Clojurists Together!

## Neanderthal update

10. [Deep Learning from Scratch to GPU - 9 - The Activation and its Derivative](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-10-The-Backward-Pass-CDU-GPU-CUDA-OpenCL-Nvidia-AMD-Intel) 
<br /> March 20, 2019 
We implement the key part of the backward pass, the computation of the error of a layer. Along the way, we set up the infrastructure for the complete implementation of backpropagation.

11. [Deep Learning from Scratch to GPU - 10 - The Backward Pass (CUDA, OpenCL, Nvidia, AMD, Intel](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-11-A-Simple-Neural-Network-API)
<br /> March 25, 2019 
We complete the basic implementation of the backward pass of backpropagation and gradient descent.

12. [Deep Learning from Scratch to GPU - 11 - A Simple Neural Network Inference API](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-12-A-Simple-Neural-Network-Training-API)
<br /> March 28, 2019 
The time is ripe for wrapping what we have built so far in a nice Neural Network API. After all, who would want to assemble networks by hand?


## Aleph Update

I've spent quite a lot of time working through comments for previous PRs and keeping those changes in a mergable state. At the end of the day a few PRs made their way to master and I hope to see more following.

### WebSockets

* [Issue](https://github.com/ztellman/aleph/issues/494) with WebSocket per-message deflate extension handshaker turned out to be way more complex than I’ve expected. I had an idea to close the gap of inconsistent APIs on Netty's side: [“Decouple WebSocket server extension handshaker from read I/O“](https://github.com/netty/netty/pull/8973). But as these changes didn't get through, I'm going to implement workaround in Aleph's codebase.

* Expose more information about handshake result both on the client and on  the server, e.g. subprotocols and extensions [#498](https://github.com/ztellman/aleph/pull/498).

* WebSocket client handshake processing flow changes, [#498](https://github.com/ztellman/aleph/pull/498). This change effectively reverts one of my old PRs with regards to how aggregated HTTP request is process when doing WebSocket handshake. Previously I thought that this approach leads to memory leaks, which turned out not to be the case. New implementation should be more performance and obviously has less memory overhead.

* A few updates to WebSocket client force close after given timeout functionality [submitted to Netty](https://github.com/netty/netty/pull/8896).

* I also spent tons of hours investigating performance of `netty/source` and `netty/sink` in turms of latencies, throughput, backpressure. I was looking into Netty's `autoRead` semantic, how Aleph deals with it and if we can improve performane oding `flush` on `readComplete`. To make story short: we can. In cases when you send/recieve a lot of small chunks. I'm still not sure how to expose this in the framework tho'. Talking about small chunks of data... I did the same investigation for UDP.

### Files

* [#497](https://github.com/ztellman/aleph/pull/497) Use `HttpUtil` to manage "Content-Length" header, small internal improvement.

* Even more fixes for Keep-Alive connections management for Netty's [examples](https://github.com/netty/netty/pull/8966), I think it makes sense to refactor part of the Aleph and move Keep-Alive logic into a separate channel handler so I would be easier to extend/fix (e.g. to close the connection after the error response).

* [Nasus](https://github.com/kachayev/nasus) (static files server) got a few more features: CORS headers, symlics support, Basic Auth.

### Performance

* HTTP client timeouts were reimplemented with `HashedWheelTimer`s that are designed specifically to handler tons of I/O events, [#499](https://github.com/ztellman/aleph/pull/499). That's a huge performance improvement for projects with higher RPS rates. The reasons for that were described in [#479](https://github.com/ztellman/aleph/issues/479), thanks to [Alexander Yakushev](https://github.com/alexander-yakushev) for brining this up. 

### MQTT

I've started this as an experiment.. but now part of client-side logic is implemented: connect/disconnect logic, publish messages to topics, acknowledgements flow for at least once QoS level. There're still a lot of things missing but end-to-end example already works. I hope I'll manage to pack those into a separate PR pretty soon.

Stay tuned!
