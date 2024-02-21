package com.superealboom.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * IdType.ASSIGN_ID: Long,Integer,String 默认雪花算法
 * IdType.ASSIGN_UUID: String，32位字符串
 * @author: tianci
 * @date: 2022/4/20 15:16
 */
@Data
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    @TableField(fill = FieldFill.INSERT)
    private String email;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
