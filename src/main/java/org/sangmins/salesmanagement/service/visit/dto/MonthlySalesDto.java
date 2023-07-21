package org.sangmins.salesmanagement.service.visit.dto;

import lombok.Builder;
import org.sangmins.salesmanagement.service.visit.dto.stats.SalesPerDateDto;
import org.sangmins.salesmanagement.service.visit.dto.stats.SalesPerDayOfWeekDto;
import org.sangmins.salesmanagement.service.visit.dto.stats.SalesPerMenuDto;
import org.sangmins.salesmanagement.service.visit.dto.stats.VisitsPerHalfHourIntervalDto;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.List;

@Builder
public record MonthlySalesDto(
        YearMonth yearMonth,
        List<DailySalesDto> dailySalesDtos,
        SalesPerDateDto salesPerDateDto,
        SalesPerDayOfWeekDto salesPerDayOfWeekDto,
        SalesPerMenuDto salesPerMenuDto,
        VisitsPerHalfHourIntervalDto visitsPerHalfHourIntervalDto,
        int totalSales,
        int averageDailySales,
        int averageCustomerUnitPrice
) implements Serializable {
}
