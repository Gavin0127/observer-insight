package com.insight.io.insight.models;

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
    //    private List<OutboundSample> outboundSamples;

    //    @Data
    //    @Builder
    //    public static class OutboundSample {
    //
    //        private Long ts;
    //        private Integer frameEncoded;
    //        private Integer keyFrameEncoded;
    //        private Integer retransmittedBytesSent;
    //        private Integer totalEncodedBytesTarget;
    //        private Integer qpSum;
    //        private Integer firCount;
    //        private Integer bytesSent;
    //        private Integer frameWidth;
    //        private Integer frameHeight;
    //        private Integer hugeFramesSent;
    //        private Integer framePerSecond;
    //
    //    }

}
