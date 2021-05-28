package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.dto.requests.UpdateBookRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookActionRepository bookActionRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public List<BookDto> listBooksWithNotStatusRejected() {

        List<Book> books = bookRepository.listBooksWithNotStatusRejected();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book bookItem :
                books) {
            BookDto bookDto = BookDto.from(bookItem, bookItem.getComments());
            bookDtos.add(bookDto);
        }
        bookDtos.sort(Comparator.comparing(BookDto::getVote).reversed());
        return bookDtos;
    }

    @Override
    @Transactional
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            BookDto bookDto = BookDto.from(book, book.getComments());
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
        bookRepository.getByTitle(request.getTitle())
                .orElseThrow(() -> new BookDuplicateException("This book is already in our database"));
        User userThatRequestedTheBook = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("User with [%s] not found",
                        request.getUserId())));
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

        bookActionRepository.save(new BookAction(userThatRequestedTheBook,
                book, LocalDateTime.now(), Status.SUBMITTED));
        sendSubmittedEmail(book);
    }

    private void sendSubmittedEmail(Book book) {
        String[] admins = userRepository.findByRole("ADMIN")
                .stream().map(User::getEmail)
                .toArray(String[]::new);
        String text = String.format(" The book %s submitted", book.getTitle());
        emailService.sendEmailNotification(text, admins);
    }


    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookActionRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookDto updateBook(Long id, UpdateBookRequest request) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", id));
        }
        Book book = optionalBook.get();
        book.setTitle(request.getTitle());
        book.setAuthors(request.getAuthors());
        book.setPublisher(request.getPublisher());
        book.setPublishedDate(request.getPublishedDate());
        book.setDescription(request.getDescription());
        book.setIndustryIdentifiers(request.getIndustryIdentifiers());
        book.setPageCount(request.getPageCount());
        book.setCategories(request.getCategories());
        book.setAverageRating(request.getAverageRating());
        book.setMaturityRating(request.getMaturityRating());
        book.setImageLinks(request.getImageLinks());
        book.setLanguage(request.getLanguage());
        book.setPreviewLink(request.getPreviewLink());
        Book updatedBook = bookRepository.save(book);
        return BookDto.from(updatedBook);
    }


}



