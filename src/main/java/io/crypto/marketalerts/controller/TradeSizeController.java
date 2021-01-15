package io.crypto.marketalerts.controller;

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
public class TradeSizeController {

    @GetMapping("/tradesize")
    public ResponseEntity tradeSize(@RequestParam(value="balance") Double balance,
                                    @RequestParam(value="risk") Double risk,
                                    @RequestParam(value="entry") Double entry,
                                    @RequestParam(value="stop") Double stop) {
        Double trade = CalculationService.calculateTradeSize(balance, risk, entry, stop);
        return ResponseEntity.ok(trade);
    }

}
