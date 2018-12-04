## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve with this funding?

I'd like some dedicated time to addressing bugs and adding new features that has slowed down a bit in the past. Recently it had to move away from deprecated APIs, and while most of the functionality was kept, there is still a lot of cleaning up to do and missing features that need to be re-implemented.

Recently there was also async support merged, and this needs to be enhanced so it can be a first-class usage of clj-http. Additionally, there is a lot of features that the Apache HTTP client exposes that clj-http doesn't nicely encapsulate. It would be great if these could be available to developers.

### Why is this project important to the Clojure community?

clj-http is one of the most popular Clojure open source projects, with over 2.7 million downloads. It's used by quite a few other clients as a base HTTP client.

## Work log

### Addressing bugs

* [#426](https://github.com/dakrone/clj-http/pull/426) to fix the socks proxied connection managers (an argument was not being passed correctly)
* [#428](https://github.com/dakrone/clj-http/pull/428) fixed a minor issue in the readme related to "unexceptional statuses"

* I spent quite a while on a tricky bug when nested query params are specified in addition to form params leading to the encoding of the body being incorrect. This led to [adding some parameters](https://github.com/dakrone/clj-http/commit/47a7762ed42e1d772e51a6f5bdaa61b436b54cb3) to allow fine-grained control over whether or not to flatten `:query-params` and `:form-params`.
* I worked on an issue where redirects to an invalid location (`https:///` in the example a user was running into) caused an asynchronous request to hang forever, rather than either returning an error or failing. [#435](https://github.com/dakrone/clj-http/pull/435) added the :validate-redirects option to disable this extra validation
* [#257](https://github.com/dakrone/clj-http/issues/257) I fixed an issue where if a server returned an empty response with headers indicated the body was gzipped, and the client requested that the body be coerced, an EOFException would be thrown trying to decode the empty response. This is now fixed by doing a pre-read phase where we tentatively attempt to read the stream, gracefully handling the situation where we it cannot be read.
* Finally, I'm working on setting up an environment to test NTLM credentials. A user reported that the NTLM currently implemented in clj-http wasn't working, so I need to have a way to reproduce this issue.
* I am hoping it can help track down an issue I spent time trying to reproduce where the synchronous connection pool is [blocking forever](https://github.com/dakrone/clj-http/issues/407) for a user.
  
* Sometimes HTTP servers want to read a request's headers, then do something without dealing with the body of the request, for example, reading a header with some authorization token before consuming a large binary upload body. A user was running into an issue where instead of returning a 413 like the server told it to, it [throws an exception about the socket having its pipe broken](https://github.com/dakrone/clj-http/issues/277) due to the connection being prematurely closed. Turns out there's an [RFC about this](https://tools.ietf.org/html/rfc2616#section-8.2.2), but it's unevenly implemented by browsers. To make things even more complicated, apache's http client incorrectly (throws the exception) handles it when used synchronously, but correctly returns the 413 when using asynchronously. I believe this is due to a difference in Apache's HTTP implementation. I'm planning on revisiting this with the 5.0 client to see if the bug persists.
* I pushed [a commit](https://github.com/dakrone/clj-http/commit/d2914602c77c48db02dbf56e82972ec60cc1c32c) attempting to fix NTLM authentication, it turns out there are multiple places credential providers can be specified on the client, and setting it on the http client itself may help solve a situation where authentication wasn't working for a user. 


### Adding new features

* [#424](https://github.com/dakrone/clj-http/pull/424) added a :mime-subtype parameter so the particular type for multipart requests can be specified
* [#422](https://github.com/dakrone/clj-http/pull/422) added :http-multipart-mode which allows selecting which type of multipart mode is used (defaulting to strict)
* I went through all boolean flags clj-http currently supports and changed them to use the utility method so users can use things like `:insecure true` or `:insecure?` true without having to remember the `?` suffix.
* I made the cookie policy selection pluggable by the user, a user can now extend the get-cookie-policy method to allow selecting their own cookie policy for validation.
* A user requested a way to get the full HTTP request/response string for requests as sent on the socket level, I implemented the sending side of this by adding an option to override the socket factory, returning a socket `OutputStream` that captures the raw data so it can be inspected later. This is behind the new `:capture-socket` option. In future work I plan to add a way to capture data read in at the socket level (think curl's `-v` option).



### Apache 5/deprecated APIs

* I started experimenting with the Apache 5.0 client. This new major version supports HTTP 2, however, it's a major change and breaks a lot of clj-http's code so the work is still ongoing.
 * I'm happy to report that the [apache5-upgrade branch](https://github.com/dakrone/clj-http/compare/apache5-upgrade) now compiles, and that you can use it in a REPL to perform requests! There are still some features that are commented out, as I have not yet discovered the equivalent 5.0 versions (there are **many** changes in the 5.0 upgrade and all the documentation has not been released). I've been going through clj-http's tests to get them passing, but so far this is a much smoother transition than the 3.x -&gt; 4.0 upgrade in the past. While that upgrade took many, many months to transition to the new and un-deprecated APIs, I'm hoping to be much more on top of this major release. Having this branch ready means that it will be a much shorter transition once the new Apache client version is out of beta and 5.0 is released.  
* I continued to work on getting tests to pass with the new Apache 5.0 Http client. I pushed commits fixing a lot invocation exceptions that were caused due to method renames from Apache changes.

### Enhancing async support

* [#432](https://github.com/dakrone/clj-http/pull/432) added a test for an issue fixed by updating the apache dependencies where multipart pieces didn't work when using the async client
* When performing an asynchronous http request, a cancellable BasicFuture is returned. However, this future did not cancel the HttpRequest object, it now aborts the HttpRequest and I've added documentation around how to perform request cancellation (if for instance a certain timeout has been reached).
* Once I enabled the logging I notice that Apache is [logging an "Illegal state ACTIVE"](https://github.com/dakrone/clj-http/issues/443) error when the integration tests are run. I spent quite a while tracking this one down and it ended with [rewriting the way that asynchronous connection pools are handled](https://github.com/dakrone/clj-http/commit/10018154ae6db5db08751ce3f7bad96a20c35aa4), no more error now!

### General maintenance/docs

Updated the readme as it was a little out of date, and added a CONTRIBUTING.md document
* Finally, I triaged all of the existing clj-http issues, there were around ~35 open issues, there are now 16 open issues and they are labeled accordingly. Hopefully this helps any new contributors since stale or over-old issues are no longer an issue.
* I wrote a [guide](https://github.com/dakrone/clj-http/blob/master/SSL.org) that a user [requested](https://github.com/dakrone/clj-http/issues/376) on how to use clj-http with a self-signed certificate so that they could test locally without turning on the :insecure option.
* I released clj-http 3.8.0. This includes many fixes and new features that were added in the first part of the Clojurists Together work, check out the [changelog](https://github.com/dakrone/clj-http/blob/master/changelog.org#380) for the full list of changes that went into 3.8.0, one of the bigger releases!
* [Aleph](https://github.com/ztellman/aleph) uses some of clj-http middleware and ran into a problem this week with transit+json responses that were coming back empty. I spent a while triaging this and determining where aleph and clj-http diverge, discovering that this is not an issue with clj-http, so that's nice to hear.
* While Clojure has its own logging framework, the Apache HTTP client prefers to use log4j for logging. Since this can be an invaluable debugging tool I wrote documentation and example code for how to configure clj-http (or rather, a project using clj-http), see [this commit](https://github.com/dakrone/clj-http/commit/1e530f7679c1497da6b845d4a52ff06c102e9ba0) for the work.
* I've released clj-http [3.9.0](https://github.com/dakrone/clj-http/tree/3.9.0) with all the most recent changes. This version includes the work for reusable http clients, asynchronous request future cancelling, supporting the `:cookie-spec` and `:cookie-policy-registry options`, the `:cache` options I mentioned above, as well as a fix for asynchronous connection pools putting the connection manager in an illegal ACTIVE state. 

### Exposing Apache HTTP client support

* I added a way that users can modify the http client in arbitrary ways. This [added](https://github.com/dakrone/clj-http/commit/2ab22cb75841c712997dc57bfc3e393cdbcd41d1) the `:http-builder-fns` and `:async-http-builder-fns` so users aren't blocked if they need to make low-level changes to the http client builders directly.
* It's not required for clj-http to build a new `HttpClient` object for every request, and up until a recent change there was no way to tell clj-http that you'd like to reuse a particular client. With a commit this week you can now retrieve the HttpClient used and send your own with the `:http-client` parameter. This allows users to do more in-depth customization of the client without relying on the option being added in clj-http first. Part of this also includes polishing the client creation functions (for synchronous and asynchronous clients) that clj-http uses so they can be used by anyone using the library.
* A user opened up [an issue](https://github.com/dakrone/clj-http/issues/444) about clj-http's cookie policy configuration, talking about the documentation being incorrect with regard to plugging your own cookie policy in. I looked into this and discovered he was correct, the wrong part of cookie policies selection was configurable. I spent a while reading up on how Apache implements their cookie spec and cookie spec providers, and [added a couple of new ways to configure the cookie parsing](https://github.com/dakrone/clj-http/commit/2608c3014cf49a800999c43771a8fccd1bfce363) in clj-http.
* A user opened [an issue asking for a cache config option](https://github.com/dakrone/clj-http/issues/445) for the internal `HttpClient` in clj-http, so I looked into this and ended up [adding support for transparent caching of requests from clj-http](https://github.com/dakrone/clj-http/commit/2843e45023850f63906aa976cbcffbb94204d8f0). You can now specify `{:cache true}` in a request for it to use a cache.

## Commits

git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="FEB 1 2018" --until "APRIL 30 2018"

```
* bd851ff - (HEAD -> master, origin/master, origin/HEAD) Fix conn-timeout and conn-request-timeout (2018-04-27 21:49:49 -0600) <Lee Hinman>
* 968b87f - Add TODO for moving timeouts to connection manager (2018-04-27 21:46:10 -0600) <Lee Hinman>
* b5e63b3 - Comment out test that hangs (only for now) (2018-04-27 21:46:00 -0600) <Lee Hinman>
* 8888f59 - Use a better default redirect strategy if non-existent strategy specified (2018-04-27 21:36:59 -0600) <Lee Hinman>
* 9725e26 - Shutting down connection managers is done with .close() now (2018-04-27 21:31:06 -0600) <Lee Hinman>
* 712ad36 - Fix make-proxy-method-with-body for Apache 5 (2018-04-27 21:22:31 -0600) <Lee Hinman>
* cd6937f - Move location of gitter and travis badges (2018-04-27 21:14:40 -0600) <Lee Hinman>
* 9fc5204 - Add Gitter badge (#448) (2018-04-27 21:13:52 -0600) <The Gitter Badger>
* b8ee19f - Remove accidental invoking of boolean flag (2018-04-27 21:11:39 -0600) <Lee Hinman>
* 634334e - Move badges to top and add gitter badge (2018-04-27 21:05:13 -0600) <Lee Hinman>
* 7fb89da - Update readme with 3.9.0 release (2018-04-27 20:58:06 -0600) <Lee Hinman>
* dd22a9d - Fix clj-http.test.conn-mgr-test/t-reusable-conn-mgrs (2018-04-25 22:33:36 -0600) <Lee Hinman>
* 0d83727 - Fix invalid argument to StringEntity and FileEntity constructors (2018-04-25 22:04:31 -0600) <Lee Hinman>
* 8d40b8b - Update readme and changelog for the apache 5 branch merge (2018-04-24 21:28:16 -0600) <Lee Hinman>
* 44cd40f - Drop support for clojure 1.6 (2018-04-24 21:25:53 -0600) <Lee Hinman>
* c0abd35 - Fix compilation for older clojure versions with `boolean?` check (2018-04-24 21:25:25 -0600) <Lee Hinman>
* 30ba027 - Remove old apache dependencies (2018-04-24 21:24:14 -0600) <Lee Hinman>
*   291c1ec - Merge remote-tracking branch 'origin/apache5-upgrade' (2018-04-24 21:22:33 -0600) <Lee Hinman>
|\  
| *   38ea3bf - (origin/apache5-upgrade) Merge remote-tracking branch 'origin/master' into apache5-upgrade (2018-04-24 21:06:06 -0600) <Lee Hinman>
| |\  
| * | 172ee02 - Move more core pieces to apache5 (2018-04-17 20:35:58 -0600) <Lee Hinman>
| * |   16d4e49 - Merge remote-tracking branch 'origin/master' into apache5-upgrade (2018-04-11 17:42:45 -0600) <Lee Hinman>
| |\ \  
| * | | 9729ed0 - All cookies tests now pass (2018-04-03 20:19:21 -0600) <Lee Hinman>
| * | | cbed52b - Start fixing cookie compilation and tests (2018-04-03 20:10:04 -0600) <Lee Hinman>
| * | | b8d40ac - Everything now compiles (2018-04-03 19:55:48 -0600) <Lee Hinman>
| * | | 54f0553 - conn_mgr.clj compiles for now, but is mostly commented out (2018-03-31 21:44:14 -0600) <Lee Hinman>
| * | | ec086ac - Remove now-unneeded core_old.clj (2018-03-31 21:28:45 -0600) <Lee Hinman>
| * | | 5226bff - Convert multipart and multipart tests to 5.0 version (2018-03-31 21:28:33 -0600) <Lee Hinman>
| * | | e35d8f0 - cookies.clj now compiles (2018-03-27 21:50:55 -0600) <Lee Hinman>
| * | | 6fc3cfa - WIP headers.clj (2018-03-27 21:50:47 -0600) <Lee Hinman>
| * | | 9ac4178 - WIP (2018-03-27 21:24:48 -0600) <Lee Hinman>
* | | | f548c6b - Drop support for java 7 (2018-04-24 21:14:53 -0600) <Lee Hinman>
* | | | 8af2032 - Update .travis.yml for 3.x branch (2018-04-24 21:13:51 -0600) <Lee Hinman>
| |_|/  
|/| |   
* | | be8a83c - Fix a couple of issues eastwood found (2018-04-24 20:38:34 -0600) <Lee Hinman>
* | | 692cb0a - Bump dependencies to satisfy lein-nvd (2018-04-24 20:36:44 -0600) <Lee Hinman>
* | | d291460 - Set NTLM authentication at the client build level instead of context (2018-04-18 21:25:04 -0600) <Lee Hinman>
* | | 36d8c3f - Fix issue where `boolean?` was used for older clojure versions (2018-04-18 20:28:42 -0600) <Lee Hinman>
* | | 2843e45 - Add HTTP response caching to the core client (2018-04-18 20:17:48 -0600) <Lee Hinman>
| |/  
|/|   
* | 2608c30 - Add :cookie-spec and :cookie-policy-registry options (2018-04-10 21:42:01 -0600) <Lee Hinman>
|/  
* 1001815 - Redo 'with-async-connection-pool' (2018-03-27 21:05:58 -0600) <Lee Hinman>
* 36bdc14 - Add aws-sig4 to a list of other middleware (2018-03-24 21:32:46 -0600) <Lee Hinman>
* 15ae5c3 - Remove outdated "help wanted" section in readme (2018-03-20 21:12:42 -0600) <Lee Hinman>
* 1e530f7 - Add documentation and usage for configuring Apache-compatible logging (2018-03-20 21:01:32 -0600) <Lee Hinman>
* eca25dd - Abort the HttpRequest when canceling an async future (2018-03-14 18:27:26 -0600) <Lee Hinman>
* 393eb79 - Update changelog (2018-03-13 23:20:00 -0600) <Lee Hinman>
* 98885eb - Add support for re-using HttpClient and HttpAsyncClient (2018-03-13 23:16:05 -0600) <Lee Hinman>
* 90987a9 - Add option to capture socket data (#440) (2018-03-13 21:35:45 -0600) <Lee Hinman>
* cf75459 - Clarify branches in readme (2018-03-08 20:11:04 -0700) <Lee Hinman>
* af7c851 - Update readme with 3.8.0 release and update changelog (2018-03-08 20:07:59 -0700) <Lee Hinman>
* c22a95b - Update tools.reader and core.cache to latest versions (2018-03-08 20:02:08 -0700) <Lee Hinman>
* 6574e0a - Handle empty gzipped responses (2018-02-27 23:54:44 -0700) <Lee Hinman>
* 3d2bbda - Update changelog (2018-02-26 20:55:23 -0700) <Lee Hinman>
* 12ab2bf - Make `get-cookie-policy` method into a multi-method (2018-02-26 20:52:11 -0700) <Lee Hinman>
* 081388b - Add :url with the original URL to the request (2018-02-26 20:44:58 -0700) <Lee Hinman>
* 9350490 - Add documentation about using a self-signed cert with clj-http (2018-02-20 21:37:09 -0700) <Lee Hinman>
* a5328c2 - Update changelog (2018-02-20 20:48:00 -0700) <Lee Hinman>
* 74d24b5 - Fix redirects to an empty location (2018-02-20 20:47:13 -0700) <Lee Hinman>
* c9fce15 - Fix check for options and add test (2018-02-13 20:04:51 -0700) <Lee Hinman>
* 564cfa0 - Multipart async (#432) (2018-02-13 19:53:07 -0700) <grinderrz>
* c830def - Bump clojure version developed against to 1.9.0 (2018-02-12 21:33:10 -0700) <Lee Hinman>
* b6c9c03 - Bump all dependencies (2018-02-12 21:30:44 -0700) <Lee Hinman>
* f16020d - Update readme with latest information (2018-02-12 21:25:47 -0700) <Lee Hinman>
* d0752b0 - Move boolean options to (opt req <option>) (2018-02-12 21:15:57 -0700) <Lee Hinman>
* 7336a8b - Add CONTRIBUTING.md (2018-02-12 21:04:14 -0700) <Lee Hinman>
* 2ab22cb - Add ability to specify arbitrary functions to modify http client builders (#434) (2018-02-12 20:57:46 -0700) <Lee Hinman>
* 47a7762 -  Add options for how to flatten/encode form and query parameters (#433) (2018-02-12 19:48:50 -0700) <Lee Hinman>
*   29116c5 - Merge pull request #428 from kachayev/patch-1 (2018-02-07 19:20:27 -0700) <Lee Hinman>
|\  
| * 6b5ec9e - Update list of unexceptional statuses in README (2018-02-07 22:27:56 +0200) <Alexey Kachayev>
|/  
* 69e3523 - Revert "Remove now unused method parse-form-params" (2018-02-06 19:40:38 -0700) <Lee Hinman>
* ca2b882 - Revert "Wrap nested querystring params before form params" (2018-02-06 19:39:57 -0700) <Lee Hinman>
* 1654c22 - Merge branch 'pr/422' (2018-02-06 19:39:23 -0700) <Lee Hinman>
* 1332b89 - Add lein-ancient (2018-02-04 21:39:21 -0700) <Lee Hinman>
* 9e89582 - Fix readme indentation (2018-02-04 21:25:20 -0700) <Lee Hinman>
* 2a651ff - Add documentation for :mime-subtype (2018-02-04 21:22:55 -0700) <Lee Hinman>
* 118238c - Update changelog (2018-02-04 21:20:17 -0700) <Lee Hinman>
* 3ac1f20 - Only pass mime-subtype, not entire request (2018-02-04 21:14:34 -0700) <Lee Hinman>
* 8a46235 - Merge branch 'pr/424' (2018-02-04 21:09:55 -0700) <Lee Hinman>
* 56a5ae8 - Remove now unused method parse-form-params (2018-02-04 21:05:06 -0700) <Lee Hinman>
* a6e7d66 - Wrap nested querystring params before form params (2018-02-04 21:02:34 -0700) <Lee Hinman>
* 95c96bc - Bump 1.9.0 tests to use 1.9.0 release (2018-02-01 21:39:59 -0700) <Lee Hinman>
```