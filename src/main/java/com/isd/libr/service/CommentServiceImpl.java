package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.CommentRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.AddCommentRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation for {@link CommentService}
 *
 * @author Grosu Kirill
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void addComment(AddCommentRequest request) {
        Optional<Book> book = bookRepository.findById(request.getBookId());
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", request.getBookId()));
        }
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", request.getUserId()));
        }
        Comment newComment = Comment.builder()
                .book(book.get())
                .user(user.get())
                .date(LocalDateTime.now())
                .comment(request.getComment())
                .date(LocalDateTime.now())
                .build();
        commentRepository.save(newComment);
    }
}
