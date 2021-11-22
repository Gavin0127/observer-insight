package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.dto.MeetingDto;
import com.insight.io.insight.entities.mongo.InitiatedCalls;
import com.insight.io.insight.repositories.MeetingRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
public class MongoMeetingRepository implements MeetingRepository {

    private String database;

    private MongoClient mongoClient;

    public MongoMeetingRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public List<MeetingDto> getMeetings() {
        return new ArrayList<>();
    }

    @Override
    public MeetingDto getMeeting(String mid) {
        log.info("get calls: " + mid);
        MongoDatabase reports = mongoClient.getDatabase(database);
        String simpleName = InitiatedCalls.class.getSimpleName();
        MongoCollection<InitiatedCalls> doc =
                reports.getCollection(simpleName, InitiatedCalls.class);
        System.out.println(doc.countDocuments());
        doc.find().forEach(e -> {
            System.out.println(e);
        });
        return null;
    }

    public MongoMeetingRepository withDatabase(String database) {
        this.database = database;
        return this;
    }
}
