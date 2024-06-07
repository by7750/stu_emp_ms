package com.ywk.sems.common;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果
 *
 * @author yao
 * @version 1.0
 * @date 2022/9/26 - 20:28
 * @since 1.0
 */

@Component
@Data
@ToString
public class ResultInfo<T> {

    private Integer status;

    private String msg;

    private T data;

    private Map<String, Object> info = new HashMap();

    /**
     * 添加自定义信息
     *
     * @param key   键名
     * @param value 值
     */
    public void put(String key, Object value) {
        info.put(key, value);
    }

    /**
     * @param key
     * @return
     */
    public Object get(String key) {
        return info.get(key);
    }

    /**
     * 成功返回数据
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> success(T t) {
        ResultInfo<T> result = success();
        result.setData(t);
        return result;
    }

    public static <T> ResultInfo<T> success() {
        ResultInfo<T> result = new ResultInfo<>();
        result.setStatus(200);
        result.setMsg("OK");
        return result;
    }


    /**
     * 失败返回错误信息
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(String msg) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }


    /**
     * 初始化返回结果
     *
     * @param status 状态码
     * @param msg    返回信息
     * @param <T>
     * @return 响应信息对象
     */
    public static <T> ResultInfo<T> init(int status, String msg) {
        ResultInfo<T> r = new ResultInfo<>();
        r.setStatus(status);
        r.setMsg(msg);
        return r;
    }


}
