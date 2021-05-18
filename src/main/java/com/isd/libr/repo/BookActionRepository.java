package com.isd.libr.repo;

import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookActionRepository extends JpaRepository<BookAction, Long> {
    List<BookAction> getAllByStatus(Status status);

//    @Modifying
//    @Query(value = "UPDATE public.book_action b SET b.status = :newStatus WHERE b.id = :id", nativeQuery = true)
//    void updateStatus(@Param("id") Long id, @Param("newStatus") String newStatus);

    BookAction getById(Long id);
}
