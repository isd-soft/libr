package com.isd.libr.service;


import com.isd.libr.web.dto.requests.AddVoteRequest;
import com.isd.libr.web.entity.Vote;

/**
 * VoteService is an interface of {@link Vote} entity.
 *
 * <p>Preferred implementation {@code VoteServiceImpl}</p>
 * @author Grosu Kirill
 */
public interface VoteService {
    /**
     * Creates a new instance of class {@link Vote} and adds it to database.
     * @param request object of type {@link AddVoteRequest} containing fields: <ul>
     *                <li>User ID</li>
     *                <li>Book ID</li>
     *                <li>Vote</li>
     * </ul>
     * @return result vote on provided book after adding all of them up
     * @throws RepeatedVoteException in case when a specific user tries to Vote specific book multiple times.
     */
    int vote(AddVoteRequest request) throws RepeatedVoteException;

    /**
     * Used for creating statistics endpoint. Returns number of all votes present in database.
     * @return number of all votes present in database.
     */
    Integer countVotes();
}
