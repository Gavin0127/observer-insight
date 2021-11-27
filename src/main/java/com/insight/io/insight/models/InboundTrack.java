package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class InboundTrack {

    private String trackId;
    private Integer ssrc;
    private String peerConnectionUUID;
    private String roomName;
    private String uid;
    private Integer mediaType;
    private List<InboundSample> inboundSamples;

    @Data
    @Builder
    public static class InboundSample {

        private Long ts;
        private Integer frameDecoded;
        private Integer keyFrameDecoded;
        private Integer packetsReceived;
        private Integer packetsLost;
        private Integer qpSum;
        private Integer bytesReceived;
        private Integer firCount;
        private Integer jitter;
        private Integer frameReceived;
        private Integer frameDropped;
        private Integer frameWidth;
        private Integer frameHeight;
        private Integer framePerSecond;
        private Integer jitterBufferDelay;
        private Integer width;
        private Integer height;
        private Integer audioLevel;
        private Integer totalAudioEnergy;
        private Integer totalSamplesDuration;

    }

}
