package daggerok.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintQueryObjectRepository extends MongoRepository<ComplaintQueryObject, String> {}
