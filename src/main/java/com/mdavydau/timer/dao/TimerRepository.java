package com.mdavydau.timer.dao;

import com.mdavydau.timer.model.Timer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mdavydau
 * @date 2019-03-31
 */
public interface TimerRepository extends MongoRepository<Timer, String> {
}
