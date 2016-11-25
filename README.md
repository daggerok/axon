axon [![build](https://travis-ci.org/daggerok/axon.svg?branch=master)](https://travis-ci.org/daggerok/axon)
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

```fish
docker-compose up
```

build

```fish
mvn clean package
```

run

```fish
mvn -pl complaints spring-boot:run
```
