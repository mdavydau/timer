package com.mdavydau.timer.scheduler;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mdavydau
 * @date 2019-03-22
 */
@Component
public class CounterScheduler {

    private Logger logger = LoggerFactory.getLogger(CounterScheduler.class);

    private static Map<String, Long> disturbMap = new HashMap<>();
    private static Integer count = 0;

    @Scheduled(fixedDelay = 1000)
    public void count() {
        int size = disturbMap.size();
        logger.info("Counter Cron millis {} map size {}", DateTime.now().getMillis(), size);
        count += size;
        logger.info("Counter Cron count {}", count);
    }

    public static Map<String, Long> getDisturbMap() {
        return disturbMap;
    }

    public static Integer getCount() {
        return count;
    }
}
