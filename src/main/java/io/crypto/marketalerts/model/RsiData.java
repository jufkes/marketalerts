package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RsiData {
    private double value;
    private RsiState state;
}
