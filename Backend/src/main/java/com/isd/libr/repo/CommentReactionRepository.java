package com.isd.libr.repo;

import com.isd.libr.web.entity.CommentReaction;
import com.isd.libr.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
    void deleteAllByUser(Optional<User> user);
}
