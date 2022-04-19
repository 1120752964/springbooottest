package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.Order_Detail;
import com.ldl.springboottest.entity.Orders;
import com.ldl.springboottest.entity.User;
import com.ldl.springboottest.entity.User_Address;
import com.ldl.springboottest.repository.Order_DetailRepository;
import com.ldl.springboottest.repository.OrdersRepository;
import com.ldl.springboottest.repository.UserRepository;
import com.ldl.springboottest.repository.User_AddressRepository;
import com.ldl.springboottest.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private User_AddressRepository user_addressRepository;
    @Autowired
    private Order_DetailRepository order_detailRepository;
    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        List<User> users = userRepository.findByunumber(user.getUnumber());
//        String string = DigestUtils.md5DigestAsHex(user.getUpassword().getBytes());
        boolean flag=false;
        for(int i=0;i<users.size();i++){
//            if (string.equals(DigestUtils.md5DigestAsHex(users.get(i).getUpassword().getBytes()))){
//                flag=true;
//            }
            if (user.getUpassword().equals(users.get(i).getUpassword())){
                flag=true;
            }
        }
        if(flag){
            User user1 =  userRepository.findByunumber(user.getUnumber()).get(0);
            return user1.getUnumber();
        }else {
            return "error";
        }
    }


    //改密码专用
    @PostMapping("/findAl")
    public List<User> findAl(){
        List<User> users =  userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            user1.setUpassword(DigestUtils.md5DigestAsHex(user1.getUpassword().getBytes()));
            userRepository.save(user1);
        }
        return userRepository.findAll();
    }

    //订单支付
    @PostMapping("/pay/{userunumber}/{upaypassword}/{onumber}")
    public String payfororder(@PathVariable String userunumber,@PathVariable String upaypassword,@PathVariable String onumber){
        List<User> users = userRepository.findByunumber(userunumber);
        Boolean flag = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUpaypassword().equals(upaypassword)){
                flag = true;
            }
        }
        if(flag){
            List<Order_Detail> orders = order_detailRepository.findByorderonumber(onumber);
            for (int i = 0; i < orders.size(); i++) {
                orders.get(i).setCommoditystatus("已支付");
                order_detailRepository.save(orders.get(i));
            }
            return "success";
        }else {
            return "error";
        }
    }

    //找到默认地址
    @PostMapping("/getdefaultaddress/{userunumber}")
    public String getdefaultaddress(@PathVariable String userunumber){
        List<User_Address> user_addresses = user_addressRepository.findByuserunumber(userunumber);
        String defaultaddress = "";
        for (int i = 0; i < user_addresses.size(); i++) {
            if (user_addresses.get(i).getIsdefault().equals("1")){
                defaultaddress = user_addresses.get(i).getUseraddress();
            }
        }
        return defaultaddress+"(默认地址)";
    }

    //找到所有地址
    @PostMapping("/getalladdress/{userunumber}")
    public ArrayList<String> getalladdress(@PathVariable String userunumber){
        List<User_Address> user_addresses = user_addressRepository.findByuserunumber(userunumber);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < user_addresses.size(); i++) {
            if (user_addresses.get(i).getIsdefault().equals("0")){
                strings.add(user_addresses.get(i).getUseraddress());
            }else{
                strings.add(user_addresses.get(i).getUseraddress()+"(默认地址)");
            }
        }
        return strings;
    }


    @GetMapping("/findAll/{page}/{size}")
    public Page<User> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return userRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody User user){
        user.setUpassword(DigestUtils.md5DigestAsHex(user.getUpassword().getBytes()));
        User user1 = userRepository.save(user);
        User_Address user_address = new User_Address();
        user_address.setUserunumber(user.getUnumber());
        user_address.setUseraddress(user.getUaddress());
        user_address.setIsdefault("1");
        user_address.setConsigneename(user.getUname());
        user_address.setPhonenumber(user.getUtelphone());
        user_addressRepository.save(user_address);
        if(user1!=null&&user_address!=null){
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findUserByUnumber/{unumber}")
    public String findById(@PathVariable("unumber")String unumber){
        if(unumber == null){
            return  null;
        }
        return userRepository.findByunumber(unumber).get(0).getUname();
    }

    @GetMapping("/findUByUnumber/{unumber}")
    public User findUserByUnumber(@PathVariable("unumber")String unumber){
        if(unumber == null){
            return  null;
        }
        return userRepository.findByunumber(unumber).get(0);
    }

    @PutMapping("/update")
    public String update(@RequestBody User user){
        User user1 = userRepository.save(user);
        if(user1!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @DeleteMapping("/deleteUserById/{id}")
    public void delete(@PathVariable("id")Integer id){
        userRepository.deleteById(id);
    }

    @GetMapping("/juddgeifdup/{unum}")
    public User juddgeifdup(@PathVariable("unum")String umum){
        User user = new User();
        user.setUid(-1);
        if(!isInteger(umum)){
            user.setUid(0);
            return user;
        }
        List<User> users = userRepository.findAll();
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getUnumber().equals(umum)){
                user = users.get(i);
            }
        }
        return user;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
