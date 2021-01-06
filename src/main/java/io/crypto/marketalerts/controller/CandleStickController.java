package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.service.BinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class CandleStickController {
    private final BinanceService binanceService;

    @GetMapping("/process")
    public ResponseEntity processData(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval, @RequestParam(value="period") Integer period) {
        binanceService.processData(symbol, interval, period);
        return ResponseEntity.ok().build();
    }

}