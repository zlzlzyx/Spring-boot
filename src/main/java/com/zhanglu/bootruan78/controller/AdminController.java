package com.zhanglu.bootruan78.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhanglu.bootruan78.dao.AdminDao;
import com.zhanglu.bootruan78.dao.UserinfoDao;
import com.zhanglu.bootruan78.dto.UserAdmin;
import com.zhanglu.bootruan78.dto.UserMember;
import com.zhanglu.bootruan78.model.Admin;
import com.zhanglu.bootruan78.model.Users;
import com.zhanglu.bootruan78.vo.QueryAdmin;
import com.zhanglu.bootruan78.vo.QueryMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 * @Date 2023/12/3 21:27
 */
@RestController
@RequestMapping("/user")
public class AdminController {
    @Resource
    UserinfoDao userinfoDao;
    @Resource
    AdminDao adminDao;
//    显示全部管理员
    @RequestMapping("/querylist2")
    public Map<String,Object> querylist2(QueryAdmin queryAdmin){
        System.out.println(queryAdmin);
        Map<String,Object> map = new HashMap<>();
//        通过链表查询 管理员数据
//        添加querywripper参数
        QueryWrapper<QueryAdmin> query =new QueryWrapper<>();
//        管理员列表 只查询管理员数据
        query.eq("u.type",1);//1 代表管理员
//      添加排序生成
        query.orderByAsc("u.id");
//        判断用户名是否为空 不为空生成对象sql模糊查询

        if(StringUtils.isNotBlank(queryAdmin.getUsername()))
        {
            query.like("u.username",queryAdmin.getUsername());
        }
        if(StringUtils.checkValNotNull(queryAdmin.getStartdate()))
        {
//            大于等于开始时间
            query.ge("u.create_time",queryAdmin.getStartdate());
        }
        if(StringUtils.checkValNotNull(queryAdmin.getEnddate()))
        {
//            小于等于结束时间
            query.le("u.create_time",queryAdmin.getEnddate());
        }
//select * from users u left join member m on u.id=m.id
//       WHERE (u.type = ?) ORDER BY u.id ASC
//        @Param(Constants.WRAPPER)如果想在自定义sql中 shouxiesql里面让mybtis
//       List<UserMember> mlist = userinfoDao.querylist(query);
        //      mybatis 自带的分页插件 自动生成limit语句
        Page<QueryAdmin> page = new Page<>(queryAdmin.getPage(),queryAdmin.getSize());
        Page<UserAdmin> usersPage =userinfoDao.querylist2(page,query);
        map.put("list",usersPage.getRecords());//对比之前的mlist 查询的列表集合数据
        map.put("total",usersPage.getTotal());//查询到的总条数
//        map.put("list",mlist);
        return map;
    }
//    添加管理员
    @RequestMapping("/addadmin")
    public String addadmin(Users users, Admin admin){
        System.out.println(users);
        System.out.println(admin);
//        新添加的管理员用户名不能重复
        QueryWrapper<Users> query =new QueryWrapper<>();
        query.eq("username",users.getUsername());
        Integer count =userinfoDao.selectCount(query);
        if(count > 0){//有返回值说明用户名已存在 请重新输入
            return "用户名已存在 请重新输入!!!";

        }
//        2.添加用户表数据
        users.setType(1);//默认是会员
        users.setCreateTime(new Date());
        //id自增 设置自增策略
        userinfoDao.insert(users);//自动生成 insert into users
//        3.添加会员表数据
//        用户和会员 1对1 直接把用户自增id 复制给会员id
        admin.setId(users.getId());
        admin.setStatus(1);//1 表示启用状态 也就是正常状态
        adminDao.insert(admin);

        return "添加数据成功!!!!";
    }
//    修改状态
    @RequestMapping("/a_updateStatus")
    public Integer a_updateStatus(Integer adminid,Integer adminstatus){
        System.out.println(adminid+"     "+adminstatus);
        Admin a=new Admin();
        a.setId(adminid);
//        判断原来的状态 然后再改状态
        if(adminstatus == 1){//停用
            a.setStatus(0);

        }else{//启用
            a.setStatus(1);
        }
        int update = adminDao.updateById(a);
        return update;

    }
//  修改管理员信息
    @RequestMapping("/updateAdmin")
    public Integer updateAdmin(Admin admin){
        return adminDao.updateById(admin);
    }
//    修改密码
    @RequestMapping("/a_updatePwd")
    public Integer a_updatePwd(Users users){
        System.out.println(users);
        return userinfoDao.updateById(users);
    }
//    删除用户
    @RequestMapping("/delAdmin")
    public Integer delAdmin(Integer id){
//        1.删除对应的用户
        userinfoDao.deleteById(id);
//        2.删除对应的管理员
        int delete = adminDao.deleteById(id);
        return delete;
    }
//    批量删除
    @RequestMapping("/a_delAll")
    public Integer a_delAll(Integer[] ids){
        List<Integer> list = Arrays.asList(ids);
        list.forEach((a) -> System.out.println(a));
//        Set<Integer> set = new HashSet<>();
//        批量删除用户信息
        userinfoDao.deleteBatchIds(list);
//        批量删除会员信息
        return adminDao.deleteBatchIds(list);
    }
}
