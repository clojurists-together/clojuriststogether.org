---
title: May 2019 Monthly Update
date: "2019-06-06"
type: post
---

## Clojurists Together News

Hi CT Community -

This month we present the first updates from one of our Q2 Projects - Fireplace!

---

![Daniel talking at Bay Area Clojure](/images/may-2019/bay-area-clojure.jpeg)

Last month, Our Project Leader Daniel Compton spoke about Clojurists Together at the [Bay Area Clojure Meetup](https://www.meetup.com/The-Bay-Area-Clojure-User-Group/events/261141279/), and at the [Clojure NYC Meetup](https://www.meetup.com/Clojure-nyc/events/260728030/). It was great to be able to meet people and talk about Clojurists Together with the community.

![Daniel talking at Clojure NYC](/images/may-2019/clojure-nyc.jpeg)

We were excited to see [Devon Zuegel](https://twitter.com/devonzuegel) mentioned Clojurists Together at GitHub Satellite as part of the inspiration for their new [GitHub Sponsors](https://github.com/sponsors) program!

![GitHub Sponsors](/images/may-2019/github-satellite.jpeg)

Daniel is also speaking about Clojurists Together again soon! Catch him at one of the following local events:

[Heart of Clojure](https://heartofclojure.eu/) - [Money for Nothing: The past and future of funding OSS](https://heartofclojure.eu/program#daniel-compton)
<br /> August 2nd in Leuven, Belgium

[Strange Loop](https://thestrangeloop.com) - [A Stitch in Time - The future of OSS Sustainability](https://thestrangeloop.com/2019/a-stitch-in-time---the-future-of-oss-sustainability.html)
<br /> September 12-14 in St. Louis, Missouri, USA

Thanks for your continued support of Clojurists Together!

## Fireplace update

### May 1 - 15

This first period has been been lots of housekeeping and bug fixes.
Much of it has been centered on the relatively small bit of Python
code used for the nREPL network connection, which has needed attention
to Python 3 support now that Python 2 is finally starting to disappear
in the wild.

I also did a spike on async job support and determined it is indeed
viable. It's not pushed yet but most of the associated refactoring is.

Raw list of commits:

- Fix ambiguous use of relative path for port file
- Update installation instructions
- Require Vim 8
- Change shell argument encoding from Bencode to JSON
- Fix typo
- Fix string encoding issue with Python 3
- Eliminate string eval retrieving Python result
- Further fixes to string encoding handling
- Fix nrepl_connection.vim reload with Python 3
- Simplify Python version specific socket code
- Fix dropped messages due to buffering - [#278](https://github.com/tpope/vim-fireplace/issues/278)
- Work around if_pyth handling of Vim integers
- Use JSON to output return value for parsing by Vim
- Allow opting out of pretty print
- Fix bdecode error handling
- Add public configuration for Python executable
- Use :python3 for listing jar contents
- Make combine() idempotent
- Use pythonx if available
- Remove zip.vim hack
- Clean up fireplace#source()
- Move bencode from VimL to Python

### May 16 - 31

This time around is more of the same: assorted housekeeping and async
job progress. I've finished my "low hanging fruit" pass through the
issue tracker and have cut a maintenance release. With a little luck,
I'll have the initial job support on master by the next update.

Commits:

- Remove transport level support for old pprint
- Don't make assumptions about colons in host name
- Remove dead code
- Fix reload issues
- Add conventional defaults for matching responses to request
- Grant access to Python traceback
- Separate argument generation from shell escaping
- Stream value responses
- Fix accidental return of None
- Add hard Vim 8 requirement
- Streamline messaging API
- Loosen match for classpath in errorformat
- Use quickfix "module" to tighten up stacktrace display
- fireplace.vim 1.2

Related issues: [#276](https://github.com/tpope/vim-fireplace/issues/276), [#290](https://github.com/tpope/vim-fireplace/issues/290), [#329](https://github.com/tpope/vim-fireplace/issues/329), [#336](https://github.com/tpope/vim-fireplace/issues/336),
