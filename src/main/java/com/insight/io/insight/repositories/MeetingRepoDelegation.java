package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.models.Meeting;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class MeetingRepoDelegation implements MeetingRepository {

    private MeetingRepository repoDelegation;

    public MeetingRepoDelegation(RepositoryProvider repositoryProvider,
            SourceConfig sourceConfig) {
        this.repoDelegation = repositoryProvider.getMeetingRepo(sourceConfig);
    }

    @Override
    public List<Meeting> getMeetings(String roomName, String uid) {
        return repoDelegation.getMeetings(roomName, uid);
    }

    @Override
    public Meeting getMeeting(String mid) {
        return repoDelegation.getMeeting(mid);
    }
}
