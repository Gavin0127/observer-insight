package com.insight.io.insight.entities;

import java.util.function.Function;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface ModelConverter<S, T> extends Function<S, T> {
}
