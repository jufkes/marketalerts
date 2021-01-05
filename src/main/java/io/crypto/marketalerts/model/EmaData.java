package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmaData {
    private double ema10;
    private double ema20;
    private Direction direction;
    private boolean confirmed;
}
