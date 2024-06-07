package com.ywk.sems.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywk.sems.bean.Employment;
import com.ywk.sems.common.ResultInfo;
import com.ywk.sems.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
@CrossOrigin
public class EmploymentController {

    @Autowired
    private EmploymentService employmentService;


    @GetMapping
    public ResultInfo getUser(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Employment> lqw = new LambdaQueryWrapper<>();
        lqw.like(Employment::getName, query);
        PageInfo<Employment> pageInfo = new PageInfo<>(employmentService.list(lqw));
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo addUser(@RequestBody Employment employment) {
        boolean b = employmentService.save(employment);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @PutMapping
    public ResultInfo updateUser(@RequestBody Employment employment) {
        boolean b = employmentService.updateById(employment);
        return b ? ResultInfo.success() : ResultInfo.error("失败");

    }

    @DeleteMapping
    public ResultInfo deleteUser(Long id) {
        boolean b = employmentService.removeById(id);
        return b ? ResultInfo.success() : ResultInfo.error("失败");

    }
}
