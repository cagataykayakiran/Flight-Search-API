package com.amadeus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataScheduler {

    private final DataService dataService;

    @Scheduled(cron = "0 44 16 * * ?")
    public void scheduledTask() {
        dataService.importFromMockApi();
        log.info("Success data import from API");
    }
}
