package org.sangmins.salesmanagement.service.visit;

import org.sangmins.salesmanagement.domain.visit.DailySales;
import org.sangmins.salesmanagement.domain.visit.MonthlySales;
import org.sangmins.salesmanagement.domain.visit.Visit;
import org.sangmins.salesmanagement.domain.visit.stats.SalesPerDate;
import org.sangmins.salesmanagement.domain.visit.stats.SalesPerDayOfWeek;
import org.sangmins.salesmanagement.domain.visit.stats.SalesPerMenu;
import org.sangmins.salesmanagement.domain.visit.stats.VisitsPerHalfHourInterval;
import org.sangmins.salesmanagement.service.visit.dto.DailySalesDto;
import org.sangmins.salesmanagement.service.visit.dto.MonthlySalesDto;
import org.sangmins.salesmanagement.presentation.visit.dto.VisitCreationRequest;
import org.sangmins.salesmanagement.service.visit.dto.VisitResponse;
import org.sangmins.salesmanagement.service.visit.dto.stats.SalesPerDateDto;
import org.sangmins.salesmanagement.service.visit.dto.stats.SalesPerDayOfWeekDto;
import org.sangmins.salesmanagement.service.visit.dto.stats.SalesPerMenuDto;
import org.sangmins.salesmanagement.service.visit.dto.stats.VisitsPerHalfHourIntervalDto;

import java.util.List;

public final class VisitDtoMapper {
    private VisitDtoMapper() {
    }

    public static Visit toVisit(VisitCreationRequest request) {
        return Visit.builder()
                .menu(request.menu())
                .price(request.price())
                .date(request.date())
                .description(request.description())
                .duration(request.duration())
                .build();
    }

    public static VisitResponse toVisitResponse(Visit visit) {
        return VisitResponse.builder()
                .id(visit.getId())
                .menu(visit.getMenu())
                .menu(visit.getMenu())
                .price(visit.getPrice())
                .date(visit.getDate())
                .description(visit.getDescription())
                .duration(visit.getDuration())
                .build();
    }

    public static DailySalesDto toDailySalesDto(DailySales dailySales) {
        List<VisitResponse> visitResponses = dailySales.getDailyVisits().stream()
                .map(VisitDtoMapper::toVisitResponse)
                .toList();

        return DailySalesDto.builder()
                .dailyVisitsResponses(visitResponses)
                .totalSales(dailySales.getTotalSales())
                .averageCustomerUnitPrice(dailySales.getAverageCustomerUnitPrice())
                .build();
    }

    public static MonthlySalesDto toMonthlySalesDto(MonthlySales monthlySales) {
        List<DailySalesDto> dailySalesDtos = monthlySales.getDailySalesList()
                .stream()
                .map(VisitDtoMapper::toDailySalesDto)
                .toList();

        SalesPerDate salesPerDate = monthlySales.getSalesPerDate();
        SalesPerDateDto salesPerDateDto = VisitDtoMapper.toSalesPerDateDto(salesPerDate);

        SalesPerDayOfWeek salesPerDayOfWeek = monthlySales.getSalesPerDayOfWeek();
        SalesPerDayOfWeekDto salesPerDayOfWeekDto = VisitDtoMapper.toSalesPerDayOfWeekDto(salesPerDayOfWeek);

        SalesPerMenu salesPerMenu = monthlySales.getSalesPerMenu();
        SalesPerMenuDto salesPerMenuDto = VisitDtoMapper.toSalesPerMenuDto(salesPerMenu);

        VisitsPerHalfHourInterval visitsPerHalfHourInterval = monthlySales.getVisitsPerHalfHourInterval();
        VisitsPerHalfHourIntervalDto visitsPerHalfHourIntervalDto = VisitDtoMapper.toVisitsPerHalfHourIntervalDto(visitsPerHalfHourInterval);


        return MonthlySalesDto.builder()
                .yearMonth(monthlySales.getYearMonth())
                .dailySalesDtos(dailySalesDtos)
                .salesPerDateDto(salesPerDateDto)
                .salesPerDayOfWeekDto(salesPerDayOfWeekDto)
                .salesPerMenuDto(salesPerMenuDto)
                .visitsPerHalfHourIntervalDto(visitsPerHalfHourIntervalDto)
                .totalSales(monthlySales.getTotalSales())
                .averageDailySales(monthlySales.getAverageDailySales())
                .averageCustomerUnitPrice(monthlySales.getAverageCustomerUnitPrice())
                .build();
    }

    private static SalesPerDateDto toSalesPerDateDto(SalesPerDate salesPerDate) {
        return SalesPerDateDto.builder()
                .stats(salesPerDate.getStats())
                .build();
    }

    private static SalesPerDayOfWeekDto toSalesPerDayOfWeekDto(SalesPerDayOfWeek salesPerDayOfWeek) {
        return SalesPerDayOfWeekDto.builder()
                .stats(salesPerDayOfWeek.getStats())
                .build();
    }

    private static SalesPerMenuDto toSalesPerMenuDto(SalesPerMenu salesPerMenu) {
        return SalesPerMenuDto.builder()
                .stats(salesPerMenu.getStats())
                .build();
    }

    private static VisitsPerHalfHourIntervalDto toVisitsPerHalfHourIntervalDto(VisitsPerHalfHourInterval visitsPerHalfHourInterval) {
        return VisitsPerHalfHourIntervalDto.builder()
                .stats(visitsPerHalfHourInterval.getStats())
                .build();
    }
}
