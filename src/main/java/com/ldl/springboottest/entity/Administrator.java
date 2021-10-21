package com.ldl.springboottest.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private String aname;
    private String anumber;
    private String apassword;
    private String acreate_time;
    private Integer astatus;
}
