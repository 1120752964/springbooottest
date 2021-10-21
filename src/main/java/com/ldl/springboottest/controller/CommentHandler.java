package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.Comment;
import com.ldl.springboottest.entity.Order_Detail;
import com.ldl.springboottest.entity.Orders;
import com.ldl.springboottest.entity.User;
import com.ldl.springboottest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentHandler {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private Order_DetailRepository order_detailRepository;

    @PostMapping("/save")
    public Comment save(@RequestBody Comment comment){
        Date date = new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sim.format(date);
        comment.setCreatedtime(time);
        System.out.println(comment);
        String username = userRepository.findByunumber(comment.getCommentusernumber()).get(0).getUname();
        comment.setCommentusername(username);
        Comment comment1 = commentRepository.save(comment);
        return comment1;
    }
    @PostMapping("/getAllComment/{commodityid}")
    public ArrayList<Comment>  getAllComment(@PathVariable  Integer commodityid){

        ArrayList<Comment> comments = commentRepository.findBycommodityid(commodityid);
        return comments;
    }
    //判断用户是否购买过并收货该商品，来决定用户能否进行评价
    @GetMapping("/findCommodityisbought/{usernumber}/{commodityid}")
    public Integer  findCommodityisbought(@PathVariable  String usernumber,@PathVariable  Integer commodityid){
        Integer isbought = 0;
        List<Orders> orders = ordersRepository.findByuserunumber(usernumber);
        List<Order_Detail> order_details = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            List<Order_Detail> order_details1 = order_detailRepository.findByorderonumber(orders.get(i).getOnumber());
            for (int j = 0; j < order_details1.size(); j++) {
                order_details.add(order_details1.get(j));
            }
        }
        for (int i = 0; i < order_details.size(); i++) {
            if (order_details.get(i).getCommoditystatus().equals("已收货")&&order_details.get(i).getCommoditycnumber()==commodityid){
                isbought=1;
            }
        }
        return isbought;
    }
}
