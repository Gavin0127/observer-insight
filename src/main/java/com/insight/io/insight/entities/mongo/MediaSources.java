package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class MediaSources {

    private String peerConnectionUUID;
    private String mediaSourceID;
    private String trackId;
    private Long timestamp;
    private String mediaType;
    private Double audioLevel;
    private Integer width;
    private Integer height;
    private Integer framesPerSecond;

}
