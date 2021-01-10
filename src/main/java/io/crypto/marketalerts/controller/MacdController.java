package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.model.MacdData;
import io.crypto.marketalerts.model.TokenRecord4h;
import io.crypto.marketalerts.repository.TokenRecord4hRepository;
import io.crypto.marketalerts.service.BinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class MacdController {

    private final BinanceService binanceService;
    @Autowired
    private TokenRecord4hRepository tokenRecord4hRepository;

    @GetMapping("/macddata")
    public ResponseEntity processData(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval) {
        Integer period = 27;

        List<CandleStickData> candles = binanceService.getCandlesStickData(symbol.toUpperCase(), Interval.valueOfLabel(interval), period);
        MacdData macdData = TechnicalIndicatorHelper.calculateMacdData(candles);

        return ResponseEntity.ok(macdData.getDirection());
    }
}
