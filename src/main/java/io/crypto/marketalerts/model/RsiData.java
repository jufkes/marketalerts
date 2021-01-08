package io.crypto.marketalerts.model;

import lombok.Builder;

@Builder
public class RsiData {
    private double value;
    private RsiState state;
}
