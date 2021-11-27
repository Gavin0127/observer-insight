package com.insight.io.insight.models;

import com.insight.io.insight.entities.mongo.FinishedCalls;
import com.insight.io.insight.entities.mongo.InitiatedCalls;
import lombok.Builder;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class Meeting {

    private String mid;
    private String roomName;
    private Long startTs;
    private Long endTs;

}
