package com.insight.io.insight.services;

import com.insight.io.insight.models.*;
import com.insight.io.insight.repositories.EventRepository;
import com.insight.io.insight.repositories.PeerConnectionRepository;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
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
        return getUserSession(sid, stats, true);
    }

    @Override
    public UserSession getUserSession(String sid, boolean stats,
            boolean includeRemote) {
        List<PeerConnection> pcs;
        List<Event> events = new ArrayList<>();
        List<UserSession> remoteUserSessions = new ArrayList<>();
        TotalAggrTrack totalAggrTrack = new TotalAggrTrack();
        if (stats) {
            pcs = pcRepo.getPeerConnectionsBySid(sid);
            events = eventRepo.getEventBySid(sid);
            totalAggrTrack.aggr(pcs.stream().map(PeerConnection::getAggrTrack)
                    .collect(Collectors.toList()));
            if (includeRemote) {
                remoteUserSessions =
                        pcs.stream().map(PeerConnection::getRemotePeerInfo)
                                .map(PeerConnection.RemotePeerInfo::getSid)
                                .map(remoteSid -> getUserSession(remoteSid,
                                        true, false))
                                .collect(Collectors.toList());
            }
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
                .remoteUserSessions(remoteUserSessions).events(events)
                .totalAggrTrack(totalAggrTrack).build();
    }


}
