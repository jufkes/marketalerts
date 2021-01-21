package io.crypto.marketalerts.service;

import io.crypto.marketalerts.factory.TokenRecordRepositoryFactory;
import io.crypto.marketalerts.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TokenRecordRepositoryService {

    private final TokenRecordRepositoryFactory tokenRecordRepositoryFactory;

    public void updateTokenRecord(Interval interval, TokenRecord tokenRecord) {
        MongoRepository tokenRecordRepository = tokenRecordRepositoryFactory.getTokenRecordRepository(interval);
        switch (interval) {
            case MINUTE_15:
                TokenRecord15m tokenRecord15m = (TokenRecord15m) tokenRecord;
                tokenRecordRepository.save(tokenRecord15m);
                break;
            case MINUTE_30:
                TokenRecord30m tokenRecord30m = (TokenRecord30m) tokenRecord;
                tokenRecordRepository.save(tokenRecord30m);
                break;
            case HOUR_1:
                TokenRecord1h tokenRecord1h = (TokenRecord1h) tokenRecord;
                tokenRecordRepository.save(tokenRecord1h);
                break;
            case HOUR_2:
                TokenRecord2h tokenRecord2h = (TokenRecord2h) tokenRecord;
                tokenRecordRepository.save(tokenRecord2h);
                break;
            case HOUR_4:
                TokenRecord4h tokenRecord4h = (TokenRecord4h) tokenRecord;
                tokenRecordRepository.save(tokenRecord4h);
                break;
            case HOUR_12:
                TokenRecord12h tokenRecord12h = (TokenRecord12h) tokenRecord;
                tokenRecordRepository.save(tokenRecord12h);
                break;
            case DAY_1:
                TokenRecord1d tokenRecord1d = (TokenRecord1d) tokenRecord;
                tokenRecordRepository.save(tokenRecord1d);
                break;
            case WEEK_1:
                TokenRecord1w tokenRecord1w = (TokenRecord1w) tokenRecord;
                tokenRecordRepository.save(tokenRecord1w);
                break;
            case MONTH_1:
                TokenRecord1M tokenRecord1M = (TokenRecord1M) tokenRecord;
                tokenRecordRepository.save(tokenRecord1M);
                break;
            default:
                throw new RuntimeException("No TokenRecord configured for interval: " + interval);
        }
    }

    public void saveTokenRecord(Interval interval, String symbol, MacdData macd, RsiData rsi, EmaData ema) {
        MongoRepository tokenRecordRepository = tokenRecordRepositoryFactory.getTokenRecordRepository(interval);
        switch (interval) {
            case MINUTE_15:
                TokenRecord15m tokenRecord15m = TokenRecord15m.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord15m);
                break;
            case MINUTE_30:
                TokenRecord30m tokenRecord30m = TokenRecord30m.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord30m);
                break;
            case HOUR_1:
                TokenRecord1h tokenRecord1h = TokenRecord1h.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord1h);
                break;
            case HOUR_2:
                TokenRecord2h tokenRecord2h = TokenRecord2h.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord2h);
                break;
            case HOUR_4:
                TokenRecord4h tokenRecord4h = TokenRecord4h.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord4h);
                break;
            case HOUR_12:
                TokenRecord12h tokenRecord12h = TokenRecord12h.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord12h);
                break;
            case DAY_1:
                TokenRecord1d tokenRecord1d = TokenRecord1d.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord1d);
                break;
            case WEEK_1:
                TokenRecord1w tokenRecord1w = TokenRecord1w.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord1w);
                break;
            case MONTH_1:
                TokenRecord1M tokenRecord1M = TokenRecord1M.builder()
                        .id(symbol)
                        .macd(macd)
                        .rsi(rsi)
                        .ema(ema)
                        .build();
                tokenRecordRepository.save(tokenRecord1M);
                break;
            default:
                throw new RuntimeException("No TokenRecord configured for interval: " + interval);
        }

    }

    public List<EmaScanner> getEmas() {
        Map<String, EmaScanner> scannerMap = new HashMap<>();
        List.of(Interval.values()).forEach(interval -> {
            MongoRepository tokenRecordRepository = tokenRecordRepositoryFactory.getTokenRecordRepository(interval);
            List<TokenRecord> records = tokenRecordRepository.findAll();
            updateScannerMap(interval, scannerMap, records);
        });

        return new ArrayList<>(scannerMap.values());
    }

    public TokenRecord getTokenRecord(Interval interval, String symbol) {
        MongoRepository tokenRecordRepository = tokenRecordRepositoryFactory.getTokenRecordRepository(interval);
        Optional<TokenRecord> tokenRecord = tokenRecordRepository.findById(symbol);
        return tokenRecord.orElse(null);
    }

    public void saveTokenRecord(Interval interval, TokenRecord record) {
        MongoRepository tokenRecordRepository = tokenRecordRepositoryFactory.getTokenRecordRepository(interval);
        tokenRecordRepository.save(record);
    }

    private Map<String, EmaScanner> updateScannerMap(Interval interval, Map<String, EmaScanner> scannerMap, List<TokenRecord> records) {
        switch (interval) {
            case MINUTE_15:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setMinute15(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case MINUTE_30:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setMinute30(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case HOUR_1:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setHour1(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case HOUR_2:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setHour2(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case HOUR_4:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setHour4(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case HOUR_12:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setHour12(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case DAY_1:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setDay1(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case WEEK_1:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setWeek1(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            case MONTH_1:
                records.forEach(record -> {
                    EmaScanner emaScanner = scannerMap.get(record.getId());
                    if (emaScanner == null) {
                        emaScanner = EmaScanner.builder().symbol(record.getId()).build();
                    }
                    emaScanner.setMonth1(record.getEma().getDirection());
                    scannerMap.put(record.getId(), emaScanner);
                });
                break;
            default:
                throw new RuntimeException("No TokenRecord configured for interval: " + interval);

        }
        return scannerMap;
    }

}
