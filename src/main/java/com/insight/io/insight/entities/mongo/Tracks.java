package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class Tracks {

    private String trackId;
    private String peerConnectionUUID;
    private String userId;
    private String callName;
    private String mediaSourceID;
    private Long timestamp;
    private String mediaType;
    private Integer frameWidth;
    private Integer frameHeight;
    private Integer framesReceived;
    private Integer framesDropped;
    private Integer hugeFramesSent;
    private Double jitterBufferDelay;
    private Boolean remoteSource;

}
