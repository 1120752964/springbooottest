package com.ldl.springboottest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Comment {
//对商品的评论
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentid;
    private Integer commodityid;
    private String commentcontent;
    private String commentusernumber;
    private Integer gradetocommdity;
    private String createdtime;
    private String commentusername;

}
