package com.insight.io.insight.services;

import com.insight.io.insight.dto.CallDto;
import com.insight.io.insight.repositories.InsightRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class InsightServiceImpl implements InsightService {

    private InsightRepository insightRepository;

    public InsightServiceImpl(InsightRepository insightRepository) {
        this.insightRepository = insightRepository;
    }


    @Override
    public List<CallDto> getCalls(String callId) {
        return insightRepository.getCalls(callId);
    }
}
