package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.Order_Detail;
import com.ldl.springboottest.entity.User;
import com.ldl.springboottest.entity.User_Address;
import com.ldl.springboottest.repository.Order_DetailRepository;
import com.ldl.springboottest.repository.OrdersRepository;
import com.ldl.springboottest.repository.UserRepository;
import com.ldl.springboottest.repository.User_AddressRepository;
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
@RequestMapping("/address")
public class AddressHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User_AddressRepository ordersRepository;
    @Autowired
    private User_AddressRepository user_addressRepository;
    @Autowired
    private Order_DetailRepository order_detailRepository;
    @PostMapping("/login")
    public String login(@RequestBody User user){
        List<User> users = userRepository.findByunumber(user.getUnumber());
        String string = DigestUtils.md5DigestAsHex(user.getUpassword().getBytes());
        boolean flag=false;
        for(int i=0;i<users.size();i++){
            if (string.equals(users.get(i).getUpassword())){
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

    //返回该用户所有地址信息
    @PostMapping("/getAllAddresses/{userunumber}")
    public List<User_Address> getalladdress(@PathVariable String userunumber){
        List<User_Address> user_addresses = user_addressRepository.findByuserunumber(userunumber);
        return user_addresses;
    }

    @PostMapping("/changeDefault")
    public List<User_Address> changeDefault(@RequestBody User_Address user_address){
//        System.out.println(user_address);
        List<User_Address> user_addresses = user_addressRepository.findAll();
        for (User_Address u : user_addresses) {
            if(u.getIsdefault().equals("1")){
                u.setIsdefault("0");
                user_addressRepository.save(u);
            }
        }
        User_Address user_address1 = user_addressRepository.findById(user_address.getUseraddressid()).get();
        user_address1.setIsdefault("1");
        user_addressRepository.save(user_address1);
        user_addresses = user_addressRepository.findByuserunumber(user_address.getUserunumber());
        return user_addresses;
    }


    @GetMapping("/findAll/{page}/{size}")
    public Page<User> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return userRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody User_Address  user_address){
        user_address.setIsdefault("0");
        user_addressRepository.save(user_address);
        if(user_address!=null&&user_address!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @PostMapping("/update")
    public String update(@RequestBody User_Address  user_address){
        System.out.println(user_address);
        user_addressRepository.save(user_address);
        if(user_address!=null&&user_address!=null){
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findAddressByAid/{aid}")
    public User_Address findById(@PathVariable("aid")Integer aid){
        System.out.println(aid);
        if(aid == null){
            return  null;
        }
        return user_addressRepository.findById(aid).get();
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
    @DeleteMapping("/deleteByID/{id}")
    public List<User_Address> delete(@PathVariable("id")Integer id){
        String number = user_addressRepository.findById(id).get().getUserunumber();
        user_addressRepository.deleteById(id);
        List<User_Address> user_addresses = user_addressRepository.findByuserunumber(number);

        return user_addresses;
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
