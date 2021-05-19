package com.isd.libr.repo;

import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookActionRepository extends JpaRepository<BookAction, Long> {
    List<BookAction> getAllByStatus(Status status);

}
