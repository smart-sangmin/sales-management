package org.sangmins.salesmanagement.service.visit.dto.stats;

import lombok.Builder;
import org.sangmins.salesmanagement.domain.visit.Menu;

import java.util.Map;
@Builder
public record SalesPerMenuDto(
        Map<Menu, Integer> stats
) {
}
