package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Comment;
import com.ldl.springboottest.entity.Order_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Order_DetailRepository extends JpaRepository<Order_Detail,Integer> {
    List<Order_Detail> findByorderonumber(String orderonumber);
    List<Order_Detail> findBycommoditycnumber(Integer orderonumber);
}
