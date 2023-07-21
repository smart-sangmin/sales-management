package org.sangmins.salesmanagement.domain.visit;

import lombok.Getter;
import org.sangmins.salesmanagement.domain.visit.stats.SalesPerDate;
import org.sangmins.salesmanagement.domain.visit.stats.SalesPerDayOfWeek;
import org.sangmins.salesmanagement.domain.visit.stats.SalesPerMenu;
import org.sangmins.salesmanagement.domain.visit.stats.VisitsPerHalfHourInterval;
import org.sangmins.salesmanagement.domain.visit.utils.VisitUtils;
import org.sangmins.salesmanagement.global.MathUtils;

import java.time.YearMonth;
import java.util.List;

@Getter
public class MonthlySales {
    private final List<DailySales> dailySalesList;
    private final SalesPerDate salesPerDate;
    private final SalesPerMenu salesPerMenu;
    private final SalesPerDayOfWeek salesPerDayOfWeek;
    private final VisitsPerHalfHourInterval visitsPerHalfHourInterval;
    private final YearMonth yearMonth;
    private final int totalSales;
    private final int averageDailySales;
    private final int averageCustomerUnitPrice;

    public MonthlySales(List<Visit> allVisitsInMonth, int year, int month) {
        this.dailySalesList = VisitUtils.convertVisitsToDailySales(allVisitsInMonth);
        this.salesPerDate = new SalesPerDate(dailySalesList);
        this.salesPerMenu = new SalesPerMenu(allVisitsInMonth);
        this.salesPerDayOfWeek = new SalesPerDayOfWeek(dailySalesList);
        this.visitsPerHalfHourInterval = new VisitsPerHalfHourInterval(allVisitsInMonth);
        this.yearMonth = YearMonth.of(year, month);
        this.totalSales = VisitUtils.calculateTotalSales(allVisitsInMonth);
        this.averageDailySales = MathUtils.roundTensPlace(totalSales, this.yearMonth.lengthOfMonth());
        this.averageCustomerUnitPrice = MathUtils.roundTensPlace(totalSales, allVisitsInMonth.size());
    }
}
