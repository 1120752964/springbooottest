package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.*;
import com.ldl.springboottest.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrdersHandler {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User_AddressRepository user_addressRepository;
    @Autowired
    private Order_DetailRepository order_detailRepository;
//    //还不会JPA的条件分页,所以暂且不分页了
//    @GetMapping("/findAll/{page}/{size}/{userunumber}")
//    public Page<Orders> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size,@PathVariable("size")String userunumber){
//        Pageable pageable = PageRequest.of(page-1,size);
//        String ordernumber =  ordersRepository.findByuserunumber(userunumber).get(0).getOnumber();
//        return ordersRepository.findAll(pageable);
//    }
    //通过订单号找到所有订单详情
    @PostMapping("/findOrderDetailsByOnumber/{onumber}")
    public List<Order_Detail> findById(@PathVariable("onumber")String onumber){
        if(onumber.equals("")){
            return  null;
        }
        return order_detailRepository.findByorderonumber(onumber);
    }
    //通过订单号找到订单信息
    @PostMapping("/findOrdersByOnumber/{onumber}")
    public Orders findOrderById(@PathVariable("onumber")String onumber){
        if(onumber.equals("")){
            return  null;
        }
        return ordersRepository.findByonumber(onumber).get(0);
    }

    //获取对应用户的所有订单
    @PostMapping("/getAllOrders/{userunumber}")
    public List<Order_Detail> getAllOrders(@PathVariable("userunumber")String userunumber){
        List<Orders> orders = ordersRepository.findByuserunumber(userunumber);
        List<Order_Detail> order_details1 = new ArrayList<>();
        String onumber = "";
        for (int i = 0; i < orders.size(); i++) {
            onumber = orders.get(i).getOnumber();
            List<Order_Detail> order_details = order_detailRepository.findByorderonumber(onumber);
            for (int j = 0; j < order_details.size(); j++) {
                order_details1.add(order_details.get(j));
            }
        }
        if(order_details1 == null){
            return  null;
        }
        return order_details1;
    }

    //获取所有未支付的订单
    @PostMapping("/getAllUnPayOrders/{userunumber}")
    public List<Order_Detail> getAllUnPayOrders(@PathVariable("userunumber")String userunumber){
        List<Orders> orders = ordersRepository.findByuserunumber(userunumber);
        List<Order_Detail> order_details1 = new ArrayList<>();
        String onumber = "";
        for (int i = 0; i < orders.size(); i++) {
            onumber = orders.get(i).getOnumber();
            List<Order_Detail> order_details = order_detailRepository.findByorderonumber(onumber);
            for (int j = 0; j < order_details.size(); j++) {
                if (order_details.get(j).getCommoditystatus().equals("未支付")){
                    order_details1.add(order_details.get(j));
                }
            }
        }
        return order_details1;
    }

    //获取所有未支付的订单
    @PostMapping("/getAllUnSendOrders/{userunumber}")
    public List<Order_Detail> getAllUnSendOrders(@PathVariable("userunumber")String userunumber){
        List<Orders> orders = ordersRepository.findByuserunumber(userunumber);
        List<Order_Detail> order_details1 = new ArrayList<>();
        String onumber = "";
        for (int i = 0; i < orders.size(); i++) {
            onumber = orders.get(i).getOnumber();
            List<Order_Detail> order_details = order_detailRepository.findByorderonumber(onumber);
            for (int j = 0; j < order_details.size(); j++) {
                if (order_details.get(j).getCommoditystatus().equals("已支付")){
                    order_details1.add(order_details.get(j));
                }
            }
        }
        return order_details1;
    }

    //获取所有已发货（未收货）的订单
    @PostMapping("/getSendOrders/{userunumber}")
    public List<Order_Detail> getSendOrders(@PathVariable("userunumber")String userunumber){
        List<Orders> orders = ordersRepository.findByuserunumber(userunumber);
        List<Order_Detail> order_details1 = new ArrayList<>();
        String onumber = "";
        for (int i = 0; i < orders.size(); i++) {
            onumber = orders.get(i).getOnumber();
            List<Order_Detail> order_details = order_detailRepository.findByorderonumber(onumber);
            for (int j = 0; j < order_details.size(); j++) {
                if (order_details.get(j).getCommoditystatus().equals("已发货")){
                    order_details1.add(order_details.get(j));
                }
            }
        }
        return order_details1;
    }

    //获取所有未发货（未支付，未发货）的订单
    @PostMapping("/getUnSendOrders/{userunumber}")
    public List<Order_Detail> getUnSendOrders(@PathVariable("userunumber")String userunumber){
        List<Orders> orders = ordersRepository.findByuserunumber(userunumber);
        List<Order_Detail> order_details1 = new ArrayList<>();
        String onumber = "";
        for (int i = 0; i < orders.size(); i++) {
            onumber = orders.get(i).getOnumber();
            List<Order_Detail> order_details = order_detailRepository.findByorderonumber(onumber);
            for (int j = 0; j < order_details.size(); j++) {
                if (order_details.get(j).getCommoditystatus().equals("未支付")||order_details.get(j).getCommoditystatus().equals("已支付")){
                    order_details1.add(order_details.get(j));
                }
            }
        }
        return order_details1;
    }

    //获取所有未评价（已收货）的订单
    @PostMapping("/getUnCommentOrders/{userunumber}")
    public List<Order_Detail> getUnCommentOrders(@PathVariable("userunumber")String userunumber){
        List<Orders> orders = ordersRepository.findByuserunumber(userunumber);
        List<Order_Detail> order_details1 = new ArrayList<>();
        String onumber = "";
        for (int i = 0; i < orders.size(); i++) {
            onumber = orders.get(i).getOnumber();
            List<Order_Detail> order_details = order_detailRepository.findByorderonumber(onumber);
            for (int j = 0; j < order_details.size(); j++) {
                if (order_details.get(j).getCommoditystatus().equals("已收货")){
                    order_details1.add(order_details.get(j));
                }
            }
        }
        return order_details1;
    }
    //订单页面提交订单 生成订单
    @PostMapping("/save")
    public String save(@RequestBody Integer[] cartids){

        //保存orders表
        Orders order = new Orders();
        String orderid = getOrderIdByTime();
        int totalcost=0;
        for (int i = 0; i < cartids.length ; i++) {
            System.out.println(cartids[i]);
            Cart cart = cartRepository.findById(cartids[i]).get();
            totalcost  += cart.getCost();
            order.setUserunumber(cart.getUserunumber());
            order.setUserunumber(cart.getUserunumber());
        }
        order.setOcost(totalcost+"");
        order.setOnumber(orderid);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        order.setCreatedtime(dateNowStr);
        ordersRepository.save(order);

        //保存order_detail表
        for (int i = 0; i < cartids.length; i++) {
            Order_Detail order_detail = new Order_Detail();
            Cart cart1 = cartRepository.findById(cartids[i]).get();
            order_detail.setCommoditycnumber(cart1.getCommodityid());
            order_detail.setOrderonumber(orderid);
            order_detail.setCommoditycolor(cart1.getColor());
            order_detail.setCommoditycount(cart1.getQuantity());
            order_detail.setCommoditysize(cart1.getSize());
            order_detail.setOrdercommoditycount(cart1.getCost().toString());
            order_detail.setCommoditystatus("未支付");
            order_detail.setCommodityname(cart1.getCommodityname());
            order_detail.setCommoditypriceforone(cart1.getPriceforone());
            cartRepository.delete(cartRepository.findById(cartids[i]).get());
            order_detailRepository.save(order_detail);
        }

        return order.getOnumber();
    }
    //保存对应订单ID的地址
    @PostMapping("/setaddressbyid")
    public Orders setaddressbyid(@RequestBody Map<String,String> map){
        System.out.println(map.toString());
        if(map == null){
            return  null;
        }
        String cartid= map.get("onumber");
        System.out.println(cartid);
        Orders orders = ordersRepository.findByonumber(cartid).get(0);

        orders.setUseraddress(map.get("address"));
        orders.setConsigneename(map.get("consigneename"));
        orders.setPhonenumber(map.get("phonenumber"));
        ordersRepository.save(orders);
        return ordersRepository.findByonumber(map.get("onumber")).get(0);
    }

    //生成订单号
    public  String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate;
    }

    //设置为已收货
    @PostMapping("/getCommodity/{id}")
    public String getCommodity(@PathVariable("id")Integer id){
        Order_Detail order_detail = order_detailRepository.findById(id).get();
        order_detail.setCommoditystatus("已收货");
        Order_Detail order_detail1 = order_detailRepository.save(order_detail);
        if(order_detail1!=null){
            return  "success";
        }else {
            return "error";
        }
    }


    @PostMapping("/findNameandTel/{userunumber}/{address}")
    public User_Address findNameandTel(@PathVariable("userunumber")String userunumber,@PathVariable("address")String address){
        if(userunumber.equals("")||address.equals("")){
            return  null;
        }
        System.out.println(userunumber);
        System.out.println(address);
        User_Address user_address = null;
        List<User_Address> user_addresses = user_addressRepository.findByuserunumber(userunumber);
        System.out.println(user_addresses);
        for (User_Address u : user_addresses) {
            if (address.equals(u.getUseraddress())){
                user_address = u;
            }
        }
        return user_address;
    }

    @PutMapping("/update")
    public String update(@RequestBody Orders order){
        Orders order1 = ordersRepository.save(order);
        if(order1!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @DeleteMapping("/deleteUserById/{id}")
    public void delete(@PathVariable("id")Integer id){
        ordersRepository.deleteById(id);
    }
}
