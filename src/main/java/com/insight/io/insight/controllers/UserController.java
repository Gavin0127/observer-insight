package com.insight.io.insight.controllers;

import com.insight.io.insight.models.UserSession;
import com.insight.io.insight.services.UserSessionService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Tag(name = "user-session-api")
@Slf4j
@Controller("/user-sessions")
public class UserController {

    @Inject
    private UserSessionService userSessionService;

    @Get("/{sid}")
    public UserSession getUserSession(
            @Parameter(description = "the user session id") String sid) {
        UserSession userSession = userSessionService.getUserSession(sid);
        return userSession;
    }

}
