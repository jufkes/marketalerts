package io.crypto.marketalerts.model;

import lombok.Builder;

@Builder
public class EmaData {
    private double ema10;
    private double ema20;
    private Direction direction;
    private boolean confirmed;
}
