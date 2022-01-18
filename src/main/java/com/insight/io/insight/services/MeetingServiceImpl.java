package com.insight.io.insight.services;

import com.insight.io.insight.models.Event;
import com.insight.io.insight.models.EventType;
import com.insight.io.insight.models.Meeting;
import com.insight.io.insight.models.UserSession;
import com.insight.io.insight.repositories.EventRepository;
import com.insight.io.insight.repositories.MeetingRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    @Inject
    private MeetingRepository meetingRepository;
    @Inject
    private UserSessionService userSessionService;
    @Inject
    private EventRepository eventRepository;


    @Override
    public List<Meeting> getMeetings(String roomName, String uid) {
        return meetingRepository.getMeetings(roomName, uid).stream()
                .collect(Collectors.toList());
        //        return List.of(mockMeeting("meetingUUID_1123"));
    }

    @Override
    public Meeting getMeeting(String mid) {
        Meeting meeting = meetingRepository.getMeeting(mid);
        List<String> sids = eventRepository.getEventsByMidAndUri(mid,
                        EventType.JOIN.getUri()).stream().map(Event::getSid).distinct()
                .collect(Collectors.toList());
        List<UserSession> userSessions = new ArrayList<>();
        sids.forEach(sid -> {
            UserSession userSession =
                    userSessionService.getUserSession(sid, false);
            if (Objects.nonNull(userSession)) {
                userSessions.add(userSession);
            }
        });
        meeting.setUserSessions(userSessions);
        return meeting;
        //        return mockMeeting(mid);
    }

    //    public static MeetingDto mockMeeting(String mid) {
    //        return MeetingDto.builder().mid(mid).roomName("Olympia")
    //                .startTs(1637771722L).endTs(1637775322L)
    //                .userSessions(List.of(
    //                        UserSessionServiceImpl.mockUserSession()))
    //                .build();
    //    }

}
