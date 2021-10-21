package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Cart;
import com.ldl.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findBycommodityid(Integer commodityid);
    List<Cart> findByuserunumber(String commodityid);
}
