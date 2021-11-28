package com.insight.io.insight.repositories;

import com.insight.io.insight.configs.SourceConfig;
import com.insight.io.insight.models.PeerConnection;
import com.insight.io.insight.models.UserSession;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Singleton
@Slf4j
public class PeerConnectionRepoDelegation implements PeerConnectionRepository {

    private PeerConnectionRepository repoDelegation;

    public PeerConnectionRepoDelegation(RepositoryProvider repositoryProvider,
            SourceConfig sourceConfig) {
        this.repoDelegation =
                repositoryProvider.getPeerConnRepo(sourceConfig);
    }

    @Override
    public UserSession.ClientInfo getClientInfoBySid(String sid) {
        return repoDelegation.getClientInfoBySid(sid);
    }

    @Override
    public List<PeerConnection> getPeerConnectionsByMid(String mid) {
        return repoDelegation.getPeerConnectionsByMid(mid);
    }

    @Override
    public List<PeerConnection> getPeerConnectionsBySid(String sid) {
        return repoDelegation.getPeerConnectionsBySid(sid);
    }

    @Override
    public PeerConnection getPeerConnection(String peerConnectionUUID) {
        return repoDelegation.getPeerConnection(peerConnectionUUID);
    }
}
