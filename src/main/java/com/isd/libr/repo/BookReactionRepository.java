package com.isd.libr.repo;

import com.isd.libr.web.entity.BookReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReactionRepository extends JpaRepository<BookReaction, Long> {
}
