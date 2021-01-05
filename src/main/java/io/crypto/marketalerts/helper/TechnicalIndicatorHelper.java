package io.crypto.marketalerts.helper;

import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.model.EmaData;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TechnicalIndicatorHelper {

    public static double round(double value) {
        return round(value, 2);
    }

    public static double round(double value, int numberOfDigitsAfterDecimalPoint) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static SmaData calculateSmaData(List<CandleStickData> candles, Integer period) {
        List<Double> prices = candles.stream().map(CandleStickData::getClose).collect(Collectors.toList());

        double[] results = new double[prices.size()];

        int maxLength = prices.size() - period;

        for (int i = 0; i <= maxLength; i++) {
            results[(i + period - 1)] = round((Arrays.stream(Arrays.copyOfRange(prices.toArray(), i, (i + period))).sum()) / period);
        }

        return this;
    }

    public static EmaData calculateEmaData(List<CandleStickData> candles, Integer period) {
        List<Double> prices = candles.stream().map(CandleStickData::getClose).collect(Collectors.toList());

        this.prices = prices;
        this.period = period;

        this.smoothingConstant = 2d / (this.period + 1);

        this.periodSma = new double[this.prices.length];
        this.periodEma = new double[this.prices.length];

        SimpleMovingAverage sma = new SimpleMovingAverage();

        for (int i = (period - 1); i < this.prices.length; i++) {
            double[] slice = Arrays.copyOfRange(this.prices, 0, i + 1);
            double[] smaResults = sma.calculate(slice, this.period).getSMA();
            this.periodSma[i] = smaResults[smaResults.length - 1];

            if (i == (period - 1)) {
                this.periodEma[i] = this.periodSma[i];
            } else if (i > (period - 1)) {
                // Formula: (Close - EMA(previous day)) x multiplier +
                // EMA(previous day)
                this.periodEma[i] = (this.prices[i] - periodEma[i - 1]) * this.smoothingConstant
                        + this.periodEma[i - 1];
            }

            this.periodEma[i] = NumberFormatter.round(this.periodEma[i]);
        }

        return this;
    }

}
