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
public class TotalAggrTrack {

    private TreeMap<Long, Double> totalSendBitrate = new TreeMap<>();
    private TreeMap<Long, Double> totalReceiveBitrate = new TreeMap<>();

    public void aggr(List<AggrTrack> peerTrackList) {
        aggrSendBitRate(peerTrackList);
        aggrRecvBitRate(peerTrackList);
    }

    private void aggrSendBitRate(List<AggrTrack> aggrTracks) {

        if (Objects.isNull(aggrTracks)) {
            return;
        }

        TreeMap<Long, Double> totalSendBitRate = new TreeMap<>();
        aggrTracks.forEach(track -> {
            var sendBitrate = track.getTotalSendBitrate();
            if (Objects.isNull(sendBitrate)) {
                return;
            }
            sendBitrate.forEach((key, value) -> {
                totalSendBitRate.put(key,
                        value + totalSendBitRate.getOrDefault(key, 0D));
            });
        });

        this.totalSendBitrate = totalSendBitRate;

    }

    private void aggrRecvBitRate(List<AggrTrack> aggrTracks) {

        if (Objects.isNull(aggrTracks)) {
            return;
        }

        TreeMap<Long, Double> receivedBitRate = new TreeMap<>();
        aggrTracks.forEach(track -> {
            var receiveBitrate = track.getTotalReceiveBitrate();
            if (Objects.isNull(receiveBitrate)) {
                return;
            }
            receiveBitrate.forEach((key, value) -> {
                receivedBitRate.put(key,
                        value + receivedBitRate.getOrDefault(key, 0D));
            });
        });

        this.totalReceiveBitrate = receivedBitRate;

    }
}
