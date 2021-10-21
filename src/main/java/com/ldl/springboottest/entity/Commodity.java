package com.ldl.springboottest.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String cname;
    private String coldprice;
    private String cnowprice;
    private String csales;
    private String caddress;
    private String cquantity;
    private String cimages;
    private String cstatus;
    private String storename;

}
