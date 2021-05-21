package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
class BookActionServiceImpl implements BookActionService {
    private final BookActionRepository bookActionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<BookAction> getByStatus(Status status) {
        return bookActionRepository.getAllByStatus(status);
    }

    @Override
    @Transactional
    public BookActionDto updateStatus(UpdateBooksStatusRequest request) {
        User user = userRepository.getById(request.getPersonId());
        Book book = bookRepository.getById(request.getBookId());
        BookAction bookAction = new BookAction(user, book, LocalDateTime.now(), Status.valueOf(request.getNewStatus()));
        BookAction updatedBookAction = bookActionRepository.save(bookAction);
        // mapping User object inside the updatedBookAction to UserDto object
        UserDto userDto = UserDto.from(updatedBookAction.getUser());
        // mapping Book object inside the updatedBookAction to BookDto object
        // mapping Book object inside the updatedBookAction to BookDto object
        BookDto bookDto = BookDto.from(updatedBookAction.getBook());
        // mapping BookAction object with all data provided above
        return BookActionDto.from(updatedBookAction, userDto, bookDto);

    }






}
