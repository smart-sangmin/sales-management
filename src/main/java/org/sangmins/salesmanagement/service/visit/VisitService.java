package org.sangmins.salesmanagement.service.visit;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sangmins.salesmanagement.domain.visit.MonthlySales;
import org.sangmins.salesmanagement.domain.visit.Visit;
import org.sangmins.salesmanagement.domain.visit.VisitJpaRepository;
import org.sangmins.salesmanagement.domain.visit.DailySales;
import org.sangmins.salesmanagement.presentation.visit.dto.VisitCreationRequest;
import org.sangmins.salesmanagement.presentation.visit.dto.VisitUpdateRequest;
import org.sangmins.salesmanagement.service.visit.dto.*;
import org.sangmins.salesmanagement.service.visit.dto.MonthlySalesDto;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {
    private static final String VISIT_NOT_FOUND = "Visit not found for that id: %s";
    private final VisitJpaRepository visitJpaRepository;

    @Transactional
    public VisitResponse createVisit(VisitCreationRequest request) {
        Visit visit = VisitDtoMapper.toVisit(request);
        Visit saved = visitJpaRepository.save(visit);
        return VisitDtoMapper.toVisitResponse(saved);
    }

    @Transactional
    public void modifiedVisit(Long id, VisitUpdateRequest request) {
        Visit visit = visitJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(VISIT_NOT_FOUND, id)));

        visit.modifiedMenu(request.menu());
        visit.modifiedPrice(request.price());
        visit.modifiedDescription(request.description());
        visit.modifiedDuration(request.duration());
    }

    public VisitResponse getVisit(Long id) {
        return VisitDtoMapper.toVisitResponse(
                visitJpaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(String.format(VISIT_NOT_FOUND, id)))
        );
    }

    public DailySalesDto getDailySalesInfo(LocalDate date) {
        List<Visit> dailyVisits = visitJpaRepository.findAllByDate(date);
        DailySales dailySales = new DailySales(date, dailyVisits);
        return VisitDtoMapper.toDailySalesDto(dailySales);
    }

    public MonthlySalesDto getMonthlySalesInfo(int year, int month) {
        List<Visit> allVisitsInMount = visitJpaRepository.findAllByYearAndMonth(year, month);
        MonthlySales monthlySales = new MonthlySales(allVisitsInMount, year, month);
        return VisitDtoMapper.toMonthlySalesDto(monthlySales);
    }

    public void deleteVisit(Long id) {
        visitJpaRepository.deleteById(id);
    }
}
