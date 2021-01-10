package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord30m;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord30mRepository extends MongoRepository<TokenRecord30m, String> {

}
