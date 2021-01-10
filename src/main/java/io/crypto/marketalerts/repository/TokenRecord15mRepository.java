package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord15m;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord15mRepository extends MongoRepository<TokenRecord15m, String> {

}
