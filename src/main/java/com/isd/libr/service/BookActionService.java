package com.isd.libr.service;

import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.requests.UpdateBooksStatusRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface BookActionService {
    List<BookAction> getAllSubmitted(Status status);

    @Transactional
    BookAction updateStatus(UpdateBooksStatusRequest request);
}
