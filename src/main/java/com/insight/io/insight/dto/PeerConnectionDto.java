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
//@Schema(description = "a webRTC peerConnection")
//@Builder
//public class PeerConnectionDto {
//
//    @Schema(description = "the peer connection UUID")
//    private String peerConnectionUUID;
//    @Schema(description = "the user id")
//    private String uid;
//    @Schema(description = "the user session start timestamp")
//    private Long startTs;
//    @Schema(description = "the user session end timestamp")
//    private Long endTs;
//    @Schema(description = "the user inbound tracks")
//    private List<InboundTrackDto> inboundTracks;
//    @Schema(description = "the user outbound tracks")
//    private List<OutboundTrackDto> outboundTracks;
//
//}
