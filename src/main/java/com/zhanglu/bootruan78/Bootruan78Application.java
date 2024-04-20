package com.zhanglu.bootruan78;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@SpringBootApplication
//通过mybatis的扫描 dao包下面的所有的接口 都有框架来管理 来创建 销毁
@MapperScan("com.zhanglu.bootruan78.dao")
public class Bootruan78Application {

    public static void main(String[] args) {
        SpringApplication.run(Bootruan78Application.class, args);
    }

}
