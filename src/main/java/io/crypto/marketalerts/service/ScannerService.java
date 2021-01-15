package io.crypto.marketalerts.service;

import io.crypto.marketalerts.model.Scanner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScannerService {

    public List<Scanner> getEmas() {
        return List.of(
                Scanner.builder().symbol("FOO").build(),
                Scanner.builder().symbol("BAR").build()
        );
    }
}
