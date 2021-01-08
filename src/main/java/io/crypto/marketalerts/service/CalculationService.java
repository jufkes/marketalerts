package io.crypto.marketalerts.service;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.*;
import io.crypto.marketalerts.repository.TokenRecord4hRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final BinanceService binanceService;
    private final TokenRecord4hRepository tokenRecord4hRepository;

    public void processData(String symbol, String interval, Integer period) {
        List<CandleStickData> candleStickData = binanceService.getCandlesStickData(symbol, interval, period);
        RsiData rsi = TechnicalIndicatorHelper.calculateRsiData(candleStickData);
        MacdData macdData = TechnicalIndicatorHelper.calculateMacdData(candleStickData);
        EmaData emaData = TechnicalIndicatorHelper.calculateEmaData(candleStickData);
        TokenRecord4h tokenRecord4h = TokenRecord4h.builder()
                .id(symbol)
                .macd(macdData)
                .rsi(rsi)
                .ema(emaData)
                .build();
        tokenRecord4hRepository.save(tokenRecord4h);
    }

}
