package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.User;
import com.isd.libr.web.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> getAllByBookAndUser(Book book, User user);

   @Query(nativeQuery = true,value = "select count(id) from vote")
   Integer countVotesById();
}
