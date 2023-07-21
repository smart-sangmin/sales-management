package org.sangmins.salesmanagement.service.visit.dto.stats;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record SalesPerDateDto(
        Map<LocalDate, Integer> stats
) {
}
