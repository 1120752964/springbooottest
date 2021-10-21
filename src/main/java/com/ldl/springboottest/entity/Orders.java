package com.ldl.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    private String userunumber;
    private String createdtime;
    private String ocost;
    private String useraddress;
    private String onumber;
    private String consigneename;
    private String phonenumber;
}
