package io.crypto.marketalerts.service;

import io.crypto.marketalerts.exception.ResourceNotFoundException;
import io.crypto.marketalerts.model.*;
import io.crypto.marketalerts.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScannerService {

    private final TokenRecordRepositoryService tokenRecordRepositoryService;
    private final AlertRepository alertRepository;

    public List<EmaScanner> getEmas() {
        return tokenRecordRepositoryService.getEmas();
    }

    public void updateAlert(UpdateAlertRequest request) {
        Optional<Alert> opAlert = alertRepository.findById(request.getInterval());
        if (opAlert.isPresent()) {
            if (request.getSignal() == SignalType.EMA) {
                Alert alert = opAlert.get();
                if (request.getWillAlert()) {
                    alert.getEmaAlerts().add(request.getSymbol().toUpperCase());
                } else {
                    alert.getEmaAlerts().remove(request.getSymbol().toUpperCase());
                }
                alertRepository.save(alert);
            } else {
                throw new RuntimeException("Alerts not configured for interval: " + request.getInterval());
            }
        } else {
            if (request.getSignal() == SignalType.EMA) {
                if (request.getWillAlert()) {
                    Alert alert = Alert.builder()
                            .id(request.getInterval())
                            .emaAlerts(Set.of(request.getSymbol().toUpperCase()))
                            .build();
                    alertRepository.save(alert);
                }
            } else {
                throw new RuntimeException("Alerts not configured for interval: " + request.getInterval());
            }
        }
    }
}
