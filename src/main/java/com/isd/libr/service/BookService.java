package com.isd.libr.service;

import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();


    void save(CreateBookRequest request);

    void deleteBookById(long id);

}
