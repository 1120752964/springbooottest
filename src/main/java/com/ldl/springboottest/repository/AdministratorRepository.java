package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Administrator;
import com.ldl.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator,Integer> {
    List<Administrator> findByanumber(String anumber);
}
