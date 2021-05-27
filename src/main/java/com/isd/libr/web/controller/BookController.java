package com.isd.libr.web.controller;

import com.isd.libr.service.BookService;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id){
        BookDto book = bookService.getById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/filtered")
    public List<BookDto> listBooksWithNotStatusRejected() {
        return bookService.listBooksWithNotStatusRejected();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BookDto> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestParam("id") Long id) {
        bookService.deleteBookById(id);
    }

    @PostMapping
    public void saveBook(@RequestBody CreateBookRequest request) {
        bookService.save(request);
    }


}

