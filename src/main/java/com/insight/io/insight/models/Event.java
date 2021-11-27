package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class Event {

    private Integer uri;
    private Long ts;
    private String content;

}
