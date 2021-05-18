package com.isd.libr.web.controller;

import com.isd.libr.service.BookActionService;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.requests.UpdateBooksStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-actions")
public class BookActionController {
    private final BookActionService bookActionService;

    @GetMapping("/{status}")
    public ResponseEntity<?> getSubmittedBooks(@PathVariable Status status) {
        List<BookAction> actions = bookActionService.getAllSubmitted(status);
        return ResponseEntity.ok(actions);
    }

    @PostMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateBooksStatusRequest request) {
        BookAction updatedBookAction = bookActionService.updateStatus(request);
        // mapping Person object inside the updatedBookAction to PersonDto object
        PersonDto personDto = new PersonDto();
        personDto.setEmail(updatedBookAction.getPerson().getEmail());
        personDto.setFullName(updatedBookAction.getPerson().getFirstName() + updatedBookAction.getPerson().getLastName());
        personDto.setPhone(updatedBookAction.getPerson().getPhone());
        // mapping Book object inside the updatedBookAction to BookDto object
        BookDto bookDto = new BookDto();
        bookDto.setTitle(updatedBookAction.getBook().getTitle());
        bookDto.setAuthors(updatedBookAction.getBook().getAuthors());
        bookDto.setPublishedDate(updatedBookAction.getBook().getPublishedDate());
        bookDto.setDescription(updatedBookAction.getBook().getDescription());
        bookDto.setPageCount(updatedBookAction.getBook().getPageCount());
        bookDto.setCategories(updatedBookAction.getBook().getCategories());
        bookDto.setAverageRating(updatedBookAction.getBook().getAverageRating());
        bookDto.setLanguage(updatedBookAction.getBook().getLanguage());
        // mapping BookAction object with all data
        BookActionDto bookActionDto = new BookActionDto();
        bookActionDto.setId(updatedBookAction.getId());
        bookActionDto.setPersons(personDto);
        bookActionDto.setBooks(bookDto);
        bookActionDto.setStatus(String.valueOf(updatedBookAction.getStatus()));
        bookActionDto.setActionDate(updatedBookAction.getActionDate());
        return ResponseEntity.ok(bookActionDto);
    }
}
