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

}
