package io.crypto.marketalerts.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TokenRecord1d extends TokenRecord {

    @Builder
    public TokenRecord1d(String id, MacdData macd, EmaData ema, RsiData rsi) {
        super(id, macd, ema, rsi);
    }
}
