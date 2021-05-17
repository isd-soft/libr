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

    @Autowired
    //Program by Interface
    private final BookService bookService;

    @GetMapping
    public List<BookDto> listBooks(Model model) {
//        bookService.findAll();
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam("id") Long id) {
//        bookService.deleteBookById(id);
    }

    @GetMapping("/search")
    public BookDto findBookByName(@RequestParam("keyword") String keyword) {
//        bookService.findBookByName(keyword);
        return null;
    }

    @PostMapping("/save")
    public void saveBook(Book book) {
        // save the book
        bookService.save(book);
    }


}
