package com.insight.io.insight.utils;

import io.micronaut.core.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class StatTransformUtils {

    public static TreeMap<Long, Integer> transformInt(
            TreeMap<Long, Integer> stats) {
        if (CollectionUtils.isEmpty(stats)) {
            return stats;
        }
        TreeMap<Long, Integer> transformed = new TreeMap<>();
        var iter = stats.entrySet().iterator();
        var pre = iter.next();
        transformed.put(pre.getKey(), pre.getValue());
        while (iter.hasNext()) {
            var preVal = pre.getValue();
            var next = iter.next();
            Long nextKey = next.getKey();
            Integer nextValue = next.getValue();
            if (Objects.isNull(nextKey) || Objects.isNull(nextValue)) {
                continue;
            }
            transformed.put(nextKey, nextValue - preVal);
            pre = next;
        }
        return transformed;
    }

    public static TreeMap<Long, Double> transformDouble(
            TreeMap<Long, Double> stats) {
        if (CollectionUtils.isEmpty(stats)) {
            return stats;
        }
        TreeMap<Long, Double> transformed = new TreeMap<>();
        var iter = stats.entrySet().iterator();
        var pre = iter.next();
        transformed.put(pre.getKey(), pre.getValue());
        while (iter.hasNext()) {
            var preVal = pre.getValue();
            var next = iter.next();
            Long nextKey = next.getKey();
            Double nextValue = next.getValue();
            if (Objects.isNull(nextKey) || Objects.isNull(nextValue)) {
                continue;
            }
            transformed.put(nextKey, nextValue - preVal);
            pre = next;
        }
        return transformed;
    }

}
