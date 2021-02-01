package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord1w;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline1wRepository extends MongoRepository<KlineRecord1w, String> {

}
