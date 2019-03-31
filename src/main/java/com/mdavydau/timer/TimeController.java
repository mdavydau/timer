package com.mdavydau.timer;

import com.mdavydau.timer.model.Response;
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
    public Response index(HttpSession session) {

        String sessionId = session.getId();

        logger.debug("Session {}", sessionId);

        if (StringUtils.isBlank(sessionId)) sessionId = UUID.randomUUID().toString();

        long now = DateTime.now().getMillis();
        Long ifAbsent = CounterScheduler.getDisturbMap().putIfAbsent(sessionId, now);

        if (ifAbsent == null) {
            //new user
            CounterScheduler.setCount(CounterScheduler.getCount() + 1);
        }

        Response response = new Response();
        response.setCount(CounterScheduler.getCount());
        response.setSec(CounterScheduler.getSec());
        response.setStart(CounterScheduler.getStart());

        return response;
    }

}
