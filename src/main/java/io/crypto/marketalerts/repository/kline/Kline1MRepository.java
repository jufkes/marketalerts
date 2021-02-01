package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord1M;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline1MRepository extends MongoRepository<KlineRecord1M, String> {

}
