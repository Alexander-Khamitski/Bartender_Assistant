package com.teachmeskills.bartender_assistant.repository;

import com.teachmeskills.bartender_assistant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
