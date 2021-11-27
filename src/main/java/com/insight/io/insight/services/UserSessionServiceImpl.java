package com.insight.io.insight.services;

import com.insight.io.insight.dto.EventDto;
import com.insight.io.insight.dto.InboundTrackDto;
import com.insight.io.insight.dto.OutboundTrackDto;
import com.insight.io.insight.dto.UserSessionDto;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Xiantao Ge
 * @since 0.1
 */
@Slf4j
@Singleton
public class UserSessionServiceImpl implements UserSessionService {

    public static UserSessionDto mockUserSession() {
        return UserSessionDto.builder().roomName("avc_data_hub").uid("Hef")
                .platformVendor("apple").osName("macOS").osVersion("10.15.7")
                .browserName("Chrome").browserVersion("96.0.4664.45")
                .engineName("Blink").startTs(1637771722L).endTs(1637775322L)
                .inboundTracks(
                        List.of(mockInboundTrack("receiver_1", 123, "tom", 0),
                                mockInboundTrack("receiver_2", 321, "tom", 1)))
                .outboundTracks(List.of(mockOutboundTrack("sender_1", 789, 0),
                        mockOutboundTrack("sender_2", 987, 1)))
                .events(List.of(mockEvent(1001, 1637775343L),
                        mockEvent(2002, 1637775353L))).build();

    }

    public static InboundTrackDto mockInboundTrack(String trackId, Integer ssrc,
            String userId, Integer mediaType) {
        return InboundTrackDto.builder().trackId(trackId).ssrc(ssrc).uid(userId)
                .mediaType(mediaType).inboundSamples(
                        List.of(mockInboundSample(1637775322L, 10),
                                mockInboundSample(1637775333L, 100))).build();
    }

    public static OutboundTrackDto mockOutboundTrack(String trackId,
            Integer ssrc, Integer mediaType) {
        return OutboundTrackDto.builder().trackId(trackId).ssrc(ssrc)
                .mediaType(mediaType).outboundSamples(
                        List.of(mockOutboundSample(1637775322L, 22),
                                mockOutboundSample(1637775433L, 310))).build();
    }

    private static InboundTrackDto.InboundSampleDto mockInboundSample(
            Long timestamp, Integer v) {
        return InboundTrackDto.InboundSampleDto.builder().audioLevel(v)
                .bytesReceived(v).frameReceived(v).frameDecoded(v)
                .keyFrameDecoded(v).frameHeight(v).frameWidth(v).firCount(v)
                .jitterBufferDelay(v).qpSum(v).ts(timestamp).framePerSecond(v)
                .totalAudioEnergy(v).build();
    }

    private static OutboundTrackDto.OutboundSampleDto mockOutboundSample(
            Long timestamp, Integer v) {
        return OutboundTrackDto.OutboundSampleDto.builder()
                .totalEncodedBytesTarget(v).bytesSent(v).frameEncoded(v)
                .keyFrameEncoded(v).frameHeight(v).frameWidth(v).firCount(v)
                .retransmittedBytesSent(v).qpSum(v).ts(timestamp)
                .framePerSecond(v).build();
    }

    private static EventDto mockEvent(Integer uri, Long ts) {
        return EventDto.builder().ts(ts).uri(uri)
                .content(uri + " : details: ts: " + ts).build();
    }

    @Override
    public UserSessionDto getUserSession(String sid) {
        return mockUserSession();
    }
}
