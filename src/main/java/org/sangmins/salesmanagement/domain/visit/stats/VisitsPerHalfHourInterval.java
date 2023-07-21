package org.sangmins.salesmanagement.domain.visit.stats;

import lombok.Getter;
import org.sangmins.salesmanagement.domain.common.Duration;
import org.sangmins.salesmanagement.domain.visit.Visit;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class VisitsPerHalfHourInterval {
    private static final int TIME_INTERVAL = 30;
    private final Map<String, Integer> stats;

    public VisitsPerHalfHourInterval(List<Visit> allVisitsInMonth) {
        this.stats = countVisitsPerHalfHour(allVisitsInMonth);
    }

    private Map<String, Integer> countVisitsPerHalfHour(List<Visit> allVisitsInMonth) {
        return halfHourInterval()
                .map(time -> new Duration(time, time.plusMinutes(TIME_INTERVAL)))
                .collect(
                        Collectors.toMap(
                                duration -> String.format("%s ~ %s", duration.getStartAt(), duration.getEndAt()),
                                duration -> (int) allVisitsInMonth.stream()
                                        .filter(visit -> isInHalfHourInterval(visit, duration))
                                        .count()
                        )
                );
    }

    private static Stream<LocalTime> halfHourInterval() {
        return Stream.iterate(
                LocalTime.of(9, 0),
                time -> time.isBefore(LocalTime.of(21, 0)),
                time -> time.plusMinutes(TIME_INTERVAL)
        );  // TODO: 추후 User 에서 OpeningTime, ClosingTime 가져오기
    }

    private boolean isInHalfHourInterval(Visit visit, Duration duration) {
        LocalTime visitStart = visit.getDuration().getStartAt();
        LocalTime visitEnd = visit.getDuration().getEndAt();
        LocalTime durationStart = duration.getStartAt();
        LocalTime durationEnd = duration.getEndAt();

        return !(durationStart.isAfter(visitEnd) || durationEnd.isBefore(visitStart) ||
                durationStart.equals(visitEnd) || durationEnd.equals(visitStart));
    }
}
