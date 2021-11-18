package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.dto.MeetingDto;
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
    public List<MeetingDto> getMeetings(String mid) {
        return repoDelegation.getMeetings(mid);
    }
}
