package org.sangmins.salesmanagement.service.visit.dto;

import lombok.Builder;
import org.sangmins.salesmanagement.domain.common.Duration;
import org.sangmins.salesmanagement.domain.visit.Menu;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record VisitResponse(
        Long id,
        Menu menu,
        int price,
        LocalDate date,
        Duration duration,
        String description
) implements Serializable {
}
