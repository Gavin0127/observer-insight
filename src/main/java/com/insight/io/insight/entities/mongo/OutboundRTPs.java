package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class OutboundRTPs {

    private Long ssrc;
    private String peerConnectionUUID;
    private String userId;
    private String callName;
    private String trackID;
    private String transportId;
    private String mediaType;
    private String mediaSourceID;
    private Long timestamp;
    private String qualityLimitationReason;
    private Integer framesEncoded;
    private Integer keyFramesEncoded;
    private Integer bytesSent;
    private Integer retransmittedBytesSent;
    private Integer totalEncodedBytesTarget;
    private Integer qpSum;
    private Integer firCount;
    private Integer pliCount;

}
