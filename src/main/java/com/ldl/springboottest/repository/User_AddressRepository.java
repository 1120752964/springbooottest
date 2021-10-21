package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Comment;
import com.ldl.springboottest.entity.User_Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_AddressRepository extends JpaRepository<User_Address,Integer> {
    List<User_Address> findByuserunumber(String unumber);
}
