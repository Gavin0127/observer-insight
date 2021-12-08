package com.insight.io.insight.utils;

import io.micronaut.core.util.CollectionUtils;

import java.util.Objects;
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

    public static <T extends Number> TreeMap<Long, Integer> transformBitRate(
            TreeMap<Long, T> stats) {
        if (CollectionUtils.isEmpty(stats)) {
            return new TreeMap<>();
        }
        TreeMap<Long, Integer> transformed = new TreeMap<>();
        var iter = stats.entrySet().iterator();
        var pre = iter.next();
        var preKey = pre.getKey();
        var preVal = pre.getValue();
        transformed.put(preKey, preVal.intValue());
        while (iter.hasNext()) {
            preKey = pre.getKey();
            //            preVal = pre.getValue();
            var next = iter.next();
            var nextKey = next.getKey();
            var nextValue = next.getValue();
            if (Objects.isNull(nextKey) || Objects.isNull(nextValue)) {
                continue;
            }
            transformed.put(nextKey, Math.toIntExact(
                    nextValue.intValue() / ((nextKey - preKey) / 1000)));
            pre = next;
        }
        return transformed;
    }

    public static <T extends Number> TreeMap<Long, Double> transformRatio(
            TreeMap<Long, T> statsDeno, TreeMap<Long, T> statElem) {
        if (CollectionUtils.isEmpty(statsDeno) ||
                CollectionUtils.isEmpty(statElem)) {
            return new TreeMap<>();
        }
        TreeMap<Long, Double> transformed = new TreeMap<>();
        statsDeno.entrySet().forEach(entry -> {
            Long key = entry.getKey();
            T denoVal = entry.getValue();
            T elemVal = statElem.get(key);
            double ratio = 0D;
            if (Objects.nonNull(denoVal) && denoVal.intValue() != 0 &&
                    Objects.nonNull(elemVal)) {
                ratio = elemVal.doubleValue() /
                        (denoVal.doubleValue() + elemVal.doubleValue());
            }
            transformed.put(key, ratio);
        });
        return transformed;
    }
}
