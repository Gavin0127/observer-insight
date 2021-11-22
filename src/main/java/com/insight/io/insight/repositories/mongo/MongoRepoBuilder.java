package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.MeetingRepository;
import com.insight.io.insight.repositories.RepoBuilder;
import com.mongodb.client.MongoClient;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class MongoRepoBuilder implements RepoBuilder<MeetingRepository> {

    private MongoClient mongoClient;

    private SourceConfig config;

    public MongoRepoBuilder(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MeetingRepository build() {
        return new MongoMeetingRepository(mongoClient).withDatabase(
                config.getDatabase());
    }

    public MongoRepoBuilder withConfig(SourceConfig config) {
        this.config = config;
        return this;
    }

}
