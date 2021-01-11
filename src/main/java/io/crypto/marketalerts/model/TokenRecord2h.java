package io.crypto.marketalerts.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TokenRecord2h extends TokenRecord {

    @Builder
    public TokenRecord2h(String id, MacdData macd, EmaData ema, RsiData rsi) {
        super(id, macd, ema, rsi);
    }

}
