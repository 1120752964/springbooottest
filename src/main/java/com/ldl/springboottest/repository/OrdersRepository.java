package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
//    List<User> findByunumber(String unumber);userunumber
    List<Orders> findByonumber(String onumber);
    List<Orders> findByuserunumber(String userunumber);
}
