package com.zhanglu.bootruan78.dto;

import com.zhanglu.bootruan78.model.Admin;
import com.zhanglu.bootruan78.model.Users;
import lombok.Data;

/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 * @Date 2023/12/3 21:21
 */
@Data
public class UserAdmin {
    private Users users;
    private Admin admin;
}
