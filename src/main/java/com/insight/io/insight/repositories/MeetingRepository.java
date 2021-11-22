package com.insight.io.insight.repositories;

import com.insight.io.insight.dto.MeetingDto;

import java.util.List;

/**
 *
 * @author Xiantao Ge
 * @since 0.1
 */
public interface MeetingRepository {

    List<MeetingDto> getMeetings();

    MeetingDto getMeeting(String mid);

}
