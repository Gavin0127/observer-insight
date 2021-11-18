package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class DetachedPeerConnections {

    private String peerConnectionUUID;
    private String callUUID;
    private Long timestamp;

}
