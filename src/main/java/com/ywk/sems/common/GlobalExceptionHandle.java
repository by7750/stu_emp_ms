package com.ywk.sems.common;

import com.ywk.sems.exceptions.AuthException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author yao
 * @version 1.0
 * @date 2022/9/27 - 9:39
 * @since 1.0
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandle {


    @ExceptionHandler(AuthException.class)
    public ResultInfo exceptionHandle(AuthException e) {
        e.printStackTrace();
        return ResultInfo.init(400, e.getMessage());
    }

    /**
     * 处理运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultInfo exceptionHandle(RuntimeException e) {
        e.printStackTrace();
        return ResultInfo.error("网络不好，请稍后重试！");
    }

}
