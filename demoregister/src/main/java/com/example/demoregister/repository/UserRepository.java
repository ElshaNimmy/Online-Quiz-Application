package com.example.demoregister.repository;

import com.example.demoregister.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Query("Select user from User user where user.id=:userId")
    User findUserById(Long userId);
}
