package com.insight.io.insight.repositories.mongo;

import com.insight.io.insight.entities.mongo.*;
import com.insight.io.insight.models.*;
import com.insight.io.insight.repositories.PeerConnectionRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
public class MongoPeerConnectionRepository implements PeerConnectionRepository {

    public static final String PEER_UUID = "peerConnectionUUID";
    public static final String CALL_UUID = "callUUID";
    public static final String SID = "sid";
    public static final String EXTENSION_TYPE = "extensionType";
    public static final String MID = "mid";
    public static final String PAYLOAD = "content";

    private String database;

    private MongoClient mongoClient;

    public MongoPeerConnectionRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public UserSession.ClientInfo getClientInfoBySid(String sid) {
        var extensionReports = getCollection(ExtensionReports.class).find(
                Filters.and(Filters.eq(EXTENSION_TYPE, SID),
                        Filters.eq(PAYLOAD, sid))).first();
        if (Objects.isNull(extensionReports)) {
            log.error("extensionStats cannot be found, sid: " + sid);
            return null;
        }
        ClientDetailss info = getCollection(ClientDetailss.class).find(
                        Filters.eq(PEER_UUID,
                                extensionReports.getPeerConnectionUUID()))
                .first();
        if (Objects.isNull(info)) {
            log.error("ClientDetails cannot be found, PeerId: " +
                    extensionReports.getPeerConnectionUUID());
            return null;
        }
        return UserSession.ClientInfo.builder().uid(info.getUserId())
                .roomName(info.getCallName()).osName(info.getOsName())
                .osVersion(info.getOsVersion())
                .browserName(info.getBrowserName())
                .browserVersion(info.getBrowserVersion())
                .engineName(info.getEngineName())
                .engineVersion(info.getEngineVersion())
                .platformVendor(info.getPlatformVendor()).build();
    }

    @Override
    public List<PeerConnection> getPeerConnectionsByMid(String mid) {
        throw new RuntimeException("getPeerConnectionsByMid is not available");
    }

    @Override
    public List<PeerConnection> getPeerConnectionsBySid(String sid) {
        var extensionReports = getCollection(ExtensionReports.class).find(
                Filters.and(Filters.eq(EXTENSION_TYPE, SID),
                        Filters.eq(PAYLOAD, sid)));
        return StreamSupport.stream(extensionReports.spliterator(), false)
                .map(ExtensionReports::getPeerConnectionUUID).distinct()
                .map(this::getPeerConnection).collect(Collectors.toList());
    }

    @Override
    public List<PeerConnection> getPeerConnectionsBySidWithoutStats(String sid) {
        var extensionReports = getCollection(ExtensionReports.class).find(
                Filters.and(Filters.eq(EXTENSION_TYPE, SID),
                        Filters.eq(PAYLOAD, sid)));
        return StreamSupport.stream(extensionReports.spliterator(), false)
                .map(ExtensionReports::getPeerConnectionUUID).distinct()
                .map(this::getPeerConnectionWithoutStat).collect(Collectors.toList());
    }

    public PeerConnection getPeerConnectionWithoutStat(String peerConnectionUUID) {
        PeerConnection.PeerConnectionBuilder builder = PeerConnection.builder();
        findInit(peerConnectionUUID, builder);
        findEnd(peerConnectionUUID, builder);
        return builder.build();
    }

    @Override
    public PeerConnection getPeerConnection(String peerConnectionUUID) {
        PeerConnection.PeerConnectionBuilder builder = PeerConnection.builder();
        findInit(peerConnectionUUID, builder);
        findEnd(peerConnectionUUID, builder);
        findStats(peerConnectionUUID, builder);
        return builder.build();
    }


    private PeerConnection.PeerConnectionBuilder findInit(
            String peerConnectionUUID,
            PeerConnection.PeerConnectionBuilder builder) {
        var peer = getCollection(JoinedPeerConnections.class).find(
                Filters.eq(PEER_UUID, peerConnectionUUID)).first();
        if (Objects.isNull(peer)) {
            return builder.startTs(0L);
        }
        return builder.peerConnectionUUID(peer.getPeerConnectionUUID())
                .startTs(peer.getTimestamp());
    }

    private PeerConnection.PeerConnectionBuilder findEnd(
            String peerConnectionUUID,
            PeerConnection.PeerConnectionBuilder builder) {
        var peer = getCollection(DetachedPeerConnections.class).find(
                Filters.eq(PEER_UUID, peerConnectionUUID)).first();
        if (Objects.isNull(peer)) {
            return builder.endTs(0L);
        }
        return builder.peerConnectionUUID(peer.getPeerConnectionUUID())
                .endTs(peer.getTimestamp());
    }


    private PeerConnection.PeerConnectionBuilder findStats(
            String peerConnectionUUID,
            PeerConnection.PeerConnectionBuilder builder) {
        Stream<InboundRTPs> inboundRTPsStream = StreamSupport.stream(
                getCollection(InboundRTPs.class).find(
                                Filters.eq(PEER_UUID, peerConnectionUUID))
                        .spliterator(), false);
        var inboundMap = inboundRTPsStream.collect(
                Collectors.groupingBy(InboundRTPs::getTrackId));

        var tracks = getCollection(Tracks.class).find(
                Filters.eq(PEER_UUID, peerConnectionUUID));
        var tracksMap = StreamSupport.stream(tracks.spliterator(), false)
                .collect(Collectors.groupingBy(Tracks::getTrackId));

//        var media = getCollection(MediaSources.class).find(
//                Filters.eq(PEER_UUID, peerConnectionUUID));
//
//        var mediaMap = StreamSupport.stream(media.spliterator(), false)
//                .collect(Collectors.groupingBy(MediaSources::getMediaSourceID));

        var outBoundRTPs = getCollection(OutBoundRTPs.class).find(
                Filters.eq(PEER_UUID, peerConnectionUUID));
        var outboundMap =
                StreamSupport.stream(outBoundRTPs.spliterator(), false).collect(
                        Collectors.groupingBy(OutBoundRTPs::getTrackId));

        List<PeerTrack> peerTracks = new ArrayList<>();
        tracksMap.entrySet().forEach(track -> {
            PeerTrack.PeerTrackBuilder trackBuilder = PeerTrack.builder();
            String trackId = track.getKey();
            trackBuilder.trackId(trackId);
            InboundTrack inboundTrack = new InboundTrack();
            trackBuilder.inboundTrack(inboundTrack);
            OutboundTrack outboundTrack = new OutboundTrack();
            trackBuilder.outboundTrack(outboundTrack);
            track.getValue().forEach(stat -> {
                trackBuilder.mediaType(stat.getMediaType());
                if (stat.getRemoteSource()) {
                    inboundTrack.getFrameReceived()
                            .put(stat.getTimestamp(), stat.getFrameReceived());
                    inboundTrack.getFrameDropped()
                            .put(stat.getTimestamp(), stat.getFrameDropped());
                    inboundTrack.getFrameWidth()
                            .put(stat.getTimestamp(), stat.getFrameWidth());
                    inboundTrack.getFrameHeight()
                            .put(stat.getTimestamp(), stat.getFrameHeight());
                    inboundTrack.getJitterBufferDelay().put(stat.getTimestamp(),
                            stat.getJitterBufferDelay());
                } else {
                    outboundTrack.getFrameHeight()
                            .put(stat.getTimestamp(), stat.getFrameHeight());
                    outboundTrack.getFrameWidth()
                            .put(stat.getTimestamp(), stat.getFrameWidth());
                    outboundTrack.getHugeFramesSent()
                            .put(stat.getTimestamp(), stat.getHugeFramesSent());
                }

            });
            List<InboundRTPs> inbounds =
                    inboundMap.getOrDefault(trackId, new ArrayList<>());
            inbounds.forEach(stat -> {
                trackBuilder.ssrc(stat.getSsrc());
                inboundTrack.setSsrc(stat.getSsrc());
                inboundTrack.getFrameDecoded()
                        .put(stat.getTimestamp(), stat.getFramesDecoded());
                inboundTrack.getKeyFrameDecoded()
                        .put(stat.getTimestamp(), stat.getKeyFramesDecoded());
                inboundTrack.getPacketsReceived()
                        .put(stat.getTimestamp(), stat.getPacketsReceived());
                inboundTrack.getPacketsLost()
                        .put(stat.getTimestamp(), stat.getPacketsLost());
                inboundTrack.getQpSum()
                        .put(stat.getTimestamp(), stat.getQpSum());
                inboundTrack.getBytesReceived()
                        .put(stat.getTimestamp(), stat.getBytesReceived());
                inboundTrack.getFirCount()
                        .put(stat.getTimestamp(), stat.getFirCount());
                inboundTrack.getJitter()
                        .put(stat.getTimestamp(), stat.getJitter());

            });
            List<OutBoundRTPs> outbounds =
                    outboundMap.getOrDefault(trackId, new ArrayList<>());
            outbounds.forEach(stat -> {
                trackBuilder.ssrc(stat.getSsrc());
                outboundTrack.setSsrc(stat.getSsrc());
                outboundTrack.getFrameEncoded()
                        .put(stat.getTimestamp(), stat.getFramesEncoded());
                outboundTrack.getKeyFrameEncoded()
                        .put(stat.getTimestamp(), stat.getKeyFramesEncoded());
                outboundTrack.getRetransmittedBytesSent()
                        .put(stat.getTimestamp(),
                                stat.getRetransmittedBytesSent());
                outboundTrack.getTotalEncodedBytesTarget()
                        .put(stat.getTimestamp(),
                                stat.getTotalEncodedBytesTarget());
                outboundTrack.getQpSum()
                        .put(stat.getTimestamp(), stat.getQpSum());
                outboundTrack.getFirCount()
                        .put(stat.getTimestamp(), stat.getFirCount());
                outboundTrack.getBytesSent()
                        .put(stat.getTimestamp(), stat.getBytesSent());
            });
            peerTracks.add(trackBuilder.build());
        });

        return builder.peerTracks(peerTracks);
    }


    private <T> MongoCollection<T> getCollection(Class<T> clazz) {
        MongoDatabase reports = mongoClient.getDatabase(this.database);
        return reports.getCollection(clazz.getSimpleName(), clazz);
    }

    public MongoPeerConnectionRepository withDatabase(String database) {
        this.database = database;
        return this;
    }

}
