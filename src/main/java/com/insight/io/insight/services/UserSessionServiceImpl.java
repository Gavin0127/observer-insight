package com.insight.io.insight.services;

import com.insight.io.insight.models.Event;
import com.insight.io.insight.models.EventType;
import com.insight.io.insight.models.PeerConnection;
import com.insight.io.insight.models.UserSession;
import com.insight.io.insight.repositories.EventRepository;
import com.insight.io.insight.repositories.PeerConnectionRepository;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
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
    public UserSession getUserSession(String sid, boolean stats) {
        List<PeerConnection> pcs;
        List<Event> events = new ArrayList<>();
        if (stats) {
            pcs = pcRepo.getPeerConnectionsBySid(sid);
            events = eventRepo.getEventBySid(sid);
        } else {
            pcs = pcRepo.getPeerConnectionsBySidWithoutStats(sid);
        }
        if (pcs.isEmpty()) {
            return null;
        }

        UserSession.ClientInfo info = pcRepo.getClientInfoBySid(sid);

        Map<Integer, List<Event>> eventMap =
                events.stream().collect(Collectors.groupingBy(Event::getUri));
        List<Event> joinList = eventMap.get(EventType.JOIN.getUri());
        Event join = null;
        if (CollectionUtils.isNotEmpty(joinList)) {
            join = joinList.get(0);
        }
        List<Event> leaveList = eventMap.get(EventType.LEAVE.getUri());
        Event leave = null;
        if (CollectionUtils.isNotEmpty(leaveList)) {
            leave = leaveList.get(0);
        }
        long startTs = 0;
        if (Objects.isNull(leave)) {
            Optional<Long> min = pcs.stream().map(PeerConnection::getStartTs)
                    .min(Long::compareTo);
            if (min.isPresent()) {
                startTs = min.get();
            }
        } else {
            startTs = join.getTs();
        }

        long endTs = 0;
        if (Objects.isNull(leave)) {
            Optional<Long> max = pcs.stream().map(PeerConnection::getEndTs)
                    .max(Long::compareTo);
            if (max.isPresent()) {
                endTs = max.get();
            }
        } else {
            endTs = leave.getTs();
        }

        return UserSession.builder().clientInfo(info).sid(sid)
                .uid(info.getUid()).roomName(info.getRoomName())
                .startTs(startTs).endTs(endTs).peerConnections(pcs)
                .events(events).build();
    }


}
