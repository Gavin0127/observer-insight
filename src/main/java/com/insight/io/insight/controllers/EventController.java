package com.insight.io.insight.controllers;

import com.insight.io.insight.dto.EventDto;
import com.insight.io.insight.services.EventService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Tag(name = "receive the event upload")
@Slf4j
@Controller("/events")
public class EventController {

    @Inject
    private EventService meetingService;

    @Post
    public void receiveEvents(@Body EventDto event) {
        if (Objects.nonNull(event)) {
            meetingService.upload(event);
            log.info("receive events" + event.toString());
        }
    }

}
