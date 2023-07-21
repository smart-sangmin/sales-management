package org.sangmins.salesmanagement.domain.visit.stats;

import lombok.Getter;
import org.sangmins.salesmanagement.domain.visit.Menu;
import org.sangmins.salesmanagement.domain.visit.Visit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SalesPerMenu {
    private final Map<Menu, Integer> stats;

    public SalesPerMenu(List<Visit> allVisitsInMonth) {
        this.stats = calculateSalesPerMenu(allVisitsInMonth);
    }

    private Map<Menu, Integer> calculateSalesPerMenu(List<Visit> allVisitsInMonth) {
        return allVisitsInMonth.stream()
                .collect(
                        Collectors.groupingBy(
                                Visit::getMenu,
                                Collectors.summingInt(Visit::getPrice)
                        )
                );
    }
}
