---
title: "Annually-Funded Developers' Update: Jan./Feb. 2025"
date: 2025-03-08T14:00:00+12:00
author: Kathy Davis
summary: "Dragan Duric, Eric Dallo, Michiel Borkent, Peter Taoussanis, Oleksandr Yakushev"  
draft: True


---

Hello Fellow Clojurists!
This is the first report from the 5 developers receivng Annual Funding on 2025. 

Dragan Duric  
Eric Dallo  
Michiel Borkent  
Peter Taoussanic  
Oleksandr Yakushev  


## Dragan Duric  
2025 Annual Funing Report 1. Published February 27, 2025.

My goal with this funding in 2025 is to support Apple silicon (M cpus) in Neanderthal
(and other Uncomplicate libraries where that makes sense and where it's possible).  

In January and February, I released the first version of Neanderhal that can run on Mac/Apple M!  

In more detail:  

Here's what I've proposed when applying for the CT grant.  

I propose to * Implement an Apple M engine for Neanderthal.* This involves:  
- buying an Apple M2/3 Mac (the cheapest M3 in Serbia is almost 3000 USD (with VAT).  
- learning enough macOS tools (Xcode was terrible back in the days) to be able to do anything.  
- exploring JavaCPP support for ARM and macOS.  
- exploring relevant libraries (OpenBLAS may even work through JavaCPP).  
- exploring Apple Accelerate.  
- learning enough JavaCPP tooling to be able to see whether it is realistic that I build Accelerate wrapper (and if I can't, at least to know how much I don't know).  
- I forgot even little C/C++ that I did know back in the day. This may also give me some headaches, as I'll have to quickly pick up whatever is needed.  
- writing articles about relevant topics so Clojurians can pick this functionality as it arrives.  

Projects directly involved:  
https://github.com/uncomplicate/neanderthal  
https://github.com/uncomplicate/deep-diamond  
https://github.com/uncomplicate/clojure-cpp  

First, I set to the task of tidying up the existing versions of Uncomplicate libraries (Neanderthal, Deep Diamond, etc.) to bring them up with the latest versions of native libraries, cuda, etc., and to fix some outstanding issues/bugs that might complicate work on Apple M support. After that, it was time for the main task, the beginnings of Apple M support.  

The plan was to buy an Apple M2/M3, but in the meantime the nice Clojurians from Prague donated a used (but fantastically beefed up) MacBook Pro Max M1, so this was covered quickly!  

I explored OpenBLAS as the first choice (the other is Apple Accelerate), as it can also work on Linux and Windows, and could be immediately beneficial to all users and easier to start with (I didn't need to switch to Apple yet).  
I implemented the OpenBLAS engine for the part of functionality that was supported by JavaCPP's openblas preset.  
A lot of critical functionality was not present there (although some of it was there in the openblas itself),
so I jumped at the opportunity to learn some JavaCPP preset building, and improved JavaCPP's OpenBLAS.
After a bit of experimentation and lot of waiting on the compiler and github tools, this is now contributed upstream.  

Next, I returned to the pleasant part of work - programming in Clojure - and completed the first Neanderthal
engine that runs in Apple M Macs. This covers the core and linalg namespace, because this is what OpenBLAS covers. The rest of Neanderthal functionality is waiting for me to explore Apple Accelerate, and to create engine based on that.  

I managed to release Neanderthal 0.53.2, which enables Clojurians to use this on their Macs, just in time for this report.  

I hope they'll have immediate benefits, and have fun doing some high performance hacking on their Macs.  

Many thanks for CT for sponsoring this work!  <br>

---

## Eric Dallo  
2025 Annual Funing Report 1. Published February 27, 2025.

