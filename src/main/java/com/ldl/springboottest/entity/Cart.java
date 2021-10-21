package com.ldl.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartid;
    private Integer commodityid;
    private String userunumber;
    private String size;
    private String color;
    private String quantity;
    private String priceforone;
    private String storename;
    private String commodityname;
    private String colorimg;
    private Integer cost;
}
