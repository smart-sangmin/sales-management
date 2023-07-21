package org.sangmins.salesmanagement.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Duration {
    private static final String INVALID_TIME = "Invalid start time or end time. start time: %s, end time %s";
    private LocalTime startAt;
    private LocalTime endAt;

    @JsonCreator
    public Duration(@JsonProperty("startAt") LocalTime startAt, @JsonProperty("endAt") LocalTime endAt) {
        if (startAt.isAfter(endAt) || startAt.equals(endAt)) {
            throw new IllegalArgumentException(String.format(INVALID_TIME, startAt, endAt));
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
