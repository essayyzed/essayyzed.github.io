---
title: 'Stop Vibe Coding. Start Spec‑Driven Development.'
slug: stop-vibe-coding-sdd
description: "SDD makes AI-assisted dev predictable: define behavior first, then implement. Quilora shows what 'good inputs' look like in-repo. OpenSpec is one implementation."
tags:
  - programming
  - AI
  - software-engineering
  - productivity
added: 'Dec 29 2025'
---

If your "requirements" live in a Slack thread, two Jira comments, and whatever you typed into an LLM at 1:12 AM…

…you're not shipping software.

You're shipping **interpretations of vibes**.

And then you act surprised when the model "misunderstands" you and rewrites half your codebase because you forgot one tiny constraint like "don't break auth" or "don't add dependencies" or "don't refactor the whole backend because you got inspired."

## The Problem Isn't AI. It's Undefined Behavior.

LLMs don't optimize for correctness. They optimize for plausibility.

So when your inputs are vague, the model fills the gaps with:

- "best practices"
- invented constraints
- refactors you didn't request
- "helpful improvements" that silently change behavior

In other words: you didn't specify reality, so it guessed.

**Spec‑Driven Development (SDD)** is how you stop paying for guessing.

## The Spec‑Driven People

These are the devs who learned (often the hard way) that "just start coding" is fine until it's not.

They write down:

- what the system should do
- what it should not do
- what "done" means
- what risks exist
- what constraints are non‑negotiable

Then they implement.

When AI enters the workflow, these devs don't magically become faster. They become safer. Because the AI is implementing a contract instead of improvising.

## Spec‑Driven Development (SDD), in One Sentence

SDD is a workflow where the **spec is the source of truth**, and code changes are implementations of spec changes.

Not waterfall.
Not a 40‑page PDF.
Not "process theater."

Just: define behavior first, then implement.

## "Okay, cool. Show me receipts."

Here's what SDD looks like when it's actually in a repo.

I'm going to use my project **Quilora** as an example (a production-ready RAG app). The important part is not "RAG." It's the _shape of the artifacts_.

### 1) Project Context (a.k.a. the repo's constitution)

If you don't tell the AI your rules, it will bring its own.

Quilora has a [project_context_doc][project-context] that's concrete. Example excerpts:

```markdown
## Purpose

**Quilora** ("Ask your documents anything") is a production-ready
Retrieval-Augmented Generation (RAG) application...

## Tech Stack

### Backend

- **Python 3.11+**
- **Haystack 2.x**
- **FastAPI**
- **aisuite**: Unified LLM interface for provider flexibility
- **Qdrant**: Vector database...

## Project Conventions

### Code Style

- **Python**: Follow PEP 8, use Black formatter (line length: 100)
- **Type hints**: Required for all function signatures

### Testing Strategy

- **Coverage Target**: Minimum 80% code coverage

## Important Constraints

- **LLM Provider Flexibility**: Must support switching between
  providers ... without code changes
- **Cost Management**: Implement token tracking and rate limiting
- **Response Time**: Target <3s for retrieval, streaming for generation
```

That's not "documentation." That's **guardrails**.

This single file prevents the classic AI failure mode:

> "I didn't know your stack, so I rewrote it into my favorite architecture."

### 2) Proposal (scope, goals, non-goals, and success criteria)

A spec-driven change starts by defining _intent_ before implementation.

Quilora's [proposal_doc][proposal] includes goals, non-goals, and measurable criteria. Example excerpts:

```markdown
## Goals

1. **Document Management**: Upload and index documents (PDF, TXT, MD, DOCX)
   into Qdrant...
2. **Semantic Search**: Retrieve relevant document chunks...
3. **Answer Generation**: Use aisuite to generate responses...
4. **REST API**: FastAPI endpoints with streaming support...
5. **Frontend UI**: Vue 3 interface...

## Non-Goals

- Multi-user authentication (can be added later)
- Advanced document preprocessing (OCR, table extraction)
- Fine-tuning LLM models

## Success Criteria

- Successfully index documents and retrieve relevant chunks with
  > 0.7 relevance score
- API response time <3s for retrieval, streaming for generation
- Docker Compose brings up entire stack ... successfully
- 80% code coverage with tests
```

This is the part vibe coders skip and then spend two weeks arguing in PR reviews about what "done" was supposed to mean.

### 3) Tasks (a checklist the AI can't pretend it didn't see)

"Build X" is a vibe. A task list is executable.

Quilora's [task_plan][tasks] is long, but notice the shape: it's ordered, checkable, and test-aware. Example excerpts:

```markdown
## 2. Configuration Management

- [ ] 2.1 Implement settings.py with Pydantic Settings...
- [ ] 2.2 Add configuration for Qdrant connection...
- [ ] 2.3 Add configuration for aisuite...

## 7. FastAPI Backend

- [ ] 7.2 Implement health check endpoint (GET /health)
- [ ] 7.3 Implement document upload endpoint (POST /documents)
- [ ] 7.6 Implement query endpoint with streaming (POST /query)
- [ ] 7.10 Write API tests using TestClient

## 9. Vue 3 Frontend

- [ ] 9.8 Add SSE (Server-Sent Events) handling for streaming responses
- [ ] 9.9 Implement error handling and loading states
```

This is how you stop the assistant from going "done!" when it's actually "I coded something and left half the acceptance criteria as an exercise for the reader."

## Where OpenSpec Fits (One Implementation of SDD)

SDD is the concept. **OpenSpec** is one implementation that standardizes how these artifacts live in-repo (proposal → tasks → spec deltas → archive).

Learn more at [openspec.dev][openspec-site] and the [OpenSpec GitHub repo][openspec-repo].

OpenSpec doesn't magically make specs good. It just makes it harder to skip them.

## SDD vs TDD vs Design Docs (Quick Reality Check)

- **TDD** proves code behaves correctly (in testable cases).
- **Design docs** justify architecture decisions.
- **SDD** defines intended behavior and constraints so implementation (human or AI) has a contract.

They're not enemies. They're different tools for different failure modes.

## The Bottom Line

If you're using AI to write code, SDD isn't bureaucracy. It's guardrails.

You don't wear a seatbelt because you love seatbelts.
You wear it because you've seen what happens when you don't.

Spec‑Driven Development is the seatbelt for AI‑assisted development.

[project-context]: https://github.com/essayyzed/quilora-ai/main/openspec/project.md
[proposal]: https://github.com/essayyzed/quilora-ai/main/openspec/changes/implement-rag-application/proposal.md
[tasks]: https://github.com//essayyzed/quilora-ai/main/openspec/changes/implement-rag-application/tasks.md
[openspec-site]: https://openspec.dev/
[openspec-repo]: https://github.com/Fission-AI/OpenSpec
