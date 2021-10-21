package com.ldl.springboottest.controller;



import com.ldl.springboottest.entity.Commodity;
import com.ldl.springboottest.entity.Commodity_Detail;
import com.ldl.springboottest.repository.CommodityRepository;
import com.ldl.springboottest.repository.Commodity_DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/commodity")
public class CommodityHandler {

    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private Commodity_DetailRepository commodity_detailRepository;

    static String store_name = "";
    Integer a=-1;

    @GetMapping("/findAllForClothingMall/{page}/{size}")
    public Page<Commodity> findAllForClothingMall(@PathVariable("page")Integer pageNum, @PathVariable("size")Integer pageSize){
        Commodity commodity = new Commodity();
        commodity.setCstatus("已通过");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("cstatus", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Commodity> example = Example.of(commodity, matcher);
        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
        Page<Commodity> page = null;
        if (StringUtils.isEmpty(commodity)) {
            page = commodityRepository.findAll(pageRequest);
        }else {
            page = commodityRepository.findAll(example, pageRequest);
        }

        return page;
    }
    @GetMapping("/findAllForAdministrate/{page}/{size}")
    public Page<Commodity> findAllForAdministrate(@PathVariable("page")Integer pageNum, @PathVariable("size")Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
        Page<Commodity> page = null;
        page = commodityRepository.findAll(pageRequest);
        return page;
    }
    @GetMapping("/findAll/{page}/{size}/{storename}")
    public Page<Commodity> findByStoreName(@PathVariable("page")Integer page, @PathVariable("size")Integer size,@PathVariable("storename")String storename){
//        System.out.println(storename);
        Commodity commodity =  new Commodity();
        commodity.setStorename(storename);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("store_name",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Commodity> example = Example.of(commodity,matcher);
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<Commodity> pages = null;
        if (StringUtils.isEmpty(storename)){
            pages = commodityRepository.findAll(pageRequest);
        }else {
            pages = commodityRepository.findAll(example,pageRequest);
        }
        return pages;
    }



    //商户端上架商品时对商品详情的接受保存
    @PostMapping("/savecommdity_detail")
    public String savecommdity_detail(@RequestBody List<Commodity_Detail> commodity_detail){
        System.out.println(commodity_detail);
        System.out.println(a);
        commodity_detailRepository.deleteCommodity_DetailByCommoditycnumber(a);
        for (int i = 0; i < commodity_detail.size(); i++) {
            commodity_detail.get(i).setCommoditycnumber(a);
             commodity_detailRepository.save(commodity_detail.get(i));
        }
        if(commodity_detail!=null){
            return "success";
        }else {
            return "error";
        }
    }
    //商户端上架商品时对商品的接受保存
    @PostMapping("/save")
    public String save(@RequestBody Commodity commodity){
        if(commodity.getCstatus()==null||!commodity.getCstatus().equals("已通过")){
            commodity.setCstatus("未通过");
        }else {
            commodity.setCstatus("已通过");

        }
        Commodity commodity1 = commodityRepository.save(commodity);
        a=commodity1.getCid();
        System.out.println("a="+a);
        if(commodity1!=null){
            return "success";
        }else {
            return "error";
        }
    }



    //搜索框的模糊查找
    @GetMapping("/search/{cname}")
    public List<Commodity> search(@PathVariable("cname")String cname){
        if(cname.equals("")){
            return  commodityRepository.findAll();
        }
        return commodityRepository.findBycnameLike("%"+cname+"%");
    }

    //搜索框的模糊查找2
    @GetMapping("/search")
    public List<Commodity> search2(){

            return  commodityRepository.findAll();

    }

    @GetMapping("/findCommodityByCname/{cname}")
    public Commodity findById(@PathVariable("cname")String cname){
        if(cname==null){
            return  null;
        }
        return commodityRepository.findBycname(cname).get(0);
    }
    @GetMapping("/setStoreName/{storename}")
    public String  setStoreName(@PathVariable("storename")String storename){
        store_name = storename;
        System.out.println("store_name2");
        System.out.println(store_name);
       return store_name;
    }
    @GetMapping("/findCommodityByCid/{cid}")
    public Commodity findById(@PathVariable("cid")Integer cid){
        if(cid==null){
            return  null;
        }
        return commodityRepository.findById(cid).get();
    }
    @GetMapping("/findCommodityDetailByCid/{cid}")
    public List<Commodity_Detail> findById2(@PathVariable("cid")Integer cid){
        if(cid==null){
            return  null;
        }
        List<Commodity_Detail> commodity_details = commodity_detailRepository.findBycommoditycnumber(cid);
        return commodity_details;
    }

    //返回对应商品的包含sizes的列表
    @GetMapping("/findCommoditySizesByCid/{cid}")
    public List<String> findSizesById(@PathVariable("cid")Integer cid){
        List<String> listS = new ArrayList<String>();
        Commodity_Detail commodity_detail = null;
        List<Commodity_Detail> listC =  commodity_detailRepository.findBycommoditycnumber(cid);
        for (int i=0;i<listC.size();i++) {
            commodity_detail = listC.get(i);
            if (listS.contains(commodity_detail.getCommoditysize())){

            }else {
              listS.add(commodity_detail.getCommoditysize());
            }
        }
        return listS;
    }

    //返回对应商品的包含colors的列表
    @GetMapping("/findCommodityColorsByCid/{cid}")
    public List<String> findColorsById(@PathVariable("cid")Integer cid){
        List<String> listS = new ArrayList<String>();
        Commodity_Detail commodity_detail = null;
        List<Commodity_Detail> listC =  commodity_detailRepository.findBycommoditycnumber(cid);
        for (int i=0;i<listC.size();i++) {
            commodity_detail = listC.get(i);
            if (listS.contains(commodity_detail.getCommoditycolorcimage())){

            }else {
                listS.add(commodity_detail.getCommoditycolorcimage());
            }
        }
        return listS;
    }

    //获取库存
    @PostMapping("/findCommodityQuantity")
    public String findAll(@RequestBody Map<String, String> map){

        String colorimg = map.get("colorimg");
        String size = map.get("size");
        List<Commodity_Detail> list =  commodity_detailRepository.findBycommoditycolorcimage(colorimg);
        String a = "";
        for (Commodity_Detail c:list) {
            if (size.equals(c.getCommoditysize())){
                a=c.getCommodity_quantity();
            }
        }
        return a;
    }

    //获取库存
    @PostMapping("/findCommodityQuantity/{colorimg}/{size}")
    public String findAll(@PathVariable("colorimg")String colorimg,@PathVariable("size")String size){
        List<Commodity_Detail> list =  commodity_detailRepository.findBycommoditycolorcimage(colorimg);
        String a = "";
        for (Commodity_Detail c:list) {
            if (size.equals(c.getCommoditysize())){
                a=c.getCommodity_quantity();
            }
        }
        return a;
    }

    @PutMapping("/update")
    public String update(@RequestBody Commodity commodity){
        Commodity commodity1 = commodityRepository.save(commodity);
        if(commodity1!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @DeleteMapping("/deleteCommodityByCname/{cname}")
    public void delete(@PathVariable("cname")String cname){
        commodityRepository.deleteById(commodityRepository.findBycname(cname).get(0).getCid());
    }

    @DeleteMapping("/deleteCommodityById/{cid}")
    public void deleteCommodityById(@PathVariable("cid")Integer cid){
        commodityRepository.deleteById(cid);
        commodity_detailRepository.deleteCommodity_DetailByCommoditycnumber(cid);
    }

    @PostMapping("/CommodityAuthorize/{cname}")
    public Commodity authorize(@PathVariable("cname")String cname){
        Commodity commodity = commodityRepository.findBycname(cname).get(0);
        commodity.setCstatus("已通过");
        commodityRepository.save(commodity);
        return commodity;
    }
    @PostMapping("/CommodityUnAuthorize/{cname}")
    public void CommodityUnAuthorize(@PathVariable("cname")String cname){
        Commodity commodity = commodityRepository.findBycname(cname).get(0);
        commodity.setCstatus("未通过");
        commodityRepository.save(commodity);
    }
    @GetMapping("/juddgeifdup/{cname}")
    public Commodity juddgeifdup(@PathVariable("cname")String cname){
        Commodity commodity = new Commodity();
        commodity.setCid(-1);
        List<Commodity> commodities = commodityRepository.findAll();
        for (int i = 0; i <commodities.size() ; i++) {
            if (commodities.get(i).getCname().equals(cname)){
                commodity = commodities.get(i);
            }
        }
        return commodity;
    }

}
