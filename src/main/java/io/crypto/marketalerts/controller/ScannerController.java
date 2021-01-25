package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.exception.ResourceNotFoundException;
import io.crypto.marketalerts.model.ScannerData;
import io.crypto.marketalerts.model.UpdateAlertRequest;
import io.crypto.marketalerts.service.ScannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/scanner")
public class ScannerController {

    private final ScannerService scannerService;

    @GetMapping("/ema")
    public ResponseEntity<List<ScannerData>> getEma() {
        return ResponseEntity.ok(scannerService.getEmas());
    }

    @GetMapping("/macd")
    public ResponseEntity<List<ScannerData>> getMacd() {
        return ResponseEntity.ok(scannerService.getMacds());
    }

    @PutMapping("/alerts")
    public ResponseEntity<Void> updateAlert(@RequestBody UpdateAlertRequest request) {
        try {
            scannerService.updateAlert(request);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }

    }

}