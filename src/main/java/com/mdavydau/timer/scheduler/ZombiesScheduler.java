package com.mdavydau.timer.scheduler;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mdavydau
 * @date 2019-03-22
 */
@Component
public class ZombiesScheduler {

    private Logger logger = LoggerFactory.getLogger(ZombiesScheduler.class);

    @Scheduled(fixedDelay = 10000)
    public void kill() {
        Map<String, Long> disturbMap = CounterScheduler.getDisturbMap();

        long now30 = DateTime.now().minusSeconds(30).getMillis();

        disturbMap.entrySet().removeIf(stringLongEntry -> stringLongEntry.getValue() < now30);

        logger.info("Kill Cron millis {} map size {}", DateTime.now().getMillis(), disturbMap.size());
    }

}
