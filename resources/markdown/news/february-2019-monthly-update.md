title: February 2019 Monthly Update
date: 03/12/2019
type: post
draft: false

## Clojurists Together News

Hello CT Community -

This month we present the first update from our new projects, Neanderthal and Aleph! Thanks to everyone for your continued support of Clojurists Together!

## Neanderthal update

During the first two weeks, I have published 4 detailed posts, around 8000 words in total:

1. [Deep Learning in Clojure from Scratch to GPU - Part 0 - Why Bother?](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-0-Why-Bother)
February 1, 2019 
An introduction to a series of tutorials about Deep Learning in Clojure funded by Clojurists Together. Start with an empty clj file and build a fast neural network that runs on the GPU, built with nothing else but plain Clojure and Neanderthal. The series is a companion to a free online book Neural Networks and Deep Learning.
2. [Deep Learning from Scratch to GPU - 1 - Representing Layers and Connections](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-1-Representing-Layers-and-Connections) 
February 6, 2019 
Here we start our journey of building a deep learning library that runs on both CPU and GPU.
3. [Deep Learning from Scratch to GPU - 2 - Bias and Activation Function](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-2-Bias-and-Activation-Function) 
February 11, 2019 
We continue building our network by adding the activation function and bias.
4. [Deep Learning from Scratch to GPU - 3 - Fully Connected Inference Layers](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-3-Fully-Connected-Inference-Layers) 
February 14, 2019 
It's time to formalize some structure of our layers into a layer type.
5. [Deep Learning from Scratch to GPU - 4 - Increasing Performance with Batch Processing](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-4-Increasing-Performance-with-Batch-Processing) 
February 18, 2019 
We increase performance many times by computing a group of (vector) inputs as one (matrix) batch.
6. [Deep Learning from Scratch to GPU - 5 - Sharing Memory](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-5-Sharing-Memory) 
February 21, 2019 
Sharing and reusing memory buffers is inescapable if we want high performance. It is a sharp but powerful tool.
7. [Deep Learning from Scratch to GPU - 6 - CUDA and OpenCL](https://dragan.rocks/articles/19/Deep-Learning-in-Clojure-From-Scratch-to-GPU-6-CUDA-and-OpenCL) 
February 28, 2019 
We generalize the network code and run it on the GPU. On an Nvidia GPU with CUDA, and on an AMD GPU with OpenCL. Even more - we mix both CUDA and OpenCL, just because we can.

## Aleph Update

### Part I

A lot has happened these last few weeks. The key focus was on implementing new features, so let's enumerate all of them.

### WebSockets

* [#422](https://github.com/ztellman/aleph/pull/422) "Handle WebSocket handshake timeouts".

Initially implemented by [Denis Shilov](https://github.com/shilder). Thanks a lot! I've reimplemented the functionality partially to avoid usage of Manifold's timer. Mostly becase of performance considerations. I've also opened [the same issue](https://github.com/netty/netty/issues/8841) for Netty as this functionality is not covered by built-in websocket protocol handlers. And it was already implemented [here](https://github.com/netty/netty/pull/8856) by [Qin Shicheng](https://github.com/qeesung).

* [#481](https://github.com/ztellman/aleph/pull/481) "Fine-grained websocket close handshake API".

This PR introduces new `http/websocket-close!` API to close the connection providing custom status code and optional reason text. Application level protocols usually use those to define and handle different reasons for closing the connection.

* [Netty's #8896](https://github.com/netty/netty/pull/8896) "WebSocket client handshaker to support force close after timeout".

RFC 6455 defines that, generally, a WebSocket client should not close a TCP connection as far as a server is the one who's responsible for doing that. In practice tho', it's not always possible to control the server. Server's misbehavior may lead to connections being leaked (if the server does not comply with the RFC). RFC 6455 #7.1.1 says "In abnormal cases (such as not having received a TCP Close from the server after a reasonable amount of time) a client MAY initiate the TCP Close.". The PR mentioned extends WebSocket client handshaker with additional param `forceCloseTimeoutMillis` and processing of the timeout when configured.

Usually I do this in a different order: implementing something in Aleph and after that porting the same functionality to Netty. But this time I went another road and started with Netty. After the next release I'm going to reuse the implementation. Current version of Aleph simply closes the connection right after the `CloseFrame` was flushed (never letting the server to reply back).

[Dominic Pearson](https://github.com/dspearson) also submitted [#488](https://github.com/ztellman/aleph/pull/488/) to give the user ability to specify name resolver when setting up a new client WebSocket connection. Thanks a lot!

I've also reimplemented acquire/release cycles for all Websocket frames both for the client and the server. Unfortunately, it's still possible to catch unreleased buffers warning when running tests (pretty randomly). So I'm going to merge all updates around WebSockets protocol handling into a separate branch to do my best to find what causes the leak.

### Large requests & responses

* [#432](https://github.com/ztellman/aleph/pull/432) "Attempt to provide correct API for multipart resources management".

I hope this one is final. The problem with multipart requests API is that the user should be very careful about resources allocation/deallocation cycle (remember, most probably this API is used for handling large file uploads... meaning you definitely don't want to screw this up). Aleph has a little chance to help because of the underlying async execution model. And Netty's implementation is far from being perfect too (I think mostly with the same reasoning). Previous implementation was designed with a single "deallocate" point. But after some testing I found a problem with the approach introduced earlier. I've reimplemented public API using `io.netty.util.ReferenceCounted` and relying on `netty/release` for each consumed chunk. The API that exposes a single `.close` method for the entire decoder object lacks flexibility as the user cannot deallocate memory from a specific chunk (potentially freeing a lot of space as soon as possible) while all other chunks are still in processing... So, I think that releasing them one-by-one is the best option in terms of resources usage even tho' it might be a bit confusing from the first glance (well, nothing special if you have some experience with Netty/C/C++/Rust etc, but probably not something you're doing in Clojure on a daily basis).

* [#473](https://github.com/ztellman/aleph/pull/473) "HTTP client to support body decompression".

This was a question of parity with `clj-http` API. `clj-http` automatically includes "Accept-Encoding" header to each request and decompress the response when it comes back being compressed. Mentioned PR introduces support for `:decompress-body?` param (switched OFF by default to provide backward compatibility). When set to `true` works pretty much the same way `clj-http` does: includes headers, checks the response "Content-Encoding" header, and decompresses the body when necessary before spitting it out.

* [#482](https://github.com/ztellman/aleph/pull/482) "Custom 100-Continue handler".

Server now accepts new `:continue-handler` param to control response flow for requests with "Except: 100-Continue" header. By default Aleph just accepts all requests automatically sending "HTTP/1.1. 101 Continue" response and resuming reading the body after that. Now you can provide as a `:continue-handler` a function that given specific request should decide whether the client should proceed or not. You can also provide a custom rejection response if the starndard "417 Expectation Failed" somehow doesn't work for you. 

* [#485](https://github.com/ztellman/aleph/pull/485) "http/file API to send region of the file".

Aleph server understands if your handler sends `java.io.File` object as a `:body` (using zero-copy transfer when possible). The PR mentioned gives you ability to send part of the file. The use case here is range queries support. Hopefully I'll prepare some documentation on this (or a new example) in the next few weeks. It also extends body coercer to support `java.nio.file.Path`. 

### Name resolvers

Previously Aleph supported two name resolvers "out of the box" (well... you still can plug in your own): sync JDK's (default one) and async Netty's DNS client. A new name resolver was introduced in [#487](https://github.com/ztellman/aleph/pull/487). `netty/static-name-resolver` creates an address resolver from a static mapping from domain names to IP addresses. Similar to what curl --resolve <mapping> does. Now you can do

```clojure
(def resolver (netty/static-name-resolver {"aleph.io" "50.116.0.25" "*.netty.io" "104.28.8.44"}))
(http/connection-pool {:connection-options {:name-resolver resolver}})
```

The next thing I want to do here is to expand the functionality with composite resolvers, so you can say "look in this mapping, if nothing found use DNS over 1.1.1.1". 

### KQueue

Long-lived feature request, implemented in [#480](https://github.com/ztellman/aleph/pull/480).

Overall it works the same way `:epoll?` works for TCP, HTTP, WebSockets, UDP and DNS by specifying `:kqueue?` option. `false` by default, to be consistent with `epoll?`. At first glance it feels a bit weird to carry two arguments `epoll?` and `kqueue?` here and there, but on the other hand that gives me the ability to switch ON both native transports whenever possible (both will not  be available on the same host anyways).

Netty `4.1.33.Final` has a weird [issue](https://github.com/netty/netty/issues/8849) with KQueue selector which [seems to be fixed](https://github.com/netty/netty/pull/8881) already.

And interesting problem with deps managemented I've spotted in [#475](https://github.com/ztellman/aleph/issues/475). `io.netty/netty-transport-native-kqueue` dependency should also be specified with appropriate classifier ("osx-x86_64"). In the PR I've updated `project.clj` to include classifiers right away, both for Epoll and KQueue JARs. Generally, this should work, apart from the fact, your JAR would be roughly 180kb larger. It also may lead to some problems with transitive dependencies from other libraries, but I don't have a lot of experience with classpath resolution order when using classifiers. The obvious advantage here: less confusion for users when working with native transports.

### Unix sockets

Another long-lived feature request, implemented in [#480](https://github.com/ztellman/aleph/pull/480).

Unix domain socket support was added for TCP & HTTP both for client and server. Works only with native transports (Epoll or KQueue), Netty does not support it with NIO (yet?).

TCP API:

```clojure
user=> (def s1 (tcp/start-server (fn [s _] (s/connect s s)) {:unix-socket "/tmp/tcp-server.sock" :kqueue? true}))
#'user/s1
user=> (def c1 @(tcp/client {:unix-socket "/tmp/tcp-server.sock" :kqueue? true}))
#'user/c1
user=> (s/put! c1 "hello")
<< … >>
user=> @(s/take! c1)
#object["[B" 0x2eb44ff0 "[B@2eb44ff0"]
user=> (.close s1)
nil
```

HTTP API:

```clojure
user=> (require '[aleph.http :as http])
nil
user=> (def s1 (http/start-server (fn [_] {:status 200 :body "OK"}) {:unix-socket "/tmp/server1.sock" :kqueue? true}))
#'user/s1
user=> (def p (http/connection-pool {:connection-options {:unix-socket "/tmp/server1.sock" :kqueue? true}}))
#'user/p
user=> (-> (http/get "http://domain/info" {:pool p}) deref :status)
200
```

When connecting to unix domain socket, we still require a valid parsable URL (host + scheme). We need a hostname to acquire a connection from the pool, setup TLS `server_name` extension (when not provided manually) and render "Host" header (this might be required by the server). `curl` has the same problem, so I think that's a reasonable trade-off. Quite a few tools use `http+unix://<socket-file>/<path>` to convey both unix socket filepath and "fake" domain, if that's interesting for users we can always implement this on top of the existing functionality later on. Proxy options are rejected when connecting to unix domain socket, DNS resolver is set to `:noop` to bypass any attempts to resolve host before sending the request (we do just the same when sending requests through proxies).

### SSL, SNI

A lot of new functionality was introduced in [#487](https://github.com/ztellman/aleph/pull/487).

* Extended version of `netty/ssl-client-context` and `netty/ssl-server-context` to manage `SslContext` objects w/o Java interop (no need to juggle with `into-array <some class>` any longer).

* `ssl-context` now can be configured passing a map of options instead of calling a helper or juggling with Java interop. So you can do e.g.

```clojure
:ssl-context {:private-key <>
              :protocols ["TLSv1.1" "TLSv1.2"]}
```

Options coercer covers pretty much all possible configuration params.

* Client catches SSL handshake exceptions and returns them to the caller instead of silently closing the connection after the timeout. It's hard to imagine how you can workaround the error in runtime.. but it makes debugging way easier than it was before. 

* Server does not log a full stack trace for failed SSL handshake, just a single line warning. It might be useful for development somehow... but dealing with a wall of similar stacktraces on prod is far from being fun.

* `http/connection-pool` accepts new optional `:sni` param to provide a fine-grained control over `server_name` TLS extension (see more ["Server Name Indication"](https://en.wikipedia.org/wiki/Server_Name_Indication)). Now you can specify different value for a `server_name` (if it's not equal with the host) the same way OpenSSL `--servername` param works. You can also switch it OFF. Usually including this extention is not a problem, but you should remember that this extension would be sent over the network unencrypted. If you don't want to reveal the host you're working with to intermediaries... set `{:sni :none}` when creating a new connections pool.

* Server now accepts `:sni` option with the mapping from domain name (to be matched with `server_name` TLS extension value) to a specific `SslContext`. This is helpful when you need to support "virtual hosts" on the same IP address or if you want to provide mutual TLS authentication for different clients. When provided, Aleph automatically spins up SNI handler. Note, that `server_name` is an optional extension, so you still should provide default `SslContext` (this fact is forced by the implementation details both by Netty and JDK).

### Small operational improvements

* The solution for `PersistentArrayMap` class not found... issue was merged into master and released with `0.4.7-alpha5`.

* DNS resolver now [works correctly](https://github.com/ztellman/aleph/pull/477) on Epoll transport, thanks [Alexander Yakushev](https://github.com/alexander-yakushev) for discovering the problem.

* [Pretty-print](https://github.com/ztellman/aleph/pull/491) Aleph server object to show the bind address, port and the transport used.  

### Experiments

Now about more crazy stuff and ideas... 

* HTTP/2 server

This one I was planning on doing for a long-long time. There's a [feature request](https://github.com/ztellman/aleph/issues/251) about HTTP/2 (with quite a few upvotes). Last week I've managed to implement [basic support for HTTP/2 protocol](https://twitter.com/kachayev/status/1097657637485903878) on the server in a way that  it's able to reuse the same handler both for HTTP/1.1 and HTTP/2 (no need to change the implementation on the user's side). It uses ALPN extension to announce support for http/1.1 and h2 letting the client to deside. When client chooses h2 it translates HTTP/2 stream and data frames into Ring-like request and passes it to the same handler. Full support for all h2 features (like settings, priorities, ping/pong frames, server pushes etc) is still under a question as they're not covered by Ring's spec. Will see how far we can go. I'm still not sure about merging this functionality into Aleph, just playing around.

* QUIC

[QUIC](https://blog.cloudflare.com/the-road-to-quic/) is a new transport protocol that aims to "replace" TCP overcoming some limitations. To make the story short, you can put HTTP/3 = HTTP/2* over QUIC (* simplifications applied). My investigative attempt to implement a QUIC client with Aleph, Netty and Manifold was [quite succesfull](https://twitter.com/kachayev/status/1100747943521464320). It's far from being practically useful tho'. Apart from non-trivial crypto based on "unofficial" forked version of OpenSSL, full implementation should handle congestion control (for TCP you usually rely on your OS). I keen to go on pursuing production-level quality and features coverage. Most probably as a transport layer in Netty first. 

--- 
Stay tuned! 
