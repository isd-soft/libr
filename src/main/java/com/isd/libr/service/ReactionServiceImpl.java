package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.ReactionRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.ReactionRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Reaction;
import com.isd.libr.web.entity.ReactionType;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;

    @Override
    public void react(ReactionRequest reactionRequest) {
        long userId = reactionRequest.getUserId();
        long bookId = reactionRequest.getBookId();
        ReactionType reactionType = reactionRequest.getType();
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", reactionRequest.getBookId()));
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", reactionRequest.getUserId()));
        }

        Reaction reaction = Reaction.builder()
                .book(book.get())
                .user(user.get())
                .reactionType(reactionType)
                .build();
        reactionRepository.save(reaction);
    }
}


