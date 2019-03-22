package com.mdavydau.timer;

import com.mdavydau.timer.scheduler.CounterScheduler;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author mdavydau
 * @date 2019-03-21
 */
@RestController("time")
public class TimeController {

    private Logger logger = LoggerFactory.getLogger(TimeController.class);

    @RequestMapping()
    public String index(HttpSession session) {

        String sessionId = session.getId();

        logger.info("Session {}", sessionId);

        if (StringUtils.isBlank(sessionId)) sessionId = UUID.randomUUID().toString();

        long now = DateTime.now().getMillis();
        CounterScheduler.getDisturbMap().putIfAbsent(sessionId, now);
        return String.valueOf(CounterScheduler.getCount());
    }

}
