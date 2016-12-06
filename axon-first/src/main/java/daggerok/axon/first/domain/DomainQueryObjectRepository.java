package daggerok.axon.first.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainQueryObjectRepository extends MongoRepository<DomainQueryObject, String> {}
