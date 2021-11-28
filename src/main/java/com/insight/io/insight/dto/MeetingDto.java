//package com.insight.io.insight.dto;
//
//import com.insight.io.insight.models.Meeting;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.List;
//import java.util.Objects;
//
///**
// * @author Xiantao Ge
// * @since 0.1
// */
//@Data
//@Builder
//@Schema(description = "the webRTC meeting")
//public class MeetingDto {
//
//    @Schema(description = "the meeting id")
//    private String mid;
//    @Schema(description = "the meeting room name")
//    private String roomName;
//    @Schema(description = "the meeting start timestamp")
//    private Long startTs;
//    @Schema(description = "the meeting end timestamp, when endTs is null, the" +
//            " meeting is on going")
//    private Long endTs;
//    @Schema(description = "the meeting user sessions")
//    private List<UserSessionDto> userSessions;
//
//    public static MeetingDto of(Meeting meeting) {
//        if (Objects.isNull(meeting)) {
//            return null;
//        }
//        return MeetingDto.builder().mid(meeting.getMid())
//                .roomName(meeting.getRoomName()).startTs(meeting.getStartTs())
//                .endTs(meeting.getEndTs()).build();
//    }
//
//}
