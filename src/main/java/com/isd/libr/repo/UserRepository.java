package com.isd.libr.repo;

import com.isd.libr.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value ="select email from users where role = 'ADMIN'")
    String findEmailByRole();
    List<User> findAll();

    User getById(Long id);
}
