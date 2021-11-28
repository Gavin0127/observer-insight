package com.insight.io.insight.models;

/**
 * @author : Xiantao Ge
 * @since 0.1
 */
public enum EventType {
    JOIN(1001),
    LEAVE(1002),
    ;
    private Integer uri;

    EventType(Integer uri) {
        this.uri = uri;
    }

    public Integer getUri() {
        return uri;
    }
}
