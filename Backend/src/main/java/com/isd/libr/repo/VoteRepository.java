package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.User;
import com.isd.libr.web.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> getAllByBookAndUser(Book book, User user);

   @Query(nativeQuery = true,value = "select count(id) from vote")
   Integer countVotesById();

    void deleteAllByUser(Optional<User> user);

    void deleteAllByBookId(long id);
}
