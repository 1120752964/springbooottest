package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Comment;
import com.ldl.springboottest.entity.Store_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Store_DetailRepository extends JpaRepository<Store_Detail,Integer> {
//    List<User> findByunumber(String unumber);
}
