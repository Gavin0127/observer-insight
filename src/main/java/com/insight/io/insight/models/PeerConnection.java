package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class PeerConnection {
    private String peerConnectionUUID;
    private String sid;
    private Long startTs;
    private Long endTs;
    private List<PeerTrack> peerTracks;



}
