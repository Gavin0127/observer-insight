package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface RepoBuilder<T>{

    T build();

    RepoBuilder<T> withConfig(SourceConfig config);

}
