package io.crypto.marketalerts.factory;

import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRecordRepositoryFactory {
    private final TokenRecord15mRepository tokenRecord15mRepository;
    private final TokenRecord30mRepository tokenRecord30mRepository;
    private final TokenRecord1hRepository tokenRecord1hRepository;
    private final TokenRecord2hRepository tokenRecord2hRepository;
    private final TokenRecord4hRepository tokenRecord4hRepository;
    private final TokenRecord12hRepository tokenRecord12hRepository;
    private final TokenRecord1dRepository tokenRecord1dRepository;
    private final TokenRecord1wRepository tokenRecord1wRepository;
    private final TokenRecord1moRepository tokenRecord1moRepository;

    public MongoRepository getTokenRecordRepository(Interval interval) {
        switch (interval) {
            case MINUTE_15:
                return tokenRecord15mRepository;
            case MINUTE_30:
                return tokenRecord30mRepository;
            case HOUR_1:
                return tokenRecord1hRepository;
            case HOUR_2:
                return tokenRecord2hRepository;
            case HOUR_4:
                return tokenRecord4hRepository;
            case HOUR_12:
                return tokenRecord12hRepository;
            case DAY_1:
                return tokenRecord1dRepository;
            case WEEK_1:
                return tokenRecord1wRepository;
            case MONTH_1:
                return tokenRecord1moRepository;
            default:
                throw new RuntimeException("No TokenRecordRepository configured for interval: " + interval);
        }
    }
}
