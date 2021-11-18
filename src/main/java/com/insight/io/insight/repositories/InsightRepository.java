package com.insight.io.insight.repositories;

import com.insight.io.insight.dto.MeetingDto;

import java.util.List;

/**
 *
 * @author Xiantao Ge
 * @since 0.1
 */
public interface InsightRepository {

    List<MeetingDto> getMeetings(String mid);

}
