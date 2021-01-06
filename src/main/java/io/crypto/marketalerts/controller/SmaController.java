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
    public ResponseEntity processData(@RequestParam(value="symbol") String symbol, @RequestParam(value="period") Integer period) {
        List<CandleStickData> candles = binanceService.getCandlesStickData(symbol);
        SmaData sma = TechnicalIndicatorHelper.calculateSmaData(candles, period);
        return ResponseEntity.ok(sma);
    }
}
