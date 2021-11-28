package com.insight.io.insight.services;

import com.insight.io.insight.models.UserSession;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface UserSessionService {

    UserSession getUserSession(String sid);

}
