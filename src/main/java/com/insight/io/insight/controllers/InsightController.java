package com.insight.io.insight.controllers;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.services.InsightService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;

/**
 *
 * @author Xiantao Ge
 * @since 0.1
 */
@Controller("/insight")
@Slf4j
public class InsightController {

    @Inject
    private InsightService insightService;

    @Get
    public String hello() {
        log.info("get access hello");
        var a = "hello";
        val b = "sdfs";
        log.info(a);
        log.info(b);
        return "Hello World";
    }

    @Get("/meetings/{mid}")
    public List<MeetingDto> getMeetings(String mid) {
        return insightService.getMeetings(mid);
    }

}
