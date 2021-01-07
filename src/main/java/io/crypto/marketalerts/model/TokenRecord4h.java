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
    // We don't need the symbol field the id can be the symbol value and can be queried later
//    private String symbol;
    private MacdData macd;

    @Data
    @Builder
    public static class MacdData {
        private double twelveEma;
        private double twentySixEma;
        private String direction;
        private boolean confirmed;
    }

    @Data
    @Builder
    public static class RsiData { }

    @Data
    @Builder
    public static class emaData { }

}

//"Symbol": "ethbtc",
//        "macd": {
//        "12ema": 0.0233,
//        "26ema": 0.0245,
//        "direction": "bearish",
//        "confirmed": false
//        },
//        "emaData": {
//        "10ema": 0.0233,
//        "20ema": 0.0231,
//        "direction": "bullish",
//        "confirmed": true
//        }
//        "rsiData": {
//        "Value": 45,
//        "State": "none
//        }