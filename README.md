# Furniture Backend

Spring Boot backend for the DIY furniture application.

## Prerequisites
- Java 11+
- Maven (optional, wrapper included)

## Build

Windows (PowerShell):
```powershell
.\mvnw.cmd -DskipTests package
```

macOS/Linux:
```bash
./mvnw -DskipTests package
```

## Run

Windows (PowerShell):
```powershell
.\mvnw.cmd spring-boot:run
```

macOS/Linux:
```bash
./mvnw spring-boot:run
```

The server starts on `http://localhost:8080` by default.

## API docs (Swagger UI)

Once the app is running, open:
```
http://localhost:8080/swagger-ui.html
```

## Quick check

Cutting plan endpoint (POST):
```
http://localhost:8080/cutting-plans
```
