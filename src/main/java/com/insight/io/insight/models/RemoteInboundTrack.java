package com.insight.io.insight.models;

import com.insight.io.insight.utils.StatTransformUtils;
import lombok.Data;

import java.util.TreeMap;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
public class RemoteInboundTrack {

    private TreeMap<Long, Double> jitter = new TreeMap<>();
    private TreeMap<Long, Double> roundTripTime = new TreeMap<>();
    private TreeMap<Long, Integer> packetsLost = new TreeMap<>();

    public void transform() {
        this.packetsLost = StatTransformUtils.transformInt(packetsLost);

    }

}
