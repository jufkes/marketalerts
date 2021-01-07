package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.CandleStickData;
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
public class MacdController {

    private final BinanceService binanceService;

    @GetMapping("/macddata")
    public ResponseEntity processData(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval) {
        Integer period = 27;
        List<CandleStickData> candles = binanceService.getCandlesStickData(symbol.toUpperCase(), interval, period);
        String state = TechnicalIndicatorHelper.calculateMacdData(candles);
        return ResponseEntity.ok(state);
    }
}
