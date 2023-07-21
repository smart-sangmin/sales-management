package org.sangmins.salesmanagement.domain.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VisitJpaRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAllByDate(LocalDate date);
    @Query("SELECT v FROM Visit v WHERE YEAR(v.date) = :year AND MONTH(v.date) = :month")
    List<Visit> findAllByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
