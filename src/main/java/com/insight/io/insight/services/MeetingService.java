package com.insight.io.insight.services;

import com.insight.io.insight.dto.MeetingDto;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface MeetingService {

    MeetingDto getMeeting(String callId);

    List<MeetingDto> getMeetings();


}
