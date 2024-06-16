package com.ywk.sems.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywk.sems.bean.Company;
import com.ywk.sems.bean.Student;
import com.ywk.sems.service.CompanyService;
import com.ywk.sems.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

/**
 * @author yao
 * @description 针对表【company】的数据库操作Service实现
 * @createDate 2024-06-16 11:05:17
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>
        implements CompanyService {

    @Override
    public boolean checkNo(Company company) {
        LambdaQueryWrapper<Company> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Company::getNo, company.getNo());
        Company c = getOne(lqw);
        if (company.getId() == null) {
            return c == null;
        }
        // 修改需要判断是否未修改用户名
        return c == null || (c.getId().equals(company.getId()) && c.getNo().equals(company.getNo()));
    }

    @Override
    public Company getByNo(String sno) {
        LambdaQueryWrapper<Company> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Company::getNo, sno);
        Company one = getOne(lqw);
        return one;
    }
}




