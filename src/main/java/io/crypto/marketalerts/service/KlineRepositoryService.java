package io.crypto.marketalerts.service;

import io.crypto.marketalerts.factory.KlineRecordRepositoryFactory;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.model.kline.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KlineRepositoryService {

    private final KlineRecordRepositoryFactory klineRecordRepositoryFactory;

    public List<KlineMessage.KlineData> getKlines(String symbol, Interval interval) {
        MongoRepository klineRepository = klineRecordRepositoryFactory.getKlineRepository(interval);
        Optional<KlineRecord> opKlineRecord = klineRepository.findById(symbol);
        if (opKlineRecord.isPresent()) {
            return opKlineRecord.get().getKlines();
        }
        return new ArrayList<>();
    }

    public void upsertKlineData(KlineMessage.KlineData klineData) {
        Interval interval = Interval.valueOfLabel(klineData.getI());

        MongoRepository klineRepository = klineRecordRepositoryFactory.getKlineRepository(interval);
        Optional<KlineRecord> opKline = klineRepository.findById(klineData.getS());
        if (opKline.isPresent()) {
            KlineRecord klineRecord = opKline.get();
            if (klineRecord.getKlines().size() >= 27) {
                // Sort by date descending
                klineRecord.getKlines().sort(Comparator.comparing(KlineMessage.KlineData::getT).reversed());
                // Drop all records after the 26th
                List<KlineMessage.KlineData> truncatedList = klineRecord.getKlines().subList(0, 26);
                klineRecord.setKlines(truncatedList);
            }
            klineRecord.getKlines().add(klineData);
            saveRecord(interval, klineRecord, klineRepository);
        } else {
            KlineRecord record = new KlineRecord(klineData.getS(), List.of(klineData));
            saveRecord(interval, record, klineRepository);
        }
    }

    private void saveRecord(Interval interval, KlineRecord klineRecord, MongoRepository klineRepository) {
        switch (interval) {
            case MINUTE_15:
                KlineRecord15m klineRecord15m = KlineRecord15m.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(klineRecord15m);
                break;
            case MINUTE_30:
                KlineRecord30m tokenRecord30m = KlineRecord30m.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord30m);
                break;
            case HOUR_1:
                KlineRecord1h tokenRecord1h = KlineRecord1h.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord1h);
                break;
            case HOUR_2:
                KlineRecord2h tokenRecord2h = KlineRecord2h.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord2h);
                break;
            case HOUR_4:
                KlineRecord4h tokenRecord4h = KlineRecord4h.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord4h);
                break;
            case HOUR_12:
                KlineRecord12h tokenRecord12h = KlineRecord12h.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord12h);
                break;
            case DAY_1:
                KlineRecord1d tokenRecord1d = KlineRecord1d.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord1d);
                break;
            case WEEK_1:
                KlineRecord1w tokenRecord1w = KlineRecord1w.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord1w);
                break;
            case MONTH_1:
                KlineRecord1M tokenRecord1M = KlineRecord1M.builder().id(klineRecord.getId()).klines(klineRecord.getKlines()).build();
                klineRepository.save(tokenRecord1M);
                break;
            default:
                throw new RuntimeException("No KlineRecord configured for interval: " + interval);
        }
    }
}
