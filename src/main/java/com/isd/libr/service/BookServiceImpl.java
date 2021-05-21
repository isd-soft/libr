package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.CommentRepository;
import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.CommentDto;
import com.isd.libr.web.dto.requests.AddCommentRequest;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book bookItem:
             books) {
            BookDto bookDto = BookDto.from(bookItem, bookItem.getComments());
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public void save(CreateBookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .authors(request.getAuthors())
                .publisher(request.getPublisher())
                .publishedDate(request.getPublishedDate())
                .description(request.getDescription())
                .industryIdentifiers(request.getIndustryIdentifiers())
                .pageCount(request.getPageCount())
                .categories(request.getCategories())
                .averageRating(request.getAverageRating())
                .ratingCount(request.getPageCount())
                .maturityRating(request.getMaturityRating())
                .imageLinks(request.getImageLinks())
                .language(request.getLanguage())
                .previewLink(request.getPreviewLink())
                .build();
        bookRepository.save(book);
    }


    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }


}



