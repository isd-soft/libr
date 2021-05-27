package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getByTitle(String title);

    @Query(nativeQuery = true, value = "select b.* " +
            "from (select ba.*, ROW_NUMBER() OVER(PARTITION BY ba.book_id ORDER BY ba.action_date desc)  " +
            "from book_action ba " +
            "order by ba.action_date desc) as actions_ranked join book b on b.id=actions_ranked.book_id  " +
            "where actions_ranked.row_number=1  " +
            "and actions_ranked.status in ('IN_USE', 'IN_LIBRARY')")
    List<Book> findAllBooksByStatusesGreaterThenApproved();


}
