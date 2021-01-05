package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MacdData {
    private double ema12;
    private double ema26;
    private Direction direction;
    private boolean confirmed;
}
