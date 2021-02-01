package io.crypto.marketalerts.factory;

import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.repository.kline.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KlineRecordRepositoryFactory {
    private final Kline15mRepository kline15mRepository;
    private final Kline30mRepository kline30mRepository;
    private final Kline1hRepository kline1hRepository;
    private final Kline2hRepository kline2hRepository;
    private final Kline4hRepository kline4hRepository;
    private final Kline12hRepository kline12hRepository;
    private final Kline1dRepository kline1dRepository;
    private final Kline1wRepository kline1wRepository;
    private final Kline1MRepository kline1MRepository;

    public MongoRepository getKlineRepository(Interval interval) {
        switch (interval) {
            case MINUTE_15:
                return kline15mRepository;
            case MINUTE_30:
                return kline30mRepository;
            case HOUR_1:
                return kline1hRepository;
            case HOUR_2:
                return kline2hRepository;
            case HOUR_4:
                return kline4hRepository;
            case HOUR_12:
                return kline12hRepository;
            case DAY_1:
                return kline1dRepository;
            case WEEK_1:
                return kline1wRepository;
            case MONTH_1:
                return kline1MRepository;
            default:
                throw new RuntimeException("No KlineRecordRepository configured for interval: " + interval);
        }
    }
}
