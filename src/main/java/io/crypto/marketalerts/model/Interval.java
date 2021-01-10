package io.crypto.marketalerts.model;

import java.util.HashMap;
import java.util.Map;

public enum Interval {
    MINUTE_15("15m"),
    MINUTE_30("30m"),
    HOUR_1("1h"),
    HOUR_2("2h"),
    HOUR_4("4h"),
    HOUR_12("12h"),
    DAY_1("1d"),
    WEEK_1("1w"),
    MONTH_1("1mo");

    private String label;

    Interval(String label) {
        this.label = label;
    }

    private static final Map<String, Interval> BY_LABEL = new HashMap<>();

    static {
        for (Interval e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    public static Interval valueOfLabel(String label) {
        Interval interval = BY_LABEL.get(label);
        if (interval == null) {
            throw new RuntimeException("No interval configured for " + label);
        }
        return interval;
    }

    public String getLabel() {
        return label;
    }

}
