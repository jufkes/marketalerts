package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class TokenRecord4h {

    @Id
    private String id;
    private MacdData macd;
    private EmaData ema;
    private RsiData rsi;

}
