package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
