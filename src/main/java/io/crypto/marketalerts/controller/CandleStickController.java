package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.service.BinanceService;
import io.crypto.marketalerts.service.CalculationService;
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

    private final CalculationService calculationService;

    @GetMapping("/process")
    public ResponseEntity processData(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval, @RequestParam(value="period") Integer period) {
        calculationService.processData(symbol.toUpperCase(), interval, period);
        return ResponseEntity.ok().build();
    }

}