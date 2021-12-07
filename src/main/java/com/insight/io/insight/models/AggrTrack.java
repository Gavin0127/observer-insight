package com.insight.io.insight.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@NoArgsConstructor
public class AggrTrack {

    private TreeMap<Long, Double> totalSendBitRate = new TreeMap<>();
    private TreeMap<Long, Double> totalReceiveBitRate = new TreeMap<>();

    public void transform() {

    }
    public void aggr(List<PeerTrack> peerTrackList) {
        aggrSendBitRate(peerTrackList);
        aggrRecvBitRate(peerTrackList);
    }

    private void aggrSendBitRate(List<PeerTrack> peerTrackList) {
        if (Objects.isNull(peerTrackList)) {
            return;
        }
        TreeMap<Long, Double> sendBitRate = new TreeMap<>();
        peerTrackList.forEach(track -> {
            OutboundTrack outboundTrack = track.getOutboundTrack();
            if (Objects.isNull(outboundTrack)) {
                return;
            }
            outboundTrack.getSendBitRate().forEach((key, value) -> {
                sendBitRate.put(key, value + sendBitRate.getOrDefault(key, 0D));
            });
        });
        this.totalSendBitRate = sendBitRate;
    }

    private void aggrRecvBitRate(List<PeerTrack> peerTrackList) {
        if (Objects.isNull(peerTrackList)) {
            return;
        }
        TreeMap<Long, Double> receivedBitRate = new TreeMap<>();
        peerTrackList.forEach(track -> {
            InboundTrack inboundTrack = track.getInboundTrack();
            if (Objects.isNull(inboundTrack)) {
                return;
            }
            inboundTrack.getReceivedBitRate().forEach((key, value) -> {
                receivedBitRate.put(key,
                        value + receivedBitRate.getOrDefault(key, 0D));
            });
        });
        this.totalReceiveBitRate = receivedBitRate;
    }
}
