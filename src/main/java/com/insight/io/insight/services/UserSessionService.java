package com.insight.io.insight.services;

import com.insight.io.insight.dto.UserSessionDto;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface UserSessionService {

    UserSessionDto getUserSession(String sid);

}
