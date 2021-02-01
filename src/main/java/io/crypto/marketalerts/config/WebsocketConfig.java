package io.crypto.marketalerts.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.crypto.marketalerts.model.kline.KlineMessage;
import io.crypto.marketalerts.websocket.WebsocketClientEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ConditionalOnProperty(
        value="websocket.enabled",
        havingValue = "true",
        matchIfMissing = false)
@Slf4j
public class WebsocketConfig {

    @Value("${websocket.url}")
    private String url;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public WebsocketClientEndpoint getWebsocketClientEndpoint() {
        try {
            // open websocket
            return new WebsocketClientEndpoint(new URI(url));
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
        return null;
    }

}
