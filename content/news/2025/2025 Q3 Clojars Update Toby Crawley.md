---
title: "Clojars Maintenance & Support: August-October Update"
date: 2025-11-28T14:00:00+12:00
author: Kathy Davis
summary: Toby Crawley Reports on Clojars logs, fixes, and updates   


---

## 2025 Critical Infrastructure: Clojars Maintentance and Support Update by Toby Crawley
August-October, 2025. Published November 22, 2025   

This is an update on the work I've done maintaining [Clojars](https://clojars.org) with the support of  Clojurists Together in August through October of 2025. Most of my work on Clojars is reactive, based on issues reported through the community or noticed through monitoring.  
If you have any issues or questions about Clojars, you can find me in the [`#clojars` channel on the Clojurians Slack](https://clojurians.slack.com/archives/C0H28NMAS), or you can file an issue on the [main Clojars GitHub repository](https://github.com/clojars/clojars-web/issues/new/choose).   

You can see the [CHANGELOG](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org) for notable changes, and see all commits in the [`clojars-web`](https://github.com/clojars/clojars-web/compare/759866053761e9f685f52520c61fa2bad10ee4b9...d46668f0e1f535f803ae2b8e110ffcef5ab9f124) and [`infrastructure`](https://github.com/clojars/infrastructure/compare/b2e0e61850d9480a7ef16d3dea3075174dd5d862...a89ec7a62f446edb3d095929beb864995e494121) repositories for this period. I also [track my work](https://tcrawley.org/clojars-worklog/) over the years for Clojurists Together (and, before that, the [Software Freedom Conservancy](https://sfconservancy.org/).  

Below are some highlights for work done in August through October:  
- I [enabled backups of the artifact repository S3 bucket](https://github.com/clojars/infrastructure/commit/a89ec7a62f446edb3d095929beb864995e494121). I thought I had done this years ago, but had not, so fixed this oversight.  
- I upgraded Clojars to Clojure 1.12.3 (from 1.12.1) and Java 25 (from 21)  
- We had a couple of places where invalid input would trigger 500s, which resulted in noise from Sentry. One was [invalid emails on password reset](https://github.com/clojars/clojars-web/commit/6524e1f0cfbf086840db8c5334f8530b528438c8), and the other was [null bytes on in project browsing url](https://github.com/clojars/clojars-web/commit/a72af40bb3427fc1f7e8ec83b330943ce7abac45) The latter one was pretty common, as I believe some fuzzing tools do this to try and find vulnerabilities. There are a couple of other places where I need to address them as well.  

Though after October, I enjoyed the opportunity to chat with Clojars users at Clojure/Conj in Charlotte, NC! It was great to see old friends and make some new ones!  

**You can find earlier updates re Clojar fixes and updates here:**
[March - July 2025](https://www.clojuriststogether.org/news/august-2025-clojars-support-and-q2-project-updates/)



