package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.entities.mongo.InitiatedCalls;
import com.insight.io.insight.repositories.InsightRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;

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
    public List<MeetingDto> getMeetings(String mid) {
        log.info("get calls: " + mid);
        MongoDatabase reports = mongoClient.getDatabase(database);
        String simpleName = InitiatedCalls.class.getSimpleName();
        System.out.println(simpleName);
        MongoCollection<InitiatedCalls> doc =
                reports.getCollection(simpleName,
                        InitiatedCalls.class);
        System.out.println(doc.countDocuments());
        doc.find().forEach(e -> {
            System.out.println(e);
        });
        return new ArrayList<>();
    }

    public MongoRepository withDatabase(String database) {
        this.database = database;
        return this;
    }
}
