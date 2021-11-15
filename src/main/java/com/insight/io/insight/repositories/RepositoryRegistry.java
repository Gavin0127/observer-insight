package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.mongo.MongoRepository;
import com.mongodb.client.MongoClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

import static com.insight.io.insight.repositories.RepoType.MONGO;
import static com.insight.io.insight.repositories.RepoType.valueOf;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
public class RepositoryRegistry {

    private static final Map<RepoType, InsightRepository> repoRegistries =
            new HashMap<>();

    @Inject
    MongoClient mongoClient;

    public InsightRepository getRepository(SourceConfig config) {
        initRepo(config);
        return repoRegistries.get(valueOf(config.getType().toUpperCase()));
    }

    public void initRepo(SourceConfig config) {
        switch (RepoType.valueOf(config.getType().toUpperCase())) {
            case MONGO:
                initMongoRepo(config);
            case REDSHIFT:
            default:
        }
    }

    private void initMongoRepo(SourceConfig config) {
//        MongoClient mongoClient = MongoClients.create();
        repoRegistries.put(MONGO, new MongoRepository(mongoClient).withDatabase(
                config.getDatabase()));
    }

}
