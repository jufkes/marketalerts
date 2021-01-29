package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.symbol.Symbol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SymbolRepository extends MongoRepository<Symbol, String> {

}
