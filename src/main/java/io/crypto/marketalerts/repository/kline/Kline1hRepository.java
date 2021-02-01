package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord1h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline1hRepository extends MongoRepository<KlineRecord1h, String> {

}
