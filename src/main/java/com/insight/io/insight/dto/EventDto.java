package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Data
@Builder
@Schema(description = "the user media track info")
public class EventDto {

    @Schema(description = "the event uri, eg. 1001 - createOffer")
    private Integer uri;
    @Schema(description = "the timestamp")
    private Long ts;
    @Schema(description = "the event content")
    private String content;

}
