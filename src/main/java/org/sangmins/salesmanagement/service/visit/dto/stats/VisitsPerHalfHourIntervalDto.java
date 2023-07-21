package org.sangmins.salesmanagement.service.visit.dto.stats;

import lombok.Builder;

import java.util.Map;

@Builder
public record VisitsPerHalfHourIntervalDto(
        Map<String, Integer> stats
) {
}
