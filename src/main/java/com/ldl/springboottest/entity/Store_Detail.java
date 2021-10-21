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
public class Store_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer store_commodity_id;
    private Integer commodity_cnumber;
    private String store_sname;

}
