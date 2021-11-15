package com.insight.io.insight.services;

import com.insight.io.insight.dto.CallDto;

import java.util.List;

/**
 *
 * @author Xiantao Ge
 * @since 0.1
 */
public interface InsightService {

    List<CallDto> getCalls(String callId);


}
