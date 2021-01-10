package io.crypto.marketalerts.service;

import io.crypto.marketalerts.factory.TokenRecordRepositoryFactory;
import io.crypto.marketalerts.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRecordRepositoryService {

    private final TokenRecordRepositoryFactory tokenRecordRepositoryFactory;

    public void saveTokenRecord(String interval, String symbol, MacdData macd, RsiData rsi, EmaData ema) {
        MongoRepository tokenRecordRepository = tokenRecordRepositoryFactory.getTokenRecordRepository(interval);
        if ("4h".equals(interval)) {
            TokenRecord4h tokenRecord4h = TokenRecord4h.builder()
                    .id(symbol)
                    .macd(macd)
                    .rsi(rsi)
                    .ema(ema)
                    .build();
            tokenRecordRepository.save(tokenRecord4h);
        } else if ("12h".equals(interval)) {
            TokenRecord12h tokenRecord12h = TokenRecord12h.builder()
                    .id(symbol)
                    .macd(macd)
                    .rsi(rsi)
                    .ema(ema)
                    .build();
            tokenRecordRepository.save(tokenRecord12h);
        } else {
            throw new RuntimeException("No TokenRecord configured for interval: " + interval);
        }

    }

}
