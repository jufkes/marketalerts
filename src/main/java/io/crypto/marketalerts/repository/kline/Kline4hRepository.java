package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord4h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline4hRepository extends MongoRepository<KlineRecord4h, String> {

}
