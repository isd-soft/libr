package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> { //, JpaSpecificationExecutor
    Optional<Book> findByTitle(String keyword);

    Book getById(Long id);
}
