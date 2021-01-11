package io.crypto.marketalerts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
abstract public class TokenRecord {

    @Id
    private String id;
    private MacdData macd;
    private EmaData ema;
    private RsiData rsi;

}
