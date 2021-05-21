package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book bookItem :
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



