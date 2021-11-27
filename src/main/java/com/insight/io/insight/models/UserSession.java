package com.insight.io.insight.models;

import com.insight.io.insight.dto.EventDto;
import com.insight.io.insight.dto.InboundTrackDto;
import com.insight.io.insight.dto.OutboundTrackDto;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class UserSession {

    private String sid;
    private String roomName;
    private String uid;
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
    private String engineName;
    private String engineVersion;
    private String platformVendor;
    private Long startTs;
    private Long endTs;
    private List<InboundTrackDto> inboundTracks;
    private List<OutboundTrackDto> outboundTracks;
    private List<EventDto> events;

}
