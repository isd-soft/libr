package com.isd.libr.web.controller;

import com.isd.libr.service.*;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.DashboardDto;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<?> getStatisticsForDashboard() {
        DashboardDto dashboardDto = dashboardService.getDashboardInfo();
        return ResponseEntity.ok(dashboardDto);
    }
}
