package com.ywk.sems.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

import java.util.Date;

/**
 * @TableName employment
 */
@TableName("employment")
@Data
public class Employment implements Serializable {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    @TableField("s_id")
    private Long sid;

    private String company;

    private Date startDate;

    private Date endDate;

    private String contract;

    @TableField(exist = false)
    private String stuNo;


}
