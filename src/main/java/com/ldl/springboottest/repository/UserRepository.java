package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByunumber(String unumber);
}
