package com.insight.io.insight.repositories;

import com.insight.io.insight.dto.CallDto;

import java.util.List;

/**
 *
 * @author Xiantao Ge
 * @since 0.1
 */
public interface InsightRepository {

    List<CallDto> getCalls(String callId);

}
