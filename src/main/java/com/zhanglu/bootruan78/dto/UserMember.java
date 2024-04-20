package com.zhanglu.bootruan78.dto;

import com.zhanglu.bootruan78.model.Member;
import com.zhanglu.bootruan78.model.Users;
import lombok.Data;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@Data
public class UserMember {
    private Users users;
//    private Member member =new Member();
    private Member member;
}
