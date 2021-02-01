package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.CandleStickData;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.model.MacdData;
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
public class MacdController {

    private final KlineRepositoryService klineRepositoryService;

    @GetMapping("/macddata")
    public ResponseEntity processData(@RequestParam(value = "symbol") String symbol, @RequestParam(value = "interval") String interval) {
        Integer period = 27;

        List<KlineMessage.KlineData> klines = klineRepositoryService.getKlines(symbol.toUpperCase(), Interval.valueOfLabel(interval));
        MacdData macdData = TechnicalIndicatorHelper.calculateMacdData(klines);

        return ResponseEntity.ok(macdData.getDirection());
    }
}
