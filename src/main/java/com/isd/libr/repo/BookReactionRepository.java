package com.isd.libr.repo;

import com.isd.libr.web.entity.BookReaction;
import com.isd.libr.web.entity.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookReactionRepository extends JpaRepository<BookReaction, Long> {
    @Query(nativeQuery = true,value = "select count(id) from book_reaction where reaction_type = :type")
    Integer countReactionType(@Param("type") String type);


    void deleteAllByBookId(Long bookId);

}
