package com.ldl.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String uname;
    private String unumber;
    private String upassword;
    private String usex;
    private String utelphone;
    private String upaypassword;
    private String uaddress;
}
