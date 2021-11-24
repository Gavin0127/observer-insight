package com.insight.io.insight.controllers;

import com.insight.io.insight.dto.MeetingDto;
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

    @Get
    public List<MeetingDto> getMeetings(@QueryValue @Nullable String roomName,
            @QueryValue @Nullable String uid) {
        return meetingService.getMeetings(roomName, uid);
    }

    @Get("/{mid}")
    public MeetingDto getMeeting(
            @Parameter(description = "the meeting id") String mid) {
        return meetingService.getMeeting(mid);
    }

}
