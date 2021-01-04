package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandleStickData {
    private long openTime;
    private long closeTime;
    private double open;
    private double close;
    private double high;
    private double low;
    private double volume;
}