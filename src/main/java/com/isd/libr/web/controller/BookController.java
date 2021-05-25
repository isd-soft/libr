package com.isd.libr.web.controller;

import com.isd.libr.service.BookService;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.AddCommentRequest;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public List<BookDto> listBooks() {
        return bookService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestParam("id") Long id) {
        bookService.deleteBookById(id);
    }

    @PostMapping("/save")
    public void saveBook(@RequestBody CreateBookRequest request) {
        bookService.save(request);
    }

}
