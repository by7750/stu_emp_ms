package com.ywk.sems.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywk.sems.bean.User;
import com.ywk.sems.service.UserService;
import com.ywk.sems.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author yao
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-06-06 23:51:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public boolean checkUser(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        User u = getOne(lqw);
        if (user.getId() == null) {
            return u == null;
        }
        // 修改需要判断是否未修改用户名
        return u == null || (u.getId().equals(user.getId()) && user.getUsername().equals(u.getUsername()));
    }
}




