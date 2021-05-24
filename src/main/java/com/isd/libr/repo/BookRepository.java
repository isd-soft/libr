package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String keyword);

    List<Book> findAll();

    Book getByTitle(String title);

    Book getById(Long id);
}
