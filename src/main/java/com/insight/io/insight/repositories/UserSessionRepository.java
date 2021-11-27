package com.insight.io.insight.repositories;

import com.insight.io.insight.models.UserSession;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface UserSessionRepository {

    List<UserSession> getUserSessions(String mid);

    UserSession getUserSession(String sid);

}
