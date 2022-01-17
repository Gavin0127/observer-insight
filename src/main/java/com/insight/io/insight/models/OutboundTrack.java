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
public class OutboundTrack {

    private TreeMap<Long, Integer> frameEncoded = new TreeMap<>();
    private TreeMap<Long, Integer> keyFrameEncoded = new TreeMap<>();
    private TreeMap<Long, Integer> retransmittedBytesSent = new TreeMap<>();
    private TreeMap<Long, Integer> totalEncodedBytesTarget = new TreeMap<>();
    private TreeMap<Long, Integer> qpSum = new TreeMap<>();
    private TreeMap<Long, Integer> firCount = new TreeMap<>();
    private TreeMap<Long, Integer> bytesSent = new TreeMap<>();
    private TreeMap<Long, Integer> sendBitrate = new TreeMap<>();
    private TreeMap<Long, Integer> targetBitrate = new TreeMap<>();
    private TreeMap<Long, Integer> frameWidth = new TreeMap<>();
    private TreeMap<Long, Integer> frameHeight = new TreeMap<>();
    private TreeMap<Long, Integer> frameDropped = new TreeMap<>();
    private TreeMap<Long, Integer> hugeFramesSent = new TreeMap<>();
    private TreeMap<Long, Integer> framePerSecond = new TreeMap<>();
    private TreeMap<Long, Integer> width = new TreeMap<>();
    private TreeMap<Long, Integer> height = new TreeMap<>();
    private TreeMap<Long, Double> audioLevel = new TreeMap<>();
    private TreeMap<Long, Double> totalAudioEnergy = new TreeMap<>();
    private TreeMap<Long, Double> totalSamplesDuration = new TreeMap<>();
    private TreeMap<Long, String> qualityLimitationReason = new TreeMap<>();

    public void transform() {
        this.retransmittedBytesSent = StatTransformUtils.transformInt(retransmittedBytesSent);
        this.bytesSent = StatTransformUtils.transformInt(bytesSent);
        this.totalAudioEnergy =
                StatTransformUtils.transformDouble(totalAudioEnergy);
        this.totalSamplesDuration =
                StatTransformUtils.transformDouble(totalSamplesDuration);
        this.frameEncoded = StatTransformUtils.transformInt(frameEncoded);
        this.keyFrameEncoded = StatTransformUtils.transformInt(keyFrameEncoded);
        this.totalEncodedBytesTarget =
                StatTransformUtils.transformInt(totalEncodedBytesTarget);
        this.qpSum = StatTransformUtils.transformInt(qpSum);
        this.firCount = StatTransformUtils.transformInt(firCount);
        this.sendBitrate = StatTransformUtils.transformBitRate(bytesSent);

    }

}
