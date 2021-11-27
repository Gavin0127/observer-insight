package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.RepoBuilder;
import com.insight.io.insight.repositories.UserSessionRepository;
import com.mongodb.client.MongoClient;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class MongoUserSessionRepoBuilder
        implements RepoBuilder<UserSessionRepository> {

    private MongoClient mongoClient;

    private SourceConfig config;

    public MongoUserSessionRepoBuilder(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public UserSessionRepository build() {
        return new MongoUserSessionRepository(mongoClient).withDatabase(
                config.getDatabase());
    }

    public MongoUserSessionRepoBuilder withConfig(SourceConfig config) {
        this.config = config;
        return this;
    }

}
