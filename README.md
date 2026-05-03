# Asset Manager

A small **Spring Boot** demo service that tracks digital assets (files) per owner and exposes how much storage a given user consumes.

## What it does

- Persists **assets** in a database: file name, type (for example PDF, PNG, PSD), size in bytes, and an **owner** identifier.
- Provides an HTTP API to **sum total storage** for all assets belonging to a specific owner.

This repository is structured as a typical layered Spring application (REST controller → service → JPA repository → entity) and can be used as a playground for refactoring or tooling demos.

## Tech stack

| Piece | Choice |
|--------|--------|
| Runtime | Java **17** |
| Framework | Spring Boot **4.x** (Web MVC, Data JPA) |
| Database | **H2** (embedded; suitable for local development) |
| Console | **H2 Console** (bundled via `spring-boot-h2console`) |
| Boilerplate | **Lombok** (`@Data` on entities) |

## API

### Total storage for a user

Returns the sum of `sizeInBytes` for every asset whose `ownerId` matches the path parameter.

```http
GET /api/assets/user/{ownerId}/storage
```

**Response:** `long` — total bytes (JSON number).

**Example:**

```http
GET /api/assets/user/alice/storage
```

If there are no matching assets, the response is `0`.

## Running locally

**Requirements:** JDK 17 and Maven (or use the included wrapper).

```bash
./mvnw spring-boot-run
```

Or:

```bash
./mvnw clean package
java -jar target/assetmanager-0.0.1-SNAPSHOT.jar
```

The default port is **8080** unless you override `server.port` in configuration.

### H2 Console

With the H2 console dependency enabled, you can open the database UI in a browser (typically at `/h2-console`) and use the JDBC URL shown in Spring Boot’s auto-configuration logs for H2. Use that to insert or inspect `Asset` rows while testing the API.

### Tests

```bash
./mvnw test
```

The included test loads the Spring context (`@SpringBootTest`).

## Project layout

- `Asset` — JPA entity for stored assets  
- `AssetRepository` — Spring Data JPA (`JpaRepository`, plus `findByOwnerId`)  
- `AssetService` — business logic (total storage for an owner)  
- `AssetController` — REST mapping under `/api/assets`

## Configuration

Application name and other settings live in `src/main/resources/application.properties`. Add datasource or server overrides there as needed for non-local environments.
