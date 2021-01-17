package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.model.EmaScanner;
import io.crypto.marketalerts.service.ScannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/scanner")
public class ScannerController {

    private final ScannerService scannerService;

    @GetMapping("/ema")
    public ResponseEntity<List<EmaScanner>> getEma() {

        return ResponseEntity.ok(scannerService.getEmas());
    }

}