package io.crypto.marketalerts.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TokenRecord15m extends TokenRecord {

    @Builder
    public TokenRecord15m(String id, MacdData macd, EmaData ema, RsiData rsi) {
        super(id, macd, ema, rsi);
    }

}
