package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Store;
import com.ldl.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Integer> {
    List<Store> findBysname(String sname);
    List<Store> findBystorenumber(String sname);
}
