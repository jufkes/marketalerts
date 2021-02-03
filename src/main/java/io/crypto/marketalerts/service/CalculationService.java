package io.crypto.marketalerts.service;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.EmaData;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.model.MacdData;
import io.crypto.marketalerts.model.RsiData;
import io.crypto.marketalerts.model.kline.KlineMessage;
import io.crypto.marketalerts.model.symbol.Symbol;
import io.crypto.marketalerts.repository.SymbolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {

    private final TokenRecordRepositoryService tokenRecordRepositoryService;
    private final SymbolRepository symbolRepository;
    private final KlineRepositoryService klineRepositoryService;

    /* symbol = coinpair to retrieve. Eventually this will be a list of things to work against
    for now, use ETHUSDT

     intervals = 15m, 30m, 1h, 2h, 4h, 12h, 1d, 1w, 1M
     - need to retrieve market data on these intervals on a schedule. Then save their data to
     collections correlated to them. So for 15m data, it would save to the a tokenRecord15m etc etc

     period = the number of candles to retrieve. Could probably hard code this to 30 for now */
    public void processData(String symbol, Interval interval) {
        List<KlineMessage.KlineData> klines = klineRepositoryService.getKlines(symbol.toUpperCase(), interval);

        if (klines.size() < 27) {
            log.info("Only " + klines.size() + " Klines stored for " + symbol + " at interval " + interval + " - skipping processing.");
            return;
        }
        // TODO need to sort the list of Klines before proceeding - Chase to get the direction required
        // Sort by Close Time ascending
        klines.sort(Comparator.comparing(KlineMessage.KlineData::getT));

        RsiData rsi = TechnicalIndicatorHelper.calculateRsiData(klines);
        MacdData macdData = TechnicalIndicatorHelper.calculateMacdData(klines);
        EmaData emaData = TechnicalIndicatorHelper.calculateEmaData(klines);

        tokenRecordRepositoryService.saveTokenRecord(interval, symbol, macdData, rsi, emaData);
    }

    public void getDataForAllSymbols(Interval interval) {
        List<String> symbols = symbolRepository.findAll().stream().map(Symbol::getId).collect(Collectors.toList());
        symbols.forEach(symbol -> {
            processData(symbol, interval);
        });
    }

    public static Double calculateTradeSize(Double balance, Double risk, Double entry, Double stop) {
        Double percentLoss = (entry - stop) * 0.001;
        return (balance * (risk * 0.001)) / (Math.abs(percentLoss));
    }

}
