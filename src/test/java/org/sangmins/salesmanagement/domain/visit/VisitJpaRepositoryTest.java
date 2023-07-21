package org.sangmins.salesmanagement.domain.visit;


import org.junit.jupiter.api.Test;
import org.sangmins.salesmanagement.domain.common.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class VisitJpaRepositoryTest {
    @Autowired
    VisitJpaRepository visitJpaRepository;
    Visit visit = Visit.builder()
            .menu(Menu.PERM)
            .price(100000)
            .date(LocalDate.of(2023, 7, 21))
            .duration(new Duration(LocalTime.of(10, 30), LocalTime.of(12, 30)))
            .description("특이사항")
            .build();

    @Test
    void given_손님방문_when_저장_then_빈값아님일치() {
        // given
        // when
        Visit save = visitJpaRepository.save(visit);
        //then
        assertThat(save).isNotNull();
    }

    @Test
    void given_방문생성및저장_when_해당날짜방문모두찾기_then_크기비교일치() {
        // given
        visitJpaRepository.save(visit);
        // when
        List<Visit> allByDate = visitJpaRepository.findAllByDate(visit.getDate());
        // then
        assertThat(allByDate).hasSize(1);
    }

    @Test
    void given_방문생성및저장_when_해당년도및해당달방문모두찾기_then_크기비교일치() {
        // given
        visitJpaRepository.save(visit);
        int year = visit.getDate().getYear();
        int month = visit.getDate().getMonthValue();
        // when
        List<Visit> allByYearAndMonth = visitJpaRepository.findAllByYearAndMonth(year, month);
        // then
        assertThat(allByYearAndMonth).hasSize(1);
    }
}
