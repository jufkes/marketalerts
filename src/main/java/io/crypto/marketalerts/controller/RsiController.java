package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.model.RsiData;
import io.crypto.marketalerts.model.kline.KlineMessage;
import io.crypto.marketalerts.service.KlineRepositoryService;
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
public class RsiController {

    private final KlineRepositoryService klineRepositoryService;

    @GetMapping("/rsidata")
    public ResponseEntity processData(@RequestParam(value = "symbol") String symbol, @RequestParam(value = "interval") String interval) {
        Integer period = 14;
        List<KlineMessage.KlineData> klines = klineRepositoryService.getKlines(symbol.toUpperCase(), Interval.valueOfLabel(interval));
        RsiData rsi = TechnicalIndicatorHelper.calculateRsiData(klines);
        return ResponseEntity.ok(rsi);
    }
}
