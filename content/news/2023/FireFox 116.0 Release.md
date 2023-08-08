---
title: "FireFox 116.0 Release"
date: 2023-08-08T08:30:00+08:00
summary: Firefox 116.0 released with custom formatters support
author: Sebastian Zartner
draft: True

---  
<br>

## Custom Formatters for Firefox  


It's been quite a while since my last post regarding custom formatters in Firefox. Though the long wait is finally over. Firefox 116.0 ships with custom formatters support!  

In addition to the overview below, you can review the [Firefox 116 release notes]( https://www.mozilla.org/en-US/firefox/116.0/releasenotes/) and the [tweets]( https://twitter.com/FirefoxDevTools/status/1686383218143240192?t=YU3ARV88wolWF9cwBVLrmQ&s=09) and [blog]( https://fxdx.dev/firefox-devtools-newsletter-116/) by the Firefox DevTools team.
For more background and the initial decision to fund this important project, check out Clojurists Together’s [post](https://clojars.slack.com/archives/D04EH0UMJAE/p1691149708318019) from July 2022.

**So let's get to it!!**  

The main point of bringing custom formatters to Firefox was to allow the [CLJS DevTools](https://github.com/binaryage/cljs-devtools) (custom formatters for ClosureScript) to work in Firefox.
To get to that point required a lot of work. First, I needed to find a way how to [parse the custom formatters](https://bugzilla.mozilla.org/show_bug.cgi?id=1734840) defined in a website in a safe way. To allow executing the code in a safe context, I've re-used a functionality that was already used by the Web Console to support [instant evaluation](https://firefox-source-docs.mozilla.org/devtools-user/web_console/the_command_line_interpreter/index.html#instant-evaluation). Though I had to [lift some of the restrictions imposed by that functionality](https://bugzilla.mozilla.org/show_bug.cgi?id=1801040). 

After that, I switched the logic behind their display from the normal tree view used in o ther places within the Firefox DevTools [to a custom component](https://bugzilla.mozilla.org/show_bug.cgi?id=1801045). This simplified the logic behind them, allows to align the display to Chrome's implementation and paved the way to [handle the config object](https://bugzilla.mozilla.org/show_bug.cgi?id=1764443).  

Also, once the basic functionality of the feature was there, I [added a way to gracefully handle errors within formatters](https://bugzilla.mozilla.org/show_bug.cgi?id=1764439) by logging them to the console.  

Initially, my focus lied on the display within the [Web Console](https://firefox-source-docs.mozilla.org/devtools-user/web_console/ui_tour/index.html). Though the Web Console isn't the only part where custom formatters are supported. I've also [added support for them within the tooltips shown when hovering variables in the Debugger](https://bugzilla.mozilla.org/show_bug.cgi?id=1820333) while script execution is stopped. Thanks to the DevTools team, there's also [support for them within the Scopes](https://bugzilla.mozilla.org/show_bug.cgi?id=1828511) and [Watch Expressions panel](https://bugzilla.mozilla.org/show_bug.cgi?id=1828509	). All those changes included creating automated test cases for this new feature.  

Once those steps were finished, I could finally [remove the experimental flag](https://bugzilla.mozilla.org/show_bug.cgi?id=1752760), behind which this feature was implemented.
And last but not least, I've also [added some documentation of the feature](https://bugzilla.mozilla.org/show_bug.cgi?id=1773035).

This rounded up the implementation regarding the Firefox side. Though, as initially noted, the main goal was to get the CLJS DevTools to work with Firefox. And that also required some work on their side. So, with the help of Antonin Hildebrand I've [added support for Firefox there](https://github.com/binaryage/cljs-devtools/pull/73), as well, and [had to further tweak the display](https://github.com/binaryage/cljs-devtools/pull/74) later on. And I also edited the documentation there to include the support in Firefox.
Antonin then also [released a new version 1.7](https://github.com/binaryage/cljs-devtools/releases/tag/v1.0.7) of those tools after that. So if you want to have Firefox support using the CLJS DevTools, please make sure to download the latest version of them.

## Give the feature a try!

You can enable custom formatters from within the Settings panel by checking the "Enable custom formatters" option.
Once that's done, custom formatters are available the next time you open the Firefox DevTools.

Download the latest version of the CLJS DevTools and integrate them into your project.

With that, ClosureScript variables are displayed in a much more readable way in the DevTools. Here's an example that covers pretty much everything they do:
![image](https://github.com/clojurists-together/clojuriststogether.org/assets/14980147/05773116-4df6-4c96-9afa-d00b1fc2e1f2)

 
All this is explained in more detail in the post at the [Firefox Developer Experience blog](https://fxdx.dev/firefox-devtools-custom-object-formatters/)
and in the [Firefox DevTools documentation](https://firefox-source-docs.mozilla.org/devtools-user/custom_formatters/index.html).

## Conclusion

While the project took way longer than expected, I am happy that the feature finally made it into Firefox and people can profit from it.

And that wouldn't have been possible without the help of Nicolas Chevobbe from the Firefox DevTools team and the creator of the CLJS DevTools, Antonin Hildebrand. Also Jan 'Honza' Odvarko from the Firefox DevTools team helped coordinate the implementation and tracked the progress. So, a big thank you! Also, thanks to Daniel who reached out to me for that project and for supporting me!

I've learned a lot during this project. And I hope you find this feature helpful.
