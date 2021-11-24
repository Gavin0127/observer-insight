package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
@Schema(description = "the webRTC meeting")
public class MeetingDto {

    @Schema(description = "the meeting id")
    private String mid;
    @Schema(description = "the meeting room name")
    private String roomName;
    @Schema(description = "the meeting start timestamp")
    private Long startTs;
    @Schema(description = "the meeting end timestamp")
    private Long endTs;
    @Schema(description = "the meeting user sessions")
    private List<UserSessionDto> userSessions;

}
