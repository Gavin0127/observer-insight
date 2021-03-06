package com.insight.io.insight.models;

import com.insight.io.insight.utils.StatTransformUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@NoArgsConstructor
public class InboundTrack {

    private TreeMap<Long, Integer> frameDecoded = new TreeMap<>();
    private TreeMap<Long, Integer> keyFrameDecoded = new TreeMap<>();
    private TreeMap<Long, Integer> packetsReceived = new TreeMap<>();
    private TreeMap<Long, Integer> packetsLost = new TreeMap<>();
    private TreeMap<Long, Double> lostRatio = new TreeMap<>();
    private TreeMap<Long, Integer> qpSum = new TreeMap<>();
    private TreeMap<Long, Integer> bytesReceived = new TreeMap<>();
    private TreeMap<Long, Integer> receivedBitrate = new TreeMap<>();
    private TreeMap<Long, Integer> firCount = new TreeMap<>();
    private TreeMap<Long, Double> jitter = new TreeMap<>();
    private TreeMap<Long, Integer> frameReceived = new TreeMap<>();
    private TreeMap<Long, Integer> frameDropped = new TreeMap<>();
    private TreeMap<Long, Integer> frameWidth = new TreeMap<>();
    private TreeMap<Long, Integer> frameHeight = new TreeMap<>();
    private TreeMap<Long, Integer> framePerSecond = new TreeMap<>();
    private TreeMap<Long, Double> jitterBufferDelay = new TreeMap<>();

    public void transform() {
        this.packetsReceived = StatTransformUtils.transformInt(packetsReceived);
        this.bytesReceived = StatTransformUtils.transformInt(bytesReceived);
        this.frameDecoded = StatTransformUtils.transformInt(frameDecoded);
        this.frameReceived = StatTransformUtils.transformInt(frameReceived);
        this.frameDropped = StatTransformUtils.transformInt(frameDropped);
        this.keyFrameDecoded = StatTransformUtils.transformInt(keyFrameDecoded);
        this.packetsLost = StatTransformUtils.transformInt(packetsLost);
        this.qpSum = StatTransformUtils.transformInt(qpSum);
        this.jitterBufferDelay =
                StatTransformUtils.transformDouble(jitterBufferDelay);
        this.receivedBitrate =
                StatTransformUtils.transformBitRate(bytesReceived);
        this.lostRatio =
                StatTransformUtils.transformRatio(packetsReceived, packetsLost);
    }

}
