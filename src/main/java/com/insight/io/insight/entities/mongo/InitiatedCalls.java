package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class InitiatedCalls {
    private String callUUID;
    private String callName;
    private String type;
    private Long timestamp;
}
