package org.sangmins.salesmanagement.domain.visit.stats;

import lombok.Getter;
import org.sangmins.salesmanagement.domain.visit.DailySales;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SalesPerDayOfWeek {
    private final Map<DayOfWeek, Integer> stats;

    public SalesPerDayOfWeek(List<DailySales> dailySalesList) {
        this.stats = calculateSalesPerDayOfWeek(dailySalesList);
    }

    private Map<DayOfWeek, Integer> calculateSalesPerDayOfWeek(List<DailySales> dailySalesList) {
        return dailySalesList.stream()
                .collect(
                        Collectors.groupingBy(
                                DailySales::getDayOfWeek,
                                Collectors.summingInt(DailySales::getTotalSales)
                        )
                );
    }
}
