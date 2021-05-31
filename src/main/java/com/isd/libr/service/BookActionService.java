package com.isd.libr.service;

import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookActionInfoDto;
import com.isd.libr.web.dto.requests.BookInfoRequest;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;

import java.util.List;
import java.util.Map;

public interface BookActionService {
    List<BookActionDto> getByStatus(Status status);

    BookActionDto updateStatus(UpdateBooksStatusRequest request);

    BookActionInfoDto getInfo(BookInfoRequest request);

    List<BookActionDto> getAllSubmissionActionsLastMonthForDashboard();

    Map<String, Integer> getAllSubmittedAndInLibrary();
}
