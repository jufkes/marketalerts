package io.crypto.marketalerts.service;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final BinanceService binanceService;
    private final TokenRecordRepositoryService tokenRecordRepositoryService;

    /* symbol = coinpair to retrieve. Eventually this will be a list of things to work against
    for now, use ETHUSDT

     intervals = 15m, 30m, 1h, 2h, 4h, 12h, 1d, 1w, 1M
     - need to retrieve market data on these intervals on a schedule. Then save their data to
     collections correlated to them. So for 15m data, it would save to the a tokenRecord15m etc etc

     period = the number of candles to retrieve. Could probably hard code this to 30 for now */
    public void processData(String symbol, Interval interval, Integer period) {
        List<CandleStickData> candleStickData = binanceService.getCandlesStickData(symbol, interval, period);
        RsiData rsi = TechnicalIndicatorHelper.calculateRsiData(candleStickData);
        MacdData macdData = TechnicalIndicatorHelper.calculateMacdData(candleStickData);
        EmaData emaData = TechnicalIndicatorHelper.calculateEmaData(candleStickData);

        tokenRecordRepositoryService.saveTokenRecord(interval, symbol, macdData, rsi, emaData);
    }

    public static Double calculateTradeSize(Double balance, Double risk, Double entry, Double stop) {
        Double percentLoss = (entry - stop) * 0.001;
        return (balance * (risk * 0.001)) / (Math.abs(percentLoss));
    }

}
