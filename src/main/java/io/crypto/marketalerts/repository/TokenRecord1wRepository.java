package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord12h;
import io.crypto.marketalerts.model.TokenRecord1w;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord1wRepository extends MongoRepository<TokenRecord1w, String> {

}
