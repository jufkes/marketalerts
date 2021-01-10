package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord1h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord1hRepository extends MongoRepository<TokenRecord1h, String> {

}
