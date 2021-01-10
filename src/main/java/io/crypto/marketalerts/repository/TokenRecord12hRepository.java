package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord12h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord12hRepository extends MongoRepository<TokenRecord12h, String> {

}
