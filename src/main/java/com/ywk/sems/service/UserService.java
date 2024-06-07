package com.ywk.sems.service;

import com.ywk.sems.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yao
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-06-06 23:51:10
*/
public interface UserService extends IService<User> {
    boolean checkUser(User user);
}
