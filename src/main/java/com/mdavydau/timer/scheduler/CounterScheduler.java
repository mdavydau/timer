package com.mdavydau.timer.scheduler;

import com.mdavydau.timer.dao.TimerRepository;
import com.mdavydau.timer.model.Timer;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author mdavydau
 * @date 2019-03-22
 */
@Component
public class CounterScheduler {

    private Logger logger = LoggerFactory.getLogger(CounterScheduler.class);

    private final TimerRepository timerRepository;

    private static Map<String, Long> disturbMap = new HashMap<>();
    private static Integer sec = 0;
    private static Integer count = 0;
    private static Long start = 0L;

    private static boolean check = false;

    private static final String ID = "timer_init";

    @Autowired
    public CounterScheduler(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }


    @Scheduled(fixedDelay = 1000)
    public void count() {

        //get from mongo
        if (!check) {
            init();
        }

        int size = disturbMap.size();
        logger.debug("Counter Cron millis {} map size {}", DateTime.now().getMillis(), size);
        sec += size;
        logger.debug("Counter Cron sec {}", sec);

        //save to mongo
        save();
    }

    private void init() {
        Optional<Timer> byId = timerRepository.findById(ID);

        byId.map(timer -> {
            setCount(timer.getCount());
            setSec(timer.getSec());
            setStart(timer.getStart());

            return timer;
        });

        check = true;
    }

    private void save() {
        Timer timer = new Timer();
        timer.setId(ID);
        timer.setCount(getCount());
        timer.setSec(getSec());
        timer.setStart(getStart());

        timerRepository.save(timer);
    }

    public static Map<String, Long> getDisturbMap() {
        return disturbMap;
    }

    public static Integer getSec() {
        return sec;
    }

    public static Long getStart() {
        return start;
    }

    private static void setStart(Long start) {
        CounterScheduler.start = start;
    }

    public static Integer getCount() {
        return count;
    }

    public static void setCount(Integer count) {
        CounterScheduler.count = count;
    }

    private static void setSec(Integer sec) {
        CounterScheduler.sec = sec;
    }
}
