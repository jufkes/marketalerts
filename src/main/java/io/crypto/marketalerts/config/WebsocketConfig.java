package io.crypto.marketalerts.config;

import io.crypto.marketalerts.websocket.WebsocketClientEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Configuration
@ConditionalOnProperty(
        value="websocket.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class WebsocketConfig {

    @Value("${websocket.url}")
    private String url;

    @Bean
    public WebsocketClientEndpoint getWebsocketClientEndpoint() {
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint =
                    new WebsocketClientEndpoint(new URI(url));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            return clientEndPoint;
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
        return null;
    }

}
