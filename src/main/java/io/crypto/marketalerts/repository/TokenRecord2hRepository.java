package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord2h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord2hRepository extends MongoRepository<TokenRecord2h, String> {

}
