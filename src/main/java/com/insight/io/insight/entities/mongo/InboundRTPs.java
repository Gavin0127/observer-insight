package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class InboundRTPs {

    private String ssrc;
    private String peerConnectionUUID;
    private String trackId;
    private String transportId;
    private Integer bytesReceived;
    private String mediaType;
    private Long timestamp;
    private Integer qpSum;
    private Long lastPacketReceivedTimestamp;
    private Double jitter;
    private Integer firCount;
    private Integer pliCount;
}
