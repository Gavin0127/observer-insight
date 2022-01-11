package com.insight.io.insight.controllers;

import com.insight.io.insight.models.Meeting;
import com.insight.io.insight.services.MeetingService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Tag(name = "meeting-api")
@Slf4j
@Controller("/meetings")
public class MeetingController {

    @Inject
    private MeetingService meetingService;

    @Get("/all")
    public List<Meeting> getAllMeetings() {
        return meetingService.getMeetings(null, null);
    }

    @Get
    public List<Meeting> getMeetings(@QueryValue @Nullable String roomName,
            @QueryValue @Nullable String uid) {
        return meetingService.getMeetings(roomName, uid);
    }

    @Get("/{mid}")
    public Meeting getMeeting(
            @Parameter(description = "the meeting id") String mid) {
        log.error("get meeting mid = " + mid);
        return meetingService.getMeeting(mid);
    }

}
