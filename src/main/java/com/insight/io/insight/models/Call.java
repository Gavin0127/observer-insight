package com.insight.io.insight.models;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class Call {

    private String callId;
    private String roomName;
    private Long startTs;
    private Long endTs;

}
