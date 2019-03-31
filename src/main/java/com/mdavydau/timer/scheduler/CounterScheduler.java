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
    private static Integer sec = 2220;
    private static Integer count = 64;

    @Scheduled(fixedDelay = 1000)
    public void count() {
        int size = disturbMap.size();
        logger.debug("Counter Cron millis {} map size {}", DateTime.now().getMillis(), size);
        sec += size;
        logger.debug("Counter Cron sec {}", sec);
    }

    public static Map<String, Long> getDisturbMap() {
        return disturbMap;
    }

    public static Integer getSec() {
        return sec;
    }

    public static Long getStart() {
        return 1553968800000L;
    }

    public static Integer getCount() {
        return count;
    }

    public static void setCount(Integer count) {
        CounterScheduler.count = count;
    }
}
