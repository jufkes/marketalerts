package io.crypto.marketalerts.controllers;

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
public class CandleStickController {
    private final BinanceService binanceService;

    @GetMapping("/candlesticks")
    public ResponseEntity<List<CandleStickData>> getSticks(@RequestParam(value="symbol") String symbol) {
        return ResponseEntity.ok(binanceService.getCandlesStickData(symbol));
    }

}