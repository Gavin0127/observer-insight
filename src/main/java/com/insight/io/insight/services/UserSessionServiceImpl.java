package com.insight.io.insight.services;

import com.insight.io.insight.models.Event;
import com.insight.io.insight.models.EventType;
import com.insight.io.insight.models.PeerConnection;
import com.insight.io.insight.models.UserSession;
import com.insight.io.insight.repositories.EventRepository;
import com.insight.io.insight.repositories.PeerConnectionRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
@Singleton
public class UserSessionServiceImpl implements UserSessionService {

    private PeerConnectionRepository pcRepo;
    private EventRepository eventRepo;

    public UserSessionServiceImpl(PeerConnectionRepository pcRepo,
            EventRepository eventRepo) {
        this.pcRepo = pcRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public UserSession getUserSession(String sid) {

        List<PeerConnection> pcs = pcRepo.getPeerConnectionsBySid(sid);
        if (pcs.isEmpty()) {
            return null;
        }

        UserSession.ClientInfo info = pcRepo.getClientInfoBySid(sid);

        List<Event> events = eventRepo.getEventBySid(sid);
        Map<Integer, Event> eventMap = events.stream()
                .collect(Collectors.toMap(Event::getUri, Function.identity()));
        Event join = eventMap.get(EventType.JOIN.getUri());
        Event leave = eventMap.get(EventType.LEAVE.getUri());
        long startTs;
        if (Objects.isNull(leave)) {
            startTs = pcs.stream().map(PeerConnection::getStartTs)
                    .min(Long::compareTo).get();
        } else {
            startTs = join.getTs();
        }

        long endTs;
        if (Objects.isNull(leave)) {
            endTs = pcs.stream().map(PeerConnection::getEndTs)
                    .max(Long::compareTo).get();
        } else {
            endTs = leave.getTs();
        }

        return UserSession.builder().sid(sid).uid(info.getUid())
                .roomName(info.getRoomName()).startTs(startTs).endTs(endTs)
                .peerConnections(pcs).events(events).build();
    }


}
