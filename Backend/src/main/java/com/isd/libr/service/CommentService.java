package com.isd.libr.service;

import com.isd.libr.web.dto.requests.AddCommentRequest;
import com.isd.libr.web.entity.User;

import java.util.Optional;

/**
 * CommentService is an interface for {@link com.isd.libr.web.entity.Comment}
 *
 * <p>Preferred implementation {@code CommentServiceImpl}</p>
 *
 * @author Grosu Kirill
 */
public interface CommentService {
    /**
     * Creates a new instance of class {@link com.isd.libr.web.entity.Comment} and adds it into database.
     *
     * @param request object of type {@link AddCommentRequest} containing fields: <ul>
     *                <li>User ID</li>
     *                <li>Book ID</li>
     *                <li>Comment</li>
     *                </ul>
     */
    void addComment(AddCommentRequest request);

    void deleteByUser(Optional<User> user);
}
