package com.teachmeskills.bartender_assistant.repository;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

    List<User> findByRoleId(int roleId);

    boolean existsByUsername(String usename);

    boolean existsByLogin(String login);

    Page<User> findByRoleId(int roleId, Pageable pageable);
}
