package com.insight.io.insight.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "the user event")
public class EventDto {

    @Schema(description = "the event uri, eg. 1001 - createOffer")
    private Integer uri;
    @Schema(description = "the user session id")
    private String sid;
    @Schema(description = "the timestamp")
    private Long ts;
    @Schema(description = "the event payload")
    private String payload;

}
