package com.insight.io.insight.repositories;

import com.insight.io.insight.models.PeerConnection;
import com.insight.io.insight.models.UserSession;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
public interface PeerConnectionRepository {

    UserSession.ClientInfo getClientInfoBySid(String sid);

    List<PeerConnection> getPeerConnectionsByMid(String mid);

    List<PeerConnection> getPeerConnectionsBySid(String sid);

    PeerConnection getPeerConnection(String peerConnectionUUID);

}
