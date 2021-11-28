package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.mongo.MongoEventRepoBuilder;
import com.insight.io.insight.repositories.mongo.MongoMeetingRepoBuilder;
import com.insight.io.insight.repositories.mongo.MongoPeerConnectionRepoBuilder;
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
    private static final Map<RepoType, PeerConnectionRepository> peerConnRepos =
            new ConcurrentHashMap<>();
    private static final Map<RepoType, EventRepository> eventRepos =
            new ConcurrentHashMap<>();

    @Inject
    MongoClient mongoClient;

    public MeetingRepository getMeetingRepo(SourceConfig config) {
        initRepo(config);
        return meetingRepos.get(valueOf(config.getType().toUpperCase()));
    }

    public PeerConnectionRepository getPeerConnRepo(SourceConfig config) {
        initRepo(config);
        return peerConnRepos.get(valueOf(config.getType().toUpperCase()));
    }

    public EventRepository getEventRepo(SourceConfig config) {
        initRepo(config);
        return eventRepos.get(valueOf(config.getType().toUpperCase()));
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
        peerConnRepos.putIfAbsent(MONGO,
                new MongoPeerConnectionRepoBuilder(mongoClient).withConfig(config)
                        .build());
        eventRepos.putIfAbsent(MONGO,
                new MongoEventRepoBuilder(mongoClient).withConfig(config)
                        .build());
    }

}
