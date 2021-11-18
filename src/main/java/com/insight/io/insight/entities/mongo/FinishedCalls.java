package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class FinishedCalls {

    private String callUUID;
    private String callName;
    private Long timestamp;

}
