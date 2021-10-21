package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Commodity;
import com.ldl.springboottest.entity.Commodity_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Commodity_DetailRepository extends JpaRepository<Commodity_Detail,Integer> {
    List<Commodity_Detail> findBycommoditycnumber(Integer v);//findBycnameLike
    List<Commodity_Detail> findBycommoditycolorcimage(String v);//findBycnameLike
    List<Commodity_Detail> findBycommoditysize(String v);//findBycnameLike
    @Modifying
    @Transactional
    List<Commodity_Detail> deleteCommodity_DetailByCommoditycnumber(Integer v);//findBycnameLike
//    List<Commodity> findBycnameLike(String cname);
}
