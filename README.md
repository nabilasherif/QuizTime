# QuizTime Backend

A REST API backend for a quiz application built with Ktor, MongoDB Atlas, and Kotlin.

## Tech Stack

- **Kotlin** with Ktor (Netty engine)
- **MongoDB Atlas** (replica set, TLS)
- **Koin** for dependency injection
- **kotlinx.serialization** for JSON
- **Logback** for logging

## Features
Here's a list of features included in this project:

| Name | Description |
|------|-------------|
| [Status Pages](https://start.ktor.io/p/io.ktor/server-status-pages) | Provides exception handling for routes |
| [Resources](https://start.ktor.io/p/io.ktor/server-resources) | Provides type-safe routing |
| [Koin](https://start.ktor.io/p/io.insert-koin/server-koin) | Provides dependency injection |
| [Request Validation](https://start.ktor.io/p/io.ktor/server-request-validation) | Adds validation for incoming requests |
| [Content Negotiation](https://start.ktor.io/p/io.ktor/server-content-negotiation) | Provides automatic content conversion according to Content-Type and Accept headers |
| [kotlinx.serialization](https://start.ktor.io/p/io.ktor/server-kotlinx-serialization) | Handles JSON serialization using kotlinx.serialization library |

## API Endpoints

### Quiz Questions `quiz/questions`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/quiz/questions` | Get all questions (optional `?topicCode=&limit=`) |
| POST | `/quiz/questions` | Create or update a question |
| GET | `/quiz/questions/{questionId}` | Get question by ID |
| DELETE | `/quiz/questions/{questionId}` | Delete question by ID |

### Quiz Topics `quiz/topics`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/quiz/topics` | Get all topics |
| POST | `/quiz/topics` | Create or update a topic |
| GET | `/quiz/topics/{topicId}` | Get topic by ID |
| DELETE | `/quiz/topics/{topicId}` | Delete topic by ID |

### Issue Reports `report/issues`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/report/issues` | Get all issue reports |
| POST | `/report/issues` | Submit an issue report for a question |
| DELETE | `/report/issues/{issueId}` | Delete an issue report by ID |


## Building & Running
To build or run the project, use one of the following tasks:


| Task | Description |
|------|-------------|
| `./gradlew test`    | Run the tests     |
| `./gradlew build`   | Build the project |
| `./gradlew run`     | Run the server    |

If the server starts successfully, you'll see the following output:
```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```
