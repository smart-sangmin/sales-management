package org.sangmins.salesmanagement.presentation.visit.controller;

import lombok.RequiredArgsConstructor;
import org.sangmins.salesmanagement.presentation.visit.dto.VisitCreationRequest;
import org.sangmins.salesmanagement.presentation.visit.dto.VisitUpdateRequest;
import org.sangmins.salesmanagement.service.visit.VisitService;
import org.sangmins.salesmanagement.service.visit.dto.*;
import org.sangmins.salesmanagement.service.visit.dto.MonthlySalesDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/visit")
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitResponse createVisit(@RequestBody VisitCreationRequest request) {
        return visitService.createVisit(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VisitResponse getVisit(@PathVariable Long id) {
        return visitService.getVisit(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateVisit(@PathVariable Long id, @RequestBody VisitUpdateRequest request) {
        visitService.modifiedVisit(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }

    @GetMapping("/dailysales/{date}")
    @ResponseStatus(HttpStatus.OK)
    public DailySalesDto getTodayVisits(@PathVariable LocalDate date) {
        return visitService.getDailySalesInfo(date);
    }

    @GetMapping("/monthlysales/{year}/{month}")
    @ResponseStatus(HttpStatus.OK)
    public MonthlySalesDto getMonthlySales(@PathVariable int year, @PathVariable int month) {
        return visitService.getMonthlySalesInfo(year, month);
    }
}
