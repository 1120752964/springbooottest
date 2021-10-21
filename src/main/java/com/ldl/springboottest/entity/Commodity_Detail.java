package com.ldl.springboottest.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Commodity_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commodity_detail_id;
    private Integer commoditycnumber;
    private String commodity_quantity;
    private String commoditysize;
    private String commoditycolorcimage;
    private String commoditycolortext;
}
