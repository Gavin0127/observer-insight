package com.insight.io.insight.entities.mongo;

import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class ClientDetailss {

    private String peerConnectionUUID;
    private String platformVendor;
    private String platformType;
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;


}
