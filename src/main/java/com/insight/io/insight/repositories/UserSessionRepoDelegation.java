package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.models.UserSession;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class UserSessionRepoDelegation implements UserSessionRepository {

    private UserSessionRepository repoDelegation;

    public UserSessionRepoDelegation(RepositoryProvider repositoryProvider,
            SourceConfig sourceConfig) {
        this.repoDelegation =
                repositoryProvider.getUserSessionRepo(sourceConfig);
    }

    @Override
    public List<UserSession> getUserSessions(String mid) {
        return repoDelegation.getUserSessions(mid);
    }

    @Override
    public UserSession getUserSession(String sid) {
        return repoDelegation.getUserSession(sid);
    }
}
