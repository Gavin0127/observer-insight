package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.InsightRepository;
import com.insight.io.insight.repositories.RepoBuilder;
import com.mongodb.client.MongoClient;
import jakarta.inject.Inject;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class MongoRepoBuilder implements RepoBuilder<InsightRepository> {

    private MongoClient mongoClient;

    private SourceConfig config;

    public MongoRepoBuilder(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public InsightRepository build() {
        return new MongoRepository(mongoClient).withDatabase(
                config.getDatabase());
    }

    public MongoRepoBuilder withConfig(SourceConfig config) {
        this.config = config;
        return this;
    }

}
