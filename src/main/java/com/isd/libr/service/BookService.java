package com.isd.libr.service;

import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> findAll();

    void save(Book book);

    void deleteBookById(long id);

    Optional<BookDto> findBookByName(String keyword);
}
