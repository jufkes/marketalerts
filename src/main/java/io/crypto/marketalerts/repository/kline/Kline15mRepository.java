package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord15m;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline15mRepository extends MongoRepository<KlineRecord15m, String> {

}
