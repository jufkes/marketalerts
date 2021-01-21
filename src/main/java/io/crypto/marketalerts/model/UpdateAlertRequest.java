package io.crypto.marketalerts.model;

import lombok.Data;

@Data
public class UpdateAlertRequest {
    private Interval interval;
    private String symbol;
    private SignalType signal;
    private Boolean willAlert;
}
