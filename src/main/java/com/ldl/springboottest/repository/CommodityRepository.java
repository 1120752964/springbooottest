package com.ldl.springboottest.repository;

import com.ldl.springboottest.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity,Integer> {
    List<Commodity> findBycname(String cname);//findBycnameLike
    List<Commodity> findBycnameLike(String cname);
    List<Commodity> findBystorename(String store_name);
}
