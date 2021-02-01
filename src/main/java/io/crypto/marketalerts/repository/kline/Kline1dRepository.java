package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord1d;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline1dRepository extends MongoRepository<KlineRecord1d, String> {

}
