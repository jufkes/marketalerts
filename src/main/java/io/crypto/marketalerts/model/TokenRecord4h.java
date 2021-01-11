package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TokenRecord4h extends TokenRecord {

    @Builder
    public TokenRecord4h(String id, MacdData macd, EmaData ema, RsiData rsi) {
        super(id, macd, ema, rsi);
    }

}
