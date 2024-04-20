package com.zhanglu.bootruan78.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanglu.bootruan78.model.User78;
import org.apache.ibatis.annotations.Delete;

import java.util.List;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
//mybatis 就会找到类名一致的数据库表
public interface UserDao extends BaseMapper<User78> {
//手写sql 注释的方式
    @Delete("delete from user78 where id= #{id}")
    int deleteUser(int id);


//    2 xml文件 sql
int adduser(User78 user78);

    List<User78> querylist(User78 user78);
}
