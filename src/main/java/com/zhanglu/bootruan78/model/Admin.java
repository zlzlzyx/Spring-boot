package com.zhanglu.bootruan78.model;

import lombok.Data;

/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 * @Date 2023/11/28 19:43
 */
@Data
public class Admin {
    private Integer id;
    private String nickname;
    private Integer sex;//'1男 2 女'
    private String tel;
    private String email;
    private String rolename;//3种 1.超级管理员 2.编辑人员 3.问题维护
    private Integer status;//(1启用 0 停用)
}
