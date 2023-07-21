package org.sangmins.salesmanagement.service.visit.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record DailySalesDto(
        List<VisitResponse> dailyVisitsResponses,
        int totalSales,
        int averageCustomerUnitPrice

) implements Serializable {
}
