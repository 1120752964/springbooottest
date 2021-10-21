package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.Administrator;
import com.ldl.springboottest.entity.Cart;
import com.ldl.springboottest.entity.User;
import com.ldl.springboottest.repository.CartRepository;
import com.ldl.springboottest.repository.CommodityRepository;
import com.ldl.springboottest.repository.Commodity_DetailRepository;
import com.ldl.springboottest.repository.UserRepository;
import com.ldl.springboottest.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartHandler {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Commodity_DetailRepository commodity_detailRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Cart> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return cartRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Cart cart){
        List<Cart> list = cartRepository.findBycommodityid(cart.getCommodityid());
        String s = commodity_detailRepository.findBycommoditycolorcimage(cart.getColor()).get(0).getCommoditycolortext();
        cart.setColor(s);
        Boolean flag = false;
        Integer cost = Integer.parseInt(cart.getPriceforone())*Integer.parseInt(cart.getQuantity());
        cart.setCost(cost);
        Cart cart1 = null;
        for (Cart c:list) {
            if (c.getColor().equals(cart.getColor())&&c.getSize().equals(cart.getSize())){
               Integer a = Integer.parseInt(c.getQuantity())+Integer.parseInt(cart.getQuantity());
               c.setCost(a*Integer.parseInt(cart.getPriceforone()));
               c.setQuantity(a+"");
               cartRepository.save(c);
               flag = true;
            }
        }
        if(!flag){ cart1 = cartRepository.save(cart);}

        if(cart1!=null||flag){
            return cart.getUserunumber();
        }else {
            return "error";
        }
    }

    //findByCartids
    @PostMapping("/findByCartids")
    public List<Cart> findByCartids(@RequestBody String[] cartids){
        List<Cart> carts = new ArrayList<Cart>();
        for (int i = 0; i < cartids.length; i++) {
            carts.add(cartRepository.findById(Integer.parseInt(cartids[i])).get());
        }
        return carts;
    }
    //减少按钮
    @GetMapping("/subQuantity/{cartid}/{userUnumber}")
    public List<Cart> subQuantity(@PathVariable("cartid")Integer cartid,@PathVariable("userUnumber")String userUnumber){
        if(cartid == null){
            return  null;
        }
        Cart cart = cartRepository.findById(cartid).get();
        cart.setQuantity((Integer.parseInt(cart.getQuantity())-1)+"");
        cart.setCost(cart.getCost()-Integer.parseInt(cart.getPriceforone()));
        cartRepository.save(cart);
        return cartRepository.findByuserunumber(userUnumber);
    }
    //增加按钮
    @GetMapping("/addQuantity/{cartid}/{userUnumber}")
    public List<Cart> addQuantity(@PathVariable("cartid")Integer cartid,@PathVariable("userUnumber")String userUnumber){
        if(cartid == null){
            return  null;
        }
        Cart cart = cartRepository.findById(cartid).get();
        cart.setQuantity((Integer.parseInt(cart.getQuantity())+1)+"");
        cart.setCost(cart.getCost()+Integer.parseInt(cart.getPriceforone()));
        cartRepository.save(cart);
        return cartRepository.findByuserunumber(userUnumber);
    }
    @GetMapping("/findByUserunumber/{userunumber}")
    public List<Cart> findById(@PathVariable("userunumber")String userunumber){
        if(userunumber == null){
            return  null;
        }
        return cartRepository.findByuserunumber(userunumber);
    }
    @GetMapping("/getpricebyid/{id}")
    public Integer getpricebyid(@PathVariable("id")Integer id){
        if(id == null){
            return  null;
        }
        return Integer.parseInt(cartRepository.findById(id).get().getPriceforone());
    }
    //删除该cart记录
    @DeleteMapping("/delete/{cartid}")
    public String delete(@PathVariable("cartid")Integer cartid){
        if(cartid == null){
            return  "error";
        }
        cartRepository.deleteById(cartid);
        return "success";
    }

//    @PutMapping("/update")
//    public String update(@RequestBody User user){
//        User user1 = userRepository.save(user);
//        if(user1!=null){
//            return "success";
//        }else {
//            return "error";
//        }
//    }

//
//    @GetMapping("/juddgeifdup/{unum}")
//    public User juddgeifdup(@PathVariable("unum")String umum){
//        User user = new User();
//        user.setUid(-1);
//        List<User> users = userRepository.findAll();
//        for (int i = 0; i <users.size() ; i++) {
//            if (users.get(i).getUnumber().equals(umum)){
//                user = users.get(i);
//            }
//        }
//        return user;
//    }

}
