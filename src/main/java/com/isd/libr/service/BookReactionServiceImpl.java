package com.isd.libr.service;

import com.isd.libr.repo.BookReactionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.BookReactionRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookReaction;
import com.isd.libr.web.entity.ReactionType;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookReactionServiceImpl implements BookReactionService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookReactionRepository bookReactionRepository;

    @Override
    public void react(BookReactionRequest bookReactionRequest) {
        long userId = bookReactionRequest.getUserId();
        long bookId = bookReactionRequest.getBookId();
        ReactionType reactionType = bookReactionRequest.getType();
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", bookReactionRequest.getBookId()));
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", bookReactionRequest.getUserId()));
        }

        BookReaction bookReaction = BookReaction.builder()
                .book(book.get())
                .user(user.get())
                .reactionType(reactionType)
                .build();
        bookReactionRepository.save(bookReaction);
    }
}


