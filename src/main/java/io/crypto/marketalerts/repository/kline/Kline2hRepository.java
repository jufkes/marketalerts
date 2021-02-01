package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord2h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline2hRepository extends MongoRepository<KlineRecord2h, String> {

}
