package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
@Schema(description = "the user media track info")
public class InboundRTPDto {

    @Schema(description = "the ssrc")
    private Integer ssrc;
    @Schema(description = "the timestamp")
    private Long timestamp;
    @Schema(description = "the frame decoded")
    private Integer frameDecoded;
    @Schema(description = "the key frame decoded")
    private Integer keyFrameDecoded;
    @Schema(description = "the frame width")
    private Integer frameWidth;
    @Schema(description = "the frame height")
    private Integer frameHeight;
    @Schema(description = "the frame received")
    private Integer frameReceived;
    @Schema(description = "the frame per second")
    private Integer framePerSecond;
    @Schema(description = "the qp sum")
    private Integer qpSum;
    @Schema(description = "the bytes received")
    private Integer bytesReceived;
    @Schema(description = "the fir count")
    private Integer firCount;
    @Schema(description = "the jitter buffer delay")
    private Integer jitterBufferDelay;
    @Schema(description = "the audio level")
    private Integer audioLevel;
    @Schema(description = "the total audio energy")
    private Integer totalAudioEnergy;

}
