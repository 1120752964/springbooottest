package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.*;
import com.ldl.springboottest.repository.*;
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
@RequestMapping("/store")
public class StoreHandler {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private Order_DetailRepository order_detailRepository;
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private Commodity_DetailRepository commodity_detailRepository;


    @PostMapping("/login")
    public String login(@RequestBody Store store){
        System.out.println(store);
        List<Store> stores = storeRepository.findBystorenumber(store.getStorenumber());
        System.out.println(stores);
        String string = DigestUtils.md5DigestAsHex(store.getStorepassword().getBytes());
        System.out.println(string);
        boolean flag=false;
        for(int i=0;i<stores.size();i++){
            if (string.equals(stores.get(i).getStorepassword())){
                flag=true;
                store = stores.get(i);
            }
        }
        System.out.println(flag);
        if(flag){
            return store.getStorenumber();
        }else {
            return "error";
        }
    }

    //获取对应商户的所有订单
    @PostMapping("/getAllOrders/{storename}")
    public List<Order_Detail> getAllOrders(@PathVariable("storename")String storename){
        List<Order_Detail> order_details = new ArrayList<>();
        List<Order_Detail> results = new ArrayList<>();
        //根据店铺名找到所有的商品号
        List<Commodity> commodities = commodityRepository.findBystorename(storename);
        //根据所有的商品号cid找到所有订单
        for (int i = 0; i < commodities.size(); i++) {
            order_details = order_detailRepository.findBycommoditycnumber(commodities.get(i).getCid());
            System.out.println(order_details);

            for (int j = 0; j < order_details.size(); j++) {
                results.add(order_details.get(j));
            }
        }
        return results;
    }

    //获取对应商户的待发货（已支付）订单
    @PostMapping("/getUnSendOrders/{storename}")
    public List<Order_Detail> getUnSendOrders(@PathVariable("storename")String storename){
        List<Order_Detail> order_details = new ArrayList<>();
        List<Order_Detail> results = new ArrayList<>();
        //根据店铺名找到所有的商品号
        List<Commodity> commodities = commodityRepository.findBystorename(storename);
        //根据所有的商品号cid找到所有订单
        for (int i = 0; i < commodities.size(); i++) {
            order_details = order_detailRepository.findBycommoditycnumber(commodities.get(i).getCid());
            for (int j = 0; j < order_details.size(); j++) {
                    results.add(order_details.get(j));
            }
        }
        List<Order_Detail> results2 = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getCommoditystatus().equals("已支付")) {
                results2.add(results.get(i));
            }
        }
        return results2;
    }

    //获取对应商户的待发货（已发货）订单
    @PostMapping("/getSendOrders/{storename}")
    public List<Order_Detail> getSendOrders(@PathVariable("storename")String storename){
        List<Order_Detail> order_details = new ArrayList<>();
        List<Order_Detail> results = new ArrayList<>();
        //根据店铺名找到所有的商品号
        List<Commodity> commodities = commodityRepository.findBystorename(storename);
        //根据所有的商品号cid找到所有订单
        for (int i = 0; i < commodities.size(); i++) {
            order_details = order_detailRepository.findBycommoditycnumber(commodities.get(i).getCid());
            for (int j = 0; j < order_details.size(); j++) {
                results.add(order_details.get(j));
            }
        }
        List<Order_Detail> results2 = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getCommoditystatus().equals("已发货")) {
                results2.add(results.get(i));
            }
        }
        return results2;
    }

    //通过ID获取店铺
    @GetMapping("/findStoreBysid/{storenumber}")
    public Store findById(@PathVariable("storenumber")String storenumber){
//        System.out.println(aid);
//        System.out.println(administratorRepository.findById(aid).get());
        return storeRepository.findBystorenumber(storenumber).get(0);
    }


    //通过OrderDetailID发货
    @PostMapping("/sendCommodity/{orderdetailnumber}")
    public String sendCommodity(@PathVariable("orderdetailnumber")Integer orderdetailnumber){
        if (orderdetailnumber==null){
            return "error";
        }else {
            Order_Detail order_detail = order_detailRepository.findById(orderdetailnumber).get();
            order_detail.setCommoditystatus("已发货");
            Integer subtoquantity = Integer.parseInt(order_detail.getCommoditycount());
            Integer cnum = order_detail.getCommoditycnumber();
            Commodity commodity = commodityRepository.findById(cnum).get();
            commodity.setCquantity((Integer.parseInt(commodity.getCquantity())-subtoquantity)+"");

            List<Commodity_Detail> commodity_details = commodity_detailRepository.findBycommoditycnumber(cnum);
            for (int i = 0; i < commodity_details.size(); i++) {
                Commodity_Detail commodityDetail = commodity_details.get(i);
                if(commodityDetail.getCommoditysize().equals(order_detail.getCommoditysize())&&commodityDetail.getCommoditycolortext().equals(order_detail.getCommoditycolor())){
                    commodityDetail.setCommodity_quantity((Integer.parseInt(commodityDetail.getCommodity_quantity())-subtoquantity)+"");
                    commodity_detailRepository.save(commodityDetail);
                }
            }
            order_detailRepository.save(order_detail);
            return "success";
        }
    }

    @GetMapping("/juddgeifdup/{storenumber}")
    public Store juddgeifdup(@PathVariable("storenumber")String storenumber){
        Store store = new Store();
        store.setStoreid(-1);
        if(!isInteger(storenumber)){
            store.setStoreid(0);
            return store;
        }
        List<Store> stores = storeRepository.findAll();
        for (int i = 0; i <stores.size() ; i++) {
            if (stores.get(i).getStorenumber().equals(storenumber)){
                store = stores.get(i);
            }
        }
        return store;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
//    //改密码专用
//    @PostMapping("/findAl")
//    public List<User> findAl(){
//        List<User> users =  userRepository.findAll();
//        for (int i = 0; i < users.size(); i++) {
//            User user1 = users.get(i);
//            user1.setUpassword(DigestUtils.md5DigestAsHex(user1.getUpassword().getBytes()));
//            userRepository.save(user1);
//        }
//        return userRepository.findAll();
//    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<Store> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return storeRepository.findAll(pageable);
    }

    //注册
    @PostMapping("/save")
    public String save(@RequestBody Store store){
        System.out.println(store);
        store.setStorepassword(DigestUtils.md5DigestAsHex(store.getStorepassword().getBytes()));
        Store store1 = storeRepository.save(store);
        if(store1!=null){
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findUserById/{id}")
    public Store findById(@PathVariable("id")Integer id){
        if(id==0){
            return  null;
        }
        return storeRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Store store){
        Store store1 = storeRepository.save(store);
        if(store1!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @DeleteMapping("/deleteUserById/{id}")
    public void delete(@PathVariable("id")Integer id){
        storeRepository.deleteById(id);
    }


}
