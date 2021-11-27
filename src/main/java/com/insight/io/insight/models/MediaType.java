package com.insight.io.insight.models;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public enum MediaType {
    AUDIO(0),
    VIDEO(1),
    SCREEN_SHARING(2),
    ;
    private Integer type;

    MediaType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
