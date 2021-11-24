package com.insight.io.insight.services;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.repositories.MeetingRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<MeetingDto> getMeetings(String roomName, String uid) {
        return List.of(mockMeeting());
    }

    @Override
    public MeetingDto getMeeting(String mid) {
        return mockMeeting();
//        return meetingRepository.getMeeting(mid);
    }

    private MeetingDto mockMeeting() {
        return MeetingDto.builder().mid(UUID.randomUUID().toString())
                .roomName("Olympia").startTs(1637771722L).endTs(1637775322L)
                .build();
    }
}
