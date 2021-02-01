package io.crypto.marketalerts.repository.kline;

import io.crypto.marketalerts.model.kline.KlineRecord12h;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Kline12hRepository extends MongoRepository<KlineRecord12h, String> {

}
