package com.ywk.sems.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywk.sems.bean.Employment;
import com.ywk.sems.bean.Student;
import com.ywk.sems.common.ResultInfo;
import com.ywk.sems.service.EmploymentService;
import com.ywk.sems.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
@CrossOrigin
public class EmploymentController {

    @Autowired
    private EmploymentService employmentService;

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResultInfo getEmp(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Employment> lqw = new LambdaQueryWrapper<>();
        lqw.like(Employment::getName, query);
        PageInfo<Employment> pageInfo = new PageInfo<>(employmentService.list(lqw));
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo addEmp(@RequestBody Employment employment) {
        System.out.println(employment);
        return saveOne(employment, true);
    }

    @PutMapping
    public ResultInfo updateEmp(@RequestBody Employment employment) {

        return saveOne(employment, false);
    }

    private ResultInfo saveOne(Employment employment, boolean isAdd) {

        Student one = studentService.getByNo(employment.getStuNo());
        System.out.println(employment);
        if (one == null && isAdd) {
            return ResultInfo.error("学号不存在");
        }
        if (isAdd) {
            employment.setSid(one.getId());
            employment.setName(one.getName());
        }
        boolean b = isAdd ? employmentService.save(employment) : employmentService.updateById(employment);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @DeleteMapping("/{id}")
    public ResultInfo delEmp(@PathVariable Long id) {
        boolean b = employmentService.removeById(id);
        return b ? ResultInfo.success() : ResultInfo.error("失败");

    }
}
