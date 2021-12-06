package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class RemoteInboundRTPs {

    private Long ssrc;
    private String peerConnectionUUID;
    private String callName;
    private String transportID;
    private String localID;
    private String mediaType;
    private Long timestamp;
    private Double jitter;
    private Double roundTripTime;
    private Integer packetsLost;

}
