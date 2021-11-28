package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.EventRepository;
import com.insight.io.insight.repositories.RepoBuilder;
import com.mongodb.client.MongoClient;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class MongoEventRepoBuilder implements RepoBuilder<EventRepository> {

    private MongoClient mongoClient;

    private SourceConfig config;

    public MongoEventRepoBuilder(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public EventRepository build() {
        return new MongoEventRepository(mongoClient).withDatabase(
                config.getDatabase());
    }

    public MongoEventRepoBuilder withConfig(SourceConfig config) {
        this.config = config;
        return this;
    }

}
