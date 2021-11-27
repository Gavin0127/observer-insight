package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.entities.mongo.FinishedCalls;
import com.insight.io.insight.entities.mongo.InitiatedCalls;
import com.insight.io.insight.models.Meeting;
import com.insight.io.insight.repositories.MeetingRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
public class MongoMeetingRepository implements MeetingRepository {

    public static final String CALL_UUID = "callUUID";
    public static final String CALL_NAME = "callName";
    public static final String UID = "userId";

    private String database;

    private MongoClient mongoClient;

    public MongoMeetingRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public List<Meeting> getMeetings(String roomName, String uid) {
        Bson filter;
        if (Objects.nonNull(roomName) && Objects.nonNull(uid)) {
            filter = Filters.and(Filters.eq(CALL_NAME, roomName),
                    Filters.eq(UID, uid));
        } else if (Objects.nonNull(roomName)) {
            filter = Filters.eq(CALL_NAME, roomName);
        } else {
            filter = Filters.eq(UID, uid);
        }
        var initiatedCalls = getCollection(InitiatedCalls.class).find(filter);
        var result = new ArrayList<Meeting>();
        initiatedCalls.forEach(call -> {
            String callUUID = call.getCallUUID();
            Meeting.MeetingBuilder meetingBuilder =
                    Meeting.builder().mid(callUUID).roomName(call.getCallName())
                            .startTs(call.getTimestamp());
            var finishedCall = getCollection(FinishedCalls.class).find(
                    Filters.eq(CALL_UUID, callUUID)).first();
            if (Objects.nonNull(finishedCall)) {
                meetingBuilder.endTs(finishedCall.getTimestamp());
            }
            result.add(meetingBuilder.build());
        });
        return result;
    }

    @Override
    public Meeting getMeeting(String mid) {
        log.info("get call by meetingId: " + mid);

        Meeting.MeetingBuilder builder = Meeting.builder();
        findInit(mid, builder);
        findEnd(mid, builder);
        return builder.build();
    }

    private Meeting.MeetingBuilder findInit(String mid,
            Meeting.MeetingBuilder builder) {
        var initiatedCall = getCollection(InitiatedCalls.class).find(
                Filters.eq(CALL_UUID, mid)).first();
        if (Objects.isNull(initiatedCall)) {
            return builder;
        }
        return builder.mid(initiatedCall.getCallUUID())
                .roomName(initiatedCall.getCallName())
                .startTs(initiatedCall.getTimestamp());
    }

    private Meeting.MeetingBuilder findEnd(String mid,
            Meeting.MeetingBuilder builder) {
        if (Objects.isNull(builder)) {
            return null;
        }
        var finishedCall = getCollection(FinishedCalls.class).find(
                Filters.eq(CALL_UUID, mid)).first();
        if (Objects.nonNull(finishedCall)) {
            builder.endTs(finishedCall.getTimestamp());
        }
        return builder;
    }

    private <T> MongoCollection<T> getCollection(Class<T> clazz) {
        MongoDatabase reports = mongoClient.getDatabase(this.database);
        return reports.getCollection(clazz.getSimpleName(), clazz);
    }

    public MongoMeetingRepository withDatabase(String database) {
        this.database = database;
        return this;
    }
}
