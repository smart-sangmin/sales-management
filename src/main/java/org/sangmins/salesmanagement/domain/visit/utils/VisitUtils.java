package org.sangmins.salesmanagement.domain.visit.utils;

import org.sangmins.salesmanagement.domain.visit.DailySales;
import org.sangmins.salesmanagement.domain.visit.Visit;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class VisitUtils {
    private VisitUtils() {
    }

    public static int calculateTotalSales(List<Visit> visits) {
        return visits.stream()
                .mapToInt(Visit::getPrice)
                .sum();
    }

    public static List<DailySales> convertVisitsToDailySales(List<Visit> visits) {
        return toDailySalesList(groupVisitsByDate(visits));
    }

    private static Map<LocalDate, List<Visit>> groupVisitsByDate(List<Visit> allVisitsForAWeek) {
        return allVisitsForAWeek.stream()
                .collect(Collectors.groupingBy(Visit::getDate));
    }

    private static List<DailySales> toDailySalesList(Map<LocalDate, List<Visit>> visitsByDate) {
        return visitsByDate.entrySet()
                .stream()
                .map(entry -> new DailySales(entry.getKey(), entry.getValue()))
                .toList();
    }
}
