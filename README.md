# Design Patterns in Java: Builder, Singleton, Prototype

A small banking domain (`BankAccount`, `Customer`) used to illustrate three creational design patterns in Java 17.

| Pattern | Where | What it shows |
|---|---|---|
| **Builder** | `BankAccount.AccountBuilder`, `BankDirector` | Fluent construction of a `BankAccount` with many optional fields |
| **Singleton** | `AccountRepositoryImpl.getInstance()` | A single, thread-safe in-memory repository shared by several threads (eager initialization in a static block) |
| **Prototype** | `BankAccount.clone()`, `Customer.clone()` | Deep copy of an account, including its customer |

Also included: a generic `JsonSerializer<T>` (Jackson) and filtering of accounts with a `Predicate`.

## Run

```bash
mvn compile exec:java -Dexec.mainClass=org.example.Main
```

Or open the project in IntelliJ IDEA and run `org.example.Main`.

## Tech
Java 17 · Maven · Jackson
