package com.mdavydau.timer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mdavydau
 * @date 2019-03-30
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Response {
    private Integer sec;
    private Long start;
    private Integer count;
    private Integer multiply;
}
