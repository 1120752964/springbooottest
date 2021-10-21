package com.ldl.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeid;
    private String sname;
    private String storeshotscore;
    private String storenumber;
    private String storepassword;
    private String storeproduce;
    private Integer commentid;
    private String storeaddress;

}
