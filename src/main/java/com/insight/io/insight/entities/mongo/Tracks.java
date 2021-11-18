package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class Tracks {

    private String trackId;
    private String peerConnectionUUID;
    private String mediaSourceID;
    private Long timestamp;
    private String mediaType;

}
