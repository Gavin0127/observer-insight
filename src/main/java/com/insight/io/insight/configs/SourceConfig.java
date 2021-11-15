package com.insight.io.insight.configs;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@ConfigurationProperties("source")
@Data
public class SourceConfig {

    private String type;

    private String database;

}
