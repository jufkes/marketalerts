package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.helper.TechnicalIndicatorHelper;
import io.crypto.marketalerts.model.EmaData;
import io.crypto.marketalerts.model.Interval;
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
public class EmaController {

    private final KlineRepositoryService klineRepositoryService;

    @GetMapping("/emadata")
    public ResponseEntity processData(@RequestParam(value = "symbol") String symbol, @RequestParam(value = "interval") String interval, @RequestParam(value = "period") Integer period) {
        // increment the period so that we get the right number of candles for processing
        period++;
        List<KlineMessage.KlineData> klines = klineRepositoryService.getKlines(symbol.toUpperCase(), Interval.valueOfLabel(interval));
        EmaData ema = TechnicalIndicatorHelper.calculateEmaData(klines);
        return ResponseEntity.ok(ema);
    }

}
