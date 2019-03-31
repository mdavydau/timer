package com.mdavydau.timer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author mdavydau
 * @date 2019-03-30
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Timer {
    @Id
    public String id;
    private Integer sec;
    private Long start;
    private Integer count;
}
