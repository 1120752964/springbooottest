package com.ldl.springboottest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Order_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderdetailid;
    private Integer commoditycnumber;
    private String orderonumber;
    private String commoditycolor;
    private String commoditycount;
    private String commoditysize;
    private String ordercommoditycount;
    private String commoditystatus;
    private String commodityname;
    private String commoditypriceforone;
}
