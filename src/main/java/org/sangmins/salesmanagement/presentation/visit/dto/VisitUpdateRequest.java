package org.sangmins.salesmanagement.presentation.visit.dto;

import org.sangmins.salesmanagement.domain.common.Duration;
import org.sangmins.salesmanagement.domain.visit.Menu;

public record VisitUpdateRequest(
        Menu menu,
        int price,
        Duration duration,
        String description
) {
}
