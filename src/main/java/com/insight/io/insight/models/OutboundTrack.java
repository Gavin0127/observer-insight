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

    public void transform() {
        StatTransformUtils.transformInt(retransmittedBytesSent);
        StatTransformUtils.transformInt(bytesSent);



    }

}
