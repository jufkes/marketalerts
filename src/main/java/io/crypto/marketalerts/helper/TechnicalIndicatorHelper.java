package io.crypto.marketalerts.helper;

import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.model.EmaData;
import io.crypto.marketalerts.model.SmaData;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


// the initial EMA needs to be calculated with an SMA. SMA is equivalent to a mean calculation
// initial EMA = currentValue * smoothingValue/(period+1) + SMA(period) * (1-smoothingValue/(period+1)
// currentValue * smoothingValue/(period+1) + previousEMA * (1-smoothingValue/(period+1))
// example: period = 5; smoothingValue = 2; values: 1.1, 1.2, 1.3, 1.4, 1.5, 1.6
// SMA = (1.2, 1.3, 1.4, 1.5, 1.6)/5
// EMA = 1.1 * 2/(5+1) + SMA(5) * (1-2/(5+1))
public class TechnicalIndicatorHelper {

    public static SmaData calculateSmaData(List<CandleStickData> candles, Integer period) {
        Collections.reverse(candles);
        List<Double> prices = candles.stream().map(CandleStickData::getClose).collect(Collectors.toList());
        double totalPriceValues = 0;
        //decrement the period to the base value...it was previously incremented for candle retrieval
        period--;
        for (int i = 1; i <= period; i++) {
            double price = prices.get(i);
            totalPriceValues = totalPriceValues + price;
        }
        SmaData SmaData = io.crypto.marketalerts.model.SmaData.builder().build();
        SmaData.setSma(totalPriceValues / period);
        return SmaData;
    }

}
