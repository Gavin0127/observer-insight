package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
@Schema(description = "the outbound track info")
public class OutboundTrackDto {

    @Schema(description = "the trackId")
    private String trackId;
    @Schema(description = "the ssrc")
    private Integer ssrc;
    @Schema(description = "the userId")
    private String userId;
    @Schema(description = "the mediaType: 0 - audio, 1 - video, 2 - screen " +
            "sharing")
    private Integer mediaType;
    @Schema(description = "the outbound RTP samples")
    private List<OutboundSampleDto> outboundSamples;

    @Data
    @Builder
    @Schema(description = "the user media track info")
    public static class OutboundSampleDto {

        @Schema(description = "the timestamp")
        private Long ts;
        @Schema(description = "the frame encoded")
        private Integer frameEncoded;
        @Schema(description = "the key frame encoded")
        private Integer keyFrameEncoded;
        @Schema(description = "the retransmitted bytes sent")
        private Integer retransmittedBytesSent;
        @Schema(description = "the total encoded bytes target")
        private Integer totalEncodedBytesTarget;
        @Schema(description = "the qp sum")
        private Integer qpSum;
        @Schema(description = "the fir count")
        private Integer firCount;
        @Schema(description = "the bytes sent")
        private Integer bytesSent;
        @Schema(description = "the frame width")
        private Integer frameWidth;
        @Schema(description = "the frame height")
        private Integer frameHeight;
        @Schema(description = "the huge frames sent")
        private Integer hugeFramesSent;
        @Schema(description = "the frame per second")
        private Integer framePerSecond;


    }

}
