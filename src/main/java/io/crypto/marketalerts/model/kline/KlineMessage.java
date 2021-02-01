package io.crypto.marketalerts.model.kline;


import lombok.Data;

@Data
public class KlineMessage {

    private KlineData k;

    @Data
    public static class KlineData {
        private String s; // Symbol
        private String i; // Interval
        private String o; // Open price
        private String c; // Close price
        private String h; // High price
        private String l; // Low price
        private boolean x; // Is this kline closed?
        private String q; // Quote asset volume
        private long T; // Kline close time
    }
}


/**
 "k": {
 "s": "BNBBTC",  // Symbol
 "i": "1m",      // Interval
 "o": "0.0010",  // Open price
 "c": "0.0020",  // Close price
 "h": "0.0025",  // High price
 "l": "0.0015",  // Low price
 "x": false,     // Is this kline closed?
 "q": "1.0000",  // Quote asset volume
 **/