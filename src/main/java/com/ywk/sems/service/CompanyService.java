package com.ywk.sems.service;

import com.ywk.sems.bean.Company;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yao
* @description 针对表【company】的数据库操作Service
* @createDate 2024-06-16 11:05:17
*/
public interface CompanyService extends IService<Company> {

    boolean checkNo(Company company);

    Company getByNo(String cno);
}
