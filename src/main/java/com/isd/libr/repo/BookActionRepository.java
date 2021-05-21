package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookActionRepository extends JpaRepository<BookAction, Long> {
    List<BookAction> getAllByStatus(Status status);

    BookAction getFirstByBookOrderByActionDateDesc(Book book);
}
