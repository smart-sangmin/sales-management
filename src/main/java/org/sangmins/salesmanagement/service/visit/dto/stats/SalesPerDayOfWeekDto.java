package org.sangmins.salesmanagement.service.visit.dto.stats;

import lombok.Builder;

import java.time.DayOfWeek;
import java.util.Map;
@Builder
public record SalesPerDayOfWeekDto(
        Map<DayOfWeek, Integer> stats
) {
}
