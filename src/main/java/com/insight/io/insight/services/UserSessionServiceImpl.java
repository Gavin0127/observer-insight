package com.insight.io.insight.services;

import com.insight.io.insight.dto.EventDto;
import com.insight.io.insight.dto.InboundRTPDto;
import com.insight.io.insight.dto.OutboundRTPDto;
import com.insight.io.insight.dto.UserSessionDto;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
@Singleton
public class UserSessionServiceImpl implements UserSessionService {

    @Override
    public UserSessionDto getUserSession(String sid) {
        return mockUserSession();
    }

    private UserSessionDto mockUserSession() {
        return UserSessionDto.builder().mid(UUID.randomUUID().toString())
                .sid(UUID.randomUUID().toString())
                .uid(UUID.randomUUID().toString()).osName("OS").osType(1)
                .startTs(1637771722L).endTs(1637775322L).inboundRTPs(
                        List.of(mockInboundRTP(1637775322L, 10),
                                mockInboundRTP(1637775333L, 100))).outboundRTPs(
                        List.of(mockOutboundRTP(1637775322L, 30),
                                mockOutboundRTP(1637775343L, 200)))
                .events(List.of(mockEvent(1001, 1637775343L),
                        mockEvent(2002, 1637775353L))).build();

    }

    private InboundRTPDto mockInboundRTP(Long timestamp, Integer v) {
        return InboundRTPDto.builder().audioLevel(v).bytesReceived(v)
                .frameReceived(v).frameDecoded(v).keyFrameDecoded(v)
                .frameHeight(v).frameWidth(v).firCount(v).jitterBufferDelay(v)
                .ssrc(v).qpSum(v).timestamp(timestamp).framePerSecond(v)
                .totalAudioEnergy(v).build();
    }

    private OutboundRTPDto mockOutboundRTP(Long timestamp, Integer v) {
        return OutboundRTPDto.builder().totalEncodedBytesTarget(v).bytesSent(v)
                .frameReceived(v).frameEncoded(v).keyFrameEncoded(v)
                .frameHeight(v).frameWidth(v).firCount(v)
                .retransmittedBytesSent(v).ssrc(v).qpSum(v).timestamp(timestamp)
                .framePerSecond(v).build();
    }

    private EventDto mockEvent(Integer uri, Long ts) {
        return EventDto.builder().timestamp(ts).uri(uri)
                .details(uri + " : details: ts: " + ts).build();
    }
}
