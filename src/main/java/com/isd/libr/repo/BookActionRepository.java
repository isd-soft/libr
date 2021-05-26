package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookActionRepository extends JpaRepository<BookAction, Long> {

    List<BookAction> getAllByStatus(Status status);


    BookAction getFirstByBookOrderByActionDateDesc(Book book);

    void deleteAllByUser(Optional<User> user);

    @Query(nativeQuery = true, value = "select actions_ranked.*, extract(day from now() - actions_ranked.action_date) as days " +
            "from (select ba.*, ROW_NUMBER() OVER(PARTITION BY ba.book_id ORDER BY ba.action_date desc) " +
            "from book_action ba where ba.status!='SUBMITTED' " +
            "order by ba.action_date desc) as actions_ranked " +
            "where actions_ranked.row_number=1 " +
            "and extract(day from now() - actions_ranked.action_date) >= :ageInDays " +
            "and actions_ranked.status='IN_USE'")
    List<BookAction> findAllInUseOlderThen(@Param("ageInDays") int ageInDays);


}
