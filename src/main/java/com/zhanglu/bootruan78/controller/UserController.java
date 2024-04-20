package com.zhanglu.bootruan78.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanglu.bootruan78.dao.UserDao;
import com.zhanglu.bootruan78.model.User78;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@Controller
//@RestController=@Controller +@responsebody
public class UserController {
    //teymeleaf 帮你配置好 文件的路径 前缀 templates文件夹和后缀.html
    @RequestMapping("/index1")//url
    public String index2(){//方法名
        return "index3";//对应的html文件名

    }
//    模拟登陆 后端拿到前端的数据进行验证 并返回结果
    @RequestMapping("/login")//url
//    public String login(String name,Integer sex){
//   假设业务复杂 当前接口 需要接收参数有几个
//    每个方法的参数可能都是几个
    public String login(User78 user78){
        System.out.println(user78.getName()+"......"+user78.getSex());
//        if条件同时满足就进入if语句 否则进入else语句
//        “admin" 1 当成数据库表里面存在的数据
//        if("admin".equals(user78.getName()) && 1==user78.getSex()){
//            return "success";
//        }else{
//            return "fail";
//        }
//        select * from user where name=admin and sex=2
//        List<User78> list=userDao.selectList(null);
//        list.forEach(u-> System.out.println(u.getName()+" "+u.getSex()));
//        通过生成where条件的sql来查询表 如果name和sex的数据存在表中 那么就返回对应的条数
//        userDao.selectCount(null);
        QueryWrapper<User78> query=new QueryWrapper<>();
//    select count(1) from user78 where (name =?)
        query.eq("name",user78.getName());
        query.eq("sex",user78.getSex());
//        userDao.selectCount(query);
        Integer integer= userDao.selectCount(query);
     if(integer >0){
       return "success";
       }else{
           return "fail";
       }
//        return "success";
    }
//http://localhost:8080/index1
//注入方式
    @Resource
    UserDao userDao;
//    int a;
@RequestMapping("/update")
    public String update(User78 user78){
    System.out.println(user78.getId());
    int update=userDao.updateById(user78);
    if(update >0){
        return "success";
    }else{
        return  "fail";
    }
}

    @RequestMapping("/delete")
    public String delete(User78 user78){
        System.out.println(user78.getId());
        int update=userDao.deleteUser(user78.getId());
        if(update >0){
            return "success";
        }else{
            return  "fail";
        }
    }

    @RequestMapping("/adduser")
    public String addUser(User78 user78){
        System.out.println(user78);
        int add=userDao.adduser(user78);
        if(add >0){
            return "success";
        }else{
            return  "fail";
        }
    }
//1.下载依赖
//2、配置连接信息
//3、创建mybaits接口和实体类
//4、扫描端口连接







//    @ResponseBody
//    public Integer a(){
//        return 10;
//    }
}
