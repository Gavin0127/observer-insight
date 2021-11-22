package com.insight.io.insight.services;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.repositories.MeetingRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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

    @Override
    public List<MeetingDto> getMeetings() {
        return new ArrayList<>();
    }

    @Override
    public MeetingDto getMeeting(String mid) {
        return meetingRepository.getMeeting(mid);
    }

}
