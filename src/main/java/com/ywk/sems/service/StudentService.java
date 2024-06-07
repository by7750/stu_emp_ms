package com.ywk.sems.service;

import com.ywk.sems.bean.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yao
* @description 针对表【student】的数据库操作Service
* @createDate 2024-06-06 23:51:10
*/
public interface StudentService extends IService<Student> {

    boolean checkNo(Student student);
}
