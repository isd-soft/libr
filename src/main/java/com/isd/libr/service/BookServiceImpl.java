package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookActionRepository bookActionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
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
    @Transactional
    public BookDto getById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", id));
        }
        return BookDto.from(book.get(), book.get().getComments());
    }

    @Override
    @Transactional
    public void save(CreateBookRequest request) throws BookDuplicateException {
        Optional<Book> existingBook = bookRepository.getByTitle(request.getTitle());
        if (existingBook.isPresent()) {
            throw new BookDuplicateException("This book is already in our database");
        }
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
        Optional<User> byId = userRepository.findById(request.getUserId());
        bookActionRepository.save(new BookAction(byId.get(),book, LocalDateTime.now(), Status.SUBMITTED));
    }


    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }




}



