---
title: July 2019 Monthly Update
date: 2019-08-06T00:00:00+12:00
type: post
---

## Clojurists Together

Hi CT Community -

Daniel has just come back from Belgium to speak at Heart of Clojure about building stable foundations. The talk was recorded and will be coming out sometime in the future. We met lots of Clojurists Together supporters at the conference and had a stand there to talk about the work we are able to fund thanks to our members.

![Heart of Clojure talk](/images/july-2019/hoc-projects.jpg)

<blockquote class="twitter-tweet"><p lang="en" dir="ltr">Daniel&#39;s favourite slide :) Developer members of Clojurists Together <a href="https://twitter.com/hashtag/heartofclojure?src=hash&amp;ref_src=twsrc%5Etfw">#heartofclojure</a> <a href="https://t.co/6qNgPtwivT">pic.twitter.com/6qNgPtwivT</a></p>&mdash; Kimmo Koskinen (@KimmoKoskinen) <a href="https://twitter.com/KimmoKoskinen/status/1157577433790451712?ref_src=twsrc%5Etfw">August 3, 2019</a></blockquote> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

(Daniel also had slides thanking our company members but we didn't get any photos of them. If you have any, let us know!)

You'll notice that the Clojurists Together has started to get a fresher look. We've been very fortunate to receive some design assistance from [@paintparty](https://github.com/paintparty) to improve the visual design and information architecture of the site. Thanks!

Fireplace has completed a wonderful quarter with Clojurists Together culminating in a release of [version 2.0](https://github.com/tpope/vim-fireplace/releases/tag/v2.0). Tim has done a ton of great work and we are grateful for the opportunity to support him.

Just a reminder that Daniel is speaking about Clojurists Together again soon. Catch him at [Strange Loop](https://thestrangeloop.com) talking about [A Stitch in Time - The future of OSS Sustainability](https://thestrangeloop.com/2019/a-stitch-in-time---the-future-of-oss-sustainability.html)
<br /> September 12-14 in St. Louis, Missouri, USA

Thanks for your continued support of Clojurists Together.

### Boot update

Keen observers of Clojurists Together may remember that we funded Matthew Ratzke this quarter to work on version 3 and 4 of Boot. However, other things came up and it turned out Matthew wasn't able to work on Boot during this time period. We cancelled this grant with no money paid out, but would love to support Boot sometime again in the future.

### Fireplace update

#### July 1-15

This update is mostly responding to user feedback and bug reports from
the async overhaul.

Commits:

- Fix :Connect nrepl://
- Set title on stacktrace location list
- Provide :Stacktrace stub to open location list
- Enable :RunTests! to skip opening quickfix window
- Tweak stdout/stderr display in :Last
- Show message id in :Last location list
- Tighten up trailing newline display after eval
- Fix potential display issue with :RunTests status message
- Add url attribute to session object
- Close quickfix window on :RunTests success
- Handle early transport exit
- Reduce quickfix window agitation on :RunTests
- Avoid "no matching autocommands" warning
- Show success/failure message after :RunTests completion
- Only open :RunTests quickfix window if still active list
- Restore version 8 check due to additional requirements
- Allow Vim versions lower than 8.0 with job support
- Restore plugin file name
- Extract autoload file
- Centralize activation
- Support massaging location list
- Eliminate inadvertent public functions
- Allow namespace aliases to contain a dot
- Support keywords ending in apostrophe
- Use UUID for message id
- Fix session creation
- Fix precedence of overridden session id
- Tighten handling of session id

#### July 16-31

Did a second pass through the issue tracker, all that's left is a
handful of feature requests. Did some polishing and released 2.0.

Commits:

- fireplace.vim 2.0
- README updates
- Document API
- Return empty string not user ns for .BufferNs() default
- Always use user namespace for :CljEval and :CljsEval
- Document :CljEval as a replacement for :Piggieback
- Provide .Query()
- Support message({...}, v:t_list, callback)
- Tweak :CljEval (.../cljs-repl ...) workflow
- Fix doc typo
- Add camel case instance method API
- Add interrupt handling to #wait()
- Enable multiple message callbacks
- Provide fireplace#wait()
- Lazily connect in #platform(), #clj(), and #cljs()
- Fix broken eval API
- Provide asynchronous #query()
- Support true, false, and null in #query()
- Provide asynchronous .Eval()
- Restore info op backed :Doc for cljs only
- Qualify clojure.core symbols in implicit require
- Restore automatic detection of user invoked piggieback
- Centralize resolution of buffer namespace
- Use jobs for Java spawning eval
- Purge disconnected channels
- Fix potential collision with global function names
- Provide completion for :CljEval and :CljsEval
- Provide global :CljEval and :CljsEval commands
- Introduce #native() as a #platform() replacement
- Make #cljs() automatic piggieback lazy
- Add #clj and #cljs() accessors
- Hard code clojure.core in eval for #info()
- Remove last vestiges of "connection" attribute
- Restore simplified interactive :Connect
- Default nrepl://host to port 7888
- Retrieve Projectionist config at activation time
- Fix omnicomplete on Neovim
- Revert "Automatically require piggieback"
- Automatically require piggieback
- Add interface for supporting other protocols
- Rename Python script
- Use clojure.repl/doc rather than custom doc output
- Replace host/port arguments with URL
- Remove keepalive file
- Remove obsolete Python code
- Provide fireplace#omnicomplete(callback, symbol)
- Remove eval fallback for completion
- Add abstraction for requiring nREPL op
- Make stacktrace retrieval an on demand operation
- Emphasize cider-nrepl in documentation
- Don't fire FireplacePreConnect when acting on explicit buffer
- Use cljc preference for all files except .clj/.cljs
- Provide option configure default cljc platform
- Use ClojureScript for cljc files if already initialized
- Try to be smart about user evaling cljs-repl function
- Encapsulate check for ClojureScript filename
- Use pretty print for :Eval!
- Query projectionist for default ClojureScript REPL
- Remove Nashorn :Piggieback default
- Discard ClojureScript session if user evals :cljs/quit
- Fix return value of #register_port_file()
- Output mutiple eval values
- Remove eval() from session layer
- Show both stdout and stderr in :Last
- Close Piggieback nREPL session when removing it
- Remove stacktrace handling from session layer eval
- Remove dead stacktrace retrieval code from spawning eval
- Remove v:none for nvim compatibility (for real)
- Remove v:none for nvim compatibility
- Provide single message temporary session interface
- Guard against Vim with broken if_pyth
- Test waters removing old fireplace#message() interface
- Return dict not bare id from message creation
- Standardize error handling for no REPL connection
- Include message in ClojureScript stacktrace for symmetry
- Eliminate calls to old single arity variant of message()
- Return session not transport from #register_port_file()
- Make jar contents retrieval more robust
