package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class OutboundTrack {

    private String trackId;
    private Integer ssrc;
    private String peerConnectionUUID;
    private String roomName;
    private Integer mediaType;
    private List<OutboundSample> outboundSamples;

    @Data
    @Builder
    public static class OutboundSample {

        private Long ts;
        private Integer frameEncoded;
        private Integer keyFrameEncoded;
        private Integer retransmittedBytesSent;
        private Integer totalEncodedBytesTarget;
        private Integer qpSum;
        private Integer firCount;
        private Integer bytesSent;
        private Integer frameWidth;
        private Integer frameHeight;
        private Integer hugeFramesSent;
        private Integer framePerSecond;

    }

}
