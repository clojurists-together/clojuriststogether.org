---
title: "Clojurists Together is Funding Clojars"
date: 2020-04-24T09:03:12+12:00
author: Daniel Compton
summary: "Clojurists Together is funding Clojars to improve its security, reliability, and ensure its long-term future"
---

In yesterdays [funding announcement](https://www.clojuriststogether.org/news/q2-2020-funding-announcement/) I forgot to mention that Clojurists Together is also going to be funding Clojars.

Note: This work was planned and approved by the Clojurists Together committee over December 2019-January 2020. However we didn't get approval from the SFC until April, so we couldn't announce it until now.

## Background

Toby Crawley and I (Daniel Compton) are the maintainers and admins of [Clojars](https://clojars.org). As many of you are aware, Clojars is a critical part of the Clojure ecosystem. A security issue could also affect sister JVM languages as companies often add Clojars as a package repo in Nexus and Artifactory. For the last few years, Clojars has run without a lot of maintenance or attention. Neither Toby nor I have had a lot of free time to spend on Clojars, and we’ve been lucky that there have been no emergencies. However, there are a number of tasks that have built up in this time. Most of them aren't urgent, but if we don’t start paying down our maintenance debt, then Clojars becomes increasingly likely to suffer a security incident or significant outage.

The committee has decided to fund Toby Crawley (A Clojars administrator and previous committee member) to work on maintaining and running the Clojars repository. There are two parts to this work:

## Shift to AWS

The first part is a major rearchitecture to move Clojars from Rackspace to AWS. Since January 2016, Rackspace has provided free infrastructure (virtual machines and object storage) for Clojars to run on. Around the end of 2019 Rackspace discounts for OSS projects have been expiring, and we need to move away. We want to publicly thank Rackspace for their support for the last three years. Toby and I decided that AWS was the best place for us to move to. We’ve applied for [AWS’ OSS funding program](https://aws.amazon.com/blogs/opensource/aws-promotional-credits-open-source-projects/) and have been accepted.

Toby started on this project over the 2019-2020 Christmas holidays. As part of the move to AWS, Toby has also moved from Sqlite to RDS Postgres, made S3 the primary repository store instead of disk, and many other changes to make Clojars more secure and reliable.

The Clojars committee has approved $10,000 for this project.

## Ongoing maintenance

The second part is funding Toby as an administrator for Clojars. This involves:

* OS and system package upgrades
* Repo deletion requests
* Reviewing and merging PRs
* Deploying changes
* Monitoring bandwidth usage to spot anything bad
* General operational tweaks and improvements
* Tuning search algorithms
* Security improvements, fixes, and responding to security reports

The Clojars committee has approved paying Toby for $1,500/month for six months for January-June ($9,000 total). At that point Toby and the committee will reassess and check if it is working for him, his family, etc. and whether Clojars needs more or less work. There is a backlog of security work which Toby will work to address in this time, after that Clojars may need less time spent on it.

## Thanks!

Thanks to all of our [members](/members/) for your continuing support and making this possible.

Clojars has been providing free JAR hosting to the Clojure community since November 2009. We're pleased to be able to fund Clojars to ensure it stays strong for the coming decade.
