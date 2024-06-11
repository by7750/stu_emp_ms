package com.ywk.sems.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywk.sems.bean.Student;
import com.ywk.sems.common.ResultInfo;
import com.ywk.sems.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stu")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResultInfo students(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.like(Student::getName, query);
        PageInfo<Student> pageInfo = new PageInfo<>(studentService.list(lqw));
        return ResultInfo.success(pageInfo);
    }

    @GetMapping("/no/{stuNo}")
    public ResultInfo student(@PathVariable String stuNo) {
        Student stu = studentService.getByNo(stuNo);
        return stu == null ? ResultInfo.error("不存在") : ResultInfo.success(stu);
    }

    @PostMapping
    public ResultInfo addStudent(@RequestBody Student student) {
        boolean b = studentService.save(student);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @PutMapping
    public ResultInfo updateStudent(@RequestBody Student student) {

        boolean b = studentService.updateById(student);
        return b ? ResultInfo.success() : ResultInfo.error("失败");

    }

    @DeleteMapping("/{id}")
    public ResultInfo deleteStudent(@PathVariable Long id) {
        boolean b = studentService.removeById(id);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @GetMapping("/check")
    public ResultInfo check(Student student) {
        return studentService.checkNo(student) ? ResultInfo.success() : ResultInfo.error("已存在");
    }
}
