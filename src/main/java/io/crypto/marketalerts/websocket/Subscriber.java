package io.crypto.marketalerts.websocket;

import io.crypto.marketalerts.repository.SymbolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Subscriber {

    private final SymbolRepository symbolRepository;
    private final WebsocketClientEndpoint wsClientEndpoint;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // send request to subscribe to streams
        wsClientEndpoint.sendMessage(getMessage("SUBSCRIBE", List.of("ethusdt@kline_30m")));
    }

    private static String getMessage(String method, List<String> params) {
        JsonArrayBuilder paramsBuilder = Json.createArrayBuilder();
        params.forEach(paramsBuilder::add);

        return Json.createObjectBuilder()
                .add("method", method)
                .add("params", paramsBuilder)
                .add("id", 1)
                .build()
                .toString();
    }
}
