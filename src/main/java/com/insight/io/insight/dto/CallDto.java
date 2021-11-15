package com.insight.io.insight.dto;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class CallDto {

    private String callId;
    private String roomName;
    private Long startTs;
    private Long endTs;

}
