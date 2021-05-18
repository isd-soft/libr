package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Person;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.dto.requests.RequestBookRequest;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookActionServiceImpl implements BookActionService {
    private final BookActionRepository bookActionRepository;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Override
    public List<BookAction> getByStatus(Status status) {
        return bookActionRepository.getAllByStatus(status);
    }

    @Override
    @Transactional
    public BookActionDto updateStatus(UpdateBooksStatusRequest request) {
        Person person = personRepository.getById(request.getPersonId());
        Book book = bookRepository.getById(request.getBookId());
        BookAction updatedBookAction = bookActionRepository.save(new BookAction(person, book, LocalDateTime.now(),  Status.valueOf(request.getNewStatus())));
        // mapping Person object inside the updatedBookAction to PersonDto object
        PersonDto personDto = PersonDto.from(updatedBookAction.getPerson());
        // mapping Book object inside the updatedBookAction to BookDto object
        BookDto bookDto = BookDto.from(updatedBookAction.getBook());
        // mapping BookAction object with all data provided above
        return BookActionDto.from(updatedBookAction, personDto, bookDto);

    }

}
