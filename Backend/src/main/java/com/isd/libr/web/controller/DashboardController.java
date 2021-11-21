package com.isd.libr.web.controller;

import com.isd.libr.service.DashboardService;
import com.isd.libr.web.dto.DashboardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<?> getStatisticsForDashboard() {
        DashboardDto dashboardDto = dashboardService.getDashboardInfo();
        return ResponseEntity.ok(dashboardDto);
    }
}
