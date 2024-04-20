package com.zhanglu.bootruan78.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
//一般类名和表名一致 方便后面操作数据库
//不一致加注释设置成关联对应关系
@TableName("user78")//具体操作的表名
@Data//自动生成属性的getset方法
public class User78 {
    //类的属性和表的字段名 保持一致
    @TableId(type= IdType.AUTO)
    private int id;
    private String name;
   private int sex;
    //alt+ins 选择getset
//当前类里面的属性默认是表的字段对应
//    不是表的字段 告诉框架 这个不是字段
    @TableField(exist = false)
    private  int page=1;
    @TableField(exist = false)
    private  int size=3;


}
