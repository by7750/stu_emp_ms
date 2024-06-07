package com.ywk.sems.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

import java.util.Date;

/**
* 
* @TableName student
*/
@Data
@TableName("student")
public class Student implements Serializable {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String no;

    private String name;

    private Integer gender;

    private Date birth;

    private String dept;

    private String clas;

    private String email;

    private String resume;

    private String photo;

}
