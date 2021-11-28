package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class JoinedPeerConnections {

    private String peerConnectionUUID;
    private String callUUID;
    private String userId;
    private String callName;
    private Long timestamp;
    private String marker;

}
