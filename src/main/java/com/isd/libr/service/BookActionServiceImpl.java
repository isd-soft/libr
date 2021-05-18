package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.requests.UpdateBooksStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookActionServiceImpl implements BookActionService {
    private final BookActionRepository bookActionRepository;

    @Override
    public List<BookAction> getAllSubmitted(Status status) {
        return bookActionRepository.getAllByStatus(status);
    }

    @Override
    @Transactional
    public BookAction updateStatus(UpdateBooksStatusRequest request) {
        BookAction bookAction = bookActionRepository.getById(request.getBookId());
        bookAction.setStatus(Status.valueOf(request.getNewStatus()));
        BookAction updatedBookAction = bookActionRepository.save(bookAction);
        return updatedBookAction;
    }
}
