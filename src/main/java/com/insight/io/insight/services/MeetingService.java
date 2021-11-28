package com.insight.io.insight.services;

import com.insight.io.insight.models.Meeting;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface MeetingService {

    Meeting getMeeting(String callId);

    List<Meeting> getMeetings(String roomName, String uid);


}
