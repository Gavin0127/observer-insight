package com.insight.io.insight.repositories;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.models.Meeting;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface MeetingRepository {

    List<Meeting> getMeetings(String roomName, String uid);

    Meeting getMeeting(String mid);

}
