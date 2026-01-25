---
title: "Clojars Maintenance and Support: November/December 2025 Update"
date: 2026-01-25T14:00:00+12:00
author: Kathy Davis
summary: Toby Crawley Reports on Clojars logs, fixes, and updates   
draft: True

---

## Critical Infrastructure: Clojars Maintentance and Support Update by Toby Crawley  
November-December, 2025. Published January 24, 2026     

This is an update on the work I've done maintaining [Clojars](https://clojars.org) in November and December of 2025.   

Most of my work on Clojars is reactive, based on issues reported through the community or noticed through monitoring.   

**If you have any issues or questions about Clojars,** you can find me in the [`#clojars` channel on the Clojurians Slack](https://clojurians.slack.com/archives/C0H28NMAS), or you can file an issue on the [main Clojars GitHub repository](https://github.com/clojars/clojars-web/issues/new/choose).   

You can see the [CHANGELOG](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org) for notable changes, and see all commits in the [`clojars-web`](https://github.com/clojars/clojars-web/compare/d46668f0e1f535f803ae2b8e110ffcef5ab9f124...6008d9b73add2c38c0c738eb47203be9b01278cc) and [`infrastructure`](https://github.com/clojars/infrastructure/commit/48ddd9c5968d306b2d539473b32a024f9ef80e93) repositories for this period. I also [track my work](https://tcrawley.org/clojars-worklog/) over the years for Clojurists Together (and, before that, the [Software Freedom Conservancy](https://sfconservancy.org/).  

Below are some highlights for work done in November and December:  

- I did lots of dependency upgrades, based on an [`antq`](https://github.com/liquidz/antq) task I added to the project.  
- I [fixed a bug where would still try do generate an email for an address in the denylist](https://github.com/clojars/clojars-web/commit/1c950b45c2d24cb230cd033f3a13940968633ba4), which resulted in an NPE and noise in Sentry.  

