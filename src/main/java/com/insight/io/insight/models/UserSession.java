package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class UserSession {

    private String sid;
    private String mid;
    private String roomName;
    private String uid;
    private Long startTs;
    private Long endTs;
    private ClientInfo clientInfo;
    private List<PeerConnection> peerConnections;
    private List<UserSession> remoteUserSessions;
    private TotalAggrTrack totalAggrTrack;
    private List<Event> events;

    @Data
    @Builder
    public static class ClientInfo {
        private String roomName;
        private String uid;
        private String osName;
        private String osVersion;
        private String browserName;
        private String browserVersion;
        private String engineName;
        private String engineVersion;
        private String platformVendor;
    }


}
