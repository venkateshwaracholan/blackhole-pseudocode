1. customer obsession
2. ownership
3. invent and simplify
4. are right, a lot
5. learn and be curious
6. Hire and develop the best
7. insist on the highest standards
8. Think Big
9. Bias for action
10. frugality
11. earn trust
12. dive deep
13. have backbone, disagree and commit
14. deliver results



1. customer obsession

1. When you’re working with a large number of customers, it’s tricky to deliver excellent service to them all. How do you go about prioritizing your customers’ needs?

A: In freshcaller, the customers are categorized into plans, estate, garden, blossom ,sprout and the number of agent licensing, phone credits and call volumes they use that directly correlates their importance to the business. so, we depend on the data for prioritizing and sometimes priority is based on th potential business opportunity. And as a product features are prioritized based on the priority customer's requirement and also after careful evaluation on the road map of the product as well.

2. Give me an example of a time when you did not meet a client’s expectation. What happened, and how did you attempt to rectify the situation?

I was working on a feature to show agent's status report, and it had existing data migration for approx 2 billion records from one format to another and i did not meet the client's expectation in term of the timelines because the nature of the work demanded time. I made sure that the product will not promise unrealistic timelines to customers moving forward.

3. Who was your most difficult customer?

A customer who does not understand the nature of business environments, the process the product has to follow to ensure a trust worthy service to most of its customers. Violating the process for urgency may lead to further catastrophe.

4.Tell the story of the last time you had to apologize to someone.

When i was working on an agents status report migration, I made an incorrect assumption without clarifying with one of the down stream services about a new field addition and there fore the pushed data was not stored in the down stream service. I apologized for the incorrect assumption, republished the data from our database through scripts, and made sure that i verify all of my assumption from that point in time when i realized assumptions are dangerous until confirmation.

5. Can you tell me about a time you obsessed over giving very high quality service to a customer?
I was working on a feature to provide custom status for agents. That had transformation of data from one table to another. And the front end code was not capable of handling the change in the data model and the impact would be after frontend deployment, customers who don't refresh will face issues. so to avoid this, I suggested a pre release frontend code that supports the change and avoid such bad experiences which took us a little more effort.

2. Ownership

1.Tell me about a time when you had to make a difficult short term decision to make long term gains.

the product uses a gem called multitenant which contains common code like account and user related code, common for most of the products and freshcaller had unique user id in users table instead of a normalized table authorization  in the multitenant gem. freshcaller overridden a lot of common code to support uuid being in users table. i had to migrate that to authorization table in a short deadline so that common code form multiteant can be used in long term without overriding.

2.Tell me about a time when you had to work on a project with unclear responsibilities.

Freshid V2 migration, requirement was unclear and had to figure out things myself talking to several teams and took roles of PMs, leads and architect.

3. Tell me about a time when you had to leave a task unfinished.
I had to leave a GDPR redis migration unfinished so that i can work on more pressing tasks, later the task was delegated the task to someone. I posted my analysis and work done so far my attaching docs and PR links in the fresh release ticket.

4.Tell me about a time when you took on a task that went beyond your normal responsibilities.

Freshid V2 migration, i provided organization level migration strategy which avoided a lot of manual work and was approved by senior architects in platforms teams.


5.Tell me about a time when you took it upon yourself to work on a challenging initiative.

I attempted and successfully completed test case parallelization in jenkins



Invent and Simplify

1. Tell me about a time when you failed to simplify a process and what you would have done differently.


2. Tell me about a time when you innovated on something and it went wrong.


3. Tell me about a time when you changed a process at work through either an innovative new way or simplification.
When in on call L2, everytime i solve a ticket, i had to track that on an spread sheet whic was tedious and error prone, I suggested that we do this though a ticketing system which was an in house product already, freshdesk

4. Tell me about a time when you invented something.

I invented migration org migration strategy - chained migration
I invented migration though double updated and flagged reads.
i invented logs can be used to republish lost data to external services even if we dont have the data in our database.

5. Tell me about a time when you gave a simple solution to a complex problem.
chained migration was a simple solution to a complex problem
logs can be efficiently be used for a lot of things
rspec parallelization



4. Are Right, A Lot

1. Tell me about a time when you had to work with incomplete data or information.
freshid v2 org

2. Tell me about a time when you were wrong
agent report assumption mistake
violating process for urgency, when i fixed a bug in integration which in turn caused another issue, but fortunately impact was same.
committing to tighter deadlines, agents report migration


3. Tell me about a time when you had to use your judgment to solve a problem.

4. Tell me about a time when you incorporated a diverse set of perspectives into solving a problem.

5. Tell me about a time when you had your beliefs challenged and how you responded.


5. Learn and Be Curious

1. Tell me about a time when you solved a problem through just superior knowledge or observation.

2. Tell me about a time when you influenced a change by only asking questions.

3. Tell me about an experience you went through that changed your way of thinking.

4. Tell me about a time when your curiosity helped you make a smarter decision.

5. Tell me about the most important lesson you learned in the past year.
assumptions are dangerous, confirmation through proper data is must.
initiatives matter a lot.


6. Hire and Develop the Best

1. Tell me about a time when you made a wrong hire. When did you figure it out and what did you do?

2. Tell me about a time when you mentored someone.

3. Tell me about the best hire your ever made.

4. What qualities do you look for most when hiring others?
ability to learn - intelligence
willingness to learn - initiatives
open mined enough for philosophical redefinitions

5. Who is the most important person in your life and why?
cannot pin point to one person, there are a lot of people whom whom i learnt from and even if they have never existed in life i would have found alternatives.
few of them are - cs teacher in school, internet community, leaders, peers in work place 


7. Insist on the Highest Standards

1. Tell me about a time when a team member didn’t meet your expectations on a project.


2. Tell me about a time when you couldn’t meet your own expectations on a project.

3. Tell me about a time when you raised the bar.

4. Tell me about a time when you motivated a team to go above and beyond.
i was leading the feature custom status, and the whole team and myself felt so motivated to stretch work on it, as i have made few thing clear.
we will stretch for this work,  but the next work item should not be stretching aswell
this extra efforts put in are rewarding as well
i stated the problem and solution approached clearly after discussing along with senior architects
so it was great to see things working in action as a team.
we also had a fun vigourous testing before giving it to qa, which actually not only proved to be fun but helped us find a lot of bugs before hand

5. Tell me about a time when you were dissatisfied with the quality of something at work and went out of your way to improve it.


8. Think Big

1. Tell me about a time when you went way beyond the scope of the project and delivered.
freshid v2 org migration

2. Tell me about your proudest professional achievement.
won in an freshworks internal appathon in 2 days of coding an application.
built multiplayer game from scratch

3. Tell me about a time when you were disappointed because you didn’t think big enough.
i have always thought big abt life.

4. Tell me about a time when your vision resulted in a big impact.
in terms of effors chained migration had an organization wide impact

5. Tell me about a time when you had to make a bold and difficult decision.
uuid authorization migration, was bold and it would have affected the delivery time if went wrong, but that was something for a long term gain.


9. Bias for Action

1. Tell me about a time when you took a calculated risk.

2. Tell me about a time you needed to get information from someone who wasn’t very responsive. What did you do?

i first try to find alternatives ways of fetching the required data along with trying with the unresponsive person.
if both are not successful, i escalate it with the reporting manager, indicating that the data requirement is a a blocker for my task to proceed. making assumptions on data is dangerous.


3.Describe a time when you saw some problem and took the initiative to correct it rather than waiting for someone else to do it.

publish call to integ issue fix.
data pushed to external services stopped due to a bug in someone's code changes. i had to immediately fix it and push it asap with minimal testing(as its already broken) and incorporate further testing on the way.
the longer we delay the greater the data that is needed to be pushed through scripts.


4. Tell me about a time when you had to make a decision with little data or information.
freshid org migration
agent reports assumption


5. Tell me about a time when you made a decision too quickly and what you would have done differently.
agent reports decision based on assumptions


10. Frugality

1. Tell me about a time when you had to work with limited time or resources.
custom status feature

2. Tell me about a time you had to rely on yourself to finish a task.

for all bug fixes and individual features i rely on myself, which is most of the time.

3. Tell me about a time where you turned down more resources to complete an assignment.

That is something that i do on a daily basis.
i initiate a lot when i find something wrong, atleast modularize and delegate it to someone  based on priority, 
or defer if its okay. I have been a point of contact for several external services and integrations.


4. Tell me about a time when you beat out the competition with less resources.
work is not a competition, its collaboration.
Appathon is a competition, and i won a special jury award.

5. Tell me about a time when you had to be frugal.
when i was committed on a stricter timeline i had to be frugal.
build minimum viable product, that meets the standards and optimize later.
custom status feature.


11. Earn Trust

1. Tell me about a time when you had to tell someone a harsh truth.

i do this all the time respectfully, to my managers, directors, PMs, tech support.
and sometimes, my intent is correct but people dont take it easily, working on that.
we are on a business and truth and trust are at most priority which affects several decisions.
leaders in my org are great enough to take criticism without ego.
there are several incidents where senior architect corrected me and i corrected the senior architect as well. 
at the end of the day better solution, in terms of, efficiency, effort, deadline and priority gets chosen.
example:

2. What would you do if you found out that your closest friend at work was stealing?

warn that person this is going to affect them in the long term once, to give a last chance to realize.
if the last chance is breached escalate to reporting manager.

3. What is the quality you value least about yourself?
multitasking, i am efficent doing one thing at a time. so context switch affect me.
just like other humans, i cant remember a lot of things.
i document them, with a lot of details so that i can get back on them where i left them.

i am too open minded and flexible enough in terms of adapting to a change, so nothing seem to affect me much.

i get bored if i work on repeated stuff,  i try to trail people who like to work on routine tasks and delegate theat to them instead.

ambiguity an assumption is a problem, i use data to arrive at a decision.


4. What do you do to gain the trust of your teammates?

i just tell the truth always,even if it is negative, i do it respectfully, so people naturally trust me.
i spend time on improving their lives, gains me additional trust.
i help them whenever they need something, or respectfully say no,  if i am busy with other tasks.
i empathize - get into others perscepctive to see how they would think and act accordingly

5. Tell me about a time you had to speak up in a difficult environment.
if things aren't going great i speak up with instant initiatives.
procrastination is not going to help.
in freshid org v2 none of the requirements were clear, and leads and pms were taking responsibility.
i spoke up to the manager abt the situation and took external communictaions though team channels and confirmed if my actions are right or wrong. i ask a lot of feedback and check with reporting manager on my progress and review.
feedbacks with examples help me improve if my actions arent aligning with the right expectations. 

12. Dive Deep

1. Tell me about something that you learnt recently in your role.
i learn a lot abt managing people which i though would be difficult and now i am able to efficiently manage them.
technical expertise in terms of application architecture, depth of code understanding.
i learnt to define thing correctly with data to back it up.

2. Give me two examples of when you did more than what was required in any job experience.
i architect solutions - chained migrations
i lead a team and took responsibility - custom status


3. Tell me about the most complex problem you’ve ever worked on.
data migration is the complex problem i have worked in
custom status, agent report
concurrency problems area little hard to visualize in the begenning, once i realized they are actually nor concurrent, they are just as sequential, its was better, but reproducing them is a little hard.

4. Tell me about a time when understanding the details of a situation helped you arrive to a solution.
freshid accounts stuck on 2 orgs, understanding gave me solutions
response time kept increasing
, research issue.
parallelize spec,  data isolation, helped me arrive at solution based on understanding
crm workflow migration was easier beco i had experience.


5. Tell me about a time you utilized in depth data to come across a solution.
before executing any migration, i run queries to find out the volume of the data need to be migrated,
estimated time taken, affected accounts, migration fallback strategy. etc, everything based on the actual data on prod.
test the mirations on staging environments and then only proceed.

even while arriving at the chained migration, the initial idea was to use excel sheets to track chain accounts and handle them separately, and when i looked at the data, such accounts were significant and a lot of coordination had to happen between teams, so needed to be automated. 



13. Have Backbone; Disagree and Commit

1. Tell me about a time when you had to step up and disagree with a team member’s approach.
freshid org v2
uuid authorization


2. If your direct manager was instructing you to do something you disagreed with, how would you handle it?
i would ask carifying questions on why we are doing it this way and understand that. that would boil down to
priority, efficiency, deadline, scalability, reusability, extensibility. if two equal approaches, with all constraints pre calculated occurs, they both are fine, i woont have problem choosing direct manager's approach as well and it makes sense. atlast we are collaborating for the same cause.

if it never makes sense, i disagree and commit, but, me agreeing t something and committing is always going to better and efficient  than disagree and commit. so its the responsibility of the manager to convince me with actual data or something that makes better sense.

3. Tell me about a time when you did not accept the status quo.

i simple disagree until it makes sense.
i believe everyone should have an individual experience, so differences in perspectives are encouraged and that can always be challenged to unify. i believe in what i understand.

4. Tell me about an unpopular decision of yours.
assumption without confirmation.
saying yes to everything which i am not doing now, everyone including me can be wrong.
we have to make sure we remember our mistakes to not commit it again.

5. What do you believe that no one else does?
i beleive truth and words are more powerful to get something done.
we dont need false promises.
initiatives matters a lot, even if its fail, fail, fast learn quick.
calculate your risks based on real data and not assumptions.
remember your mistakes and document them carefully
humans tend to forget, do document every action to revisit.
work should be measurable,modularizable so that delegation and rating are quantifiable and not based on perspectives.



14. Deliver Results

1. Give me an example of a time when you were 75% of the way through a project, and you had to pivot strategy–how were you able to make that into a success story?

custom status feature
splitting into several releases, to release feature first and then work on tech debt, decision
mvp to efficient model.


2. By providing an example, tell me when you have had to handle a variety of assignments. Describe the results.




3. What is the most difficult situation you have ever faced in your life? How did you handle it?
i went to canada for higher studies and decided to come back.
choices are a little hard, but careful evaluation with data will surely help. a lot of things arent easy to rollback. there is a time and cost involved both for business and life.

4. Tell me about a time you had too much on your plate to deal with and how you handled getting everything done.

on call tickets getting above 50, is too much on my plate, 
and i immediately escalate to as for more resource to handle tickets, 
request assistance to fix and deploy urgent issues causing too much incoming tickets
educate L1 support on repeated talsk that can be fixed at L1 end.



5. Tell me about a time when everyone else on your team gave up on something but you pushed the team towards delivering a result.

freshid org v2
agent report migration
migration integ bug


Situation:
task:
action:
result:



1. lack of data
2. possesiveness or ego
3. not questioning 
4. not expressing disagreement
5. commiting to unrealistic deadlines
6. expecting success in something we arent experienced in.
7. lack of confidence
8. trouble asking for help.
9. difficult time working with certain people.
10. work life balance.
11. ambiguity and assumptions
12. not speaking up.
13. disagreement doesnt mean we should not commit to it.
14. not taking ownership or responsibility
15. 




