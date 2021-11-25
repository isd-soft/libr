package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.service.BookActionService;
import com.isd.libr.exceptions.BookNotFoundException;
import com.isd.libr.exceptions.UserNotFoundException;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookActionInfoDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.BookInfoRequest;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Implementation for {@link BookActionService}
 *
 * @author Grosu Kirill
 */
@RequiredArgsConstructor
@Service
class BookActionServiceImpl implements BookActionService {
    private final BookActionRepository bookActionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<BookActionDto> getByStatus(Status status) {
        List<BookAction> bookActions = bookActionRepository.getAllByStatus(status.name());
        List<BookActionDto> bookActionDtos = new ArrayList<>();
        for (BookAction action : bookActions) {
            UserDto userDto = UserDto.from(action.getUser());
            BookDto bookDto = BookDto.from(action.getBook());
            bookActionDtos.add(BookActionDto.from(action, userDto, bookDto));
        }
        return bookActionDtos;
    }

    @Override
    @Transactional
    public BookActionDto updateStatus(UpdateBooksStatusRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", request.getUserId()));
        }
        Optional<Book> book = bookRepository.findById(request.getBookId());
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", request.getBookId()));
        }
        BookAction bookAction = new BookAction(user.get(), book.get(), LocalDateTime.now(), Status.valueOf(request.getNewStatus()));
        BookAction updatedBookAction = bookActionRepository.save(bookAction);
        UserDto userDto = UserDto.from(updatedBookAction.getUser());
        BookDto bookDto = BookDto.from(updatedBookAction.getBook());
        return BookActionDto.from(updatedBookAction, userDto, bookDto);
    }

    public BookActionInfoDto getInfo(BookInfoRequest request) {
        BookAction bookAction = bookActionRepository.findLastActionByBookIdAndStatus(request.getId(), request.getStatus().toString());
        String userFirstName = bookAction.getUser().getFirstName();
        String userLastName = bookAction.getUser().getLastName();
        String date = bookAction.getActionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String actualStatus = bookAction.getStatus().toString();
        return BookActionInfoDto.from(userFirstName, userLastName, date, actualStatus);
    }

    @Override
    public Map<Integer, Integer> getAllSubmissionActionsLastMonthForDashboard() {
        List<Object[]> queryResult = bookActionRepository.getAllSubmissionActionsLastMonthForDashboard();
        Map<Integer, Integer> mappedResult = new HashMap<>();
        for (Object[] obj : queryResult ) {
            mappedResult.put(((Double) obj[0]).intValue(), ((BigInteger) obj[1]).intValue());
        }
        Map<Integer, Integer> result = new HashMap<>();
        LocalDate start = LocalDate.now().minusWeeks(4).plusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);
        while(!start.isEqual(end)){
            int currentWeekNumber = start.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
            result.put(currentWeekNumber, mappedResult.getOrDefault(currentWeekNumber, 0));
            start = start.plusWeeks(1);
        }
        return result;
    }

    @Override
    public Map<String, Integer> getAllSubmittedAndInLibrary() {
        Integer submitted = bookActionRepository.countByStatus(Status.SUBMITTED);
        Integer inLibrary = bookActionRepository.countByStatus(Status.IN_LIBRARY);
        Map<String, Integer> result = new HashMap<>();
        result.put("SUBMITTED", submitted);
        result.put("IN_LIBRARY", inLibrary);
        return result;
    }
}




