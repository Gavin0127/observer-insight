package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.dto.CallDto;
import com.insight.io.insight.repositories.InsightRepository;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
public class MongoRepository implements InsightRepository {

    private String database;

    private MongoClient mongoClient;

    public MongoRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public List<CallDto> getCalls(String callId) {
        log.info("get calls: " + callId);
        MongoDatabase reports = mongoClient.getDatabase(database);
        ListCollectionsIterable<Document> documents = reports.listCollections();
        documents.forEach(doc -> System.out.println(doc.toJson()));
        return new ArrayList<>();
    }

    public MongoRepository withDatabase(String database) {
        this.database = database;
        return this;
    }
}
