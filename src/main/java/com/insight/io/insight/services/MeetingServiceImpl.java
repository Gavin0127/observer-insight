package com.insight.io.insight.services;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.repositories.MeetingRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    private MeetingRepository meetingRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public static MeetingDto mockMeeting(String mid) {
        return MeetingDto.builder().mid(mid).roomName("Olympia")
                .startTs(1637771722L).endTs(1637775322L)
                .userSessions(List.of(UserSessionServiceImpl.mockUserSession()))
                .build();
    }

    @Override
    public List<MeetingDto> getMeetings(String roomName, String uid) {
        return List.of(mockMeeting("meetingUUID_1123"));
    }

    @Override
    public MeetingDto getMeeting(String mid) {
        return mockMeeting(mid);
        //        return meetingRepository.getMeeting(mid);
    }
}
