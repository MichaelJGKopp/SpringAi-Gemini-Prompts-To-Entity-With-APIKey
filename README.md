# sb3-spring-AI-prompt-to-entity

A Spring Boot application that leverages Spring AI and Gemini to generate personalized book recommendations for customers, mapping AI responses to structured entities.

## Overview

This service provides AI-generated book recommendations through a simple REST API. It formats recommendations either as plain text or as structured `Book` entities with complete metadata.

## API Endpoints

### 1. Simple Recommendation

```
GET /
```

Returns a text-based book recommendation.

**Parameters:**
- `topic` - Book subject (default: "AI and coding")
- `language` - Recommendation language (default: "English")

### 2. Structured Book Data

```
GET /book
```

Returns a fully structured book recommendation with complete metadata including title, authors, ISBN, price, description, etc.

**Parameters:**
- `topic` - Book subject (default: "AI and coding")
- `language` - Recommendation language (default: "English")

## Technology Stack

- Spring Boot 3.4.4
- Spring AI 1.0.0-M7
- Google Gemini AI API (gemini-2.0-flash model)
- Java 21

## Configuration

The application requires a Gemini API key set as the `GEMINI_API_KEY` environment variable.

## Example Usage

```
GET /book?topic=machine%20learning&language=French
```

Returns a structured book recommendation about machine learning in French.