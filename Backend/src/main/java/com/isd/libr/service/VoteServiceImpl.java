package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.repo.VoteRepository;
import com.isd.libr.exceptions.BookNotFoundException;
import com.isd.libr.exceptions.RepeatedVoteException;
import com.isd.libr.exceptions.UserNotFoundException;
import com.isd.libr.service.VoteService;
import com.isd.libr.web.dto.requests.AddVoteRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.User;
import com.isd.libr.web.entity.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation for {@link VoteService}
 *
 * @author Grosu Kirill
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public int vote(AddVoteRequest request) throws RepeatedVoteException {
        long userId = request.getUserId();
        long bookId = request.getBookId();
        int voteNumber = request.getVote();
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", request.getBookId()));
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", request.getUserId()));
        }
        if (checkForRepeatedVote(book.get(), user.get())) {
            throw new RepeatedVoteException("This user already voted this book");
        }
        Vote vote = Vote.builder()
                .book(book.get())
                .user(user.get())
                .vote(voteNumber)
                .build();
        voteRepository.save(vote);
        return book.get().getSumOfVotes();
    }

    @Override
    public Integer countVotes() {
        return voteRepository.countVotesById();
    }

    @Override
    public void deleteByUser(Optional<User> user) {
        voteRepository.deleteAllByUser(user);
    }

    private boolean checkForRepeatedVote(Book book, User user) {
        List<Vote> votes = voteRepository.getAllByBookAndUser(book, user);
        return !votes.isEmpty();
    }
}
