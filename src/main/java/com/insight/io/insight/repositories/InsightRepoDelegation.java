package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.dto.CallDto;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class InsightRepoDelegation implements InsightRepository {

    private InsightRepository repoDelegation;

    public InsightRepoDelegation(RepositoryProvider repositoryProvider,
            SourceConfig sourceConfig) {
        this.repoDelegation = repositoryProvider.getRepository(sourceConfig);
    }

    @Override
    public List<CallDto> getCalls(String callId) {
        return repoDelegation.getCalls(callId);
    }
}
