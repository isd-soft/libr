package com.isd.libr.service;

import com.isd.libr.web.dto.DashboardDto;

/**
 * DashboardService is an interface used for statistics purposes.
 *
 * <p>Preferred implementation {@code DashboardServiceImpl}</p>
 *
 * @author Grosu Kirill
 */
public interface DashboardService {
    /**
     * Used to create statistics endpoint
     * @return {@link DashboardDto} containing all required information in form of statistics.
     */
    DashboardDto getDashboardInfo();
}
