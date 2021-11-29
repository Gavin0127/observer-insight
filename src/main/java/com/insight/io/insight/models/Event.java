package com.insight.io.insight.models;

import com.insight.io.insight.entities.mongo.Events;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
public class Event {

    private Integer uri;
    private String sid;
    private String mid;
    private String uid;
    private String roomName;
    private Long ts;
    private String payload;

    public static Event of(Events events) {
        EventBuilder builder = Event.builder();
        if (Objects.nonNull(events)) {
            builder.sid(events.getSid()).uri(events.getUri()).ts(events.getTs())
                    .mid(events.getMid()).uid(events.getUid())
                    .roomName(events.getRoomName())
                    .payload(events.getPayload());
        }
        return builder.build();
    }

}
