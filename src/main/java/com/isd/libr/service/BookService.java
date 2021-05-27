package com.isd.libr.service;

import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.dto.requests.UpdateBookRequest;

import java.util.List;

public interface BookService {
    List<BookDto> listBooksWithNotStatusRejected();

    List<BookDto> findAll();

    BookDto getById(long id);

    void save(CreateBookRequest request);

    void deleteBookById(long id);

    BookDto updateBook(Long id, UpdateBookRequest request);
}
