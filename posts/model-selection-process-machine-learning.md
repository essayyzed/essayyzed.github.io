---
title: "Picking the Right ML Model Isn't Magic. It's a Process."
description: "A practical guide to model selection in machine learning—why train/validation/test splits matter and how to avoid picking a 'lucky' model."
tags: ["machine-learning", "model-selection", "data-science", "ml-engineering", "statistics"]
added: 2026-04-09
slug: model-selection-process-machine-learning
---

You trained a model with 80% accuracy. You shipped it. A week later, users are complaining. Spam is leaking into inboxes.

What went wrong?

Probably not the algorithm. Probably not the data pipeline. The most likely culprit is something quieter and more dangerous: **you picked the wrong model without knowing it.**

In machine learning, picking the "best" model is less about who wins once—and more about who keeps winning when the data changes. A model can look great on paper and still fail in production.

The fix is simple in theory and surprisingly easy to get wrong in practice: **you need a proper model selection process.**

## What you're actually trying to simulate

Let's ground this in a real scenario.

You train a spam detection model using data up to July. In August, new emails arrive. Your model has never seen them—but it still needs to classify them correctly.

That's the real test.

So when evaluating a model, your goal is not "how well does it fit the data?" but:

**How well will it perform on data it hasn't seen yet?**

You can't time-travel to August. But you can simulate it.

## The basic idea: hide some data

Start with your full dataset. Then split it:

- 80% → training  
- 20% → validation  

Train your model only on the training data. Then evaluate it on the validation data—the part you *pretended doesn't exist*.

This gives you a rough idea of how your model performs on unseen data.

Here's what that looks like in practice:

- Train model `g` using `X_train`, `y_train`  
- Apply it to `X_val`  
- Compare predictions (`ŷ`) with actual labels (`y_val`)  

If your model gets 4 out of 6 predictions correct, that's about 66% accuracy. Do this for multiple models, and you can compare them.

Simple enough.

But there's a trap hiding here.

## The "lucky model" problem

Imagine this: instead of a real model, you use a coin flip.

- Heads → spam  
- Tails → not spam  

Now try multiple coins.

One coin performs terribly. Another does slightly better. And one—by pure chance—gets everything right. 100% accuracy.

You wouldn't trust that coin.

But the exact same thing can happen with real models.

When you test many models on the same validation set, one of them can **get lucky**. Not because it's better—but because randomness lined up in its favor.

This is known as a **multiple comparison problem** in statistics.

And if you're not careful, you'll pick the "winner"… which is actually just the luckiest model.

So how do you protect yourself?

## The fix: introduce a test set

Instead of splitting your data into two parts, split it into three:

- 60% → training  
- 20% → validation  
- 20% → test  

![60/20/20 Dataset Split Diagram](/train_val_test_split.png)

Now the process becomes more reliable:

1. Train models using the training set  
2. Compare them using the validation set  
3. Pick the best one  
4. **Only then** evaluate it on the test set  

The test set is your final checkpoint. You don't touch it during model selection. You keep it hidden until the very end.

If your model performs well on both validation and test data, it's probably genuinely good—not just lucky.

This is also exactly how models overfit—not just to training data, but to your validation process itself. Every time you evaluate a model on the validation set and tweak based on the result, you're leaking information. The test set exists precisely to catch this.

## What model selection actually looks like

Let's say you try four models:

- Logistic Regression → 66%  
- Decision Tree → 60%  
- Random Forest → 67%  
- Neural Network → 80%  

Based on validation performance, the neural network wins.

![Model Comparison Table](/model_comparison_table.png)

Now comes the critical step: test it.

You evaluate it on the test dataset and get 78%.

That's close to 80%. Good sign.

If instead it dropped to 55%, that would mean the model overfit the validation data or got lucky. You'd need to go back and rethink your approach.

This final check is what separates a promising model from a production-ready one.

## The subtle inefficiency (and how to fix it)

There's one more detail most people miss.

During model selection, you only train on 60% of your data. The validation set (20%) is used only for comparison—not training.

That means you're leaving useful data on the table.

Once you've selected the best model, you can do better:

- Combine **training + validation data (80%)**  
- Retrain your model on this larger dataset  
- Then evaluate once on the test set  

This gives your final model more data to learn from—without compromising the integrity of your evaluation.

It's a small tweak. But it often improves performance in real systems.

## The full process, stripped down

If you zoom out, model selection is just six steps:

1. Split data into train / validation / test  
2. Train a model on training data  
3. Evaluate it on validation data  
4. Repeat for multiple models  
5. Select the best model  
6. Test it once on the test dataset  

Notice what's missing here: no fancy math, no deep learning tricks, no hyperparameter wizardry.

Just a process—and the discipline to follow it.

That's the part most people skip. Not because they don't know it, but because it feels boring compared to trying a new architecture or tuning parameters. But skipping it is exactly how you end up shipping a model that looked great in your notebook and quietly fails in production.

## Why this matters more than the model itself

Most beginners obsess over which algorithm to use—logistic regression, trees, neural networks.

But in practice, **how you evaluate models matters more than which model you choose**.

A simple model, evaluated correctly, will outperform a complex model chosen poorly.

The biggest mistake in machine learning isn't choosing the wrong model.

**It's trusting a result you didn't properly test.**
