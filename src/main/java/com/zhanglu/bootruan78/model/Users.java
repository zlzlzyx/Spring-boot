package com.zhanglu.bootruan78.model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@Data
public class Users {
    @TableId(type = IdType.AUTO)//Id自增
    private Integer id;
    private String username;
    private String password;
//   默认开启驼峰转换 大小写 下划线后面首字母大小写 去掉下划线
//    pattern 事件格式 yyyy年-MM月-dd日 HH小时:mm分:ss秒
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss ",timezone = "GMT+8")
    private Date createTime;
    private Integer type;//'类型1 管理员 2 会员'
}
