//package com.insight.io.insight.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.List;
//
///**
// * @author Xiantao Ge
// * @since 0.1
// */
//@Data
//@Builder
//@Schema(description = "the inbound track info")
//public class InboundTrackDto {
//
////    @Schema(description = "the trackId")
////    private String trackId;
////    @Schema(description = "the ssrc")
////    private Integer ssrc;
////    @Schema(description = "the remote userId")
////    private String uid;
////    @Schema(description = "the mediaType: 0 - audio, 1 - video, 2 - screen " +
////            "sharing")
////    private Integer mediaType;
////    @Schema(description = "the inbound RTP samples")
////    private List<InboundSampleDto> inboundSamples;
////
////
////    @Data
////    @Builder
////    @Schema(description = "the inbound samples")
////    public static class InboundSampleDto {
////
////        @Schema(description = "the timestamp")
////        private Long ts;
////        @Schema(description = "the frame decoded")
////        private Integer frameDecoded;
////        @Schema(description = "the key frame decoded")
////        private Integer keyFrameDecoded;
////        @Schema(description = "the packets received")
////        private Integer packetsReceived;
////        @Schema(description = "the packets lost")
////        private Integer packetsLost;
////        @Schema(description = "the qp sum")
////        private Integer qpSum;
////        @Schema(description = "the bytes received")
////        private Integer bytesReceived;
////        @Schema(description = "the fir count")
////        private Integer firCount;
////        @Schema(description = "the jitter")
////        private Integer jitter;
////        @Schema(description = "the frame received")
////        private Integer frameReceived;
////        @Schema(description = "the frame dropped")
////        private Integer frameDropped;
////        @Schema(description = "the frame width")
////        private Integer frameWidth;
////        @Schema(description = "the frame height")
////        private Integer frameHeight;
////        @Schema(description = "the frame per second")
////        private Integer framePerSecond;
////        @Schema(description = "the jitter buffer delay")
////        private Integer jitterBufferDelay;
////        @Schema(description = "the video width")
////        private Integer width;
////        @Schema(description = "the video height")
////        private Integer height;
////        @Schema(description = "the audio level")
////        private Integer audioLevel;
////        @Schema(description = "the total audio energy")
////        private Integer totalAudioEnergy;
////        @Schema(description = "the total samples duration")
////        private Integer totalSamplesDuration;
////
////    }
//}
