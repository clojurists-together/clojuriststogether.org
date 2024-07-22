title: "May & June 2024 Long-Term Project Updates"
date: 2024-07-21T14:00:00+12:00
author: Kathy Davis
summary: "The latest from Bozhidar Batsov, Michiel Borkent, Toby Crawley, Thomas Heller, Kira McLean, Nikita Prokopov, Tommi Reiman, Peter Taoussanis"  
draft: True


---
 

A huge thank you to our 2024 long-term developers for their amazing work in May and June. Check out their latest project updates!


[Bozhidar Batsov:](#bozhidar-batsov) CIDER       
[Michiel Borkent:](#michiel-borkent) squint, neil, clj-kondo,nbb, CLI, and more   
[Toby Crawley:](#toby-crawley) clojars-web    
[Thomas Heller:](#thomas-heller) shadow-cljs  
[Kira McLean:](#kira-mclean) Scicloj Libraries and more   
[Nikita Prokopov:](#nikita-prokopov) Datascript, Clj-reload, Clojure Sublimed, and more  
[Tommi Reiman:](#tommi-reiman) Reitit 7.0. Malli    
[Peter Taoussanis:](#peter-taoussanis) http-kit, Nippy, Telemere, and more  


## Bozhidar Batsov   
This period was quite busy and productive for CIDER and friends. The highlights are 3 (!!!) CIDER releases and a couple of nREPL releases:  

- [CIDER 1.14 ("Verona")](https://github.com/clojure-emacs/cider/releases/tag/v1.14.0)  
- [CIDER 1.15 ("Cogne")](https://github.com/clojure-emacs/cider/releases/tag/v1.15.0)  
- [CIDER 1.15.1 ](https://github.com/clojure-emacs/cider/releases/tag/v1.15.1) (small bug-fix release)  
- [nREPL 1.1.2](https://github.com/nrepl/nrepl/releases/tag/v1.1.2) (small bug-fix release)  
- [nREPL 1.2.0](https://github.com/nrepl/nrepl/releases/tag/v1.2.0)  

CIDER 1.14 is our most ambitious release since CIDER 1.8 ("Geneva"), that got released last autumn.  

The single most notable user-visible change of this release is that CIDER is now more robust when evaluating and displaying large values. CIDER will no longer hang when `C-x C-e`ing a big value in a source buffer or stepping over such a value with CIDER debugger.  

I'm guessing that many people will also appreciate the improvements we've made to flex completion (which is finally fully compliant with the Emacs completion API), the inspector and to the cider-cheatsheet functionality which was mostly redesigned.  

nREPL 1.2 restores the ability to interrupt evaluation on JDK 20+ (see https://github.com/nrepl/nrepl/pull/318 for details) and CIDER 1.15 implements support for nREPL 1.2.  

More interesting work is in progress, so I hope I'll have another exciting report for all of you in a couple of months!  <br>

---


## Michiel Borkent  

**Updates**
In this post I'll give updates about open source I worked on during May and June 2024. To see previous OSS updates, go [here](https://blog.michielborkent.nl/tags/oss-updates.html).  

### Sponsors

I'd like to thank all the sponsors and contributors that make this work possible. Without _you_, the below projects would not be as mature or wouldn't exist or be maintained at all.

Current top tier sponsors:  
* [Clojurists Together](https://clojuriststogether.org/)
* [Roam Research](https://roamresearch.com/)
* [Nextjournal](https://nextjournal.com/)
* [Nubank](https://nubank.com.br/)

### Sponsor info  
If you want to ensure that the projects I work on are sustainably maintained, you can sponsor this work in the following ways. Thank you!  
* [Github Sponsors](https://github.com/sponsors/borkdude)
* The [Babaska](https://opencollective.com/babashka) or [Clj-kondo](https://opencollective.com/clj-kondo) OpenCollective
* [Ko-fi](https://ko-fi.com/borkdude)
* [Patreon](https://www.patreon.com/borkdude)
* [Clojurists Together](https://www.clojuriststogether.org/)

If you're used to sponsoring through some other means which isn't listed above, please get in touch.

On to the projects that I've been working on!







---

## Toby Crawley  






---

## 