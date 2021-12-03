package com.insight.io.insight.utils;

import io.micronaut.core.util.CollectionUtils;

import java.util.TreeMap;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public class StatTransformUtils {

    public static void transformInt(TreeMap<Long, Integer> stats) {
        if (CollectionUtils.isEmpty(stats)) {
            return;
        }
        TreeMap<Long, Integer> transformed = new TreeMap<>();
        var iter = stats.entrySet().iterator();
        var pre = iter.next();
        while (iter.hasNext()) {
            var preVal = pre.getValue();
            transformed.put(pre.getKey(), preVal);
            var next = iter.next();
            transformed.put(next.getKey(), next.getValue() - preVal);
            pre = next;
        }
        stats = transformed;
    }

    public static void transformDouble(TreeMap<Long, Double> stats) {
        if (CollectionUtils.isEmpty(stats)) {
            return;
        }
        TreeMap<Long, Double> transformed = new TreeMap<>();
        var iter = stats.entrySet().iterator();
        var pre = iter.next();
        while (iter.hasNext()) {
            var preVal = pre.getValue();
            transformed.put(pre.getKey(), preVal);
            var next = iter.next();
            transformed.put(next.getKey(), next.getValue() - preVal);
            pre = next;
        }
        stats = transformed;
    }

}
