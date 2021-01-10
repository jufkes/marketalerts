package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord1d;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord1dRepository extends MongoRepository<TokenRecord1d, String> {

}
