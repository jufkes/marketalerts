package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.model.SmaData;
import io.crypto.marketalerts.service.BinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class SmaController {

    private final BinanceService binanceService;

    @GetMapping("/smadata")
    public ResponseEntity processData(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval, @RequestParam(value="period") Integer period) {
        // increment the period so that we get the right number of candles for processing
        period++;
        List<CandleStickData> candles = binanceService.getCandlesStickData(symbol, interval, period);
        SmaData sma = TechnicalIndicatorHelper.calculateSmaData(candles, period);
        return ResponseEntity.ok(sma);
    }
}
