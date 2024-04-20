package com.zhanglu.bootruan78.model;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
import lombok.Data;
@Data
public class Member {

    private Integer id;
    private Integer sex;//'1男 2 女'
    private String tel;
    private String email;
    private String address;
    private Integer status;//(1启用 0 停用)
    private String city;
    private String nickname;
    private String sign;


}
