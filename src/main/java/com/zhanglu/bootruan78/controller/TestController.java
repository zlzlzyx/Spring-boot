package com.zhanglu.bootruan78.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
//开发命名的规范
//通过注解标明当前接口 为对外开放
@RestController
public class TestController {

    //ip 端口 url路径
//    要对当前方法进行设置 web服务器里面 对应url web的请求路径
//   helloword hello RequestMapping 注解 映射处理器 会把这个路径和方法建立一个关联关系
    @RequestMapping("/hello")
    public Integer hello(){
        int a=10*5;
        return a;
    }
//    type=not found.status=404;
//    http://localhost:8080/helloworld
//    http://localhost:8080/hello
//    获取参数
@RequestMapping("/hello2")
//    public String hello2(@RequestParam("ids") Integer id,String name){
    public String hello2(Integer id,String name){
        System.out.println("id:"+id+" name:"+name);
        return "接收到的参数是："+id+name;

    }
//    第一 要传参
//    http://localhost:8080/hello2?id=3
//    参数名称和变量编码保持一致
//    url 和参数之间同?隔开
//    多个参数之间用&符号隔开
//    http://localhost:8080/hello2?id=3&name=admin
}
