---
title: "August 2025 Clojure Support and Q2 Project Updates"
date: 2025-09-12T14:00:00+12:00
author: Kathy Davis
summary: "Clojure Support, Code Combat, SciCloj Building Bridges"



---
Greetings all! 
We have updates from two Q2 projects and a new report from Toby Crawley who provides ongoing support and maintenance for Clojure. You'll find a brief description of the all these projects below.  


[Toby Crawley: Clojure Support and Maintenance](#clojure-support-and-maintenance-toby-crawley)    
Toby's report includes links to Clojure Changelogs for March through July 2025 as well as an overview of fixes and updates. He monitors community channels on a regular basis.
If you have any issues or questions about Clojars, you can find him in the [`#clojars` channel on the Clojurians Slack](https://clojurians.slack.com/archives/C0H28NMAS), or you can file an issue on the [main Clojars GitHub repository](https://github.com/clojars/clojars-web/issues/new/choose).  
  

[Karl Pietzrak: Code Combat](#code-combat-karl-pietzrak)   
This project will focus on adding Clojure(Script) to CodeCombat. 
See Wiki page at https://github.com/codecombat/codecombat/wiki/Aether  


[Siyoung Byun: SciCloj Building Bridges to New Clojure Users](#scicloj-building-bridges-to-new-clojure-users-siyoung-byun)   
Scicloj aims to improve the accessibility of Clojure for individuals working with data, regardless of their programming backgrounds. The project aims to develop standardized templates to encourage greater consistency across the documentation of existing Scicloj ecosystem libraries, making those libraries more robust and user-friendly.  <br>




## Clojure Support and Maintenance: Toby Crawley  
This is an update on the work I've done maintaining [Clojars](https://clojars.org) in March through July 2025 with the ongoing support of Clojurists Together. 

Most of my work on Clojars is reactive, based on issues reported through the community or noticed through monitoring. If you have any issues or questions about Clojars, you can find me in the [`#clojars` channel on the Clojurians Slack](https://clojurians.slack.com/archives/C0H28NMAS), or you can file an issue on the [main Clojars GitHub repository](https://github.com/clojars/clojars-web/issues/new/choose). 

You can see the [CHANGELOG](https://github.com/clojars/clojars-web/blob/main/CHANGELOG.org) for notable changes, and see all commits in the [`clojars-web`](https://github.com/clojars/clojars-web/compare/0aaeb277fa4ff7ce75533d6a915ff226b5f10c1d...759866053761e9f685f52520c61fa2bad10ee4b9) and [`infrastructure`](https://github.com/clojars/infrastructure/compare/42610d719338aba1b44a84d8c437f82a39fd5591...b2e0e61850d9480a7ef16d3dea3075174dd5d862) repositories for this period. I also [track my work](https://tcrawley.org/clojars-worklog/) over the years for Clojurists Together (and, before that, the [Software Freedom Conservancy](https://sfconservancy.org/).  

**Below are some highlights for work done in March through July:**

- I finally addressed issues with running out of memory on occasion. It turned out to be the in-memory session store; we were using [aging
    sessions](https://github.com/kirasystems/aging-session), but we were generating enough sessions in 48 hours (the session ttl) to exhaust the heap. [Adjusting the ttl to 1 hour](https://github.com/clojars/clojars-web/commit/5d50868decdf95b8014a957f4f88635695dcc3ee) solved the problem, but a better long-term solution would be to not create a session until a user logs in, as that is all we need a session for. Clojars currently creates a session for each visit to the site.  
- Clojars was storing uploads in `/tmp` during deploys, and there is no signal to when a deploy is complete, so we can't delete them at the end of the deploy. This was causing the server to run out of disk space, so I [moved upload storage to a larger partition](https://github.com/clojars/clojars-web/commit/318fff4a23feaf6931e326e50d735c6c4363629a), and made [tmp file cleanup happen more often](https://github.com/clojars/infrastructure/commit/164091a948bb8b67cd9edd6cc5ff68bd7860b494).  
- We had some client that was repeatedly connecting to Clojars, then failing TLS negotiation, then trying again. This caused our AWS load balancer expense to increase by several hundred dollars, so I [blocked that IP address from accessing Clojars](https://github.com/clojars/infrastructure/commit/ed2e08cb17d835409deec91ac4b52b0308b9a983).  
- I upgraded a few dependencies to address some CVEs.  
- I worked on spiking out how to implement using [Problem Details (rfc9457)](https://www.rfc-editor.org/rfc/rfc9457) to return deploy validation failures to the client. See [this issue](https://github.com/clojars/clojars-web/issues/911) for more details.  

I worked with [Ambrose Bonnaire-Sergeant](https://github.com/frenchy64) on some security (& other) fixes:  
-   [Fixing](https://github.com/clojars/clojars-web/commit/baade8967c7be8abd9a9b27499c511efd41f6164), then [inlining](https://github.com/clojars/clojars-web/commit/d3623de947dcba56392c3e2bc3041ed3c1bf89a5) a `deps.edn` alias we used to override versions to resolve CVEs. We weren't actually using the alias when building the uberjar, and then realized we didn't need the alias at all, as those dependencies could be top-level.  
-   [Adding a `pom.xml`](https://github.com/clojars/clojars-web/pull/907) to the repository to allow Dependabot to detect vulnerable dependencies.  
-   [Importing/adding clj-kondo configurations](https://github.com/clojars/clojars-web/pull/905) for dependencies to give better linting.  <br>  


---


## Code Combat: Karl Pietzrak      
Q2 2025 $2k. Report No. 1, Published 9 September 2025   

- Successfully deployed CodeCombat on our own private cloud (GCP)  
- Some of CodeCombat's dependencies are very old:  
    - for sandboxing user code, Aether, hasn't been updated since 2017  
    - closer.js, which contains some Clojure support, hasn't been touched since 2014.  
We are in the process of updating these so build in a modern context, as many of their dependencies are dead.  

Progress update to follow in our next report.  <br>  

---


## SciCloj Building Bridges to New Clojure Users: Siyoung Byun  
Q2 2025 $2k. Report No. 2, Published 14 August 2025  

In the time since my previous update I have continued to focus on the community expansion side of the Scicloj initiative. The work and effort continues as a long-term goal alongside other dedicated contributors in the Scicloj community.  

### Macroexpand 2: Connecting Clojure Data Practitioners  

I co-hosted and prepared a three-hour-long online discussion session called [Macroexpand 2: Connecting Clojure Data Practitioners](https://clojureverse.org/t/macroexpand-2-connecting-clojure-data-practitioners/11485) as part of [Macroexpand gatherings](https://scicloj.github.io/docs/community/groups/macroexpand/) on August 9th. Working with co-host Daniel Slutsky, I planned the event agenda and reached out to Clojurians from diverse backgrounds and positions to invite them to join and share their insights at this gathering.   

This initiative represents a step forward in positioning Scicloj not only as a data science community, but as a platform for community expansion, networking, and outreach in the Clojure ecosystem by providing dedicated space and time for discussing and sharing knowledge between individuals and organizations.  

### Purpose of the Gathering  
The gathering aimed to share real-world experiences and challenges, provide networking opportunities for fellow Clojurians while identifying and developing concrete solutions to common problems, and shape clearer direction for follow-up sessions.  

### Key Outcomes  
The gathering brought together participants from different organizations with varied backgrounds, giving us an opportunity to collect valuable insights as individuals, groups, and as a community. The event included participant introductions, lightning rounds where some attendees presented ongoing projects, community expansion efforts, educational outreach initiatives, and discussions about potential tools or platforms for facilitating hiring and job opportunities within the Clojure ecosystem.  

Through discussions about success stories of using Clojure in data work, current challenges, and hiring market impressions, we identified several key insights: including the critical importance of integration libraries and tools for other languages (particularly with an example with Python integration).   

Other insights included the value of staying connected within the community not only for technological updates but also for accessing new job and collaboration opportunities, and the importance of using streamlined communication channels to maintain community connections. We also explored actionable strategies the community could implement to support and sustain a healthy, thriving Clojure ecosystem.  

We partially recorded the gathering, and will publish an edited version soon.  

### Further Vision  
This gathering represents an ongoing initiative rather than a one-time event. Following this successful first session, we are confident that such community discussions are both important and needed. I will continue organizing future gatherings, with formats evolving based on participants inputs and feedbacks along the way. I am excited about the potential impact of this initiative, and look forward to contributing and sharing its outcome in the future.  


### Macroexpand 2025: Two Upcoming Online Conferences  
[Macroexpand 2025](https://scicloj.github.io/macroexpand-2025/)(two online conferences) will be held in October 2025 hosted by Scicloj. I have been working as a co-organizer to prepare for the conferences, and this initiative will continue.  

### About the Conferences  
**Macroexpand-Noj** (October 17-18, 2025) focuses on practical applications of the Noj toolkit in real-world data science, featuring talks on any data analysis related projects and methology, updates on the tool, documentation efforts. And **Macroexpand-Deep** (October 24-25, 2025) is dedicated to AI system research and applications in Clojure, exploring deep learning networks, large language models, vector embeddings, and supporting infrastructure.  

### Current Work  
As one of the organizers, I am actively preparing and organizing both conferences. I published [a post](https://clojurecivitas.github.io/scicloj/macroexpand/macroexpand_2025.html) about the upcoming conferences on Clojure Civitas, including a call for speakers.  

I will continue to assist potential speakers in preparing talks, brainstorm talk ideas with interested participants, reach out to community members, help others conceptualize their projects for presentation, contribute to Scicloj organizing team discussions, and plan the upcoming conferences. This remains an ongoing initiative with significant potential for community impact.  




