package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class PeerTrack {

    private String trackId;
    private Long ssrc;
    private String mediaType;
    private InboundTrack inboundTrack;
    private OutboundTrack outboundTrack;
    private RemoteInboundTrack remoteInboundTrack;


}
