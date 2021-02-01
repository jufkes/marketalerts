package io.crypto.marketalerts.helper;

import io.crypto.marketalerts.model.*;
import io.crypto.marketalerts.model.kline.KlineMessage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TechnicalIndicatorHelper {

    public static Double calculateSmaData(List<KlineMessage.KlineData> klines, Integer period) {
        Collections.reverse(klines);
        List<Double> prices = klines.stream().map(KlineMessage.KlineData::getC).map(Double::parseDouble).collect(Collectors.toList());
        double totalPriceValues = 0;
        //decrement the period to the base value...it was previously incremented for candle retrieval
        period--;
        for (int i = 1; i <= period; i++) {
            double price = prices.get(i);
            totalPriceValues = totalPriceValues + price;
        }

        return totalPriceValues / period;
    }

    private static Double calculateEma(List<KlineMessage.KlineData> klines, Integer period) {
        Integer smoothingValue = 2;
        period--;
        List<Double> prices = klines.stream().map(KlineMessage.KlineData::getC).map(Double::parseDouble).collect(Collectors.toList());
        Double ema = prices.get(0) * (smoothingValue/(period + 1)) + calculateSmaData(klines, period) * (1 - smoothingValue/(period + 1));
        return ema;
    }

    public static EmaData calculateEmaData(List<KlineMessage.KlineData> klines) {
        Double tenEma = calculateEma(klines, 10);
        Double twentyEma = calculateEma(klines, 20);

        Direction direction = Direction.NONE;
        if ( tenEma < twentyEma ) {
            direction = Direction.BEARISH;
        } else if ( tenEma > twentyEma ) {
            direction = Direction.BULLISH;
        }

        return EmaData.builder()
                .ema10(tenEma)
                .ema20(twentyEma)
                .direction(direction)
                .confirmed(true)
                .build();
    }

    public static MacdData calculateMacdData(List<KlineMessage.KlineData> klines) {
        Double twelveEma = calculateEma(klines, 12);
        Double twentySixEma = calculateEma(klines, 26);
        Direction direction = Direction.NONE;
        if (twelveEma > twentySixEma) {
            direction = Direction.BULLISH;
        } else if (twelveEma < twentySixEma) {
            direction = Direction.BEARISH;
        }

        MacdData macdData = MacdData.builder()
                .ema12(calculateEma(klines, 12))
                .ema26(calculateEma(klines, 26)).direction(direction)
                .confirmed(true).build();
        return macdData;
    }

    public static RsiData calculateRsiData(List<KlineMessage.KlineData> klines) {
        int periodLength = 14;
        int lastBar = klines.size() - 1;
        int firstBar = lastBar - periodLength + 1;
        if (firstBar < 0) {
            String msg = "Quote history length " + klines.size() + " is insufficient to calculate the indicator.";
        }

        double aveGain = 0, aveLoss = 0;
        for (int bar = firstBar + 1; bar <= lastBar; bar++) {
            double change = Double.parseDouble(klines.get(bar).getC()) - Double.parseDouble(klines.get(bar - 1).getC());
            if (change >= 0) {
                aveGain += change;
            } else {
                aveLoss += change;
            }
        }

        double rs = aveGain / Math.abs(aveLoss);
        double rsi = 100 - 100 / (1 + rs);

        // determine overbought / oversold
        RsiState state = RsiState.NONE;
        if (rsi < 35 ) {
            state = RsiState.OVERSOLD;
        } else if (rsi > 65 ) {
            state = RsiState.OVERBOUGHT;
        }

        return RsiData.builder()
                .value(rsi)
                .state(state)
                .build();
    }

}
