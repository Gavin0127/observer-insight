package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.models.Event;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class EventRepoDelegation implements EventRepository {

    private EventRepository repoDelegation;

    public EventRepoDelegation(RepositoryProvider repositoryProvider,
            SourceConfig sourceConfig) {
        this.repoDelegation = repositoryProvider.getEventRepo(sourceConfig);
    }

    @Override
    public void save(Event event) {
        repoDelegation.save(event);
    }

    @Override
    public Event getEventBySidAndUri(String sid, Integer uri) {
        return repoDelegation.getEventBySidAndUri(sid, uri);
    }

    @Override
    public List<Event> getEventsByMidAndUri(String mid, Integer uri) {
        return repoDelegation.getEventsByMidAndUri(mid, uri);
    }

    @Override
    public List<Event> getEventBySid(String sid) {
        return repoDelegation.getEventBySid(sid);
    }
}
