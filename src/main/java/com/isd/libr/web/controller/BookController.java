package com.isd.libr.web.controller;

import com.isd.libr.service.BookService;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> listBooks() {
        return bookService.findAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@RequestParam("id") Long id) {
        bookService.deleteBookById(id);
    }
    @PostMapping
    public void saveBook(Book book) {
        bookService.save(book);
    }


}
