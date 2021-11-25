package com.isd.libr.repo;

import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookActionRepository extends JpaRepository<BookAction, Long> {

    @Query(nativeQuery = true, value="select actions_ranked.* " +
            "from (select ba.*, ROW_NUMBER() OVER (PARTITION BY ba.book_id ORDER BY ba.action_date desc) " +
            "      from book_action ba " +
            "      order by ba.action_date desc) as actions_ranked " +
            "where actions_ranked.row_number = 1 " +
            "  and actions_ranked.status = :status")
    List<BookAction> getAllByStatus(@Param("status") String status);

    void deleteAllByBookId(Long bookId);

    @Query(nativeQuery = true,value = "select * from book_action where book_id = :bookId and status = :status " +
            "order by action_date desc limit 1")
     BookAction findLastActionByBookIdAndStatus (@Param("bookId") Long bookId,@Param("status") String status);


    void deleteAllByUser(Optional<User> user);

    @Query(nativeQuery = true, value = "select actions_ranked.*, extract(day from now() - actions_ranked.action_date) as days " +
            "from (select ba.*, ROW_NUMBER() OVER(PARTITION BY ba.book_id ORDER BY ba.action_date desc) " +
            "from book_action ba where ba.status!='SUBMITTED' " +
            "order by ba.action_date desc) as actions_ranked " +
            "where actions_ranked.row_number=1 " +
            "and extract(day from now() - actions_ranked.action_date) >= :ageInDays " +
            "and actions_ranked.status='IN_USE'")
    List<BookAction> findAllInUseOlderThen(@Param("ageInDays") int ageInDays);

    @Query(nativeQuery = true, value = "select extract('week' FROM ba.action_date) week, count(ba.id) from book_action ba " +
            "where ba.action_date > current_date - interval '1 month' " +
            "  and ba.status = 'SUBMITTED' " +
            "group by extract('week' FROM ba.action_date)")
    List<Object[]> getAllSubmissionActionsLastMonthForDashboard();


    Integer countByStatus(Status status);
}
