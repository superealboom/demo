package com.example.loadbalance.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 *  @Author: tianci
 *  @Date: 2022/4/7 13:35
 *  @Description: 统一返回类，返回给前端看的
 */
@Data
public class R {

    private Boolean success;
    private Integer code;
    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();



    /**
     *  @Author: tianci
     *  @Date: 2022/4/7 13:38
     *  @Description: 构造方法私有化
     */
    private R() {}



    /**
     *  @Author: tianci
     *  @Date: 2022/4/7 13:38
     *  @Description: 返回成功
     */
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMessage());
        return r;
    }



    /**
     *  @Author: tianci
     *  @Date: 2022/4/7 13:38
     *  @Description: 返回失败
     */
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultEnum.ERROR.getCode());
        r.setMessage(ResultEnum.ERROR.getMessage());
        return r;
    }



    /**
     *  @Author: tianci
     *  @Date: 2022/4/7 13:39
     *  @Description: 赋值给R对象
     */
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
    public R message(String message) {
        this.setMessage(message);
        return this;
    }
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
