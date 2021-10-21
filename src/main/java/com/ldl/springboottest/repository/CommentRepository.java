package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Cart;
import com.ldl.springboottest.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    ArrayList<Comment> findBycommodityid(Integer unumber);

}
