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
@Schema(description = "a webRTC meeting")
@Builder
public class UserSessionDto {

    @Schema(description = "the session id")
    private String sid;
    @Schema(description = "the meeting room name")
    private String roomName;
    @Schema(description = "the user id")
    private String uid;
    @Schema(description = "the user os name")
    private String osName;
    @Schema(description = "the user os version")
    private String osVersion;
    @Schema(description = "the user browser name")
    private String browserName;
    @Schema(description = "the user browser version")
    private String browserVersion;
    @Schema(description = "the user browser engine name")
    private String engineName;
    @Schema(description = "the user browser engine version")
    private String engineVersion;
    @Schema(description = "the user platform vendor")
    private String platformVendor;
    @Schema(description = "the user session start timestamp")
    private Long startTs;
    @Schema(description = "the user session end timestamp")
    private Long endTs;
    @Schema(description = "the user inbound tracks")
    private List<InboundTrackDto> inboundTracks;
    @Schema(description = "the user outbound tracks")
    private List<OutboundTrackDto> outboundTracks;
    @Schema(description = "the user events")
    private List<EventDto> events;

}
