package com.isd.libr.service;

import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();


    void save(CreateBookRequest request);

    void deleteBookById(long id);

}
