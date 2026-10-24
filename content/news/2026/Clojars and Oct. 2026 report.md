---
title: "Clojars and October 2026 Project Updates"
date: 2026-10-24T14:00:00+12:00
author: Kathy Davis
summary: Clojars support, Malli, SciCloj doc and plotting libraries




---
Our October report includes an update on the Clojars maintenance and 'on-call" support work undertaken by Daniel Compton and Toby Crawley during the last 6 months. In addition, Ambrose Bonnaire-Seargent and Cvetomir Dimov share the most recent updates on their projects. 

[Clojars maintenance and support](#clojars-maintenance-and-infrastructure-overview)  
[Malli: Ambrose Bonnaire-Seargent](#malli-ambrose-bonnaire-seargent)  
[SciCloj Documentation and Plotting Libraries: Cvetomir Dimov](#scicloj-documentation-and-plotting-libraries-cvetomir-dimov)



## Clojars maintenance and infrastructure overview
Clojurists Together funds Toby Crawley and Daniel Compton to co-maintain Clojars. Their work includes responding to operational issues and community support requests, maintaining the service and its infrastructure, applying security updates, improving publishing and administration tools, and keeping hosting reliable and cost-effective. You can follow this work in the Clojars changelog and in our project updates. For help with Clojars, visit the #clojars channel on Clojurians Slack or open an issue. Clojars itself is open source, and contributions are welcome in the Clojars GitHub organization. <br>
**Review period:** April to 30 September 2026  
**Author:** Daniel Compton  
**Context:** I took lead maintenance responsibility for Clojars from April-June while Toby Crawley was away and went back to co-maintainer when he returned.<br>  

##  Clojars maintenance & support: April to June 2026   

### Account and supply-chain security

**Stronger password requirements:** I increased the minimum Clojars password length from eight to twelve characters [#934](https://github.com/clojars/clojars-web/pull/934).  

**Session invalidation after password changes:** I reviewed an earlier proposal for invalidating sessions, then made a smaller implementation [#944](https://github.com/clojars/clojars-web/pull/944). When a user changes or resets a password, Clojars now removes that user's active sessions. This was a fairly low-risk issue, but featured highly in bug bounty submissions.  

**Deploy and token notifications:** I added a migration to enable deploy notification emails for old users created before this preference existed [#943](https://github.com/clojars/clojars-web/pull/943). This will help maintainers detect a supply-chain takeover or unexpected deploys.  
I also reviewed and merged the work adding email alerts when a deploy token is created [#945](https://github.com/clojars/clojars-web/pull/945).  

**Compromised-password detection:**  I started working on Have I Been Pwned password detection in [#951](https://github.com/clojars/clojars-web/pull/951) but haven't completed it.

### Infrastructure reliability and resilience  

**IPv6, DNS management, and redundancy:**  I imported our DNSimple DNS records into Terraform so changes could be reviewed and audited. I also added IPv6 support for `repo.clojars.org.  
I also added Route 53 as a second DNS provider alongside DNSimple (closing [#60](https://github.com/clojars/infrastructure/issues/60)). This increases resiliency to DNS outage issues. This is not a theoretical concern, [in February](https://dnsimple.statuspage.io/incidents/s5rkdrmr3d2d) DNSimple had an DNS resolving outage which caused a Clojars outage.  

**Build and deployment repair:** I fixed the Packer build [#55](https://github.com/clojars/infrastructure/pull/55). I also fixed the app uberjar so it can build on macOS case-insensitive filesystems [#936](https://github.com/clojars/clojars-web/pull/936).  

I also made the instance-refresh script wait for AWS Auto Scaling to report success or failure instead of returning as soon as a refresh started. It reports progress and exits unsuccessfully for failed, cancelled, or rolled-back refreshes. I updated the Leiningen version on the production image to resolve a deployment problem.  

**Infrastructure as code:** I moved the Clojars maintainer IAM group and its day-to-day AWS permissions into Terraform. This replaced console-only configuration with a Terraform maintained policy covering the services needed to operate Clojars, billing information, and Route 53 Domains.  

I added contributor and coding-assistant guidance to the infrastructure repository and updated its setup documentation for DNS credentials and the Terraform workflow.  

### Development workflow  
I added coding agent guidance to help guide usage of coding agents.  <br>


## Clojars maintenance & support: July to September 2026  
This covers Daniel's and Toby's work on Clojars.  

### Daniel's work  

#### Infrastructure hardening  

Daniel changed the production EC2 launch-template configuration to require IMDSv2 tokens and limited metadata responses to one network hop. Before opening the change, he checked CloudWatch metadata metrics and exercised the real Cognitect AWS client against a local IMDSv2-only endpoint [#61](https://github.com/clojars/infrastructure/pull/61).  

He prepared dedicated private database subnets with no internet or NAT route, moved the production RDS configuration into a private subnet group, disabled public accessibility, and enabled deletion protection [#62](https://github.com/clojars/infrastructure/pull/62).  

Daniel also updated the Terraform backend to use the native S3 lockfile supported by current Terraform versions, removing the need for a separate DynamoDB locking table [commit 357062b](https://github.com/clojars/infrastructure/commit/357062b79cda35fe17798ef00eb20aaa94295d5c).  

Toby identified that the production repository metadata still referred to the old HTTP URL in [#966](https://github.com/clojars/clojars-web/issues/966). Daniel then fixed the repo metadata to `https://repo.clojars.org` and managed the S3 object via Terraform [#63](https://github.com/clojars/infrastructure/pull/63).  

#### Application tooling and maintainer review  
Daniel updated the `bin/clj-kondo` bootstrap script to select the correct release for Intel and ARM64 builds on macOS and Linux and to keep architecture-specific cache directories. [Commit 32cf33f](https://github.com/clojars/clojars-web/commit/32cf33fc3a07952eac368b44ab04eaaa8be6723e) landed on 20 August.  

#### On Call management   
Daniel added more monitoring for Clojars and setup an on-call 24x7 pager rotation for Clojars.  

#### Security review  
Daniel began a security review of the Clojars service. No major issues were identified, but some remediation work is in progress and will be published soon.  

### Toby's work  

#### Clojure Conj Talk  
Toby gave a talk at the 2026 Clojure Conj about the history of Clojars and how it works. The video will be available on the [ClojureTV](https://www.youtube.com/ClojureTV) YouTube channel soon.  

#### Account, session, and authentication improvements  
Toby simplified the normal login form so it only requests an MFA code after a user with MFA enabled has supplied valid credentials [#950](https://github.com/clojars/clojars-web/pull/950).  

He moved sessions from the application's memory into PostgreSQL, added a twenty-four-hour idle expiry, and added database cleanup for expired sessions. This makes longer-lived sessions practical and is one of the required steps for us to run multiple application instances - [#964](https://github.com/clojars/clojars-web/pull/964).  

Toby tightened what the OAuth workflow returns so unserialisable provider data would not leak into session state, then corrected the workflow response handling after GitHub login failed in production. These fixes landed directly as [commit 1896a51](https://github.com/clojars/clojars-web/commit/1896a511281643c24b261e716c49126a234da8a7) and [commit e9542cf](https://github.com/clojars/clojars-web/commit/e9542cfef3695e570a6e9142f15a6affcdaa0624).  

He also updated GitLab login to accept all email addresses that GitLab marks as verified instead of requiring the address to be primary [#971](https://github.com/clojars/clojars-web/pull/971). Separately, [#969](https://github.com/clojars/clojars-web/pull/969) added the Clojars account name to notification-email footers, making messages unambiguous for people who have more than one account associated with an address.  

#### Supply-chain and dependency security  
Toby updated Jackson, the PostgreSQL driver, and Logback to address reported vulnerabilities in [#956](https://github.com/clojars/clojars-web/pull/956), then handled another set of dependency vulnerabilities and cleaned up obsolete or spurious suppressions in [#965](https://github.com/clojars/clojars-web/pull/965). A further September dependency update addressed eight CVEs in [commit 67727be](https://github.com/clojars/clojars-web/commit/67727beb0285264374326efe201a06ebabe5791a).  

He prevented publication of POM and Gradle module metadata containing dynamic dependency versions, e.g. version ranges like `[16,17)`, RELEASE, or SNAPSHOT [#960](https://github.com/clojars/clojars-web/pull/960).  

Toby removed the separate Apache HTTP client stack in favour of the http-kit client already in use, reducing the dependency and CVE surface [#961](https://github.com/clojars/clojars-web/pull/961). He also switched from a temporary fork of the `one-time` library back to its official release [#962](https://github.com/clojars/clojars-web/pull/962).  

#### Development, CI, and operational reliability  
Toby updated clj-kondo, adopted its new constant-condition linter, and fixed the issue the new check found [#963](https://github.com/clojars/clojars-web/pull/963).  

After removing Apache HttpClient, he fixed Fastly cache purging by switching to Fastly's POST API, which the http-kit client supports. [Commit 0c7e45a](https://github.com/clojars/clojars-web/commit/0c7e45ae0a57775cb4d04d410fb605cff95ea413) landed on 22 July. He also aligned the local Docker Compose environment with the PostgreSQL 18.4 version used in production in [commit 61eb196](https://github.com/clojars/clojars-web/commit/61eb196dbf2fdab266e90477b948125c975c639c).  

Toby replaced the unavailable MinIO test image with S3Mock, updated the CircleCI base image, and repaired Maven dependency caching that had not been using the intended path [#970](https://github.com/clojars/clojars-web/pull/970).  

His infrastructure maintenance increased Fastly log retention for analysis, corrected that policy so it actually retained 120 days, and fixed the instance-selection script so status messages no longer contaminated the IP address returned to callers. These changes landed as [commits 5ebe01a](https://github.com/clojars/infrastructure/commit/5ebe01adea08e57989d7c669d25ae13a7009140a), [a994755](https://github.com/clojars/infrastructure/commit/a994755a12f1b062af621b4feb384511e72d6ed4), and [f615c38](https://github.com/clojars/infrastructure/commit/f615c38e319363369fe4651be7241d6e9df06882). <br>  

---


## Malli: Ambrose Bonnaire-Seargent  
Q2 2026 Final Report 3. Published October 15, 2026   


In this project, I am tackling exponential growth related to Malli refs. This is my final update of three. Thanks to Clojurists Together and Metabase for supporting this project.  

I concentrated on three activities for this time period.  

Draft PR: https://github.com/metosin/malli/pull/1284/changes  

First, I added documentation to my branch explaining what the proposed changes to `malli.core`
enables. See the [README.md changes in my pull request](https://github.com/metosin/malli/pull/1284/changes#diff-b335630551682c19a781afebcf4d07bf978fb1f8ac04c6bf87428ed5106870f5).  

Second, I added more complex test cases for the new cycle detection in schemas.
There were a few useful test cases I introduced years ago to test cycle detection
in `malli.generators`, and now I have repurposed them for `malli.core` tests as the cycle detection
has moved there.  

Third, I took some time to deeply consider why a subtle condition is needed in `malli.generator`,
that disables caching for generators if we're currently under a `gen/recursive-gen`.
Again, this was something I added years ago but didn't fully understand its function.
Earlier in this project, I had removed the condition, but it caused the tests to fail.
It resulted in [this small documentation change](https://github.com/metosin/malli/commit/a446dac30f5055e92a0ed26f20786d52ddd72c35).
I don't know if we can improve the caching in this area of code, and it's a potential
area of future work. I suspect there's more to learn here, and it may reveal a more
effective approach to creating generators.  

I'm pretty happy with the code, overall it elegantly resolves several hacks deep in Malli's design while
either conserving or improving performance, and exposes them such that extensions can better support recursive schemas.
I need to do some work to update the PR description and communicate my proposal to the Malli maintainers.  

A goal of this project was to address a hack in Metabase around the performance of ref schemas.
I think this work has brought us closer to that goal by better understanding how to detect cycles in schemas
and when it is safe to reuse schema objects. The project concentrated on a smaller scope, mainly property registries,
and I documented some ideas for how we could generalize the benefits to more complex registries. More importantly,
the time spent considering the smaller scope will give greater confidence that such generalizations will actually
be correct and a good idea. I hope to find out soon.  <br>  

---


### SciCloj Documentation and Plotting Libraries: Cvetomir Dimov  
Q2 2026 Final Report 1. Published September 30, 2026  

During the first month, I worked on both documentation and libraries. In terms of documentation, I mostly finished with the redesign of the Noj website. The website now looks much more modern with a theme inspired by the Macroexpand conference website and a main page. It also makes sure to spell out Noj's main strengths, it presents the main libraries in an appealing way, and links to Zulip, Github and SciCloj. Moreover, the website is now structured to have separate sections for a quick start, for a book to learn data science with Noj, and for library references.

In terms of library development, initial steps have been made toward a new library for interactive dashboards and for porting the dataset library Tablecloth to ClojureScript. Specifically, the repository of the interactive dashboard library has been initiated, a feature list has been created and the base architecture has been designed. Currently, the process for selecting the required technologies is taking place. For Tablecloth, I have started studying the design of the existing Clojure library. 
