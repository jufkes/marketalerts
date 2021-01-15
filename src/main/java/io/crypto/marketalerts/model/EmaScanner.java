package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmaScanner {
    private String symbol;
    private Direction minute15;
    private Direction minute30;
    private Direction hour1;
    private Direction hour2;
    private Direction hour4;
    private Direction hour12;
    private Direction day1;
    private Direction week1;
    private Direction month1;

}
