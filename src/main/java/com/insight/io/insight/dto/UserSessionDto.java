//package com.insight.io.insight.dto;
//
//import com.insight.io.insight.models.UserSession;
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
//@Schema(description = "a webRTC user session")
//@Builder
//public class UserSessionDto {
//
//    @Schema(description = "the session id")
//    private String sid;
//    @Schema(description = "the meeting room name")
//    private String roomName;
//    @Schema(description = "the user id")
//    private String uid;
//    @Schema(description = "the user os name")
//    private String osName;
//    @Schema(description = "the user os version")
//    private String osVersion;
//    @Schema(description = "the user browser name")
//    private String browserName;
//    @Schema(description = "the user browser version")
//    private String browserVersion;
//    @Schema(description = "the user browser engine name")
//    private String engineName;
//    @Schema(description = "the user browser engine version")
//    private String engineVersion;
//    @Schema(description = "the user platform vendor")
//    private String platformVendor;
//    @Schema(description = "the user session start timestamp")
//    private Long startTs;
//    @Schema(description = "the user session end timestamp")
//    private Long endTs;
//    @Schema(description = "the peer connections")
//    private List<PeerConnectionDto> peerConnections;
//    @Schema(description = "the user events")
//    private List<EventDto> events;
//
//    public static UserSessionDto of(UserSession session) {
//        return UserSessionDto.builder().build();
//
//    }
//
//}
