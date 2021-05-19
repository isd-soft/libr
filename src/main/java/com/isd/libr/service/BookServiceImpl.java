package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(BookDto::from).collect(Collectors.toList());
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }


    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}



