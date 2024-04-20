package com.zhanglu.bootruan78.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 * @Date 2023/12/3 21:20
 */
@Data
public class QueryAdmin {
    private String username;
    private Integer page;
    private Integer size;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enddate;
}
