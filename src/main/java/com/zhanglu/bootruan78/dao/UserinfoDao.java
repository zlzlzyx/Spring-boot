package com.zhanglu.bootruan78.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhanglu.bootruan78.dto.UserAdmin;
import com.zhanglu.bootruan78.dto.UserMember;
import com.zhanglu.bootruan78.model.Users;
import com.zhanglu.bootruan78.vo.QueryAdmin;
import com.zhanglu.bootruan78.vo.QueryMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
public interface UserinfoDao extends BaseMapper<Users> {

//    List<UserMember> querylist(@Param(Constants.WRAPPER) QueryWrapper<QueryMember> query);
Page<UserMember> querylist(Page<QueryMember> page, @Param(Constants.WRAPPER) QueryWrapper<QueryMember> query);

//    Page<UserMember> querylist2(Page<QueryAdmin> page, QueryWrapper<QueryAdmin> query);
Page<UserAdmin> querylist2(Page<QueryAdmin> page, @Param(Constants.WRAPPER) QueryWrapper<QueryAdmin> query);
}
