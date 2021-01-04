package io.crypto.marketalerts.model;

import lombok.Data;

@Data
public class BinanceCandleStickData {
    private long openTime;
    private long closeTime;
    private String open;
    private String close;
    private String high;
    private String low;
    private String volume;
}