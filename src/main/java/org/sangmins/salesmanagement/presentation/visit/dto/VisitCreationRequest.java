package org.sangmins.salesmanagement.presentation.visit.dto;

import org.sangmins.salesmanagement.domain.common.Duration;
import org.sangmins.salesmanagement.domain.visit.Menu;

import java.time.LocalDate;

public record VisitCreationRequest(
        Menu menu,
        int price,
        LocalDate date,
        Duration duration,
        String description
) {
}
