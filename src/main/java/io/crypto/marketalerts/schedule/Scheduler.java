package io.crypto.marketalerts.schedule;

import io.crypto.marketalerts.helper.SymbolHelper;
import io.crypto.marketalerts.model.Interval;
import io.crypto.marketalerts.service.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    @Value("${schedule.enabled:true}")
    private boolean schedulesEnabled;

    private final CalculationService calculationService;

    @Scheduled(cron = "0 0,15,30,45 * * * *")
    public void schedule15Minute() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 15 minute interval");
        calculationService.getDataForAllSymbols(Interval.MINUTE_15);
    }

    @Scheduled(cron = "0 5,35 * * * *")
    public void schedule30Minute() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 30 minute interval");
        calculationService.getDataForAllSymbols(Interval.MINUTE_30);
    }

    @Scheduled(cron = "0 10 * * * *")
    public void schedule1Hour() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 1 hour interval");
        calculationService.getDataForAllSymbols(Interval.HOUR_1);
    }

    @Scheduled(cron = "0 15 0/2 * * *")
    public void schedule2Hour() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 2 hour interval");
        calculationService.getDataForAllSymbols(Interval.HOUR_2);
    }

    @Scheduled(cron = "0 20 0/4 * * *")
    public void schedule4Hour() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 4 hour interval");
        calculationService.getDataForAllSymbols(Interval.HOUR_4);
    }

    @Scheduled(cron = "0 25 0,12 * * *")
    public void schedule12Hour() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 12 hour interval");
        calculationService.getDataForAllSymbols(Interval.HOUR_12);
    }

    @Scheduled(cron = "0 30 0 * * *")
    public void schedule1Day() {
        if (!schedulesEnabled) return;

        log.info("Processing data for 1 day interval");
        calculationService.getDataForAllSymbols(Interval.DAY_1);
    }
}
