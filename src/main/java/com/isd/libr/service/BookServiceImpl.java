package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class BookServiceImpl implements BookService {
    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> findAll() {
      List<Book> bookList =  bookRepository.findAll();
      List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
          bookDtoList.add(convertToBookDto(bookList.get(i)));
        }
    return bookDtoList;
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
    public Optional<BookDto> findBookByName(String keyword) {
       Optional<Book> bookByName =  bookRepository.findByTitle(keyword);
       return bookByName.map(this::convertToBookDto);
    }

    public BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setCategories(book.getCategories());
        bookDto.setDescription(book.getDescription());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPageCount(book.getPageCount());
        bookDto.setPublishedDate(book.getPublishedDate());
        bookDto.setAverageRating(book.getAverageRating());
        return bookDto;
    }
}


