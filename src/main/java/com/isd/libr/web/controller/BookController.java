package com.isd.libr.web.controller;

import com.isd.libr.service.BookService;
import com.isd.libr.web.dto.BookDto;
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

    @GetMapping("/**")
    public List<BookDto> listBooks() {
        return bookService.findAll();
    }
    // /books/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
    bookService.deleteBookById(id);
    }

    //books/effective
    @GetMapping("/{keyword}")
    public ResponseEntity<Optional<BookDto>> findBookByName(@PathVariable("keyword") String keyword) {
        Optional<BookDto> bookByName = bookService.findBookByName(keyword);
        return ResponseEntity.ok(bookByName);

    }
    @PostMapping
    public void saveBook(@RequestBody Book book) {
        bookService.save(book);
    }


}
