package com.ywk.sems.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("company")
public class Company {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;
    private String no;
    private String name;
    private String addr;
    private String email;
    private String intro;
    private String photo;

}
