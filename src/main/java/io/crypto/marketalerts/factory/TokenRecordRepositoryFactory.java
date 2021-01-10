package io.crypto.marketalerts.factory;

import io.crypto.marketalerts.repository.TokenRecord12hRepository;
import io.crypto.marketalerts.repository.TokenRecord4hRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRecordRepositoryFactory {
    private final TokenRecord4hRepository tokenRecord4hRepository;
    private final TokenRecord12hRepository tokenRecord12hRepository;

    public MongoRepository getTokenRecordRepository(String interval) {
        if ("4h".equals(interval)) {
            return tokenRecord4hRepository;
        } else if ("12h".equals(interval)) {
            return tokenRecord12hRepository;
        } else {
            throw new RuntimeException("No TokenRecordRepository configured for interval: " + interval);
        }
    }
}
