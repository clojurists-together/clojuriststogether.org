title: May 2019 Monthly Update
date: 06/06/2019
type: post
draft: true

## Clojurists Together News

Hi CT Community -

This month we present the first updates from one of our Q2 Projects - Fireplace!

****

Our Project Leader, Daniel Compton, is speaking about Clojurists Together soon! Catch him at one of the following local events:

[Heart of Clojure](https://heartofclojure.eu/)
<br /> August 2nd in Leuven, Belgium

Thanks for your continued support of Clojurists Together!

## Fireplace update

This first period has been been lots of housekeeping and bug fixes.
Much of it has been centered on the relatively small bit of Python
code used for the nREPL network connection, which has needed attention
to Python 3 support now that Python 2 is finally starting to disappear
in the wild.

I also did a spike on async job support and determined it is indeed
viable. It's not pushed yet but most of the associated refactoring is.

Raw list of commits:
* Fix ambiguous use of relative path for port file
* Update installation instructions
* Require Vim 8
* Change shell argument encoding from Bencode to JSON
* Fix typo
* Fix string encoding issue with Python 3
* Eliminate string eval retrieving Python result
* Further fixes to string encoding handling
* Fix nrepl_connection.vim reload with Python 3
* Simplify Python version specific socket code
* Fix dropped messages due to buffering
* Work around if_pyth handling of Vim integers
* Use JSON to output return value for parsing by Vim
* Allow opting out of pretty print
* Fix bdecode error handling
* Add public configuration for Python executable
* Use :python3 for listing jar contents
* Make combine() idempotent
* Use pythonx if available
* Remove zip.vim hack
* Clean up fireplace#source()
* Move bencode from VimL to Python

This time around is more of the same: assorted housekeeping and async
job progress. I've finished my "low hanging fruit" pass through the
issue tracker and have cut a maintenance release. With a little luck,
I'll have the initial job support on master by the next update.

Commits:
* Remove transport level support for old pprint
* Don't make assumptions about colons in host name
* Remove dead code
* Fix reload issues
* Add conventional defaults for matching responses to request
* Grant access to Python traceback
* Separate argument generation from shell escaping
* Stream value responses
* Fix accidental return of None
* Add hard Vim 8 requirement
* Streamline messaging API
* Loosen match for classpath in errorformat
* Use quickfix "module" to tighten up stacktrace display
* fireplace.vim 1.2
