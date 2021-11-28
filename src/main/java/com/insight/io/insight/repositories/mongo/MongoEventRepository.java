package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.entities.mongo.Events;
import com.insight.io.insight.models.Event;
import com.insight.io.insight.repositories.EventRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
public class MongoEventRepository implements EventRepository {

    public static final String PAYLOAD = "payload";
    public static final String URI = "uri";
    public static final String SID = "sid";

    private String database;

    private MongoClient mongoClient;

    public MongoEventRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void save(Event event) {
        MongoCollection<Events> collection = getCollection(Events.class);
        collection.insertOne(
                Events.builder().uri(event.getUri()).ts(event.getTs())
                        .sid(event.getSid()).payload(event.getPayload())
                        .build());

    }

    @Override
    public Event getEventBySidAndUri(String sid, Integer uri) {
        return Event.of(getCollection(Events.class).find(
                        Filters.and(Filters.eq(SID, sid), Filters.eq(URI, uri)))
                .first());
    }

    @Override
    public List<Event> getEventBySid(String sid) {
        return StreamSupport.stream(
                        getCollection(Events.class).find(Filters.eq(SID, sid))
                                .spliterator(), false).map(Event::of)
                .collect(Collectors.toList());
    }

    private <T> MongoCollection<T> getCollection(Class<T> clazz) {
        MongoDatabase database = getMongoDatabase();
        return database.getCollection(clazz.getSimpleName(), clazz);
    }

    private MongoDatabase getMongoDatabase() {
        return mongoClient.getDatabase(this.database);
    }

    public MongoEventRepository withDatabase(String database) {
        this.database = database;
        return this;
    }

}
