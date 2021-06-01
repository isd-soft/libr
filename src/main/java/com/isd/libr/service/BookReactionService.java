package com.isd.libr.service;

import com.isd.libr.web.dto.requests.BookReactionRequest;
import com.isd.libr.web.entity.ReactionType;

/**
 * BookReactionService is an interface for {@link com.isd.libr.web.entity.BookReaction} entity.
 *
 * <p>Preferred implementation {@code BookReactionServiceImpl}</p>
 *
 * @author Alexandru Baris
 */
public interface BookReactionService {
    /**
     * Creates an object of class {@link com.isd.libr.web.entity.BookReaction} from provided data and inserts into database.
     * @param bookReactionRequest object of type {@link BookReactionRequest} containing fields: <ul>
     *                            <li>User ID</li>
     *                            <li>Book ID</li>
     *                            <li>Reaction Type</li>
     * </ul>
     */
    void react(BookReactionRequest bookReactionRequest);

    /**
     * Counts all reactions present in database by type. Used for statistics purposes.
     * @param type enum with one of fields: <ul>
     *             <li>HEART</li>
     *             <li>LAUGH</li>
     *             <li>SAD</li>
     * </ul>
     * @return number of total reactions of provided type.
     */
    Integer countReactionTypes(ReactionType type);
}
