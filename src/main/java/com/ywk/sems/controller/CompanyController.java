package com.ywk.sems.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywk.sems.bean.Company;
import com.ywk.sems.common.ResultInfo;
import com.ywk.sems.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yao
 * @version 1.0
 * @date 2024/6/16 - 11:06
 * @className CompanyController
 * @since 1.0
 */
@RestController
@RequestMapping("/comp")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResultInfo companies(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Company> lqw = new LambdaQueryWrapper<>();
        lqw.like(Company::getName, query);
        PageInfo<Company> pageInfo = new PageInfo<>(companyService.list(lqw));
        return ResultInfo.success(pageInfo);
    }

    @GetMapping("/no/{stuNo}")
    public ResultInfo company(@PathVariable String stuNo) {
        Company stu = companyService.getByNo(stuNo);
        return stu == null ? ResultInfo.error("不存在") : ResultInfo.success(stu);
    }

    @PostMapping
    public ResultInfo addCompany(@RequestBody Company Company) {
        boolean b = companyService.save(Company);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @PutMapping
    public ResultInfo updateCompany(@RequestBody Company Company) {

        boolean b = companyService.updateById(Company);
        return b ? ResultInfo.success() : ResultInfo.error("失败");

    }

    @DeleteMapping("/{id}")
    public ResultInfo deleteCompany(@PathVariable Long id) {
        boolean b = companyService.removeById(id);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @GetMapping("/check")
    public ResultInfo check(Company Company) {
        return companyService.checkNo(Company) ? ResultInfo.success() : ResultInfo.error("已存在");
    }
}
