package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord30m;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline30mRepository extends MongoRepository<KlineRecord30m, String> {

}
