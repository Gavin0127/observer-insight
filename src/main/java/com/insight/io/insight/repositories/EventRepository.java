package com.insight.io.insight.repositories;

import com.insight.io.insight.models.Event;

import java.util.List;

/**
 * @author : Xiantao Ge
 * @since 0.1
 */
public interface EventRepository {

    void save(Event event);

    Event getEventBySidAndUri(String sid, Integer uri);
    List<Event> getEventsByMidAndUri(String mid, Integer uri);
    List<Event> getEventBySid(String sid);

}
