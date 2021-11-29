package com.insight.io.insight.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class Meeting {

    private String mid;
    private String roomName;
    private Long startTs;
    private Long endTs;
    private Set<Long> sids;
    private List<UserSession> userSessions;

}
