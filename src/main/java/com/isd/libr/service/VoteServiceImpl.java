package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.repo.VoteRepository;
import com.isd.libr.web.dto.requests.AddVoteRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.User;
import com.isd.libr.web.entity.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void vote(AddVoteRequest request) throws RepeatedVoteException {
        long userId = request.getUserId();
        long bookId = request.getBookId();
        int voteNumber = request.getVote();
        Book book = bookRepository.getById(bookId);
        User user = userRepository.getById(userId);
        if (checkForRepeatedVote(book, user)) {
            throw new RepeatedVoteException("This user already voted this book");
        }
        Vote vote = Vote.builder()
                .book(book)
                .user(user)
                .vote(voteNumber)
                .build();
        voteRepository.save(vote);
    }

    private boolean checkForRepeatedVote(Book book, User user) {
        List<Vote> votes = voteRepository.getAllByBookAndUser(book, user);
        return !votes.isEmpty();
    }
}
