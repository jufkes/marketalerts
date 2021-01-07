package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord4h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord4hRepository extends MongoRepository<TokenRecord4h, String> {

}
