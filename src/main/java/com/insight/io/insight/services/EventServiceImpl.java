package com.insight.io.insight.services;

import com.insight.io.insight.dto.EventDto;
import com.insight.io.insight.models.Event;
import com.insight.io.insight.repositories.EventRepository;
import jakarta.inject.Inject;

import java.time.Instant;

/**
 * @author : Xiantao Ge
 * @since 0.1
 */
public class EventServiceImpl implements EventService {

    @Inject
    private EventRepository eventRepository;


    @Override
    public void upload(EventDto eventDto) {
        eventRepository.save(
                Event.builder().uri(eventDto.getUri()).sid(eventDto.getSid())
                        .ts(Instant.now().toEpochMilli()).mid(eventDto.getMid())
                        .roomName(eventDto.getRoomName()).uid(eventDto.getUid())
                        .payload(eventDto.getPayload()).build());

    }
}
