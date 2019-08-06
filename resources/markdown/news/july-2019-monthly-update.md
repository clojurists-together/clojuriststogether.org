title: July 2019 Monthly Update
date: 08/06/2019
type: post
draft: true

## Clojurists Together 

Hi CT Community -

Fireplace has completed a wonderful quarter with Clojurists Together and we are grateful for the opportunity to support Tim's continued work.

Just a reminder that Daniel is speaking about Clojurists Together again soon. Catch him at one of the following local events:

[Strange Loop](https://thestrangeloop.com) - [A Stitch in Time - The future of OSS Sustainability](https://thestrangeloop.com/2019/a-stitch-in-time---the-future-of-oss-sustainability.html)
<br /> September 12-14 in St. Louis, Missouri, USA

Thanks for your continued support of Clojurists Together.

### Fireplace update

This update is mostly responding to user feedback and bug reports from
the async overhaul.

Commits:
* Fix :Connect nrepl://
* Set title on stacktrace location list
* Provide :Stacktrace stub to open location list
* Enable :RunTests! to skip opening quickfix window
* Tweak stdout/stderr display in :Last
* Show message id in :Last location list
* Tighten up trailing newline display after eval
* Fix potential display issue with :RunTests status message
* Add url attribute to session object
* Close quickfix window on :RunTests success
* Handle early transport exit
* Reduce quickfix window agitation on :RunTests
* Avoid "no matching autocommands" warning
* Show success/failure message after :RunTests completion
* Only open :RunTests quickfix window if still active list
* Restore version 8 check due to additional requirements
* Allow Vim versions lower than 8.0 with job support
* Restore plugin file name
* Extract autoload file
* Centralize activation
* Support massaging location list
* Eliminate inadvertent public functions
* Allow namespace aliases to contain a dot
* Support keywords ending in apostrophe
* Use UUID for message id
* Fix session creation
* Fix precedence of overridden session id
* Tighten handling of session id

Did a second pass through the issue tracker, all that's left is a
handful of feature requests. Did some polishing and released 2.0.

Commits:
* fireplace.vim 2.0
* README updates
* Document API
* Return empty string not user ns for .BufferNs() default
* Always use user namespace for :CljEval and :CljsEval
* Document :CljEval as a replacement for :Piggieback
* Provide .Query()
* Support message({...}, v:t_list, callback)
* Tweak :CljEval (.../cljs-repl ...) workflow
* Fix doc typo
* Add camel case instance method API
* Add interrupt handling to #wait()
* Enable multiple message callbacks
* Provide fireplace#wait()
* Lazily connect in #platform(), #clj(), and #cljs()
* Fix broken eval API
* Provide asynchronous #query()
* Support true, false, and null in #query()
* Provide asynchronous .Eval()
* Restore info op backed :Doc for cljs only
* Qualify clojure.core symbols in implicit require
* Restore automatic detection of user invoked piggieback
* Centralize resolution of buffer namespace
* Use jobs for Java spawning eval
* Purge disconnected channels
* Fix potential collision with global function names
* Provide completion for :CljEval and :CljsEval
* Provide global :CljEval and :CljsEval commands
* Introduce #native() as a #platform() replacement
* Make #cljs() automatic piggieback lazy
* Add #clj and #cljs() accessors
* Hard code clojure.core in eval for #info()
* Remove last vestiges of "connection" attribute
* Restore simplified interactive :Connect
* Default nrepl://host to port 7888
* Retrieve Projectionist config at activation time
* Fix omnicomplete on Neovim
* Revert "Automatically require piggieback"
* Automatically require piggieback
* Add interface for supporting other protocols
* Rename Python script
* Use clojure.repl/doc rather than custom doc output
* Replace host/port arguments with URL
* Remove keepalive file
* Remove obsolete Python code
* Provide fireplace#omnicomplete(callback, symbol)
* Remove eval fallback for completion
* Add abstraction for requiring nREPL op
* Make stacktrace retrieval an on demand operation
* Emphasize cider-nrepl in documentation
* Don't fire FireplacePreConnect when acting on explicit buffer
* Use cljc preference for all files except .clj/.cljs
* Provide option configure default cljc platform
* Use ClojureScript for cljc files if already initialized
* Try to be smart about user evaling cljs-repl function
* Encapsulate check for ClojureScript filename
* Use pretty print for :Eval!
* Query projectionist for default ClojureScript REPL
* Remove Nashorn :Piggieback default
* Discard ClojureScript session if user evals :cljs/quit
* Fix return value of #register_port_file()
* Output mutiple eval values
* Remove eval() from session layer
* Show both stdout and stderr in :Last
* Close Piggieback nREPL session when removing it
* Remove stacktrace handling from session layer eval
* Remove dead stacktrace retrieval code from spawning eval
* Remove v:none for nvim compatibility (for real)
* Remove v:none for nvim compatibility
* Provide single message temporary session interface
* Guard against Vim with broken if_pyth
* Test waters removing old fireplace#message() interface
* Return dict not bare id from message creation
* Standardize error handling for no REPL connection
* Include message in ClojureScript stacktrace for symmetry
* Eliminate calls to old single arity variant of message()
* Return session not transport from #register_port_file()
* Make jar contents retrieval more robust
