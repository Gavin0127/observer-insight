package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Schema(description = "the user media track info")
public class OutboundRTPDto {

    @Schema(description = "the ssrc")
    private Integer ssrc;
    @Schema(description = "the frame encoded")
    private Integer frameEncoded;
    @Schema(description = "the key frame encoded")
    private Integer keyFrameEncoded;
    @Schema(description = "the frame width")
    private Integer frameWidth;
    @Schema(description = "the frame height")
    private Integer frameHeight;
    @Schema(description = "the target bit rate")
    private Integer targetBitrate;
    @Schema(description = "the frame received")
    private Integer frameReceived;
    @Schema(description = "the frame per second")
    private Integer framePerSecond;
    @Schema(description = "the sum of queries per second")
    private Integer qpSum;
    @Schema(description = "the bytes sent")
    private Integer bytesSent;
    @Schema(description = "the fir count")
    private Integer firCount;
    @Schema(description = "the total encoded bytes target")
    private Integer totalEncodedBytesTarget;
    @Schema(description = "the retransmitted bytes sent")
    private Integer retransmittedBytesSent;


}
