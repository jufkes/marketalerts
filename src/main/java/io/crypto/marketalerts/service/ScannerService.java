package io.crypto.marketalerts.service;

import io.crypto.marketalerts.model.EmaScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScannerService {

    private final TokenRecordRepositoryService tokenRecordRepositoryService;

    public List<EmaScanner> getEmas() {
        return tokenRecordRepositoryService.getEmas();
    }
}
