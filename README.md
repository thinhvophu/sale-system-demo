# sale-system-demo
This is a simple sale system which provide simple APIs to create/retrieve and buy items.
Java must be installed to build and run source code.
## Technical stack
- Java 11
- Spring Boot, Spring Data JPA
- postgresql
- Gradle
- Docker
## Runtime
Application and database will run inside 2 separate docker containers in the same docker network:
- application-server
- postgre-server
## Commands
- start.sh: to start the application from scratch. This script will stop, remove all running containers then build and running new one from latest source code.
- stop.sh: stop the running containers.
- restart.sh: restart or start containers from last state.
