package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.mongo.MongoRepoBuilder;
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

    private static final Map<RepoType, MeetingRepository> repoRegistries =
            new ConcurrentHashMap<>();

    @Inject
    MongoClient mongoClient;

    public MeetingRepository getRepository(SourceConfig config) {
        initRepo(config);
        return repoRegistries.get(valueOf(config.getType().toUpperCase()));
    }

    private void initRepo(SourceConfig config) {
        RepoType repoType = valueOf(config.getType().toUpperCase());
        if (repoRegistries.containsKey(repoType)) {
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
        repoRegistries.putIfAbsent(MONGO,
                new MongoRepoBuilder(mongoClient).withConfig(config).build());
    }

}
