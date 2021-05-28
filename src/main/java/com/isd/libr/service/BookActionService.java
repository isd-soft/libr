package com.isd.libr.service;

import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookActionInfoDto;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;

import java.util.List;

public interface BookActionService {
    List<BookAction> getByStatus(Status status);

    BookActionDto updateStatus(UpdateBooksStatusRequest request);

 BookActionInfoDto getInfo(Long id);

}
