package org.sangmins.salesmanagement.domain.visit.stats;

import lombok.Getter;
import org.sangmins.salesmanagement.domain.visit.DailySales;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SalesPerDate {
    private final Map<LocalDate, Integer> stats;

    public SalesPerDate(List<DailySales> dailySalesList) {
        this.stats = calculateSalesPerDate(dailySalesList);
    }

    private Map<LocalDate, Integer> calculateSalesPerDate(List<DailySales> dailySales) {
        return dailySales.stream()
                .collect(
                        Collectors.groupingBy(
                                DailySales::getDate,
                                Collectors.summingInt(DailySales::getTotalSales)
                        )
                );
    }
}
