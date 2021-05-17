package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.web.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookServiceImpl implements BookService {

    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }


    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public List<Book> findBookByName(String keyword) {
        return bookRepository.findAllByTitle(keyword);
    }
}


