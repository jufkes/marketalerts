package io.crypto.marketalerts.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Scanner {
    private String symbol;
    private Double minute15;
    private Double minute30;
    private Double hour1;
    private Double hour2;
    private Double hour4;
    private Double hour12;
    private Double day1;
    private Double week1;
    private Double month1;

}
