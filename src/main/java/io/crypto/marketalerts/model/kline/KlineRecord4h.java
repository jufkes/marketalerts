package io.crypto.marketalerts.model.kline;

import io.crypto.marketalerts.model.kline.KlineMessage.KlineData;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class KlineRecord4h extends KlineRecord {

    @Builder
    public KlineRecord4h(String id, List<KlineData> klines) {
        super(id, klines);
    }

}
