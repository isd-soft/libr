package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> { //, JpaSpecificationExecutor
    Optional<Book> findByTitle(String keyword);


}
