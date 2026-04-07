---
title: "Clojars Maintenance Update for Q1 2026"
date: 2026-04-07T14:00:00+12:00
author: Kathy Davis
summary: "Toby Crawley: Fixes & Updates Jan.- March 2026"
draft: True



---

## Clojars Q1 2026 Update: Toby Crawley  
Posted April 7, 2026  

This is an update on the work I've done maintaining
[Clojars](https://clojars.org) in January through March 2026 with the
ongoing support of Clojurists Together.  

Most of my work on Clojars is reactive, based on issues reported through
the community or noticed through monitoring. If you have any issues or
questions about Clojars, you can find me in the [`#clojars` channel on
the Clojurians Slack](https://clojurians.slack.com/archives/C0H28NMAS),
or you can file an issue on the [main Clojars GitHub
repository](https://github.com/clojars/clojars-web/issues/new/choose).  

You can see the
[CHANGELOG](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org)
for notable changes, and see all commits in the
[`clojars-web`](https://github.com/clojars/clojars-web/compare/6008d9b73add2c38c0c738eb47203be9b01278cc...fee5d496e47955e63a827fadad43b7abae60bff7)
and
[`infrastructure`](https://github.com/clojars/infrastructure/compare/48ddd9c5968d306b2d539473b32a024f9ef80e93...f6aeb9c83b0e4b56efcdbbcab7d41adfed09822a)
repositories for this period. I also [track my
work](https://tcrawley.org/clojars-worklog/) over the years for
Clojurists Together (and, before that, the [Software Freedom
Conservancy](https://sfconservancy.org/)).  

**Below are some highlights for work done in January through March:**  

- I updated Maven index generation to [use the database instead of downloading POMs from S3](https://github.com/clojars/clojars-web/pull/921). This reduces the monthly hosting cost by ~100 USD/month, as we had to list the full repository bucket each time we wanted to sync. We have all the data we need in the database to do this, but the indexer takes pom files, so we just generate simple poms from the db.  

-  I updated Clojars to use [a fork of http-kit instead of Jetty](https://github.com/clojars/clojars-web/pull/923). This removes Jetty as a dependency as we don't need its complexity and it often brings in CVEs. We have to use a fork of  http-kit to support custom status messages for validation failures (that's the currently supported way to report them using the version of [maven-resolver](https://maven.apache.org/resolver/) currently used by [pomegranate](https://github.com/clj-commons/pomegranate)).  

- I implemented  [RFC 9457 Problem Details responses on deploy validation failures](https://github.com/clojars/clojars-web/pull/924) in addition to status messages. This will be the new way to signal validation failures, once my [PR to update pomegranate](https://github.com/clj-commons/pomegranate/pull/233) is merged, and we're able to update [leiningen](https://codeberg.org/leiningen/leiningen) and [deps-deploy](https://github.com/slipset/deps-deploy** to use that new version.  


**A note on 11 years of Clojars maintenance**  

I became the lead maintainer of Clojars a little over [11 years](https://groups.google.com/g/clojars-maintainers/c/8zw2NBVKVcc/m/V5_LwVxie88J) ago. I've done [quite a bit of work on Clojars](https://github.com/clojars/clojars-web/graphs/contributors) during that period, and have thoroughly enjoyed working on it & supporting the community! I greatly appreciate the support I've gotten from [GitHub sponsors](https://github.com/tobias), the [Software Freedom Conservancy](https://sfconservancy.org/), and Clojurists Together over the years. After all that, it's time for a little break! I'm taking a few months away from Clojars (and computers in general) to go backpacking for a few months. I'm handing off lead maintenance to [Daniel Compton](https://github.com/danielcompton), and it is in good hands!  


