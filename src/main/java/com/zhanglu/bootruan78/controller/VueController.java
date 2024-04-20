package com.zhanglu.bootruan78.controller;

import com.zhanglu.bootruan78.dao.UserDao;
import com.zhanglu.bootruan78.model.User78;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@Controller
//类路径 url 一级路径
@RequestMapping("/vue")
public class VueController {
//    http://localhost:8080/login url 必须是唯一的 不管在那个页面
//    http://localhost:8080/vue/login 整体路径有区别 就不冲突 可以使用
//    @RequestMapping("/login")
//    http://localhost:8080/vue/tolist
//    二级路径
    @RequestMapping("/tolist")
    public String tolist(){
//        跟上面路径类似 一级user文件夹二级list
        return "user/list";
    }
//    查询表
    @RequestMapping("/list")
//    在controller里面 方法加上这个 表示此接口只返回数据
    @ResponseBody
//    public List<User78> list(User78 user78){  //定义接收参数的类型
        public Map<String,Object> list(User78 user78){
        System.out.println(user78);
//        查询总条数
//        userDao.selectCount(null)
        Integer count = userDao.selectCount(null);

//        querylist 这个不是内置的方法 自己定义的查询方法
        user78.setPage((user78.getPage()-1)*user78.getSize());
        List<User78> list=userDao.querylist(user78);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    @Resource
    UserDao userDao;
//删除
    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(Integer userid){
        return userDao.deleteById(userid);
    }
}
