package io.crypto.marketalerts.helper;

import io.crypto.marketalerts.model.CandleStickData;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TechnicalIndicatorHelper {

    public static Double calculateSmaData(List<CandleStickData> candles, Integer period) {
        Collections.reverse(candles);
        List<Double> prices = candles.stream().map(CandleStickData::getClose).collect(Collectors.toList());
        double totalPriceValues = 0;
        //decrement the period to the base value...it was previously incremented for candle retrieval
        period--;
        for (int i = 1; i <= period; i++) {
            double price = prices.get(i);
            totalPriceValues = totalPriceValues + price;
        }

        return totalPriceValues / period;
    }

    public static Double calculateEmaData(List<CandleStickData> candles, Integer period) {
        Integer smoothingValue = 2;
        period--;
        List<Double> prices = candles.stream().map(CandleStickData::getClose).collect(Collectors.toList());
        Double ema = prices.get(0) * (smoothingValue/(period + 1)) + calculateSmaData(candles, period) * (1 - smoothingValue/(period + 1));
        return ema;
    }

    public static String calculateMacdData(List<CandleStickData> candles) {
        Double twelveEma = calculateEmaData(candles, 12);
        Double twentySixEma = calculateEmaData(candles, 26);
        String state;
        if (twelveEma > twentySixEma) {
            state = "bullish";
        } else if (twelveEma < twentySixEma) {
            state = "bearish";
        } else {
            state = "none";
        }
        return state;
    }

    public static Double calculateRsiData(List<CandleStickData> candles) {
        int periodLength = 14;
        int lastBar = candles.size() - 1;
        int firstBar = lastBar - periodLength + 1;
        if (firstBar < 0) {
            String msg = "Quote history length " + candles.size() + " is insufficient to calculate the indicator.";
        }

        double aveGain = 0, aveLoss = 0;
        for (int bar = firstBar + 1; bar <= lastBar; bar++) {
            double change = candles.get(bar).getClose() - candles.get(bar - 1).getClose();
            if (change >= 0) {
                aveGain += change;
            } else {
                aveLoss += change;
            }
        }

        double rs = aveGain / Math.abs(aveLoss);
        double rsi = 100 - 100 / (1 + rs);

        return rsi;
    }

}
