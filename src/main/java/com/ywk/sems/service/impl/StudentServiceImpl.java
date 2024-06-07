package com.ywk.sems.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywk.sems.bean.Student;
import com.ywk.sems.bean.User;
import com.ywk.sems.service.StudentService;
import com.ywk.sems.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author yao
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-06-06 23:51:10
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Override
    public boolean checkNo(Student student) {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Student::getNo, student.getNo());
        Student s = getOne(lqw);
        if (student.getId() == null) {
            return s == null;
        }
        // 修改需要判断是否未修改用户名
        return s == null || (s.getId().equals(student.getId()) && s.getNo().equals(student.getNo()));
    }
}




