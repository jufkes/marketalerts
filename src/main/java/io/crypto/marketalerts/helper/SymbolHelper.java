package io.crypto.marketalerts.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SymbolHelper {

    public List<String> getSymbolsFromFile() {
        List<String> symbols = new ArrayList<>();

        try {
            InputStream resource = Objects.requireNonNull(
                    this.getClass().getClassLoader().getResource("symbols.txt")).openStream();

            symbols = new BufferedReader(
                    new InputStreamReader(
                            resource,
                            StandardCharsets.UTF_8
                    )
            ).lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error reading symbols file: " + e.getMessage());
        }
        return symbols;
    }
}
