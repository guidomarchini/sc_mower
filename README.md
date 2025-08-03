# sc_mower

## Overview
This project is a solution to the SEAT:CODE mower control coding challenge.

It demonstrates a DDD-inspired, modular, and testable architecture using Kotlin and Maven.

The application allows controlling robotic mowers on a rectangular plateau, processing movement instructions and reporting their final positions.

## Architecture
- **model-layer**: Domain model (value objects, business logic)
- **service-layer**: Application logic (MowerEngine, validation, handlers)
- **web-layer**: REST API (Spring Boot, controllers, OpenAPI/Swagger)
- **docs/**: PlantUML diagrams for each layer

> **Note:** Persistence is not supported, as it was not required in the problem statement.

## How it works
- The plateau is defined by its top-right coordinates (bottom-left is always 0,0).
- Each mower is initialized with a position (x, y, direction) and a sequence of actions (L, R, M).
- Mowers are processed sequentially: the next mower starts only after the previous one finishes.
- The system prevents mowers from leaving the plateau or overlapping.
- Input and output formats match the problem statement (see below).

## Assumptions & Decisions
- Only one plateau is supported (as per MVP scope).
- Input is received as a multiline string (via HTTP POST).
- Directions are N, E, S, W. Actions are L (left), R (right), M (move forward).
- If a mower tries to move to an invalid or occupied position, it stays in place.
- The domain and service layers are pure Kotlin, with no Spring dependencies.
- Parsers and mappers are placed in the web-layer for the MVP; for multi-interface support, move to a shared module.

## How to run
1. **Build the project:**
   ```sh
   mvn clean install
   ```
2. **Run the web application:**
   ```sh
   cd web-layer
   mvn spring-boot:run
   ```
3. **Access Swagger UI:**
   - Open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

4. **Example input:**
   ```
   5 5
   1 2 N
   LMLMLMLMM
   3 3 E
   MMRMMRMRRM
   ```
   **Expected output:**
   ```
   1 3 N
   5 1 E
   ```

## Testing
- Run all tests:
  ```sh
  mvn test
  ```
- Includes unit tests (domain, service) and integration tests (controller, E2E)

## Diagrams
- See `docs/` for PlantUML diagrams of each layer.

## License
MIT
