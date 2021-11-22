package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Schema(description = "a webRTC meeting")
public class UserSessionDto {

    @Schema(description = "the user session id")
    private String sid;
    @Schema(description = "the meeting id")
    private String mid;
    @Schema(description = "the user id")
    private String uid;
    @Schema(description = "the user os type")
    private Integer osType;
    @Schema(description = "the user os name")
    private String osName;
    @Schema(description = "the user platform type")
    private Integer platformType;
    @Schema(description = "the user platform ver")
    private String platformVer;
    @Schema(description = "the meeting start timestamp")
    private Long startTs;
    @Schema(description = "the meeting end timestamp")
    private Long endTs;
    @Schema(description = "the inbound RTP samples")
    private List<InboundRTPDto> inboundRTPs;
    @Schema(description = "the outbound RTP samples")
    private List<OutboundRTPDto> outboundRTPs;
    @Schema(description = "the user events")
    private List<EventDto> events;

}
