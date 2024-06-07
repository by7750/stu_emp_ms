package com.ywk.sems.mapper;

import com.ywk.sems.bean.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yao
* @description 针对表【student】的数据库操作Mapper
* @createDate 2024-06-06 23:51:10
* @Entity com.ywk.sems.bean.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




