package org.sangmins.salesmanagement.domain.visit;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sangmins.salesmanagement.domain.common.BaseEntity;
import org.sangmins.salesmanagement.domain.common.Duration;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Menu menu;
    private int price;
    private LocalDate date;
    @Embedded
    private Duration duration;
    private String description;

    @Builder
    public Visit(Menu menu, int price, LocalDate date, @Nullable Duration duration, String description) {
        this.menu = menu;
        this.price = price;
        this.date = date;
        this.duration = duration;
        this.description = description;
    }

    public void modifiedMenu(Menu menu) {
        this.menu = menu;
    }

    public void modifiedPrice(int price) {
        this.price = price;
    }

    public void modifiedDuration(Duration duration) {
        this.duration = duration;
    }

    public void modifiedDescription(String description) {
        this.description = description;
    }
}
