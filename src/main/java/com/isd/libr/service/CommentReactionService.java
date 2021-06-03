package com.isd.libr.service;

import com.isd.libr.web.dto.requests.CommentReactionRequest;
import com.isd.libr.web.entity.User;

import java.util.Optional;

/**
 * BookReactionService is an interface for {@link com.isd.libr.web.entity.CommentReaction} entity.
 *
 * <p>Preferred implementation {@code CommentReactionServiceImpl}</p>
 *
 * @author Grosu Kirill
 */
public interface CommentReactionService {
    /**
     * Creates an object of class {@link com.isd.libr.web.entity.CommentReaction} from provided data and inserts into database.
     *
     * @param commentReactionRequest object of type {@link CommentReactionRequest} containing fields: <ul>
     *                               <li>User ID</li>
     *                               <li>Comment ID</li>
     *                               <li>Reaction Type</li>
     *                               </ul>
     */
    void react(CommentReactionRequest commentReactionRequest);

    void deleteByUser(Optional<User> user);
}
