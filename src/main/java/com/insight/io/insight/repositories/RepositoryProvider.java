package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.mongo.MongoMeetingRepoBuilder;
import com.insight.io.insight.repositories.mongo.MongoUserSessionRepoBuilder;
import com.mongodb.client.MongoClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.insight.io.insight.repositories.RepoType.MONGO;
import static com.insight.io.insight.repositories.RepoType.valueOf;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class RepositoryProvider {

    private static final Map<RepoType, MeetingRepository> meetingRepos =
            new ConcurrentHashMap<>();
    private static final Map<RepoType, UserSessionRepository> userSessionRepos =
            new ConcurrentHashMap<>();

    @Inject
    MongoClient mongoClient;

    public MeetingRepository getMeetingRepo(SourceConfig config) {
        initRepo(config);
        return meetingRepos.get(valueOf(config.getType().toUpperCase()));
    }

    public UserSessionRepository getUserSessionRepo(SourceConfig config) {
        initRepo(config);
        return userSessionRepos.get(valueOf(config.getType().toUpperCase()));
    }

    private void initRepo(SourceConfig config) {
        RepoType repoType = valueOf(config.getType().toUpperCase());
        if (meetingRepos.containsKey(repoType)) {
            return;
        }
        switch (repoType) {
            case MONGO:
                initMongoRepo(config);
            case REDSHIFT:
            default:
        }
    }

    private void initMongoRepo(SourceConfig config) {
        meetingRepos.putIfAbsent(MONGO,
                new MongoMeetingRepoBuilder(mongoClient).withConfig(config)
                        .build());
        userSessionRepos.putIfAbsent(MONGO,
                new MongoUserSessionRepoBuilder(mongoClient).withConfig(config)
                        .build());
    }

}
