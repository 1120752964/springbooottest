package com.ldl.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User_Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer useraddressid;
    private String isdefault;
    private String userunumber;
    private String useraddress;
    private String consigneename;
    private String phonenumber;
}
