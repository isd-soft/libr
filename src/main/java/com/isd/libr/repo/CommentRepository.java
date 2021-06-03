package com.isd.libr.repo;

import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByUser(Optional<User> user);
}
