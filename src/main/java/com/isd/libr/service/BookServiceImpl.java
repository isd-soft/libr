package com.isd.libr.service;

import com.isd.libr.exceptions.BookDuplicateException;
import com.isd.libr.exceptions.BookNotFoundException;
import com.isd.libr.exceptions.UserNotFoundException;
import com.isd.libr.repo.*;
import com.isd.libr.service.*;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.dto.requests.UpdateBookRequest;
import com.isd.libr.web.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation for {@link BookService}
 *
 * @author Grosu Kirill
 */
@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookActionRepository bookActionRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final BookReactionRepository bookReactionRepository;
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;

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
        Optional<Book> existsBook = bookRepository.getByTitle(request.getTitle());
        if (existsBook.isPresent()) {
            throw new BookDuplicateException("This book is already in our library or requested");
        }
        Optional<User> existsUser = userRepository.findById(request.getUserId());
        if (existsUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", request.getUserId()));
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
        if (request.getIsManuallyAdded()) {
            User userThatRequestedTheBook = existsUser.get();
            bookActionRepository.save(new BookAction(userThatRequestedTheBook,
                    book, LocalDateTime.now(), Status.IN_LIBRARY));
        }
        if (!request.getIsManuallyAdded()) {
            User userThatRequestedTheBook = existsUser.get();
            bookActionRepository.save(new BookAction(userThatRequestedTheBook,
                    book, LocalDateTime.now(), Status.SUBMITTED));
        }
        try {
            sendSubmittedEmail(book);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookActionRepository.deleteAllByBookId(id);
        bookReactionRepository.deleteAllByBookId(id);
        voteRepository.deleteAllByBookId(id);
        commentRepository.deleteAllByBookId(id);
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

    @Override
    public List<Map<String, Integer>> getAllSortedUniqueCategories() {
        return bookRepository.getAllSortedUniqueCategories();
    }


   private void sendSubmittedEmail(Book book) throws MessagingException {
       String sign = "Libr Team";
       String[] admins = userRepository.findByRole("ADMIN")
               .stream().map(User::getEmail)
               .toArray(String[]::new);

       Context context = new Context();
       context.setVariable("sign",sign);
       context.setVariable("logo","images/logo.png");
       context.setVariable("book",book.getTitle());
       String templateName = templateEngine.process("notificationForAdmin.html", context);
       emailService.sendEmailNotification("Notification",templateName,admins);
    }


    @Override
    public Integer countBooks() {
        return bookRepository.countBooksByIdGreaterThan(0L);
    }
}



