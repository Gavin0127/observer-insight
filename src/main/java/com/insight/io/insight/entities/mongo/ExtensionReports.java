package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class ExtensionReports {

    private String peerConnectionUUID;
    private String callName;
    private Long timestamp;
    private String extensionType;
    private String payload;

}
