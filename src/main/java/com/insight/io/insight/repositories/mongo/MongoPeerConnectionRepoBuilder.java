package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.repositories.RepoBuilder;
import com.insight.io.insight.repositories.PeerConnectionRepository;
import com.mongodb.client.MongoClient;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class MongoPeerConnectionRepoBuilder
        implements RepoBuilder<PeerConnectionRepository> {

    private MongoClient mongoClient;

    private SourceConfig config;

    public MongoPeerConnectionRepoBuilder(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public PeerConnectionRepository build() {
        return new MongoPeerConnectionRepository(mongoClient).withDatabase(
                config.getDatabase());
    }

    public MongoPeerConnectionRepoBuilder withConfig(SourceConfig config) {
        this.config = config;
        return this;
    }

}
