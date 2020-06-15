## Clojurists Together goals

Support open source software, infrastructure, and documentation that is important to the Clojure and ClojureScript community.

## Project proposal

### What are you wanting to achieve with this funding?

- Integration of Clojure specs 
- Integration of user-contributed examples 
- Offline documentation downloads (Dash/Zeal integration) 
- Improve overall user experience / UI 
- Support users and make them aware cljdoc exists 
- Build an active community around this project, ensure there's more than just one developing it 

### Why is this project important to the Clojure community?

Cljdoc is a website that builds and hosts documentation for Clojure and ClojureScript libraries. Poor and out-of-date documentation has long been a complaint of Clojure developers. It improves the state of Clojure documentation (libraries + language) by providing minimal-effort documentation building and hosting for all Clojure jars on Clojars and Maven.

## Work log

### I switched the **storage layer**. 
- SQLite is now used instead of lib-grimoire. More details in the respective architecture decision record: [ADR-0013](https://github.com/cljdoc/cljdoc/blob/master/doc/adr/0013-move-to-sqlite-for-storage.md)

- This was a lot of work but it sets the project up for more interesting extensions besides API documentation and articles (think specs & examples).

### A look into **integrating specs** 
- Without changes to spec it is impossible to determine if a spec originates from the artefact that is being analysed or from one of its dependencies. To fix this specs will need to support metadata ([CLJ-2194](https://dev.clojure.org/jira/browse/CLJ-2194)) but the timeline for this is unclear.

- In the light of this I'm considering focusing on examples first.  More details to come. 

### Some more minor things that happened:

- I printed [**stickers**](https://twitter.com/martinklepsch/status/1037802412680126464) which I'm planning to send to contributors. 
- Bozhidar likes favicons so I added one :) 
- Work is underway to integrate cljdoc into [Dash](https://kapeli.com/dash)
- Various fixes to the analyser code, mostly to eliminate slight differences between Clojure and ClojureScript as well as some dependency related improvements.
- I shipped a [**quick switcher (demo)**](https://giant.gfycat.com/GoodCluelessKusimanse.mp4) that allows you to switch between projects that you opened recently. I hope to expand this to quickly finding vars, namespaces and articles in the current project.


## Commits

git log --graph --pretty=format:'%Cblue%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%ci) %C(bold cyan)<%an>%Creset' --abbrev-commit --since="AUGUST 1 2018" --until "SEPTEMBER 15 2018"

* c899a6e - properly sort versions using version-clj (2018-09-11 22:51:12 +0200) <Martin Klepsch>
* d5dad83 - relativize-path: dont drop more than first item (2018-09-11 14:05:53 +0200) <Martin Klepsch>
* 125f4f6 - fix how paths are made relative for offline docs (2018-09-11 12:48:37 +0200) <Martin Klepsch>
* 2391ebb - log what revision analysis is done with (2018-09-10 08:03:09 +0200) <Martin Klepsch>
* 14b2b7a - Fix analysis for cider/cider-nrepl (2018-09-09 17:18:55 +0200) <Martin Klepsch>
* 42c01c3 - url-decode article slugs as UTF-8 strings (2018-09-09 17:14:48 +0200) <Martin Klepsch>
* 422f463 - ensure projects can specify newer versions of analysis deps (2018-09-08 21:45:23 +0200) <Martin Klepsch>
* cf25264 - dont bring up project switcher when typing k into search field (2018-09-08 18:45:34 +0200) <Martin Klepsch>
* 26ff286 - assert that doctree items have attribute map (2018-09-04 17:20:16 +0200) <Martin Klepsch>
* 99b2a42 - (origin/grimoire-cleanup) delete grimoire code now that migration is complete (2018-09-03 20:11:15 +0200) <Martin Klepsch>
* 1cba5b3 - delete outdated code to render docs to files (2018-09-03 20:11:00 +0200) <Martin Klepsch>
* e075b5b - fix opengraph image urls #33 (2018-09-03 19:43:04 +0200) <Martin Klepsch>
* 81f49c5 - fix #33: add proper meta tags; allow robots in (2018-09-03 19:38:20 +0200) <Martin Klepsch>
* 88ed8fa - fix #104: ensure revision is set in build logs for SNAPSHOT builds (2018-09-03 18:58:10 +0200) <Martin Klepsch>
* 4a2f0f4 - redirect to running build if one exists for same artifact (2018-08-29 19:24:37 +0200) <Martin Klepsch>
* c31b6f8 - switcher: reset selectedIndex to 0 when results change (2018-08-28 18:55:26 +0200) <Martin Klepsch>
* 43fc012 - update codox for arglists quoting fix (2018-08-28 18:23:17 +0200) <Martin Klepsch>
* 2e25cd5 - keep markdown headings black (2018-08-27 18:43:43 +0200) <Martin Klepsch>
* 4de9308 - 108: Fixed wikilinks from rendering inside code blocks (2018-08-27 17:39:16 +0200) <clyfe>
* d7a705c - fix issue with projects that dont have an SCM uri (2018-08-27 12:50:22 +0200) <Martin Klepsch>
* 7094e9e - formatting (2018-08-25 12:58:07 +0200) <Martin Klepsch>
* 1b90fb7 - codox sanitization is no longer necessary after adjustments to our fork (2018-08-25 12:57:54 +0200) <Martin Klepsch>
* c53d0ec - when importing docs, log project info (2018-08-25 12:57:21 +0200) <Martin Klepsch>
* b75f9e5 - less verbose warning when type varies (2018-08-25 12:56:53 +0200) <Martin Klepsch>
* 81f08fa - first raw version of docs switcher (2018-08-24 19:19:06 +0200) <Martin Klepsch>
* db9a2ce - dont create test-data dir (2018-08-22 14:03:26 +0200) <Martin Klepsch>
* e7e1219 - profile is set in code (2018-08-22 13:59:32 +0200) <Martin Klepsch>
* 04f9e2e - update ingest script to run migrations if necessary (2018-08-22 13:54:21 +0200) <Martin Klepsch>
* 55834aa - create test data directory (2018-08-22 11:52:56 +0200) <Martin Klepsch>
* 5996bea - use test profile in circleci (2018-08-22 10:56:32 +0200) <Martin Klepsch>
* 2f18646 - switch to sqlite (2018-08-21 12:47:02 +0200) <Martin Klepsch>
* 1e283c0 - only warn on macro-wierdness (2018-08-21 09:41:48 +0200) <Martin Klepsch>
* 71e53db - more useful exception in macro issue case (2018-08-18 21:05:10 +0200) <Martin Klepsch>
* 36da42c - minor logging tweaks (2018-08-18 20:33:54 +0200) <Martin Klepsch>
*   34f4803 - Merge branch 'new-storage-backend' (2018-08-18 14:21:20 +0200) <Martin Klepsch>
|\  
| * f3e64a2 - (origin/new-storage-backend) adr-0013: add section on 'why not X' (2018-08-18 13:31:46 +0200) <Martin Klepsch>
| * 23f0c3a - move storage type config into config.edn (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * 1dfc715 - better exception when ns insert violates constraints (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * f501e6c - update some analysis files for tests after analyzer updates (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * 50517d8 - properly sanitize codox data before ingest (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * 0ba27dd - more assertions when sanitizing codox data (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * 188a424 - introduce global storage type switch (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * 6774c08 - better exception if var can't be inserted (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * 517fe4a - diff cleanup (2018-08-18 13:31:45 +0200) <Martin Klepsch>
| * d29b751 - ADR-0013 sql benefits (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * caad7f3 - add ADR-0013 on new persistence layer (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * 3d77423 - fix some issues with grimoire (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * 8ae575e - rename grimoire-loader->data-loader, prep for sqlite swap (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * a42dead - add -main to sqlite import for easy starting from CLI (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * 9ae10e9 - properly pass db-spec when checking if version exists (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * 4f4cf01 - add note about misleading db filename (2018-08-18 13:31:44 +0200) <Martin Klepsch>
| * c51d38d - add (yet unused) sqlite storage impl (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * 9ca6ddb - set wal, normal journal mode for sqlite db (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * 3a90643 - use grimoire from fork so list-versions works with weird version numbers (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * 7dc427e - grimoire: no longer include platform info in bundle (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * 005836d - add helper fn that deduplicates macros from codox (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * b8bdf10 - add cljdoc.util/time for some basic time measuring (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * 93f463f - ignore .DS_Store files (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * 61aa4d1 - grimoire: little helper to return all version entities in grimoire store (2018-08-18 13:31:43 +0200) <Martin Klepsch>
| * d0f0a11 - sqlite: pass foreign_keys pragma (2018-08-18 13:31:42 +0200) <Martin Klepsch>
|/  
* 2346970 - Fix wikilink markup reference (#106) (2018-08-18 13:24:41 +0200) <Bozhidar Batsov>
* ea1f483 - add basic docs on fixing builds (2018-08-17 15:41:00 +0200) <Martin Klepsch>
* 1b00f31 - fix order how analysis deps are merged (2018-08-16 12:58:46 +0200) <Martin Klepsch>
* 717a8e6 - update codox version (2018-08-16 12:58:39 +0200) <Martin Klepsch>
* 47703b9 - ensure tools.namespace is loaded in recent version (2018-08-16 09:41:49 +0200) <Martin Klepsch>
* 0297c1b - Fix a typo (2018-08-15 16:13:59 +0200) <Bozhidar Batsov>
* 65f4ae9 - add docstring to unify-defs (2018-08-14 08:23:21 +0200) <Martin Klepsch>
* 4cc8012 - Revert "Merge branch 'jdhorwitz-feature/sort-vars-by-occurrence'" (2018-08-14 08:19:16 +0200) <Martin Klepsch>
*   bd81c22 - Merge branch 'jdhorwitz-feature/sort-vars-by-occurrence' (2018-08-14 07:49:43 +0200) <Martin Klepsch>
|\  
| * 990cb63 - slight adjustments to ordering note (2018-08-14 07:49:10 +0200) <Martin Klepsch>
| * 4484fbd - Added description of how vars are ordered (2018-08-14 07:44:14 +0200) <Joshua Horwitz>
| * 968651a - Switched to sort by line versus name (2018-08-14 07:44:00 +0200) <Joshua Horwitz>
|/  
* 3ba4442 - add a favicon; fix #94 (2018-08-14 07:42:07 +0200) <Martin Klepsch>
* 3366eb5 - allow clojure.parellel to silently fail (2018-08-13 20:32:16 +0200) <Martin Klepsch>
* 1dcf4b5 - ensure analyzed project dependency always has precedence (2018-08-13 20:31:50 +0200) <Martin Klepsch>
* 2ddb799 - delete hardcoded config for artemis, deriving works great (2018-08-13 15:08:14 +0200) <Martin Klepsch>
* 19ad8f3 - ensure proper quoting (2018-08-07 20:49:31 +0200) <Martin Klepsch>
* 6679775 - ensure timbre is loaded for tufte (2018-08-07 20:32:55 +0200) <Martin Klepsch>
* 634dad4 - (origin/dogstats) set up some storage/render related metrics (2018-08-07 17:36:37 +0200) <Martin Klepsch>
* 910b300 - stubs for dogstats (2018-08-07 17:32:28 +0200) <Martin Klepsch>
