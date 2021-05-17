package com.isd.libr.service;

import com.isd.libr.web.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    void save(Book book);

    void deleteBookById(long id);

    List<Book> findBookByName(String keyword);
}
