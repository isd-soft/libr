package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.CommentRepository;
import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.requests.AddCommentRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addComment(AddCommentRequest request) {
        Book book = bookRepository.getById(request.getBookId());
        Person person = personRepository.getById(request.getPersonId());
        Comment newComment = Comment.builder()
                .book(book)
                .person(person)
                .comment(request.getComment())
                .build();
        commentRepository.save(newComment);
    }
}
