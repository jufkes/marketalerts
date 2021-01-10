package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord1M;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord1MRepository extends MongoRepository<TokenRecord1M, String> {

}
