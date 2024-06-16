package com.ywk.sems.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywk.sems.bean.User;
import com.ywk.sems.common.ResultInfo;
import com.ywk.sems.service.UserService;
import com.ywk.sems.utils.MD5Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultInfo login(@RequestBody User user, HttpSession session) {
        user.setPassword(MD5Utils.md5(user.getPassword()));
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        User u = userService.getOne(lqw);
        if (u == null) {
            return ResultInfo.error("用户名或密码错误");
        }
        session.setAttribute("user", u);
        ResultInfo<User> success = ResultInfo.success(u);
        Map<String, Object> map = new HashMap<>();
        map.put("token", user.getUsername() + "=" + user.getPassword());
        success.setInfo(map);
        return success;
    }

    @GetMapping("/logout")
    public ResultInfo logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResultInfo.success();
    }

    @PostMapping("/register")
    public ResultInfo register(@RequestBody User user) {
        if (user.getUsername() == null || "".equals(user.getUsername().trim()) || user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return ResultInfo.error("用户名和密码不能为空");
        }
        user.setPassword(MD5Utils.md5(user.getPassword()));
        boolean b = userService.save(user);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @GetMapping
    public ResultInfo getUser(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(User::getRealName, query);
        PageInfo<User> pageInfo = new PageInfo<>(userService.list(lqw));
        return ResultInfo.success(pageInfo);
    }

    @GetMapping("/{id}")
    public ResultInfo getUser(@PathVariable Long id) {

        return ResultInfo.success(userService.getById(id));
    }

    @GetMapping("/avatar")
    public ResultInfo getUser(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username);
        User one = userService.getOne(lqw);
        if (one != null) {
            return ResultInfo.success(one.getAvatar());
        }
        return ResultInfo.error("");
    }

    @PostMapping
    public ResultInfo addUser(@RequestBody User user) {
        user.setPassword(MD5Utils.md5(user.getPassword()));
        boolean b = userService.save(user);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @PutMapping
    public ResultInfo updateUser(@RequestBody User user) {
        System.out.println(user);
        if (!"".equals(user.getPassword()) && user.getPassword() != null) {
            user.setPassword(MD5Utils.md5(user.getPassword()));
        }
        boolean b = userService.updateById(user);
        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @DeleteMapping("/{id}")
    public ResultInfo deleteUser(@PathVariable Long id) {
        boolean b = userService.removeById(id);

        return b ? ResultInfo.success() : ResultInfo.error("失败");
    }

    @GetMapping("/check")
    public ResultInfo checkUser(User user) {
        return userService.checkUser(user) ? ResultInfo.success() : ResultInfo.error("已存在");
    }

}
