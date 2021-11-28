package com.insight.io.insight.entities.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Events {

    private Integer uri;
    private String sid;
    private Long ts;
    private String payload;

}
