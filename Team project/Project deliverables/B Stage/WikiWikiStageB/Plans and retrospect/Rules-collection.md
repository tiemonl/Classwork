# Stage A/B Rules collection.

* Team: WikiWiki
* Members: Ryan Guard, Elizabeth Gieske, Ronnie Hoover, Liam Tiemon, Christopher Groppe 
* Date: 04/19/2018

### Rules

1. Rumsfeld law: In our implementations we had to redefine our scope several times due to the nature of finding holes in our initial design, not being familiar with python libraries for interfacing with postgresql databases, learning how to utilize flask, learning to do UI with Jinja2, etc. So we didn't know what we didn't know and adapted as we learned.
2. KISS principle: While narrowing down our implmentation strategies and designs we focused on making systems as simple to not add unnecessary feature creap or complexity. One example of this was we started with a 2 table system for storing pages (1 for the overarching page reference and 1 for the page commits) but later we realized the page table served little function so instead we used a composite primary key with one table that allowed for us to only have to interact with a singular table for our implementation of the storage feature.
3. No suprises rule: We mostly made it a rule to keep everyone clearly informed about scheduling conflicts, and kept in close contact through the chat service called "Discord". That way we all knew what to expect from each member.
4. Law of demeter: 