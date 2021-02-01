package io.crypto.marketalerts.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.model.kline.KlineMessage;
import io.crypto.marketalerts.model.symbol.Symbol;
import io.crypto.marketalerts.repository.SymbolRepository;
import io.crypto.marketalerts.service.KlineRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Subscriber {

    private final SymbolRepository symbolRepository;
    private final WebsocketClientEndpoint wsClientEndpoint;
    private final ObjectMapper objectMapper;
    private final KlineRepositoryService klineRepositoryService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // send request to subscribe to streams
        List<String> listOfStreams = getListOfStreams();
        wsClientEndpoint.sendMessage(buildRequest("SUBSCRIBE", listOfStreams));

        // add listener
        wsClientEndpoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                try {
                    KlineMessage klineMessage = objectMapper.readValue(message, KlineMessage.class);
                    // Is candle closed?
                    if (klineMessage.getK() != null && klineMessage.getK().isX()) {
                        log.info("Closed Kline: " + klineMessage.toString());
                        KlineMessage.KlineData klineData = klineMessage.getK();
                        klineRepositoryService.upsertKlineData(klineData);
                    }
                } catch (Exception e) {
                    log.error("Unable to process message: " + message);
                    e.printStackTrace();
                }
            }
        });
    }

    private static String buildRequest(String method, List<String> params) {
        JsonArrayBuilder paramsBuilder = Json.createArrayBuilder();
        params.forEach(paramsBuilder::add);

        return Json.createObjectBuilder()
                .add("method", method)
                .add("params", paramsBuilder)
                .add("id", 1)
                .build()
                .toString();
    }

    private List<String> getListOfStreams() {
        List<String> streams = new ArrayList<>();
        List<Symbol> allSymbols = symbolRepository.findAll();
        allSymbols.forEach(symbol -> {
            for (Interval interval : Interval.values()) {
                streams.add(symbol.getId().toLowerCase() + "@kline_" + interval.getLabel());
            }
        });

        return streams;
    }
}
