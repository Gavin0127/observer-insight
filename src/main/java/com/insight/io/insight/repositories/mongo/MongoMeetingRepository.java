package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.entities.mongo.ExtensionReports;
import com.insight.io.insight.entities.mongo.FinishedCalls;
import com.insight.io.insight.entities.mongo.InitiatedCalls;
import com.insight.io.insight.entities.mongo.JoinedPeerConnections;
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
    public static final String EXTENSION_TYPE = "extensionType";
    public static final String MID = "mid";
    public static final String PAYLOAD = "content";
    public static final String PEER_UUID = "peerConnectionUUID";

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
            var jpc = getCollection(JoinedPeerConnections.class).find(
                    Filters.eq(CALL_UUID, callUUID)).first();
            var extension = getCollection(ExtensionReports.class).find(
                            Filters.and(Filters.eq(EXTENSION_TYPE, MID),
                                    Filters.eq(PEER_UUID,
                                            jpc.getPeerConnectionUUID())))
                    .first();
            Meeting.MeetingBuilder meetingBuilder =
                    Meeting.builder().mid(extension.getPayload())
                            .roomName(call.getCallName())
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
        var extension = getCollection(ExtensionReports.class).find(
                Filters.and(Filters.eq(EXTENSION_TYPE, MID),
                        Filters.eq(PAYLOAD, mid))).first();
        if (Objects.isNull(extension)) {
            log.error("no peerConnectionId found, mid: " + mid);
            return builder;
        }
        String pid = extension.getPeerConnectionUUID();
        var jpc = getCollection(JoinedPeerConnections.class).find(
                Filters.eq(PEER_UUID, pid)).first();
        var initiatedCall = getCollection(InitiatedCalls.class).find(
                Filters.eq(CALL_UUID, jpc.getCallUUID())).first();
        if (Objects.isNull(initiatedCall)) {
            return builder;
        }
        return builder.mid(mid).roomName(initiatedCall.getCallName())
                .startTs(initiatedCall.getTimestamp());
    }

    private Meeting.MeetingBuilder findEnd(String mid,
            Meeting.MeetingBuilder builder) {
        var extension = getCollection(ExtensionReports.class).find(
                Filters.and(Filters.eq(EXTENSION_TYPE, MID),
                        Filters.eq(PAYLOAD, mid))).first();
        if (Objects.isNull(extension)) {
            log.error("no peerConnectionId found, mid: " + mid);
            return builder;
        }
        String pid = extension.getPeerConnectionUUID();
        var jpc = getCollection(JoinedPeerConnections.class).find(
                Filters.eq(PEER_UUID, pid)).first();
        var finishedCall = getCollection(FinishedCalls.class).find(
                Filters.eq(CALL_UUID, jpc.getCallUUID())).first();
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
