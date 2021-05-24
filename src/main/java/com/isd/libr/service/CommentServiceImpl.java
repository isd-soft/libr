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

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addComment(AddCommentRequest request) {
        Book book = bookRepository.getById(request.getBookId());
        User user = userRepository.getById(request.getUserId());
        Comment newComment = Comment.builder()
                .book(book)
                .user(user)
                .comment(request.getComment())
                .build();
        commentRepository.save(newComment);
    }
}
