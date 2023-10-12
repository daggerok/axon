axon [![CI](https://github.com/daggerok/axon/actions/workflows/ci.yml/badge.svg)](https://github.com/daggerok/axon/actions/workflows/ci.yml)
====

**microservices, cqrs and event sourcing using: spring-boot + axon**:
- axon provides easy api for splitting business logic from infrastructure
- spring-boot provides whole infrastructure
- axon-spring-boot-starter provides auto-configurations  

*follow the code*:

1. receiving a complaint command
2. handling command (there validation / business logic happens)
3. processing fired event on previous step, saving / applying command  
4. handle on each event sourcing replay

backing services (docker-compose required):

```bash
docker-compose up
```

build

```bash
./mvnw clean package
```

run

```bash
./mvnw -pl complaints spring-boot:run
```
