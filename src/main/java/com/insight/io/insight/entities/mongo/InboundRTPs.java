package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class InboundRTPs {

    private Long ssrc;
    private String peerConnectionUUID;
    private String trackId;
    private String transportId;
    private Integer framesDecoded;
    private Integer keyFramesDecoded;
    private Integer packetsReceived;
    private Integer packetsLost;
    private Integer qpSum;
    private Integer bytesReceived;
    private Integer firCount;
    private Double jitter;
    private String mediaType;
    private Long timestamp;
    private Long lastPacketReceivedTimestamp;
    private Integer pliCount;
}
