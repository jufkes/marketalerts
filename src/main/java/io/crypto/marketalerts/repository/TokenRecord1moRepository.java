package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.TokenRecord1mo;
import io.crypto.marketalerts.model.TokenRecord30m;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRecord1moRepository extends MongoRepository<TokenRecord1mo, String> {

}
