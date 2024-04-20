package com.zhanglu.bootruan78.controller;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.javafx.collections.ElementObservableListDecorator;
import com.zhanglu.bootruan78.dao.MemberDao;
import com.zhanglu.bootruan78.dao.UserinfoDao;
import com.zhanglu.bootruan78.dto.EchartData;
import com.zhanglu.bootruan78.dto.UserMember;
import com.zhanglu.bootruan78.model.Member;
import com.zhanglu.bootruan78.model.Users;
import com.zhanglu.bootruan78.vo.QueryMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    UserinfoDao userinfoDao;
    @RequestMapping("/login")
    public Object login(Users users){
        System.out.println(users);
        QueryWrapper<Users> query =new QueryWrapper<>();
        query.eq("username",users.getUsername());
        query.eq("password",users.getPassword());
//        userinfoDao.selectOne(null);select * from users;
//        userinfoDao.selectOne(null);select * from users where username=admin and password=123456;
//        selectOne 返回的是实体类
       Users one = userinfoDao.selectOne(query);
       return one;
    }
//    http://localhost:8080/user/login

    @RequestMapping("/addmember")
    public String addmember(Users users, Member member){
        System.out.println(users);
        System.out.println(member);
//        1.不能让用户表里面name重复 判断name是否已存在表中
//        select count from user where username =张晓
        QueryWrapper<Users> query =new QueryWrapper<>();
        query.eq("username",users.getUsername());
        Integer count =userinfoDao.selectCount(query);
        if(count > 0){//有返回值说明用户名已存在 请重新输入
            return "用户名已存在 请重新输入!!!";

        }
//        2.添加用户表数据
        users.setType(2);//默认是会员
        users.setCreateTime(new Date());
        //id自增 设置自增策略
        userinfoDao.insert(users);//自动生成 insert into users
//        3.添加会员表数据
//        用户和会员 1对1 直接把用户自增id 复制给会员id
        member.setId(users.getId());
        member.setStatus(1);//1 表示启用状态 也就是正常状态
        memberDao.insert(member);

        return "添加数据成功!!!!";
    }
    @Resource
    MemberDao memberDao;

    @RequestMapping("/querylist")
    public Map<String,Object> querylist(QueryMember queryMember){
        System.out.println(queryMember);
        Map<String,Object> map = new HashMap<>();
//        通过链表查询 用户数据 会员数据
//        添加querywripper参数
        QueryWrapper<QueryMember> query =new QueryWrapper<>();
//        会员列表 只查询会员数据
        query.eq("u.type",2);//2 代表会员
//      添加排序生成
        query.orderByAsc("u.id");
//        判断用户名是否为空 不为空声生成对象sql模糊查询

        if(StringUtils.isNotBlank(queryMember.getUsername()))
        {
            query.like("u.username",queryMember.getUsername());
        }
        if(StringUtils.checkValNotNull(queryMember.getStartdate()))
        {
//            大于等于开始时间
            query.ge("u.create_time",queryMember.getStartdate());
        }
        if(StringUtils.checkValNotNull(queryMember.getEnddate()))
        {
//            小于等于结束时间
            query.le("u.create_time",queryMember.getEnddate());
        }
//select * from users u left join member m on u.id=m.id
//       WHERE (u.type = ?) ORDER BY u.id ASC
//        @Param(Constants.WRAPPER)如果想在自定义sql中 shouxiesql里面让mybtis
//       List<UserMember> mlist = userinfoDao.querylist(query);
        //      mybatis 自带的分页插件 自动生成limit语句
        Page<QueryMember> page = new Page<>(queryMember.getPage(),queryMember.getSize());
        Page<UserMember> usersPage =userinfoDao.querylist(page,query);
        map.put("list",usersPage.getRecords());//对比之前的mlist 查询的列表集合数据
        map.put("total",usersPage.getTotal());//查询到的总条数
//        map.put("list",mlist);
        return map;
    }
//    http://localhost:8080/user/querylist
    @RequestMapping("/updateStatus")
    public Integer updateStatus(Integer memberid,Integer memberstatus){
        System.out.println(memberid+"     "+memberstatus);
        Member m=new Member();
        m.setId(memberid);
//        判断原来的状态 然后再改状态
        if(memberstatus == 1){//停用
            m.setStatus(0);

        }else{//启用
            m.setStatus(1);
        }
        int update = memberDao.updateById(m);
        return update;
    }

    @RequestMapping("/delMember")
    public Integer delMember(Integer id){
//    public String delMember(Integer id){
//       1. 删除对应的用户
        userinfoDao.deleteById(id);
//       2. 删除对应的会员
        int delete=memberDao.deleteById(id);
//        if(delete >0){
////            return "success";
//            return "删除数据成功！！！";
//        }else{
////            return  "fail";
//            return "删除数据失败";
//        }
        return delete;
    }
    @RequestMapping("/updateMember")
    public Integer updateMember(Member member){

        return memberDao.updateById(member);
    }
    @RequestMapping("/updatePwd")
    public Integer updatePwd(Users users){
        System.out.println(users);
        return userinfoDao.updateById(users);
    }
    @RequestMapping("/delAll")
    public Integer delAll(Integer[] ids) {
//     Arrays.asList(ids)数据转list
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(ids));
        List<Integer> list = Arrays.asList(ids);
        list.forEach((a) -> System.out.println(a));
//        Set<Integer> set = new HashSet<>();
//        批量删除用户信息
        userinfoDao.deleteBatchIds(list);
//        批量删除会员信息
        return memberDao.deleteBatchIds(list);
    }
@RequestMapping("/getdata")
    public List<EchartData> getdata(){
        List<Member> members =memberDao.selectList(null);
        Random random=new Random();

        List<EchartData> list = new ArrayList<>();
        members.forEach((m) ->{
            EchartData e=new EchartData();
//            把城市名称 给到name属性
//            就是取值赋值
            e.setName(m.getCity());
//            value 值 用一个随机函数生成
            int value = random.nextInt(100);
            e.setValue(value);
//            集合添加元素
            list.add(e);
        });
        return list;
    }
}

