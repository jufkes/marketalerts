package io.crypto.marketalerts.service;

import io.crypto.marketalerts.model.CandleStickData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BinanceService {

    private final WebClient webClient;

    public List<CandleStickData> getCandlesStickData(String symbol) {

        List<ArrayList> data = webClient.get().uri(uriBuilder ->
                uriBuilder.queryParam("symbol", symbol)
                        .queryParam("interval", "4h")
                        .queryParam("limit", 27).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ArrayList>>() {})
                .block();

        if (data != null) {
            return data.stream().map(datum -> {
                CandleStickData candleStick = CandleStickData.builder()
                        .openTime((long) datum.get(0))
                        .closeTime((long) datum.get(6))
                        .open(Double.parseDouble((String) datum.get(1)))
                        .high(Double.parseDouble((String) datum.get(2)))
                        .low(Double.parseDouble((String) datum.get(3)))
                        .close(Double.parseDouble((String) datum.get(4)))
                        .volume(Double.parseDouble((String) datum.get(5)))
                        .build();
                return candleStick;
            }).collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    public void processData(String symbol) {
        List<CandleStickData> candleStickData = getCandlesStickData(symbol);

    }

}

