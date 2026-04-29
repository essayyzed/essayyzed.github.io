---
title: "The AI Layoff Problem Is Not What You Think"
description: "Why the AI layoff wave is a coordination failure that hurts firms too, and why UBI, capital taxes, and retraining can't fix it."
tags: ["ai", "economics", "labor", "automation", "policy", "future-of-work"]
added: 2026-04-29
slug: ai-layoff-problem-is-not-what-you-think
---

In February 2026, Jack Dorsey told more than 4,000 Block employees their jobs had been eliminated. That's roughly 40% of the company's workforce, gone in one announcement. AI had made the roles unnecessary, he explained, with the calm of a man who'd done the math. Then he said something stranger than the layoff itself: "Within the next year, the majority of companies will reach the same conclusion."

He wasn't warning his peers. He was predicting them.

The popular framing of the AI layoff wave goes like this: automation is something *done to* workers by greedy CEOs and capital. The fix, in this framing, is to redistribute. Tax the profits. Fund a UBI. Retrain the displaced. Standard cleanup-after-the-spill stuff.

I want to argue this framing is wrong, and the policy responses that follow from it cannot work. The AI layoff wave isn't a story about workers losing and shareholders winning. It's a story about *everyone losing*, including the firms doing the laying off, because of a coordination failure none of them can stop on their own.

## The math the standard story misses

The standard story treats automation as a transfer from labor to capital. If that were true, the firms would at least be winning. They're not, and the reason is almost embarrassingly obvious: workers are also customers.

When Salesforce cut 4,000 customer-support roles in 2025, CEO Marc Benioff put it bluntly. "I've reduced it from 9,000 heads to about 5,000, because I need less heads." (One assumes the PR team workshopped that line for hours.) Those workers stopped spending. They cut grocery bills, canceled subscriptions, delayed car purchases. Some of that lost spending eventually circles back to Salesforce itself, when the firms it sells to face their own customer pullback and cancel software contracts. The loop closes. The only question is how many steps it takes.

So every layoff has two effects. A cost saving for the firm doing it. And a demand reduction spread across the entire economy. The firm captures the full saving. It eats only a tiny fraction of the demand reduction. The rest lands on every other firm.

That asymmetry is the entire story. The firm that automates is taking a small slice from itself and a much larger slice from its competitors. It does this rationally. So does every other firm. When they all do it at once, they collectively destroy the customer base they all depend on. Falk and Tsoukalas, in a recent theoretical paper, [*The AI Layoff Trap*](https://arxiv.org/abs/2603.20617), formalize this dynamic and prove that rational firms with full visibility into the cliff ahead will race toward it anyway. Here's why.

## Why visibility doesn't help

You might think: surely if the cliff is visible, firms will stop. CEOs aren't stupid. They read the same news the rest of us do.

They can. They do. They cannot stop.

Imagine ten firms in an industry. Nine sit down and agree: we'll all hold back on automation, keep workers employed, keep shared demand healthy. You're the tenth firm, listening politely.

If the others actually hold back, you can quietly automate, capture the cost savings, and sell into a healthy market. You win huge. If they break their word and automate too (which, let's be honest, they will), you'd better automate as well. Otherwise you're stuck paying full wages while demand collapses around you. You get crushed.

The math says automate in both scenarios. So does every CEO's math. The agreement collapses the moment it's tested. Not because anyone is greedy, but because *holding back is dominated by automating no matter what others do*. Economists call this a Prisoner's Dilemma, and it has a brutal property: communication doesn't fix it. Even if everyone agrees in writing that mutual restraint would make everyone richer, each individual firm still has to defect.

Which is why Dorsey's prediction reads less like a warning and more like an admission. He wasn't telling his peers what they *should* do. He was telling them what they *would* do.

## Competition makes it worse

Here's the result that should make economists uncomfortable. In most economic stories, competition is the discipline that keeps firms honest. More firms, lower prices, less monopoly rent. Boilerplate Econ 101.

In this story, competition makes the problem strictly worse.

When a firm lays off workers, the demand loss spreads across all firms in the industry. With two firms, each absorbs roughly half the damage. The firm thinking about automating sees a cost saving on one side and a hefty demand penalty on the other, and may well decide it isn't worth it. With a hundred firms, the damage spreads a hundred ways. Each absorbs only one percent. The cost saving is unchanged. The demand penalty is now negligible. Automating becomes a no-brainer.

A monopolist, by contrast, eats the full demand loss its layoffs cause and behaves like a wise central planner. Yes, you read that right. The conclusion is uncomfortable: in this specific dimension, *concentration is more responsible than competition*. This isn't an argument for monopolies. They remain bad on price, quality, and innovation. It's an argument that the discipline-by-competition story we've inherited from textbook economics doesn't apply here. The competitive pressure that's supposed to keep firms in line is the very thing pushing them off the cliff faster.

If competition is the source of the problem rather than its solution, the standard policy menu starts to look strange.

## Why the popular fixes don't fix it

Take each proposal in turn and ask the same question: *does this change the calculation a CEO makes when deciding whether to automate?*

**Capital taxes don't.** A 50% tax on profits scales every option by the same factor. If automating earned $130M and not automating earned $100M, after tax those become $65M and $50M. The ranking is identical. The firm still automates. The CEO doesn't even need to redo the spreadsheet.

**Voluntary coordination doesn't.** The Prisoner's Dilemma kills it on contact. We covered this.

**Worker equity barely does.** It narrows the gap, because workers' spending now recycles some capital gains back into demand. But it can't close the gap unless workers spend every dollar in the same sector that paid them. They don't. Workers buy groceries.

**Retraining helps, but lags.** Raising the income-replacement rate does shrink the externality. The problem is that retraining repairs damage after it's done. By the time the program graduates its first cohort, the next round of layoffs is already underway.

**Universal Basic Income** is the one most people put their hopes in, and it's the cleanest illustration of why the standard menu fails. UBI raises the floor on living standards for laid-off workers, which is genuinely valuable. But it does not change a single firm's automation decision. The cost saving from replacing a worker is unchanged. The demand loss the firm absorbs is unchanged. The CEO weighing the choice sees no shift in any input. UBI cushions the *consequences* of the externality without touching the *cause*. It's a very nice ambulance.

The only intervention that actually changes the firm's decision is a per-layoff tax, what economists call a Pigouvian tax, set equal to the demand loss pushed onto rivals. This works because it appears directly in the CEO's spreadsheet. Automating a worker no longer just saves money on wages. It now costs the tax too. The firm internalizes the harm it was previously externalizing. The math changes, so the choice changes.

This is not a more efficient form of redistribution. It is a different *kind* of intervention. The redistributive proposals try to fix the consequences. The Pigouvian tax tries to prevent some of the layoffs from happening in the first place. One of these things is not like the other.

## What you should and shouldn't believe

The skeptic's case is real, and the academic literature is already making it. A formal replication by Jeremy McEntire, posted in April 2026, verified all ten of the paper's mathematical propositions but argued that the alarming conclusions are conditional statements whose sign depends on parameter choices. Under the baseline assumptions, the model predicts catastrophe. Under equally defensible parameters (modestly higher reabsorption, some owner spending, differentiated products), the same model produces stability or even *under*-automation. The math is right. The calibration is contested.

The empirical record cuts the same way. Wharton's Ethan Mollick has noted that AI tools are too new for firms to plausibly justify 50% efficiency gains organizationally. An Oxford Economics report in January 2026 found that many "AI-driven" layoffs were actually delayed corrections to pandemic-era overhiring. (Translation: companies hired too many people in 2021, and "AI" makes a more flattering story for the earnings call than "we screwed up.") Yale's Budget Lab analyzed U.S. labor data from 2022 to 2025 and found that the share of workers across job categories hadn't shifted dramatically since ChatGPT's launch. Even Salesforce has walked back its position. Some of those 4,000 layoffs turned out to be redeployments. Senior leaders have admitted being "too confident" in AI's ability to handle complex customer service. And Benioff himself has now called AI-driven layoffs "a lazy way out," which is quite a turn from his "I need less heads" comment six months earlier. Pick a lane, Marc.

The walkback is itself revealing. Firms appear to be making consequential displacement decisions before they fully understand the consequences, which is exactly what the externality model would predict. But it also means the data is too early to settle the question. The strongest defensible position isn't "AI will definitely cause mass unemployment." It's something narrower: *the mechanism is real, the evidence we're already in it is mixed, and the asymmetry of risks favors acting before we have proof.* Acting too late means the displacement has already happened by the time the data is conclusive. Acting too early, with a mild correction, costs a little efficiency in exchange for buying time. The downside of being early is small. The downside of being late is enormous.

Dorsey was honest, in his way. He told us, on the record, that the firms in this game cannot stop themselves. Whether he's right that they'll all follow, or wrong because the real world's parameters are kinder than his model assumes, is the question the next two years will answer. The case for building the corrective tool, though, doesn't depend on knowing the answer. It depends on the cost of being wrong about it.

---

## Sources

**The paper this post is built around:**
- Falk, B. H. & Tsoukalas, G. (2026). [*The AI Layoff Trap*](https://arxiv.org/abs/2603.20617). arXiv:2603.20617. ([SSRN version](https://papers.ssrn.com/sol3/papers.cfm?abstract_id=6448898))

**The replication that pushes back:**
- McEntire, J. (2026). [*Three Headlines from One Equation: A Replication and Sensitivity Analysis of "The AI Layoff Trap"*](https://papers.ssrn.com/sol3/papers.cfm?abstract_id=6592220). SSRN.

**On the layoffs themselves:**
- CNN Business (Feb 2026). [Block lays off nearly half its staff because of AI](https://www.cnn.com/2026/02/26/business/block-layoffs-ai-jack-dorsey).
- CNBC (Sep 2025). [Salesforce CEO confirms 4,000 layoffs "because I need less heads" with AI](https://www.cnbc.com/2025/09/02/salesforce-ceo-confirms-4000-layoffs-because-i-need-less-heads-with-ai.html).
- CNBC (Dec 2025). [AI was behind over 50,000 layoffs in 2025](https://www.cnbc.com/2025/12/21/ai-job-cuts-amazon-microsoft-and-more-cite-ai-for-2025-layoffs.html).

**On the empirical pushback (AI-washing critique):**
- SF Standard (Feb 2026). [AI made him do it: Jack Dorsey lays off 40% of Block staff](https://sfstandard.com/2026/02/26/block-lays-off-staff/), includes Ethan Mollick's commentary.
- Yale Budget Lab (Oct 2025), analysis of U.S. labor market data finding limited displacement since ChatGPT.
- Oxford Economics (Jan 2026), report finding many "AI-driven" layoffs reflect pandemic-era overhiring.

**Related theoretical work referenced:**
- Acemoglu, D. & Restrepo, P. (2018). [The Race Between Man and Machine](https://www.aeaweb.org/articles?id=10.1257/aer.20160696). *American Economic Review*.
- Eloundou, T., Manning, S., Mishkin, P. & Rock, D. (2024). [GPTs are GPTs: Labor market impact potential of LLMs](https://www.science.org/doi/10.1126/science.adj0998). *Science*.
- Guerreiro, J., Rebelo, S. & Teles, P. (2022). Should robots be taxed? *Review of Economic Studies*.
